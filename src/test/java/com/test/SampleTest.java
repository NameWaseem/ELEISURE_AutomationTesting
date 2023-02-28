package com.test;

import static com.qmetry.qaf.automation.step.client.RuntimeScenarioFactory.scenario;

import com.common.utilities.MyScreenRecorder;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import org.openqa.selenium.By;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import pages.Login;

public class SampleTest extends WebDriverTestCase {

	@BeforeSuite
	public void startRecord() {
		try {
			MyScreenRecorder.startRecording(context.getSuite().getName());
			//MyScreenRecorder.startRecording(context.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void stopRecord() {
		try {
			MyScreenRecorder.stopRecording();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*@Test
	public void AdobeCommerceLogin() throws InterruptedException {
		get("https://integration2-hohc4oi-jg34lh7kuslkw.us-a1.magentosite.cloud/admin/admin/");
		Thread.sleep(5000);
		QAFExtendedWebElement username = new QAFExtendedWebElement(By.xpath("//input[@id='username']"));
		username.verifyPresent("User name field is displayed");
		username.sendKeys("rwguser");
		Reporter.log("Entered username");
		QAFExtendedWebElement password = new QAFExtendedWebElement(By.xpath("//input[@id='login']"));
		password.verifyPresent("Password field is displayed");
		password.sendKeys("rwguser123");
		Thread.sleep(5000);
		Reporter.log("Entered password");
		QAFExtendedWebElement sginin = new QAFExtendedWebElement(By.xpath("//button[@class='action-login action-primary']"));
		sginin.verifyPresent("Sign in button displayed");
		sginin.click();
		Thread.sleep(10000);
	}*/

	@Test
	public void AdobeCommerceLogin(){
		Login login = new Login();
		scenario().given("URL of Adobe Commerce website", () -> {
			getDriver().navigate().to(ConfigurationManager.getBundle().getString("env.baseurl"));
			Reporter.log("Open entered url");
		}).when("entered username and password", () -> {
			login.loginAdobeCommerce("rwguser","rwguser123");
		}).then("Verify Dashboard page is displayed", () -> {
			QAFExtendedWebElement homePage = new QAFExtendedWebElement(By.xpath("//title[text()='Dashboard / Magento Admin']"));
			homePage.verifyPresent("Dashboard");
		}).execute();
	}
}
