package com.invest.ecommerceapi.presentation.errors;

import com.invest.ecommerceapi.domain.interfaces.Response;

public class InternalServerError extends ErrorCustom implements Response {
    public InternalServerError(Exception error) {
        super("[InternalServerError]: " + error.getMessage());
    }
}
