package com.invest.ecommerceapi.presentation.errors;

import com.invest.ecommerceapi.domain.interfaces.Response;

public class NameInUseError extends ErrorCustom implements Response {
    public NameInUseError() {
        super("[NameInUseError]: The received name is already in use.");
    }
}
