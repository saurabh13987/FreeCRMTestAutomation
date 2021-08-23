package com.crm.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;

	@FindAll({ @FindBy(xpath = "//a[text()='Contacts']/parent::li//ul//a") })
	List<WebElement> contactsLinkOptions;

	@FindAll({ @FindBy(xpath = "//a[text()='Deals']/parent::li//ul//a") })
	List<WebElement> dealsLinkOptions;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public ContactsPage getContactsPage() {
		contactsLink.click();
		return new ContactsPage();
	}

	public List<WebElement> getContactsLinkOptions() {
		return contactsLinkOptions;
	}

	public WebElement getUserNameLabel(String userName) {
		return driver.findElement(By.xpath("//td[contains(text(),'User: " + userName + "')]"));
	}

}
