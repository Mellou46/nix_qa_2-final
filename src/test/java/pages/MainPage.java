package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    public String expTitleOfWomanPage = "WOMEN";
    public String expTitleOfMainPage = "POPULAR";
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
    Actions action = new Actions(driver);

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage goToMain() {
        driver.get("http://automationpractice.com/");
        return this;
    }

    public MainPage selectFirstItem() {
        action.moveToElement(itemsOnMain).click().build().perform();
        return this;
    }

    public MainPage addToCart() {
        selectFirstItem().addToCartOnMain.click();
        return this;
    }

    public MainPage confirmAddingToCart() {
        confirmAddToCartOnMain.click();
        return this;
    }

    public String isMain() {
        return isMainCheck.getText();
    }

    public MainPage womanPageRedirect() {
        womenHeader.click();
        return this;
    }

    public String isWoman() {
        return isWomanCheck.getText();
    }

    public MainPage dressesPageRedirect() {
        dressesHeader.click();
        return this;
    }

    public MainPage tshortsPageRedirect() {
        tshortsHeader.click();
        return this;
    }

    public MainPage cartRedirect() {
        cart.click();
        return this;
    }
}
