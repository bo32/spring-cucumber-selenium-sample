package com.david.spring.cucumber.spring;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.david.spring.cucumber.beans.SharedWebDriver;

@Configuration
public class CucumberContext {
	
	@Bean(name="WebDriver", destroyMethod="close")
	public SharedWebDriver getFirefoxWebDriver() {
		return new SharedWebDriver();
	}
	
	@Bean("WebDriverWait")
	public WebDriverWait getWebDriverWait() {
		return new WebDriverWait(getFirefoxWebDriver(), 5);
	}

}
