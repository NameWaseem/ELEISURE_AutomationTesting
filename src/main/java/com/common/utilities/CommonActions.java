package com.common.utilities;

import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class CommonActions extends WebDriverBaseTestPage<WebDriverTestPage> {
	//WebUtilities util = new WebUtilities();
	static JavascriptExecutor executor = null;
	List<String> listId = new ArrayList<>();

	// created by waseem
	public String pickLatestFileFromDownloads(String testname) {
		String currentUsersHomeDir = System.getProperty("user.home");
		String downloadFolder = currentUsersHomeDir + File.separator + "Downloads" + File.separator;
		File dir = new File(downloadFolder);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			Reporter.log("There is no file in the folder");
		}
		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		String k = lastModifiedFile.toString();
		System.out.println(lastModifiedFile);
		Path p = Paths.get(k);
		String file = p.getFileName().toString();
		String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		new File(System.getProperty("user.dir") + "//Downloads//").mkdir();
		boolean newfile = new File(System.getProperty("user.dir") + "//Downloads//" + testname + fileSuffix).mkdir();
		Path sourcepath = Paths.get(k);
		Path destinationepath = Paths
				.get(System.getProperty("user.dir") + "//Downloads//" + testname + fileSuffix + "/" + file);
		try {
			Files.walk(sourcepath)
					.forEach(source -> copy(source, destinationepath.resolve(sourcepath.relativize(source))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	static void copy(Path source, Path dest) {
		try {
			Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
	}


/*
	public void select_Config_Parameter(String attribrute, String attributeData) {
		QAFExtendedWebElement element = driver.findElement(
				By.xpath("(//form//label[contains(.,'" + attribrute + "')]//following::select[1])[last()]"));
		util.scrollIntoElement(element);
		util.selectValuefromDropDown(element, e -> e.selectByVisibleText(attributeData));
	}
*/


/*
	public String GenerateTollFreeNumber(String tollfreeType) {
		String tollNumber = null;
		RandomDataGenerator bean = new RandomDataGenerator();
		bean.fillRandomData();
		switch (tollfreeType) {
		case "Domestic":
			tollNumber = String.format("1800%s", bean.getTollFreeNumber());
			tollNumber = StringUtils.rightPad(tollNumber, 8, "0");
			break;
		case "MyNumber":
			tollNumber = String.format("1700%s", bean.getTollFreeNumber());
			tollNumber = StringUtils.rightPad(tollNumber, 8, "0");
			break;
		case "International":
			tollNumber = String.format("17180621%s", bean.getTollFreeNumber());
			tollNumber = StringUtils.rightPad(tollNumber, 12, "0");
			break;
		case "Universal":
			tollNumber = String.format("17180621%s", bean.getTollFreeNumber());
			tollNumber = StringUtils.rightPad(tollNumber, 12, "0");
			break;
		default:
			System.out.println("Please enter type of toll free number");
			break;
		}
		return tollNumber;
	}
*/

	/**
	 * getOptionsDropdown -- get all the option from the dropdown
	 * 
	 * @author Waseem
	 * @return this method returns all the options of the dropdown as a list of
	 *         WebElement
	 */
	public List<WebElement> getOptionsDropdown(QAFWebElement element) {
		Select select = new Select(element);
		List<WebElement> allOptions = select.getOptions();
		return allOptions;
	}

	/**
	 * getSelectedOptionDropdown -- It
	 * 
	 * @author Waseem
	 * @return this method returns all the options of the dropdown as a list of
	 *         WebElement
	 */
	public String getSelectedOptionDropdown(QAFWebElement element) {
		Select select = new Select(element);
		WebElement ele = select.getFirstSelectedOption();
		return ele.getText();
	}

	/**
	 * mousemover method help to move the mouse
	 * 
	 * @author Waseem
	 */
	public void mouseMover() {
		Robot robot = null;
		try {
			robot = new Robot();
			Random random = new Random();
			int x = random.nextInt() % 640;
			int y = random.nextInt() % 480;
			robot.mouseMove(x, y);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}


	public void clickUsingJavaScript(QAFWebElement webElement) {
		Reporter.log("Click using JavaScript on element " + webElement.toString(), MessageTypes.Info);
		executor.executeScript("arguments[0].scrollIntoViewIfNeeded(true)", webElement);
		executor.executeScript("arguments[0].click();", webElement);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickUsingJavaScript(By locator) {
		clickUsingJavaScript(new WebDriverTestBase().getDriver().findElement(locator));
	}

	public void enterTextUsingJavaScript(QAFWebElement element, String value) {
		element.waitForVisible(300000);
		executor.executeScript("arguments[0].scrollIntoViewIfNeeded(true)", element);
		String args = "arguments[0].value=" + "'" + value + "'";
		System.out.println(args);
		element.clear();
		executor.executeScript(args, element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
