package litecartTest.appTests.adminTest;

import litecartTest.appTests.BaseTest;
import litecartTest.appTests.framework.LoginAdminPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Marina on 12.03.2017.
 */
public class AddNewProductTest extends BaseTest {
    private By addNewProductButton = By.xpath(".//*[@id='content']/div[1]/a[2]");
    private By saveButton = By.xpath(".//*[@name='save']");

    /** GENERAL */
    /**  Locators */
    private By statusEnabled = By.xpath(".//*[@name='status' and @value=1]");
    private By name = By.xpath(".//*[@name='name[en]']");
    private By code = By.xpath(".//*[@name='code']");
    //categories /
    private By categories = By.xpath(".//*[@name='categories[]']");
    //default category
    private By defaultCategory = By.xpath(".//*[@name='default_category_id']");
    //product groups
    private By productGroups = By.xpath(".//*[@name='product_groups[]']");
    private By guantity = By.xpath(".//*[@name='quantity']");
    private By quantityUnit = By.xpath(".//*[@name='quantity_unit_id']");
    private By deliveryStatus = By.xpath(".//*[@name='delivery_status_id']");
    private By soldOutStatus = By.xpath(".//*[@name='sold_out_status_id']");
    private By uploadImages = By.xpath(".//*[@name='new_images[]']");
    private By dateValidFrom = By.xpath(".//*[@name='date_valid_from']");
    private By dateValidTo = By.xpath(".//*[@name='date_valid_to']");

    /**  Select */
    private Select defaultCategorySelect;
    private Select quantityUnitSelect;
    private Select deliveryStatusSelect;
    private Select soldOutStatusSelect;

    private void setDefaultCategorySelect() {
        defaultCategorySelect = new Select(driver.findElement(defaultCategory));
    }
    private void setQuantityUnitSelect() {
        quantityUnitSelect = new Select(driver.findElement(quantityUnit));
    }
    private void setDeliveryStatusSelect() {
        deliveryStatusSelect = new Select(driver.findElement(deliveryStatus));
    }
    private void setSoldOutStatusSelect() {
        soldOutStatusSelect = new Select(driver.findElement(soldOutStatus));
    }

    /** Check-box */
    private List<WebElement> getListsCategories() {
        //метод для получения всех чекбоксов для всех категорий
        return driver.findElements(categories);
    }

    private List<WebElement> getListsProductGroups() {
        //метод для получения всех чекбоксов для всех групп продуктов
        return driver.findElements(productGroups);
    }

    /** INFORMATION Locators */
    private By informationLink = By.xpath(".//ul[@class='index']/li/a[contains(text(),'Information')]");
    private By manufacturer = By.xpath(".//*[@name='manufacturer_id']");
    private By supplier = By.xpath(".//*[@name='supplier_id']");
    private By keywords = By.xpath(".//*[@name='keywords']");
    private By description = By.xpath(".//*[@class='trumbowyg-editor']");
    private By headTitle = By.xpath(".//*[@name='head_title[en]']");
    private By metaDescription = By.xpath(".//*[@name='meta_description[en]']");


    /**  Select */
    private Select manufacturerSelect;
    private Select supplierSelect;


    private void setManufacturerSelect() {
        manufacturerSelect = new Select(driver.findElement(manufacturer));
    }
    private void setSupplierSelect() {
        supplierSelect = new Select(driver.findElement(supplier));
    }

    /** PRICES */
    /**  Locators */
    private By pricesLink = By.xpath(".//ul[@class='index']/li/a[contains(text(),'Prices')]");
    private By purchasePrice = By.xpath(".//*[@name='purchase_price']");
    private By purchaseCurrency = By.xpath(".//*[@name='purchase_price_currency_code']");
    private By taxClass = By.xpath(".//*[@name='tax_class_id']");
    private By priceUsd = By.xpath(".//*[@name='prices[USD]']");
    private By priceUsdTax = By.xpath(".//*[@name='gross_prices[USD]']");
    private By priceEuro = By.xpath(".//*[@name='prices[EUR]']");
    private By priceEuroTax = By.xpath(".//*[@name='gross_prices[EUR]']");

    /**  Select */
    private Select purchaseCurrencySelect;
    private Select taxClassSelect;

