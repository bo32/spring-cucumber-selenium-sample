package com.david.spring.cucumber.steps;

import org.springframework.test.context.ContextConfiguration;

import com.david.spring.cucumber.spring.CucumberContext;
import com.david.spring.cucumber.spring.PropertiesContext;

@ContextConfiguration(classes={CucumberContext.class, PropertiesContext.class})
public class ParentSteps {

}
