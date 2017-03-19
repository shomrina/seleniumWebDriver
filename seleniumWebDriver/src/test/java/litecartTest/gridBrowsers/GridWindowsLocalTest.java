package litecartTest.gridBrowsers;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Marina on 19.03.2017.
 */
public class GridWindowsLocalTest {

    private RemoteWebDriver driverLocal;
    private WebDriverWait waitLocal;

    @BeforeClass(alwaysRun = true)
    public void setupBrowsersDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilitiesLocal;
        desiredCapabilitiesLocal = DesiredCapabilities.chrome();
        desiredCapabilitiesLocal.setCapability("platform", "WIN10");
        driverLocal = new RemoteWebDriver(new URL("http://192.168.1.39:4444/wd/hub"), desiredCapabilitiesLocal);
        waitLocal = new WebDriverWait(driverLocal, 10);
    }

    @Test
    public void testGridChromeLocal() throws InterruptedException {
        driverLocal.get("http://localhost/litecart/admin/");
        driverLocal.findElement(By.name("username")).sendKeys("admin");
        driverLocal.findElement(By.name("password")).sendKeys("admin");
        driverLocal.findElement(By.name("login")).click();
        Thread.sleep(3000L);
    }

    @AfterClass(alwaysRun = true)
    public void stop() {
        driverLocal.quit();
        driverLocal = null;

    }
}
