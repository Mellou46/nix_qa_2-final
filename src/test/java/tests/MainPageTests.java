package tests;

import core.BaseTest;
import io.qameta.allure.Attachment;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.MainPage;

public class MainPageTests extends BaseTest {

    MainPage mainPage;
    @BeforeClass
    public void pageCreation(){
        mainPage = new MainPage(driver);
    }

    @Test
    public void openMainPageTest() {
        String actualMsg = mainPage
                .goToMain()
                .isMain()
                .toUpperCase();
        Assert.assertEquals(actualMsg, mainPage.expTitleOfMainPage);
    }

    @Test @Attachment
    public void openWomanPageFromMain() {
        String actualMsg = mainPage
                .goToMain()
                .womanPageRedirect()
                .isWoman()
                .toUpperCase();
        Assert.assertEquals(actualMsg, mainPage.expTitleOfWomanPage);
    }
}
