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

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import com.MeghPI.Attendance.pages.MeghAttendancePolicyPage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentMappingPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamMappingPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;

import utils.Pramod;
import utils.Utils;

public class MeghMasterTeamMappingTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	public String NameofOFffice = "";
	private String officename = "";
	private String deptname = "";
	public String emplastname = "";
	private String teamname = "";
	private String deptnamess = "";
	public String teamnamebeforeassigning = "";

	private String cmpcode = "";
	private String Emailid = "";
	private String entityname = "";
	private String designationname = "";

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
		designationname = "AutoDESN" + initBase.executionRunTime;
		
		System.out.println("cmpcode and emailid" +cmpcode+Emailid );
	}

	// MPI_518_TeamMapping_01
	@Test(enabled = true, priority = 1, groups = { "Smoke", "AddMaster" })
	public void MPI_518_TeamMapping_01() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a department and map it with an office. Then, navigate to Team Mapping, click on 'Add Team Mapping', select the created team, select the mapped department, and save. After saving, create an employee for the selected location. Navigate to the employee's profile, select the department, click on the team field, enter the team name, and ensure that the mapped team is displayed.");

		ArrayList<String> data = initBase.loadExcelData("Master_teammapping", currTC,
				"password,captcha,deptname,officename,mailinator,empid,empfirstname,emplastname,empemail,empphoneno,empjoiningdate,reportingto,enrollmentimage,state,teamname,pin");

		MeghMasterTeamMappingPage TeamMappingPage = new MeghMasterTeamMappingPage(driver);
		String password = data.get(0);
		String captcha = data.get(1);
		deptname = data.get(2) + initBase.executionRunTime;
		officename = data.get(3) + initBase.executionRunTime;

		
		String mailinator = data.get(4);
		String empid = data.get(5) + Pramod.generateRandomAlpha(6);
		String empfirstname = data.get(6) + Pramod.generateRandomAlpha(5);
		emplastname = data.get(7) + Pramod.generateRandomAlpha(5);
		String empemail = data.get(8);
		String empphoneno = data.get(9) + Pramod.generateRandomNumber(5);
		String empjoiningdate = data.get(10);
		String reportingto = data.get(11);
		String email = empemail + initBase.executionRunTime + Pramod.generateRandomAlpha(3) + mailinator;
		String enrollmentimage = data.get(12);
		String state = data.get(13);
		teamname = data.get(14) + initBase.executionRunTime;
		String pin = data.get(15) + Pramod.generateRandomNumber(3);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);

		String OfficeName = Utils.propsReadWrite("data/addmaster.properties", "get", "OfficeName", "");
		String DeptName = Utils.propsReadWrite("data/addmaster.properties", "get", "DeptName", "");
		String TeamName = Utils.propsReadWrite("data/addmaster.properties", "get", "TeamName", "");

		if (initBase.stopNewData) { // Tapan 5 July 25
			if (OfficeName.equals("pass") && DeptName.equals("pass") && TeamName.equals("pass")) {
				logResults.createLogs("Y", "INFO", "Skip Adding New Data. Continue with Old Test Data.");
				throw new SkipException("Skip Adding New Data. Continue with Old Test Data.");
			}
		}

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}

		// OfficeName Logic
		if (!OfficeName.equals("pass")) {
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

			if (EmployeePage.OfficeButton()) {
				logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Office Button : " + EmployeePage.getExceptionDesc());
			}

			if (EmployeePage.AddOfficeButton()) {
				logResults.createLogs("Y", "PASS", " Successfully Clicked On AddOfficeButton : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On AddOfficeButton : " + EmployeePage.getExceptionDesc());
			}

			if (EmployeePage.OfficeName(officename)) {
				logResults.createLogs("Y", "PASS", " OfficeName Entered In the Office Name TextField : " + officename);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Inputting The Office Name : " + EmployeePage.getExceptionDesc());
			}

			if (EmployeePage.OfficeTypeDropdown()) {
				logResults.createLogs("Y", "PASS", " Office Type Selected In the Office Type Dropdown : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting The Office Type : " + EmployeePage.getExceptionDesc());
			}

			if (EmployeePage.CountryDropdown()) {
				logResults.createLogs("Y", "PASS", " Country Selected In the Country  Dropdown : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting The Country : " + EmployeePage.getExceptionDesc());
			}

			if (EmployeePage.StateDropdown(state)) {
				logResults.createLogs("Y", "PASS", " State Selected In the State  Dropdown : " + state);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting The State : " + EmployeePage.getExceptionDesc());
			}

			if (EmployeePage.CompanyLocationSaveButton()) {
				Utils.propsReadWrite("data/addmaster.properties", "set", "OfficeName", "pass");
				logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton : ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On CompanyLocationSaveButton : " + EmployeePage.getExceptionDesc());
			}

			Thread.sleep(2000);
		} // End of office Name

		// Dept Name
		if (!DeptName.equals("pass")) {
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

			if (DepartmentMappingpage.DepartmentSelectDropDown(deptname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected Department: " + deptname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Department : " + DepartmentMappingpage.getExceptionDesc());
			}

			if (DepartmentMappingpage.OfficeSelectDropDown(officename)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected OfficeName: " + officename);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting OfficeName : " + DepartmentMappingpage.getExceptionDesc());
			}

			if (DepartmentMappingpage.AddDeptMappingSaveButton()) {
				Utils.propsReadWrite("data/addmaster.properties", "set", "DeptName", "pass");
				logResults.createLogs("Y", "PASS", "AddDeptMappingSaveButton Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error while Clicking On AddDeptMappingSaveButton: "
						+ DepartmentMappingpage.getExceptionDesc());
			}

			Thread.sleep(2000);
		} // End of Dept Name
		
		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (!TeamName.equals("pass")) {

			if (EmployeePage.CompanyTab()) {
				logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Company Tab : " + EmployeePage.getExceptionDesc());
			}
			
			
			
			if (TeamPage.TeamDropIcon()) {
				logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
			}

			if (TeamPage.TeamButton()) {
				logResults.createLogs("Y", "PASS", "Team Button  Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Team Button : " + TeamPage.getExceptionDesc());
			}

			if (TeamPage.AddTeamButton()) {
				logResults.createLogs("Y", "PASS", "Add Team Button  Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Add Team Button : " + TeamPage.getExceptionDesc());
			}

			if (TeamPage.TeamNameTextField(teamname)) {
				logResults.createLogs("Y", "PASS", "Team Name Is Entered In Team Name Text Field: " + teamname);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Team Name: " + TeamPage.getExceptionDesc());
			}

			if (TeamPage.TeamSaveButton()) {
				logResults.createLogs("Y", "PASS", "Team Save Button  Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On TeamSaveButton : " + TeamPage.getExceptionDesc());
			}

			Thread.sleep(3000);
			
			if (TeamPage.TeamDropIcon()) {
				logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
			}

			if (TeamPage.TeamMappingButton()) {
				logResults.createLogs("Y", "PASS", "TeamMapping Button  Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On TeamMapping Button : " + TeamPage.getExceptionDesc());
			}

			if (TeamPage.AddTeamMappingButton()) {
				logResults.createLogs("Y", "PASS", "AddTeamMapping Button  Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On AddTeamMapping Button : " + TeamPage.getExceptionDesc());
			}

			if (TeamMappingPage.TeamDropDownOfForm(teamname)) {
				logResults.createLogs("Y", "PASS", "Team Name Selected Successfully: " + teamname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Team Name : " + TeamMappingPage.getExceptionDesc());
			}

			if (TeamMappingPage.TeamDeptDropDown()) {
				logResults.createLogs("Y", "PASS", "Department DropDown Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Department DropDown : " + TeamMappingPage.getExceptionDesc());
			}

			if (TeamMappingPage.TeamDeptSearchField(deptname)) {
				logResults.createLogs("Y", "PASS", "Department Name Inputted Successfully: " + deptname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Inputting Department Name : " + TeamMappingPage.getExceptionDesc());
			}
			Thread.sleep(3000);

			if (TeamMappingPage.TeamDeptSearchResult()) {
				logResults.createLogs("Y", "PASS", "Department Name Selected Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Department Name : " + TeamMappingPage.getExceptionDesc());
			}

			if (TeamMappingPage.AddTeamMappingSaveButton()) {
				Utils.propsReadWrite("data/addmaster.properties", "set", "TeamName", "pass");
				logResults.createLogs("Y", "PASS", "Add Team Mapping Saved Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Saving The Team Mapping : " + TeamMappingPage.getExceptionDesc());
			}

//			if (TeamPage.ManageProfileTeamDropdownSearchResult()) {
//				
//				logResults.createLogs("Y", "PASS", "Successfully Displayed Team Name: ");
//			} else {
//				logResults.createLogs("Y", "FAIL", "Error while Displaying Team Name : " + TeamPage.getExceptionDesc());
//			}
		} // Team Name

		Thread.sleep(4000);

		// creating new user
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
		
		if (EmployeePage.FirstDateSelection(empjoiningdate)) {
			logResults.createLogs("Y", "PASS",
					"Employee Joining Date Entered On Joining Date Text Field: " + empjoiningdate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Joining Date: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEmployeeSave()) {
			logResults.createLogs("Y", "PASS", "Clicked On AddEmployeeSave Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEmployeeSave: " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(3000);

		// enrolling the User

		if (EmployeePage.GoToDirectory()) {
			logResults.createLogs("Y", "PASS", "Clicked On GoToDirectory Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GoToDirectory: " + EmployeePage.getExceptionDesc());
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
		Thread.sleep(4000);

		if (DepartmentPage.EditIcon()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile : "
					+ DepartmentPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (DepartmentPage.DepartmentDropDown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentDropDown : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentDropDown  : " + DepartmentPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (TeamMappingPage.DeptDropDownTextField(deptname)) {
			logResults.createLogs("Y", "PASS", "Successfully Input The DeptName : " + deptname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting  Department Name  : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(2000);
		if (TeamMappingPage.DeptSearchResult()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected DeptName : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting  Department Name  : " + TeamMappingPage.getExceptionDesc());
		}

		Thread.sleep(2000);
		
		if (AttendancePolicyPage.TeamDropDown(teamname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
		}
		Thread.sleep(2000);
/*
		if (TeamPage.ManageProfileTeamDropdown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On TeamDropDown: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On TeamDropDown : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.ManageProfileTeamDropdownSearchField(teamname)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Team Name: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputted Team Name : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);
		
		*/
		
		if (AttendancePolicyPage.DesignationDropdown(designationname)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown: " + designationname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Designation From Dropdown : " + AttendancePolicyPage.getExceptionDesc());
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

	// MPI_521_TeamMapping_04
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_521_TeamMapping_04() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, disable the mapped department which isn't mapped with team and ensure the disabled mapped department is not displayed in the 'Team Department' dropdown of the 'Add Team Department Mapping' form.");

		ArrayList<String> data = initBase.loadExcelData("Master_teammapping", currTC,
				"password,captcha,deptname,officename,state");

		MeghMasterTeamMappingPage TeamMappingPage = new MeghMasterTeamMappingPage(driver);
		

	
		String password = data.get(0);
		String captcha = data.get(1);
		
		deptnamess = data.get(2) + Pramod.generateRandomAlpha(5);

		String officenamess = data.get(3) + Pramod.generateRandomAlpha(7);
		
		String state = data.get(4);

		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);
		
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);

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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddOfficeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddOfficeButton : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddOfficeButton : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeName(officenamess)) {
			logResults.createLogs("Y", "PASS", " OfficeName Entered In the Office Name TextField : " + officenamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Office Name : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeTypeDropdown()) {
			logResults.createLogs("Y", "PASS", " Office Type Selected In the Office Type Dropdown : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The Office Type : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CountryDropdown()) {
			logResults.createLogs("Y", "PASS", " Country Selected In the Country  Dropdown : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The Country : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.StateDropdown(state)) {
			logResults.createLogs("Y", "PASS", " State Selected In the State  Dropdown : " + state);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting The State : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationSaveButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CompanyLocationSaveButton : " + EmployeePage.getExceptionDesc());
		}

		Thread.sleep(2000);

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

		if (DepartmentPage.AddDepartmentButton()) {
			logResults.createLogs("Y", "PASS", "Add Department Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Department Tab : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentName(deptnamess)) {
			logResults.createLogs("Y", "PASS", "Department Name Entered Successfully: " + deptnamess);
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

		if (DepartmentMappingpage.DepartmentSelectDropDown(deptnamess)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected Department: " + deptnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Department : " + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.OfficeSelectDropDown(officenamess)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected OfficeName: " + officenamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting OfficeName : " + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentMappingpage.AddDeptMappingSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddDeptMappingSaveButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddDeptMappingSaveButton: " + DepartmentMappingpage.getExceptionDesc());
		}

		Thread.sleep(2000);

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

		

		if (DepartmentPage.DepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentMapping Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentMappingpage.SearchTextField(deptnamess)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted DeptName In Search TextField: " + deptnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Dept Name : " + DepartmentMappingpage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (DepartmentMappingpage.MappedDeptToggleSwitch()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Mapped Dept Toggle Switch: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Mapped Dept Toggle Switch : " + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentToggleSwitchConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Ok Button : " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab : " + EmployeePage.getExceptionDesc());
		}
		
		if (TeamPage.TeamDropIcon()) {
			logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
		}

		
		Thread.sleep(3000);

		if (TeamPage.TeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "TeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TeamMapping Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.AddTeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "AddTeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddTeamMapping Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamMappingPage.TeamDeptDropDown()) {
			logResults.createLogs("Y", "PASS", "Department DropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department DropDown : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.TeamDeptSearchField(deptnamess)) {
			logResults.createLogs("Y", "PASS", "Department Name Inputted Successfully: " + deptnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (TeamPage.NoResultFoundMsg()) {
			logResults.createLogs("Y", "PASS",
					"Disabled Team Name Isn't Displayed And Error Msg Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Displaying Error Msg : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

	}

	// MPI_522_TeamMapping_05
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_522_TeamMapping_05() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enable the previously disabled mapped department and ensure that after enabling, the mapped department is displayed in the 'Team Department' dropdown of the 'Add Team Department Mapping' form.");

		ArrayList<String> data = initBase.loadExcelData("Master_teammapping", currTC,
				"password,captcha");

		MeghMasterTeamMappingPage TeamMappingPage = new MeghMasterTeamMappingPage(driver);
		

		
		String password = data.get(0);
		String captcha = data.get(1);
	

		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);
		
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);

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

		
		if (DepartmentPage.DepartmentMappingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentMapping Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentMappingpage.SearchTextField(deptnamess)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted DeptName In Search TextField: " + deptnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Dept Name : " + DepartmentMappingpage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (DepartmentMappingpage.MappedDeptToggleSwitch()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Mapped Dept Toggle Switch: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Mapped Dept Toggle Switch : " + DepartmentMappingpage.getExceptionDesc());
		}

		if (DepartmentPage.DepartmentToggleSwitchConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Ok Button : " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab : " + EmployeePage.getExceptionDesc());
		}
		
		if (TeamPage.TeamDropIcon()) {
			logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
		}

		

		Thread.sleep(3000);

		if (TeamPage.TeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "TeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TeamMapping Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.AddTeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "AddTeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddTeamMapping Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamMappingPage.TeamDeptDropDown()) {
			logResults.createLogs("Y", "PASS", "Department DropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department DropDown : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.TeamDeptSearchField(deptnamess)) {
			logResults.createLogs("Y", "PASS", "Department Name Inputted Successfully: " + deptnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (TeamMappingPage.TeamDeptSearchResult()) {
			logResults.createLogs("Y", "PASS", "Department Name Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Department Name : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(2000);

	}

	// MPI_528_TeamMapping_11
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_528_TeamMapping_11() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a new team, click the edit icon of an already mapped team record, select the new team name and map it. Then, navigate to the employee profile and ensure that after selecting the department, the new mapped team name is displayed in the team dropdown.");

		ArrayList<String> data = initBase.loadExcelData("Master_teammapping", currTC,
				"password,captcha,teamname");

		MeghMasterTeamMappingPage TeamMappingPage = new MeghMasterTeamMappingPage(driver);
		

	
		String password = data.get(0);
		String captcha = data.get(1);
	
		String teamnamess = data.get(2) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);

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
		
		if (TeamPage.TeamDropIcon()) {
			logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamButton()) {
			logResults.createLogs("Y", "PASS", "Team Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Team Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.AddTeamButton()) {
			logResults.createLogs("Y", "PASS", "Add Team Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Team Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamNameTextField(teamnamess)) {
			logResults.createLogs("Y", "PASS", "Team Name Is Entered In Team Name Text Field: " + teamnamess);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Team Name: " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamSaveButton()) {
			logResults.createLogs("Y", "PASS", "Team Save Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TeamSaveButton : " + TeamPage.getExceptionDesc());
		}

		Thread.sleep(3000);
		
		if (TeamPage.TeamDropIcon()) {
			logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "TeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TeamMapping Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.AddTeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "AddTeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddTeamMapping Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamMappingPage.TeamDropDownOfForm(teamnamess)) {
			logResults.createLogs("Y", "PASS", "Team Name Selected Successfully: " + teamnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team Name : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.TeamDeptDropDown()) {
			logResults.createLogs("Y", "PASS", "Department DropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department DropDown : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.TeamDeptSearchField(deptname)) {
			logResults.createLogs("Y", "PASS", "Department Name Inputted Successfully: " + deptname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (TeamMappingPage.TeamDeptSearchResult()) {
			logResults.createLogs("Y", "PASS", "Department Name Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Department Name : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.AddTeamMappingSaveButton()) {
			logResults.createLogs("Y", "PASS", "Add Team Mapping Saved Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Saving The Team Mapping : " + TeamMappingPage.getExceptionDesc());
		}

		Thread.sleep(4000);

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

		

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully: " + emplastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Employee: " + EmployeePage.getExceptionDesc());
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

		if (DepartmentPage.DepartmentDropDown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentDropDown : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On DepartmentDropDown  : " + DepartmentPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (TeamMappingPage.DeptDropDownTextField(deptname)) {
			logResults.createLogs("Y", "PASS", "Successfully Input The DeptName : " + deptname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting  Department Name  : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(2000);
		if (TeamMappingPage.DeptSearchResult()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected DeptName : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting  Department Name  : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.ManageProfileTeamDropdown()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On TeamDropDown: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On TeamDropDown : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.ManageProfileTeamDropdownSearchField(teamnamess)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Team Name: " + teamnamess);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputted Team Name : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.ManageProfileTeamDropdownSearchResult()) {
			logResults.createLogs("Y", "PASS", "Successfully Displayed Team Name: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Displaying Team Name : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

	}

	// MPI_529_TeamMapping_12
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_529_TeamMapping_12() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"\"To verify this, try to disable the team which is not assigned to the employee and ensure that team is disabled.");

		ArrayList<String> data = initBase.loadExcelData("Master_teammapping", currTC,
				"password,captcha,teamname");

		MeghMasterTeamMappingPage TeamMappingPage = new MeghMasterTeamMappingPage(driver);
		
		String password = data.get(0);
		String captcha = data.get(1);
		 teamnamebeforeassigning = data.get(2) + Pramod.generateRandomAlpha(5); 
		

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}

		Thread.sleep(3000);

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
		
		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab : " + EmployeePage.getExceptionDesc());
		}
		
		if (TeamPage.TeamDropIcon()) {
			logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamButton()) {
			logResults.createLogs("Y", "PASS", "Team Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.AddTeamButton()) {
			logResults.createLogs("Y", "PASS", "Add Team Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Team Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamNameTextField(teamnamebeforeassigning)) {
			logResults.createLogs("Y", "PASS", "Team Name Is Entered In Team Name Text Field: " + teamnamebeforeassigning);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Team Name: " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamSaveButton()) {
			logResults.createLogs("Y", "PASS", "Team Save Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TeamSaveButton : " + TeamPage.getExceptionDesc());
		}
		
		if (TeamPage.TeamDropIcon()) {
			logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
		}
		
		if (TeamPage.TeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "TeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TeamMapping Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.AddTeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "AddTeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddTeamMapping Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamMappingPage.TeamDropDownOfForm(teamnamebeforeassigning)) {
			logResults.createLogs("Y", "PASS", "Team Name Selected Successfully: " + teamnamebeforeassigning);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team Name : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.TeamDeptDropDown()) {
			logResults.createLogs("Y", "PASS", "Department DropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Department DropDown : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.TeamDeptSearchField(deptname)) {
			logResults.createLogs("Y", "PASS", "Department Name Inputted Successfully: " + deptname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Department Name : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (TeamMappingPage.TeamDeptSearchResult()) {
			logResults.createLogs("Y", "PASS", "Department Name Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Department Name : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.AddTeamMappingSaveButton()) {
			
			logResults.createLogs("Y", "PASS", "Add Team Mapping Saved Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Saving The Team Mapping : " + TeamMappingPage.getExceptionDesc());
		}
		
		if (TeamPage.TeamDropIcon()) {
			logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamButton()) {
			logResults.createLogs("Y", "PASS", "Team Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamSearchTextField(teamnamebeforeassigning)) {
			logResults.createLogs("Y", "PASS", "Mapped Team Name Inputted Successfully: " + teamnamebeforeassigning);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Mapped Team Name : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (TeamPage.TeamToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " Successfully Turn Off The Toggle Switch : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Toggle Switch : " + TeamPage.getExceptionDesc());
		}


		if (DepartmentPage.DepartmentToggleSwitchConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Ok Button : " + DepartmentPage.getExceptionDesc());
		}


		if (MeghLoginPage.InvalidPasswordValidation()) {

			logResults.createLogs("Y", "PASS", "Error Message is Displayed: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error Message isn't Displayed: " + MeghLoginPage.getExceptionDesc());
		}
		

	}

	// MPI_535_TeamMapping_16
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_535_TeamMapping_16() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this,  enable the team which is not assigned to the employee and ensure that team is enabled and displayed in the .");

		ArrayList<String> data = initBase.loadExcelData("Master_teammapping", currTC,
				"password,captcha");

		MeghMasterTeamMappingPage TeamMappingPage = new MeghMasterTeamMappingPage(driver);
		

		
		String password = data.get(0);
		String captcha = data.get(1);
		

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}

		Thread.sleep(3000);

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

		if (TeamPage.TeamDropIcon()) {
			logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "TeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TeamMapping Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamMappingPage.TeamMappingSearchTextField(teamnamebeforeassigning)) {
			logResults.createLogs("Y", "PASS", "Mapped Team Name Inputted Successfully: " + teamnamebeforeassigning);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Mapped Team Name : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (TeamMappingPage.TeamMappingToggleSwitch()) {
			logResults.createLogs("Y", "PASS", "Mapped Team Name Toggle Switch Enabled Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Mapped Team Toggle Switch : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (DepartmentPage.DepartmentToggleSwitchConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Ok Button : " + DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(4000);
		
		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab : " + EmployeePage.getExceptionDesc());
		}
		
		if (TeamPage.TeamDropIcon()) {
			logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "TeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TeamMapping Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.AddTeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "AddTeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddTeamMapping Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamMappingPage.TeamDropDownOfForm(teamnamebeforeassigning)) {
			logResults.createLogs("Y", "PASS", "Team Name Selected Successfully: " + teamnamebeforeassigning);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team Name : " + TeamMappingPage.getExceptionDesc());
		}
			
	}

	// MPI_533_TeamMapping_15
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_533_TeamMapping_15() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, after assigning a mapped team to any employee, try to disable the team and ensure that a proper error message is displayed.");

		ArrayList<String> data = initBase.loadExcelData("Master_teammapping", currTC,
				"password,captcha,pin");

		MeghMasterTeamMappingPage TeamMappingPage = new MeghMasterTeamMappingPage(driver);
		String password = data.get(0);
		String captcha = data.get(1);
		
		
		String pin = data.get(2) + Pramod.generateRandomNumber(3);

		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}
		
		Thread.sleep(3000);
		
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

		if (TeamPage.TeamDropIcon()) {
			logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamButton()) {
			logResults.createLogs("Y", "PASS", "Team Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamSearchTextField(teamname)) {
			logResults.createLogs("Y", "PASS", "Mapped Team Name Inputted Successfully: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Mapped Team Name : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (TeamPage.TeamToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " Successfully Turn Off The Toggle Switch : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Toggle Switch : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (DepartmentPage.DepartmentToggleSwitchConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Ok Button : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentMappingpage.DepartmentMappingErrorMessage()) {
			logResults.createLogs("Y", "PASS", " Successfully Displayed Error Message : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message : " + DepartmentMappingpage.getExceptionDesc());
		}

	}

	// MPI_524_TeamMapping_07
	@Test(enabled = true, priority = 8, groups = { "Smoke" })
	public void MPI_524_TeamMapping_07() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of the search feature for the first name and last name of the HOD.");

		ArrayList<String> data = initBase.loadExcelData("Master_teammapping", currTC,
				"password,captcha");

		MeghMasterTeamMappingPage TeamMappingPage = new MeghMasterTeamMappingPage(driver);
		
		String password = data.get(0);
		String captcha = data.get(1);
	
		String Teamfirstrow = "";

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}

		Thread.sleep(3000);

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

		if (TeamPage.TeamDropIcon()) {
			logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Drop Icon : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "TeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TeamMapping Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamMappingPage.FirstRecord()) {
			Teamfirstrow = TeamMappingPage.Firstrow;

			logResults.createLogs("Y", "PASS", "TeamMapping First Row Name Fetched Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Fetching  TeamMapping First Row : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.FirstNameCheckBox()) {
			logResults.createLogs("Y", "PASS", "FirstNameCheckBox  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FirstNameCheckBox : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.LastNameCheckBox()) {
			logResults.createLogs("Y", "PASS", "LastNameCheckBox  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On LastNameCheckBox : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown : " + TeamMappingPage.getExceptionDesc());
		}

		if (TeamMappingPage.SearchTextField(Teamfirstrow)) {
			logResults.createLogs("Y", "PASS", "Team Name Inputted Successfully: " + Teamfirstrow);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Team Name : " + TeamMappingPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (TeamMappingPage.TeamMappingFirstRecord(Teamfirstrow)) {
			logResults.createLogs("Y", "PASS", "Mapped Team Name Validated Successfully: " + Teamfirstrow);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Mapped Team Name : " + TeamMappingPage.getExceptionDesc());
		}
		Thread.sleep(4000);

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
