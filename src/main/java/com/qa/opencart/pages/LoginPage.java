package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstatnts;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. By Locator or Object repository
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By registrationLink = By.linkText("Register");

	// 2. Page class constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. Page actions
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstatnts.LOGIN_PAGE_TITLE, AppConstatnts.SMALL_DEFAULT_TIMEOUT);
		System.out.println("Login page title :" + title);
		return title;
	}

	public String getLoginPageUrl() {
		String url = eleUtil.waitForUrl(AppConstatnts.SMALL_DEFAULT_TIMEOUT, AppConstatnts.LOGIN_PAGE_URL_FRACTION);
		System.out.println("Login Page Url :" + url);
		return url;
	}

	public boolean isForgotPwdLinkDispayed() {
		return eleUtil.waitForElementPresence(forgotPwdLink, AppConstatnts.SMALL_DEFAULT_TIMEOUT).isDisplayed();
	}

	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.waitForElementPresence(emailId, AppConstatnts.SMALL_DEFAULT_TIMEOUT);
		eleUtil.doSendKeysWithWait(emailId, AppConstatnts.SMALL_DEFAULT_TIMEOUT, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
//		return eleUtil.waitForTitleToBe(AppConstatnts.ACCOUNTS_PAGE_TITLE, AppConstatnts.MEDEIUM_DEFAULT_TIMEOUT);
		return new AccountsPage(driver);
	}

	public RegisterPage goToRegisterPage() {
		eleUtil.doClickWithWait(registrationLink, AppConstatnts.SMALL_DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}
}
