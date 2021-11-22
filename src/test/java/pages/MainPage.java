package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    public String expTitleOfWomanPage = "WOMEN";
    public String expTitleOfMainPage = "POPULAR";
    Actions action = new Actions(driver);
    @FindBy(xpath = "//div[@id='block_top_menu']/ul[1]/li[1]")
    private WebElement womenHeader;
    @FindBy(xpath = "//div[@id='block_top_menu']/ul[1]/li[2]")
    private WebElement dressesHeader;
    @FindBy(xpath = "//div[@id='block_top_menu']/ul[1]/li[3]")
    private WebElement tshortsHeader;
    @FindBy(xpath = "//div[@class='breadcrumb clearfix']/span[2]")
    private WebElement isWomanCheck;
    @FindBy(xpath = "//ul[@id='home-page-tabs']/li[1]/a")
    private WebElement isMainCheck;
    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement cart;
    @FindBy(xpath = "//ul[@id ='homefeatured']/li[1]")
    private WebElement itemsOnMain;
    @FindBy(xpath = "//ul[@id='homefeatured']/li[1]//a[@title='Add to cart']")
    private WebElement addToCartOnMain;
    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    private WebElement confirmAddToCartOnMain;
    @FindBy(xpath = "//ul[@id='homefeatured']/li[1]/div[@class='product-container']")
    private WebElement firstItemOnMain;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переходим на главную страницу")
    public MainPage goToMain() {
        driver.get("http://automationpractice.com/");
        return this;
    }

    @Step("Выбираем первый товар на главной странице")
    public MainPage selectFirstItem() {
        action.moveToElement(itemsOnMain).build().perform();
        return this;
    }

    @Step("Добавляем товар с главной страницы в корзину")
    public MainPage addToCart() {
        selectFirstItem().addToCartOnMain.click();
        return this;
    }

    @Step("Подтверждаем добавление товара в корзину на открывшемся попапе")
    public MainPage confirmAddingToCart() {
        confirmAddToCartOnMain.click();
        return this;
    }

    @Step("Проверяем, действительно ли мы попали на главную страницу")
    public String isMain() {
        return isMainCheck.getText();
    }

    @Step("Переходим на страницу с женской одеждой")
    public MainPage womanPageRedirect() {
        womenHeader.click();
        return this;
    }

    @Step("Проверяем действительно ли мы перешли на страницу с женской одеждой")
    public String isWoman() {
        return isWomanCheck.getText();
    }

    @Step("Переходим в корзину")
    public MainPage cartRedirect() {
        cart.click();
        return this;
    }

    public MainPage dressesPageRedirect() {
        dressesHeader.click();
        return this;
    }

    public MainPage tshortsPageRedirect() {
        tshortsHeader.click();
        return this;
    }
}
