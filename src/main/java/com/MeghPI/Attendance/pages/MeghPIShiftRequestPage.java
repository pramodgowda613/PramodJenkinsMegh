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
import org.openqa.selenium.support.ui.Select;

import utils.Utils;

public class MeghPIShiftRequestPage {

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
	public String reason = "";
	
	public MeghPIShiftRequestPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//button[contains(text(), 'Monthly Shift Summary')]")
	private WebElement MonthlyShiftSummaryDropDown; //1stTestCase
	
	@FindBy(xpath = "(//a[contains(text(), 'Apply Shift Request')])[1]")
	private WebElement ApplyShiftRequest; //1stTestCase
	
	@FindBy(xpath = "//input[@id='shiftDate']/../input[2]")
	private WebElement SelectDateTextField; //1stTestCase
	
	@FindBy(xpath = "//select[@id='drpShiftPolicy']")
	private WebElement SelectShiftPolicyDropDown; //1stTestCase
	
	@FindBy(xpath = "//span[@id='select2-drpShift-container']")
	private WebElement SelectShiftDropDown; //1stTestCase
	
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpShift-results']")
	private WebElement SelectShiftSearchInput; //1stTestCase
	
	@FindBy(xpath = "//li[contains(@id, 'select2-drpShift-result')]")
	private WebElement SelectShiftFromOption; //1stTestCase
	
	@FindBy(xpath = "//input[@id='txtShiftChangeReason']")
	private WebElement ShiftReasonTextField; //1stTestCase
	
	@FindBy(xpath = "//button[@id='btnShiftChangeRequest']")
	private WebElement ShiftApplyButton; //1stTestCase
	
	@FindBy(xpath = "//p[@id='ShiftRequestPending']")
	private WebElement PendingShiftRequestCard; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr[1]/td[2]")
	private WebElement EmpActTableDateRow; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr[1]/td[7]")
	private WebElement EmpActTableStatusRow; //1stTestCas
	
	
	@FindBy(xpath = "//input[@aria-controls='dtShiftRequest']")
	private WebElement ShiftRequestSearchTextField; //1stTestCas
	
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr/td[1]/div/div/p[1]")
	private List<WebElement> ShiftRequestEmpNameList; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr/td[2]")
	private List<WebElement> ShiftRequestEmpDateRowList; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr/td[8]/div/button[2]")
	private List<WebElement> ShiftRequestEmpApproveButtonList; //1stTestCase Approved Tab
	
	
	@FindBy(xpath = "//button[@id='btnShiftApproveRequest']")
	private WebElement ShiftRequestApproveButton; //1stTestCas
	
	@FindBy(xpath = "//p[@id='ShiftRequestApprove']")
	private WebElement ApprovedShiftRequestSummaryCount; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr[1]/td[2]")
	private WebElement EmpShiftRequestDateInAdminTable; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr[1]/td[7]")
	private WebElement EmpShiftRequestStatusInAdminTable; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement ShiftRequestEmpIDValidateInAdminTable; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr/td[8]/div/button[1]")
	private List<WebElement> ShiftRequestEmpRejectButtonList; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//input[@id='txtShiftRejectReason']")
	private WebElement ShiftRequestEmpRejectReasonTextField; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//button[@id='btnShiftRejectRequest']")
	private WebElement ShiftRequestEmpRejectConfirmButton; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//p[@id='ShiftRequestReject']")
	private WebElement ShiftRequestRejectedSummaryCount; //1stTestCase Approved Tab
	
	
	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[2]")
	public List<WebElement> EmpAttendanceInTimetRow;

	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[3]")
	public List<WebElement> EmpAttendanceOutTimetRow;

	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[6]")
	public List<WebElement> EmpAttendanceShiftRow;

	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[7]")
	public List<WebElement> EmpAttendanceStatustRow;

	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[4]")
	public List<WebElement> EmpAttendanceWorkingHourstRow;
	
	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[5]")
	public List<WebElement> EmpAttendanceOThourstRow;
	
