package com.MeghPI.Attendance.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;
public class MeghPILateArrivalReportsPage {


	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
    public String HolidayPolicyName = "";
    public String actualholiday = "";
    public String Defaultholiday = "";
    public String getempid = "";
    public String getemplastname = "";
	
	
	
	
	public MeghPILateArrivalReportsPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//p[text()='Late Arrival Report']")
	private WebElement LateArrivalReportButton; //1stTestCase lateArrivalReport Tab
	
	@FindBy(xpath = "//input[@aria-controls='dtLateArrivalReport']")
	private WebElement LateArrivalReportSearchTextField; //1stTestCase lateArrivalReport Tab
	
	@FindBy(xpath = "(//button[@id='btnFilterLateArrivalReport'])[2]")
	private WebElement LateArrivalReportFilterButton; //1stTestCase lateArrivalReport Tab
	
	@FindBy(xpath = "//table[@id='dtLateArrivalReport']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement LateArrivalReportEmpIDRecord; //1stTestCase lateArrivalReport Tab
	
	@FindBy(xpath = "//table[@id='dtLateArrivalReport']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement LateArrivalReportEmpNameRecord; //1stTestCase lateArrivalReport Tab
	
	
	
	
	
	public boolean  LateArrivalReportButton()
	{
		try {
			utils.waitForEle(LateArrivalReportButton, "visible", "", 15);
		
			LateArrivalReportButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean  LateArrivalReportSearchTextField(String Emp)
	{
		try {
			utils.waitForEle(LateArrivalReportSearchTextField, "visible", "", 15);
			LateArrivalReportSearchTextField.clear();
			LateArrivalReportSearchTextField.sendKeys(Emp);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  LateArrivalReportFilterButton()
	{
		try {
			utils.waitForEle(LateArrivalReportFilterButton, "visible", "", 15);
		
			LateArrivalReportFilterButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LateArrivalMonthlyReportEmpIDRecord() {
	    try {
	        utils.waitForEle(LateArrivalReportEmpIDRecord, "visible", "", 10);

	        String fullText = LateArrivalReportEmpIDRecord.getText(); // e.g., "#Emp008"
	        getempid = fullText.split("#")[1]; // directly get "Emp008"

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
	
	public boolean LateArrivalMonthlyReportEmpNameRecord() {
	    try {
	        utils.waitForEle(LateArrivalReportEmpNameRecord, "visible", "", 10);

	        String getemplastnames = LateArrivalReportEmpNameRecord.getText().trim();
	        // Example: "Empofive Morning shift"

	        // Split by space
	        String[] parts = getemplastnames.split(" ");
	        
	        if (parts.length >= 2) {
	            // The second word should be "Morning"
	             getemplastname = parts[1];
	            System.out.println("Extracted Shift Name: " + getemplastname);
	            return true;
	        } else {
	            exceptionDesc = "Unexpected text format: " + getemplastnames;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean LateArrivalReportEmpIDRecordValidation(String empid) {
	    try {
	        utils.waitForEle(LateArrivalReportEmpIDRecord, "visible", "", 10);
	        
	        String actualText = LateArrivalReportEmpIDRecord.getText().trim();
	        if (actualText.contains(empid)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected EmpID not found. Actual text: " + actualText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean LateArrivalReportEmpNameRecordValidation(String empname) {
	    try {
	        utils.waitForEle(LateArrivalReportEmpNameRecord, "visible", "", 10);
	        
	        String actualText = LateArrivalReportEmpNameRecord.getText().trim();
	        if (actualText.contains(empname)) {
	            return true;
	        } else {
	            exceptionDesc = "Expected Emp Name not found. Actual text: " + actualText;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	
	public boolean  LateInReportEmpIDRecord()
	{
		try {
			utils.waitForEle(LateArrivalReportEmpIDRecord, "visible", "", 15);
		
			LateArrivalReportEmpIDRecord.isDisplayed();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LateArrivalDateHeaderMatchWithTableData(String expectedDate, String expectedOTHours) {
	    try {
	        // Extract day from expected date (e.g., 2025-10-02 -> "02")
	        String day = expectedDate.substring(8, 10);

	        // Locate all header cells (e.g., "01 Wed", "02 Thu", etc.)
	        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtLateArrivalReport']//thead//th"));

	        int targetColIndex = -1;

	        // Find header that contains the day (e.g., "02 Thu")
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.startsWith(day)) {
	                targetColIndex = i + 1; // +1 for XPath 1-based index
	                break;
	            }
	        }

	        if (targetColIndex == -1) {
	            throw new Exception("Date header for day " + day + " not found in the table.");
	        }

	        // Locate OT hour cell under that date (for first employee row or as per your logic)
	        WebElement otCell = driver.findElement(By.xpath(
	                "(//table[@id='dtLateArrivalReport']//tbody//tr[1]/td[" + targetColIndex + "])"
	        ));

	        String actualOT = otCell.getText().trim();

	        // Log or compare OT data
	        if (!actualOT.equals(expectedOTHours)) {
	            throw new Exception("Late Hours mismatch for date " + expectedDate + ": Expected '" + expectedOTHours + "', Found '" + actualOT + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	
	public boolean lateInDateHeaderMatchWithTableDatas(String date1, String lateHrs1, String date2, String lateHrs2) {
	    try {
	        // Extract day parts (e.g., 2025-10-01 → "01")
	        String day1 = date1.substring(8, 10);
	        String day2 = date2.substring(8, 10);

	        // Get all header columns (e.g., "01 Wed", "02 Thu", ..., "Total")
	        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtLateArrivalReport']//thead//th"));

	        int colIndex1 = -1;
	        int colIndex2 = -1;
	        int totalColIndex = -1;

	        // Identify header indexes for both dates and "Total"
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.startsWith(day1)) colIndex1 = i + 1;
	            if (headerText.startsWith(day2)) colIndex2 = i + 1;
	            if (headerText.equalsIgnoreCase("Total")) totalColIndex = i + 1;
	        }

	        if (colIndex1 == -1 || colIndex2 == -1 || totalColIndex == -1) {
	            throw new Exception("Header not found for one of the dates or Total column (" + date1 + ", " + date2 + ")");
	        }

	        // Fetch data cells for the first employee (modify tr index if needed)
	        String baseXPath = "//table[@id='dtLateArrivalReport']//tbody//tr[1]/td[";

	        String actual1 = driver.findElement(By.xpath(baseXPath + colIndex1 + "]")).getText().trim();
	        String actual2 = driver.findElement(By.xpath(baseXPath + colIndex2 + "]")).getText().trim();
	        String actualTotal = driver.findElement(By.xpath(baseXPath + totalColIndex + "]")).getText().trim();

	        // --- Compare each date’s expected values ---
	        if (!actual1.equals(lateHrs1))
	            throw new Exception("Late-in mismatch for " + date1 + ": expected '" + lateHrs1 + "', found '" + actual1 + "'");
	        if (!actual2.equals(lateHrs2))
	            throw new Exception("Late-in mismatch for " + date2 + ": expected '" + lateHrs2 + "', found '" + actual2 + "'");

	        // --- Calculate expected total (sum of both date late hours) ---
	        String expectedTotal = addTimeStrings(lateHrs1, lateHrs2);

	        if (!expectedTotal.equals(actualTotal)) {
	            throw new Exception("Total mismatch: expected '" + expectedTotal + "', found '" + actualTotal + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	/**
	 * Utility to add time strings in HH:mm format.
	 * Example: "00:10" + "00:15" = "00:25"
	 */
	private String addTimeStrings(String time1, String time2) {
	    try {
	        String[] t1 = time1.split(":");
	        String[] t2 = time2.split(":");

	        int mins1 = Integer.parseInt(t1[0]) * 60 + Integer.parseInt(t1[1]);
	        int mins2 = Integer.parseInt(t2[0]) * 60 + Integer.parseInt(t2[1]);
	        int totalMins = mins1 + mins2;

	        int hours = totalMins / 60;
	        int mins = totalMins % 60;

	        return String.format("%02d:%02d", hours, mins);
	    } catch (Exception e) {
	        return "00:00";
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public  void setExceptionDesc(String exceptionDesc) {  
		exceptionDesc = this.exceptionDesc;
	}
	

}
