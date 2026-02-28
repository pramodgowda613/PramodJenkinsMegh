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
import com.MeghPI.Attendance.pages.MeghLeavePolicyPage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghWeekOffPolicyPage;

import utils.Pramod;
import utils.Utils;

public class MeghWeekOffPolicyTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	public String weekoffpolicyname = "";
	public String weekoffpolicynameemp = "";
	public String emplastname = "";
	public String empfirstname = "";
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


	// MPI_576_WeekOff_Policy_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_576_WeekOff_Policy_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, add aÂ Week Off PolicyÂ by selectingÂ 2nd Half on ThursdayÂ andÂ Full Day on Friday, then make the policyÂ active. When the system prompts with the messageÂ \"Do you want to change weekly-off policy in Employee Profile based on current policy employee criteria?\", selectÂ \"Yes\". Finally, ensure that all applicable employees are assigned to the newly createdÂ Week Off PolicyÂ based on the defined criteria.");

		ArrayList<String> data = initBase.loadExcelData("week_off_policy", currTC,
				"weekoffpolicyname");

		weekoffpolicyname = data.get(0) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);

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

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button."
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage." + LeavePolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AddWeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddWeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddWeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyNameTextField(weekoffpolicyname)) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy Name Inputted Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.ThursdayButton()) {

			logResults.createLogs("Y", "PASS", "ThursdayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ThursdayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SecondHalf()) {

			logResults.createLogs("Y", "PASS", "SecondHalf Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SecondHalf ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.FridayButton()) {

			logResults.createLogs("Y", "PASS", "FridayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FridayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.FullDayButton()) {

			logResults.createLogs("Y", "PASS", "FullDayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FullDayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.CreateWeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreateWeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateWeekOffPolicyButton ." + WeekOffPolicyPage.getExceptionDesc());
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


		if (WeekOffPolicyPage.WeekOffPolicyNameInEmp(weekoffpolicyname)) {
			String WeekoffPolicyNameAssigned = WeekOffPolicyPage.WeekOffPolicyName;

			logResults.createLogs("Y", "PASS", "CreateWeekOffPolicy Is Assigned To All Emp Successfully."
					+ WeekoffPolicyNameAssigned + "=" + weekoffpolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created WeekOff PolicyName ." + WeekOffPolicyPage.getExceptionDesc());
		}

	}

	// MPI_577_WeekOff_Policy_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_577_WeekOff_Policy_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, navigate to the Week Off Policy module and edit an existing policy. Update the week-off configuration by setting Saturday as a 2nd half day off and Sunday as a full day off for the 1st and 4th weeks. For the 2nd and 3rd weeks, configure both Saturday and Sunday as full day offs. Save the changes to the policy and ensure that the system reflects the updated configuration accurately, confirming that the record has been successfully updated as per the changes made.");



		MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
		
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

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button."
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage." + LeavePolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicySearchTextField(weekoffpolicyname)) {

			logResults.createLogs("Y", "PASS",
					"WeekOffPolicy Name Inputted In Search TextField Successfully." + weekoffpolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy First Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffEditButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffEditButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffEditButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SaturdayButton1stWeek()) {

			logResults.createLogs("Y", "PASS", "SaturdayButton1stWeek Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SaturdayButton1stWeek." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SecondHalf()) {

			logResults.createLogs("Y", "PASS", "SecondHalf Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SecondHalf ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SundayButton1stWeek()) {

			logResults.createLogs("Y", "PASS", "SundayButton1stWeek Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SundayButton1stWeek ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.FullDayButton()) {

			logResults.createLogs("Y", "PASS", "FullDayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FullDayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SundayButton4thWeek()) {

			logResults.createLogs("Y", "PASS", "SundayButton4thWeek Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SundayButton4thWeek ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.FullDayButton()) {

			logResults.createLogs("Y", "PASS", "FullDayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FullDayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SaturdayButton4thWeek()) {

			logResults.createLogs("Y", "PASS", "SaturdayButton4thWeek Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SaturdayButton4thWeek ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SecondHalf()) {

			logResults.createLogs("Y", "PASS", "SecondHalf Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SecondHalf ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SundayButton2ndWeek()) {

			logResults.createLogs("Y", "PASS", "SundayButton2ndWeek Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SundayButton2ndWeek ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.FullDayButton()) {

			logResults.createLogs("Y", "PASS", "FullDayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FullDayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SaturdayButton2ndWeek()) {

			logResults.createLogs("Y", "PASS", "SaturdayButton2ndWeek Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SaturdayButton2ndWeek ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.FullDayButton()) {

			logResults.createLogs("Y", "PASS", "FullDayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FullDayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SundayButton3rdWeek()) {

			logResults.createLogs("Y", "PASS", "SundayButton3rdWeek Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SundayButton3rdWeek ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.FullDayButton()) {

			logResults.createLogs("Y", "PASS", "FullDayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FullDayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SaturdayButton3rdWeek()) {

			logResults.createLogs("Y", "PASS", "SaturdayButton3rdWeek Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SaturdayButton3rdWeek ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.FullDayButton()) {

			logResults.createLogs("Y", "PASS", "FullDayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FullDayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.CreateWeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreateWeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateWeekOffPolicyButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {

			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AfterEditWeekOffData()) {

			logResults.createLogs("Y", "PASS", "Edited WeekOff Data Displayed Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Edited WeekOff Data ." + WeekOffPolicyPage.getExceptionDesc());
		}

	}

	// MPI_578_WeekOff_Policy_03
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_578_WeekOff_Policy_03()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a user and assign them to the \"QC\" department with \"For Grade\" as the team, \"QC Head\" as the designation, and \"Staff\" as the employee type. Then, create a Week Off Policy and define the same employee criteria within the policy. After saving the policy, ensure that the employee matching the specified criteria is correctly assigned to the newly created Week Off Policy, confirming that the policy assignment based on employee attributes is functioning as expected. ");

		ArrayList<String> data = initBase.loadExcelData("week_off_policy", currTC,
				"weekoffpolicyname,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,pin");

		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);

		
		int i = 0;
	
		weekoffpolicynameemp = data.get(i++) + initBase.executionRunTime + Pramod.generateRandomAlpha(2);
		String mailinator = data.get(i++);
		empid = data.get(i++) + Pramod.generateRandomAlpha(5);
		empfirstname = data.get(i++) + Pramod.generateRandomAlpha(5);
		emplastname = data.get(i++) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(i++);
		String empphoneno = data.get(i++) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(i++);
		String reportingto = data.get(i++);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(4) + mailinator;
		String enrollmentimage =data.get(i++);
		String pin = data.get(i++) + Pramod.generateRandomNumber(3);
	
	
	

		MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
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

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button."
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage." + LeavePolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AddWeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddWeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddWeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyNameTextField(weekoffpolicynameemp)) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy Name Inputted Successfully." + weekoffpolicynameemp);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.ThursdayButton()) {

			logResults.createLogs("Y", "PASS", "ThursdayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ThursdayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SecondHalf()) {

			logResults.createLogs("Y", "PASS", "SecondHalf Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SecondHalf ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.FridayButton()) {

			logResults.createLogs("Y", "PASS", "FridayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FridayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.FullDayButton()) {

			logResults.createLogs("Y", "PASS", "FullDayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FullDayButton ." + WeekOffPolicyPage.getExceptionDesc());
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
			logResults.createLogs("Y", "PASS", "Successfully Clicked On ApplyButton." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Apply Button ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.CreateWeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreateWeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateWeekOffPolicyButton ." + WeekOffPolicyPage.getExceptionDesc());
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

		if (WeekOffPolicyPage.WeekOffPolicyNameInEmps(weekoffpolicynameemp)) {
			String WeekoffPolicyNameAssigned = WeekOffPolicyPage.WeekOffPolicyNames;

			logResults.createLogs("Y", "PASS", "CreateWeekOffPolicy Is Assigned To All Emp Successfully."
					+ WeekoffPolicyNameAssigned + "=" + weekoffpolicynameemp);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created WeekOff PolicyName ." + WeekOffPolicyPage.getExceptionDesc());
		}

	}

	// MPI_579_WeekOff_Policy_04
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_579_WeekOff_Policy_04()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a Week Off Policy and set it as the \"Default\" policy. Then, create a new employee account. Ensure that the newly created employee is automatically assigned to the default Week Off Policy, confirming that the system correctly applies the default policy to new employees.");

		ArrayList<String> data = initBase.loadExcelData("week_off_policy", currTC,
				"weekoffpolicyname,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,Default");

		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);

		int i = 0;
		String weekoffpolicynameemp = data.get(i++) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		String mailinator = data.get(i++);
		String empid = data.get(i++) + Pramod.generateRandomAlpha(5);
		String empfirstname = data.get(i++) + Pramod.generateRandomAlpha(5);
		String emplastname = data.get(i++) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(i++);
		String empphoneno = data.get(i++) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(i++);
		String reportingto = data.get(i++);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(4) + mailinator;

		String Default = data.get(i++);

		MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
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

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button."
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage." + LeavePolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AddWeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddWeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddWeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyNameTextField(weekoffpolicynameemp)) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy Name Inputted Successfully." + weekoffpolicynameemp);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.DefaultCheckBox()) {

			logResults.createLogs("Y", "PASS", "DefaultCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DefaultCheckBox ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.ThursdayButton()) {

			logResults.createLogs("Y", "PASS", "ThursdayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ThursdayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.SecondHalf()) {

			logResults.createLogs("Y", "PASS", "SecondHalf Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SecondHalf ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.FridayButton()) {

			logResults.createLogs("Y", "PASS", "FridayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FridayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.FullDayButton()) {

			logResults.createLogs("Y", "PASS", "FullDayButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FullDayButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.CreateWeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreateWeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateWeekOffPolicyButton ." + WeekOffPolicyPage.getExceptionDesc());
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

		if (WeekOffPolicyPage.WeekOffPolicyNameInEmps(Default)) {
			String WeekoffPolicyNameAssigned = WeekOffPolicyPage.WeekOffPolicyNames;

			logResults.createLogs("Y", "PASS", "CreateWeekOffPolicy Is Assigned To All Emp Successfully."
					+ WeekoffPolicyNameAssigned + "=" + Default);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created WeekOff PolicyName ." + WeekOffPolicyPage.getExceptionDesc());
		}

	}

	// MPI_581_WeekOff_Policy_06
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_581_WeekOff_Policy_06()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, inactivate the Week Off Policy that was created using specific employee criteria. Once the policy is deactivated, ensure that the employees who were previously assigned to this policy are automatically reassigned to the default Week Off Policy.");

		ArrayList<String> data = initBase.loadExcelData("week_off_policy", currTC,
				"Default");


		String Default = data.get(0);
		String WeekoffPolicyNameAssigned = "";

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

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button."
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage." + LeavePolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicySearchTextField(weekoffpolicynameemp)) {

			logResults.createLogs("Y", "PASS",
					"WeekOffPolicy Name Inputted In Search TextField Successfully." + weekoffpolicynameemp);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy First Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.DeleteButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy DeleteButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffPolicy Delete Button." + WeekOffPolicyPage.getExceptionDesc());
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

		if (WeekOffPolicyPage.WeekOffPolicyNameInEmps()) {
			 WeekoffPolicyNameAssigned = WeekOffPolicyPage.extractedPolicyName;

			logResults.createLogs("Y", "PASS", "CreateWeekOffPolicy Is Assigned To All Emp Successfully."
					+ WeekoffPolicyNameAssigned + "=" + Default);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created WeekOff PolicyName ." + WeekOffPolicyPage.getExceptionDesc());
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

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button."
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage." + LeavePolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicySearchTextField(WeekoffPolicyNameAssigned)) {

			logResults.createLogs("Y", "PASS",
					"WeekOffPolicy Name Inputted In Search TextField Successfully." + WeekoffPolicyNameAssigned);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy First Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCount()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy AssignedEmpLinkCount Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOffPolicy AssignedEmpLinkCount."
					+ WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy AssignedEmpLinkCountFirstRecord Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountSearchTextField(empfirstname)) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy Assigned Employee Name  Successfully." + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy AssignedEmpLinkCountFirstRecord Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
		}
		

	}

	// MPI_582_WeekOff_Policy_07
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_582_WeekOff_Policy_07()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, activate the Week Off Policy that was previously created with specific employee criteria. After activation, ensure that all employees who match the defined criteria are reassigned to this policy. This confirms that the system automatically reassigns employees to the reactivated policy based on their eligibility.");

	

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

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button."
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage." + LeavePolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicySearchTextField(weekoffpolicynameemp)) {

			logResults.createLogs("Y", "PASS",
					"WeekOffPolicy Name Inputted In Search TextField Successfully." + weekoffpolicynameemp);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy First Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffEditButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffEditButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffEditButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.ActiveCheckBox()) {

			logResults.createLogs("Y", "PASS", "ActiveCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ActiveCheckBox." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.CreateWeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreateWeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateWeekOffPolicyButton ." + WeekOffPolicyPage.getExceptionDesc());
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

		if (WeekOffPolicyPage.WeekOffPolicyNameInEmps(weekoffpolicynameemp)) {
			String WeekoffPolicyNameAssigned = WeekOffPolicyPage.WeekOffPolicyNames;

			logResults.createLogs("Y", "PASS", "CreateWeekOffPolicy Is Assigned To All Emp Successfully."
					+ WeekoffPolicyNameAssigned + "=" + weekoffpolicynameemp);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Created WeekOff PolicyName ." + WeekOffPolicyPage.getExceptionDesc());
		}

	}

	// MPI_584_WeekOff_Policy_09
	@Test(enabled = true, priority = 9, groups = { "Smoke" })
	public void MPI_584_WeekOff_Policy_09()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of the search feature by entering relevant keywords or criteria in the search field. Ensure that the search results are displayed accurately, matching the entered input, and that only the relevant records are shown in the results.");

		logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_577_WeekOff_Policy_02 TestCase.");
	}

	// MPI_585_WeekOff_Policy_10
	@Test(enabled = true, priority = 10, groups = { "Smoke" })
	public void MPI_585_WeekOff_Policy_10()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, click on the \"Assign Employees\" count link for the employee criteria-added policy and check the functionality of the search feature in the mini window that appears. Ensure that the search works correctly using various options such as First Name, Last Name, Employee ID, Enroll ID, Designation, Department, and Office. Verify that relevant records are displayed based on the entered search input.");


		MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
		
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

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button."
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage." + LeavePolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicySearchTextField(weekoffpolicynameemp)) {

			logResults.createLogs("Y", "PASS",
					"WeekOffPolicy Name Inputted In Search TextField Successfully." + weekoffpolicynameemp);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy First Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCount()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy AssignedEmpLinkCount Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOffPolicy AssignedEmpLinkCount."
					+ WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy AssignedEmpLinkCountFirstRecord Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountSearchTextField(empfirstname)) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy Assigned Employee Name  Successfully." + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy AssignedEmpLinkCountFirstRecord Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
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

		if (WeekOffPolicyPage.AssignedEmpLinkCountSearchTextField(emplastname)) {

			logResults.createLogs("Y", "PASS",
					"WeekOffPolicy Assigned Employee Last Name  Successfully." + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying  WeekOffPolicy AssignedEmp Last Name."
					+ WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy AssignedEmpLinkCountFirstRecord Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountSearchTextField(empid)) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy Assigned Employee Id Entered Successfully." + empid);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  WeekOffPolicy AssignedEmp Id." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy AssignedEmpLinkCountFirstRecord Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
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

		if (WeekOffPolicyPage.AssignedEmpLinkCountSearchTextField(departmentname)) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy Assigned Employee Name  Successfully." + departmentname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy AssignedEmpLinkCountFirstRecord Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountSearchTextField(designationname)) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy Assigned Employee Name  Successfully." + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy AssignedEmpLinkCountFirstRecord Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown." + LeavePolicyPage.getExceptionDesc());
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

		if (LeavePolicyPage.OfficeCheckBox()) {

			logResults.createLogs("Y", "PASS", "OfficeCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On OfficeCheckBox." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.SearchDropDown()) {

			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchDropDown." + LeavePolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountSearchTextField(officename)) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy Assigned Employee Name  Successfully." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.AssignedEmpLinkCountFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy AssignedEmpLinkCountFirstRecord Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  WeekOffPolicy AssignedEmp." + WeekOffPolicyPage.getExceptionDesc());
		}

	}
	
	
	// MPI_848_WeekOff_Policy_12
		@Test(enabled = true, priority = 0, groups = { "Smoke", "AddMaster" })
		public void MPI_848_WeekOff_Policy_12()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, create an policy and add emp criteria to the policy and ensure matching criteria employees are assigned to the created policy");

			ArrayList<String> data = initBase.loadExcelData("week_off_policy", currTC,
					"password,captcha,weekoffpolicyname");

			MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
			MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);

			
			int i = 0;
			
			String password = data.get(i++);
			String captcha = data.get(i++);
			
		String	weekoffpolicyname = data.get(i++) + initBase.executionRunTime;

			MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
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
				String WeekOffPolicyName = Utils.propsReadWrite("data/addmaster.properties", "get", "WeekOffPolicyName", "");
				if (WeekOffPolicyName.equals("pass")) {
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

			if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

				logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button."
						+ LeavePolicyPage.getExceptionDesc());
			}

			if (LeavePolicyPage.LeavePolicyPageLoaded()) {

				logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Loading LeavePolicyPage." + LeavePolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.WeekOffPolicyButton()) {

				logResults.createLogs("Y", "PASS", "WeekOffPolicyButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.AddWeekOffPolicyButton()) {

				logResults.createLogs("Y", "PASS", "AddWeekOffPolicyButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddWeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.WeekOffPolicyNameTextField(weekoffpolicyname)) {

				logResults.createLogs("Y", "PASS", "WeekOffPolicy Name Inputted Successfully." + weekoffpolicyname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.SaturdayOption()) {

				logResults.createLogs("Y", "PASS", "ThursdayButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On ThursdayButton ." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.FullDayButton()) {

				logResults.createLogs("Y", "PASS", "SecondHalf Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On SecondHalf ." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.SundayOption()) {

				logResults.createLogs("Y", "PASS", "SundayButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On FridayButton ." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.FullDayButton()) {

				logResults.createLogs("Y", "PASS", "FullDayButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On FullDayButton ." + WeekOffPolicyPage.getExceptionDesc());
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
				logResults.createLogs("Y", "PASS", "Successfully Clicked On ApplyButton." );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Apply Button ." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.CreateWeekOffPolicyButton()) {

				logResults.createLogs("Y", "PASS", "CreateWeekOffPolicyButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CreateWeekOffPolicyButton ." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.YesButton()) {
				Utils.propsReadWrite("data/addmaster.properties", "set", "WeekOffPolicyName", "pass");
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

