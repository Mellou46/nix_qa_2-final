package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends MainPage {

    public static String expEmptyCartText = "your shopping cart is empty.";
    public double price;
    @FindBy(xpath = "//p[@class='alert alert-warning']")
    public WebElement isCartEmptyText;
    @FindBy(xpath = "//span[text()='Your shopping cart']")
    private WebElement isCartpage;
    @FindBy(xpath = "//span/span[@class='price']")
    private WebElement itemPrice;
    @FindBy(xpath = "//span[@id='summary_products_quantity']")
    private WebElement countOfProductsInTheCart;
    @FindBy(xpath = "//td[@class='cart_quantity text-center']/input[2]")
    private WebElement productCountField;
    @FindBy(xpath = "//tr[@id='product_1_1_0_0']/td[6]/span")
    private WebElement totalPrice;
//
//    @FindBy(xpath = "")
//    private WebElement name6;
//
//    @FindBy(xpath = "")
//    private WebElement name7;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage goToCart() {
        driver.get("http://automationpractice.com/index.php?controller=order");
        return this;
    }

    public String isCart() {
        return isCartpage.getText();
    }

    public String isEmptyCart() {
        return isCartEmptyText.getText().toLowerCase();
    }

    public CartPage getItemPrice() {
        this.price = Double.parseDouble(itemPrice.getText().substring(1));
        return this;
    }

    public double getTotalItemsPrice() {
        return Double.parseDouble(totalPrice.getText().substring(1));
    }

    public CartPage setCountOfItems() throws InterruptedException {
        productCountField.click();
        productCountField.clear();
        productCountField.sendKeys("3");
        Thread.sleep(3000);
        return this;
    }

    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }
}
