package automation_test.mortgage_calculator_parameterized;

import comand_providers.ActOn;
import data_providers.RealAprDataProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.Home;
import utilities.ReadConfigFiles;

public class CalculateRates_Parameterized {
    WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(CalculateRates_Parameterized.class);

    @BeforeMethod
    public void browserInitialization () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info(" ---------Start Test (CalculateRates)---------");
        String url = ReadConfigFiles.getPropertyValues("MortgageCalculatorURL");
        ActOn.browser(driver).openBrowser(url);
        //or: ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("MortgageCalculatorURL"));
        LOGGER.info(" Browser is successfully Initiated with the URL:" + url);
    }
    @Test (dataProvider = "RealAprRates", dataProviderClass = RealAprDataProvider.class)
    public void calculateRealApr(String homePrice, String downPayment, String interestRate, String aprRate) {
        new Home(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageToLoad()
                .typeHomeValue(homePrice)
                .clickOnDownPaymentInDollar()
                .typeDownPayment(downPayment)
                .typeInterestRate(interestRate)
                .clickOnCalculateButton()
                .validatingRealAprRates(aprRate);
    }
    @AfterMethod
    public void closingBrowser () {
        ActOn.browser(driver).closeBrowser();
        LOGGER.info(" Browser is Closed");
        LOGGER.info(" ===== End Test (CalculateRates)=====");
    }
}
