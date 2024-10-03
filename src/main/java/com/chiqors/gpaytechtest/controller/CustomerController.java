package com.chiqors.gpaytechtest.controller;

import com.chiqors.gpaytechtest.base.BaseResponse;
import com.chiqors.gpaytechtest.dto.CustomerRequest;
import com.chiqors.gpaytechtest.enums.ResponseCodeEnum;
import com.chiqors.gpaytechtest.exception.ValidationException;
import com.chiqors.gpaytechtest.model.Customer;
import com.chiqors.gpaytechtest.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<BaseResponse<String>> addCustomer(@RequestBody CustomerRequest request) {
        BaseResponse<String> response = new BaseResponse<>();
        try {
            customerService.addCustomer(request, response);
            response.setStatusEnumAndMessage(ResponseCodeEnum.CREATED, null);
            return ResponseEntity.status(ResponseCodeEnum.CREATED.getCode()).body(response);
        } catch (ValidationException e) {
            response.setStatusEnumAndMessage(ResponseCodeEnum.BAD_REQUEST, null);
            return ResponseEntity.status(ResponseCodeEnum.BAD_REQUEST.getCode()).body(response);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.status(ResponseCodeEnum.INTERNAL_SERVER_ERROR.getCode()).body(response);
    }
}