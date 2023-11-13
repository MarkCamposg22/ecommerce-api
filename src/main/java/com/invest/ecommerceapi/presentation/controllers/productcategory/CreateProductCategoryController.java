package com.invest.ecommerceapi.presentation.controllers.productcategory;

import com.invest.ecommerceapi.application.services.productcategory.CreateProductCategoryService;
import com.invest.ecommerceapi.application.validations.RequiredFieldValidation;
import com.invest.ecommerceapi.application.validations.ValidationComposite;
import com.invest.ecommerceapi.domain.interfaces.Response;
import com.invest.ecommerceapi.domain.interfaces.Validation;
import com.invest.ecommerceapi.domain.schemas.CreateProductCategoryRequestSchema;
import com.invest.ecommerceapi.domain.schemas.ProductCategoryResponseSchema;
import com.invest.ecommerceapi.presentation.controllers.ProductCategoryController;
import com.invest.ecommerceapi.presentation.errors.NameInUseError;
import com.invest.ecommerceapi.presentation.helpers.HttpHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CreateProductCategoryController extends ProductCategoryController {

    @Autowired
    private CreateProductCategoryService createProductCategoryService;

    private final ValidationComposite validator;

    public CreateProductCategoryController() {
        ArrayList<Validation> validations = new ArrayList<>();
        String[] fieldsRequiredName = {"name"};
        validations.add(new RequiredFieldValidation(fieldsRequiredName));
        this.validator = new ValidationComposite(validations);
    }

    @Operation(summary = "Cria uma nova categoria de produtos", description = "Cria uma nova categoria de produtos pelo: nome")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "[CREATED] Categoria criada com sucesso!",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductCategoryResponseSchema.class))
                    }
            ),
            @ApiResponse(responseCode = "400", description = "[BAD_REQUEST] Erro ao criar nova categoria!", content = {@Content}),
            @ApiResponse(responseCode = "401", description = "[UNAUTHORIZED] Usuário não autorizado!", content = {@Content}),
            @ApiResponse(responseCode = "403", description = "[FORBIDDEN] Categoria já existe!", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "[INTERNAL_SERVER_ERROR] Erro interno de servidor!", content = {@Content})
    })
    @SecurityRequirement(name = "bearer-key")
    @PostMapping("/create")
    public ResponseEntity<Response> handle(
            @RequestBody CreateProductCategoryRequestSchema requestModel
    ) {
        try {
            var error = this.validator.validate(requestModel);
            if (error != null) return HttpHelper.badRequest(error);
            var response = this.createProductCategoryService.create(requestModel);
            if (response == null) return HttpHelper.forbidden(new NameInUseError());
            return HttpHelper.created(response);
        } catch (Exception error) {
            return HttpHelper.internalError(error);
        }
    }
}
