 package com.MeghPI.Attendance.pages;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utils;

public class MeghLeavePage {

	WebDriver driver;
	private  String exceptionDesc;
	Utils utils = new Utils(driver);
	
	
	public MeghLeavePage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//button[text()='Apply Leave']")
	private WebElement ApplyLeaveButton; //1stTestCase
	
	@FindBy(xpath = "//span[@id='select2-drpLeaveType-container']")
	private WebElement LeaveTypeDropdown; //1stTestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpLeaveType-results']/li[contains(text(), 'Sick')]")
	private WebElement SickLeaveSelected; //1stTestCase
	
	@FindBy(xpath = "//input[@id='fromDateMyLeave']/../input[2]")
	private WebElement FromDateTextField; //1stTestCase
	
	@FindBy(xpath = "//span[@id='select2-drpFromLeaveDurationMyLeave-container']")
	private WebElement LeaveDurationOne; //1stTestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpFromLeaveDurationMyLeave-results']/li[contains(text(), 'Full')]")
	private WebElement FullDayDurationOne; //1stTestCase
	
	@FindBy(xpath = "//input[@id='toDateMyLeave']/../input[2]")
	private WebElement ToDateTextField; //1stTestCase
	
	@FindBy(xpath = "//span[@id='select2-drpToLeaveDurationMyLeave-container']")
	private WebElement LeaveDurationTwo; //1stTestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpToLeaveDurationMyLeave-results']/li[contains(text(), 'Full')]")
	private WebElement FullDayDurationTwo; //1stTestCase
	
	@FindBy(xpath = "(//input[@id='txtReason'])[1]")
	private WebElement ReasonTextField; //1stTestCase
	
	@FindBy(xpath = "(//button[@id='btnApplyMyLeave'])[1]")
	private WebElement RequestApplyButton; //1stTestCase
	
	@FindBy(xpath = "(//input[@type='search'])[1]")
	private WebElement LeaveSearchTextField; //1stTestCase
	
	
	@FindBy(xpath = "//table[@id='dtMyLeaveRequest']/tbody/tr/td[1]")
	private WebElement LeaveTypeInTableInEmp; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtMyLeaveRequest']/tbody/tr/td[2]")
	private List<WebElement> LeaveFromDateInTableInEmp; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtMyLeaveRequest']/tbody/tr/td[7]")
	private List<WebElement> LeaveStatusInTableInEmp; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[3]")
	private WebElement LeaveFromDateInTableInAdmin; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[8]")
	private WebElement LeaveStatusInTableInAdmin; //1stTestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemLeave-LeaveType-NamedtLeave']")
	private WebElement LeaveTypeOptionSelected; //1stTestCase
	
	@FindBy(xpath = "//input[@id='txtRejectReason']")
	private WebElement LeaveRejectionReason; //1stTestCase
	
	@FindBy(xpath = "//button[@id='btnRejectRequest']")
	private WebElement LeaveRejectButton; //1stTestCase
	
	@FindBy(xpath = "//button[@id='btnApproveRequest']")
	private WebElement LeaveApproveButton; //1stTestCase
	
	@FindBy(xpath = "//select[@name='dtLeave_length']")
	private WebElement LeaveModulePaginationInAdmin; //1stTestCase
	
	@FindBy(xpath = "//select[@name='dtMyLeaveRequest_length']")
	private WebElement LeaveModulePaginationInEmp; //1stTestCase
	
	@FindBy(xpath = "//button[@id='btnRevokeRequest']")
	private WebElement RevokeButton; //1stTestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpLeaveType-results']/li[contains(text(), 'Casual')]")
	private WebElement CasualLeaveSelected; //1stTestCase
	
	@FindBy(xpath = "//a[text()='Leave for Others']")
	private WebElement LeaveForOthersButton; //5th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpLeaveForOthersEmp-container']")
	private WebElement EmployeeSelectionDropDown; //5th TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpLeaveForOthersEmp-results']")
	private WebElement EmployeeInputField; //5th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpLeaveForOthersEmp-results']")
	private WebElement EmpNameDisplayedAndSelected; //5th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpLeaveForOthersLeaveType-container']")
	private WebElement LeaveTypeDropDownOnAdmin; //5h TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpLeaveForOthersLeaveType-results']")
	private WebElement LeaveTypeInputtedOnAdmin; //5th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpLeaveForOthersLeaveType-results']")
	private WebElement LeaveTypeSelectedOnAdmin; //5th TestCase
	
	@FindBy(xpath = "//input[@id='fromDate']/../input[2]")
	private WebElement FromDateOnAdmin; //5th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpFromLeaveDuration-container']")
	private WebElement FromLeaveDurationDropdownAdmin; //5th TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpFromLeaveDuration-results']")
	private WebElement FromLeaveDurationInputtedInAdmin; //5th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpFromLeaveDuration-results']/li[contains(text(), 'Full')]")
	private WebElement FromLeaveDurationSelectedInAdmin; //5th TestCase
	
	@FindBy(xpath = "//input[@id='toDate']/../input[2]")
	private WebElement ToDateTextFieldInAdmin; //5th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpToLeaveDuration-container']")
	private WebElement ToLeaveDurationDropDownInAdmin; //5th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpToLeaveDuration-results']/li[contains(text(), 'Full')]")
	private WebElement ToLeaveDurationFullDaySelectedInAdmin; //5th TestCase
	
	@FindBy(xpath = "(//button[@id='btnApplyForOther'])[1]")
	private WebElement ApplyForOthersButton; //5th TestCase
	
	@FindBy(xpath = "//a[text()='Leave for Me']")
	private WebElement ApplyForMeButton; //6th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpLeaveForMeLeaveType-container']")
	private WebElement LeaveTypeSelectionDropDownOfLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpLeaveForMeLeaveType-results']")
	private WebElement LeaveTypeNameTextFieldOfLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpLeaveForMeLeaveType-results']/li[1]")
	private WebElement LeaveTypeSelectedOfLeaveForMe; //6th TestCase
	
	
	@FindBy(xpath = "//input[@id='fromDateForMe']/../input[2]")
	private WebElement FromDateClickedLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//select[@id='drpFromLeaveDurationForMe']")
	private WebElement FromLeaveDurationSelectedOfLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//input[@id='toDateForMe']/../input[2]")
	private WebElement ToDateClickedLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//select[@id='drpToLeaveDurationForMe']")
	private WebElement ToLeaveDurationSelectedOfLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//input[@id='txtReasonForMe']")
	private WebElement LeaveReasonOfLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//button[@id='btnApplyForMe']")
	private WebElement ApplyButtonOfLeaveForMe; //6th TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemLeave-Entity-EmployeeIddtLeave']")
	private WebElement EmpIDCheckBoxSearchOption; //7th TestCase
	
	@FindBy(xpath = "//label[text()='Leave Reason']")
	private WebElement ReasonCheckBoxSearchOption;//7th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr[1]/td[1]/div[1]/div/p[2]")
	private WebElement EmpIDInTable; //7th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr[1]/td[6]")
	private WebElement ReasonInTable; //7th TestCase
	
	@FindBy(xpath = "(//button[@id='AttendanceFilter'])[2]")
	private WebElement FilterButton; //8th TestCase
	
	@FindBy(xpath = "//select[@id='drpFilterCompanyLocation']")
	private WebElement OfficeDropDownSelected; //8th TestCase
	
	@FindBy(xpath = "//select[@id='drpFilterDepartment']/../span")
	private WebElement DeptDropDownClicked; //8th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpFilterDepartment-results']/li")
	private WebElement DeptDropDownSelected; //8th TestCase
	
	@FindBy(xpath = "//select[@id='drpFilterTeam']/../span")
	private WebElement TeamDropDownClicked; //8th TestCase
	
	
	@FindBy(xpath = "//ul[@id='select2-drpFilterTeam-results']/li")
	private WebElement TeamDropDownSelected; //8th TestCase
	
