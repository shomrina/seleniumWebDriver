package litecartTest.pageObjectStructure.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Marina on 25.03.2017.
 */
public class CartPage extends AbstractPage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By removeProductButtonLocator = By.xpath(".//*[@name='remove_cart_item']");
    private By backLocator = By.xpath(".//*[@id='checkoutLocator-cart-wrapper']/p[2]/a");
    private By orderTableLocator = By.xpath(".//*[@id='order_confirmation-wrapper']/table");

    public By getOrderTableLocator() {
        return orderTableLocator;
    }

    public void deleteGoods(int amountGoodsForDelete) {
        List<WebElement> trTable;
        for (int i = 0; i < amountGoodsForDelete; i++) {
            if(areElementsPresent(driver, backLocator)) {                                                                      //првоерка, на случай если товаров меньше, чем 3
                System.out.println("condition IF = " + i);
                break;                                                                                                  //выйти из цикла если виден определенный элемент
            }
            //получение размерности таблицы для проверки
             trTable = driver.findElement(orderTableLocator).findElements(By.xpath(".//tr"));
            int trTableSize = trTable.size();

            System.out.println("i delete = " + i);
            waitElementVisibilityLocated(removeProductButtonLocator, 30);                                            //проверка. что удаляемый объект доступен
            driver.findElement(removeProductButtonLocator).click();                                                                  //удаление продукта
            System.out.println("delete [" + i + "] " );

            WebDriverWait wait3 = new WebDriverWait(driver, 5);
            wait3.until(ExpectedConditions.textToBePresentInElement(trTable.get(trTableSize - 1), "Payment Due:"));     //дождаться обновления таблицы
        }

    }
}
