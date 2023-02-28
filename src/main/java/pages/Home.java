package pages;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import org.openqa.selenium.By;

public class Home extends WebDriverBaseTestPage<WebDriverTestPage> {

    QAFExtendedWebElement navLink;
    QAFExtendedWebElement submenuCustomers = new QAFExtendedWebElement(By.xpath("//strong[@class='submenu-title' and text()='Customers']"));
    QAFExtendedWebElement submenuCloseButton = new QAFExtendedWebElement(By.xpath("//strong[@class='submenu-title' and text()='Customers']//following::a[@data-role='close-submenu']"));
    QAFExtendedWebElement submenuCustomersAllMerchant = new QAFExtendedWebElement(By.xpath("//strong[@class='submenu-title' and text()='Customers']//following::a[.='All Customers']"));
    QAFExtendedWebElement submenuCustomersMerchantApproval = new QAFExtendedWebElement(By.xpath("//strong[@class='submenu-title' and text()='Customers']//following::a[.='Merchant Approval']"));
    QAFExtendedWebElement submenuCustomersPaymentConfig = new QAFExtendedWebElement(By.xpath("//strong[@class='submenu-title' and text()='Customers']//following::a[.='Payment Configuration']"));


    QAFExtendedWebElement submenuSystem = new QAFExtendedWebElement(By.xpath("//strong[@class='submenu-title' and text()='System']"));
    QAFExtendedWebElement systemAllUser = new QAFExtendedWebElement(By.xpath("//span[text()='All Users']//parent::a"));


    @Override
    protected void openPage(PageLocator pageLocator, Object... objects) {
    }

    public void setNavLink(String menu_item) {
        this.navLink = new QAFExtendedWebElement(By.xpath("//ul[@id='nav']//span[text()='"+menu_item+"']//parent::a"));
    }

    public QAFExtendedWebElement getNavLink() {
        return navLink;
    }

    /**
     * homePageNavigator method is used to click on home page Navigation
     *
     * @author waseem
     * @param menu_item: need to pass navigation (like Dashboard, Catalog, Pricing, Customer and System)
     * @return nothing
     */
    public void homePageNavigator(String menu_item){
        navLink = new QAFExtendedWebElement(By.xpath("//ul[@id='nav']//span[text()='"+menu_item+"']//parent::a"));
        navLink.click();
        Reporter.log("Clicked on "+menu_item);
    }

    /**
     * clickOnAllCustomers method is used to verify Customers sub-menu and click on
     * submenu Customers All Merchant link
     *
     * @author waseem
     * @return nothing
     */
    public void clickOnAllCustomers(){
        submenuCustomers.verifyPresent("Customers sub-menu");
        submenuCustomersAllMerchant.verifyPresent("All Merchant link");
        submenuCustomersAllMerchant.click();
        Reporter.log("Clicked on All Merchant link from Customers sub-menu");
    }

    /**
     * clickOnAllCustomers method is used to verify Customers sub-menu and click on
     * submenu Customers All Merchant link
     *
     * @author waseem
     * @return nothing
     */
    public void clickOnSystemAllUsers(){
        submenuSystem.verifyPresent("System sub-menu");
        systemAllUser.verifyPresent("All Users link");
        systemAllUser.click();
        Reporter.log("Clicked on All Users link from System sub-menu");
    }
}
