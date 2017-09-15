package step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.BrowserManager;


/**
 * Created by Mauricio on 14/9/2017.
 */
public class MainPageSteps {
    WebDriver driver;
    int explicitWaiter = Integer.parseInt(System.getProperty("explicitWaiter"));

    /**
     * Navigates to the app page
     */
    @Given("^I navigate to College View web page$")
    public void navigate_to_college_view_page(){
        this.driver = BrowserManager.getDriverInstance();
        driver.get("http://www.collegeview.com/index.jsp");
    }

    /**
     * Clicks on the Start searching button of the main page
     */
    @When("^I Select the Start Searching button$")
    public void select_the_start_searching_button(){
        WebElement searchingButton = (new WebDriverWait(driver,explicitWaiter))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/a/img[@src='/_img/start_searching.jpg']")));
        searchingButton.click();

    }
}
