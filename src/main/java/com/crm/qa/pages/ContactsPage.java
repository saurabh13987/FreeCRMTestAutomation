package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;

	@FindBy(xpath = "//input[@name='first_name']")
	WebElement firstName;

	@FindBy(xpath = "//input[@name='middle_initial']")
	WebElement middleName;

	@FindBy(xpath = "//input[@name='surname']")
	WebElement lastName;

	@FindBy(xpath = "//input[@name='client_lookup']")
	WebElement company;

	@FindBy(xpath = "//input[@type='submit' and @value='Save']")
	WebElement saveButton;

	public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getMiddleName() {
		return middleName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getCompany() {
		return company;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	public WebElement getContactsLabel() {
		return contactsLabel;
	}

	public void createNewContact(String title, String firstName, String middleName, String lastName, String company) {
		Select select = new Select(driver.findElement(By.xpath("//select[@class='select' and @name = 'title']")));
		select.selectByVisibleText(title);
		this.firstName.sendKeys(firstName);
		if (middleName != null && !middleName.isEmpty())
			this.middleName.sendKeys(middleName);
		if (lastName != null && !lastName.isEmpty())
			this.lastName.sendKeys(lastName);
		this.company.sendKeys(company);
		saveButton.click();
	}

	public WebElement getContactName(String userName) {
		return driver.findElement(By.xpath("//a[text()='" + userName
				+ "']/parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']"));
	}

}
