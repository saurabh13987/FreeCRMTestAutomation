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
import com.relevantcodes.extentreports.LogStatus;

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
		extentTest = extent.startTest("Login Page Title Test");
		Assert.assertEquals(driver.getTitle(),
				"Free CRM - CRM software for customer relationship management, sales, and support.");
	}

	@Test(priority = 2)
	public void verifycrmLogoImageTest() {
		extentTest = extent.startTest("Login Page Logo Test");
		Assert.assertTrue(loginPage.getCRMImage().isDisplayed());
	}

	@Test(priority = 3)
	public void verifyloginTest() {
		extentTest = extent.startTest("Login Page Test");
		homePage = loginPage.login(testProperties.getBrowserProps().getUsername(),
				testProperties.getBrowserProps().getUserPassword());
		Assert.assertEquals(driver.getTitle(), "CRMPRO");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test Case Failed is : " + result.getMethod().getMethodName());
			extentTest.log(LogStatus.FAIL, "Test Case Error is : " + result.getThrowable());

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case Skipped is : " + result.getMethod().getMethodName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case Passed is : " + result.getMethod().getMethodName());
		}
		extent.endTest(extentTest);
		driver.quit();
	}

}
