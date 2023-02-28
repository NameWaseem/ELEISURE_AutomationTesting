package pages;

import com.common.utilities.RandomDataGenerator;
import com.common.utilities.WebActions;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Customers extends WebActions {

    QAFExtendedWebElement addNewCustomer = new QAFExtendedWebElement(By.xpath("//button[@title='Add New Customer']"));
    QAFExtendedWebElement newCustomerHeader = new QAFExtendedWebElement(By.xpath("//h1[@class='page-title' and text()='New Customer']"));
    QAFExtendedWebElement firstNameInput = new QAFExtendedWebElement(By.xpath("//input[@name='customer[firstname]']"));
    QAFExtendedWebElement middleNameInput = new QAFExtendedWebElement(By.xpath("//input[@name='customer[middlename]']"));
    QAFExtendedWebElement lastNameInput = new QAFExtendedWebElement(By.xpath("//input[@name='customer[lastname]']"));
    QAFExtendedWebElement emailInput = new QAFExtendedWebElement(By.xpath("//input[@name='customer[email]']"));
    QAFExtendedWebElement merchantCodeInput = new QAFExtendedWebElement(By.xpath("//input[@name='customer[merchant_code]']"));
    QAFExtendedWebElement deactivationReasonInput = new QAFExtendedWebElement(By.xpath("//input[@name='customer[inactivated_reason]']"));
    QAFExtendedWebElement companyRegistrationName = new QAFExtendedWebElement(By.xpath("//input[@name='customer[company_reg_number]']"));
    QAFExtendedWebElement expiryTime = new QAFExtendedWebElement(By.xpath("//input[@name='customer[expiry_time]']"));
    QAFExtendedWebElement inactivatedSince = new QAFExtendedWebElement(By.xpath("//input[@name='customer[inactivated_since]']"));
    QAFExtendedWebElement StatusDropdown = new QAFExtendedWebElement(By.xpath("//select[@name='customer[merchant_status]']"));
    QAFExtendedWebElement saveCustomer = new QAFExtendedWebElement(By.xpath("//button[@id='save' and @title='Save Customer']"));

    QAFExtendedWebElement messageSuccess = new QAFExtendedWebElement(By.xpath("//div[text()='You saved the customer.']"));

    QAFExtendedWebElement filters = new QAFExtendedWebElement(By.xpath("//button[text()='Filters']"));
    QAFExtendedWebElement applyFilters = new QAFExtendedWebElement(By.xpath("//span[text()='Apply Filters']//parent::button"));
    QAFExtendedWebElement FiltersIDFrom = new QAFExtendedWebElement(By.xpath("//input[@name='entity_id[from]']"));
    QAFExtendedWebElement FiltersIDTo = new QAFExtendedWebElement(By.xpath("//input[@name='entity_id[to]']"));
    QAFExtendedWebElement FiltersCustomerSinceFrom = new QAFExtendedWebElement(By.xpath("//input[@name='created_at[from]']"));
    QAFExtendedWebElement FiltersCustomerSinceTo = new QAFExtendedWebElement(By.xpath("//input[@name='created_at[to]']"));
    QAFExtendedWebElement FiltersName = new QAFExtendedWebElement(By.xpath("//input[@name='name']"));
    QAFExtendedWebElement FiltersEmail = new QAFExtendedWebElement(By.xpath("//input[@name='email']"));
    QAFExtendedWebElement FiltersStateProvince = new QAFExtendedWebElement(By.xpath("//input[@name='billing_region']"));
    QAFExtendedWebElement FiltersStatus = new QAFExtendedWebElement(By.xpath("//select[@name='merchant_status']"));
    QAFExtendedWebElement FiltersMerchantCode = new QAFExtendedWebElement(By.xpath("//input[@name='merchant_code']"));


    @Override
    protected void openPage(PageLocator pageLocator, Object... objects) {

    }

    /**
     * getAddNewCustomer method is used to return addNewCustomer QAFExtendedWebElement
     *
     * @return addNewCustomer QAFExtendedWebElement
     * @author waseem
     */
    public QAFExtendedWebElement getAddNewCustomer() {
        return addNewCustomer;
    }

    /**
     * clickOnAddNewCustomer method is used to click on Add new customer button from customer page
     *
     * @return nothing
     * @author waseem
     */
    public void clickOnAddNewCustomer() {
        addNewCustomer.click();
        newCustomerHeader.waitForPresent();
        Reporter.log("Clicked on Add New Customer button from Customer page");
    }

    /**
     * AddNewCustomerInfo method is used to enter random data on firstname, middle, lastname,
     * merchantCode and companyRegistrationName
     *
     * @param randomData: need to pass object of RandomDataGenerator class which is coming form
     *                    utilities package
     * @return nothing
     * @author waseem
     */
    public void AddNewCustomerInfo(RandomDataGenerator randomData) {
        randomData.fillRandomData();

        firstNameInput.verifyPresent("First Name");
        String firstName = randomData.getFirstName();
        firstNameInput.sendKeys(firstName);
        Reporter.log("New First Name entered");

        middleNameInput.verifyPresent("Middle Name/Initial");
        middleNameInput.sendKeys(randomData.getMiddleName());
        Reporter.log("New Middle entered");

        lastNameInput.verifyPresent("Last Name");
        String lastName = randomData.getLastName();
        lastNameInput.sendKeys(lastName);
        Reporter.log("New Last Name entered");

        emailInput.verifyPresent("Email");
        emailInput.sendKeys(lastName + "@gmail.com");
        Reporter.log("New email entered");

        merchantCodeInput.verifyPresent("Merchant Code");
        String merchantCode=Long.toString(randomData.getNumber()) + lastName;
        ConfigurationManager.getBundle().setProperty("Merchant_Code",merchantCode);
        merchantCodeInput.sendKeys(merchantCode);
        Reporter.log("new Merchant Code entered");

        /*deactivationReasonInput.verifyPresent("Deactivation Reason");
        deactivationReasonInput.sendKeys("");
        Reporter.log("new Deactivation Reason entered");*/

        companyRegistrationName.verifyPresent("Company Registration Name");
        companyRegistrationName.sendKeys(firstName + lastName + "company");
        Reporter.log("new company Registration Name entered");

        /*expiryTime.verifyPresent("Expiry Time");
        expiryTime.sendKeys("");
        Reporter.log("given Expiry Time");*/

        StatusDropdown.verifyPresent("Status");
        selectValueFromDropdown(StatusDropdown, value -> value.selectByVisibleText("Disable"));
        Reporter.log("Selected Status");
    }

    /**
     * saveNewCustomerInfo method is used to click on save customer
     *
     * @return nothing
     * @author waseem
     */
    public void saveNewCustomerInfo() {
        saveCustomer.verifyPresent("Save Customer");
        saveCustomer.click();
    }

    /**
     * getMessageSuccess method is used to return the Success message after adding new customer QAFExtendedWebElement
     *
     * @return QAFExtendedWebElement
     * @author waseem
     */
    public QAFExtendedWebElement getMessageSuccess() {
        return messageSuccess;
    }

    public void FilterCustomerByMerchantCode(String merchantcode){
        filters.click();
        FiltersMerchantCode.waitForPresent();
        FiltersMerchantCode.sendKeys(merchantcode);
        applyFilters.click();
        Reporter.log("Enter merchant code and apply filter");
        waitFor(15);
        List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr[@class='data-row']"));
        for(WebElement row : rows)
        {
            String code = null;
            List<WebElement> columns = row.findElements(By.tagName("td"));
            for(WebElement column : columns)
            {
                String columnText = column.getText();
                if(columnText.equalsIgnoreCase(merchantcode)){
                    code = columnText;
                }
            }
            Validator.verifyTrue(code.equalsIgnoreCase(merchantcode),"Merchant Code is not matched","Merchant Code is matched");
        }
    }
}
