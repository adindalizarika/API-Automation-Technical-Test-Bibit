package utils;

import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;

import static org.hamcrest.MatcherAssert.assertThat;

public class JsonSchemaValidatorUtil {
    public static void validateJsonSchema(Response response, String schemaPath) {
        assertThat(response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }
} 