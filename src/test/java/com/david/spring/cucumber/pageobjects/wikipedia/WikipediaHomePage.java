package com.david.spring.cucumber.pageobjects.wikipedia;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.david.spring.cucumber.pageobjects.PageObject;

public class WikipediaHomePage extends PageObject {

	private final static Logger logger = LoggerFactory.getLogger(WikipediaHomePage.class);
	
	@FindBy(xpath="//*[@id=\"searchInput\"]")
	private WebElement searchField;
	
	@FindBy(xpath="/html/body/div[6]/div")
	private WebElement resultSuggestions;
	
	public WikipediaHomePage(WebDriver driver) {
		super(driver);
	}
	
	public void searchText(String input) {
		searchField.sendKeys(input);
	}

	public void selectTheTechnicalResult() {
		List<WebElement> links = getSuggestionLinks();
		
		for (WebElement link: links) {
			String linkText = link.getText();
			logger.debug("link text: " + linkText);
			if(linkText.contains("software") || linkText.contains("language") || linkText.contains("IT")) {
				// somehow the click() on an embedded link in a div doesn't work with gecko driver (but works with chrome driver).
				Actions act = new Actions(webdriver);
				act.moveToElement(link).click().perform();
//				link.click();
				return;
			}
		}
		
	}

	public void selectSuggestionAtIndex(int index) {
		List<WebElement> links = getSuggestionLinks();
//		links.get(index - 1).click();

		Actions act = new Actions(webdriver);
		WebElement link = links.get(index - 1);
		act.moveToElement(link).click().perform();
	}
	
	public List<WebElement> getSuggestionLinks() {
		return resultSuggestions.findElements(By.tagName("a"));
	}

}
