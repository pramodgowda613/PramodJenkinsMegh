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
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class MeghMasterOfficeTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	

	
	private String cmpcode = "";
	private String Emailid = "";
	private String officename = "";
	
	
	@Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
	//	driver = loadDriver.getDriver(device, hubnodeip);

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
		officename = "AutoON" + initBase.executionRunTime;
	}

	// MPI_161_Office_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_161_Office_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create an office and ensure the created office is displayed in the list.");

		ArrayList<String> data = initBase.loadExcelData("company_office", currTC,
				"password,captcha,message,officename,officeaddress,state,officeno,officeemail,mailinator");

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);

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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficePageValidation()) {
			logResults.createLogs("Y", "PASS", "Office Page  Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Office Page Is Not Loaded Completely ." + OfficePage.getExceptionDesc());
		}
		
		if (OfficePage.OfficeSearchTextField(officename)) {
			logResults.createLogs("Y", "PASS", "OfficeType Entered In The TextField Successfully." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeType In Search Text Field ." + OfficePage.getExceptionDesc());
		}
		
		if (OfficePage.FirstCompanyRecord()) {

			
			logResults.createLogs("Y", "PASS", " Successfully Fetched The First Record Company Name .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Fetching Disabled Company Location Name ." + OfficePage.getExceptionDesc());
		}
		
		

		

	}

	// MPI_180_Office_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_180_Office_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the office address and ensure that the updated address is reflected correctly as per the changes made ");

		ArrayList<String> data = initBase.loadExcelData("company_office", currTC,
				"officeaddress,state,longitude");

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		String officeaddress = data.get(0) + initBase.executionRunTime;
		String state = data.get(1);
		String longitude  = data.get(2);
		
		String newoffice = officename + Pramod.generateRandomAlpha(2);

		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

		

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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}
		if (OfficePage.OfficePageValidation()) {
			logResults.createLogs("Y", "PASS", "Office Page  Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Office Page Is Not Loaded Completely ." + OfficePage.getExceptionDesc());
		}

		

		

		if (OfficePage.OfficeSearchTextField(officename)) {
			logResults.createLogs("Y", "PASS", "OfficeType Entered In The TextField Successfully." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeType In Search Text Field ." + OfficePage.getExceptionDesc());
		}

	

		if (OfficePage.OfficeEdit3dots()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Office Record 3dots .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Record 3dots ." + OfficePage.getExceptionDesc());
		}

		if (OfficePage.OfficeEditButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On OfficeEditButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On OfficeEditButton ." + OfficePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeName(newoffice)) {
			logResults.createLogs("Y", "PASS", " OfficeName Entered In the Office Name TextField ." + newoffice);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Office Name ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeTypeDropdown()) {
			logResults.createLogs("Y", "PASS", " Office Type Selected In the Office Type Dropdown .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The Office Type ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeAddressTextfield(officeaddress)) {
			logResults.createLogs("Y", "PASS",
					" Office Address Entered In the Office Address TextField ." + officeaddress);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Office Address ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CountryDropdown()) {
			logResults.createLogs("Y", "PASS", " Country Selected In the Country  Dropdown .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The Country ." + EmployeePage.getExceptionDesc());
		}
		

		if (EmployeePage.StateDropdown(state)) {
			logResults.createLogs("Y", "PASS", " State Selected In the State  Dropdown ." + state);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting The State ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.GeoFencingRange(longitude)) {
			logResults.createLogs("Y", "PASS", " Geo Fencing Range Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Geo Fencing Range ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.LatitudeTextField(longitude)) {
			logResults.createLogs("Y", "PASS", " Latitude Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Latitude ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.LongitudeTextField(longitude)) {
			logResults.createLogs("Y", "PASS", " Longitude Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Longitude ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationSaveButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
		}
		

		if (OfficePage.OfficeSearchTextField(newoffice)) {
			logResults.createLogs("Y", "PASS", "OfficeType Entered In The TextField Successfully." + newoffice);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeType In Search Text Field ." + OfficePage.getExceptionDesc());
		}	

		
		
		if (OfficePage.OfficeEdit3dots()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On Office Record 3dots .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Record 3dots ." + OfficePage.getExceptionDesc());
		}

		if (OfficePage.OfficeEditButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On OfficeEditButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On OfficeEditButton ." + OfficePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeName(officename)) {
			logResults.createLogs("Y", "PASS", " OfficeName Entered In the Office Name TextField ." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Office Name ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeTypeDropdown()) {
			logResults.createLogs("Y", "PASS", " Office Type Selected In the Office Type Dropdown .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The Office Type ." + EmployeePage.getExceptionDesc());
		}

		

		if (EmployeePage.CompanyLocationSaveButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
		}
		
		if (OfficePage.OfficeSearchTextField(officename)) {
			logResults.createLogs("Y", "PASS", "OfficeType Entered In The TextField Successfully." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeType In Search Text Field ." + OfficePage.getExceptionDesc());
		}
		

		if (OfficePage.FirstCompanyRecord()) {
		
			logResults.createLogs("Y", "PASS", " Successfully Fetched The First Record Company Name .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Fetching Disabled Company Location Name ." + OfficePage.getExceptionDesc());
		}
	}

	// MPI_181_Office_03
	@Test(enabled = true, priority = 3, groups = { "Sanity" })
	public void MPI_181_Office_03()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, update the location status to 'Inactive' and ensure that during the 'Add Employee' process, the location is not displayed in the Company Location dropdown.");

	//	ArrayList<String> data = initBase.loadExcelData("company_office", currTC,"password,captcha,message,officename");

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		String officenames = "";

		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
			

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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficePageValidation()) {
			logResults.createLogs("Y", "PASS", "Office Page  Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Office Page Is Not Loaded Completely ." + OfficePage.getExceptionDesc());
		}

		if (OfficePage.OfficeSearchTextField(officename)) {
			logResults.createLogs("Y", "PASS", "OfficeType Entered In The TextField Successfully." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeType In Search Text Field ." + OfficePage.getExceptionDesc());
		}

		

		if (OfficePage.FirstCompanyRecordToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " Successfully Turn Off The Toggle Switch .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Toggle Switch ." + OfficePage.getExceptionDesc());
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

		if (OfficePage.CompanyLocationDropDown()) {
			logResults.createLogs("Y", "PASS",
					" Successfully Clicked On The Company Location DropDown ." + officenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking CompanyLocationDropDown." + OfficePage.getExceptionDesc());
		}
		

		if (OfficePage.CompanyLocationTextField(officename)) {
			logResults.createLogs("Y", "PASS",
					" Successfully Entered  The Disabled Company Location In The Search Field ." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Disabled Company Location Name." + OfficePage.getExceptionDesc());
		}
		

		if (OfficePage.SearchResultErrorMsg()) {
			logResults.createLogs("Y", "PASS",
					" Disabled Location Name Isn't Displayed And Respective Message Is Displayed.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying  Error Message." + OfficePage.getExceptionDesc());
		}

	}

	// MPI_182_Office_04
	@Test(enabled = true, priority = 4, groups = { "Sanity" })
	public void MPI_182_Office_04()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, update the location status to 'Active' and ensure that the location is displayed in the Company Location dropdown during the 'Add Employee' process");

	//	ArrayList<String> data = initBase.loadExcelData("company_office", currTC,"password,captcha");

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		String officenames = "";

		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);			

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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficePageValidation()) {
			logResults.createLogs("Y", "PASS", "Office Page  Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Office Page Is Not Loaded Completely ." + OfficePage.getExceptionDesc());
		}

		if (OfficePage.OfficeSearchTextField(officename)) {
			logResults.createLogs("Y", "PASS", "OfficeType Entered In The TextField Successfully." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeType In Search Text Field ." + OfficePage.getExceptionDesc());
		}

		

		if (OfficePage.FirstCompanyRecordToggleSwitch()) {
			logResults.createLogs("Y", "PASS", " Successfully Turn Off The Toggle Switch .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Toggle Switch ." + OfficePage.getExceptionDesc());
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

		if (OfficePage.CompanyLocationDropDown()) {
			logResults.createLogs("Y", "PASS",
					" Successfully Clicked On The Company Location DropDown ." + officenames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking CompanyLocationDropDown." + OfficePage.getExceptionDesc());
		}
		

		if (OfficePage.CompanyLocationTextField(officename)) {
			logResults.createLogs("Y", "PASS",
					" Successfully Entered  The Status enabled Company Location In The Search Field ." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The status enabled Company Location Name." + OfficePage.getExceptionDesc());
		}
		

		if (OfficePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", " Enabled Location Name Is Displayed.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying  Enabled Location Name." + OfficePage.getExceptionDesc());
		}

	}

	// MPI_186_Office_08
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_186_Office_08()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of the search text field by selectingÂ \"Headquarter\"Â as the office type and ensure that only the records havingÂ HeadquarterÂ as their office type are displayed in the filtered results.");

		ArrayList<String> data = initBase.loadExcelData("company_office", currTC,"officetype");

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		String officetype = data.get(0);

		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);


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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficePageValidation()) {
			logResults.createLogs("Y", "PASS", "Office Page  Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Office Page Is Not Loaded Completely ." + OfficePage.getExceptionDesc());
		}

		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}

		if (OfficePage.SearchOptionSelected()) {
			logResults.createLogs("Y", "PASS", "OfficeType Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting OfficeType ." + OfficePage.getExceptionDesc());
		}
		

		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}

		if (OfficePage.OfficeSearchTextField(officetype)) {
			logResults.createLogs("Y", "PASS", "OfficeType Entered In The TextField Successfully." + officetype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting OfficeType In Search Text Field ." + OfficePage.getExceptionDesc());
		}

		if (OfficePage.SearchResultValidation()) {
			logResults.createLogs("Y", "PASS", "OfficeType Search Results Displayed Successfully." + officetype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying OfficeType In Search Results ." + OfficePage.getExceptionDesc());
		}

	}

	// MPI_184_Office_06
	@Test(enabled = true, priority = 5, groups = { "Sanity" })
	public void MPI_184_Office_06()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of pagination by selecting '25 records per page' view and ensure that only 25 records are displayed per page");

	//	ArrayList<String> data = initBase.loadExcelData("company_office", currTC, "password,captcha");

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		
		String Pagecount = "";

		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);			

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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficePageValidation()) {
			logResults.createLogs("Y", "PASS", "Office Page  Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Office Page Is Not Loaded Completely ." + OfficePage.getExceptionDesc());
		}

		if (OfficePage.PaginationDropdown()) {
			Pagecount = OfficePage.Pagination;
			logResults.createLogs("Y", "PASS", "50 Records Should Be Display Successfully." + Pagecount);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting 50 No From Pagination Dropdown ." + OfficePage.getExceptionDesc());
		}


	}

	// MPI_187_Office_09
	@Test(enabled = true, priority = 9, groups = { "Smoke" })
	public void MPI_187_Office_09()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a duplicate address and check whether an appropriate error message is displayed indicating that the address already exists. ");

		ArrayList<String> data = initBase.loadExcelData("company_office", currTC,
				"state,longitude");

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		String state = data.get(0);
		String longitude = data.get(1);
		
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);


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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficePageValidation()) {
			logResults.createLogs("Y", "PASS", "Office Page  Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Office Page Is Not Loaded Completely ." + OfficePage.getExceptionDesc());
		}
		
		if (EmployeePage.AddOfficeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddOfficeButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddOfficeButton ." + EmployeePage.getExceptionDesc());
		}

		
		

		if (EmployeePage.OfficeName(officename)) {
			logResults.createLogs("Y", "PASS", " OfficeName Entered In the Office Name TextField ." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Office Name ." + EmployeePage.getExceptionDesc());
		}
		
		if (EmployeePage.OfficeTypeDropdown()) {
			logResults.createLogs("Y", "PASS", " Office Type Selected In the Office Type Dropdown .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The Office Type ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CountryDropdown()) {
			logResults.createLogs("Y", "PASS", " Country Selected In the Country  Dropdown .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The Country ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.StateDropdown(state)) {
			logResults.createLogs("Y", "PASS", " State Selected In the State  Dropdown ." + state);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The State ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.GeoFencingRange(longitude)) {
			logResults.createLogs("Y", "PASS", " Geo Fencing Range Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Geo Fencing Range ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.LatitudeTextField(longitude)) {
			logResults.createLogs("Y", "PASS", " Latitude Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Latitude ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.LongitudeTextField(longitude)) {
			logResults.createLogs("Y", "PASS", " Longitude Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Longitude ." + EmployeePage.getExceptionDesc());
		}
		

		if (EmployeePage.CompanyLocationSaveButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.DuplicateErrorMessage()) {
			logResults.createLogs("Y", "PASS", " Successfully Displayed Error Message .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message ." + OfficePage.getExceptionDesc());
		}
	
	}

	// MPI_196_Office_10
	@Test(enabled = true, priority = 10, groups = { "Sanity" })
	public void MPI_196_Office_10()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check whether a proper error message is displayed when the mandatory fields are left blank.");

	ArrayList<String> data = initBase.loadExcelData("company_office", currTC, "longitude");

	String longitude = data.get(0);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);


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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficePageValidation()) {
			logResults.createLogs("Y", "PASS", "Office Page  Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Office Page Is Not Loaded Completely ." + OfficePage.getExceptionDesc());
		}

		if (EmployeePage.AddOfficeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddOfficeButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddOfficeButton ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.GeoFencingRange(longitude)) {
			logResults.createLogs("Y", "PASS", " Geo Fencing Range Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Geo Fencing Range ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.LatitudeTextField(longitude)) {
			logResults.createLogs("Y", "PASS", " Latitude Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Latitude ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.LongitudeTextField(longitude)) {
			logResults.createLogs("Y", "PASS", " Longitude Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting Longitude ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationSaveButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficeNameValidationMessage()) {
			logResults.createLogs("Y", "PASS", " Mandatory Error Message Displayed Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Displaying Office Name Mandatory Validation Message ."
					+ OfficePage.getExceptionDesc());
		}

	}

	// MPI_197_Office_11
	@Test(enabled = true, priority = 11, groups = { "Sanity" })
	public void MPI_197_Office_11()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check whether proper error message is displayed if the user enters only one character in the office name and on address text field");

		ArrayList<String> data = initBase.loadExcelData("company_office", currTC,
				"officename,officeaddress");

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		String officename = data.get(0);
		String officeaddress = data.get(1);

		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);


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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficePageValidation()) {
			logResults.createLogs("Y", "PASS", "Office Page  Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Office Page Is Not Loaded Completely ." + OfficePage.getExceptionDesc());
		}

		if (EmployeePage.AddOfficeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddOfficeButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddOfficeButton ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeName(officename)) {
			logResults.createLogs("Y", "PASS", " OfficeName Entered In the Office Name TextField ." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Office Name ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeTypeDropdown()) {
			logResults.createLogs("Y", "PASS", " Office Type Selected In the Office Type Dropdown .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting The Office Type ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeAddressTextfield(officeaddress)) {
			logResults.createLogs("Y", "PASS",
					" Office Address Entered In the Office Address TextField ." + officeaddress);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Office Address ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationSaveButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficeNameValidationMessage()) {
			logResults.createLogs("Y", "PASS",
					" Office Name Requires At Least 2 Characters. Error Message Displayed Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message ." + OfficePage.getExceptionDesc());
		}

	}

	// MPI_198_Office_12
	@Test(enabled = true, priority = 12, groups = { "Sanity" })
	public void MPI_198_Office_12()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check whether a proper error message is displayed when the user enters special characters in the Office Name text field, and ensure that the field accepts only alphabets and numbers.");

		ArrayList<String> data = initBase.loadExcelData("company_office", currTC,
				"officename");

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		String officename = data.get(0);

		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);


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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficePageValidation()) {
			logResults.createLogs("Y", "PASS", "Office Page  Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Office Page Is Not Loaded Completely ." + OfficePage.getExceptionDesc());
		}

		if (EmployeePage.AddOfficeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddOfficeButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddOfficeButton ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.OfficeName(officename)) {
			logResults.createLogs("Y", "PASS", " OfficeName Entered In the Office Name TextField ." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Office Name ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationSaveButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficeNameValidationMessage()) {
			logResults.createLogs("Y", "PASS",
					" Office Name Allowed Only Alphabets And Numbers. Error Message Displayed Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message ." + OfficePage.getExceptionDesc());
		}

	}

	// MPI_200_Office_14
	@Test(enabled = true, priority = 14, groups = { "Sanity" })
	public void MPI_200_Office_14()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enter alphabets and special characters in the phone number text field and check whether a proper error message is displayed.");

		ArrayList<String> data = initBase.loadExcelData("company_office", currTC,
				"officeno");

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		String officeno = data.get(0);

		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);


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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficePageValidation()) {
			logResults.createLogs("Y", "PASS", "Office Page  Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Office Page Is Not Loaded Completely ." + OfficePage.getExceptionDesc());
		}

		if (EmployeePage.AddOfficeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddOfficeButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddOfficeButton ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyPrimaryContactNo(officeno)) {
			logResults.createLogs("Y", "PASS",
					" Special Characters Entered In the Office Number TextField ." + officeno);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Office Number ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationSaveButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
		}


	}

	// MPI_201_Office_15
	@Test(enabled = true, priority = 15, groups = { "Sanity" })
	public void MPI_201_Office_15()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check whether a proper error message is displayed if the user enters an invalid email ID");

		ArrayList<String> data = initBase.loadExcelData("company_office", currTC,
				"officeemail");

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

	

		String officeemail = data.get(0);

		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);



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

		if (EmployeePage.OfficeButton()) {
			logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficePageValidation()) {
			logResults.createLogs("Y", "PASS", "Office Page  Validated Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Office Page Is Not Loaded Completely ." + OfficePage.getExceptionDesc());
		}

		if (EmployeePage.AddOfficeButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On AddOfficeButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddOfficeButton ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyPrimaryEmailId(officeemail)) {
			logResults.createLogs("Y", "PASS",
					" Invalid Office Email Entered In the Office Email TextField ." + officeemail);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Office Email ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.CompanyLocationSaveButton()) {
			logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
		}

		if (OfficePage.OfficeEmailValidation()) {
			logResults.createLogs("Y", "PASS", " Email Is Not Valid. Error Message Displayed Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Displaying Error Message ." + OfficePage.getExceptionDesc());
		}

	}

	@AfterMethod(alwaysRun = true)
	void Aftermethod() {
		logResults.onlyLog();
		//if (driver != null) {
		//	driver.quit();
		//}
	}

	@AfterClass(alwaysRun = true)
	void cleanUp() {
		if (driver != null) {
			driver.quit();
		}
	}

}
