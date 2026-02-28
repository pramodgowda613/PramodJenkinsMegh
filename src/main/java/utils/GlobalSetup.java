package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlSuite;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import org.w3c.dom.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import org.testng.annotations.Test;

/**
 * Tapan Gandhi 12/12/2021
 */
/**
 * 
 */
public class GlobalSetup {
	WebDriver driver;
	// Declare all the ThrSafe Var here.
//	private static final ThreadLocal<String> reportTime = new ThreadLocal<>();
	private static String reportTime;
	private static boolean showStatus = false;
	private static final ThreadLocal<String> execSuiteName = new ThreadLocal<>();

	//
	@BeforeSuite(alwaysRun = true)
	@Parameters({ "projName", "ReportName" })
	void beforeSuite(String projName, @Optional("") String ReportName, ITestContext context) {
		try {
			initBase.readSettingsExcel(projName);
//			initBase.jenSendEmail="N";
			if (!ReportName.isEmpty()) {
				initBase.reportName = ReportName;
			}
			String ts = java.time.ZonedDateTime.now()
					.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yy-HH-mm-ss"));
			setReportTime(ts);
			if (!"N".equals(initBase.holdNewData)) { // Verify if new or continue with existing master data till
														// threshold
				Utils.getExecutionID();
			} else {// The below line is to forcefully delete addmaster if holdnewdata=N
				Files.deleteIfExists(Path.of("data", "addmaster.properties"));
			} // Reason being in code direct calls are made to check the availability of
				// required masters
			XmlSuite suite1 = context.getSuite().getXmlSuite();
			String suiteName = suite1.getName();
			
			List<String> fileNames = suite1.getFileName() != null ? List.of(suite1.getFileName()) : List.of();
			ISuite suite = context.getSuite();
			File file = new File(suite1.getFileName());
			String fileName = file.getName();
			initBase.allmapPubVar.put("suiteName", fileName.split("\\.")[0]);
			Utils.enableDualLogging(); // Analyze all test plans inside suite
			XmlSuite xmlSuite = suite.getXmlSuite();
			String parallelMode = xmlSuite.getParallel().toString(); // Retrieves the parallel mode
			int threadCount = xmlSuite.getThreadCount(); // Retrieves the thread-count
			if (threadCount > 1 && parallelMode.equals("tests")) {
				initBase.bParallel = true;
			}
			String temp = Utils.getConfigValue("config.properties", "showStatus");
			if (temp.equals("true")) {
				showStatus = true;
			}
			if (showStatus) {
				System.out.println("🧾 Suite name: " + suiteName);
				System.out.println("📄 Parsed file: " + fileNames);
				temp = fileNames.toString().replace("[", "");
				temp = temp.replace("]", "");
				analyzeTestNGXml(temp);
			}

			Utils utils = new Utils(driver);
			utils.cleanReports();
			String fullPath = suite1.getFileName();
			suiteName = fullPath.substring(fullPath.lastIndexOf(File.separator) + 1);
			execSuiteName.set(suiteName);
			String jenrerun = System.getenv("jenrerun") == null ? "" : System.getenv("jenrerun");
			context.setAttribute("rerun", false);
			if (jenrerun.equalsIgnoreCase("NotRunTC")) {
				context.getSuite().setAttribute("reportFolder",
						Utils.getConfigValue("data\\TS_Executed.properties", GlobalSetup.getSuitName()));
				initBase.jenRerun = "NotRunTC";
				context.setAttribute("rerun", true);
			} else {
				Utils.writeToPropsFile("data\\TS_Executed.properties", suiteName, initBase.reportFld + reportTime +  "-"+ initBase.allmapPubVar.get("suiteName"));
				context.getSuite().setAttribute("reportFolder", initBase.reportFld + reportTime +  "-"+initBase.allmapPubVar.get("suiteName"));
			}
		} catch (Exception e) {
			System.err.println("❌ Error in Before Suite: " + e.getMessage());
		}
	}

	@AfterSuite(alwaysRun = true)
	@Parameters({ "sendEmail" })
	public void globalTeardown(String sendEmail) {
		LogResults logResults = new LogResults();
		logResults.mergeReports();
		if (initBase.jenSendEmail.equalsIgnoreCase("Y")) {
			sendEmail = "Y";
		}
		if (sendEmail.equalsIgnoreCase("Y")) {
			Email.SendEmail();
		}
		logResults.generateSummaryReport();
		CleanUp();
		if (initBase.jenRerun.equalsIgnoreCase("Y")) {
			Utils.writeToPropsFile("data\\TS_Executed.properties", execSuiteName.get(),
					initBase.reportFld + reportTime +  "-"+initBase.allmapPubVar.get("suiteName"));
		}
	}

