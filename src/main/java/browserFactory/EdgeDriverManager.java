package browserFactory;/*
 * EdgeDriverManager implements the BrowserManager interface to handle Edge WebDriver
 * initialization and configuration. This class is responsible for creating and configuring
 * Edge browser instances for both local and remote execution environments.

 * @created 11/03/2025 - 2:22 PM
 * @author Dinu
 */

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Constants;

import java.net.MalformedURLException;
import java.net.URL;

public class EdgeDriverManager implements BrowserManager{
    @Override
    public WebDriver createDriver(String env, boolean headless) {
        WebDriver driver= null;
        EdgeOptions edgeOptions = new EdgeOptions();
        if (headless) {
            edgeOptions.addArguments("--headless=new");
        }
        if (env.equals("local")) {
            driver = new EdgeDriver(edgeOptions);
        } else if (env.equals("remote")) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setPlatform(Platform.WIN11);
            desiredCapabilities.setBrowserName("MicrosoftEdge");
            edgeOptions.merge(desiredCapabilities);
            try {
                driver = new RemoteWebDriver(new URL(Constants.HUB_URL), desiredCapabilities);
            } catch (MalformedURLException ignored) {
            }
        }
        return driver;
    }
}
