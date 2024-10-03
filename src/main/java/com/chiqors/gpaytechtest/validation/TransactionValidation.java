package com.chiqors.gpaytechtest.validation;

import com.chiqors.gpaytechtest.base.BaseErrorMessage;
import com.chiqors.gpaytechtest.dto.TransactionRequest;
import com.chiqors.gpaytechtest.enums.TransactionTypeEnum;
import com.chiqors.gpaytechtest.model.Customer;
import com.chiqors.gpaytechtest.model.Transaction;
import com.chiqors.gpaytechtest.repository.CustomerRepository;
import com.chiqors.gpaytechtest.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component("transactionValidation")
public class TransactionValidation {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void validateTransaction(TransactionRequest request, List<BaseErrorMessage> errorMessages) {
        Optional<Transaction> existingTransaction = transactionRepository.findByReference(request.getReference());

        if (existingTransaction.isPresent()) {
            errorMessages.add(new BaseErrorMessage("reference", "Transaction with reference " + request.getReference() + " already exists."));
        }

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElse(null);

        if (customer == null) {
            errorMessages.add(new BaseErrorMessage("customer_id", "Customer not found."));
            return;
        }

        if (request.getType() == TransactionTypeEnum.DEBIT && customer.getBalance().compareTo(request.getAmount()) < 0) {
            errorMessages.add(new BaseErrorMessage("balance", "Insufficient balance."));
        }

        BigDecimal newBalance = request.getType() == TransactionTypeEnum.CREDIT ?
                customer.getBalance().add(request.getAmount()) :
                customer.getBalance().subtract(request.getAmount());

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            errorMessages.add(new BaseErrorMessage("balance", "Balance cannot be negative."));
        }
    }
}
