package com.codeninjas.spotaspot.config;

import com.codeninjas.spotaspot.config.dto.SwaggerResponse;
import com.codeninjas.spotaspot.util.ReadJsonFileToJsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.json.JSONObject;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@OpenAPIDefinition
@Configuration
@SecurityScheme(
        name = "token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SpringDocConfig {
    ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public OpenAPI baseOpenAPI() throws IOException {
        Components components = new Components();
        loadResponsesFromJSON().forEach(components::addResponses);

        return new OpenAPI()
                .components(components)
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

    private Map<String, ApiResponse> loadResponsesFromJSON() throws IOException {

        JSONObject responses = ReadJsonFileToJsonObject.read("src/main/resources/openapi/responses.json");

        Map<String, ApiResponse> result = new HashMap<>();

        Set<String> arr = responses.keySet();
        arr.forEach(name -> {
            try {
                SwaggerResponse res = objectMapper.readValue(responses.get(name).toString(), SwaggerResponse.class);

                ApiResponse apiResponse = new ApiResponse().content(
                        new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                                new io.swagger.v3.oas.models.media.MediaType().addExamples(
                                        "default",
                                        new Example().value(res.content())
                                ))
                ).description(res.description());

                result.put(name, apiResponse);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        });

        return result;
    }
}
