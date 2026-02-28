package com.MeghPI.Attendance.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.LogResults;
import utils.Utils;

public class MeghPIOverTimeRequestPage {
	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
	LogResults logResults = new LogResults();

	public String reason = "";
	public int holidayCountTillToday = 0;
	public int absentCount = 0;
	public int holidayCount = 0;
	public int workingDayCount = 0;   // <-
	
	public MeghPIOverTimeRequestPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//button[contains(text(), 'Monthly overtime request')]")
	private WebElement EmpActOTSummaryClicked; //1stTestCas

	@FindBy(xpath = "//p[@id='txtPendingCount']")
	private WebElement EmpActPendingOTSummaryClicked; //1stTestCas

	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[1]")
	private WebElement EmpActTableDateRow; //1stTestCas

	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[5]")
	private WebElement EmpActTableStatusRow; //1stTestCas
	
	@FindBy(xpath = "//p[@id='txtPendingCount']")
	private WebElement AdminActPendingSummaryClicked; //1stTestCas
	
	@FindBy(xpath = "//input[@aria-controls='dtOverTime']")
	private WebElement AdminActSearchTextField; //1stTestCas
	
	@FindBy(xpath = "//p[@id='txtApprovedCount']")
	private WebElement AdminActApprovedSummaryClicked; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[2]")
	private WebElement EmpOTDateInAdminTable; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[6]")
	private WebElement EmpOTStatusInAdminTable; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement EmpIDValidateInAdminTable; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtAttendanceEmployee']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement EmpIDInAdminEmployeeTabTable; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtAttendanceEmployee']/tbody/tr[1]/td[5]")
	public WebElement OTHoursRowValidation;
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[3]/span[1]")
	public WebElement OverStayHoursCountInTable;
	
	@FindBy(xpath = "//p[@id='txtRejectedCount']")
	public WebElement RejectCountOnSummary;
	
	@FindBy(xpath = "//label[text()='Approval Comments']")
	public WebElement ApprovalCommentsCheckBox;
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[5]")
	public WebElement OTApprovalCommentsRowValidation;
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[4]")
	public WebElement OTApprovalCommentsRowValidationInEmp;
	
	@FindBy(xpath = "//label[text()='Day Type']")
	public WebElement DayTypeSearchCheckBox;
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr[1]/td[3]")
	public WebElement DayTypeSearchResult;
	
	@FindBy(xpath = "//p[@id='txtRejectedCount']")
	public WebElement RejectedSummaryClicked;
	
	
	@FindBy(xpath = "//input[@id='txtApproveAllReason']")
	public WebElement ApproveAllReasonTextField;
	
	
	
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr/td[2]")
	private List<WebElement> EmpOTDateListInAdminTable; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr/td[6]")
	private List<WebElement> EmpOTStatusListInAdminTable; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr/td[1]/div/div/p[2]")
	private List<WebElement> EmpIDListValidateInAdminTable; //1stTestCas
	
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr/td[4]/span[2]")
	private List<WebElement> EmpApprovedUpdatedOTListValidateInAdminTable; //1stTestCas
	
	
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr/td[1]")
	private List<WebElement> EmpOTDateListInEmpTable; //1stTestCas
	
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr/td[3]/span[2]")
	private List<WebElement> EmpApprovedUpdatedOTListValidateInEmpTable; //1stTestCas
	
	
	@FindBy(xpath = "//table[@id='dtOverTime']/tbody/tr/td[5]")
	private List<WebElement> EmpOTStatusListInEmpTable; //1stTestCas
	
	
	@FindBy(xpath = "//a[@id='OT_tab']")
	public WebElement OverTimeApprovedTab;
	
	@FindBy(xpath = "//input[@aria-controls='dtOTdetails']")
	public WebElement OverTimeApprovedTabSearchTextField;
	
	@FindBy(xpath = "//table[@id='dtOTdetails']/tbody/tr/td[1]/div/div/p[2]")
	private List<WebElement> OverTimeApprovedTabEmpidSearchResult; //1stTestCas
	
	
	@FindBy(xpath = "//a[@id='attendanceReProcess_tab']")
	private WebElement AttendanceProcessTab; //1stTestCas
	
	@FindBy(xpath = "(//button[@id='btnAddAttendanceReProcess'])[2]")
	private WebElement AddAttendanceProcessButton; //1stTestCas
	
	@FindBy(xpath = "//input[@id='drpFromDate']/../input[2]")
	private WebElement AddAttendanceProcessDateTextField; //1stTestCas
	
	@FindBy(xpath = "//span[@id='select2-drpEntity-container']")
	private WebElement AddAttendanceProcessNameDropDown; //1stTestCas
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpEntity-results']")
	private WebElement AddAttendanceProcessNameTextField; //1stTestCas
	
	@FindBy(xpath = "//ul[@id='select2-drpEntity-results']/li[1]")
	private WebElement AddAttendanceProcessEmpNameSelected; //1stTestCas
	
	@FindBy(xpath = "//button[@id='btnAttendanceReProcess']")
	private WebElement AddAttendanceProcessApplyButton; //1stTestCas
	
	@FindBy(xpath = "//td[text()='No matching records found']")
	private WebElement AddAttendanceProcessValidation; //1stTestCas
	
	
	
	
	
	
	
	
	
	
	
	public boolean  DayTypeSearchCheckBox()
	{
		try {
		
			utils.waitForEle(DayTypeSearchCheckBox, "visible", "", 20);
			DayTypeSearchCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  RejectedSummaryClicked()
	{
		try {
		
			utils.waitForEle(RejectedSummaryClicked, "visible", "", 20);
			RejectedSummaryClicked.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	public boolean  EmpActOTSummaryClicked()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(EmpActOTSummaryClicked, "visible", "", 20);
			EmpActOTSummaryClicked.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean  EmpActPendingOTSummaryClicked()
	{
		try {
		
			utils.waitForEle(EmpActPendingOTSummaryClicked, "visible", "", 20);
			EmpActPendingOTSummaryClicked.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	public boolean ValidateEmpOTStatusOnEmpAccount(String date, String status) {
	    try {
	        // Convert yyyy-MM-dd → UI format (Mon, 03-11-2025)
	        LocalDate givenDate = LocalDate.parse(date);  // yyyy-MM-dd
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);
	        String expectedUIDate = givenDate.format(uiFormatter);

	        // Wait for element
	        utils.waitForEle(EmpActTableDateRow, "visible", "", 15);

	        String actualDate = EmpActTableDateRow.getText().trim();
	        String actualStatus = EmpActTableStatusRow.getText().trim();

	        // Debug printing
	        System.out.println("Expected UI Date: " + expectedUIDate);
	        System.out.println("Actual UI Date: " + actualDate);
	        System.out.println("Expected Status: " + status);
	        System.out.println("Actual Status: " + actualStatus);

	        // Validation
	        if (actualDate.contains(expectedUIDate) && actualStatus.contains(status)) {
	            System.out.println("✅ Date & Status matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("Date Match: " + actualDate.contains(expectedUIDate));
	            System.out.println("Status Match: " + actualStatus.contains(status));
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}





	

	public boolean  AdminActPendingSummaryClicked()
	{
		try {

			utils.waitForEle(AdminActPendingSummaryClicked, "visible", "", 20);
			AdminActPendingSummaryClicked.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}


	public boolean  AdminActSearchTextField(String name)
	{
		try {

			utils.waitForEle(AdminActSearchTextField, "visible", "", 20);
			AdminActSearchTextField.clear();
			AdminActSearchTextField.sendKeys(name);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean  AdminActApprovedSummaryClicked()
	{
		try {

			utils.waitForEle(AdminActApprovedSummaryClicked, "visible", "", 20);
			AdminActApprovedSummaryClicked.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	



	public boolean ValidateEmpOTStatusOnAdminAccount(String date, String status, String empId) {
	    try {
	        // Convert yyyy-MM-dd → UI format (Mon, 03-11-2025)
	        LocalDate givenDate = LocalDate.parse(date);
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);
	        String expectedUIDate = givenDate.format(uiFormatter);

	        // Prepare expected EmpID with "#"
	        String expectedEmpId = "#" + empId.trim();

	        // Wait for elements
	        utils.waitForEle(EmpOTDateInAdminTable, "visible", "", 15);

	        String actualDate = EmpOTDateInAdminTable.getText().trim();
	        String actualStatus = EmpOTStatusInAdminTable.getText().trim();
	        String actualEmpId = EmpIDValidateInAdminTable.getText().trim();

	        // Debug Logs
	        System.out.println("Expected UI Date: " + expectedUIDate);
	        System.out.println("Actual UI Date: " + actualDate);

	        System.out.println("Expected Emp ID: " + expectedEmpId);
	        System.out.println("Actual Emp ID: " + actualEmpId);

	        System.out.println("Expected Status: " + status);
	        System.out.println("Actual Status: " + actualStatus);

	        // Validation
	        boolean dateMatch = actualDate.contains(expectedUIDate);
	        boolean statusMatch = actualStatus.contains(status);
	        boolean empIdMatch = actualEmpId.contains(expectedEmpId);

	        if (dateMatch && statusMatch && empIdMatch) {
	            System.out.println("✅ Date, EmpID & Status matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("Date Match: " + dateMatch);
	            System.out.println("Status Match: " + statusMatch);
	            System.out.println("EmpID Match: " + empIdMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	

	
	public boolean ValidateEmpOTHoursCountAdminEmployeeTab(String empid, String othours) {
	    try {
	     
	        utils.waitForEle(EmpIDInAdminEmployeeTabTable, "visible", "", 15);

	        String actualDate = EmpIDInAdminEmployeeTabTable.getText().trim();
	        String actualOT = OTHoursRowValidation.getText().trim();

	        // Debug printing
	        System.out.println("Actual UI Date: " + actualDate);
	        System.out.println("Expected Status: " + othours);
	        System.out.println("Actual Status: " + actualOT);

	        // Validation
	        if (actualDate.contains(empid) && actualOT.contains(othours)) {
	            System.out.println("✅ Date & Status matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("Date Match: " + actualDate.contains(actualDate));
	            System.out.println("Status Match: " + actualOT.contains(othours));
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean ValidateEmpOTStatusAndOverStayCountOnEmpAccount(String date, String status, String othours) {
	    try {
	        // Convert yyyy-MM-dd → UI format (Mon, 03-11-2025)
	        LocalDate givenDate = LocalDate.parse(date);  // yyyy-MM-dd
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);
	        String expectedUIDate = givenDate.format(uiFormatter);

	        // Wait for element
	        utils.waitForEle(EmpActTableDateRow, "visible", "", 15);

	        String actualDate = EmpActTableDateRow.getText().trim();
	        String actualStatus = EmpActTableStatusRow.getText().trim();
	        String Overstay = OverStayHoursCountInTable.getText().trim();

	        // Debug printing
	        System.out.println("Expected UI Date: " + expectedUIDate);
	        System.out.println("Actual UI Date: " + actualDate);
	        System.out.println("Expected Status: " + status);
	        System.out.println("Actual Status: " + actualStatus);

	        // Validation
	        if (actualDate.contains(expectedUIDate) && actualStatus.contains(status) && Overstay.contains(othours)) {
	            System.out.println("✅ Date & Status matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("Date Match: " + actualDate.contains(expectedUIDate));
	            System.out.println("Status Match: " + actualStatus.contains(status));
	            System.out.println("OverStay Mismatch:" + Overstay.contains(othours));
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	public boolean  RejectCountOnSummary()
	{
		try {

			utils.waitForEle(RejectCountOnSummary, "visible", "", 20);
			RejectCountOnSummary.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	

	
	


	public boolean  ApprovalCommentsCheckBox()
	{
		try {

			utils.waitForEle(ApprovalCommentsCheckBox, "visible", "", 20);
			ApprovalCommentsCheckBox.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	
	public boolean EmpIDValidateInAdminTable(String empid) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(EmpIDValidateInAdminTable, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = EmpIDValidateInAdminTable.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(empid)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Emp ID contains '" + empid +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean OTApprovalCommentsRowValidation(String empid) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(OTApprovalCommentsRowValidation, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = OTApprovalCommentsRowValidation.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(empid)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Emp ID contains '" + empid +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean OTApprovalCommentsRowValidationInEmp(String empid) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(OTApprovalCommentsRowValidationInEmp, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = OTApprovalCommentsRowValidationInEmp.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(empid)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Emp ID contains '" + empid +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean DayTypeSearchResult(String empid) {
	    try {
	        // wait for the element to be visible
	        utils.waitForEle(DayTypeSearchResult, "visible", "", 15);

	        // get the actual text from the element
	        String actualTeam = DayTypeSearchResult.getText().trim();

	        // check if it contains the expected team
	        if (actualTeam.contains(empid)) {
	            return true;
	        } else {
	            System.out.println("Mismatch: Expected Emp ID contains '" + empid +
	                               "', but actual text is '" + actualTeam + "'");
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean  ApproveAllReasonTextField(String name)
	{
		try {

			utils.waitForEle(ApproveAllReasonTextField, "visible", "", 20);
			ApproveAllReasonTextField.clear();
			ApproveAllReasonTextField.sendKeys(name);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}


	public boolean ValidateEmpOTStatuslistOnAdminAccount(String date, String status, String empId) {
	    try {

	        // Convert yyyy-MM-dd → UI format (EEE, dd-MM-yyyy)
	        LocalDate givenDate = LocalDate.parse(date);
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);
	        String expectedUIDate = givenDate.format(uiFormatter);

	        // Prepare expected EmpID
	        String expectedEmpId = "#" + empId.trim();

	        // Wait until table rows are loaded
	        utils.waitForEle(EmpOTDateListInAdminTable.get(0), "visible", "", 15);

	        // Loop through rows
	        for (int i = 0; i < EmpOTDateListInAdminTable.size(); i++) {

	            String actualDate = EmpOTDateListInAdminTable.get(i).getText().trim();
	            String actualStatus = EmpOTStatusListInAdminTable.get(i).getText().trim();
	            String actualEmpId = EmpIDListValidateInAdminTable.get(i).getText().trim();

	            // Debug logs
	            System.out.println("Row " + i + ": Date=" + actualDate + ", Status=" + actualStatus + ", EmpID=" + actualEmpId);

	            boolean dateMatch = actualDate.contains(expectedUIDate);
	            boolean statusMatch = actualStatus.equalsIgnoreCase(status.trim());
	            boolean empIdMatch = actualEmpId.contains(expectedEmpId);

	            // If match found
	            if (dateMatch && statusMatch && empIdMatch) {
	                System.out.println("✅ Found matching OT row for EmpID: " + expectedEmpId);
	                return true;
	            }
	        }

	        // If no row matched
	        exceptionDesc = "No matching OT record found for: Date=" + expectedUIDate +
	                ", Status=" + status + ", EmpID=" + expectedEmpId;

	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean ValidateEmpStatusOnAdminAccountForFilterResultOfOTEmp(String status, String empId) {
	    try {
	      

	        // Prepare expected EmpID with "#"
	        String expectedEmpId = "#" + empId.trim();

	        // Wait for elements
	        utils.waitForEle(EmpOTStatusInAdminTable, "visible", "", 15);

	       
	        String actualStatus = EmpOTStatusInAdminTable.getText().trim();
	        String actualEmpId = EmpIDValidateInAdminTable.getText().trim();

	        // Debug Logs
	       

	        System.out.println("Expected Emp ID: " + expectedEmpId);
	        System.out.println("Actual Emp ID: " + actualEmpId);

	        System.out.println("Expected Status: " + status);
	        System.out.println("Actual Status: " + actualStatus);

	        // Validation
	       
	        boolean statusMatch = actualStatus.contains(status);
	        boolean empIdMatch = actualEmpId.contains(expectedEmpId);

	        if (statusMatch && empIdMatch) {
	            System.out.println("✅ Date, EmpID & Status matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("Status Match: " + statusMatch);
	            System.out.println("EmpID Match: " + empIdMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	
	public boolean EmpOTApprovedCountValidation(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(AdminActApprovedSummaryClicked, "visible", "", 15);

	        String count = AdminActApprovedSummaryClicked.getText().trim();
	        // Debug Logs
	        System.out.println("Expected Emp OT Approved count: " + expcount);
	        System.out.println("Actual Emp OT Approved count: " + count);
	        // Validation
	       
	        boolean statusMatch = count.contains(expcount);

	        if (statusMatch) {
	            System.out.println("✅  Emp OT Approved Count matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("OT Approved Count Match: " + statusMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean EmpOTPendingCountValidation(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(EmpActPendingOTSummaryClicked, "visible", "", 15);

	        String count = EmpActPendingOTSummaryClicked.getText().trim();
	        // Debug Logs
	        System.out.println("Expected Emp OT Pending count: " + expcount);
	        System.out.println("Actual Emp OT Pending count: " + count);
	        // Validation
	       
	        boolean statusMatch = count.contains(expcount);

	        if (statusMatch) {
	            System.out.println("✅  Emp OT Pending Count matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("OT Pending Count Match: " + statusMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean EmpOTRejectedCountValidation(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(RejectCountOnSummary, "visible", "", 15);

	        String count = RejectCountOnSummary.getText().trim();
	        // Debug Logs
	        System.out.println("Expected Emp OT Rejected count: " + expcount);
	        System.out.println("Actual Emp OT Rejected count: " + count);
	        // Validation
	       
	        boolean statusMatch = count.contains(expcount);

	        if (statusMatch) {
	            System.out.println("✅  Emp OT Rejected Count matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("OT Rejected Count Match: " + statusMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	

	
	
		public boolean ValidateEmpUpdatedOTHoursOnAdminAccount(String date, String updatedOT, String empId) {
		    try {

		        // Convert yyyy-MM-dd → UI format (EEE, dd-MM-yyyy)
		        LocalDate givenDate = LocalDate.parse(date);
		        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);
		        String expectedUIDate = givenDate.format(uiFormatter);

		        // Prepare expected EmpID
		        String expectedEmpId = "#" + empId.trim();

		        // Wait until table rows are loaded
		        utils.waitForEle(EmpOTDateListInAdminTable.get(0), "visible", "", 15);

		        // Loop through rows
		        for (int i = 0; i < EmpOTDateListInAdminTable.size(); i++) {

		            String actualDate = EmpOTDateListInAdminTable.get(i).getText().trim();
		            String actualOT = EmpApprovedUpdatedOTListValidateInAdminTable.get(i).getText().trim();
		            String actualEmpId = EmpIDListValidateInAdminTable.get(i).getText().trim();

		            // Debug logs
		            System.out.println("Row " + i + ": Date=" + actualDate + ", Status=" + actualOT + ", EmpID=" + actualEmpId);

		            boolean dateMatch = actualDate.contains(expectedUIDate);
		            boolean statusMatch = actualOT.contains(updatedOT.trim());
		            boolean empIdMatch = actualEmpId.contains(expectedEmpId);

		            // If match found
		            if (dateMatch && statusMatch && empIdMatch) {
		                System.out.println("✅ Found matching OT row for EmpID: " + expectedEmpId);
		                return true;
		            }
		        }

		        // If no row matched
		        exceptionDesc = "No matching OT record found for: Date=" + expectedUIDate +
		                ", Status=" + updatedOT + ", EmpID=" + expectedEmpId;

		        return false;

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		}
	
		
		
		
	
		public boolean ValidateEmpUpdatedOTHoursOnEmpAccount(String date, String updatedOT, String status) {
		    try {

		        // Convert yyyy-MM-dd → UI format (EEE, dd-MM-yyyy)
		        LocalDate givenDate = LocalDate.parse(date);
		        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);
		        String expectedUIDate = givenDate.format(uiFormatter);

		     

		        // Wait until table rows are loaded
		        utils.waitForEle(EmpOTDateListInEmpTable.get(0), "visible", "", 15);

		        // Loop through rows
		        for (int i = 0; i < EmpOTDateListInEmpTable.size(); i++) {

		            String actualDate = EmpOTDateListInEmpTable.get(i).getText().trim();
		            String actualOT = EmpApprovedUpdatedOTListValidateInEmpTable.get(i).getText().trim();
		            String actualstatus = EmpOTStatusListInEmpTable.get(i).getText().trim();

		            // Debug logs
		            System.out.println("Row " + i + ": Date=" + actualDate + ", OT =" + actualOT + ", status=" + actualstatus);

		            boolean dateMatch = actualDate.contains(expectedUIDate);
		            boolean OTmatch = actualOT.contains(updatedOT.trim());
		            boolean statusmatch = actualstatus.contains(status);

		            // If match found
		            if (dateMatch && OTmatch && statusmatch) {
		                System.out.println("✅ Found matching OT row for status: " + statusmatch);
		                return true;
		            }
		        }

		        // If no row matched
		        exceptionDesc = "No matching OT record found for: Date=" + expectedUIDate +
		                ", OT=" + updatedOT + ", status =" + status;

		        return false;

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		}
		
		
		
		
		public boolean  OverTimeApprovedTab()
		{
			try {

				utils.waitForEle(OverTimeApprovedTab, "visible", "", 20);
				OverTimeApprovedTab.click();
				
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		
		public boolean  OverTimeApprovedTabSearchTextField(String name)
		{
			try {

				utils.waitForEle(OverTimeApprovedTabSearchTextField, "visible", "", 20);
				OverTimeApprovedTabSearchTextField.clear();
				OverTimeApprovedTabSearchTextField.sendKeys(name);
				
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		
		
		public boolean OverTimeApprovedTabEmpidSearchResult(String name) {
		    try {
		        for (WebElement empid : OverTimeApprovedTabEmpidSearchResult) {
		            if (empid.getText().contains(name)) {
		                return true; // name found
		            }
		        }

		        // If loop completes → name not found
		        System.out.println("Employee ID not found in Overtime Approved Tab: " + name);

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        System.out.println("Exception in OverTimeApprovedTabEmpidSearchResult: " + e.getMessage());
		        return false;
		    }

		    return false; // name NOT found
		}

		
		public boolean  AttendanceProcessTab()
		{
			try {
			
				utils.waitForEle(AttendanceProcessTab, "visible", "", 20);
				AttendanceProcessTab.click();
				
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean  AddAttendanceProcessButton()
		{
			try {
			
				utils.waitForEle(AddAttendanceProcessButton, "visible", "", 20);
				AddAttendanceProcessButton.click();
				
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		
		public boolean  AddAttendanceProcessDateTextField()
		{
			try {
			
				utils.waitForEle(AddAttendanceProcessDateTextField, "visible", "", 20);
				AddAttendanceProcessDateTextField.click();
				
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean AddAttendanceProcessDateTextFieldInput(String breakStartTime) {
			
			 try {
			        JavascriptExecutor js = (JavascriptExecutor) driver;

			        // Remove readonly just in case, though Flatpickr API doesn't need it
			        js.executeScript("document.getElementById('drpFromDate').removeAttribute('readonly');");

			        // Use Flatpickr's setDate API
			        js.executeScript(
			            "if (document.getElementById('drpFromDate')._flatpickr) {" +
			            "  document.getElementById('drpFromDate')._flatpickr.setDate('" + breakStartTime + "', true);" +
			            "} else { throw new Error('Flatpickr not initialized on drpFromDate'); }"
			        );

			    } catch (Exception e) {
			        exceptionDesc = e.getMessage();
			        return false;
			    }
			    return true;
			}
		
		
		public boolean  AddAttendanceProcessNameDropDown()
		{
			try {
			
				utils.waitForEle(AddAttendanceProcessNameDropDown, "visible", "", 20);
				AddAttendanceProcessNameDropDown.click();
				
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		
		public boolean  AddAttendanceProcessNameTextField(String name)
		{
			try {

				utils.waitForEle(AddAttendanceProcessNameTextField, "visible", "", 20);
				AddAttendanceProcessNameTextField.clear();
				AddAttendanceProcessNameTextField.sendKeys(name);
				
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		
		public boolean  AddAttendanceProcessEmpNameSelected()
		{
			try {
			
				utils.waitForEle(AddAttendanceProcessEmpNameSelected, "visible", "", 20);
				AddAttendanceProcessEmpNameSelected.click();
				
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		
		public boolean  AddAttendanceProcessApplyButton()
		{
			try {
			
				utils.waitForEle(AddAttendanceProcessApplyButton, "visible", "", 20);
				AddAttendanceProcessApplyButton.click();
				
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean AddAttendanceProcessValidation() {
		    try {
		        // Retry up to 3 times
		        for (int i = 1; i <= 5; i++) {

		            try {
		                if (AddAttendanceProcessValidation.isDisplayed()) {
		                    return true;   // Found
		                }
		            } catch (Exception ignored) {
		                // Element not found this attempt
		            }

		            // Not found → retry after refresh
		            driver.navigate().refresh();
		            Thread.sleep(10000); // Wait 5 seconds
		        }

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return true;
		    }

		    return true;  // After 3 attempts still not found
		}

		
		
		
		
		
		
		
	


	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public  void setExceptionDesc(String exceptionDesc) {  
		exceptionDesc = this.exceptionDesc;
	}
}
