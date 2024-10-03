package com.chiqors.gpaytechtest.service;

import com.chiqors.gpaytechtest.base.BaseErrorMessage;
import com.chiqors.gpaytechtest.base.BaseResponse;
import com.chiqors.gpaytechtest.dto.TransactionRequest;
import com.chiqors.gpaytechtest.enums.TransactionStatusEnum;
import com.chiqors.gpaytechtest.enums.TransactionTypeEnum;
import com.chiqors.gpaytechtest.exception.ValidationException;
import com.chiqors.gpaytechtest.model.Customer;
import com.chiqors.gpaytechtest.model.Transaction;
import com.chiqors.gpaytechtest.repository.CustomerRepository;
import com.chiqors.gpaytechtest.repository.TransactionRepository;
import com.chiqors.gpaytechtest.validation.TransactionValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionValidation transactionValidation;

    /**
     * Process a transaction request.
     *
     * @param request the transaction request
     * @param response the response
     * @throws ValidationException if the request is invalid
     */
    @Transactional
    public void processTransaction(TransactionRequest request, BaseResponse<String> response) throws ValidationException {
        List<BaseErrorMessage> errorMessages = new ArrayList<>();

        transactionValidation.validateTransaction(request, errorMessages);

        if (!errorMessages.isEmpty()) {
            response.setErrorMessages(errorMessages);
            throw new ValidationException();
        }

        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow();

        BigDecimal newBalance = request.getType() == TransactionTypeEnum.CREDIT ?
                customer.getBalance().add(request.getAmount()) :
                customer.getBalance().subtract(request.getAmount());

        customer.setBalance(newBalance);
        customerRepository.save(customer);

        Transaction transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setTimestamp(new Date());
        transaction.setStatus(TransactionStatusEnum.SUCCESS);
        transaction.setReference(request.getReference());

        transactionRepository.save(transaction);
    }
}
