package steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.JsonSchemaValidatorUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeletePostSteps {
    private Response response;

    @When("I send a DELETE request to {string}")
    public void sendDeleteRequest(String endpoint) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        
        response = RestAssured
                .given()
                .when()
                .delete(endpoint)
                .then()
                .extract().response();
        
        System.out.println("Response status code: " + response.statusCode());
    }

    @And("the response should be an empty object")
    public void verifyResponseIsEmptyObject() {
        // Verify the response body is an empty object {}
        String responseBody = response.getBody().asString();
        assertThat("Response should be an empty object", responseBody, equalTo("{}"));
        
        System.out.println("Successfully verified empty response object: " + responseBody);
    }

    @And("the response should match the delete post JSON schema")
    public void verifyResponseMatchesDeletePostSchema() {
        JsonSchemaValidatorUtil.validateJsonSchema(response, "schemas/delete-post-schema.json");
        System.out.println("The response matches the delete post JSON schema");
    }

    @Then("the delete post response status code should be {int}")
    public void verifyDeletePostResponseStatusCode(int expectedStatusCode) {
        assertThat(response.statusCode(), is(expectedStatusCode));
        System.out.println("Response status code verified: " + response.statusCode());
    }
} 