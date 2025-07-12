package com.jocata.cibil.service.Impl;

import com.jocata.cibil.dao.*;
import com.jocata.cibil.entity.*;
import com.jocata.cibil.form.*;
import com.jocata.cibil.service.CibilService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CibilServiceImpl implements CibilService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private CibilScoreDao cibilScoreDao;
    @Autowired
    private CreditReportDao creditReportDao;
    @Autowired
    private CustomersDao customersDao;
    @Autowired
    private EnquiriesDao enquiriesDao;
    @Autowired
    private RemarksDao remarksDao;

    @Override
    @Transactional
    public CreditReportResponseForm createCibil(CreditReportRequestForm request) {
        String panNumber = request.getCustomer().getPanNumber();
        Customers existingCustomer = customersDao.findByPan(panNumber);

        CreditReports report;

        if (existingCustomer != null && existingCustomer.getCreditReport() != null) {
            report = existingCustomer.getCreditReport(); // reuse existing
        } else {
            report = new CreditReports();
            report.setGeneratedOn(LocalDateTime.now());
            creditReportDao.create(report);
        }


        Customers customer;
        Addresses address;
        CibilScores score;
        List<Accounts> accountList = new ArrayList<>();
        List<Enquiries> enquiryList = new ArrayList<>();
        List<Remarks> remarkList = new ArrayList<>();

        if (existingCustomer != null) {
            customer = existingCustomer;
            customer.setCreditReport(report);
            customersDao.update(customer);

            address = customer.getAddress();

            score = cibilScoreDao.findByReportId(customer.getCreditReport().getReportId());
            if (score == null) score = new CibilScores();
            score.setScore(Integer.valueOf(request.getCibilScore().getScore()));
            score.setScoreDate(Date.valueOf(request.getCibilScore().getScoreDate()));
            score.setRiskGrade(request.getCibilScore().getRiskGrade());
            score.setCreditReport(report);
            cibilScoreDao.create(score);

            if (request.getAccounts() != null) {
                for (AccountsRequestForm af : request.getAccounts()) {
                    Accounts account = accountDao.findByAccountNumber(af.getAccountNumber());
                    if (account == null) account = new Accounts();

                    account.setAccountNumber(af.getAccountNumber());
                    account.setAccountType(af.getAccountType());
                    account.setMemberName(af.getMemberName());
                    account.setOwnership(af.getOwnership());
                    account.setDateOpened(Date.valueOf(af.getDateOpened()));
                    account.setLastPaymentDate(Date.valueOf(af.getLastPaymentDate()));
                    account.setCurrentBalance(new BigDecimal(af.getCurrentBalance()));
                    account.setCreditLimit(new BigDecimal(af.getCreditLimit()));
                    account.setSanctionedAmount(new BigDecimal(af.getSanctionedAmount()));
                    account.setEmiAmount(new BigDecimal(af.getEmiAmount()));
                    account.setTenureMonths(Integer.parseInt(af.getTenureMonths()));
                    account.setPaymentHistory(af.getPaymentHistory());
                    account.setAccountStatus(af.getAccountStatus());
                    account.setCreditReport(report);

                    accountDao.createOrUpdate(account);
                    accountList.add(account);
                }
            }

            if (request.getEnquiries() != null) {
                for (EnquiriesRequestForm ef : request.getEnquiries()) {
                    Enquiries enquiry = new Enquiries();
                    enquiry.setEnquiryDate(Date.valueOf(ef.getEnquiryDate()));
                    enquiry.setMemberName(ef.getMemberName());
                    enquiry.setEnquiryPurpose(ef.getEnquiryPurpose());
                    enquiry.setEnquiryAmount(new BigDecimal(ef.getEnquiryAmount()));
                    enquiry.setCreditReport(report);
                    enquiriesDao.create(enquiry);
                    enquiryList.add(enquiry);
                }
            }

            if (request.getRemarks() != null) {
                for (RemarksRequestForm rf : request.getRemarks()) {
                    Remarks remark = new Remarks();
                    remark.setDescription(rf.getDescription());
                    remark.setCreditReport(report);
                    remarksDao.create(remark);
                    remarkList.add(remark);
                }
            }

        } else {

            customer = new Customers();
            customer.setFullName(request.getCustomer().getFullName());
            customer.setDateOfBirth(Date.valueOf(request.getCustomer().getDateOfBirth()));
            customer.setGender(request.getCustomer().getGender());
            customer.setPanNumber(panNumber);
            customer.setMobile(request.getCustomer().getMobile());
            customer.setEmail(request.getCustomer().getEmail());
            customer.setAadhaar(request.getCustomer().getAadhaar());
            customer.setCreditReport(report);
            customersDao.create(customer);

            address = new Addresses();
            address.setHsNo(request.getCustomer().getAddress().getHsNo());
            address.setStreet(request.getCustomer().getAddress().getStreet());
            address.setCity(request.getCustomer().getAddress().getCity());
            address.setPincode(String.valueOf(Integer.valueOf(request.getCustomer().getAddress().getPincode())));
            address.setState(request.getCustomer().getAddress().getState());
            address.setCustomer(customer);
            addressDao.create(address);
            customer.setAddress(address);

            score = new CibilScores();
            score.setScore(Integer.valueOf(request.getCibilScore().getScore()));
            score.setScoreDate(Date.valueOf(request.getCibilScore().getScoreDate()));
            score.setRiskGrade(request.getCibilScore().getRiskGrade());
            score.setCreditReport(report);
            cibilScoreDao.create(score);

            if (request.getAccounts() != null) {
                for (AccountsRequestForm af : request.getAccounts()) {
                    Accounts account = new Accounts();
                    account.setAccountNumber(af.getAccountNumber());
                    account.setAccountType(af.getAccountType());
                    account.setMemberName(af.getMemberName());
                    account.setOwnership(af.getOwnership());
                    account.setDateOpened(Date.valueOf(af.getDateOpened()));
                    account.setLastPaymentDate(Date.valueOf(af.getLastPaymentDate()));
                    account.setCurrentBalance(new BigDecimal(af.getCurrentBalance()));
                    account.setCreditLimit(new BigDecimal(af.getCreditLimit()));
                    account.setSanctionedAmount(new BigDecimal(af.getSanctionedAmount()));
                    account.setEmiAmount(new BigDecimal(af.getEmiAmount()));
                    account.setTenureMonths(Integer.parseInt(af.getTenureMonths()));
                    account.setPaymentHistory(af.getPaymentHistory());
                    account.setAccountStatus(af.getAccountStatus());
                    account.setCreditReport(report);
                    accountDao.create(account);
                    accountList.add(account);
                }
            }

            if (request.getEnquiries() != null) {
                for (EnquiriesRequestForm ef : request.getEnquiries()) {
                    Enquiries enquiry = new Enquiries();
                    enquiry.setEnquiryDate(Date.valueOf(ef.getEnquiryDate()));
                    enquiry.setMemberName(ef.getMemberName());
                    enquiry.setEnquiryPurpose(ef.getEnquiryPurpose());
                    enquiry.setEnquiryAmount(new BigDecimal(ef.getEnquiryAmount()));
                    enquiry.setCreditReport(report);
                    enquiriesDao.create(enquiry);
                    enquiryList.add(enquiry);
                }
            }

            if (request.getRemarks() != null) {
                for (RemarksRequestForm rf : request.getRemarks()) {
                    Remarks remark = new Remarks();
                    remark.setDescription(rf.getDescription());
                    remark.setCreditReport(report);
                    remarksDao.create(remark);
                    remarkList.add(remark);
                }
            }
        }

        return buildCreditReportResponse(report, customer, score, accountList, enquiryList, remarkList);
    }

    @Override
    public CreditReportResponseForm getCibilByPanNumber(String panNumber) {
        Customers customer = customersDao.findByPan(panNumber);

        if (customer == null || customer.getCreditReport() == null) {
            return null;
        }

        CreditReports report = customer.getCreditReport();
        CibilScores score = cibilScoreDao.findByReportId(report.getReportId());
        List<Accounts> accounts = accountDao.findByReportId(report.getReportId());
        List<Enquiries> enquiries = enquiriesDao.findByReportId(report.getReportId());
        List<Remarks> remarks = remarksDao.findByReportId(report.getReportId());
        Addresses address = addressDao.getByCustomerId(customer.getCustomerId());
        customer.setAddress(address); // ensure response has it

        return buildCreditReportResponse(report, customer, score, accounts, enquiries, remarks);
    }


    private CreditReportResponseForm buildCreditReportResponse(CreditReports report, Customers customer, CibilScores score,
                                                               List<Accounts> accountList,
                                                               List<Enquiries> enquiryList,
                                                               List<Remarks> remarkList) {
        CreditReportResponseForm response = new CreditReportResponseForm();
        response.setReportId(String.valueOf(report.getReportId()));
        response.setGeneratedOn(String.valueOf(report.getGeneratedOn()));

        CustomerResponseForm customerResponse = new CustomerResponseForm();
        customerResponse.setCustomerId(String.valueOf(customer.getCustomerId()));
        customerResponse.setFullName(customer.getFullName());
        customerResponse.setDateOfBirth(String.valueOf(customer.getDateOfBirth()));
        customerResponse.setGender(customer.getGender());
        customerResponse.setPanNumber(customer.getPanNumber());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setMobile(customer.getMobile());
        customerResponse.setAadhaar(customer.getAadhaar());

        Addresses savedAddress = customer.getAddress();
        if (savedAddress != null) {
            AddressResponseForm addressResponse = new AddressResponseForm();
            addressResponse.setAddressId(String.valueOf(savedAddress.getAddressId()));
            addressResponse.setHsNo(savedAddress.getHsNo());
            addressResponse.setStreet(savedAddress.getStreet());
            addressResponse.setCity(savedAddress.getCity());
            addressResponse.setState((savedAddress.getState()));
            addressResponse.setPincode(savedAddress.getPincode());
            customerResponse.setAddress(addressResponse);
        }

        response.setCustomer(customerResponse);

        if (score != null) {
            CibilScoreResponseForm scoreResponse = new CibilScoreResponseForm();
            scoreResponse.setScoreId(String.valueOf(score.getScoreId()));
            scoreResponse.setScore(String.valueOf(score.getScore()));
            scoreResponse.setScoreDate(String.valueOf(score.getScoreDate()));
            scoreResponse.setRiskGrade(score.getRiskGrade());
            response.setCibilScore(scoreResponse);
        }

        List<AccountsResponseForm> accountResponses = accountList.stream().map(account -> {
            AccountsResponseForm ar = new AccountsResponseForm();
            ar.setAccountId(String.valueOf(account.getAccountId()));
            ar.setAccountNumber(account.getAccountNumber());
            ar.setAccountType(account.getAccountType());
            ar.setMemberName(account.getMemberName());
            ar.setOwnership(account.getOwnership());
            ar.setDateOpened(String.valueOf(account.getDateOpened()));
            ar.setLastPaymentDate(String.valueOf(account.getLastPaymentDate()));
            ar.setCurrentBalance(String.valueOf(account.getCurrentBalance()));
            ar.setCreditLimit(String.valueOf(account.getCreditLimit()));
            ar.setSanctionedAmount(String.valueOf(account.getSanctionedAmount()));
            ar.setEmiAmount(String.valueOf(account.getEmiAmount()));
            ar.setTenureMonths(String.valueOf(account.getTenureMonths()));
            ar.setPaymentHistory(account.getPaymentHistory());
            ar.setAccountStatus(account.getAccountStatus());
            return ar;
        }).collect(Collectors.toList());
        response.setAccounts(accountResponses);

        List<EnquiriesResponseForm> enquiryResponses = enquiryList.stream().map(enquiry -> {
            EnquiriesResponseForm er = new EnquiriesResponseForm();
            er.setEnquiryId(String.valueOf(enquiry.getEnquiryId()));
            er.setEnquiryDate(String.valueOf(enquiry.getEnquiryDate()));
            er.setMemberName(enquiry.getMemberName());
            er.setEnquiryPurpose(enquiry.getEnquiryPurpose());
            er.setEnquiryAmount(String.valueOf(enquiry.getEnquiryAmount()));
            return er;
        }).collect(Collectors.toList());
        response.setEnquiries(enquiryResponses);

        List<String> remarkDescriptions = remarkList.stream()
                .map(Remarks::getDescription)
                .collect(Collectors.toList());
        response.setRemarks(remarkDescriptions);

        return response;
    }
}