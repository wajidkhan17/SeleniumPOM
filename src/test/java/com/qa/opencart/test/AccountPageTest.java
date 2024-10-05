package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstatnts;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void isLogoutLinkExist() {
		Assert.assertEquals(accPage.isLogoutLinkExist(), true);
	}

	@Test
	public void acctPageTitleTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstatnts.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void verifyAccountPageHeaders() {
		List<String> acctHeadersText = accPage.getAccountSectionHeaderList();
		Assert.assertEquals(acctHeadersText, AppConstatnts.EXPECTED_ACCOUNT_HEADERS_LIST);
	}
}
