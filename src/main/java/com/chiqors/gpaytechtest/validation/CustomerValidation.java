package com.chiqors.gpaytechtest.validation;

import com.chiqors.gpaytechtest.base.BaseErrorMessage;
import com.chiqors.gpaytechtest.dto.CustomerRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component("customerValidation")
public class CustomerValidation {
    public void validateCustomerAdd(CustomerRequest request, List<BaseErrorMessage> errorMessages) {
        if (request.getInitialBalance().compareTo(BigDecimal.ZERO) < 0) {
            errorMessages.add(new BaseErrorMessage("initial_balance", "Initial balance cannot be negative."));
        }
    }
}
