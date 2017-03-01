package litecartTest.appTests;

import litecartTest.appTests.framework.LoginAdminPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Marina on 01.03.2017.
 */
public class AdminNavigateTest extends  BaseTest {
    private By headerLocator = By.cssSelector("#content>h1");

    @BeforeClass(alwaysRun = true)
    public void login() {
        LoginAdminPage loginAdminPage = new LoginAdminPage(driver);
        loginAdminPage.fillLoginAdmin();
        loginAdminPage.clickLoginButtonAdmin();
    }



    @Test
    public void leftMenuClickTest() {
        List<WebElement> leftMenuList = driver.findElement(By.id("box-apps-menu")).findElements(By.tagName("li"));
                                                                                                                                //get names point of menu
        System.out.println("leftMenuList size = " + leftMenuList.size());
        List<String > leftMenuListName = new ArrayList<>();
        for (int l = 0; l < leftMenuList.size(); l++) {
            String name = leftMenuList.get(l).getText();
            leftMenuListName.add(name);
        }

        for (int i = 0; i < leftMenuList.size(); i++) {
            System.out.println("By.xpath = " + "//*[text() = '" + leftMenuListName.get(i) + "']");
            driver.findElement(By.xpath("//*[text() = '" + leftMenuListName.get(i) + "']")).click();                            //click on point Menu
            waitElementVisibility(headerLocator);
            areElementsPresent(driver, headerLocator);

            System.out.println("<Получение вложенных пунктов меню>");

                if (areElementsPresent(driver, By.className("docs"))) {                                                       //check element 'docs' is exist
                    List<WebElement> addList = driver.findElement(By.className("docs")).findElements(By.tagName("li"));      //get all added point Menu (secondary)
                    //если найден элемент вложенного меню:
                    if (addList.size() > 0) {
                        List<String> addListId = new ArrayList<>();                                                            //for all id of secondary points
                        System.out.println("<Получение ид вложенных пунктов меню>");
                        for (int m = 0; m < addList.size(); m++) {
                            String idName = addList.get(m).getAttribute("id");                                            //get all id of secondary points
                            addListId.add(idName);
                        }

                        System.out.println("<Прокликивание вложенных пунктов меню>");
                        for (int k = 0; k < addListId.size(); k++) {
                            driver.findElement(By.id(addListId.get(k))).click();                                                    //click by each secondary point

                            waitElementVisibility(headerLocator);
                            Assert.assertTrue(areElementsPresent(driver, headerLocator));

                        }
                    } else System.out.println("<Вложенных элементов меню у " + leftMenuListName.get(i) + " нет>");
            }else System.out.println("<Вложенных элементов меню у " + leftMenuListName.get(i) + " нет>");

        }
    }


}
