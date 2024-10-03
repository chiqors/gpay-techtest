package com.chiqors.gpaytechtest.base;

import com.chiqors.gpaytechtest.enums.ResponseCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> {
    @JsonProperty("status_code")
    private Integer statusCode;
    @JsonProperty("message")
    private String message;
    @JsonProperty("error_messages")
    private List<BaseErrorMessage> errorMessages;
    @JsonProperty("warning_messages")
    private List<BaseWarningMessage> warningMessages;
    @JsonProperty("data")
    private T data;

    public BaseResponse() {
        this.errorMessages = new ArrayList<>();
        this.warningMessages = new ArrayList<>();
    }

    public void setStatusEnumAndMessage(ResponseCodeEnum statusEnum, String customMessage) {
        this.statusCode = statusEnum.getCode();
        if (customMessage == null || customMessage.isEmpty()) {
            this.message = statusEnum.getMessage();
        } else {
            this.message = customMessage;
        }
    }

    public void setXenditErrorMessage(String title, String message) {
        this.errorMessages.add(new BaseErrorMessage("xendit_error_title", title));
        this.errorMessages.add(new BaseErrorMessage("xendit_error_message", message));
    }

    public void setMiddlewareErrorMessage(String title, String message) {
        this.errorMessages.add(new BaseErrorMessage("middleware_error_title", title));
        this.errorMessages.add(new BaseErrorMessage("middleware_error_message", message));
    }
}
