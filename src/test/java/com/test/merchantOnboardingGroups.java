package com.test;

import com.common.utilities.MyScreenRecorder;
import com.common.utilities.RandomDataGenerator;
import com.common.utilities.WebActions;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.testng.dataprovider.QAFDataProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import org.openqa.selenium.By;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.Customers;
import pages.Home;
import pages.Login;

import static com.qmetry.qaf.automation.step.client.RuntimeScenarioFactory.scenario;

public class merchantOnboardingGroups extends WebDriverTestCase {

//    @BeforeSuite
    public void startRecord() {
        try {
            MyScreenRecorder.startRecording(context.getSuite().getName());
            //MyScreenRecorder.startRecording(context.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @AfterSuite
    public void stopRecord() {
        try {
            MyScreenRecorder.stopRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @QAFDataProvider(dataFile = "resources/testdata/merchantTestdata.xlsx",sheetName = "Create",key = "${key.name}")
    @Test(description = "As admin user login in to Adobe Commerce system", priority = 0)
    public void AdobeCommerceLogin(){
        Login login = new Login();
        scenario().given("URL of Adobe Commerce website", () -> {
            getDriver().navigate().to(ConfigurationManager.getBundle().getString("env.baseurl"));
            Reporter.log("Open entered url");
        }).when("entered username and password", () -> {
            login.loginAdobeCommerce("rwguser","rwguser123");
        }).then("Verify Dashboard page is displayed", () -> {
            QAFExtendedWebElement homePageHeading = new QAFExtendedWebElement(By.xpath("//title[text()='Dashboard / Magento Admin']"));
            homePageHeading.waitForPresent();
            homePageHeading.verifyPresent("Dashboard");
        }).execute();
    }

    @Test(description = "As user clicked ", priority = 1, dependsOnMethods = {"AdobeCommerceLogin"})
    public void OpenCustomersPage(){
        Home home = new Home();
        WebActions webActions = new WebActions();
        scenario().given("On homepage, to Click on System",()->{
            home.setNavLink("System");
            home.getNavLink().verifyPresent("System");
        }).when("I click on System link",()->{
            home.homePageNavigator("System");
            home.clickOnSystemAllUsers();
        }).then("",()->{
            webActions.waitFor(15);
            QAFExtendedWebElement usersPageHeading = new QAFExtendedWebElement(By.xpath("//title[text()='Users / Permissions / System / Magento Admin']"));
            usersPageHeading.waitForPresent();
            usersPageHeading.verifyPresent("Users page");
        }).execute();
    }

   /* @Test(description = "As user creating new customer", priority = 2, dependsOnMethods = {"OpenCustomersPage"})
    public void CreateNewUser(){
        Customers customer = new Customers();
        WebActions webActions = new WebActions();
        RandomDataGenerator randomData = new RandomDataGenerator();
        scenario().given("On Customer page, to create new customer",()->{
            customer.getAddNewCustomer().verifyPresent("Add New Customer button");
        }).when("Creating new merchant profile",()->{
            customer.clickOnAddNewCustomer();
            webActions.waitFor(10);
            customer.AddNewCustomerInfo(randomData);
            customer.saveNewCustomerInfo();
        }).then("Verify success message and filter new customer",()->{
            webActions.waitFor(20);
            customer.getMessageSuccess().waitForPresent();
            customer.getMessageSuccess().verifyPresent("You saved the customer");
            customer.FilterCustomerByMerchantCode(ConfigurationManager.getBundle().getPropertyValue("Merchant_Code"));
        }).execute();
    }*/

    @Test(description = "As user creating new customer", priority = 2, dependsOnMethods = {"OpenCustomersPage"})
    public void CreateNewUser(){
        Customers customer = new Customers();
        WebActions webActions = new WebActions();
        RandomDataGenerator randomData = new RandomDataGenerator();
        scenario().given("On Customer page, to create new User",()->{

        }).when("",()->{

        }).then("",()->{

        }).execute();
    }
}
