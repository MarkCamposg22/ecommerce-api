package com.invest.ecommerceapi.presentation.errors;

import com.invest.ecommerceapi.domain.interfaces.Response;

public class UnauthorizedError extends ErrorCustom implements Response {
    public UnauthorizedError() {
        super("[UnauthorizedError]: Unauthrorized");
    }
}
