Feature: Update a Post
  As a user
  I want to update a post using the /posts/{id} endpoint
  So that I can verify the update was successful

  Scenario: Successfully update a post
    Given I have the updated post details with title "Updated Post Title", body "This is the updated body content.", and userId 99
    When I send a PUT request to "/posts/1"
    Then the update post response status code should be 200
    And the response should contain the updated title, body, and userId
    And the response should match the update post JSON schema 