package litecartTest.appTests;

import litecartTest.appTests.framework.ShopMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Marisha on 05.03.2017.
 */
public class ClickGoodsChromeTest {
    protected WebDriver driver;
    private WebDriverWait wait;


    @AfterClass(alwaysRun = true)
    public void stop() {
        driver.quit();
        driver = null;
    }

    @BeforeClass(alwaysRun = true)
    public void openMainPageInChrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        ShopMainPage shopMainPage = new ShopMainPage(driver);
        shopMainPage.openMainPage();
    }

    @Test
    public void clickGoodsTestInChrome() {
        //1. выбрать товар в категории Campaigns
        WebElement good = driver.findElement(By.xpath(".//*[@id='box-campaigns']//a[1]"));

        //2. название товара на главной страницы
        String goodNameOnMainPage = good.getAttribute("title");
        System.out.println("goodNameOnMainPage = " + goodNameOnMainPage);
        //3. цены обычная и акционная
        WebElement regularPrice1 = good.findElement(By.xpath(".//s[@class = 'regular-price']"));
        String regularPriceOnMainPage = regularPrice1.getText();
        WebElement salePrice1 = good.findElement(By.xpath(".//strong[@class = 'campaign-price']"));
        String salePriceOnMainPage = salePrice1.getText();
        System.out.println("regularPriceOnMainPage = " + regularPriceOnMainPage + ", salePriceOnMainPage = " + salePriceOnMainPage);
        //4. цвет
        String regularPriceColor1 = regularPrice1.getCssValue("color");
        String salePriceColor1 = salePrice1.getCssValue("color");
        System.out.println("regularPriceColor1 = " + regularPriceColor1 + ", salePriceColor1 = " + salePriceColor1);
        Assert.assertEquals(regularPriceColor1, "rgba(119, 119, 119, 1)");
        Assert.assertEquals(salePriceColor1, "rgba(204, 0, 0, 1)");

        //5. перечеркнутость
        String regularPriceDecoration1 = regularPrice1.getCssValue("text-decoration");                                                  //получение стиля для перечеркнутости
        String salePriceDecoration1 = salePrice1.getCssValue("text-decoration");
        System.out.println("regularPriceDecoration1 = " + regularPriceDecoration1 + ", salePriceDecoration1 = " + salePriceDecoration1);
        Assert.assertTrue(regularPriceDecoration1.contains("line-through"));
       // Assert.assertEquals(regularPriceDecoration1, "line-through");
      //  Assert.assertEquals(salePriceDecoration1, "none");
        Assert.assertTrue(salePriceDecoration1.contains("none"));

        //6. жирность
        String regularPriceStyle1 = regularPrice1.getCssValue("font-weight");                                                           //получения стиля для определения жирности
        String salePriceStyle1 = salePrice1.getCssValue("font-weight");
        System.out.println("regularPriceStyle = " + regularPriceStyle1 + ", salePriceStyle = " + salePriceStyle1);
        Assert.assertEquals(regularPriceStyle1, "normal");
        Assert.assertEquals(salePriceStyle1, "bold");

        //7.высота
        String regularPriceSize1 = regularPrice1.getCssValue("font-size");                                                              //получения стиля для определения жирности
        String salePriceSize1 = salePrice1.getCssValue("font-size");
        System.out.println("regularPriceSize1 = " + regularPriceSize1 + ", salePriceSize1 = " + salePriceSize1);
        int rez = regularPriceSize1.compareTo(salePriceSize1);
        Assert.assertTrue(rez < 0, "Высота не соответствует ожидаемому: salePriceSize1 = " + salePriceSize1 + ", salePriceSize1 = " + salePriceSize1 + "\n");

        //8. переход на страницу товара
        good.click();
        waitElementVisibility(By.xpath(".//h1[@class = 'title']"));
        String goodNameOnPersonalPage = driver.findElement(By.xpath(".//h1[@class = 'title']")).getText();                                  //9. получаем заголовок
        System.out.println("goodNameOnPersonalPage = " + goodNameOnPersonalPage);
        Assert.assertEquals(goodNameOnMainPage, goodNameOnPersonalPage);                                                                    //10. проверка соответствия заголовков на главной странице и странице товара

        WebElement regularPrice2 = driver.findElement(By.xpath(".//s[@class = 'regular-price']"));
        WebElement salePrice2 = driver.findElement(By.xpath(".//strong[@class = 'campaign-price']"));
        //price
        String regularPriceOnPersonalPage = regularPrice2.getText();                                                                        //11. получаем обычную цену
        Assert.assertEquals(regularPriceOnMainPage, regularPriceOnPersonalPage);                                                            //12. проверка соответствия обычной цены на главной странице и странице товара
        String salePriceOnPersonalPage = salePrice2.getText();                                                                              //13. получаем акционную цену
        Assert.assertEquals(salePriceOnMainPage, salePriceOnPersonalPage);                                                                  //14.проверка соответствия акционной цены на главной странице и странице товара
        System.out.println("regularPriceOnPersonalPage = " + regularPriceOnPersonalPage + ", salePriceOnPersonalPage = " + salePriceOnPersonalPage);

        //цвет
        String regularPriceColor2 = regularPrice2.getCssValue("color");
        String salePriceColor2 = salePrice2.getCssValue("color");
        System.out.println("regularPriceColor2 = " + regularPriceColor2 + ", salePriceColor2 = " + salePriceColor2);
        Assert.assertEquals(regularPriceColor2, "rgba(102, 102, 102, 1)");
        Assert.assertEquals(salePriceColor2, "rgba(204, 0, 0, 1)");

        //перечеркнутость
        String regularPriceDecoration2 = regularPrice2.getCssValue("text-decoration");                                                    //получение стиля для перечеркнутости
        String salePriceDecoration2 = salePrice2.getCssValue("text-decoration");
        System.out.println("regularPriceDecoration2 = " + regularPriceDecoration2 + ", salePriceDecoration2 = " + salePriceDecoration2);
        Assert.assertTrue(regularPriceDecoration2.contains("line-through"));
       // Assert.assertEquals(regularPriceDecoration2, "line-through");
        //Assert.assertEquals(salePriceDecoration2, "none");
        Assert.assertTrue(salePriceDecoration2.contains("none"));

        //жирность
        String regularPriceStyle2 = regularPrice2.getCssValue("font-weight");                                                             //получения стиля для определения жирности
        String salePriceStyle2 = salePrice2.getCssValue("font-weight");
        System.out.println("regularPriceStyle2 = " + regularPriceStyle2 + ", salePriceStyle2 = " + salePriceStyle2);
        Assert.assertEquals(regularPriceStyle2, "normal");
        Assert.assertEquals(salePriceStyle2, "bold");

        //высота
        String regularPriceSize2 = regularPrice2.getCssValue("font-size");                                                              //получения стиля для определения жирности
        String salePriceSize2 = salePrice2.getCssValue("font-size");
        System.out.println("regularPriceSize2 = " + regularPriceSize2 + ", salePriceSize2 = " + salePriceSize2);
        int rez2 = regularPriceSize2.compareTo(salePriceSize2);
        Assert.assertTrue(rez2 < 0, "Высота не соответствует ожидаемому: salePriceSize2 = " + salePriceSize2 + ", salePriceSize2 = " + salePriceSize2 + "\n");
    }

    public void waitElementVisibility(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
    }
}
