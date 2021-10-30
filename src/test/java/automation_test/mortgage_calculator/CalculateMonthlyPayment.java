package automation_test.mortgage_calculator;

import comand_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.Home;
import utilities.DataUtils;
import utilities.ReadConfigFiles;

public class CalculateMonthlyPayment {
    private static final Logger LOGGER = LogManager.getLogger(CalculateMonthlyPayment.class);
    WebDriver driver;

    @BeforeMethod
    public void OpenBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info(" ---------Start Test (CalculateMonthlyPayment)---------");
        String url = ReadConfigFiles.getPropertyValues("MortgageCalculatorURL");
        ActOn.browser(driver).openBrowser(url);
        //or: ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("MortgageCalculatorURL"));
        LOGGER.info(" Browser is successfully Initiated with the URL:"+ url);
    }

    @Test
    public void calculateMonthlyPayment() {
        String Date = DataUtils.returnNextMonth();
        String[] dateArray = Date.split("-");
        String month = dateArray[0];
        String year = dateArray[1];
        new Home(driver)
                .typeHomePrice("300000")
                .typeDownPayment("60000")
                .clickDownPaymentInDollar()
                .typeLoanAmount("240000")
                .typeInterestRate("3")
                .typeLoanTermInYear("30")
                .selectMonth(month)
                .selectYear(year)
                .typePropertyTax("5000")
                .typePmi("0.5")
                .typeHoi("1000")
                .typeHoa("100")
                .selectLoanType("FHA")
                .selectBuyOrRefi("Buy")
                .clickOnCalculateButton()
                .validateMonthlyPayment("1,611.85");

    }

    @AfterMethod
    public void testCleanUp () {
        ActOn.browser(driver).closeBrowser();
        LOGGER.info(" Browser is Closed");
        LOGGER.info(" ===== End Test (CalculateMonthlyPayment)=====");
       }
}

