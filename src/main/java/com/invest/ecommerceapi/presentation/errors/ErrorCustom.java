package com.invest.ecommerceapi.presentation.errors;

public class ErrorCustom extends Error {
    public ErrorCustom(String input) {
        super(input);
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return null;
    }

    @Override
    public String getLocalizedMessage() {
        return null;
    }
}
