package litecartTest.pageObjectStructure.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Marina on 25.03.2017.
 */
public class GoodPage extends AbstractPage {

    public GoodPage(WebDriver driver) {
        super(driver);
    }
        //locators
    private By sizeLocator = By.xpath(".//*[@name='options[Size]']");
    private Select sizeSelect;
    private void setSizeSelect() {
        sizeSelect = new Select(driver.findElement(sizeLocator));
    }
    private By addToCartLocator = By.xpath(".//*[@name='add_cart_product']");
    private By itemsCartLocator = By.xpath(".//span[@class='quantity']");
    private By priceValueLocator = By.xpath(".//span[@class='formatted_value']");

    //GETTERS
    public By getAddToCartLocator() {
        return addToCartLocator;
    }

    //othet methods
    public void setValueInSizeSelect(String sizeValue) {
        try {
            setSizeSelect();
            sizeSelect.selectByVisibleText(sizeValue);                                                    //если найден, установить значение
        } catch (NoSuchElementException ex) {}                                                                //если не найден  - ничего не делать
    }

    public void addGoodToCart(String varifyText) {
        driver.findElement(addToCartLocator).click();                                                                 //клик по кнопке Добавить в корзину
        waitShort.until(ExpectedConditions.textToBePresentInElementLocated(itemsCartLocator,varifyText));        //подождать пока текст в элементе станет указанным
    }


}
