package page_objects;

import comand_providers.ActOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBar {
    private static final Logger LOGGER = LogManager.getLogger(NavigationBar.class);
    private final By MortgageCalculatorLogo = By.xpath("//img[@alt='Logo']");
    private final By RateLink = By.linkText("Rates");
    private final By RealAprLink = By.linkText("Real APR");

    public WebDriver driver;

    public NavigationBar(WebDriver driver) {
        this.driver = driver;

    }

    public NavigationBar mouseHoverToRates() {
        LOGGER.debug(" Mouse Hover to the Rates Links");
        ActOn.element(driver, RateLink).mouseHover();
        return this;
    }

    public RealApr navigateToRealApr() {
        LOGGER.debug(" Navigate to the Real Apr Link");
        ActOn.element(driver,RealAprLink).click();
        return new RealApr(driver);
    }

    public Home navigateToHome () {
        LOGGER.debug(" Navigate to the Mortgage Calculator Logo");
        ActOn.element(driver, MortgageCalculatorLogo).click();
        return new Home(driver);
    }

}
