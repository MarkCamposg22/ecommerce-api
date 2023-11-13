package com.invest.ecommerceapi.presentation.controllers.user;

import com.invest.ecommerceapi.application.services.user.UserRegisterService;
import com.invest.ecommerceapi.application.validations.EmailValidation;
import com.invest.ecommerceapi.application.validations.RequiredFieldValidation;
import com.invest.ecommerceapi.application.validations.ValidationComposite;
import com.invest.ecommerceapi.domain.interfaces.Controller;
import com.invest.ecommerceapi.domain.interfaces.Response;
import com.invest.ecommerceapi.domain.interfaces.Validation;
import com.invest.ecommerceapi.domain.schemas.UserRegisterRequestSchema;
import com.invest.ecommerceapi.domain.schemas.UserResponseSchema;
import com.invest.ecommerceapi.presentation.errors.EmailInUseError;
import com.invest.ecommerceapi.presentation.helpers.HttpHelper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.invest.ecommerceapi.presentation.controllers.UserController;
import java.util.ArrayList;

@RestController
public class UserRegisterController extends UserController implements Controller<UserRegisterRequestSchema> {
    @Autowired
    private UserRegisterService userRegisterService;

    private final ValidationComposite validator;

    public UserRegisterController() {
        ArrayList<Validation> validations = new ArrayList<>();
        validations.add(new EmailValidation());
        String[] fieldsRequiredName = {"name", "email", "password"};
        validations.add(new RequiredFieldValidation(fieldsRequiredName));
        this.validator = new ValidationComposite(validations);
    }

    @Override
    @Operation(summary = "Registrar um novo usuário.", description = "Registra um novo usuário pelo: e-mail, nome e senha")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "[CREATED] Usuário criado com sucesso!",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseSchema.class))
                    }
            ),
            @ApiResponse(responseCode = "400", description = "[BAD_REQUEST] Erro ao criar novo usuário!", content = {@Content}),
            @ApiResponse(responseCode = "403", description = "[FORBIDDEN] E-mail já utilizado!", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "[INTERNAL_SERVER_ERROR] Erro interno de servidor!", content = {@Content})
    })
    @PostMapping("/register")
    public ResponseEntity<Response> handle(@RequestBody UserRegisterRequestSchema requestModel) {
        try {
            var error = this.validator.validate(requestModel);
            if (error != null) return HttpHelper.badRequest(error);
            var response = this.userRegisterService.register(requestModel);
            if (response == null) return HttpHelper.forbidden(new EmailInUseError());
            return HttpHelper.created(response);
        } catch (Exception error) {
            return HttpHelper.internalError(error);
        }
    }
}