	@FindBy(xpath = "//table[@id='dtMyLeaveRequest']/tbody/tr[1]/td[1]")
	private WebElement EmpLeaveRequestOnEmp; //8th TestCase
	
	
	
	
	@FindBy(xpath = "//button[@id='btnFilter']")
	private WebElement SaveFilterButton; //8th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr[1]")
	private WebElement FilterResults; //8th TestCase
	
	@FindBy(xpath = "//select[@id='drpLeaveTypes']")
	private WebElement LeaveTypesFilter; //8th TestCase
	
	@FindBy(xpath = "//select[@id='drpStatusFilter']")
	private WebElement LeaveStatusFilter; //8th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[2]")
	private List<WebElement> LeaveTypesearchResults; //8th TestCase
	
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[9]")
	private List<WebElement> LeaveStatusSearchResults; //8th TestCase
	
	
	@FindBy(xpath = "//select[@id='drpFilterCompanyLocation']/../span")
	private WebElement OfficeDrpDownClicked; //8th TestCase
	
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpFilterCompanyLocation-results']/../../li/input")
	private WebElement OfficeNameInputted; //8th TestCase
	
	@FindBy(xpath = "//ul[@id='select2-drpFilterCompanyLocation-results']/li[1]")
	private WebElement OfficeNameSelectedOfFilter; //8th TestCase
	
	@FindBy(xpath = "//button[contains(text(), 'Monthly Leave')]")
	private WebElement MonthlyLeaveSummaryDrpIcon; //8th TestCase
	
	@FindBy(xpath = "//p[text()='Applied Leaves']/../..")
	private WebElement StatisticsDisplayed; //8th TestCase
	
	@FindBy(xpath = "(//span[@id='showMonthyear'])[2]")
	private WebElement MonthFilterContains; //8th TestCase
	
	@FindBy(xpath = "(//button[@id='btnPrevious'])[2]")
	private WebElement MonthFilterBackButton; //8th TestCase
	
	@FindBy(xpath = "//p[@id='PendingLeaves']")
	private WebElement PendingLeaveCount; //8th TestCase
	
	@FindBy(xpath = "(//button[@id='btnRejectAllModel'])[2]")
	private WebElement RejectAllButton; //8th TestCase
	
	@FindBy(xpath = "//p[@id='RejectedLeaves']")
	private WebElement RejectSummaryCount; //8th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[1]/div/div/p[2]")
	private List<WebElement> EmpIDOnAdmin; //8th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[2]")
	private List<WebElement> EmpLeaveTypeOnAdmin; //8th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[3]")
	private List<WebElement> EmpLeaveDateOnAdmin; //8th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[9]")
	private List<WebElement> EmpLeaveStatusOnAdmin; //8th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement EmpIDRow; //8th TestCase
	
	@FindBy(xpath = "(//button[text()=' Approve All'])[2]")
	private WebElement ApproveAllButtonOnAdmin; //8th TestCase
	
	@FindBy(xpath = "//button[text()='Approve All']")
	private WebElement ApproveAllConfirmButtonOnAdmin; //8th TestCase
	
	@FindBy(xpath = "//p[@id='ApprovedLeaves']")
	private WebElement ApproveSummaryCount; //8th TestCase
	
	@FindBy(xpath = "//p[@id='RevokedLeaves']")
	private WebElement RevokedSummaryCount; //8th TestCase
	
	@FindBy(xpath = "//label[text()='Leave Type']")
	private WebElement LeaveTypeSearchOption; //8th TestCase
	
	@FindBy(xpath = "//label[text()='Remarks']")
	private WebElement LeaveRemarksSearchOption; //8th TestCase
	
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr[1]/td[2]")
	private WebElement LeaveNameInTable; //7th TestCase
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr[1]/td[7]")
	private WebElement LeaveRejectReasonInTable; //7th TestCase
	
	@FindBy(xpath = "(//label[text()='Status'])[1]")
	private WebElement LeaveStatusInTable; //7th TestCase
	
	@FindBy(xpath = "//table[@id='dtMyLeaveRequest']/tbody/tr[1]/td[5]")
	private WebElement LeaveReasonInEmpTable; //7th TestCase
	
	@FindBy(xpath = "//table[@id='dtMyLeaveRequest']/tbody/tr[1]/td[7]")
	private WebElement LeaveStatusInEmpTable; //7th TestCase
	
	
	@FindBy(xpath = "//table[@id='tblLeaveType']/tbody/tr[1]/td[1]")
	private WebElement EditLeaveBalanceForSickLeave; //7th TestCase
	
	
	@FindBy(xpath = "//table[@id='tblLeaveType']/tbody/tr[2]/td[1]")
	private WebElement EditLeaveBalanceForTravelLeave; //7th TestCase
	
	@FindBy(xpath = "//table[@id='tblLeaveType']/tbody/tr[3]/td[1]")
	private WebElement EditLeaveBalanceForCasualLeave; //7th TestCase
	
	@FindBy(xpath = "//table[@id='tblLeaveType']/tbody/tr[1]/td[4]/button")
	private WebElement EditButtonForSickLeave; //7th TestCase
	
	@FindBy(xpath = "//table[@id='tblLeaveType']/tbody/tr[2]/td[4]/button")
	private WebElement EditButtonForTravelLeave; //7th TestCase
	
	@FindBy(xpath = "//table[@id='tblLeaveType']/tbody/tr[3]/td[4]/button")
	private WebElement EditButtonForCasualLeave; //7th TestCase
	
	
	@FindBy(xpath = "//button[@id='fixed-balanced-tab']")
	private WebElement FixedBalanceButton; //7th TestCase
	
	
	@FindBy(xpath = "//button[@id='btnChangeCreditRuleConfirmYes']")
	private WebElement FixedBalanceConfirmButton; //7th TestCase
	
	@FindBy(xpath = "//input[@id='NoOfCredit']")
	private WebElement NoOfCreditInput; //7th TestCase
	
	@FindBy(xpath = "//select[@id='RepeatingCredit']")
	private WebElement RepeatCreditSelected; //7th TestCase
	
	@FindBy(xpath = "//button[@id='btnEditLeavePolicyDetail']")
	private WebElement EditLeaveSaveButton; //7th TestCase
	
	@FindBy(xpath = "//input[@id='AllowHalfDayLeaves']")
	private WebElement AllowhalfDayLeave; //7th TestCase
	
	@FindBy(xpath = "//input[@aria-controls='dtLeaveBalance']")
	private WebElement LeaveBalanceSearchTextField; //7th TestCase
	
	@FindBy(xpath = "//input[@aria-controls='dtWeeklyLeaveCalender']")
	private WebElement WeeklyLeaveCalendarSearchTextField; //7th TestCase
	
	
	
	@FindBy(xpath = "//table[@id='dtLeaveBalance']/tbody/tr")
	private List<WebElement> LeaveBalanceRows;

