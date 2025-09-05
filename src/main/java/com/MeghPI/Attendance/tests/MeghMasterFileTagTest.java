package com.MeghPI.Attendance.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterFileTagPage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class MeghMasterFileTagTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	

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
		
	}

	// MPI_331_FileTags_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_331_FileTags_01() throws InterruptedException, IOException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a file tag and ensure the created tag is displayed in the File module during the file upload process.");

		ArrayList<String> data = initBase.loadExcelData("company_filetag", currTC,
				"password,captcha,tagname,uploadfile");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterFileTagPage FileTagPage = new MeghMasterFileTagPage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String tagname = data.get(2) + initBase.executionRunTime;

		String resulttag = "";
		String uploadfile = data.get(3);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
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

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab : " + EmployeePage.getExceptionDesc());
		}

		if (FileTagPage.FileTagButton()) {
			logResults.createLogs("Y", "PASS", "FileTag Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FileTag Button : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.FileTagPageLoaded()) {
			logResults.createLogs("Y", "PASS", "FileTag Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Page Is Not Loaded Completely : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.CreateTagButton()) {
			logResults.createLogs("Y", "PASS", "CreateTag Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CreateTag Button : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.TagNameTextField(tagname)) {
			logResults.createLogs("Y", "PASS", "Tag Name Inputted Successfully: " + tagname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Tag Name : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.EmployeeFilesRadioButton()) {
			logResults.createLogs("Y", "PASS", "EmployeeFilesRadioButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Employee Files Radio Button : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.ColorPicker()) {
			logResults.createLogs("Y", "PASS", "ColorPicker  Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting ColorPicker : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.AddTagSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddTagSaveButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddTagSaveButton : " + FileTagPage.getExceptionDesc());
		}
		Thread.sleep(7000);

		if (RolePermissionpage.FileButton()) {

			logResults.createLogs("Y", "PASS", "FileButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FileButton: " + RolePermissionpage.getExceptionDesc());
		}

		if (FileTagPage.UploadFileButton()) {

			logResults.createLogs("Y", "PASS", "UploadFile Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On UploadFile Button: " + FileTagPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (FileTagPage.UploadFileFromStorage(uploadfile)) {

			logResults.createLogs("Y", "PASS", "File Selected Successfully: " + uploadfile);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting File : " + FileTagPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (FileTagPage.FileTagDropdown()) {

			logResults.createLogs("Y", "PASS", "FileTagDropdown Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FileTagDropdown: " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.FileTagSearchDropDown(tagname)) {

			logResults.createLogs("Y", "PASS", "Tag Name Inputted Successfully: " + tagname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Tag Name: " + FileTagPage.getExceptionDesc());
		}

		Thread.sleep(4000);

		if (FileTagPage.FileTagSearchResult()) {
			resulttag = FileTagPage.createdtag;
			logResults.createLogs("Y", "PASS", "Tag Name Displayed Successfully: " + tagname + "=" + resulttag);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Tag Name: " + FileTagPage.getExceptionDesc());
		}

	}

	// MPI_332_FileTags_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_332_FileTags_02() throws InterruptedException, IOException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a file tag by selecting 'Company Files' as the section type and ensure the added file tag is displayed under the 'Company Files' section type");

		ArrayList<String> data = initBase.loadExcelData("company_filetag", currTC,
				"password,captcha,tagname");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterFileTagPage FileTagPage = new MeghMasterFileTagPage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
	
		String tagname = data.get(2) + initBase.executionRunTime;

	
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

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab : " + EmployeePage.getExceptionDesc());
		}

		if (FileTagPage.FileTagButton()) {
			logResults.createLogs("Y", "PASS", "FileTag Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FileTag Button : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.FileTagPageLoaded()) {
			logResults.createLogs("Y", "PASS", "FileTag Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Page Is Not Loaded Completely : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.CreateTagButton()) {
			logResults.createLogs("Y", "PASS", "CreateTag Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CreateTag Button : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.TagNameTextField(tagname)) {
			logResults.createLogs("Y", "PASS", "Tag Name Inputted Successfully: " + tagname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Tag Name : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.CompanyFileRadioButton()) {
			logResults.createLogs("Y", "PASS", "CompanyFileRadioButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Files Radio Button : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.ColorPicker()) {
			logResults.createLogs("Y", "PASS", "ColorPicker  Selected Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting ColorPicker : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.AddTagSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddTagSaveButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddTagSaveButton : " + FileTagPage.getExceptionDesc());
		}

		Thread.sleep(3000);

		if (FileTagPage.CompanyFiless(tagname)) {
			logResults.createLogs("Y", "PASS", "Created Tag Displayed Successfully: " + tagname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Created Company File Tag : " + FileTagPage.getExceptionDesc());
		}
		Thread.sleep(3000);

	}

	// MPI_333_FileTags_03
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_333_FileTags_03() throws InterruptedException, IOException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults
				.setScenarioName("To verify this, edit the tag and ensure the tag is updated according to the changes");

		ArrayList<String> data = initBase.loadExcelData("company_filetag", currTC,
				"password,captcha,tagname");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterFileTagPage FileTagPage = new MeghMasterFileTagPage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);

		String tagname = data.get(2) + initBase.executionRunTime + Pramod.generateRandomAlpha(3);

		
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

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab : " + EmployeePage.getExceptionDesc());
		}

		if (FileTagPage.FileTagButton()) {
			logResults.createLogs("Y", "PASS", "FileTag Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FileTag Button : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.FileTagPageLoaded()) {
			logResults.createLogs("Y", "PASS", "FileTag Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Page Is Not Loaded Completely : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.FileTagEditIcon4thRecord()) {
			logResults.createLogs("Y", "PASS", "5th Created Tag Edit Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CreateTag Edit Button : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.TagNameTextField(tagname)) {
			logResults.createLogs("Y", "PASS", "Tag Name Inputted Successfully: " + tagname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Tag Name : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.CompanyFileRadioButton()) {
			logResults.createLogs("Y", "PASS", "CompanyFileRadioButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Files Radio Button : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.AddTagSaveButton()) {
			logResults.createLogs("Y", "PASS", "AddTagSaveButton  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddTagSaveButton : " + FileTagPage.getExceptionDesc());
		}

		Thread.sleep(3000);

		if (FileTagPage.CompanyFiless(tagname)) {
			logResults.createLogs("Y", "PASS", "Created Tag Displayed Successfully: " + tagname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Created Company File Tag : " + FileTagPage.getExceptionDesc());
		}
		Thread.sleep(3000);

	}

	// MPI_341_FileTags_11
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_341_FileTags_11() throws InterruptedException, IOException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, select 'Company Files' from the filter dropdown and ensure that only file tags with the section type 'Company Files' are filtered and displayed.");

		ArrayList<String> data = initBase.loadExcelData("company_filetag", currTC,
				"password,captcha,tagtype");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterFileTagPage FileTagPage = new MeghMasterFileTagPage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		String tagtype = data.get(2);

		
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

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab : " + EmployeePage.getExceptionDesc());
		}

		if (FileTagPage.FileTagButton()) {
			logResults.createLogs("Y", "PASS", "FileTag Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FileTag Button : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.FileTagPageLoaded()) {
			logResults.createLogs("Y", "PASS", "FileTag Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Page Is Not Loaded Completely : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.SelectSectionTypeDropDown(tagtype)) {
			logResults.createLogs("Y", "PASS", "Company FileTag Selected Successfully: " + tagtype);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting FileTag  : " + FileTagPage.getExceptionDesc());
		}

		Thread.sleep(3000);

		if (FileTagPage.OnlyCompanyFiles()) {
			logResults.createLogs("Y", "PASS", "Only Company FileTag Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Only Company FileTag  : " + FileTagPage.getExceptionDesc());
		}

	}

	// MPI_342_FileTags_12
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_342_FileTags_12() throws InterruptedException, IOException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, upload two files in the File module — select the 'Company' file tag for one file and the 'Employee' file tag for the other. After uploading, go to the File Tag module and move the file tagged under the Company section type to the Employee section type. Ensure the file  is moved successfully from compay to employee filetag");

		ArrayList<String> data = initBase.loadExcelData("company_filetag", currTC,
				"password,captcha,uploadfile,fromtagname,totagname,date");

		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterFileTagPage FileTagPage = new MeghMasterFileTagPage(driver);

	
		String password = data.get(0);
		String captcha = data.get(1);
		

		
		String uploadfile = data.get(2);

		String fromtagname = data.get(3);
		String totagname = data.get(4);
		String date = data.get(5);
	

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

MeghLoginTest meghlogintest = new MeghLoginTest();
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
		}

		Thread.sleep(3000);

		if (RolePermissionpage.FileButton()) {

			logResults.createLogs("Y", "PASS", "FileButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FileButton: " + RolePermissionpage.getExceptionDesc());
		}

		if (FileTagPage.UploadFileButton()) {

			logResults.createLogs("Y", "PASS", "UploadFile Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On UploadFile Button: " + FileTagPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (FileTagPage.UploadFileFromStorage(uploadfile)) {

			logResults.createLogs("Y", "PASS", "File Selected Successfully: " + uploadfile);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting File : " + FileTagPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (FileTagPage.FileTagSelectDropDown(fromtagname)) {

			logResults.createLogs("Y", "PASS", "Company FileTag Selected Successfully: " + fromtagname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Company FileTag From Dropdown: " + FileTagPage.getExceptionDesc());
		}
		
		if (FileTagPage.Date()) {

			logResults.createLogs("Y", "PASS", "Date TextField Is Clicked Successfully: " );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Date TextField: " + FileTagPage.getExceptionDesc());
		}
		
		if (FileTagPage.MonthSelection("December")) {

			logResults.createLogs("Y", "PASS", "Month Selected Successfully: " );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Month : " + FileTagPage.getExceptionDesc());
		}
		
		if (FileTagPage.DaySelection()) {

			logResults.createLogs("Y", "PASS", "Day Selected Successfully: " );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Day : " + FileTagPage.getExceptionDesc());
		}
		

		if (FileTagPage.UploadFilesSaveButton()) {

			logResults.createLogs("Y", "PASS", "Upload FileSave Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On UploadFilesSaveButton: " + FileTagPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (FileTagPage.UploadButton2ndRow()) {

			logResults.createLogs("Y", "PASS", "UploadFile Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On UploadFile Button: " + FileTagPage.getExceptionDesc());
		}
		Thread.sleep(2000);

		if (FileTagPage.UploadFileFromStorage(uploadfile)) {

			logResults.createLogs("Y", "PASS", "File Selected Successfully: " + uploadfile);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting File : " + FileTagPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (FileTagPage.FileTagSelectDropDown(totagname)) {

			logResults.createLogs("Y", "PASS", "Company FileTag Selected Successfully: " + totagname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Company FileTag From Dropdown: " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.Date()) {

			logResults.createLogs("Y", "PASS", "Date TextField Is Clicked Successfully: " );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Date TextField: " + FileTagPage.getExceptionDesc());
		}
		
		if (FileTagPage.MonthSelection("December")) {

			logResults.createLogs("Y", "PASS", "Month Selected Successfully: " );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Month : " + FileTagPage.getExceptionDesc());
		}
		
		if (FileTagPage.DaySelection()) {

			logResults.createLogs("Y", "PASS", "Day Selected Successfully: " );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Day : " + FileTagPage.getExceptionDesc());
		}

		

		if (FileTagPage.UploadFilesSaveButton()) {

			logResults.createLogs("Y", "PASS", "Upload FileSave Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On UploadFilesSaveButton: " + FileTagPage.getExceptionDesc());
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

		if (EmployeePage.CompanyTab()) {
			logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Company Tab : " + EmployeePage.getExceptionDesc());
		}

		if (FileTagPage.FileTagButton()) {
			logResults.createLogs("Y", "PASS", "FileTag Button  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On FileTag Button : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.FileTagPageLoaded()) {
			logResults.createLogs("Y", "PASS", "FileTag Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Page Is Not Loaded Completely : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.MoveFileDropDown()) {
			logResults.createLogs("Y", "PASS", "MoveFileDropDown  Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On MoveFileDropDown : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.FromDropDown(fromtagname)) {
			logResults.createLogs("Y", "PASS", "From Tag Name Selected Successfully: " + fromtagname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting From Tagname : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.ToDropDown(totagname)) {
			logResults.createLogs("Y", "PASS", "To Tag Name Selected Successfully: " + totagname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting To Tagname : " + FileTagPage.getExceptionDesc());
		}

		if (FileTagPage.MoveSaveButton()) {
			logResults.createLogs("Y", "PASS", "MoveSaveButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On MoveSaveButton : " + FileTagPage.getExceptionDesc());
		}

		Thread.sleep(5000);

		if (FileTagPage.MovedValidation()) {
			logResults.createLogs("Y", "PASS", "Moved Validation Done Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Validating Moved Files From One Tag To Another : " + FileTagPage.getExceptionDesc());
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
