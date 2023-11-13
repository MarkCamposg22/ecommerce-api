package com.invest.ecommerceapi.presentation.errors;

import com.invest.ecommerceapi.domain.interfaces.Response;

public class InvalidCredentialError extends ErrorCustom implements Response {
    public InvalidCredentialError() {
        super("[InvalidCredentialError]: Access credentials are incorrect.");
    }
}