    private void setPurchaseCurrencySelect() {
        purchaseCurrencySelect =  new Select(driver.findElement(purchaseCurrency));
    }
    private void setTaxClassSelect() {
        taxClassSelect = new Select(driver.findElement(taxClass));
    }
    private void assertExistCreatedProduct(final String productName) {
        //метод для проверки, что товар появился. Попытка его получить. Если нет, то ошибка
        try {
            WebElement createdProduct = driver.findElement(By.xpath(".//*[@class='dataTable']//td//a[contains(text(),'" + productName + "')]"));
        } catch (NoSuchElementException e) {
            e.getMessage();
        }
    }





    @BeforeClass
    public void openCatalogPage() {
        LoginAdminPage loginAdminPage = new LoginAdminPage(driver);
        loginAdminPage.fillLoginAdmin();
        loginAdminPage.clickLoginButtonAdmin();
        driver.findElement(By.xpath("//*[text() = 'Catalog']")).click();
        waitElementVisibility(By.xpath(".//*[@class='dataTable']"));
    }

    @Test
    public void testAddNewProduct() {
        driver.findElement(addNewProductButton).click();
        waitElementVisibility(name);

        /** GENERAL */
        driver.findElement(statusEnabled).click();
        String productName = "Bear " + (int) (Math.random()*100);
        driver.findElement(name).sendKeys(productName);
        driver.findElement(code).sendKeys("" + (int) (Math.random()*1000));
        getListsCategories().get(1).click();
        waitInSeconds(1);
        setDefaultCategorySelect();
        defaultCategorySelect.selectByVisibleText("Rubber Ducks");
        getListsProductGroups().get(2).click();
        driver.findElement(guantity).sendKeys("10");
        setQuantityUnitSelect();
        quantityUnitSelect.selectByVisibleText("pcs");
        setDeliveryStatusSelect();
        deliveryStatusSelect.selectByVisibleText("3-5 days");
        setSoldOutStatusSelect();
        soldOutStatusSelect.selectByVisibleText("Temporary sold out");
        driver.findElement(uploadImages).sendKeys(convertToAbsolutePath("bear.jpg").toString());
        waitInSeconds(1);
        driver.findElement(dateValidFrom).sendKeys("2017-03-12");
        driver.findElement(dateValidTo).sendKeys("2018-03-12");

        /** INFORMATION */
        driver.findElement(informationLink).click();
        waitElementVisibility(manufacturer);
        setManufacturerSelect();
        manufacturerSelect.selectByVisibleText("ACME Corp.");
        setSupplierSelect();
        supplierSelect.selectByVisibleText("-- Select --");
        driver.findElement(keywords).sendKeys("bear, toys");
        driver.findElement(description).sendKeys("description for test");
        driver.findElement(headTitle).sendKeys("Teddy Bear");
        driver.findElement(metaDescription).sendKeys("meta description for test");

        /** PRICES */
        driver.findElement(pricesLink).click();
        waitElementVisibility(purchasePrice);
        driver.findElement(purchasePrice).sendKeys("100");
        setPurchaseCurrencySelect();
        purchaseCurrencySelect.selectByVisibleText("Euros");
        setTaxClassSelect();
        taxClassSelect.selectByVisibleText("-- Select --");
        driver.findElement(priceUsd).sendKeys("100");
        driver.findElement(priceUsdTax).clear();
        driver.findElement(priceUsdTax).sendKeys("150");
        driver.findElement(priceEuro).sendKeys("95");
        driver.findElement(priceEuroTax).clear();
        driver.findElement(priceEuroTax).sendKeys("120");

        driver.findElement(saveButton).click();
        waitInSeconds(1);
        waitElementVisibility(By.xpath(".//*[@class='dataTable']"));
        assertExistCreatedProduct(productName);

    }

    public Path convertToAbsolutePath(final String fileName) {
        //fileName - имя файла в папке ресурс для преобразования пути
        // Path productPath = Paths.get("C:\\Users\\Marina\\Documents\\GitHub\\seleniumWebDriver\\seleniumWebDriver\\src\\test\\resources\\bear.jpg");
        Path productPath = Paths.get("src/test/resources/" + fileName);  //получение относительного пути
        //  System.out.println("productPath = " + productPath);
        return productPath.toAbsolutePath();   //convert to absolute path
        // System.out.println("product = " + product);
    }

}
