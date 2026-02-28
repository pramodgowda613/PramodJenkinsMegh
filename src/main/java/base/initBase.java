package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


//ctrl shift numpad divide to collapse all /
//Ctrl Shift P last line in method put on braces
// Alt shift j for header
//-ctrl + 9 = exand all.
//shift . copy same line below

import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//Excel
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

/**
 * Tapan Gandhi 12/12/2021
 */
public class initBase {
	public static String jenEnv = "", jenSendEmail = "", jenRerun = "";// Jenkins related variables
	public static String remoteIP = "";
	// ExcelSettings variables
	public static String browser;
	public static String sleep;
	public static String screenShot;
	public static String screenShotFolder = System.getProperty("user.dir") + "//screenshots//";;
	public static int SkipCount;
	public static String mySQLString;
	public static String strDeleteHTML;
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static FileOutputStream outputStream;
	public static String reportFld = "", reportDash = "", hideBrowser = "", pvtBrowser = "";
	public static String holdNewData = "", launchChromeNewProf = "";
	public static boolean stopNewData;
	public static String browserstackuser = "", browserstackkey = "";

	// Excel as DB Variables
	public static Recordset recordset = null;
	public static Fillo fillo = new Fillo();
	public static Connection connection;
	public static String currDir = System.getProperty("user.dir") + "\\"; // Current directory where the FW is
	public static String userHome = System.getProperty("user.home") + "\\.m2\\repository\\"; // This will hold the
	public static String userTmpDir = System.getProperty("java.io.tmpdir") + "\\"; // repository path
	public static String pageLoadTimeout = "30";
	public static String implicitlyWait = "15";
	public static int explicitWait = 20;
	public static String runDir = System.getProperty("user.dir");
	public static String urlID, URL, wkDay, results;
	boolean headLess = false;
	// ExcelSettings variables
	public static String testenv;
	public static String reportName;
	public static String[][] arrDevices = new String[10][11]; // Store mobile device related info.
	// Report the Module wise counts
	public static LinkedHashMap<String, String> mapModuleResults = new LinkedHashMap<String, String>();
	public static LinkedHashMap<String, String> mapMethodResults = new LinkedHashMap<String, String>();
	public static Map<String, String> allmapPubVar = new HashMap<>();
	// log File Name
	public static String logFileName = "log.txt", logFileName1 = "log1.txt";
	public static File logFile = new File(currDir + "\\data\\", logFileName),
			logFile1 = new File(currDir + "\\data\\", logFileName1);
	public static int deviceNo = 0;
	public static String strEmailTo = "";
	public static String strEmailCC = "";
	public static String jsonPostLoginURL1, jsonPostLoginURL2;
	// Hashmap
//	public static HashMap<String, Integer> hashMapTestCaseData = new HashMap<String, Integer>();

//	private static final ConcurrentMap<String, Integer> hashMapTestCaseData = new ConcurrentHashMap<>();
	private static final Map<String, Integer> hashMapTestCaseData =
	        Collections.synchronizedMap(new LinkedHashMap<>());

