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
 * Launch browser Mozilla ESR with old-scheme run Se : (FirefoxDriver.MARIONETTE = false) передать в capabilities
 * Firefox ESR is intended for groups who deploy and maintain the desktop environment in large organizations
 * https://www.mozilla.org/en-US/firefox/organizations/all/
 * При установленных нескольких браузерах, чтобы Селениум знал какой запускать, надо явно указать путь к исполняемому файлу в конструкторе веб-драйвера
 * See Selenium WebDriver - полное руководство, "Запуск Firefox" (part 2) 5:21
 */
public class AppFirefoxESRTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    // = BeforeMethod
    public void start() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, false);
        driver = new FirefoxDriver(
                new FirefoxBinary(new File("E:\\Program Files\\Mozilla Firefox ESR\\firefox.exe")),  //FirefoxBinary - указывает путь к исполняемому файлу бразуера
                new FirefoxProfile(), caps);                                                                   //FirefoxProfile  - отвечает за настройки создаваемого профиля браузера. В данном случае профиль по умолчанию.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);                                       //чтобы селениум дожидался, когда элемент появится на странице
        System.out.println(((HasCapabilities) driver).getCapabilities());                                      //вывод капабилитес в консоль
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstFirefoxESRTest() {
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
