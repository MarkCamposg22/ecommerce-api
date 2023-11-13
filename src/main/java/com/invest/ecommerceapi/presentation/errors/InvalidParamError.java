package com.invest.ecommerceapi.presentation.errors;

import com.invest.ecommerceapi.domain.interfaces.Response;

public class InvalidParamError extends ErrorCustom implements Response {
    public InvalidParamError(String fieldName) {
        super("[InvalidParamError]: " + fieldName + " invalid!");
    }
}