	private static final Set<String> allSheetNames = ConcurrentHashMap.newKeySet();

//	public static LinkedHashMap<String, Integer> hashMapTestCaseData = new LinkedHashMap<String, Integer>();
//	private static String allSheetNames = "";
	public static String executionRunTime = new SimpleDateFormat("ddMMyyHHmmss").format(new Date()); // Unique
	public static String fmtDate = new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date()); // Unique
	// Total Pass/Fail/TC Count
	public static Integer totalPass = 0;
	public static Integer totalFail = 0;
	// Parallel execution
	public static boolean bParallel = false;
	// Atomic Var
	public static AtomicInteger atotalPass = new AtomicInteger(0);
	public static AtomicInteger atotalFail = new AtomicInteger(0);
	//

	public static void incrementPass() {
		atotalPass.incrementAndGet();
	}

	public static void incrementFail() {
		atotalFail.incrementAndGet();
	}

	public static int getPassCount() {
		return atotalPass.get();
	}

	public static int getFailCount() {
		return atotalFail.get();
	}

	// Return read-only view
	public static Set<String> getAllTestCaseKeys() {
		return hashMapTestCaseData.keySet();
	}

	public static void clearAllTestCaseKeys() {
		hashMapTestCaseData.clear();
	}
	
	public static void clearAllSheetNames() {
		allSheetNames.clear();
	}
	/**
	 * @param infoMessage
	 * @param titleBar
	 */
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Tapan, 12/12/2021
	 * 
	 * @param cellIndex
	 * @param iRow
	 * @return
	 */
	public static String getSafeCellValue(int cellIndex, int iRow) {
		XSSFRow row = null;
		XSSFCell cell = null;
		try {
			row = sheet.getRow(iRow);
			cell = row.getCell(cellIndex);

			if (cell == null || cell.getCellType() == CellType.BLANK) {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
		return cell.toString().trim();
	}

	/**
	 * Loads all the settings required for the driver
	 */
	public static void readSettingsExcel(String projName) {
		try {
			jenEnv = System.getenv("jenenv");
			if (jenEnv == null) {
				jenEnv = "";
			}
			jenSendEmail = System.getenv("minopsendemail");
			if (jenSendEmail == null) {
				jenSendEmail = "";
			}
			System.out.println("Email Send : " + jenSendEmail + "," + jenEnv);
			int ienv = 1;
			if (jenEnv.equalsIgnoreCase("stage")) {
				ienv = 2; // Stage environment
			}
			if (Objects.isNull(fis) != true) {
//				return; Augu 25 to allow multiple excel files to be read.
				fis.close();
				workbook.close();
			}
			if (verifyInputfile(currDir + "data\\Project.xlsx") == false) {
				System.out.println("Configuration file not found, Can't continue...");
				infoBox("Configuration file not found, Can't continue...", "Error.");
				System.exit(0);
			}
			if (verifyInputfile(currDir + "data\\" + projName + "TestData.xlsm") == false) {
				System.out.println("Project test data file not found, Can't continue...");
				infoBox("Project file not found, Can't continue...", "Error.");
				System.exit(0);
			}
			fis = new FileInputStream(currDir + "data\\" + projName + "TestData.xlsm");
			workbook = (XSSFWorkbook) WorkbookFactory.create(fis);
			sheet = workbook.getSheetAt(workbook.getSheetIndex("settings"));
			int i = 0, j = ienv;
			XSSFRow row = sheet.getRow(i++);
			browser = getSafeCellValue(j, i++);
			URL = getSafeCellValue(j, i++);
			sleep = getSafeCellValue(j, i++);
			testenv = getSafeCellValue(j, i++);
			pageLoadTimeout = getSafeCellValue(j, i++);
			implicitlyWait = getSafeCellValue(j, i++);
			reportName = getSafeCellValue(j, i++);
			screenShot = getSafeCellValue(j, i++);
			SkipCount = Integer.parseInt(getSafeCellValue(j, i++));
			explicitWait = Integer.parseInt(getSafeCellValue(j, i++));
			mySQLString = getSafeCellValue(j, i++);
			strDeleteHTML = getSafeCellValue(j, i++);
			strEmailTo = getSafeCellValue(j, i++);
			strEmailCC = getSafeCellValue(j, i++);
			jsonPostLoginURL1 = getSafeCellValue(j, i++);
			jsonPostLoginURL2 = getSafeCellValue(j, i++);
			remoteIP = getSafeCellValue(j, i++);
			reportFld = getSafeCellValue(j, i++);
			reportDash = getSafeCellValue(j, i++);
			hideBrowser = getSafeCellValue(j, i++);
			pvtBrowser = getSafeCellValue(j, i++);
			holdNewData = getSafeCellValue(j, i++);
			launchChromeNewProf = getSafeCellValue(j, i++);
			browserstackuser = getSafeCellValue(j, i++);
			browserstackkey = getSafeCellValue(j, i++);

			allmapPubVar = getAllPublicVarMap(); // Oct 25 Make sure to redeclare all excel in here.
			int k = workbook.getSheetIndex("device");
			if (k > 0) {
				sheet = workbook.getSheetAt(workbook.getSheetIndex("device"));
				try {
					List<String> command = new ArrayList<>();
					String adbPath = System.getProperty("user.home")
							+ "\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb.exe";
					command.add(adbPath);
					command.add("devices");
					for (int nrow = 0; nrow < 11; nrow++) {
						row = sheet.getRow(nrow + 1);
						if (row == null) {
							break;
						}
						String temp = sheet.getRow(nrow + 1).getCell(0).getStringCellValue();
						if (temp.isEmpty()) {
							break;
						}
						for (int jcol = 0; jcol < 11; jcol++) {
							cell = row.getCell(jcol);
							FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
							arrDevices[nrow][jcol] = getCellValueAsString(cell, evaluator);
						}
					}
				} catch (Exception e) {
					System.out.println("Error while loading devices info ");
				}
				if (arrDevices.length < 1) {
					throw new Exception("Devices information missing");
				}
			}

		} catch (Exception e) {
			System.out.println("Fatal Error while reading Excel Settings: " + e.getMessage());
			System.exit(1);
		}
	}

	/*
	 * Tapan, Sep 25 This method will return the cell value as String irrespective
	 * of the cell
	 */
	// Utility: Safe string conversion for any Excel cell
	public static String getCellValueAsString(Cell cell, FormulaEvaluator evaluator) {
		if (cell == null) {
			return "";
		}
		String temp = "";
		DataFormatter formatter = new DataFormatter();
		try {
			temp = formatter.formatCellValue(cell, evaluator).trim();
			temp = temp.replace("'", ""); // Remove all single quotes
			return temp;
		} catch (Exception e) {
			System.out.println("getCellValueAsString Error : " + e.getMessage());
			return "";
		}
	}

//		if (cell == null) {
//			return "";
//		}
//		switch (cell.getCellType()) {
//		case STRING:
//			String value = cell.getStringCellValue();
//			if (value.startsWith("'")) {
//				value = value.substring(1); // remove only the first character if it's a quote
//			}
//			return value;
//		case NUMERIC:
//			if (DateUtil.isCellDateFormatted(cell)) {
//				return cell.getDateCellValue().toString().replace("'", "");
//			} else {
//				int intValue = (int) Math.floor(cell.getNumericCellValue());
//				return String.valueOf(intValue);
//			}
//		case BOOLEAN:
//			return String.valueOf(cell.getBooleanCellValue());
//		case FORMULA:
//			// Evaluate the formula and return the computed value
//			CellValue cellValue = evaluator.evaluate(cell);
//			switch (cellValue.getCellType()) {
//			case STRING:
//				value = cell.getStringCellValue();
//				if (value.startsWith("'")) {
//					value = value.substring(1); // remove only the first character if it's a quote
//				}
//				return value;
//			case NUMERIC:
//				return String.valueOf(cellValue.getNumberValue());
//			case BOOLEAN:
//				return String.valueOf(cellValue.getBooleanValue());
//			default:
//				return "Unsupported Formula Result";
//			}
//		case BLANK:
//			return "";
//		default:
//			return "Unknown Cell Type";
//		}

	/**
	 * Java hashmap for storing all the test cases value as key and values pair
	 */

	public static ArrayList<String> loadExcelData(String sheetName, String tcName, String fieldNames) {
		ArrayList<String> cellValues = new ArrayList<>();

		try {
			XSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));

			// Load sheet data only once
			if (allSheetNames.add(sheetName)) {
				int lastRow = retExcelLastRow(sheet);
				for (int i = 1; i < lastRow; i++) {
					try {
						String excelTCName = sheet.getRow(i).getCell(0).getStringCellValue();
						if (excelTCName != null && excelTCName.length() > 1) {
							hashMapTestCaseData.putIfAbsent(excelTCName, i);
						}
					} catch (Exception e) {
						System.out.println("The total TS are : " + hashMapTestCaseData.size());
						break;
					}
				}
			}

			// Now fetch requested values
			Integer rowIndex = hashMapTestCaseData.get(tcName);
			if (rowIndex == null) {
				System.out.println("Test case not found: " + tcName);
				return cellValues;
			}

			int noOfColumns = sheet.getRow(0).getLastCellNum();
			String[] temp = fieldNames.split(",");
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

			for (String field : temp) {
				String colName = testenv + "_" + field;
				boolean found = false;

				for (int j = 1; j <= noOfColumns; j++) {
					try {
						String columnName = sheet.getRow(0).getCell(j).getStringCellValue();
						if (colName.equalsIgnoreCase(columnName)) {
							Cell cell = sheet.getRow(rowIndex).getCell(j);
							String rowvalue = (cell != null) ? getCellValueAsString(cell, evaluator) : "";
							cellValues.add(rowvalue);
							found = true;
							break;
						}
					} catch (Exception e) {
						System.out.println("Error while reading excel column: " + e.getMessage());
					}
				}
				if (!found) {
					cellValues.add("");
					System.out.println("No values found in Excel Data for column : " + colName);
				}
			}
		} catch (Exception e) {
			System.out.println("Error while reading excel : " + sheetName + "-" + e.getMessage());
		}
		return cellValues;
	}

