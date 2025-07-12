package com.jocata.service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jocata.dao.LoanApplicationDao;
import com.jocata.entity.*;
import com.jocata.responseform.AadhaarResponse;
import com.jocata.responseform.CibilResponse;
import com.jocata.responseform.PanResponse;
import com.jocata.service.AadhaarService;
import com.jocata.service.CibilService;
import com.jocata.service.DataVerificationService;
import com.jocata.service.PanService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
@Service
public class DataVerificationServiceImpl implements DataVerificationService {
    @Autowired
    private PanService panService;
    @Autowired
    private AadhaarService aadhaarService;
    @Autowired
    private CibilService cibilService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public void getCustomerData(Integer loanApplicationId) {
        try {

            LoanApplications loanApplication = entityManager.find(LoanApplications.class, loanApplicationId);
            if (loanApplication == null) {
                throw new RuntimeException("Loan Application not found with ID: " + loanApplicationId);
            }


            Customers customer = loanApplication.getCustomer();
            if (customer == null) {
                throw new RuntimeException("Customer not found for loan application ID: " + loanApplicationId);
            }


            String panNumber = customer.getPanNumber();
            ResponseEntity<String> response = panService.getPanDetailsByPan(panNumber);
            String responseBody = response.getBody();
            System.out.println("PAN API raw response: " + responseBody);

            if (responseBody.contains("\"status\":\"error\"") || responseBody.contains("\"panDetails\":null")) {
                throw new RuntimeException("PAN not found or invalid PAN response received from API");
            }

            ObjectMapper mapper = new ObjectMapper();
            var root = mapper.readTree(responseBody);
            var panDetailsNode = root.path("panDetails");

            if (panDetailsNode.isMissingNode() || panDetailsNode.isNull()) {
                throw new RuntimeException("PAN details not present in response");
            }


            PanResponse panRes = mapper.treeToValue(panDetailsNode, PanResponse.class);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dobParsed = LocalDate.parse(panRes.getDob(), formatter);
            LocalDate issuedOnParsed = LocalDate.parse(panRes.getIssuedOn(), formatter);

            PanDetails panDetails = new PanDetails();
            panDetails.setPanNumber(panRes.getPanNumber());
            panDetails.setFullName(panRes.getFullName());
            panDetails.setDob(Date.valueOf(dobParsed));
            panDetails.setFatherName(panRes.getFatherName());
            panDetails.setStatus(panRes.getStatus());
            panDetails.setIssuedOn(Date.valueOf(issuedOnParsed));
            panDetails.setGender(panRes.getGender());
            panDetails.setCategory(panRes.getCategory());
            panDetails.setAddress(panRes.getAddress());
            panDetails.setCustomer(customer);

            customer.setPanDetails(panDetails);
            entityManager.persist(panDetails);
            entityManager.merge(customer);

            String aadhaarNumber = customer.getAadhaarNumber();
            ResponseEntity<String> aadhaarResponse = aadhaarService.getAadhaarDetailsByAadhaar(aadhaarNumber);
            String aadhaarResponseBody = aadhaarResponse.getBody();
            System.out.println("Aadhaar API raw response: " + aadhaarResponseBody);

            if (aadhaarResponseBody.contains("\"status\":\"error\"") || aadhaarResponseBody.contains("\"aadhaarDetails\":null")) {
                throw new RuntimeException("Aadhaar not found or invalid Aadhaar response");
            }

            var aadhaarRoot = objectMapper.readTree(aadhaarResponseBody);
            var aadhaarDetailsNode = aadhaarRoot.path("aadhaarDetails");
            if (aadhaarDetailsNode.isMissingNode() || aadhaarDetailsNode.isNull()) {
                throw new RuntimeException("Aadhaar details missing in API response");
            }
            AadhaarResponse aadhaarRes = objectMapper.treeToValue(aadhaarDetailsNode, AadhaarResponse.class);
            var addressNode = aadhaarDetailsNode.path("address");
            if (!addressNode.isMissingNode() && !addressNode.isNull()) {
                aadhaarRes.setStreet(addressNode.path("street").asText());
                aadhaarRes.setCity(addressNode.path("city").asText());
                aadhaarRes.setState(addressNode.path("state").asText());
                aadhaarRes.setPinCode(addressNode.path("pinCode").asText());
            }


            LocalDate aadhaarDobParsed = LocalDate.parse(aadhaarRes.getDob()); // Default ISO_LOCAL_DATE
            AadhaarDetails aadhaarDetails = new AadhaarDetails();
            aadhaarDetails.setAadhaarNumber(aadhaarRes.getAadhaarNumber());
            aadhaarDetails.setName(aadhaarRes.getName());
            aadhaarDetails.setDob(Date.valueOf(aadhaarDobParsed));
            aadhaarDetails.setGender(aadhaarRes.getGender());
            aadhaarDetails.setFatherName(aadhaarRes.getFatherName());
            aadhaarDetails.setStreet(aadhaarRes.getStreet());
            aadhaarDetails.setCity(aadhaarRes.getCity());
            aadhaarDetails.setState(aadhaarRes.getState());
            aadhaarDetails.setPincode(aadhaarRes.getPinCode());
            aadhaarDetails.setCustomer(customer);


            customer.setAadhaarDetails(aadhaarDetails);
            entityManager.persist(aadhaarDetails);

            entityManager.merge(customer);







            ResponseEntity<String> response1 = cibilService.getCiibilByPanNumber(panNumber);
            String responseB = response1.getBody();
            System.out.println("CIBIL API raw response: " + responseB);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseB);

            int cibilScore = rootNode.path("cibilScore").path("score").asInt();

            String creditStatus;
            if (cibilScore > 750) {
                creditStatus = "Good";
            } else if (cibilScore >= 720) {
                creditStatus = "Average";
            } else {
                creditStatus = "Poor";
            }

            JsonNode enquiriesNode = rootNode.path("enquiries");
            int noOfEnquiries = enquiriesNode.isArray() ? enquiriesNode.size() : 0;

            JsonNode accountsNode = rootNode.path("accounts");
            int noOfActiveAccounts = 0;
            BigDecimal loanOutstanding = BigDecimal.ZERO;
            BigDecimal totalCurrentEMI = BigDecimal.ZERO;

            LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            if (accountsNode.isArray()) {
                for (JsonNode account : accountsNode) {
                    String status = account.path("accountStatus").asText();
                    String lastPaymentDateStr = account.path("lastPaymentDate").asText();

                    if ("Active".equalsIgnoreCase(status) && !lastPaymentDateStr.isBlank()) {
                        LocalDate lastPaymentDate = LocalDate.parse(lastPaymentDateStr, formatter1);
                        if (!lastPaymentDate.isBefore(threeMonthsAgo)) {
                            noOfActiveAccounts++;
                            BigDecimal emiAmount = new BigDecimal(account.path("emiAmount").asText("0"));
                            totalCurrentEMI = totalCurrentEMI.add(emiAmount);

                        }

                        BigDecimal balance = BigDecimal.ZERO;
                        try {
                            balance = new BigDecimal(account.path("currentBalance").asText("0"));
                        } catch (NumberFormatException e) {
                        }

                        loanOutstanding = loanOutstanding.add(balance);
                    }
                }
            }

            CibilDetails cibilDetails = new CibilDetails();
            cibilDetails.setCustomer(customer);
            cibilDetails.setCibilScore(cibilScore);
            cibilDetails.setCreditStatus(creditStatus);
            cibilDetails.setLastUpdated(Date.valueOf(LocalDate.now()));
            cibilDetails.setNoOfEnquiries(noOfEnquiries);
            cibilDetails.setNoOfActiveAccounts(noOfActiveAccounts);
            cibilDetails.setLoanOutstanding(loanOutstanding);
            cibilDetails.setCurrentEmi(totalCurrentEMI);
            cibilDetails.setCreatedTimestamp(LocalDateTime.now());

            entityManager.persist(cibilDetails);



        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during KYC verification: " + e.getMessage(), e);
        }
    }

}