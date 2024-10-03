package com.chiqors.gpaytechtest.dto;

import com.chiqors.gpaytechtest.enums.TransactionTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {
    @JsonProperty("customer_id")
    private Long customerId;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("type")
    private TransactionTypeEnum type;
    @JsonProperty("reference")
    private String reference;
}
