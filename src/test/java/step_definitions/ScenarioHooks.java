package step_definitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import support.BrowserManager;

/**
 * Created by Mauricio Viscarra on 9/15/2017.
 */
public class ScenarioHooks
{
    WebDriver driver;
    @Before
    public void beforeScenario(){
        driver = BrowserManager.getDriverInstance();
        //This method has some issues in some FF versions, so commenting for now
        //driver.manage().window().maximize();
    }
    @After
    public void afterScenario(){
        driver.close();
    }
}
