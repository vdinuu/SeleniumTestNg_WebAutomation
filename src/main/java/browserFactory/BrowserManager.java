package browserFactory;/*
 * @created 11/03/2025 - 2:04 PM
 * @author Dinu
 * Manages the creation of WebDriver instances for different browser types.
 * This interface defines the contract for browser initialization and configuration
 * in the test automation framework.
 */

import org.openqa.selenium.WebDriver;

public interface BrowserManager {

    WebDriver createDriver(String env, boolean headless);
}
