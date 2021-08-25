package com.crm.qa.testcases;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.*;
import com.crm.qa.util.SeleniumUtilities;

public class HomePageTests extends TestBase {

	HomePage homePage;
	LoginPage loginPage;
	ContactsPage contactsPage;
	SeleniumUtilities seleniumUtils;
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
		seleniumUtils = new SeleniumUtilities();
		homePage = loginPage.login(testProperties.getBrowserProps().getUsername(),
				testProperties.getBrowserProps().getUserPassword());
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		logger.info("Validating Home Page Title");
		Assert.assertEquals(driver.getTitle(), "CRMPRO");
	}

	@Test(priority = 2)
	public void verifyHomePageUserNameLabelTest() {
		logger.info("Validating Home Page UserName Label");
		seleniumUtils.switchToFrame("mainpanel");
		Assert.assertTrue(homePage.getUserNameLabel("group automation").isDisplayed());
	}

	@Test(priority = 3, groups = "Home Page Links", enabled = false)
	public void verifyContactsLinkTest() {
		logger.info("Validating Contacts Page Link on Home Page");
		seleniumUtils.switchToFrame("mainpanel");
		homePage.getContactsPage();
		Assert.assertEquals(driver.getTitle(), "CRMPRO");
	}

	@Test(priority = 4, groups = "Home Page Links Options", enabled = false)
	public void verifyContactsLinkHover() {
		logger.info("Validating various Hover links for Contacts Link on Home Page");
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
	public void tearDown() {
		driver.quit();
	}

}
