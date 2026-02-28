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
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentMappingPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterTeamPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class MeghMasterDepartmentMappingTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	private String officename = "";
	private String officenames = "";
	private String deptnames = "";
	private String emplastname = "";
	private String empfirstname = "";
	private String officenameofdept = "";


	private String cmpcode = "";
	private String Emailid = "";
	private String entityname = "";

	@Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) {  
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
	}

	// MPI_203_Department_10
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_203_Department_10()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, disable the company location and check whether the disabled location is not displayed in the \"assign office\" dropdown in the department mapping form. ");

		ArrayList<String> data = initBase.loadExcelData("Master_departmentmapping", currTC, "password,captcha,officename,state,longitude");

		
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);

		 officenameofdept = data.get(2)  + Pramod.generateRandomAlpha(3);
		String state = data.get(3);
		String longitude = data.get(4);

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghLoginPage MeghloginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddOfficeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddOfficeButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddOfficeButton ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeName(officenameofdept)) {
			logResults.createLogs("Y", "PASS", " OfficeName Entered In the Office Name TextField ." + officenameofdept);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Office Name ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeTypeDropdown()) {
			logResults.createLogs("Y", "PASS", " Office Type Selected In the Office Type Dropdown .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The Office Type ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CountryDropdown()) {
			logResults.createLogs("Y", "PASS", " Country Selected In the Country  Dropdown .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The Country ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.StateDropdown(state)) {
			logResults.createLogs("Y", "PASS", " State Selected In the State  Dropdown ." + state);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting The State ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.GeoFencingRange(longitude)) {
			logResults.createLogs("Y", "PASS", " Geo Fencing Range Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Geo Fencing Range ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.LatitudeTextField(longitude)) {
			logResults.createLogs("Y", "PASS", " Latitude Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Latitude ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.LongitudeTextField(longitude)) {
			logResults.createLogs("Y", "PASS", " Longitude Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Longitude ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationSaveButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
		}

		

		if (OfficePage.OfficeSearchTextField(officenameofdept)) {
			logResults.createLogs("Y", "PASS", "OfficeType Entered In The TextField Successfully." + officenameofdept);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeType In Search Text Field ." + OfficePage.getExceptionDesc());
		}

		

		if (OfficePage.FirstCompanyRecordToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " Successfully Turn Off The Toggle Switch .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Toggle Switch ." + OfficePage.getExceptionDesc());
		}

		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button ." + OfficePage.getExceptionDesc());
		}

		
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On AddDepartmentMapping Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddDepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentMappingpage.OfficeDropDown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On OfficeDropDown Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On OfficeDropDown Button ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.OfficeDropDownSearchField(officenameofdept)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted OfficeName.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeName ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentPage.DropDownErrorMsg()) {
			logResults.createLogs("Y", "PASS", "HODDropDownErrorMsg Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message." + DepartmentPage.getExceptionDesc());
		}
	}

	// MPI_202_Department_09
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_202_Department_09()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enable the inactive office name and ensure that, after activation, the office name is displayed in the 'Assign Office' dropdown of the 'Add Department Location Mapping' form");

		//ArrayList<String> data = initBase.loadExcelData("Master_departmentmapping", currTC,"password,captcha");


		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);


		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficeSearchTextField(officenameofdept)) {
			logResults.createLogs("Y", "PASS", "OfficeType Entered In The TextField Successfully." + officenameofdept);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeType In Search Text Field ." + OfficePage.getExceptionDesc());
		}



		if (OfficePage.FirstCompanyRecordToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " Successfully Turn Off The Toggle Switch .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Toggle Switch ." + OfficePage.getExceptionDesc());
		}

		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button ." + OfficePage.getExceptionDesc());
		}


		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
		}

		

		if (DepartmentPage.DepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On AddDepartmentMapping Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddDepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentMappingpage.OfficeDropDown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On OfficeDropDown Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On OfficeDropDown Button ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.OfficeDropDownSearchField(officenameofdept)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted OfficeName." + officenameofdept);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeName ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.OfficeDropDownSearchResult(officenameofdept)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted OfficeName.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeName ." + DepartmentMappingpage.getExceptionDesc());
		}

	}

	// MPI_514_Department_mapping_06
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_514_Department_mapping_06()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this Select a department, select an office, and assign a Head of Department. Then, map the department with the selected office. After mapping, navigate to the employee'sÂ Manage ProfileÂ page. Under theÂ DepartmentÂ dropdown, search for the mapped department and ensure that it is displayed. ");

		ArrayList<String> data = initBase.loadExcelData("Master_departmentmapping", currTC,
				"deptname,officename,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,state,longitude");

		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

		int i = 0;

		deptnames = data.get(i++) + initBase.executionRunTime + Pramod.generateRandomAlpha(4);
		officenames = data.get(i++) + Pramod.generateRandomAlpha(5);
		String mailinator = data.get(i++);
		String empid = data.get(i++) + Pramod.generateRandomAlpha(6);
		 empfirstname = data.get(i++) + Pramod.generateRandomAlpha(5);
		emplastname = data.get(i++) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(i++);
		String empphoneno = data.get(i++) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(i++);
		String reportingto = data.get(i++);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(3) + mailinator;
	
		String state = data.get(i++);
		String longitude = data.get(i++);

		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);
		
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddOfficeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddOfficeButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddOfficeButton ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeName(officenames)) {
			logResults.createLogs("Y", "PASS", " OfficeName Entered In the Office Name TextField ." + officenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Office Name ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeTypeDropdown()) {
			logResults.createLogs("Y", "PASS", " Office Type Selected In the Office Type Dropdown .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The Office Type ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CountryDropdown()) {
			logResults.createLogs("Y", "PASS", " Country Selected In the Country  Dropdown .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The Country ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.StateDropdown(state)) {
			logResults.createLogs("Y", "PASS", " State Selected In the State  Dropdown ." + state);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting The State ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.GeoFencingRange(longitude)) {
			logResults.createLogs("Y", "PASS", " Geo Fencing Range Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Geo Fencing Range ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.LatitudeTextField(longitude)) {
			logResults.createLogs("Y", "PASS", " Latitude Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Latitude ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.LongitudeTextField(longitude)) {
			logResults.createLogs("Y", "PASS", " Longitude Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Longitude ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationSaveButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
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
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Add Department Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Tab ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentName(deptnames)) {
			logResults.createLogs("Y", "PASS", "Department Name Entered Successfully." + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentSaveButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Add Department Save Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Save Button ." + DepartmentPage.getExceptionDesc());
		}
	
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On AddDepartmentMapping Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddDepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentMappingpage.DepartmentSelectDropDown(deptnames)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected Department." + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Department ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.OfficeSelectDropDown(officenames)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected OfficeName." + officenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting OfficeName ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.AddDeptMappingSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddDeptMappingSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddDeptMappingSaveButton." + DepartmentMappingpage.getExceptionDesc());
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

		if (EmployeePage.CompanyLocationDropdown(officenames)) {
			logResults.createLogs("Y", "PASS", " CompanyLocation Selected From The DropDown ." + officenames);
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
		

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile ."
					+ DepartmentPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeptDropdown(deptnames)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Dept ." + AttendancePolicyPage.getExceptionDesc());
		}


	}

	// MPI_510_Department_mapping_02
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_510_Department_mapping_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Attempt to inactivate a mapped department that is already mapped to an teamÂ  Ensure that aÂ proper error message is displayed, preventing the inactivation. ");

		//ArrayList<String> data = initBase.loadExcelData("Master_departmentmapping", currTC, "password,captcha");

		
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);
	
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentSearchIcon(deptnames)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Input The Updated Department Name In The Search Text Field." + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting  Department Name In Search Text Field ."
					+ DepartmentPage.getExceptionDesc());
		}
		

		if (DepartmentPage.DepartmentToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " Successfully Turn Off The Toggle Switch .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Toggle Switch ." + DepartmentPage.getExceptionDesc());
		}
		

		if (DepartmentPage.DepartmentToggleSwitchConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Ok Button ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentMappingpage.DepartmentMappingErrorMessage()) {
			logResults.createLogs("Y", "PASS", " Successfully Displayed Error Message .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message ." + DepartmentMappingpage.getExceptionDesc());
		}
	}

	// MPI_515_Department_mapping_07
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_515_Department_mapping_07()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the previously mapped department by changing its mapped office to 'Mantra Office'. Then, navigate to the employee profile who is mapped with mantra office and check whether updated dept is displayed in the dropdown");

	//	ArrayList<String> data = initBase.loadExcelData("Master_departmentmapping", currTC, "password,captcha");

		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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
		
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On AddDepartmentMapping Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddDepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentMappingpage.DepartmentSelectDropDown(deptnames)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected Department." + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Department ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.OfficeSelectDropDown(officenames)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected OfficeName." + officenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting OfficeName ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.AddDeptMappingSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddDeptMappingSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddDeptMappingSaveButton." + DepartmentMappingpage.getExceptionDesc());
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
		

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile ."
					+ DepartmentPage.getExceptionDesc());
		}
		

		if (DepartmentPage.DepartmentDropdown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Dept DropDown.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Dept DropDown ." + DepartmentPage.getExceptionDesc());
		}
		

		if (DepartmentPage.DepartmentDropdownSearchTextField(deptnames)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Dept Name." + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Dept Name ." + DepartmentPage.getExceptionDesc());
		}
		

		if (DepartmentPage.DepartmentDropdownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Successfully Displayed Dept Name.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Dept Name ." + DepartmentPage.getExceptionDesc());
		}
	}

	// MPI_511_Department_mapping_03
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_511_Department_mapping_03()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this:Check the functionality of the search feature by selecting different search options such asÂ Office Name,Â Employee First Name,Â Last Name, andÂ Department Name, and ensure the relevant employee records are displayed correctly based on the selected criteria. ");

	//	ArrayList<String> data = initBase.loadExcelData("Master_departmentmapping", currTC, "password,captcha");

		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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
		
		
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentMappingpage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchDropDown.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On DepartmentMapping SearchDropDown ."
					+ DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.OfficeNameCheckBox()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected OfficeNameCheckBox.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selected OfficeNameCheckBox ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.FirstNameCheckBox()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected FirstNameCheckBox.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selected FirstNameCheckBox ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchDropDown.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On DepartmentMapping SearchDropDown ."
					+ DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.SearchTextField(officename)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted OfficeName In Search TextField." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeName ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.OfficeNameSearchResult(officename)) {
			logResults.createLogs("Y", "PASS", "Successfully Validated OfficeName ." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating OfficeName ." + DepartmentMappingpage.getExceptionDesc());
		}


		if (DepartmentMappingpage.SearchTextField(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Inputted Emp FirstName In Search TextField." + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Emp First Name ." + DepartmentMappingpage.getExceptionDesc());
		}
		

		if (DepartmentMappingpage.HODNameSearchResult(empfirstname)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Validated HOD FirstName With Search Result." + empfirstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Emp First Name ." + DepartmentMappingpage.getExceptionDesc());
		}
		

		if (DepartmentMappingpage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchDropDown.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On DepartmentMapping SearchDropDown ."
					+ DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.OfficeNameCheckBox()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected OfficeNameCheckBox.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selected OfficeNameCheckBox ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.LastNameCheckBox()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected LastNameCheckBox.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selected LastNameCheckBox ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchDropDown.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On DepartmentMapping SearchDropDown ."
					+ DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.SearchTextField(emplastname)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Inputted Emp LastName In Search TextField." + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Emp LastName ." + DepartmentMappingpage.getExceptionDesc());
		}
		

		if (DepartmentMappingpage.HODLastNameSearchResult(emplastname)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Validated HOD LastName With Search Result." + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Emp Last Name ." + DepartmentMappingpage.getExceptionDesc());
		}
	}

	// MPI_512_Department_mapping_04
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_512_Department_mapping_04()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, disable the mapped department, then navigate to the employee profile. In the department dropdown, ensure that the disabled department is not displayed, provided it is not assigned to any employee.â€‹â€‹â€‹â€‹â€‹â€‹â€‹  ");

		//ArrayList<String> data = initBase.loadExcelData("Master_departmentmapping", currTC, "password,captcha");

		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);
		
		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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
		
		
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentMappingpage.SearchTextField(deptnames)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted DeptName In Search TextField." + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Dept Name ." + DepartmentMappingpage.getExceptionDesc());
		}
		

		if (DepartmentMappingpage.MappedDeptToggleSwitch()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Mapped Dept Toggle Switch.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Mapped Dept Toggle Switch ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentToggleSwitchConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Ok Button ." + DepartmentPage.getExceptionDesc());
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
		

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile ."
					+ DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentDropdown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Dept DropDown.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Dept DropDown ." + DepartmentPage.getExceptionDesc());
		}


		if (DepartmentPage.DepartmentDropdownSearchTextField(deptnames)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Dept Name." + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Dept Name ." + DepartmentPage.getExceptionDesc());
		}
		

		if (TeamPage.NoResultFoundMsg()) {
			logResults.createLogs("Y", "PASS",
					"Disabled Team Name Isn't Displayed And Error Msg Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Displaying Error Msg ." + TeamPage.getExceptionDesc());
		}
	
	}

	// MPI_513_Department_mapping_05
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_513_Department_mapping_05()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enable the previously disabled department that is mapped to an office, and ensure that after enabling, the department is displayed in the department dropdown of the employee profile. ");

		//ArrayList<String> data = initBase.loadExcelData("Master_departmentmapping", currTC,"password,captcha");

		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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
		
		
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentMappingpage.SearchTextField(deptnames)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted DeptName In Search TextField." + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Dept Name ." + DepartmentMappingpage.getExceptionDesc());
		}
		

		if (DepartmentMappingpage.MappedDeptToggleSwitch()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Mapped Dept Toggle Switch.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Mapped Dept Toggle Switch ." + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentToggleSwitchConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Ok Button ." + DepartmentPage.getExceptionDesc());
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
		

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile ."
					+ DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentDropdown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Dept DropDown.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Dept DropDown ." + DepartmentPage.getExceptionDesc());
		}
		

		if (DepartmentPage.DepartmentDropdownSearchTextField(deptnames)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Dept Name." + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Dept Name ." + DepartmentPage.getExceptionDesc());
		}
		

		if (DepartmentPage.DepartmentDropdownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Successfully Displayed Dept Name.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Dept Name ." + DepartmentPage.getExceptionDesc());
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
