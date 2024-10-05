package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstatnts;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By productCount = By.className("product-thumb");

	public int getSearchProductCount() {
		return eleUtil.waitForElementsToBeVisible(productCount, AppConstatnts.SMALL_DEFAULT_TIMEOUT).size();
	}

	public ProductInfoPage selectProduct(String searchProductName) {
		By product = By.linkText(searchProductName);
		eleUtil.doClickWithWait(product, AppConstatnts.SMALL_DEFAULT_TIMEOUT);
		return new ProductInfoPage(driver);
	}

}
