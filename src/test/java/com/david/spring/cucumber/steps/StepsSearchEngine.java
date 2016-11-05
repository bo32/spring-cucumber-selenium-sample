package com.david.spring.cucumber.steps;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.david.spring.cucumber.beans.CucumberContext;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes={CucumberContext.class})
public class StepsSearchEngine {
	
	@Autowired
	private WebDriver webdriver;
	
	@Given("^I am on the search engine home page \"([^\"]*)\"$")
	public void i_am_on_the_search_engine_home_page(String url) throws Throwable {
		webdriver.get(url);  
	}

	@Given("^I enter a string \"([^\"]*)\"$")
	public void i_enter_a_string(String research) throws Throwable {
		webdriver.findElement(By.xpath("//*[@id=\"search_form_input_homepage\"]")).sendKeys(research);
	}

	@When("^I press the search button$")
	public void i_press_the_search_button() throws Throwable {
		webdriver.findElement(By.xpath("//*[@id=\"search_button_homepage\"]")).click();
	}

	@Then("^I should see some results$")
	public void i_should_see_some_results() throws Throwable {
		WebElement links = webdriver.findElement(By.xpath("//*[@id=\"links\"]"));
		List<WebElement> results = links.findElements(By.className("result"));
		Assert.assertTrue(results.size() > 0);
	}

}
