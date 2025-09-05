package base;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.GlobalSetup;
import utils.Utils;

/**
 * Tapan Gandhi 12/12/2021
 */
public class LogResults {
	// HTML Reports
	private ExtentSparkReporter extentHtmlReporter;
	private ExtentReports extentReports;
	private ExtentTest extentTest;
	//
	// Java Logger
	private FileHandler handler;
	private Logger logger;
	private SimpleFormatter formatter = new SimpleFormatter();
	// Pass fail related variables
	private Integer testMethodErrorCount = 0;
	private String scenarioName;
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

	/**
	 * @param currTC
	 */
	public void createExtentReport(String currTC) {
		try {
			extentTest = extentReports.createTest(currTC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will create the reports using extent for the end user The
	 * reports contains rich look and complete execution status
	 */
	public void createReport(int device) {
		repTime = (GlobalSetup.getReportTime());
		deviceExec = device;
		totTestTime = 0.0; // Do not play around with this variable holds total test execution time.
		StackTraceElement[] ar = Thread.currentThread().getStackTrace();
		StackTraceElement parentElement = ar[2];
		String strT1 = parentElement.toString();
		strT1 = strT1.replace("(", "-");
		String arrT2 = strT1.split("-")[1];
		arrT2 = arrT2.replace(".", "-");
		arrT2 = arrT2.split("-")[0];
		try {
			// String[] arrep = this.getClass().getName().toString().split("\\.");
			String rep = arrT2; // arrep[arrep.length - 1];
			if (device >= 1 && device <= 20) {
				browser = initBase.arrDevices[device - 1][10] + "_" + device + "_";
			} else {
				browser = initBase.browser;
			}
			String strjsonFdr = initBase.reportFld + repTime + "\\json\\" + browser + rep + ".json";
			String strhtmlFdr = initBase.reportFld + repTime + "\\" + browser + rep + ".html";
			File reportDir = new File(initBase.reportFld + repTime);
			initBase.screenShotFolder = initBase.reportFld + repTime + "\\screenshot";
			if (reportDir.exists() != true) {
				reportDir.mkdirs();
				reportDir = new File(initBase.reportFld + repTime + "\\json");
				reportDir.mkdirs();
				File screeshtFdr = new File(initBase.screenShotFolder);
				screeshtFdr.mkdirs();
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
			handler = new FileHandler(initBase.reportFld + repTime + "\\" + browser + rep + "-Logs.txt", true);
			handler.setFormatter(formatter);
			logger = Logger.getLogger(initBase.class.getName());
			logger.addHandler(handler);
			logger.setLevel(Level.INFO);
			extentReports.flush();
		} catch (Exception e) {
			System.out.println("No Logs found to Generate Report.");
			onlyLog();
			System.exit(1);
		}
	}

	/**
	 * @param passFail -
	 * @param message  This function will log status in the Execution report and
	 *                 also log into the log files simultaneously for analyze
	 */
	public void createLogs(String lgMessage, String passFail, String message) throws SkipException {
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
			if (lgMessage.equals("Y")) {
				takeScreenShot(st, newMessage);
			}
			logger.info(passFail + " - " + message);
			if (passFail.contains("FAIL")) {
				testMethodErrorCount++;
				if (testMethodErrorCount >= initBase.SkipCount) { // Skip remaining steps if the error counts in the
					logger.info("FAIL - Skipping rest steps as error count reached configured value : "
							+ testMethodErrorCount);
					if (initBase.SkipCount > 1) {
						extentTest.info("Skipping rest steps as error count reached configured value : "
								+ testMethodErrorCount);
					}
					throw new SkipException("Skip Steps"); // reaches the global configured count
				}
			}
		} catch (Exception e) {
			throw new SkipException("Skip Steps");
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
			String strT1 = parentElement.toString();// Tapan 23rd July
			strT1 = strT1.replace("(", "#-#");
			arrT2=strT1.split("#-#")[1];
			arrT2 = arrT2.split("\\.")[0];
			//wORKING lOGIc
//			arrT2 = strT1.split("\\.")[2];
//			arrT2 = arrT2.replace(".", "-");
//			arrT2 = arrT2.split("-")[1];
			//Delete later
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
		return arrT2;
	}

	/**
	 * This function will take the screen shot of the screen
	 */
//	public void takeScreenShot(Status st, String message) {
//		String fileName = new SimpleDateFormat("dd-MM-yyyy-hhmmss").format(new Date());
//		try {
//			if (initBase.screenShot.equalsIgnoreCase("Y") && (!Objects.isNull(driver))) {
//				if (st.toString().toUpperCase().equals("FAIL") || initBase.screenShot.equals("Y")) {
//					File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//					FileUtils.copyFile(srcFile,
//							new File(initBase.currDir + initBase.screenShotFolder + "\\" + fileName + ".png"));
//					message = message + "<a href='" + "screenshot/" + fileName + ".png" + "' target='_blank'>"
//							+ "'>ScreenShot</a>";
//				}
//			}
//			// July 24
//			if (st.toLower().equalsIgnoreCase("pass")) {
//				extentTest.pass(message);
//			} else if (st.toLower().equalsIgnoreCase("info")) {
//				extentTest.info(message);
//			} else {
//				extentTest.fail(message);
//			}
//			//
//		} catch (Exception e) {
//			System.out.println("Screenshot not captured : " + e.getMessage().toString());
//		}
//	}
	public void takeScreenShot(Status st, String message) {
		String fileName = new SimpleDateFormat("dd-MM-yyyy-hhmmss").format(new Date());

		try {
			if (initBase.screenShot.equalsIgnoreCase("Y") && driver != null) {
				boolean isAlertPresent = false;

				// Check for alert presence
				try {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
					wait.until(ExpectedConditions.alertIsPresent());
					isAlertPresent = true;
					System.out.println("Alert detected. Using desktop screenshot.");
				} catch (Exception e) {
					// No alert, proceed as usual
				}

				if (st.toString().toUpperCase().equals("FAIL") || initBase.screenShot.equalsIgnoreCase("Y")) {
					String screenshotPath = initBase.currDir + initBase.screenShotFolder + "\\" + fileName + ".png";

					if (isAlertPresent) {// Take desktop screenshot
						try {
							if (initBase.hideBrowser.equals("N")) {
								Robot robot = new Robot();
								Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
								BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
								ImageIO.write(screenFullImage, "png", new File(screenshotPath));
							} else {
								System.out.println("Desktop screenshot Can not work during alert && hidden browserΩ!");
							}
						} catch (Exception ex) {
							System.out.println("Failed to capture desktop screenshot: " + ex.getMessage());
						}
					} else { // Take browser screenshot
						try {
							File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
							FileUtils.copyFile(srcFile, new File(screenshotPath));
						} catch (Exception ex) {
							System.out.println("Failed to capture browser screenshot: " + ex.getMessage());
						}
					}
					message = message + "<a href='" + "screenshot/" + fileName + ".png"
							+ "' target='_blank'>ScreenShot</a>";
				}
			} // Log to Extent Report
			if (st.toString().equalsIgnoreCase("PASS")) {
				extentTest.pass(message);
			} else if (st.toString().equalsIgnoreCase("INFO")) {
				extentTest.info(message);
			} else {
				extentTest.fail(message);
			}

		} catch (Exception e) {
			System.out.println("Screenshot not captured: " + e.getMessage());
		}
	}

	/*
	 * 
	 * This function will only write logs e.g.when @Test method gets executed
	 */
	public void onlyLog() {
		extentReports.flush();
		if (getScenarioName() == null) {
			return;
		}
		Status st;
		String passFail = "Passed";
		if (getTestMethodErrorCount() > 0) // IF any step fails this counter will be more than 0
		{
			initBase.totalFail++;
			ptotalFail++;
			initBase.incrementFail();
			passFail = "Failed";
		} else {
			initBase.totalPass++;
			ptotalPass++;
			initBase.incrementPass();
		}
		if (passFail.equalsIgnoreCase("Passed")) {
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
		startTime = System.nanoTime();// System.currentTimeMillis();
		initBase.mapModuleResults.put(moduleName + "-" + initBase.browser,
				ptotalPass + "," + ptotalFail + "," + (ptotalPass + ptotalFail) + "," + strEndTime + ","
						+ initBase.fmtDate + "," + browser + "," + initBase.testenv);
		Utils.showToastMessage(driver, "Total Pass : " + initBase.getPassCount() + " Total Fail : "
				+ initBase.getFailCount() + "  Total TC : " + runcnt, "info");
	}

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public Integer getTestMethodErrorCount() {
		return this.testMethodErrorCount;
	}

	public void setTestMethodErrorCount(Integer testMethodErrorCount) {
		this.testMethodErrorCount = testMethodErrorCount;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public void setDriver(WebDriver driver) {
		startTime = System.nanoTime();// System.currentTimeMillis(); // This will track individual TC or test time
		this.driver = driver;
	}

	public void mergeReports() {
		try {
			repTime = (GlobalSetup.getReportTime());
			String strjsonFdr = initBase.reportFld + repTime + "\\json\\";
			String strHTMLfdr = initBase.reportFld + repTime + "\\";
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
		int iPass = 0, iFail = 0, iTotal = 0;
		String ftime = "";

		try {
			repTime = (GlobalSetup.getReportTime());
			ExtentSparkReporter spark = new ExtentSparkReporter(initBase.reportFld + repTime + "\\" + "Dashboard.html");
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
} // End of LogResults
