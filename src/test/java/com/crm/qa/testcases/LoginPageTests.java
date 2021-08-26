package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.listener.TestExecutionListener;
import com.crm.qa.pages.*;

@Listeners(TestExecutionListener.class)
public class LoginPageTests extends TestBase {
	LoginPage loginPage;
	HomePage homePage;

	@BeforeMethod
	public void setup() {
		driverInitialization();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void verifyloginPageTitleTest() {
		Assert.assertEquals(driver.getTitle(),
				"Free CRM - CRM software for customer relationship management, sales, and support.");
	}

	@Test(priority = 2)
	public void verifycrmLogoImageTest() {
		Assert.assertTrue(loginPage.getCRMImage().isDisplayed());
	}

	@Test(priority = 3)
	public void verifyloginTest() {
		homePage = loginPage.login(testProperties.getBrowserProps().getUsername(),
				testProperties.getBrowserProps().getUserPassword());
		Assert.assertEquals(driver.getTitle(), "CRMPRO");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		driver.quit();
	}

}
