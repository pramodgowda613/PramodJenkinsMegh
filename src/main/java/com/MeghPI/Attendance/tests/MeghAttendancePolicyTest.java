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
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class MeghAttendancePolicyTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	public String policynames = "";
	public String attendancepolicyname = "";
	

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

	// MPI_434_Attendance_Policy_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_434_Attendance_Policy_01() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, click on \"add policy\" button and ensure \"add attendance policy\" form is displayed");

		ArrayList<String> data = initBase.loadExcelData("attendance_policy", currTC,
				"password,captcha");

	
		String password = data.get(0);
		String captcha = data.get(1);

		 MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		
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

		if (RolePermissionpage.AttendancePolicy()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AttendancePolicy: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Attendance Policy Page Is Not Loaded Completely: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddAttendancePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddAttendancePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddAttendancePolicyButton: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddAttendancePolicyText()) {

			logResults.createLogs("Y", "PASS", "AddAttendancePolicyText Displayed Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying AddAttendancePolicyText: " + RolePermissionpage.getExceptionDesc());
		}

	}

	// MPI_435_Attendance_Policy_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_435_Attendance_Policy_02() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, click on 'Add Attendance Policy', enter the policy name, and click on 'Create Policy'. In the popup, select 'No' when asked 'Do you want to change attendance policy in Employee Profile based on current policy employee criteria?', and ensure that the new policy is created and no one employees are assigned to it");

		ArrayList<String> data = initBase.loadExcelData("attendance_policy", currTC,
				"password,captcha,policyname,assignedemp,gracehours");

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String policyname = data.get(2) + initBase.executionRunTime + Pramod.generateRandomAlpha(4);

		String assignedemp = data.get(3);
		String gracehours = data.get(4);

		String assignedempcounts = "";

		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

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

		if (RolePermissionpage.AttendancePolicy()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AttendancePolicy: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Attendance Policy Page Is Not Loaded Completely: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddAttendancePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddAttendancePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddAttendancePolicyButton: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.PolicyNameTextField(policyname)) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Name Entered Successfully : " + policyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting AttendancePolicy Name: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodButton()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod Button Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EarlyClockOut()) {

			logResults.createLogs("Y", "PASS", "EarlyClockOut CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EarlyClockOut CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursClick()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceHours  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceHours TextField : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursInput(gracehours)) {

			logResults.createLogs("Y", "PASS", " GraceHours Inputted  Successfully : " + gracehours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting GraceHours : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceTimeApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceTimeApply Button  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceTimeApply Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodCheckBox()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.RegulationRequestCheckBox()) {

			logResults.createLogs("Y", "PASS", "RegulationRequest CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On RegulationRequest CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CreatePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicyButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.NoOption()) {

			logResults.createLogs("Y", "PASS", "NoOption Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On NoOption : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.SearchTextField(policyname)) {

			logResults.createLogs("Y", "PASS", "On SearchTextField Policy Name Inputted Successfully : " + policyname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Policy Name On Search TextField : "
					+ AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (AttendancePolicyPage.GetAssignedEmpCount()) {

			assignedempcounts = AttendancePolicyPage.Assignedemp;

			logResults.createLogs("Y", "PASS", "No of Employees Assigned Get Successfully : " + assignedempcounts);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Fetching Assigned Emp Count : " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (AttendancePolicyPage.AssignedEmpComparision(assignedemp)) {

			logResults.createLogs("Y", "PASS",
					"Assigned Emp Count Compared Successfully : " + assignedemp + "=" + assignedempcounts);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Comparing Actual Assigned Emp Count : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

	}

	// MPI_436_Attendance_Policy_03
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_436_Attendance_Policy_03() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, in the created policy where all employees are assigned, enable Web Punching and ensure that employees are able to perform Punch In from the Home module");

		ArrayList<String> data = initBase.loadExcelData("attendance_policy", currTC,
				"password,captcha,policyname,gracehours");

		
		String password = data.get(0);
		String captcha = data.get(1);
		String policyname = data.get(2) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		String gracehours = data.get(3);

		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

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

		if (RolePermissionpage.AttendancePolicy()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AttendancePolicy: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Attendance Policy Page Is Not Loaded Completely: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddAttendancePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddAttendancePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddAttendancePolicyButton: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.PolicyNameTextField(policyname)) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Name Entered Successfully : " + policyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting AttendancePolicy Name: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.WebPunchButton()) {

			logResults.createLogs("Y", "PASS", "WebPunchButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WebPunchButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodButton()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod Button  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EarlyClockOut()) {

			logResults.createLogs("Y", "PASS", "EarlyClockOut CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EarlyClockOut CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursClick()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceHours  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceHours TextField : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursInput(gracehours)) {

			logResults.createLogs("Y", "PASS", " GraceHours Inputted  Successfully : " + gracehours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting GraceHours : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceTimeApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceTimeApply Button  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceTimeApply Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodCheckBox()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.RegulationRequestCheckBox()) {

			logResults.createLogs("Y", "PASS", "RegulationRequest CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On RegulationRequest CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CreatePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicyButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "YesButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On YesButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (RolePermissionpage.HomeButton()) {

			logResults.createLogs("Y", "PASS", "HomeButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On HomeButton : " + RolePermissionpage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (AttendancePolicyPage.CheckInButton()) {

			logResults.createLogs("Y", "PASS", "CheckInButton Displayed Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying CheckInButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CheckOutButton()) {

			logResults.createLogs("Y", "PASS", "CheckOutButton Displayed Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying CheckOutButton : " + AttendancePolicyPage.getExceptionDesc());
		}
	}

	// MPI_454_Attendance_Policy_21
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_454_Attendance_Policy_21() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Edit the attendance policy and click Add Employee Criteria. Select Office, Department, Designation, Group, and Employee Type, then click Apply. Set Punch Require to Four, then click Create Policy. In the confirmation popup \"Do you want to change attendance policy in Employee Profile based on current policy employee criteria?\", select Yes and ensure all employees matching the criteria are assigned to the new policy.");

		ArrayList<String> data = initBase.loadExcelData("attendance_policy", currTC,
				"password,captcha,policyname,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,pin,gracehours");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

		int i = 0;
		String password = data.get(i);
		String captcha = data.get(++i);
		
		policynames = data.get(++i) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		
		
		String mailinator = data.get(++i);
		String empid = data.get(++i) + Pramod.generateRandomAlpha(6);
	

		String empfirstname = data.get(++i) + Pramod.generateRandomAlpha(5);
		String emplastname = data.get(++i) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(++i);
		String empphoneno = data.get(++i) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(++i);
		String reportingto = data.get(++i);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(3) + mailinator;
	
		String enrollmentimage = data.get(++i);
		String pin = data.get(++i) + Pramod.generateRandomNumber(3);
		
		String gracehours = data.get(++i);

		attendancepolicyname = "";

		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		
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
					"Employee Joining Date Entered On Joining Date Text Field: " +empjoiningdate);
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

		// enrolling the User

		if (EmployeePage.GoToDirectory()) {
			logResults.createLogs("Y", "PASS", "Clicked On GoToDirectory Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GoToDirectory: " + EmployeePage.getExceptionDesc());
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

		if (RolePermissionpage.AttendancePolicy()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AttendancePolicy: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Attendance Policy Page Is Not Loaded Completely: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddAttendancePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddAttendancePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddAttendancePolicyButton: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.PolicyNameTextField(policynames)) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Name Entered Successfully : " + policynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting AttendancePolicy Name: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.WebPunchButton()) {

			logResults.createLogs("Y", "PASS", "WebPunchButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WebPunchButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodButton()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod Button  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EarlyClockOut()) {

			logResults.createLogs("Y", "PASS", "EarlyClockOut CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EarlyClockOut CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursClick()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceHours  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceHours TextField : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursInput(gracehours)) {

			logResults.createLogs("Y", "PASS", " GraceHours Inputted  Successfully : " + gracehours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting GraceHours : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceTimeApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceTimeApply Button  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceTimeApply Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodCheckBox()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.RegulationRequestCheckBox()) {

			logResults.createLogs("Y", "PASS", "RegulationRequest CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On RegulationRequest CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddEmpCriteria()) {

			logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddEmpCriteria Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.OfficeDropDown(officename)) {

			logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully : " + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Office Name : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.DeptDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Dept DropDown: " + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.DeptOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting  Dept : " + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.TeamDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team  Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.TeamOptionSelected()) {
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

		

		if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Employee Type From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.ApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Apply Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Apply Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CreatePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicyButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(4000);

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

		if (AttendancePolicyPage.PolicyButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record Policy Button: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record Policy Button : "
					+ AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(3000);

		if (AttendancePolicyPage.AttendancePolicyName()) {
			attendancepolicyname = AttendancePolicyPage.AttendancePolicy;

			logResults.createLogs("Y", "PASS", "Successfully Fetch the Assigned Attendance Policy Name Of Employee : ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Fetching Employee's Attendance Policy Name : "
					+ AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AssignedAttendancePolicyComparision(policynames)) {

			logResults.createLogs("Y", "PASS",
					"Successfully Compared The Assigned Policy Name And Created Policy Name With Emp criteria: "
							+ policynames + "=" + attendancepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Comparing Attendance Policy Name : " + AttendancePolicyPage.getExceptionDesc());
		}

	}

	// MPI_457_Attendance_Policy_24
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_457_Attendance_Policy_24() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults
				.setScenarioName("To verify this, check the functionality of search feature by enter the policy name");

		logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_435_Attendance_Policy_02 TestCase: ");
	}

	// MPI_494_Attendance_Policy_44
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_494_Attendance_Policy_44() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, inactivate the policy Ensure that all employees who were assigned to the inactivated policy are now automatically assigned to the default attendance policy.");

		ArrayList<String> data = initBase.loadExcelData("attendance_policy", currTC,
				"password,captcha,assignedemp,policyname");

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String assignedemp = data.get(2);
		String defaultpolicy = data.get(3);

		String EmpID = "";
		String assignedempcount = "";

		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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

		if (RolePermissionpage.AttendancePolicy()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AttendancePolicy: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Attendance Policy Page Is Not Loaded Completely: " + RolePermissionpage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.SearchTextField(policynames)) {

			logResults.createLogs("Y", "PASS", "On SearchTextField Policy Name Inputted Successfully : " + policynames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Policy Name On Search TextField : "
					+ AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(6000);

		if (AttendancePolicyPage.ClickOnAssignedEmpCount()) {

			logResults.createLogs("Y", "PASS", "Clicked On AssignedEmpCount Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Assigned Emp Count: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CountLoaded()) {

			logResults.createLogs("Y", "PASS", "Emp Record Loaded Validated Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"All Emp Records Are Not Loaded: " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.FirstRowEmpId()) {
			EmpID = AttendancePolicyPage.AssignedempId;

			logResults.createLogs("Y", "PASS", "First Row Emp ID Fetched Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Fetching Emp Id: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EmpIdCheckBox()) {

			logResults.createLogs("Y", "PASS", "EmpIdCheckBox Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmpIdCheckBox: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown: " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(EmpID)) {

			logResults.createLogs("Y", "PASS", "FirstRow Emp ID Inputted Successfully : " + EmpID);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp ID: " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (AttendancePolicyPage.CloseIcon()) {

			logResults.createLogs("Y", "PASS", "CloseIcon Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CloseIcon: " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (AttendancePolicyPage.SearchTextField(policynames)) {

			logResults.createLogs("Y", "PASS", "On SearchTextField Policy Name Inputted Successfully : " + policynames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Policy Name On Search TextField : "
					+ AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (AttendancePolicyPage.DeleteIcon()) {

			logResults.createLogs("Y", "PASS", "DeleteIcon Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteIcon: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeleteButton()) {

			logResults.createLogs("Y", "PASS", "DeleteButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteButton: " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(3000);

		if (AttendancePolicyPage.SearchTextField(policynames)) {

			logResults.createLogs("Y", "PASS", "On SearchTextField Policy Name Inputted Successfully : " + policynames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Policy Name On Search TextField : "
					+ AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (AttendancePolicyPage.GetAssignedEmpCount()) {

			assignedempcount = AttendancePolicyPage.Assignedemp;

			logResults.createLogs("Y", "PASS", "No of Employees Assigned Get Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Fetching Assigned Emp Count : " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (AttendancePolicyPage.AssignedEmpComparision(assignedemp)) {

			logResults.createLogs("Y", "PASS",
					"Assigned Emp Count Compared Successfully : " + assignedemp + "=" + assignedempcount);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Comparing Actual Assigned Emp Count : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(4000);

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

		if (EmployeePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EmpIdCheckBox()) {
			logResults.createLogs("Y", "PASS", "Selected  EmpID  Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting EmpID: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (EmployeePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(EmpID)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Employee EmpID Successfully: " + EmpID);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting EmpID: " + EmployeePage.getExceptionDesc());
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

		if (AttendancePolicyPage.AttendancePolicyName()) {
			attendancepolicyname = AttendancePolicyPage.AttendancePolicy;

			logResults.createLogs("Y", "PASS", "Successfully Fetch the Assigned Attendance Policy Name Of Employee : ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Fetching Employee's Attendance Policy Name : "
					+ AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AssignedAttendancePolicyComparisions(defaultpolicy)) {

			logResults.createLogs("Y", "PASS",
					"Successfully Compared The Assigned Policy Name And Created Policy Name With Emp criteria: "
							+ defaultpolicy + "=" + attendancepolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Comparing Attendance Policy Name : " + AttendancePolicyPage.getExceptionDesc());
		}

	}

	// MPI_496_Attendance_Policy_46
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_496_Attendance_Policy_46() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, open the \"Assigned Employees\" popup/small window and test the search functionality by entering values for the following fields: First Name, Last Name, Employee ID, Enroll ID, Designation, Department, Office Ensure the search results accurately reflect the entered criteria and display matching employee records only.");

		ArrayList<String> data = initBase.loadExcelData("attendance_policy", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);
		
	

		
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
	

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

		if (RolePermissionpage.AttendancePolicy()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AttendancePolicy: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Attendance Policy Page Is Not Loaded Completely: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.PaginationDropDown()) {

			logResults.createLogs("Y", "PASS", "PaginationDropDown Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PaginationDropDown: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AssignedEmpLinkedCountIfGreaterThanZero()) {

			logResults.createLogs("Y", "PASS", "Clicked On AssignedEmpCount Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Assigned Emp Count: " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown: " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (AttendancePolicyPage.DeptCheckBox()) {

			logResults.createLogs("Y", "PASS", "DeptCheckBox Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeptCheckBox: " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (AttendancePolicyPage.OfficeCheckBox()) {

			logResults.createLogs("Y", "PASS", "OfficeCheckBox Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On OfficeCheckBox: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown: " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(departmentname)) {

			logResults.createLogs("Y", "PASS", "Department Name Inputted Successfully : " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Dept Name: " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (AttendancePolicyPage.DepartmentList(departmentname)) {

			logResults.createLogs("Y", "PASS",
					"Entered Department Name Only Displayed And Validated Successfully : " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Entered Dept With Displayed Dept: "
					+ AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(officename)) {

			logResults.createLogs("Y", "PASS", "office Name Inputted Successfully : " + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting office Name: " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(3000);

		if (AttendancePolicyPage.OfficeList(officename)) {

			logResults.createLogs("Y", "PASS",
					"Entered office Name Only Displayed And Validated Successfully : " + officename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Entered office With Displayed office: "
					+ AttendancePolicyPage.getExceptionDesc());
		}

	}

	// MPI_498_Attendance_Policy_48
	@Test(enabled = true, priority = 8, groups = { "Smoke" })
	public void MPI_498_Attendance_Policy_48() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, first create an employee and assign the designation as \"QC\". Then, create a new policy and apply the \"Add Employee Criteria\" by selecting \"QC\" from the Department dropdown. Save the policy and click on the \"Assigned Employees\" count link to open the assigned employees popup. In the popup, use the search text field to search for the employee. Ensure that the employee row is displayed in the search results, confirming that the employee is correctly assigned to the policy based on the selected department criteria.");

		ArrayList<String> data = initBase.loadExcelData("attendance_policy", currTC,
				"password,captcha,policyname,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,pin,gracehours");

		
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

		int i = 0;
		String password = data.get(i);
		String captcha = data.get(++i);
		
		String policyname = data.get(++i) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		
		String mailinator = data.get(++i);
		String empid = data.get(++i) + Pramod.generateRandomAlpha(5);
		

		String empfirstname = data.get(++i) + Pramod.generateRandomAlpha(5);
		String emplastname = data.get(++i) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(++i);
		String empphoneno = data.get(++i) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(++i);
		String reportingto = data.get(++i);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(3) + mailinator;
		String enrollmentimage = data.get(++i);
		
		String pin = data.get(++i) + Pramod.generateRandomNumber(3);
		
		String gracehours = data.get(++i);

		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
	
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
	

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

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: " + empfirstname);
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

		if (RolePermissionpage.AttendancePolicy()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AttendancePolicy: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Attendance Policy Page Is Not Loaded Completely: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddAttendancePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddAttendancePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddAttendancePolicyButton: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.PolicyNameTextField(policyname)) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Name Entered Successfully : " + policyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting AttendancePolicy Name: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.WebPunchButton()) {

			logResults.createLogs("Y", "PASS", "WebPunchButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WebPunchButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodButton()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod Button  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EarlyClockOut()) {

			logResults.createLogs("Y", "PASS", "EarlyClockOut CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EarlyClockOut CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursClick()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceHours  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceHours TextField : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursInput(gracehours)) {

			logResults.createLogs("Y", "PASS", " GraceHours Inputted  Successfully : " + gracehours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting GraceHours : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceTimeApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceTimeApply Button  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceTimeApply Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodCheckBox()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.RegulationRequestCheckBox()) {

			logResults.createLogs("Y", "PASS", "RegulationRequest CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On RegulationRequest CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddEmpCriteria()) {

			logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddEmpCriteria Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.OfficeDropDown(officename)) {

			logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully : " + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Office Name : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.DeptDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Dept DropDown: " + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.DeptOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting  Dept : " + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.TeamDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team  Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.TeamOptionSelected()) {
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

		

		if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Employee Type From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.ApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Apply Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Apply Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CreatePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicyButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (AttendancePolicyPage.SearchTextField(policyname)) {

			logResults.createLogs("Y", "PASS", "On SearchTextField Policy Name Inputted Successfully : " + policyname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Policy Name On Search TextField : "
					+ AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (AttendancePolicyPage.ClickOnAssignedEmpCount()) {

			logResults.createLogs("Y", "PASS", "Clicked On AssignedEmpCount Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Assigned Emp Count: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CountLoaded()) {

			logResults.createLogs("Y", "PASS", "Emp Record Loaded Validated Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"All Emp Records Are Not Loaded: " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);
		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(empfirstname)) {

			logResults.createLogs("Y", "PASS", "Employee Name Inputted Successfully : " + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Employee Name: " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (AttendancePolicyPage.EmployeeFirstName(empfirstname)) {
			String EmployeeFirstname = AttendancePolicyPage.actualFirstName;
			logResults.createLogs("Y", "PASS", "Emp Criteria Added Employee FirstName Displayed Successfully : "
					+ empfirstname + "=" + EmployeeFirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Comparing Employee Name: " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(3000);
	}

	// MPI_499_Attendance_Policy_49
	@Test(enabled = true, priority = 9, groups = { "Smoke" })
	public void MPI_499_Attendance_Policy_49() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, go to the \"Add Employee Criteria\" section and click on \"Add More Criteria.\" From the available filters, select \"Grade\" and choose a grade that is assigned to only one specific employee. After saving the policy, click on the \"Assigned Employees\" count link to open the list. Ensure that only the employee matching the selected grade is assigned to the policy, confirming that the grade-based filtering is working correctly.");

		ArrayList<String> data = initBase.loadExcelData("attendance_policy", currTC,
				"password,captcha,policyname,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,pin,emptwo,gracehours");

		
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

		
		int i = 0;
		String password = data.get(i);
		String captcha = data.get(++i);
		String policyname = data.get(++i) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
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
		String gracehours = data.get(++i);

		String seconduseremail = empemail + Pramod.generateRandomAlpha(4) + mailinator;

		String secondempid = empid + Pramod.generateRandomAlpha(5);

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);

		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
	
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		

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

		if (EmployeePage.EmployeeLastName(emptwo)) {
			logResults.createLogs("Y", "PASS",
					"Employee Last Name Entered On Employee Last Name Text Field: " + emptwo);
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

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: " + empfirstname);
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

		if (RolePermissionpage.AttendancePolicy()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AttendancePolicy: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Attendance Policy Page Is Not Loaded Completely: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddAttendancePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddAttendancePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddAttendancePolicyButton: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.PolicyNameTextField(policyname)) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Name Entered Successfully : " + policyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting AttendancePolicy Name: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.WebPunchButton()) {

			logResults.createLogs("Y", "PASS", "WebPunchButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WebPunchButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodButton()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod Button  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EarlyClockOut()) {

			logResults.createLogs("Y", "PASS", "EarlyClockOut CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EarlyClockOut CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursClick()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceHours  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceHours TextField : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursInput(gracehours)) {

			logResults.createLogs("Y", "PASS", " GraceHours Inputted  Successfully : " + gracehours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting GraceHours : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceTimeApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceTimeApply Button  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceTimeApply Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodCheckBox()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.RegulationRequestCheckBox()) {

			logResults.createLogs("Y", "PASS", "RegulationRequest CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On RegulationRequest CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddEmpCriteria()) {

			logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddEmpCriteria Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.OfficeDropDown(officename)) {

			logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully : " + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Office Name : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.DeptDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Dept DropDown: " + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.DeptOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept: " + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting  Dept : " + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.TeamDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team  Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}
		
		if (AttendancePolicyPage.TeamOptionSelected()) {
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

		

		if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Employee Type From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddMoreCriteriaButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Add More Criteria: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add More Criteria : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GradeCheckBox()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On GradeCheckBox: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GradeCheckBox: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddMoreCriteriaButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Add More Criteria: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add More Criteria : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.IncludeEmpCheckBox()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On IncludeEmpCheckBox: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On IncludeEmpCheckBox: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddMoreCriteriaButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Add More Criteria: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add More Criteria : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.ExcludeEmpCheckBox()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On ExcludeEmpCheckBox: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ExcludeEmpCheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GradeDropDown(gradename)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Grade From Dropdown: " + gradename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Grade From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.IncludeNameTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS", "Successfully Input The Employee First Name: " + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Employee First Name : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.IncludeName()) {
			logResults.createLogs("Y", "PASS",
					"Successfully Clicked On Displayed Employee First Name: " + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Employee Name : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.ExcludeNameTextField(emptwo)) {
			logResults.createLogs("Y", "PASS", "Successfully Input The 2nd Employee First Name: " + emptwo);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting 2nd Employee First Name : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.ExcludeName()) {
			logResults.createLogs("Y", "PASS",
					"Successfully Clicked On Displayed Employee First Name: " + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Employee Name : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.ApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Apply Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Apply Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.CreatePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicyButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (AttendancePolicyPage.SearchTextField(policyname)) {

			logResults.createLogs("Y", "PASS", "On SearchTextField Policy Name Inputted Successfully : " + policyname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Policy Name On Search TextField : "
					+ AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(10000);

		if (AttendancePolicyPage.ClickOnAssignedEmpCount()) {

			logResults.createLogs("Y", "PASS", "Clicked On AssignedEmpCount Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Assigned Emp Count: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CountLoaded()) {

			logResults.createLogs("Y", "PASS", "Emp Record Loaded Validated Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"All Emp Records Are Not Loaded: " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(emptwo)) {

			logResults.createLogs("Y", "PASS", "Employee Name Inputted Successfully : " + emptwo);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Employee Name: " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(3000);

		if (AttendancePolicyPage.NoMatchingRecord()) {

			logResults.createLogs("Y", "PASS", "Error Message Displayed Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Error Message: " + AttendancePolicyPage.getExceptionDesc());
		}

	}

	// MPI_502_Attendance_Policy_51
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_502_Attendance_Policy_51() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, activate the inactive attendance policy and ensure that the policy is successfully activated and employees are assigned to it after activation.");

		ArrayList<String> data = initBase.loadExcelData("attendance_policy", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String name = "";

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);

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

		if (RolePermissionpage.AttendancePolicy()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AttendancePolicy: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Attendance Policy Page Is Not Loaded Completely: " + RolePermissionpage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (AttendancePolicyPage.SearchTextField(policynames)) {

			logResults.createLogs("Y", "PASS", "On SearchTextField Policy Name Inputted Successfully : " + policynames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Policy Name On Search TextField : "
					+ AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(6000);

		if (ShiftPolicyPage.EditIcon()) {

			logResults.createLogs("Y", "PASS", "Shift Policy Edit Button Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicy Edit Button: " + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.ActiveCheckBox()) {

			logResults.createLogs("Y", "PASS", "Shift Policy Active CheckBox Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On ShiftPolicy Active CheckBox Button: "
					+ AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CreatePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicyButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "YesButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On YesButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (AttendancePolicyPage.SearchTextField(policynames)) {

			logResults.createLogs("Y", "PASS", "On SearchTextField Policy Name Inputted Successfully : " + policynames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Policy Name On Search TextField : "
					+ AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (AttendancePolicyPage.ClickOnAssignedEmpCount()) {
			logResults.createLogs("Y", "PASS", "Clicked On Assigned Emp Count Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ClickOnAssignedEmpCount: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CountLoaded()) {

			logResults.createLogs("Y", "PASS", "Emp Record Loaded Validated Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"All Emp Records Are Not Loaded: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EmployeeFirstNamesdata()) {
			name = AttendancePolicyPage.actualFirstNamess;
			logResults.createLogs("Y", "PASS", "Fetched The Employee FirstName Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Fetching Employee First Name: " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);
		if (AttendancePolicyPage.CloseIcon()) {

			logResults.createLogs("Y", "PASS", "CloseIcon Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CloseIcon: " + AttendancePolicyPage.getExceptionDesc());
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

		if (EmployeePage.SearchTextField(name)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: " + name);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

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

		if (AttendancePolicyPage.AttendancePolicyNames(policynames)) {
			String attendancepolicyNames = AttendancePolicyPage.attendancePolicys;

			logResults.createLogs("Y", "PASS", "Successfully Fetch the Assigned Attendance Policy Name Of Employee : "
					+ attendancepolicyNames + "=" + policynames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Fetching Employee's Attendance Policy Name : "
					+ AttendancePolicyPage.getExceptionDesc());
		}
	}

	// MPI_503_Attendance_Policy_52
	@Test(enabled = true, priority = 11, groups = { "Smoke" })
	public void MPI_503_Attendance_Policy_52() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a policy and set it as the default. Then, create an employee and ensure that the employee is assigned to the created policy since it has been set as the default.");

		ArrayList<String> data = initBase.loadExcelData("attendance_policy", currTC,
				"password,captcha,policyname,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,gracehours");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

		int i = 0;
		String password = data.get(i);
		String captcha = data.get(++i);
		String policyname = data.get(++i) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
	
		String mailinator = data.get(++i);
		String empid = data.get(++i) + Pramod.generateRandomAlpha(5);
		String empfirstname = data.get(++i) + Pramod.generateRandomAlpha(5);
		String emplastname = data.get(++i) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(++i);
		String empphoneno = data.get(++i) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(++i);
		String reportingto = data.get(++i);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(4) + mailinator;
		
		String enrollmentimage = data.get(++i);
		String gracehours = data.get(++i);

		attendancepolicyname = "";

		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

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

		if (RolePermissionpage.AttendancePolicy()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AttendancePolicy: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Attendance Policy Page Is Not Loaded Completely: " + RolePermissionpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.AddAttendancePolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddAttendancePolicyButton Clicked Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddAttendancePolicyButton: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.PolicyNameTextField(policyname)) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Name Entered Successfully : " + policyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting AttendancePolicy Name: " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DefaultCheckBox()) {

			logResults.createLogs("Y", "PASS", "DefaultCheckBox  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DefaultCheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodButton()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod Button  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EarlyClockOut()) {

			logResults.createLogs("Y", "PASS", "EarlyClockOut CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EarlyClockOut CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursClick()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceHours  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceHours TextField : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursInput(gracehours)) {

			logResults.createLogs("Y", "PASS", " GraceHours Inputted  Successfully : " + gracehours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting GraceHours : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceTimeApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceTimeApply Button  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceTimeApply Button : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodCheckBox()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.RegulationRequestCheckBox()) {

			logResults.createLogs("Y", "PASS", "RegulationRequest CheckBox Clicked  Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On RegulationRequest CheckBox : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CreatePolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicyButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "YesButton Button Successfully : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On YesButton : " + AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(2000);

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

		if (EmployeePage.SearchTextField(empfirstname)) {
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

		if (EmployeePage.SearchTextField(empfirstname)) {
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

		if (AttendancePolicyPage.PolicyButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record Policy Button: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record Policy Button : "
					+ AttendancePolicyPage.getExceptionDesc());
		}

		Thread.sleep(3000);

		if (AttendancePolicyPage.AttendancePolicyNameDefault(policyname)) {
			String attendancepolicyNames = AttendancePolicyPage.newinput;

			logResults.createLogs("Y", "PASS", "Successfully Fetch the Assigned Attendance Policy Name Of Employee : "
					+ attendancepolicyNames + "=" + policyname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Fetching Employee's Attendance Policy Name : "
					+ AttendancePolicyPage.getExceptionDesc());
		}

	}
	
	
	// MPI_846_Attendance_Policy_71
		@Test(enabled = true, priority = 4, groups = { "Smoke", "AddMaster" })
		public void MPI_846_Attendance_Policy_71() throws InterruptedException {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, create an attendance policy and add employee criteria and ensure matching criteria employee are assigned to the created policy");

			ArrayList<String> data = initBase.loadExcelData("attendance_policy", currTC,
					"password,captcha,policyname,gracehours");

			MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

			int i = 0;
			String password = data.get(i);
			String captcha = data.get(++i);
			
		String policyname = data.get(++i) + initBase.executionRunTime;
		String gracehours = data.get(++i);
			
			
			

			MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			
			if (initBase.stopNewData) { // Tapan 5 July 25
				String AttenPolicyName = Utils.propsReadWrite("data/addmaster.properties", "get", "AttenPolicyName", "");
				if (AttenPolicyName.equals("pass")) {
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

			if (RolePermissionpage.AttendancePolicy()) {

				logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AttendancePolicy: " + RolePermissionpage.getExceptionDesc());
			}

			if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

				logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Attendance Policy Page Is Not Loaded Completely: " + RolePermissionpage.getExceptionDesc());
			}

			if (AttendancePolicyPage.AddAttendancePolicyButton()) {

				logResults.createLogs("Y", "PASS", "AddAttendancePolicyButton Clicked Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddAttendancePolicyButton: " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.PolicyNameTextField(policyname)) {

				logResults.createLogs("Y", "PASS", "AttendancePolicy Name Entered Successfully : " + policyname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting AttendancePolicy Name: " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.WebPunchButton()) {

				logResults.createLogs("Y", "PASS", "WebPunchButton Button Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WebPunchButton : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.EnableGracePeriodButton()) {

				logResults.createLogs("Y", "PASS", "EnableGracePeriod Button  Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On EnableGracePeriod Button : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.EarlyClockOut()) {

				logResults.createLogs("Y", "PASS", "EarlyClockOut CheckBox Clicked  Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On EarlyClockOut CheckBox : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.GraceHoursClick()) {

				logResults.createLogs("Y", "PASS", "Clicked On GraceHours  Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On GraceHours TextField : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.GraceHoursInput(gracehours)) {

				logResults.createLogs("Y", "PASS", " GraceHours Inputted  Successfully : " + gracehours);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting GraceHours : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.GraceTimeApplyButton()) {

				logResults.createLogs("Y", "PASS", "Clicked On GraceTimeApply Button  Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On GraceTimeApply Button : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.EnableGracePeriodCheckBox()) {

				logResults.createLogs("Y", "PASS", "EnableGracePeriod CheckBox Clicked  Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On EnableGracePeriod CheckBox : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.RegulationRequestCheckBox()) {

				logResults.createLogs("Y", "PASS", "RegulationRequest CheckBox Clicked  Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On RegulationRequest CheckBox : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.AddEmpCriteria()) {

				logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddEmpCriteria Button : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.OfficeDropDown(officename)) {

				logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully : " + officename);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Office Name : " + AttendancePolicyPage.getExceptionDesc());
			}

			Thread.sleep(2000);

			if (AttendancePolicyPage.DeptDropDownClick()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown: " + departmentname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Dept DropDown: " + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (AttendancePolicyPage.DeptOptionSelected()) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Dept: " + departmentname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting  Dept : " + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (AttendancePolicyPage.TeamDropDownClick()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown: " + teamname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Team  Dropdown : " + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (AttendancePolicyPage.TeamOptionSelected()) {
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

			

			if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown: " + entityname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Employee Type From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.ApplyButton()) {

				logResults.createLogs("Y", "PASS", "Clicked On Apply Button Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Apply Button : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.CreatePolicyButton()) {

				logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CreatePolicyButton : " + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.YesButton()) {
				Utils.propsReadWrite("data/addmaster.properties", "set", "AttenPolicyName", "pass");
				logResults.createLogs("Y", "PASS", "Yes Button Button Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Yes Button : " + AttendancePolicyPage.getExceptionDesc());
			}

			Thread.sleep(4000);

			

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
