package page_objects;

import comand_providers.ActOn;
import comand_providers.AssertThat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends NavigationBar{
    private static final Logger LOGGER = LogManager.getLogger(Home.class);
        private final By HomeValue = By.id("homeval");
        private final By DownPayment = By.id("downpayment");
        private final By DownPaymentInDollar = By.xpath("//input[contains(@value,'money')]");
        private final By LoanAmount = By.id("loanamt");
        private final By InterestRate = By.id("intrstsrate");
        private final By LoanTerm = By.id("loanterm");
        private final By StartDateMonth = By.xpath("//select[@name='param[start_month]']");
        private final By StartDateYear = By.id("start_year");
        private final By PropertyTax = By.id("pptytax");
        private final By Pmi = By.id("pmi");
        private final By Hoi = By.id("hoi");
        private final By Hoa = By.id("hoa");
        private final By LoanType = By.name("param[milserve]");
        private final By BuyOrRefi = By.name("param[refiorbuy]");
        private final By Calculate = By.name("cal");

        public Home (WebDriver driver){
            super(driver);
        }

        public Home typeHomePrice(String value) {
            LOGGER.debug(" Entered Home Price is " + value);
            ActOn.element(driver, HomeValue).setValue(value);
            return this;
        }
        public Home typeDownPayment (String value) {
            ActOn.wait(driver, DownPayment).waitForElementToBeVisible();
            LOGGER.debug(" Entered Down Payment is " + value);
            ActOn.element(driver, DownPayment).setValue(value);
                return this;
            }
            public Home clickDownPaymentInDollar () {
            LOGGER.debug(" Click in the Down Payment In Dollar");
            ActOn.element(driver, DownPaymentInDollar).click();
            return this;
        }
        public Home typeLoanAmount (String value) {
            LOGGER.debug(" Entered Loan Amount is " + value);
             ActOn.element(driver, LoanAmount).setValue(value);
             return this;
        }
        public Home typeInterestRate(String value) {
            LOGGER.debug(" Entered Interest Rate is " + value);
            ActOn.element(driver, InterestRate).setValue("3");
            return this;
        }
        public Home typeLoanTermInYear(String value) {
            LOGGER.debug(" Entered Loan Term is " + value);
            ActOn.element(driver, LoanTerm).setValue(value);
            return this;
        }
        public Home selectMonth(String month) {
            LOGGER.debug(" Select Start Month is " + month);
            ActOn.element(driver, StartDateMonth).selectValue(month);
            return this;
        }
        public Home selectYear(String year) {
            LOGGER.debug(" Select Start Year is " + year);
            ActOn.element(driver, StartDateYear).setValue(year);
            return this;
        }

        public Home typePropertyTax (String value) {
            LOGGER.debug(" Entered Property Tax is " + value);
            ActOn.element(driver, PropertyTax).setValue(value);
            return this;
        }

        public Home typePmi(String value) {
            LOGGER.debug(" Entered Pmi is " + value);
            ActOn.element(driver, Pmi).setValue(value);
            return this;
        }
        public Home typeHoi(String value) {
            LOGGER.debug(" Entered Home Owner Insurance is " + value);
            ActOn.element(driver, Hoi).setValue(value);
            return this;
        }
        public Home typeHoa(String value) {
            LOGGER.debug(" Entered Hoa is " + value);
            ActOn.element(driver, Hoa).setValue(value);
            return this;
        }
        public Home selectLoanType(String value) {
            LOGGER.debug(" Entered Loan Type is " + value);
            ActOn.element(driver, LoanType).selectValue(value);
            return this;
        }
        public Home selectBuyOrRefi(String value) {
            LOGGER.debug(" Selected from BuyOrRefi is" + value);
            ActOn.element(driver, BuyOrRefi).selectValue(value);
            return this;
        }
        public Home clickOnCalculateButton() {
            LOGGER.debug(" click On Calculate Button");
            ActOn.element(driver, Calculate).click();
            return this;
        }
        public Home validateMonthlyPayment (String totalMonthlyPayment) {
            LOGGER.debug(" Validating the total Monthly payment" + totalMonthlyPayment);
            By monthlyPaymentLocator =By.xpath( "//h3[contains(.,'"+ totalMonthlyPayment +"')]");
            AssertThat.elementAssertions(driver, monthlyPaymentLocator).elementExist();
            return this;
        }
}