	@FindBy(xpath = "//table[@id='dtEmployeeAttendance']//tr/td[1]")
	public List<WebElement> EmpAttendanceDatetRow;
	
	
	@FindBy(xpath = "//p[@id='ShiftRequestRevoke']")
	private WebElement ShiftRequestRevokedSummaryCount; //1stTestCase Approved Tab
	
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr/td[1]")
	private List<WebElement> EmpActTableDateRowList; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr/td[6]")
	private List<WebElement> EmpActTableStatusRowList; //1stTestCas
	
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr/td[2]")
	private List<WebElement> EmpShiftRequestDateInAdminTableList; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr/td[7]")
	private List<WebElement> EmpShiftRequestStatusInAdminTableList; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr/td[1]/div/div/p[2]")
	private List<WebElement> ShiftRequestEmpIDValidateInAdminTableList; //1stTestCas
	
	
	@FindBy(xpath = "//label[text()='Enroll ID']")
	private WebElement EnrollIDSearchOption; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//label[text()='Assign Shift Policy']")
	private WebElement AssignShiftPolicySearchOption; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//label[text()='Assign Shift']")
	private WebElement AssignShiftSearchOption; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//label[text()='Applied Shift']")
	private WebElement AppliedShiftSearchOption; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemReasondtShiftRequest']")
	private WebElement ShiftRequestReasonSearchOption; //1stTestCase Approved Tab
	
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement EmpIDSearchResult; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr[1]/td[3]")
	private WebElement AssignShiftPolicySearchResult; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr[1]/td[4]")
	private WebElement AssignShiftSearchResult; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr[1]/td[5]")
	private WebElement AppliedShiftSearchResult; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtShiftRequest']/tbody/tr[1]/td[6]")
	private WebElement ReasonSearchResult; //1stTestCase Approved Tab
	
	
	@FindBy(xpath = "//button[contains(text(), 'Apply Shift Request')]")
	private WebElement ApplyShiftRequestOnAdmin; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//a[contains(text(), ' Shift request for Others')]")
	private WebElement ApplyShiftRequestForOthersOnAdmin; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//span[@id='select2-drpEntityQuickShiftOther-container']")
	private WebElement SelectEmpDropDownOnAdmin; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpEntityQuickShiftOther-results']")
	private WebElement SelectEmpSearchFieldOnAdmin; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//ul[@id='select2-drpEntityQuickShiftOther-results']/li")
	private WebElement SelectedEmpFromDropDownOnAdmin; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//input[@id='shiftDateForOther']/../input[2]")
	private WebElement SelectShiftDateForOthersOnAdmin; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//select[@id='drpShiftPolicyForOther']")
	private WebElement SelectShiftPolicyForOthersOnAdmin; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//span[@id='select2-drpShiftForOther-container']")
	private WebElement SelectShiftDropDownForOthersOnAdmin; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpShiftForOther-results']")
	private WebElement SelectShiftSearchFieldForOthersOnAdmin; //1stTestCase Approved Tab
	
	
	@FindBy(xpath = "//li[contains(@id, 'select2-drpShiftForOther-result')]")
	private WebElement SelectShiftResultForOthersOnAdmin; //1stTestCase Approved 
	
	@FindBy(xpath = "//input[@id='txtShiftChangeReasonForOther']")
	private WebElement ShiftReasonForOthersOnAdmin; //1stTestCase Approved 
	
	@FindBy(xpath = "//button[@id='btnShiftChangeRequestForOther']")
	private WebElement ShiftRequestApplyButtonForOthers; //1stTestCase Approved 
	
