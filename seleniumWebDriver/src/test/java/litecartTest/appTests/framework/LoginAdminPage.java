package litecartTest.appTests.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Marina on 01.03.2017.
 */
public class LoginAdminPage extends AbstractPage {

  //  protected WebDriver driver;

    private String loginAdminPageURL = "http://localhost/litecart/admin/";
    private By username = By.name("username");
    private By password = By.name("password");
    private By loginButton = By.name("login");

    public LoginAdminPage(WebDriver driver) {
        super(driver);
    }

    public String getLoginAdminPageURL() {
        return loginAdminPageURL;
    }

    public void clickLoginButtonAdmin() {
        driver.findElement(loginButton).click();
        waitElementVisibility(By.xpath("/*//*[text() = 'Appearence']"));
    }

    public void fillLoginAdmin(String username, String password) {
        driver.get(loginAdminPageURL);
        driver.findElement(this.username).sendKeys(username);
        driver.findElement(this.password).sendKeys(password);
    }

    public void fillLoginAdmin() {
        fillLoginAdmin("admin", "admin");

    }
}
