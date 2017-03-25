package litecartTest.pageObjectStructure;

import litecartTest.pageObjectStructure.framework.CartPage;
import litecartTest.pageObjectStructure.framework.GoodPage;
import litecartTest.pageObjectStructure.framework.ShopPageFactory;
import litecartTest.pageObjectStructure.framework.ShopMainPage;
import org.testng.annotations.Test;


/**
 * Created by mashomri on 24.03.2017.
 */
public class CartGoodsTest extends MainTest {


    @Test
    public void testAddProductToCart() {
        ShopMainPage shopMainPage = ShopPageFactory.createInstanse();
        shopMainPage.openMainPage();
                                                                                                                    //получить значение товаров и общий стоимости в корзине
        logGoodsInCart(shopMainPage);
                                                                                                                    //цикл для тройного прохода по текущему кейс
        for (int i = 0; i < 3; i++) {
            System.out.println("i = " + i);
            GoodPage goodPage = shopMainPage.chooseGoodAndOpenGoodPage(0);
            goodPage.setValueInSizeSelect("Small");
            goodPage.addGoodToCart(""  + (i + 1));
                                                                                                                        //получить значение товаров и общий стоимости в корзине
            logGoodsInCart(shopMainPage);

            shopMainPage = goodPage.goToMainPageByLogo();
        }

        CartPage cartPage = shopMainPage.goToCartPage();
        cartPage.deleteGoods(3);
    }

    private void logGoodsInCart(ShopMainPage shopMainPage) {
        String items = shopMainPage.getAmountGoods();
        String summeryPrice = shopMainPage.getSummeryPriceGoods();
        System.out.println("items = " + items + "; " + "summeryPrice = " + summeryPrice + "\n");
    }
}
