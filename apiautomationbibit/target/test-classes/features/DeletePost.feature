Feature: Delete a Post
  As a user
  I want to delete a post using the /posts/{id} endpoint
  So that I can verify the deletion was successful

  Scenario: Successfully delete a post
    When I send a DELETE request to "/posts/1"
    Then the delete post response status code should be 200
    And the response should be an empty object
    And the response should match the delete post JSON schema 