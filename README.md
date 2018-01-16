# Sample project Spring + Cucumber + Selenium

## Description

This is a sample project in order to have an easy-to-understand example of Dependency Injection of a Selenium web driver with a Spring configuration, only with annotations.
The execution of the project is only unit tests to execute with Cucumber, based on some features written in Gherkin.

The aim of this project is to reuse a web driver through different classes that implement the different steps of the Gherkin features.

Some simple extras features useful for a Selenium project are also implemented and ready to use: 
 - take a screenshot with Selenium when an error occurs;
 - use Maven and global properties for the behavior of the application; 

## Run the tests
### Choose the web-browser
To use Firefox:  
```bash
mvn clean test
```
To use Chrome:  
```bash
mvn clean test -Dwebbrowser=chrome
```

