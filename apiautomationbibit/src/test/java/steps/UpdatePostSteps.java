package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.JsonSchemaValidatorUtil;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UpdatePostSteps {
    private Map<String, Object> updateData;
    private Response response;

    @Given("I have the updated post details with title {string}, body {string}, and userId {int}")
    public void setUpdatedPostDetails(String title, String body, int userId) {
        updateData = new HashMap<>();
        updateData.put("title", title);
        updateData.put("body", body);
        updateData.put("userId", userId);
        
        System.out.println("Updated post details - Title: " + title + ", Body: " + body + ", UserId: " + userId);
    }

    @When("I send a PUT request to {string}")
    public void sendPutRequest(String endpoint) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        
        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updateData)
                .when()
                .put(endpoint)
                .then()
                .extract().response();
        
        System.out.println("Response status code: " + response.statusCode());
    }

    @And("the response should contain the updated title, body, and userId")
    public void verifyResponseContainsUpdatedDetails() {
        assertThat(response.jsonPath().getString("title"), equalTo(updateData.get("title")));
        assertThat(response.jsonPath().getString("body"), equalTo(updateData.get("body")));
        assertThat(response.jsonPath().getInt("userId"), equalTo(updateData.get("userId")));
        
        System.out.println("Updated title: " + response.jsonPath().getString("title"));
        System.out.println("Updated body: " + response.jsonPath().getString("body"));
        System.out.println("Updated userId: " + response.jsonPath().getInt("userId"));
        System.out.println("The response contains the updated post details");
    }

    @And("the response should match the update post JSON schema")
    public void verifyResponseMatchesUpdatePostSchema() {
        JsonSchemaValidatorUtil.validateJsonSchema(response, "schemas/update-post-schema.json");
        System.out.println("The response matches the update post JSON schema");
    }

    @Then("the update post response status code should be {int}")
    public void verifyUpdatePostResponseStatusCode(int expectedStatusCode) {
        assertThat(response.statusCode(), is(expectedStatusCode));
        System.out.println("Response status code verified: " + response.statusCode());
    }
} 