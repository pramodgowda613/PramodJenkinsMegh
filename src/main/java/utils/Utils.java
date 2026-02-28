package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Properties;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.base.Function;
import com.mysql.cj.jdbc.CallableStatement;

import base.initBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.ScreenOrientation;

/**
 * Tapan Gandhi 12/12/2021
 */
public class Utils {
	// MySQL Variables should be here
	private String dbURL = initBase.mySQLString;
	private Connection connection;
	private Connection con_obj;
	private Statement st_obj;
	WebDriver driver;
	private String exceptionDesc;
	private int days;
	private int maxHeader = 0;
	private int lastRowNum = 0;

	public Utils(WebDriver driver) {
		this.driver = driver;
	}

	//
	public void cleanReports() {
		try {
			String folderPath = initBase.screenShotFolder; // Specify the folder path
			String jsonfolderPath = "", scrShtFdrPth = "";
			days = Integer.parseInt(initBase.strDeleteHTML);// Specify the number of days
			// deleteOldFiles(folderPath, days);
			folderPath = (initBase.reportFld).split("\\\\")[0];
			File directory = new File(folderPath);
			File jsondirectory = null;
			File[] files = directory.listFiles();
			for (File file : files) {
				// If the file is a directory, delete it recursively
				if (file.isDirectory()) {
					jsonfolderPath = file.getAbsolutePath() + "\\json\\";
					scrShtFdrPth = file.getAbsolutePath() + "\\screenshot\\";
					jsondirectory = new File(jsonfolderPath);
					if (jsondirectory.isDirectory()) {
						deleteOldFiles(jsondirectory.getAbsolutePath());
						jsondirectory.delete();
					}
					deleteOldFiles(file.getAbsolutePath());
					// Delete Screenshot folder
					jsondirectory = new File(scrShtFdrPth);
					if (jsondirectory.isDirectory()) {
						deleteOldFiles(jsondirectory.getAbsolutePath());
						jsondirectory.delete();
					}
					deleteOldFiles(file.getAbsolutePath());
				}
				file.delete();
			}
		} catch (Exception e) {
			System.out.println("Error while deleting report directory." + e.getMessage());
		}
	}

	// Getter for the connection object
	public Connection getConnection() {
		return con_obj;
	}

	// Getter for the statement object
	public Statement getStatement() {
		return st_obj;
	}

