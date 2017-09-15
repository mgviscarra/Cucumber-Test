package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by Mauricio on 14/9/2017.
 */
public class BrowserManager {
    private static WebDriver browserInstance = null ;

    /**
     *
     * @return
     */
    public static WebDriver getDriverInstance(){
        System.getProperty("webdriver.gecko.driver");
        String browser = System.getProperty("browser");
        if(browserInstance == null){
            if (browser.equals("firefox")) {
                browserInstance = new FirefoxDriver();
                return browserInstance;

            } else if (browser.equals("chrome")) {
                browserInstance = new ChromeDriver();
                return browserInstance;

            } else if (browser.equals("msie")) {
                browserInstance = new InternetExplorerDriver();
                return browserInstance;

            } else{
                throw new RuntimeException("The given browser could not be found: "+browser);
            }
        }
        return browserInstance;
    }

}
