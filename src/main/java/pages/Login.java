package pages;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import org.openqa.selenium.By;

public class Login extends WebDriverBaseTestPage<WebDriverTestPage> {

    QAFExtendedWebElement loginHeader = new QAFExtendedWebElement(By.xpath("//img[@title='Adobe Commerce Admin Panel']"));
    QAFExtendedWebElement UserName = new QAFExtendedWebElement(By.xpath("//input[@id='username']"));
    QAFExtendedWebElement Password = new QAFExtendedWebElement(By.xpath("//input[@type='password']"));
    QAFExtendedWebElement SignIn = new QAFExtendedWebElement(By.xpath("//button['Sign in']"));

    @Override
    protected void openPage(PageLocator pageLocator, Object... objects) {
    }


    /**
     * loginAdobeCommerce method is used to enter given username, password
     * and click on sing in button.
     *
     * @author waseem
     * @param username: need to pass valid username
     * @param password: need to pass valid password
     * @return nothing
     */
    public void loginAdobeCommerce(String username, String password){
        loginHeader.verifyPresent("Adobe Commerce Admin Panel is displayed");
        UserName.sendKeys(username);
        Password.sendKeys(password);
        Reporter.logWithScreenShot("Entered username and password on login page");
        SignIn.click();
    }
}