	@FindBy(xpath = "//table[@id='dtLeaveBalance']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement LeaveBalanceEmpIDRow;

	
	@FindBy(xpath = "//table[@id='dtWeeklyLeaveCalender']/tbody/tr")
	private List<WebElement> weeklyLeaveRows;

	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[10]/div/button[text()= 'Approve']")
	private List<WebElement> LeaveApproveButtonList;
	
	
	@FindBy(xpath = "//table[@id='dtLeave']/tbody/tr/td[10]/div/button[text()= 'Reject']")
	private List<WebElement> LeaveRejectButtonList;
	
	public boolean WeeklyLeaveCalendarSearchTextField(String LeaveReason) {
		try {

			utils.waitForEle(WeeklyLeaveCalendarSearchTextField,  "visible", "", 10);
			WeeklyLeaveCalendarSearchTextField.clear();
			WeeklyLeaveCalendarSearchTextField.sendKeys(LeaveReason);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	public boolean LeaveBalanceSearchTextField(String LeaveReason) {
		try {

			utils.waitForEle(LeaveBalanceSearchTextField,  "visible", "", 10);
			LeaveBalanceSearchTextField.clear();
			LeaveBalanceSearchTextField.sendKeys(LeaveReason);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveStatusInTable()
	{
		try {
			utils.waitForEle(LeaveStatusInTable, "visible", "", 10);
			
			LeaveStatusInTable.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveRemarksSearchOption()
	{
		try {
			utils.waitForEle(LeaveRemarksSearchOption, "visible", "", 10);
			
			LeaveRemarksSearchOption.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeSearchOption()
	{
		try {
			utils.waitForEle(LeaveTypeSearchOption, "visible", "", 10);
			
			LeaveTypeSearchOption.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean RevokedSummaryCount()
	{
		try {
			utils.waitForEle(RevokedSummaryCount, "visible", "", 10);
			
			RevokedSummaryCount.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ApproveSummaryCount()
	{
		try {
			utils.waitForEle(ApproveSummaryCount, "visible", "", 10);
			
			ApproveSummaryCount.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ApproveAllButtonOnAdmin()
	{
		try {
			utils.waitForEle(ApproveAllButtonOnAdmin, "visible", "", 10);
			
			ApproveAllButtonOnAdmin.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ApproveAllConfirmButtonOnAdmin()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(ApproveAllConfirmButtonOnAdmin, "visible", "", 30);
			
			ApproveAllConfirmButtonOnAdmin.click();
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	public boolean ApplyLeaveButton()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(ApplyLeaveButton, "visible", "", 10);
			
			ApplyLeaveButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeDropdown()
	{
		try {
			utils.waitForEle(LeaveTypeDropdown, "visible", "", 10);
			
			LeaveTypeDropdown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SickLeaveSelected()
	{
		try {
			utils.waitForEle(SickLeaveSelected, "visible", "", 10);
			
			SickLeaveSelected.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean CasualLeaveSelected()
	{
		try {
			utils.waitForEle(CasualLeaveSelected, "visible", "", 10);
			
			CasualLeaveSelected.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateTextField()
	{
		try {
			utils.waitForEle(FromDateTextField, "visible", "", 10);
			
			FromDateTextField.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateSelected(String LeaveFromDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('fromDateMyLeave').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('fromDateMyLeave')._flatpickr) {" +
		            "  document.getElementById('fromDateMyLeave')._flatpickr.setDate('" + LeaveFromDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on fromDateMyLeave'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}	
	
	public boolean LeaveDurationOne()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(LeaveDurationOne, "visible", "", 15);
			
			LeaveDurationOne.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FullDayDurationOne()
	{
		try {
			utils.waitForEle(FullDayDurationOne, "visible", "", 10);
			
			FullDayDurationOne.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDateTextField()
	{
		try {
			utils.waitForEle(ToDateTextField, "visible", "", 10);
			
			ToDateTextField.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDateSelected(String LeaveToDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('toDateMyLeave').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('toDateMyLeave')._flatpickr) {" +
		            "  document.getElementById('toDateMyLeave')._flatpickr.setDate('" + LeaveToDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on toDateMyLeave'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}	
	
	public boolean LeaveDurationTwo()
	{
		try {
			utils.waitForEle(LeaveDurationTwo, "visible", "", 10);
			
			LeaveDurationTwo.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FullDayDurationTwo()
	{
		try {
			utils.waitForEle(FullDayDurationTwo, "visible", "", 10);
			
			FullDayDurationTwo.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ReasonTextField(String LeaveReason) {
		try {

			utils.waitForEle(ReasonTextField,  "visible", "", 10);
			ReasonTextField.clear();
			ReasonTextField.sendKeys(LeaveReason);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RequestApplyButton()
	{
		try {
			utils.waitForEle(RequestApplyButton, "visible", "", 10);
			
			RequestApplyButton.click();
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveSearchTextField(String LeaveType)
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(LeaveSearchTextField, "visible", "", 10);
			
			LeaveSearchTextField.clear();
			LeaveSearchTextField.sendKeys(LeaveType);
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean verifyLeaveRowInAdmin(String empName, String expectedLeaveType, String expectedDate, String expectedStatus) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Format yyyy-MM-dd → dd-MM-yyyy
	        String formattedExpectedDate = expectedDate.substring(8, 10) + "-"
	                                     + expectedDate.substring(5, 7) + "-"
	                                     + expectedDate.substring(0, 4);

	        for (int scrollAttempt = 0; scrollAttempt < 10; scrollAttempt++) {
	            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#dtLeave tbody tr")));
	            List<WebElement> rows = driver.findElements(By.cssSelector("#dtLeave tbody tr"));

	            for (WebElement row : rows) {
	                try {
	                    String name = row.findElement(By.cssSelector("td:nth-child(1) p.font_weight_medium")).getText().trim();
	                    String leaveType = row.findElement(By.cssSelector("td:nth-child(2)")).getText().trim();
	                    String fromDate = row.findElement(By.cssSelector("td:nth-child(3)")).getText().trim();
	                    String toDate = row.findElement(By.cssSelector("td:nth-child(4)")).getText().trim();
	                    String status = row.findElement(By.cssSelector("td:nth-child(9) p")).getText().trim();

	                    // Remove weekday part (e.g., "Thu, ")
	                    if (fromDate.contains(",")) {
	                        fromDate = fromDate.split(",")[1].trim();
	                    }
	                    if (toDate.contains(",")) {
	                        toDate = toDate.split(",")[1].trim();
	                    }

	                    // ✅ Check all parameters
	                    if (name.contains(empName)
	                            && leaveType.contains(expectedLeaveType)
	                            && (fromDate.contains(formattedExpectedDate) || toDate.equals(formattedExpectedDate))
	                            && status.contains(expectedStatus)) {

	                        System.out.println("✅ Matching row found: " + name + " | " + leaveType + " | " + fromDate + " | " + status);
	                        return true;
	                    }

	                } catch (StaleElementReferenceException ignored) {}
	            }

	            // Scroll down and retry
	            js.executeScript("window.scrollBy(0, 600);");
	            Thread.sleep(700);
	        }

	        exceptionDesc = String.format(
	                "❌ No matching row found for EmpName: %s, LeaveType: %s, Date: %s, Status: %s",
	                empName, expectedLeaveType, expectedDate, expectedStatus);
	        System.out.println(exceptionDesc);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error while verifying leave row in Admin Table: " + e.getMessage();
	        System.out.println(exceptionDesc);
	        return false;
	    }
	}





	public boolean verifyLeaveRowInEmp(String expectedLeaveType, String expectedDate, String expectedStatus) {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Convert yyyy-MM-dd → dd-MM-yyyy
	        String formattedExpectedDate = expectedDate.substring(8, 10) + "-"
	                                     + expectedDate.substring(5, 7) + "-"
	                                     + expectedDate.substring(0, 4);

	        for (int scrollAttempt = 0; scrollAttempt < 15; scrollAttempt++) {
	            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#dtMyLeaveRequest tbody tr")));
	            List<WebElement> rows = driver.findElements(By.cssSelector("#dtMyLeaveRequest tbody tr"));

	            for (WebElement row : rows) {
	                try {
	                    // ✅ Extract all required fields
	                    String leaveType = row.findElement(By.cssSelector("td:nth-child(1) span.font_weight_medium")).getText().trim();
	                    String fromDate = row.findElement(By.cssSelector("td:nth-child(2)")).getText().trim();
	                    String toDate = row.findElement(By.cssSelector("td:nth-child(3)")).getText().trim();
	                    String status = row.findElement(By.cssSelector("td:nth-child(7) p")).getText().trim();

	                    // Normalize date (remove day name like "Wed,")
	                    fromDate = fromDate.replaceAll("^[A-Za-z]{3},\\s*", "").trim();
	                    toDate = toDate.replaceAll("^[A-Za-z]{3},\\s*", "").trim();

	                    // ✅ Match all expected fields
	                    if (leaveType.equalsIgnoreCase(expectedLeaveType)
	                            && (fromDate.equals(formattedExpectedDate) || toDate.equals(formattedExpectedDate))
	                            && status.equalsIgnoreCase(expectedStatus)) {

	                        System.out.println("✅ Matching leave record found: "
	                                + leaveType + " | " + fromDate + " | " + status);
	                        return true;
	                    }

	                } catch (StaleElementReferenceException ignored) {}
	            }

	            // Scroll & retry
	            js.executeScript("window.scrollBy(0, 600);");
	            Thread.sleep(800);
	        }

	        exceptionDesc = String.format("❌ No matching leave record found — LeaveType: %s | Date: %s | Status: %s",
	                expectedLeaveType, expectedDate, expectedStatus);
	        System.out.println(exceptionDesc);
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "❌ Error while verifying leave row: " + e.getMessage();
	        System.out.println(exceptionDesc);
	        return false;
	    }
	}




	
	
	public boolean LeaveTypeOptionSelected()
	{
		try {
			utils.waitForEle(RequestApplyButton, "visible", "", 10);
			
			LeaveTypeOptionSelected.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//2nd TestCase
	
	public boolean approveLeaveByDateAndEmp(String empId, String excelDate) {
	    try {

	        // Convert yyyy-MM-dd → Tue, 11-11-2025 (UI format)
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("E, dd-MM-yyyy", Locale.ENGLISH);
	        String expectedUIDate = LocalDate.parse(excelDate, inputFormat).format(uiFormat);

	        // Wait till table is visible
	        utils.waitForEle(EmpIDOnAdmin.get(0), "visible", "", 20);

	        int rowCount = EmpIDOnAdmin.size();

	        for (int i = 0; i < rowCount; i++) {

	            String uiEmpId = EmpIDOnAdmin.get(i).getText().trim();
	            String uiDate = EmpLeaveDateOnAdmin.get(i).getText().trim();

	            System.out.println("Row " + i + " | UI EmpID: " + uiEmpId + " | UI Date: " + uiDate);

	            if (uiEmpId.contains(empId) && uiDate.contains(expectedUIDate)) {

	                utils.waitForEle(LeaveApproveButtonList.get(i), "clickable", "", 10);
	                LeaveApproveButtonList.get(i).click();

	                return true;
	            }
	        }

	        exceptionDesc = "No matching leave record found. EmpID: " + empId +
	                        " | Expected Date: " + expectedUIDate;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}


	public boolean clickRejectButtonByEmpAndDate(String empId, String excelDate) {
	    try {

	        // Convert yyyy-MM-dd → Tue, 11-11-2025 (UI format)
	        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	        DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("E, dd-MM-yyyy", Locale.ENGLISH);
	        String expectedUIDate = LocalDate.parse(excelDate, inputFormat).format(uiFormat);

	        // Wait till table data is visible
	        utils.waitForEle(EmpIDOnAdmin.get(0), "visible", "", 20);

	        int rowCount = EmpIDOnAdmin.size();

	        for (int i = 0; i < rowCount; i++) {

	            String uiEmpId = EmpIDOnAdmin.get(i).getText().trim();
	            String uiDate = EmpLeaveDateOnAdmin.get(i).getText().trim();

	            System.out.println("Row " + i + 
	                               " | UI EmpID: " + uiEmpId + 
	                               " | UI Date: " + uiDate);

	            if (uiEmpId.contains(empId) && uiDate.contains(expectedUIDate)) {

	                utils.waitForEle(LeaveRejectButtonList.get(i), "clickable", "", 10);
	                LeaveRejectButtonList.get(i).click();

	                return true;
	            }
	        }

	        exceptionDesc = "No matching leave record found. EmpID: " + empId +
	                        " | Expected Date: " + expectedUIDate;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}







	
	
	
	
	
	public boolean LeaveRejectionReason(String reason)
	{
		try {
			utils.waitForEle(LeaveRejectionReason, "visible", "", 10);
			
			LeaveRejectionReason.clear();
			LeaveRejectionReason.sendKeys(reason);
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean LeaveRejectButton()
	{
		try {
			utils.waitForEle(LeaveRejectButton, "visible", "", 10);
			
			LeaveRejectButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean LeaveApproveButton()
	{
		try {
			utils.waitForEle(LeaveApproveButton, "visible", "", 10);
			
			LeaveApproveButton.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	
	
	public boolean LeaveModulePaginationInAdmin()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(LeaveModulePaginationInAdmin, "visible", "", 10);
			
		Select sel = new Select(LeaveModulePaginationInAdmin);
		sel.selectByVisibleText("100");
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveModulePaginationInEmp()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(LeaveModulePaginationInEmp, "visible", "", 10);
			
		Select sel = new Select(LeaveModulePaginationInEmp);
		sel.selectByVisibleText("100");
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
//2nd TestCase
	public boolean clickRevokeButtonByDate(String excelDate) {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Convert "2025-08-04" → "04-08-2025"
	        String formattedDate = excelDate.substring(8, 10) + "-"
	                             + excelDate.substring(5, 7) + "-"
	                             + excelDate.substring(0, 4);

	        int previousRowCount = 0;
	        int scrollAttempts = 0;
	        boolean found = false;

	        while (scrollAttempts < 15) {
	            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#dtMyLeaveRequest tbody tr")));
	            List<WebElement> rows = driver.findElements(By.cssSelector("#dtMyLeaveRequest tbody tr"));

	            for (WebElement row : rows) {
	                try {
	                    // Get "From" date from column (check your table structure)
	                    String fromDate = row.findElement(By.cssSelector("td:nth-child(2)")).getText().trim();
	                    fromDate = fromDate.replaceAll("^[A-Za-z]{3},\\s*", "").trim();

	                    // Match formatted date
	                    if (fromDate.equals(formattedDate)) {
	                        List<WebElement> revokeButtons = row.findElements(By.cssSelector(".revoke_btn"));
	                        if (!revokeButtons.isEmpty() && revokeButtons.get(0).isDisplayed()) {
	                            js.executeScript("arguments[0].scrollIntoView(true);", revokeButtons.get(0));
	                            Thread.sleep(800);
	                            revokeButtons.get(0).click();
	                            found = true;
	                            break;
	                        }
	                    }
	                } catch (StaleElementReferenceException ignored) {}
	            }

	            if (found) return true;

	            // Scroll for more rows
	            js.executeScript("window.scrollBy(0, 400);");
	            Thread.sleep(800);

	            if (rows.size() == previousRowCount) {
	                scrollAttempts++;
	            } else {
	                previousRowCount = rows.size();
	                scrollAttempts = 0;
	            }
	        }

	        throw new Exception("No matching row with Revoke button found for date: " + excelDate);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean RevokeButton()
	{
		try {
			utils.waitForEle(RevokeButton, "visible", "", 10);
			
			RevokeButton.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//5th TestCase
	
	public boolean LeaveForOthersButton()
	{
		try {
			utils.waitForEle(LeaveForOthersButton, "visible", "", 10);
			
			LeaveForOthersButton.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeSelectionDropDown()
	{
		try {
			utils.waitForEle(EmployeeSelectionDropDown, "visible", "", 10);
			
			EmployeeSelectionDropDown.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeInputField(String empname)
	{
		try {
			utils.waitForEle(EmployeeInputField, "visible", "", 10);
			
			EmployeeInputField.clear();
			EmployeeInputField.sendKeys(empname);
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpNameDisplayedAndSelected()
	{
		try {
			utils.waitForEle(EmpNameDisplayedAndSelected, "visible", "", 10);
			
			EmpNameDisplayedAndSelected.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeDropDownOnAdmin()
	{
		try {
			utils.waitForEle(LeaveTypeDropDownOnAdmin, "visible", "", 10);
			
			LeaveTypeDropDownOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeInputtedOnAdmin(String leavetype)
	{
		try {
			utils.waitForEle(LeaveTypeInputtedOnAdmin, "visible", "", 10);
			
			LeaveTypeInputtedOnAdmin.clear();
			LeaveTypeInputtedOnAdmin.sendKeys(leavetype);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeSelectedOnAdmin()
	{
		try {
			utils.waitForEle(LeaveTypeSelectedOnAdmin, "visible", "", 10);
			
			LeaveTypeSelectedOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateOnAdmin()
	{
		try {
			utils.waitForEle(FromDateOnAdmin, "visible", "", 10);
			
			FromDateOnAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateOnAdminSelected(String LeaveFromDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('fromDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('fromDate')._flatpickr) {" +
		            "  document.getElementById('fromDate')._flatpickr.setDate('" + LeaveFromDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on fromDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}	
	
	public boolean FromLeaveDurationDropdownAdmin()
	{
		try {
			utils.waitForEle(FromLeaveDurationDropdownAdmin, "visible", "", 10);
			
			FromLeaveDurationDropdownAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromLeaveDurationSelectedInAdmin()
	{
		try {
			utils.waitForEle(FromLeaveDurationSelectedInAdmin, "visible", "", 10);
			
			FromLeaveDurationSelectedInAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDateTextFieldInAdmin()
	{
		try {
			utils.waitForEle(ToDateTextFieldInAdmin, "visible", "", 10);
			
			ToDateTextFieldInAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDateTextFieldInAdminSelected(String LeaveToDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('toDate').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('toDate')._flatpickr) {" +
		            "  document.getElementById('toDate')._flatpickr.setDate('" + LeaveToDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on toDate'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean ToLeaveDurationDropDownInAdmin()
	{
		try {
			utils.waitForEle(ToLeaveDurationDropDownInAdmin, "visible", "", 10);
			
			ToLeaveDurationDropDownInAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToLeaveDurationFullDaySelectedInAdmin()
	{
		try {
			utils.waitForEle(ToLeaveDurationFullDaySelectedInAdmin, "visible", "", 10);
			
			ToLeaveDurationFullDaySelectedInAdmin.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ApplyForOthersButton()
	{
		try {
			
			utils.waitForEle(ApplyForOthersButton, "visible", "", 30);
			
			ApplyForOthersButton.click();
			Thread.sleep(4000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//6th TestCase
	
	public boolean ApplyForMeButton()
	{
		try {
			utils.waitForEle(ApplyForMeButton, "visible", "", 10);
			
			ApplyForMeButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeSelectionDropDownOfLeaveForMe()
	{
		try {
			utils.waitForEle(LeaveTypeSelectionDropDownOfLeaveForMe, "visible", "", 10);
			
			LeaveTypeSelectionDropDownOfLeaveForMe.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeNameTextFieldOfLeaveForMe(String LeaveType)
	{
		try {
			utils.waitForEle(LeaveTypeNameTextFieldOfLeaveForMe, "visible", "", 10);
			
			LeaveTypeNameTextFieldOfLeaveForMe.clear();
			LeaveTypeNameTextFieldOfLeaveForMe.sendKeys( LeaveType);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeSelectedOfLeaveForMe()
	{
		try {
			utils.waitForEle(LeaveTypeSelectedOfLeaveForMe, "visible", "", 10);
			
			LeaveTypeSelectedOfLeaveForMe.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateClickedLeaveForMe()
	{
		try {
			utils.waitForEle(FromDateClickedLeaveForMe, "visible", "", 10);
			
			FromDateClickedLeaveForMe.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FromDateSelectedLeaveForMe(String LeaveFromDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('fromDateForMe').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('fromDateForMe')._flatpickr) {" +
		            "  document.getElementById('fromDateForMe')._flatpickr.setDate('" + LeaveFromDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on fromDateForMe'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean FromLeaveDurationSelectedOfLeaveForMe(String LeaveDuration)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(FromLeaveDurationSelectedOfLeaveForMe, "visible", "", 10);
			
		Select sel = new Select(FromLeaveDurationSelectedOfLeaveForMe);
		sel.selectByVisibleText(LeaveDuration);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDateClickedLeaveForMe()
	{
		try {
			utils.waitForEle(ToDateClickedLeaveForMe, "visible", "", 10);
			
			ToDateClickedLeaveForMe.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ToDateSelectedLeaveForMe(String LeaveToDate) {
		 try {
			 Thread.sleep(3000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('toDateForMe').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('toDateForMe')._flatpickr) {" +
		            "  document.getElementById('toDateForMe')._flatpickr.setDate('" + LeaveToDate + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on toDateForMe'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean ToLeaveDurationSelectedOfLeaveForMe(String LeaveDuration)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(ToLeaveDurationSelectedOfLeaveForMe, "visible", "", 10);
			
		Select sel = new Select(ToLeaveDurationSelectedOfLeaveForMe);
		sel.selectByVisibleText(LeaveDuration);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveReasonOfLeaveForMe(String LeaveReason)
	{
		try {
			utils.waitForEle(LeaveReasonOfLeaveForMe, "visible", "", 10);
			
			LeaveReasonOfLeaveForMe.clear();
			LeaveReasonOfLeaveForMe.sendKeys( LeaveReason);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ApplyButtonOfLeaveForMe()
	{
		try {
			utils.waitForEle(ApplyButtonOfLeaveForMe, "visible", "", 10);
			
			ApplyButtonOfLeaveForMe.click();
			Thread.sleep(5000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	//7th TestCase
	
	public boolean EmpIDCheckBoxSearchOption()
	{
		try {
			utils.waitForEle(EmpIDCheckBoxSearchOption, "visible", "", 10);
			
			EmpIDCheckBoxSearchOption.click();
			Thread.sleep(5000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ReasonCheckBoxSearchOption()
	{
		try {
			utils.waitForEle(ReasonCheckBoxSearchOption, "visible", "", 10);
			
			ReasonCheckBoxSearchOption.click();
			Thread.sleep(5000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmpIDInTable(String Empid) {
	    try {
	        utils.waitForEle(EmpIDInTable, "visible", "", 10);

	        String actualText = EmpIDInTable.getText().trim();
	        if (actualText.contains(Empid)) {
	            return true;
	            
	        } else {
	            throw new Exception("Employee ID not found in table. Expected: " + Empid + ", Found: " + actualText);
	        }
	       

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean LeaveStatusInEmpTable(String reason) {
	    try {
	        utils.waitForEle(LeaveStatusInEmpTable, "visible", "", 10);

	        String actualText = LeaveStatusInEmpTable.getText().trim();
	        if (actualText.contains(reason)) {
	            return true;
	        } else {
	            throw new Exception("Employee Leave Request  Status not found in table. Expected: " + reason + ", Found: " + actualText);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean LeaveReasonInEmpTable(String reason) {
	    try {
	        utils.waitForEle(LeaveReasonInEmpTable, "visible", "", 10);

	        String actualText = LeaveReasonInEmpTable.getText().trim();
	        if (actualText.contains(reason)) {
	            return true;
	        } else {
	            throw new Exception("Employee Leave Request  Reason not found in table. Expected: " + reason + ", Found: " + actualText);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean LeaveRejectReasonInTable(String reason) {
	    try {
	        utils.waitForEle(LeaveRejectReasonInTable, "visible", "", 10);

	        String actualText = LeaveRejectReasonInTable.getText().trim();
	        if (actualText.contains(reason)) {
	            return true;
	        } else {
	            throw new Exception("Employee Leave Request  Reject Reason not found in table. Expected: " + reason + ", Found: " + actualText);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	public boolean LeaveNameInTable(String reason) {
	    try {
	        utils.waitForEle(LeaveNameInTable, "visible", "", 10);

	        String actualText = LeaveNameInTable.getText().trim();
	        if (actualText.contains(reason)) {
	            return true;
	        } else {
	            throw new Exception("Employee Request Leave Name not found in table. Expected: " + reason + ", Found: " + actualText);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean ReasonInTable(String reason) {
	    try {
	        utils.waitForEle(ReasonInTable, "visible", "", 10);

	        String actualText = ReasonInTable.getText().trim();
	        if (actualText.contains(reason)) {
	            return true;
	        } else {
	            throw new Exception("Employee Request Reason not found in table. Expected: " + reason + ", Found: " + actualText);
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	//8th TestCase
	public boolean FilterButton()
	{
		try {
			
			utils.waitForEle(FilterButton, "visible", "", 15);
			
			FilterButton.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OfficeDropDownSelected(String officename)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(OfficeDropDownSelected, "visible", "", 10);
			
			Select sel = new Select(OfficeDropDownSelected);
			sel.deselectByVisibleText(officename);
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DeptDropDownClicked()
	{
		try {
			utils.waitForEle(DeptDropDownClicked, "visible", "", 10);
			
			DeptDropDownClicked.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DeptDropDownSelected()
	{
		try {
			utils.waitForEle(DeptDropDownSelected, "visible", "", 10);
			
			DeptDropDownSelected.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamDropDownClicked()
	{
		try {
			utils.waitForEle(TeamDropDownClicked, "visible", "", 10);
			
			TeamDropDownClicked.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamDropDownSelected()
	{
		try {
			utils.waitForEle(TeamDropDownSelected, "visible", "", 10);
			
			TeamDropDownSelected.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SaveFilterButton()
	{
		try {
			utils.waitForEle(SaveFilterButton, "visible", "", 10);
			
			SaveFilterButton.click();
			Thread.sleep(4000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	

	public boolean LeaveTypesFilter(String LeaveType) {
	    try {
	        // Scroll into view
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", LeaveTypesFilter);

	        // Wait until dropdown is visible
	        utils.waitForEle(LeaveTypesFilter, "visible", "", 10);

	        // Select option by visible text
	        Select sel = new Select(LeaveTypesFilter);
	        sel.selectByVisibleText(LeaveType);

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean LeaveStatusFilter(String LeaveStatus) {
	    try {
	        // Scroll into view
	    	Thread.sleep(3000);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", LeaveStatusFilter);

	        // Wait until dropdown is visible
	   
	        utils.waitForEle(LeaveStatusFilter, "visible", "", 10);
	        Thread.sleep(3000);
	        // Select option by visible text
	        Select sel = new Select(LeaveStatusFilter);
	        sel.selectByVisibleText(LeaveStatus);

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean LeaveTypesearchResults(String LeaveType) {
	    try {
	        Thread.sleep(2000);  // ensure results loaded

	        for (WebElement leave : LeaveTypesearchResults) {
	            String actual = leave.getText().trim();

	            if (actual.equalsIgnoreCase(LeaveType) || actual.toLowerCase().contains(LeaveType.toLowerCase())) {
	                driver.navigate().refresh();  // refresh ONLY after success
	                return true;
	            }
	        }

	        // If no match found
	        driver.navigate().refresh();  // refresh even after failure
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}


	
	public boolean LeaveStatusSearchResults(String Leavestatus) {
	    try {
	        // Small wait to ensure elements are loaded
	        Thread.sleep(2000);

	        for (WebElement statusElement : LeaveStatusSearchResults) {
	            if (statusElement.getText().trim().toLowerCase().contains(Leavestatus.toLowerCase())) {
	                return true; // Found matching leave status (partial match)
	            }
	        }

	        throw new Exception("No matching leave status found: " + Leavestatus);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean OfficeDrpDownClicked()
	{
		try {
			utils.waitForEle(OfficeDrpDownClicked, "visible", "", 10);
			
			OfficeDrpDownClicked.click();
			Thread.sleep(4000);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean OfficeNameInputted(String officename)
	{
		try {
			utils.waitForEle(OfficeNameInputted, "visible", "", 10);
			
			OfficeNameInputted.sendKeys(officename);
		
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean OfficeNameSelectedOfFilter()
	{
		try {
			utils.waitForEle(OfficeNameSelectedOfFilter, "visible", "", 10);
			
			OfficeNameSelectedOfFilter.click();
		
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean compareLeaveStatistics() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // ✅ Ensure accordion is expanded before reading counts
	        WebElement accordionBody = driver.findElement(By.id("collapseOneLeaveTab"));
	        if (!accordionBody.getAttribute("class").contains("show")) {
	            WebElement accordionHeader = driver.findElement(By.id("headingOne"));
	            accordionHeader.click();
	            wait.until(ExpectedConditions.attributeContains(accordionBody, "class", "show"));
	        }

	        // ✅ Wait for counts to be visible
	        int uiApplied = Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AppliedLeaves"))).getText().trim());
	        int uiPending = Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PendingLeaves"))).getText().trim());
	        int uiApproved = Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ApprovedLeaves"))).getText().trim());
	        int uiRejected = Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("RejectedLeaves"))).getText().trim());
	        int uiRevoked = Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("RevokedLeaves"))).getText().trim());
	        int uiCancelled = Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CancelledLeaves"))).getText().trim());

	        System.out.println(uiApplied + " Applied");
	        System.out.println(uiPending + " Pending");
	        System.out.println(uiApproved + " Approved");
	        System.out.println(uiRejected + " Rejected");
	        System.out.println(uiRevoked + " Revoked");
	        System.out.println(uiCancelled + " Cancelled");

	        // --- Step 2: Wait for Table Data to Load ---
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dtLeave")));
	        List<WebElement> statusCells = driver.findElements(By.xpath("//table[@id='dtLeave']/tbody/tr/td[9]"));

	        int tableTotal = statusCells.size();
	        int tablePending = 0, tableApproved = 0, tableRejected = 0, tableRevoked = 0, tableCancelled = 0;

	        for (WebElement cell : statusCells) {
	            String status = cell.getText().trim().toLowerCase();
	            if (status.contains("pending")) tablePending++;
	            else if (status.contains("approved")) tableApproved++;
	            else if (status.contains("rejected")) tableRejected++;
	            else if (status.contains("revoked")) tableRevoked++;
	            else if (status.contains("cancelled")) tableCancelled++;
	        }

	        // --- Step 3: Comparison ---
	        boolean allMatch = true;
	        if (uiApplied != tableTotal) {
	            System.out.println("Mismatch: Applied Leaves UI=" + uiApplied + ", Table=" + tableTotal);
	            allMatch = false;
	        }
	        if (uiPending != tablePending) {
	            System.out.println("Mismatch: Pending UI=" + uiPending + ", Table=" + tablePending);
	            allMatch = false;
	        }
	        if (uiApproved != tableApproved) {
	            System.out.println("Mismatch: Approved UI=" + uiApproved + ", Table=" + tableApproved);
	            allMatch = false;
	        }
	        if (uiRejected != tableRejected) {
	            System.out.println("Mismatch: Rejected UI=" + uiRejected + ", Table=" + tableRejected);
	            allMatch = false;
	        }
	        if (uiRevoked != tableRevoked) {
	            System.out.println("Mismatch: Revoked UI=" + uiRevoked + ", Table=" + tableRevoked);
	            allMatch = false;
	        }
	        if (uiCancelled != tableCancelled) {
	            System.out.println("Mismatch: Cancelled UI=" + uiCancelled + ", Table=" + tableCancelled);
	            allMatch = false;
	        }

	        if (allMatch) {
	            System.out.println("✅ Comparison Passed: UI and Table values match.");
	        } else {
	            System.out.println("❌ Comparison Failed: UI and Table values do not match.");
	        }

	        return allMatch;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        System.out.println("Error in compareLeaveStatistics: " + e.getMessage());
	        return false;
	    }
	}
	
	public Map<String, Integer> getLeaveStatusCounts() {
	    Map<String, Integer> statusCountMap = new HashMap<>();
	    try {
	        List<WebElement> statusCells = driver.findElements(By.xpath("//table[@id='dtLeave']/tbody/tr/td[9]"));

	        for (WebElement cell : statusCells) {
	            String statusText = cell.getText().trim();
	            statusCountMap.put(statusText, statusCountMap.getOrDefault(statusText, 0) + 1);
	        }

	        System.out.println("Table Status Counts: " + statusCountMap);

	    } catch (Exception e) {
	        System.out.println("Error fetching table counts: " + e.getMessage());
	        exceptionDesc = e.getMessage();
	    }
	    return statusCountMap;
	}
	
	
	
	public boolean MonthlyLeaveSummaryDrpIcon()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(MonthlyLeaveSummaryDrpIcon, "visible", "", 10);
			
			MonthlyLeaveSummaryDrpIcon.click();
			
			utils.waitForEle(StatisticsDisplayed, "visible", "", 10);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean compareLeaveStatisticsOfEmp() {
	    try {
	        // --- Step 1: Get UI Statistics ---
	        int uiApplied = Integer.parseInt(driver.findElement(By.id("AppliedLeaves")).getText().trim());
	        int uiPending = Integer.parseInt(driver.findElement(By.id("PendingLeaves")).getText().trim());
	        int uiApproved = Integer.parseInt(driver.findElement(By.id("ApprovedLeaves")).getText().trim());
	        int uiRejected = Integer.parseInt(driver.findElement(By.id("RejectedLeaves")).getText().trim());
	        int uiRevoked = Integer.parseInt(driver.findElement(By.id("RevokedLeaves")).getText().trim());
	        int uiCancelled = Integer.parseInt(driver.findElement(By.id("CancelledLeaves")).getText().trim());
	        
	        System.out.println(uiApplied + "Applied");
	        System.out.println(uiPending + "pending");
	        System.out.println(uiApproved + "Approved");
	        System.out.println(uiRejected + "Rejected");
	        System.out.println(uiRevoked + "Revoked");
	       

	        // --- Step 2: Get Table Data ---
	        List<WebElement> statusCells = driver.findElements(By.xpath("//table[@id='dtMyLeaveRequest']/tbody/tr/td[7]"));
	        int tableTotal = statusCells.size();
	        int tablePending = 0, tableApproved = 0, tableRejected = 0, tableRevoked = 0, tableCancelled = 0;

	        for (WebElement cell : statusCells) {
	            String status = cell.getText().trim().toLowerCase();
	            if (status.contains("pending")) tablePending++;
	            else if (status.contains("approved")) tableApproved++;
	            else if (status.contains("rejected")) tableRejected++;
	            else if (status.contains("revoked")) tableRevoked++;
	            else if (status.contains("cancelled")) tableCancelled++;
	        }

	        // --- Step 3: Comparison ---
	        boolean allMatch = true;
	        if (uiApplied != tableTotal) {
	            System.out.println("Mismatch: Applied Leaves UI=" + uiApplied + ", Table=" + tableTotal);
	            allMatch = false;
	        }
	        if (uiPending != tablePending) {
	            System.out.println("Mismatch: Pending UI=" + uiPending + ", Table=" + tablePending);
	            allMatch = false;
	        }
	        if (uiApproved != tableApproved) {
	            System.out.println("Mismatch: Approved UI=" + uiApproved + ", Table=" + tableApproved);
	            allMatch = false;
	        }
	        if (uiRejected != tableRejected) {
	            System.out.println("Mismatch: Rejected UI=" + uiRejected + ", Table=" + tableRejected);
	            allMatch = false;
	        }
	        if (uiRevoked != tableRevoked) {
	            System.out.println("Mismatch: Revoked UI=" + uiRevoked + ", Table=" + tableRevoked);
	            allMatch = false;
	        }
	        if (uiCancelled != tableCancelled) {
	            System.out.println("Mismatch: Cancelled UI=" + uiCancelled + ", Table=" + tableCancelled);
	            allMatch = false;
	        }

	        if (allMatch) {
	            System.out.println("Comparison Passed: UI and Table values match.");
	        } else {
	            System.out.println("Comparison Failed: UI and Table values do not match.");
	        }

	        return allMatch;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        System.out.println("Error in compareLeaveStatistics: " + e.getMessage());
	        return false;
	    }
	}
	
	
	
	public boolean MonthFilterContains(String date) {
	    try {
	    	Thread.sleep(4000);
	        // Convert "2025-08-13" → "August 2025"
	        LocalDate inputDate = LocalDate.parse(date);
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
	        String expectedMonth = inputDate.format(formatter);

	        System.out.println("Expected Month: " + expectedMonth);

	        // loop max 12 times (full year), avoid infinite loop
	        for (int i = 0; i < 12; i++) {

	            utils.waitForEle(MonthFilterContains, "visible", "", 20);

	            String uiMonth = MonthFilterContains.getText().trim();
	            System.out.println("UI Month: " + uiMonth);

	            // ✅ If match found, return true
	            if (uiMonth.equalsIgnoreCase(expectedMonth)) {
	                return true;
	            }

	            // ✅ If not match → click back and retry
	            MonthFilterBackButton.click();
	          

	            // Now go again inside Attendance > Month filter (since you returned back)
	            // NOTE: You must call your AttendanceButton method here
	            
	        }

	        // ❌ Month never found after 12 checks
	        exceptionDesc = "Month not matched within navigation loops";
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}


	
	public boolean PendingLeaveCount()
	{
		try {
			utils.waitForEle(PendingLeaveCount, "visible", "", 10);
			
			PendingLeaveCount.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	
	public boolean LeaveValidationInEmp(String inputDate, String expectedStatus) {
	    try {
	        utils.waitForEle(EmpLeaveRequestOnEmp, "visible", "", 10);

	        // --- Convert yyyy-MM-dd → Tue, 04-11-2025 ---
	        String expectedDateUI = "";
	        try {
	            DateTimeFormatter sourceFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy");

	            LocalDate date = LocalDate.parse(inputDate, sourceFormat);
	            expectedDateUI = date.format(uiFormat);
	        } catch (Exception e) {
	            return false; // invalid date input
	        }

	        boolean dateMatch = false;
	        boolean statusMatch = false;

	        // --- Validate Date ---
	        for (WebElement dateEle : LeaveFromDateInTableInEmp) {
	            String actualDate = dateEle.getText().trim();

	            if (actualDate.contains(expectedDateUI)) {
	                dateMatch = true;
	                break;
	            }
	        }

	        // --- Validate Status ---
	        for (WebElement statusEle : LeaveStatusInTableInEmp) {
	            String actualStatus = statusEle.getText().trim();

	            if (actualStatus.contains(expectedStatus)) {
	                statusMatch = true;
	                break;
	            }
	        }

	        return dateMatch && statusMatch;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean RejectSummaryCount()
	{
		try {
			utils.waitForEle(RejectSummaryCount, "visible", "", 10);
			
			RejectSummaryCount.click();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	
	public boolean LeaveValidationInAdmin(String inputDate, String expectedStatus, String empid, String leavetype) {
	    try {
	        utils.waitForEle(EmpIDRow, "visible", "", 10);

	        // --- Convert yyyy-MM-dd → Tue, 04-11-2025 ---
	        String expectedDateUI;
	        try {
	            DateTimeFormatter sourceFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            DateTimeFormatter uiFormat = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy");

	            LocalDate date = LocalDate.parse(inputDate, sourceFormat);
	            expectedDateUI = date.format(uiFormat);
	        } catch (Exception e) {
	            return false; // invalid date input
	        }

	        // --- Validate row-wise data ---
	        for (int i = 0; i < EmpIDOnAdmin.size(); i++) {

	            String actualEmpId = EmpIDOnAdmin.get(i).getText().trim();
	            String actualLeaveType = EmpLeaveTypeOnAdmin.get(i).getText().trim();
	            String actualLeaveDate = EmpLeaveDateOnAdmin.get(i).getText().trim();
	            String actualLeaveStatus = EmpLeaveStatusOnAdmin.get(i).getText().trim();

	            if (actualEmpId.contains(empid)
	                    && actualLeaveType.contains(leavetype)
	                    && actualLeaveDate.contains(expectedDateUI)
	                    && actualLeaveStatus.contains(expectedStatus)) {

	                return true;   // Found matching row
	            }
	        }

	        return false;   // No matching row found

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean EditLeaveBalanceForSickLeave() {
	    try {
	        utils.waitForEle(EditLeaveBalanceForSickLeave, "visible", "", 20);

	        EditButtonForSickLeave.click();
	       
	       

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}

	public boolean EditLeaveBalanceForTravelLeave() {
	    try {
	        utils.waitForEle(EditLeaveBalanceForTravelLeave, "visible", "", 20);

	        EditButtonForTravelLeave.click();
	       
	       

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	
	public boolean EditLeaveBalanceForCasualLeave() {
	    try {
	        utils.waitForEle(EditLeaveBalanceForCasualLeave, "visible", "", 20);

	        EditButtonForCasualLeave.click();
	       
	       

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	
	public boolean FixedBalanceButton() {
	    try {
	        utils.waitForEle(FixedBalanceButton, "visible", "", 20);

	        FixedBalanceButton.click();

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean FixedBalanceConfirmButton() {
	    try {
	        utils.waitForEle(FixedBalanceConfirmButton, "visible", "", 20);

	        FixedBalanceConfirmButton.click();

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean NoOfCreditInput(String days) {
	    try {
	        utils.waitForEle(NoOfCreditInput, "visible", "", 20);

	        NoOfCreditInput.clear();
	        NoOfCreditInput.sendKeys(days);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean RepeatCreditSelected() {
	    try {
	    	Thread.sleep(4000);
	        utils.waitForEle(RepeatCreditSelected, "visible", "", 20);

	   Select sel = new Select(RepeatCreditSelected);
	   sel.selectByVisibleText("Monthly");

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	
	public boolean AllowhalfDayLeave() {
	    try {
	        utils.waitForEle(AllowhalfDayLeave, "visible", "", 20);

	        AllowhalfDayLeave.click();

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean EditLeaveSaveButton() {
	    try {
	        utils.waitForEle(EditLeaveSaveButton, "visible", "", 20);

	        EditLeaveSaveButton.click();

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	



	private String normalize(String text) {
	    return text.toLowerCase()
	               .replace("(", "")
	               .replace(")", "")
	               .replace("_", " ")
	               .trim();
	}

	public boolean validateLeaveBalance(String empId, String leaveType, String expectedCount) {
	    try {
	        utils.waitForEle(LeaveBalanceEmpIDRow, "visible", "", 10);

	        String normalizedEmpId = empId.startsWith("#") ? empId : ("#" + empId);

	        int rowCount = LeaveBalanceRows.size();

	        for (int i = 1; i <= rowCount; i++) {

	            String uiEmpId = driver.findElement(
	                    By.xpath("//table[@id='dtLeaveBalance']/tbody/tr[" + i + "]/td[1]//p[2]")
	            ).getText().trim();

	            String uiLeaveType = driver.findElement(
	                    By.xpath("//table[@id='dtLeaveBalance']/thead/tr[" + i + "]/th[4]/div")
	            ).getText().trim();

	            String uiCount = driver.findElement(
	                    By.xpath("//table[@id='dtLeaveBalance']/tbody/tr[" + i + "]/td[4]//span[@class='text_success-light']")
	            ).getText().trim();

	            // Logging
	            System.out.println("ROW-" + i +
	                    " | UI EmpID: " + uiEmpId +
	                    " | UI LeaveType: " + uiLeaveType +
	                    " | UI Count: " + uiCount);

	            if (!uiEmpId.equalsIgnoreCase(normalizedEmpId))
	                continue;

	            // normalize both UI and input
	            if (!normalize(uiLeaveType).equals(normalize(leaveType)))
	                continue;

	            if (uiCount.equalsIgnoreCase(expectedCount)) {
	                return true;
	            } else {
	                exceptionDesc = "Count mismatch → Expected: " + expectedCount + ", UI: " + uiCount;
	                return false;
	            }
	        }

	        exceptionDesc = "No matching row found for EmpID: " + normalizedEmpId +
	                        ", LeaveType: " + leaveType +
	                        ", ExpectedCount: " + expectedCount;
	        return false;

	    } catch (Exception e) {
	        exceptionDesc = "Exception: " + e.getMessage();
	        return false;
	    }
	}
	
	
	
	public boolean validateLeaveBalanceRow(
	        String empId,
	        String expectedLeaveType,
	        String expectedDayCount,
	        String inputDateYMD) {

	    try {

	        // Convert yyyy-MM-dd → dd MMM (UI format)
	        String expectedDateUI = "";
	        try {
	            LocalDate dt = LocalDate.parse(inputDateYMD);
	            expectedDateUI = dt.format(DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH));
	        } catch (Exception ex) {
	            exceptionDesc = "Invalid date format: " + inputDateYMD;
	            return false;
	        }

	        // -------------------------
	        // 1. Validate Date in Header
	        // -------------------------
	        List<WebElement> headerDates = driver.findElements(
	                By.xpath("//table[@id='dtWeeklyLeaveCalender']/thead/tr[1]/th")
	        );

	        boolean dateFoundInHeader = false;

	        for (WebElement th : headerDates) {
	            String uiHeaderDate = th.getText().trim();
	            if (uiHeaderDate.contains(expectedDateUI)) {
	                dateFoundInHeader = true;
	                break;
	            }
	        }

	        if (!dateFoundInHeader) {
	            exceptionDesc = "Date not found in header. Expected: " + expectedDateUI;
	            return false;
	        }

	        // -------------------------
	        // 2. Validate Table Row
	        // -------------------------
	        for (WebElement row : weeklyLeaveRows) {

	            WebElement empIdCell     = row.findElement(By.xpath("./td[1]"));
	            WebElement leaveTypeCell = row.findElement(By.xpath("./td[2]"));
	            WebElement dayCountCell  = row.findElement(By.xpath("./td[3]"));

	            String uiEmpId     = empIdCell.getText().trim();
	            String uiLeaveType = leaveTypeCell.getText().trim();
	            String uiDayCount  = dayCountCell.getText().trim()
	                                    .replace("\n", " ")
	                                    .replaceAll("\\s+", " ");

	            // Match Emp
	            if (!uiEmpId.contains(empId)) continue;

	            // Validate Leave Type
	            if (!uiLeaveType.contains(expectedLeaveType)) {
	                exceptionDesc = "LeaveType mismatch. Expected: " + expectedLeaveType +
	                        ", UI: " + uiLeaveType;
	                return false;
	            }

	            // Validate Day Count
	            String expectedDayText = expectedDayCount + " Day";
	            if (!uiDayCount.contains(expectedDayText)) {
	                exceptionDesc = "DayCount mismatch. Expected: " + expectedDayText +
	                        ", UI: " + uiDayCount;
	                return false;
	            }

	            // ✔ All validations passed
	            return true;
	        }

	        exceptionDesc = "No matching row found for EmpID: " + empId;
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
