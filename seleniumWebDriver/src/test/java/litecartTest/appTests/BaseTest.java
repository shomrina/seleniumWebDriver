package litecartTest.appTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Marina on 01.03.2017.
 */
public class BaseTest {

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

    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void waitElementPresence(By element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

//    public boolean isElementsPresent(WebDriver driver, By locator) {
//            return driver.findElements(locator).size() > 0;
//    }


    public  boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void waitInSeconds(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException ex) {
            ex.getMessage();
        }
    }

    public String getRandomIntNumber(int low, int high) {
        //генерация целого случайного числа в указанном промежутке between low (inclusive - включительно) and high (exclusive - исключая)
        final Random random = new Random();
        int r = random.nextInt(high - low) + low; // возвращает следующее случайное значение
        return Integer.toString(r); // преобразование полученного числа в строку
    }

}
