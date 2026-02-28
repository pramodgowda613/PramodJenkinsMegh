package com.MeghPI.Attendance.pages;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

public class MeghPITotalLeavesTakenReportPage {

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
    public String HolidayPolicyName = "";
    public String actualholiday = "";
    public String Defaultholiday = "";
    public String getempid = "";
    public String getemplastname = "";
	
	public MeghPITotalLeavesTakenReportPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//p[text()='Total Leaves Taken']")
	private WebElement TotalLeavesTakenReportButton; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//input[@aria-controls='dtTotalLeaveReport']")
	private WebElement TotalLeavesTakenReportSearchTextField; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr[1]/td[5]")
	private WebElement TotalLeavesTakenReportTotalDaysResult; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr[1]/td[3]")
	private WebElement TotalLeavesTakenReportLeaveFromResult; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr[1]/td[4]")
	private WebElement TotalLeavesTakenReportLeaveToDateResult; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr[1]/td[7]")
	private WebElement TotalLeavesTakenReportLeaveStatusResult; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr[1]/td[2]")
	private WebElement TotalLeavesTakenReportLeaveNameResult; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement TotalLeavesTakenReportLeaveTakenEmpNameResult; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//a[@id='TotalLeaveReport_Tab'][1]")
	private WebElement TotalLeavesTakenReportAllTab; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//label[text()='LeaveType']")
	private WebElement TotalLeavesTakenReportLeaveTypeCheckbox; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//label[text()='TotalDays']")
	private WebElement TotalLeavesTakenReportTotalDaysCheckbox; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemStatusdtTotalLeaveReport']")
	private WebElement TotalLeavesTakenReportStatusCheckbox; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//label[text()='Reason']")
	private WebElement TotalLeavesTakenReportLeaveReasonCheckbox; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr[1]/td[6]")
	private WebElement TotalLeavesTakenReportLeaveReason; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "(//button[@id='btnFilterTotalLeaveReport'])[2]")
	private WebElement TotalLeavesTakenReportFilterButton; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//a[@id='TotalLeaveReport_Tab'][2]")
	private WebElement TotalLeavesTakenReportApprovedTab; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr/td[1]/div/div/p[1]")
	private List<WebElement> ListOfEmpNameRows; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr/td[3]")
	private List<WebElement> ListOfAppliedLeavesDateRows; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr/td[7]")
	private List<WebElement> ListOfAppliedLeavesStatusRows; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//a[@id='TotalLeaveReport_Tab'][3]")
	private WebElement TotalLeavesTakenReportRejectedTab; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//a[@id='TotalLeaveReport_Tab'][4]")
	private WebElement TotalLeavesTakenReportPendingTab; //1stTestCase TotalLeavesTakenReport
	
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr/td[5]")
	private List<WebElement> TotalLeavesTakenReportTotalDaysResultList; //1stTestCase TotalLeavesTakenReport
	
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr[1]/td[1]")
	private WebElement TotalLeavesTakenReportLeaveNameResultOnEmp; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr[1]/td[4]")
	private WebElement TotalLeavesTakenReportTotalDaysResultOnEmp; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr/td[4]")
	private List<WebElement> TotalLeavesTakenReportTotalDaysResultListOnEmp; //1stTestCase TotalLeavesTakenReport
	
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr[1]/td[6]")
	private WebElement TotalLeavesTakenReportLeaveStatusResultOnEmp; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr[1]/td[5]")
	private WebElement TotalLeavesTakenReportLeaveReasonOnEmp; //1stTestCase TotalLeavesTakenReport
	
	@FindBy(xpath = "//table[@id='dtTotalLeaveReport']/tbody/tr[1]/td[2]")
	private WebElement TotalLeavesTakenReportLeaveFromResultOnEmp; //1stTestCase TotalLeavesTakenReport
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean  TotalLeavesTakenReportButton()
	{
		try {
		Thread.sleep(2009);
		
			utils.waitForEle(TotalLeavesTakenReportButton, "visible", "", 15);
			return Utils.safeClick(driver, TotalLeavesTakenReportButton);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
	}
	
	
	public boolean  TotalLeavesTakenReportAllTab()
	{
		try {
		
			utils.waitForEle(TotalLeavesTakenReportAllTab, "visible", "", 30);
			TotalLeavesTakenReportAllTab.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  TotalLeavesTakenReportEmpNameRowDisplay()
	{
		try {
		
			utils.waitForEle(TotalLeavesTakenReportLeaveTakenEmpNameResult, "visible", "", 30);
			driver.navigate().refresh();
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  TotalLeavesTakenReportSearchTextField(String empname)
	{
		try {
		
			utils.waitForEle(TotalLeavesTakenReportSearchTextField, "visible", "", 15);
			TotalLeavesTakenReportSearchTextField.clear();
			TotalLeavesTakenReportSearchTextField.sendKeys(empname);
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean LeaveStatusDateStatusValidation(String name, String leaveName, String fromDate, String toDate, String totalDays, String status) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportLeaveTakenEmpNameResult, "visible", "", 30);

	        // Convert input date (yyyy-MM-dd) to UI format (EEE, dd-MM-yyyy)
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);

	        String formattedFromDate = LocalDate.parse(fromDate, inputFormatter).format(uiFormatter);
	        String formattedToDate = LocalDate.parse(toDate, inputFormatter).format(uiFormatter);

	        // Validation checks
	        if (!TotalLeavesTakenReportLeaveTakenEmpNameResult.getText().contains(name)) {
	            throw new Exception("Employee Name mismatch. Expected: " + name);
	        }

	        if (!TotalLeavesTakenReportLeaveNameResult.getText().contains(leaveName)) {
	            throw new Exception("Leave Type mismatch. Expected: " + leaveName);
	        }

	        if (!TotalLeavesTakenReportLeaveFromResult.getText().contains(formattedFromDate)) {
	            throw new Exception("From Date mismatch. Expected: " + formattedFromDate);
	        }

	        if (!TotalLeavesTakenReportLeaveToDateResult.getText().contains(formattedToDate)) {
	            throw new Exception("To Date mismatch. Expected: " + formattedToDate);
	        }

	        if (!TotalLeavesTakenReportTotalDaysResult.getText().contains(totalDays)) {
	            throw new Exception("Total Days mismatch. Expected: " + totalDays);
	        }

	        if (!TotalLeavesTakenReportLeaveStatusResult.getText().contains(status)) {
	            throw new Exception("Status mismatch. Expected: " + status);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	
	public boolean  TotalLeavesTakenReportLeaveTypeCheckbox()
	{
		try {
		
			utils.waitForEle(TotalLeavesTakenReportLeaveTypeCheckbox, "visible", "", 30);
			TotalLeavesTakenReportLeaveTypeCheckbox.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  TotalLeavesTakenReportTotalDaysCheckbox()
	{
		try {
		
			utils.waitForEle(TotalLeavesTakenReportTotalDaysCheckbox, "visible", "", 30);
			TotalLeavesTakenReportTotalDaysCheckbox.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  TotalLeavesTakenReportStatusCheckbox()
	{
		try {
		
			utils.waitForEle(TotalLeavesTakenReportStatusCheckbox, "visible", "", 30);
			TotalLeavesTakenReportStatusCheckbox.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  TotalLeavesTakenReportLeaveReasonCheckbox()
	{
		try {
		
			utils.waitForEle(TotalLeavesTakenReportLeaveReasonCheckbox, "visible", "", 30);
			TotalLeavesTakenReportLeaveReasonCheckbox.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean TotalLeavesTakenReportLeaveNameResultValidation(String leavename) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportLeaveNameResult, "visible", "", 30);

	        String actualLeaveName = TotalLeavesTakenReportLeaveNameResult.getText().trim();

	        if (!actualLeaveName.contains(leavename)) {
	            throw new Exception("Leave Name mismatch. Expected: " + leavename + ", Found: " + actualLeaveName);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	public boolean TotalLeavesTakenReportTotalDaysResultValidation(String leavename) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportTotalDaysResult, "visible", "", 30);

	        boolean matchFound = false;

	        for (WebElement totalLeaves : TotalLeavesTakenReportTotalDaysResultList) {
	            String actualLeaveName = totalLeaves.getText().trim();

	            if (actualLeaveName.contains(leavename)) {
	                matchFound = true;
	                break;
	            }
	        }

	        if (!matchFound) {
	            throw new Exception("Leave TotalDays mismatch. Expected leave: " + leavename + 
	                                " but not found in UI list.");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	
	public boolean TotalLeavesTakenReportLeaveStatusResultValidation(String leavename) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportLeaveStatusResult, "visible", "", 30);

	        String actualLeaveName = TotalLeavesTakenReportLeaveStatusResult.getText().trim();

	        if (!actualLeaveName.contains(leavename)) {
	            throw new Exception("Leave Status mismatch. Expected: " + leavename + ", Found: " + actualLeaveName);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean TotalLeavesTakenReportLeaveReasonValidation(String leavename) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportLeaveReason, "visible", "", 30);

	        String actualLeaveName = TotalLeavesTakenReportLeaveReason.getText().trim();

	        if (!actualLeaveName.contains(leavename)) {
	            throw new Exception("Leave Reason mismatch. Expected: " + leavename + ", Found: " + actualLeaveName);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	
	public boolean  TotalLeavesTakenReportFilterButton()
	{
		try {
		
			utils.waitForEle(TotalLeavesTakenReportFilterButton, "visible", "", 15);
			TotalLeavesTakenReportFilterButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean FilteredDateValidation(String year, String month) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportLeaveFromResult, "visible", "", 30);

	        // Convert month name (e.g., "October") to month number ("10")
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
	        Month monthEnum = Month.from(inputFormatter.parse(month));
	        String monthNumber = String.format("%02d", monthEnum.getValue()); // "10" for October

	        // Expected substring pattern like "-10-2025"
	        String expectedPattern = "-" + monthNumber + "-" + year;

	        String actualDate = TotalLeavesTakenReportLeaveFromResult.getText().trim();

	        if (!actualDate.contains(expectedPattern)) {
	            throw new Exception("Date mismatch: expected month/year '" + expectedPattern + "', found '" + actualDate + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean LeavesTakenRptFilteredDateValidation(String date) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportLeaveFromResult, "visible", "", 30);

	        // Convert input date (yyyy-MM-dd) to UI format (dd-MM-yyyy)
	        LocalDate inputDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String formattedDate = inputDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	        // Get the actual date text from UI
	        String actualDate = TotalLeavesTakenReportLeaveFromResult.getText().trim();

	        if (!actualDate.contains(formattedDate)) {
	            throw new Exception("Date mismatch: expected '" + formattedDate + "', but found '" + actualDate + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean  TotalLeavesTakenReportApprovedTab()
	{
		try {
		
			utils.waitForEle(TotalLeavesTakenReportApprovedTab, "visible", "", 15);
			TotalLeavesTakenReportApprovedTab.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ApprovedLeaveValidation(String empName, String date, String status) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportLeaveTakenEmpNameResult, "visible", "", 15);

	        // Convert input date (yyyy-MM-dd) → UI format (EEE, dd-MM-yyyy)
	        LocalDate inputDate = LocalDate.parse(date);
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);
	        String formattedDate = inputDate.format(uiFormatter);

	        int rowCount = ListOfEmpNameRows.size();
	        System.out.println("=== ApprovedLeaveValidation Debug Info ===");
	        System.out.println("Expected → Emp: " + empName + ", Date: " + formattedDate + ", Status: " + status);
	        System.out.println("Total rows found in UI: " + rowCount);

	        boolean matchFound = false;

	        for (int i = 0; i < rowCount; i++) {
	            String actualEmp = ListOfEmpNameRows.get(i).getText().trim();
	            String actualDate = ListOfAppliedLeavesDateRows.get(i).getText().trim();
	            String actualStatus = ListOfAppliedLeavesStatusRows.get(i).getText().trim();

	            System.out.println("Row " + (i + 1) + " → Emp: " + actualEmp 
	                               + " | Date: " + actualDate 
	                               + " | Status: " + actualStatus);

	            // Compare while ignoring weekday text prefix if UI differs slightly
	            String cleanedActualDate = actualDate.replaceAll("^[A-Za-z]{3},\\s*", "").trim(); // removes "Thu, "

	            if (actualEmp.contains(empName)
	                    && (actualDate.contains(formattedDate) || cleanedActualDate.contains(formattedDate.replaceAll("^[A-Za-z]{3},\\s*", "")))
	                    && actualStatus.contains(status)) {

	                System.out.println("✅ Match found at row " + (i + 1));
	                matchFound = true;
	                break;
	            }
	        }

	        if (!matchFound) {
	            throw new Exception("No matching record found for Emp: " + empName
	                    + ", Date: " + date + " (formatted as " + formattedDate + ")"
	                    + ", Status: " + status);
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        System.err.println("❌ Exception in ApprovedLeaveValidation: " + e.getMessage());
	        return false;
	    }
	}

	public boolean  TotalLeavesTakenReportRejectedTab()
	{
		try {
		
			utils.waitForEle(TotalLeavesTakenReportRejectedTab, "visible", "", 15);
			TotalLeavesTakenReportRejectedTab.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  TotalLeavesTakenReportPendingTab()
	{
		try {
		
			utils.waitForEle(TotalLeavesTakenReportPendingTab, "visible", "", 15);
			TotalLeavesTakenReportPendingTab.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean TotalLeavesTakenReportLeaveNameResultOnEmp(String leavename) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportLeaveNameResultOnEmp, "visible", "", 30);

	        String actualLeaveName = TotalLeavesTakenReportLeaveNameResultOnEmp.getText().trim();

	        if (!actualLeaveName.contains(leavename)) {
	            throw new Exception("Leave Name mismatch. Expected: " + leavename + ", Found: " + actualLeaveName);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	public boolean TotalLeavesTakenReportTotalDaysResultOnEmp(String leavename) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportTotalDaysResultOnEmp, "visible", "", 30);

	        boolean matchFound = false;

	        for (WebElement totalLeaves : TotalLeavesTakenReportTotalDaysResultListOnEmp) {
	            String actualLeaveName = totalLeaves.getText().trim();

	            if (actualLeaveName.contains(leavename)) {
	                matchFound = true;
	                break;
	            }
	        }

	        if (!matchFound) {
	            throw new Exception("Leave TotalDays mismatch. Expected leave: " + leavename + 
	                                " but not found in UI list.");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	
	public boolean TotalLeavesTakenReportLeaveStatusResultOnEmp(String leavename) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportLeaveStatusResultOnEmp, "visible", "", 30);

	        String actualLeaveName = TotalLeavesTakenReportLeaveStatusResultOnEmp.getText().trim();

	        if (!actualLeaveName.contains(leavename)) {
	            throw new Exception("Leave Status mismatch. Expected: " + leavename + ", Found: " + actualLeaveName);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean TotalLeavesTakenReportLeaveReasonOnEmp(String leavename) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportLeaveReasonOnEmp, "visible", "", 30);

	        String actualLeaveName = TotalLeavesTakenReportLeaveReasonOnEmp.getText().trim();

	        if (!actualLeaveName.contains(leavename)) {
	            throw new Exception("Leave Reason mismatch. Expected: " + leavename + ", Found: " + actualLeaveName);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	
	public boolean LeavesTakenRptFilteredDateValidationOnEmp(String date) {
	    try {
	        utils.waitForEle(TotalLeavesTakenReportLeaveFromResultOnEmp, "visible", "", 30);

	        // Convert input date (yyyy-MM-dd) to UI format (dd-MM-yyyy)
	        LocalDate inputDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        String formattedDate = inputDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	        // Get the actual date text from UI
	        String actualDate = TotalLeavesTakenReportLeaveFromResultOnEmp.getText().trim();

	        if (!actualDate.contains(formattedDate)) {
	            throw new Exception("Date mismatch: expected '" + formattedDate + "', but found '" + actualDate + "'");
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
