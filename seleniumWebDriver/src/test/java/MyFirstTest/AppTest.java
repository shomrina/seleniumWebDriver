package MyFirstTest;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * Unit test for simple App.
 */
public class AppTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        driver.get("http://www.google.com/");

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
