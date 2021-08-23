package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	@FindBy(name = "username")
	WebElement userName;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginButton;

	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	WebElement signUpButton;

	@FindBy(xpath = "//img[@class='img-responsive' and contains(@src,'logo')]")
	WebElement crmLogo;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getSignUpButton() {
		return signUpButton;
	}

	public WebElement getCRMImage() {
		return crmLogo;
	}

	public HomePage login(String userName, String password) {
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		loginButton.click();
		return new HomePage();
	}

}
