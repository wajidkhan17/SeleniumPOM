package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductSearchTest extends BaseTest {

	@BeforeClass
	public void productSearchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "Macbook", "MacBook Air" }, { "Macbook", "MacBook Pro" },
				{ "Samsung", "Samsung SyncMaster 941BW" },

		};
	}

	@Test(dataProvider = "getProductData")
	public void productSearchPage(String searchKey, String productName) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String actProductHeaderName = productInfoPage.getProductHeaderValue();
		Assert.assertEquals(actProductHeaderName, productName);
	}

	@DataProvider
	public Object[][] getProductInfo() {
		return new Object[][] { { "Macbook", "MacBook Air", 4 }, { "Macbook", "MacBook Pro", 4 },
				{ "Samsung", "Samsung SyncMaster 941BW", 1 },

		};
	}

	@Test(dataProvider = "getProductInfo")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}

	@Test
	public void productDataTest() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfo = productInfoPage.getProductInfo();

		softAssert.assertEquals(actProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfo.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actProductInfo.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actProductInfo.get("productprice"), "$2,000.00");
		softAssert.assertEquals(actProductInfo.get("extaxprice"), "Ex Tax:$2,000.00");
		softAssert.assertAll();
	}
}
