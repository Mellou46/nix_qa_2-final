package tests;

import core.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginPageTests extends BaseTest {

    @Description("Тест логин формы")
    @Issues({@Issue("1456"), @Issue("1234")})
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Test(dataProvider = "loginErrorData")
    public void loginErrorsTest(String username, String passw, String expectedMsg) {
        String actualMsg = new LoginPage(driver)
                .goToLogIn()
                .fillEmail(username)
                .fillPassword(passw)
                .submit()
                .getErrorMessage();
        Assert.assertEquals(actualMsg, expectedMsg);
    }

    @DataProvider(name = "loginErrorData")
    public Object[][] loginErrorDataProvider() throws IOException {
        String path = properties.getProperty("login.data");
        Scanner scanner = new Scanner(new FileInputStream(path), StandardCharsets.UTF_8);
        List<String> lines = new ArrayList<>();
        // чтение файла с данными построчно
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            System.out.println(nextLine);
            lines.add(nextLine);
        }
        scanner.close();
        Object[][] data = new Object[lines.size()][3];

        for (int i = 0; i < lines.size(); i++) {
            String[] lineParts = lines.get(i).split(",");

            data[i][0] = lineParts[0];
            data[i][1] = lineParts[1];
            data[i][2] = lineParts[2];
        }
        return data;
    }
}
