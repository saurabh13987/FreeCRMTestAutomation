package com.crm.qa.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.*;
import com.crm.qa.util.ReadExcel;
import com.crm.qa.util.SeleniumUtilities;

public class ContactsPageTests extends TestBase {
	ContactsPage contactsPage;
	HomePage homePage;
	LoginPage loginPage;
	SeleniumUtilities seleniumUtils;
	ReadExcel readExcelData;
	private Logger logger = Logger.getLogger(this.getClass());

	public final String testFileName = "freecrm_data.xlsx";
	public final String testSheetName = "contacts";
	public final String testDataFile = System.getProperty("user.dir") + "/src/main/java/com/crm/qa/testdata/"
			+ testFileName;

	@DataProvider
	public Object[][] getContactsTestData() {
		readExcelData = new ReadExcel();
		Object contactsTestData[][] = readExcelData.getTestData(testDataFile, testSheetName);
		return contactsTestData;
	}

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
	public void verifyContactsPageTitleTest() {
		seleniumUtils.switchToFrame("mainpanel");
		contactsPage = homePage.getContactsPage();
		logger.info("Validating Contacts Page Title");
		Assert.assertEquals(driver.getTitle(), "CRMPRO");
	}

	@Test(priority = 2)
	public void verifyContactsPageLabelTest() {
		seleniumUtils.switchToFrame("mainpanel");
		contactsPage = homePage.getContactsPage();
		logger.info("Validating Contacts Page Label");
		Assert.assertTrue(contactsPage.getContactsLabel().isDisplayed());
	}

	@Test(priority = 3)
	public void verifyContactsTest() {
		seleniumUtils.switchToFrame("mainpanel");
		contactsPage = homePage.getContactsPage();
		logger.info("Validating Contacts");
		Assert.assertTrue(contactsPage.getContactName("amruta dhale").isEnabled());
		Assert.assertTrue(!contactsPage.getContactName("amruta dhale").isSelected());
		contactsPage.getContactName("amruta dhale").click();
		Assert.assertTrue(contactsPage.getContactName("amruta dhale").isSelected());
	}

	@Test(priority = 4, dataProvider = "getContactsTestData")
	public void verifyCreateContactTest(String title, String firstName, String middleName, String lastName,
			String company) throws InterruptedException {
		logger.info("Validating Creation of new contact");
		seleniumUtils.switchToFrame("mainpanel");
		Actions action = new Actions(driver);
		action.moveToElement(homePage.getContactsLink()).build().perform();
		for (WebElement element : homePage.getContactsLinkOptions()) {
			if (element.getText().equals("New Contact")) {
				element.click();
				break;
			} else {
				continue;
			}
		}
		contactsPage = new ContactsPage();
		contactsPage.createNewContact(title, firstName, middleName, lastName, company);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
