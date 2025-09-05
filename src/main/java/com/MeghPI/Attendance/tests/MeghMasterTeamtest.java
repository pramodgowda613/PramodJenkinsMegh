package com.MeghPI.Attendance.tests;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
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
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;

import utils.Pramod;
import utils.Utils;

public class MeghMasterTeamtest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	private String teamname = "";
	private String teamnames = "";

	private String cmpcode = "";
	private String Emailid = "";
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
		officename = "AutoON" + initBase.executionRunTime;
		System.out.println("cmpcode and emailid" +cmpcode+Emailid );
	}

	// MPI_212_Team_01
	@Test(enabled = true, priority = 1, groups = { "Smoke","AddMaster" })
	public void MPI_212_Team_01() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create an team and check whether created team is displayed in the team mapping  > team dropdown");

		ArrayList<String> data = initBase.loadExcelData("company_team", currTC,
				"password,captcha,teamname,teamdescription");

		
		String password = data.get(0);
		String captcha = data.get(1);
		teamname = data.get(2) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		String teamdescription = data.get(3) + initBase.executionRunTime;
	
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		
MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}
		
		if (RolePermissionpage.ManageButton()) {
			logResults.createLogs("Y", "PASS", "Admin Manage Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Manage Button : " + RolePermissionpage.getExceptionDesc());
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

		if (TeamPage.TeamPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Team Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Team Page Is Not Loaded Completely : " + TeamPage.getExceptionDesc());
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

		if (TeamPage.TeamAreaTextField(teamdescription)) {
			logResults.createLogs("Y", "PASS",
					"Team Description Is Entered In Team Description Text Field: " + teamdescription);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Team Description: " + TeamPage.getExceptionDesc());
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
		Thread.sleep(2000);

		if (TeamPage.TeamDropDowns()) {
			logResults.createLogs("Y", "PASS", "TeamDropDown  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On TeamDropDown : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.TeamDropDwonTextField(teamname)) {
			logResults.createLogs("Y", "PASS", "Team Name Inputted In TeamDropDwonTextField  Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Team Name On TeamDropDown : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.TeamSearchResult(teamname)) {
			logResults.createLogs("Y", "PASS", "Team Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Displaying Team Name : " + TeamPage.getExceptionDesc());
		}

	}

	// MPI_213_Team_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_213_Team_02() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the team details and confirm that the changes are reflected by searching using the updated team name.");

		ArrayList<String> data = initBase.loadExcelData("company_team", currTC,
				"password,captcha,teamname");

		
		String password = data.get(0);
		String captcha = data.get(1);
		teamnames = data.get(2) + Pramod.generateRandomAlpha(3);
		String teamnamess = teamnames + Pramod.generateRandomNumber(4);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);

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

		if (TeamPage.TeamPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Team Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Team Page Is Not Loaded Completely : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.AddTeamButton()) {
			logResults.createLogs("Y", "PASS", "Add Team Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Team Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamNameTextField(teamnames)) {
			logResults.createLogs("Y", "PASS", "Team Name Is Entered In Team Name Text Field: " + teamnames);
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

		if (TeamPage.TeamSearchTextField(teamnames)) {
			logResults.createLogs("Y", "PASS",
					"Updated Team Name Entered In Search TextField Successfully: " + teamnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Updated Team Name : " + TeamPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (TeamPage.Team3dots()) {
			logResults.createLogs("Y", "PASS", "Team First Record  3Dots Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Team 3Dots : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.FirstTeamRecordEditIcon()) {
			logResults.createLogs("Y", "PASS", "Team First Record  Edit Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team Edit Button : " + TeamPage.getExceptionDesc());
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

		Thread.sleep(2000);

		if (TeamPage.TeamSearchTextField(teamnamess)) {
			logResults.createLogs("Y", "PASS",
					"Updated Team Name Entered In Search TextField Successfully: " + teamnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Updated Team Name : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.FirstTeamRecordName()) {
			logResults.createLogs("Y", "PASS", "Updated Team Name Record Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Updated Team Name Record : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);
	}

	// MPI_226_Team_14
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_226_Team_14() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, try to create duplicate team and ensure proper error message is displayed");

		ArrayList<String> data = initBase.loadExcelData("company_team", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);
		

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

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

		if (TeamPage.TeamPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Team Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Team Page Is Not Loaded Completely : " + TeamPage.getExceptionDesc());
		}

		
		if (TeamPage.AddTeamButton()) {
			logResults.createLogs("Y", "PASS", "Add Team Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Add Team Button : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamNameTextField(teamnames)) {
			logResults.createLogs("Y", "PASS", "Team Name Is Entered In Team Name Text Field: " + teamnames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Team Name: " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamSaveButton()) {
			logResults.createLogs("Y", "PASS", "Team Save Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On TeamSaveButton : " + TeamPage.getExceptionDesc());
		}

		if (OfficePage.DuplicateErrorMessage()) {
			logResults.createLogs("Y", "PASS", " Successfully Displayed Error Message : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message : " + OfficePage.getExceptionDesc());
		}
		Thread.sleep(2000);
	}

	// MPI_227_Team_15
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_227_Team_15() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, disable the team and ensure team name is't displayed in the team dropdown in \"team mapping\" module");

		ArrayList<String> data = initBase.loadExcelData("company_team", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

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

		if (TeamPage.TeamPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Team Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Team Page Is Not Loaded Completely : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamSearchTextField(teamname)) {
			logResults.createLogs("Y", "PASS",
					"Updated Team Name Entered In Search TextField Successfully: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Updated Team Name : " + TeamPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (TeamPage.TeamToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " Successfully Turn Off The Toggle Switch : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Toggle Switch : " + TeamPage.getExceptionDesc());
		}

		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button : " + OfficePage.getExceptionDesc());
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
		Thread.sleep(2000);

		if (TeamPage.AddTeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "AddTeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddTeamMapping Button : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.TeamDropDowns()) {
			logResults.createLogs("Y", "PASS", "TeamDropDown  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On TeamDropDown : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.TeamDropDwonTextField(teamname)) {
			logResults.createLogs("Y", "PASS", "Team Name Inputted In TeamDropDwonTextField  Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Team Name On TeamDropDown : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.NoResultFoundMsg()) {
			logResults.createLogs("Y", "PASS",
					"Disabled Team Name Isn't Displayed And Error Msg Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Displaying Error Msg : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);
	}

	// MPI_228_Team_16
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_228_Team_16() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				" To verify this, enable a previously disabled team record and ensure that, after enabling, the team name appears in the 'Team' dropdown within the team mapping \"add team mapping\" module");

		ArrayList<String> data = initBase.loadExcelData("company_team", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

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

		if (TeamPage.TeamPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Team Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Team Page Is Not Loaded Completely : " + TeamPage.getExceptionDesc());
		}

		if (TeamPage.TeamSearchTextField(teamname)) {
			logResults.createLogs("Y", "PASS",
					"Updated Team Name Entered In Search TextField Successfully: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Updated Team Name : " + TeamPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (TeamPage.TeamToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " Successfully Turn On The Toggle Switch : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Toggle Switch : " + TeamPage.getExceptionDesc());
		}

		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button : " + OfficePage.getExceptionDesc());
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

		if (TeamPage.AddTeamMappingButton()) {
			logResults.createLogs("Y", "PASS", "AddTeamMapping Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddTeamMapping Button : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.TeamDropDowns()) {
			logResults.createLogs("Y", "PASS", "TeamDropDown  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On TeamDropDown : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.TeamDropDwonTextField(teamname)) {
			logResults.createLogs("Y", "PASS", "Team Name Inputted In TeamDropDwonTextField  Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Team Name On TeamDropDown : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.TeamSearchResult(teamname)) {
			logResults.createLogs("Y", "PASS", "Team Name Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Displaying Team Name : " + TeamPage.getExceptionDesc());
		}

	}

	// MPI_229_Team_17
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_229_Team_17() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, test the search functionality by selecting the 'Status' filter, entering 'Inactive', and ensuring that only inactive records are displayed");

		ArrayList<String> data = initBase.loadExcelData("company_team", currTC,
				"password,captcha,status");

		
		String password = data.get(0);
		String captcha = data.get(1);
		String status = data.get(2);

	

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

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

		if (TeamPage.TeamPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Team Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Team Page Is Not Loaded Completely : " + TeamPage.getExceptionDesc());
		}
		
		if (TeamPage.TeamSearchTextField(teamname)) {
			logResults.createLogs("Y", "PASS",
					"Status as 'Inactive' Entered In Search TextField Successfully: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting 'Inactive' Name : " + TeamPage.getExceptionDesc());
		}

		
		
		if (TeamPage.TeamToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " Successfully Turn Off The Toggle Switch : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Toggle Switch : " + TeamPage.getExceptionDesc());
		}

		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button : " + OfficePage.getExceptionDesc());
		}

		if (DepartmentPage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", " Search DropDown  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking Search DropDown : " + DepartmentPage.getExceptionDesc());
		}

		if (TeamPage.StatusCheckbox()) {
			logResults.createLogs("Y", "PASS", " Status Checkbox  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking Status Checkbox : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (DepartmentPage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", " Search DropDown  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking Search DropDown : " + DepartmentPage.getExceptionDesc());
		}

		if (TeamPage.TeamSearchTextField(status)) {
			logResults.createLogs("Y", "PASS",
					"Status as 'Inactive' Entered In Search TextField Successfully: " + status);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting 'Inactive' Name : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (TeamPage.FirstTeamRecordName()) {
			logResults.createLogs("Y", "PASS", "Inactive Team Name Record Displayed Successfully: " + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Inactive Team Name Record : " + TeamPage.getExceptionDesc());
		}
		Thread.sleep(2000);
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
