package ui.tests;

import org.testng.annotations.Test;
import ui.pages.cars.BuyOrSellCarPage;
import ui.pages.cars.CreateNewCarsPage;
import ui.pages.cars.ReadAllCarsPage;
import ui.pages.houses.CreateNewHousesPage;
import ui.pages.houses.ReadOneByIDPage;
import ui.pages.houses.SettleOrEvictUserPage;
import ui.pages.login.LoginPage;
import ui.pages.users.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import utils.AllureUtils;
import org.testng.asserts.SoftAssert;
import utils.PropertyReader;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    public SoftAssert softAssert;

    BuyOrSellCarPage buyOrSellCarPage;
    CreateNewCarsPage createNewCarsPage;
    ReadAllCarsPage readAllCarsPage;
    CreateNewHousesPage createNewHousesPage;
    ReadOneByIDPage readOneByIDPage;
    SettleOrEvictUserPage settleOrEvictUserPage;
    AddMoneyPage addMoneyPage;
    BuyOrSellCarUsersPage buyOrSellCarUsersPage;
    CreateNewUsersPage createNewUsersPage;
    IssueALoanPage issueALoanPage;
    ReadUserWithCarsPage readUserWithCarsPage;
    public ReadAllUsersPage readAllUsersPage;
    SettleToHousePage settleToHousePage;
    protected LoginPage loginPage;
    //public  String user = System.getProperty("user");
    //public String password = System.getProperty("password");
    public  String user = System.getProperty("user", PropertyReader.getProperty("user"));
    public String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
           // options.addArguments("--headless");
            options.setCapability("unhandledPromptBehavior", "accept");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
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
        issueALoanPage = new IssueALoanPage(driver);
        readAllUsersPage = new ReadAllUsersPage(driver);
        readUserWithCarsPage = new ReadUserWithCarsPage(driver);
        settleToHousePage = new SettleToHousePage(driver);
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            AllureUtils.takeScreenshot(driver);
        }
        driver.quit();
    }
}
