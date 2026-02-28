package com.MeghPI.Attendance.pages;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.Utils;
public class MeghPIWeekOffRequestPage {
	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
	public String reason = "";
	
	public MeghPIWeekOffRequestPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//a[text()='Week-Off for Me']")
	private WebElement WeekOffForMeButtonOnEmp; //1stTestCase
	
	
	@FindBy(xpath = "//input[@id='txtOldWeekOffDate']/../input[2]")
	private WebElement WeekOffForMeOldWODate; //1stTestCase
	
	@FindBy(xpath = "//input[@id='txtNewWeekOffDate']/../input[2]")
	private WebElement WeekOffForMeNewWODate; //1stTestCase
	
	@FindBy(xpath = "//button[@id='btnWeekOffReqApply']")
	private WebElement WeekOffApplyButtonOnEmp; //1stTestCase
	
	@FindBy(xpath = "//input[@id='txtWeekOffQuickReason']")
	private WebElement WeekOffForMeReasonTextField; //1stTestCase
	
	@FindBy(xpath = "//button[contains(text(), 'Monthly Week-Off Sum')]")
	private WebElement WeekOffOfEmpMonthlySummaryBtn; //1stTestCase
	
	@FindBy(xpath = "//p[@id='MyWeekOffRequestPending']")
	private WebElement WeekOffOfEmpMonthlyPendingSummaryCount; //1stTestCase
	
	@FindBy(xpath = "//p[@id='WeekOffRequestPending']")
	private WebElement WeekOffOfAdminPendingSummaryCount; //1stTestCase
	
	@FindBy(xpath = "//input[@aria-controls='dtWeekOffRequest']")
	private WebElement WeekOffRequestSearchTextField; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtWeekOffRequest']/tbody/tr/td[1]/div/div/p[1]")
	private List<WebElement> WeekOffRequestEmpNameList; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtWeekOffRequest']/tbody/tr/td[2]")
	private List<WebElement> WeekOffRequestEmpOldWODateRowList; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtWeekOffRequest']/tbody/tr/td[3]")
	private List<WebElement> WeekOffRequestEmpNewWODateRowList; //1stTestCase Approved Tab
	
	
	
	@FindBy(xpath = "//table[@id='dtWeekOffRequest']/tbody/tr/td[6]/div/button[2]")
	private List<WebElement> WeekOffRequestEmpApproveButtonList; //1stTestCase Approved Tab
	
	
	@FindBy(xpath = "//button[@id='btnApproveWeekOffRequest']")
	private WebElement WeekOffRequestApproveButton; //1stTestCas
	
	@FindBy(xpath = "//p[@id='WeekOffRequestApprove']")
	private WebElement WeekOffRequestApproveSummaryCount; //1stTestCas
	
	
	@FindBy(xpath = "//table[@id='dtWeekOffRequest']/tbody/tr/td[5]")
	private List<WebElement> WeekOffRequestStatusRowOnAdmin; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtWeekOffRequest']/tbody/tr/td[1]/div/div/p[2]")
	private List<WebElement> EmpIDOfWeekOffRequestOnAdmin; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//p[@id='MyWeekOffRequestApprove']")
	private WebElement WeekOffRequestApproveSummaryCountOnEmp; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtMyWeekOffRequest']/tbody/tr/td[1]/div/div/p[2]")
	private List<WebElement> EmpIDOfWeekOffRequestOnEmp; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtMyWeekOffRequest']/tbody/tr/td[1]")
	private List<WebElement> OldWeekOffDateOfWeekOffRequestOnEmp; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtMyWeekOffRequest']/tbody/tr/td[2]")
	private List<WebElement> NewWeekOffDateOfWeekOffRequestOnEmp; //1stTestCase Approved Tab
	
	@FindBy(xpath = "//table[@id='dtMyWeekOffRequest']/tbody/tr/td[4]")
	private List<WebElement> StatusOfWeekOffRequestOnEmp; //1stTestCase Approved Tab
	
	
	@FindBy(xpath = "//table[@id='dtWeekOffRequest']/tbody/tr/td[6]/div/button[1]")
	private List<WebElement> WeekOffRequestEmpRejectButtonList; //1stTestCase Approved Tab
	
	
	@FindBy(xpath = "//input[@id='txtWeekOffRejectReason']")
	private WebElement WeekOffRequestRejectReason; //1stTestCas
	
	@FindBy(xpath = "//button[@id='btnRejectWeekOffRequest']")
	private WebElement WeekOffRequestRejectConfirmButton; //1stTestCas
	
	
	@FindBy(xpath = "//p[@id='WeekOffRequestReject']")
	private WebElement WeekOffRequestRejectSummaryCountOnAdmin; //1stTestCas
	
