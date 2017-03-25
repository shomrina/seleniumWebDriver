package litecartTest.appTests.adminTest;

import litecartTest.appTests.BaseTest;
import litecartTest.appTests.framework.LoginAdminPage1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marina on 02.03.2017.
 * Пробный класс с пробными теста. Жалко было выбрасывать
 */
public class StuffTest extends BaseTest {

    private By headerLocator = By.cssSelector("#content>h1");

    @BeforeClass
    public void login() {
        LoginAdminPage1 loginAdminPage = new LoginAdminPage1(driver);
        loginAdminPage.fillLoginAdmin();
        loginAdminPage.clickLoginButtonAdmin();
    }


    @Test(enabled = false)
    public void appearenceTest() {
        driver.findElement(By.xpath("//*[text() = 'Appearence']")).click();
        waitAllElementVisibility(headerLocator, 20);
        String appearence = driver.findElement(headerLocator).getText();  //.getAttribute("text");
        Assert.assertEquals(appearence, "Template", "Заголовок не совпадает с ожидаемым значением \n");

        driver.findElement(By.id("doc-template")).click();
        waitAllElementVisibility(By.cssSelector("#content>h1"), 20);
        appearence = driver.findElement(headerLocator).getText();  //.getAttribute("text");
        Assert.assertEquals(appearence, "Template", "Заголовок не совпадает с ожидаемым значением \n");

        driver.findElement(By.id("doc-logotype")).click();
        waitAllElementVisibility(headerLocator, 20);
        appearence = driver.findElement(headerLocator).getText();  //.getAttribute("text");
        Assert.assertEquals(appearence, "Logotype", "Заголовок не совпадает с ожидаемым значением \n");
    }

    @Test(enabled = false)
    public void catalogTest() {
        driver.findElement(By.xpath("//*[text() = 'Catalog']")).click();
        waitAllElementVisibility(headerLocator, 20);
        String appearence = driver.findElement(headerLocator).getText();
        Assert.assertEquals(appearence, "Catalog", "Заголовок не совпадает с ожидаемым значением \n");


        List<WebElement> catalogList = driver.findElement(By.className("docs")).findElements(By.tagName("li"));
        List<String > catalogListName = new ArrayList<>();
        List<String > catalogListId = new ArrayList<>();
        for (int i = 0; i < catalogList.size(); i++) {
            String name = catalogList.get(i).getText();
            String idName = catalogList.get(i).getAttribute("id");
            catalogListName.add(name);
            catalogListId.add(idName);
        }


        for (int k = 0; k < catalogListId.size(); k++) {
            driver.findElement(By.id(catalogListId.get(k))).click();
            waitAllElementVisibility(headerLocator, 20);
            appearence = driver.findElement(headerLocator).getText();
            Assert.assertEquals(appearence, catalogListName.get(k), "Заголовок не совпадает с ожидаемым значением \n");
        }
    }
}
