package com.MeghPI.Attendance.tests;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.pages.MeghAttendancePolicyPage;
import com.MeghPI.Attendance.pages.MeghHolidayPolicyPage;
import com.MeghPI.Attendance.pages.MeghLeavePolicyPage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;

import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghWeekOffPolicyPage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;


public class MeghHolidayPolicyTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	public String holidaypolicyname = "";
	public String Assignedholidaypolicyname = "";
	public String assignedempfirstname = "";
	public String emplastname = "";
	public String empid = "";
	

	private String cmpcode = "";
	private String Emailid = "";
	private String entityname = "";
	private String officename = "";
	private String departmentname = "";
	private String teamname = "";
	private String designationname= "";
 
	
	
	
	@Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
	//	driver = loadDriver.getDriver(device, hubnodeip);

		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

	@Parameters({ "device", "hubnodeip" })
	@BeforeClass(alwaysRun = true)
	void runOnce(int device, @Optional String hubnodeip) {
		logResults.createReport(device);
		logResults.setTestMethodErrorCount(0);
		driver = loadDriver.getDriver(device, hubnodeip);
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		entityname = "AutoEN" + initBase.executionRunTime;
	}
	
	// MPI_586_Holiday_Policy_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_586_Holiday_Policy_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a holiday policy by selecting 2024-2025 as duration yearÂ and ensure that the created policy is displayed in the employee profile.");

		ArrayList<String> data = initBase.loadExcelData("Holiday_Policy", currTC,
				"holidaypolicyname,year");

		holidaypolicyname = data.get(0) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		String year = data.get(1);

	 MeghHolidayPolicyPage HolidayPolicypage = new MeghHolidayPolicyPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		 MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		 MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		 MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		 MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);

		 MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
	
			 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			if (meghloginpage.MainLandingPage()) {
				logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			}
			

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded." + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidaysButtonFromSidebar()) {

			logResults.createLogs("Y", "PASS", "HolidaysButtonFromSidebar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On HolidaysButtonFromSidebar Button."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.AddHolidayPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddHolidayPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddHolidayPolicyButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyNameTextField(holidaypolicyname)) {

			logResults.createLogs("Y", "PASS",
					"HolidayPolicyName Inputted In TextField Successfully." + holidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  HolidayPolicyName In TextField." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FinancialYear(year)) {

			logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting The Year." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.NextStepButton()) {

			logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.NextStepButton()) {

			logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.CreatePolicySaveButton()) {

			logResults.createLogs("Y", "PASS", "CreatePolicySaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicySaveButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton ."
					+ DepartmentPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.PolicyButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record Policy Button.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record Policy Button ."
					+ AttendancePolicyPage.getExceptionDesc());
		}


		if (HolidayPolicypage.HolidayPolicyNameInEmp(holidaypolicyname)) {
			String HolidayPolicyNameAssigned = HolidayPolicypage.HolidayPolicyName;

			logResults.createLogs("Y", "PASS", "Created Holiday Policy Is Assigned To All Emp Successfully."
					+ HolidayPolicyNameAssigned + "=" + holidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created Holiday PolicyName ." + HolidayPolicypage.getExceptionDesc());
		}

	}

	// MPI_587_Holiday_Policy_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_587_Holiday_Policy_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("To verify this, check the functionality of search featureÂ ");

	//	ArrayList<String> data = initBase.loadExcelData("Holiday_Policy", currTC, "password,captcha");


		MeghHolidayPolicyPage HolidayPolicypage = new MeghHolidayPolicyPage(driver);
		
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


if (meghloginpage.MainLandingPage()) {
	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
}
		

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded." + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidaysButtonFromSidebar()) {

			logResults.createLogs("Y", "PASS", "HolidaysButtonFromSidebar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On HolidaysButtonFromSidebar Button."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicySearchTextField(holidaypolicyname)) {

			logResults.createLogs("Y", "PASS",
					"HolidayPolicy Name Inutted In SearchTextField Successfully." + holidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Holidays HolidayPolicy Name In TextField."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyFirstRecord(holidaypolicyname)) {

			logResults.createLogs("Y", "PASS", "HolidayPolicy Displayed Successfully." + holidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created HolidayPolicy Name ." + HolidayPolicypage.getExceptionDesc());
		}

	}

	// MPI_590_Holiday_Policy_05
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_590_Holiday_Policy_05()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the holiday policy and add another holiday and ensure added holiday is added to the policyÂ ");

		ArrayList<String> data = initBase.loadExcelData("Holiday_Policy", currTC,
				"holidaypolicyname,year");

		String UpdatedHolidayPolicyname = data.get(0) + Pramod.generateRandomAlpha(4);
		String year = data.get(1);

		MeghHolidayPolicyPage HolidayPolicypage = new MeghHolidayPolicyPage(driver);
		
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

MeghLoginPage meghloginpage = new MeghLoginPage(driver);


if (meghloginpage.MainLandingPage()) {
	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
}
	
		
		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded." + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidaysButtonFromSidebar()) {

			logResults.createLogs("Y", "PASS", "HolidaysButtonFromSidebar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On HolidaysButtonFromSidebar Button."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicySearchTextField(holidaypolicyname)) {

			logResults.createLogs("Y", "PASS",
					"HolidayPolicy Name Inutted In SearchTextField Successfully." + holidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Holidays HolidayPolicy Name In TextField."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyFirstRecord(holidaypolicyname)) {

			logResults.createLogs("Y", "PASS", "HolidayPolicy Displayed Successfully." + holidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created HolidayPolicy Name ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyEditIcon()) {

			logResults.createLogs("Y", "PASS", "HolidayPolicyEditIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Created HolidayPolicy Edit Icon ."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyNameTextField(UpdatedHolidayPolicyname)) {

			logResults.createLogs("Y", "PASS",
					"HolidayPolicyName Inputted In TextField Successfully." + UpdatedHolidayPolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  HolidayPolicyName In TextField." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FinancialYear(year)) {

			logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting The Year." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.NextStepButton()) {

			logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.NextStepButton()) {

			logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.CreatePolicySaveButton()) {

			logResults.createLogs("Y", "PASS", "CreatePolicySaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicySaveButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicySearchTextField(UpdatedHolidayPolicyname)) {

			logResults.createLogs("Y", "PASS",
					"HolidayPolicy Name Inutted In SearchTextField Successfully." + UpdatedHolidayPolicyname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Holidays HolidayPolicy Name In TextField."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyFirstRecord(UpdatedHolidayPolicyname)) {

			logResults.createLogs("Y", "PASS", "HolidayPolicy Displayed Successfully." + UpdatedHolidayPolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created HolidayPolicy Name ." + HolidayPolicypage.getExceptionDesc());
		}

	}

	// MPI_591_Holiday_Policy_06
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_591_Holiday_Policy_06()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, add a new holiday and ensure added holiday is displaying in the list during policy creation");

		ArrayList<String> data = initBase.loadExcelData("Holiday_Policy", currTC,
				"holidaypolicyname,year,holidayname,empjoiningdate");

		String holidaypolicynames = data.get(0) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		String year = data.get(1);
		String holidayname = data.get(2) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		String date = data.get(3);

		MeghHolidayPolicyPage HolidayPolicypage = new MeghHolidayPolicyPage(driver);
		
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


if (meghloginpage.MainLandingPage()) {
	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
}
		

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded." + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidaysButtonFromSidebar()) {

			logResults.createLogs("Y", "PASS", "HolidaysButtonFromSidebar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On HolidaysButtonFromSidebar Button."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.AddHolidayPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddHolidayPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddHolidayPolicyButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyNameTextField(holidaypolicynames)) {

			logResults.createLogs("Y", "PASS",
					"HolidayPolicyName Inputted In TextField Successfully." + holidaypolicynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  HolidayPolicyName In TextField." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FinancialYear(year)) {

			logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting The Year." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.NextStepButton()) {

			logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.CreateCustomHoliday()) {

			logResults.createLogs("Y", "PASS", "CreateCustomHoliday Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateCustomHoliday." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayNameTextField(holidayname)) {

			logResults.createLogs("Y", "PASS", "HolidayName Inputted In TextField  Successfully." + holidayname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Holiday Name." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayNameTextField(holidayname)) {

			logResults.createLogs("Y", "PASS", "HolidayName Inputted In TextField  Successfully." + holidayname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Holiday Name." + HolidayPolicypage.getExceptionDesc());
		}
		
		if (HolidayPolicypage.DateTextField()) {

			logResults.createLogs("Y", "PASS", "Date TextField Clicked  Successfully." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Date TextField ." + HolidayPolicypage.getExceptionDesc());
		}
		
		

		if (HolidayPolicypage.DateInputted(date)) {

			logResults.createLogs("Y", "PASS", "Date Inputted In TextField  Successfully." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting DateOfHoliday ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.CountryDropDown()) {

			logResults.createLogs("Y", "PASS", "Country Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Country ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.StateDropDown()) {

			logResults.createLogs("Y", "PASS", "State Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting State ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.AddHolidayButton()) {

			logResults.createLogs("Y", "PASS", "Holiday Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On HolidaySave Button ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayList(holidayname)) {

			logResults.createLogs("Y", "PASS", "Created Holiday Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On HolidaySave Button ." + HolidayPolicypage.getExceptionDesc());
		}

	}

	// MPI_592_Holiday_Policy_07
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_592_Holiday_Policy_07()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of search feature in add holiday policy and input holiday name and ensure inputted holiday name is displaying or not");

		ArrayList<String> data = initBase.loadExcelData("Holiday_Policy", currTC,
				"year,holidayname");

		String year = data.get(0);
		String holidayname = data.get(1);
		
	

		MeghHolidayPolicyPage HolidayPolicypage = new MeghHolidayPolicyPage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		
 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


if (meghloginpage.MainLandingPage()) {
	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
}
	

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded." + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidaysButtonFromSidebar()) {

			logResults.createLogs("Y", "PASS", "HolidaysButtonFromSidebar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On HolidaysButtonFromSidebar Button."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.AddHolidayPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddHolidayPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddHolidayPolicyButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyNameTextField(holidayname)) {

			logResults.createLogs("Y", "PASS", "HolidayPolicyName Inputted In TextField Successfully." + holidayname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  HolidayPolicyName In TextField." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FinancialYear(year)) {

			logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting The Year." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidaySearchTextField(holidayname)) {

			logResults.createLogs("Y", "PASS", "Holiday Name Inputted Successfully." + holidayname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Holiday Name." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayResult(holidayname)) {
			String holiday = HolidayPolicypage.actualholiday;
			logResults.createLogs("Y", "PASS", "Holiday Name Displayed Successfully." + holidayname + "=" + holiday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Holiday Name." + HolidayPolicypage.getExceptionDesc());
		}

	}

	// MPI_593_Holiday_Policy_08
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_593_Holiday_Policy_08()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create an policy and make it to default and then create new employee and ensure new employee is added under default holiday policy");

		ArrayList<String> data = initBase.loadExcelData("Holiday_Policy", currTC,
				"holidaypolicyname,year,reportingto,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,mailinator,Default");

		int i = 0;

		String holidaypolicyname = data.get(i++) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		String year = data.get(i++);

		String reportingto = data.get(i++);
		String empid = data.get(i++) + Pramod.generateRandomAlpha(5);
	
		String empfirstname = data.get(i++) + Pramod.generateRandomAlpha(4);
		String emplastname = data.get(i++) + Pramod.generateRandomAlpha(6);
		String empemail = data.get(i++) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		String empphoneno = data.get(i++) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(i++);
		String mailinator = data.get(i++);
		String DefaultText = data.get(i++);

		String combinemail = empemail + mailinator;

		MeghHolidayPolicyPage HolidayPolicypage = new MeghHolidayPolicyPage(driver);
		MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


if (meghloginpage.MainLandingPage()) {
	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
}
		
		
		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded." + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidaysButtonFromSidebar()) {

			logResults.createLogs("Y", "PASS", "HolidaysButtonFromSidebar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On HolidaysButtonFromSidebar Button."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.AddHolidayPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddHolidayPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddHolidayPolicyButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyNameTextField(holidaypolicyname)) {

			logResults.createLogs("Y", "PASS",
					"HolidayPolicyName Inputted In TextField Successfully." + holidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  HolidayPolicyName In TextField." + HolidayPolicypage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.DefaultCheckBox()) {

			logResults.createLogs("Y", "PASS", "DefaultCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DefaultCheckBox ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.FinancialYear(year)) {

			logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting The Year." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.NextStepButton()) {

			logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.NextStepButton()) {

			logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.CreatePolicySaveButton()) {

			logResults.createLogs("Y", "PASS", "CreatePolicySaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicySaveButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		

		if (EmployeePage.TotalCountDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On TotalCountDropDown Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TotalCountDropDown." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddNewButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddNewButton Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddNewButton." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeFormDisplayValidated()) {
			logResults.createLogs("Y", "PASS", "Employee Form Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Loading Employee Form." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTypeDropdown(entityname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On EmployeeTypeDropdown Successfully And Selected EntityName Successfully."
							+ entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTypeDropdown  ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.ReportingToDropdown()) {
			logResults.createLogs("Y", "PASS", "Clicked On ReportingToDropdown Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ReportingToDropdown  ." + EmployeePage.getExceptionDesc());
		}
	
		if (EmployeePage.ReportingToSearchInput(reportingto)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On ReportingTo Search Text Field And Input Is Passed." + reportingto);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ReportingTo Search Text Field ." + EmployeePage.getExceptionDesc());
		}
	

		if (EmployeePage.ReportingToSearchResult()) {
			logResults.createLogs("Y", "PASS", "Entity Name Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Entity Name   ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeId(empid)) {
			logResults.createLogs("Y", "PASS", "EmployeeID Entered On Employee ID Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  EmployeeID." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationDropdown(officename)) {
			logResults.createLogs("Y", "PASS", " CompanyLocation Selected From The DropDown ." + officename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Office Name." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeFirstName(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Employee First Name Entered On Employee FirstName Text Field." + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee First Name." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeLastName(emplastname)) {
			logResults.createLogs("Y", "PASS",
					"Employee Last Name Entered On Employee Last Name Text Field." + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Last Name." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeEmailID(combinemail)) {
			logResults.createLogs("Y", "PASS", "Employee Email Entered On Email ID Text Field." + combinemail);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Email." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeePhoneNo(empphoneno)) {
			logResults.createLogs("Y", "PASS",
					"Employee Phone Number Entered On Phone Number Text Field." + empphoneno);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Phone Number." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeDateTextField()) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date  Text Field Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Joining Date." + EmployeePage.getExceptionDesc());
		}
		
		if (EmployeePage.FirstDateSelection(empjoiningdate)) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date Entered On Joining Date Text Field." + empjoiningdate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Joining Date." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEmployeeSave()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddEmployeeSave Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEmployeeSave." + EmployeePage.getExceptionDesc());
		}
	

		if (EmployeePage.GoToDirectory()) {
			logResults.createLogs("Y", "PASS", "Clicked On GoToDirectory Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GoToDirectory." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.LasTNameSelected()) {
			logResults.createLogs("Y", "PASS", "Selected  LasTName  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting LasTName." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(emplastname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully." + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field." + EmployeePage.getExceptionDesc());
		}
	

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully." + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee." + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton ."
					+ DepartmentPage.getExceptionDesc());
		}
		

		if (AttendancePolicyPage.PolicyButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record Policy Button.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record Policy Button ."
					+ AttendancePolicyPage.getExceptionDesc());
		}

		

		if (HolidayPolicypage.DefaultHolidayPolicy(DefaultText)) {
			String HolidayPolicyNameAssigned = HolidayPolicypage.Defaultholiday;

			logResults.createLogs("Y", "PASS", "Created Holiday Policy Is Assigned To All Emp Successfully."
					+ HolidayPolicyNameAssigned + "=" + DefaultText);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created Holiday PolicyName ." + HolidayPolicypage.getExceptionDesc());
		}

	}

	// MPI_594_Holiday_Policy_09
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_594_Holiday_Policy_09()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a user and assign them to the \"QC\" department with \"For Grade\" as the team, \"QC Head\" as the designation, and \"Staff\" as the employee type. Then, create a holiday Policy and define the same employee criteria within the policy. After saving the policy, ensure that the employee matching the specified criteria is correctly assigned to the newly created holidayÂ  Policy, confirming that the policy assignment based on employee attributes is functioning as expected.");

		ArrayList<String> data = initBase.loadExcelData("Holiday_Policy", currTC,
				"holidaypolicyname,year,reportingto,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,mailinator,pin");

		int i = 0;
	
		Assignedholidaypolicyname = data.get(i++) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		String year = data.get(i++);

		String reportingto = data.get(i++);
		empid = data.get(i++) + Pramod.generateRandomAlpha(6);
	
		assignedempfirstname = data.get(i++) + Pramod.generateRandomAlpha(5);
		emplastname = data.get(i++) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(i++) + initBase.executionRunTime + Pramod.generateRandomAlpha(4);
		String empphoneno = data.get(i++) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(i++);
		String mailinator = data.get(i++);


		
		String pin = data.get(i++) + Pramod.generateRandomNumber(3);

		String combinemail = empemail + mailinator;

		MeghHolidayPolicyPage HolidayPolicypage = new MeghHolidayPolicyPage(driver);
		MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


if (meghloginpage.MainLandingPage()) {
	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
}
		

		// creating new user
		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		

		if (EmployeePage.TotalCountDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On TotalCountDropDown Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TotalCountDropDown." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddNewButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddNewButton Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddNewButton." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeFormDisplayValidated()) {
			logResults.createLogs("Y", "PASS", "Employee Form Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Loading Employee Form." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTypeDropdown(entityname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On EmployeeTypeDropdown Successfully And Selected EntityName Successfully."
							+ entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTypeDropdown  ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.ReportingToDropdown()) {
			logResults.createLogs("Y", "PASS", "Clicked On ReportingToDropdown Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ReportingToDropdown  ." + EmployeePage.getExceptionDesc());
		}
	
		if (EmployeePage.ReportingToSearchInput(reportingto)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On ReportingTo Search Text Field And Input Is Passed." + reportingto);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ReportingTo Search Text Field ." + EmployeePage.getExceptionDesc());
		}
		

		if (EmployeePage.ReportingToSearchResult()) {
			logResults.createLogs("Y", "PASS", "Entity Name Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Entity Name   ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeId(empid)) {
			logResults.createLogs("Y", "PASS", "EmployeeID Entered On Employee ID Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  EmployeeID." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationDropdown(officename)) {
			logResults.createLogs("Y", "PASS", " CompanyLocation Selected From The DropDown ." + officename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Office Name." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeFirstName(assignedempfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Employee First Name Entered On Employee FirstName Text Field." + assignedempfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee First Name." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeLastName(emplastname)) {
			logResults.createLogs("Y", "PASS",
					"Employee Last Name Entered On Employee Last Name Text Field." + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Last Name." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeEmailID(combinemail)) {
			logResults.createLogs("Y", "PASS", "Employee Email Entered On Email ID Text Field." + combinemail);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Email." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeePhoneNo(empphoneno)) {
			logResults.createLogs("Y", "PASS",
					"Employee Phone Number Entered On Phone Number Text Field." + empphoneno);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Phone Number." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeDateTextField()) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date  Text Field Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Joining Date." + EmployeePage.getExceptionDesc());
		}
		
		if (EmployeePage.FirstDateSelection(empjoiningdate)) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date Entered On Joining Date Text Field." + empjoiningdate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Joining Date." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEmployeeSave()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddEmployeeSave Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEmployeeSave." + EmployeePage.getExceptionDesc());
		}
	

		if (EmployeePage.GoToDirectory()) {
			logResults.createLogs("Y", "PASS", "Clicked On GoToDirectory Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GoToDirectory." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.LasTNameSelected()) {
			logResults.createLogs("Y", "PASS", "Selected  LastName  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting LasTName." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(emplastname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully." + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field." + EmployeePage.getExceptionDesc());
		}
	

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully." + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee." + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton ."
					+ DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile ."
					+ DepartmentPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeptDropdown(departmentname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Dept ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DesignationDropdown(designationname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Designation From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
		}
		

		if (AttendancePolicyPage.TeamDropDown(teamname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
		}
	

		if (RolePermissionpage.PinTextField(pin)) {
			logResults.createLogs("Y", "PASS", "Successfully Enter The Pin." + pin);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Pin ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.SaveChanges()) {
			logResults.createLogs("Y", "PASS", "SaveChanges Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SaveChanges Button." + RolePermissionpage.getExceptionDesc());
		}


		if (RolePermissionpage.AutoSetPolicy()) {
			logResults.createLogs("Y", "PASS", "AutoSetPolicy Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AutoSetPolicy Button." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded." + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidaysButtonFromSidebar()) {

			logResults.createLogs("Y", "PASS", "HolidaysButtonFromSidebar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On HolidaysButtonFromSidebar Button."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.AddHolidayPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddHolidayPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddHolidayPolicyButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyNameTextField(Assignedholidaypolicyname)) {

			logResults.createLogs("Y", "PASS",
					"HolidayPolicyName Inputted In TextField Successfully." + Assignedholidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  HolidayPolicyName In TextField." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FinancialYear(year)) {

			logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting The Year." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.NextStepButton()) {

			logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.NextStepButton()) {

			logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddEmpCriteria()) {

			logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddEmpCriteria Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.OfficeDropDown(officename)) {

			logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully ." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Office Name ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeptDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown." + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Dept DropDown." + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.DeptOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting  Dept ." + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.TeamDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown." + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team  Dropdown ." + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (WeekOffPolicyPage.TeamOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DesignationDropdown(designationname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Designation From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
		}

		

		if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown." + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Employee Type From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.ApplyButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On ApplyButton.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Apply Button ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.CreatePolicySaveButton()) {

			logResults.createLogs("Y", "PASS", "CreatePolicySaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicySaveButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicySearchTextField(Assignedholidaypolicyname)) {

			logResults.createLogs("Y", "PASS",
					"HolidayPolicy Name Inutted In SearchTextField Successfully." + Assignedholidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Holidays HolidayPolicy Name In TextField."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyFirstRecord(Assignedholidaypolicyname)) {

			logResults.createLogs("Y", "PASS", "HolidayPolicy Displayed Successfully." + Assignedholidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created HolidayPolicy Name ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.AssignedEmpCountLink()) {

			logResults.createLogs("Y", "PASS", "AssignedEmpCountLink Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AssignedEmpCountLink ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FirstEmpRecordInAssignedEmpCountPage()) {

			logResults.createLogs("Y", "PASS", "FirstEmpRecordInAssignedEmpCountPage Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying FirstEmpRecordInAssignedEmpCountPage Emp Records ."
							+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.SearchTextFieldInAssignedEmpCountPage(assignedempfirstname)) {

			logResults.createLogs("Y", "PASS",
					"Emp Name Inputted In SearchTextField Successfully." + assignedempfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Name In Search Text Field ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FirstEmpRecord(assignedempfirstname)) {

			logResults.createLogs("Y", "PASS", "Assigned Emp Displayed Successfully." + assignedempfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Assigned Emp Records ." + HolidayPolicypage.getExceptionDesc());
		}
	}

	// MPI_595_Holiday_Policy_10
	@Test(enabled = true, priority = 8, groups = { "Smoke" })
	public void MPI_595_Holiday_Policy_10()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, inactivate the policy to which employees are assigned and ensure all the employees are reassigned to default policyÂ ");

		ArrayList<String> data = initBase.loadExcelData("Holiday_Policy", currTC,
				"Default");

		String DefaultText = data.get(0);

		MeghHolidayPolicyPage HolidayPolicypage = new MeghHolidayPolicyPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


if (meghloginpage.MainLandingPage()) {
	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
}
		

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded." + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidaysButtonFromSidebar()) {

			logResults.createLogs("Y", "PASS", "HolidaysButtonFromSidebar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On HolidaysButtonFromSidebar Button."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicySearchTextField(Assignedholidaypolicyname)) {

			logResults.createLogs("Y", "PASS",
					"HolidayPolicy Name Inutted In SearchTextField Successfully." + Assignedholidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Holidays HolidayPolicy Name In TextField."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyFirstRecord(Assignedholidaypolicyname)) {

			logResults.createLogs("Y", "PASS", "HolidayPolicy Displayed Successfully." + Assignedholidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created HolidayPolicy Name ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyDeleteIcon()) {

			logResults.createLogs("Y", "PASS", "Holiday Policy Delete Icon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Holiday Policy Delete Icon." + HolidayPolicypage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeleteButton()) {

			logResults.createLogs("Y", "PASS", "DeleteButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteButton." + AttendancePolicyPage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(assignedempfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully."
							+ assignedempfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field." + EmployeePage.getExceptionDesc());
		}
	

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee." + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton ."
					+ DepartmentPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.PolicyButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record Policy Button.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record Policy Button ."
					+ AttendancePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.DefaultHolidayPolicy(DefaultText)) {
			String HolidayPolicyNameAssigned = HolidayPolicypage.Defaultholiday;

			logResults.createLogs("Y", "PASS", "Created Holiday Policy Is Assigned To All Emp Successfully."
					+ HolidayPolicyNameAssigned + "=" + DefaultText);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created Holiday PolicyName ." + HolidayPolicypage.getExceptionDesc());
		}

	}

	// MPI_597_Holiday_Policy_11
	@Test(enabled = true, priority = 9, groups = { "Smoke" })
	public void MPI_597_Holiday_Policy_11()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, activate the policy and ensure again all the employees based on criteria are again fall under that created policyÂ ");

		ArrayList<String> data = initBase.loadExcelData("Holiday_Policy", currTC, "year");

		String year = data.get(0);

		MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
		MeghHolidayPolicyPage HolidayPolicypage = new MeghHolidayPolicyPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);

		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


if (meghloginpage.MainLandingPage()) {
	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
}
		

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded." + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidaysButtonFromSidebar()) {

			logResults.createLogs("Y", "PASS", "HolidaysButtonFromSidebar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On HolidaysButtonFromSidebar Button."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicySearchTextField(Assignedholidaypolicyname)) {

			logResults.createLogs("Y", "PASS",
					"HolidayPolicy Name Inutted In SearchTextField Successfully." + Assignedholidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Holidays HolidayPolicy Name In TextField."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyFirstRecord(Assignedholidaypolicyname)) {

			logResults.createLogs("Y", "PASS", "HolidayPolicy Displayed Successfully." + Assignedholidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created HolidayPolicy Name ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyEditIcon()) {

			logResults.createLogs("Y", "PASS", "HolidayPolicyEditIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Created HolidayPolicy Edit Icon ."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FinancialYear(year)) {

			logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting The Year." + HolidayPolicypage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.ActiveCheckBox()) {

			logResults.createLogs("Y", "PASS", "ActiveCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ActiveCheckBox." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.NextStepButton()) {

			logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.NextStepButton()) {

			logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.CreatePolicySaveButton()) {

			logResults.createLogs("Y", "PASS", "CreatePolicySaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicySaveButton." + HolidayPolicypage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button." + EmployeePage.getExceptionDesc());
		}

	
		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(assignedempfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully."
							+ assignedempfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field." + EmployeePage.getExceptionDesc());
		}
	

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee." + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton ."
					+ DepartmentPage.getExceptionDesc());
		}


		if (AttendancePolicyPage.PolicyButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record Policy Button.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record Policy Button ."
					+ AttendancePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyNameInEmp(Assignedholidaypolicyname)) {
			String HolidayPolicyNameAssigned = HolidayPolicypage.HolidayPolicyName;

			logResults.createLogs("Y", "PASS", "Created Holiday Policy Is Assigned To All Emp Successfully."
					+ HolidayPolicyNameAssigned + "=" + Assignedholidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created Holiday PolicyName ." + HolidayPolicypage.getExceptionDesc());
		}

	}

	// MPI_598_Holiday_Policy_12
	@Test(enabled = true, priority = 10, groups = { "Smoke" })
	public void MPI_598_Holiday_Policy_12()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, click on the \"Assign Employees\" count link for the employee criteria-added policy and check the functionality of the search feature in the mini window that appears. Ensure that the search works correctly using various options such as First Name, Last Name, Employee ID, Enroll ID, Designation, Department, and Office. Verify that relevant records are displayed based on the entered search input.");

		//ArrayList<String> data = initBase.loadExcelData("Holiday_Policy", currTC, "password,captcha,Default");


		MeghHolidayPolicyPage HolidayPolicypage = new MeghHolidayPolicyPage(driver);
		
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);

 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


if (meghloginpage.MainLandingPage()) {
	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
}
		
		
		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded." + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidaysButtonFromSidebar()) {

			logResults.createLogs("Y", "PASS", "HolidaysButtonFromSidebar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On HolidaysButtonFromSidebar Button."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicySearchTextField(Assignedholidaypolicyname)) {

			logResults.createLogs("Y", "PASS",
					"HolidayPolicy Name Inutted In SearchTextField Successfully." + Assignedholidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Holidays HolidayPolicy Name In TextField."
					+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.HolidayPolicyFirstRecord(Assignedholidaypolicyname)) {

			logResults.createLogs("Y", "PASS", "HolidayPolicy Displayed Successfully." + Assignedholidaypolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created HolidayPolicy Name ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.AssignedEmpCountLink()) {

			logResults.createLogs("Y", "PASS", "AssignedEmpCountLink Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AssignedEmpCountLink ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FirstEmpRecordInAssignedEmpCountPage()) {

			logResults.createLogs("Y", "PASS", "FirstEmpRecordInAssignedEmpCountPage Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying FirstEmpRecordInAssignedEmpCountPage Emp Records ."
							+ HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.SearchTextFieldInAssignedEmpCountPage(assignedempfirstname)) {

			logResults.createLogs("Y", "PASS",
					"Emp Name Inputted In SearchTextField Successfully." + assignedempfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Name In Search Text Field ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FirstEmpRecord(assignedempfirstname)) {

			logResults.createLogs("Y", "PASS", "Assigned Emp Displayed Successfully." + assignedempfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Assigned Emp Records ." + HolidayPolicypage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LastNameCheckBox()) {

			logResults.createLogs("Y", "PASS", "LastNameCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LastNameCheckBox." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.EmployeeIDCheckBox()) {

			logResults.createLogs("Y", "PASS", "EmployeeIDCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmployeeIDCheckBox." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown." + LeavePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.SearchTextFieldInAssignedEmpCountPage(emplastname)) {

			logResults.createLogs("Y", "PASS", "Emp Name Inputted In SearchTextField Successfully." + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Name In Search Text Field ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FirstEmpRecordComparison()) {

			logResults.createLogs("Y", "PASS", "Assigned Emp Displayed Successfully." + assignedempfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Assigned Emp Records ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.SearchTextFieldInAssignedEmpCountPage(empid)) {

			logResults.createLogs("Y", "PASS", "Emp Name Inputted In SearchTextField Successfully." + empid);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Name In Search Text Field ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FirstEmpRecordComparison()) {

			logResults.createLogs("Y", "PASS", "Assigned Emp Displayed Successfully." + assignedempfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Assigned Emp Records ." + HolidayPolicypage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LastNameCheckBox()) {

			logResults.createLogs("Y", "PASS", "LastNameCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LastNameCheckBox." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.EmployeeIDCheckBox()) {

			logResults.createLogs("Y", "PASS", "EmployeeIDCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmployeeIDCheckBox." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.DesignationCheckBox()) {

			logResults.createLogs("Y", "PASS", "DesignationCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DesignationCheckBox." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.DepartmentCheckBox()) {

			logResults.createLogs("Y", "PASS", "DepartmentCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DepartmentCheckBox." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown." + LeavePolicyPage.getExceptionDesc());
		}

		if (HolidayPolicypage.SearchTextFieldInAssignedEmpCountPage(designationname)) {

			logResults.createLogs("Y", "PASS", "Emp Name Inputted In SearchTextField Successfully." + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Name In Search Text Field ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FirstEmpRecordComparison()) {

			logResults.createLogs("Y", "PASS", "Assigned Emp Displayed Successfully." + assignedempfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Assigned Emp Records ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.SearchTextFieldInAssignedEmpCountPage(departmentname)) {

			logResults.createLogs("Y", "PASS", "Emp Name Inputted In SearchTextField Successfully." + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Name In Search Text Field ." + HolidayPolicypage.getExceptionDesc());
		}

		if (HolidayPolicypage.FirstEmpRecordComparison()) {

			logResults.createLogs("Y", "PASS", "Assigned Emp Displayed Successfully." + assignedempfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Assigned Emp Records ." + HolidayPolicypage.getExceptionDesc());
		}

	}
	
	
	// MPI_849_Holiday_Policy_13
		@Test(enabled = true, priority = 0, groups = { "Smoke", "AddMaster" })
		public void MPI_849_Holiday_Policy_13()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, create an holiday policy and add employee criteria and ensure matching criteria employee's are assigned to created policy");

			ArrayList<String> data = initBase.loadExcelData("Holiday_Policy", currTC,
					"password,captcha,holidaypolicyname");

			int i = 0;
			String password = data.get(i++);
			String captcha = data.get(i++);
			String holidaypolicyname = data.get(i++) + initBase.executionRunTime;
	
			

			MeghHolidayPolicyPage HolidayPolicypage = new MeghHolidayPolicyPage(driver);
			MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
			MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
			MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			
			
			
			
			
			if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
				logResults.createLogs("Y", "PASS", "Login Done Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Login Is Failed." + MeghLoginPage.getExceptionDesc());
			}
			
			if (initBase.stopNewData) { // Tapan 5 July 25
				String HolidayPolicyName = Utils.propsReadWrite("data/addmaster.properties", "get", "HolidayPolicyName", "");
				if (HolidayPolicyName.equals("pass")) {
					logResults.createLogs("Y", "INFO", "Skip Adding New Data. Continue with Old Test Data.");
				return;
				}
			}
	
			if (RolePermissionpage.PolicyIcon()) {

				logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
			}

			if (ShiftPolicyPage.ShiftMasterLoaded()) {

				logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded." + ShiftPolicyPage.getExceptionDesc());
			}

			if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

				logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On LeaveDropDownFromSideBar Button." + LeavePolicyPage.getExceptionDesc());
			}

			if (HolidayPolicypage.HolidaysButtonFromSidebar()) {

				logResults.createLogs("Y", "PASS", "HolidaysButtonFromSidebar Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On HolidaysButtonFromSidebar Button."
						+ HolidayPolicypage.getExceptionDesc());
			}

			if (HolidayPolicypage.AddHolidayPolicyButton()) {

				logResults.createLogs("Y", "PASS", "AddHolidayPolicyButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddHolidayPolicyButton." + HolidayPolicypage.getExceptionDesc());
			}

			if (HolidayPolicypage.HolidayPolicyNameTextField(holidaypolicyname)) {

				logResults.createLogs("Y", "PASS",
						"HolidayPolicyName Inputted In TextField Successfully." + holidaypolicyname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  HolidayPolicyName In TextField." + HolidayPolicypage.getExceptionDesc());
			}
			
			
			if (HolidayPolicypage.NextStepButton()) {

				logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
			}

			if (HolidayPolicypage.NextStepButton()) {

				logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
			}

			if (AttendancePolicyPage.AddEmpCriteria()) {

				logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddEmpCriteria Button ." + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.OfficeDropDown(officename)) {

				logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully ." + officename);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Office Name ." + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.DeptDropDownClick()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown." + departmentname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Dept DropDown." + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (AttendancePolicyPage.DeptOptionSelected()) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + departmentname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting  Dept ." + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (AttendancePolicyPage.TeamDropDownClick()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown." + teamname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Team  Dropdown ." + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (WeekOffPolicyPage.TeamOptionSelected()) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Team From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.DesignationDropdown(designationname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Designation From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown." + entityname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Employee Type From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.ApplyButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On ApplyButton.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Apply Button ." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (HolidayPolicypage.CreatePolicySaveButton()) {

				logResults.createLogs("Y", "PASS", "CreatePolicySaveButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CreatePolicySaveButton." + HolidayPolicypage.getExceptionDesc());
			}

			if (AttendancePolicyPage.YesButton()) {
				
				logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
			} 
			if (HolidayPolicypage.HolidayPolicySearchTextField(holidaypolicyname)) {
				Utils.propsReadWrite("data/addmaster.properties", "set", "HolidayPolicyName", "pass");

				logResults.createLogs("Y", "PASS",
						"HolidayPolicy Name Inutted In SearchTextField Successfully." + holidaypolicyname);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Holidays HolidayPolicy Name In TextField."
						+ HolidayPolicypage.getExceptionDesc());
			}
			if (HolidayPolicypage.HolidayPolicyEditIcon()) {

				logResults.createLogs("Y", "PASS", "HolidayPolicyEditIcon Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Created HolidayPolicy Edit Icon ."
						+ HolidayPolicypage.getExceptionDesc());
			}
			
			if (HolidayPolicypage.DefaultCheckBox()) {

				logResults.createLogs("Y", "PASS", "Default CheckBox CLicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking Default Checkbox ."
						+ HolidayPolicypage.getExceptionDesc());
			}
			if (HolidayPolicypage.DefaultClickConfirm()) {

				logResults.createLogs("Y", "PASS", "Policy Made To Default Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking Default Checkbox Confirm Button ."
						+ HolidayPolicypage.getExceptionDesc());
			}
			
			
			if (HolidayPolicypage.NextStepButton()) {

				logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
			}

			if (HolidayPolicypage.NextStepButton()) {

				logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
			}
			
			if (HolidayPolicypage.CreatePolicySaveButton()) {

				logResults.createLogs("Y", "PASS", "CreatePolicySaveButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CreatePolicySaveButton." + HolidayPolicypage.getExceptionDesc());
			}

			if (AttendancePolicyPage.YesButton()) {
				
				logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
			}
			
			/*    Done by me because of some reason
			
			if (HolidayPolicypage.HolidayPolicySearchTextField(Default)) {

				logResults.createLogs("Y", "PASS",
						"HolidayPolicy Name Inutted In SearchTextField Successfully." + Default);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Holidays HolidayPolicy Name In TextField."
						+ HolidayPolicypage.getExceptionDesc());
			}

			if (HolidayPolicypage.HolidayPolicyFirstRecord(Default)) {

				logResults.createLogs("Y", "PASS", "HolidayPolicy Displayed Successfully." + Default);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying Created HolidayPolicy Name ." + HolidayPolicypage.getExceptionDesc());
			}
			if (HolidayPolicypage.HolidayPolicyEditIcon()) {

				logResults.createLogs("Y", "PASS", "HolidayPolicyEditIcon Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Created HolidayPolicy Edit Icon ."
						+ HolidayPolicypage.getExceptionDesc());
			}
			
			if (HolidayPolicypage.NextStepButton()) {

				logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
			}

			if (HolidayPolicypage.NextStepButton()) {

				logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
			}

			if (AttendancePolicyPage.AddEmpCriteria()) {

				logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddEmpCriteria Button ." + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.OfficeDropDown(officename)) {

				logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully ." + officename);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Office Name ." + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.DeptDropDownClick()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown." + departmentname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Dept DropDown." + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (AttendancePolicyPage.DeptOptionSelected()) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + departmentname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting  Dept ." + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (AttendancePolicyPage.TeamDropDownClick()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown." + teamname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Team  Dropdown ." + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (WeekOffPolicyPage.TeamOptionSelected()) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Team From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.DesignationDropdown(designationname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Designation From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown." + entityname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Employee Type From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.ApplyButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On ApplyButton.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Apply Button ." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (HolidayPolicypage.CreatePolicySaveButton()) {

				logResults.createLogs("Y", "PASS", "CreatePolicySaveButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CreatePolicySaveButton." + HolidayPolicypage.getExceptionDesc());
			}

			if (AttendancePolicyPage.YesButton()) {
				Utils.propsReadWrite("data/addmaster.properties", "set", "HolidayPolicyName", "pass");
				logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (HolidayPolicypage.HolidayPolicySearchTextField(Default)) {

				logResults.createLogs("Y", "PASS",
						"HolidayPolicy Name Inutted In SearchTextField Successfully." + Default);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Holidays HolidayPolicy Name In TextField."
						+ HolidayPolicypage.getExceptionDesc());
			}
			if (HolidayPolicypage.HolidayPolicyEditIcon()) {

				logResults.createLogs("Y", "PASS", "HolidayPolicyEditIcon Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Created HolidayPolicy Edit Icon ."
						+ HolidayPolicypage.getExceptionDesc());
			}
			
			if (HolidayPolicypage.DefaultCheckBox()) {

				logResults.createLogs("Y", "PASS", "Default CheckBox CLicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking Default Checkbox ."
						+ HolidayPolicypage.getExceptionDesc());
			}
			if (HolidayPolicypage.DefaultClickConfirm()) {

				logResults.createLogs("Y", "PASS", "Policy Made To Default Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking Default Checkbox ."
						+ HolidayPolicypage.getExceptionDesc());
			}
			
			
			if (HolidayPolicypage.NextStepButton()) {

				logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
			}

			if (HolidayPolicypage.NextStepButton()) {

				logResults.createLogs("Y", "PASS", "NextStepButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On NextStepButton." + HolidayPolicypage.getExceptionDesc());
			}
			
			if (HolidayPolicypage.CreatePolicySaveButton()) {

				logResults.createLogs("Y", "PASS", "CreatePolicySaveButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CreatePolicySaveButton." + HolidayPolicypage.getExceptionDesc());
			}

			if (AttendancePolicyPage.YesButton()) {
				
				logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
			}
	*/
		}
	
	
	
	
	
	
	

	@AfterMethod(alwaysRun = true)
	void Aftermethod() {
		logResults.onlyLog();
		
	}

	@AfterClass(alwaysRun = true)
	void cleanUp() {
		if (driver != null) {
			driver.quit();
		}
	}

}
