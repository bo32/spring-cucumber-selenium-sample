package com.david.spring.cucumber.beans;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thanks to the project https://github.com/cucumber/cucumber-jvm
 * The following has been written upon 
 * https://github.com/cucumber/cucumber-jvm/blob/master/examples/java-webbit-websockets-selenium/src/test/java/cucumber/examples/java/websockets/SharedDriver.java
 * in order to properly close the webdriver bean after execution of all the tests.
 */
public class FirefoxWebDriver extends EventFiringWebDriver {
	
	private static final WebDriver webdriver;
	private static final Logger logger = LoggerFactory.getLogger(FirefoxWebDriver.class);

	private static final Thread CLOSE_THREAD = new Thread() {
		@Override
		public void run() {
			webdriver.close();
		}
	};

	static {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
		
		FirefoxOptions capabilities = new FirefoxOptions();
		webdriver = new FirefoxDriver(capabilities);
		
		Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
	}

	public FirefoxWebDriver() {
		super(webdriver);
	}

	@Override
	public void close() {
		if (Thread.currentThread() != CLOSE_THREAD) {
			logger.info("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
		}
		super.close();
	}
	
}
