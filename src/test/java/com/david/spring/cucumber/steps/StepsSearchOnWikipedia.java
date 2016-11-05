package com.david.spring.cucumber.steps;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.david.spring.cucumber.beans.CucumberContext;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes={CucumberContext.class})
public class StepsSearchOnWikipedia {
	
	@Autowired
	private WebDriver webdriver;
	@Autowired
	private WebDriverWait wait;
	
	@Given("^I type a string Selenium$")
	public void i_type_a_string_Selenium() throws Throwable {
	    webdriver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys("Selenium");
	}

	@Given("^I type a string Cucumber$")
	public void i_type_a_string_Cucumber() throws Throwable {
		webdriver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys("Cucumber");
	}
	
	@When("^I select the suggestion at the index (\\d+)$")
	public void i_select_the_suggestion_at_the_index(int index) throws Throwable {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("suggestions-results")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div")));
		WebElement select = webdriver.findElement(By.xpath("/html/body/div[6]/div"));
		List<WebElement> links = select.findElements(By.tagName("a"));
		links.get(index - 1).click();
	}

	@Then("^I should redirected to the Selenium article$")
	public void i_should_redirected_to_the_Selenium_article() throws Throwable {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"firstHeading\"]")));
	    Assert.assertEquals("https://en.wikipedia.org/wiki/Selenium", webdriver.getCurrentUrl());
	}
	
	@Then("^I should redirected to the Cucumber article$")
	public void i_should_redirected_to_the_Cucumber_article() throws Throwable {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"firstHeading\"]")));
	    Assert.assertEquals("https://en.wikipedia.org/wiki/Cucumber", webdriver.getCurrentUrl());
	}

}
