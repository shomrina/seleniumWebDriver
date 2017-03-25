package litecartTest.pageObjectStructure.framework;

import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

/**
 * Created by Marina on 25.03.2017.
 */
public class ShopPageFactory {

    private static ChromeDriver driver;

    public static ShopMainPage createInstanse() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   //неявное ожидание
        }

        return new ShopMainPage(driver);
    }

    public static void deinit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
