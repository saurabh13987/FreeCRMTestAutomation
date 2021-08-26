package com.crm.qa.testproperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import org.apache.log4j.Logger;

import com.crm.qa.util.WindowsActions;

public class BrowserProperties {

	// Properties File Variable
	private final String browserNameProperties = "Browser";
	private final String browserPathProperties = "BrowserDriverPath";
	private final String browserUrlProperties = "BrowserURL";
	private final String browserMaximiseProperties = "MaximiseBrowser";
	private final String browserHeadlessProperties = "HeadLessBrowser";
	private final String userNameProperties = "UserName";
	private final String passwordProperties = "Password";
	private final String browserImplicitWaitProperties = "BrowserImplicitWait";
	private final String browserPageLoadWaitProperties = "BrowserPageLoadWait";
	private final String lineSeparator = "***********************************************************************";

	private static String browser;
	private static String browserPath;
	private static String browserUrl;
	private static boolean browserMaximise;
	private static boolean browserHeadless;
	private static String userName;
	private static String userPassword;
	private static int implicitWait;
	private static int pageLoadWait;

	private Logger logger = Logger.getLogger(this.getClass());

	public BrowserProperties(String propsFileName, HashMap<String, String> browserParams) throws IOException {
		WindowsActions actions = new WindowsActions();
		logger.info("Reading Browser Properties.....");
		Properties props = actions.readPropsFile(propsFileName);
		setBrowserProperties(props, browserParams);
	}

	public void setBrowserProperties(Properties props, HashMap<String, String> browserParams) {
		browser = browserParams.get(browserNameProperties);
		browserPath = browserParams.get(browserPathProperties);
		browserUrl = browserParams.get(browserUrlProperties);
		browserMaximise = Boolean.parseBoolean(browserParams.get(browserMaximiseProperties));
		browserHeadless = Boolean.parseBoolean(browserParams.get(browserHeadlessProperties));
		userName = browserParams.get(userNameProperties);
		userPassword = browserParams.get(passwordProperties);
		implicitWait = Integer.parseInt(browserParams.get(browserImplicitWaitProperties));
		pageLoadWait = Integer.parseInt(browserParams.get(browserPageLoadWaitProperties));
		logger.info(lineSeparator);
		logger.info("Broswer set as--->" + browser);
		logger.info("Browser URL set as--->" + browserUrl);
		logger.info("Testing for Maximise Window Size--->" + browserMaximise);
		logger.info("Testing for Headless Browser--->" + browserHeadless);
		logger.info("User selected as--->" + userName);
		logger.info("Password entered as--->" + userPassword);
		logger.info("Browser Implicit Time selected as--->" + implicitWait);
		logger.info("Browser Page Load Time selected as--->" + pageLoadWait);
		logger.info(lineSeparator);
	}

	public String getBrowser() {
		return browser;
	}

	public String getBrowserPath() {
		return browserPath;
	}

	public String getBrowserUrl() {
		return browserUrl;
	}

	public String getUsername() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public int getImplicitWait() {
		return implicitWait;
	}

	public int getPageLoadWait() {
		return pageLoadWait;
	}

	public boolean isMaximise() {
		return browserMaximise;
	}

	public boolean isHeadless() {
		return browserHeadless;
	}
}
