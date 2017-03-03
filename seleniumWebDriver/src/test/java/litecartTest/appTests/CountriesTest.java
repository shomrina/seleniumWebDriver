package litecartTest.appTests;

import litecartTest.appTests.framework.LoginAdminPage;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Marisha on 03.03.2017.
 */
public class CountriesTest extends BaseTest {

    @BeforeTest
    public void openCountiesPage() {
        LoginAdminPage loginAdminPage = new LoginAdminPage(driver);
        loginAdminPage.fillLoginAdmin();
        loginAdminPage.clickLoginButtonAdmin();
        driver.findElement(By.xpath("//*[text() = 'Appearence']")).click();
        waitElementVisibility(By.xpath(".//*[@class='dataTable']"));
    }

    @Test
    public void countriesNameAndZoneTest() {

    }
}
