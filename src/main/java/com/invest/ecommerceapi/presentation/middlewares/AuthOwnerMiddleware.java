package com.invest.ecommerceapi.presentation.middlewares;

import com.invest.ecommerceapi.application.cryptography.Encrypter;
import com.invest.ecommerceapi.infra.entities.User;
import com.invest.ecommerceapi.infra.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class AuthOwnerMiddleware extends AuthMiddleware implements HandlerInterceptor {
    @Autowired
    private Encrypter encrypter;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (this.shouldNotFilter(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        try {
            String accessToken = token.replace("Bearer ", "");
            var userId = this.encrypter.decrypt(accessToken);
            var user = this.userRepository.findById((String) userId);
            var isOwner = user.map(User::isOwner).orElse(false);
            if (!isOwner) {
                this.sendUnauthorizedResponse(response);
                return;
            }
            request.setAttribute("userId", user.get().getId());
        } catch (Exception error) {
            this.sendUnauthorizedResponse(response);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
