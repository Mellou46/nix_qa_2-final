package tests;

import core.BaseTest;
import io.qameta.allure.Attachment;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;

import static org.testng.Assert.assertEquals;

public class CartTests extends BaseTest {

    CartPage cart;

    @BeforeClass
    public void pageCreation() {
        cart = new CartPage(driver);
    }

    @Test
    public void isEmptyCartTest() {
        String actMsg = cart.goToCart()
                .isEmptyCart();
        assertEquals(actMsg, CartPage.expEmptyCartText);
    }

    @Test
    @Attachment
    public void addItemToCart() {
        cart.goToMain()
                .selectFirstItem()
                .addToCart()
                .confirmAddingToCart();
    }

    @Test
    public void calculateItemPrice() throws InterruptedException {
        addItemToCart();
        double actualTotalPrice = cart.getItemPrice()
                .setCountOfItems()
                .getTotalItemsPrice();
        double expectedTotalPrice = cart.price * 3;
        assertEquals(actualTotalPrice, expectedTotalPrice);
    }

    @BeforeMethod
    public void clearCart() {
        cart.clearCookies();
    }
}
