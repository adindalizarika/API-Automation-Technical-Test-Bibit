Feature: Retrieve Posts
  As a user
  I want to retrieve all posts using the /posts endpoint
  So that I can verify the response data and schema

  Scenario: Successfully retrieve all posts
    When I send a GET request to "/posts"
    Then the retrieve posts response status code should be 200
    And all posts should have non-null id fields
    And the response should match the posts JSON schema 