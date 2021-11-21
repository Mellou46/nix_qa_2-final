package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WebDriverEventListenerImpl implements WebDriverEventListener {
    private static Logger LOG = LogManager.getLogger(WebDriverEventListenerImpl.class.getName());

    private String screenshotDir;

    public WebDriverEventListenerImpl(String screenshotDir) {
        this.screenshotDir = screenshotDir;
    }

    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        LOG.info("go to url {}", url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        LOG.debug("try find element  by '{}'", by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        String tagName = "";

        if (element != null)
            tagName = element.getTagName();

        LOG.debug("Found element <{}>  by '{}'", tagName, by);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        LOG.error("Error. Details: {}", throwable.getMessage());
        makeScreenshot(driver);
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {

    }

    private void makeScreenshot(WebDriver driver) {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenName = "screen_" + System.currentTimeMillis() + ".png";
        String screenPath = screenshotDir + "/" + screenName;
        try {
            Files.copy(new FileInputStream(tmp), Path.of(screenPath));
        } catch (IOException exc) {
            LOG.error("Error copying screenshot from '{}' to '{}'. Details: {}",
                    tmp, screenPath, exc);
        }
    }
}
