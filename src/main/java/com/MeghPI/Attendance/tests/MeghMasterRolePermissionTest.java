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
import com.MeghPI.Attendance.pages.MeghMasterDesignationPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;
import utils.Pramod;
import utils.Utils;

public class MeghMasterRolePermissionTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();

	private String email = "";
	private String empid = "";
	private String emailhr = "";
	private String empidhr = "";

	private String cmpcode = "";
	private String Emailid = "";
	private String entityname = "";
	private String officename = "";
	private String deptname = "";
	private String teamname = "";
	private String designationname = "";

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
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		deptname = "AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" + initBase.executionRunTime;
		designationname = "AutoDESN" + initBase.executionRunTime;
	}

	// MPI_265_RolePermission_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_265_RolePermission_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check whetherÂ all active rolesÂ are displayed in theÂ \"Select Role\"Â dropdown during theÂ \"Add Role Permission\"Â process.");

		ArrayList<String> data = initBase.loadExcelData("company_rolepermission", currTC,
				"password,captcha,status");

		String password = data.get(0);
		String captcha = data.get(1);

		MeghLoginTest meghlogintest = new MeghLoginTest();

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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

		

		if (RolePermissionpage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Permission Page  Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Role Permission Page Not Loaded Completely ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AddRolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Add RolePermission Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add RolePermission Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.Comparision()) {
			logResults.createLogs("Y", "PASS", "All The Active Roles Are Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Mapping The Active Roles ." + RolePermissionpage.getExceptionDesc());
		}

	}

	// MPI_267_RolePermission_03
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_267_RolePermission_03()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"SelectÂ \"owner\"Â as the role, assign only theÂ \"General Settings\"Â permissions, and save. Then, click onÂ EditÂ for the same role and validate whetherÂ only the assigned permissionsÂ are reflected correctly");

		ArrayList<String> data = initBase.loadExcelData("company_rolepermission", currTC,
				"password,captcha,rolename");

	
		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String rolename = data.get(2);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();


		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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

		if (RolePermissionpage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Permission Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Permission Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Permission Page  Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Role Permission Page Not Loaded Completely ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AddRolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Add RolePermission Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add RolePermission Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Role DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role DropDown ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearch(rolename)) {
			logResults.createLogs("Y", "PASS", "Role Selected Successfully." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Role Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.SettingsCheckBox()) {
			logResults.createLogs("Y", "PASS", "Settings Checkbox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Settings CheckBox ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionSaveButton()) {
			logResults.createLogs("Y", "PASS", "The Role Permission Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Permission Save Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionSearchField(rolename)) {
			logResults.createLogs("Y", "PASS", "Role Name Entered In Search TextField Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Role Name ." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.RolePermissionEditIcon()) {
			logResults.createLogs("Y", "PASS", "Role Permission Edit Icon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Permission Edit Icon ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.CheckBoxIsSelected()) {
			logResults.createLogs("Y", "PASS", "Master Role Is Selected And Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Master Role Isn't Selected ." + RolePermissionpage.getExceptionDesc());
		}
	}

	// MPI_277_RolePermission_12
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_277_RolePermission_12()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of the search text field by entering a role name and ensure that the correct role is displayed in the search results.");

		ArrayList<String> data = initBase.loadExcelData("company_rolepermission", currTC,
				"password,captcha,rolename");

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String rolename = data.get(2);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();


		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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

		if (RolePermissionpage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Permission Page  Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Role Permission Page Not Loaded Completely ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionSearchField(rolename)) {
			logResults.createLogs("Y", "PASS", "Role Name Entered In Search TextField Successfully." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Role Name ." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.RolePermissionSearchResult()) {
			logResults.createLogs("Y", "PASS", "Role Name Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Role Name ." + RolePermissionpage.getExceptionDesc());
		}
	}

	// MPI_280_RolePermission_15
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_280_RolePermission_15()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, assign Attendance permissions to an employee, then log in using that employeeâ€™s account and ensure that all Attendance-related optionsâ€”My Regularization, My OT Approval, My Shift Request, and My Week Off Requestâ€”are displayed as per the assigned permissions.");

		ArrayList<String> data = initBase.loadExcelData("company_rolepermission", currTC,
				"password,captcha,rolename,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,pin");

	

		int i = 0;
		String password = data.get(i++);
		String captcha = data.get(i++);
		
		String rolename = data.get(i++);
		String mailinator = data.get(i++);
		empid = data.get(i++) + Pramod.generateRandomAlpha(5) + Pramod.generateRandomNumber(3);
		

		String empfirstname = data.get(i++) + Pramod.generateRandomAlpha(6);
		String emplastname = data.get(i++) + Pramod.generateRandomAlpha(6);
		String empemail = data.get(i++);
		String empphoneno = data.get(i++) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(i++);
		String reportingto = data.get(i++);
		email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(4) + mailinator;
		
		String enrollmentimage = data.get(i++);
		String pin = data.get(i++) + Pramod.generateRandomNumber(3);

	
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterRolePage RolePage = new MeghMasterRolePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();


		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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
			logResults.createLogs("Y", "PASS", "EmployeeID Entered On Employee ID Text Field." + empidhr);
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
			logResults.createLogs("Y", "PASS", "Employee Email Entered On Email ID Text Field." + emailhr);
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

		if (RolePermissionpage.DeptDropdown(deptname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + deptname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Dept ." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.DesignationDropdown(designationname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Designation From Dropdown ." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePage.ManageProfileRoleTextField(rolename)) {
			logResults.createLogs("Y", "PASS", "Created Role Name Entered In DropDown Of Manage Profile." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting  Role Name In DropDown ." + RolePage.getExceptionDesc());
		}
		

		if (RolePage.ManageProfileRoleSearchResultselect()) {
			logResults.createLogs("Y", "PASS",
					"Created Role Name Displayed Successfully In Manage Profile  ." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying  Newly Created RoleName ." + RolePage.getExceptionDesc());
		}

		

		if (RolePermissionpage.TeamDropDown(teamname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team From Dropdown ." + RolePermissionpage.getExceptionDesc());
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

		if (RolePermissionpage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Permission Page  Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Role Permission Page Not Loaded Completely ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AddRolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Add RolePermission Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add RolePermission Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Role DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role DropDown ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearch(rolename)) {
			logResults.createLogs("Y", "PASS", "Role Selected Successfully." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Role Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EmployeeAttendancePermission()) {
			logResults.createLogs("Y", "PASS", "Attendance Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Attendance Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionSaveButton()) {
			logResults.createLogs("Y", "PASS", "RolePermissionSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On RolePermissionSaveButton ." + RolePermissionpage.getExceptionDesc());
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
		
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, email, empid, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}

		if (RolePermissionpage.EmployeeAttendanceButton()) {

			logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.EmpAttendanceTab()) {

			logResults.createLogs("Y", "PASS", "Employee Can Access His Attendance Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Attendance Tab." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EmpRegulationRequest()) {

			logResults.createLogs("Y", "PASS", "Employee Can Access His EmpRegulationRequest Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying EmpRegulationRequest Tab." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EmpOverTimeRequest()) {

			logResults.createLogs("Y", "PASS", "Employee Can Access His EmpOverTimeRequest Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying EmpOverTimeRequest Tab." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EmpShiftRequest()) {

			logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EmpWeekOffRequest()) {

			logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
		}

	}

	// MPI_281_RolePermission_16
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_281_RolePermission_16()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, log in using the employee account where Leave module permissions have been assigned, and ensure thatÂ My Leave Request,Â Comp-off Request, andÂ My Leave BalanceÂ are displayed as per the assigned permissions.");

		ArrayList<String> data = initBase.loadExcelData("company_rolepermission", currTC,
				"password,captcha,rolename");

		

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String rolename = data.get(2);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();


		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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

		if (RolePermissionpage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Permission Page  Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Role Permission Page Not Loaded Completely ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AddRolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Add RolePermission Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add RolePermission Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Role DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role DropDown ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearch(rolename)) {
			logResults.createLogs("Y", "PASS", "Role Selected Successfully." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Role Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LeavePermissionCheckBox()) {
			logResults.createLogs("Y", "PASS", "Leave Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Leave Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionSaveButton()) {
			logResults.createLogs("Y", "PASS", "RolePermissionSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On RolePermissionSaveButton ." + RolePermissionpage.getExceptionDesc());
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
		
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, email, empid, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}

		if (RolePermissionpage.LeaveButton()) {

			logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.LeaveRequestsTab()) {

			logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LeaveBalanceTab()) {

			logResults.createLogs("Y", "PASS", " EmpLeaveBalanceTab Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveBalanceTab."
					+ RolePermissionpage.getExceptionDesc());
		}

	}

	// MPI_282_RolePermission_17
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_282_RolePermission_17()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, log in using the employee account and ensure that the following reports are displayed:In/Out Reports,Â Early & Late In/Out,Â Regularization Report,Â Overtime,Â Total Leaves Taken,Â Weekly Off Report,Â Punch Report, andÂ Attendance Report.");

		ArrayList<String> data = initBase.loadExcelData("company_rolepermission", currTC,
				"password,captcha,rolename");

		

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String rolename = data.get(2);


		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();


		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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

		if (RolePermissionpage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Permission Page  Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Role Permission Page Not Loaded Completely ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AddRolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Add RolePermission Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add RolePermission Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Role DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role DropDown ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearch(rolename)) {
			logResults.createLogs("Y", "PASS", "Role Selected Successfully." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Role Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ReportPermissionCheckBox()) {
			logResults.createLogs("Y", "PASS", "EmpReport Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting EmpReport Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionSaveButton()) {
			logResults.createLogs("Y", "PASS", "RolePermissionSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On RolePermissionSaveButton ." + RolePermissionpage.getExceptionDesc());
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
		
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, email, empid, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Employee EmpReprt Button Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}

	
		if (RolePermissionpage.AttendanceReport()) {

			logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.PunchReport()) {

			logResults.createLogs("Y", "PASS", "PunchReport Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Punch Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.InOutReport()) {

			logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Employee EmpReprt Button Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.TimeLogReport()) {

			logResults.createLogs("Y", "PASS", "TimeLogReport Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying TimeLog Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RegulationReport()) {

			logResults.createLogs("Y", "PASS", "RegulationReport Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Regulation Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.OverTimeReport()) {

			logResults.createLogs("Y", "PASS", "OverTimeReport Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying OverTime Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.TotalLeavesTakenReport()) {

			logResults.createLogs("Y", "PASS", "OverTimeReport Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying TotalLeavesTaken Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.WeekOffReport()) {

			logResults.createLogs("Y", "PASS", "WeekOffReport Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying WeekOffReport Report." + RolePermissionpage.getExceptionDesc());
		}

	}

	// MPI_283_RolePermission_18
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_283_RolePermission_18()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, log in using the employee account and ensure that only the following are displayed.In the Directory module, My Employee table and Org Chart; In the Shift module, My Shift section only; In the File module, My Files and Employee Company Files are displayed.");

		ArrayList<String> data = initBase.loadExcelData("company_rolepermission", currTC,
				"password,captcha,rolename");

		

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String rolename = data.get(2);
	
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();


		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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

		if (RolePermissionpage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Permission Page  Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Role Permission Page Not Loaded Completely ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AddRolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Add RolePermission Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add RolePermission Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Role DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role DropDown ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearch(rolename)) {
			logResults.createLogs("Y", "PASS", "Role Selected Successfully." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Role Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ShiftPermission()) {
			logResults.createLogs("Y", "PASS", "Shift Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Shift Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.DirectoryPermission()) {
			logResults.createLogs("Y", "PASS", "Directory Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Directory Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.FilesPermission()) {
			logResults.createLogs("Y", "PASS", "File Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting File Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionSaveButton()) {
			logResults.createLogs("Y", "PASS", "RolePermissionSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On RolePermissionSaveButton ." + RolePermissionpage.getExceptionDesc());
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
	
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, email, empid, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}

		if (RolePermissionpage.FileButton()) {

			logResults.createLogs("Y", "PASS", "EmpFileButton Clicked Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmpFileButton." + RolePermissionpage.getExceptionDesc());
		}
	

		if (RolePermissionpage.MyFilesTab()) {

			logResults.createLogs("Y", "PASS", "MyFilesTab Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying MyFilesTab In Files Module." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.CompanyFilesTab()) {

			logResults.createLogs("Y", "PASS", "CompanyFilesTab Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying CompanyFilesTab In Files Module." + RolePermissionpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.ShiftButton()) {

			logResults.createLogs("Y", "PASS", "Shift Button Clicked Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Shift Button." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.MyShiftTab()) {

			logResults.createLogs("Y", "PASS", "Shift Tab Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying MyShiftTab In Shift Module." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EmpDirectoryButton()) {

			logResults.createLogs("Y", "PASS", "EmpDirectoryButton Clicked Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmpDirectoryButton." + RolePermissionpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.MyEmployeeTab()) {

			logResults.createLogs("Y", "PASS", "MyEmployeeTab Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying MyEmployeeTab In Directory Module."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ORGChartTab()) {

			logResults.createLogs("Y", "PASS", "ORGChartTab Displayed Successfully In Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying ORGChartTab In Directory Module." + RolePermissionpage.getExceptionDesc());
		}

	}

	// MPI_284_RolePermission_19
	@Test(enabled = true, priority = 8, groups = { "Smoke" })
	public void MPI_284_RolePermission_19()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Assign the HR role to the user and ensure that the user can access the following features.Employee, Regularization Request, Overtime Request, Shift Request, WeekOff Request, and Attendance ReProcess.");

		ArrayList<String> data = initBase.loadExcelData("company_rolepermission", currTC,
				"password,captcha,rolename,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,pin");

		
        int i =0;
		
		String password = data.get(i++);
		String captcha = data.get(i++);
		
		String rolename = data.get(i++);
		

	
		String mailinator = data.get(i++);
		empidhr = data.get(i++) + Pramod.generateRandomAlpha(5);
		

		String empfirstname = data.get(i++) + Pramod.generateRandomAlpha(5);
		String emplastname = data.get(i++) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(i++);
		String empphoneno = data.get(i++) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(i++);
		String reportingto = data.get(i++);
		emailhr = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(3) + mailinator;
	
		String enrollmentimage = data.get(i++);
		String pin = data.get(i++) + Pramod.generateRandomNumber(3);

		
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterRolePage RolePage = new MeghMasterRolePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();


		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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

		if (EmployeePage.EmployeeId(empidhr)) {
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

		if (EmployeePage.EmployeeEmailID(emailhr)) {
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
		

		// enrolling the User

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

		if (RolePermissionpage.DeptDropdown(deptname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + deptname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Dept ." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.DesignationDropdown(designationname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Designation From Dropdown ." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePage.ManageProfileRoleTextField(rolename)) {
			logResults.createLogs("Y", "PASS", "Created Role Name Entered In DropDown Of Manage Profile." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting  Role Name In DropDown ." + RolePage.getExceptionDesc());
		}
		

		if (RolePage.ManageProfileRoleSearchResultselect()) {
			logResults.createLogs("Y", "PASS",
					"Created Role Name Displayed Successfully In Manage Profile  ." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying  Newly Created RoleName ." + RolePage.getExceptionDesc());
		}


		if (RolePermissionpage.TeamDropDown(teamname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team From Dropdown ." + RolePermissionpage.getExceptionDesc());
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

		if (RolePermissionpage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Permission Page  Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Role Permission Page Not Loaded Completely ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AddRolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Add RolePermission Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add RolePermission Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Role DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role DropDown ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearch(rolename)) {
			logResults.createLogs("Y", "PASS", "Role Selected Successfully." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Role Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.HrAttendancePermission()) {
			logResults.createLogs("Y", "PASS", "HrAttendancePermission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting HrAttendancePermission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionSaveButton()) {
			logResults.createLogs("Y", "PASS", "RolePermissionSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On RolePermissionSaveButton ." + RolePermissionpage.getExceptionDesc());
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
		
		

		if (meghlogintest.verifyCompanyLogin(cmpcode, emailhr, empidhr, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}

		

		if (RolePermissionpage.EmployeeAttendanceButton()) {

			logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.HrAccountAttendanceEmpTab()) {

			logResults.createLogs("Y", "PASS", "HrAccountAttendanceEmpTab Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountAttendanceEmpTab In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.HrAccountRegulationRequestTab()) {

			logResults.createLogs("Y", "PASS", "HrAccountRegulationRequestTab Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying HrAccountRegulationRequestTab In Attendance Module."
							+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.HrAccountOTRequestTab()) {

			logResults.createLogs("Y", "PASS", "HrAccountOTRequestTab Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountOTRequestTab In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.HrAccountShiftRequestTab()) {

			logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.HrAccountWeekOffRequestTab()) {

			logResults.createLogs("Y", "PASS", "HrAccountWeekOffRequestTab Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying HrAccountWeekOffRequestTab In Attendance Module."
							+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.HrAccountAttendanceReprocessTab()) {

			logResults.createLogs("Y", "PASS",
					"HrAccountAttendanceReprocessTab Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying HrAccountAttendanceReprocessTab In Attendance Module."
							+ RolePermissionpage.getExceptionDesc());
		}

	}

	// MPI_285_RolePermission_20
	@Test(enabled = true, priority = 8, groups = { "Smoke" })
	public void MPI_285_RolePermission_20()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, assign the HR role and ensure that in the Leave module, the following features are displayed.Leave Balance, Leave Request, Comp-off Request, and Weekly Leave Calendar. Additionally, in the Shift module, ensure that Shift and Roaster are displayed.");

		ArrayList<String> data = initBase.loadExcelData("company_rolepermission", currTC,
				"password,captcha,rolename");

		

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String rolename = data.get(2);
		

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();


		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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

		if (RolePermissionpage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Permission Page  Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Role Permission Page Not Loaded Completely ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AddRolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Add RolePermission Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add RolePermission Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Role DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role DropDown ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearch(rolename)) {
			logResults.createLogs("Y", "PASS", "Role Selected Successfully." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Role Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Role ." + RolePermissionpage.getExceptionDesc());
		}
		if (RolePermissionpage.LeavePermissionCheckBox()) {
			logResults.createLogs("Y", "PASS", "Leave Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Leave Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ShiftPermission()) {
			logResults.createLogs("Y", "PASS", "Shift Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Shift Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionSaveButton()) {
			logResults.createLogs("Y", "PASS", "RolePermissionSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On RolePermissionSaveButton ." + RolePermissionpage.getExceptionDesc());
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
		
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, emailhr, emailhr, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.EmployeeToggleSwitch()) {

			logResults.createLogs("Y", "PASS", "Employee Toggle Switch Successfully To Switch To Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Toggle Switch." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.EmployeeToggleSwitch()) {

			logResults.createLogs("Y", "PASS", "Employee Toggle Switch Successfully To Switch To Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Toggle Switch." + RolePermissionpage.getExceptionDesc());
		}
	

		if (ShiftPage.loginValidate()) {

			logResults.createLogs("Y", "PASS", "login Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Login Completion." + ShiftPage.getExceptionDesc());
		}

		if (RolePermissionpage.LeaveButton()) {

			logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
		}
	
		if (RolePermissionpage.LeaveRequestsTab()) {

			logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
					+ RolePermissionpage.getExceptionDesc());
		}


		if (RolePermissionpage.LeaveBalanceTab()) {

			logResults.createLogs("Y", "PASS", " EmpLeaveBalanceTab Clicked Successfully Of HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveBalanceTab."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.HrAccountLeaveWeeklyLeaveTab()) {

			logResults.createLogs("Y", "PASS", " HrAccountLeaveWeeklyLeaveTab Clicked Successfully Of HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module HrAccountLeaveWeeklyLeaveTab."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ShiftButton()) {

			logResults.createLogs("Y", "PASS", "Shift Button Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Shift Button." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.HrAccountShiftTab()) {

			logResults.createLogs("Y", "PASS", "HrAccountShift Button Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On HrAccountShift Button." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.HrAccountShiftRosterTab()) {

			logResults.createLogs("Y", "PASS", "HrAccountShiftRoster Tab  Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On HrAccountShiftRoster Tab ." + RolePermissionpage.getExceptionDesc());
		}

	}

	// MPI_286_RolePermission_21
	@Test(enabled = true, priority = 9, groups = { "Smoke" })
	public void MPI_286_RolePermission_21()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, assign the HR role to the user and ensure that the following permissions are displayed in the Reports module.In/Out Reports, Early & Late In/Out, Regularization Report, Overtime, Leave Balance Summary, Total Leaves Taken, Weekly Off Report, Employee Data Report, Onboarding and Offboarding, Employee Block/Unblock, Device Punch Type, Employee Active/InActive, Punch Report, Monthly Attendance Register Report, Attendance Report, Monthly Time Entry Report, Punch Statistics Report, Holiday Report, Yearly Attendance Summary Report, Daily Attendance Report, Overtime Report, Missing Punch Report, Late Arrival Report, Schedule Report History, Muster Roll Report, Monthly Shift Wise Attendance Report, Form No.25 Report, Form No.23 Report.");

		ArrayList<String> data = initBase.loadExcelData("company_rolepermission", currTC,
				"password,captcha,rolename");

		

		
		String password = data.get(0);
		String captcha = data.get(1);
	
		String rolename = data.get(2);
		

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();


		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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

		if (RolePermissionpage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Permission Page  Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Role Permission Page Not Loaded Completely ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AddRolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Add RolePermission Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add RolePermission Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Role DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role DropDown ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearch(rolename)) {
			logResults.createLogs("Y", "PASS", "Role Selected Successfully." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Role Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ReportPermissionCheckBox()) {
			logResults.createLogs("Y", "PASS", "EmpReport Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting EmpReport Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionSaveButton()) {
			logResults.createLogs("Y", "PASS", "RolePermissionSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On RolePermissionSaveButton ." + RolePermissionpage.getExceptionDesc());
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
		
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, emailhr, emailhr, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}

		
		if (RolePermissionpage.EmployeeToggleSwitch()) {

			logResults.createLogs("Y", "PASS", " Toggle Switch Clicked Successfully To Switch To Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Toggle Switch." + RolePermissionpage.getExceptionDesc());
		}

		

		if (RolePermissionpage.EmployeeToggleSwitch()) {

			logResults.createLogs("Y", "PASS", " Toggle Switch Clicked Successfully To Switched Back To HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Toggle Switch." + RolePermissionpage.getExceptionDesc());
		}

		

		if (ShiftPage.loginValidate()) {

			logResults.createLogs("Y", "PASS", "login Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Login Completion." + ShiftPage.getExceptionDesc());
		}

		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully On HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}

		
		if (RolePermissionpage.AttendanceReport()) {

			logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.PunchReport()) {

			logResults.createLogs("Y", "PASS", "PunchReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Punch Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.InOutReport()) {

			logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully On HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.TimeLogReport()) {

			logResults.createLogs("Y", "PASS", "TimeLogReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying TimeLog Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RegulationReport()) {

			logResults.createLogs("Y", "PASS", "RegulationReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Regulation Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.OverTimeReport()) {

			logResults.createLogs("Y", "PASS", "OverTimeReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying OverTime Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.DailyAttendanceReport()) {

			logResults.createLogs("Y", "PASS", "DailyAttendanceReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying DailyAttendance Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LateArrivalReport()) {

			logResults.createLogs("Y", "PASS", "LateArrivalReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying LateArrivalReport Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.MonthlyShiftWiseAttendanceReport()) {

			logResults.createLogs("Y", "PASS",
					"MonthlyShiftWiseAttendanceReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying MonthlyShiftWiseAttendanceReport Report."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.MonthlyAttendanceRegisterReport()) {

			logResults.createLogs("Y", "PASS",
					"MonthlyAttendanceRegisterReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying MonthlyAttendanceRegisterReport Report."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.MusterRollReport()) {

			logResults.createLogs("Y", "PASS", "MusterRollReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying MusterRollReport Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.OverTimeReports()) {

			logResults.createLogs("Y", "PASS", "OverTimeReports Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying OverTimeReports Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EarlyLateInOut()) {

			logResults.createLogs("Y", "PASS", "EarlyLateInOut Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying EarlyLateInOut Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.PunchStatisticsReportTab()) {

			logResults.createLogs("Y", "PASS", "PunchStatisticsReportTab Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying PunchStatisticsReportTab Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully On HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.YearlyAttendanceSummaryReport()) {

			logResults.createLogs("Y", "PASS", "YearlyAttendanceSummaryReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying YearlyAttendanceSummaryReport Report."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.FormNo25Report()) {

			logResults.createLogs("Y", "PASS", "FormNo25Report Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying FormNo25Report Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.FormNo23Report()) {

			logResults.createLogs("Y", "PASS", "FormNo23Report Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying FormNo23Report Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LeaveBalanceSummary()) {

			logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying LeaveBalanceSummary Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.TotalLeavesTakenReport()) {

			logResults.createLogs("Y", "PASS", "OverTimeReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying TotalLeavesTaken Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.WeekOffReport()) {

			logResults.createLogs("Y", "PASS", "WeekOffReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying WeekOffReport Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EmployeeActiveInActiveReport()) {

			logResults.createLogs("Y", "PASS", "EmployeeActiveInActiveReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying EmployeeActiveInActiveReport Report."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EmployeeBlockUnblockReport()) {

			logResults.createLogs("Y", "PASS", "EmployeeBlockUnblockReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying EmployeeBlockUnblockReport Report."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EmployeeDataReport()) {

			logResults.createLogs("Y", "PASS", "EmployeeDataReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying EmployeeDataReport Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.OnboardingAndOffboarding()) {

			logResults.createLogs("Y", "PASS", "OnboardingAndOffboarding Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying OnboardingAndOffboarding Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.PunchTypeAnalyticsReport()) {

			logResults.createLogs("Y", "PASS", "PunchTypeAnalyticsReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying PunchTypeAnalyticsReport Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ScheduleReports()) {

			logResults.createLogs("Y", "PASS", "ScheduleReports Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying ScheduleReports Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.BillLedgerReport()) {

			logResults.createLogs("Y", "PASS", "BillLedgerReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying BillLedgerReport Report." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.MonthlyBillTransactionReport()) {

			logResults.createLogs("Y", "PASS", "MonthlyBillTransactionReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying MonthlyBillTransactionReport Report."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.DailyBillTransactionReport()) {

			logResults.createLogs("Y", "PASS", "DailyBillTransactionReport Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying DailyBillTransactionReport Report."
					+ RolePermissionpage.getExceptionDesc());
		}

	}

	// MPI_287_RolePermission_22
	@Test(enabled = true, priority = 11, groups = { "Smoke" })
	public void MPI_287_RolePermission_22()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, assign the HR role to the user and ensure that the following permissions are accessible.Employee Files from the File module, Attendance Policy, Leave Policy, Weekly-off Policy, and Holidays, and shiftpolicy , approval flow and shift permission from the Policy Master.");

		ArrayList<String> data = initBase.loadExcelData("company_rolepermission", currTC,
				"password,captcha,rolename");

		

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String rolename = data.get(2);
		

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();

		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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

		if (RolePermissionpage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Permission Page  Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Role Permission Page Not Loaded Completely ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AddRolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Add RolePermission Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add RolePermission Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Role DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role DropDown ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearch(rolename)) {
			logResults.createLogs("Y", "PASS", "Role Selected Successfully." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Role Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.PolicyMasterPermission()) {
			logResults.createLogs("Y", "PASS", "PolicyMaster Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting PolicyMaster Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.FilesPermission()) {
			logResults.createLogs("Y", "PASS", "File Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting File Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionSaveButton()) {
			logResults.createLogs("Y", "PASS", "RolePermissionSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On RolePermissionSaveButton ." + RolePermissionpage.getExceptionDesc());
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
		
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, emailhr, emailhr, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.EmployeeToggleSwitch()) {

			logResults.createLogs("Y", "PASS", " Toggle Switch Clicked Successfully To Switch To Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Toggle Switch." + RolePermissionpage.getExceptionDesc());
		}

		

		if (RolePermissionpage.EmployeeToggleSwitch()) {

			logResults.createLogs("Y", "PASS", " Toggle Switch Clicked Successfully To Switched Back To HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Toggle Switch." + RolePermissionpage.getExceptionDesc());
		}

		

		if (ShiftPage.loginValidate()) {

			logResults.createLogs("Y", "PASS", "login Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Login Completion." + ShiftPage.getExceptionDesc());
		}

		if (RolePermissionpage.FileButton()) {

			logResults.createLogs("Y", "PASS", "EmpFileButton Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmpFileButton." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.EmployeeFilesTab()) {

			logResults.createLogs("Y", "PASS", "EmployeeFilesTab Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmpFileButton." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.CompanyFilesTabs()) {

			logResults.createLogs("Y", "PASS", "CompanyFilesTabs Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CompanyFilesTabs." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AttendancePolicy()) {

			logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AttendancePolicy." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LeaveDropDown()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDown Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LeavePolicy()) {

			logResults.createLogs("Y", "PASS", "LeavePolicy Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeavePolicy." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.WeeklyOffPolicy()) {

			logResults.createLogs("Y", "PASS", "WeeklyOffPolicy Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeeklyOffPolicy." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.HoliDayPolicy()) {

			logResults.createLogs("Y", "PASS", "HoliDayPolicy Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On HoliDayPolicy." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyButton Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyButton." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ShiftPolicyTab()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyTab Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyTab." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ApprovalFlowDropDown()) {

			logResults.createLogs("Y", "PASS", "ApprovalFlowDropDown Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ApprovalFlowDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ApprovalFlowButton()) {

			logResults.createLogs("Y", "PASS", "ApprovalFlowButton Clicked Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ApprovalFlowButton." + RolePermissionpage.getExceptionDesc());
		}

	}

	// MPI_288_RolePermission_23
	@Test(enabled = true, priority = 12, groups = { "Smoke" })
	public void MPI_288_RolePermission_23()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, assign the Manager role to the user and ensure that they have access to all permissions, including Dashboard, Attendance, Master Module, Policy Master, Leave, Shift, Reports, Directory, Files, and Billing Module.");

		ArrayList<String> data = initBase.loadExcelData("company_rolepermission", currTC,
				"password,captcha,rolename,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,reportingto,enrollmentimage,pin,empjoiningdate");

		
int i = 0;
	
		String password = data.get(i++);
		String captcha = data.get(i++);
		
		String rolename = data.get(i++);
		

		
		String mailinator = data.get(i++);
		String empid = data.get(i++) + Pramod.generateRandomNumber(6);
	

		String empfirstname = data.get(i++) + Pramod.generateRandomAlpha(5);
		String emplastname = data.get(i++) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(i++);
		String empphoneno = data.get(i++) + Pramod.generateRandomNumber(5);
		String reportingto = data.get(i++);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(3) + mailinator;
		
		String enrollmentimage = data.get(i++);
		String pin = data.get(i++) + Pramod.generateRandomNumber(3);
		String empjoiningdate = data.get(i++);

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterDesignationPage DesignationPage = new MeghMasterDesignationPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterRolePage RolePage = new MeghMasterRolePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();

		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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
		

		// enrolling the User

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

		if (RolePermissionpage.EmailInvite()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On EmailInvite.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record EmailInvite ."
					+ RolePermissionpage.getExceptionDesc());
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

		if (RolePermissionpage.DeptDropdown(deptname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + deptname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Dept ." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePermissionpage.DesignationDropdown(designationname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Designation From Dropdown ." + RolePermissionpage.getExceptionDesc());
		}
		

		if (RolePage.ManageProfileRoleTextField(rolename)) {
			logResults.createLogs("Y", "PASS", "Created Role Name Entered In DropDown Of Manage Profile." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting  Role Name In DropDown ." + RolePage.getExceptionDesc());
		}
		

		if (RolePage.ManageProfileRoleSearchResultselect()) {
			logResults.createLogs("Y", "PASS",
					"Created Role Name Displayed Successfully In Manage Profile  ." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying  Newly Created RoleName ." + RolePage.getExceptionDesc());
		}

		

		if (RolePermissionpage.TeamDropDown(teamname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team From Dropdown ." + RolePermissionpage.getExceptionDesc());
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

		if (RolePermissionpage.RolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Role Permission Page  Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Role Permission Page Not Loaded Completely ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AddRolePermissionButton()) {
			logResults.createLogs("Y", "PASS", "Add RolePermission Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add RolePermission Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Role DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Role DropDown ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearch(rolename)) {
			logResults.createLogs("Y", "PASS", "Role Selected Successfully." + rolename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RoleDropDownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Role Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Role ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.MasterPermissionCheckBox()) {
			logResults.createLogs("Y", "PASS", "MasterPermissionCheckBox Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting MasterPermissionCheckBox Permission ."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ReportPermissionCheckBox()) {
			logResults.createLogs("Y", "PASS", "EmpReport Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting EmpReport Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ShiftPermission()) {
			logResults.createLogs("Y", "PASS", "Shift Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Shift Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.SettingsCheckBox()) {
			logResults.createLogs("Y", "PASS", "Settings Checkbox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Settings CheckBox ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.FilesPermission()) {
			logResults.createLogs("Y", "PASS", "File Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting File Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EmployeeAttendancePermission()) {
			logResults.createLogs("Y", "PASS", "Attendance Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Attendance Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LeavePermissionCheckBox()) {
			logResults.createLogs("Y", "PASS", "Leave Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Leave Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.HomePermissionCheckBox()) {
			logResults.createLogs("Y", "PASS", "HomePermissionCheckBox  Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Home Module Permission CheckBox." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.DirectoryPermission()) {
			logResults.createLogs("Y", "PASS", "Directory Permission Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Directory Permission ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.BillingPermissionCheckBox()) {
			logResults.createLogs("Y", "PASS", "BillingPermissionCheckBox  Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting Billing Module Permission CheckBox."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.RolePermissionSaveButton()) {
			logResults.createLogs("Y", "PASS", "RolePermissionSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On RolePermissionSaveButton ." + RolePermissionpage.getExceptionDesc());
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
		
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, email, empid, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}

		
		if (RolePermissionpage.EmployeeToggleSwitch()) {

			logResults.createLogs("Y", "PASS", " Toggle Switch Clicked Successfully To Switch To Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Toggle Switch." + RolePermissionpage.getExceptionDesc());
		}

		

		if (RolePermissionpage.EmployeeToggleSwitch()) {

			logResults.createLogs("Y", "PASS",
					" Toggle Switch Clicked Successfully To Switched Back To Manager Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Toggle Switch." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPage.loginValidate()) {

			logResults.createLogs("Y", "PASS", "login Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Login Completion." + ShiftPage.getExceptionDesc());
		}

		if (RolePermissionpage.BillingButton()) {

			logResults.createLogs("Y", "PASS", "BillingButton Displayed Successfully In Manager Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying BillingButton ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.BillingPageWalletTab()) {

			logResults.createLogs("Y", "PASS", "Billing Module Wallet Tab Displayed Successfully In Manager Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Wallet Tab ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.HomeButton()) {

			logResults.createLogs("Y", "PASS", "HomeButton Displayed Successfully In Manager Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying HomeButton ." + RolePermissionpage.getExceptionDesc());
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
		

		if (EmployeePage.TotalCountDropDown()) {
			logResults.createLogs("Y", "PASS", "Clicked On TotalCountDropDown Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TotalCountDropDown." + EmployeePage.getExceptionDesc());
		}

		if (RolePermissionpage.EntityActiveInactive()) {
			logResults.createLogs("Y", "PASS", "EntityActiveInactive Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying EntityActiveInactive." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EntityBlockUnBlock()) {
			logResults.createLogs("Y", "PASS", "EntityBlockUnBlock Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying EntityBlockUnBlock." + RolePermissionpage.getExceptionDesc());
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

		if (DepartmentPage.DepartmentDropIcon()) {
			logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department Tab ." + DepartmentPage.getExceptionDesc());
		}

		if (TeamPage.TeamDropIcon()) {
			logResults.createLogs("Y", "PASS", "Team Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Team Button ." + TeamPage.getExceptionDesc());
		}

		if (DesignationPage.DesignationButon()) {
			logResults.createLogs("Y", "PASS", "Designation Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Designation Button ." + DesignationPage.getExceptionDesc());
		}

		if (RolePage.RoleButton()) {
			logResults.createLogs("Y", "PASS", "Role Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Role Button ." + RolePage.getExceptionDesc());
		}

		if (RolePermissionpage.GradeButton()) {
			logResults.createLogs("Y", "PASS", "GradeButton   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Grade Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EntityTtypeButton()) {
			logResults.createLogs("Y", "PASS", "EntityType   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EntityType Button ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.AttendanceStatusButton()) {
			logResults.createLogs("Y", "PASS", "AttendanceStatusButton   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AttendanceStatusButton ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.FileTagButton()) {
			logResults.createLogs("Y", "PASS", "FileTagButton   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FileTagButton ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LeaveTypeButton()) {
			logResults.createLogs("Y", "PASS", "LeaveTypeButton   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LeaveTypeButton ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.OrgChart()) {
			logResults.createLogs("Y", "PASS", "OrgChart   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On OrgChart ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.SettingIcon()) {
			logResults.createLogs("Y", "PASS", "SettingIcon   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SettingIcon ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.EmployeeMasterDropDown()) {
			logResults.createLogs("Y", "PASS", "EmployeeMasterDropDown   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeMasterDropDown ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.ImportBulkForm()) {
			logResults.createLogs("Y", "PASS", "ImportBulkForm   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On ImportBulkForm ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.FieldConfiguration()) {
			logResults.createLogs("Y", "PASS", "FieldConfiguration   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FieldConfiguration ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.FieldsConfigurationText()) {
			logResults.createLogs("Y", "PASS", "FieldsConfigurationText   Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying FieldsConfigurationText ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.SettingIcon()) {
			logResults.createLogs("Y", "PASS", "SettingIcon   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SettingIcon ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.GeneralSettingsButton()) {
			logResults.createLogs("Y", "PASS", "GeneralSettingsButton   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GeneralSettingsButton ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.SettingIcon()) {
			logResults.createLogs("Y", "PASS", "SettingIcon   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SettingIcon ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.PersonalSettingsTab()) {
			logResults.createLogs("Y", "PASS", "PersonalSettingsTab   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On PersonalSettingsTab ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.SettingIcon()) {
			logResults.createLogs("Y", "PASS", "SettingIcon   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SettingIcon ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.SMTPSettingsTab()) {
			logResults.createLogs("Y", "PASS", "SMTPSettingsTab   Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SMTPSettingsTab ." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully In Manager Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.DeviceSetup()) {

			logResults.createLogs("Y", "PASS", "DeviceSetup Clicked Successfully In Manager Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeviceSetup." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.DeviceList()) {

			logResults.createLogs("Y", "PASS", "DeviceList Clicked Successfully In Manager Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeviceList." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.DeviceGroup()) {

			logResults.createLogs("Y", "PASS", "DeviceGroup Clicked Successfully In Manager Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeviceGroup." + RolePermissionpage.getExceptionDesc());
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
