package com.invest.ecommerceapi.presentation.errors;

import com.invest.ecommerceapi.domain.interfaces.Response;

public class EmailInUseError extends ErrorCustom implements Response {
    public EmailInUseError() {
        super("[EmailInUseError]: The received email is already in use");
    }
}
