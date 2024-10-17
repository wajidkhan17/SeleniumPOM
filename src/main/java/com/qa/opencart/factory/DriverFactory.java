package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * @author wajidKhan
 */
public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * this method is used to initialize the driver on the basis of the given
	 * browserName.
	 * 
	 * @param browserName
	 * @return this method returns driver
	 */
	public WebDriver initDriver(Properties prop) {
		
		String browserName  = prop.getProperty("browser");
		System.out.println("browser name is : " + browserName);
		optionsManager = new OptionsManager(prop);
		switch (browserName) {
		case "chrome":
			if (browserName.equalsIgnoreCase("chrome")) {
//				driver = new ChromeDriver();
				tlDriver.set(new ChromeDriver());
				break;
			}
		case "firefox":
			if (browserName.equalsIgnoreCase("firefox")) {
//				driver = new FirefoxDriver();
				tlDriver.set(new FirefoxDriver());
				break;
			}
		case "safari":
			if (browserName.equalsIgnoreCase("safari")) {
//				driver = new SafariDriver();
				tlDriver.set(new SafariDriver());
				break;
			}
		default:
			System.out.println("Please launch the correct broeser" + browserName);
		}
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * 
	 * @return this returns properties reference with all the config properties
	 */

	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resource/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
