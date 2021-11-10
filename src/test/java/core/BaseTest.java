package core;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

@Listeners(TestListener.class)
public class BaseTest {
    private static Logger LOG = LogManager.getLogger(BaseTest.class.getName());
    protected EventFiringWebDriver driver;
    private final StringBuffer verificationErrors = new StringBuffer();
    protected Properties properties;

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) throws Exception {
        initProperties();

        String edgeDriver = properties.getProperty("edge.driver");
        String geckoDriver = properties.getProperty("gecko.driver");
        String chromeDriver = properties.getProperty("driver.chrome");
        String screenDir = properties.getProperty("screen.dir");

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", chromeDriver);
                driver = new EventFiringWebDriver(new ChromeDriver());

                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", geckoDriver);
                driver = new EventFiringWebDriver(new FirefoxDriver());

                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", edgeDriver);
                driver = new EventFiringWebDriver(new EdgeDriver());
                break;
            case "safari":
                driver = new EventFiringWebDriver(new SafariDriver());
                break;
            default:
                throw new Exception("Unknown browser, please run tests in chrome, edge, firefox or safari");
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.register(new WebDriverEventListenerImpl(screenDir));
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    @BeforeMethod
    public void testStart(Method method, Object[] params) {
        LOG.info("test {} starts with params: {}",
                method.getName(), Arrays.toString(params));
    }

    @AfterMethod
    public void testComplete(Method method) {
        LOG.info("test {} finished.", method.getName());
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return driver.getScreenshotAs(OutputType.BYTES);
    }

    private void initProperties() throws IOException {
        // Ищем путь к конфигам по ключу
        String path = System.getProperty("cfg");
        InputStream is;
        if (path == null) // иначе берем конфиги из рессурсов
            is = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream("demo.properties");
        else
            is = new FileInputStream(path);
        properties = new Properties();
        properties.load(is);
    }
}

