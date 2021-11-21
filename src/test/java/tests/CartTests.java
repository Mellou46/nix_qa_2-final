package tests;

import core.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;

import static org.testng.Assert.assertEquals;

public class CartTests extends BaseTest {

    CartPage cart;
    Actions action;

    @BeforeClass
    public void pageCreation() {
        cart = new CartPage(driver);
        action = new Actions(driver);
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

//    @Test
//    public void fff(){
//        cart.goToMain();
//        WebElement hoverEl = driver.findElement(By.xpath("//ul[@id='homefeatured']/li[1]//div[@class='right-block']//div/span[@itemprop='price']"));
//        WebElement hoverEl = driver.findElement(By.xpath("//ul[@id ='homefeatured']/li[1]"));
//        action.moveToElement(hoverEl).click().build().perform();
//    }

    @BeforeMethod
    public void clearCart() {
        cart.clearCookies();
    }
}
