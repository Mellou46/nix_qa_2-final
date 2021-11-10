package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;

public class MainPageTests extends BaseTest {

    MainPage mainPage = new MainPage(driver);

    @Test
    public void openMainPageTest() {
        String actualMsg = new MainPage(driver)
                .goToMain()
                .isMain()
                .toUpperCase();
        Assert.assertEquals(actualMsg, mainPage.expTitleOfMainPage);
    }

    @Test
    public void openWomanPageFromMain() {
        String actualMsg = new MainPage(driver)
                .goToMain()
                .womanPageRedirect()
                .isWoman()
                .toUpperCase();
        Assert.assertEquals(actualMsg, mainPage.expTitleOfWomanPage);
    }
}