	public void closeConnection() {
		try {
			if (con_obj != null) {
				con_obj.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteOldFiles(String folderPath) {
		File folder = new File(folderPath);
		if (!folder.exists() || !folder.isDirectory()) {
			System.out.println("Invalid folder path.");
			return;
		}
		FilenameFilter imageFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String lowerName = name.toLowerCase();
				return lowerName.endsWith(".png") || lowerName.endsWith(".txt") || lowerName.endsWith(".lck")
						|| lowerName.endsWith(".html") || lowerName.endsWith(".json");
			}
		};
		// List image files in the directory
		File[] files = folder.listFiles(imageFilter);
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					try {
						BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
						Instant fileTime = attrs.creationTime().toInstant();
						LocalDateTime fileDateTime = LocalDateTime.ofInstant(fileTime, ZoneId.systemDefault());
						LocalDateTime cutoffDate = LocalDateTime.now().minus(days, ChronoUnit.DAYS);
						if (fileDateTime.isBefore(cutoffDate) || days == 0) {
							if (!file.delete()) {
								System.out.println("Failed to delete: " + file.getAbsolutePath());
							}
						}
					} catch (Exception e) {
						System.err.println("Error processing file: " + file.getAbsolutePath());
					}
				}
			}
		}
	}

	public static String getRandomDateXPath(int dayOffset) {
		LocalDate now = LocalDate.now().plusDays(dayOffset);
		int day = now.getDayOfMonth();
		String monthName = now.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		int year = now.getYear();
		return "//android.view.View[@content-desc='" + day + " " + monthName + " " + year + "']";
	}

	public static String getRandomDateFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyHHmmss");
		// Format the current date
		String date = formatter.format(new Date());
		// Return the formatted date string
		return date;
	}

	/**
	 * @param dbURL
	 * @param mySQLQry
	 */
	public boolean updateMYSQL(String dbURL, String mySQLQry) {
		boolean lfail = false;
		String temp = "";
		for (int i = 0; i < 5; i++) {
			try {
				if (connection != null) {
					connection.close();
				}
				if (i > 0) {
					Thread.sleep(5000);
					System.out.println("Retrying DB Update Query:" + i + "/4");
				}
				connection = DriverManager.getConnection(dbURL);
				lfail = false;
			} catch (Exception e) {
				temp = "Error in updateMySQL-> " + e.getMessage() + " sql->" + mySQLQry;
				exceptionDesc = temp;
				lfail = true;
			}

			try (PreparedStatement statement = connection.prepareStatement(mySQLQry)) {
				// Execute the insert statement
				Integer rowsInserted = statement.executeUpdate();
				exceptionDesc = "DB->Total records updated are : " + rowsInserted + " for the query ->" + mySQLQry;
				System.out.println(exceptionDesc);
				connection.close();
				break;
			} catch (Exception e) {
				temp = "Error in updateMySQL-> " + e.getMessage() + " sql->" + mySQLQry;
				exceptionDesc = temp;
				lfail = true;
			}
		}
		if (lfail) {
			System.out.println(temp);
			return false;
		}
		return true;
	}

	/**
	 * This function will explicitly wait for the given number of seconds for the
	 * element to be available
	 * 
	 * @param ele
	 * @param expCondition
	 * @return
	 */
	public boolean waitForEle(final WebElement ele, final String expCondition, final String subString) {
		boolean result = false;
		try {
			Wait<WebDriver> wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(initBase.explicitWait))
					.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
			Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver arg0) {
					try {
						if (expCondition.contains("enable")) {
							if (ele.isDisplayed() && ele.isEnabled()) {
								return true;
							}
						} else if (expCondition.contains("visible")) {
							if (ele.isDisplayed()) {
								return true;
							}
						} else if (expCondition.contains("selected")) {
							if (ele.isSelected()) {
								return true;
							}
						} else if (expCondition.contains("innerHTML")) {
							return (ele.getAttribute("innerHTML").contains(subString)
									|| ele.toString().contains(subString));
						} else if (expCondition.contains("notvisible")) {
							if (ele.isDisplayed() != true) {
								return true;
							}
						}
					} catch (Exception e) {
						exceptionDesc = e.getMessage().toString();
					}
					return false;
				}
			};
			result = wait.until(function);
			wait.until(function);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			result = false;
		}
		return result;
	}

	/**
	 * @param ele
	 * @param expCondition
	 * @param subString
	 * @param waitForSeconds
	 * @return
	 */
	public boolean waitForEle(final WebElement ele, final String expCondition, final String subString,
			int waitForSeconds) {
		boolean result = false;
		try {
			Wait<WebDriver> wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(waitForSeconds))
					.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class);
			Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
				@Override
				public Boolean apply(WebDriver arg0) {
					try {
						if (expCondition.contains("enable")) {
							if (ele.isDisplayed() && ele.isEnabled()) {
								return true;
							}
						} else if (expCondition.contains("visible")) {
							if (ele.isDisplayed()) {
								return true;
							}
						} else if (expCondition.contains("selected")) {
							if (ele.isSelected()) {
								return true;
							}
						} else if (expCondition.contains("innerHTML")) {
							return (ele.getAttribute("innerHTML").contains(subString)
									|| ele.toString().contains(subString));
						} else if (expCondition.contains("notvisible")) {
							if (ele.isDisplayed() != true) {
								return true;
							}
						}
					} catch (Exception e) {
						exceptionDesc = e.getMessage().toString();
					}
					return false;
				}
			};
			result = wait.until(function);
			wait.until(function);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			result = false;
		}
		return result;
	}

	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public boolean verifyToastMessagesFromSource(WebElement ele) {
		try {
			String srcXML = this.driver.getPageSource();
			String searchTexxt = ele.getText();
			if (srcXML.contains(searchTexxt)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static String runCommandPrompt(List<String> command, String search) {
		// Create a ProcessBuilder instance
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		String results = "";
		try {
			// Start the process
			Process process = processBuilder.start();
			// Capture the output
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			// Parse the output
			while ((line = reader.readLine()) != null) {
				if (search.isEmpty() != true) {
					if (line.contains(search)) {
						results = results + line;
						break;
					}
				} else {
					results = results + line;
				}
			}
			// Wait for the process to complete
			int exitCode = process.waitFor();
			System.out.println("Command executed with exit code: " + exitCode);
		} catch (Exception e) {
			System.out.println("Error while running ADB command prompt: " + e.getMessage());
		}
		return results;
	}

	public void readWriteXML(String filePath) {
		try {
			File xmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(xmlFile);
			document.getDocumentElement().normalize();
			System.out.println("Root element: " + document.getDocumentElement().getNodeName());
			Element rootElement = document.getDocumentElement();
			// NodeList nodeList = document.getDocumentElement().getChildNodes();
			// Determine the sheet name from the root element
			String strsheetName = rootElement.getTagName();
			initBase.sheet = initBase.workbook.getSheet(strsheetName);
			if (initBase.sheet == null) {
				// If the sheet doesn't exist, create a new one
				initBase.sheet = initBase.workbook.createSheet(strsheetName);
			}
			// Get the last row number to start writing new data
			lastRowNum = initBase.sheet.getLastRowNum();
			// Create a header row if it's the first entry
			if (lastRowNum <= 0) {
				lastRowNum = 0; // returns -1 hence this hard coded value to 0
				createHeaderRow(initBase.sheet, rootElement);
			}
			// Write the data row for the current XML
			Row dataRow = initBase.sheet.createRow(++lastRowNum);
			writeDataRow(dataRow, rootElement);
//			Row headerRow = sheet.createRow(0);
//			int cellIdx = 0;
//			for (int i = 0; i < nodeList.getLength(); i++) {
//				Node node = nodeList.item(i);
//				if (node.getNodeType() == Node.ELEMENT_NODE) {
//					Element element = (Element) node;
//					// element.getChildNodes().getLength()
//					
//					// Only add the tag name if it's not already present in the header
//					if (!headerExists(headerRow, element.getTagName())) {
//						headerRow.createCell(cellIdx++).setCellValue(element.getTagName());
//					}
//				}
//			}
//			int lastRowNum = sheet.getLastRowNum() + 1;
//			Row dataRow = sheet.createRow(lastRowNum);
//			 NodeList childNodes = rootElement.getChildNodes();
//			
//			for (int i = 0; i < childNodes.getLength(); i++) {
//				Node node = childNodes.item(i);
//				if (node.getNodeType() == Node.ELEMENT_NODE) {
//					Element element = (Element) node;
//					// Find the correct column based on the header row
//					int colIdx = findColumnIndex(sheet.getRow(0), element.getTagName());
//					if (colIdx != -1) {
//						dataRow.createCell(colIdx).setCellValue(element.getTextContent());
//					}
//				}
//			}
			initBase.workbook.write(initBase.outputStream);
//			for (int i = 0; i < nList.getLength(); i++) {
//				Node applicationNode = nList.item(i);
//				if (applicationNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element applicationElement = (Element) applicationNode;
//					
//					Element element = (Element) applicationNode;
//
//                    // Create a new row
//                    Row row = sheet.createRow(rowNum++);
//
//                    // Create cells and set the element's tag name and text content
//                    row.createCell(0).setCellValue(element.getTagName());
//                    row.createCell(1).setCellValue(element.getTextContent());
//					// Print each element and its value
//					System.out.println("Id: " + applicationElement.getElementsByTagName("Id").item(0).getTextContent());
//					System.out.println(
//							"Name: " + applicationElement.getElementsByTagName("Name").item(0).getTextContent());
//					System.out.println("ProjectId: "
//							+ applicationElement.getElementsByTagName("ProjectId").item(0).getTextContent());
//					System.out.println("CreatedById: "
//							+ applicationElement.getElementsByTagName("CreatedById").item(0).getTextContent());
//					System.out.println("UpdatedById: "
//							+ applicationElement.getElementsByTagName("UpdatedById").item(0).getTextContent());
//					System.out.println("CreatedDate: "
//							+ applicationElement.getElementsByTagName("CreatedDate").item(0).getTextContent());
//					System.out.println("UpdatedDate: "
//							+ applicationElement.getElementsByTagName("UpdatedDate").item(0).getTextContent());
//					System.out.println("ApplicationType: "
//							+ applicationElement.getElementsByTagName("ApplicationType").item(0).getTextContent());
//					System.out.println("----------------------------");
//				}
//			}
		} catch (Exception e) {
			System.out.println("Error while processing XML : " + e.getMessage());
		}
	}

	public static void CreateExcel(String filePath) throws IOException {
		try {
			File excelFile = new File(filePath);
			if (excelFile.exists()) {
				initBase.fis = new FileInputStream(excelFile);
				initBase.workbook = new XSSFWorkbook(OPCPackage.open(initBase.fis));
			} else {
				initBase.workbook = new XSSFWorkbook();
			}
			initBase.outputStream = new FileOutputStream(filePath);
		} catch (Exception e) {
			System.out.println("Error while creating XLS : " + e.getMessage());
		}
	}

	public static void WriteDataInExcel(String strsheetName, String[] data) {
		try {
			int sheetNo = 0;
			if (initBase.workbook.getSheet(strsheetName).equals(null)) {
				initBase.sheet = initBase.workbook.createSheet(strsheetName);
			}
			sheetNo = initBase.workbook.getSheetIndex(strsheetName);
			initBase.workbook.setActiveSheet(sheetNo);
			int rowNum = 0;
			Row row = initBase.sheet.createRow(rowNum++);
			int colNum = 0;
			for (String field : data) {
				Cell cell = row.createCell(colNum++);
				cell.setCellValue(field);
			}
			initBase.workbook.write(initBase.outputStream);
		} catch (Exception e) {
			System.out.println("Error while Writing Data In XLS : " + e.getMessage());
		}
	}

	public static void closeExcelSheet() {
		try {
			if (initBase.workbook != null) {
				initBase.workbook.close();
			}
			if (initBase.fis != null) {
				initBase.fis.close();
			}
		} catch (Exception e) {
			System.out.println("Error while Closing XLS : " + e.getMessage());
		}
	}

	// Method to create the header row based on the XML element tags
	private static void createHeaderRow(Sheet sheet, Element rootElement) {
		Row headerRow = sheet.createRow(0);
		List<String> columnNames = new ArrayList<>();
		try {
			getColumnNames(rootElement, columnNames);
			// Convert the list of column names to an array
			String[] columnNamesArray = columnNames.toArray(new String[0]);
			int cellIdx = 0;
			for (String columnName : columnNamesArray) {
				headerRow.createCell(cellIdx++).setCellValue(columnName);
			}
			// Optionally print the array of tag names for verification
			for (String tagName : columnNamesArray) {
				System.out.println("Tag Name: " + tagName);
			}
			initBase.workbook.write(initBase.outputStream);
		} catch (Exception e) {
			System.out.println("Error while createHeaderRow : " + e.getMessage());
		}
	}

	// Recursive method to gather all unique element tag names as column headers
	private static void getColumnNames(Node node, List<String> columnNames) {
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				String nodeName = childNode.getNodeName();
				if (!columnNames.contains(nodeName)) {
					columnNames.add(nodeName);
				}
				childNode = ((Node) childNodes).getChildNodes().item(i);
				getColumnNames(childNode, columnNames);
			}
		}
	}

	// Method to write the data row based on the XML element values
	public void writeDataRow(Row dataRow, Element rootElement) {
//		int cellIdx = 0;
		try {
			NodeList childNodes = rootElement.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				System.out.println(childNodes.item(i).getNodeName());
				Node childNode = childNodes.item(i).getChildNodes().item(i);
				writeAllChildNodes(childNode, dataRow);
				dataRow = initBase.sheet.createRow(++lastRowNum);
				initBase.workbook.write(initBase.outputStream);
			}
		} catch (Exception e) {
			System.out.println("Error while writing in excel : " + e.getMessage());
		}
	}

	// Helper method to find the column index of a tag name in the header row
	public static int findColumnIndex(Row headerRow, String tagName) {
		for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
			Cell cell = headerRow.getCell(i);
			if (cell.getStringCellValue().equals(tagName)) {
				return i;
			}
		}
		return -1;
	}

	public void writeAllChildNodes(Node childNode, Row dataRow) {
		try {
			int childAny = childNode.getChildNodes().getLength();
			if (childAny > 0) { // That means this node has children so do again a loop;
				childNode = childNode.getChildNodes().item(0);
				System.out.println(childNode.getNodeName() + "-" + childNode.getNodeValue());
				// System.out.println(childNode.getNodeName()+ "-" + childNode.getNodeValue());
				writeAllChildNodes(childNode, dataRow);
				for (int j = 0; j < childAny; j++) {
					int colIdx = findColumnIndex(initBase.sheet.getRow(0),
							childNode.getChildNodes().item(j).getNodeName());
					if (colIdx != -1) {
						dataRow.createCell(colIdx).setCellValue(childNode.getChildNodes().item(j).getTextContent());
					} else {
						maxHeader = initBase.sheet.getRow(0).getPhysicalNumberOfCells() + 1;
						dataRow.createCell(maxHeader).setCellValue(childNode.getChildNodes().item(j).getTextContent());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error while writeAllChildNodes : " + e.getMessage());
		}
	}

	public static long startTime() {
		return System.currentTimeMillis();
	}

	public static String endTime(double startTime) {
		double endTime = System.currentTimeMillis();
		double durationMillis = endTime - startTime;

		long totalSeconds = (long) (durationMillis / 1000);
		long minutes = totalSeconds / 60;
		long seconds = totalSeconds % 60;

		String formattedTime = String.format("%02d:%02d", minutes, seconds);
		System.out.println("Time taken: " + formattedTime);
		return formattedTime;
	}

	public static String retDateTime() {
		return new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date()); // Unique
	}

	public static String retRandomString(int intLen) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder(intLen);
		SecureRandom rnd = new SecureRandom();
		for (int i = 0; i < intLen; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

	public boolean execSP(String SP) {
		String storedProc = "{" + SP + "()}";
		dbURL = initBase.mySQLString;
		connection = null;

		try {
			try {
				connection = DriverManager.getConnection(dbURL);
			} catch (Exception e) {
				System.out.println("Error in execSP : " + e.getMessage());
				return false;
			}
			CallableStatement callableStatement = (CallableStatement) connection.prepareCall(storedProc);
			// Set input parameters (if any)
			callableStatement.setQueryTimeout(60);
			// Execute the stored procedure
//			ResultSet resultSet = callableStatement.executeQuery();
			boolean executed = callableStatement.execute();
			// connection.commit();
			connection.close();
			System.out.println("Execution was : " + executed);
		} catch (Exception e) {
			System.out.println("Stored SP Execution : Error :" + e.getMessage());
			return false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
		return true;
	}

	public int getDaysInMonth(String yearMonthStr) {
		YearMonth yearMonth = YearMonth.parse(yearMonthStr); // e.g., "2025-05"
		return yearMonth.lengthOfMonth(); // e.g., 31
	}

	public static String formatSecondsToHHMMSS(double totalSeconds) {
		long totalSecs = (long) totalSeconds;
		long hours = totalSecs / 3600;
		long minutes = (totalSecs % 3600) / 60;
		long seconds = totalSecs % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	public static String retFormattedHHMMSS(List<String> timeList) {
		int totalSeconds = 0;

		for (String time : timeList) {
			String[] parts = time.split(":");
			int hours = Integer.parseInt(parts[0]);
			int minutes = Integer.parseInt(parts[1]);
			int seconds = Integer.parseInt(parts[2]);
			totalSeconds += (hours * 3600) + (minutes * 60) + seconds;
		}

		int hours = totalSeconds / 3600;
		int minutes = (totalSeconds % 3600) / 60;
		int seconds = totalSeconds % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	public static void showToastMessage(WebDriver driver, String message, String type) {
		String bgColor;
		try {
			String url = driver.getCurrentUrl();
			// Determine background color based on message type
			switch (type.toLowerCase()) {
			case "Passed":
				bgColor = "#28a745"; // Green
				break;
			case "Failed":
				bgColor = "#dc3545"; // Red
				break;
			case "warning":
				bgColor = "#ffc107"; // Yellow
				break;
			case "info":
			default:
				bgColor = "#17a2b8"; // Blue
				break;
			}
			// JavaScript to inject a toast message
			String script = "var toast = document.createElement('div');" + "toast.innerText = `" + message + "`;"
					+ "toast.style.position = 'fixed';" + "toast.style.top = '50%';" + "toast.style.left = '50%';"
					+ "toast.style.transform = 'translate(-50%, -50%)';" + "toast.style.backgroundColor = '" + bgColor
					+ "';" + "toast.style.color = 'white';" + "toast.style.padding = '20px 30px';"
					+ "toast.style.borderRadius = '8px';" + "toast.style.boxShadow = '0 0 15px rgba(0,0,0,0.3)';"
					+ "toast.style.zIndex = '9999';" + "toast.style.fontFamily = 'Arial, sans-serif';"
					+ "toast.style.fontSize = '16px';" + "toast.style.textAlign = 'center';"
					+ "toast.style.opacity = '1';" + "toast.style.transition = 'opacity 0.5s ease';"
					+ "document.body.appendChild(toast);"
					+ "setTimeout(function() { toast.style.opacity = '0'; }, 3500);"
					+ "setTimeout(function() { toast.remove(); }, 4000);";
			((JavascriptExecutor) driver).executeScript(script);
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("showToastMessage : " + e.getMessage());
		}
	}

	/**
	 * Tapan Gandhi 18 June 25
	 * 
	 * @param path     data\\addmaster.properties
	 * @param getPut   set or get
	 * @param keyWord  executionid
	 * @param keyValue done,datetime unique
	 * @return
	 */
	public static String propsReadWrite(String path, String getPut, String keyWord, String keyValue) {
		Properties props = new Properties();
		File file = new File(path);

		if (getPut.equalsIgnoreCase("set")) { // Load existing props if file exists
			if (file.exists()) {
				try (FileInputStream fRead = new FileInputStream(file)) {
					props.load(fRead);
				} catch (Exception e) {
					System.out.println("Error loading existing props: " + e.getMessage());
					return "Error loading existing props: " + e.getMessage();
				}
			} else {
				props.setProperty("executionID", initBase.executionRunTime);
			} // Set key-value pair
			props.setProperty(keyWord, keyValue);

			try (FileOutputStream fWrite = new FileOutputStream(file)) { // Write the properties to file (create or
																			// overwrite)
				props.store(fWrite, null);
			} catch (Exception e) {
				System.out.println("Error saving props: " + e.getMessage());
				return "Error saving props: " + e.getMessage();
			}
			return "true"; // success
		}

		if (getPut.equalsIgnoreCase("get")) {
			if (!file.exists()) {
				return "";
			}
			try (FileInputStream fRead = new FileInputStream(file)) {
				props.load(fRead);
				return props.getProperty(keyWord, ""); // return value or empty string
			} catch (Exception e) {
				System.out.println("Error reading props: " + e.getMessage());
				return "Error reading props: " + e.getMessage();
			}
		}

		return "";
	}

	/**
	 * Tapan Gandhi 18 June 25
	 * 
	 * @return
	 */
	public static String getExecutionID() {
		String uniq = Utils.propsReadWrite("data/addmaster.properties", "get", "executionID", "");
		try {
			if (uniq.length() == 12) {
				String date1 = initBase.executionRunTime.substring(0, 6);
				String date2 = uniq.substring(0, 6);
				long days = 0;
				try {
					days = getDateDifference(date1, date2);
				} catch (Exception e) {
				}
				int nthresld = Integer.parseInt(initBase.holdNewData);
				if (days <= nthresld) {
					initBase.executionRunTime = uniq;
					initBase.stopNewData = true;
					System.out.println("Continuing with Existing Master Data due to threshold value: " + date1 + " - "
							+ date2 + " - Threshold : " + nthresld + ", with Exec. ID: " + initBase.executionRunTime);
				} else {
					try {
						boolean deleted = Files.deleteIfExists(Path.of("data", "addmaster.properties"));
						if (deleted) {
							System.out.println("Deleted successfully");
						} else {
							System.out.println("File not found");
						}
					} catch (IOException e) {
						System.err.println("Deletion failed: " + e.getMessage());
					}

					uniq = new SimpleDateFormat("ddMMyyHHmmss").format(new Date());
					initBase.executionRunTime = uniq;
					Utils.propsReadWrite("data/addmaster.properties", "set", "executionID", uniq);
					System.out.println("Created NEW EXEC ID IN data/addmaster.properties as Exceeds Threshold value: "
							+ date1 + " - " + date2 + " - ThreSld: " + nthresld + ", new Exec. ID: "
							+ initBase.executionRunTime);
				}
			} else {
				Files.deleteIfExists(Path.of("data", "addmaster.properties"));
				System.out.println("Deleted data\\addmaster.properties file.");
			}
		} catch (Exception e) {
			System.out.println("Error while getExecutionID :" + e.getMessage());
		}
		return uniq;
	}

	/**
	 * Tapan Gandhi 18 June 25
	 */
	static long getDateDifference(String date1, String date2) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");

		LocalDate d1 = LocalDate.parse(date1, formatter);
		LocalDate d2 = LocalDate.parse(date2, formatter);

		return ChronoUnit.DAYS.between(d2, d1); // positive or negative
	}

	public static boolean safeClick(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(element));

			// Scroll into view with some padding (center alignment)
			((JavascriptExecutor) driver)
					.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
			// Small wait to allow for scroll animation (optional)
			Thread.sleep(300);
			element.click();
		} catch (Exception e) {
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				System.out.println("Fallback JS click performed.");
			} catch (Exception ex) {
				System.err.println("Click failed even with JS: " + ex.getMessage());
				return false;
			}
		}
		return true;
	}

	/**
	 * Tapan July 25
	 * 
	 */
	public static void enableDualLogging() {
		try {
			String repTime = (GlobalSetup.getReportTime());
			// Create data directory if it doesn't exist
			File logDir = new File(initBase.reportFld + repTime + "-" + initBase.allmapPubVar.get("suiteName"));
			if (!logDir.exists()) {
				logDir.mkdirs();
			}
			// Define log file
			File logFile = new File(logDir, "sysout" + repTime + initBase.allmapPubVar.get("suiteName") + ".log");
			FileOutputStream fos = new FileOutputStream(logFile, true); // append mode

			// Create custom OutputStream that writes to both console and file
			OutputStream dualStream = new OutputStream() {
				private final PrintStream console = System.out;
				private final OutputStream file = fos;

				@Override
				public void write(int b) throws IOException {
					console.write(b);
					file.write(b);
				}

				@Override
				public void flush() throws IOException {
					console.flush();
					file.flush();
				}

				@Override
				public void close() throws IOException {
					console.close();
					file.close();
				}
			}; // Set System.out to write to both console and file
			PrintStream ps = new PrintStream(dualStream, true);
			System.setOut(ps);
			System.setErr(ps); // Optional: redirect errors too
			System.out.println(">> Console logging redirected to data/console.log");
		} catch (Exception e) {
			System.out.println("Issue while creating sysout to dual mode.");
		}
	}

	/**
	 * this method reads a value from a properties file. Author: Tapan Gandhi
	 * 
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String getConfigValue(String fileName, String key) {
		File configFile = new File(fileName);
		String value = "";
		if (!configFile.exists()) {
			return "";
		}
		try (FileInputStream fis = new FileInputStream(configFile)) {
			Properties props = new Properties();
			props.load(fis);
			value = props.getProperty(key);
			if (value == null) {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
		return value;
	}

	/**
	 * This method writes or updates a key-value pair in a properties file. If the
	 * file doesn't exist, it will be created. If the key already exists, its value
	 * will be updated.
	 */
	public static void writeToPropsFile(String filePath, String key, String value) {
		Properties props = new Properties();
		File file = new File(filePath);

		try { // If file exists, load existing properties
			if (file.exists()) {
				try (FileInputStream fis = new FileInputStream(file)) {
					props.load(fis);
				}
			} else { // Ensure parent directory exists
				file.getParentFile().mkdirs();
				file.createNewFile();
			} // Set / update property
			props.setProperty(key, value);
			// Store back
			try (FileOutputStream fos = new FileOutputStream(file)) {
				props.store(fos, new Date().toString());
			}
		} catch (Exception e) {
			System.out.println("writeToPropsFile Error: " + e.getMessage());
		}
	}

	/**
	 * Builds a full path by combining two or more directory or file parts. Example:
	 * buildPath("C:\\base", "run123", "allure-report"); Tapan Oct 25
	 * 
	 * @param pathParts One or more parts of the path (must include at least one)
	 * @return Combined Path object
	 * @throws IllegalArgumentException if no parts are provided
	 */
	public static Path getAbsolutePath(String... pathParts) {
		if (pathParts == null || pathParts.length == 0) {
			throw new IllegalArgumentException("At least one path part must be provided");
		}
		// Ensure no null elements
		for (String part : pathParts) {
			Objects.requireNonNull(part, "Path part cannot be null");
		}
		return Paths.get(pathParts[0], java.util.Arrays.copyOfRange(pathParts, 1, pathParts.length));
	}

	/**
	 * Kill HUB & NODE if stuck
	 */
	public static void killHUBNODE(String HubIP) {
//		powershell.exe -Command "Invoke-Command -ComputerName desktop-1tbu5i -Credential (New-Object System.Management.Automation.PSCredential('.\Administrator', (ConvertTo-SecureString 'Mantra@123@123' -AsPlainText -Force))) -ScriptBlock { cmd.exe /c 'C:\sw\SeleniumGrid\CloseOrphanCmds.bat > C:\sw\temp.log 2>&1' }"
		List<String> command = new ArrayList<>();
		command.add("powershell.exe");
		command.add("-Command");
		if (HubIP.contains(".82")) {
			command.add(
					"Invoke-Command -ComputerName desktop-1tbu5i -Credential (New-Object System.Management.Automation.PSCredential('.\\Administrator', (ConvertTo-SecureString 'Mantra@123' -AsPlainText -Force))) -ScriptBlock { cmd.exe /c 'C:\\sw\\SeleniumGrid\\CloseOrphanCmds.bat > C:\\sw\\temp.log 2>&1' }");
		} else if (HubIP.contains(".231")) {
			command.add(
					"Invoke-Command -ComputerName vm-225 -Credential (New-Object System.Management.Automation.PSCredential('.\\Administrator', (ConvertTo-SecureString 'Mantra@123' -AsPlainText -Force))) -ScriptBlock { cmd.exe /c 'C:\\sw\\SeleniumGrid\\CloseOrphanCmds.bat > C:\\sw\\temp.log 2>&1' }");
		} else if (HubIP.contains(".243")) {
			command.add(
					"Invoke-Command -ComputerName admin -Credential (New-Object System.Management.Automation.PSCredential('.\\Administrator', (ConvertTo-SecureString 'Mantra@123' -AsPlainText -Force))) -ScriptBlock { cmd.exe /c 'C:\\sw\\SeleniumGrid\\CloseOrphanCmds.bat > C:\\sw\\temp.log 2>&1' }");
		}
		Utils.runCommandPrompt(command, "");
		command.clear();
		command.add("powershell.exe");
		command.add("-Command");
		if (HubIP.contains(".82")) {
			command.add(
					"Invoke-Command -ComputerName desktop-1tbu5i -Credential (New-Object System.Management.Automation.PSCredential('.\\Administrator', (ConvertTo-SecureString 'Mantra@123' -AsPlainText -Force))) -ScriptBlock { cmd.exe /c 'C:\\sw\\SeleniumGrid\\Hub-NodeStart.bat > C:\\sw\\temp.log 2>&1' }");
		} else if (HubIP.contains(".231")) {
			command.add(
					"Invoke-Command -ComputerName vm-225 -Credential (New-Object System.Management.Automation.PSCredential('.\\Administrator', (ConvertTo-SecureString 'Mantra@123' -AsPlainText -Force))) -ScriptBlock { cmd.exe /c 'C:\\sw\\SeleniumGrid\\Hub-NodeStart.bat > C:\\sw\\temp.log 2>&1' }");
		} else if (HubIP.contains(".243")) {
			command.add(
					"Invoke-Command -ComputerName admin -Credential (New-Object System.Management.Automation.PSCredential('.\\Administrator', (ConvertTo-SecureString 'Mantra@123' -AsPlainText -Force))) -ScriptBlock { cmd.exe /c 'C:\\sw\\SeleniumGrid\\Hub-NodeStart.bat > C:\\sw\\temp.log 2>&1' }");
		}
		Utils.runCommandPrompt(command, "");
	}

	/**
	 * Check if a Selenium Grid node is ready by querying its /status endpoint.
	 * 
	 * @param hubNodeIP The IP address or URL of the Selenium Grid node (e.g.,
	 */
	public static boolean isHubNodeReady(String hubNodeIP, int timeoutSeconds) {
		String statusUrl = hubNodeIP.replace("/wd/hub", "") + "/status";
		long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000);

		while (System.currentTimeMillis() < endTime) {
			try {
				URL url = new URL(statusUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(3000); // 3 seconds timeout for each attempt
				conn.setReadTimeout(3000);
				conn.setRequestMethod("GET");

				int responseCode = conn.getResponseCode();
				if (responseCode == 200) {
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String inputLine;
					StringBuilder response = new StringBuilder();
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					if (response.toString().contains("Selenium Grid ready")) {
						return true;
					}
					System.out.println("Node not ready yet, response: " + response);
				}
			} catch (Exception e) {
				// Optionally log the retry here
			}
			try {
				Thread.sleep(1000); // Wait 1 second before retrying
			} catch (InterruptedException e) {
				// Ignore
			}
		}
		return false;
	}

	/**
	 * Hides the keyboard on Android devices if the driver is an instance of
	 * AndroidDriver.
	 * 
	 * @param driver The WebDriver instance to check and hide the keyboard on.
	 */
	public void hideKeyboardIfAndroid(WebDriver driver) {
		if (driver instanceof AndroidDriver) {
			try {
				((AndroidDriver) driver).hideKeyboard();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Sets the device orientation to Portrait or Landscape. Works for both Android
	 * and iOS drivers.
	 *
	 * @param driver The active AppiumDriver or WebDriver instance
	 * @param mode   Either "portrait" or "landscape" (case-insensitive)
	 */
	public static void setDeviceOrientation(AppiumDriver driver, String mode) {
		if (driver == null) {
			System.out.println("⚠️ Driver is null, cannot change orientation.");
			return;
		}
		try {
			ScreenOrientation orientation;
			switch (mode.toLowerCase()) {
			case "landscape":
				orientation = ScreenOrientation.LANDSCAPE;
				break;
			case "portrait":
				orientation = ScreenOrientation.PORTRAIT;
				break;
			default:
				System.out.println("⚠️ Invalid orientation mode: " + mode + ". Use 'portrait' or 'landscape'.");
				return;
			}
		} catch (Exception e) {
			System.out.println("setDeviceOrientation: " + e.getMessage());
		}
	}
}// END of Utils class
