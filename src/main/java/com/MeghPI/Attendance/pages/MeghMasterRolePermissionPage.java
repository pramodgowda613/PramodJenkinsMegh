package com.MeghPI.Attendance.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

public class MeghMasterRolePermissionPage {
	WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
	public String RoleNames = "";

	public MeghMasterRolePermissionPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//input[@type='search'])[2]")
	private WebElement RoleSearchTextField; // 1st TestCase

	@FindBy(xpath = "//input[@id='chkdivDrpItemIsActivedtRole']")
	private WebElement StatusCheckbox; // 1st TestCase

	@FindBy(xpath = "(//button[@id='btnHeaderFilter'])[2]")
	private WebElement SearchDropDown; // 1st TestCase

	@FindBy(xpath = "//button[@id='tab_RolePermission']")
	private WebElement RolePermissionButton; // 1St TestCase

	@FindBy(xpath = "//select[@name='dtRole_length']")
	private WebElement RoleModulePagination; // 1St TestCase

	@FindBy(xpath = "(//button[@id='btnAddRolePermission'])[2]")
	private WebElement AddRolePermissionButton; // 1St TestCase

	@FindBy(xpath = "//select[@id='drpRole']")
	private WebElement SelectRoleDropDown; // 1St TestCase

	@FindBy(xpath = "//label[text()='Settings']/../input")
	private WebElement SettingsCheckBox; // 2nd TestCase

	@FindBy(xpath = "//label[text()='Master']")
	private WebElement MasterModuleExpand; // 2nd TestCase

	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	private WebElement MasterModuleCheckBox; // 2nd TestCase

	@FindBy(xpath = "//button[@id='btnRolePermissionSave']")
	private WebElement RolePermissionSaveButton; // 2nd TestCase

	@FindBy(xpath = "//input[@aria-controls='dtRolePermission']")
	private WebElement RolePermissionSearchField; // 2nd TestCase

	@FindBy(xpath = "//img[@alt='table-edit-icon']")
	private WebElement RolePermissionEditIcon; // 2nd TestCase

	@FindBy(xpath = "//span[@aria-labelledby='select2-drpRole-container']")
	private WebElement RoleDropDownClick; // 2nd TestCase

	@FindBy(xpath = "//input[@aria-controls='select2-drpRole-results']")
	private WebElement RoleDropDownSearch; // 2nd TestCase

	@FindBy(xpath = "//li[contains(@id,'select2-drpRole-result')]")
	private WebElement RoleDropDownSearchResult; // 2nd TestCase

	@FindBy(xpath = "//table[@id='dtRolePermission']/tbody/tr[1]/td[1]")
	private WebElement RolePermissionSearchResult; // 3rd TestCase

	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	private WebElement EmployeeAttendancePermission; // 4th TestCase

	@FindBy(xpath = "//body[@id='addCustomClassForBody']/section/div/div/div[1]/div/div[2]/a/img")
	private WebElement ManageButton; // 4th TestCase

	@FindBy(xpath = "//button[@id='emp_logout_tab']")
	private WebElement LogoutButton; // 4th TestCase

	@FindBy(xpath = "//input[@id='ManageRole']")
	private WebElement EmployeeToggleSwitch; // 4th TestCase

	@FindBy(xpath = "(//p[text()='Attendance'])[1]")
	private WebElement EmployeeAttendanceButton; // 4th TestCase

	@FindBy(xpath = "//a[@id='empAttendance_Tab']")
	private WebElement EmpAttendanceTab; // 4th TestCase

	@FindBy(xpath = "//a[@id='myRegularizationRquest_tab']")
	private WebElement EmpRegulationRequest; // 4th TestCase

	@FindBy(xpath = "//a[@id='myOverTime_tab']")
	private WebElement EmpOverTimeRequest; // 4th TestCase

	@FindBy(xpath = "//a[@id='myShiftRequest_tab']")
	private WebElement EmpShiftRequest; // 4th TestCase

	@FindBy(xpath = "//a[@id='myWeekOffRequest_tab']")
	private WebElement EmpWeekOffRequest; // 4th TestCase

	@FindBy(xpath = "//input[@id='Main_4']")
	private WebElement LeavePermissionCheckBox; // 5th TestCase

	@FindBy(xpath = "//li[@id= 'v-pills-LeaveTab-tab']")
	private WebElement LeaveButton; // 5th TestCase

	@FindBy(xpath = "//a[@id='leaveRequest_tab']")
	private WebElement LeaveRequestsTab; // 5th TestCase

	@FindBy(xpath = "//a[@id='leaveBalance_tab']")
	private WebElement LeaveBalanceTab; // 5th TestCase

	@FindBy(xpath = "//input[@id='Main_6']")
	private WebElement ReportPermissionCheckBox; // 6th TestCase

	@FindBy(xpath = "//div[@class='nav-icon navigation-icon report-nicon']")
	private WebElement ReprtButton; // 6th TestCase

	@FindBy(xpath = "//p[text()='Attendance Report']")
	private WebElement AttendanceReport; // 6th TestCase

	@FindBy(xpath = "//p[text()='Time Log - Early and Late In/Out']")
	private WebElement TimeLogReport; // 6th TestCase

	@FindBy(xpath = "//p[text()='Regularization Report']")
	private WebElement RegulationReport; // 6th TestCase

	@FindBy(xpath = "//p[text()='Over Time']")
	private WebElement OverTimeReport; // 6th TestCase

	@FindBy(xpath = "//p[text()='Total Leaves Taken']")
	private WebElement TotalLeavesTakenReport; // 6th TestCase

	@FindBy(xpath = "//p[text()='Weekly-off']")
	private WebElement WeekOffReport; // 6th TestCase

	@FindBy(xpath = "//a[@id='PunchReport_Tab']")
	private WebElement PunchReport; // 6th TestCase

	@FindBy(xpath = "//a[@id='inOutReport_Tab']")
	private WebElement InOutReport; // 6th TestCase

	@FindBy(xpath = "//input[@id='Main_5']")
	private WebElement ShiftPermission; // 7th TestCase

	@FindBy(xpath = "//input[@id='Main_7']")
	private WebElement DirectoryPermission; // 7th TestCase

	@FindBy(xpath = "//input[@id='Main_66']")
	private WebElement FilesPermission; // 7th TestCase

	@FindBy(xpath = "//button[@id='sidebarToggle']/../div[1]/div/ul/li[contains(@title,'Shift')]")
	private WebElement ShiftButton; // 7th TestCase

	@FindBy(xpath = "//a[@id='myShift_tab']")
	private WebElement MyShiftTab; // 7th TestCase

	@FindBy(xpath = "//div[@class='nav-icon navigation-icon file-nicon']")
	private WebElement FileButton; // 7th TestCase

	@FindBy(xpath = "//a[@id='myFiles_tab']")
	private WebElement MyFilesTab; // 7th TestCase

	@FindBy(xpath = "//a[@id='empCompanyFiles_tab']")
	private WebElement CompanyFilesTab; // 7th TestCase

	@FindBy(xpath = "//div[@class='nav-icon navigation-icon directory-nicon']")
	private WebElement EmpDirectoryButton; // 7th TestCase

