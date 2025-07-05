Feature: Create a New Post
  As a user
  I want to create a new post using the /posts endpoint
  So that I can verify the response and schema

  Scenario: Successfully create a new post
    Given I have the post details with title "Learn API Testing", body "Practicing API testing with JSONPlaceholder", and userId 101
    When I send a POST request to "/posts"
    Then the create post response status code should be 201
    And the response should contain the same title, body, and userId
    And the response should match the post JSON schema 