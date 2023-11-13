package com.invest.ecommerceapi.domain.interfaces;

import org.springframework.http.ResponseEntity;

public interface Controller<T> {
    public ResponseEntity<Response> handle(T requestModel);
}
