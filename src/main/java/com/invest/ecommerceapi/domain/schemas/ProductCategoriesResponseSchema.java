package com.invest.ecommerceapi.domain.schemas;

import com.invest.ecommerceapi.domain.interfaces.Response;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema
public class ProductCategoriesResponseSchema implements Response {
    List<ProductCategoryResponseSchema> productCategories;

    public ProductCategoriesResponseSchema(List<ProductCategoryResponseSchema> productCategories) {
        this.productCategories = productCategories;
    }

    public List<ProductCategoryResponseSchema> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<ProductCategoryResponseSchema> productCategories) {
        this.productCategories = productCategories;
    }
}
