package com.invest.ecommerceapi.application.validations;

import com.invest.ecommerceapi.domain.interfaces.Response;
import com.invest.ecommerceapi.domain.interfaces.Validation;
import com.invest.ecommerceapi.presentation.errors.InvalidParamError;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component()
public class EmailValidation implements Validation {
    @Override
    public Response validate(Object input) {
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        boolean isValid;
        try {
            Field emailField = input.getClass().getDeclaredField("email");
            emailField.setAccessible(true);
            Object emailValue = emailField.get(input);
            Matcher matcher = pattern.matcher((String) emailValue);
            isValid = matcher.matches();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (!isValid) {
            return new InvalidParamError("e-mail");
        }
        return null;
    }
}
