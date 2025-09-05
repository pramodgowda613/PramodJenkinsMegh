package base;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Utils;

/**
 * Tapan Gandhi 12/12/2021
 */
public class LoadDriver {
	WebDriver driver, driver1, driver2;
	DesiredCapabilities capabilities = new DesiredCapabilities(), capabilities1 = new DesiredCapabilities();

	public WebDriver getDriver(int device) {
		URI uri = null;
		URL serverUrl;
		try {
			if (initBase.deviceNo == 0) {
				initBase.deviceNo = device;
			}
			int nrow = device - 1;
			System.out.println("Launching Driver...");
			if (device >= 1 && device <= 20) {
				try {
					Thread.sleep(2000);
				} catch (Exception e1) {
				}
				capabilities.setCapability("newCommandTimeout", 600); // 10 minutes
				capabilities.setCapability("adbExecTimeout", 10000); // 60 seconds
				capabilities.setCapability("platformName", initBase.arrDevices[nrow][0]);
				capabilities.setCapability("deviceName", initBase.arrDevices[nrow][1]);
				capabilities.setCapability("udid", initBase.arrDevices[nrow][1]);
				capabilities.setCapability("appPackage", initBase.arrDevices[nrow][2]);
				capabilities.setCapability("appActivity", initBase.arrDevices[nrow][3]);
				capabilities.setCapability("platformVersion", initBase.arrDevices[nrow][4]);
				capabilities.setCapability("autoGrantPermissions", initBase.arrDevices[nrow][5]);
				capabilities.setCapability("automationName", initBase.arrDevices[nrow][6]);
				try {
					uri = new URI(
							"http://" + initBase.arrDevices[nrow][7] + ":" + initBase.arrDevices[nrow][8] + "/wd/hub");
					serverUrl = uri.toURL();
					UnlockDevice(initBase.arrDevices[nrow][1]);
					driver = new AndroidDriver(serverUrl, capabilities);
					// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				} catch (Exception e) {
					System.out.println("Issue loading driver, waiting for 10 seconds..:" + e.getMessage());
				}
				return driver;
			} else if (device >= 21 && device <= 30) {
				launchBrowser();
				return driver;
			} else if (device == 69) {
				launchInBrowserStack();
				return driver;
			}
		} catch (Exception e) {
			System.out.println(
					"Issue loading driver, waiting for 10 seconds..:" + e.getMessage().toString() + " ,URL " + uri);
		}
		return null;
	}

	private void UnlockDevice(String deviceid) {
		List<String> command = new ArrayList<>();
		String adbPath = System.getProperty("user.home") + "\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb.exe";
		command.add(adbPath);
		command.add("-s");
		command.add(deviceid);
		command.add("shell");
		command.add("input");
		command.add("keyevent");
		command.add("224");
		String result = Utils.runCommandPrompt(command, "");

	}

	public static void lockDevice() {
		// Feb 25 Tapan this is added to avoid getting into Device related commands.
		int k = initBase.workbook.getSheetIndex("device");
		if (k > 0) {
			String adbPath = System.getProperty("user.home")
					+ "\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb.exe";
			List<String> command = new ArrayList<>();
			command.add(adbPath);
			command.add("-s");
			command.add(initBase.arrDevices[0][1]); // Currently fixed to first as multiple devices can be done later
			command.add("shell");
			command.add("input");
			command.add("keyevent");
			command.add("26");
			String result = Utils.runCommandPrompt(command, "");
		}
	}

	/*
	 * Launch browser based on configuration
	 */
	void launchBrowser() {
		try {
			if (initBase.browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", initBase.userHome + "geckodriver.exe");
//				driver = new FirefoxDriver();
			} else if (initBase.browser.equals("chrome")) {
				try {
					WebDriverManager.chromedriver().setup(); // Tapan June 25
				} catch (Exception e) {
					System.err.println("WebDriverManager failed to download driver: " + e.getMessage());
				}
				// System.setProperty("webdriver.chrome.driver", initBase.userHome +
				// "chromedriver.exe");
				String driverPath = System.getProperty("webdriver.chrome.driver");
				ChromeDriverService service = new ChromeDriverService.Builder()
						.usingDriverExecutable(new File(driverPath)).withVerbose(true)
						.withLogFile(new File("chromedriver.log")).build();

				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("autofill.profile_enabled", false);
				prefs.put("autofill.credit_card_enabled", false);
				prefs.put("safebrowsing.enabled", false);
				options.setExperimentalOption("prefs", prefs);
				// Optional: Disable infobars and automation extension
				options.setPageLoadStrategy(PageLoadStrategy.NONE); // Don’t wait for full page load
				options.addArguments("--start-maximized", "disable-translate", "--disable-infobars",
						"--disable-notifications", "--disable-save-password-bubble",
						"--disable-password-manager-reauthentication", "--ignore-certificate-errors",
						"--allow-insecure-localhost", "--window-size=1920,1080",
						"--disable-features=PasswordCheck,AutofillServerCommunication,AutofillEnableAccountWallet");
				options.setAcceptInsecureCerts(true);
				if (initBase.pvtBrowser.equalsIgnoreCase("Y")) {
					options.addArguments("--incognito");
				}
				if (initBase.hideBrowser.equalsIgnoreCase("Y")) {
					options.addArguments("--headless"); // Hide the browser
				}
				if (initBase.launchChromeNewProf.equalsIgnoreCase("Y")) {
					String chromeProfilePath = System.getProperty("java.io.tmpdir") + "/chrome_" + UUID.randomUUID();
					options.addArguments("--user-data-dir=" + chromeProfilePath);
				}
				if (initBase.remoteIP.length() > 5) { // Remote execution in Chrome.
					try {
						// Replace this with your actual hub IP and port
						URL gridUrl = new URL(initBase.remoteIP);
						driver = new RemoteWebDriver(gridUrl, options);
					} catch (Exception e) {
						System.out.println("Error while starting RemoteWebDriver: " + e.getMessage());
						return; // Exit if the remote setup fails
					}
				} else {
					driver = new ChromeDriver(service, options);
					Dimension size = driver.manage().window().getSize();
					System.out.println(driver.manage().window().getSize());
				}
			} else {
				System.setProperty("webdriver.ie.driver", initBase.userHome + "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(initBase.implicitlyWait)));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(initBase.pageLoadTimeout)));
			driver.navigate().to(initBase.URL);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		} catch (Exception e) {
			System.out.println("Critical - Issue loading driver..:" + e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * Tapan July 25
	 * 
	 * @return
	 */
	boolean launchInBrowserStack() {
		String USERNAME = initBase.browserstackuser;
		String ACCESS_KEY = initBase.browserstackkey;
		String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("browserVersion", "latest");
		Map<String, Object> browserstackOptions = new HashMap<>();
		browserstackOptions.put("os", "Windows");
		browserstackOptions.put("osVersion", "10");
		browserstackOptions.put("sessionName", initBase.reportName);
		browserstackOptions.put("resolution", "1920x1080");
		caps.setCapability("bstack:options", browserstackOptions);

		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
		} catch (Exception e) {
			System.out.println("Error during launch for BrowserStack: " + e.getMessage());
			return false;
		}
		return true;
	}

}// END Loaddriver
