package com.crm.qa.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.qa.testproperties.TestProperties;

public class DriverForBrowser {

	private WebDriver driver;

	public WebDriver getDriver(TestProperties testProperties) {

		String browser = testProperties.getBrowserProps().getBrowser();
		String driverPath = testProperties.getBrowserProps().getBrowserPath();
		int implicitWait = testProperties.getBrowserProps().getImplicitWait();
		int pageLoadWait = testProperties.getBrowserProps().getPageLoadWait();
		boolean headlessBrowser = testProperties.getBrowserProps().isHeadless();

		driverPath = System.getProperty("user.dir") + driverPath;

		switch (browser.toLowerCase()) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
			if (headlessBrowser) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("window-size=1400,800");
				options.addArguments("headless");
				driver = new ChromeDriver(options);
			} else
				driver = new ChromeDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", driverPath + "msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		if (testProperties.getBrowserProps().isMaximise()) {
			driver.manage().window().maximize();
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(pageLoadWait, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
		return driver;
	}

}
