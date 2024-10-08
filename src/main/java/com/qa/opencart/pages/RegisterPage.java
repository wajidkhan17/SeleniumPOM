package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstatnts;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By firstName = By.xpath("//input[@id='input-firstname']");
	private By lastName = By.xpath("//input[@id='input-lastname']");
	private By email = By.xpath("//input[@id='input-email']");
	private By telephone = By.xpath("//input[@id='input-telephone']");
	private By password = By.xpath("//input[@id='input-password']");
	private By confirmPassword = By.xpath("//input[@id='input-confirm']");
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");
	private By checkbox = By.xpath("//input[@name='agree']");
	private By continueButton = By.xpath("//input[@value='Continue']");
	private By sucessMessage = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registrationLink = By.linkText("Register");

	public boolean userRegistration(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		eleUtil.doClick(registrationLink);
		eleUtil.doSendKeysWithWait(this.firstName, AppConstatnts.MEDEIUM_DEFAULT_TIMEOUT, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(confirmPassword, password);
		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(checkbox);
		eleUtil.doClick(continueButton);

		String actSuccessMesg = eleUtil.waitForElementVisible(sucessMessage, AppConstatnts.MEDEIUM_DEFAULT_TIMEOUT)
				.getText();
		if (actSuccessMesg.contains(AppConstatnts.REGISTER_SCCCESS_MESSG)) {
			return true;

		} else {
			return false;
			//return
		}
	}

	public void goToRegisterPage() {
		eleUtil.doClick(logoutLink);
		eleUtil.doClick(registrationLink);
	}
}
