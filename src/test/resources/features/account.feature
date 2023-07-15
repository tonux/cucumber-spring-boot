Feature: Account Management - Add Account

  Background:
    Given the account service is available

  Scenario: Add account
    When I send a POST request to "/api/accounts" with the following body
      | phone   | 773333333 |
      | balance | 4000.0    |
    Then the response status code should be 201

  Scenario: Get all accounts
    When I send a GET request to "/api/accounts"
    Then the response status code should be 200
    And the response body should contain a list of AccountDTO

  Scenario: Create and get account by phone number
    When I send a POST request to "/api/accounts" with the following body
      | phone   | 774444444 |
      | balance | 4000.0    |
    And the response status code should be 201
    And I send a GET request to "/api/accounts/search/774444444"
    Then the response status code should be 200
    And the response body should contain a AccountDTO