package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import folder.BaseTest;
import pages.MainPage;

public class FirstStart extends BaseTest {

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
    public void openWomanPageFromMain(){
        String actualMsg = new MainPage(driver)
                .goToMain()
                .womanPageRedirect()
                .isWoman()
                .toUpperCase();
        Assert.assertEquals(actualMsg, mainPage.expTitleOfWomanPage);
    }

}
