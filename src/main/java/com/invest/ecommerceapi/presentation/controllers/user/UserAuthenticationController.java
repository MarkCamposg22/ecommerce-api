package com.invest.ecommerceapi.presentation.controllers.user;

import com.invest.ecommerceapi.application.services.user.UserAuthenticationService;
import com.invest.ecommerceapi.application.validations.EmailValidation;
import com.invest.ecommerceapi.application.validations.RequiredFieldValidation;
import com.invest.ecommerceapi.application.validations.ValidationComposite;
import com.invest.ecommerceapi.domain.interfaces.Controller;
import com.invest.ecommerceapi.domain.interfaces.Response;
import com.invest.ecommerceapi.domain.interfaces.Validation;
import com.invest.ecommerceapi.domain.schemas.UserAuthenticationRequestSchema;
import com.invest.ecommerceapi.domain.schemas.UserResponseSchema;
import com.invest.ecommerceapi.presentation.controllers.UserController;
import com.invest.ecommerceapi.presentation.errors.InvalidCredentialError;
import com.invest.ecommerceapi.presentation.helpers.HttpHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.jdbc.Expectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserAuthenticationController extends UserController implements Controller<UserAuthenticationRequestSchema> {
    @Autowired
    private UserAuthenticationService userAuthenticationService;

    private final ValidationComposite validator;

    public UserAuthenticationController() {
        ArrayList<Validation> validations = new ArrayList<>();
        validations.add(new EmailValidation());
        String[] fieldsRequiredName = {"email", "password"};
        validations.add(new RequiredFieldValidation(fieldsRequiredName));
        this.validator = new ValidationComposite(validations);
    }

    @Override
    @Operation(summary = "Autenticar um usuário", description = "Autentica um usuário pelo: e-mail e senha")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "[OK] Autenticado com sucesso!",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseSchema.class))
                    }
            ),
            @ApiResponse(responseCode = "400", description = "[BAD_REQUEST] Erro ao autenticar usuário!", content = {@Content}),
            @ApiResponse(responseCode = "403", description = "[FORBIDDEN] Credenciais inválidas!", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "[INTERNAL_SERVER_ERROR] Erro interno de servidor!", content = {@Content})
    })
    @PostMapping("/login")
    public ResponseEntity<Response> handle(@RequestBody UserAuthenticationRequestSchema requestModel) {
        try {
            var error = this.validator.validate(requestModel);
            if (error != null) return HttpHelper.badRequest(error);
            var response = this.userAuthenticationService.auth(requestModel);
            if (response == null) return HttpHelper.forbidden(new InvalidCredentialError());
            return HttpHelper.ok(response);
        } catch (Exception error) {
            return HttpHelper.internalError(error);
        }
    }
}
