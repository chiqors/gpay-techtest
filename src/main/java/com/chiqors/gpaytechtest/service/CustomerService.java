package com.chiqors.gpaytechtest.service;

import com.chiqors.gpaytechtest.base.BaseErrorMessage;
import com.chiqors.gpaytechtest.base.BaseResponse;
import com.chiqors.gpaytechtest.dto.CustomerRequest;
import com.chiqors.gpaytechtest.exception.ValidationException;
import com.chiqors.gpaytechtest.model.Customer;
import com.chiqors.gpaytechtest.repository.CustomerRepository;
import com.chiqors.gpaytechtest.validation.CustomerValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerValidation customerValidation;

    /**
     * Add a new customer.
     *
     * @param request the customer request
     * @param response the response
     * @throws ValidationException if the request is invalid
     */
    @Transactional
    public void addCustomer(CustomerRequest request, BaseResponse<String> response) throws ValidationException {
        List<BaseErrorMessage> errorMessages = new ArrayList<>();

        customerValidation.validateCustomerAdd(request, errorMessages);

        if (!errorMessages.isEmpty()) {
            response.setErrorMessages(errorMessages);
            throw new ValidationException();
        }

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setBalance(request.getInitialBalance());
        customerRepository.save(customer);
    }
}
