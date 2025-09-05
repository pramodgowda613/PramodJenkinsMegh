package com.MeghPI.Attendance.tests;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.pages.MeghAttendancePolicyPage;
import com.MeghPI.Attendance.pages.MeghLeavePolicyPage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterDesignationPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterLeaveTypePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghWeekOffPolicyPage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class MeghLeavePolicyTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	public String empfirstname = "";
	public String leavepolicyname = "";
	public String empid = "";

	
	public String emplastname = "";
	

	private String cmpcode = "";
	private String Emailid = "";
	private String entityname = "";
	private String officename = "";
	private String departmentname = "";
	private String teamname = "";
	private String designationname= "";
    private String gradename = ""; 
	
	
	
	@Parameters({ "device" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device) { // String param1
		initBase.browser = "chrome";
		driver = loadDriver.getDriver(device);

		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

	@Parameters({ "device" })
	@BeforeClass(alwaysRun = true)
	void runOnce(int device) {
		logResults.createReport(device);
		logResults.setTestMethodErrorCount(0);
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		entityname = "AutoEN" + initBase.executionRunTime;
		gradename = "AutoGN" + initBase.executionRunTime;
	}

	// MPI_544_Leave_Policy_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_544_Leave_Policy_01() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a leave policy without selecting any employee criteria, make it active, and ensure that the policy is assigned to all users. Also, check in an existing user's profile under the policy module to confirm that the created policy is assigned.");

		ArrayList<String> data = initBase.loadExcelData("leave_policy", currTC,
				"password,captcha,leavepolicyname,sickleave,travelleave,casualleave");

		
		String password = data.get(0);
		String captcha = data.get(1);
		String leavepolicynames = data.get(2) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		String sickleave = data.get(3);
		String travelleave = data.get(4);
		String casualleave = data.get(5);

		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		
		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}


		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon: " + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded: " + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button: "
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeaveTypePage.AddLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddLeavePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddLeavePolicyButton: " + LeaveTypePage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyNameTextField(leavepolicynames)) {

			logResults.createLogs("Y", "PASS", "LeavePolicy Name Inputted Successfully: " + leavepolicynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting LeavePolicy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeaveTypePage.SelectLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "SelectLeavePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SelectLeavePolicyButton: " + LeaveTypePage.getExceptionDesc());
		}

		if (LeavePolicyPage.AllLeaves(sickleave)) {

			logResults.createLogs("Y", "PASS", "SickLeave Selected Successfully: " + sickleave);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Sick Leave: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AllLeaves2(travelleave)) {

			logResults.createLogs("Y", "PASS", "Travel Leave Selected Successfully: " + travelleave);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Travel Leave: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AllLeaves3(casualleave)) {

			logResults.createLogs("Y", "PASS", "Casual Leave Selected Successfully: " + casualleave);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Casual Leave: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AddLeaveSaveButton()) {

			logResults.createLogs("Y", "PASS", "AddLeaveSaveButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddLeaveSaveButton: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.CreateLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreateLeavePolicyButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateLeavePolicyButton: " + LeavePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton : "
					+ DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (AttendancePolicyPage.PolicyButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record Policy Button: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record Policy Button : "
					+ AttendancePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyInManageProfile(leavepolicynames)) {
			String leavenames = LeavePolicyPage.fullLeaveName;
			logResults.createLogs("Y", "PASS", "Leave Policy Name Compared And Same Only Displayed Successfully: "
					+ leavepolicynames + "=" + leavenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Leave PolicyName: " + LeavePolicyPage.getExceptionDesc());
		}

	}

	// MPI_545_Leave_Policy_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_545_Leave_Policy_02() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the leave policy and set it as the default. Then, create a new employee and ensure that the default leave policy is automatically assigned to the newly created employee.");

		ArrayList<String> data = initBase.loadExcelData("leave_policy", currTC,
				"password,captcha,leavepolicyname,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,sickleave,defaulttext");

		
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

		
		int i = 0;
		
		String password = data.get(i);
		String captcha = data.get(++i);
		String leavepolicyname = data.get(++i) + Pramod.generateRandomAlpha(4);
	
		String mailinator = data.get(++i);
		String empid = data.get(++i) + Pramod.generateRandomNumber(5) + Pramod.generateRandomAlpha(3);
		

		String empfirstname = data.get(++i) + Pramod.generateRandomAlpha(5);
		String emplastname = data.get(++i) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(++i);
		String empphoneno = data.get(++i) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(++i);
		String reportingto = data.get(++i);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(3) + mailinator;
		
		String enrollmentimage = data.get(++i);
		String sickleave = data.get(++i);
		String Defaulttext = data.get(++i);

		String editleavepolicyname = leavepolicyname + Pramod.generateRandomAlpha(4);

		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

	
		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}


		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon: " + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded: " + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button: "
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeaveTypePage.AddLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddLeavePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddLeavePolicyButton: " + LeaveTypePage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyNameTextField(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "LeavePolicy Name Inputted Successfully: " + leavepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting LeavePolicy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeaveTypePage.SelectLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "SelectLeavePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SelectLeavePolicyButton: " + LeaveTypePage.getExceptionDesc());
		}

		if (LeavePolicyPage.AllLeaves(sickleave)) {

			logResults.createLogs("Y", "PASS", "SickLeave Selected Successfully: " + sickleave);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Sick Leave: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AddLeaveSaveButton()) {

			logResults.createLogs("Y", "PASS", "AddLeaveSaveButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddLeaveSaveButton: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.CreateLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreateLeavePolicyButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateLeavePolicyButton: " + LeavePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicySearchTextFields(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Inputted Successfully: " + leavepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchInputtedPolicyvalidation(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyEditIcon()) {

			logResults.createLogs("Y", "PASS", "Created Policy Edit Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Policy Edit Icon: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.DefaultCheckBox()) {

			logResults.createLogs("Y", "PASS", "DefaultCheckBox Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DefaultCheckBox: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.ActiveCheckBox()) {

			logResults.createLogs("Y", "PASS", "ActiveCheckBox Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ActiveCheckBox: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.CreateLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreateLeavePolicyButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateLeavePolicyButton: " + LeavePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button : " + AttendancePolicyPage.getExceptionDesc());
		}

//creating new user
		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}



		if (EmployeePage.TotalCountDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On TotalCountDropDown Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TotalCountDropDown: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddNewButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddNewButton Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddNewButton: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeFormDisplayValidated()) {
			logResults.createLogs("Y", "PASS", "Employee Form Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Loading Employee Form: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTypeDropdown(entityname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On EmployeeTypeDropdown Successfully And Selected EntityName Successfully: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTypeDropdown  : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.ReportingToDropdown()) {
			logResults.createLogs("Y", "PASS", "Clicked On ReportingToDropdown Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ReportingToDropdown  : " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(2000);
		if (EmployeePage.ReportingToSearchInput(reportingto)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On ReportingTo Search Text Field And Input Is Passed: " + reportingto);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ReportingTo Search Text Field : " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (EmployeePage.ReportingToSearchResult()) {
			logResults.createLogs("Y", "PASS", "Entity Name Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Entity Name   : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeId(empid)) {
			logResults.createLogs("Y", "PASS", "EmployeeID Entered On Employee ID Text Field: " + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  EmployeeID: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationDropdown(officename)) {
			logResults.createLogs("Y", "PASS", " CompanyLocation Selected From The DropDown : " + officename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Office Name: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeFirstName(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Employee First Name Entered On Employee FirstName Text Field: " + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee First Name: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeLastName(emplastname)) {
			logResults.createLogs("Y", "PASS",
					"Employee Last Name Entered On Employee Last Name Text Field: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Last Name: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeEmailID(email)) {
			logResults.createLogs("Y", "PASS", "Employee Email Entered On Email ID Text Field: " + email);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Email: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeePhoneNo(empphoneno)) {
			logResults.createLogs("Y", "PASS",
					"Employee Phone Number Entered On Phone Number Text Field: " + empphoneno);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Phone Number: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeDateTextField()) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date  Text Field Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Joining Date: " + EmployeePage.getExceptionDesc());
		}
		
		if (EmployeePage.FirstDateSelection(empjoiningdate)) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date Entered On Joining Date Text Field: "+ empjoiningdate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Joining Date: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEmployeeSave()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddEmployeeSave Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEmployeeSave: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

//enrolling the User

		if (EmployeePage.GoToDirectory()) {
			logResults.createLogs("Y", "PASS", "Clicked On GoToDirectory Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GoToDirectory: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee First Name Successfully: "
							+ empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DotsMenu()) {
			logResults.createLogs("Y", "PASS", "Clicked On 3Dots Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On 3Dots: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.WebEnrollment()) {
			logResults.createLogs("Y", "PASS", "Clicked On WebEnrollment Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On WebEnrollment: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.FaceEnrollmentTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On FaceEnrollmentTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FaceEnrollmentTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.ImageUploadButton(enrollmentimage)) {
			logResults.createLogs("Y", "PASS", "Image Selected For WebEnrollment Successfully: " + enrollmentimage);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Image For WebEnrollment: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CropImage()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On CropImage: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On CropImage: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SaveEnrollmentButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SaveEnrollmentButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SaveEnrollmentButton: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SaveEnrollmentSuccessMsg()) {
			logResults.createLogs("Y", "PASS", "Success Msg Is Displayed: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Success Msg: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: "
							+ empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee: " + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton : "
					+ DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (AttendancePolicyPage.PolicyButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record Policy Button: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record Policy Button : "
					+ AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (LeavePolicyPage.LeavePolicyInManageProfile(Defaulttext)) {
			String leavenames = LeavePolicyPage.fullLeaveName;
			logResults.createLogs("Y", "PASS", "Leave Policy Name Compared And Same Only Displayed Successfully: "
					+ Defaulttext + "=" + leavenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Leave PolicyName: " + LeavePolicyPage.getExceptionDesc());
		}

	}

	// MPI_546_Leave_Policy_03
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_546_Leave_Policy_03() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create an employee by selecting 'QC' as the department, 'Grade1' as the grade, and 'For Grade' as the team option, and ensure these are updated in the employee profile. Then, create a leave policy and set the employee criteria to match the same department, grade, and team as assigned to the employee. Save the leave policy, and then click on the \"Assigned Employee Count\" in the leave policy module. Ensure that the employee with the matching criteria is displayed in the list.");

		ArrayList<String> data = initBase.loadExcelData("leave_policy", currTC,
				"password,captcha,leavepolicyname,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,sickleave,defaulttext,pin");

		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

		int i=0;
		
		String password = data.get(i);
		String captcha = data.get(++i);
		

		leavepolicyname = data.get(++i) + Pramod.generateRandomAlpha(4);
		
		String mailinator = data.get(++i);
		empid = data.get(++i) + Pramod.generateRandomNumber(5) + Pramod.generateRandomAlpha(5);
		

		empfirstname = data.get(++i) + Pramod.generateRandomAlpha(5);
		emplastname = data.get(++i) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(++i);
		String empphoneno = data.get(++i) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(++i);
		String reportingto = data.get(++i);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(4) + mailinator;
		
		String enrollmentimage = data.get(++i);
		String sickleave = data.get(++i);
		String Defaulttext = data.get(++i);
		String pin = data.get(++i) + Pramod.generateRandomNumber(3);
		

		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}


		// creating new user
		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.TotalCountDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On TotalCountDropDown Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TotalCountDropDown: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddNewButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddNewButton Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddNewButton: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeFormDisplayValidated()) {
			logResults.createLogs("Y", "PASS", "Employee Form Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Loading Employee Form: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTypeDropdown(entityname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On EmployeeTypeDropdown Successfully And Selected EntityName Successfully: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTypeDropdown  : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.ReportingToDropdown()) {
			logResults.createLogs("Y", "PASS", "Clicked On ReportingToDropdown Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ReportingToDropdown  : " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(2000);
		if (EmployeePage.ReportingToSearchInput(reportingto)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On ReportingTo Search Text Field And Input Is Passed: " + reportingto);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ReportingTo Search Text Field : " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (EmployeePage.ReportingToSearchResult()) {
			logResults.createLogs("Y", "PASS", "Entity Name Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Entity Name   : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeId(empid)) {
			logResults.createLogs("Y", "PASS", "EmployeeID Entered On Employee ID Text Field: " + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  EmployeeID: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationDropdown(officename)) {
			logResults.createLogs("Y", "PASS", " CompanyLocation Selected From The DropDown : " + officename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Office Name: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeFirstName(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Employee First Name Entered On Employee FirstName Text Field: " + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee First Name: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeLastName(emplastname)) {
			logResults.createLogs("Y", "PASS",
					"Employee Last Name Entered On Employee Last Name Text Field: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Last Name: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeEmailID(email)) {
			logResults.createLogs("Y", "PASS", "Employee Email Entered On Email ID Text Field: " + email);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Email: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeePhoneNo(empphoneno)) {
			logResults.createLogs("Y", "PASS",
					"Employee Phone Number Entered On Phone Number Text Field: " + empphoneno);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Phone Number: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeDateTextField()) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date  Text Field Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Joining Date: " + EmployeePage.getExceptionDesc());
		}
		
		if (EmployeePage.FirstDateSelection(empjoiningdate)) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date Entered On Joining Date Text Field: " + empjoiningdate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Joining Date: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEmployeeSave()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddEmployeeSave Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEmployeeSave: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

//enrolling the User

		if (EmployeePage.GoToDirectory()) {
			logResults.createLogs("Y", "PASS", "Clicked On GoToDirectory Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GoToDirectory: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee First Name Successfully: "
							+ empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DotsMenu()) {
			logResults.createLogs("Y", "PASS", "Clicked On 3Dots Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On 3Dots: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.WebEnrollment()) {
			logResults.createLogs("Y", "PASS", "Clicked On WebEnrollment Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On WebEnrollment: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.FaceEnrollmentTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On FaceEnrollmentTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FaceEnrollmentTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.ImageUploadButton(enrollmentimage)) {
			logResults.createLogs("Y", "PASS", "Image Selected For WebEnrollment Successfully: " + enrollmentimage);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Image For WebEnrollment: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CropImage()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On CropImage: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On CropImage: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SaveEnrollmentButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SaveEnrollmentButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SaveEnrollmentButton: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SaveEnrollmentSuccessMsg()) {
			logResults.createLogs("Y", "PASS", "Success Msg Is Displayed: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Success Msg: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: "
							+ empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee: " + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton : "
					+ DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile : "
					+ DepartmentPage.getExceptionDesc());
		}

		Thread.sleep(3000);

		if (AttendancePolicyPage.DeptDropdown(departmentname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Dept : " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (AttendancePolicyPage.TeamDropDown(teamname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DesignationDropdown(designationname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown: " + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Designation From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (RolePermissionpage.PinTextField(pin)) {
			logResults.createLogs("Y", "PASS", "Successfully Enter The Pin: " + pin);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Pin : " + RolePermissionpage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (RolePermissionpage.SaveChanges()) {
			logResults.createLogs("Y", "PASS", "SaveChanges Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SaveChanges Button: " + RolePermissionpage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (RolePermissionpage.DefaultPolicy()) {
			logResults.createLogs("Y", "PASS", "DefaultPolicy Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DefaultPolicy Button: " + RolePermissionpage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon: " + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded: " + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button: "
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeaveTypePage.AddLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddLeavePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddLeavePolicyButton: " + LeaveTypePage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyNameTextField(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "LeavePolicy Name Inputted Successfully: " + leavepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting LeavePolicy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeaveTypePage.SelectLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "SelectLeavePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SelectLeavePolicyButton: " + LeaveTypePage.getExceptionDesc());
		}

		if (LeavePolicyPage.AllLeaves(sickleave)) {

			logResults.createLogs("Y", "PASS", "SickLeave Selected Successfully: " + sickleave);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Sick Leave: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AddLeaveSaveButton()) {

			logResults.createLogs("Y", "PASS", "AddLeaveSaveButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddLeaveSaveButton: " + LeavePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddEmpCriteria()) {

			logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddEmpCriteria Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.OfficeDropDown(officename)) {

			logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully : " + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Office Name : " + LeavePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.DeptDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Dept DropDown: " + AttendancePolicyPage.getExceptionDesc());
		}
		
		
		
		if (LeavePolicyPage.DeptOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting  Dept : " + LeavePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.TeamDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team  Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (WeekOffPolicyPage.TeamOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team From Dropdown : " + WeekOffPolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.DesignationDropdown(designationname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown: " + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Designation From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Employee Type From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftAddEmpCriteriaApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Apply Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Apply Button : " + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.CreateLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreateLeavePolicyButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateLeavePolicyButton: " + LeavePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicySearchTextFields(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Inputted Successfully: " + leavepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLink()) {

			logResults.createLogs("Y", "PASS", "Created Policy Assigned Link Count Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Policy Assigned Emp Count Link: "
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.EmployeeListLoaded()) {

			logResults.createLogs("Y", "PASS", "Employee List Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Employee Records Are Not Loaded: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLinkSearchTextField(empfirstname)) {

			logResults.createLogs("Y", "PASS", "Emp Name Displayed Successfully: " + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Emp Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLinkSearchResult(empfirstname)) {
			String empfirstnames = LeavePolicyPage.firstname;

			logResults.createLogs("Y", "PASS",
					"Emp Name Validated And Displayed Successfully: " + empfirstname + "=" + empfirstnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Name: " + LeavePolicyPage.getExceptionDesc());
		}

	}

	// MPI_547_Leave_Policy_04
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_547_Leave_Policy_04() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, delete or inactivate the employee-criteria-based leave policy and ensure that the affected employees are automatically reassigned to the default leave policy.");

		ArrayList<String> data = initBase.loadExcelData("leave_policy", currTC,
				"password,captcha,defaulttext,assignedemp");

		
		String password = data.get(0);
		String captcha = data.get(1);

		String Defaulttext = data.get(2);
		String assignedemp = data.get(3);

		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}


		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon: " + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded: " + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button: "
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicySearchTextFields(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Inputted Successfully: " + leavepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchInputtedPolicyvalidation(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyDeleteIcon()) {

			logResults.createLogs("Y", "PASS", "Created Policy Delete Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Policy Delete Icon: " + LeavePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeleteButton()) {

			logResults.createLogs("Y", "PASS", "DeleteButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteButton: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicySearchTextFields(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Inputted Successfully: " + leavepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchInputtedPolicyvalidation(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCount(assignedemp)) {
			String assignempcountno = LeavePolicyPage.AssignedEmpCounts;

			logResults.createLogs("Y", "PASS", "Created Policy Name Displayed Successfully: " + assignedemp);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: "
							+ empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee: " + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton : "
					+ DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (AttendancePolicyPage.PolicyButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record Policy Button: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record Policy Button : "
					+ AttendancePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyInManageProfile(Defaulttext)) {
			String leavenames = LeavePolicyPage.fullLeaveName;
			logResults.createLogs("Y", "PASS", "Leave Policy Name Compared And Same Only Displayed Successfully: "
					+ Defaulttext + "=" + leavenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Leave PolicyName: " + LeavePolicyPage.getExceptionDesc());
		}

		Thread.sleep(3000);

	}

	// MPI_548_Leave_Policy_05
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_548_Leave_Policy_05() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, activate the previously inactivated employee-criteria-based policy and ensure that the employees matching the criteria are reassigned to that policy.");

		ArrayList<String> data = initBase.loadExcelData("leave_policy", currTC,
				"password,captcha");

		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
		

		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

		

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}


		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon: " + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded: " + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button: "
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}
		
		Thread.sleep(5000);

		if (LeavePolicyPage.LeavePolicySearchTextFields(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Inputted Successfully: " + leavepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchInputtedPolicyvalidation(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyEditIcon()) {

			logResults.createLogs("Y", "PASS", "Created Policy Edit Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Policy Edit Icon: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.ActiveCheckBox()) {

			logResults.createLogs("Y", "PASS", "ActiveCheckBox Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ActiveCheckBox: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.CreateLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreateLeavePolicyButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateLeavePolicyButton: " + LeavePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicySearchTextFields(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Inputted Successfully: " + leavepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchInputtedPolicyvalidation(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountGreaterThanZero()) {

			String count = LeavePolicyPage.assignedEmpCountStr;

			logResults.createLogs("Y", "PASS", "Assigned Emp Count Displayed Successfully: " + count);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Emp Count  : " + LeavePolicyPage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: "
							+ empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee: " + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton : "
					+ DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (AttendancePolicyPage.PolicyButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record Policy Button: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record Policy Button : "
					+ AttendancePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyInManageProfile(leavepolicyname)) {
			String leavenames = LeavePolicyPage.fullLeaveName;
			logResults.createLogs("Y", "PASS", "Leave Policy Name Compared And Same Only Displayed Successfully: "
					+ leavepolicyname + "=" + leavenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Leave PolicyName: " + LeavePolicyPage.getExceptionDesc());
		}

		Thread.sleep(3000);

	}

	// MPI_549_Leave_Policy_06
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_549_Leave_Policy_06() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of search text field by entering policy name");

		logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_548_Leave_Policy_05 TestCase: ");
	}

	// MPI_556_Leave_Policy_13
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_556_Leave_Policy_13() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, go to the Create Leave Policy module, click on \"Create Leave,\" and ensure that the newly created leave is displayed in the Leave module.");

		ArrayList<String> data = initBase.loadExcelData("leave_policy", currTC,
				"password,captcha,leavename,leavecode,leavedescription");

		
		String password = data.get(0);
		String captcha = data.get(1);
		String leavename = data.get(2) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);

		String leavecode = data.get(3) + Pramod.generateRandomAlpha(3);
		String leavedescription = data.get(4) + initBase.executionRunTime;

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		
		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}


		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon: " + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded: " + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button: "
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeaveTypePage.AddLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddLeavePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddLeavePolicyButton: " + LeaveTypePage.getExceptionDesc());
		}

		if (LeavePolicyPage.CreateLeaveTypeButton()) {

			logResults.createLogs("Y", "PASS", "CreateLeaveTypeButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateLeaveTypeButton: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveNameTextField(leavename)) {
			logResults.createLogs("Y", "PASS", "Leave Type Name Inputted Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name : " + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveCodeTextField(leavecode)) {
			logResults.createLogs("Y", "PASS", "Leave Type Code Inputted Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Code : " + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveDescriptionTextField(leavedescription)) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab : " + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.PaidRadioButton()) {
			logResults.createLogs("Y", "PASS", "Leave Type Description Inputted  Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Description : " + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.MaleRadioButton()) {
			logResults.createLogs("Y", "PASS", "Male Radio Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Male Radio Button : " + LeaveTypePage.getExceptionDesc());
		}
		if (LeavePolicyPage.AddLeaveTypeSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddLeaveTypeSaveButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddLeaveTypeSaveButton : " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SuccessMsg()) {

			logResults.createLogs("Y", "PASS", "Record Created Success Message Displayed  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Success Msg: " + LeavePolicyPage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab : " + EmployeePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypeButton()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeButton : " + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "LeaveType Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Leave Type Page Is Not Completely : " + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypeSearchField(leavename)) {
			logResults.createLogs("Y", "PASS", "Updated Leave Type Name Inputted Successfully: " + leavename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name : " + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypeFirstRow()) {
			String firstrowleave = LeaveTypePage.firstrowleavename;
			logResults.createLogs("Y", "PASS",
					"Updated Leave Type Name Inputted Successfully: " + leavename + "=" + firstrowleave);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name : " + LeaveTypePage.getExceptionDesc());
		}

	}

	// MPI_558_Leave_Policy_15
	// @Test(enabled = true, priority = 9, groups = { "Smoke" })
	public void MPI_558_Leave_Policy_15() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create two employee accounts and update their profiles by selecting \"QC\" as the department, \"For Grade\" as the team, and \"Grade1\" as the grade. Save the profiles, then navigate to the Leave Policy module and add an employee criteria by selecting the same department, team, and grade. In the \"Add More Criteria\" section, use the \"Exclude Name\" option to enter the second employee's name and save the policy. Finally, click on the assigned employee count link and ensure that the second employee is not listed.");

		ArrayList<String> data = initBase.loadExcelData("attendance_policy", currTC,
				"password,captcha,leavepolicyname,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,pin,emptwo,sickleave");

		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

		int i =0;
		String password = data.get(i);
		String captcha = data.get(++i);
		String leavepolicyname = data.get(++i) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		
		String mailinator = data.get(++i);
		String empid = data.get(++i) + Pramod.generateRandomAlpha(4);
		

		String empfirstname = data.get(++i) + Pramod.generateRandomAlpha(5);
		String emplastname = data.get(++i) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(++i);
		String empphoneno = data.get(++i) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(++i);
		String reportingto = data.get(++i);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(3) + mailinator;
		
		String enrollmentimage = data.get(++i);
		String pin = data.get(++i) + Pramod.generateRandomNumber(3);
		
		
		String emptwo = data.get(++i) + Pramod.generateRandomAlpha(5);
		String sickleave = data.get(++i);

		String seconduseremail = empemail + Pramod.generateRandomAlpha(4) + mailinator;

		String secondempid = empid + Pramod.generateRandomAlpha(5);

		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}


		// creating new user
		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}



		if (EmployeePage.TotalCountDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On TotalCountDropDown Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TotalCountDropDown: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddNewButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddNewButton Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddNewButton: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeFormDisplayValidated()) {
			logResults.createLogs("Y", "PASS", "Employee Form Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Loading Employee Form: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTypeDropdown(entityname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On EmployeeTypeDropdown Successfully And Selected EntityName Successfully: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTypeDropdown  : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.ReportingToDropdown()) {
			logResults.createLogs("Y", "PASS", "Clicked On ReportingToDropdown Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ReportingToDropdown  : " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(2000);
		if (EmployeePage.ReportingToSearchInput(reportingto)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On ReportingTo Search Text Field And Input Is Passed: " + reportingto);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ReportingTo Search Text Field : " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (EmployeePage.ReportingToSearchResult()) {
			logResults.createLogs("Y", "PASS", "Entity Name Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Entity Name   : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeId(empid)) {
			logResults.createLogs("Y", "PASS", "EmployeeID Entered On Employee ID Text Field: " + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  EmployeeID: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationDropdown(officename)) {
			logResults.createLogs("Y", "PASS", " CompanyLocation Selected From The DropDown : " + officename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Office Name: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeFirstName(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Employee First Name Entered On Employee FirstName Text Field: " + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee First Name: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeLastName(emplastname)) {
			logResults.createLogs("Y", "PASS",
					"Employee Last Name Entered On Employee Last Name Text Field: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Last Name: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeEmailID(email)) {
			logResults.createLogs("Y", "PASS", "Employee Email Entered On Email ID Text Field: " + email);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Email: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeePhoneNo(empphoneno)) {
			logResults.createLogs("Y", "PASS",
					"Employee Phone Number Entered On Phone Number Text Field: " + empphoneno);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Phone Number: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeDateTextField()) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date  Text Field Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Joining Date: " + EmployeePage.getExceptionDesc());
		}
		
		if (EmployeePage.FirstDateSelection(empjoiningdate)) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date Entered On Joining Date Text Field: " + empjoiningdate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Joining Date: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEmployeeSave()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddEmployeeSave Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEmployeeSave: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.GoToDirectory()) {
			logResults.createLogs("Y", "PASS", "Clicked On GoToDirectory Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GoToDirectory: " + EmployeePage.getExceptionDesc());
		}

		Thread.sleep(3000);

//creating new user

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}



		if (EmployeePage.TotalCountDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On TotalCountDropDown Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TotalCountDropDown: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddNewButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddNewButton Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddNewButton: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeFormDisplayValidated()) {
			logResults.createLogs("Y", "PASS", "Employee Form Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Loading Employee Form: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTypeDropdown(entityname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On EmployeeTypeDropdown Successfully And Selected EntityName Successfully: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTypeDropdown  : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.ReportingToDropdown()) {
			logResults.createLogs("Y", "PASS", "Clicked On ReportingToDropdown Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ReportingToDropdown  : " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(2000);
		if (EmployeePage.ReportingToSearchInput(reportingto)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On ReportingTo Search Text Field And Input Is Passed: " + reportingto);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ReportingTo Search Text Field : " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (EmployeePage.ReportingToSearchResult()) {
			logResults.createLogs("Y", "PASS", "Entity Name Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Entity Name   : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeId(secondempid)) {
			logResults.createLogs("Y", "PASS", "EmployeeID Entered On Employee ID Text Field: " + secondempid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  EmployeeID: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationDropdown(officename)) {
			logResults.createLogs("Y", "PASS", " CompanyLocation Selected From The DropDown : " + officename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Office Name: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeFirstName(emptwo)) {
			logResults.createLogs("Y", "PASS",
					"Employee First Name Entered On Employee FirstName Text Field: " + emptwo);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee First Name: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeLastName(emplastname)) {
			logResults.createLogs("Y", "PASS",
					"Employee Last Name Entered On Employee Last Name Text Field: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Last Name: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeEmailID(seconduseremail)) {
			logResults.createLogs("Y", "PASS", "Employee Email Entered On Email ID Text Field: " + seconduseremail);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Email: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeePhoneNo(empphoneno)) {
			logResults.createLogs("Y", "PASS",
					"Employee Phone Number Entered On Phone Number Text Field: " + empphoneno);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering  Employee Phone Number: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeDateTextField()) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date  Text Field Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Joining Date: " + EmployeePage.getExceptionDesc());
		}
		
		if (EmployeePage.FirstDateSelection(empjoiningdate)) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date Entered On Joining Date Text Field: " + empjoiningdate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Joining Date: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEmployeeSave()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddEmployeeSave Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEmployeeSave: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.GoToDirectory()) {
			logResults.createLogs("Y", "PASS", "Clicked On GoToDirectory Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GoToDirectory: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.LasTNameSelected()) {
			logResults.createLogs("Y", "PASS", "Selected  LasTName  Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting LasTName: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(emplastname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee: " + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton : "
					+ DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile : "
					+ DepartmentPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeptDropdown(departmentname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Dept : " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (AttendancePolicyPage.DesignationDropdown(designationname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown: " + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Designation From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (AttendancePolicyPage.GradeDropDown(gradename)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Grade From Dropdown: " + gradename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Grade From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.TeamDropDown(teamname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (RolePermissionpage.PinTextField(pin)) {
			logResults.createLogs("Y", "PASS", "Successfully Enter The Pin: " + pin);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Pin : " + RolePermissionpage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (RolePermissionpage.SaveChanges()) {
			logResults.createLogs("Y", "PASS", "SaveChanges Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SaveChanges Button: " + RolePermissionpage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (RolePermissionpage.DefaultPolicy()) {
			logResults.createLogs("Y", "PASS", "DefaultPolicy Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DefaultPolicy Button: " + RolePermissionpage.getExceptionDesc());
		}

		Thread.sleep(2000);
		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(emptwo)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: " + emptwo);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee: " + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton : "
					+ DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile : "
					+ DepartmentPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeptDropdown(departmentname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Dept : " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (AttendancePolicyPage.DesignationDropdown(designationname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown: " + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Designation From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (AttendancePolicyPage.GradeDropDown(gradename)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Grade From Dropdown: " + gradename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Grade From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.TeamDropDown(teamname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (RolePermissionpage.PinTextField(pin)) {
			logResults.createLogs("Y", "PASS", "Successfully Enter The Pin: " + pin);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Pin : " + RolePermissionpage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (RolePermissionpage.SaveChanges()) {
			logResults.createLogs("Y", "PASS", "SaveChanges Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SaveChanges Button: " + RolePermissionpage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (RolePermissionpage.DefaultPolicy()) {
			logResults.createLogs("Y", "PASS", "DefaultPolicy Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DefaultPolicy Button: " + RolePermissionpage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: "
							+ empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DotsMenu()) {
			logResults.createLogs("Y", "PASS", "Clicked On 3Dots Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On 3Dots: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.WebEnrollment()) {
			logResults.createLogs("Y", "PASS", "Clicked On WebEnrollment Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On WebEnrollment: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.FaceEnrollmentTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On FaceEnrollmentTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FaceEnrollmentTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.ImageUploadButton(enrollmentimage)) {
			logResults.createLogs("Y", "PASS", "Image Selected For WebEnrollment Successfully: " + enrollmentimage);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Image For WebEnrollment: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CropImage()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On CropImage: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On CropImage: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SaveEnrollmentButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SaveEnrollmentButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SaveEnrollmentButton: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SaveEnrollmentSuccessMsg()) {
			logResults.createLogs("Y", "PASS", "Success Msg Is Displayed: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Success Msg: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(emptwo)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: " + emptwo);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DotsMenu()) {
			logResults.createLogs("Y", "PASS", "Clicked On 3Dots Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On 3Dots: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.WebEnrollment()) {
			logResults.createLogs("Y", "PASS", "Clicked On WebEnrollment Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On WebEnrollment: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (EmployeePage.FaceEnrollmentTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On FaceEnrollmentTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FaceEnrollmentTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.ImageUploadButton(enrollmentimage)) {
			logResults.createLogs("Y", "PASS", "Image Selected For WebEnrollment Successfully: " + enrollmentimage);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Image For WebEnrollment: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CropImage()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On CropImage: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On CropImage: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SaveEnrollmentButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SaveEnrollmentButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SaveEnrollmentButton: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SaveEnrollmentSuccessMsg()) {
			logResults.createLogs("Y", "PASS", "Success Msg Is Displayed: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Success Msg: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(4000);
		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon: " + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded: " + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button: "
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeaveTypePage.AddLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddLeavePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddLeavePolicyButton: " + LeaveTypePage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyNameTextField(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "LeavePolicy Name Inputted Successfully: " + leavepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting LeavePolicy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeaveTypePage.SelectLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "SelectLeavePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SelectLeavePolicyButton: " + LeaveTypePage.getExceptionDesc());
		}

		if (LeavePolicyPage.AllLeaves(sickleave)) {

			logResults.createLogs("Y", "PASS", "SickLeave Selected Successfully: " + sickleave);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Sick Leave: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AddLeaveSaveButton()) {

			logResults.createLogs("Y", "PASS", "AddLeaveSaveButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddLeaveSaveButton: " + LeavePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddEmpCriteria()) {

			logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddEmpCriteria Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.OfficeDropDown(officename)) {

			logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully : " + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Office Name : " + LeavePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.DeptDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Dept DropDown: " + AttendancePolicyPage.getExceptionDesc());
		}
		
		
		
		if (LeavePolicyPage.DeptOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting  Dept : " + LeavePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.TeamDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team  Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (WeekOffPolicyPage.TeamOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team From Dropdown : " + WeekOffPolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.DesignationDropdown(designationname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown: " + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Designation From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Employee Type From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftAddEmpCriteriaApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Apply Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Apply Button : " + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.CreateLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreateLeavePolicyButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateLeavePolicyButton: " + LeavePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicySearchTextFields(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Inputted Successfully: " + leavepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLink()) {

			logResults.createLogs("Y", "PASS", "Created Policy Assigned Link Count Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Policy Assigned Emp Count Link: "
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.EmployeeListLoaded()) {

			logResults.createLogs("Y", "PASS", "Employee List Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Employee Records Are Not Loaded: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLinkSearchTextField(empfirstname)) {

			logResults.createLogs("Y", "PASS", "Emp Name Displayed Successfully: " + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Emp Name: " + LeavePolicyPage.getExceptionDesc());
		}

	}

	// MPI_561_Leave_Policy_18
	@Test(enabled = true, priority = 8, groups = { "Smoke" })
	public void MPI_561_Leave_Policy_18() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of the search feature in the Assigned Employees popup window by selecting each of the following search options: First Name, Last Name, Employee ID, Enroll ID, Designation, Department, and Office Name.");

		ArrayList<String> data = initBase.loadExcelData("leave_policy", currTC,
				"password,captcha,defaulttext,assignedemp");

		
		String password = data.get(0);
		String captcha = data.get(1);

		String Defaulttext = data.get(2);
		String assignedemp = data.get(3);

		
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
	

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon: " + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded: " + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button: "
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicySearchTextFields(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Inputted Successfully: " + leavepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchInputtedPolicyvalidation(leavepolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created Leave Policy Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLink()) {

			logResults.createLogs("Y", "PASS", "Created Policy Assigned Link Count Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Policy Assigned Emp Count Link: "
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.EmployeeListLoaded()) {

			logResults.createLogs("Y", "PASS", "Employee List Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Employee Records Are Not Loaded: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLinkSearchTextField(empfirstname)) {

			logResults.createLogs("Y", "PASS", "Emp Name Displayed Successfully: " + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLinkSearchResult(empfirstname)) {
			String empfirstnames = LeavePolicyPage.firstname;

			logResults.createLogs("Y", "PASS",
					"Emp Name Validated And Displayed Successfully: " + empfirstname + "=" + empfirstnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LastNameCheckBox()) {

			logResults.createLogs("Y", "PASS", "LastNameCheckBox Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LastNameCheckBox: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.EmployeeIDCheckBox()) {

			logResults.createLogs("Y", "PASS", "EmployeeIDCheckBox Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmployeeIDCheckBox: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLinkSearchTextField(emplastname)) {

			logResults.createLogs("Y", "PASS", "Emp Name Displayed Successfully: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Emp Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLinkSearchResult(emplastname)) {
			String emplastnames = LeavePolicyPage.firstname;

			logResults.createLogs("Y", "PASS",
					"Emp Name Validated And Displayed Successfully: " + emplastname + "=" + emplastnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Name: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLinkSearchTextField(empid)) {

			logResults.createLogs("Y", "PASS", "Emp Id Displayed Successfully: " + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Id: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.EmpIdValidation(empid)) {
			String empids = LeavePolicyPage.FullEmpID;

			logResults.createLogs("Y", "PASS", "Emp ID Validated And Displayed Successfully: " + empid + "=" + empids);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp ID: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LastNameCheckBox()) {

			logResults.createLogs("Y", "PASS", "LastNameCheckBox Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LastNameCheckBox: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.EmployeeIDCheckBox()) {

			logResults.createLogs("Y", "PASS", "EmployeeIDCheckBox Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmployeeIDCheckBox: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.DesignationCheckBox()) {

			logResults.createLogs("Y", "PASS", "DesignationCheckBox Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DesignationCheckBox: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.DepartmentCheckBox()) {

			logResults.createLogs("Y", "PASS", "DepartmentCheckBox Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DepartmentCheckBox: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLinkSearchTextField(designationname)) {

			logResults.createLogs("Y", "PASS", "Emp Designation Displayed Successfully: " + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Designation: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.DesignationValidation(designationname)) {
			String DesignationName = LeavePolicyPage.Designationname;

			logResults.createLogs("Y", "PASS",
					"Emp Designation Validated And Displayed Successfully: " + designationname + "=" + DesignationName);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Designation: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.AssignedEmpCountLinkSearchTextField(departmentname)) {

			logResults.createLogs("Y", "PASS", "Emp Department Displayed Successfully: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Department: " + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.DepartmentValidation(departmentname)) {
			String DepartmentNames = LeavePolicyPage.DepartmentName;

			logResults.createLogs("Y", "PASS",
					"Emp Department Validated And Displayed Successfully: " + departmentname + "=" + DepartmentNames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Department: " + LeavePolicyPage.getExceptionDesc());
		}

	}
	
	
	// MPI_847_Leave_Policy_37
		@Test(enabled = true, priority = 10, groups = { "Smoke", "AddMaster" })
		public void MPI_847_Leave_Policy_37() throws InterruptedException {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, create an policy and add the employee criteria to the policy and ensure only matching criteria employees are assigned to that created policy");

			ArrayList<String> data = initBase.loadExcelData("leave_policy", currTC,
					"password,captcha,leavepolicyname,sickleave,travelleave,casualleave");

			MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

			int i=0;
			
			String password = data.get(i);
			String captcha = data.get(++i);
			

			leavepolicyname = data.get(++i) + initBase.executionRunTime;
			
			
			String sickleave = data.get(++i);
			String travelleave = data.get(++i);
			String casualleave = data.get(++i);
		
			MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
			MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
			MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);

	MeghLoginTest meghlogintest = new MeghLoginTest();
	
	
	if (initBase.stopNewData) { // Tapan 5 July 25
		String LeavePolicyName = Utils.propsReadWrite("data/addmaster.properties", "get", "LeavePolicyName", "");
		if (LeavePolicyName.equals("pass")) {
			logResults.createLogs("Y", "INFO", "Skip Adding New Data. Continue with Old Test Data.");
			throw new SkipException("Skip Adding New Data. Continue with Old Test Data.");
		}
	}
			
			if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
				logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
			}
	
			if (RolePermissionpage.PolicyIcon()) {

				logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On PolicyIcon: " + RolePermissionpage.getExceptionDesc());
			}

			if (ShiftPolicyPage.ShiftMasterLoaded()) {

				logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded: " + ShiftPolicyPage.getExceptionDesc());
			}

			if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

				logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On LeaveDropDownFromSideBar Button: " + LeavePolicyPage.getExceptionDesc());
			}

			if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

				logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button: "
						+ LeavePolicyPage.getExceptionDesc());
			}

			if (LeavePolicyPage.LeavePolicyPageLoaded()) {

				logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Loading LeavePolicyPage: " + LeavePolicyPage.getExceptionDesc());
			}

			if (LeaveTypePage.AddLeavePolicyButton()) {

				logResults.createLogs("Y", "PASS", "AddLeavePolicyButton Clicked Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddLeavePolicyButton: " + LeaveTypePage.getExceptionDesc());
			}

			if (LeavePolicyPage.LeavePolicyNameTextField(leavepolicyname)) {

				logResults.createLogs("Y", "PASS", "LeavePolicy Name Inputted Successfully: " + leavepolicyname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting LeavePolicy Name: " + LeavePolicyPage.getExceptionDesc());
			}

			if (LeaveTypePage.SelectLeavePolicyButton()) {

				logResults.createLogs("Y", "PASS", "SelectLeavePolicyButton Clicked Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On SelectLeavePolicyButton: " + LeaveTypePage.getExceptionDesc());
			}

			if (LeavePolicyPage.AllLeaves(sickleave)) {

				logResults.createLogs("Y", "PASS", "SickLeave Selected Successfully: " + sickleave);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Sick Leave: " + LeavePolicyPage.getExceptionDesc());
			}
			

			if (LeavePolicyPage.AllLeaves2(travelleave)) {

				logResults.createLogs("Y", "PASS", "Travel Leave Selected Successfully: " + travelleave);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Travel Leave: " + LeavePolicyPage.getExceptionDesc());
			}

			if (LeavePolicyPage.AllLeaves3(casualleave)) {

				logResults.createLogs("Y", "PASS", "Casual Leave Selected Successfully: " + casualleave);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Casual Leave: " + LeavePolicyPage.getExceptionDesc());
			}

			if (LeavePolicyPage.AddLeaveSaveButton()) {

				logResults.createLogs("Y", "PASS", "AddLeaveSaveButton Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddLeaveSaveButton: " + LeavePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.AddEmpCriteria()) {

				logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddEmpCriteria Button : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (LeavePolicyPage.OfficeDropDown(officename)) {

				logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully : " + officename);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Office Name : " + LeavePolicyPage.getExceptionDesc());
			}

			Thread.sleep(2000);

			if (AttendancePolicyPage.DeptDropDownClick()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown: " + departmentname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Dept DropDown: " + AttendancePolicyPage.getExceptionDesc());
			}
			
			
			
			if (LeavePolicyPage.DeptOptionSelected()) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Dept: " + departmentname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting  Dept : " + LeavePolicyPage.getExceptionDesc());
			}
			
			if (AttendancePolicyPage.TeamDropDownClick()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown: " + teamname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Team  Dropdown : " + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (WeekOffPolicyPage.TeamOptionSelected()) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown: " + teamname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Team From Dropdown : " + WeekOffPolicyPage.getExceptionDesc());
			}

			Thread.sleep(2000);

			if (AttendancePolicyPage.DesignationDropdown(designationname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown: " + designationname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Designation From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown: " + entityname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Employee Type From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (ShiftPolicyPage.ShiftAddEmpCriteriaApplyButton()) {

				logResults.createLogs("Y", "PASS", "Clicked On Apply Button Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Apply Button : " + ShiftPolicyPage.getExceptionDesc());
			}

			if (LeavePolicyPage.CreateLeavePolicyButton()) {

				logResults.createLogs("Y", "PASS", "CreateLeavePolicyButton Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CreateLeavePolicyButton: " + LeavePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.YesButton()) {
				Utils.propsReadWrite("data/addmaster.properties", "set", "LeavePolicyName", "pass");
				
				logResults.createLogs("Y", "PASS", "Yes Button Button Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Yes Button : " + AttendancePolicyPage.getExceptionDesc());
			}	
			
	
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@AfterMethod(alwaysRun = true)
	void Aftermethod() {
		logResults.onlyLog();
		if (driver != null) {
			driver.quit();
		}
	}

	@AfterClass(alwaysRun = true)
	void cleanUp() {
		if (driver != null) {
			driver.quit();
		}
	}

}
