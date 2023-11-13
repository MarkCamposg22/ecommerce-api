package com.invest.ecommerceapi.domain.schemas;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class UserSchema {
    private String email;
    private String name;
    private boolean owner;
    private String cep;
    private String address;

    public UserSchema(String email, String name, boolean owner, String cep, String address) {
        this.email = email;
        this.name = name;
        this.owner = owner;
        this.cep = cep;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
