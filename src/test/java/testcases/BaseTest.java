package testcases;/*
 * @created 11/03/2025 - 2:40 PM
 * @author Dinu
 */

import browserFactory.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import static browserFactory.DriverFactory.getDriver;

public class BaseTest {

    public static Properties prop;

    public DriverFactory driverFactory;
    private ThreadLocal<SoftAssert> threadLocal = new ThreadLocal();

    public BaseTest(){
        prop = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, String browser){
        driverFactory = new DriverFactory();
        driverFactory.initDriver(browser, prop.getProperty("executionEnv"),
                Boolean.getBoolean("headless"), prop.getProperty("url"));
        SoftAssert softAssert = new SoftAssert();
        threadLocal.set(softAssert);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if(null != getDriver()){
            getDriver().quit();
        }
        getSoftAssert().assertAll();
    }

    public SoftAssert getSoftAssert(){
        return threadLocal.get();
    }
}
