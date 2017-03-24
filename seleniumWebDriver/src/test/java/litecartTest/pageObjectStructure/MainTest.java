package litecartTest.pageObjectStructure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

/**
 * Created by mashomri on 24.03.2017.
 */
public class MainTest {
    protected WebDriver driver;
    protected WebDriverWait wait;


    @BeforeClass(alwaysRun = true)
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   //неявное ожидание
        wait = new WebDriverWait(driver, 10);               //для явного ожидания
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

    public void waitElementVisibility(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementVisibilityLocated(By element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }



    public void waitElementPresence(By element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public  boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }


}
