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
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterGradePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;


import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class MeghMasterGradeTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	

	private String cmpcode = "";
	private String Emailid = "";
	private String gradename = "";
	

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
		gradename = "AutoGN" + initBase.executionRunTime;
	}

	// MPI_289_Grade_01
	@Test(enabled = true, priority = 1, groups = { "Smoke", "AddMaster" })
	public void MPI_289_Grade_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Create a new Grade and ensure that the created Grade is displayed in the Grade dropdown under the Employee/User Manage Profile section");

		ArrayList<String> data = initBase.loadExcelData("company_grade", currTC,
				"password,captcha,gradename,gradedescription");

		String password = data.get(0);
		String captcha = data.get(1);

		gradename = data.get(2) + initBase.executionRunTime;
		String gradedescription = data.get(3) + initBase.executionRunTime;
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterGradePage GradePage = new MeghMasterGradePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}
		if (initBase.stopNewData) { // Tapan 5 July 25
			String GradeName = Utils.propsReadWrite("data/addmaster.properties", "get", "GradeName", "");
			if (GradeName.equals("pass")) {
				logResults.createLogs("Y", "INFO", "Skip Adding New Data. Continue with Old Test Data.");
			return;
			}
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

		if (GradePage.GradeButton()) {
			logResults.createLogs("Y", "PASS", "Grade Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Grade Button ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Grade Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Grade Page Isn't Loaded Completely ." + GradePage.getExceptionDesc());
		}

		if (GradePage.AddGradeButton()) {
			logResults.createLogs("Y", "PASS", "Add Grade Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Grade Button ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradeNameTextField(gradename)) {
			logResults.createLogs("Y", "PASS", " Grade Name Entered Successfully." + gradename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Grade Name ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradeDescriptionTextField(gradedescription)) {
			logResults.createLogs("Y", "PASS", " Grade Description Entered Successfully." + gradedescription);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Grade Description ." + GradePage.getExceptionDesc());
		}

		if (GradePage.AddGradeSaveButton()) {
			Utils.propsReadWrite("data/addmaster.properties", "set", "GradeName", "pass");
			logResults.createLogs("Y", "PASS", "AddGradeSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddGradeSaveButton ." + GradePage.getExceptionDesc());
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
		

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile ."
					+ DepartmentPage.getExceptionDesc());
		}


		if (GradePage.ManageProfileGradeDropDown()) {
			logResults.createLogs("Y", "PASS", "ManageProfileGradeDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ManageProfileGradeDropDown ." + GradePage.getExceptionDesc());
		}
		

		if (GradePage.ManageProfileGradeSearchField(gradename)) {
			logResults.createLogs("Y", "PASS",
					"Grade Name Entered In ManageProfileGradeTextField Successfully." + gradename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting On Manage Profile Grade TextField ." + GradePage.getExceptionDesc());
		}

		

		if (GradePage.ManageProfileGradeSearchResult()) {
			
			logResults.createLogs("Y", "PASS", "Manage Profile Grade SearchResult Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying  Manage Profile Grade SearchResult ." + GradePage.getExceptionDesc());
		}

	}

	// MPI_290_Grade_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_290_Grade_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Edit an existing Grade and ensure the updated Grade is displayed correctly in the Grade list. Use the Search feature to validate that the updated Grade appears as expected.");

		ArrayList<String> data = initBase.loadExcelData("company_grade", currTC,
				"gradename");

		String gradename = data.get(0) + Pramod.generateRandomNumber(4);

		String gradenames = gradename + Pramod.generateRandomAlpha(3);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterGradePage GradePage = new MeghMasterGradePage(driver);
		
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

		if (GradePage.GradeButton()) {
			logResults.createLogs("Y", "PASS", "Grade Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Grade Button ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Grade Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Grade Page Isn't Loaded Completely ." + GradePage.getExceptionDesc());
		}

		if (GradePage.AddGradeButton()) {
			logResults.createLogs("Y", "PASS", "Add Grade Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Grade Button ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradeNameTextField(gradename)) {
			logResults.createLogs("Y", "PASS", " Grade Name Entered Successfully." + gradename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Grade Name ." + GradePage.getExceptionDesc());
		}

		if (GradePage.AddGradeSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddGradeSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddGradeSaveButton ." + GradePage.getExceptionDesc());
		}
		

		if (GradePage.GradeSearchField(gradename)) {
			logResults.createLogs("Y", "PASS", " Grade Name Entered Successfully." + gradename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Grade Name ." + GradePage.getExceptionDesc());
		}

		

		if (GradePage.Grade3Dots()) {
			logResults.createLogs("Y", "PASS", "Grade 3Dots  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Grade 3Dots ." + GradePage.getExceptionDesc());
		}
		

		if (GradePage.GradeEditIcon()) {
			logResults.createLogs("Y", "PASS", "Grade EditIcon  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Grade EditIcon ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradeNameTextField(gradenames)) {
			logResults.createLogs("Y", "PASS", " Grade Name Entered Successfully." + gradenames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Grade Name ." + GradePage.getExceptionDesc());
		}

		if (GradePage.AddGradeSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddGradeSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddGradeSaveButton ." + GradePage.getExceptionDesc());
		}


		if (GradePage.GradeSearchField(gradenames)) {
			logResults.createLogs("Y", "PASS", " Grade Name Entered Successfully." + gradenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Grade Name In SearchField ." + GradePage.getExceptionDesc());
		}

		

		if (GradePage.GradeSearchResultsOrFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Updated Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Updated Grade Name Row ." + GradePage.getExceptionDesc());
		}

	}

	// MPI_292_Grade_04
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_292_Grade_04()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults
				.setScenarioName("To verify this, create duplicate Grade and ensure proper error message is displayed");

	//	ArrayList<String> data = initBase.loadExcelData("company_grade", currTC,"password,captcha");

		String firstrowgradename = "";

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterGradePage GradePage = new MeghMasterGradePage(driver);
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

		if (GradePage.GradeButton()) {
			logResults.createLogs("Y", "PASS", "Grade Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Grade Button ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Grade Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Grade Page Isn't Loaded Completely ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradeSearchResultsOrFirstRecord()) {
			firstrowgradename = GradePage.firstgradename;
			logResults.createLogs("Y", "PASS", "Updated Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Updated Grade Name Row ." + GradePage.getExceptionDesc());
		}
		

		if (GradePage.AddGradeButton()) {
			logResults.createLogs("Y", "PASS", "Add Grade Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Grade Button ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradeNameTextField(firstrowgradename)) {
			logResults.createLogs("Y", "PASS", " Grade Name Entered Successfully." + firstrowgradename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Grade Name ." + GradePage.getExceptionDesc());
		}

		if (GradePage.AddGradeSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddGradeSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddGradeSaveButton ." + GradePage.getExceptionDesc());
		}

		if (OfficePage.DuplicateErrorMessage()) {
			logResults.createLogs("Y", "PASS", " Successfully Displayed Error Message .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message ." + OfficePage.getExceptionDesc());
		}


	}

	// MPI_298_Grade_10
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_298_Grade_10()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, disable the Grade and ensure disabled Grade name is't displayed in the employee's  manage profile \"Grade\" dropdown");

	//	ArrayList<String> data = initBase.loadExcelData("company_grade", currTC, "password,captcha");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterGradePage GradePage = new MeghMasterGradePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
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

		if (GradePage.GradeButton()) {
			logResults.createLogs("Y", "PASS", "Grade Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Grade Button ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Grade Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Grade Page Isn't Loaded Completely ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradeSearchField(gradename)) {
			logResults.createLogs("Y", "PASS", " Grade Name Entered Successfully." + gradename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Grade Name In SearchField ." + GradePage.getExceptionDesc());
		}

		

		if (GradePage.GradeToggleSwitch()) {
			logResults.createLogs("Y", "PASS", "Grade Inactivated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Grade Toggle Switch ." + GradePage.getExceptionDesc());
		}

		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button ." + OfficePage.getExceptionDesc());
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
		

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile ."
					+ DepartmentPage.getExceptionDesc());
		}


		if (GradePage.ManageProfileGradeDropDown()) {
			logResults.createLogs("Y", "PASS", "ManageProfileGradeDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ManageProfileGradeDropDown ." + GradePage.getExceptionDesc());
		}
	

		if (GradePage.ManageProfileGradeSearchField(gradename)) {
			logResults.createLogs("Y", "PASS",
					"Disabled Grade Name Entered In ManageProfileGradeTextField Successfully." + gradename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Grade Name On Manage Profile Grade TextField ."
					+ GradePage.getExceptionDesc());
		}


		if (OfficePage.SearchResultErrorMsg()) {
			logResults.createLogs("Y", "PASS",
					" Disabled Location Name Isn't Displayed And Respective Message Is Displayed.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying  Error Message." + OfficePage.getExceptionDesc());
		}

	}

	// MPI_299_Grade_11
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_299_Grade_11()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enable a previously disabled Grade record and ensure that, after enabling, the enabled Grade name appears in the 'Grade' dropdown which is in Employee's Manage Profile section.");

	//	ArrayList<String> data = initBase.loadExcelData("company_grade", currTC, "password,captcha");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterGradePage GradePage = new MeghMasterGradePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
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

		if (GradePage.GradeButton()) {
			logResults.createLogs("Y", "PASS", "Grade Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Grade Button ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Grade Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Grade Page Isn't Loaded Completely ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradeSearchField(gradename)) {
			logResults.createLogs("Y", "PASS", " Grade Name Entered Successfully." + gradename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Grade Name In SearchField ." + GradePage.getExceptionDesc());
		}

		

		if (GradePage.GradeToggleSwitch()) {
			logResults.createLogs("Y", "PASS", "Grade Activated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Grade Toggle Switch ." + GradePage.getExceptionDesc());
		}

		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button ." + OfficePage.getExceptionDesc());
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
		

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile ."
					+ DepartmentPage.getExceptionDesc());
		}


		if (GradePage.ManageProfileGradeDropDown()) {
			logResults.createLogs("Y", "PASS", "ManageProfileGradeDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ManageProfileGradeDropDown ." + GradePage.getExceptionDesc());
		}
		

		if (GradePage.ManageProfileGradeSearchField(gradename)) {
			logResults.createLogs("Y", "PASS",
					"Enabled Grade Name Entered In ManageProfileGradeTextField Successfully." + gradename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Grade Name On Manage Profile Grade TextField ."
					+ GradePage.getExceptionDesc());
		}

		

		if (GradePage.ManageProfileGradeSearchResult()) {
			logResults.createLogs("Y", "PASS",
					"Enabled Grade Displayed On Manage Profile Grade DropDown  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying  Grade SearchResult ." + GradePage.getExceptionDesc());
		}

	}

	// MPI_300_Grade_12
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_300_Grade_12()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("To verify this, check the functionality of search feature by inputting grade name");

	//	ArrayList<String> data = initBase.loadExcelData("company_grade", currTC, "password,captcha");

		
		String firstrowgradename = "";

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterGradePage GradePage = new MeghMasterGradePage(driver);
		

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

		if (GradePage.GradeButton()) {
			logResults.createLogs("Y", "PASS", "Grade Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Grade Button ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Grade Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Grade Page Isn't Loaded Completely ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradeSearchResultsOrFirstRecord()) {
			firstrowgradename = GradePage.firstgradename;
			logResults.createLogs("Y", "PASS", "Searched Grade Record Displayed Successfully." + firstrowgradename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Searched Grade Row ." + GradePage.getExceptionDesc());
		}

		if (GradePage.GradeSearchField(firstrowgradename)) {
			logResults.createLogs("Y", "PASS", "Mapped Designation Name Entered Successfully." + firstrowgradename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Mapped Designation In SearchField ." + GradePage.getExceptionDesc());
		}

		

		if (GradePage.GradeSearchResultsOrFirstRecord()) {
			firstrowgradename = GradePage.firstgradename;
			logResults.createLogs("Y", "PASS", "Searched Grade Record Displayed Successfully." + firstrowgradename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Searched Grade Row ." + GradePage.getExceptionDesc());
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