	@FindBy(xpath = "//a[@id='dir_myEmployee_tab']")
	private WebElement MyEmployeeTab; // 7th TestCase

	@FindBy(xpath = "//a[@id='dir_org_chart']")
	private WebElement ORGChartTab; // 7th TestCase

	@FindBy(xpath = "//input[@id='Main_3']")
	private WebElement HrAttendancePermission; // 8th TestCase

	@FindBy(xpath = "//a[@id='attendancEmployee_tab']")
	private WebElement HrAccountAttendanceEmpTab; // 8th TestCase

	@FindBy(xpath = "//a[@id='regularizationRquest_tab']")
	private WebElement HrAccountRegulationRequestTab; // 8th TestCase

	@FindBy(xpath = "//a[@id='overTime_tab']")
	private WebElement HrAccountOTRequestTab; // 8th TestCase

	@FindBy(xpath = "//a[@id='shiftRequest_tab']")
	private WebElement HrAccountShiftRequestTab; // 8th TestCase

	@FindBy(xpath = "//a[@id='weekOffRequest_tab']")
	private WebElement HrAccountWeekOffRequestTab; // 8th TestCase

	@FindBy(xpath = "//a[@id='attendanceReProcess_tab']")
	private WebElement HrAccountAttendanceReprocessTab; // 8th TestCase

	@FindBy(xpath = "//a[@id='weeklyLeaveCalender_tab']")
	private WebElement HrAccountLeaveWeeklyLeaveTab; // 9th TestCase

	@FindBy(xpath = "//a[@id='allEmployeeShiftDetails_tab']")
	private WebElement HrAccountShiftTab; // 9th TestCase

	@FindBy(xpath = "//a[@id='shiftRoaster_tab']")
	private WebElement HrAccountShiftRosterTab; // 9th TestCase

	@FindBy(xpath = "//p[text()='Daily Attendance Report']")
	private WebElement DailyAttendanceReport; // 10th TestCase

	@FindBy(xpath = "//p[text()='Daily Bill Transaction Report']")
	private WebElement DailyBillTransactionReport; // 10th TestCase

	@FindBy(xpath = "//p[text()='Punch Type analytics']")
	private WebElement PunchTypeAnalyticsReport; // 10th TestCase

	@FindBy(xpath = "//p[text()='Employee Active/InActive Report']")
	private WebElement EmployeeActiveInActiveReport; // 10th TestCase

	@FindBy(xpath = "//p[text()='Employee Block/Unblock Report']")
	private WebElement EmployeeBlockUnblockReport; // 10th TestCase

	@FindBy(xpath = "//p[text()='Employee Data Report']")
	private WebElement EmployeeDataReport; // 10th TestCase

	@FindBy(xpath = "//p[text()='Form No. 23 Report']")
	private WebElement FormNo23Report; // 10th TestCase

	@FindBy(xpath = "//p[text()='Form No. 25 Report']")
	private WebElement FormNo25Report; // 10th TestCase

	@FindBy(xpath = "//p[text()='Leave Balance Summary']")
	private WebElement LeaveBalanceSummary; // 10th TestCase

	@FindBy(xpath = "//p[text()='Late Arrival Report']")
	private WebElement LateArrivalReport; // 10th TestCase

	@FindBy(xpath = "//p[text()='Monthly Bill Transaction Report']")
	private WebElement MonthlyBillTransactionReport; // 10th TestCase

	@FindBy(xpath = "//p[text()='Monthly Shift Wise Attendance Report']")
	private WebElement MonthlyShiftWiseAttendanceReport; // 10th TestCase

	@FindBy(xpath = "//p[text()='Monthly Attendance Register Report']")
	private WebElement MonthlyAttendanceRegisterReport; // 10th TestCase

	@FindBy(xpath = "//p[text()='Muster Roll Report']")
	private WebElement MusterRollReport; // 10th TestCase

	@FindBy(xpath = "//p[text()='Onboarding and Offboarding']")
	private WebElement OnboardingAndOffboarding; // 10th TestCase

	@FindBy(xpath = "//p[text()='Over Time Report']")
	private WebElement OverTimeReports; // 10th TestCase

	@FindBy(xpath = "//a[@id='PunchStatisticsReport_Tab']")
	private WebElement PunchStatisticsReportTab; // 10th TestCase

	@FindBy(xpath = "//p[text()='Schedule Reports']")
	private WebElement ScheduleReports; // 10th TestCase

	@FindBy(xpath = "//p[text()='Yearly Attendance Summary Report']")
	private WebElement YearlyAttendanceSummaryReport; // 10th TestCase

	@FindBy(xpath = "//p[text()='Bill Ledger Report']")
	private WebElement BillLedgerReport; // 10th TestCase

	@FindBy(xpath = "//input[@id='Main_2']")
	private WebElement PolicyMasterPermission; // 11th TestCase

	@FindBy(xpath = "//a[@id='employeeFiles_tab']")
	private WebElement EmployeeFilesTab; // 11th TestCase

	@FindBy(xpath = "//a[@id='companyFiles_tab']")
	private WebElement CompanyFilesTabs; // 11th TestCase

	@FindBy(xpath = "//a[@id='policyIcon']")
	private WebElement PolicyIcon; // 11th TestCase

	@FindBy(xpath = "//a[text()='Attendance Policy']")
	private WebElement AttendancePolicy; // 11th TestCase

	@FindBy(xpath = "//button[contains(text(),'Leave')]")
	private WebElement LeaveDropDown; // 11th TestCase

	@FindBy(xpath = "//a[contains(text(),'Leave Policy')]")
	private WebElement LeavePolicy; // 11th TestCase

	@FindBy(xpath = "//a[@id='weeklyoffPolicy_tab']")
	private WebElement WeeklyOffPolicy; // 11th TestCase

	@FindBy(xpath = "//a[@id='holidayPolicy_tab']")
	private WebElement HoliDayPolicy; // 11th TestCase

	@FindBy(xpath = "//div[@id='policyMaster']/div/div/div[3]/h2/button")
	private WebElement ShiftDropDown; // 11th TestCase

	@FindBy(xpath = "//a[text()='Shift']")
	private WebElement ShiftPolicyButton; // 11th TestCase

	@FindBy(xpath = "//a[@id='shiftPolicy_tab']")
	private WebElement ShiftPolicyTab; // 11th TestCase

	@FindBy(xpath = "//button[contains(text(),'Approval Flow')]")
	private WebElement ApprovalFlowDropDown; // 11th TestCase

	@FindBy(xpath = "//a[text()='Approval Flow']")
	private WebElement ApprovalFlowButton; // 11th TestCase

	@FindBy(xpath = "//a[@id='dir_org_chart']")
	private WebElement OrgChart; // 12th TestCase

	@FindBy(xpath = "//button[@id='tab_Grade']")
	private WebElement GradeButton; // 12th TestCase

	@FindBy(xpath = "//button[@id='entityType_tab']")
	private WebElement EntityTtypeButton; // 12th TestCase

