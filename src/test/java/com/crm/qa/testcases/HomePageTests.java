package com.crm.qa.testcases;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.listener.TestExecutionListener;
import com.crm.qa.pages.*;
import com.crm.qa.util.SeleniumUtilities;

@Listeners(TestExecutionListener.class)
public class HomePageTests extends TestBase {

	HomePage homePage;
	LoginPage loginPage;
	ContactsPage contactsPage;
	SeleniumUtilities seleniumUtils;

	@BeforeMethod
	public void setup() {
		driverInitialization();
		loginPage = new LoginPage();
		seleniumUtils = new SeleniumUtilities();
		homePage = loginPage.login(testProperties.getBrowserProps().getUsername(),
				testProperties.getBrowserProps().getUserPassword());
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		Assert.assertEquals(driver.getTitle(), "CRMPRO");
	}

	@Test(priority = 2)
	public void verifyHomePageUserNameLabelTest() {
		seleniumUtils.switchToFrame("mainpanel");
		Assert.assertTrue(homePage.getUserNameLabel("group automation").isDisplayed());
	}

	@Test(priority = 3, groups = "Home Page Links", enabled = false)
	public void verifyContactsLinkTest() {
		seleniumUtils.switchToFrame("mainpanel");
		homePage.getContactsPage();
		Assert.assertEquals(driver.getTitle(), "CRMPRO");
	}

	@Test(priority = 4, groups = "Home Page Links Options", enabled = false)
	public void verifyContactsLinkHover() {
		ArrayList<String> elementsListExpected = new ArrayList<String>();
		elementsListExpected.add("New Contact");
		elementsListExpected.add("Combined Form");
		elementsListExpected.add("Full Search Form");
		seleniumUtils.switchToFrame("mainpanel");
		Actions action = new Actions(driver);
		action.moveToElement(homePage.getContactsLink()).build().perform();
		ArrayList<String> elementsListActual = new ArrayList<String>();
		for (WebElement element : homePage.getContactsLinkOptions()) {
			elementsListActual.add(element.getText());
		}
		Assert.assertEquals(elementsListActual, elementsListExpected);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		driver.quit();
	}

}
