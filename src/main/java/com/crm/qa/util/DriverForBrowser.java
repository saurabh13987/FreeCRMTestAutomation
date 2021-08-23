package com.crm.qa.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.listener.WebEventListener;
import com.crm.qa.testproperties.TestProperties;

public class DriverForBrowser {

	private WebDriver driver;
	private EventFiringWebDriver e_driver;
	private WebEventListener eventListener;

	public WebDriver getDriver(TestProperties testProperties) {

		String browser = testProperties.getBrowserProps().getBrowser();
		String browserPath = testProperties.getBrowserProps().getBrowserPath();
		int implicitWait = testProperties.getBrowserProps().getImplicitWait();
		int pageLoadWait = testProperties.getBrowserProps().getPageLoadWait();
		boolean headlessBrowser = testProperties.getBrowserProps().isHeadless();

		switch (browser.toLowerCase()) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", browserPath + "\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", browserPath + "\\chromedriver.exe");
			if (headlessBrowser) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("window-size=1400,800");
				options.addArguments("headless");
				driver = new ChromeDriver(options);
			} else
				driver = new ChromeDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", browserPath + "\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", browserPath + "\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		if (testProperties.getBrowserProps().isMaximise()) {
			driver.manage().window().maximize();
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(pageLoadWait, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
		return driver;
	}

}