	@FindBy(xpath = "//p[@id='MyWeekOffRequestReject']")
	private WebElement WeekOffRequestRejectSummaryCountOnEmp; //1stTestCas
	
	@FindBy(xpath = "//table[@id='dtMyWeekOffRequest']//button[contains(@class,'revoke_btn')]")
	private List<WebElement> WeekOffRequestRevokeButton;

	
	@FindBy(xpath = "//button[@id='btnMyWeekOffRevokeRequest']")
	private WebElement WeekOffRequestRevokeConfirmButton; //1stTestCas
	
	@FindBy(xpath = "//p[@id='MyWeekOffRequestRevoke']")
	private WebElement WeekOffRequestRevokeSummaryClicked; //1stTestCas
	
	
	@FindBy(xpath = "//p[@id='WeekOffRequestRevoke']")
	private WebElement WeekOffRequestRevokeSummaryClickedOnAdmin; //1stTestCas
	
	@FindBy(xpath = "//button[contains(text(), 'Total Employee')]")
	private WebElement TotalEmpSummaryCountClickedInAdmin; //1stTestCase
	
	@FindBy(xpath = "//a[text()='Week-Off for Me']")
	private WebElement WeekOffForMeClickedInAdmin; //1stTestCase
	
	
	@FindBy(xpath = "//label[text()='Employee ID']")
	private WebElement EmpIDSearchOption; //1stTestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemEntityWeekOffAllocation-Entity-EnrollIddtWeekOffRequest']")
	private WebElement EnrollIDSearchOption; //1stTestCase

	@FindBy(xpath = "//input[@id='chkdivDrpItemStatusdtMyWeekOffRequest']")
	private WebElement WeekOffStatusSearchOption; //1stTestCase
	
	
	@FindBy(xpath = "//table[@id='dtWeekOffRequest']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement EmpNameRowInAdmin; //1stTestCase
	
	
	@FindBy(xpath = "//input[@aria-controls='dtMyWeekOffRequest']")
	private WebElement EmpAccountWOSearchTextField; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtMyWeekOffRequest']/tbody/tr[1]/td[5]")
	private WebElement WeekOffStatusRowValidation; //1stTestCase
	
	@FindBy(xpath = "//input[@aria-controls='dtAttendanceReProcess']")
	private WebElement AttendanceProcessSearchTextField; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtAttendanceReProcess']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement AttendanceProcessEmpIDSearchResult; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtAttendanceReProcess']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement AttendanceProcessEmpNameSearchResult; //1stTestCase
	
	
	@FindBy(xpath = "//select[@id='drpPunchRequired']")
	private WebElement AttendanceNoPunchSelected; //1stTestCase
	
	@FindBy(xpath = "//select[@id='drpOffice']")
	private WebElement AttendanceProcessOfficeSelection; //1stTestCase
	
	@FindBy(xpath = "//span[@dir='ltr']/../label[text()='Department: ']/../span[1]")
	private WebElement AttendanceProcessDeptDropDown; //1stTestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpDepartment-results']")
	private WebElement AttendanceProcessDeptSelected; //1stTestCase
	
	@FindBy(xpath = "//span[@dir='ltr']/../label[text()='Team: ']/../span")
	private WebElement AttendanceProcessTeamDropDown; //1stTestCase
	
	
	@FindBy(xpath = "//ul[@id='select2-drpTeam-results']")
	private WebElement AttendanceProcessTeamSelected; //1stTestCase
	
	@FindBy(xpath = "//select[@id='drpEntityType']")
	private WebElement AttendanceProcessEntitySelected; //1stTestCase
	
	
	@FindBy(xpath = "//select[@id='drpDeviceType']")
	private WebElement AttendanceProcessDeviceTypeSelected; //1stTestCase
	
	
	
	
	
	
	
	
	
