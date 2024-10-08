package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	private WebDriver driver;
	private Actions act;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
	}

	// Perform search
	public void performSearch(By search, String searchKey, By suggListLocator, String suggName)
			throws InterruptedException {
		doSendKeys(search, searchKey);
		Thread.sleep(2000);
		List<WebElement> suggList = getElements(suggListLocator);
		for (WebElement e : suggList) {
			String suggText = e.getText();
			System.out.println(suggText);
			if (suggText.contains(suggName)) {
				e.click();
				break;
			}
		}
	}

	// Get attribute list generic method
	public List<String> getAttributeList(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleAttrvalList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attrValue = e.getAttribute(attrName);
			eleAttrvalList.add(attrValue);
		}
		return eleAttrvalList;
	}

	// Get Elements text list generic method
	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	// Get non blank list count generic method
	public int getElementsTextListCount(By locator) {
		return getElementsTextList(locator).size();
	}

	// Get empty elements count generic method
	public int getEmptyElementTextList(By locator) {
		int totalLinks = getPageElementsCount(locator);
		List<String> totalNonEmptyLinks = getElementsTextList(locator);
		return totalLinks - totalNonEmptyLinks.size();
	}

	// Page elements count generic method
	public int getPageElementsCount(By locator) {
		return getElements(locator).size();
	}

	// Find Elements generic method
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	// Get attribute generic method
	public String doElementgetAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	// get Text generic method
	public String doElementgetText(By locator) {
		String eleText = getElement(locator).getText();
		return eleText;
	}

	// Click generic method
	public void doClick(By locator) {
		getElement(locator).click();
	}

	// Send Keys generic method
	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	// Get Element generic method
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	// ******************Displayed utility Methods******************//

	// Check Multiple element exist
	public boolean checkMultipleElementExist(By locator) {
		if (getElementsCount(locator) > 1) {
			return true;
		}
		return false;
	}

	// Check single element exist
	public boolean checkSingleElementExist(By locator) {
		if (getElementsCount(locator) == 1) {
			return true;
		}
		return false;
	}

	// Is displayed single element
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	// elements count generic method
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	// ******************Select drop-down utilities******************//

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public int getDropdownCount(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions().size();
	}

	public List<String> getDropdownValueList(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsEleList = select.getOptions();
		List<String> optionsValueList = new ArrayList<String>();
		for (WebElement e : optionsEleList) {
			String text = e.getText();
			optionsValueList.add(text);
		}
		return optionsValueList;
	}

	public void doSelectValuesUsingOptions(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	// Without select class using, select the drop-down values
	public void doSelectValuesFromDropDown(By locator, String value) {
		List<WebElement> options = getElements(locator);
		for (WebElement e : options) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	// ******************Actions class utilities******************//

	public void doDragAndDrop(By drag, By drop) {
		act.dragAndDrop(getElement(drag), getElement(drop)).build().perform();
	}

	public void doActionsSendKeys(By locator, String value) {
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doActionsClick(By locator) {
		act.sendKeys(getElement(locator)).perform();
	}

	public void doContextClick(By locator) {
		act.contextClick(getElement(locator)).build().perform();
	}

	// **********************Wait Utils***********************//
	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	public void aceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public void alertSendKeys(String value, int timeOut) {
		waitForAlert(timeOut).sendKeys(value);
	}

	public String waitForTitleContains(String titleFractionValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		if (wait.until(ExpectedConditions.titleContains(titleFractionValue))) {
			return driver.getTitle();
		} else {
			System.out.println("title is not found.....");
			return null;
		}
	}

	public String waitForTitleToBe(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		if (wait.until(ExpectedConditions.titleIs(titleValue))) {
			return driver.getTitle();
		} else {
			System.out.println("title is not found.....");
			return null;
		}
	}

	public String waitForUrl(int timeOut, String urlFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlContains(urlFractionValue))) {
			return driver.getCurrentUrl();
		} else {
			System.out.println("url is not found....");
			return null;
		}

	}

	public String waitForUrlToBe(int timeOut, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
			return driver.getCurrentUrl();
		} else {
			System.out.println("url is not found....");
			return null;
		}

	}

	public void waitForFrameUsingLocatorAndSwitchToIt(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameUsingIndexAndSwitchToIt(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameUsingNameOrIdAndSwitchToIt(int timeOut, String frameNameOrId) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrId));
	}

	public void waitForFrameUsingElementOrIdAndSwitchToIt(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElement(frameLocator)));
	}

	public void doSendKeysWithWait(By locator, int timeOut, String value) {
		WebElement ele = waitForElementVisible(locator, timeOut);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClickWithWait(By locator, int timeOut) {
		waitForElementVisible(locator, timeOut).click();
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0. Default polling Time = 500 ms
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0. Default polling Time =
	 * customized
	 * 
	 * @param locator
	 * @param timeOut
	 * @param pollingTime
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeOut, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void printAllElementsText(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println(text);
		}
	}

	public List<String> getAllElementsTextList(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}

	public WebElement waitForElementVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				.withMessage("element is not present on the page.....Sorry.....");

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// **************************Custom Wait********************************//
	public static void shortWait() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void mediumWait() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void longWait() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitFor(int timeOut) {
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitForPageLoad(int timeOut) {
		long endTime = System.currentTimeMillis() + timeOut;
		while (System.currentTimeMillis() < endTime) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String pageState = js.executeScript("return document.readyState;").toString();
			System.out.println("page loading state: " + pageState);
			if (pageState.equals("complete")) {
				System.out.println("page is fully loaded with all scripts, images, css...");
				break;
			}
		}

	}

	public WebElement retryingElement(By locator, int timeOut) {
		WebElement element = null;
		int attempts = 0;
		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				System.out.println("element is found....in attempt: " + (attempts + 1));
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt: " + attempts + " for locator: " + locator);
				try {
					Thread.sleep(500);// default interval time
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}

		if (element == null) {
			try {
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			} catch (Exception e) {
				System.out.println("element is not found....tried for :" + timeOut + " secs "
						+ "with the interval of : " + 500 + " ms");
			}
		}

		return element;

	}

	public WebElement retryingElement(By locator, int timeOut, int intervalTime) {
		WebElement element = null;
		int attempts = 0;
		while (attempts < timeOut) {

			try {
				element = getElement(locator);
				System.out.println("element is found....in attempt: " + (attempts + 1));
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt: " + attempts + " for locator: " + locator);
				try {
					Thread.sleep(intervalTime);// interval time
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			attempts++;

		}

		if (element == null) {
			try {
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			} catch (Exception e) {
				System.out.println("element is not found....tried for :" + timeOut + " secs "
						+ "with the interval of : " + intervalTime + " ms");
			}
		}

		return element;

	}

	// ************************* Frames ***************************
}