package litecartTest.appTests.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Marina on 02.03.2017.
 */
public class ShopMainPage extends AbstractPage {
    private String mainPageUrl = "http://localhost/litecart/en/";
    private By goodsLocator = By.xpath(".//*[@class='image-wrapper']");



    public ShopMainPage(WebDriver driver) {
        super(driver);
    }


    public String getMainPageUrl() {
        return mainPageUrl;
    }

    public void openMainPage() {
        driver.get(mainPageUrl);
        waitElementVisibility(goodsLocator);
    }
}
