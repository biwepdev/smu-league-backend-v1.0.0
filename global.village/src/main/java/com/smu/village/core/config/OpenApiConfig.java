package com.smu.village.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        var devServer = new Server();
        devServer.setUrl("http://127.0.0.1:9822");
        devServer.setDescription("Server URL development environment");

        var prodServer = new Server();
        prodServer.setUrl("http://192.168.1.114:9822");
        prodServer.setDescription("Server URL production environment");

        var contact = new Contact();
        contact.setName("BI-WEP");
        contact.setEmail("biwep@gmail.com");
        contact.setUrl("https://www.biwep.com");

        var license = new License()
                .name("Apache License").url("https://www.biwep.com");

        var info = new Info().title("VILLAGE SERVICE FOR SMU SYSTEM")
                .version("1.0.0")
                .contact(contact).description("VILLAGE SERVICE FOR SMU SYSTEM")
                .termsOfService("[https://www.degce.com](https://www.degce.com)").license(license);
        return new OpenAPI().info(info).servers(List.of(devServer, prodServer)).addSecurityItem(new SecurityRequirement().addList("Token")).components(new Components().addSecuritySchemes("Token", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT").description("JWT authentication with Token")));
    }
}
