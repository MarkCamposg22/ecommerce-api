package com.invest.ecommerceapi.presentation.controllers.productcategory;

import com.invest.ecommerceapi.application.services.productcategory.GetAllProductCategoriesService;
import com.invest.ecommerceapi.domain.interfaces.Response;
import com.invest.ecommerceapi.domain.schemas.ProductCategoriesResponseSchema;
import com.invest.ecommerceapi.domain.schemas.ProductCategoryResponseSchema;
import com.invest.ecommerceapi.presentation.controllers.ProductCategoryController;
import com.invest.ecommerceapi.presentation.helpers.HttpHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetAllProductCategoriesController extends ProductCategoryController {
    @Autowired
    private GetAllProductCategoriesService getAllProductCategoriesService;

    @Operation(summary = "Lista todas as categorias de produtos.", description = "Lista todas as categorias de produtos com: id e nome")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "[OK] Listagem de categorias realizada com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductCategoriesResponseSchema.class))
            ),
            @ApiResponse(responseCode = "500", description = "[INTERNAL_SERVER_ERROR] Erro interno de servidor!", content = {@Content})
    })
    @GetMapping("/get-all")
    public ResponseEntity<Response> handle() {
        try {
            var response = this.getAllProductCategoriesService.getAll();
            return HttpHelper.ok(response);
        } catch (Exception error) {
            return HttpHelper.internalError(error);
        }
    }
}
