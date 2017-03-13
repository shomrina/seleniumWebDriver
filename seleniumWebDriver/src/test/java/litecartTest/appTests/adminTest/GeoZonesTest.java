package litecartTest.appTests.adminTest;

import litecartTest.appTests.BaseTest;
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
 * Created by Marisha on 05.03.2017.
 */
public class GeoZonesTest extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void openGeoZonesPage() {
        LoginAdminPage loginAdminPage = new LoginAdminPage(driver);
        loginAdminPage.fillLoginAdmin();
        loginAdminPage.clickLoginButtonAdmin();
        driver.findElement(By.xpath("//*[text() = 'Geo Zones']")).click();
        waitAllElementVisibility(By.xpath(".//*[@class='dataTable']"), 20);
    }

    @Test
    public void geoZonesNameTest() {
        List<WebElement> rowsList = driver.findElements(By.xpath(".//*[@class='row']"));
        System.out.println("rowsList size = " + rowsList.size());
        List<String> countriesNameWithZone = new ArrayList<>();
                                                                                                                                                //получить названия всех стран а странице Geo Zones
        for (int i = 0; i < rowsList.size(); i++) {
                System.out.println("country  = " + rowsList.get(i).findElement(By.xpath(".//td[3]/a")).getAttribute("textContent"));
                countriesNameWithZone.add(rowsList.get(i).findElement(By.xpath(".//td[3]/a")).getAttribute("textContent"));
        }

        for (int k = 0; k < countriesNameWithZone.size(); k++) {                                                                                //проход по кол-ву найденных стран
            List<WebElement> countryNamesLinks = driver.findElements(By.xpath(".//*[@class='row']//td[3]/a"));                                  //получить ссылки всех элементов (стран)
            for (int l = 0; l < countryNamesLinks.size(); l++) {                                                                                //обход по массиву ссылок
/*                System.out.println("countryNamesLinks.get(l).getAttribute = : " + countryNamesLinks.get(l).getAttribute("textContent"));
                System.out.println("countriesNameWithZone.get(k) = : " + countriesNameWithZone.get(k));*/
                if (countryNamesLinks.get(l).getAttribute("textContent").equals(countriesNameWithZone.get(k))) {                             //если атрибут текст у ссылки совпадает с названием страны в k-ом элементе массива, то..
                    countryNamesLinks.get(l).click();       //нажимаем на ссылку и переходи внутрь
                    waitAllElementVisibility(By.xpath(".//*[@id='table-zones']"), 20);
                    List<WebElement> zonesNameList = driver.findElements(By.xpath(".//*[@id='table-zones']//td[3]"));                           //получить все зоны как веб элементы
                    for (int j = 0; j < zonesNameList.size() - 1; j++) {
                        List<WebElement> optionsList = zonesNameList.get(j).findElements(By.xpath(".//select/option"));                         //получить названия всех опций у данного выпадающего списка (веб элемента)
                                                                                                                                                //определение названия зоны в селект боксе
                        String zone = null;
                        for (int m = 0; m < optionsList.size(); m++){                                                                           //проходим по массиву опций, и если есть тру у дефолтСелектид берем от туда значение атрибута лейбл
/*                            System.out.println("defaultSelected = " + optionsList.get(m).getAttribute("defaultSelected"));
                            System.out.println("defaultSelected isSelected [" + m + "] = " + optionsList.get(m).isSelected());*/
                            if (optionsList.get(m).isSelected()) {
                                String name = optionsList.get(m).getAttribute("label");
                                zone = name;
                                break;
                            }
                        }

                        List<WebElement> optionsList2 = zonesNameList.get(j + 1).findElements(By.xpath(".//select/option"));                    //получить опции у второго списка (сравниваемого)
                        String zone2 = null;
                        for (int n = 0; n < optionsList2.size(); n++){
                            if (optionsList2.get(n).isSelected()) {
                                String name = optionsList2.get(n).getAttribute("label");
                                zone2 = name;
                                break;
                            }
                        }
//                        System.out.println("zoneName1 = " + zone +  "\n zoneName2 = " + zone2);
                        int rez = zone.compareTo(zone2);
                        Assert.assertTrue(rez < 0, "Строки расположены не по алфавиту: zoneName1 = " + zone + ", zoneName2 = " + zone2 + "\n");


                    }
                    WebElement element2 = driver.findElement(By.xpath("//*[text() = 'Geo Zones']"));
                    ((Locatable) element2).getCoordinates().inViewPort();
                    driver.findElement(By.xpath("//*[text() = 'Geo Zones']")).click();
                    waitAllElementVisibility(By.xpath(".//*[@class='dataTable']"), 20);
                    break;

                }
            }
        }
    }
}
