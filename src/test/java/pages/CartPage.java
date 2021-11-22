package pages;

import io.qameta.allure.Step;
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

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переходим на страницу корзины")
    public CartPage goToCart() {
        driver.get("http://automationpractice.com/index.php?controller=order");
        return this;
    }

    @Step("Проверяем, действительно ли мы попали на страницу корзины")
    public String isCart() {
        return isCartpage.getText();
    }

    @Step("Проверяем, что в корзине отсутствуют товары")
    public String isEmptyCart() {
        return isCartEmptyText.getText().toLowerCase();
    }

    @Step("Получаем цену одного добавленного айтема")
    public CartPage getItemPrice() {
        this.price = Double.parseDouble(itemPrice.getText().substring(1));
        return this;
    }

    @Step("Получаем финальную цену за все добавленные товары в корзину, без учета суммы доставки")
    public double getTotalItemsPrice() {
        return Double.parseDouble(totalPrice.getText().substring(1));
    }

    @Step("Изменяем количество товара в корзине")
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
