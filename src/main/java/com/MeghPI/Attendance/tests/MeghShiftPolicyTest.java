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

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import com.MeghPI.Attendance.pages.MeghAttendancePolicyPage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;

import utils.Pramod;
import utils.Utils;

public class MeghShiftPolicyTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	public String shiftpolicynames = "";
	public String empfirstname = "";

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
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		entityname = "AutoEN" + initBase.executionRunTime;
	}

	// MPI_478_Shift_Policy_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_478_Shift_Policy_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create the shift policy by giving name, click on add shift and select \"A shift\" and click on \"create policy\" and ensure policy is created and ensure created policy is displaying in the list");

		ArrayList<String> data = initBase.loadExcelData("shift_policy", currTC,
				"shiftpolicyname");

		
	
		String shiftpolicyname = data.get(0) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);

		String searchresult = "";

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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicy Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					" ShiftPolicy Page Is Not Loaded Completely." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.PolicyNameTextField(shiftpolicyname)) {

			logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + shiftpolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.GeneralShiftCheckBox()) {

			logResults.createLogs("Y", "PASS", "GeneralShiftCheckBox Selected Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting On GeneralShiftCheckBox." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftSaveButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftSaveButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftSaveButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.CreatePolicySave()) {

			logResults.createLogs("Y", "PASS", "CreatePolicySave Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicySave Button." + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		

		if (ShiftPolicyPage.ShiftSearchTextField(shiftpolicyname)) {

			logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + shiftpolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.FirstRowShiftName()) {
			searchresult = ShiftPolicyPage.firstrowshiftname;

			logResults.createLogs("Y", "PASS", "Created Policy Name Displayed Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying And Fetching Searched Shift Name." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.FirstRowShiftNameComparison(shiftpolicyname)) {

			logResults.createLogs("Y", "PASS", "Created Policy Name Displayed And Validated Successfully ."
					+ shiftpolicyname + "=" + searchresult);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying And Validating Created Shift Name." + ShiftPolicyPage.getExceptionDesc());
		}

	}

	// MPI_479_Shift
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_479_Shift_Policy_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, in \"create shift policy\" page click on \"create shift\" and create new shift and ensure created policy is displaying in the shift module");

		ArrayList<String> data = initBase.loadExcelData("shift_policy", currTC,
				"shiftname,shiftcode,starttime,endtime,buffertime,fullday,halfday");

		
		String shiftname = data.get(0)  + Pramod.generateRandomAlpha(4);
		String shiftcode = data.get(1) + Pramod.generateRandomNumber(3);
		String starttime = data.get(2);
		String endtime = data.get(3);
		String buffertime = data.get(4);
		String fullday = data.get(5);
		String halfday = data.get(6);

		
		String firstshiftname = "";

		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicy Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					" ShiftPolicy Page Is Not Loaded Completely." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.CreateShiftButton()) {

			logResults.createLogs("Y", "PASS", "CreateShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateShiftButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftNameTextField(shiftname)) {

			logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully ." + shiftname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Name." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftCodeTextField(shiftcode)) {

			logResults.createLogs("Y", "PASS", "Shift Code Inputted Successfully ." + shiftcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Code." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftStartTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.ShiftStartTime(starttime)) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Inputted Successfully ." + starttime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftEndTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Inputted Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftEndTime(endtime)) {

			logResults.createLogs("Y", "PASS", "Shift End Time Inputted Successfully ." + endtime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift End Time." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.BufferAllTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Buffer Time Inputted Successfully ." + buffertime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Buffer Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.BufferAllTime(buffertime)) {

			logResults.createLogs("Y", "PASS", "Shift Buffer Time Inputted Successfully ." + buffertime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Buffer Time." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.FullDayTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Full Day Time Inputted Successfully ." + fullday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Full Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.FullDayTime(fullday)) {

			logResults.createLogs("Y", "PASS", "Shift Full Day Time Inputted Successfully ." + fullday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Full Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

	

		if (ShiftPolicyPage.HalfDayTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Half Day Time Inputted Successfully ." + halfday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Half Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.HalfDayTime(halfday)) {

			logResults.createLogs("Y", "PASS", "Shift Half Day Time Inputted Successfully ." + halfday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Half Day Time." + ShiftPolicyPage.getExceptionDesc());
		}
		if (ShiftPolicyPage.ShiftCodeTextField(shiftcode)) {

			logResults.createLogs("Y", "PASS", "Shift Code Inputted Successfully ." + shiftcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Code." + ShiftPolicyPage.getExceptionDesc());
		}
		if (ShiftPolicyPage.CreateShiftSaveButton()) {

			logResults.createLogs("Y", "PASS", "Create Shift Save Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateShift Save Button." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.BrowserRefresh()) {

			logResults.createLogs("Y", "PASS", "Browser Refreshed Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Browser Refresh Button." + ShiftPolicyPage.getExceptionDesc());
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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftButtonFromSidebar()) {

			logResults.createLogs("Y", "PASS", "Shift Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Shift." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftModuleSearchTextField(shiftname)) {

			logResults.createLogs("Y", "PASS", "Created Shift Name Inputted Successfully ." + shiftname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Created Shift Time." + ShiftPolicyPage.getExceptionDesc());
		}

		

		if (ShiftPolicyPage.ShiftSearchResult()) {

			firstshiftname = ShiftPolicyPage.createdshiftname;
			logResults.createLogs("Y", "PASS", "Created Shift Displayed Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created Shift Name." + ShiftPolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.CreatedShiftNameComparison(shiftname)) {

			logResults.createLogs("Y", "PASS",
					"Created Shift And Searched Shift Compared Successfully ." + shiftname + "=" + firstshiftname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Comparing Created Shift Name With Searched Result."
					+ ShiftPolicyPage.getExceptionDesc());
		}

	}

	// MPI_480_Shift_Policy_03
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_480_Shift_Policy_03()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, first edit the employee profile by selecting \"QC\" as the department, \"QC Head\" as the designation, and the appropriate group, then save the profile. Next, navigate to the shift policy section and edit the relevant shift policy. Click on \"Add Employee Criteria\" and select the office, department, designation, group, and employee type to match those of the employee. After saving the updated shift policy, click on the assigned employees count to ensure that the employeeâ€™s record is displayed correctly under the policy.");

		ArrayList<String> data = initBase.loadExcelData("shift_policy", currTC,
				"shiftpolicyname,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,pin");

		
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

	     int i = 0;
	
		shiftpolicynames = data.get(i++) + Pramod.generateRandomAlpha(3)+ initBase.executionRunTime;
		String mailinator = data.get(i++);
		String empid = data.get(i++) + Pramod.generateRandomNumber(7);
		

		empfirstname = data.get(i++) + Pramod.generateRandomAlpha(7);
		String emplastname = data.get(i++) + Pramod.generateRandomAlpha(6);
		String empemail = data.get(i++);
		String empphoneno = data.get(i++) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(i++);
		String reportingto = data.get(i++);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(3) + mailinator;
		
		String enrollmentimage = data.get(i++);
		String pin = data.get(i++) + Pramod.generateRandomNumber(3);
		
		

		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

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
					"Clicked On EmployeeTypeDropdown Successfully And Selected EntityName Successfully." + entityname);
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

		if (EmployeePage.EmployeeEmailID(email)) {
			logResults.createLogs("Y", "PASS", "Employee Email Entered On Email ID Text Field." + email);
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
		

//enrolling the User

		if (EmployeePage.GoToDirectory()) {
			logResults.createLogs("Y", "PASS", "Clicked On GoToDirectory Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GoToDirectory." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee First Name Successfully."
							+ empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field." + EmployeePage.getExceptionDesc());
		}
		

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully." + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DotsMenu()) {
			logResults.createLogs("Y", "PASS", "Clicked On 3Dots Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On 3Dots." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.WebEnrollment()) {
			logResults.createLogs("Y", "PASS", "Clicked On WebEnrollment Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On WebEnrollment." + EmployeePage.getExceptionDesc());
		}
		

		if (EmployeePage.FaceEnrollmentTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On FaceEnrollmentTab Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FaceEnrollmentTab." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.ImageUploadButton(enrollmentimage)) {
			logResults.createLogs("Y", "PASS", "Image Selected For WebEnrollment Successfully." + enrollmentimage);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Image For WebEnrollment." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CropImage()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On CropImage.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On CropImage." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SaveEnrollmentButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SaveEnrollmentButton.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SaveEnrollmentButton." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SaveEnrollmentSuccessMsg()) {
			logResults.createLogs("Y", "PASS", "Success Msg Is Displayed.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Success Msg." + EmployeePage.getExceptionDesc());
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

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully."
							+ empfirstname);
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

		if (AttendancePolicyPage.TeamDropDown(teamname)) {
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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicy Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					" ShiftPolicy Page Is Not Loaded Completely." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.PolicyNameTextField(shiftpolicynames)) {

			logResults.createLogs("Y", "PASS", "Shift PolicyName Inputted Successfully ." + shiftpolicynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.GeneralShiftCheckBox()) {

			logResults.createLogs("Y", "PASS", "GeneralShiftCheckBox Selected Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting On GeneralShiftCheckBox." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftSaveButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftSaveButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftSaveButton." + ShiftPolicyPage.getExceptionDesc());
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
		
		if (AttendancePolicyPage.TeamOptionSelected()) {
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

		if (ShiftPolicyPage.ShiftAddEmpCriteriaApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Apply Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Apply Button ." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.CreatePolicySave()) {

			logResults.createLogs("Y", "PASS", "CreatePolicySave Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicySave Button." + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		

		if (ShiftPolicyPage.ShiftSearchTextField(shiftpolicynames)) {

			logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + shiftpolicynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AssignedEmpLinkedCount()) {

			logResults.createLogs("Y", "PASS", "Clicked On AssignedEmp Linked Count Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Assigned Emp Linked Count ." + ShiftPolicyPage.getExceptionDesc());
		}


		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(empfirstname)) {

			logResults.createLogs("Y", "PASS", "FirstRow Emp ID Inputted Successfully ." + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp ID." + AttendancePolicyPage.getExceptionDesc());
		}

		

		if (ShiftPolicyPage.FirstEmpName(empfirstname)) {
			String firstnamerow = ShiftPolicyPage.actualFirstName;

			logResults.createLogs("Y", "PASS",
					"Clicked On AssignedEmp Linked Count Successfully ." + empfirstname + "=" + firstnamerow);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Assigned Emp Linked Count ." + ShiftPolicyPage.getExceptionDesc());
		}

	}

	// MPI_481_Shift_Policy_04
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_481_Shift_Policy_04()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, ensure that the policy name is displayed in the employee profile when the policy matches the employeeâ€™s company, office name, designation, and groups.");


	


		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicy Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					" ShiftPolicy Page Is Not Loaded Completely." + ShiftPolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.ShiftSearchTextField(shiftpolicynames)) {

			logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + shiftpolicynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AssignedEmpLinkedCount()) {

			logResults.createLogs("Y", "PASS", "Clicked On AssignedEmp Linked Count Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Assigned Emp Linked Count ." + ShiftPolicyPage.getExceptionDesc());
		}

		

		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(empfirstname)) {

			logResults.createLogs("Y", "PASS", "FirstRow Emp ID Inputted Successfully ." + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp ID." + AttendancePolicyPage.getExceptionDesc());
		}

		

		if (ShiftPolicyPage.FirstEmpNames()) {

			logResults.createLogs("Y", "PASS", "FirstRow Emp ID Inputted Successfully ." + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CloseIcon()) {

			logResults.createLogs("Y", "PASS", "CloseIcon Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CloseIcon." + AttendancePolicyPage.getExceptionDesc());
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

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully."
							+ empfirstname);
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


		if (ShiftPolicyPage.ShiftPolicyNameInManageProfile(shiftpolicynames)) {
			String Assignedpolicyname = ShiftPolicyPage.actualPolicyName;

			logResults.createLogs("Y", "PASS", "Successfully Validated The Assigned Shift Policy Name Of Employee ."
					+ shiftpolicynames + "=" + Assignedpolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Employee's Shift Policy Name ." + AttendancePolicyPage.getExceptionDesc());
		}

	}

	// MPI_483_Shift_Policy_06
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_483_Shift_Policy_06()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("To verify this, check the functionality of search feature by entering policy name");

		logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_478_Shift_Policy_01 TestCase.");
	}

	// MPI_486_Shift_Policy_09
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_486_Shift_Policy_09()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, delete the shift policy and ensure the assigned employees count drops to zero. Also, confirm that those employees are automatically assigned to the default policy.");

		ArrayList<String> data = initBase.loadExcelData("shift_policy", currTC,
				"shiftpolicyname,assignedemp");

	
		
		String defaultpolicys = data.get(0);
		String assignedemp = data.get(1);

		


		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicy Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					" ShiftPolicy Page Is Not Loaded Completely." + ShiftPolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.ShiftSearchTextField(shiftpolicynames)) {

			logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + shiftpolicynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}
	

		if (ShiftPolicyPage.AssignedEmpLinkedCount()) {

			logResults.createLogs("Y", "PASS", "Clicked On AssignedEmp Linked Count Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Assigned Emp Linked Count ." + ShiftPolicyPage.getExceptionDesc());
		}

		

		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(empfirstname)) {

			logResults.createLogs("Y", "PASS", "FirstRow Emp ID Inputted Successfully ." + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp ID." + AttendancePolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.FirstEmpNames()) {

			logResults.createLogs("Y", "PASS", "FirstRow Emp FirstName Fetched Successfully ." + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Fetching First Emp FirstName." + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CloseIcon()) {

			logResults.createLogs("Y", "PASS", "CloseIcon Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CloseIcon." + AttendancePolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.ShiftSearchTextField(shiftpolicynames)) {

			logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + shiftpolicynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}


		if (AttendancePolicyPage.DeleteIcon()) {

			logResults.createLogs("Y", "PASS", "DeleteIcon Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteIcon." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeleteButton()) {

			logResults.createLogs("Y", "PASS", "DeleteButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteButton." + AttendancePolicyPage.getExceptionDesc());
		}

		

		if (ShiftPolicyPage.ShiftSearchTextField(shiftpolicynames)) {

			logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + shiftpolicynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.AssignedEmpLinkedCounts(assignedemp)) {

			String assignedempcounts = ShiftPolicyPage.actualcountofemp;

			logResults.createLogs("Y", "PASS",
					"No of Employees Assigned Get Successfully ." + assignedempcounts + "=" + assignedemp);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Fetching Assigned Emp Count ." + AttendancePolicyPage.getExceptionDesc());
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

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully."
							+ empfirstname);
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


		if (ShiftPolicyPage.ShiftPolicyNameInManageProfiles(defaultpolicys)) {
			String Assignedpolicyname = ShiftPolicyPage.actualPolicySuffix;

			logResults.createLogs("Y", "PASS", "Successfully Validated The Assigned Shift Policy Name Of Employee ."
					+ defaultpolicys + "=" + Assignedpolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Employee's Shift Policy Name ." + AttendancePolicyPage.getExceptionDesc());
		}

	}

	// MPI_487_Shift_Policy_10
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_487_Shift_Policy_10()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, activate the deactivated policy by clicking the \"Active\" button and ensure all employees meeting the policy criteria are reassigned to this policy.");

	

		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicy Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					" ShiftPolicy Page Is Not Loaded Completely." + ShiftPolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.ShiftSearchTextField(shiftpolicynames)) {

			logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + shiftpolicynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.EditIcon()) {

			logResults.createLogs("Y", "PASS", "Shift Policy Edit Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicy Edit Button." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.SelectActiveCheckBox()) {

			logResults.createLogs("Y", "PASS", "Shift Policy Active CheckBox Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicy Active CheckBox." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.CreatePolicySave()) {

			logResults.createLogs("Y", "PASS", "CreatePolicySave Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicySave Button." + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftSearchTextField(shiftpolicynames)) {

			logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + shiftpolicynames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.AssignedEmpLinkedCount()) {

			logResults.createLogs("Y", "PASS", "Clicked On AssignedEmp Linked Count Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Assigned Emp Linked Count ." + ShiftPolicyPage.getExceptionDesc());
		}


		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(empfirstname)) {

			logResults.createLogs("Y", "PASS", "FirstRow Emp ID Inputted Successfully ." + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp ID." + AttendancePolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.FirstEmpNames()) {

			logResults.createLogs("Y", "PASS", "FirstRow Emp FirstName Fetched Successfully ." + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Fetching First Emp FirstName." + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.CloseIcon()) {

			logResults.createLogs("Y", "PASS", "CloseIcon Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CloseIcon." + AttendancePolicyPage.getExceptionDesc());
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

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully."
							+ empfirstname);
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


		if (ShiftPolicyPage.ShiftPolicyNameInManageProfile(shiftpolicynames)) {
			String Assignedpolicyname = ShiftPolicyPage.actualPolicyName;

			logResults.createLogs("Y", "PASS", "Successfully Validated The Assigned Shift Policy Name Of Employee ."
					+ shiftpolicynames + "=" + Assignedpolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Employee's Shift Policy Name ." + AttendancePolicyPage.getExceptionDesc());
		}

	}

	// MPI_489_Shift_Policy_12
	@Test(enabled = true, priority = 8, groups = { "Smoke" })
	public void MPI_489_Shift_Policy_12()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the 2nd shift policy and set it as the default policy. Then, create a new employee and ensure the newly added employee is automatically assigned to the 2nd shift.");

		ArrayList<String> data = initBase.loadExcelData("shift_policy", currTC,
				"shiftpolicyname,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,shiftname");

		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

		int i = 0;
		String shiftpolicyname = data.get(i++) + Pramod.generateRandomAlpha(3);
		String mailinator = data.get(i++);
		String empid = data.get(i++) + Pramod.generateRandomNumber(4) + Pramod.generateRandomAlpha(3);
		String empfirstname = data.get(i++) + Pramod.generateRandomAlpha(7);
		String emplastname = data.get(i++) + Pramod.generateRandomAlpha(7);
		String empemail = data.get(i++);
		String empphoneno = data.get(i++) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(i++);
		String reportingto = data.get(i++);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomNumber(2) + mailinator;
	
		String enrollmentimage = data.get(i++);
		String shiftname = data.get(i++);

		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicy Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					" ShiftPolicy Page Is Not Loaded Completely." + ShiftPolicyPage.getExceptionDesc());
		}
		
		if (ShiftPolicyPage.AddShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.PolicyNameTextField(shiftpolicyname)) {

			logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + shiftpolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.GeneralShiftCheckBox()) {

			logResults.createLogs("Y", "PASS", "GeneralShiftCheckBox Selected Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting On GeneralShiftCheckBox." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftSaveButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftSaveButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftSaveButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.CreatePolicySave()) {

			logResults.createLogs("Y", "PASS", "CreatePolicySave Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicySave Button." + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftSearchTextField(shiftpolicyname)) {

			logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + shiftpolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.EditIcon()) {

			logResults.createLogs("Y", "PASS", "Shift Policy Edit Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicy Edit Button." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.SelectDefaultCheckBox()) {

			logResults.createLogs("Y", "PASS", "Shift Policy Default CheckBox Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicy Default CheckBox." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.SelectActiveCheckBox()) {

			logResults.createLogs("Y", "PASS", "Shift Policy Active CheckBox Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicy Active CheckBox." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.GeneralShiftCheckBox()) {

			logResults.createLogs("Y", "PASS", "GeneralShiftCheckBox Selected Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting On GeneralShiftCheckBox." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftSaveButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftSaveButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftSaveButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.CreatePolicySave()) {

			logResults.createLogs("Y", "PASS", "CreatePolicySave Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicySave Button." + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
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
					"Clicked On EmployeeTypeDropdown Successfully And Selected EntityName Successfully." + entityname);
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

		if (EmployeePage.EmployeeEmailID(email)) {
			logResults.createLogs("Y", "PASS", "Employee Email Entered On Email ID Text Field." + email);
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


//enrolling the User

		if (EmployeePage.GoToDirectory()) {
			logResults.createLogs("Y", "PASS", "Clicked On GoToDirectory Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GoToDirectory." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee First Name Successfully."
							+ empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully." + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DotsMenu()) {
			logResults.createLogs("Y", "PASS", "Clicked On 3Dots Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On 3Dots." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.WebEnrollment()) {
			logResults.createLogs("Y", "PASS", "Clicked On WebEnrollment Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On WebEnrollment." + EmployeePage.getExceptionDesc());
		}
	

		if (EmployeePage.FaceEnrollmentTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On FaceEnrollmentTab Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FaceEnrollmentTab." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.ImageUploadButton(enrollmentimage)) {
			logResults.createLogs("Y", "PASS", "Image Selected For WebEnrollment Successfully." + enrollmentimage);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Image For WebEnrollment." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CropImage()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On CropImage.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On CropImage." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SaveEnrollmentButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SaveEnrollmentButton.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SaveEnrollmentButton." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SaveEnrollmentSuccessMsg()) {
			logResults.createLogs("Y", "PASS", "Success Msg Is Displayed.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Success Msg." + EmployeePage.getExceptionDesc());
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

		if (EmployeePage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully."
							+ empfirstname);
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

		if (ShiftPolicyPage.ShiftPolicyNameInManageProfile(shiftname)) {
			String Assignedpolicyname = ShiftPolicyPage.actualPolicyName;

			logResults.createLogs("Y", "PASS", "Successfully Validated The Assigned Shift Policy Name Of Employee ."
					+ shiftname + "=" + Assignedpolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Employee's Shift Policy Name ." + AttendancePolicyPage.getExceptionDesc());
		}
	}

	// MPI_491_Shift_Policy_14
	@Test(enabled = true, priority = 9, groups = { "Smoke" })
	public void MPI_491_Shift_Policy_14()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, open the \"Assigned Employees\" popup/small window and test the search functionality by entering values for the following fields.First Name, Last Name, Employee ID, Enroll ID, Designation, Department, Office Ensure the search results accurately reflect the entered criteria and display matching employee records only.");

		
		String firstnamerow = "";
		String lastname = "";
		String employeeidno = "";

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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftPolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicy Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					" ShiftPolicy Page Is Not Loaded Completely." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.PaginationDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicy  Pagination Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecitng  ShiftPolicy Pagination Count." + ShiftPolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.AssignedEmpLinkedCountIfGreaterThanZero()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicy Emp Count Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicy Assigned Emp Count." + ShiftPolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.FirstrowEmpName()) {
			firstnamerow = ShiftPolicyPage.actualFirstRowNames;

			logResults.createLogs("Y", "PASS", "FirstName Fetched Successfully ." + firstnamerow);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Fetching First Name ." + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(firstnamerow)) {

			logResults.createLogs("Y", "PASS", "FirstRow Emp ID Inputted Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp ID." + AttendancePolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.ClearTextField()) {

			logResults.createLogs("Y", "PASS", "TextField Data Removed Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Removing Text Field Data." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.FirstrowEmpLastName()) {
			lastname = ShiftPolicyPage.actualLastRowNames;
			logResults.createLogs("Y", "PASS", "FirstRow Emp Last Name Fetched Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Fetching Emp Last Name." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "Search DropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.LastNameCheckBox()) {

			logResults.createLogs("Y", "PASS", "LastNameCheckBox Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LastNameCheckBox." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "Search DropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown." + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(lastname)) {

			logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Last Name." + AttendancePolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.ClearTextField()) {

			logResults.createLogs("Y", "PASS", "TextField Data Removed Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Removing Text Field Data." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.EmpID()) {
			employeeidno = ShiftPolicyPage.employeeID;
			logResults.createLogs("Y", "PASS", "FirstRow Emp ID Fetched Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Fetching Emp ID." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "Search DropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.LastNameCheckBox()) {

			logResults.createLogs("Y", "PASS", "LastNameCheckBox Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LastNameCheckBox." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.EmpIDCheckBox()) {

			logResults.createLogs("Y", "PASS", "EmpIDCheckBox Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmpIDCheckBox." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "Search DropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown." + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(employeeidno)) {

			logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully ." + employeeidno);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp ID." + AttendancePolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.ClearTextField()) {

			logResults.createLogs("Y", "PASS", "TextField Data Removed Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Removing Text Field Data." + ShiftPolicyPage.getExceptionDesc());
		}


		if (ShiftPolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "Search DropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.EmpIDCheckBox()) {

			logResults.createLogs("Y", "PASS", "EmpIDCheckBox Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmpIDCheckBox." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.DesignationCheckBox()) {

			logResults.createLogs("Y", "PASS", "LastNameCheckBox Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LastNameCheckBox." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "Search DropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown." + ShiftPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.SearchTextFieldOfEmpCount(designationname)) {

			logResults.createLogs("Y", "PASS", "Emp Designation Inputted Successfully ." + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Designation." + AttendancePolicyPage.getExceptionDesc());
		}
	}
	
	// MPI_850_Shift_policy_22
		@Test(enabled = true, priority = 0, groups = { "Smoke", "AddMaster" })
		public void MPI_850_Shift_policy_22()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, create an shift policy and add emp criteria to the policy and ensure criteria matching employee's are assigned to the created policy");

			ArrayList<String> data = initBase.loadExcelData("shift_policy", currTC,
					"password,captcha,shiftpolicyname");

			
			MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

		     int i = 0;
			String password = data.get(i++);
			String captcha = data.get(i++);
			String shiftpolicyname = data.get(i++) + initBase.executionRunTime;
			
			MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
		
			
			if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
				logResults.createLogs("Y", "PASS", "Login Done Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Login Is Failed." + MeghLoginPage.getExceptionDesc());
			}
			
			if (initBase.stopNewData) { // Tapan 5 July 25
				String ShiftPolicyName = Utils.propsReadWrite("data/addmaster.properties", "get", "ShiftPolicyName", "");
				if (ShiftPolicyName.equals("pass")) {
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

			if (RolePermissionpage.ShiftDropDown()) {

				logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
			}

			if (ShiftPolicyPage.ShiftPolicyButton()) {

				logResults.createLogs("Y", "PASS", "ShiftPolicyButton Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On ShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
			}

			if (ShiftPolicyPage.ShiftPolicyPageLoaded()) {

				logResults.createLogs("Y", "PASS", "ShiftPolicy Page Loaded Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						" ShiftPolicy Page Is Not Loaded Completely." + ShiftPolicyPage.getExceptionDesc());
			}

			if (ShiftPolicyPage.AddShiftPolicyButton()) {

				logResults.createLogs("Y", "PASS", "AddShiftPolicyButton Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
			}

			if (ShiftPolicyPage.PolicyNameTextField(shiftpolicyname)) {

				logResults.createLogs("Y", "PASS", "Shift PolicyName Inputted Successfully ." + shiftpolicyname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
			}

			if (ShiftPolicyPage.AddShiftButton()) {

				logResults.createLogs("Y", "PASS", "AddShiftButton Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddShiftButton." + ShiftPolicyPage.getExceptionDesc());
			}

			if (ShiftPolicyPage.GeneralShiftCheckBox()) {

				logResults.createLogs("Y", "PASS", "GeneralShiftCheckBox Selected Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting On GeneralShiftCheckBox." + ShiftPolicyPage.getExceptionDesc());
			}

			if (ShiftPolicyPage.AddShiftSaveButton()) {

				logResults.createLogs("Y", "PASS", "AddShiftSaveButton Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddShiftSaveButton." + ShiftPolicyPage.getExceptionDesc());
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
			
			if (AttendancePolicyPage.TeamOptionSelected()) {
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

			if (ShiftPolicyPage.ShiftAddEmpCriteriaApplyButton()) {

				logResults.createLogs("Y", "PASS", "Clicked On Apply Button Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Apply Button ." + ShiftPolicyPage.getExceptionDesc());
			}

			if (ShiftPolicyPage.CreatePolicySave()) {

				logResults.createLogs("Y", "PASS", "CreatePolicySave Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CreatePolicySave Button." + ShiftPolicyPage.getExceptionDesc());
			}

			
			
			if (AttendancePolicyPage.YesButton()) {
				Utils.propsReadWrite("data/addmaster.properties", "set", "ShiftPolicyName", "pass");
				logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
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
