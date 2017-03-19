package litecartTest.cloudGrid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Marina on 19.03.2017.
 * https://www.browserstack.com
 * https://www.browserstack.com/automate/builds/38701911062044763661b6ddb01f32da8dd03b56
 */
public class CloudGridTest {

    private static final String USERNAME = "shomrinamarina1";
    private static final String AUTOMATE_KEY = "MsqM9iRSyEbXgzWuujF2";
    private static final String url = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    private WebDriver driverCloud;

    @BeforeClass
    public void setupBrowser() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "chrome");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("build", "First build");

        driverCloud = new RemoteWebDriver(new URL(url), caps);
    }

    @Test
    public void testCloudGrid() throws InterruptedException {
        driverCloud.get("http://www.google.com");
        WebElement element = driverCloud.findElement(By.name("q"));

        element.sendKeys("BrowserStack");
        element.submit();

        System.out.println(driverCloud.getTitle());
        Thread.sleep(3000L);
    }

    @AfterClass
    public void stop() {
        driverCloud.quit();
        driverCloud = null;
    }
}
