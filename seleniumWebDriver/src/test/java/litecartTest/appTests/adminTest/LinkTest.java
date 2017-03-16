package litecartTest.appTests.adminTest;

import litecartTest.appTests.BaseTest;
import litecartTest.appTests.framework.LoginAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

/**
 * Created by mashomri on 16.03.2017.
 * в этом упражнении требуется именно кликнуть по ссылке,
 чтобы она открылась в новом окне,
 потом переключиться в новое окно,
 закрыть его,
 вернуться обратно,
 и повторить эти действия для всех таких ссылок.
 */
public class LinkTest extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void openCountriesPage() {
        LoginAdminPage loginAdminPage = new LoginAdminPage(driver);
        loginAdminPage.fillLoginAdmin();
        loginAdminPage.clickLoginButtonAdmin();
        driver.findElement(By.xpath("//*[text() = 'Countries']")).click();
        waitAllElementVisibility(By.xpath(".//*[@class='dataTable']"), 20);
    }

    @Test
    public void testOpenLinkInNewWindow() {
        List<WebElement> rowsList = driver.findElements(By.xpath(".//*[@class='row']"));                                    //получить все страны
        int index = Integer.parseInt(getRandomIntNumber(0, rowsList.size()));                                               //выбрать случайный индекс (для выбора страны произвольно)
        System.out.println("index = " + index + ": "
                + rowsList.get(index).findElement(By.xpath(".//td[5]/a")).getAttribute("textContent"));
        rowsList.get(index).findElement(By.xpath(".//td[5]/a")).click();                                                    //октрыть страницу редактирования страны
        waitElementPresence(By.xpath(".//form[@method='post']/table//a"), 15);
        List<WebElement> linksList = driver.findElements(By.xpath(".//form[@method='post']/table//a[@target='_blank']"));   //получить все ссылочкные элементы на странице
        System.out.println("linksList = " + linksList.size());
        for (int i = 0; i < linksList.size(); i++) {
            String mainWindow = driver.getWindowHandle();                                                                    //получение ид активного окна
            System.out.println("currentHandle = " + mainWindow);
            Set<String> oldWindowHandles =  driver.getWindowHandles();                                                             //получение ид всех открытых окон
            System.out.println("windowHandles = " + oldWindowHandles.size());

            linksList.get(i).click();                                                                                       //нажать на ссылку
            System.out.println("Open link with index: " + i);

            String newWindow = wait.until((WebDriver d) -> thereIsWindowOtherThan(oldWindowHandles));                   //ожидание нового окна (лямбда выражение. until на вход принимает предикат, а метод возращшает строку. поэтому нужно так)
            driver.switchTo().window(newWindow);                                                                        //переключение на новое открытое окно
   //         System.out.println("currentURL: " + driver.getCurrentUrl() + "\n");
            driver.close();                                                                                             //закрытие окна, на которое переключились
            driver.switchTo().window(mainWindow);                                                                       // переключение на основное окно
            }
        }






    public String thereIsWindowOtherThan(Set<String> oldWindowHandles) {
        //метод, который находит новое окно
        Set<String> newWindowHandles =  driver.getWindowHandles();                                                             //получение ид всех открытых окон
        System.out.println("windowHandles = " + newWindowHandles.size());
        for(String newWindow: newWindowHandles) {
            if(!oldWindowHandles.contains(newWindow)) {
                System.out.println("new window:  = " + newWindow);
                return newWindow;
            }
        }
        return null;
        //другой вариант решения, без передачи лммбда выражения: сделать класс, который реализует интерфейс Function, и передавать в метод until экземпляр этого класса. Это будет громоздко)
    }
}
