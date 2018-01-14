package com.david.spring.cucumber.pageobjects.duckduckgo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.david.spring.cucumber.pageobjects.PageObject;

public class DuckduckgoResultsPage extends PageObject {
	
	@FindBy(xpath="//*[@id=\"links\"]")
	private WebElement links;
	
	public DuckduckgoResultsPage(WebDriver driver) {
		super(driver);
	}
	
	public List<WebElement> getAllResults() {
		return links.findElements(By.className("result"));
	}
	
}
