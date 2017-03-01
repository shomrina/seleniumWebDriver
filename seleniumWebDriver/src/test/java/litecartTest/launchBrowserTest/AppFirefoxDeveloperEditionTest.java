package litecartTest.launchBrowserTest;

import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Marina on 25.02.2017.
 */
public class AppFirefoxDeveloperEditionTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    // = BeforeMethod
    public void start() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, true);                                             //launch browser by new scheme
        driver = new FirefoxDriver(
                new FirefoxBinary(new File("E:\\Program Files (x86)\\Firefox Developer Edition\\firefox.exe")),        //FirefoxBinary - указывает путь к исполняемому файлу бразуера
                new FirefoxProfile(), caps);                                                                   //FirefoxProfile  - отвечает за настройки создаваемого профиля браузера. В данном случае профиль по умолчанию.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);                                       //чтобы селениум дожидался, когда элемент появится на странице
        System.out.println(((HasCapabilities) driver).getCapabilities());                                      //вывод капабилитес в консоль
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstFirefoxDeveloperEditionTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @AfterMethod
    public void stop() {
        driver.quit();
        driver = null;
    }
}
