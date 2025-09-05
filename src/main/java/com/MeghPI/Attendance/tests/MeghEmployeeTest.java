package com.MeghPI.Attendance.tests;

import java.awt.AWTException;
import java.io.IOException;
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

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class MeghEmployeeTest {
	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	public String emplastname = "";
	private String departmentname = "";
	private String teamname = "";
	private String designationname= "";
	
	

	private String cmpcode = "";
	private String Emailid = "";
	private String entityname = "";
	private String officename = "";
	
	
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
	}

	// MPI_145_Directory_AddEmployee_01
	@Test(enabled = true, priority = 1, groups = { "Sanity" })
	public void MPI_145_Directory_AddEmployee_01() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, click on the 'Employee' tab, then click on the 'Total Employee' button, and check whether the 'Add New' button is displayed.");

		ArrayList<String> data = initBase.loadExcelData("employee", currTC, "password,captcha,message");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

		
		
		String password = data.get(0);
		String captcha = data.get(1);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

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

		if (EmployeePage.NewEmployeeFormValidation()) {
			logResults.createLogs("Y", "PASS", "New Employee Onboardiang Text Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while NewEmployeeFormValidation Text Display: " + EmployeePage.getExceptionDesc());
		}

	}

	// MPI_146_Directory_AddEmployee_02
	@Test(enabled = true, priority = 2, groups = { "Sanity" })
	public void MPI_146_Directory_AddEmployee_02() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create an employee type by adding a new entity with the card type set to 'Staff' or 'Worker', and check whether the created employee type appears in the Employee Type dropdown during the 'Add Employee' process.");

		ArrayList<String> data = initBase.loadExcelData("employee", currTC,
				"password,captcha,message,entityname");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
		

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

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
					"Clicked On EmployeeTypeDropdown Successfully And Added EntityName Displayed Successfully: "
							+ entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTypeDropdown  : " + EmployeePage.getExceptionDesc());
		}

		Thread.sleep(3000);

	}

	// MPI_148_Directory_AddEmployee_03
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_148_Directory_AddEmployee_03() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create an office in the Company module and check whether the created office appears in the 'Company Location' dropdown during the 'Add Employee' process.");

		ArrayList<String> data = initBase.loadExcelData("employee", currTC,
				"password,captcha");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

		
		
		String password = data.get(0);
		String captcha = data.get(1);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

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
		Thread.sleep(3000);

		if (EmployeePage.EmployeeFormDisplayValidated()) {
			logResults.createLogs("Y", "PASS", "Employee Form Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Loading Employee Form: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationDropdown(officename)) {
			logResults.createLogs("Y", "PASS", " Created OfficeName Is Displayed In The DropDown : " + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking or Displaying Created Office Name: " + EmployeePage.getExceptionDesc());
		}

	}

	// MPI_149_Directory_AddEmployee_04
	@Test(enabled = true, priority = 04, groups = { "Sanity" })
	public void MPI_149_Directory_AddEmployee_04() throws InterruptedException, AWTException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of 'add more employee' and ensure another form is displayed");
		ArrayList<String> data = initBase.loadExcelData("employee", currTC, "password,captcha,message");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);

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

		if (EmployeePage.AddMoreEmployee()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddMoreEmployee Button Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddMoreEmployee Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.Employee2()) {
			logResults.createLogs("Y", "PASS", "Employee 2 Form Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Displaying Employee2: " + EmployeePage.getExceptionDesc());
		}

	}

	// MPI_150_Directory_AddEmployee_05
	@Test(enabled = true, priority = 05, groups = { "Smoke" })
	public void MPI_150_Directory_AddEmployee_05() throws InterruptedException, AWTException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Create a new employee by selecting the previously created 'Employee Type'. Then, search and select a name for the 'Reporting To' field from the users already created. Choose the appropriate 'Company Location' that was added earlier, and complete the employee creation process. Finally, verify that the newly added employee appears in the Employee List");
		ArrayList<String> data = initBase.loadExcelData("employee", currTC,
				"password,captcha,reportingto,empid,empfirstname,emplastname,empemail,empphoneno,mailinator,empjoiningdate");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);


		String password = data.get(0);
		String captcha = data.get(1);
		String reportingto = data.get(2);
		String empid = data.get(3) + Pramod.generateRandomAlpha(5);
		

		String empfirstname = data.get(4) + Pramod.generateRandomAlpha(6);
		 emplastname = data.get(5) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(6);
		String empphoneno = data.get(7) + Pramod.generateRandomNumber(5);
		String mailinator = data.get(8);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(3) + mailinator;
		String joiningdate = data.get(9);

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

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
		
		if (EmployeePage.FirstDateSelection(joiningdate)) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date Entered On Joining Date Text Field: " + joiningdate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Joining Date: " + EmployeePage.getExceptionDesc());
		}
		

		if (EmployeePage.AddEmployeeSave()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddEmployeeSave Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEmployeeSave: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (EmployeePage.GoToDirectory()) {
			logResults.createLogs("Y", "PASS", "Clicked On GoToDirectory Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GoToDirectory: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

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

	}

	// MPI_159_Directory_AddEmployee_14
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_159_Directory_AddEmployee_14() throws InterruptedException, IOException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enroll the newly added employee and check whether the enrolled employee is displayed in the 'Reporting To' dropdown on the 'Add New Employee' page");

		ArrayList<String> data = initBase.loadExcelData("employee", currTC,
				"password,captcha,enrollmentimage");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
		String enrollmentimage = data.get(2);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

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

		Thread.sleep(3000);

		if (EmployeePage.ImageUploadButton(enrollmentimage)) {
			logResults.createLogs("Y", "PASS", "Image Selected For WebEnrollment Successfully: " + enrollmentimage);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Image For WebEnrollment: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

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
		
	}
	
		// MPI_626_Directory_AddEmployee_19
		@Test(enabled = true, priority = 07, groups = { "Smoke", "AddMaster" })
		public void MPI_626_Directory_AddEmployee_19() throws InterruptedException, AWTException {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, create an employee and enroll the employee and ensure created employee record is displayed in the attendance module");
			ArrayList<String> data = initBase.loadExcelData("employee", currTC,
					"password,captcha,reportingto,empid,empfirstname,emplastname,empemail,empphoneno,mailinator,empjoiningdate,enrollmentimage,pin");

			MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
			MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

			String password = data.get(0);
			String captcha = data.get(1);
			String reportingto = data.get(2);
			String empid = data.get(3) + Pramod.generateRandomNumber(4);
			

			String empfirstname = data.get(4) + Pramod.generateRandomAlpha(6);
			 String emplastname = data.get(5) + Pramod.generateRandomAlpha(5);
			String empemail = data.get(6);
			String empphoneno = data.get(7) + Pramod.generateRandomNumber(5);
			String mailinator = data.get(8);
			String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(3) + mailinator;
			String joiningdate = data.get(9);
			String enrollmentimage = data.get(10);
			String pin = data.get(11);

			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghShiftPage ShiftPage = new MeghShiftPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

	MeghLoginTest meghlogintest = new MeghLoginTest();
	MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
	
	if (initBase.stopNewData) { // Tapan 5 July 25
		String EmpName = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpName", "");
		if (EmpName.equals("pass")) {
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
			
			if (EmployeePage.FirstDateSelection(joiningdate)) {
				logResults.createLogs("Y", "PASS",
						"Employee Joining Date Entered On Joining Date Text Field: " + joiningdate);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Joining Date: " + EmployeePage.getExceptionDesc());
			}
			

			if (EmployeePage.AddEmployeeSave()) {
				logResults.createLogs("Y", "PASS", "Clicked On AddEmployeeSave Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On AddEmployeeSave: " + EmployeePage.getExceptionDesc());
			}
			Thread.sleep(4000);

			if (EmployeePage.GoToDirectory()) {
				logResults.createLogs("Y", "PASS", "Clicked On GoToDirectory Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On GoToDirectory: " + EmployeePage.getExceptionDesc());
			}
			Thread.sleep(3000);	
		
			if (EmployeePage.SearchTextField(empfirstname)) {
				logResults.createLogs("Y", "PASS",
						"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: " + empfirstname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Search Text Field: " + EmployeePage.getExceptionDesc());
			}
			Thread.sleep(3000);

			if (EmployeePage.SearchResult()) {
				logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully: " + empfirstname);
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

			Thread.sleep(3000);

			if (EmployeePage.ImageUploadButton(enrollmentimage)) {
				logResults.createLogs("Y", "PASS", "Image Selected For WebEnrollment Successfully: " + enrollmentimage);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Image For WebEnrollment: " + EmployeePage.getExceptionDesc());
			}
			Thread.sleep(3000);

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
				Utils.propsReadWrite("data/addmaster.properties", "set", "EmpName", "pass");
				Utils.propsReadWrite("data/addmaster.properties", "set", "EmpID", empid);
				Utils.propsReadWrite("data/addmaster.properties", "set", "EmpFirstName", empfirstname);
				
				logResults.createLogs("Y", "PASS", "Success Msg Is Displayed: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Displaying Success Msg: " + EmployeePage.getExceptionDesc());
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

			if (EmployeePage.DotsMenu()) {
				logResults.createLogs("Y", "PASS", "Clicked On 3Dots Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error while Clicking On 3Dots: " + EmployeePage.getExceptionDesc());
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
			
			
			if (EmployeePage.SearchTextField(reportingto)) {
				logResults.createLogs("Y", "PASS",
						"Clicked On Search Text Field And Pass The Created Employee LastName Successfully: " + reportingto);
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
			
			if (EmployeePage.OfficeNameSelected(officename)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Office: " + officename);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Office : " + EmployeePage.getExceptionDesc());
			}
			
			
			
			if (EmployeePage.EntityTypeSelected(entityname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Entity Name: " + entityname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Entity Name : " + EmployeePage.getExceptionDesc());
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
