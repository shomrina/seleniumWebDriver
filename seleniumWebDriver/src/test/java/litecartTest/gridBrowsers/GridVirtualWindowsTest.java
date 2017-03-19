package litecartTest.gridBrowsers;

import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Marina on 19.03.2017.
 */
public class GridVirtualWindowsTest {


    private RemoteWebDriver driverVirtual;
    private WebDriverWait waitVirtual;

    @BeforeClass(alwaysRun = true)
    public void setupBrowsersDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilitiesVirtual;
        desiredCapabilitiesVirtual = DesiredCapabilities.internetExplorer();

       /* desiredCapabilitiesVirtual.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
      //  desiredCapabilitiesVirtual.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://www.google.com");
        desiredCapabilitiesVirtual.setCapability("ignoreProtectedModeSettings", true);
        desiredCapabilitiesVirtual.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        desiredCapabilitiesVirtual.setJavascriptEnabled(true);
        desiredCapabilitiesVirtual.setCapability("requireWindowFocus", true);
        desiredCapabilitiesVirtual.setCapability("enablePersistentHover", false);
        desiredCapabilitiesVirtual.setCapability("requireWindowFocus", true);*/

        desiredCapabilitiesVirtual.setCapability("platform", "VISTA");  //VISTA
        driverVirtual = new RemoteWebDriver(new URL("http://192.168.1.39:4444/wd/hub"), desiredCapabilitiesVirtual);
//        driverVirtual.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//        driverVirtual.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        waitVirtual = new WebDriverWait(driverVirtual, 10);
    }

    @Test
    public void testGridIeOnVirtual() throws InterruptedException {
        driverVirtual.get("http://192.168.1.39/litecart/admin/");
   //     Thread.sleep(5000L);
   //     waitElementPresence(By.name("username"), 30);
        driverVirtual.findElement(By.name("username")).sendKeys("admin");
        driverVirtual.findElement(By.name("password")).sendKeys("admin");
        driverVirtual.findElement(By.name("login")).click();
        Thread.sleep(3000L);
    }

    @AfterClass(alwaysRun = true)
    public void stop() {
        driverVirtual.quit();
        driverVirtual = null;
    }

    public void waitElementPresence(By element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driverVirtual, timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }
}
