package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

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
	private static final ThreadLocal<String> reportTime = new ThreadLocal<>();
	private static boolean showStatus=false;

	//
	@BeforeSuite(alwaysRun = true)
	@Parameters({ "projName", "ReportName" })
	void beforeSuite(String projName, @Optional("") String ReportName, ITestContext context) {
		initBase.readSettingsExcel(projName);
		if (!ReportName.isEmpty()) {
			initBase.reportName = ReportName;
		}
		setReportTime(new SimpleDateFormat("dd-MM-yy_HH_mm_ss").format(new Date()));
		if (!"N".equals(initBase.holdNewData)) { // Verify if new or continue with existing master data till threshold
			Utils.getExecutionID();
		}
		Utils.enableDualLogging(); // Analyze all test plans inside suite
		XmlSuite suite1 = context.getSuite().getXmlSuite();
		String suiteName = suite1.getName();
		List<String> fileNames = suite1.getFileName() != null ? List.of(suite1.getFileName()) : List.of();

		ISuite suite = context.getSuite();
		XmlSuite xmlSuite = suite.getXmlSuite();
		String parallelMode = xmlSuite.getParallel().toString(); // Retrieves the parallel mode
		int threadCount = xmlSuite.getThreadCount(); // Retrieves the thread-count
		if (threadCount > 1 && parallelMode.equals("tests")) {
			initBase.bParallel = true;
		}
		String temp=Utils.getConfigValue("config.properties", "showStatus");
		if (temp.equals("true")) {
			showStatus=true;
		}
		if (showStatus) {
			System.out.println("🧾 Suite name: " + suiteName);
			System.out.println("📄 Parsed file: " + fileNames);
			temp = fileNames.toString().replace("[", "");
			temp = temp.replace("]", "");
			analyzeTestNGXml(temp);
		}

		Utils utils = new Utils(driver);
		LoadDriver.lockDevice();
		utils.cleanReports();
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
	}

	/**
	 * Tapan Aug 25 wrote this to support multiple XMLS in a single suite. 
	 */
	private void CleanUp() {
		initBase.totalPass = 0;
		initBase.totalFail = 0;
		initBase.mapModuleResults.clear();
		initBase.mapMethodResults.clear();
	}

	/**
	 * Tapan Jul 25, This method will be use for all glb var across tests
	 */
	public static void setReportTime(String value) {
		reportTime.set(value);
	}

	public static String getReportTime() {
		return reportTime.get();
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
