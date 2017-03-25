package litecartTest.appTests.adminTest;

import litecartTest.appTests.framework.LoginAdminPage1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mashomri on 22.03.2017.
 */
public class EventFiringCatalogTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   //неявное ожидание
        wait = new WebDriverWait(driver, 10);               //для явного ожидания
    }
    @Test
    public void testEventFiringInCatalog() {
            //LOGIN in admin
        LoginAdminPage1 loginAdminPage = new LoginAdminPage1(driver);
        loginAdminPage.fillLoginAdmin();
        loginAdminPage.clickLoginButtonAdmin();
            //go to page Catalog
        driver.findElement(By.xpath("//*[text() = 'Catalog']")).click();
        waitAllElementVisibility(By.xpath(".//*[@class='dataTable']"), 20);
            //
        driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'Rubber Ducks')]")).click();                       //click by Rubber Ducks
        waitElementVisibility(driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'Subcategory')]")), 5);  //wait other folder
        driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'Subcategory')]")).click();                    //click by Subcategory
        waitAllElementVisibility(By.xpath(".//*[@class='dataTable']//td//a[contains(@href,'product_id')]"), 20);   //wait

        List<WebElement> goodsList = driver.findElements(By.xpath(".//*[@class='dataTable']//td[3]//a[contains(@href,'product_id')]"));  //выбрать в таблице все объекты, которые продукты
        System.out.println("goodList size = " + goodsList.size());
        for (int i = 0; i < goodsList.size(); i++) {
            if (i != 0) {
                driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'Rubber Ducks')]")).click();                       //click by Rubber Ducks
                waitElementVisibility(driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'Subcategory')]")), 5);  //wait other folder
                driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'Subcategory')]")).click();                    //click by Subcategory
                waitAllElementVisibility(By.xpath(".//*[@class='dataTable']//td//a[contains(@href,'product_id')]"), 20);   //wait
            }

            WebElement good = driver.findElements(By.xpath(".//*[@class='dataTable']//td[3]//a[contains(@href,'product_id')]")).get(i);

            System.out.println("i = [" + i + "]: href = "
                    + good.getAttribute("href")
            + ", good name = " + good.getText());

            good.click();  //открыть страницу товара
            waitAllElementVisibility(By.xpath(".//*[@name='name[en]']"), 20);                               //ожидание

            //todo здесь вставить проверку не появляются ли в логе браузера сообщения (любого уровня)


            driver.findElement(By.xpath(".//*[@name='cancel']")).click();
            waitAllElementVisibility(By.xpath(".//*[@class='dataTable']"), 20);
        }




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

    public static class MyListener extends AbstractWebDriverEventListener {

    }

}
