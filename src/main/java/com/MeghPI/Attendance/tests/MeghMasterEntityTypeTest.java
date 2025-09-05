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
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterEntityTypePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class MeghMasterEntityTypeTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	private String entityname = "";

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
		entityname = "AutoEN" + initBase.executionRunTime;
	}

	// MPI_308_EntityType_01
	@Test(enabled = true, priority = 1, groups = { "Smoke", "AddMaster" })
	public void MPI_308_EntityType_01() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a new entity type and ensure that the added entity is displayed in the entity dropdown on the Add New Employee form.");

		ArrayList<String> data = initBase.loadExcelData("company_entitytype", currTC,
				"password,captcha,entityname,cardtype");

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterEntityTypePage EntityTypePage = new MeghMasterEntityTypePage(driver);

		String password = data.get(0);
		String captcha = data.get(1);
		entityname = data.get(2) + initBase.executionRunTime;
		String cardtype = data.get(3);

		if (initBase.stopNewData) { // Tapan 5 July 25
			String EntityName = Utils.propsReadWrite("data/addmaster.properties", "get", "EntityName", "");
			if (EntityName.equals("pass")) {
				logResults.createLogs("Y", "INFO", "Skip Adding New Data. Continue with Old Test Data.");
				throw new SkipException("Skip Adding New Data. Continue with Old Test Data.");
			}
		}
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

		
MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}
		
		if (RolePermissionpage.SettingIcon()) {
			logResults.createLogs("Y", "PASS", "SettingIcon   Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SettingIcon : " + RolePermissionpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.GeneralSettings()) {
			logResults.createLogs("Y", "PASS", "GeneralSettings   Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On GeneralSettings : " + RolePermissionpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.WebEnrollmentClickAction()) {
			logResults.createLogs("Y", "PASS", "WebEnrollmentSelectOption Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting WebEnrollmentSelectOption : " + RolePermissionpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.YesSelectedForEnrollment()) {
			logResults.createLogs("Y", "PASS", "Yes Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Yes Option : " + RolePermissionpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.WebEnrollmentTypesFace()) {
			logResults.createLogs("Y", "PASS", "WebEnrollmentTypesFace Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting WebEnrollmentTypesFace : " + RolePermissionpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.WebEnrollmentTypesCard()) {
			logResults.createLogs("Y", "PASS", "WebEnrollmentTypesCard Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting WebEnrollmentTypesCard : " + RolePermissionpage.getExceptionDesc());
		}
		
		
		if (RolePermissionpage.SaveChangesButton()) {
			logResults.createLogs("Y", "PASS", "SaveChangesButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SaveChangesButton : " + RolePermissionpage.getExceptionDesc());
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

		if (EmployeePage.EntityTypeButton()) {
			logResults.createLogs("Y", "PASS", "EntityTypeButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EntityTypeButton : " + EmployeePage.getExceptionDesc());
		}

		if (EntityTypePage.EntityTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "EntityType Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Entity Type Page Isn't Loaded : " + EntityTypePage.getExceptionDesc());
		}

		if (EmployeePage.AddEntityTypeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddEntityTypeButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEntityTypeButton : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEntityTypeName(entityname)) {
			logResults.createLogs("Y", "PASS", " Entityname Entered In The Entiry Name TextField: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputing Entity Name : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SelectCardTypebytext(cardtype)) {
			logResults.createLogs("Y", "PASS", " Entity Card Type Selected Successfullly: " + cardtype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Entity Card Type : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEntityTypeSave()) {
			logResults.createLogs("Y", "PASS", " Successfullly Clicked On AddEntityTypeSave Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEntityTypeSave Button : " + EmployeePage.getExceptionDesc());
		}
		
		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
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

		Thread.sleep(2000);

		if (EmployeePage.EmployeeTypeDropdown(entityname)) {
			Utils.propsReadWrite("data/addmaster.properties", "set", "EntityName", "pass");
			logResults.createLogs("Y", "PASS",
					"Clicked On EmployeeTypeDropdown Successfully And Added EntityName Displayed Successfully: "
							+ entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTypeDropdown  : " + EmployeePage.getExceptionDesc());
		}

		Thread.sleep(3000);

	}

	// MPI_309_EntityType_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_309_EntityType_02() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a worker and ensure the created worker is displayed in the list of entities by validating it through the search function");

		ArrayList<String> data = initBase.loadExcelData("company_entitytype", currTC,
				"password,captcha,entityname,cardtype");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);

	
		String password = data.get(0);
		String captcha = data.get(1);
		
		String entityname = data.get(2);
		String ename = entityname + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		String cardtype = data.get(3);

		String cardtypeentityname = "";

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghMasterEntityTypePage EntityTypePage = new MeghMasterEntityTypePage(driver);

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

		if (EmployeePage.EntityTypeButton()) {
			logResults.createLogs("Y", "PASS", "EntityTypeButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EntityTypeButton : " + EmployeePage.getExceptionDesc());
		}

		if (EntityTypePage.EntityTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "EntityType Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Entity Type Page Isn't Loaded : " + EntityTypePage.getExceptionDesc());
		}

		if (EmployeePage.AddEntityTypeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddEntityTypeButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEntityTypeButton : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEntityTypeName(ename)) {
			logResults.createLogs("Y", "PASS", " Entityname Entered In The Entiry Name TextField: " + ename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputing Entity Name : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SelectCardTypebytext(cardtype)) {
			logResults.createLogs("Y", "PASS", " Entity Card Type Selected Successfullly: " + cardtype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Entity Card Type : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEntityTypeSave()) {
			logResults.createLogs("Y", "PASS", " Successfullly Clicked On AddEntityTypeSave Button: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEntityTypeSave Button : " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (EntityTypePage.EntitySearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown : " + EntityTypePage.getExceptionDesc());
		}

		if (EntityTypePage.CardTypeOptionToSearch()) {
			logResults.createLogs("Y", "PASS", " In SearchDropDown CardType Selected  Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On SearchDropDown CardType CheckBox Selection  : "
					+ EntityTypePage.getExceptionDesc());
		}

		if (EntityTypePage.EntitySearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown : " + EntityTypePage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (EntityTypePage.EntitySearchField(cardtype)) {
			logResults.createLogs("Y", "PASS", " In SearchDropDown CardType Selected  Successfully: " + cardtype);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On SearchDropDown CardType CheckBox Selection  : "
					+ EntityTypePage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (EntityTypePage.verifyAllEntityTypesAreStaff()) {

			logResults.createLogs("Y", "PASS",
					"WORKER  CardType Entity Are Only Displaying  Successfully: " + cardtypeentityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Only WORKER CardType Entity  : " + EntityTypePage.getExceptionDesc());
		}

		Thread.sleep(3000);
	}

	// MPI_310_EntityType_03
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_310_EntityType_03() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the entity name and ensure the entity is updated accordingly by validating it using the search feature.");

		ArrayList<String> data = initBase.loadExcelData("company_entitytype", currTC,
				"password,captcha,entityname,cardtype");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);

	
		String password = data.get(0);
		String captcha = data.get(1);
		
		String entityname = data.get(2);
		String ename = entityname + Pramod.generateRandomAlpha(3);
		String cardtype = data.get(3);

		String updatedentity = ename + Pramod.generateRandomNumber(4);

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		
		MeghMasterEntityTypePage EntityTypePage = new MeghMasterEntityTypePage(driver);

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

		if (EmployeePage.EntityTypeButton()) {
			logResults.createLogs("Y", "PASS", "EntityTypeButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EntityTypeButton : " + EmployeePage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (EntityTypePage.EntityTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "EntityType Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Entity Type Page Isn't Loaded : " + EntityTypePage.getExceptionDesc());
		}

		if (EmployeePage.AddEntityTypeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddEntityTypeButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEntityTypeButton : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEntityTypeName(ename)) {
			logResults.createLogs("Y", "PASS", " Entityname Entered In The Entiry Name TextField: " + ename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputing Entity Name : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SelectCardTypebytext(cardtype)) {
			logResults.createLogs("Y", "PASS", " Entity Card Type Selected Successfullly: " + cardtype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Entity Card Type : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEntityTypeSave()) {
			logResults.createLogs("Y", "PASS", " Successfullly Clicked On AddEntityTypeSave Button: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEntityTypeSave Button : " + EmployeePage.getExceptionDesc());
		}

		if (EntityTypePage.EntitySearchField(ename)) {
			logResults.createLogs("Y", "PASS", " Entity Name Inputted In Search Field  Successfully: " + ename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Entity Name : " + EntityTypePage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (EntityTypePage.Entity3Dots()) {
			logResults.createLogs("Y", "PASS", "Entity 3Dots Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Entity 3Dots : " + EntityTypePage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (EntityTypePage.EntityEditButton()) {
			logResults.createLogs("Y", "PASS", "Entity Edit Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Entity Edit : " + EntityTypePage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (EmployeePage.AddEntityTypeName(updatedentity)) {
			logResults.createLogs("Y", "PASS", " Entityname Entered In The Entiry Name TextField: " + updatedentity);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputing Entity Name : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SelectCardTypebytext(cardtype)) {
			logResults.createLogs("Y", "PASS", " Entity Card Type Selected Successfullly: " + cardtype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Entity Card Type : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEntityTypeSave()) {
			logResults.createLogs("Y", "PASS", " Successfullly Clicked On AddEntityTypeSave Button: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEntityTypeSave Button : " + EmployeePage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (EntityTypePage.EntitySearchField(updatedentity)) {
			logResults.createLogs("Y", "PASS", " Entity Name Inputted In Search Field  Successfully: " + updatedentity);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Entity Name : " + EntityTypePage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (EntityTypePage.EntityTypeFirstRow()) {
			updatedentity = EntityTypePage.entityname;

			logResults.createLogs("Y", "PASS",
					" Updated Entity Name Record Displayed  Successfully: " + updatedentity + ename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Entity Name : " + EntityTypePage.getExceptionDesc());
		}
		Thread.sleep(2000);

	}

	// MPI_313_EntityType_06
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_313_EntityType_06() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create duplicate enityType and ensure proper error message is displayed");

		ArrayList<String> data = initBase.loadExcelData("company_entitytype", currTC,
				"password,captcha,entityname,cardtype");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String entityname = data.get(2);
		String ename = entityname + initBase.executionRunTime + Pramod.generateRandomAlpha(3);
		String cardtype = data.get(3);

		String updatedentity = "";

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghMasterEntityTypePage EntityTypePage = new MeghMasterEntityTypePage(driver);

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

		if (EmployeePage.EntityTypeButton()) {
			logResults.createLogs("Y", "PASS", "EntityTypeButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EntityTypeButton : " + EmployeePage.getExceptionDesc());
		}

		if (EntityTypePage.EntityTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "EntityType Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Entity Type Page Isn't Loaded : " + EntityTypePage.getExceptionDesc());
		}

		if (EntityTypePage.EntityTypeFirstRow()) {
			updatedentity = EntityTypePage.entityname;

			logResults.createLogs("Y", "PASS",
					" Updated Entity Name Record Displayed  Successfully: " + updatedentity + ename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Entity Name : " + EntityTypePage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (EmployeePage.AddEntityTypeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddEntityTypeButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEntityTypeButton : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEntityTypeName(updatedentity)) {
			logResults.createLogs("Y", "PASS", " Entityname Entered In The Entiry Name TextField: " + updatedentity);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputing Entity Name : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SelectCardTypebytext(cardtype)) {
			logResults.createLogs("Y", "PASS", " Entity Card Type Selected Successfullly: " + cardtype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Entity Card Type : " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.AddEntityTypeSave()) {
			logResults.createLogs("Y", "PASS", " Successfullly Clicked On AddEntityTypeSave Button: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddEntityTypeSave Button : " + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.DuplicateErrorMessage()) {
			logResults.createLogs("Y", "PASS", " Successfully Displayed Error Message : ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message : " + OfficePage.getExceptionDesc());
		}

		Thread.sleep(4000);
	}

	// MPI_319_EntityType_12
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_319_EntityType_12() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, disable the entity and ensure the disabled entity name is not displayed in the 'Employee Type' dropdown on the Add Employee form.");

		ArrayList<String> data = initBase.loadExcelData("company_entitytype", currTC,
				"password,captcha,entityname,cardtype");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		

		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghMasterEntityTypePage EntityTypePage = new MeghMasterEntityTypePage(driver);

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

		if (EmployeePage.EntityTypeButton()) {
			logResults.createLogs("Y", "PASS", "EntityTypeButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EntityTypeButton : " + EmployeePage.getExceptionDesc());
		}

		if (EntityTypePage.EntityTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "EntityType Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Entity Type Page Isn't Loaded : " + EntityTypePage.getExceptionDesc());
		}

		if (EntityTypePage.EntitySearchField(entityname)) {
			logResults.createLogs("Y", "PASS", " Entity Name Inputted In Search Field  Successfully: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Entity Name : " + EntityTypePage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (EntityTypePage.EntityToggleSwitch()) {
			logResults.createLogs("Y", "PASS", "Entity Toggle Switch  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Entity Toggle Switch : " + EntityTypePage.getExceptionDesc());
		}

		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button : " + OfficePage.getExceptionDesc());
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

		if (EntityTypePage.EntityDropDownInAddEmployee(entityname)) {
			logResults.createLogs("Y", "PASS",
					"Disabled Entity Isn't Present And Validated Successfully: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Disabled Entity Name : " + EntityTypePage.getExceptionDesc());
		}

	}

	// MPI_320_EntityType_13
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_320_EntityType_13() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enable a previously disabled Entity record and ensure that, after enabling, the enabled Entity name appears in the \"employee type\" dropdown which is in Add Employee process.");

		ArrayList<String> data = initBase.loadExcelData("company_entitytype", currTC,
				"password,captcha");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
	
		

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghMasterEntityTypePage EntityTypePage = new MeghMasterEntityTypePage(driver);

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

		if (EmployeePage.EntityTypeButton()) {
			logResults.createLogs("Y", "PASS", "EntityTypeButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EntityTypeButton : " + EmployeePage.getExceptionDesc());
		}

		if (EntityTypePage.EntityTypePageLoaded()) {
			logResults.createLogs("Y", "PASS", "EntityType Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Entity Type Page Isn't Loaded : " + EntityTypePage.getExceptionDesc());
		}

		if (EntityTypePage.EntitySearchField(entityname)) {
			logResults.createLogs("Y", "PASS", " Entity Name Inputted In Search Field  Successfully: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Entity Name : " + EntityTypePage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (EntityTypePage.EntityToggleSwitch()) {
			logResults.createLogs("Y", "PASS", "Entity Toggle Switch  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Entity Toggle Switch : " + EntityTypePage.getExceptionDesc());
		}

		if (OfficePage.ConfirmButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Ok Button : ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Ok Button : " + OfficePage.getExceptionDesc());
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

		Thread.sleep(2000);

		if (EmployeePage.EmployeeTypeDropdown(entityname)) {
			
			logResults.createLogs("Y", "PASS",
					"Enabled Entity Name Displayed And Validated Successfully: " + entityname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Enabled Entity Name: " + EmployeePage.getExceptionDesc());
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
