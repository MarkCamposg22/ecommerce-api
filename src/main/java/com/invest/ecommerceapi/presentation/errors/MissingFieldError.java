package com.invest.ecommerceapi.presentation.errors;

import com.invest.ecommerceapi.domain.interfaces.Response;

public class MissingFieldError extends ErrorCustom implements Response {
    public MissingFieldError(String fieldName) {
        super("[MissingFieldError]: " + fieldName + " is required!");
    }
}
