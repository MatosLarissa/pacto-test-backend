package com.pacto.internalrecruitment.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    private static final String BASE_PACKEG = "pacto.internalrecruitment";
    private static final String TITLE = "Recrutamento Interno Pacto";
    private static final String DESCRIPTION = "Aplicação web para facilitar o processo de recrutamento interno na Pacto";
    private static final String VERSION = "1.0.0";
    private static final String NAME = "Larissa Matos";
    private static final String GITHUB = "https://github.com/MatosLarissa";
    private static final String EMAIL = "larissamatost@outlook.com";

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .info(new Info()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .version(VERSION)
                        .contact(new Contact()
                                .name(NAME)
                                .url(GITHUB)
                                .email(EMAIL)));
    }
}