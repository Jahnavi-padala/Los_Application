package com.jocata.service.Impl;

import com.jocata.dao.AddressDao;
import com.jocata.dao.CustomerDao;
import com.jocata.dao.IncomeDetailsDao;
import com.jocata.dao.LoanApplicationDao;
import com.jocata.entity.Address;
import com.jocata.entity.Customers;
import com.jocata.entity.IncomeDetails;
import com.jocata.entity.LoanApplications;
import com.jocata.form.*;
import com.jocata.service.LoanApplicationService;
import com.jocata.util.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
    @Autowired
    private LoanApplicationDao loanDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AddressDao addressDao;
    @Autowired
    private IncomeDetailsDao incomeDetailsDao;

    @Override
    @Transactional
    public LoanForm createApplication(@RequestBody LoanForm loanRequestForm) {
        CustomerForm customerForm = loanRequestForm.getCustomer();
        AddressForm addressForm = customerForm.getAddress();
        IncomeDetailsForm incomeDetailsForm = loanRequestForm.getIncomeDetails();
        LoanApplicationForm loanForm = loanRequestForm.getLoanDetails();

        Customers existingPan = customerDao.findByPan(customerForm.getPanNumber());
        Customers existingAadhaar = customerDao.findByAadhaar(customerForm.getAadhaarNumber());

        if (existingPan != null && existingAadhaar != null) {
            return buildErrorLoanForm("PAN number and Aadhaar number already exist");
        } else if (existingPan != null) {
            return buildErrorLoanForm("PAN number already exists");
        } else if (existingAadhaar != null) {
            return buildErrorLoanForm("Aadhaar number already exists");
        }

        Customers customer = new Customers();
        customer.setFirstName(customerForm.getFirstName());
        customer.setLastName(customerForm.getLastName());
        customer.setEmail(customerForm.getEmail());
        customer.setPhoneNumber(customerForm.getPhoneNumber());
        customer.setPanNumber(customerForm.getPanNumber());
        customer.setAadhaarNumber(customerForm.getAadhaarNumber());
        customer.setDob(Date.valueOf(LocalDate.parse(customerForm.getDob())));
        customer.setCreatedAt(LocalDate.parse(customerForm.getCreatedAt()).atStartOfDay());
        Customers savedCustomer = customerDao.create(customer);


        Address address = new Address();
        address.setStreet(addressForm.getStreet());
        address.setCity(addressForm.getCity());
        address.setState(addressForm.getState());
        address.setZipCode(addressForm.getZipCode());
        address.setCountry(addressForm.getCountry());
        address.setCustomer(customer);
        Address savedAddress = addressDao.create(address);

        IncomeDetails incomeDetails = new IncomeDetails();
        incomeDetails.setMonthlyIncome(BigDecimal.valueOf(Double.parseDouble(incomeDetailsForm.getMonthlyIncome())));
        incomeDetails.setEmploymentStatus(incomeDetailsForm.getEmploymentStatus());
        incomeDetails.setEmployerName(incomeDetailsForm.getEmployerName());
        incomeDetails.setYearsAtJob(Integer.valueOf(incomeDetailsForm.getYearsAtJob()));
        incomeDetails.setCustomer(customer);
        IncomeDetails savedIncomeDetails = incomeDetailsDao.create(incomeDetails);

        LoanApplications loanApplication = new LoanApplications();
        loanApplication.setLoanAmount(BigDecimal.valueOf(Double.parseDouble(loanForm.getLoanAmount())));
        loanApplication.setLoanType(loanForm.getLoanType());
        loanApplication.setApplicationDate(Date.valueOf(LocalDate.parse(loanForm.getApplicateDate())));
        loanApplication.setApprovedAmount(BigDecimal.valueOf(loanForm.getApprovedAmount() != null ? Double.parseDouble(loanForm.getApprovedAmount()) : null));
        loanApplication.setIsActive(Boolean.valueOf(loanForm.getIsActive()));
        loanApplication.setStatus(Status.valueOf(loanForm.getStatus()));
        loanApplication.setTermMonths(Integer.valueOf(loanForm.getTermMonths()));
        loanApplication.setLoanPurpose(loanForm.getLoanPurpose());
        loanApplication.setCustomer(customer);
        LoanApplications savedLoanApplication = loanDao.create(loanApplication);
        return buildLoanRequestFormFromEntities(savedCustomer, savedAddress, savedIncomeDetails, savedLoanApplication);
    }

    @Override
    public LoanForm buildErrorLoanForm(String errorMessage) {
        LoanForm errorForm = new LoanForm();
        CustomerForm customerForm = new CustomerForm();
        customerForm.setErrorMessage(errorMessage);
        errorForm.setCustomer(customerForm);
        return errorForm;
    }

    @Override
    public LoanForm getApplicationById(Integer applicationId) {
        LoanApplications loan = loanDao.getById(applicationId);
        if (loan == null) {
            return null;
        }

        Customers customer = loan.getCustomer();
        Address address = customer.getAddress();
        IncomeDetails incomeDetails = customer.getIncomeDetails();

        return buildLoanRequestFormFromEntities(customer, address, incomeDetails, loan);
    }


    public LoanForm buildLoanRequestFormFromEntities(Customers customer, Address address,
                                                     IncomeDetails incomeDetails, LoanApplications loanApplication) {


        CustomerForm updatedCustomerForm = new CustomerForm();
        updatedCustomerForm.setCustomerId(String.valueOf(customer.getCustomerId()));
        updatedCustomerForm.setFirstName(customer.getFirstName());
        updatedCustomerForm.setLastName(customer.getLastName());
        updatedCustomerForm.setEmail(customer.getEmail());
        updatedCustomerForm.setPhoneNumber(customer.getPhoneNumber());
        updatedCustomerForm.setDob(customer.getDob().toString());
        updatedCustomerForm.setCreatedAt(customer.getCreatedAt().toString());
        updatedCustomerForm.setPanNumber(customer.getPanNumber());
        updatedCustomerForm.setAadhaarNumber(customer.getAadhaarNumber());


        AddressForm updatedAddressForm = new AddressForm();
        updatedAddressForm.setAddressId(String.valueOf(address.getAddressId()));
        updatedAddressForm.setStreet(address.getStreet());
        updatedAddressForm.setCity(address.getCity());
        updatedAddressForm.setState(address.getState());
        updatedAddressForm.setZipCode(address.getZipCode());
        updatedAddressForm.setCountry(address.getCountry());
        updatedAddressForm.setCustomerId(String.valueOf(customer.getCustomerId()));

        updatedCustomerForm.setAddress(updatedAddressForm);


        IncomeDetailsForm updatedIncomeForm = new IncomeDetailsForm();
        updatedIncomeForm.setIncomeId(String.valueOf(incomeDetails.getIncomeId()));
        updatedIncomeForm.setMonthlyIncome(String.valueOf(incomeDetails.getMonthlyIncome()));
        updatedIncomeForm.setEmploymentStatus(incomeDetails.getEmploymentStatus());
        updatedIncomeForm.setEmployerName(incomeDetails.getEmployerName());
        updatedIncomeForm.setYearsAtJob(String.valueOf(incomeDetails.getYearsAtJob()));
        updatedIncomeForm.setCustomerId(String.valueOf(customer.getCustomerId()));

        LoanApplicationForm updatedLoanForm = new LoanApplicationForm();
        updatedLoanForm.setApplicationId(String.valueOf(loanApplication.getApplicationId()));
        updatedLoanForm.setLoanAmount(String.valueOf(loanApplication.getLoanAmount()));
        updatedLoanForm.setLoanType(loanApplication.getLoanType());
        updatedLoanForm.setApplicateDate(loanApplication.getApplicationDate().toString());
        updatedLoanForm.setApprovedAmount(
                loanApplication.getApprovedAmount() != null
                        ? String.valueOf(loanApplication.getApprovedAmount())
                        : null
        );
        updatedLoanForm.setIsActive(String.valueOf(loanApplication.getIsActive()));
        updatedLoanForm.setStatus(String.valueOf(loanApplication.getStatus()));
        updatedLoanForm.setTermMonths(String.valueOf(loanApplication.getTermMonths()));
        updatedLoanForm.setLoanPurpose(loanApplication.getLoanPurpose());
        updatedLoanForm.setCustomerId(String.valueOf(customer.getCustomerId()));


        LoanForm updatedRequestForm = new LoanForm();
        updatedRequestForm.setCustomer(updatedCustomerForm);
        updatedRequestForm.setIncomeDetails(updatedIncomeForm);
        updatedRequestForm.setLoanDetails(updatedLoanForm);

        return updatedRequestForm;
    }

}
