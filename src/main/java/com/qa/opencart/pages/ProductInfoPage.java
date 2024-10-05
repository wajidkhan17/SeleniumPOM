package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstatnts;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By prductMetaData = By.xpath("//div[@class='col-sm-4']/ul[@class='list-unstyled'][1]/li");
	private By prductPriceData = By.xpath("//div[@class='col-sm-4']/ul[@class='list-unstyled'][2]/li");

	private Map<String, String> productMap;

	public String getProductHeaderValue() {
		String productHeaderVal = eleUtil.doElementgetText(productHeader);
		System.out.println("prod header: " + productHeaderVal);
		return productHeaderVal;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsToBeVisible(productImages, AppConstatnts.SMALL_DEFAULT_TIMEOUT).size();
		System.out.println("images count: " + imagesCount);
		return imagesCount;
	}

	public Map<String, String> getProductInfo() {
		productMap = new LinkedHashMap<String, String>();
		// Add product name
		productMap.put("productname", getProductHeaderValue());
		// product meta data:
		getProductMetaData();
		// product price data
		getProductPriceData();
		System.out.println("-----------------act product info-----------------");
		productMap.forEach((k, v) -> System.out.println(k + ":" + v));
		return productMap;
	}

	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(prductMetaData);
		for (WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String key = meta[0].trim();
			String value = meta[1].trim();
			productMap.put(key, value);
		}
	}

	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.getElements(prductPriceData);
		String productPrice = metaPriceList.get(0).getText().trim();
		String productExtaxPrice = metaPriceList.get(1).getText().trim();
		productMap.put("productprice", productPrice);
		productMap.put("extaxprice", productExtaxPrice);
	}
}
