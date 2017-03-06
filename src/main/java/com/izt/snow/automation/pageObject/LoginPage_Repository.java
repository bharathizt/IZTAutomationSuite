package com.izt.snow.automation.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 
 * @author Bharath
 * 
 */
/* Login Page Web Elements */
public class LoginPage_Repository {

	@FindBy(id = "user_name")
	private WebElement userNameEdit;

	@FindBy(id = "user_password")
	private WebElement passwordEdit;

	@FindBy(xpath = "//label[@class='checkbox-label']")
	private WebElement checkBoxClick;

	@FindBy(id = "sysverb_login")
	private WebElement logInButton;

	@FindBy(xpath = "//a[@class='web login-link']")
	private WebElement useExternalLoginLink;

	public WebElement getUserNameEdit() {
		return userNameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getCheckBoxClick() {
		return checkBoxClick;
	}

	public WebElement getLogInButton() {
		return logInButton;
	}

	public WebElement getUseExternalLoginLink() {
		return useExternalLoginLink;
	}

	
}
