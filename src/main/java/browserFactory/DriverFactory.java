package browserFactory;/*
 * @created 11/03/2025 - 2:24 PM
 * @author Dinu
 */

import org.openqa.selenium.WebDriver;

public class DriverFactory {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public void initDriver(String browser, String env, boolean headless, String url){
        BrowserManager browserManager = switch (browser.toLowerCase()){
            case "chrome" -> new ChromeDriverManager();
            case "firefox" -> new FirefoxDriverManager();
            case "edge" -> new EdgeDriverManager();
            default -> throw new IllegalArgumentException("Unexpected value: "+browser.toLowerCase());
        };
        tlDriver.set(browserManager.createDriver(env, headless));
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(url);
    }

    public static synchronized WebDriver getDriver(){
        return tlDriver.get();
    }

}
