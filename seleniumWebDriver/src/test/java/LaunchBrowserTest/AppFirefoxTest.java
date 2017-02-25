package LaunchBrowserTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Marina on 25.02.2017.
 * Launch Firefox new version with new scheme run Se (geckodriver, MARIONETTE = true by default)
 * // новая схема более явно:
 DesiredCapabilities caps = new DesiredCapabilities();
 caps.setCapability(FirefoxDriver.MARIONETTE, true);
 WebDriver driver = new FirefoxDriver(caps);
 */
public class AppFirefoxTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before                 // = BeforeMethod
    public void start() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstFirefoxTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
