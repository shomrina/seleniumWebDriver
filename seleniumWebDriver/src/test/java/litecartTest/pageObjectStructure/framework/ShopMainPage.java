package litecartTest.pageObjectStructure.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

/**
 * Created by Marina on 25.03.2017.
 * Описывается главная страница магазина для обычного пользователя
 */
public class ShopMainPage extends AbstractPage {
    private String mainPageUrl = "http://localhost/litecart/en/";
    private By goodsLocator = By.xpath(".//*[@class='image-wrapper']");


    private By itemsCartLocator = By.xpath(".//span[@class='quantity']");
    private By priceValueLocator = By.xpath(".//span[@class='formatted_value']");

    private By goodLinkLocator = By.xpath(".//*[@id='box-most-popular']//a[@class='link']");                        // //найти все товары на страницы (по ссылкам)


    public ShopMainPage(WebDriver driver) {
        super(driver);
    }


    public String getMainPageUrl() {
        return mainPageUrl;
    }

    public ShopMainPage openMainPage() {
        driver.get(mainPageUrl);
        waitElementVisibility(goodsLocator);
        return this;
    }

    public String getAmountGoods() {
        return driver.findElement(itemsCartLocator).getText();                                                      //возращает кол-во товаров в корзине в текстовом виде
    }

    public String getSummeryPriceGoods() {
        return driver.findElement(priceValueLocator).getText();
    }

    public GoodPage chooseGoodAndOpenGoodPage(int index) {
        List<WebElement> productList = driver.findElements(goodLinkLocator);                                        //найти все товары на страницы (по ссылкам)
        productList.get(index).click();                                                                             //выбрать из полученного массива указанный объект (нулевой элемент)
        GoodPage goodPage = new GoodPage(driver);
        goodPage.waitElementPresence(goodPage.getAddToCartLocator());
        return goodPage;
    }


}