	@FindBy(xpath = "//button[@id='attendanceStatus_tab']")
	private WebElement AttendanceStatusButton; // 12th TestCase

	@FindBy(xpath = "//button[@id='fileTag_tab']")
	private WebElement FileTagButton; // 12th TestCase

	@FindBy(xpath = "//button[@id='leavetype_tab']")
	private WebElement LeaveTypeButton; // 12th TestCase

	@FindBy(xpath = "//a[@id='settingsIcon']")
	private WebElement SettingIcon; // 12th TestCase

	@FindBy(xpath = "//button[contains(text(),'Employee Master')]")
	private WebElement EmployeeMasterDropDown; // 12th TestCase

	@FindBy(xpath = "//a[text()='Import Bulk Form']")
	private WebElement ImportBulkForm; // 12th TestCase

	@FindBy(xpath = "//a[text()='Field Configuration']")
	private WebElement FieldConfiguration; // 12th TestCase

	@FindBy(xpath = "//button[contains(text(),'Device Setup')]")
	private WebElement DeviceSetup; // 12th TestCase

	@FindBy(xpath = "//a[text()='Device List']")
	private WebElement DeviceList; // 12th TestCase

	@FindBy(xpath = "//a[text()='Device Group']")
	private WebElement DeviceGroup; // 12th TestCase

	@FindBy(xpath = "//a[text()='General Settings']")
	private WebElement GeneralSettingsButton; // 12th TestCase

	@FindBy(xpath = "//a[text()='Personal Settings']")
	private WebElement PersonalSettingsTab; // 12th TestCase

	@FindBy(xpath = "//a[text()='SMTP Settings']")
	private WebElement SMTPSettingsTab; // 12th TestCase

	@FindBy(xpath = "//div[@class='nav-icon navigation-icon home-nicon']")
	private WebElement HomeButton; // 12th TestCase

	@FindBy(xpath = "//p[text()='Active/In-Active']")
	private WebElement EntityActiveInactive; // 12th TestCase

	@FindBy(xpath = "//p[text()='Block/Un-Block']")
	private WebElement EntityBlockUnBlock; // 12th TestCase

	@FindBy(xpath = "//div[@class='nav-icon navigation-icon billing_icon']")
	private WebElement BillingButton; // 12th TestCase

	@FindBy(xpath = "//input[@id='Main_1']")
	private WebElement MasterPermissionCheckBox; // 12th TestCase

	@FindBy(xpath = "//input[@id='Main_133']")
	private WebElement HomePermissionCheckBox; // 12th TestCase

	@FindBy(xpath = "//input[@id='Main_224']")
	private WebElement BillingPermissionCheckBox; // 12th TestCase

	@FindBy(xpath = "//h2[text()='Fields Configuration']")
	private WebElement FieldsConfigurationText; // 12th TestCase

	@FindBy(xpath = "//select[@id='drpDepartment']")
	private WebElement DeptDropdown; // 12th TestCase

	@FindBy(xpath = "//select[@id='drpDesignation']")
	private WebElement DesignationDropdown; // 12th TestCase

	@FindBy(xpath = "//select[@id='drpEmployeeGroup']")
	private WebElement TeamDropDown; // 12th TestCase

	@FindBy(xpath = "//input[@id='txtPIN']")
	private WebElement PinTextField; // 12th TestCase

	@FindBy(xpath = "//select[@id='drpRole']")
	private WebElement RoleAssigned; // 12th TestCase

	@FindBy(xpath = "//button[@id='btnWorkInfoSave']")
	private WebElement SaveChanges; // 12th TestCase

	@FindBy(xpath = "//button[text()='Auto set Policy']")
	private WebElement DefaultPolicy; // 12th TestCase

	@FindBy(xpath = "//a[text()='Send Invite']")
	private WebElement EmailInvite; // 12th TestCase

	@FindBy(xpath = "//label[text()='Master']")
	private WebElement MasterMenu; // 13th TestCase

	@FindBy(xpath = "//label[text()='Policy Master']")
	private WebElement PolicyMasterMenu; // 13th TestCase

	@FindBy(xpath = "//label[text()='Attendance']")
	private WebElement AttendanceMenu; // 13th TestCase

	@FindBy(xpath = "//label[text()='Leave']")
	private WebElement LeaveMenu; // 13th TestCase

	@FindBy(xpath = "//label[text()='Shift']")
	private WebElement ShiftMenu; // 13th TestCase

	@FindBy(xpath = "//label[text()='Reports']")
	private WebElement ReportsMenu; // 13th TestCase

	@FindBy(xpath = "//label[text()='Home']")
	private WebElement HomeMenu; // 13th TestCase

	@FindBy(xpath = "//label[text()='Directory']")
	private WebElement DirectoryMenu; // 13th TestCase

	@FindBy(xpath = "//label[text()='Files']")
	private WebElement FilesMenu; // 13th TestCase

	@FindBy(xpath = "//label[text()='Billing']")
	private WebElement BillingMenu; // 13th TestCase

	@FindBy(xpath = "//input[@id='Delete_1_8']")
	private WebElement MasterEditCheckbox; // 13th TestCase

	@FindBy(xpath = "//input[@id='Delete_2_19']")
	private WebElement PolicyMasterCheckbox; // 13th TestCase

	@FindBy(xpath = "(//input[contains(@id,'Delete')])[1]")
	private WebElement AttendanceCheckbox; // 13th TestCase

	@FindBy(xpath = "(//input[contains(@id, 'Delete')])[6]")
	private WebElement LeaveCheckbox; // 13th TestCase

	@FindBy(xpath = "(//input[contains(@id, 'Delete')])[9]")
	private WebElement ShiftCheckbox; // 13th TestCase

	@FindBy(xpath = "(//input[contains(@id, 'Delete')])[10]")
	private WebElement ReportsCheckbox; // 13th TestCase

	@FindBy(xpath = "//input[@id='Delete_133_134']")
	private WebElement HomeCheckbox; // 13th TestCase

	@FindBy(xpath = "(//input[contains(@id, 'Delete')])[18]")
	private WebElement DirectoryCheckbox; // 13th TestCase

	@FindBy(xpath = "(//input[contains(@id, 'Delete')])[20]")
	private WebElement FilesCheckbox; // 13th TestCase

	@FindBy(xpath = "//input[@id='Delete_224_223']")
	private WebElement BillingCheckbox; // 13th TestCase

	@FindBy(xpath = "//div[text()='User Role']")
	private WebElement RolePermissionPageLoaded; // 13th TestCase

	@FindBy(xpath = "//div[@id='nav-tab']/a")
	private WebElement BillingPageWalletTab; // 13th TestCase

	@FindBy(xpath = "//div[@id='setting']/div/ul/li[1]/a")
	private WebElement GeneralSettings; // 13th TestCase

	@FindBy(xpath = "//select[@id='drpIsWebEnrollment']")
	private WebElement WebEnrollmentSelectOption; // 13th TestCase

	@FindBy(xpath = "//select[@id='drpEnrollmentType']")
	private WebElement WebEnrollmentTypes; // 13th TestCase

