Feature: As a user, I want to look for an article on Wikipedia

Scenario Outline: I want to find an article on Wikipedia

Given I am on the search engine home page "https://en.wikipedia.org"
And I type a string <article>
When I select the suggestion at the index 1
Then I should redirected to the <article> article

Examples:
    | article	| 
    | Selenium 	| 
    | Cucumber 	|  