package com.invest.ecommerceapi.presentation.middlewares;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invest.ecommerceapi.domain.interfaces.Response;
import com.invest.ecommerceapi.presentation.helpers.HttpHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthMiddleware extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {}

    public boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/swagger-ui") || path.startsWith("/v3") || path.startsWith("/user/register") || path.startsWith("/user/login");
    }

    public void sendUnauthorizedResponse(HttpServletResponse response) throws IOException {
        ResponseEntity<Response> unauthorizedResponse = HttpHelper.unauthrorized();
        writeResponse(response, unauthorizedResponse);
    }

    public void writeResponse(HttpServletResponse response, ResponseEntity<Response> httpResponse) throws IOException {
        response.setStatus(httpResponse.getStatusCodeValue());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(httpResponse.getBody());
        response.getWriter().write(json);
    }
}
