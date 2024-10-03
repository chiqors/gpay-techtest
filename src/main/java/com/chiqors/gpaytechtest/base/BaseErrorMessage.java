package com.dml.midapp.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class BaseErrorMessage {
    private String field;
    private String message;

    public static List<BaseErrorMessage> createSingleErrorMessage(String field, String message) {
        return List.of(new BaseErrorMessage(field, message));
    }
}
