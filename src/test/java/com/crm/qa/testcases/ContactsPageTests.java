package com.crm.qa.testcases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.listener.TestExecutionListener;
import com.crm.qa.pages.*;
import com.crm.qa.util.ReadExcel;
import com.crm.qa.util.SeleniumUtilities;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(TestExecutionListener.class)
public class ContactsPageTests extends TestBase {
	ContactsPage contactsPage;
	HomePage homePage;
	LoginPage loginPage;
	SeleniumUtilities seleniumUtils;
	ReadExcel readExcelData;

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
	public void setup() {
		driverInitialization();
		loginPage = new LoginPage();
		seleniumUtils = new SeleniumUtilities();
		homePage = loginPage.login(testProperties.getBrowserProps().getUsername(),
				testProperties.getBrowserProps().getUserPassword());
	}

	@Test(priority = 1)
	public void verifyContactsPageTitleTest() {
		extentTest = extent.startTest("Contacts Page Title Test");
		seleniumUtils.switchToFrame("mainpanel");
		contactsPage = homePage.getContactsPage();
		Assert.assertEquals(driver.getTitle(), "CRMPRO");
	}

	@Test(priority = 2)
	public void verifyContactsPageLabelTest() {
		extentTest = extent.startTest("Contacts Page Label Test");
		seleniumUtils.switchToFrame("mainpanel");
		contactsPage = homePage.getContactsPage();
		Assert.assertTrue(contactsPage.getContactsLabel().isDisplayed());
	}

	@Test(priority = 3, enabled = false)
	public void verifyContactsTest() {
		extentTest = extent.startTest("Contacts Page Contact Search Test");
		seleniumUtils.switchToFrame("mainpanel");
		contactsPage = homePage.getContactsPage();
		Assert.assertTrue(contactsPage.getContactName("amruta dhale").isEnabled());
		Assert.assertTrue(!contactsPage.getContactName("amruta dhale").isSelected());
		contactsPage.getContactName("amruta dhale").click();
		Assert.assertTrue(contactsPage.getContactName("amruta dhale").isSelected());
	}

	@Test(priority = 4, dataProvider = "getContactsTestData", enabled = false)
	public void verifyCreateContactTest(String title, String firstName, String middleName, String lastName,
			String company) throws InterruptedException {
		extentTest = extent.startTest("Contacts Page Contact Creation Test");
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
