package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstatnts {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final Integer SMALL_DEFAULT_TIMEOUT = 5;
	public static final Integer MEDEIUM_DEFAULT_TIMEOUT = 10;
	public static final Integer LONG_DEFAULT_TIMEOUT = 15;
	public static final String REGISTER_SCCCESS_MESSG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "register";

	public static final List<String> EXPECTED_ACCOUNT_HEADERS_LIST = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");

}
