package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstatnts;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String acttitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(acttitle, AppConstatnts.LOGIN_PAGE_TITLE);
	}

	@Test
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstatnts.LOGIN_PAGE_URL_FRACTION));
	}

	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertEquals(loginPage.isForgotPwdLinkDispayed(), true);
	}

	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstatnts.ACCOUNTS_PAGE_TITLE);
	}
}
