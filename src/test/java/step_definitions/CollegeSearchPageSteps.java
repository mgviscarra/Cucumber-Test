package step_definitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.BrowserManager;

import javax.annotation.Nullable;

/**
 * Created by Mauricio on 15/9/2017.
 */
public class CollegeSearchPageSteps {
    WebDriver driver = BrowserManager.getDriverInstance();
    int explicitWaiter = Integer.parseInt(System.getProperty("explicitWaiter"));

    public CollegeSearchPageSteps(){
        driver.switchTo().frame(0);
    }
    @When("^I Select Best Fit: \"([^\"]*)\"")
    public void select_best_fit_option(String bestFitOption) throws InterruptedException {
        final WebElement bestFitButton = (new WebDriverWait(driver,explicitWaiter))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='"+bestFitOption+"']/parent::*")));
        //Since sometimes selenium clicks on the "best fit" buttons while they are being expanded o collapsed
        //and the click does not take effect, I created a custom waiter to click on the element until it is expanded totaly
        //if it is not expanded after X time, will fail
        WebDriverWait waiter = new WebDriverWait(driver,explicitWaiter);
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(@Nullable WebDriver webDriver) {
                bestFitButton.click();
                return bestFitButton.getAttribute("aria-expanded").equals("true");
            }
        };
        try{
            waiter.until(expectation);
        }
        catch (TimeoutException e){
            throw  new AssertionError("The Option was not expanded after: "+explicitWaiter+ "minutes");
        }
        new WebDriverWait(driver, explicitWaiter).until(ExpectedConditions.attributeContains(By.xpath("//a[text()='"+bestFitOption+"']/parent::*"),"aria-expanded","true"));
    }

    /**
     * This method selects a given option in the "Location" list box
     * @param locationOption, string with the option to be selected
     *
     */
    @When("^I Select Location: \"([^\"]*)\"")
    public void select_location_label(String locationOption){
        //Since the location div has scroll and when we click to them sometimes the actions is not done
        //because they are not visible, doing by javascript could be a solution.
        WebElement locationLabel = (new WebDriverWait(driver,explicitWaiter))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+locationOption+"']")));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()",locationLabel);
    }

    /**
     * Selects a radio button with the given degree in the Majors area
     * @param degree, string with the degree to be selected
     */
    @When("^I Select degree: \"([^\"]*)\" radio button$")
    public void select_degree_radio_button(String degree){
        WebElement radioButton = (new WebDriverWait(driver,explicitWaiter))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=\""+degree+"" +"\"]/preceding-sibling::input")));
        radioButton.click();
    }

    /**
     * Sets the major text box with a given text
     * @param text, the value to set the major text box
     */
    @When("^I set Major textbox with: \"([^\"]*)\"$")
    public void set_major_textbox(String text){
        WebElement majorTextBox = (new WebDriverWait(driver,explicitWaiter))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("majorSearchText")));
        majorTextBox.sendKeys(text);
    }

    /**
     * Clicks and select the given major in the "major" list
     * @param major, and string with the option to be selected
     */
    @When("^I add Major: \"([^\"]*)\"$")
    public void add_major(String major){
        WebElement majorLabel = (new WebDriverWait(driver,explicitWaiter))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@id='matchingMajors']//span[text()=' "+major+"']")));
        majorLabel.click();
    }

    /**
     * This method verifies that a given university is listed after filtering
     * @param university, an string with the university to be verified
     */
    @Then("^\"([^\"]*)\" university should be listed$")
    public void university_should_be_listed(String university){
        try{
            WebElement majorLabel = (new WebDriverWait(driver,explicitWaiter))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[text()='"+university+"']")));
        }
        catch (AssertionFailedError e){
            throw new AssertionFailedError("The university: "+university+" has not been listed!");
        }
    }

    /**
     * Verifies the percentaje of a given university
     * @param university, string with the university to check the percentaje
     * @param percentaje, percentaje to be verified
     */
    @Then("^Matching percentaje for university: \"([^\"]*)\" should be: \"([^\"]*)\"$")
    public void matching_percentaje_should_be(String university, String percentaje){
        WebElement majorLabel = (new WebDriverWait(driver,explicitWaiter))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(".//a[text()='"+university+"']/ancestor::div[@class='resultItemPanelContent']//div[@class='percentMatch']")));
        String actualPercentaje = majorLabel.getAttribute("innerText");
        Assert.assertEquals("The percentajes are not the same: actual: "
                +actualPercentaje+", expected: "+percentaje,percentaje,actualPercentaje);
    }
}
