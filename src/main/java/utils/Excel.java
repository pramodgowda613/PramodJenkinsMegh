package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import base.initBase;

/**
 * Tapan Gandhi
 * 12/12/2021
 */
public class Excel {
	// ExcelSettings variables
	String testenv;
	String reportName;
	String browser;
	String sleep;
	String screenShot;
	int SkipCount, explicitWait;
	String implicitlyWait, URL, pageLoadTimeout;
	String mySQLString;
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	HashMap<String, Integer> hashMapTestCaseData = new HashMap<String, Integer>();
	String allSheetNames = "";

	/**
	 * Loads all the settings required for the driver
	 */
	public void readSettingsExcel(String projName) {
		try {
			if (Objects.isNull(fis) != true) {
				return;
			}
			if (verifyInputfile(initBase.currDir + "reports", "") == false) {
				System.out.println("Report Directory not found, Can't continue...");
				infoBox("Report Directory not found, Can't continue...", "Error.");
				System.exit(0);
			}
			if (verifyInputfile(initBase.currDir + "data\\Project.xlsx") == false) {
				System.out.println("Configuration file not found, Can't continue...");
				infoBox("Configuration file not found, Can't continue...", "Error.");
				System.exit(0);
			}
			if (verifyInputfile(initBase.currDir + "data\\" + projName + "TestData.xlsx") == false) {
				System.out.println("Project test data file not found, Can't continue...");
				infoBox("Project file not found, Can't continue...", "Error.");
				System.exit(0);
			}
			fis = new FileInputStream(initBase.currDir + "data\\" + projName + "TestData.xlsx");
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(workbook.getSheetIndex("settings"));
			int i = 1, j = 1;
			browser = sheet.getRow(i++).getCell(j).getStringCellValue();
			URL = sheet.getRow(i++).getCell(j).getStringCellValue();
			sleep = sheet.getRow(i++).getCell(j).getStringCellValue();
			testenv = sheet.getRow(i++).getCell(j).getStringCellValue();
			pageLoadTimeout = sheet.getRow(i++).getCell(j).getStringCellValue();
			implicitlyWait = sheet.getRow(i++).getCell(j).getStringCellValue();
			reportName = sheet.getRow(i++).getCell(j).getStringCellValue();
			screenShot = sheet.getRow(i++).getCell(j).getStringCellValue();
			SkipCount = Integer.parseInt(sheet.getRow(i++).getCell(j).getStringCellValue());
			explicitWait = Integer.parseInt(sheet.getRow(i++).getCell(j).getStringCellValue());
			mySQLString = sheet.getRow(i++).getCell(j).getStringCellValue();
			sheet = workbook.getSheetAt(workbook.getSheetIndex("device"));
			try {
				for (int nrow = 0; nrow < 10; nrow++) {
					row = sheet.getRow(nrow + 1);
					String temp = sheet.getRow(nrow + 1).getCell(0).getStringCellValue();
					if (temp.isEmpty())
						break;
					for (int jcol = 0; jcol < 10; jcol++) {
						cell = row.getCell(jcol);
						initBase.arrDevices[nrow][jcol] = getCellValueAsString(cell);
					}
				}
			} catch (Exception e) {
			}
			if (initBase.arrDevices.length < 1) {
				throw new Exception("Devices information missing");
			}
		} catch (Exception e) {
			System.out.println("Fatal Error while reading Excel Settings: " + e.getMessage());
			System.exit(1);
		}
	}

	/**
	 * @param cell
	 * @return
	 */
	private String getCellValueAsString(XSSFCell cell) {
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			} else {
				int intValue = (int) Math.floor(cell.getNumericCellValue());
				return String.valueOf(intValue);
			}
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
			return "";
		default:
			return "Unknown Cell Type";
		}
	}

	/**
	 * Java hashmap for storing all the test cases value as key and values pair
	 */
	/**
	 * @param sheetName
	 * @param tcName
	 * @param fieldNames
	 * @return
	 */
	public ArrayList<String> loadExcelData(String sheetName, String tcName, String fieldNames) {
		ArrayList<String> cellValues = new ArrayList<String>();
		try {
			sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
			int i = 1, j = 1, k;
			if (allSheetNames.contains(sheetName) == false) {
				// Retrieve all the test case names into HashMap.
				allSheetNames = allSheetNames + sheetName + ",";
				j = sheet.getLastRowNum();
				String excelTCName = "";
				for (i = 1; i <= j; i++) {
					try {
						excelTCName = sheet.getRow(i).getCell(0).getStringCellValue();
						if (excelTCName.length() < 2)
							continue;
						hashMapTestCaseData.put(excelTCName, i);
					} catch (Exception e) {
						System.out.println("Error while storing TCNames: " + e.getMessage());
					} // Add Scenario name in future if required.
				}
			}
			// Retrieve all the values based on the required columns
			i = hashMapTestCaseData.get(tcName);
			int noOfColumns = sheet.getRow(0).getLastCellNum();
			Boolean found = false;
			String columnName;
			String[] temp = fieldNames.split(",");
			for (k = 0; k < temp.length; k++) {
				found = false;
				String col = testenv + "_" + temp[k]; // Take the current Environment and club with the passed column
														// name from @Test Method
				for (j = 1; j <= noOfColumns; j++) {
					try {
						columnName = sheet.getRow(0).getCell(j).getStringCellValue();
						if (col.equals(columnName)) {
							String rowvalue = "";
							try {
								rowvalue = sheet.getRow(i).getCell(j).getStringCellValue();
							} catch (Exception e) {
							}
							cellValues.add(rowvalue);
							found = true;
							break;
						}
					} catch (Exception e) {
						System.out.println("Error while reading excel : " + e.getMessage());
					}
				}
				if (found != true) {
					cellValues.add("");
					System.out.println("No values found in Excel Data for column : " + col);
				}
			}
		} catch (Exception e) {
			System.out.println("Error while reading excel : " + e.getMessage());
		}
		return cellValues;
	}

	/**
	 * This function verifies file exist on the given path
	 * 
	 */
	public boolean verifyInputfile(String filename) {
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

	private boolean verifyInputfile(String filename, String directory) {
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

	/**
	 * @param infoMessage
	 * @param titleBar
	 */
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
} // Excel END
