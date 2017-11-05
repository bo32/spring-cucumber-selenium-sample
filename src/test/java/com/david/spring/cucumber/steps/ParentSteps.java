package com.david.spring.cucumber.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.david.spring.cucumber.spring.CucumberContext;
import com.david.spring.cucumber.spring.PropertiesContext;

@ContextConfiguration(classes={CucumberContext.class, PropertiesContext.class})
public class ParentSteps {

	@Autowired
	protected WebDriver webdriver;
	
	@Autowired
	protected WebDriverWait wait;
	
	@Autowired
	protected boolean screenshotOnFailure;
	
	@Autowired
	protected String screenshotDestinationFolder;
	
}
