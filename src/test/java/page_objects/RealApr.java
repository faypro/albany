package page_objects;

import comand_providers.ActOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RealApr extends NavigationBar {
    private static final Logger LOGGER = LogManager.getLogger(RealApr.class);
    private final By CalculatorTab = By.xpath("//label[.='Calculator']");
    private final By HomeValue = By.name("HomeValue");
    private final By DownPayment = By.name("DownPayment");
    private final By DownPaymentInDollar = By.id("DownPaymentSel0");
    private final By InterestRate = By.name("Interest");
    private final By CalculateButton = By.name("calculate");
    private final By ActualAprValue = By.xpath("//strong[text()='3.130%']");

    public RealApr(WebDriver driver) {
        super(driver);
    }

    public RealApr waitForPageToLoad() {
        LOGGER.debug(" Wait For the Real Apr Page To be Loaded");
        ActOn.wait(driver, CalculatorTab).waitForElementToBeVisible();
        return this;
    }
    public RealApr typeHomeValue(String value) {
        LOGGER.debug(" Entered Home Value is " + value);
        ActOn.element(driver, HomeValue).setValue(value);
        return this;
    }
    public RealApr typeDownPayment(String value) {
        LOGGER.debug(" Entered Down Payment is " + value);
        ActOn.element(driver,DownPayment).setValue(value);
        return this;
    }
    public RealApr clickOnDownPaymentInDollar() {
        LOGGER.debug(" Click On Down Payment In Dollar");
        ActOn.element(driver, DownPaymentInDollar).click();
        return this;
    }
    public RealApr typeInterestRate(String value) {
        LOGGER.debug(" Entered Interest Rate is " + value);
        ActOn.element(driver,InterestRate).setValue(value);
        return this;
    }
    public RealApr clickOnCalculateButton(){
        LOGGER.debug(" click On the Calculate Button");
        ActOn.element(driver,CalculateButton).click();
        return this;
    }
    public RealApr validatingRealAprRates(String expectedValue) {
        String aprRate = ActOn.element(driver, ActualAprValue).getTextValue();
        LOGGER.debug(" validating the total RealApr Rates"+ expectedValue);
        Assert.assertEquals(aprRate,expectedValue, " Actual Value is not matching with the Expected Value");
        return this;
    }
}