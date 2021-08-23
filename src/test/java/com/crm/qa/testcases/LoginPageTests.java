package com.crm.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.*;

public class LoginPageTests extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	private Logger logger = Logger.getLogger(this.getClass());

	@BeforeMethod
	@Parameters({ "browser", "browserDriverPath", "headlessBrowser", "maximiseBrowser", "browserUrl", "userName",
			"password", "implicitWait", "pageLoadWait" })
	public void setup(String browser, String browserDriverPath, String headlessBrowser, String maximiseBrowser,
			String browserUrl, String userName, String password, String implicitWait, String pageLoadWait) {
		browserParamsInit(browser, browserDriverPath, headlessBrowser, maximiseBrowser, browserUrl, userName, password,
				implicitWait, pageLoadWait);
		initialization();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void verifyloginPageTitleTest() {
		logger.info("Validating Login Page Title");
		Assert.assertEquals(driver.getTitle(),
				"Free CRM - CRM software for customer relationship management, sales, and support.");
	}

	@Test(priority = 2)
	public void verifycrmLogoImageTest() {
		logger.info("Validating Login Page CRM Logo");
		Assert.assertTrue(loginPage.getCRMImage().isDisplayed());
	}

	@Test(priority = 3)
	public void verifyloginTest() {
		logger.info("Validating User is able to login successfully");
		homePage = loginPage.login(testProperties.getBrowserProps().getUsername(),
				testProperties.getBrowserProps().getUserPassword());
		Assert.assertEquals(driver.getTitle(), "CRMPRO");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
