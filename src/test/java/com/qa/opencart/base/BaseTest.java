package com.qa.opencart.base;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Parameters;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;
	public DriverFactory df;
	public LoginPage loginPage;
	public AccountsPage accPage;
	public SearchResultsPage searchResPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerPage;

	public SoftAssert softAssert;

	@BeforeTest 
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String generated_string = RandomStringUtils.randomAlphabetic(7);
		return generated_string;
	}

	public String randomNum() {
		String generated_int = RandomStringUtils.randomNumeric(10);
		return generated_int;
	}

	public String randomAlphaNumeric() {
		String generated_string = RandomStringUtils.randomAlphabetic(7);
		String generated_int = RandomStringUtils.randomNumeric(10);
		return (generated_string + "@" + generated_int);
	}
}
