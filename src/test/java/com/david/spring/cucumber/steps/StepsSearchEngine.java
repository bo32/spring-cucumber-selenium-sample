package com.david.spring.cucumber.steps;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.david.spring.cucumber.pageobjects.duckduckgo.DuckduckgoHomePage;
import com.david.spring.cucumber.pageobjects.duckduckgo.DuckduckgoResultsPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepsSearchEngine extends ParentSteps {
	
	private static final Logger logger = LoggerFactory.getLogger(StepsSearchEngine.class);
	private DuckduckgoHomePage homePage;
	private DuckduckgoResultsPage resultsPage;
	
	@Given("^I am on the search engine home page \"([^\"]*)\"$")
	public void i_am_on_the_search_engine_home_page(String url) throws Throwable {
		webdriver.get(url);
		homePage = new DuckduckgoHomePage(webdriver);
	}

	@Given("^I enter a string \"([^\"]*)\"$")
	public void i_enter_a_string(String research) throws Throwable {
		homePage.inputText(research);
	}

	@When("^I press the search button$")
	public void i_press_the_search_button() throws Throwable {
		resultsPage = homePage.clickOnSearchButton();
	}

	@Then("^I should see some results$")
	public void i_should_see_some_results() throws Throwable {
		List<WebElement> results = resultsPage.getAllResults();
		Assert.assertTrue(results.size() > 0);
	}

	@After
	public void afterScenario(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			if (screenshotOnFailure) {
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmss");
				String timestamp = sdf.format(now);
				
				File srcFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
				File destFile = new File(screenshotDestinationFolder
						+ timestamp + "_"
						+ scenario.getName().replaceAll(" ", "_") + ".png");
				FileUtils.moveFile(srcFile, destFile);
				logger.info("Screenshot taken: " + destFile.getAbsolutePath());
			}
		}
	}

}
