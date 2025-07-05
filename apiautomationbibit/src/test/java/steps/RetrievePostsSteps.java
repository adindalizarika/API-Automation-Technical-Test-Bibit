package steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.JsonSchemaValidatorUtil;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RetrievePostsSteps {
    private Response response;

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        
        response = RestAssured
                .given()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
        
        System.out.println("Response status code: " + response.statusCode());
    }

    @And("all posts should have non-null id fields")
    public void allPostsShouldHaveNonNullIdFields() {
        List<Object> posts = response.jsonPath().getList("$");
        
        for (int i = 0; i < posts.size(); i++) {
            Integer postId = response.jsonPath().getInt("[" + i + "].id");
            assertThat("Post at index " + i + " should have non-null id", postId, notNullValue());
            System.out.println("Post " + (i + 1) + " has ID: " + postId);
        }
        
        System.out.println("All " + posts.size() + " posts have non-null ID fields");
    }

    @And("the response should match the posts JSON schema")
    public void verifyResponseMatchesPostsSchema() {
        JsonSchemaValidatorUtil.validateJsonSchema(response, "schemas/posts-schema.json");
        System.out.println("The response matches the posts JSON schema");
    }

    @Then("the retrieve posts response status code should be {int}")
    public void verifyRetrievePostsResponseStatusCode(int expectedStatusCode) {
        assertThat(response.statusCode(), is(expectedStatusCode));
        System.out.println("Response status code verified: " + response.statusCode());
    }
} 