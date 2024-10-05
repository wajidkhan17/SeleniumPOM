package com.qa.opencart.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstatnts;
import com.qa.opencart.utils.ExcelUtil;


public class RegisterTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.goToRegisterPage();
	}

//	@DataProvider
//	public Object[][] getRegisterData() {
//		return new Object[][] { { "Tom", "automation", "Riyaesteauto3@gmail.com", "9999923230", "riya@123", "yes" },
//				{ "Tom1", "automation1", "Riyaesteauto4@gmail.com", "9999923231", "riya@123", "no" },
//				{ "Tom2", "automation2", "Riyaesteauto5@gmail.com", "9999923232", "riya@123", "yes" } };
//	}

	@DataProvider
	public Object[][] getRegisterExcelData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstatnts.REGISTER_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider = "getRegisterExcelData")
	public void userRegistration(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		boolean succeFlag = registerPage.userRegistration(firstName, lastName, email, telephone, password, subscribe);
		registerPage.goToRegisterPage();
		Assert.assertEquals(succeFlag, true);
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
