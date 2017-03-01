package litecartTest.appTests;

import litecartTest.appTests.framework.LoginAdminPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



/**
 * Created by Marina on 01.03.2017.
 */
public class AdminNavigateTest extends  BaseTest {

    @BeforeClass
    public void login() {
        LoginAdminPage loginAdminPage = new LoginAdminPage(driver);
        loginAdminPage.fillLoginAdmin();
        loginAdminPage.clickLoginButtonAdmin();
    }


    @Test
    public void AppearenceTest() {
        driver.findElement(By.xpath("//*[text() = 'Appearence']")).click();
        waitElementVisibility(By.cssSelector("#content>h1"));

        String appearence = driver.findElement(By.cssSelector("#content>h1")).getText();  //.getAttribute("text");
        Assert.assertEquals(appearence, "Template", "Заголовок не совпадает с ожидаемым значением \n");

        driver.findElement(By.id("doc-template")).click();
        appearence = driver.findElement(By.cssSelector("#content>h1")).getText();  //.getAttribute("text");
        Assert.assertEquals(appearence, "Template", "Заголовок не совпадает с ожидаемым значением \n");

        driver.findElement(By.id("doc-logotype")).click();
        appearence = driver.findElement(By.cssSelector("#content>h1")).getText();  //.getAttribute("text");
        Assert.assertEquals(appearence, "Logotype", "Заголовок не совпадает с ожидаемым значением \n");
    }

    @Test
    public void CatalogTest() {

    }


}
