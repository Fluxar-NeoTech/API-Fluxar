package org.example.apifluxar.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Fluxar")
                        .version("1.0.0")
                        .description("Documentação da API para gerenciamento de estoque do Fluxar")
                        .contact(new Contact()
                                .name("Equipe Fluxar")
                                .email("suporte2025.neo.tech@gmail.com")
                        )
                );
    }
}
