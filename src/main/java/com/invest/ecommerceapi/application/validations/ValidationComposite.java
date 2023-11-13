package com.invest.ecommerceapi.application.validations;

import com.invest.ecommerceapi.domain.interfaces.Response;
import com.invest.ecommerceapi.domain.interfaces.Validation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component()
public class ValidationComposite implements Validation {
    private final ArrayList<Validation> validations;

    public ValidationComposite(ArrayList<Validation> validations) {
        this.validations = validations;
    }

    @Override
    public Response validate(Object input) {
        for (Validation validation : this.validations) {
            var error = validation.validate(input);
            if (error != null) return error;
        }
        return null;
    }
}
