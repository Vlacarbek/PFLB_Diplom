package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.asserts.SoftAssert;
import pages.cars.BuyOrSellCarPage;
import pages.cars.CreateNewCarsPage;
import pages.cars.ReadAllCarsPage;
import pages.houses.CreateNewHousesPage;
import pages.houses.ReadAllHousesPage;
import pages.houses.ReadOneByIDPage;
import pages.houses.SettleOrEvictUserPage;
import pages.login.LoginPage;
import pages.users.*;
import utils.AllureUtils;
import utils.PropertyReader;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;

    public SoftAssert softAssert;
    public BuyOrSellCarPage buyOrSellCarPage;
    public CreateNewCarsPage createNewCarsPage;
    public ReadAllCarsPage readAllCarsPage;
    public CreateNewHousesPage createNewHousesPage;
    public ReadOneByIDPage readOneByIDPage;
    public SettleOrEvictUserPage settleOrEvictUserPage;
    public pages.users.AddMoneyPage addMoneyPage;
    public BuyOrSellCarUsersPage buyOrSellCarUsersPage;
    public pages.users.CreateNewUsersPage createNewUsersPage;
    public pages.users.ReadUserWithCarsPage readUserWithCarsPage;
    public ReadAllUsersPage readAllUsersPage;
    public ReadAllHousesPage readAllHousesPage;
    public LoginPage loginPage;

    public static String user = System.getProperty("user", PropertyReader.getProperty("user"));
    public static String password = System.getProperty("password", PropertyReader.getProperty("password"));
    public static WebDriverWait wait;

    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--headless");
            options.setCapability("unhandledPromptBehavior", "accept");
            driver = new ChromeDriver(options);
            //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS).scriptTimeout(Duration.ofSeconds(10));
            int timeoutInSeconds = 10;
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        } else if (browser.equalsIgnoreCase("internetexplorer")) {
            driver = new InternetExplorerDriver();
        }

        buyOrSellCarPage = new BuyOrSellCarPage(driver);
        createNewCarsPage = new CreateNewCarsPage(driver);
        readAllCarsPage = new ReadAllCarsPage(driver);
        createNewHousesPage = new CreateNewHousesPage(driver);
        readOneByIDPage = new ReadOneByIDPage(driver);
        settleOrEvictUserPage = new SettleOrEvictUserPage(driver);
        addMoneyPage = new AddMoneyPage(driver);
        buyOrSellCarUsersPage = new BuyOrSellCarUsersPage(driver);
        createNewUsersPage = new CreateNewUsersPage(driver);
        readAllUsersPage = new ReadAllUsersPage(driver);
        readUserWithCarsPage = new ReadUserWithCarsPage(driver);
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
        readAllHousesPage = new ReadAllHousesPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            AllureUtils.takeScreenshot(driver);
        }
        if (driver != null) {
            driver.quit();
        }
        }
    }
