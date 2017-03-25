package litecartTest.appTests.adminTest;

import litecartTest.appTests.framework.LoginAdminPage1;
import litecartTest.appTests.framework.MyAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static org.hamcrest.core.Is.is;

/**
 * Created by mashomri on 23.03.2017.
 */
public class LogsBrowserCatalogTest extends MyAssertions {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void start() {
                                                                                                                                        //включение более высокого уровня логирования в браузере
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new ChromeDriver(cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);                                                            //неявное ожидание
        wait = new WebDriverWait(driver, 10);                                                                           //для явного ожидания
    }
    @Test
    public void testBrowserLogCatalog() {
        //LOGIN in admin
        LoginAdminPage1 loginAdminPage = new LoginAdminPage1(driver);
        loginAdminPage.fillLoginAdmin();
        loginAdminPage.clickLoginButtonAdmin();
        //go to page Catalog
        driver.findElement(By.xpath("//*[text() = 'Catalog']")).click();
        waitAllElementVisibility(By.xpath(".//*[@class='dataTable']"), 20);
        //
        driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'Rubber Ducks')]")).click();                       //click by Rubber Ducks
        waitElementVisibility(driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'Subcategory')]")), 5);  //wait other folder
        driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'Subcategory')]")).click();                    //click by Subcategory
        waitAllElementVisibility(By.xpath(".//*[@class='dataTable']//td//a[contains(@href,'product_id')]"), 20);   //wait

        List<WebElement> goodsList = driver.findElements(By.xpath(".//*[@class='dataTable']//td[3]//a[contains(@href,'product_id')]"));  //выбрать в таблице все объекты, которые продукты
        System.out.println("goodList size = " + goodsList.size());
        for (int i = 0; i < goodsList.size(); i++) {
            if (i != 0) {
                driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'Rubber Ducks')]")).click();                       //click by Rubber Ducks
                waitElementVisibility(driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'Subcategory')]")), 5);  //wait other folder
                driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'Subcategory')]")).click();                    //click by Subcategory
                waitAllElementVisibility(By.xpath(".//*[@class='dataTable']//td//a[contains(@href,'product_id')]"), 20);   //wait
            }

            WebElement good = driver.findElements(By.xpath(".//*[@class='dataTable']//td[3]//a[contains(@href,'product_id')]")).get(i);

            System.out.println("i = [" + i + "]: href = "
                    + good.getAttribute("href")
                    + ", good name = " + good.getText());

            good.click();  //открыть страницу товара
            waitAllElementVisibility(By.xpath(".//*[@name='name[en]']"), 20);                               //ожидание

            //todo здесь вставить проверку не появляются ли в логе браузера сообщения (любого уровня)
         /*   for (LogEntry l : driver.manage().logs().get("browser")) {
                System.out.println(l);
            }*/
            List<LogEntry> loggs = driver.manage().logs().get("browser").getAll();
            System.out.println("loggs size = " + loggs.size());
            for (LogEntry l : loggs) {
                System.out.println(l);

            }
            assertThat(loggs.size(), is(0));

            driver.findElement(By.xpath(".//*[@name='cancel']")).click();
            waitAllElementVisibility(By.xpath(".//*[@class='dataTable']"), 20);
        }
        assertAll();




    }


    @AfterClass(alwaysRun = true)
    public void stop() {
        driver.quit();
        driver = null;
    }


    public void waitAllElementVisibility(By element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
    }

    private void waitElementVisibility(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
