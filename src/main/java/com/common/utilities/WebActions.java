package com.common.utilities;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

public class WebActions extends WebDriverBaseTestPage<WebDriverTestPage> {
	// class contains Test base and commons methods
	//TestDataBean bean = new TestDataBean();
	public WebDriverWait waituntil = new WebDriverWait(driver,Duration.ofSeconds(60));
	JavascriptExecutor executor = driver;
	Actions actions = null;
	Map<?, ?> map = null;
	Wait<WebDriver> wait = null;
	// QAFExtendedWebDriver driver = new WebDriverTestBase().driver;
//	public void scrollIntoElement(By locator) {
//		WebElement element = driver.findElement(locator);
//		js = ((JavascriptExecutor) driver);
//		js.executeScript("arguments[0].scrollIntoView(true);", element);
//	}
//	public Boolean clickDropdownValue(String dropdownvalue) {
//		try {
//			scrollIntoElement(By.xpath("//*[text()='" + dropdownvalue + "']"));
//			clickUsingJs(By.xpath("//*[text()='" + dropdownvalue + "']"));
//		} catch (Exception e) {
//		}
//		return true;
//	}

	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
	}

	public void clickButton(QAFWebElement element) {
		element.waitForVisible(300000);
		element.executeScript("scrollIntoView(true);");
		element.click();
	}

	public void enterText(QAFWebElement element, String textToEnter) {
		element.waitForVisible(300000);
		element.executeScript("scrollIntoView(true);");
		element.clear();
		element.sendKeys(textToEnter);
	}

	public void enterTextUsingJs(QAFWebElement element, String value) {
		element.waitForVisible(300000);
		element.executeScript("scrollIntoView(true);");
		String args = "arguments[0].value=" + "'" + value + "'";
		System.out.println(args);
		element.clear();
		executor.executeScript(args, element);
	}

	public void enterText(By locator, String textToEnter) {
		driver.findElement(locator).waitForVisible(30000);
		driver.findElement(locator).executeScript("scrollIntoView(true);");
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(textToEnter);
	}

	public void enterTextUsingJs(By element, String value) {
		String args = "arguments[0].value=" + "'" + value + "'";
		System.out.println(args);
		int count = 0;
		boolean flag = true;
		while (flag && count <= 3)
			try {
				if (!flag) {
					break;
				}
				waitFor(element, 5, true);
				scrollIntoElement(element);
				driver.findElement(element).clear();
				executor.executeScript(args, driver.findElement(element));
				flag = false;
			} catch (Exception e) {
				count++;
			}
	}

	public void selectDropdownValue(QAFWebElement dropdown, String dropDownValue) {
		clickButton(dropdown);
		this.waitFor(8);
		QAFWebElement dropdownSelection = driver.findElement(By.xpath("//*[@title='" + dropDownValue + "']"));
		dropdownSelection.executeScript("scrollIntoView(true);");
		dropdownSelection.waitForVisible(300000);
		executor.executeScript("arguments[0].click();", dropdownSelection);
		// dropdown.waitForVisible(300000);
	}

	public void clickDropdownValue(String dropdownvalue) {
		// QAFWebElement dropdownSelection = driver.findElement(By.xpath("//*[text()='"
		// + dropdownvalue + "']"));
		QAFWebElement dropdownSelection = new QAFExtendedWebElement(
				"(//*[text()='" + dropdownvalue + "'])|(//*[contains(text(),'" + dropdownvalue + "')])");
		dropdownSelection.executeScript("scrollIntoView(true);");
		dropdownSelection.waitForVisible(300000);
		executor.executeScript("arguments[0].click();", dropdownSelection);
	}

	public void clickUsingJs(QAFWebElement element) {
		element.waitForVisible(30000);
		element.executeScript("scrollIntoView(true);");
		executor.executeScript("arguments[0].click();", element);
	}

	public Boolean clickUsingJs(By locator) {
		int count = 0;
		boolean isClicked = false;
		boolean flag = true;
		while (flag && count <= 3) {
			try {
				if (!flag) {
					break;
				}
				waitFor(locator, 5, true);
				scrollIntoElement(locator);
				executor.executeScript("arguments[0].click();", driver.findElement(locator));
				isClicked = true;
				flag = false;
			} catch (Exception e) {
				count++;
			}
		}
		return isClicked;
	}

	public Boolean jsLooseClick(By locator) {
		boolean isClicked = false;
		if (!isClicked) {
			waitFor(locator, 10, true);
			scrollIntoElement(locator);
			executor.executeScript("arguments[0].click();", driver.findElement(locator));
			isClicked = true;
		}
		return isClicked;
	}

	public void clickUsingJs(WebElement element) {
		waitFor(element, 10, true);
		executor.executeScript("arguments[0].scrollIntoView();", element);
		executor.executeScript("arguments[0].click();", element);
	}

	public void waitForClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public Boolean waitFor(By locator, int seconds, Boolean visibility) {
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(seconds))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		try {
			if (visibility) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			} else {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			}
		} catch (Exception e) {
		}
		return true;
	}

	public Boolean waitFor(WebElement element, int seconds, Boolean visibility) {
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(seconds))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		try {
			if (visibility) {
				wait.until(ExpectedConditions.visibilityOf(element));
			} else {
				wait.until(ExpectedConditions.invisibilityOf(element));
			}
		} catch (Exception e) {
		}
		return true;
	}

	public void SwitchToFrameByElement(QAFWebElement element) {
		driver.switchTo().frame(element);
	}

	public void SwitchToFrameByIndex(int num) {
		driver.switchTo().frame(num);
	}

	public void switchToActionFrame() {
		refreshPage();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@scrolling=\"auto\"])[last()]")));
	}

	public void listofiframe() {
		List<WebElement> iframes = driver.findElements(By.xpath("//iframe"));
		for (WebElement iframe : iframes) {
			driver.switchTo().frame(iframe);
			System.out.println(iframe.getAttribute("title"));
		}
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void refreshSwitchToFrame() {
		try {
			refreshPage();
			waitForPageToLoad();
			driver.switchTo().defaultContent();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions
					.frameToBeAvailableAndSwitchToIt(By.xpath("(//iframe[@title='accessibility title'])[last()]")));
		} catch (Exception e2) {
		}
	}

	public void AttachFrame() {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@title='accessibility title'])[last()]")));
		} catch (Exception e2) {
		}
	}

	// To change the stage status
	public String ChangeStatus(QAFWebElement locator1, QAFWebElement locator2) {
		clickUsingJs(locator1);
		clickUsingJs(locator2);
		waitForGenericToastMessage();
		String notify = "Notification title";
		try {
			notify = driver.findElement(By.xpath("//*[@data-aura-class='forceToastMessage']")).getText();
			driver.findElement(By.xpath("//*[@data-aura-class='forceToastMessage']")).waitForVisible(10000);
		} catch (Exception e) {
		}
		System.out.println();
		return notify;
	}

	public String ChangeStatus(String status) {
		clickUsingJs(By.xpath("(//a[@data-tab-name='" + status + "'])[last()]"));
		clickUsingJs(By.xpath("//span[text()='Mark as Current Status']"));
		scrollUp();
		return waitForGenericToastMessage();
	}

	public String ChangeStatusSMAX(String status) {
		clickUsingJs(By.xpath("(//a[@data-tab-name='" + status + "'])[last()]"));
		clickUsingJs(By.xpath("//span[text()='Mark as Current Status']"));
		this.waitFor(5);
		clickUsingJs(By.xpath("//button[.='Done']"));
		return waitForGenericToastMessage();
	}

	public String ChangeStatusSMAX(String status, String SMAXStatus) {
		clickUsingJs(By.xpath("(//a[@data-tab-name='" + status + "'])[last()]"));
		clickUsingJs(By.xpath("//span[text()='Mark as Current Status']"));
		this.waitFor(5);
		clickUsingJs(By.xpath("(//label[.='SMAX Status']/following::button)[1]"));
		this.waitFor(3);
		clickUsingJs(By.xpath("//lightning-base-combobox-item[@data-value='" + SMAXStatus + "']"));
		clickUsingJs(By.xpath("//button[.='Done']"));
		return waitForGenericToastMessage();
	}

	public String waitForGenericToastMessage() {
		waitFor(By.xpath("//*[@data-aura-class='forceToastMessage']"), 60, true);
		String notify = "Notification title";
		try {
			driver.findElement(By.xpath("//*[@data-aura-class='forceToastMessage']")).waitForVisible(10000);
			notify = driver.findElement(By.xpath("//*[@data-aura-class='forceToastMessage']")).getText();
		} catch (Exception e) {
		}
		return notify;
	}

	// To navigate to any URL
	public void goToURL(String string) {
		driver.get(string);
	}

	// To navigate to Home page
	public void goToHomePage() {
		URL uri = null;
		try {
			uri = new URL(driver.getCurrentUrl());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		goToURL("https://" + uri.getHost() + "/lightning/page/home");
	}

	// To click on any record in the table
	public void clickOnRecord(String name) {
		System.out.println(name);
		String nameLink = "//a[text()=" + "'" + name + "'" + "]";
		QAFWebElement searchName = new QAFExtendedWebElement(nameLink);
		searchName.waitForPresent(30000);
		searchName.click();
	}

	public void searchRecord(String name) {
		QAFWebElement searchName = new QAFExtendedWebElement("//*[contains(@name,'search-input')]");
		clickUsingJs(searchName);
		typeDataTo(searchName, name);
		enterKey(searchName);
	}

	public void enterKey(QAFWebElement e) {
		actions = new Actions(driver);
		actions.moveToElement(e).sendKeys(Keys.ENTER).build().perform();
	}

	public void enterKey() {
		actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).build().perform();
	}

	public void filterRecords(String dropdownValue) {
		QAFWebElement filterIcon = new QAFExtendedWebElement("//*[text()='Select a List View']/parent::*");
		clickUsingJs(filterIcon);
		clickDropdownValue(dropdownValue);
	}

	public void typeDataTo(QAFWebElement webelement, String data) {
		char[] dateChars = data.toCharArray();
		for (int i = 0; i < data.length(); i++) {
			webelement.sendKeys(Character.toString(dateChars[i]));
		}
	}



	public void scrollTillVisible(By locator) {
		int height = Integer
				.parseInt(executor.executeScript("return document.documentElement.scrollHeight").toString());
		int checkScroll = 100;
		while (checkScroll <= height) {
			try {
				waitFor(locator, 10, true);
				executor.executeScript("window.scrollBy(" + 100 + ", " + checkScroll + ");");
				driver.findElement(locator).getText();
				break;
			} catch (Exception e2) {
			}
			checkScroll += 100;
		}
	}

	public void scrollTillVisible(WebElement element) {
		int height = Integer
				.parseInt(executor.executeScript("return document.documentElement.scrollHeight").toString());
		int checkScroll = 100;
		while (checkScroll <= height) {
			try {
				waitFor(element, 1, true);
				executor.executeScript("window.scrollBy(" + 100 + ", " + checkScroll + ");");
				element.getText();
				break;
			} catch (Exception e2) {
			}
			checkScroll += 100;
		}
	}


	private List getAllFrames() {
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// List iFrmLst =
		// driver.findElements(By.xpath("//iframe[contains(@name,'vfFrameId')]"));
		List iFrmLst = driver.findElements(By.xpath("//iframe"));
		List totalFrms = new ArrayList();
		totalFrms.addAll(iFrmLst);
		return totalFrms;
	}


	public void selectBy(QAFExtendedWebElement element, String visibleText) {
		Select select = new Select(element);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		select.selectByVisibleText(visibleText);
	}

	public void scrollIntoElement(WebElement element) {
		JavascriptExecutor js = (driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollIntoElement(By locator) {
		waitFor(locator, 5, true);
		JavascriptExecutor js = (driver);
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
	}

	public void moveToElement(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			actions = new Actions(driver);
			actions.moveToElement(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isElementDisplayed(By locator) {
		Boolean isDisplayed = false;
		try {
			isDisplayed = driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
		}
		return isDisplayed;
	}

	public String getBackgroudColor(By locator) {
		String color = driver.findElement(locator).getCssValue("background");
		System.out.println(color);
		String hex = Color.fromString(color).asHex();
		System.out.println(hex);
		return color;
	}


	public void waitFor(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickUsingActions(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			actions = new Actions(driver);
			scrollIntoElement(locator);
			actions.moveToElement(element).click().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rightClick(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			actions = new Actions(driver);
			actions.moveToElement(element).contextClick().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getEnvironment() {
		String resources = pageProps.getString("environment");
		return resources;
	}

	public void scrollUp() {
		JavascriptExecutor js = driver;
		js.executeScript("window.scrollBy(0,-2000)", "");
	}

	public void scrollDown() {
		JavascriptExecutor js = driver;
		js.executeScript("window.scrollBy(0,2000)", "");
	}

	public void switchWindow() {
		try {
			String parentWindow = driver.getWindowHandle();
			// System.out.println("Parent Window: "+parentWindow);
			Set<String> allWindows = driver.getWindowHandles();
			for (String child_window : allWindows) {
				if (!parentWindow.equalsIgnoreCase(child_window)) {
					driver.switchTo().window(child_window);
					// System.out.println("Child Window: "+child_window);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*

	// created by waseem: to fill the random value at runtime
	public void type(String fieldName, String data) {
		try {
			QAFExtendedWebElement element = new QAFExtendedWebElement(
					"xpath=//input[@id=//label[span[normalize-space(text())='" + fieldName
							+ "']]/@for]|//input[@id=//label[normalize-space(text())='" + fieldName
							+ "']/@for]|//a[@aria-describedby=//span[normalize-space(text())='" + fieldName
							+ "']/@id]");
			map = (Map<?, ?>) ConfigurationManager.getBundle().getObject("testdata");
			if (map.get(fieldName) != null && map.get(fieldName).toString().equalsIgnoreCase("Random")) {
				executor.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
				enterText(element, data);
//					Reporter.logWithScreenShot("Entered:-" + map.get(fieldName).toString() + "", MessageTypes.Info);
				Reporter.logWithScreenShot("Entered " + data + " in to " + fieldName + "  Field", MessageTypes.Info);
			}
		} catch (Exception e) {
			System.out.println("Typing data to :" + fieldName + " is failed");
		}
	}

	// created by waseem: to select the random value at runtime
	public void select(String fieldName, String data) {
		map = (Map<?, ?>) ConfigurationManager.getBundle().getObject("testdata");
		String dropdownvalue = map.get(fieldName).toString();
		if (dropdownvalue.equalsIgnoreCase("Random")) {
			click(fieldName);
			QAFExtendedWebElement DropDownelement = new QAFExtendedWebElement(
					"xpath=//lightning-base-combobox-item[@data-value='" + data + "']|//a[@title='" + data + "']");
//						DropDownelement.click();
			clickUsingJs(DropDownelement);
//						Reporter.logWithScreenShot("Selected:-" + dropdownvalue + "", MessageTypes.Info);
			Reporter.logWithScreenShot("Selected " + data + " from " + fieldName + "  drop-down", MessageTypes.Info);
		}
	}

	// created by waseem: to type the random value at runtime
	public void typeIntoTextArea(String fieldName, String data) {
		try {
			QAFExtendedWebElement element = new QAFExtendedWebElement(
					"xpath=//input[@id=//label[span[normalize-space(text())='" + fieldName
							+ "']]/@for]|//input[@id=//label[normalize-space(text())='" + fieldName
							+ "']/@for]|//textarea[@id=//label[normalize-space(text())='" + fieldName
							+ "']/@for]|//textarea[@id=//label[span[normalize-space(text())='" + fieldName
							+ "']]/@for]");
			Map<?, ?> map = (Map<?, ?>) ConfigurationManager.getBundle().getObject("testdata");
			if (map.get(fieldName) != null && map.get(fieldName).toString().equalsIgnoreCase("Random")) {
				enterText(element, data);
//				Reporter.logWithScreenShot("Entered:-" + map.get(fieldName).toString() + "", MessageTypes.Info);
				Reporter.logWithScreenShot("Entered " + data + " in to " + fieldName + "Field", MessageTypes.Info);
			}
		} catch (Exception e) {
		}
	}

	// created by waseem: to click the random value at runtime
	public void click(String fieldName, String data) {
		try {
			map = (Map<?, ?>) ConfigurationManager.getBundle().getObject("testdata");
			String checkBox = map.get(fieldName).toString();
			if (checkBox != null && checkBox.equalsIgnoreCase("Random")) {
				QAFExtendedWebElement element = new QAFExtendedWebElement(
						"xpath=//button[@id=//label[span[normalize-space(text())='" + fieldName
								+ "']]/@for]|//button[@id=//label[normalize-space(text())='" + fieldName
								+ "']/@for]|//a[@aria-describedby=//span[normalize-space(text())='" + fieldName
								+ "']/@id]");
				element.executeScript("scrollIntoView(true);");
//				element.click();
				clickUsingJs(element);
				Reporter.logWithScreenShot("Clicked on " + fieldName + " button", MessageTypes.Info);
			}
		} catch (Exception e) {
			System.out.println("Clicking On :" + fieldName + " is failed");
		}
	}
*/

	public void selectOptionByPartialText(WebElement element, String partialText) {
		Select select = new Select(element);
		select.getOptions().parallelStream()
				.filter(option -> option.getAttribute("textContent").toLowerCase().contains(partialText.toLowerCase()))
				.findFirst().ifPresent(option -> select.selectByVisibleText(option.getAttribute("textContent")));
	}


	/**
	 * implicitlyWait method used to wait until page gets loaded
	 * 
	 * @author waseem
	 * @param seconds: pass the seconds to wait execution
	 * @return nothing
	 * @throws InterruptedException
	 */
	public void implicitlyWait(int seconds) {
		wait = new FluentWait<WebDriver>(new WebDriverTestBase().getDriver())
				.withTimeout(Duration.ofSeconds(pageProps.getInt("explicitwait.timeout")))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		try {
//         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[@id='oneHeader']//div[@data-message-id='loginAsSystemMessage']")));
			wait.until(ExpectedConditions.visibilityOf(new WebDriverTestBase().getDriver()
					.findElement(By.xpath("//header[@id='oneHeader']//div[@data-message-id='loginAsSystemMessage']"))));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@class='slds-no-print oneAppNavContainer']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='fullheight center oneCenterStage mainContentMark']")));
		} catch (Exception e) {
		} finally {
//         new WebDriverTestBase().getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
//         new WebDriverTestBase().getDriver().executeScript("return document.readyState").equals("complete");
			waitFor(seconds);
		}
	}


	// created by waseem
	public static final Pattern DIACRITICS_AND_FRIENDS = Pattern
			.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

	public String stripDiacritics(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = DIACRITICS_AND_FRIENDS.matcher(str).replaceAll("");
		return str;
	}

	public void KeysTAB(QAFWebElement element) {
		element.sendKeys(Keys.TAB);
	}


	public void selectValueFromDropdown(QAFExtendedWebElement element, String data, String selectStrategy) {
		Select select = new Select(element);
		waitFor(2);
		if (selectStrategy.equalsIgnoreCase("Text")) {
			select.selectByVisibleText(data);
		}
		if (selectStrategy.equalsIgnoreCase("Value")) {
			select.selectByValue(data);
		}
		if (selectStrategy.equalsIgnoreCase("index")) {
			select.selectByIndex(Integer.parseInt(data));
		}
	}

	public void selectValueFromDropdown(QAFExtendedWebElement element, Consumer<Select> consumer) {
		consumer.accept(new Select(element));
	}
}