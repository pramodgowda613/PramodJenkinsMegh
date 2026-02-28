package com.MeghPI.Attendance.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

public class MeghPILeaveBalanceSummaryReportPage {

	WebDriver driver;
	private  String exceptionDesc;
	Utils utils = new Utils(driver);
    public String HolidayPolicyName = "";
    public String actualholiday = "";
    public String Defaultholiday = "";
    public String getempid = "";
    public String getemplastname = "";
	
	public MeghPILeaveBalanceSummaryReportPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//p[text()='Leave Balance Summary']")
	private WebElement LeaveBalanceSummaryReportButton; //1stTestCase LeaveBalanceSummaryReport
	
	@FindBy(xpath = "//input[@aria-controls='dtLeaveBalanceReport']")
	private WebElement LeaveBalanceSummaryReportSearchTextField; //1stTestCase LeaveBalanceSummaryReport

	@FindBy(xpath = "//table[@id='dtLeaveBalanceReport']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement LeaveBalanceSummaryReportEmpIDSearchResult; //1stTestCase LeaveBalanceSummaryReport
	
	@FindBy(xpath = "//table[@id='dtLeaveBalanceReport']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement LeaveBalanceSummaryReportEmpNameSearchResult; //1stTestCase LeaveBalanceSummaryReport
	
	@FindBy(xpath = "(//button[@id='btnFilterLeaveBalanceReport'])[2]")
	private WebElement LeaveBalanceSummaryReportFilterButton; //1stTestCase LeaveBalanceSummaryReport
	
	@FindBy(xpath = "//table[@id='dtLeaveBalanceReport']/tbody/tr[1]/td[3]")
	private WebElement LeaveBalanceSummaryReportLeaveTakenCount; //1stTestCase LeaveBalanceSummaryReport
	
	@FindBy(xpath = "//a[@id='leaveBalance_tab']")
	private WebElement LeaveBalanceTab; //1stTestCase LeaveBalanceSummaryReport
	
	@FindBy(xpath = "//input[@aria-controls='dtLeaveBalance']")
	private WebElement LeaveBalanceSearchTextField; //1stTestCase LeaveBalanceSummaryReport
	
	@FindBy(xpath = "//button[@id='btnEditLeaveBalance']")
	private WebElement LeaveBalanceEditLeaveBalanceButton; //1stTestCase LeaveBalanceSummaryReport
	
	@FindBy(xpath = "//label[@id='Add']")
	private WebElement LeaveBalanceAddLeaveCheckBox; //1stTestCase LeaveBalanceSummaryReport
	
	@FindBy(xpath = "//input[@id='txtAddLeaveBalance']")
	private WebElement LeaveBalanceAddLeaveTextField; //1stTestCase LeaveBalanceSummaryReport
	
	@FindBy(xpath = "//button[@id='btnLeaveBalanceChange']")
	private WebElement LeaveBalanceAddLeaveSaveChanges; //1stTestCase LeaveBalanceSummaryReport
	
	@FindBy(xpath = "//label[@id='Deduct']")
	private WebElement LeaveBalanceDeductLeaveCheckBox; //1stTestCase LeaveBalanceSummaryReport
	
	@FindBy(xpath = "//input[@id='txtDeductLeaveBalance']")
	private WebElement LeaveBalanceDeductLeaveTextField; //1stTestCase LeaveBalanceSummaryReport
	
	
	
	
	
	public boolean  LeaveBalanceSummaryReportButton()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(LeaveBalanceSummaryReportButton, "visible", "", 15);
			return Utils.safeClick(driver, LeaveBalanceSummaryReportButton);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	public boolean  LeaveBalanceSummaryReportSearchTextField(String locationName)
	{
		try {
			utils.waitForEle(LeaveBalanceSummaryReportSearchTextField, "visible", "", 15);
			LeaveBalanceSummaryReportSearchTextField.clear();
			LeaveBalanceSummaryReportSearchTextField.sendKeys(locationName);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveBalanceSummaryReportEmpIDSearchResult() {
	    try {
	        utils.waitForEle(LeaveBalanceSummaryReportEmpIDSearchResult, "visible", "", 10);

	        String fullText = LeaveBalanceSummaryReportEmpIDSearchResult.getText(); // e.g., "#Emp008"
	        getempid = fullText.split("#")[1]; // directly get "Emp008"

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return true;
	}
	
	public boolean LeaveBalanceSummaryReportEmpNameSearchResult() {
	    try {
	        utils.waitForEle(LeaveBalanceSummaryReportEmpNameSearchResult, "visible", "", 10);

	        String getemplastnames = LeaveBalanceSummaryReportEmpNameSearchResult.getText().trim();
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
	
	
	public boolean LeaveBalanceSummaryReportEmpIDRecordValidation(String empid) {
	    try {
	        utils.waitForEle(LeaveBalanceSummaryReportEmpIDSearchResult, "visible", "", 10);
	        
	        String actualText = LeaveBalanceSummaryReportEmpIDSearchResult.getText().trim();
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

	public boolean LeaveBalanceSummaryReportEmpNameRecordValidation(String empname) {
	    try {
	        utils.waitForEle(LeaveBalanceSummaryReportEmpNameSearchResult, "visible", "", 10);
	        
	        String actualText = LeaveBalanceSummaryReportEmpNameSearchResult.getText().trim();
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
	
	public boolean  LeaveBalanceSummaryReportRowResult()
	{
		try {
			utils.waitForEle(LeaveBalanceSummaryReportEmpNameSearchResult, "visible", "", 30);
			LeaveBalanceSummaryReportEmpNameSearchResult.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  LeaveBalanceSummaryReportFilterButton()
	{
		try {
			utils.waitForEle(LeaveBalanceSummaryReportFilterButton, "visible", "", 15);
			LeaveBalanceSummaryReportFilterButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean LeaveBalanceSummaryReportLeaveTakenCount(String expectedCount) {
	    try {
	        utils.waitForEle(LeaveBalanceSummaryReportLeaveTakenCount, "visible", "", 15);

	        String actualCount = LeaveBalanceSummaryReportLeaveTakenCount.getText().trim();

	        // Log actual vs expected for debugging
	        System.out.println("DEBUG: Expected Leave Taken Count: " + expectedCount + ", Actual: " + actualCount);

	        if (!actualCount.equals(expectedCount)) {
	            throw new Exception("Leave Taken Count mismatch. Expected: " + expectedCount + ", Found: " + actualCount);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	
	public boolean verifyLeaveTypeValue(String leaveTypeName, String expectedValue) {
	    try {
	        // Locate the Leave Balance Report table
	        WebElement table = driver.findElement(By.id("dtLeaveBalanceReport"));

	        // 1️⃣ Find all headers
	        List<WebElement> headers = table.findElements(By.xpath(".//thead/tr/th"));
	        int targetColIndex = -1;
	        WebElement targetHeader = null;

	        // 2️⃣ Identify the column index for the given leave type
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase(leaveTypeName)) {
	                targetColIndex = i + 1; // XPath is 1-based
	                targetHeader = headers.get(i);
	                break;
	            }
	        }

	        if (targetColIndex == -1 || targetHeader == null) {
	            throw new Exception("Leave Type '" + leaveTypeName + "' not found in table header!");
	        }

	        // 3️⃣ Scroll to the header to make sure it's visible
	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", targetHeader);
	        Thread.sleep(1000);

	        // 4️⃣ Locate the corresponding cell (whole button text)
	        WebElement targetCell = table.findElement(By.xpath(
	                ".//tbody/tr[1]/td[" + targetColIndex + "]//button[contains(@class,'edit_leave')]"));

	        // Scroll the cell into view for safety
	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", targetCell);
	        Thread.sleep(500);

	        // 5️⃣ Get full text from the cell — includes both red and green spans (e.g., "2/365")
	        String actualValue = targetCell.getText().trim().replaceAll("\\s+", "");

	        // 6️⃣ Compare with expected value
	        System.out.println("DEBUG: Leave Type = " + leaveTypeName +
	                           " | Expected = " + expectedValue +
	                           " | Actual = " + actualValue);

	        if (!actualValue.equals(expectedValue)) {
	            throw new Exception("Mismatch for '" + leaveTypeName +
	                                "'. Expected: " + expectedValue +
	                                ", Found: " + actualValue);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}



	
	public boolean  LeaveBalanceTab()
	{
		try {
			utils.waitForEle(LeaveBalanceTab, "visible", "", 15);
			LeaveBalanceTab.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LeaveBalanceSearchTextField(String empname)
	{
		try {
			utils.waitForEle(LeaveBalanceSearchTextField, "visible", "", 15);
			LeaveBalanceSearchTextField.clear();
			LeaveBalanceSearchTextField.sendKeys(empname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean clickEditIconForLeaveType(String leaveTypeName) {
	    try {
	        // Locate the main table
	        WebElement table = driver.findElement(By.id("dtLeaveBalance"));

	        // 1️⃣ Find all headers
	        List<WebElement> headers = table.findElements(By.xpath(".//thead/tr/th"));
	        int targetColIndex = -1;
	        WebElement targetHeader = null;

	        // 2️⃣ Identify the header column index matching leaveTypeName
	        for (int i = 0; i < headers.size(); i++) {
	            String headerText = headers.get(i).getText().trim();
	            if (headerText.equalsIgnoreCase(leaveTypeName)) {
	                targetColIndex = i + 1; // XPath uses 1-based indexing
	                targetHeader = headers.get(i);
	                break;
	            }
	        }

	        if (targetColIndex == -1 || targetHeader == null) {
	            throw new Exception("Leave Type '" + leaveTypeName + "' not found in table header!");
	        }

	        // 3️⃣ Scroll horizontally to bring that column into view
	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});",
	                targetHeader);
	        Thread.sleep(1000); // small pause to ensure scroll is complete
	        LeaveBalanceEditLeaveBalanceButton.click();
	        Thread.sleep(2000);
	        // 4️⃣ Locate the corresponding edit button inside the first row for that column
	        WebElement editIcon = table.findElement(By.xpath(
	                ".//tbody/tr[1]/td[" + targetColIndex + "]//img[contains(@class,'imgEditLeaveBalance')]"));

	        // 5️⃣ Scroll that cell into view too (for some UI frameworks)
	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", editIcon);
	        Thread.sleep(500);

	        // 6️⃣ Click the edit icon
	        editIcon.click();

	        System.out.println("Clicked edit icon for Leave Type: " + leaveTypeName);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	public boolean  LeaveBalanceAddLeaveCheckBox()
	{
		try {
			utils.waitForEle(LeaveBalanceAddLeaveCheckBox, "visible", "", 15);
			LeaveBalanceAddLeaveCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LeaveBalanceAddLeaveTextField(String leavecount)
	{
		try {
			utils.waitForEle(LeaveBalanceAddLeaveTextField, "visible", "", 15);
			LeaveBalanceAddLeaveTextField.clear();
			LeaveBalanceAddLeaveTextField.sendKeys(leavecount);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LeaveBalanceAddLeaveSaveChanges()
	{
		try {
			utils.waitForEle(LeaveBalanceAddLeaveSaveChanges, "visible", "", 15);
			LeaveBalanceAddLeaveSaveChanges.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LeaveBalanceDeductLeaveTextField(String leavecount)
	{
		try {
			utils.waitForEle(LeaveBalanceDeductLeaveTextField, "visible", "", 15);
			LeaveBalanceDeductLeaveTextField.clear();
			LeaveBalanceDeductLeaveTextField.sendKeys(leavecount);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  LeaveBalanceDeductLeaveCheckBox()
	{
		try {
			utils.waitForEle(LeaveBalanceDeductLeaveCheckBox, "visible", "", 15);
			LeaveBalanceDeductLeaveCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
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
