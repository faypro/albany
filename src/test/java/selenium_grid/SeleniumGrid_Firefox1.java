package selenium_grid;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.TestEnvironment;
import java.net.MalformedURLException;

public class SeleniumGrid_Firefox1 {
    WebDriver driver;
    @Test
    public void executeInAwsDocker() throws MalformedURLException {
        driver = TestEnvironment.selectTestExecutionEnvironment();
        driver.get("https://www.qateksolutions.com/");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
