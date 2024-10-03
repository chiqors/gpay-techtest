package com.chiqors.gpaytechtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerRequest {
    @JsonProperty("name")
    private String name;
    @JsonProperty("initial_balance")
    private BigDecimal initialBalance;
}
