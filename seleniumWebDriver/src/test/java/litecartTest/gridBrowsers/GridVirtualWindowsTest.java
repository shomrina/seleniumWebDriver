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
public class GridVirtualWindowsTest {


    private RemoteWebDriver driverVirtual;
    private WebDriverWait waitVirtual;

    @BeforeClass
    public void setupBrowsersDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilitiesVirtual;
        desiredCapabilitiesVirtual = DesiredCapabilities.internetExplorer();
        desiredCapabilitiesVirtual.setCapability("platform", "VISTA");
        driverVirtual = new RemoteWebDriver(new URL("http://192.168.1.39:4444/wd/hub"), desiredCapabilitiesVirtual);
        waitVirtual = new WebDriverWait(driverVirtual, 10);
    }

    @Test
    public void testGridIeOnVirtual() {
        driverVirtual.get("http://localhost/litecart/admin/");
        driverVirtual.findElement(By.name("username")).sendKeys("admin");
        driverVirtual.findElement(By.name("password")).sendKeys("admin");
        driverVirtual.findElement(By.name("login")).click();
    }

    @AfterClass
    public void stop() {
        driverVirtual.quit();
        driverVirtual = null;
    }
}
