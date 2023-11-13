package com.invest.ecommerceapi.domain.schemas;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class CreateProductCategoryRequestSchema {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
