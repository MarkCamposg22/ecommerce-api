package com.invest.ecommerceapi.domain.schemas;

import com.invest.ecommerceapi.domain.interfaces.Response;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class ProductCategoryResponseSchema implements Response {
    private String id;
    private String name;

    public ProductCategoryResponseSchema(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