	@FindBy(xpath = "//a[contains(text(), ' Shift request for Me')]")
	private WebElement ShiftRequestApplyButtonForMe; //1stTestCase Approved 
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean ShiftRequestApplyButtonForMe() {
		try {

			utils.waitForEle(ShiftRequestApplyButtonForMe, "visible", "", 30);
			ShiftRequestApplyButtonForMe.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean ShiftRequestRevokedSummaryCount() {
		try {

			utils.waitForEle(ShiftRequestRevokedSummaryCount, "visible", "", 30);
			ShiftRequestRevokedSummaryCount.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean MonthlyShiftSummaryDropDown() {
		try {
Thread.sleep(4000);
			utils.waitForEle(MonthlyShiftSummaryDropDown, "visible", "", 30);
			MonthlyShiftSummaryDropDown.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ApplyShiftRequest() {
		try {

			utils.waitForEle(ApplyShiftRequest, "visible", "", 30);
			ApplyShiftRequest.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ApprovedShiftRequestSummaryCount() {
		try {

			utils.waitForEle(ApprovedShiftRequestSummaryCount, "visible", "", 30);
			ApprovedShiftRequestSummaryCount.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean SelectDateTextField() {
		try {

			utils.waitForEle(SelectDateTextField, "visible", "", 30);
			SelectDateTextField.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean SelectDateTextFieldDateSelected(String date) {
		
		 try {
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('shiftDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('shiftDate')._flatpickr) {" +
		            "  document.getElementById('shiftDate')._flatpickr.setDate('" + date + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on shiftDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	
	public boolean SelectShiftPolicyDropDown(String policy) {
		try {
Thread.sleep(4000);
			utils.waitForEle(SelectShiftPolicyDropDown, "visible", "", 30);
	Select sel = new Select(SelectShiftPolicyDropDown);
	sel.selectByVisibleText(policy);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean SelectShiftDropDown() {
		try {

			utils.waitForEle(SelectShiftDropDown, "visible", "", 30);
			SelectShiftDropDown.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SelectShiftSearchInput(String shiftname) {
		try {

			utils.waitForEle(SelectShiftSearchInput, "visible", "", 30);
			SelectShiftSearchInput.clear();
			SelectShiftSearchInput.sendKeys(shiftname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SelectShiftFromOption() {
		try {

			utils.waitForEle(SelectShiftFromOption, "visible", "", 30);
			SelectShiftFromOption.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ShiftReasonTextField(String reason) {
		try {

			utils.waitForEle(ShiftReasonTextField, "visible", "", 30);
			ShiftReasonTextField.clear();
			ShiftReasonTextField.sendKeys(reason);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftApplyButton() {
		try {

			utils.waitForEle(ShiftApplyButton, "visible", "", 30);
			ShiftApplyButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean PendingShiftRequestCard() {
		try {

			utils.waitForEle(PendingShiftRequestCard, "visible", "", 30);
			PendingShiftRequestCard.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean ValidateEmpShiftRequestStatusOnEmpAccount(String date, String status) {
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
	
	
	
	public boolean  ShiftRequestSearchTextField(String name)
	{
		try {

			utils.waitForEle(ShiftRequestSearchTextField, "visible", "", 20);
			ShiftRequestSearchTextField.clear();
			ShiftRequestSearchTextField.sendKeys(name);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean ShiftRequestEmpApprove(String empname, String expectedDate) {
	    try {

	        // Convert yyyy-MM-dd → Tue, 04-11-2025 format
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("E, dd-MM-yyyy", Locale.ENGLISH);

	        String dateUI = LocalDate.parse(expectedDate, inputFormat).format(uiFormat);

	        // Wait until first row loads
	        utils.waitForEle(ShiftRequestEmpNameList.get(0), "visible", "", 20);

	        // Loop rows
	        for (int i = 0; i < ShiftRequestEmpNameList.size(); i++) {

	            String uiEmpName  = ShiftRequestEmpNameList.get(i).getText().trim();
	            String uiDate     = ShiftRequestEmpDateRowList.get(i).getText().trim();

	            System.out.println("Row " + i + ": Name=" + uiEmpName + " | Date=" + uiDate);

	            boolean nameMatch = uiEmpName.contains(empname);
	            boolean dateMatch = uiDate.contains(dateUI);

	            if (nameMatch && dateMatch) {

	                // Click Approve button for the same row
	                utils.waitForEle(ShiftRequestEmpApproveButtonList.get(i), "clickable", "", 15);
	                ShiftRequestEmpApproveButtonList.get(i).click();

	                return true;
	            }
	        }

	        // No matching row found
	        exceptionDesc = "No matching OT row found. Expected Name: " + empname +
	                        " | Expected Date: " + dateUI;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
 
	
	
	public boolean ShiftRequestApproveButton() {
		try {

			utils.waitForEle(ShiftRequestApproveButton, "visible", "", 30);
			ShiftRequestApproveButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean ValidateEmpShiftRequestStatusOnAdminAccount(String date, String status, String empId) {
	    try {
	        // Convert yyyy-MM-dd → UI format (Mon, 03-11-2025)
	        LocalDate givenDate = LocalDate.parse(date);
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);
	        String expectedUIDate = givenDate.format(uiFormatter);

	        // Prepare expected EmpID with "#"
	        String expectedEmpId = "#" + empId.trim();

	        // Wait for elements
	        utils.waitForEle(EmpShiftRequestDateInAdminTable, "visible", "", 15);

	        String actualDate = EmpShiftRequestDateInAdminTable.getText().trim();
	        String actualStatus = EmpShiftRequestStatusInAdminTable.getText().trim();
	        String actualEmpId = ShiftRequestEmpIDValidateInAdminTable.getText().trim();

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
	
	

	public boolean ShiftRequestEmpReject(String empname, String expectedDate) {
	    try {

	        // Convert yyyy-MM-dd → Tue, 04-11-2025 format
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("E, dd-MM-yyyy", Locale.ENGLISH);

	        String dateUI = LocalDate.parse(expectedDate, inputFormat).format(uiFormat);

	        // Wait until first row loads
	        utils.waitForEle(ShiftRequestEmpNameList.get(0), "visible", "", 20);

	        // Loop rows
	        for (int i = 0; i < ShiftRequestEmpNameList.size(); i++) {

	            String uiEmpName  = ShiftRequestEmpNameList.get(i).getText().trim();
	            String uiDate     = ShiftRequestEmpDateRowList.get(i).getText().trim();

	            System.out.println("Row " + i + ": Name=" + uiEmpName + " | Date=" + uiDate);

	            boolean nameMatch = uiEmpName.contains(empname);
	            boolean dateMatch = uiDate.contains(dateUI);

	            if (nameMatch && dateMatch) {

	                // Click Approve button for the same row
	                utils.waitForEle(ShiftRequestEmpRejectButtonList.get(i), "clickable", "", 15);
	                ShiftRequestEmpRejectButtonList.get(i).click();

	                return true;
	            }
	        }

	        // No matching row found
	        exceptionDesc = "No matching OT row found. Expected Name: " + empname +
	                        " | Expected Date: " + dateUI;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	
	
	public boolean ShiftRequestEmpRejectReasonTextField(String name) {
		try {

			utils.waitForEle(ShiftRequestEmpRejectReasonTextField, "visible", "", 30);
			ShiftRequestEmpRejectReasonTextField.clear();
			ShiftRequestEmpRejectReasonTextField.sendKeys(name);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ShiftRequestEmpRejectConfirmButton() {
		try {

			utils.waitForEle(ShiftRequestEmpRejectConfirmButton, "visible", "", 30);
			ShiftRequestEmpRejectConfirmButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftRequestRejectedSummaryCount() {
		try {

			utils.waitForEle(ShiftRequestRejectedSummaryCount, "visible", "", 30);
			ShiftRequestRejectedSummaryCount.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean validateAttendanceRowForShiftRequest(
	        String dateInput,
	        String expectedIn,
	        String expectedOut,
	        String expectedShift,
	        String expectedStatus
	        ) {

	    try {
	        // Accepts yyyy-MM-dd or yyyyMMdd
	        LocalDate date = dateInput.contains("-") ?
	                LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd")) :
	                LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyyMMdd"));

	        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	        String dayName = date.format(DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH));
	        String finalDateUI = dayName + ", " + formattedDate;

	        // Find row by matching date column
	        for (int i = 0; i < EmpAttendanceDatetRow.size(); i++) {

	            String uiDate = EmpAttendanceDatetRow.get(i).getText().trim();

	            if (uiDate.contains(finalDateUI)) {

	                String actualIn = EmpAttendanceInTimetRow.get(i).getText().trim();
	                String actualOut = EmpAttendanceOutTimetRow.get(i).getText().trim();
	                String actualShift = EmpAttendanceShiftRow.get(i).getText().trim();
	                String actualStatus = EmpAttendanceStatustRow.get(i).getText().trim();
	               

	                // Logging to console for debugging
	                System.out.println("============== VALIDATING ROW ==============");
	                System.out.println("Expected → Date:" + finalDateUI +
	                        ", In:" + expectedIn +
	                        ", Out:" + expectedOut +
	                        ", Shift:" + expectedShift +
	                        ", Status:" + expectedStatus);

	                System.out.println("Actual   → In:" + actualIn +
	                        ", Out:" + actualOut +
	                        ", Shift:" + actualShift +
	                        ", Status:" + actualStatus );
	                System.out.println("============================================");

	                // Comparison
	                if (!actualIn.contains(expectedIn)) {
	                    exceptionDesc = "InTime mismatch. Expected: " + expectedIn + ", Found: " + actualIn;
	                    return false;
	                }

	                if (!actualOut.contains(expectedOut)) {
	                    exceptionDesc = "OutTime mismatch. Expected: " + expectedOut + ", Found: " + actualOut;
	                    return false;
	                }

	                if (!actualShift.contains(expectedShift)) {
	                    exceptionDesc = "Shift mismatch. Expected: " + expectedShift + ", Found: " + actualShift;
	                    return false;
	                }

	                if (!actualStatus.contains(expectedStatus)) {
	                    exceptionDesc = "Status mismatch. Expected: " + expectedStatus + ", Found: " + actualStatus;
	                    return false;
	                }

	                return true;
	            }
	        }

	        exceptionDesc = "No attendance row found for date: " + finalDateUI;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "Exception while validating row: " + e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean ValidateEmpShiftRequestStatusOnEmpAccountForList(String date, String status) {
	    try {

	        // Convert yyyy-MM-dd → UI format (Mon, 03-11-2025)
	        LocalDate givenDate = LocalDate.parse(date);
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);
	        String expectedUIDate = givenDate.format(uiFormatter);

	        // Wait for table rows
	       

	        // Loop through each row
	        for (int i = 0; i < EmpActTableDateRowList.size(); i++) {

	            String actualDate = EmpActTableDateRowList.get(i).getText().trim();
	            String actualStatus = EmpActTableStatusRowList.get(i).getText().trim();

	            System.out.println("Row " + (i + 1));
	            System.out.println("Actual Date: " + actualDate);
	            System.out.println("Actual Status: " + actualStatus);

	            // Match validation
	            if (actualDate.contains(expectedUIDate) && actualStatus.contains(status)) {
	                System.out.println("✅ Match found at row " + (i + 1));
	                return true;
	            }
	        }

	        // If reached here → no matching row found
	        exceptionDesc = "No matching row found for Date: " + expectedUIDate + " and Status: " + status;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean ValidateEmpShiftRequestStatusOnAdminAccountList(String date, String status, String empId) {
	    try {

	        // Convert yyyy-MM-dd → UI format (Mon, 03-11-2025)
	        LocalDate givenDate = LocalDate.parse(date);
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy", Locale.ENGLISH);
	        String expectedUIDate = givenDate.format(uiFormatter);

	        // Expected EmpID format → #1011
	        String expectedEmpId = "#" + empId.trim();

	        // Wait for table to load
	        if (EmpShiftRequestDateInAdminTableList.size() == 0) {
	            exceptionDesc = "Shift Request Table has no rows";
	            return false;
	        }

	        utils.waitForEle(EmpShiftRequestDateInAdminTableList.get(0), "visible", "", 15);

	        // Iterate all rows
	        for (int i = 0; i < EmpShiftRequestDateInAdminTableList.size(); i++) {

	            String actualEmpId = ShiftRequestEmpIDValidateInAdminTableList.get(i).getText().trim();
	            String actualDate = EmpShiftRequestDateInAdminTableList.get(i).getText().trim();
	            String actualStatus = EmpShiftRequestStatusInAdminTableList.get(i).getText().trim();

	            System.out.println("------------ Row " + (i + 1) + " ------------");
	            System.out.println("Actual EmpID: " + actualEmpId + " | Expected: " + expectedEmpId);
	            System.out.println("Actual Date : " + actualDate + " | Expected: " + expectedUIDate);
	            System.out.println("Actual Status: " + actualStatus + " | Expected: " + status);

	            boolean empIdMatch = actualEmpId.contains(expectedEmpId);
	            boolean dateMatch = actualDate.contains(expectedUIDate);
	            boolean statusMatch = actualStatus.contains(status);

	            // If all match → SUCCESS
	            if (empIdMatch && dateMatch && statusMatch) {
	                System.out.println("✅ Match found at row " + (i + 1));
	                return true;
	            }
	        }

	        // If no match found
	        exceptionDesc = "No row found matching → Date: " + expectedUIDate +
	                        ", Status: " + status +
	                        ", EmpID: " + expectedEmpId;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean ApprovedShiftRequestSummaryCountGetText(String firstname) {
	    try {
	        utils.waitForEle(ApprovedShiftRequestSummaryCount, "visible", "", 15);
	       

	        String uiName = ApprovedShiftRequestSummaryCount.getText().trim();

	        if (uiName.contains(firstname)) {
	            return true;   // success
	        } else {
	            exceptionDesc = "Expected Approved Count: " + firstname + ", but found: " + uiName;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean PendingShiftRequestCardGetText(String firstname) {
	    try {
	        utils.waitForEle(PendingShiftRequestCard, "visible", "", 15);
	       

	        String uiName = PendingShiftRequestCard.getText().trim();

	        if (uiName.contains(firstname)) {
	            return true;   // success
	        } else {
	            exceptionDesc = "Expected Pending Count: " + firstname + ", but found: " + uiName;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean ShiftRequestRejectedSummaryCountGetText(String firstname) {
	    try {
	        utils.waitForEle(ShiftRequestRejectedSummaryCount, "visible", "", 15);
	       

	        String uiName = ShiftRequestRejectedSummaryCount.getText().trim();

	        if (uiName.contains(firstname)) {
	            return true;   // success
	        } else {
	            exceptionDesc = "Expected Rejected Count: " + firstname + ", but found: " + uiName;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean ShiftRequestRevokedSummaryCountGetText(String firstname) {
	    try {
	        utils.waitForEle(ShiftRequestRevokedSummaryCount, "visible", "", 15);
	       

	        String uiName = ShiftRequestRevokedSummaryCount.getText().trim();

	        if (uiName.contains(firstname)) {
	            return true;   // success
	        } else {
	            exceptionDesc = "Expected Revoked Count: " + firstname + ", but found: " + uiName;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean EnrollIDSearchOption() {
		try {

			utils.waitForEle(EnrollIDSearchOption, "visible", "", 30);
			EnrollIDSearchOption.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AssignShiftPolicySearchOption() {
		try {

			utils.waitForEle(AssignShiftPolicySearchOption, "visible", "", 30);
			AssignShiftPolicySearchOption.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AssignShiftSearchOption() {
		try {

			utils.waitForEle(AssignShiftSearchOption, "visible", "", 30);
			AssignShiftSearchOption.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AppliedShiftSearchOption() {
		try {

			utils.waitForEle(AppliedShiftSearchOption, "visible", "", 30);
			AppliedShiftSearchOption.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftRequestReasonSearchOption() {
		try {

			utils.waitForEle(ShiftRequestReasonSearchOption, "visible", "", 30);
			ShiftRequestReasonSearchOption.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean EmpIDSearchResult(String firstname) {
	    try {
	        utils.waitForEle(EmpIDSearchResult, "visible", "", 15);
	       

	        String uiName = EmpIDSearchResult.getText().trim();

	        if (uiName.contains(firstname)) {
	            return true;   // success
	        } else {
	            exceptionDesc = "Expected EmpID: " + firstname + ", but found: " + uiName;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean AssignShiftPolicySearchResult(String firstname) {
	    try {
	        utils.waitForEle(AssignShiftPolicySearchResult, "visible", "", 15);
	       

	        String uiName = AssignShiftPolicySearchResult.getText().trim();

	        if (uiName.contains(firstname)) {
	            return true;   // success
	        } else {
	            exceptionDesc = "Expected Assigned Shift Policy: " + firstname + ", but found: " + uiName;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean AssignShiftSearchResult(String firstname) {
	    try {
	        utils.waitForEle(AssignShiftSearchResult, "visible", "", 15);
	       

	        String uiName = AssignShiftSearchResult.getText().trim();

	        if (uiName.contains(firstname)) {
	            return true;   // success
	        } else {
	            exceptionDesc = "Expected Assigned Shift : " + firstname + ", but found: " + uiName;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean AppliedShiftSearchResult(String firstname) {
	    try {
	        utils.waitForEle(AppliedShiftSearchResult, "visible", "", 15);
	       

	        String uiName = AppliedShiftSearchResult.getText().trim();

	        if (uiName.contains(firstname)) {
	            return true;   // success
	        } else {
	            exceptionDesc = "Expected Applied Shift : " + firstname + ", but found: " + uiName;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean ReasonSearchResult(String firstname) {
	    try {
	        utils.waitForEle(ReasonSearchResult, "visible", "", 15);
	       

	        String uiName = ReasonSearchResult.getText().trim();

	        if (uiName.contains(firstname)) {
	            return true;   // success
	        } else {
	            exceptionDesc = "Expected Reason : " + firstname + ", but found: " + uiName;
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	

	public boolean ApplyShiftRequestOnAdmin() {
		try {

			utils.waitForEle(ApplyShiftRequestOnAdmin, "visible", "", 30);
			ApplyShiftRequestOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ApplyShiftRequestForOthersOnAdmin() {
		try {

			utils.waitForEle(ApplyShiftRequestForOthersOnAdmin, "visible", "", 30);
			ApplyShiftRequestForOthersOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean SelectEmpDropDownOnAdmin() {
		try {

			utils.waitForEle(SelectEmpDropDownOnAdmin, "visible", "", 30);
			SelectEmpDropDownOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SelectEmpSearchFieldOnAdmin(String firstname) {
	    try {
	        utils.waitForEle(SelectEmpSearchFieldOnAdmin, "visible", "", 15);
	       

	        SelectEmpSearchFieldOnAdmin.clear();
	        SelectEmpSearchFieldOnAdmin.sendKeys(firstname);
	    } catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SelectedEmpFromDropDownOnAdmin() {
		try {

			utils.waitForEle(SelectedEmpFromDropDownOnAdmin, "visible", "", 30);
			SelectedEmpFromDropDownOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SelectShiftDateForOthersOnAdmin() {
		try {

			utils.waitForEle(SelectShiftDateForOthersOnAdmin, "visible", "", 30);
			SelectShiftDateForOthersOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SelectShiftDateForOthersOnAdminInputted(String date) {
		
		 try {
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('shiftDateForOther').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('shiftDateForOther')._flatpickr) {" +
		            "  document.getElementById('shiftDateForOther')._flatpickr.setDate('" + date + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on shiftDateForOther'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	
	public boolean SelectShiftPolicyForOthersOnAdmin(String shiftpolicy) {
		try {
Thread.sleep(4000);
			utils.waitForEle(SelectShiftPolicyForOthersOnAdmin, "visible", "", 30);
		Select sel = new Select(SelectShiftPolicyForOthersOnAdmin);
		sel.selectByVisibleText(shiftpolicy);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SelectShiftDropDownForOthersOnAdmin() {
		try {

			utils.waitForEle(SelectShiftDropDownForOthersOnAdmin, "visible", "", 30);
			SelectShiftDropDownForOthersOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SelectShiftSearchFieldForOthersOnAdmin(String firstname) {
	    try {
	        utils.waitForEle(SelectShiftSearchFieldForOthersOnAdmin, "visible", "", 15);
	       

	        SelectShiftSearchFieldForOthersOnAdmin.clear();
	        SelectShiftSearchFieldForOthersOnAdmin.sendKeys(firstname);
	    } catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SelectShiftResultForOthersOnAdmin() {
		try {

			utils.waitForEle(SelectShiftResultForOthersOnAdmin, "visible", "", 30);
			SelectShiftResultForOthersOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftReasonForOthersOnAdmin(String firstname) {
	    try {
	        utils.waitForEle(ShiftReasonForOthersOnAdmin, "visible", "", 15);
	       

	        ShiftReasonForOthersOnAdmin.clear();
	        ShiftReasonForOthersOnAdmin.sendKeys(firstname);
	    } catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftRequestApplyButtonForOthers() {
		try {

			utils.waitForEle(ShiftRequestApplyButtonForOthers, "visible", "", 30);
			ShiftRequestApplyButtonForOthers.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean ValidateEmpShiftRequestStatusOnAdminAccountForFilter(String status, String empId) {
	    try {

	       

	        // Expected EmpID format → #1011
	        String expectedEmpId = "#" + empId.trim();

	        // Wait for table to load
	        if (EmpShiftRequestDateInAdminTableList.size() == 0) {
	            exceptionDesc = "Shift Request Table has no rows";
	            return false;
	        }

	        utils.waitForEle(EmpShiftRequestDateInAdminTableList.get(0), "visible", "", 15);

	        // Iterate all rows
	        for (int i = 0; i < EmpShiftRequestDateInAdminTableList.size(); i++) {

	            String actualEmpId = ShiftRequestEmpIDValidateInAdminTableList.get(i).getText().trim();
	            String actualStatus = EmpShiftRequestStatusInAdminTableList.get(i).getText().trim();

	            System.out.println("------------ Row " + (i + 1) + " ------------");
	            System.out.println("Actual EmpID: " + actualEmpId + " | Expected: " + expectedEmpId);
	            System.out.println("Actual Status: " + actualStatus + " | Expected: " + status);

	            boolean empIdMatch = actualEmpId.contains(expectedEmpId);
	            boolean statusMatch = actualStatus.contains(status);

	            // If all match → SUCCESS
	            if (empIdMatch  && statusMatch) {
	                System.out.println("✅ Match found at row " + (i + 1));
	                return true;
	            }
	        }

	        // If no match found
	        exceptionDesc = "No row found matching → Date: "  +
	                        ", Status: " + status +
	                        ", EmpID: " + expectedEmpId;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public  void setExceptionDesc(String exceptionDesc) {  
		exceptionDesc = this.exceptionDesc;
	}
	
	
}
