package litecartTest.pageObjectStructure.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Marina on 25.03.2017.
 * Описываются общие методы и все, что относится ко всем страницам
 */
public class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait waitShort;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        waitShort = new WebDriverWait(driver, 5);
    }

    public By logoLocator = By.xpath(".//*[@id='logotype-wrapper']/a");
    public By itemsCartLocator = By.xpath(".//span[@class='quantity']");
    public By priceValueLocator = By.xpath(".//span[@class='formatted_value']");
    private By checkoutLocator = By.xpath(".//*[@class='link']");

    public CartPage goToCartPage() {
        driver.findElement(checkoutLocator).click();
        CartPage cartPage = new CartPage(driver);
        cartPage.waitElementPresence(cartPage.getOrderTableLocator(), 10);
        return cartPage;
    }

    public ShopMainPage goToMainPageByLogo() {
        driver.findElement(logoLocator).click();                                                                           //вернуться на главную страницу
        waitElementPresence(By.xpath(".//*[@class='image-wrapper']"));
        return new ShopMainPage(driver);
    }

    public void waitElementVisibility(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
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

    public void waitElementPresence(By element) {
        waitElementPresence(element, 20);
    }

    public  boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }
}
