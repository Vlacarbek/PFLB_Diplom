package UI.tests;

import UI.Pages.Cars.BuyOrSellCarPage;
import UI.Pages.Cars.CreateNewCarsPage;
import UI.Pages.Cars.ReadAllCarsPage;
import UI.Pages.Houses.CreateNewHousesPage;
import UI.Pages.Houses.ReadOneByIDPage;
import UI.Pages.Houses.SettleOrEvictUserPage;
import UI.Pages.Users.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import utils.AllureUtils;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;

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
    ReadAllUsersPage readAllUsersPage;
    ReadUserWithCarsPage readUserWithCarsPage;
    SettleToHousePage settleToHousePage;

    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--headless");
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

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            AllureUtils.takeScreenshot(driver);
        }
        driver.quit();
    }
}
