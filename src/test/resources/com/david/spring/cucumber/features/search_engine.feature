Feature: As a user, I want to look for something on a search engine

Scenario: I do my research and get some results

Given I am on the search engine home page "http://duckduckgo.com"
And I enter a string "Selenium"
When I press the search button
Then I should see some results