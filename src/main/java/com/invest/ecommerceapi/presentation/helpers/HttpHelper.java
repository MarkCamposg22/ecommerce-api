package com.invest.ecommerceapi.presentation.helpers;

import com.invest.ecommerceapi.domain.interfaces.Response;
import com.invest.ecommerceapi.presentation.errors.InternalServerError;
import com.invest.ecommerceapi.presentation.errors.UnauthorizedError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class HttpHelper {
    public static ResponseEntity<Response> created(Response data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    public static ResponseEntity<Response> internalError(Exception error) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InternalServerError(error));
    }

    public static ResponseEntity<Response> badRequest(Response error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    public static ResponseEntity<Response> forbidden(Response error) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    public static ResponseEntity<Response> ok(Response data) {
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    public static ResponseEntity<Response> unauthrorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UnauthorizedError());
    }
}
