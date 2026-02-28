package com.MeghPI.Attendance.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.pages.MeghAttendancePolicyPage;
import com.MeghPI.Attendance.pages.MeghLeavePolicyPage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterEntityTypePage;
import com.MeghPI.Attendance.pages.MeghMasterLeaveTypePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class MeghMasterLeaveTypeTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	public String leavename = "";

	private String cmpcode = "";
	private String Emailid = "";

	@Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
		//driver = loadDriver.getDriver(device, hubnodeip);

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
		
	}

	// MPI_343_LeaveType_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_343_LeaveType_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this.Add a leave type and ensure the created leave type is displayed in the Leave Type dropdown on the Apply Leave Request form after adding this leave type in default leave policy. ");

		ArrayList<String> data = initBase.loadExcelData("company_leavetype", currTC,
				"password,captcha,leavename,leavecode,leavedescription,policyname");

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
	
		leavename = data.get(2) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);

		String leavecode = data.get(3) + Pramod.generateRandomAlpha(3);
		String leavedescription = data.get(4) + initBase.executionRunTime;
		String defaulttext = data.get(5);
		String policynames = "";

		MeghLoginPage MeghloginPage = new MeghLoginPage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghloginPage.getExceptionDesc());
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

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab ." + EmployeePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypeButton()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "LeaveType Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Leave Type Page Is Not Completely ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.AddLeaveTypeButton()) {
			logResults.createLogs("Y", "PASS", "AddLeaveTypeButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddLeaveTypeButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveNameTextField(leavename)) {
			logResults.createLogs("Y", "PASS", "Leave Type Name Inputted Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveCodeTextField(leavecode)) {
			logResults.createLogs("Y", "PASS", "Leave Type Code Inputted Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Code ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveDescriptionTextField(leavedescription)) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.PaidRadioButton()) {
			logResults.createLogs("Y", "PASS", "Leave Type Description Inputted  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Description ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.MaleRadioButton()) {
			logResults.createLogs("Y", "PASS", "Male Radio Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Male Radio Button ." + LeaveTypePage.getExceptionDesc());
		}
		if (LeaveTypePage.AddLeaveTypeSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddLeaveTypeSaveButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddLeaveTypeSaveButton ." + LeaveTypePage.getExceptionDesc());
		}
		

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveButtonFromRightSideBar()) {

			logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeavePolicy." + LeaveTypePage.getExceptionDesc());
		}

		if (RolePermissionpage.LeavePolicy()) {

			logResults.createLogs("Y", "PASS", "LeavePolicy Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeavePolicy." + RolePermissionpage.getExceptionDesc());
		}
		
		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SelectPagination()) {

			logResults.createLogs("Y", "PASS", "LeavePolicy Pagination 100 Per Page Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting LeavePolicy Page Pagination." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.ContainsDefaultText(defaulttext)) {
			policynames = LeavePolicyPage.actualPolicyName;

			logResults.createLogs("Y", "PASS", "LeavePolicy Extracted Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Extracting LeavePolicy." + LeavePolicyPage.getExceptionDesc());
		}
		

		if (LeavePolicyPage.LeavePolicySearchTextField(policynames)) {

			logResults.createLogs("Y", "PASS", "Default LeavePolicy Inputted Successfully." + policynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting LeavePolicy." + LeavePolicyPage.getExceptionDesc());
		}
		

		if (LeavePolicyPage.EditButton()) {

			logResults.createLogs("Y", "PASS", "Default LeavePolicy Edit Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Edit Button Of LeavePolicy." + LeavePolicyPage.getExceptionDesc());
		}


		if (LeaveTypePage.SelectLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "SelectLeavePolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SelectLeavePolicyButton." + LeaveTypePage.getExceptionDesc());
		}

		

		if (LeavePolicyPage.ClickMatchingValue(leavename)) {

			logResults.createLogs("Y", "PASS", "Created Leave Added To Policy Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Created Leave To The Policy." + LeavePolicyPage.getExceptionDesc());
		}
		

		if (LeavePolicyPage.AddLeaveButton()) {

			logResults.createLogs("Y", "PASS", "AddLeave Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddLeave Button." + LeavePolicyPage.getExceptionDesc());
		}

		

		if (LeavePolicyPage.SavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "SavePolicy Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SavePolicy Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "YesButton Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On YesButton ." + AttendancePolicyPage.getExceptionDesc());
		}

		

		if (RolePermissionpage.LeaveButton()) {

			logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.LeaveRequestsTab()) {

			logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
					+ RolePermissionpage.getExceptionDesc());
		}
		if (LeaveTypePage.ApplyLeaveButton()) {
			logResults.createLogs("Y", "PASS", "ApplyLeaveButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ApplyLeaveButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveForMeButton()) {
			logResults.createLogs("Y", "PASS", "LeaveForMeButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveForMeButton ." + LeaveTypePage.getExceptionDesc());
		}
		

		if (LeaveTypePage.LeaveTypeDropDown()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeDropDown  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeDropDown ." + LeaveTypePage.getExceptionDesc());
		}

		

		if (LeaveTypePage.LeaveTypeDropDownSearchField(leavename)) {
			logResults.createLogs("Y", "PASS", "Created Leave Type Inputted Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypeDropDownSearchResults(leavename)) {
			logResults.createLogs("Y", "PASS", "LeaveType Search Result Displayed Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Leave Type ." + LeaveTypePage.getExceptionDesc());
		}

	}

	// MPI_344_LeaveType_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_344_LeaveType_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this:Â Edit the leave type and ensure the changes are updated successfully. Validate the update using the search feature");

		ArrayList<String> data = initBase.loadExcelData("company_leavetype", currTC,
				"leavename,leavecode,leavedescription");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);

	
		String leavenames = data.get(0) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);

		String leavecode = data.get(1) + Pramod.generateRandomAlpha(3);
		String leavedescription = data.get(2) + initBase.executionRunTime;

		String leavenamesss = leavenames + Pramod.generateRandomAlpha(3);

		String firstrowleave = "";


 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


		if (meghloginpage.MainLandingPage()) {
			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
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

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab ." + EmployeePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypeButton()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "LeaveType Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Leave Type Page Is Not Completely ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.AddLeaveTypeButton()) {
			logResults.createLogs("Y", "PASS", "AddLeaveTypeButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddLeaveTypeButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveNameTextField(leavenames)) {
			logResults.createLogs("Y", "PASS", "Leave Type Name Inputted Successfully." + leavenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveCodeTextField(leavecode)) {
			logResults.createLogs("Y", "PASS", "Leave Type Code Inputted Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Code ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveDescriptionTextField(leavedescription)) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.PaidRadioButton()) {
			logResults.createLogs("Y", "PASS", "Leave Type Description Inputted  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Description ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.MaleRadioButton()) {
			logResults.createLogs("Y", "PASS", "Male Radio Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Male Radio Button ." + LeaveTypePage.getExceptionDesc());
		}
		if (LeaveTypePage.AddLeaveTypeSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddLeaveTypeSaveButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddLeaveTypeSaveButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypeSearchField(leavenames)) {
			logResults.createLogs("Y", "PASS", "Updated Leave Type Name Inputted Successfully." + leavenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name ." + LeaveTypePage.getExceptionDesc());
		}

		

		if (LeaveTypePage.LeaveType3Dots()) {
			logResults.createLogs("Y", "PASS", "LeaveType3Dots  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveType3Dots ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypeEditButton()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeEditButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeEditButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveNameTextField(leavenamesss)) {
			logResults.createLogs("Y", "PASS", "Leave Type Name Inputted Successfully." + leavenamesss);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.PaidRadioButton()) {
			logResults.createLogs("Y", "PASS", "Leave Type Description Inputted  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Description ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.MaleRadioButton()) {
			logResults.createLogs("Y", "PASS", "Male Radio Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Male Radio Button ." + LeaveTypePage.getExceptionDesc());
		}
		if (LeaveTypePage.AddLeaveTypeSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddLeaveTypeSaveButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddLeaveTypeSaveButton ." + LeaveTypePage.getExceptionDesc());
		}
		

		if (LeaveTypePage.LeaveTypeSearchField(leavenamesss)) {
			logResults.createLogs("Y", "PASS", "Updated Leave Type Name Inputted Successfully." + leavenamesss);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name ." + LeaveTypePage.getExceptionDesc());
		}

		
		if (LeaveTypePage.LeaveTypeFirstRow()) {
			firstrowleave = LeaveTypePage.firstrowleavename;
			logResults.createLogs("Y", "PASS",
					"Updated Leave Type Name Inputted Successfully." + leavenamesss + "=" + firstrowleave);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name ." + LeaveTypePage.getExceptionDesc());
		}

	}

	// MPI_345_LeaveType_03
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_345_LeaveType_03()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a duplicate leave type and ensure the proper error message is displayed.");

		ArrayList<String> data = initBase.loadExcelData("company_leavetype", currTC,
				"leavecode,leavedescription");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);

	

		String leavecode = data.get(0) + Pramod.generateRandomAlpha(3);
		String leavedescription = data.get(1) + initBase.executionRunTime;
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


		if (meghloginpage.MainLandingPage()) {
			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
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

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab ." + EmployeePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypeButton()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "LeaveType Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Leave Type Page Is Not Completely ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.AddLeaveTypeButton()) {
			logResults.createLogs("Y", "PASS", "AddLeaveTypeButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddLeaveTypeButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveNameTextField(leavename)) {
			logResults.createLogs("Y", "PASS", "Leave Type Name Inputted Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveCodeTextField(leavecode)) {
			logResults.createLogs("Y", "PASS", "Leave Type Code Inputted Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Code ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveDescriptionTextField(leavedescription)) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.PaidRadioButton()) {
			logResults.createLogs("Y", "PASS", "Leave Type Description Inputted  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Description ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.MaleRadioButton()) {
			logResults.createLogs("Y", "PASS", "Male Radio Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Male Radio Button ." + LeaveTypePage.getExceptionDesc());
		}
		if (LeaveTypePage.AddLeaveTypeSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddLeaveTypeSaveButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddLeaveTypeSaveButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (OfficePage.DuplicateErrorMessage()) {
			logResults.createLogs("Y", "PASS", " Successfully Displayed Error Message .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message ." + OfficePage.getExceptionDesc());
		}

	}

	// MPI_352_LeaveType_10
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_352_LeaveType_10()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of search feature by selecting code as search option");

	//	ArrayList<String> data = initBase.loadExcelData("company_leavetype", currTC, "password,captcha");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);

		
		String firstrowleavecode = "";

		MeghMasterEntityTypePage EntityTypePage = new MeghMasterEntityTypePage(driver);

		 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


		if (meghloginpage.MainLandingPage()) {
			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
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

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab ." + EmployeePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypeButton()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "LeaveType Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Leave Type Page Is Not Completely ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveCodeRow()) {
			firstrowleavecode = LeaveTypePage.firstrowleaveCode;
			logResults.createLogs("Y", "PASS", "First Row Leave Code Extracted Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Fetching First Row Leave Code ." + LeaveTypePage.getExceptionDesc());
		}

		if (EntityTypePage.EntitySearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + EntityTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveCodeCheckBox()) {
			logResults.createLogs("Y", "PASS", "LeaveCodeCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveCodeCheckBox ." + LeaveTypePage.getExceptionDesc());
		}

		if (EntityTypePage.EntitySearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + EntityTypePage.getExceptionDesc());
		}

		
		if (LeaveTypePage.LeaveTypeSearchField(firstrowleavecode)) {
			logResults.createLogs("Y", "PASS", " Leave Code Name Inputted Successfully." + firstrowleavecode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Code Name ." + LeaveTypePage.getExceptionDesc());
		}

		

		if (LeaveTypePage.LeaveCodeRow()) {

			logResults.createLogs("Y", "PASS", "First Row Leave Code Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying First Row Leave Code ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.Comparison(firstrowleavecode)) {

			logResults.createLogs("Y", "PASS", "Inputted Leave Code Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Searched Leave Code ." + LeaveTypePage.getExceptionDesc());
		}

	}

	// MPI_354_LeaveType_12
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_354_LeaveType_12()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, remove the leave type from leave policy and ensure that removed leave type is not displaying in the apply leave request leave type dropdown.");

		ArrayList<String> data = initBase.loadExcelData("company_leavetype", currTC,
				"policyname");

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);

		String defaulttext = data.get(0);

		String policynames = "";
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


		if (meghloginpage.MainLandingPage()) {
			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
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

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab ." + EmployeePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypeButton()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeButton ." + LeaveTypePage.getExceptionDesc());
		}
		if (LeaveTypePage.LeaveTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "LeaveType Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Leave Type Page Is Not Completely ." + LeaveTypePage.getExceptionDesc());
		}

		

		if (LeaveTypePage.LeaveTypeSearchField(leavename)) {
			logResults.createLogs("Y", "PASS", " Leave  Name Inputted Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave  Name ." + LeaveTypePage.getExceptionDesc());
		}

		

		if (LeaveTypePage.LeaveTypeToggleSwitch()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeToggleSwitch  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeToggleSwitch ." + LeaveTypePage.getExceptionDesc());
		}

		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button ." + OfficePage.getExceptionDesc());
		}

		if (LeaveTypePage.ToastMessage()) {
			logResults.createLogs("Y", "PASS", "ToastMessage Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message ." + LeaveTypePage.getExceptionDesc());
		}

		

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveButtonFromRightSideBar()) {

			logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeavePolicy." + LeaveTypePage.getExceptionDesc());
		}

		if (RolePermissionpage.LeavePolicy()) {

			logResults.createLogs("Y", "PASS", "LeavePolicy Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeavePolicy." + RolePermissionpage.getExceptionDesc());
		}

		if (LeavePolicyPage.SelectPagination()) {

			logResults.createLogs("Y", "PASS", "LeavePolicy Pagination 100 Per Page Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting LeavePolicy Page Pagination." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.ContainsDefaultText(defaulttext)) {
			policynames = LeavePolicyPage.actualPolicyName;

			logResults.createLogs("Y", "PASS", "LeavePolicy Extracted Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Extracting LeavePolicy." + LeavePolicyPage.getExceptionDesc());
		}
		

		if (LeavePolicyPage.LeavePolicySearchTextField(policynames)) {

			logResults.createLogs("Y", "PASS", "Default LeavePolicy Inputted Successfully." + policynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting LeavePolicy." + LeavePolicyPage.getExceptionDesc());
		}
		

		if (LeavePolicyPage.EditButton()) {

			logResults.createLogs("Y", "PASS", "Default LeavePolicy Edit Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Edit Button Of LeavePolicy." + LeavePolicyPage.getExceptionDesc());
		}

		

		if (LeavePolicyPage.LeaveTypeRows(leavename)) {

			logResults.createLogs("Y", "PASS", "Default LeavePolicy Edit Button Clicked Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Edit Button Of LeavePolicy." + LeavePolicyPage.getExceptionDesc());
		}

		

		if (LeavePolicyPage.DeleteConfirm()) {

			logResults.createLogs("Y", "PASS", "DeleteConfirm Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteConfirm." + LeavePolicyPage.getExceptionDesc());
		}

		

		if (LeavePolicyPage.SavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "SavePolicy Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SavePolicy Button." + LeavePolicyPage.getExceptionDesc());
		}
		

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "YesButton Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On YesButton ." + AttendancePolicyPage.getExceptionDesc());
		}

		

		if (RolePermissionpage.LeaveButton()) {

			logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.LeaveRequestsTab()) {

			logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
					+ RolePermissionpage.getExceptionDesc());
		}
		

		if (LeaveTypePage.ApplyLeaveButton()) {
			logResults.createLogs("Y", "PASS", "ApplyLeaveButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ApplyLeaveButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveForMeButton()) {
			logResults.createLogs("Y", "PASS", "LeaveForMeButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveForMeButton ." + LeaveTypePage.getExceptionDesc());
		}


		if (LeaveTypePage.LeaveTypeDropDown()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeDropDown  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeDropDown ." + LeaveTypePage.getExceptionDesc());
		}
		

		if (LeaveTypePage.LeaveTypeDropDownSearchField(leavename)) {
			logResults.createLogs("Y", "PASS", "Disabled Leave Type Inputted Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name ." + LeaveTypePage.getExceptionDesc());
		}
		

		if (DepartmentPage.DropDownErrorMsg()) {
			logResults.createLogs("Y", "PASS", "HODDropDownErrorMsg Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message." + DepartmentPage.getExceptionDesc());
		}

	}

	// MPI_355_LeaveType_13
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_355_LeaveType_13()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this.Add the leave type that was previously removed in the Leave Policy. After adding the leave type, navigate to the Leave module â†’ Leave Request form â†’ click on the Leave Type dropdown, and ensure the leave type is displayed.");

		ArrayList<String> data = initBase.loadExcelData("company_leavetype", currTC,
				"policyname");

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);

		String defaulttext = data.get(0);

		
		String policynames = "";

		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
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
		

		if (LeaveTypePage.LeaveButtonFromRightSideBar()) {

			logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeavePolicy." + LeaveTypePage.getExceptionDesc());
		}
		

		if (RolePermissionpage.LeavePolicy()) {

			logResults.createLogs("Y", "PASS", "LeavePolicy Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeavePolicy." + RolePermissionpage.getExceptionDesc());
		}

		if (LeavePolicyPage.SelectPagination()) {

			logResults.createLogs("Y", "PASS", "LeavePolicy Pagination 100 Per Page Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting LeavePolicy Page Pagination." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.ContainsDefaultText(defaulttext)) {
			policynames = LeavePolicyPage.actualPolicyName;

			logResults.createLogs("Y", "PASS", "LeavePolicy Extracted Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Extracting LeavePolicy." + LeavePolicyPage.getExceptionDesc());
		}
		

		if (LeavePolicyPage.LeavePolicySearchTextField(policynames)) {

			logResults.createLogs("Y", "PASS", "Default LeavePolicy Inputted Successfully." + policynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting LeavePolicy." + LeavePolicyPage.getExceptionDesc());
		}
		

		if (LeavePolicyPage.EditButton()) {

			logResults.createLogs("Y", "PASS", "Default LeavePolicy Edit Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Edit Button Of LeavePolicy." + LeavePolicyPage.getExceptionDesc());
		}

		

		if (LeaveTypePage.SelectLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "SelectLeavePolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SelectLeavePolicyButton." + LeaveTypePage.getExceptionDesc());
		}

	

		if (LeavePolicyPage.ClickMatchingValue(leavename)) {

			logResults.createLogs("Y", "PASS", "Created Leave Added To Policy Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Created Leave To The Policy." + LeavePolicyPage.getExceptionDesc());
		}
		

		if (LeavePolicyPage.AddLeaveButton()) {

			logResults.createLogs("Y", "PASS", "AddLeave Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddLeave Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "SavePolicy Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SavePolicy Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "YesButton Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On YesButton ." + AttendancePolicyPage.getExceptionDesc());
		}

		

		if (RolePermissionpage.LeaveButton()) {

			logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.LeaveRequestsTab()) {

			logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
					+ RolePermissionpage.getExceptionDesc());
		}
		

		if (LeaveTypePage.ApplyLeaveButton()) {
			logResults.createLogs("Y", "PASS", "ApplyLeaveButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ApplyLeaveButton ." + LeaveTypePage.getExceptionDesc());
		}
	

		if (LeaveTypePage.LeaveForMeButton()) {
			logResults.createLogs("Y", "PASS", "LeaveForMeButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveForMeButton ." + LeaveTypePage.getExceptionDesc());
		}
	

		if (LeaveTypePage.LeaveTypeDropDown()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeDropDown  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeDropDown ." + LeaveTypePage.getExceptionDesc());
		}
		

		if (LeaveTypePage.LeaveTypeDropDownSearchField(leavename)) {
			logResults.createLogs("Y", "PASS", "Created Leave Type Inputted Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Leave Type Name ." + LeaveTypePage.getExceptionDesc());
		}
		

		if (LeaveTypePage.LeaveTypeDropDownSearchResults(leavename)) {
			logResults.createLogs("Y", "PASS", "LeaveType Search Result Displayed Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Leave Type ." + LeaveTypePage.getExceptionDesc());
		}

	}

	// MPI_356_LeaveType_14
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_356_LeaveType_14()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this:Â Create a new leave type and ensure the newly created leave type is displayed in theÂ \"Select Leave Type\"Â dropdown within theÂ \"Create New Leave Policy\"Â form.");

	//	ArrayList<String> data = initBase.loadExcelData("company_leavetype", currTC,"password,captcha");

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);



		 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


		if (meghloginpage.MainLandingPage()) {
			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
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

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab ." + EmployeePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypeButton()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeButton ." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "LeaveType Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Leave Type Page Is Not Completely ." + LeaveTypePage.getExceptionDesc());
		}

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (LeaveTypePage.LeaveButtonFromRightSideBar()) {

			logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeavePolicy." + LeaveTypePage.getExceptionDesc());
		}

		if (RolePermissionpage.LeavePolicy()) {

			logResults.createLogs("Y", "PASS", "LeavePolicy Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeavePolicy." + RolePermissionpage.getExceptionDesc());
		}

		if (LeaveTypePage.AddLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddLeavePolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddLeavePolicyButton." + LeaveTypePage.getExceptionDesc());
		}

		if (LeaveTypePage.SelectLeavePolicyButton()) {

			logResults.createLogs("Y", "PASS", "SelectLeavePolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SelectLeavePolicyButton." + LeaveTypePage.getExceptionDesc());
		}
		if (LeaveTypePage.Comparision()) {

			logResults.createLogs("Y", "PASS", "All The Created Leave Types Comparision Done Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Performing Comparision." + LeaveTypePage.getExceptionDesc());
		}


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