//	public static ArrayList<String> loadExcelData(String sheetName, String tcName, String fieldNames) {
//		ArrayList<String> cellValues = new ArrayList<String>();
//		XSSFSheet sheet = null;
//		try {
//			sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
//			int i = 1, j = 0, k;
//			if (allSheetNames.contains(sheetName) == false) {
//				// Retrieve all the test case names into HashMap.
//				allSheetNames = allSheetNames + sheetName + ",";
//				j = retExcelLastRow(sheet); // sheet.getLastRowNum();
//				String excelTCName = "";
//				for (i = 1; i < j; i++) {
//					try {
//						excelTCName = sheet.getRow(i).getCell(0).getStringCellValue();
//						if (excelTCName.length() < 2) {
//							continue;
//						}
//						hashMapTestCaseData.put(excelTCName, i);
//					} catch (Exception e) {
//						System.out.println("The total TS are : " + hashMapTestCaseData.size());
//						break;
//					} // Add Scenario name in future if required.
//				}
//			}
//			// Retrieve all the values based on the required columns
//			i = hashMapTestCaseData.get(tcName);
//			int noOfColumns = sheet.getRow(0).getLastCellNum();
//			Boolean found = false;
//			String columnName;
//			String[] temp = fieldNames.split(",");
//			for (k = 0; k < temp.length; k++) {
//				found = false;
//				String col = testenv + "_" + temp[k]; // Take the current Environment and club with the passed column
//														// name from @Test Method
//				for (j = 1; j <= noOfColumns; j++) {
//					try {
//						columnName = sheet.getRow(0).getCell(j).getStringCellValue();
//						if (col.equalsIgnoreCase(columnName)) {
//							String rowvalue = "";
//							try {
//								cell = sheet.getRow(i).getCell(j);
//								if (cell != null) {
//									FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//									rowvalue = getCellValueAsString(cell, evaluator);
//								} else {
//									rowvalue = "";
//								}
//							} catch (Exception e) {
//							}
//							cellValues.add(rowvalue);
//							found = true;
//							break;
//						}
//					} catch (Exception e) {
//						System.out.println("Error while reading excel : " + e.getMessage());
//					}
//				}
//				if (found != true) {
//					cellValues.add("");
//					System.out.println("No values found in Excel Data for column : " + col);
//				}
//			}
//		} catch (Exception e) {
//			System.out.println("Error while reading excel : " + sheetName + "-" + e.getMessage());
//		}
//		return cellValues;
//	}

	/**
	 * This function verifies file exist on the given path
	 * 
	 */
	public static boolean verifyInputfile(String filename) {
		try {
			File file = new File(filename);
			if (file.exists()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("File does not exist: " + filename);
		}
		return false;
	}

	/**
	 * This function verifies dir exist on the given path
	 * 
	 */
	public static boolean verifyInputfile(String filename, String directory) {
		try {
			File file = new File(filename);
			if (file.isDirectory() != true) {
				file.mkdir();
			}
			return true;
		} catch (Exception e) {
			System.out.println("Unable to create Directory/Path : " + e.getMessage());
		}
		return false;
	}

	public static int retExcelLastRow(XSSFSheet sheet) {
		int actualRowCount = 0;
		for (Row row : sheet) {
			boolean isEmpty = true;
			for (Cell cell : row) {
				if (cell.getCellType() != CellType.BLANK && cell.getCellType() != CellType._NONE
						&& cell.toString().trim().length() > 0) {
					isEmpty = false;
					break;
				}
			}
			if (!isEmpty) {
				actualRowCount++;
			}
		}
		return actualRowCount;
	}

	public static String maskPassword(String password) {
		return "*".repeat(password.length());
	}
	
	/** Tapan Oct 25
	 * Returns a map of all public variables and their values.
	 *
	 * @return A map containing variable names as keys and their corresponding
	 *         values.
	 */
	static Map<String, String> getAllPublicVarMap() {
        Map<String, String> map = new HashMap<>();

        map.put("browser", browser);
        map.put("URL", URL);
        map.put("sleep", sleep);
        map.put("testenv", testenv);
        map.put("pageLoadTimeout", pageLoadTimeout);
        map.put("implicitlyWait", implicitlyWait);
        map.put("reportName", reportName);
        map.put("screenShot", screenShot);
        map.put("SkipCount", String.valueOf(SkipCount));
        map.put("explicitWait", String.valueOf(explicitWait));
        map.put("mySQLString", mySQLString);
        map.put("strDeleteHTML", strDeleteHTML);
        map.put("strEmailTo", strEmailTo);
        map.put("strEmailCC", strEmailCC);
        map.put("jsonPostLoginURL1", jsonPostLoginURL1);
        map.put("jsonPostLoginURL2", jsonPostLoginURL2);
        map.put("remoteIP", remoteIP);
        map.put("reportFld", reportFld);
        map.put("reportDash", reportDash);
        map.put("hideBrowser", hideBrowser);
        map.put("pvtBrowser", pvtBrowser);
        map.put("holdNewData", holdNewData);
        map.put("launchChromeNewProf", launchChromeNewProf);
        map.put("browserstackuser", browserstackuser);
        map.put("browserstackkey", browserstackkey);

        return map;
    }

//	public class WindowsDriverFactory {
//		/**
//		 * Creates and returns a WindowsDriver instance for automating Windows desktop
//		 * apps.
//		 *
//		 * @param appIdOrExecutable The Application User Model ID or executable path of
//		 *                          the Windows app to automate.
//		 * @param winAppDriverUrl   The URL where WinAppDriver server is running
//		 *                          (default: http://127.0.0.1:4723)
//		 * @return WindowsDriver instance if created successfully, otherwise null.
//		 */
//		public static WindowsDriver createWindowsDriver(String appIdOrExecutable, String winAppDriverUrl) {
//			WindowsDriver driver = null;
//			try {
//				DesiredCapabilities capabilities = new DesiredCapabilities();
//				capabilities.setCapability("app", appIdOrExecutable);
//				capabilities.setCapability("platformName", "Windows");
//				capabilities.setCapability("deviceName", "WindowsPC");
//
//				driver = new WindowsDriver(new URL(winAppDriverUrl), capabilities);
//				// Optionally add implicit wait or other driver setup here
//				driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
//				System.out.println("WindowsDriver started successfully.");
//			} catch (MalformedURLException e) {
//				System.err.println("Invalid WinAppDriver URL: " + winAppDriverUrl);
//				e.printStackTrace();
//				System.exit(0);
//			} catch (Exception e) {
//				System.err.println("Failed to create WindowsDriver.");
//				e.printStackTrace();
//				System.exit(0);
//			}
//			return driver;
//		}
//	}

	// Tips
	// 1. When 500 error comes run below
//	adb uninstall io.appium.uiautomator2.server
//	adb uninstall io.appium.uiautomator2.server.test S+alt+J HEAD
//	
//	1. Install Java Development Kit (JDK)
//	2. Install Android Studio and SDK
//	3. Install Appium Desktop applicaiton
//	4. Install Eclipse IDE
//	5. Install TestNG Plugin for EcliΓpse
//	6. Install Appium inspector (setup with appropriate appium port and URL)
//	client.. not worked had  to install appium in npm only.
//	nodejs
//	npm install -g appium
//	npm install -g adb
//https://developer.android.com/tools/releases/platform-tools for installing ADB
	// npm install -g appium-uiautomator2-driver
	// Jenkins
	// 1 Check all the masters tblemployeemaster, tblpolicymaster, tblshiftmaster
	// tblusermaster, tblshiftgroupmaster

	// 2 The mm-dd-yyyy is needed for Testsigma, hence before running the jenkins
	// ensure the date is yyyy-mm-dd for jenkins. tblsystemconfiguraitonmaster
	//

	//
}// END
