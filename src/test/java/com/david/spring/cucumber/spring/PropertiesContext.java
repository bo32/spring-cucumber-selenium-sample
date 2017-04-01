package com.david.spring.cucumber.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesContext {
	
	@Value("${selenium.screenshotOnFailure}")
	private String screenshotOnFailure;
	
	@Bean("screenshotOnFailure")
	public boolean takeScreenshotOnFailure() {
		return Boolean.parseBoolean(screenshotOnFailure);
	}
	
	@Value("${selenium.screenshotDestinationFolder}")
	private String screenshotDestinationFolder;
	
	@Bean("screenshotDestinationFolder")
	public String getScreenshotDestinationFolder() {
		return screenshotDestinationFolder;
	}

}
