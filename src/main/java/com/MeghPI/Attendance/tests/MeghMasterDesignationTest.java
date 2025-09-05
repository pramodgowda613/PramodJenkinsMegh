package com.MeghPI.Attendance.tests;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterDesignationPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghShiftPage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class MeghMasterDesignationTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	private String designationname = "";
	private String designationnamess = "";

	private String cmpcode = "";
	private String Emailid = "";

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
		designationname ="AutoDESN" + initBase.executionRunTime;
	}

	// MPI_232_Designation_01
	@Test(enabled = true, priority = 1, groups = { "Smoke", "AddMaster" })
	public void MPI_232_Designation_01() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a new designation and ensure that the created designation appears in the 'Designation' dropdown within the Employee Manage Profile section.");

		ArrayList<String> data = initBase.loadExcelData("company_designation", currTC,
				"password,captcha,designationname,designationdescription");

		
		String password = data.get(0);
		String captcha = data.get(1);
	

		designationname = data.get(2) + initBase.executionRunTime;
		String designationdescription = data.get(3) + initBase.executionRunTime;
		
		if (initBase.stopNewData) { // Tapan 5 July 25
			String DesignationName = Utils.propsReadWrite("data/addmaster.properties", "get", "DesignationName", "");
			if (DesignationName.equals("pass")) {
				logResults.createLogs("Y", "INFO", "Skip Adding New Data. Continue with Old Test Data.");
				throw new SkipException("Skip Adding New Data. Continue with Old Test Data.");
			}
		}

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterDesignationPage DesignationPage = new MeghMasterDesignationPage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
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

		if (DesignationPage.DesignationButon()) {
			logResults.createLogs("Y", "PASS", "Designation Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Button : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Designation Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Loading Designation Page : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.AddDesignationButon()) {
			logResults.createLogs("Y", "PASS", "Add Designation Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Designation Button : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationTextField(designationname)) {
			logResults.createLogs("Y", "PASS", " Designation Entered In The Input Successfully: " + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Designation Name : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationTextAreaField(designationdescription)) {
			logResults.createLogs("Y", "PASS",
					" Designation Description Entered In The Input Successfully: " + designationdescription);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Designation Description Name : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.AddDesignationSaveButton()) {
			Utils.propsReadWrite("data/addmaster.properties", "set", "DesignationName", "pass");
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Designation Save Button : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking Designation Save Button : " + DesignationPage.getExceptionDesc());
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

		Thread.sleep(2000);

		if (DesignationPage.ManageProfileDesignationDropdown()) {
			logResults.createLogs("Y", "PASS", "Manage Profile Designation DropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Dropdown : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.ManageProfileDesignationDropdownSearchField(designationname)) {
			logResults.createLogs("Y", "PASS",
					" Created Designation Name Entered In The Input Successfully: " + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Created Designation Name : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.ManageProfileDesignationDropdownSearchResult()) {
			
			logResults.createLogs("Y", "PASS", "Created Designation Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Created Name : " + DesignationPage.getExceptionDesc());
		}
		Thread.sleep(2000);

	}

	// MPI_233_Designation_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_233_Designation_02() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the designation and confirm that the update is successful by searching for the updated designation");

		ArrayList<String> data = initBase.loadExcelData("company_designation", currTC,
				"password,captcha,designationname");

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String SearchResult = "";

		String designationnames = data.get(2) + Pramod.generateRandomAlpha(6);
		 designationnamess = designationnames + Pramod.generateRandomNumber(3);

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterDesignationPage DesignationPage = new MeghMasterDesignationPage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		
MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
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

		if (DesignationPage.DesignationButon()) {
			logResults.createLogs("Y", "PASS", "Designation Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Button : " + DesignationPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (DesignationPage.DesignationPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Designation Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Loading Designation Page : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.AddDesignationButon()) {
			logResults.createLogs("Y", "PASS", "Add Designation Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Designation Button : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationTextField(designationnames)) {
			logResults.createLogs("Y", "PASS", " Designation Entered In The Input Successfully: " + designationnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Designation Name : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.AddDesignationSaveButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Designation Save Button : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking Designation Save Button : " + DesignationPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (DesignationPage.DesignationSearchTextField(designationnames)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Enter The Designation Name On Search Text Field : " + designationnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Designation Name : " + DesignationPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (DesignationPage.Designation3dots2ndRow()) {
			logResults.createLogs("Y", "PASS", "Designation 3 Dots  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation 3 Dots : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationEdit2ndRow()) {
			logResults.createLogs("Y", "PASS", "Designation Edit Buton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Edit Button : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationTextField(designationnamess)) {
			logResults.createLogs("Y", "PASS", " Designation Entered In The Input Successfully: " + designationnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Designation Name : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.AddDesignationSaveButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Designation Save Button : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking Designation Save Button : " + DesignationPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (DesignationPage.DesignationSearchTextField(designationnamess)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Enter The Designation Name On Search Text Field : " + designationnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Designation Name : " + DesignationPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (DesignationPage.Designation1stRowName()) {
			SearchResult = DesignationPage.Designation1stRecordName;
			logResults.createLogs("Y", "PASS", "Successfully Display The Searched Designation : " + SearchResult);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Designation Name : " + DesignationPage.getExceptionDesc());
		}

		Thread.sleep(3000);

	}

	// MPI_234_Designation_03
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_234_Designation_03() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, attempt to create a duplicate designation and ensure that a proper error message is displayed.");

		ArrayList<String> data = initBase.loadExcelData("company_designation", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);
		String Firstdesignation = "";

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterDesignationPage DesignationPage = new MeghMasterDesignationPage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		
MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
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

		if (DesignationPage.DesignationButon()) {
			logResults.createLogs("Y", "PASS", "Designation Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Button : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Designation Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Loading Designation Page : " + DesignationPage.getExceptionDesc());
		}

		

		if (DesignationPage.AddDesignationButon()) {
			logResults.createLogs("Y", "PASS", "Add Designation Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Designation Button : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationTextField(designationnamess)) {
			logResults.createLogs("Y", "PASS", " Designation Entered In The Input Successfully: " + Firstdesignation);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Designation Name : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.AddDesignationSaveButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Designation Save Button : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking Designation Save Button : " + DesignationPage.getExceptionDesc());
		}
	

		if (OfficePage.DuplicateErrorMessage()) {
			logResults.createLogs("Y", "PASS", " Successfully Displayed Error Message : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message : " + OfficePage.getExceptionDesc());
		}
		
	}

	// MPI_245_Designation_14
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_245_Designation_14() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, disable the Designation and ensure disabled Designation name is't displayed in the employee manage profile \"Designation\" dropdown");

		ArrayList<String> data = initBase.loadExcelData("company_designation", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String Firstdesignation = "";

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterDesignationPage DesignationPage = new MeghMasterDesignationPage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
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

		if (DesignationPage.DesignationButon()) {
			logResults.createLogs("Y", "PASS", "Designation Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Button : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Designation Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Loading Designation Page : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationSearchTextField(designationnamess)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Enter The Designation Name On Search Text Field : " + designationnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Designation Name : " + DesignationPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (DesignationPage.DesignationToggleSwitch()) {
			Firstdesignation = DesignationPage.Designation1stRecordName;
			logResults.createLogs("Y", "PASS",
					"Successfully Turn Off The Designation Toggle Switch : " + Firstdesignation);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Toggle Switch : " + DesignationPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button : " + OfficePage.getExceptionDesc());
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

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile : "
					+ DepartmentPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (DesignationPage.ManageProfileDesignationDropdown()) {
			logResults.createLogs("Y", "PASS", "Manage Profile Designation DropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Dropdown : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.ManageProfileDesignationDropdownSearchField(designationnamess)) {
			logResults.createLogs("Y", "PASS",
					" Disabled Designation Name Entered In The Input Successfully: " + designationnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Disabled Designation Name : " + DesignationPage.getExceptionDesc());
		}

		if (DepartmentPage.DropDownErrorMsg()) {
			logResults.createLogs("Y", "PASS", "DropDownErrorMsg Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message: " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

	}

	// MPI_246_Designation_15
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_246_Designation_15() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enable a previously disabled Designation record and ensure that, after enabling, the enabled designation name appears in the 'designation' dropdown which is in Employee Manage Profile section.");

		ArrayList<String> data = initBase.loadExcelData("company_designation", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String Firstdesignation = "";

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterDesignationPage DesignationPage = new MeghMasterDesignationPage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
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

		if (DesignationPage.DesignationButon()) {
			logResults.createLogs("Y", "PASS", "Designation Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Button : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Designation Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Loading Designation Page : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationSearchTextField(designationnamess)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Enter The Designation Name On Search Text Field : " + designationnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Designation Name : " + DesignationPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (DesignationPage.DesignationToggleSwitch()) {
			Firstdesignation = DesignationPage.Designation1stRecordName;
			logResults.createLogs("Y", "PASS",
					"Successfully Turn On The Designation Toggle Switch : " + Firstdesignation);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Toggle Switch : " + DesignationPage.getExceptionDesc());
		}
		Thread.sleep(2000);
		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button : " + OfficePage.getExceptionDesc());
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

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile : "
					+ DepartmentPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (DesignationPage.ManageProfileDesignationDropdown()) {
			logResults.createLogs("Y", "PASS", "Manage Profile Designation DropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Dropdown : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.ManageProfileDesignationDropdownSearchField(designationnamess)) {
			logResults.createLogs("Y", "PASS",
					" Disabled Designation Name Entered In The Input Successfully: " + designationnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Disabled Designation Name : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.ManageProfileDesignationDropdownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Designation Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Created Name : " + DesignationPage.getExceptionDesc());
		}
		Thread.sleep(2000);

	}

	// MPI_247_Designation_16
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_247_Designation_16() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("To verify this, test the search functionality.");

		ArrayList<String> data = initBase.loadExcelData("company_designation", currTC,
				"password,captcha,team");

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String SearchResult = "";

		String team = data.get(2);

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterDesignationPage DesignationPage = new MeghMasterDesignationPage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
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

		if (DesignationPage.DesignationButon()) {
			logResults.createLogs("Y", "PASS", "Designation Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Button : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Designation Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Loading Designation Page : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.Designation1stRowName()) {
			SearchResult = DesignationPage.Designation1stRecordName;
			logResults.createLogs("Y", "PASS",
					"Successfully Display The Searched Team Name Assigned Designation : " + SearchResult);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Designation Name Record : " + DesignationPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationSearchTextField(SearchResult)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Enter The Team Name In  Search Text Field : " + SearchResult);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Team Name : " + DesignationPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (DesignationPage.Designation1stRowName()) {
			SearchResult = DesignationPage.Designation1stRecordName;
			logResults.createLogs("Y", "PASS",
					"Successfully Display The Searched Team Name Assigned Designation : " + SearchResult);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Designation Name Record : " + DesignationPage.getExceptionDesc());
		}

		Thread.sleep(3000);

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
