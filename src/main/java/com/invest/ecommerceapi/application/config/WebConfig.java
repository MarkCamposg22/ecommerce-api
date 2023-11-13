package com.invest.ecommerceapi.application.config;

import com.invest.ecommerceapi.presentation.middlewares.AuthClientMiddleware;
import com.invest.ecommerceapi.presentation.middlewares.AuthOwnerMiddleware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] routesOwnerProtected = {"/product-category/create"};
        // String[] routesClientProtected = {""};
        registry.addInterceptor(new AuthOwnerMiddleware()).addPathPatterns(routesOwnerProtected);
        // registry.addInterceptor(new AuthClientMiddleware()).addPathPatterns(routesClientProtected);
    }
}
