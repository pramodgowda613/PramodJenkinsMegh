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

import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterAttendanceStatusPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;


import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class MeghMasterAttendanceStatusTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	

	private String cmpcode = "";
	private String Emailid = "";
	
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
		
		
	}


	// MPI_321_AttendanceStatus_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_321_AttendanceStatus_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the attendance status and ensure the updated status is reflected in the list by validating it using the custÂ status.");

		ArrayList<String> data = initBase.loadExcelData("company_attendancestatus", currTC,
				"password,captcha,custstatus,Attendancestatusdescription");
		
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
		String custstatus = data.get(2) + Pramod.generateRandomAlpha(3);
		String Attendancestatusdescription = data.get(3) + initBase.executionRunTime;
		String hdh = "";
		String updatedcust = "";

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterAttendanceStatusPage AttendanceStatusPage = new MeghMasterAttendanceStatusPage(driver);

		MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
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

		if (AttendanceStatusPage.AttendanceStatusButton()) {
			logResults.createLogs("Y", "PASS", "AttendanceStatusButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AttendanceStatusButton ." + AttendanceStatusPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.AttendanceStatusPageLoaded()) {
			logResults.createLogs("Y", "PASS", "AttendanceStatus Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Loading AttendanceStatus Page ." + AttendanceStatusPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.HDHStatusEditing()) {
			hdh = AttendanceStatusPage.HDHrow;
			logResults.createLogs("Y", "PASS", "HDH Row Name Fetched  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Fetching HDH Row ." + AttendanceStatusPage.getExceptionDesc());
		}
		

		if (DepartmentPage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Department Search DropDown  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking DepartmentSearchDropDown ." + DepartmentPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.CustStatusCheckBox()) {
			logResults.createLogs("Y", "PASS", "Cust Status CheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Cust Status CheckBox ." + AttendanceStatusPage.getExceptionDesc());
		}

		if (DepartmentPage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Department Search DropDown  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking DepartmentSearchDropDown ." + DepartmentPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.AttendanceSearchTextField(hdh)) {

			logResults.createLogs("Y", "PASS", "Fetched Input Entered In Search TextField Successfully." + hdh);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Fetched Row Name ." + AttendanceStatusPage.getExceptionDesc());
		}
	

		if (AttendanceStatusPage.AttendanceStatus3Dots()) {

			logResults.createLogs("Y", "PASS", "Attendance Status 3Dots  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Attendance Status 3Dots ." + AttendanceStatusPage.getExceptionDesc());
		}

	
		if (AttendanceStatusPage.AttendanceStatusEditButton()) {

			logResults.createLogs("Y", "PASS", "Attendance Status Edit Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Attendance Status Edit Button ."
					+ AttendanceStatusPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.CustStatusTextField(custstatus)) {

			logResults.createLogs("Y", "PASS", "Cust Status Entered Successfully." + custstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Cust Status ." + AttendanceStatusPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.DescriptionTextField(Attendancestatusdescription)) {

			logResults.createLogs("Y", "PASS",
					"Attendance Description Entered Successfully." + Attendancestatusdescription);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Description ." + AttendanceStatusPage.getExceptionDesc());
		}
		

		if (AttendanceStatusPage.AttendanceStatusSaveButton()) {

			logResults.createLogs("Y", "PASS", " AttendanceStatusSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AttendanceStatusSaveButton ." + AttendanceStatusPage.getExceptionDesc());
		}
		

		if (AttendanceStatusPage.AttendanceSearchTextField(custstatus)) {

			logResults.createLogs("Y", "PASS",
					"Updated Cust Status Name Inputted In Search Field  Successfully." + custstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab ." + AttendanceStatusPage.getExceptionDesc());
		}

		
		if (AttendanceStatusPage.UpdatedCustStatusRowName()) {
			updatedcust = AttendanceStatusPage.updatedcuststatus;

			logResults.createLogs("Y", "PASS",
					"Updated Record Displayed Successfully." + updatedcust + "=" + custstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Updated Cust Status Row ." + AttendanceStatusPage.getExceptionDesc());
		}


	}

	// MPI_322_AttendanceStatus_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_322_AttendanceStatus_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the description and ensure it is properly updated by validating it through a search using the description.");

		ArrayList<String> data = initBase.loadExcelData("company_attendancestatus", currTC,
				"custstatus,Attendancestatusdescription");

		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		

		String custstatus = data.get(0) + Pramod.generateRandomAlpha(3);
		String Attendancestatusdescription = data.get(1) + initBase.executionRunTime;
		String hdh = "";
		
		String descrow = "";

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterAttendanceStatusPage AttendanceStatusPage = new MeghMasterAttendanceStatusPage(driver);
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

		if (AttendanceStatusPage.AttendanceStatusButton()) {
			logResults.createLogs("Y", "PASS", "AttendanceStatusButton  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AttendanceStatusButton ." + AttendanceStatusPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.AttendanceStatusPageLoaded()) {
			logResults.createLogs("Y", "PASS", "AttendanceStatus Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Loading AttendanceStatus Page ." + AttendanceStatusPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.HDHStatusEditing()) {
			hdh = AttendanceStatusPage.HDHrow;
			logResults.createLogs("Y", "PASS", "HDH Row Name Fetched  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Fetching HDH Row ." + AttendanceStatusPage.getExceptionDesc());
		}
		

		if (DepartmentPage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Department Search DropDown  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking DepartmentSearchDropDown ." + DepartmentPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.CustStatusCheckBox()) {
			logResults.createLogs("Y", "PASS", "Cust Status CheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Cust Status CheckBox ." + AttendanceStatusPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.DescriptionCheckBox()) {
			logResults.createLogs("Y", "PASS", "DescriptionCheckBox CheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On DescriptionCheckBox CheckBox ."
					+ AttendanceStatusPage.getExceptionDesc());
		}

		if (DepartmentPage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Department Search DropDown  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking DepartmentSearchDropDown ." + DepartmentPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.AttendanceSearchTextField(hdh)) {

			logResults.createLogs("Y", "PASS", "Fetched Input Entered In Search TextField Successfully." + hdh);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Fetched Row Name ." + AttendanceStatusPage.getExceptionDesc());
		}
		

		if (AttendanceStatusPage.AttendanceStatus3Dots()) {

			logResults.createLogs("Y", "PASS", "Attendance Status 3Dots  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Attendance Status 3Dots ." + AttendanceStatusPage.getExceptionDesc());
		}
	
		if (AttendanceStatusPage.AttendanceStatusEditButton()) {

			logResults.createLogs("Y", "PASS", "Attendance Status Edit Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Attendance Status Edit Button ."
					+ AttendanceStatusPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.CustStatusTextField(custstatus)) {

			logResults.createLogs("Y", "PASS", "Cust Status Entered Successfully." + custstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Cust Status ." + AttendanceStatusPage.getExceptionDesc());
		}

		if (AttendanceStatusPage.DescriptionTextField(Attendancestatusdescription)) {

			logResults.createLogs("Y", "PASS",
					"Attendance Description Entered Successfully." + Attendancestatusdescription);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Description ." + AttendanceStatusPage.getExceptionDesc());
		}
		

		if (AttendanceStatusPage.AttendanceStatusSaveButton()) {

			logResults.createLogs("Y", "PASS", " AttendanceStatusSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AttendanceStatusSaveButton ." + AttendanceStatusPage.getExceptionDesc());
		}
		

		if (AttendanceStatusPage.AttendanceSearchTextField(custstatus)) {

			logResults.createLogs("Y", "PASS",
					"Updated Cust Status Name Inputted In Search Field  Successfully." + custstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab ." + AttendanceStatusPage.getExceptionDesc());
		}

	
		if (AttendanceStatusPage.DescriptionRow()) {
			descrow = AttendanceStatusPage.desc;

			logResults.createLogs("Y", "PASS",
					"Updated Record Displayed Successfully." + descrow + "=" + Attendancestatusdescription);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Updated Cust Status Row ." + AttendanceStatusPage.getExceptionDesc());
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
