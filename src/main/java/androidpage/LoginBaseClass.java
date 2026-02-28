package androidpage;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginBaseClass {

    protected AndroidDriver driver; // Use WebElement instead of MobileElement

    @BeforeClass
    public void setUp() {
        System.out.println("==== Starting Setup ====");

        try {
            System.out.println("Step 1: Setting capabilities...");
            DesiredCapabilities caps = new DesiredCapabilities();
            
            
            caps.setCapability("platformName", "Android");
            caps.setCapability("appium:deviceName", "3c3b8179");
            caps.setCapability("appium:automationName", "UiAutomator2");
            caps.setCapability("appium:appPackage", "com.mantra.praveshsaas");
            caps.setCapability("appium:appActivity", "com.mantra.praveshsaas.MainActivity");

            // Prevent Appium from clearing app data
            caps.setCapability("appium:noReset", true);
            caps.setCapability("appium:fullReset", false);
            caps.setCapability("appium:ensureWebviewsHavePages", true);
            caps.setCapability("appium:waitForIdleTimeout", 10000);

            System.out.println("Step 2: Initializing AndroidDriver...");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps); // Add /wd/hub
            System.out.println("Step 3: AndroidDriver initialized successfully.");

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException occurred: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception occurred during driver initialization: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("==== Setup Complete ====");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("==== Starting TearDown ====");
        if (driver != null) {
            driver.quit();
            System.out.println("Driver quit successfully.");
        } else {
            System.out.println("Driver was null, nothing to quit.");
        }
        System.out.println("==== TearDown Complete ====");
    }
}
