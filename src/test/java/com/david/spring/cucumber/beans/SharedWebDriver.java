package com.david.spring.cucumber.beans;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thanks to the project https://github.com/cucumber/cucumber-jvm
 * The following has been written upon 
 * https://github.com/cucumber/cucumber-jvm/blob/master/examples/java-webbit-websockets-selenium/src/test/java/cucumber/examples/java/websockets/SharedDriver.java
 * in order to properly close the webdriver bean after execution of all the tests.
 */
public class SharedWebDriver extends EventFiringWebDriver {

	private static final DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	private static final WebDriver REAL_DRIVER = new FirefoxDriver(capabilities);
	private static final Logger logger = LoggerFactory.getLogger(SharedWebDriver.class);

	private static final Thread CLOSE_THREAD = new Thread() {
		@Override
		public void run() {
			REAL_DRIVER.close();
		}
	};

	static {
		System.setProperty("webdriver.gecko.driver", "/usr/bin/firefox");
		
		Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
	}

	public SharedWebDriver() {
		super(REAL_DRIVER);
	}

	@Override
	public void close() {
		if (Thread.currentThread() != CLOSE_THREAD) {
			logger.info("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
		}
		super.close();
	}
	
}
