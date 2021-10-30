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
import utilities.ReadConfigFiles;

public class CalculateRates {
    WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(CalculateRates.class);

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
    @Test
    public void calculateRealApr() {
        new Home(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageToLoad()
                .typeHomeValue("200000")
                .clickOnDownPaymentInDollar()
                .typeDownPayment("15000")
                .typeInterestRate("3")
                .clickOnCalculateButton()
                .validatingRealAprRates("3.130%");
    }
    @AfterMethod
    public void closingBrowser () {
        ActOn.browser(driver).closeBrowser();
            LOGGER.info(" Browser is Closed");
        LOGGER.info(" ===== End Test (CalculateRates)=====");
        }
}
