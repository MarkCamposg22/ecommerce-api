package com.invest.ecommerceapi.domain.schemas;

import com.invest.ecommerceapi.domain.interfaces.Response;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class UserResponseSchema implements Response {
    private UserSchema user;
    private String accessToken;

    public UserResponseSchema(UserSchema user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }

    public UserSchema getUser() {
        return user;
    }

    public void setUser(UserSchema user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