	/**
	 * Tapan Aug 25 wrote this to support multiple XMLS in a single suite.
	 */
	private void CleanUp() {
		initBase.totalPass = 0;
		initBase.totalFail = 0;
		initBase.mapModuleResults.clear();
		initBase.mapMethodResults.clear();
		initBase.atotalPass = new AtomicInteger(0);
		initBase.atotalFail = new AtomicInteger(0);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				if (driver != null) {
					driver.quit();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

	}

	/**
	 * Tapan Jul 25, This method will be use for all glb var across tests
	 */
	public static void setReportTime(String value) {
		if (reportTime == null) { // guard against accidental overwrite
			reportTime = value;
		}
	}

	public static String getReportTime() {
		return reportTime;
	}

	public static void setSuitName(String value) {
		execSuiteName.set(value);
	}

	public static String getSuitName() {
		return execSuiteName.get();
	}

	public static void analyzeTestNGXml(String xmlName) {
		System.out.println("🧾 Starting TestNG XML Analysis...");
		try {
			File xmlFile = new File(xmlName); // Adjust path if needed
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			NodeList testList = doc.getElementsByTagName("test");
			int totalTests = testList.getLength();
			int includeCount = 0;
			int excludeCount = 0;
			int classCount = 0;
			int totalEnabledMethods = 0;
			int totalDisabledMethods = 0;

			for (int i = 0; i < testList.getLength(); i++) {
				Node testNode = testList.item(i);
				Element testElement = (Element) testNode;

				NodeList classList = testElement.getElementsByTagName("class");
				classCount += classList.getLength();

				for (int j = 0; j < classList.getLength(); j++) {
					Element classElement = (Element) classList.item(j);
					String className = classElement.getAttribute("name");
					System.out.println("🔸 Analyzing class: " + className);

					// Count <include> and <exclude> in XML
					NodeList methodsNodes = classElement.getElementsByTagName("methods");
					for (int k = 0; k < methodsNodes.getLength(); k++) {
						Element methodsElement = (Element) methodsNodes.item(k);
						NodeList includes = methodsElement.getElementsByTagName("include");
						NodeList excludes = methodsElement.getElementsByTagName("exclude");
						includeCount += includes.getLength();
						excludeCount += excludes.getLength();
					}

					// Now use reflection to count enabled/disabled @Test methods
					try {
						Class<?> clazz = Class.forName(className);
						Method[] methods = clazz.getDeclaredMethods();

						int enabledInClass = 0;
						int disabledInClass = 0;

						for (Method method : methods) {
							if (method.isAnnotationPresent(Test.class)) {
								Test testAnnotation = method.getAnnotation(Test.class);
								if (testAnnotation.enabled()) {
									enabledInClass++;
								} else {
									disabledInClass++;
								}
							}
						}

						totalEnabledMethods += enabledInClass;
						totalDisabledMethods += disabledInClass;

						System.out.println("  ✅ Enabled test methods: " + enabledInClass);
						System.out.println("  🚫 Disabled test methods: " + disabledInClass);

					} catch (ClassNotFoundException e) {
						System.err.println("❌ Could not load class: " + className);
					}
				}
			}

			// Summary
			System.out.println("\n🔍 TestNG Suite Analysis Summary:");
			System.out.println("• Total <test> tags: " + totalTests);
			System.out.println("• Total <class> entries: " + classCount);
			System.out.println("• Total <include> tags in XML: " + includeCount);
			System.out.println("• Total <exclude> tags in XML: " + excludeCount);
			System.out.println("• Total enabled @Test methods (via reflection): " + totalEnabledMethods);
			System.out.println("• Total disabled @Test methods (via reflection): " + totalDisabledMethods);

			if (excludeCount > 0 || totalDisabledMethods > 0) {
				System.err.println("⚠️ Warning: Some test methods are excluded or disabled!");
			}

		} catch (Exception e) {
			System.err.println("❌ Failed to analyze testng.xml: " + e.getMessage());
		}
	}

}// End of GlobalSetup
