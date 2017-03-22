package litecartTest.appTests.adminTest;

import litecartTest.appTests.framework.LoginAdminPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by mashomri on 22.03.2017.
 */
public class EventFiringCatalogTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   //неявное ожидание
        wait = new WebDriverWait(driver, 10);               //для явного ожидания
    }
    @Test
    public void testEventFiringInCatalog() {
            //LOGIN in admin
        LoginAdminPage loginAdminPage = new LoginAdminPage(driver);
        loginAdminPage.fillLoginAdmin();
        loginAdminPage.clickLoginButtonAdmin();
            //go to page Catalog
        driver.findElement(By.xpath("//*[text() = 'Catalog']")).click();
        waitAllElementVisibility(By.xpath(".//*[@class='dataTable']"), 20);



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

}
