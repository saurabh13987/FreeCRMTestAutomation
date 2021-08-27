package com.crm.qa.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.qa.testproperties.TestProperties;
import com.crm.qa.util.WindowsActions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.crm.qa.util.DriverForBrowser;

public class TestBase {

	public static WebDriver driver;
	public final static String propertiesFileName = "/src/main/java/com/crm/qa/config/config.properties";
	public final static String testEvidenceFolder = "/screenshots/";

	public static TestProperties testProperties;
	public static WindowsActions windowsActions;
	public static DriverForBrowser driverForBrowser;
	public static HashMap<String, String> browserParams = new HashMap<String, String>();

	public static ExtentReports extent;
	public static ExtentTest extentTest;

	private static Logger logger = Logger.getLogger(TestBase.class);

	@Parameters({ "browser", "browserDriverPath", "headlessBrowser", "maximiseBrowser", "browserUrl", "userName",
			"password", "implicitWait", "pageLoadWait", "extentReportPath", "environment" })
	@BeforeSuite
	public static void initialization(String browser, String browserDriverPath, String headlessBrowser,
			String maximiseBrowser, String browserUrl, String userName, String password, String implicitWait,
			String pageLoadWait, String extentReportPath, String environment) {

		try {
			extentreportInit(extentReportPath, environment, browser);
			browserParamsInit(browser, browserDriverPath, headlessBrowser, maximiseBrowser, browserUrl, userName,
					password, implicitWait, pageLoadWait);
			testProperties = new TestProperties();
			windowsActions = new WindowsActions();
			driverForBrowser = new DriverForBrowser();

			testProperties.setBrowserprops(propertiesFileName, browserParams);

		} catch (Exception exception) {
			StringWriter exceptionLogs = new StringWriter();
			exception.printStackTrace(new PrintWriter(exceptionLogs));
			logger.error(exceptionLogs.toString());
		}
	}

	public static void extentreportInit(String extentReportPath, String environment, String browser) {
		extent = new ExtentReports(System.getProperty("user.dir") + extentReportPath, true);
		try {
			java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
			extent.addSystemInfo("Host Name", localMachine.getHostName());
			extent.addSystemInfo("User Name", System.getProperty("user.name"));
			extent.addSystemInfo("Environment", environment);
			extent.addSystemInfo("Browser", browser);

		} catch (Exception exception) {
			StringWriter exceptionLogs = new StringWriter();
			exception.printStackTrace(new PrintWriter(exceptionLogs));
			logger.error(exceptionLogs.toString());
		}
	}

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

	public static void driverInitialization() {
		driver = driverForBrowser.getDriver(testProperties);
		logger.info("Driver Initialised");
		driver.get(testProperties.getBrowserProps().getBrowserUrl());
	}

	@AfterSuite
	public static void endReport() {
		extent.flush();
		extent.close();
	}

}