	public boolean  AttendanceProcessSearchTextField(String name)
	{
		try {

			utils.waitForEle(AttendanceProcessSearchTextField, "visible", "", 20);
			AttendanceProcessSearchTextField.clear();
			AttendanceProcessSearchTextField.sendKeys(name);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	public boolean EmpIDSearchOption() {
		try {

			utils.waitForEle(EmpIDSearchOption, "visible", "", 30);
			EmpIDSearchOption.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
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
	
	
	
	public boolean WeekOffRequestRevokeSummaryClicked() {
		try {

			utils.waitForEle(WeekOffRequestRevokeSummaryClicked, "visible", "", 30);
			WeekOffRequestRevokeSummaryClicked.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean WeekOffStatusSearchOption() {
		try {

			utils.waitForEle(WeekOffStatusSearchOption, "visible", "", 30);
			WeekOffStatusSearchOption.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean WeekOffForMeButtonOnEmp() {
		try {

			utils.waitForEle(WeekOffForMeButtonOnEmp, "visible", "", 30);
			WeekOffForMeButtonOnEmp.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean WeekOffForMeClickedInAdmin() {
		try {

			utils.waitForEle(WeekOffForMeClickedInAdmin, "visible", "", 30);
			WeekOffForMeClickedInAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	public boolean WeekOffRequestRevokeSummaryClickedOnAdmin() {
		try {

			utils.waitForEle(WeekOffRequestRevokeSummaryClickedOnAdmin, "visible", "", 30);
			WeekOffRequestRevokeSummaryClickedOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean WeekOffForMeOldWODate() {
		try {

			utils.waitForEle(WeekOffForMeOldWODate, "visible", "", 30);
			WeekOffForMeOldWODate.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean WeekOffRequestRejectSummaryCountOnAdmin() {
		try {

			utils.waitForEle(WeekOffRequestRejectSummaryCountOnAdmin, "visible", "", 30);
			WeekOffRequestRejectSummaryCountOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean WeekOffForMeOldWODateInputted(String date) {
		
		 try {
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('txtOldWeekOffDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('txtOldWeekOffDate')._flatpickr) {" +
		            "  document.getElementById('txtOldWeekOffDate')._flatpickr.setDate('" + date + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on txtOldWeekOffDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	
	public boolean WeekOffForMeNewWODate() {
		try {

			utils.waitForEle(WeekOffForMeNewWODate, "visible", "", 30);
			WeekOffForMeNewWODate.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean WeekOffForMeNewWODateInputted(String date) {
		
		 try {
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('txtOldWeekOffDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('txtNewWeekOffDate')._flatpickr) {" +
		            "  document.getElementById('txtNewWeekOffDate')._flatpickr.setDate('" + date + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on txtNewWeekOffDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}

	
	public boolean  WeekOffForMeReasonTextField(String name)
	{
		try {

			utils.waitForEle(WeekOffForMeReasonTextField, "visible", "", 20);
			WeekOffForMeReasonTextField.clear();
			WeekOffForMeReasonTextField.sendKeys(name);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  EmpAccountWOSearchTextField(String name)
	{
		try {

			utils.waitForEle(EmpAccountWOSearchTextField, "visible", "", 20);
			EmpAccountWOSearchTextField.clear();
			EmpAccountWOSearchTextField.sendKeys(name);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	public boolean WeekOffApplyButtonOnEmp() {
		try {

			utils.waitForEle(WeekOffApplyButtonOnEmp, "visible", "", 30);
			WeekOffApplyButtonOnEmp.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean WeekOffOfEmpMonthlySummaryBtn() {
		try {
Thread.sleep(4000);
			utils.waitForEle(WeekOffOfEmpMonthlySummaryBtn, "visible", "", 30);
			WeekOffOfEmpMonthlySummaryBtn.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean WeekOffOfEmpMonthlyPendingSummaryCount() {
		try {

			utils.waitForEle(WeekOffOfEmpMonthlyPendingSummaryCount, "visible", "", 30);
			WeekOffOfEmpMonthlyPendingSummaryCount.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean WeekOffOfAdminPendingSummaryCount() {
		try {

			utils.waitForEle(WeekOffOfAdminPendingSummaryCount, "visible", "", 30);
			WeekOffOfAdminPendingSummaryCount.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	
	public boolean  WeekOffRequestSearchTextField(String name)
	{
		try {

			utils.waitForEle(WeekOffRequestSearchTextField, "visible", "", 20);
			WeekOffRequestSearchTextField.clear();
			WeekOffRequestSearchTextField.sendKeys(name);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean WeekOffRequestEmpApprove(String empname, String expectedoldDate, String expectednewdate) {
	    try {

	        // Convert yyyy-MM-dd → Tue, 04-11-2025 format
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);

	        String olddateUI = LocalDate.parse(expectedoldDate, inputFormat).format(uiFormat);
	        String newdateUI = LocalDate.parse(expectednewdate, inputFormat).format(uiFormat);

	        // Wait until first row loads
	        utils.waitForEle(WeekOffRequestEmpNameList.get(0), "visible", "", 20);

	        // Loop rows
	        for (int i = 0; i < WeekOffRequestEmpNameList.size(); i++) {

	            String uiEmpName  = WeekOffRequestEmpNameList.get(i).getText().trim();
	            String oldDate     = WeekOffRequestEmpOldWODateRowList.get(i).getText().trim();
	            String newdate = WeekOffRequestEmpNewWODateRowList.get(i).getText().trim();

	            System.out.println("Row " + i + ": Name=" + uiEmpName + " | OldWeekOffDate=" + oldDate + " |: NewWeekOffDate" +newdate);

	             boolean nameMatch = uiEmpName.contains(empname);
	            boolean olddateMatch = oldDate.contains(olddateUI);
	            boolean newdatematch = newdate.contains(newdateUI);

	            if (nameMatch && olddateMatch && newdatematch) {

	                // Click Approve button for the same row
	                utils.waitForEle(WeekOffRequestEmpApproveButtonList.get(i), "clickable", "", 15);
	                WeekOffRequestEmpApproveButtonList.get(i).click();

	                return true;
	            }
	        }

	        // No matching row found
	        exceptionDesc = "No matching OT row found. Expected Name: " + empname +
	                        " | Expected Date: " + olddateUI;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	

	public boolean WeekOffRequestApproveButton() {
		try {

			utils.waitForEle(WeekOffRequestApproveButton, "visible", "", 30);
			WeekOffRequestApproveButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean WeekOffRequestApproveSummaryCount() {
		try {

			utils.waitForEle(WeekOffRequestApproveSummaryCount, "visible", "", 30);
			WeekOffRequestApproveSummaryCount.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean ValidateEmpWeekOffRequestStatusOnAdminAccountList(String olddate, String newdate, String status, String empId) {
	    try {

	        // Convert yyyy-MM-dd → UI format (03-11-2025)
	        LocalDate givenDate = LocalDate.parse(olddate);
	        LocalDate givennewDate = LocalDate.parse(newdate);
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
	        
	        String expectedoldDate = givenDate.format(uiFormatter);
	        String expectednewDate = givennewDate.format(uiFormatter);

	        // Expected EmpID format → #1011
	        String expectedEmpId = "#" + empId.trim();

	        // Wait for table to load
	        if (WeekOffRequestEmpOldWODateRowList.size() == 0) {
	            exceptionDesc = "Shift Request Table has no rows";
	            return false;
	        }

	        utils.waitForEle(WeekOffRequestEmpOldWODateRowList.get(0), "visible", "", 15);

	        // Iterate all rows
	        for (int i = 0; i < WeekOffRequestEmpOldWODateRowList.size(); i++) {

	            String actualEmpId = EmpIDOfWeekOffRequestOnAdmin.get(i).getText().trim();
	            String actualoldDate = WeekOffRequestEmpOldWODateRowList.get(i).getText().trim();
	            String actualnewDate = WeekOffRequestEmpNewWODateRowList.get(i).getText().trim();
	            String actualStatus = WeekOffRequestStatusRowOnAdmin.get(i).getText().trim();

	            System.out.println("------------ Row " + (i + 1) + " ------------");
	            System.out.println("Actual EmpID: " + actualEmpId + " | Expected: " + expectedEmpId);
	            System.out.println("Actual Old Date : " + actualoldDate + " | Expected: " + expectedoldDate);
	            System.out.println("Actual Status: " + actualStatus + " | Expected: " + status);

	            boolean empIdMatch = actualEmpId.contains(expectedEmpId);
	            boolean olddateMatch = actualoldDate.contains(expectedoldDate);
	            boolean NewdateMatch = actualnewDate.contains(expectednewDate);
	            boolean statusMatch = actualStatus.contains(status);

	            // If all match → SUCCESS
	            if (empIdMatch && olddateMatch && NewdateMatch && statusMatch) {
	                System.out.println("✅ Match found at row " + (i + 1));
	                return true;
	            }
	        }

	        // If no match found
	        exceptionDesc = "No row found matching → Date: " + expectedoldDate +
	                        ", Status: " + status +
	                        ", EmpID: " + expectedEmpId;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean WeekOffRequestApproveSummaryCountOnEmp() {
		try {

			utils.waitForEle(WeekOffRequestApproveSummaryCountOnEmp, "visible", "", 30);
			WeekOffRequestApproveSummaryCountOnEmp.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	
	public boolean ValidateEmpWeekOffRequestStatusOnEmpAccountList(String olddate, String newdate, String status) {
	    try {

	        // Convert yyyy-MM-dd → UI format (03-11-2025)
	        LocalDate givenDate = LocalDate.parse(olddate);
	        LocalDate givennewDate = LocalDate.parse(newdate);
	        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
	        
	        String expectedoldDate = givenDate.format(uiFormatter);
	        String expectednewDate = givennewDate.format(uiFormatter);

	       

	        // Wait for table to load
	        if (OldWeekOffDateOfWeekOffRequestOnEmp.size() == 0) {
	            exceptionDesc = "Shift Request Table has no rows";
	            return false;
	        }

	        utils.waitForEle(OldWeekOffDateOfWeekOffRequestOnEmp.get(0), "visible", "", 15);

	        // Iterate all rows
	        for (int i = 0; i < OldWeekOffDateOfWeekOffRequestOnEmp.size(); i++) {

	            String actualoldDate = OldWeekOffDateOfWeekOffRequestOnEmp.get(i).getText().trim();
	            String actualnewDate = NewWeekOffDateOfWeekOffRequestOnEmp.get(i).getText().trim();
	            String actualStatus = StatusOfWeekOffRequestOnEmp.get(i).getText().trim();

	            System.out.println("------------ Row " + (i + 1) + " ------------");
	            System.out.println("Actual Old Date : " + actualoldDate + " | Expected: " + expectedoldDate);
	            System.out.println("Actual Status: " + actualStatus + " | Expected: " + status);

	            boolean olddateMatch = actualoldDate.contains(expectedoldDate);
	            boolean NewdateMatch = actualnewDate.contains(expectednewDate);
	            boolean statusMatch = actualStatus.contains(status);

	            // If all match → SUCCESS
	            if (olddateMatch && NewdateMatch && statusMatch) {
	                System.out.println("✅ Match found at row " + (i + 1));
	                return true;
	            }
	        }

	        // If no match found
	        exceptionDesc = "No row found matching → Date: " + expectedoldDate +
	                        ", Status: " + status ;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean WeekOffRequestEmpRejectRequest(String empname, String expectedoldDate, String expectednewdate) {
	    try {

	        // Convert yyyy-MM-dd → Tue, 04-11-2025 format
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);

	        String olddateUI = LocalDate.parse(expectedoldDate, inputFormat).format(uiFormat);
	        String newdateUI = LocalDate.parse(expectednewdate, inputFormat).format(uiFormat);

	        // Wait until first row loads
	        utils.waitForEle(WeekOffRequestEmpNameList.get(0), "visible", "", 20);

	        // Loop rows
	        for (int i = 0; i < WeekOffRequestEmpNameList.size(); i++) {

	            String uiEmpName  = WeekOffRequestEmpNameList.get(i).getText().trim();
	            String oldDate     = WeekOffRequestEmpOldWODateRowList.get(i).getText().trim();
	            String newdate = WeekOffRequestEmpNewWODateRowList.get(i).getText().trim();

	            System.out.println("Row " + i + ": Name=" + uiEmpName + " | OldWeekOffDate=" + oldDate + " |: NewWeekOffDate" +newdate);

	             boolean nameMatch = uiEmpName.contains(empname);
	            boolean olddateMatch = oldDate.contains(olddateUI);
	            boolean newdatematch = newdate.contains(newdateUI);

	            if (nameMatch && olddateMatch && newdatematch) {

	                // Click Approve button for the same row
	                utils.waitForEle(WeekOffRequestEmpRejectButtonList.get(i), "clickable", "", 15);
	                WeekOffRequestEmpRejectButtonList.get(i).click();

	                return true;
	            }
	        }

	        // No matching row found
	        exceptionDesc = "No matching OT row found. Expected Name: " + empname +
	                        " | Expected Date: " + olddateUI;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	public boolean  WeekOffRequestRejectReason(String name)
	{
		try {

			utils.waitForEle(WeekOffRequestRejectReason, "visible", "", 20);
			WeekOffRequestRejectReason.clear();
			WeekOffRequestRejectReason.sendKeys(name);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean WeekOffRequestRejectConfirmButton() {
		try {

			utils.waitForEle(WeekOffRequestRejectConfirmButton, "visible", "", 30);
			WeekOffRequestRejectConfirmButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean WeekOffRequestRejectSummaryCountOnEmp() {
		try {

			utils.waitForEle(WeekOffRequestRejectSummaryCountOnEmp, "visible", "", 30);
			WeekOffRequestRejectSummaryCountOnEmp.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean WeekOffRequestEmpRevoking(String expectedoldDate, String expectednewdate) {
	    try {

	        // Convert yyyy-MM-dd → Tue, 04-11-2025 format
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);

	        String olddateUI = LocalDate.parse(expectedoldDate, inputFormat).format(uiFormat);
	        String newdateUI = LocalDate.parse(expectednewdate, inputFormat).format(uiFormat);

	        // Wait until first row loads
	        utils.waitForEle(OldWeekOffDateOfWeekOffRequestOnEmp.get(0), "visible", "", 20);

	        // Loop rows
	        for (int i = 0; i < OldWeekOffDateOfWeekOffRequestOnEmp.size(); i++) {

	            String oldDate     = OldWeekOffDateOfWeekOffRequestOnEmp.get(i).getText().trim();
	            String newdate = NewWeekOffDateOfWeekOffRequestOnEmp.get(i).getText().trim();
	           

	            System.out.println("Row " + i +  " | OldWeekOffDate=" + oldDate + " |: NewWeekOffDate" +newdate);

	            boolean olddateMatch = oldDate.contains(olddateUI);
	            boolean newdatematch = newdate.contains(newdateUI);

	            if (olddateMatch && newdatematch) {

	                // Click Approve button for the same row
	                utils.waitForEle(WeekOffRequestRevokeButton.get(i), "clickable", "", 15);
	                WeekOffRequestRevokeButton.get(i).click();
	                

	                return true;
	            }
	        }

	        // No matching row found
	        exceptionDesc = "No matching OT row found. Expected Name: " +
	                        " | Expected Date: " + olddateUI;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	

	
	
	public boolean WeekOffRequestRevokeConfirmButton() {
		try {
Thread.sleep(2000);
utils.waitForEle(WeekOffRequestRevokeConfirmButton, "visible", "", 30);
			WeekOffRequestRevokeConfirmButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean EmpWeekOffApprovedCountValidation(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(TotalEmpSummaryCountClickedInAdmin, "visible", "", 15);

	        String count = WeekOffRequestApproveSummaryCount.getText().trim();
	        // Debug Logs
	        System.out.println("Expected Emp WeekOff Approved count: " + expcount);
	        System.out.println("Actual Emp WeekOff Approved count: " + count);
	        // Validation
	       
	        boolean statusMatch = count.contains(expcount);

	        if (statusMatch) {
	            System.out.println("✅  Emp WeekOff Approved Count matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("WeekOff Approved Count Match: " + statusMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean EmpWeekOffRejectedCountValidation(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(TotalEmpSummaryCountClickedInAdmin, "visible", "", 15);

	        String count = WeekOffRequestRejectSummaryCountOnAdmin.getText().trim();
	        // Debug Logs
	        System.out.println("Expected Emp WeekOff Rejected count: " + expcount);
	        System.out.println("Actual Emp WeekOff Rejected count: " + count);
	        // Validation
	       
	        boolean statusMatch = count.contains(expcount);

	        if (statusMatch) {
	            System.out.println("✅  Emp WeekOff Rejected Count matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("WeekOff Rejected Count Match: " + statusMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean EmpWeekOffRevokedCountValidation(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(TotalEmpSummaryCountClickedInAdmin, "visible", "", 15);

	        String count = WeekOffRequestRevokeSummaryClickedOnAdmin.getText().trim();
	        // Debug Logs
	        System.out.println("Expected Emp WeekOff Revoked count: " + expcount);
	        System.out.println("Actual Emp WeekOff Revoked count: " + count);
	        // Validation
	       
	        boolean statusMatch = count.contains(expcount);

	        if (statusMatch) {
	            System.out.println("✅  Emp WeekOff Revoked Count matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("WeekOff Revoked Count Match: " + statusMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	
	public boolean EmpWeekOffPendingCountValidation(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(TotalEmpSummaryCountClickedInAdmin, "visible", "", 15);

	        String count = WeekOffOfAdminPendingSummaryCount.getText().trim();
	        // Debug Logs
	        System.out.println("Expected Emp WeekOff Pending count: " + expcount);
	        System.out.println("Actual Emp WeekOff Pending count: " + count);
	        // Validation
	       
	        boolean statusMatch = count.contains(expcount);

	        if (statusMatch) {
	            System.out.println("✅  Emp WeekOff Pending Count matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("WeekOff Pending Count Match: " + statusMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	}
	
	public boolean EmpWeekOffApprovedCountValidationOnEmp(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(WeekOffRequestApproveSummaryCountOnEmp, "visible", "", 15);

	        String count = WeekOffRequestApproveSummaryCountOnEmp.getText().trim();
	        // Debug Logs
	        System.out.println("Expected Emp WeekOff Approved count: " + expcount);
	        System.out.println("Actual Emp WeekOff Approved count: " + count);
	        // Validation
	       
	        boolean statusMatch = count.contains(expcount);

	        if (statusMatch) {
	            System.out.println("✅  Emp WeekOff Approved Count matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("WeekOff Approved Count Match: " + statusMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean EmpWeekOffRejectedCountValidationOnEmp(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(WeekOffRequestRejectSummaryCountOnEmp, "visible", "", 15);

	        String count = WeekOffRequestRejectSummaryCountOnEmp.getText().trim();
	        // Debug Logs
	        System.out.println("Expected Emp WeekOff Rejected count: " + expcount);
	        System.out.println("Actual Emp WeekOff Rejected count: " + count);
	        // Validation
	       
	        boolean statusMatch = count.contains(expcount);

	        if (statusMatch) {
	            System.out.println("✅  Emp WeekOff Rejected Count matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("WeekOff Rejected Count Match: " + statusMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean EmpWeekOffRevokedCountValidationOnEmp(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(WeekOffRequestRevokeSummaryClicked, "visible", "", 15);

	        String count = WeekOffRequestRevokeSummaryClicked.getText().trim();
	        // Debug Logs
	        System.out.println("Expected Emp WeekOff Revoked count: " + expcount);
	        System.out.println("Actual Emp WeekOff Revoked count: " + count);
	        // Validation
	       
	        boolean statusMatch = count.contains(expcount);

	        if (statusMatch) {
	            System.out.println("✅  Emp WeekOff Revoked Count matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("WeekOff Revoked Count Match: " + statusMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean EmpWeekOffPendingCountValidationOnEmp(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(WeekOffOfEmpMonthlyPendingSummaryCount, "visible", "", 15);

	        String count = WeekOffOfEmpMonthlyPendingSummaryCount.getText().trim();
	        // Debug Logs
	        System.out.println("Expected Emp WeekOff Pending count: " + expcount);
	        System.out.println("Actual Emp WeekOff Pending count: " + count);
	        // Validation
	       
	        boolean statusMatch = count.contains(expcount);

	        if (statusMatch) {
	            System.out.println("✅  Emp WeekOff Pending Count matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("WeekOff Pending Count Match: " + statusMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean EmpNameRowInAdmin(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(EmpNameRowInAdmin, "visible", "", 15);

	        String empname = EmpNameRowInAdmin.getText().trim();
	        // Debug Logs
	       
	        System.out.println("Actual Emp ID: " + empname);
	        // Validation
	       
	        boolean empmatch = empname.contains(expcount);

	        if (empmatch) {
	            System.out.println("✅  Emp ID matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("EmpID MisMatch: " + empmatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	public boolean WeekOffStatusRowValidation(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(WeekOffStatusRowValidation, "visible", "", 15);

	        String empname = WeekOffStatusRowValidation.getText().trim();
	        // Debug Logs
	       
	        System.out.println("Actual Status: " + empname);
	        // Validation
	       
	        boolean empmatch = empname.contains(expcount);

	        if (empmatch) {
	            System.out.println("✅  Status matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("Status MisMatch: " + empmatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean ValidateEmpWeekOffRequestStatusOnAdminAccountForFilter(String status, String empId) {
	    try {

	       

	        // Expected EmpID format → #1011
	        String expectedEmpId = "#" + empId.trim();

	        // Wait for table to load
	        if (EmpIDOfWeekOffRequestOnAdmin.size() == 0) {
	            exceptionDesc = "Shift Request Table has no rows";
	            return false;
	        }

	        utils.waitForEle(EmpIDOfWeekOffRequestOnAdmin.get(0), "visible", "", 15);

	        // Iterate all rows
	        for (int i = 0; i < EmpIDOfWeekOffRequestOnAdmin.size(); i++) {

	            String actualEmpId = EmpIDOfWeekOffRequestOnAdmin.get(i).getText().trim();
	            String actualStatus = WeekOffRequestStatusRowOnAdmin.get(i).getText().trim();

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
	
	
	
	public boolean AttendanceProcessEmpIDSearchResult(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(AttendanceProcessEmpIDSearchResult, "visible", "", 15);

	       
	      String empid = AttendanceProcessEmpIDSearchResult.getText().trim();
	        // Validation
	       
	        boolean EmpidMatch = empid.contains(expcount);

	        if (EmpidMatch) {
	            System.out.println("✅  Emp ID matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("Emp ID Not Match: " + EmpidMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	public boolean AttendanceProcessEmpNameSearchResult(String expcount) {
	    try {
	
	        // Wait for elements
	        utils.waitForEle(AttendanceProcessEmpNameSearchResult, "visible", "", 15);

	       
	      String empid = AttendanceProcessEmpNameSearchResult.getText().trim();
	        // Validation
	       
	        boolean EmpidMatch = empid.contains(expcount);

	        if (EmpidMatch) {
	            System.out.println("✅  Emp Name matched successfully.");
	            return true;
	        } else {
	            System.out.println("❌ Validation failed.");
	            System.out.println("Emp Name Not Match: " + EmpidMatch);
	            return false;
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean AttendanceNoPunchSelected() {
		try {
Thread.sleep(3000);
utils.waitForEle(AttendanceNoPunchSelected, "visible", "", 30);
		Select sel = new Select(AttendanceNoPunchSelected);
		sel.selectByVisibleText("No Punch");
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	
	public boolean validateWeekdayStatus(String empID, String expectedStatus) {
	    try {
	        WebElement table = driver.findElement(By.id("dtAttendanceEmployee"));
	        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
String empId  = "#"+ empID;
	        for (WebElement row : rows) {

	            // match employee id
	            String uiEmp = row.findElement(By.cssSelector("p.user_email_id")).getText().trim();
	            if (!uiEmp.contains(empId)) {
	                continue;
	            }

	            // all <td> for this row
	            List<WebElement> cells = row.findElements(By.tagName("td"));

	            // date columns start from index 5
	            for (int i = 5; i < cells.size(); i++) {
	                WebElement dayCell = cells.get(i);

	                // read date from header
	                String dateText = driver.findElement(
	                        By.xpath("//table[@id='dtAttendanceEmployee']/thead/tr/th[" + (i + 1) + "]")
	                ).getText().trim();

	                // convert to LocalDate
	                LocalDate date = parseDateUI(dateText);

	                // skip Saturday & Sunday
	                DayOfWeek day = date.getDayOfWeek();
	                if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
	                    continue;
	                }

	                // extract status from cell
	                String status;
	                try {
	                    status = dayCell.findElement(By.cssSelector("div.roster-block")).getText().trim();
	                } catch (Exception e) {
	                    status = dayCell.getText().trim();
	                }

	                // compare with expected
	                if (!status.equalsIgnoreCase(expectedStatus)) {
	                    System.out.println("Mismatch on: " + date + " → UI: " + status + " Expected: " + expectedStatus);
	                    return false;
	                }
	            }

	            return true; // all weekday statuses match
	        }

	        return false; // employee not found

	    } catch (Exception e) {
	        return false;
	    }
	}

	private LocalDate parseDateUI(String dateText) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
	    return LocalDate.parse(dateText + " " + LocalDate.now().getYear(), formatter);
	}

	

	public boolean AttendanceProcessOfficeSelection(String officename) {
		try {
Thread.sleep(3000);
utils.waitForEle(AttendanceProcessOfficeSelection, "visible", "", 30);
		Select sel = new Select(AttendanceProcessOfficeSelection);
		sel.selectByVisibleText(officename);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	public boolean AttendanceProcessDeptDropDown() {
		try {
Thread.sleep(3000);
utils.waitForEle(AttendanceProcessDeptDropDown, "visible", "", 30);
AttendanceProcessDeptDropDown.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AttendanceProcessDeptSelected() {
		try {
Thread.sleep(3000);
utils.waitForEle(AttendanceProcessDeptSelected, "visible", "", 30);
AttendanceProcessDeptSelected.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AttendanceProcessTeamDropDown() {
		try {
Thread.sleep(3000);
utils.waitForEle(AttendanceProcessTeamDropDown, "visible", "", 30);
AttendanceProcessTeamDropDown.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AttendanceProcessTeamSelected() {
		try {
Thread.sleep(3000);
utils.waitForEle(AttendanceProcessTeamSelected, "visible", "", 30);
AttendanceProcessTeamSelected.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean AttendanceProcessEntitySelected(String entityname) {
		try {
Thread.sleep(3000);
utils.waitForEle(AttendanceProcessEntitySelected, "visible", "", 30);
		Select sel = new Select(AttendanceProcessEntitySelected);
		sel.selectByVisibleText(entityname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AttendanceProcessDeviceTypeSelected(String devicename) {
		try {
Thread.sleep(3000);
utils.waitForEle(AttendanceProcessDeviceTypeSelected, "visible", "", 30);
		Select sel = new Select(AttendanceProcessDeviceTypeSelected);
		sel.selectByVisibleText(devicename);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
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
