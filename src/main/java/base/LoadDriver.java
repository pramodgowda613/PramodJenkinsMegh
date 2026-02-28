package base;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.GlobalSetup;
import utils.Utils;

/**
 * Tapan Gandhi 12/12/2021
 */
public class LoadDriver {
	WebDriver driver, driver1, driver2;
	DesiredCapabilities capabilities = new DesiredCapabilities(), capabilities1 = new DesiredCapabilities();
	private String hubNodeIP = "";
	private String suiteName = "";
	private Map<String, String> localPublicMap;// Tapan oct 25
	private Integer deviceExec = 0;

	public WebDriver getDriver(int device, String hubnodeIP) {
		hubNodeIP = hubnodeIP;
		return getDriver(device);
	}

	public WebDriver getDriver(int device) {
		deviceExec=device;
		this.localPublicMap = initBase.allmapPubVar;
		this.suiteName = localPublicMap.get("suiteName");
		URI uri = null;
		URL serverUrl;
		try {
			if (initBase.deviceNo == 0) {
				initBase.deviceNo = device;
			}
			int nrow = device - 1;
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
//			lockDevice();
			System.out.println("Launching Driver... " + dtf.format(now));
			if (device >= 1 && device <= 20) {
				try {
					Thread.sleep(2000);
				} catch (Exception e1) {
				}

				capabilities.setCapability("appium:newCommandTimeout", 600); // 10 minutes
				capabilities.setCapability("appium:adbExecTimeout", 10000); // 10 seconds
				// Core platform and device details
				capabilities.setCapability("platformName", initBase.arrDevices[nrow][0]);
				capabilities.setCapability("appium:deviceName", initBase.arrDevices[nrow][1]);
				capabilities.setCapability("appium:udid", initBase.arrDevices[nrow][1]);
				capabilities.setCapability("appium:platformVersion", initBase.arrDevices[nrow][4]);
				// Target app
				capabilities.setCapability("appium:appPackage", initBase.arrDevices[nrow][2]);
				capabilities.setCapability("appium:appActivity", initBase.arrDevices[nrow][3]);
				// Automation & permissions
				capabilities.setCapability("appium:automationName", initBase.arrDevices[nrow][6]);
				// 🚀 Force always fresh launch
				capabilities.setCapability("appium:noReset", true);
				capabilities.setCapability("appium:fullReset", false);
				capabilities.setCapability("appium:dontStopAppOnReset", false);
				// (Optional but helpful for reliability)
				capabilities.setCapability("appium:ensureWebviewsHavePages", true);
				capabilities.setCapability("appium:appWaitForLaunch", true);
				capabilities.setCapability("appium:appWaitActivity", "*");
				capabilities.setCapability("appium:appWaitForLaunch", true);

				try {
					uri = new URI(
							"http://" + initBase.arrDevices[nrow][7] + ":" + initBase.arrDevices[nrow][8] + "/");
					serverUrl = uri.toURL();
					UnlockDevice(initBase.arrDevices[nrow][1]);
					//Kill App before launching
//					killApp(initBase.arrDevices[nrow][1]);
					killApp(device);
					driver = new AndroidDriver(serverUrl, capabilities);
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

	/*
	 * private void killApp(String deviceId) { String adbPath =
	 * System.getProperty("user.home") +
	 * "\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb.exe";
	 * 
	 * List<String> command = new ArrayList<>(); command.add(adbPath);
	 * command.add("-s"); command.add(initBase.arrDevices[deviceExec][1]);
	 * command.add("shell"); command.add("am"); command.add("force-stop");
	 * command.add(initBase.arrDevices[deviceExec][2]); String result =
	 * Utils.runCommandPrompt(command, ""); }
	 */
	
	private void killApp(int deviceId) {
	    String adbPath = System.getProperty("user.home") + "\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb.exe";
	    String udid = initBase.arrDevices[deviceId - 1][1];
	    String appPackage = initBase.arrDevices[deviceId - 1][2];

	    try {
	        System.out.println("🧹 Clearing and killing app: " + appPackage + " on device " + udid);

	        // Step 1: Clear app data (guaranteed to stop all services)
	        Utils.runCommandPrompt(Arrays.asList(
	                adbPath, "-s", udid, "shell", "pm", "clear", appPackage
	        ), "");

	        // Step 2: Force-stop the app to ensure it’s completely killed
	        Utils.runCommandPrompt(Arrays.asList(
	                adbPath, "-s", udid, "shell", "am", "force-stop", appPackage
	        ), "");

	        // Optional: Verify PID (debugging/logging purpose)
	        String pid = Utils.runCommandPrompt(Arrays.asList(
	                adbPath, "-s", udid, "shell", "pidof", appPackage
	        ), "");

	        if (pid != null && !pid.trim().isEmpty()) {
	            System.out.println("⚠️ App may still be running. PID: " + pid.trim());
	        } else {
	            System.out.println("✅ App stopped successfully.");
	        }

	        // Step 3: Give OS time to settle
	        Thread.sleep(1500);

	    } catch (Exception e) {
	        System.err.println("❌ Failed to kill app: " + e.getMessage());
	    }
	}



	void lockDevice() {
		// Feb 25 Tapan this is added to avoid getting into Device related commands.
		int k = initBase.workbook.getSheetIndex("device");
		if (k > 0) {
			String adbPath = System.getProperty("user.home")
					+ "\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb.exe";
			List<String> command = new ArrayList<>();
			command.add(adbPath);
			command.add("-s");
			command.add(initBase.arrDevices[deviceExec][1]); // Currently fixed to first as multiple devices can be done
																// later
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
		if (driver != null) {
			driver.quit();
			driver = null;
		}
		try {
			if (initBase.browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", initBase.userHome + "geckodriver.exe");
				driver = new FirefoxDriver();
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
				options.addArguments("--disable-dev-shm-usage"); // fix /dev/shm issues in docker/linux
				options.addArguments("--no-sandbox"); // required in some restricted environments
				options.addArguments("--disable-gpu");
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
					int i = 0;
					String temp = "";
					for (i = 0; i < 3; i++) {
						URL gridUrl = null;
						try {
							gridUrl = new URL((hubNodeIP.length() > 2) ? hubNodeIP : initBase.remoteIP);
							Thread.sleep(3000);
							System.out.println("Starting Remote WebDriver on " + gridUrl.toString());
							driver = new RemoteWebDriver(gridUrl, options);
							((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
							driver.getCurrentUrl();
							break;
						} catch (Exception e) {
							temp = e.getMessage().toLowerCase();
//							if (temp.contains("could not start a new session")) {
//								// Kill HUB+NODE & RESTART Tapan Oct 25
//								System.out.println("CRITICAL - Killing HUB/Node and restarting..");
//								Utils.killHUBNODE(gridUrl.toString());
//								boolean ready = Utils.isHubNodeReady(gridUrl.toString(), 30);
//								System.out.println("The HUB/Node is ready? --> " + ready);
//							}
							if (driver != null) {
								try {
									driver.quit();
								} catch (Exception ex) {
								}
							}
							driver = null;
							Thread.sleep(3000);
						}
						if (i > 3) {
							System.out.println("Critical- RemoteWebDriver: " + temp);
							System.exit(0);
						}
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
			if (initBase.reportFld.contains("Megh")) {
				System.out.println("Report Folder : http://192.168.6.201:8002/RunID-" + GlobalSetup.getReportTime()
						+ "-" + suiteName);
			} else if (initBase.reportFld.contains("Minop")) {
				System.out.println("Report Folder : http://192.168.6.201:8001/RunID-" + GlobalSetup.getReportTime()
						+ "-" + suiteName);
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(initBase.implicitlyWait)));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(initBase.pageLoadTimeout)));
			driver.navigate().to(initBase.URL);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		} catch (

		Exception e) {
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
