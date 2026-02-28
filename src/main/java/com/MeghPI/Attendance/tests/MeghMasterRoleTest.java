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

import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import utils.Pramod;
import utils.Utils;

public class MeghMasterRoleTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	public String rolename = "";

	private String cmpcode = "";
	private String Emailid = "";

	@Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
		driver = loadDriver.getDriver(device, hubnodeip);

		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

	@Parameters({ "device", "hubnodeip" })
	@BeforeClass(alwaysRun = true)
	void runOnce(int device, @Optional String hubnodeip) {
		logResults.createReport(device);
		logResults.setTestMethodErrorCount(0);
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
	}

	// MPI_250_Role_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_250_Role_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a role and check whether created role is displayed in the Employee manage profile role dropdown");

		ArrayList<String> data = initBase.loadExcelData("company_role", currTC,
				"password,captcha,rolename,roledescription");

		int i = 0;
		String password = data.get(i++);
		String captcha = data.get(i++);
	

		rolename = data.get(i++) + initBase.executionRunTime;
		String roledescription = data.get(i++) + initBase.executionRunTime;
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);


		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterRolePage RolePage = new MeghMasterRolePage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

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

		if (RolePage.RoleButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Role Button ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleModulePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Module Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Role Module Isn't Loaded Completely ." + RolePage.getExceptionDesc());
		}

		if (RolePage.AddRoleButton()) {
			logResults.createLogs("Y", "PASS", "Add Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Role Button ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleNameTextField(rolename)) {
			logResults.createLogs("Y", "PASS", " Role Name Entered Successfully." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting  Role Name ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleDescriptionField(roledescription)) {
			logResults.createLogs("Y", "PASS", " Role Description Entered Successfully." + roledescription);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting  Role Description ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleSaveButton()) {
			logResults.createLogs("Y", "PASS", " Role Save Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Save Button ." + RolePage.getExceptionDesc());
		}

		

		if (RolePermissionpage.ManageButton()) {
			logResults.createLogs("Y", "PASS", "Admin Manage Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Manage Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LogoutButton()) {
			logResults.createLogs("Y", "PASS", "Admin Logout Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Logout Button ." + RolePermissionpage.getExceptionDesc());
		}


		
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

		if (RolePage.ManageProfileRoleTextField(rolename)) {
			logResults.createLogs("Y", "PASS", "Created Role Name Entered In DropDown Of Manage Profile." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting  Role Name In DropDown ." + RolePage.getExceptionDesc());
		}

		if (RolePage.ManageProfileRoleSearchResult()) {
			logResults.createLogs("Y", "PASS",
					"Created Role Name Displayed Successfully In Manage Profile  ." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying  Newly Created RoleName ." + RolePage.getExceptionDesc());
		}

	}

	// MPI_251_Role_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_251_Role_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the role name and ensure that the updated role is saved successfully. Validate it using the search feature and confirm that the updated role is displayed");

		ArrayList<String> data = initBase.loadExcelData("company_role", currTC,
				"password,captcha,rolename");

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String rolenames = data.get(2) + Pramod.generateRandomAlpha(3);
		String firstrowrolename = "";

		String newrole = rolenames + Pramod.generateRandomAlpha(4);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterRolePage RolePage = new MeghMasterRolePage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		
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

		if (RolePage.RoleButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Role Button ." + RolePage.getExceptionDesc());
		}
		

		if (RolePage.RoleModulePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Module Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Role Module Isn't Loaded Completely ." + RolePage.getExceptionDesc());
		}

		if (RolePage.AddRoleButton()) {
			logResults.createLogs("Y", "PASS", "Add Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Role Button ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleNameTextField(rolenames)) {
			logResults.createLogs("Y", "PASS", " Role Name Entered Successfully." + rolenames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting  Role Name ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleSaveButton()) {
			logResults.createLogs("Y", "PASS", " Role Save Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Save Button ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleSearchTextField(rolenames)) {
			logResults.createLogs("Y", "PASS", " Role Name Entered Successfully In Search TextField." + rolenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Role Name In Search TextField ." + RolePage.getExceptionDesc());
		}
		

		if (RolePage.Role3Dots2ndRow()) {
			logResults.createLogs("Y", "PASS", "Role 3Dots Icon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role 3Dots Icon ." + RolePage.getExceptionDesc());
		}
		if (RolePage.RoleEdit2ndRow()) {
			logResults.createLogs("Y", "PASS", "Role Edit Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Edit Button ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleNameTextField(newrole)) {
			logResults.createLogs("Y", "PASS", " Role Name Entered Successfully." + newrole);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting  Role Name ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleSaveButton()) {
			logResults.createLogs("Y", "PASS", " Role Save Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Save Button ." + RolePage.getExceptionDesc());
		}
		

		if (RolePage.RoleSearchTextField(newrole)) {
			logResults.createLogs("Y", "PASS", " Role Name Entered Successfully In Search TextField." + newrole);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Role Name In Search TextField ." + RolePage.getExceptionDesc());
		}
		

		if (RolePage.RoleFirstRowName()) {
			firstrowrolename = RolePage.RoleNames;
			logResults.createLogs("Y", "PASS", " Searched Role Displayed Successfully." + firstrowrolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Displaying Searched Role ." + RolePage.getExceptionDesc());
		}
		

	}

	// MPI_252_Role_03
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_252_Role_03()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults
				.setScenarioName("To verify this, create duplicate role and ensure proper error message is displayed");

		ArrayList<String> data = initBase.loadExcelData("company_role", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);
		

		String firstrowrolename = "";

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterRolePage RolePage = new MeghMasterRolePage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

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

		if (RolePage.RoleButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Role Button ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleModulePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Module Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Role Module Isn't Loaded Completely ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleFirstRowName()) {
			firstrowrolename = RolePage.RoleNames;
			logResults.createLogs("Y", "PASS", " Searched Role Displayed Successfully." + firstrowrolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Displaying Searched Role ." + RolePage.getExceptionDesc());
		}
		

		if (RolePage.AddRoleButton()) {
			logResults.createLogs("Y", "PASS", "Add Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Role Button ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleNameTextField(firstrowrolename)) {
			logResults.createLogs("Y", "PASS", " Role Name Entered Successfully." + firstrowrolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting  Role Name ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleSaveButton()) {
			logResults.createLogs("Y", "PASS", " Role Save Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Save Button ." + RolePage.getExceptionDesc());
		}

		if (OfficePage.DuplicateErrorMessage()) {
			logResults.createLogs("Y", "PASS", " Successfully Displayed Error Message .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message ." + OfficePage.getExceptionDesc());
		}

	}

	// MPI_258_Role_09
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_258_Role_09()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, disable the RoleÂ and ensure disabledÂ Role name is't displayed in the employee manage profile \"Role\" dropdown");

		ArrayList<String> data = initBase.loadExcelData("company_role", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterRolePage RolePage = new MeghMasterRolePage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

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

		if (RolePage.RoleButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Role Button ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleModulePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Module Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Role Module Isn't Loaded Completely ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleSearchTextField(rolename)) {
			logResults.createLogs("Y", "PASS", " Role Name Entered Successfully In Search TextField." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Role Name In Search TextField ." + RolePage.getExceptionDesc());
		}
		

		if (RolePage.RoleToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " First Role Row Toggle Switch Disabled Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Toggle Switch ." + RolePage.getExceptionDesc());
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

		if (RolePage.ManageProfileRoleTextField(rolename)) {
			logResults.createLogs("Y", "PASS", "Created Role Name Entered In DropDown Of Manage Profile." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting  Role Name In DropDown ." + RolePage.getExceptionDesc());
		}

		if (OfficePage.SearchResultErrorMsg()) {
			logResults.createLogs("Y", "PASS",
					" Disabled Role Name Isn't Displayed And Respective Message Is Displayed.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying  Error Message." + OfficePage.getExceptionDesc());
		}


	}

	// MPI_259_Role_10
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_259_Role_10()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enable a previously disabled Role record and ensure that, after enabling, the enabled Role name appears in the 'Role ' dropdown which is in Employee Manage Profile section.");

		ArrayList<String> data = initBase.loadExcelData("company_role", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);
		

		String firstrowrolename = "";

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterRolePage RolePage = new MeghMasterRolePage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

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

		if (RolePage.RoleButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Role Button ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleModulePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Module Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Role Module Isn't Loaded Completely ." + RolePage.getExceptionDesc());
		}

		
		if (RolePage.RoleSearchTextField(rolename)) {
			logResults.createLogs("Y", "PASS", " Role Name Entered Successfully In Search TextField." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Role Name In Search TextField ." + RolePage.getExceptionDesc());
		}
		

		if (RolePage.RoleToggleSwitch()) {

			logResults.createLogs("Y", "PASS", " First Role Row Toggle Switch Disabled Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Toggle Switch ." + RolePage.getExceptionDesc());
		}
		

		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button ." + OfficePage.getExceptionDesc());
		}

		

		if (RolePermissionpage.ManageButton()) {
			logResults.createLogs("Y", "PASS", "Admin Manage Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Manage Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LogoutButton()) {
			logResults.createLogs("Y", "PASS", "Admin Logout Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Logout Button ." + RolePermissionpage.getExceptionDesc());
		}


		
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

		

		if (RolePage.ManageProfileRoleTextField(rolename)) {
			logResults.createLogs("Y", "PASS", "Created Role Name Entered In DropDown Of Manage Profile." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting  Role Name In DropDown ." + RolePage.getExceptionDesc());
		}

		if (RolePage.ManageProfileRoleSearchResult()) {
			logResults.createLogs("Y", "PASS",
					"Enabled Role Name Displayed Successfully In Manage Profile  ." + firstrowrolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying  Newly Created RoleName ." + RolePage.getExceptionDesc());
		}

	}

	// MPI_260_Role_11
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_260_Role_11()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, test the search functionality by selecting 'Status' as the search option and ensure that inactive roles are displayed after entering 'inactive' in the search field.");

		ArrayList<String> data = initBase.loadExcelData("company_role", currTC,
				"password,captcha,status");

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		
		String status = data.get(2);

		String firstrowrolename = "";



		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterRolePage RolePage = new MeghMasterRolePage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);

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

		if (RolePage.RoleButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Role Button ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleModulePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Module Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Role Module Isn't Loaded Completely ." + RolePage.getExceptionDesc());
		}
		

		if (DepartmentPage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", " Search DropDown  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking Search DropDown ." + DepartmentPage.getExceptionDesc());
		}

		if (RolePage.StatusCheckbox()) {
			logResults.createLogs("Y", "PASS", " Status Checkbox  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking Status Checkbox ." + RolePage.getExceptionDesc());
		}
		

		if (DepartmentPage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", " Search DropDown  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking Search DropDown ." + DepartmentPage.getExceptionDesc());
		}

		if (RolePage.RoleSearchTextField(status)) {
			logResults.createLogs("Y", "PASS", " 'Inactive' Entered Successfully In Search TextField." + status);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The 'Inactive' In Search TextField ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleFirstRowName()) {
			firstrowrolename = RolePage.RoleNames;
			logResults.createLogs("Y", "PASS", " Inactive Role Name Fetched Successfully." + firstrowrolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Fetching Role Name ." + RolePage.getExceptionDesc());
		}

	}

	// MPI_263_Role_14
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_263_Role_14()  {

		/*
		 * MeghLoginTest loginscript = new MeghLoginTest(); MeghLoginPage MeghLoginPage
		 * = new MeghLoginPage(driver);
		 * 
		 * 
		 * if (loginscript.MPI_7_Login_Page_07()) { logResults.createLogs("Y", "PASS",
		 * "Login Is Done In Single Method Calling." ); } else {
		 * logResults.createLogs("Y", "FAIL", "Error While Executing Login Method." +
		 * MeghLoginPage.getExceptionDesc()); }
		 */

		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check whether all the created roles are displayed in the role permission moduleÂ ");

		ArrayList<String> data = initBase.loadExcelData("company_role", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);


		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		

		MeghMasterRolePage RolePage = new MeghMasterRolePage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		

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

		if (RolePage.RoleButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Role Button ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleModulePageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Module Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Role Module Isn't Loaded Completely ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RoleRecords()) {
			logResults.createLogs("Y", "PASS", "Role Records  Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Displaying Role Records ." + RolePage.getExceptionDesc());
		}
		if (RolePage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Permission Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Permission Button ." + RolePage.getExceptionDesc());
		}

		if (RolePage.RolePermissionRecords()) {
			logResults.createLogs("Y", "PASS", "Role Permission Records Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying  Role Permission Records ." + RolePage.getExceptionDesc());
		}

		if (RolePage.Comparision()) {
			logResults.createLogs("Y", "PASS", "Role Permission Records And Roles Records Compared Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Comparing  Role Permission Records ." + RolePage.getExceptionDesc());
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
