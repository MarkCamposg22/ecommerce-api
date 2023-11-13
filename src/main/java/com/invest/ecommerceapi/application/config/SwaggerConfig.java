package com.invest.ecommerceapi.application.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("E-commerce API")
                .description("Documentação API REST de um e-commerce desenvolvido em sala de aula.")
                .version("v1.0.0")
            )
            .externalDocs(new ExternalDocumentation()
                .description("GitHub")
                .url("https://github.com/MarkCamposg22/ecommerce-api")
            );
    }
}
