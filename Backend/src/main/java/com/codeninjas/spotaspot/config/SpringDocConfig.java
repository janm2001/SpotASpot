package com.codeninjas.spotaspot.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
@SecurityScheme(
        name = "token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SpringDocConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Spot a Spot API Docs")
                .version("1.0.0")
                .description("SpotASpot je aplikacija za događaje koja korisnicima omogućuje pretraživanje i " +
                        "prisustvovanje događajima iz različitih kategorija poput radionica, glazbe, kviza, igara, " +
                        "sporta i drugih. Organizatori događaja mogu stvarati i upravljati svojim događajima na " +
                        "platformi te vidjeti feedback na prijašnje eventove što može pomoći oko poboljšanja budućih " +
                        "eventova. Korisnici mogu ocjenjivati događaje, primati personalizirane preporuke i vidjeti " +
                        "da li njihovi prijatelji prisustvuju događajima. Aplikacija neće prodavati karte nego će " +
                        "biti \"middleman\" između korisnika i nekog događaja.\n"));

    }

    @Bean
    public GroupedOpenApi authenticationApi() {
        String [] paths = {"/api/v1/auth/**"};
        return GroupedOpenApi.builder()
                .group("Authentication")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi EventApi() {
        String [] paths = {"/api/v1/event/**"};
        return GroupedOpenApi.builder()
                .group("Event")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi UserApi() {
        String [] paths = {"/api/v1/user/**"};
        return GroupedOpenApi.builder()
                .group("User")
                .pathsToMatch(paths)
                .build();
    }
}
