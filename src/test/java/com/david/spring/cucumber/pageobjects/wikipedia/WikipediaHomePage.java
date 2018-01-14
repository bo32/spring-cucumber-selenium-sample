package com.david.spring.cucumber.pageobjects.wikipedia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.david.spring.cucumber.pageobjects.PageObject;

public class WikipediaHomePage extends PageObject {
	
	@FindBy(xpath="//*[@id=\"searchInput\"]")
	private WebElement searchField;
	
	public WikipediaHomePage(WebDriver driver) {
		super(driver);
	}
	
	public void searchText(String input) {
		searchField.sendKeys(input);
	}

}
