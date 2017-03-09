package litecartTest.appTests;

import litecartTest.appTests.framework.ShopMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by Marisha on 08.03.2017.
 */
public class RegistrationNewUserTest extends BaseTest {

    //locators
    private By initCreatedNewCustomer = By.xpath(".//*[@name='login_form']//tr[5]/td/a");
    private By emailAddress = By.xpath(".//*[@name='email']");
    private By password = By.xpath(".//*[@name='password']");
    private By loginButton = By.xpath(".//*[@name='login']");

    private By headerCreaterAccount = By.xpath(".//*[@id='create-account']/h1");
    private By taxId = By.xpath(".//*[@name='tax_id']");
    private By company = By.xpath(".//*[@name='company']");
    private By firstName = By.xpath(".//*[@name='firstname']");
    private By lastName = By.xpath(".//*[@name='lastname']");
    private By address1 = By.xpath(".//*[@name='address1']");
    private By address2 = By.xpath(".//*[@name='address2']");
    private By postcode = By.xpath(".//*[@name='postcode']");
    private By city = By.xpath(".//*[@name='city']");
 //   private By country = By.xpath(".//*[@id='select2-country_code-nk-container']");  //select United States
    private By country = By.xpath(".//*[@name='country_code']");  //select United States
    private By searchInSelectCountry = By.xpath(".//[@class='select2-search__field'");
    private By zone = By.xpath(".//select[@name='zone_code']");  //select any
    private By email = By.xpath(".//*[@name='email']");
    private By phone = By.xpath(".//*[@name='phone']");
    private By password2 = By.xpath(".//*[@name='password']");
    private By confirmPassword = By.xpath(".//*[@name='confirmed_password']");
    private By createAccountButton = By.xpath(".//*[@type='submit']");

    private By logout = By.xpath(".//*[@class='list-vertical']/li[4]/a");


    private Select countrySelect;

    private String emailFotTest;
    private String passwordForTest;


    private void setCountrySelect() {
        countrySelect = new Select(driver.findElement(country));
    }

    private void setZoneSelect() {
        zoneSelect = new Select(driver.findElement(zone));
    }

    private Select zoneSelect;



    @BeforeClass
    public void openMainPage() {
        ShopMainPage shopMainPage = new ShopMainPage(driver);
        shopMainPage.openMainPage();
    }

    @Test
    public void createNewAccountTest() {
        //init creation account
        driver.findElement(initCreatedNewCustomer).click();
        waitElementVisibility(headerCreaterAccount);

        //fill create form
        driver.findElement(taxId).sendKeys("test");
        driver.findElement(company).sendKeys("test Company");
        driver.findElement(firstName).sendKeys("Marina" + Math.random()*1000);
        driver.findElement(lastName).sendKeys("Shomrina" + Math.random()*1000);
        driver.findElement(address1).sendKeys("test address 1");
        driver.findElement(address2).sendKeys("test address 2");
        driver.findElement(postcode).sendKeys(getRandomIntNumber(10000, 100000));
        driver.findElement(city).sendKeys("test City");


        setCountrySelect();
        countrySelect.selectByVisibleText("United States");
     //   waitElementVisibility(zone);
        setZoneSelect();
        setSelectedInSet(zoneSelect);
        emailFotTest = "mar" + Math.random()*1000 + "@ma.ru";
        driver.findElement(email).sendKeys(emailFotTest);
        driver.findElement(phone).sendKeys(getRandomIntNumber(100, 10000000));
        passwordForTest = "mar";
        driver.findElement(password2).sendKeys(passwordForTest);
        driver.findElement(confirmPassword).sendKeys("mar");
        driver.findElement(createAccountButton).click();

        //logout
        waitElementVisibility(logout);
        driver.findElement(logout).click();

        //login
        waitElementVisibility(emailAddress);
        driver.findElement(emailAddress).sendKeys(emailFotTest);
        driver.findElement(password).sendKeys(passwordForTest);
        driver.findElement(loginButton).click();

        //logout
        waitElementVisibility(logout);
        driver.findElement(logout).click();

    }

    public String getRandomIntNumber(int low, int high) {
        //генерация целого случайного числа в указанном промежутке between low (inclusive - включительно) and high (exclusive - исключая)
        final Random random = new Random();
        int r = random.nextInt(high - low) + low; // возвращает следующее случайное значение
        return Integer.toString(r); // преобразование полученного числа в строку
    }

    public void setSelectedInSet(Select set) {
        //метод выбирает произвольное значение из всего списка селекта
        int index = (int) (Math.random()*(set.getOptions().size()-2))+1;
        set.selectByIndex(index);
    }

}
