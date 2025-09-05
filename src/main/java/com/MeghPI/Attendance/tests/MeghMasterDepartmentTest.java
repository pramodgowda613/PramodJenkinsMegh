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

import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterTeamPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class MeghMasterDepartmentTest {
	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	private String deptnameN = "";

	private String cmpcode = "";
	private String Emailid = "";

	@Parameters({ "device" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device) {  
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
	}

	// MPI_188_Department_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_188_Department_01() throws SkipException, InterruptedException {
		
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, add a new department and check whether the created department is displayed in the 'Department' dropdown during department mapping in \"add department mapping\" module");

		ArrayList<String> data = initBase.loadExcelData("company_department", currTC,
				"password,captcha,message,deptname,deptdescription,headofdepartment");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		int i=0;
		String password = data.get(i);
		String captcha = data.get(++i);
		deptnameN = data.get(++i) + Pramod.generateRandomAlpha(4);
		String deptdescription = data.get(++i) + initBase.executionRunTime;
		String headofdepartment = data.get(++i);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}
		
		if (DepartmentPage.DepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		

		if (DepartmentPage.DepartmentPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Department Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Department Page : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Add Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentName(deptnameN)) {
			logResults.createLogs("Y", "PASS", "Department Name Entered Successfully: " + deptnameN);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentDescription(deptdescription)) {
			logResults.createLogs("Y", "PASS", "Department Description Entered Successfully: " + deptdescription);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Description : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentSaveButton()) {
			logResults.createLogs("Y", "PASS",
					"Successfully Clicked On Add Department Save Button: " + headofdepartment);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Save Button : " + DepartmentPage.getExceptionDesc());
		}
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button: " + headofdepartment);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentMapping Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS",
					"Successfully Clicked On AddDepartmentMapping Button: " + headofdepartment);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddDepartmentMapping Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentDropDown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentDropDown : " + headofdepartment);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentDropDown  : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentDropDownTextField(deptnameN)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Dept Name : " + deptnameN);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name  : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentDropDownSearchResult(deptnameN)) {
			logResults.createLogs("Y", "PASS", "Successfully Displayed created Dept Name : " + deptnameN);

		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Department Name  : " + DepartmentPage.getExceptionDesc());
		}

	}

	// MPI_189_Department_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_189_Department_02() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the department name and check whether the changes are updated by validating through a search using the updated department name.");

		ArrayList<String> data = initBase.loadExcelData("company_department", currTC,
				"password,captcha,deptname");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
		String deptnames = deptnameN + Pramod.generateRandomAlpha(2);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab : " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(4000);
		if (DepartmentPage.DepartmentPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Department Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Department Page : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentSearchIcon(deptnameN)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Input The Updated Department Name In The Search Text Field: " + deptnameN);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting  Department Name In Search Text Field : "
					+ DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Display Updated Department Name Record: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Updated Department Record : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.Department3DotsIcon()) {
			logResults.createLogs("Y", "PASS", " Clicked On Department 3Dots Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department 3Dots : " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (DepartmentPage.DepartmentEditIcon()) {
			logResults.createLogs("Y", "PASS", " Clicked On Department Edit Button Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Edit Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentName(deptnames)) {
			logResults.createLogs("Y", "PASS", "Department Name Entered Successfully: " + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentSaveButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Add Department Save Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Save Button : " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (DepartmentPage.DepartmentSearchIcon(deptnames)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Input The Updated Department Name In The Search Text Field: " + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting  Department Name In Search Text Field : "
					+ DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (DepartmentPage.DepartmentFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Display Updated Department Name Record: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Updated Department Record : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.Department3DotsIcon()) {
			logResults.createLogs("Y", "PASS", " Clicked On Department 3Dots Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department 3Dots : " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (DepartmentPage.DepartmentEditIcon()) {
			logResults.createLogs("Y", "PASS", " Clicked On Department Edit Button Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Edit Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentName(deptnameN)) {
			logResults.createLogs("Y", "PASS", "Department Name Entered Successfully: " + deptnameN);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentSaveButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Add Department Save Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Save Button : " + DepartmentPage.getExceptionDesc());
		}

	}

	// MPI_190_Department_03
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_190_Department_03() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("To verify this, check the functionality of search feature.");

		ArrayList<String> data = initBase.loadExcelData("company_department", currTC,
				"password,captcha");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		String password = data.get(0);
		String captcha = data.get(1);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Department Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Department Page : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentSearchIcon(deptnameN)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Input The Office Name In The Search Text Field: " + deptnameN);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting  Office Name In Search Text Field : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Display Updated Department Name Record: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Updated Department Record : " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

	}

	// MPI_191_Department_04
	@Test(enabled = true, priority = 4, groups = { "Sanity" })
	public void MPI_191_Department_04() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, Enter leading space in department name text field and check whether proper error message is displayed");

		ArrayList<String> data = initBase.loadExcelData("company_department", currTC,
				"password,captcha,deptname");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		String password = data.get(0);
		String captcha = data.get(1);
		String deptname = data.get(2);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Department Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Department Page : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Add Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentName(deptname)) {
			logResults.createLogs("Y", "PASS", "Department Name Entered Successfully: " + deptname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentSaveButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Add Department Save Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Save Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentNameValidation()) {
			logResults.createLogs("Y", "PASS", "Successfully Displayed Error Message: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message : " + DepartmentPage.getExceptionDesc());
		}
	}

	// MPI_192_Department_05
	@Test(enabled = true, priority = 5, groups = { "Sanity" })
	public void MPI_192_Department_05() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this: Check whether an appropriate error message is displayed when the user does not provide input in the mandatory fields.");

		ArrayList<String> data = initBase.loadExcelData("company_department", currTC,
				"password,captcha,message");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		String password = data.get(0);
		String captcha = data.get(1);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Department Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Department Page : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Add Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentSaveButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Add Department Save Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Save Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentNameValidation()) {
			logResults.createLogs("Y", "PASS", "Successfully Displayed Error Message: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message : " + DepartmentPage.getExceptionDesc());
		}
	}

	// MPI_194_Department_07
	@Test(enabled = true, priority = 7, groups = { "Sanity" })
	public void MPI_194_Department_07() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check whether a proper error message is displayed if the user enters any special character except '&'.");

		ArrayList<String> data = initBase.loadExcelData("company_department", currTC,
				"password,captcha,deptname");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		String password = data.get(0);
		String captcha = data.get(1);
		String deptname = data.get(2);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Department Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Department Page : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Add Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentName(deptname)) {
			logResults.createLogs("Y", "PASS", "Department Name Entered Successfully: " + deptname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentSaveButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Add Department Save Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Save Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentNameValidation()) {
			logResults.createLogs("Y", "PASS", "Successfully Displayed Error Message: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message : " + DepartmentPage.getExceptionDesc());
		}
	}

	// MPI_195_Department_08
	@Test(enabled = true, priority = 8, groups = { "Sanity" })
	public void MPI_195_Department_08() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check whether a proper error message is displayed if the user enters only one character in the department name text field.");

		ArrayList<String> data = initBase.loadExcelData("company_department", currTC,
				"password,captcha,deptname");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		String password = data.get(0);
		String captcha = data.get(1);
		String deptname = data.get(2);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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

		Thread.sleep(3000);

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab : " + EmployeePage.getExceptionDesc());
		}

		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}
		
		if (DepartmentPage.DepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Department Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Department Page : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Add Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentName(deptname)) {
			logResults.createLogs("Y", "PASS", "Department Name Entered Successfully: " + deptname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentSaveButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Add Department Save Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Save Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentNameValidation()) {
			logResults.createLogs("Y", "PASS", "Successfully Displayed Error Message: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message : " + DepartmentPage.getExceptionDesc());
		}
	}

	// MPI_205_Department_12
	@Test(enabled = true, priority = 12, groups = { "Sanity" })
	public void MPI_205_Department_12() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, disable the active department name and navigate to the \"add department mapping\" tab  check whether the inactive department is not displayed in the department dropdown list while \"add department mapping\" process");

		ArrayList<String> data = initBase.loadExcelData("company_department", currTC,
				"password,captcha");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		String password = data.get(0);
		String captcha = data.get(1);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab : " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (DepartmentPage.DepartmentPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Department Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Department Page : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentSearchIcon(deptnameN)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Input The Updated Department Name In The Search Text Field: " + deptnameN);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting  Department Name In Search Text Field : "
					+ DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Display Updated Department Name Record: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Updated Department Record : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " Successfully Turn Off The Toggle Switch : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Toggle Switch : " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (DepartmentPage.DepartmentToggleSwitchConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Ok Button : " + DepartmentPage.getExceptionDesc());
		}
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentMapping Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On AddDepartmentMapping Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddDepartmentMapping Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentDropDown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentDropDown : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentDropDown  : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentDropDownTextField(deptnameN)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Dept Name : " + deptnameN);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name  : " + DepartmentPage.getExceptionDesc());
		}

		if (TeamPage.NoResultFoundMsg()) {
			logResults.createLogs("Y", "PASS",
					"Disabled Team Name Isn't Displayed And Error Msg Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Displaying Error Msg : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);
	}

	// MPI_206_Department_13
	@Test(enabled = true, priority = 13, groups = { "Sanity" })
	public void MPI_206_Department_13() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enable the inactive department name and navigate to the add department mapping tab check whether the enabled department is displayed in the department dropdown list while department mapping process ");

		ArrayList<String> data = initBase.loadExcelData("company_department", currTC,
				"password,captcha");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		String password = data.get(0);
		String captcha = data.get(1);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab : " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (DepartmentPage.DepartmentPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Department Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Department Page : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentSearchIcon(deptnameN)) {
			logResults.createLogs("Y", "PASS",
					"Successfully Input The Updated Department Name In The Search Text Field: " + deptnameN);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting  Department Name In Search Text Field : "
					+ DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Display Updated Department Name Record: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Updated Department Record : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " Successfully Turn Off The Toggle Switch : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Toggle Switch : " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (DepartmentPage.DepartmentToggleSwitchConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Ok Button : " + DepartmentPage.getExceptionDesc());
		}

		Thread.sleep(3000);
		
		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}
		
		if (DepartmentPage.DepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentMapping Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On AddDepartmentMapping Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddDepartmentMapping Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentDropDown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentDropDown : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentDropDown  : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentDropDownTextField(deptnameN)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Dept Name : " + deptnameN);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name  : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentDropDownSearchResult(deptnameN)) {
			logResults.createLogs("Y", "PASS", "Successfully Displayed created Dept Name : " + deptnameN);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Department Name  : " + DepartmentPage.getExceptionDesc());
		}

		Thread.sleep(3000);
	}

	// MPI_210_Department_17
	@Test(enabled = true, priority = 17, groups = { "Smoke" })
	public void MPI_210_Department_17() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create duplicate department and ensure proper error message is displayed");

		ArrayList<String> data = initBase.loadExcelData("company_department", currTC,
				"password,captcha");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

		String password = data.get(0);
		String captcha = data.get(1);
		
		String deptnames = "";

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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

		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Drop Icon : " + DepartmentPage.getExceptionDesc());
		}
		
		if (DepartmentPage.DepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Department Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Department Page : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentFirstRecord()) {
			deptnames = DepartmentPage.Firstrecorddepartmentname;
			logResults.createLogs("Y", "PASS", "Successfully Get The Department Name: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Extracting Department Record Name : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Add Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentName(deptnames)) {
			logResults.createLogs("Y", "PASS", "Department Name Entered Successfully: " + deptnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.AddDepartmentSaveButton()) {
			logResults.createLogs("Y", "PASS",
					"Successfully Clicked On Add Department Save Button: " );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Save Button : " + DepartmentPage.getExceptionDesc());
		}

		if (OfficePage.DuplicateErrorMessage()) {
			logResults.createLogs("Y", "PASS", " Successfully Displayed Error Message : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message : " + OfficePage.getExceptionDesc());
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
