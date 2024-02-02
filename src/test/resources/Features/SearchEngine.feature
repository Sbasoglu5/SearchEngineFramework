Feature: Search Engine Test

  @google @smoke @regression
  Scenario: Verify the first search result on google
    When user submits the search term "facebook"
    Then the user should see search results
    And the first result should contain the expected information

  @yahoo @regression
  Scenario: Verify the first search result on yahoo
    Given user submits the search term "yahoo"
    When user clicks on the yahoo link
    Then user submits the search term facebook
    And the user should see search results
    And the first result on yahoo should contain the expected information

  @monkey @regression
  Scenario Outline: Verify the first search result on Google with Monkey Test
    When user submits the search term "<search>"
    Then the user should see search results
    And the first result should verify "<verification>" the results

    Examples:
      |search|verification|
      |facebook|Facebook - log in or sign up|
      |instagram|Instagram                  |