	@FindBy(xpath = "//button[@id='btnGeneralSettingSave']")
	private WebElement SaveChangesButton; // 13th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpIsWebEnrollment-container']")
	private WebElement WebEnrollmentClickAction; // 13th TestCase
	
	@FindBy(xpath = "//li[text()='YES']")
	private WebElement YesSelectedForEnrollment; // 13th TestCase

	
	// 1st TestCase
	public boolean RolePermissionButton() {
		try {

			utils.waitForEle(RolePermissionButton, "visible", "", 10);
			RolePermissionButton.isDisplayed();
			RolePermissionButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RoleModulePagination() {
		try {
			Thread.sleep(3000);
			utils.waitForEle(RoleModulePagination, "visible", "", 10);
			Select select = new Select(RoleModulePagination);
			select.selectByVisibleText("50");

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AddRolePermissionButton() {
		try {

			utils.waitForEle(AddRolePermissionButton, "visible", "", 10);
			AddRolePermissionButton.isDisplayed();
			AddRolePermissionButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// Role Comparison
	public List<String> getRoleRecordsss() throws InterruptedException {
		List<String> recordList = new ArrayList<>();
		Thread.sleep(2000);
		driver.get("http://demo.meghpi.com/Directory/Company");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='tab_Role']")).click();

	
		utils.waitForEle(SearchDropDown, "visible", "", 15);
		SearchDropDown.click();
	
		utils.waitForEle(StatusCheckbox, "visible", "", 15);
		StatusCheckbox.click();
	
		utils.waitForEle(SearchDropDown, "visible", "", 15);
		SearchDropDown.click();
		Thread.sleep(2000);
		utils.waitForEle(RoleSearchTextField, "visible", "", 15);
		RoleSearchTextField.sendKeys("Active");
	

		Thread.sleep(2000);
		Select select = new Select(RoleModulePagination);
		select.selectByVisibleText("100");
		Thread.sleep(2000);

		List<WebElement> elements = driver.findElements(By.xpath("//table[@id='dtRole']/tbody/tr/td[1]"));

		for (WebElement el : elements) {
			recordList.add(el.getText().trim());

		}
		return recordList;

	}

//RolePermission
	public List<String> SelectRoleDropDown() throws InterruptedException {
		List<String> recordList2 = new ArrayList<>();
		driver.get("https://demo.meghpi.com/RolePermission/AddRolePermission");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[@id='select2-drpRole-container']")).click();
		Thread.sleep(2000);

		List<WebElement> elements2 = driver.findElements(By.xpath("//select[@id='drpRole']/option[position() > 1]"));
		for (WebElement el2 : elements2) {
			recordList2.add(el2.getText().trim());

		}
		return recordList2;

	}

	// comparison
	public boolean Comparision() {
		try {
			List<String> moduleRoleList = getRoleRecordsss();
			System.out.println("role records output");
			List<String> selectRoleList = SelectRoleDropDown();
			
			System.out.println("permission output");

			// Convert lists to sets for comparison, ignoring case
			Set<String> moduleSet = moduleRoleList.stream().map(String::toLowerCase).collect(Collectors.toSet());

			Set<String> permissionSet = selectRoleList.stream().map(String::toLowerCase).collect(Collectors.toSet());

			System.out.println("Module Role List: " + moduleRoleList);
			System.out.println("Permission Role List: " + selectRoleList);

			if (!moduleSet.equals(permissionSet)) {
				System.out.println("Comparison failed: Role sets do not match");
				// Print differences
				Set<String> onlyInModule = new HashSet<>(moduleSet);
				onlyInModule.removeAll(permissionSet);
				System.out.println("Only in Module list: " + onlyInModule);

				Set<String> onlyInPermission = new HashSet<>(permissionSet);
				onlyInPermission.removeAll(moduleSet);
				System.out.println("Only in Permission list: " + onlyInPermission);
				return false;
			}

			System.out.println("All role records matched successfully.");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 2nd TestCase

	public boolean SettingsCheckBox() {
		try {

			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", SettingsCheckBox);
			Thread.sleep(2000);

			utils.waitForEle(SettingsCheckBox, "visible", "", 10);
			if (!SettingsCheckBox.isSelected()) {
				SettingsCheckBox.click();
			}

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean MasterModuleExpand() {
		try {

			utils.waitForEle(MasterModuleExpand, "visible", "", 10);
			MasterModuleExpand.isDisplayed();
			MasterModuleExpand.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean MasterModuleCheckBox() {
		try {

			utils.waitForEle(MasterModuleCheckBox, "visible", "", 10);
			MasterModuleCheckBox.isDisplayed();
			MasterModuleCheckBox.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RolePermissionSaveButton() {
		try {

			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", RolePermissionSaveButton);
			Thread.sleep(2000);

			utils.waitForEle(RolePermissionSaveButton, "visible", "", 10);
			RolePermissionSaveButton.isDisplayed();
			RolePermissionSaveButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RolePermissionSearchField(String rolename) {
		try {
			utils.waitForEle(RolePermissionSearchField, "enable", "", 20);
			RolePermissionSearchField.sendKeys(rolename);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RolePermissionEditIcon() {
		try {

			utils.waitForEle(RolePermissionEditIcon, "visible", "", 10);
			RolePermissionEditIcon.isDisplayed();
			RolePermissionEditIcon.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean SelectRoleDropdownOption(String rolename) {
		try {
			Thread.sleep(3000);
			utils.waitForEle(SelectRoleDropDown, "enable", "",10);
			Select select = new Select(SelectRoleDropDown);
			select.selectByVisibleText(rolename);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CheckBoxIsSelected() {
		try {

			utils.waitForEle(MasterModuleCheckBox, "visible", "", 10);
			MasterModuleCheckBox.isDisplayed();
			MasterModuleCheckBox.isSelected();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RoleDropDownClick() {
		try {

			utils.waitForEle(RoleDropDownClick, "visible", "", 10);
			RoleDropDownClick.isDisplayed();
			RoleDropDownClick.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RoleDropDownSearch(String rolename) {
		try {

			utils.waitForEle(RoleDropDownSearch,  "visible", "", 10);
			RoleDropDownSearch.isDisplayed();
			RoleDropDownSearch.sendKeys(rolename);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RoleDropDownSearchResult() {
		try {

			utils.waitForEle(RoleDropDownSearchResult, "visible", "", 10);
			RoleDropDownSearchResult.isDisplayed();
			RoleDropDownSearchResult.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 3rdTestCase
	public boolean RolePermissionSearchResult() {
		try {

			utils.waitForEle(RolePermissionSearchResult, "visible", "", 10);
			RolePermissionSearchResult.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 4th TestCase
	public boolean EmployeeAttendancePermission() {
		try {
			utils.waitForEle(EmployeeAttendancePermission, "visible", "", 10);

			if (!EmployeeAttendancePermission.isSelected()) {
				EmployeeAttendancePermission.click();
			}

			return true; // Checkbox is now selected or was already selected
		} catch (Exception e) {
			exceptionDesc = e.getMessage();
			return false;
		}
	}

	public boolean ManageButton() {
		int attempts = 0;

		while (attempts < 2) {
			try {
				Thread.sleep(4000); // Consider replacing this with smarter wait if possible
				utils.waitForEle(ManageButton, "visible", "", 10);
				ManageButton.click();
				return true; // Success on first or retry attempt
			} catch (Exception e) {
				exceptionDesc = e.getMessage();
				attempts++;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ie) {
					// Restore interrupted state
					Thread.currentThread().interrupt();
				}
			}
		}
		return false;
	}

	public boolean LogoutButton() {
		try {
			return Utils.safeClick(driver, LogoutButton);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
	}

	public boolean EmployeeToggleSwitch() {
	    try {
	        Thread.sleep(4000);
	        WebElement toggleSwitch = driver.findElement(By.xpath("//input[@id='ManageRole']"));
	        utils.waitForEle(toggleSwitch, "visible", "", 30);

	        if (toggleSwitch.isDisplayed()) {
	            toggleSwitch.click();
	        } else {
	            throw new Exception("Toggle switch not visible");
	        }
	        
	        Thread.sleep(6000);
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}


	public boolean EmployeeAttendanceButton() {
		try {

			utils.waitForEle(EmployeeAttendanceButton, "visible", "", 10);
			EmployeeAttendanceButton.isDisplayed();
			EmployeeAttendanceButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmpAttendanceTab() {
		try {

			utils.waitForEle(EmpAttendanceTab, "visible", "", 30);
			EmpAttendanceTab.isDisplayed();
			EmpAttendanceTab.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmpRegulationRequest() {
		try {

			utils.waitForEle(EmpRegulationRequest, "visible", "", 10);
			EmpRegulationRequest.isDisplayed();
			EmpRegulationRequest.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmpOverTimeRequest() {
		try {

			utils.waitForEle(EmpOverTimeRequest, "visible", "", 10);
			EmpOverTimeRequest.isDisplayed();
			EmpOverTimeRequest.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmpShiftRequest() {
		try {

			utils.waitForEle(EmpShiftRequest, "visible", "", 10);
			EmpShiftRequest.isDisplayed();
			EmpShiftRequest.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmpWeekOffRequest() {
		try {

			utils.waitForEle(EmpWeekOffRequest, "visible", "", 10);
			EmpWeekOffRequest.isDisplayed();
			EmpWeekOffRequest.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 5th TestCase
	public boolean LeavePermissionCheckBox() {
		try {

			utils.waitForEle(LeavePermissionCheckBox, "visible", "", 10);
			if (!LeavePermissionCheckBox.isSelected()) {
				LeavePermissionCheckBox.click();
			}

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LeaveButton() {
		try {

			utils.waitForEle(LeaveButton, "visible", "", 10);
			LeaveButton.isDisplayed();
			Actions actions = new Actions(driver);
			actions.doubleClick(LeaveButton).perform();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LeaveRequestsTab() {
		try {

			utils.waitForEle(LeaveRequestsTab, "visible", "", 10);
			LeaveRequestsTab.isDisplayed();
			LeaveRequestsTab.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LeaveBalanceTab() {
		try {

			utils.waitForEle(LeaveBalanceTab, "visible", "", 10);
			LeaveBalanceTab.isDisplayed();
			LeaveBalanceTab.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 6th TestCase
	public boolean ReportPermissionCheckBox() {
		try {

			utils.waitForEle(ReportPermissionCheckBox, "visible", "", 10);
			if (!ReportPermissionCheckBox.isSelected()) {
				ReportPermissionCheckBox.click();
			}

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ReprtButton() {
		try {

			utils.waitForEle(ReprtButton, "visible", "", 10);
			ReprtButton.isDisplayed();
			ReprtButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AttendanceReport() {
		try {

			utils.waitForEle(AttendanceReport, "visible", "", 10);
			AttendanceReport.isDisplayed();
			AttendanceReport.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean TimeLogReport() {
		try {

			utils.waitForEle(TimeLogReport, "visible", "", 10);
			TimeLogReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RegulationReport() {
		try {

			utils.waitForEle(RegulationReport, "visible", "", 10);
			RegulationReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OverTimeReport() {
		try {

			utils.waitForEle(OverTimeReport, "visible", "", 10);
			OverTimeReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean TotalLeavesTakenReport() {
		try {

			utils.waitForEle(TotalLeavesTakenReport, "visible", "", 10);
			TotalLeavesTakenReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean WeekOffReport() {
		try {

			utils.waitForEle(WeekOffReport, "visible", "", 10);
			WeekOffReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PunchReport() {
		try {

			utils.waitForEle(PunchReport, "visible", "", 10);
			PunchReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean InOutReport() {
		try {

			utils.waitForEle(InOutReport, "visible", "", 10);
			InOutReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 7th TestCase

	public boolean ShiftPermission() {
		try {

			utils.waitForEle(ShiftPermission, "visible", "", 10);
			if (!ShiftPermission.isSelected()) {
				ShiftPermission.click();
			}
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DirectoryPermission() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", DirectoryPermission);
			Thread.sleep(2000);

			utils.waitForEle(DirectoryPermission, "visible", "", 10);
			if (!DirectoryPermission.isSelected()) {
				DirectoryPermission.click();
			}
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean FilesPermission() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", FilesPermission);
			Thread.sleep(2000);

			utils.waitForEle(FilesPermission, "visible", "", 10);
			if (!FilesPermission.isSelected()) {
				FilesPermission.click();
			}
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ShiftButton() {
	    try {
	    	Thread.sleep(4000);
	        By shiftBtnLocator = By.xpath("//button[@id='sidebarToggle']/../div[1]/div/ul/li[contains(@title,'Shift')]");
	        
	        WebElement shiftBtn = driver.findElement(shiftBtnLocator);
	        utils.waitForEle(shiftBtn, "visible", "", 30);

	        Actions act = new Actions(driver);
	        act.doubleClick(shiftBtn).perform();  // Perform double-click
	        shiftBtn.click();                     // Then perform single click

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}



	public boolean MyShiftTab() {
		try {
Thread.sleep(4000);
utils.waitForEle(MyShiftTab, "visible", "", 30);
		

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean FileButton() {
		try {

			utils.waitForEle(FileButton, "visible", "", 10);
			FileButton.isDisplayed();
			FileButton.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean MyFilesTab() {
		try {

			utils.waitForEle(MyFilesTab, "visible", "", 10);
			MyFilesTab.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CompanyFilesTab() {
		try {
Thread.sleep(4000);
			utils.waitForEle(CompanyFilesTab, "visible", "", 30);
			

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmpDirectoryButton() {
		try {

			utils.waitForEle(EmpDirectoryButton, "visible", "", 10);
			EmpDirectoryButton.isDisplayed();
			EmpDirectoryButton.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean MyEmployeeTab() {
		try {

			utils.waitForEle(MyEmployeeTab, "visible", "", 10);
			MyEmployeeTab.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ORGChartTab() {
		try {

			utils.waitForEle(ORGChartTab, "visible", "", 10);
			ORGChartTab.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 8th TestCase
	public boolean HrAttendancePermission() {
		try {

			utils.waitForEle(HrAttendancePermission, "visible", "", 10);
			if (!HrAttendancePermission.isSelected()) {
				HrAttendancePermission.click();
			}
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HrAccountAttendanceEmpTab() {
		try {

			utils.waitForEle(HrAccountAttendanceEmpTab, "visible", "", 10);
			HrAccountAttendanceEmpTab.isDisplayed();
			HrAccountAttendanceEmpTab.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HrAccountRegulationRequestTab() {
		try {

			utils.waitForEle(HrAccountRegulationRequestTab, "visible", "", 10);
			HrAccountRegulationRequestTab.isDisplayed();
			HrAccountRegulationRequestTab.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HrAccountOTRequestTab() {
		try {

			utils.waitForEle(HrAccountOTRequestTab, "visible", "", 10);
			HrAccountOTRequestTab.isDisplayed();
			HrAccountOTRequestTab.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HrAccountShiftRequestTab() {
		try {

			utils.waitForEle(HrAccountShiftRequestTab, "visible", "", 10);
			HrAccountShiftRequestTab.isDisplayed();
			HrAccountShiftRequestTab.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HrAccountWeekOffRequestTab() {
		try {

			utils.waitForEle(HrAccountWeekOffRequestTab, "visible", "", 10);
			HrAccountWeekOffRequestTab.isDisplayed();
			HrAccountWeekOffRequestTab.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HrAccountAttendanceReprocessTab() {
		try {

			utils.waitForEle(HrAccountAttendanceReprocessTab, "visible", "", 10);
			HrAccountAttendanceReprocessTab.isDisplayed();
			HrAccountAttendanceReprocessTab.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 9th TestCase

	public boolean HrAccountLeaveWeeklyLeaveTab() {
		try {

			utils.waitForEle(HrAccountLeaveWeeklyLeaveTab, "visible", "", 10);
			HrAccountLeaveWeeklyLeaveTab.isDisplayed();
			HrAccountLeaveWeeklyLeaveTab.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HrAccountShiftTab() {
		try {

			utils.waitForEle(HrAccountShiftTab, "visible", "", 10);
			HrAccountShiftTab.isDisplayed();
			HrAccountShiftTab.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HrAccountShiftRosterTab() {
		try {

			utils.waitForEle(HrAccountShiftRosterTab, "visible", "", 10);
			HrAccountShiftRosterTab.isDisplayed();
			HrAccountShiftRosterTab.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 10th TestCase

	public boolean DailyAttendanceReport() {
		try {

			utils.waitForEle(DailyAttendanceReport, "visible", "", 10);
			DailyAttendanceReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DailyBillTransactionReport() {
		try {

			utils.waitForEle(DailyBillTransactionReport, "visible", "", 10);
			DailyBillTransactionReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PunchTypeAnalyticsReport() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", PunchTypeAnalyticsReport);
			Thread.sleep(2000);

			utils.waitForEle(PunchTypeAnalyticsReport, "visible", "", 10);
			PunchTypeAnalyticsReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmployeeActiveInActiveReport() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", EmployeeActiveInActiveReport);
			Thread.sleep(2000);

			utils.waitForEle(EmployeeActiveInActiveReport, "visible", "", 10);
			EmployeeActiveInActiveReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmployeeBlockUnblockReport() {
		try {

			utils.waitForEle(EmployeeBlockUnblockReport, "visible", "", 10);
			EmployeeBlockUnblockReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmployeeDataReport() {
		try {

			utils.waitForEle(EmployeeDataReport, "visible", "", 10);
			EmployeeDataReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean FormNo23Report() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", FormNo23Report);
			Thread.sleep(2000);

			utils.waitForEle(FormNo23Report, "visible", "", 10);
			FormNo23Report.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean FormNo25Report() {
		try {

			utils.waitForEle(FormNo25Report, "visible", "", 10);
			FormNo25Report.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LeaveBalanceSummary() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", LeaveBalanceSummary);
			Thread.sleep(2000);
			utils.waitForEle(LeaveBalanceSummary, "visible", "", 10);
			LeaveBalanceSummary.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LateArrivalReport() {
		try {

			utils.waitForEle(LateArrivalReport, "visible", "", 10);
			LateArrivalReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean MonthlyBillTransactionReport() {
		try {

			utils.waitForEle(MonthlyBillTransactionReport, "visible", "", 10);
			MonthlyBillTransactionReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean MonthlyShiftWiseAttendanceReport() {
		try {

			utils.waitForEle(MonthlyShiftWiseAttendanceReport, "visible", "", 10);
			MonthlyShiftWiseAttendanceReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean MonthlyAttendanceRegisterReport() {
		try {

			utils.waitForEle(MonthlyAttendanceRegisterReport, "visible", "", 10);
			MonthlyAttendanceRegisterReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean MusterRollReport() {
		try {

			utils.waitForEle(MusterRollReport, "visible", "", 10);
			MusterRollReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OnboardingAndOffboarding() {
		try {

			utils.waitForEle(OnboardingAndOffboarding, "visible", "", 10);
			OnboardingAndOffboarding.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OverTimeReports() {
		try {

			utils.waitForEle(OverTimeReports, "visible", "", 10);
			OverTimeReports.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PunchStatisticsReportTab() {
		try {

			utils.waitForEle(PunchStatisticsReportTab, "visible", "", 10);
			PunchStatisticsReportTab.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ScheduleReports() {
		try {

			utils.waitForEle(ScheduleReports, "visible", "", 10);
			ScheduleReports.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean YearlyAttendanceSummaryReport() {
		try {

			utils.waitForEle(YearlyAttendanceSummaryReport, "visible", "", 10);
			YearlyAttendanceSummaryReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean BillLedgerReport() {
		try {

			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", BillLedgerReport);
			Thread.sleep(2000);

			utils.waitForEle(BillLedgerReport, "visible", "", 10);
			BillLedgerReport.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EarlyLateInOut() {
		try {

			utils.waitForEle(TimeLogReport, "visible", "", 10);
			TimeLogReport.isDisplayed();
			TimeLogReport.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 11th TestCase
	public boolean PolicyMasterPermission() {
		try {

			utils.waitForEle(PolicyMasterPermission, "visible", "", 10);
			if (!PolicyMasterPermission.isSelected()) {
				PolicyMasterPermission.click();
			}
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmployeeFilesTab() {
		try {

			utils.waitForEle(EmployeeFilesTab, "visible", "", 10);
			EmployeeFilesTab.isDisplayed();
			EmployeeFilesTab.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CompanyFilesTabs() {
		try {

			utils.waitForEle(CompanyFilesTabs, "visible", "", 10);
			CompanyFilesTabs.isDisplayed();
			CompanyFilesTabs.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PolicyIcon() {
		try {

			Thread.sleep(4000);
			utils.waitForEle(PolicyIcon, "visible", "", 10);
			PolicyIcon.isDisplayed();
			PolicyIcon.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AttendancePolicy() {
		try {

			utils.waitForEle(AttendancePolicy, "visible", "", 10);
			AttendancePolicy.isDisplayed();
			AttendancePolicy.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LeaveDropDown() {
		try {

			utils.waitForEle(LeaveDropDown, "visible", "", 10);
			LeaveDropDown.isDisplayed();
			LeaveDropDown.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LeavePolicy() {
		try {

			utils.waitForEle(LeavePolicy, "visible", "", 10);
			LeavePolicy.isDisplayed();
			LeavePolicy.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean WeeklyOffPolicy() {
		try {

			utils.waitForEle(WeeklyOffPolicy, "visible", "", 10);
			WeeklyOffPolicy.isDisplayed();
			WeeklyOffPolicy.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HoliDayPolicy() {
		try {

			utils.waitForEle(HoliDayPolicy, "visible", "", 10);
			HoliDayPolicy.isDisplayed();
			HoliDayPolicy.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ShiftDropDown() {
		try {

			utils.waitForEle(ShiftDropDown, "visible", "", 10);
			ShiftDropDown.isDisplayed();
			ShiftDropDown.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ShiftPolicyButton() {
		try {

			utils.waitForEle(ShiftPolicyButton, "visible", "", 10);
			ShiftPolicyButton.isDisplayed();
			ShiftPolicyButton.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ShiftPolicyTab() {
		try {

			utils.waitForEle(ShiftPolicyTab, "visible", "", 10);
			ShiftPolicyTab.isDisplayed();
			ShiftPolicyTab.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ApprovalFlowDropDown() {
		try {

			utils.waitForEle(ApprovalFlowDropDown, "visible", "", 10);
			ApprovalFlowDropDown.isDisplayed();
			ApprovalFlowDropDown.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ApprovalFlowButton() {
		try {

			utils.waitForEle(ApprovalFlowButton, "visible", "", 10);
			ApprovalFlowButton.isDisplayed();
			ApprovalFlowButton.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 12th TestCase
	public boolean OrgChart() {
		try {

			utils.waitForEle(OrgChart, "visible", "", 10);
			OrgChart.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean GradeButton() {
		try {

			utils.waitForEle(GradeButton, "visible", "", 10);
			GradeButton.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EntityTtypeButton() {
		try {

			utils.waitForEle(EntityTtypeButton, "visible", "", 10);
			EntityTtypeButton.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AttendanceStatusButton() {
		try {

			utils.waitForEle(AttendanceStatusButton, "visible", "", 10);
			AttendanceStatusButton.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean FileTagButton() {
		try {

			utils.waitForEle(FileTagButton, "visible", "", 10);
			FileTagButton.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LeaveTypeButton() {
		try {

			utils.waitForEle(LeaveTypeButton, "visible", "", 10);
			LeaveTypeButton.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean SettingIcon() {
		try {

			utils.waitForEle(SettingIcon, "visible", "", 10);
			SettingIcon.isDisplayed();
			SettingIcon.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmployeeMasterDropDown() {
		try {

			utils.waitForEle(EmployeeMasterDropDown, "visible", "", 10);
			EmployeeMasterDropDown.isDisplayed();
			EmployeeMasterDropDown.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ImportBulkForm() {
		try {

			utils.waitForEle(ImportBulkForm, "visible", "", 10);
			ImportBulkForm.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean FieldConfiguration() {
		try {

			utils.waitForEle(FieldConfiguration, "visible", "", 10);
			FieldConfiguration.isDisplayed();
			FieldConfiguration.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean FieldsConfigurationText() {
		try {

			utils.waitForEle(FieldsConfigurationText, "visible", "", 10);
			FieldsConfigurationText.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DeviceSetup() {
		try {

			utils.waitForEle(DeviceSetup, "visible", "", 10);
			DeviceSetup.isDisplayed();
			DeviceSetup.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DeviceList() {
		try {

			utils.waitForEle(DeviceList, "visible", "", 10);
			DeviceList.isDisplayed();
			DeviceList.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DeviceGroup() {
		try {

			utils.waitForEle(DeviceGroup, "visible", "", 10);
			DeviceGroup.isDisplayed();
			DeviceGroup.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean GeneralSettingsButton() {
		try {

			utils.waitForEle(GeneralSettingsButton, "visible", "", 10);
			GeneralSettingsButton.isDisplayed();
			GeneralSettingsButton.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PersonalSettingsTab() {
		try {

			utils.waitForEle(PersonalSettingsTab, "visible", "", 10);
			PersonalSettingsTab.isDisplayed();
			PersonalSettingsTab.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HomeButton() {
		try {

			utils.waitForEle(HomeButton, "visible", "", 10);
			HomeButton.isDisplayed();
			HomeButton.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EntityActiveInactive() {
		try {

			utils.waitForEle(EntityActiveInactive, "visible", "", 10);
			EntityActiveInactive.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EntityBlockUnBlock() {
		try {

			utils.waitForEle(EntityBlockUnBlock, "visible", "", 10);
			EntityBlockUnBlock.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean BillingButton() {
		try {

			utils.waitForEle(BillingButton, "visible", "", 10);
			BillingButton.isDisplayed();
			BillingButton.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean MasterPermissionCheckBox() {
		try {

			utils.waitForEle(MasterPermissionCheckBox, "visible", "", 10);
			if (!MasterPermissionCheckBox.isSelected()) {
				MasterPermissionCheckBox.click();
			}
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HomePermissionCheckBox() {
		try {

			utils.waitForEle(HomePermissionCheckBox, "visible", "", 10);
			if (!HomePermissionCheckBox.isSelected()) {
				HomePermissionCheckBox.click();
			}
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean BillingPermissionCheckBox() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", BillingPermissionCheckBox);
			Thread.sleep(2000);

			utils.waitForEle(BillingPermissionCheckBox, "visible", "", 10);
			if (!BillingPermissionCheckBox.isSelected()) {
				BillingPermissionCheckBox.click();
			}
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DeptDropdown(String deptname) {
		try {
			Thread.sleep(3000);
			utils.waitForEle(DeptDropdown, "visible", "", 10);
			DeptDropdown.isDisplayed();
			Select select = new Select(DeptDropdown);
			select.selectByVisibleText(deptname);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DesignationDropdown(String Designationname) {
		try {
			Thread.sleep(3000);
			utils.waitForEle(DesignationDropdown, "visible", "", 10);
			DesignationDropdown.isDisplayed();
			Select select = new Select(DesignationDropdown);
			select.selectByVisibleText(Designationname);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean TeamDropDown(String teamname) {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", TeamDropDown);
			Thread.sleep(2000);

			utils.waitForEle(TeamDropDown, "visible", "", 10);
			TeamDropDown.isDisplayed();
			Select select = new Select(TeamDropDown);
			select.selectByVisibleText(teamname);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RoleAssigned(String rolename) {
		try {
			Thread.sleep(3000);
			utils.waitForEle(RoleAssigned, "visible", "", 10);
			RoleAssigned.isDisplayed();
			Select select = new Select(RoleAssigned);
			select.selectByVisibleText(rolename);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PinTextField(String pin) {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", PinTextField);
			Thread.sleep(2000);

			utils.waitForEle(PinTextField,  "visible", "", 10);
			PinTextField.isDisplayed();
			PinTextField.sendKeys(pin);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean SaveChanges() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", SaveChanges);
			Thread.sleep(2000);

			utils.waitForEle(SaveChanges, "visible", "", 10);
			SaveChanges.isDisplayed();
			SaveChanges.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DefaultPolicy() {
		try {

			utils.waitForEle(DefaultPolicy, "visible", "", 10);
			DefaultPolicy.isDisplayed();
			DefaultPolicy.click();
			Thread.sleep(6000);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmailInvite() {
		try {

			utils.waitForEle(EmailInvite, "visible", "", 10);
			EmailInvite.isDisplayed();
			EmailInvite.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 13th testCase

	public boolean MasterMenu() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", MasterMenu);
			Thread.sleep(2000);

			utils.waitForEle(MasterMenu, "visible", "", 10);
			MasterMenu.isDisplayed();
			MasterMenu.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PolicyMasterMenu() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", PolicyMasterMenu);
			Thread.sleep(2000);

			utils.waitForEle(PolicyMasterMenu, "visible", "", 10);
			PolicyMasterMenu.isDisplayed();
			PolicyMasterMenu.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AttendanceMenu() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", AttendanceMenu);
			Thread.sleep(2000);

			utils.waitForEle(AttendanceMenu, "visible", "", 10);
			AttendanceMenu.isDisplayed();
			AttendanceMenu.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LeaveMenu() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", LeaveMenu);
			Thread.sleep(2000);

			utils.waitForEle(LeaveMenu, "visible", "", 10);
			LeaveMenu.isDisplayed();
			LeaveMenu.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ShiftMenu() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", ShiftMenu);
			Thread.sleep(2000);

			utils.waitForEle(ShiftMenu, "visible", "", 10);
			ShiftMenu.isDisplayed();
			ShiftMenu.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ReportsMenu() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", ReportsMenu);
			Thread.sleep(2000);

			utils.waitForEle(ReportsMenu, "visible", "", 10);
			ReportsMenu.isDisplayed();
			ReportsMenu.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HomeMenu() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", HomeMenu);
			Thread.sleep(2000);

			utils.waitForEle(HomeMenu, "visible", "", 10);
			HomeMenu.isDisplayed();
			HomeMenu.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DirectoryMenu() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", DirectoryMenu);
			Thread.sleep(2000);

			utils.waitForEle(DirectoryMenu, "visible", "", 10);
			DirectoryMenu.isDisplayed();
			DirectoryMenu.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean FilesMenu() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", FilesMenu);
			Thread.sleep(2000);

			utils.waitForEle(FilesMenu, "visible", "", 10);
			FilesMenu.isDisplayed();
			FilesMenu.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean BillingMenu() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();", BillingMenu);
			Thread.sleep(2000);

			utils.waitForEle(BillingMenu, "visible", "", 10);
			BillingMenu.isDisplayed();
			BillingMenu.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean MasterEditCheckbox() {
		try {
			utils.waitForEle(MasterEditCheckbox, "visible", "", 10);
			MasterEditCheckbox.isDisplayed();
			MasterEditCheckbox.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PolicyMasterCheckbox() {
		try {
			utils.waitForEle(PolicyMasterCheckbox, "visible", "", 10);
			PolicyMasterCheckbox.isDisplayed();
			PolicyMasterCheckbox.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AttendanceCheckbox() {
		try {
			utils.waitForEle(AttendanceCheckbox, "visible", "", 10);
			AttendanceCheckbox.isDisplayed();
			AttendanceCheckbox.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LeaveCheckbox() {
		try {
			utils.waitForEle(LeaveCheckbox, "visible", "", 10);
			LeaveCheckbox.isDisplayed();
			LeaveCheckbox.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ShiftCheckbox() {
		try {
			utils.waitForEle(ShiftCheckbox, "visible", "", 10);
			ShiftCheckbox.isDisplayed();
			ShiftCheckbox.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ReportsCheckbox() {
		try {
			utils.waitForEle(ReportsCheckbox, "visible", "", 10);
			ReportsCheckbox.isDisplayed();
			ReportsCheckbox.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean HomeCheckbox() {
		try {
			utils.waitForEle(HomeCheckbox, "visible", "", 10);
			HomeCheckbox.isDisplayed();
			HomeCheckbox.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DirectoryCheckbox() {
		try {
			utils.waitForEle(DirectoryCheckbox, "visible", "", 10);
			DirectoryCheckbox.isDisplayed();
			DirectoryCheckbox.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean FilesCheckbox() {
		try {
			utils.waitForEle(FilesCheckbox, "visible", "", 10);
			FilesCheckbox.isDisplayed();
			FilesCheckbox.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean BillingCheckbox() {
		try {
			utils.waitForEle(BillingCheckbox, "visible", "", 10);
			BillingCheckbox.isDisplayed();
			BillingCheckbox.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean SMTPSettingsTab() {
		try {
			utils.waitForEle(SMTPSettingsTab, "visible", "", 10);
			SMTPSettingsTab.isDisplayed();
			SMTPSettingsTab.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RolePermissionPageLoaded() {
		try {
			utils.waitForEle(RolePermissionPageLoaded, "visible", "", 20);
			RolePermissionPageLoaded.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean BillingPageWalletTab() {
		try {
			utils.waitForEle(BillingPageWalletTab, "visible", "", 10);
			BillingPageWalletTab.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// WebEnrollmentOptionSelection

	public boolean GeneralSettings() {
		try {
			utils.waitForEle(GeneralSettings, "visible", "", 10);
			GeneralSettings.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean WebEnrollmentSelectOption() {
		try {
			Thread.sleep(3000);
			utils.waitForEle(WebEnrollmentSelectOption, "visible", "", 10);
			Select select = new Select(WebEnrollmentSelectOption);
			select.selectByVisibleText("YES");

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean WebEnrollmentTypesFace() {
		try {
			Thread.sleep(3000);
			utils.waitForEle(WebEnrollmentTypes, "visible", "", 10);
			Select select = new Select(WebEnrollmentTypes);
			select.selectByVisibleText("Face");

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean WebEnrollmentTypesCard() {
		try {
			Thread.sleep(3000);
			utils.waitForEle(WebEnrollmentTypes, "visible", "", 10);
			Select select = new Select(WebEnrollmentTypes);
			select.selectByVisibleText("Card");

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean SaveChangesButton() {
		try {
			utils.waitForEle(SaveChangesButton, "visible", "", 10);
			SaveChangesButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean WebEnrollmentClickAction() {
		try {
			utils.waitForEle(WebEnrollmentClickAction, "visible", "", 10);
			WebEnrollmentClickAction.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean YesSelectedForEnrollment() {
		try {
			utils.waitForEle(YesSelectedForEnrollment, "visible", "", 10);
			YesSelectedForEnrollment.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public void setExceptionDesc(String exceptionDesc) {
		exceptionDesc = exceptionDesc;
	}
}
