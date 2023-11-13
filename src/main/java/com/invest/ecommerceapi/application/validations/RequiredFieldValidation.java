package com.invest.ecommerceapi.application.validations;

import com.invest.ecommerceapi.domain.interfaces.Response;
import com.invest.ecommerceapi.domain.interfaces.Validation;
import com.invest.ecommerceapi.presentation.errors.MissingFieldError;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;

@Component
public class RequiredFieldValidation implements Validation {
    private final String[] fieldsRequiredName;

    public RequiredFieldValidation(String[] fieldsRequiredName) {
        this.fieldsRequiredName = fieldsRequiredName;
    }

    @Override
    public Response validate(Object input) {
        for (String fieldName : this.fieldsRequiredName) {
            try {
                Field field = input.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(input);
                if (value == null) return new MissingFieldError(fieldName);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
