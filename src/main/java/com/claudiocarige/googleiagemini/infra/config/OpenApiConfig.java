package com.claudiocarige.googleiagemini.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Criador de prompt para interagir com o Google Gemini AI")
                        .version("v1")
                        .description("Esta API irá te ajuadar a construir um prompt para ser usado em uma iteração com o Gemini AI" +
                                "Criando de forma simples um contexto para a geração da sua necessidade textual para iteração como o " +
                                "modelo de linguagem")
                        .termsOfService("https://sua-api-exemplo.com/termos-de-servico")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
    }
}
