package litecartTest.appTests;

import litecartTest.appTests.framework.ShopMainPage1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Marina on 02.03.2017.
 */
public class DuckTest extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void openMainPage() {
        ShopMainPage1 shopMainPage = new ShopMainPage1(driver);
        shopMainPage.openMainPage();
    }

    @Test
    public void duckStickerTest() {
        List<WebElement> ducksList = driver.findElements(By.className("image-wrapper"));  //get all ducks
        System.out.println("all ducks = " + ducksList.size());

        for(WebElement duck : ducksList) {
            List<WebElement> stickersList = duck.findElements(By.xpath(".//div[contains(@class, 'sticker')]"));  //искать относительно полученного элемента стикеры
            Assert.assertEquals(stickersList.size(), 1, "Количество стикеров не соответствует ожидаемому результату \n");

        }

    }
}
