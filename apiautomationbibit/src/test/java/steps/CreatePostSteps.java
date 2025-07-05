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

public class CreatePostSteps {
    private Map<String, Object> postData;
    private Response response;

    @Given("I have the post details with title {string}, body {string}, and userId {int}")
    public void setPostDetails(String title, String body, int userId) {
        postData = new HashMap<>();
        postData.put("title", title);
        postData.put("body", body);
        postData.put("userId", userId);
    }

    @When("I send a POST request to {string}")
    public void sendPostRequest(String endpoint) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        
        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(postData)
                .when()
                .post(endpoint)
                .then()
                .extract().response();
                System.out.println("Response status code: " + response.statusCode());
    }

    @Then("the create post response status code should be {int}")
    public void verifyCreatePostResponseStatusCode(int expectedStatusCode) {
        assertThat(response.statusCode(), is(expectedStatusCode));
        System.out.println("Response status code verified: " + response.statusCode());
    }

    @And("the response should contain the same title, body, and userId")
    public void verifyResponseContainsPostDetails() {
        assertThat(response.jsonPath().getString("title"), equalTo(postData.get("title")));
        assertThat(response.jsonPath().getString("body"), equalTo(postData.get("body")));
        assertThat(response.jsonPath().getInt("userId"), equalTo(postData.get("userId")));
        System.out.println("The response is matching the post details");
    }

    @And("the response should match the post JSON schema")
    public void verifyResponseMatchesPostSchema() {
        JsonSchemaValidatorUtil.validateJsonSchema(response, "schemas/post-schema.json");
        System.out.println("The response is matching the post JSON schema");
    }
} 