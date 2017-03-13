package litecartTest.appTests;

import litecartTest.appTests.framework.ShopMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Marina on 13.03.2017.
 */
public class CartTest extends BaseTest {
    private By addToCart = By.xpath(".//*[@name='add_cart_product']");
    private By itemsCart = By.xpath(".//span[@class='quantity']");                              //.//*[@id='cart']/a[2]/span[@class='quantity']"
    private By priceValue = By.xpath(".//span[@class='formatted_value']");                      //.//*[@id='cart']/a[2]/span[@class='formatted_value']
    private By logo = By.xpath(".//*[@id='logotype-wrapper']/a");
    private By checkout = By.xpath(".//*[@class='link']");
    private By removeProduct = By.xpath(".//*[@name='remove_cart_item']");
    private By back = By.xpath(".//*[@id='checkout-cart-wrapper']/p[2]/a");
    private By orderTable = By.xpath(".//*[@id='order_confirmation-wrapper']/table");
    private By size = By.xpath(".//*[@name='options[Size]']");

    private Select sizeSelect;
    private void setSizeSelect() {
        sizeSelect = new Select(driver.findElement(size));
    }


    @BeforeClass
    public void openMainPage() {
        ShopMainPage shopMainPage = new ShopMainPage(driver);
        shopMainPage.openMainPage();
    }

    @Test
    public void testAddProductToCart() {
        //получить значение товаров и общий суммы в корзине
        String items = driver.findElement(itemsCart).getText();
        String summeryPrice = driver.findElement(priceValue).getText();
        System.out.println("items = " + items + "\n" + "summeryPrice = " + summeryPrice);

        List<WebElement> productList;

        for (int i = 0; i < 3; i++) {
            productList = driver.findElements(By.xpath(".//*[@id='box-most-popular']//a[@class='link']"));
            System.out.println("i = " + i);
            productList.get(1).click();
            waitAllElementVisibility(By.xpath(".//h1[@class = 'title']"), 20);
            WebDriverWait wait2 = new WebDriverWait(driver, 5);
            try {
      //          wait2.until(ExpectedConditions.presenceOfElementLocated(size));
                setSizeSelect();
                sizeSelect.selectByVisibleText("Small");
            } catch (NoSuchElementException ex) {

            }

            driver.findElement(addToCart).click();


            wait2.until(ExpectedConditions.textToBePresentInElementLocated(itemsCart,"" + (i + 1)));
            items = driver.findElement(itemsCart).getText();
            summeryPrice = driver.findElement(priceValue).getText();
            System.out.println("items [" + i + "] = " + items + "\n" + "summeryPrice = " + summeryPrice + "\n");
            driver.findElement(logo).click();
            waitAllElementVisibility(By.xpath(".//*[@class='image-wrapper']"), 20);
        }
        driver.findElement(checkout).click();
        waitElementPresence(orderTable, 10);

        List<WebElement> trTable;
        for (int i = 0; i < 3; i++) {
            if(areElementsPresent(driver, back)) {
                System.out.println("condition IF = " + i);
                break;
            }
            trTable = driver.findElement(orderTable).findElements(By.xpath(".//tr"));
            int trTableSize = trTable.size();

            System.out.println("i delete = " + i);
            waitElementVisibilityLocated(removeProduct, 30);
            driver.findElement(removeProduct).click();
            System.out.println("delete [" + i + "] " );

            WebDriverWait wait3 = new WebDriverWait(driver, 5);
            wait3.until(ExpectedConditions.textToBePresentInElement(trTable.get(trTableSize - 1), "Payment Due:"));
        }





    }
}
