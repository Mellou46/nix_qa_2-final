package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[@id='SubmitLogin']")
    private WebElement submitButton;
    @FindBy(xpath = "//div[@id='center_column']/div/ol/li")
    private WebElement errorMessage;

    public LoginPage goToLogIn() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        return this;
    }

    @Step("Вводим email {0}")
    public LoginPage fillEmail(String email) {
        fillTextField(emailField, email);
        return this;
    }

    @Step("Вводим пароль {0}")
    public LoginPage fillPassword(String password) {
        fillTextField(passwordField, password);
        return this;
    }

    @Step("Нажимаем submit")
    public LoginPage submit() {
        submitButton.click();
        return this;
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
