Feature: As a user, I want to look for an article on Wikipedia

  Scenario: I want to find an article on Wikipedia
    Given I am on the search engine home page "https://en.wikipedia.org"
    And I type a string "Cucumber"
    When I select the suggestion at the index 1
    Then I should redirected to the "Cucumber" article

  Scenario Outline: I want to find a technical article on Wikipedia
    Given I am on the search engine home page "https://en.wikipedia.org"
    And I type a string "<search>"
    When I select the technical suggestion
    Then I should redirected to the "<article>" article

    Examples: 
      | search   | article                     |
      | Selenium | Selenium_(software)         |
      | Java     | Java_(programming_language) |
