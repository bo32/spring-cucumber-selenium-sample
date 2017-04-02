package com.david.spring.cucumber.steps;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepsSearchOnWikipedia extends ParentSteps {
	
	@Autowired
	private WebDriver webdriver;
	@Autowired
	private WebDriverWait wait;
	
	private final static Logger logger = LoggerFactory.getLogger(StepsSearchOnWikipedia.class);
	
	@When("^I select the suggestion at the index (\\d+)$")
	public void i_select_the_suggestion_at_the_index(int index) throws Throwable {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("suggestions-results")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div")));
		WebElement select = webdriver.findElement(By.xpath("/html/body/div[6]/div"));
		List<WebElement> links = select.findElements(By.tagName("a"));
		links.get(index - 1).click();
	}
	
	@Given("^I type a string \"([^\"]*)\"$")
	public void i_type_a_string(String article) throws Throwable {
		webdriver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys(article);
	}

	@When("^I select the technical suggestion$")
	public void i_select_the_technical_suggestion() throws Throwable {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("suggestions-results")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div")));
		WebElement select = webdriver.findElement(By.xpath("/html/body/div[6]/div"));
		List<WebElement> links = select.findElements(By.tagName("a"));
		logger.debug("count links: " + links.size());
		
		for (WebElement link: links) {
			String linkText = link.getText();
			logger.debug("link text: " + linkText);
			if(linkText.contains("software") || linkText.contains("language") || linkText.contains("IT")) {
				link.click();
				return;
			}
		}
	}

	@Then("^I should redirected to the \"([^\"]*)\" article$")
	public void i_should_redirected_to_the_article(String article) throws Throwable {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"firstHeading\"]")));
	    Assert.assertEquals("https://en.wikipedia.org/wiki/" + article, webdriver.getCurrentUrl());
	}

}
