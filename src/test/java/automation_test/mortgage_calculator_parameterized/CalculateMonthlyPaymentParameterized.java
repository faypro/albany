package automation_test.mortgage_calculator_parameterized;

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
import utilities.SqlConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculateMonthlyPaymentParameterized {
    private static final Logger LOGGER = LogManager.getLogger(CalculateMonthlyPaymentParameterized.class);
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
        ResultSet rs = SqlConnector.readData("Select * from monthly_mortgage");
        try {
            while (rs.next()) {
                LOGGER.info("******* Start of " + rs.getRow() + " Iteration *******");
                new Home(driver)
                        .typeHomePrice(rs.getString("homevalue"))
                        .typeDownPayment(rs.getString("downpayment"))
                        .clickDownPaymentInDollar()
                        .typeLoanAmount(rs.getString("loanamount"))
                        .typeInterestRate(rs.getString("interestrate"))
                        .typeLoanTermInYear(rs.getString("loanterm"))
                        .selectMonth(month)
                        .selectYear(year)
                        .typePropertyTax(rs.getString("propertytax"))
                        .typePmi(rs.getString("pmi"))
                        .typeHoi(rs.getString("homeownerinsurance"))
                        .typeHoa(rs.getString("monthlyhoa"))
                        .selectLoanType(rs.getString("loantype"))
                        .selectBuyOrRefi(rs.getString("buyorrefi"))
                        .clickOnCalculateButton()
                        .validateMonthlyPayment(rs.getString("totalmonthlypayment"));
            }
        } catch (SQLException e) {
            LOGGER.error(" SQL Query Error : " + e.getMessage());
        }
    }

    @AfterMethod
    public void testCleanUp () {

        ActOn.browser(driver).closeBrowser();
        LOGGER.info(" Browser is Closed");
        LOGGER.info(" ===== End Test (CalculateMonthlyPayment)=====");
    }
}

