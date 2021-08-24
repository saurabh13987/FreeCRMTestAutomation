package com.crm.qa.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.crm.qa.testproperties.TestProperties;
import com.crm.qa.util.WindowsActions;
import com.crm.qa.util.DriverForBrowser;

public class TestBase {

	public static WebDriver driver;
	public final static String propertiesFileName = "/src/main/java/com/crm/qa/config/config.properties";
	public final static String testEvidenceFolder = "/screenshots/";

	public static TestProperties testProperties;
	public static WindowsActions windowsActions;
	public static DriverForBrowser driverForBrowser;
	public static HashMap<String, String> browserParams = new HashMap<String, String>();

	private static Logger logger = Logger.getLogger(TestBase.class);

	public static void browserParamsInit(String browser, String browserDriverPath, String headlessBrowser,
			String maximiseBrowser, String browserUrl, String userName, String password, String implicitWait,
			String pageLoadWait) {

		browserParams.put("Browser", browser);
		browserParams.put("BrowserDriverPath", browserDriverPath);
		browserParams.put("HeadLessBrowser", headlessBrowser);
		browserParams.put("MaximiseBrowser", maximiseBrowser);
		browserParams.put("BrowserURL", browserUrl);
		browserParams.put("UserName", userName);
		browserParams.put("Password", password);
		browserParams.put("BrowserImplicitWait", implicitWait);
		browserParams.put("BrowserPageLoadWait", pageLoadWait);
	}

	public static void initialization() {

		try {
			testProperties = new TestProperties();
			windowsActions = new WindowsActions();
			driverForBrowser = new DriverForBrowser();

			testProperties.setBrowserprops(propertiesFileName, browserParams);

			driver = driverForBrowser.getDriver(testProperties);
			logger.info("Driver Initialised");

			driver.get(testProperties.getBrowserProps().getBrowserUrl());
		} catch (Exception exception) {
			StringWriter exceptionLogs = new StringWriter();
			exception.printStackTrace(new PrintWriter(exceptionLogs));
			logger.error(exceptionLogs.toString());
		}
	}

}
