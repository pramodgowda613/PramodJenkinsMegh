package base;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//import io.qameta.allure.Allure;
//import io.qameta.allure.Step;
import utils.GlobalSetup;
import utils.Utils;

// ✅ Log4j2 imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.Assert;

/**
 * Tapan Gandhi 12/12/2021
 */
public class LogResults {
	// HTML Reports ExtentX variables
	private ExtentSparkReporter extentHtmlReporter;
	private ExtentReports extentReports;
	private ExtentTest extentTest;

	// Replaced Java Logger with Log4j2
	private Logger logger = LogManager.getLogger(LogResults.class);

	// Pass fail related variables
	private Integer testMethodErrorCount = 0;
	
	private String scenarioName, lsnrStatus = "";
	// Webdriver
	private WebDriver driver;
	private String moduleName = getTestMethodName(3);
	private long startTime = System.nanoTime();
	private double totTestTime = 0.0;
	private Integer deviceExec = 0;
	private Integer ptotalPass = 0;
	private Integer ptotalFail = 0;
	private String browser = "";
	private String repTime = "";
	 private Map<String, String> localPublicMap;//Tapan oct 25
	 private String suiteName="";
	 
	public void createExtentReport(String currTC) {
		try {
			extentTest = extentReports.createTest(currTC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Step("{message}")
//	public void logWithAllure(String message) {
//		Allure.step(message);
//	}

	public void createReport(int device) {
		this.localPublicMap = initBase.allmapPubVar;  
		this.suiteName=localPublicMap.get("suiteName"); 
		repTime = (GlobalSetup.getReportTime());
		deviceExec = device;
		totTestTime = 0.0;
		StackTraceElement[] ar = Thread.currentThread().getStackTrace();
		StackTraceElement parentElement = ar[2];
		String strT1 = parentElement.toString();
		strT1 = strT1.replace("(", "-");
		String arrT2 = strT1.split("-")[1];
		arrT2 = arrT2.replace(".", "-");
		arrT2 = arrT2.split("-")[0];
		try {
			String rep = arrT2;
			if (device >= 1 && device <= 20) {
				browser = initBase.arrDevices[device - 1][10] + "_" + device + "_";
			} else {
				browser = initBase.browser;
			}
			String strjsonFdr = initBase.reportFld + repTime + "-"+ suiteName + "\\json\\" + browser + rep + ".json";
			String strhtmlFdr = initBase.reportFld + repTime + "-"+ suiteName + "\\" + browser + rep + ".html";

			File reportDir = new File(initBase.reportFld + repTime + "-"+ suiteName);
			initBase.screenShotFolder = initBase.reportFld + repTime + "-"+ suiteName + "\\screenshot";
			if (!reportDir.exists()) {
				reportDir.mkdirs();
			}
			reportDir = new File(initBase.reportFld + repTime + "-"+ suiteName + "\\json");
			if (!reportDir.exists()) {
				reportDir.mkdirs();
				File screeshtFdr = new File(initBase.screenShotFolder);
				screeshtFdr.mkdirs();
				if (initBase.jenRerun.equalsIgnoreCase("NotRunTC")) {
					callReRunMagic();
				}
			}

			extentReports = new ExtentReports();
			extentReports.createDomainFromJsonArchive(strjsonFdr);
			extentHtmlReporter = new ExtentSparkReporter(strhtmlFdr);
			JsonFormatter json = new JsonFormatter(strjsonFdr);
			extentReports.attachReporter(json, extentHtmlReporter);
			extentReports.setSystemInfo("Environment", initBase.jenEnv);
			extentReports.setSystemInfo("URL", initBase.URL);
			extentReports.setSystemInfo("Send Email", initBase.jenSendEmail);
			extentHtmlReporter.config().setDocumentTitle(initBase.reportName);
			extentHtmlReporter.config().setReportName(initBase.reportName + "-" + browser + "-" + initBase.testenv);
			extentHtmlReporter.config().setTheme(Theme.STANDARD);

			// ✅ Setup thread-safe Log4j2 log file
			String logFileName = "MeghPIreports/RunID-" + repTime + "-"+ suiteName + "/" + browser + rep + ".txt";
			ThreadContext.clearAll();
			ThreadContext.put("logFileName", logFileName);

			logger.info("Initialized Log4j2 for test: {}", logFileName);

		} catch (Exception e) {
			System.out.println("No Logs found to Generate Report.");
			onlyLog();
			System.exit(1);
		}
	}

	public void createLogs(String lgMessage, String passFail, String message) {
		String strMetNam = getMethodName(3);
		String newMessage = "";
		try {
			newMessage = message;
			if (newMessage.length() > 600) {
				newMessage = newMessage.substring(0, 600);
			}
			Status st = null;
			if (passFail.contains("PASS")) {
				st = Status.PASS;
			} else if (passFail.contains("FAIL")) {
				st = Status.FAIL;
				if (lsnrStatus.isEmpty()) {
					lsnrStatus = "FAIL";
					ITestResult result = Reporter.getCurrentTestResult();
					result.setStatus(ITestResult.FAILURE);
				}
			} else {
				st = Status.INFO;
			}
			String strModuleString;
			if (initBase.bParallel) {
				strModuleString = moduleName + deviceExec;
			} else {
				strModuleString = moduleName;
			}
			newMessage = newMessage.replace(",", " ");
			strModuleString = strModuleString + "," + strMetNam + "," + newMessage + "," + st + ","
					+ Utils.retDateTime();
			
			initBase.mapMethodResults.put(Utils.retRandomString(10), strModuleString);
			
			if ("Y".equalsIgnoreCase(lgMessage)) {
				takeScreenShot(st, newMessage);
			} else {
				if (st.toString().equalsIgnoreCase("PASS")) {
					extentTest.pass(newMessage);
				} else if (st.toString().equalsIgnoreCase("INFO")) {
					extentTest.info(newMessage);
				} else {
					extentTest.fail(newMessage);
				}
			}

//			logWithAllure(newMessage);

			// ✅ Log4j2 replacement
			if (passFail.contains("FAIL"))
				logger.error("{} - {}", passFail, message);
			else if (passFail.contains("PASS"))
				logger.info("{} - {}", passFail, message);
			else
				logger.debug("{} - {}", passFail, message);

			if (passFail.contains("FAIL")) {
				testMethodErrorCount++;
				if (testMethodErrorCount >= initBase.SkipCount) {
					logger.info("Skipping rest steps as error count reached configured value: {}",
							testMethodErrorCount);
					Assert.fail();
				}
			}
		} catch (Exception e) {
			Assert.fail("Error in logging function: " + e.getMessage());
		}
	}

	private String getMethodName(int pos) {
		StackTraceElement[] ar = Thread.currentThread().getStackTrace();
		StackTraceElement parentElement = ar[pos];
		String arrT2 = "";
		try {
			String strT1 = parentElement.toString();
			strT1 = strT1.replace("(", "-");
			arrT2 = strT1.split("\\.")[2];
			arrT2 = arrT2.replace(".", "-");
			arrT2 = arrT2.split("-")[0];
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
		return arrT2;
	}

	private String getTestMethodName(int pos) {
		StackTraceElement[] ar = Thread.currentThread().getStackTrace();
		StackTraceElement parentElement = ar[pos];
		String arrT2 = "";
		try {
			String strT1 = parentElement.toString();
			strT1 = strT1.replace("(", "#-#");
			arrT2 = strT1.split("#-#")[1];
			arrT2 = arrT2.split("\\.")[0];
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
		return arrT2;
	}

	public void cleanUp() {
//		ThreadContext.clearAll();
	}

	// This function will copy the previous executed TC_Executed.csv to current
	// folder Aug 25
	private void callReRunMagic() {
		String sFile = "data\\TS_Executed.properties";
		sFile = Utils.getConfigValue(sFile, GlobalSetup.getSuitName());
		File sourceFile = new File(sFile + "\\TC_Executed.csv");
		if (sourceFile.exists()) {
			try {
				Path sourcePath = sourceFile.toPath();
				Path destPath = Paths.get(initBase.reportFld + GlobalSetup.getReportTime() + "-"+suiteName, "TC_Executed.csv");

				// ensure destination folder exists
				destPath.getParent().toFile().mkdirs();

				// copy file (overwrite if already exists)
				Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);

				System.out.println("Copied TC_Executed.csv into " + sFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No previous TC_Executed.csv found at " + sourceFile.getAbsolutePath());
		}
	}

	public Integer getTestMethodErrorCount() {
		return this.testMethodErrorCount;
	}

	public void setDriver(WebDriver driver) {
		startTime = System.nanoTime();// System.currentTimeMillis(); // This will track individual TC or test time
		this.driver = driver;
	}

	public void mergeReports() {
		try {
			this.suiteName=initBase.allmapPubVar.get("suiteName");
			repTime = (GlobalSetup.getReportTime());
			String strjsonFdr = initBase.reportFld + repTime + "-"+ suiteName + "\\json\\";
			String strHTMLfdr = initBase.reportFld + repTime + "-"+ suiteName + "\\";
			File jsonOPDirectory = new File(strjsonFdr);
			if (jsonOPDirectory.exists()) {
				ExtentSparkReporter mergedSpark = new ExtentSparkReporter(strHTMLfdr + "AllReports.html");
				ExtentReports extentMerged = new ExtentReports();
				FilenameFilter jsonFilter = new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						String lowerName = name.toLowerCase();
						return lowerName.endsWith(".json");
					}
				};
				File[] files = jsonOPDirectory.listFiles(jsonFilter);
				if (files != null) {
					for (File file : files) {
						if (file.isFile()) {
							try {
								extentMerged.createDomainFromJsonArchive(file.getPath());
								file.delete();
							} catch (Exception e) {
								System.err.println("Error processing file: " + file.getAbsolutePath());
							}
						}
					}
				}
				jsonOPDirectory.delete();
				extentMerged.attachReporter(mergedSpark);
				extentMerged.flush();
			}
			if (initBase.reportDash.equalsIgnoreCase("N")) {
				Files.deleteIfExists(Paths.get(strHTMLfdr + "AllReports.html"));
			}
		} catch (Exception e) {
			System.out.println("No Logs found to Generate Report.");
		}

	}

	public void generateSummaryReport() {
		if (initBase.reportDash.equalsIgnoreCase("N")) {
			return;
		}
		this.suiteName=initBase.allmapPubVar.get("suiteName");
		int iPass = 0, iFail = 0, iTotal = 0;
		String ftime = "";

		try {
			repTime = (GlobalSetup.getReportTime());
			ExtentSparkReporter spark = new ExtentSparkReporter(initBase.reportFld + repTime + "-"+ suiteName + "\\" + "Dashboard.html");
			extentReports = new ExtentReports();
			extentReports.attachReporter(spark);
			ExtentTest dashboard = extentReports.createTest("Module Summary");
			extentReports.setSystemInfo("Environment", initBase.testenv);
			spark.config().setDocumentTitle("Dashboard Summary Report");
			int srNo = 0;
			List<String> myList = new ArrayList<>();

			String modName = "";
			String[] allValues;
			// Create the table header
			StringBuilder table = new StringBuilder();
			table.append("<table border='1' style='width:100%'>");
			table.append("<tr>");
			table.append("<th>Sr</th>");
			table.append("<th>Module Name</th>");
			table.append("<th>Pass</th>");
			table.append("<th>Fail</th>");
			table.append("<th>Total</th>");
			table.append("<th>Time</th>");
			table.append("</tr>");
			// Iterate through the map and write down the contents
			for (Map.Entry<String, String> entry : initBase.mapModuleResults.entrySet()) {
				allValues = entry.getValue().split(",");
				modName = entry.getKey();
				srNo = srNo + 1;
				table.append("<tr>");
				table.append("<td>").append("" + srNo).append("</td>");
				table.append("<td>").append(modName).append("</td>");
				table.append("<td>").append(allValues[0]).append("</td>");
				table.append("<td>").append(allValues[1]).append("</td>");
				table.append("<td>").append(allValues[2]).append("</td>");
				table.append("<td>").append(allValues[3]).append("</td>");
				table.append("</tr>");
				iPass = iPass + Integer.parseInt(allValues[0]);
				iFail = iFail + Integer.parseInt(allValues[1]);
				iTotal = iTotal + Integer.parseInt(allValues[2]);
				myList.add(allValues[3]);
			}
			ftime = Utils.retFormattedHHMMSS(myList);
			table.append("<tr style='font-weight: bold; background-color: #f0f0f0;'>");
			table.append("<td colspan='2'>").append("Total").append("</td>");
			table.append("<td>").append(iPass).append("</td>");
			table.append("<td>").append(iFail).append("</td>");
			table.append("<td>").append(iTotal).append("</td>");
			table.append("<td>").append(ftime).append("</td>");
			table.append("</tr>");

			// Close the table tag
			table.append("</table>");
			// Add the table to the ExtentReport
			dashboard.info(table.toString());
			extentReports.flush();
			// Now add the test cases summary
			dashboard = extentReports.createTest("Test Cases Summary");
			srNo = 0;
			modName = "";
			String prvModName = "", prvTestCaseName = "";
			// Create the table header
			table = new StringBuilder();
			table.append("<table border='1' style='width:100%'>");
			table.append("<tr>");
			table.append("<th>Sr</th>");
			table.append("<th>Module</th>");
			table.append("<th>Test Case</th>");
			table.append("<th>Step</th>");
			table.append("<th>Status</th>");
			table.append("<th>Date</th>");
			table.append("</tr>");
			// Iterate through the map and write down the contents
			if (initBase.bParallel) {
				srtMapmethodResults();
			}
			for (Map.Entry<String, String> entry : initBase.mapMethodResults.entrySet()) {
				allValues = entry.getValue().split(",");
				table.append("<tr>");
				modName = allValues[0];
				if (srNo == 0) {
					prvModName = modName;
					prvTestCaseName = allValues[1];
					srNo = 1;
					table.append("<td>").append("" + srNo).append("</td>");
					table.append("<td>").append(modName).append("</td>");
					table.append("<td>").append(allValues[1]).append("</td>");
					table.append("<td>").append(allValues[2]).append("</td>");
					table.append("<td>").append(allValues[3]).append("</td>");
					table.append("<td>").append(allValues[4]).append("</td>");
					table.append("</tr>");
					continue;
				}
				if (prvModName.equalsIgnoreCase(modName) != true) {
					srNo = srNo + 1;
					prvModName = modName;// Nov 24
					modName = allValues[0];
					prvTestCaseName = allValues[1];
					table.append("<td>").append("" + srNo).append("</td>");
					table.append("<td>").append(modName).append("</td>");
					table.append("<td>").append(allValues[1]).append("</td>");
					table.append("<td>").append(allValues[2]).append("</td>");
					table.append("<td>").append(allValues[3]).append("</td>");
					table.append("<td>").append(allValues[4]).append("</td>");
					table.append("</tr>");
					continue;
				} else {
					table.append("<td>").append("").append("</td>");
					table.append("<td>").append("").append("</td>");
				}
				if (prvTestCaseName.equalsIgnoreCase(allValues[1])) {
					table.append("<td>").append("").append("</td>");
				} else {
					prvTestCaseName = allValues[1];
					table.append("<td>").append(allValues[1]).append("</td>");
				}
				table.append("<td>").append(allValues[2]).append("</td>");
				table.append("<td>").append(allValues[3]).append("</td>");
				table.append("<td>").append(allValues[4]).append("</td>");
				table.append("</tr>");
			}
			// Close the table tag
			table.append("</table>");
			// Add the table to the ExtentReport
			dashboard.info(table.toString());
			extentReports.flush();
		} catch (Exception e) {
			System.out.println("Error In Dashboard reports : " + e.getMessage().toString());
		}
	}

	private void srtMapmethodResults() {
		// mapMethodResults
		try {
			LinkedHashMap<String, String> tempResults = new LinkedHashMap<String, String>(initBase.mapMethodResults);
			initBase.mapMethodResults.clear();

			for (int j = 1; j <= 10; j++) { // Max 4 mobiles at present
				for (Map.Entry<String, String> entry : tempResults.entrySet()) {
					String sValue = entry.getValue();
					String sMethNam = sValue.split(",")[0];
					String sDevice = sMethNam.charAt(sMethNam.length() - 1) + "";
					if (sDevice.equalsIgnoreCase("" + j)) {
						initBase.mapMethodResults.put(entry.getKey(), sValue);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public void generateAllureReport() {
		this.suiteName=localPublicMap.get("suiteName");
		Path fullPath = Utils.getAbsolutePath(initBase.currDir, initBase.reportFld + repTime + "-"+ suiteName, "allrep");
		String allureResultsDirectory = System.getProperty("allure.results.directory");
		String allureReportDirectory = fullPath.toString();

		fullPath = Utils.getAbsolutePath(initBase.currDir, "config.yml");
		List<String> command = new ArrayList<>();
		command.add("C:\\Users\\admin\\scoop\\apps\\allure\\current\\bin\\allure.bat");
		command.add("generate");
		command.add(allureResultsDirectory);
		command.add("--clean");
		command.add("-o");
		command.add(allureReportDirectory);
		command.add("--config");
		command.add(fullPath.toString());
		System.out.println(allureReportDirectory + command.toString());
		Utils.runCommandPrompt(command, "");
	}

	public void onlyLog() {
		extentReports.flush();
		lsnrStatus = "";
		String scenarioName = getScenarioName();
		Status st;
		if (scenarioName == null || scenarioName.length() < 1) {
			return;
		}
		String passFail = "Passed";
		if (getTestMethodErrorCount() > 0) {
			initBase.totalFail++;
			ptotalFail++;
			initBase.incrementFail();
			passFail = "Failed";
		} else {
			initBase.totalPass++;
			ptotalPass++;
			initBase.incrementPass();
		}
		if ("Passed".equalsIgnoreCase(passFail)) {
			st = Status.PASS;
		} else {
			st = Status.FAIL;
		}

		logger.info(passFail + " - " + moduleName + "-" + extentTest.getModel().getName() + "-" + getScenarioName());
		Integer runcnt = initBase.getPassCount() + initBase.getFailCount();
		System.out.println("---------------------------------------------------------------");
		System.out.println(moduleName + extentTest.getModel().getName());
		logger.info("Total Pass : " + initBase.getPassCount() + " Total Fail : " + initBase.getFailCount()
				+ "  Total TC : " + runcnt);
		System.out.println("---------------------------------------------------------------");
		extentTest.log(st, getScenarioName() + " :- " + passFail);
		setTestMethodErrorCount(0);
		scenarioName = "";
		extentReports.flush();
		long endTime = System.nanoTime();
		double elapsedSeconds = (endTime - startTime) / 1_000_000_000.0;
		totTestTime += elapsedSeconds;
		String strEndTime = Utils.formatSecondsToHHMMSS(totTestTime);
		System.out.printf("Test case took: %.4f seconds%n", elapsedSeconds);
		System.out.println("Total TC Time: " + strEndTime);
		startTime = System.nanoTime();
		initBase.mapModuleResults.put(moduleName + "-" + initBase.browser,
				ptotalPass + "," + ptotalFail + "," + (ptotalPass + ptotalFail) + "," + strEndTime + ","
						+ initBase.fmtDate + "," + browser + "," + initBase.testenv);
		if (deviceExec > 20) { // Show only for Browsers.
			try {
				String url = driver.getCurrentUrl();
				Utils.showToastMessage(driver, "Total Pass : " + initBase.getPassCount() + " Total Fail : "
						+ initBase.getFailCount() + "  Total TC : " + runcnt, "info");
			} catch (Exception e) {
				System.out.println("Toast error : " + e.getMessage());
			}
		}
//		generateAllureReport();
		cleanUp();
	}

	public String getScenarioName() {
		return scenarioName;
	}

	public void setTestMethodErrorCount(Integer testMethodErrorCount) {
		this.testMethodErrorCount = testMethodErrorCount;
	}

	public void takeScreenShot(Status st, String message) {
		String fileName = new SimpleDateFormat("dd-MM-yyyy-HHmmss-SSS").format(new Date());
		String screenshotPath = initBase.currDir + initBase.screenShotFolder + File.separator + fileName + ".png";

		try {
			if ("Y".equalsIgnoreCase(initBase.screenShot) && driver != null) {
				boolean isAlertPresent = false;

				// Check if alert is present
				try {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
					wait.until(ExpectedConditions.alertIsPresent());
					isAlertPresent = true;
					System.out.println("Alert detected. Using desktop screenshot.");
				} catch (Exception ignored) {
				}

				if ("FAIL".equalsIgnoreCase(st.toString()) || "Y".equalsIgnoreCase(initBase.screenShot)) {
					if (isAlertPresent) {
						// Capture desktop screenshot if alert is blocking the DOM
						try {
							if ("N".equalsIgnoreCase(initBase.hideBrowser)) {
								Robot robot = new Robot();
								Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
								BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
								ImageIO.write(screenFullImage, "png", new File(screenshotPath));
							} else {
								System.out.println("Desktop screenshot not supported when browser is hidden!");
							}
						} catch (Exception ex) {
							System.out.println("Failed to capture desktop screenshot: " + ex.getMessage());
						}
					} else {
						// Capture browser screenshot (safer way for parallel runs)
						try {
							byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
							Files.createDirectories(Paths.get(initBase.currDir, initBase.screenShotFolder));
							Files.write(Paths.get(screenshotPath), screenshotBytes);
						} catch (Exception ex) {
							System.out.println("Failed to capture browser screenshot: " + ex.getMessage());
						}
					}

					// Attach link in report
					message = message + " <a href='screenshot/" + fileName + ".png' target='_blank'>ScreenShot</a>";
				}
			}

			// Log to Extent Report
			if ("PASS".equalsIgnoreCase(st.toString())) {
				extentTest.pass(message);
			} else if ("INFO".equalsIgnoreCase(st.toString())) {
				extentTest.info(message);
			} else {
				extentTest.fail(message);
			}

		} catch (Exception e) {
			System.out.println("Screenshot not captured: " + e.getMessage());
		}
	}
}// EOF