package com.MeghPI.Attendance.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

public class MeghPIOverTimeMonthlyReportPage {


	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
    public String HolidayPolicyName = "";
    public String actualholiday = "";
    public String Defaultholiday = "";
    public String getempid = "";
    public String getemplastname = "";
	
	
	
	
	public MeghPIOverTimeMonthlyReportPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//p[text()='Over Time Report']")
	private WebElement OverTimeReportMainButton; //1stTestCase All Tab

	@FindBy(xpath = "//input[@aria-controls='dtOverTimeReport']")
	private WebElement OverTimeReportSearchTextField; //1stTestCase All Tab

	@FindBy(xpath = "//table[@id='dtOverTimeReport']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement OverTimeReportEmpNameRecord; //1stTestCase All Tab

	@FindBy(xpath = "(//button[@id='btnFilterOverTimeReport'])[2]")
	private WebElement OverTimeMonthlyReportFilterButton; //1stTestCase All Tab


	@FindBy(xpath = "//table[@id='dtOverTimeReport']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement OverTimeReportEmpIDRecord; //1stTestCase All Tab

	@FindBy(xpath = "//table[@id='dtOverTimeReport']/thead/tr/th/div")
	private List<WebElement> DateSHeader; //1stTestCase All Tab

	@FindBy(xpath = "//table[@id='dtOverTimeReport']/tbody/tr/td")
	private List<WebElement> OTHourData; //1stTestCase All Tab
	

public boolean  OverTimeReportMainButton()
{
	try {
		utils.waitForEle(OverTimeReportMainButton, "visible", "", 15);
	
		OverTimeReportMainButton.click();
		
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

public boolean  OverTimeMonthlyReportSearchTextField(String Emp)
{
	try {
		utils.waitForEle(OverTimeReportSearchTextField, "visible", "", 15);
		OverTimeReportSearchTextField.clear();
		OverTimeReportSearchTextField.sendKeys(Emp);
		
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

public boolean  OverTimeMonthlyReportFilterButton()
{
	try {
		utils.waitForEle(OverTimeMonthlyReportFilterButton, "visible", "", 15);
	
		OverTimeMonthlyReportFilterButton.click();
		
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}

public boolean OverTimeMonthlyReportEmpIDRecord() {
    try {
        utils.waitForEle(OverTimeReportEmpIDRecord, "visible", "", 10);

        String fullText = OverTimeReportEmpIDRecord.getText(); // e.g., "#Emp008"
        getempid = fullText.split("#")[1]; // directly get "Emp008"

    } catch (Exception e) {
        exceptionDesc = e.getMessage().toString();
        return false;
    }
    return true;
}

public boolean OverTimeMonthlyReportEmpNameRecord() {
    try {
        utils.waitForEle(OverTimeReportEmpNameRecord, "visible", "", 10);

        String getemplastnames = OverTimeReportEmpNameRecord.getText().trim();
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

public boolean OverTimeReportEmpIDRecordValidation(String empid) {
    try {
        utils.waitForEle(OverTimeReportEmpIDRecord, "visible", "", 10);
        
        String actualText = OverTimeReportEmpIDRecord.getText().trim();
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

public boolean OverTimeReportEmpNameRecordValidation(String empname) {
    try {
        utils.waitForEle(OverTimeReportEmpNameRecord, "visible", "", 10);
        
        String actualText = OverTimeReportEmpNameRecord.getText().trim();
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

public boolean OverTimeDateHeaderMatchWithTableData(String expectedDate, String expectedOTHours) {
    try {
        // Extract day from expected date (e.g., 2025-10-02 -> "02")
        String day = expectedDate.substring(8, 10);

        // Locate all header cells (e.g., "01 Wed", "02 Thu", etc.)
        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtOverTimeReport']//thead//th"));

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
                "(//table[@id='dtOverTimeReport']//tbody//tr[1]/td[" + targetColIndex + "])"
        ));

        String actualOT = otCell.getText().trim();

        // Log or compare OT data
        if (!actualOT.equals(expectedOTHours)) {
            throw new Exception("OT mismatch for date " + expectedDate + ": Expected '" + expectedOTHours + "', Found '" + actualOT + "'");
        }

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }
    return true;
}



public boolean  OverTimeReportEmpIDRecord()
{
	try {
		utils.waitForEle(OverTimeReportEmpIDRecord, "visible", "", 15);
	
		OverTimeReportEmpIDRecord.isDisplayed();
		
	} catch (Exception e) {
		exceptionDesc=	e.getMessage().toString();
		return false;
	}
	return true;
}


public boolean OverTimeDateHeaderMatchWithTableDatas(String date1, String otHours1, String date2, String otHours2) {
    try {
        // Extract day parts (e.g., 2025-10-01 -> "01")
        String day1 = date1.substring(8, 10);
        String day2 = date2.substring(8, 10);

        // Get all header columns (e.g., "01 Wed", "02 Thu", etc.)
        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='dtOverTimeReport']//thead//th"));

        int colIndex1 = -1;
        int colIndex2 = -1;

        // Identify header indexes for both dates
        for (int i = 0; i < headers.size(); i++) {
            String headerText = headers.get(i).getText().trim();
            if (headerText.startsWith(day1)) {
                colIndex1 = i + 1;
            }
            if (headerText.startsWith(day2)) {
                colIndex2 = i + 1;
            }
        }

        if (colIndex1 == -1 || colIndex2 == -1) {
            throw new Exception("Header not found for one or both dates (" + date1 + ", " + date2 + ")");
        }

        // Fetch OT cells for first employee row (modify tr index if needed)
        String baseXPath = "//table[@id='dtOverTimeReport']//tbody//tr[1]/td[";

        WebElement otCell1 = driver.findElement(By.xpath(baseXPath + colIndex1 + "]"));
        WebElement otCell2 = driver.findElement(By.xpath(baseXPath + colIndex2 + "]"));

        String actualOT1 = otCell1.getText().trim();
        String actualOT2 = otCell2.getText().trim();

        // Compare values
        if (!actualOT1.equals(otHours1)) {
            throw new Exception("OT mismatch for date " + date1 + ": Expected '" + otHours1 + "', Found '" + actualOT1 + "'");
        }

        if (!actualOT2.equals(otHours2)) {
            throw new Exception("OT mismatch for date " + date2 + ": Expected '" + otHours2 + "', Found '" + actualOT2 + "'");
        }

    } catch (Exception e) {
        exceptionDesc = e.getMessage();
        return false;
    }

    return true;
}

















public String getExceptionDesc() {
	return this.exceptionDesc;
}

public  void setExceptionDesc(String exceptionDesc) {  
	exceptionDesc = this.exceptionDesc;
}

}
