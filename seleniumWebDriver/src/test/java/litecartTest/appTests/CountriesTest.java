package litecartTest.appTests;

import litecartTest.appTests.framework.LoginAdminPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Marisha on 03.03.2017.
 */
public class CountriesTest extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void openCountiesPage() {
        LoginAdminPage loginAdminPage = new LoginAdminPage(driver);
        loginAdminPage.fillLoginAdmin();
        loginAdminPage.clickLoginButtonAdmin();
        driver.findElement(By.xpath("//*[text() = 'Countries']")).click();
        waitElementVisibility(By.xpath(".//*[@class='dataTable']"));
    }

    @Test
    public void countriesNameTest() {
        List<WebElement> rowsList = driver.findElements(By.xpath(".//*[@class='row']"));
        System.out.println("rowsList size = " + rowsList.size());

        //assert Countries by alphabet
        for (int k = 0; k < rowsList.size() - 1; k++) {
            String country1 = rowsList.get(k).findElement(By.xpath(".//td[5]")).getAttribute("textContent");
            String country2 = rowsList.get(k + 1).findElement(By.xpath(".//td[5]")).getAttribute("textContent");
//            System.out.println("country1 = " + country1 + "\n country2 = " + country2);
            int rez = country1.compareTo(country2);
//            System.out.println("rez countries= " + rez);
            Assert.assertTrue(rez < 0, "Строки расположены не по алфавиту: zoneName1 = " + country1 + ", zoneName2 =  " + country2 + "\n");
        }

    }

    @Test
    public void countriesZoneTest() {
        List<WebElement> rowsList = driver.findElements(By.xpath(".//*[@class='row']"));
        System.out.println("rowsList size = " + rowsList.size());
        List<String> countriesNameWithZone = new ArrayList<>();
        //получить названия всех стран, у которых есть зоны
        for (int i = 0; i < rowsList.size(); i++) {
            int amountZones = Integer.parseInt(rowsList.get(i).findElement(By.xpath(".//td[6]")).getText());  //getAttributes("textContent");
//            System.out.println("countriesNameWithZone " + rowsList.get(i).findElement(By.xpath(".//td[5]/a")).getAttribute("textContent"));
            if (amountZones != 0) {
                System.out.println("country with zone = " + rowsList.get(i).findElement(By.xpath(".//td[5]/a")).getAttribute("textContent"));
                countriesNameWithZone.add(rowsList.get(i).findElement(By.xpath(".//td[5]/a")).getAttribute("textContent"));
            }
        }

        for (int k = 0; k < countriesNameWithZone.size(); k++) {
            List<WebElement> countryNames = driver.findElements(By.xpath(".//*[@class='row']//td[5]/a"));
            for (int l = 0; l < countryNames.size(); l++) {
//                System.out.println("countryNames.get(l).getAttribute = : " + countryNames.get(l).getAttribute("textContent"));
//                System.out.println("countriesNameWithZone.get(k) = : " + countriesNameWithZone.get(k));
                if (countryNames.get(l).getAttribute("textContent").equals(countriesNameWithZone.get(k))) {
                    countryNames.get(l).click();
                    waitElementVisibility(By.xpath(".//*[@id='content']//h2"));
                    WebElement element = driver.findElement(By.xpath(".//*[@id='table-zones']"));
                    ((Locatable) element).getCoordinates().inViewPort();
                    waitElementVisibility(By.xpath(".//*[@id='table-zones']"));
                    List<WebElement> zonesNameList = driver.findElements(By.xpath(".//*[@id='table-zones']//td[3]"));
//                    System.out.println("zonesNameList size = " + zonesNameList.size());
                    for (int j = 0; j < zonesNameList.size() - 2; j++) {
                        String zoneName1 = zonesNameList.get(j).getAttribute("textContent");
                        String zoneName2 = zonesNameList.get(j + 1).getAttribute("textContent");
//                        System.out.println("zoneName1 = " + zoneName1 +  "\n zoneName2 = " + zoneName2);
                        int rez2 = zoneName1.compareTo(zoneName2);
                        Assert.assertTrue(rez2 < 0, "Строки расположены не по алфавиту: zoneName1 = " + zoneName1 + ", zoneName2 = " + zoneName2 + "\n");
                    }

                    WebElement element2 = driver.findElement(By.xpath("//*[text() = 'Countries']"));
                    ((Locatable) element2).getCoordinates().inViewPort();
                    driver.findElement(By.xpath("//*[text() = 'Countries']")).click();
                    waitElementVisibility(By.xpath(".//*[@class='dataTable']"));
                    break;

                }
            }


        }

    }
}
