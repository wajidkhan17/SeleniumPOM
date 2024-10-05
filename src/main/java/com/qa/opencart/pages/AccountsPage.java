package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstatnts;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 1. By locator
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchIcon = By.xpath("//span/button[@type='button']");
	private By acctPageHeaders = By.xpath("//div[@id='content']/h2");

	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstatnts.ACCOUNTS_PAGE_TITLE, AppConstatnts.SMALL_DEFAULT_TIMEOUT);
		System.out.println("Account page title :" + title);
		return title;
	}

	public String getAccountPageUrl() {
		String url = eleUtil.waitForUrl(AppConstatnts.SMALL_DEFAULT_TIMEOUT, AppConstatnts.ACCOUNT_PAGE_URL_FRACTION);
		System.out.println("Account Page Url :" + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementPresence(logoutLink, AppConstatnts.SMALL_DEFAULT_TIMEOUT).isDisplayed();
	}

	public boolean isSearchFieldExist() {
		return eleUtil.waitForElementPresence(searchField, AppConstatnts.SMALL_DEFAULT_TIMEOUT).isDisplayed();
	}

	public List<String> getAccountSectionHeaderList() {
		return eleUtil.getAllElementsTextList(acctPageHeaders, AppConstatnts.SMALL_DEFAULT_TIMEOUT);
	}

	// Common page actions
	public SearchResultsPage doSearch(String productName) {
		System.out.println("Searching for : " + productName);
		eleUtil.doSendKeysWithWait(searchField, AppConstatnts.SMALL_DEFAULT_TIMEOUT, productName);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
}
