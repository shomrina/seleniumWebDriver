package litecartTest.pageObjectStructure;


import litecartTest.pageObjectStructure.framework.ShopPageFactory;
import org.testng.annotations.AfterClass;

/**
 * Created by mashomri on 24.03.2017.
 */
public class MainTest {

    @AfterClass(alwaysRun = true)
    public void stop() {
        ShopPageFactory.deinit();
    }




}
