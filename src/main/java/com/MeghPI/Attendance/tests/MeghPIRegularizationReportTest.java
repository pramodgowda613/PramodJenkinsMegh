package com.MeghPI.Attendance.tests;


import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationReportsPage;
import com.MeghPI.Attendance.pages.MeghPITimeLogReportsPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import utils.Pramod;
import utils.Utils;

public class MeghPIRegularizationReportTest {

	
	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	private String cmpcode = "";
	private String Emailid = "";
	private String entityname = "";
	private String officename = "";
	private String departmentname = "";
	private String teamname = "";
	private String designationname= "";
    private String EmpID = "";
    private String EmpFirstName = "";
  
	
	
	
    @Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
		driver = loadDriver.getDriver(device);

		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

    @Parameters({ "device", "hubnodeip" })
	@BeforeClass(alwaysRun = true)
	void runOnce(int device, @Optional String hubnodeip) {
		logResults.createReport(device);
		logResults.setTestMethodErrorCount(0);
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		EmpID = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpID", "");
		EmpFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpFirstName", "");
		
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		entityname = "AutoEN" + initBase.executionRunTime;
	}
	
	
	
	// MPI_1115_All_Reports_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_1115_All_Reports_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of search feature using each search option");

		ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
				"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,regularizationreason");

		
		String password = data.get(0);
		String captcha = data.get(1);
		String regularizationstatus = data.get(2);
		String regularizationintime = data.get(3);
		String regularizationouttime = data.get(4);
		String regularizationreason = data.get(5);
	
		  Map<String, String> details = Pramod.getPrevious12WorkingDates();

	        String Regulizationdate = details.get("month1WorkingDate");
	        String formattedPrevDate = Pramod.convertToUIFormat(Regulizationdate);
	        String monthName = details.get("monthName");
	        String year = details.get("year");
		
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
	
    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
MeghLoginTest meghlogintest = new MeghLoginTest();
MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

if (meghlogintest.verifyCompanyLogin(cmpcode, EmpID, EmpID, captcha, logResults, driver)) {
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



if (RolePermissionpage.EmpRegulationRequest()) {

	logResults.createLogs("Y", "PASS", "Employee Can Access His EmpRegulationRequest Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Displaying EmpRegulationRequest Tab." + RolePermissionpage.getExceptionDesc());
}

if (meghpiregularizationReportsPage.ApplyRegularizationButtonEmp()) {

	logResults.createLogs("Y", "PASS", "Apply Regularization Button Clicked Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Clicking On Apply Regularization Button." + meghpiregularizationReportsPage.getExceptionDesc());
}
if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

	logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
}
if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(Regulizationdate)) {

	logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + Regulizationdate);
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Inputting From Date." + meghpiregularizationReportsPage.getExceptionDesc());
}
if (meghpiregularizationReportsPage.EmpRegularizationInTime()) {

	logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Clicking On From Time." + meghpiregularizationReportsPage.getExceptionDesc());
}
if (meghpiregularizationReportsPage.EmpRegularizationInTimeInputted(regularizationintime)) {

	logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + regularizationintime);
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Inputting In Time." + meghpiregularizationReportsPage.getExceptionDesc());
}
if (meghpiregularizationReportsPage.EmpRegularizationOutDate()) {

	logResults.createLogs("Y", "PASS", "To Date TextField Clicked Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Clicking On To Date." + meghpiregularizationReportsPage.getExceptionDesc());
}
if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(Regulizationdate)) {

	logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + Regulizationdate);
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
}
if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(Regulizationdate)) {

	logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + Regulizationdate);
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
}

if (meghpiregularizationReportsPage.EmpRegularizationOutTime()) {

	logResults.createLogs("Y", "PASS", "Out Time TextField Clicked Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Clicking On Out Time." + meghpiregularizationReportsPage.getExceptionDesc());
}
if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(regularizationouttime)) {

	logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + regularizationouttime);
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Inputting Out TIme." + meghpiregularizationReportsPage.getExceptionDesc());
}
if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(regularizationouttime)) {

	logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + regularizationouttime);
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Inputting Out TIme." + meghpiregularizationReportsPage.getExceptionDesc());
}

if (meghpiregularizationReportsPage.EmpRegularizationType()) {

	logResults.createLogs("Y", "PASS", "Regularization Type Selected Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Selecting Regularization Type." + meghpiregularizationReportsPage.getExceptionDesc());
}

if (meghpiregularizationReportsPage.EmpRegularizationReason(regularizationreason)) {

	logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regularizationreason);
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Inputting Regularization Reason." + meghpiregularizationReportsPage.getExceptionDesc());
}
if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

	logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Clicking On Regularization Apply Save Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}

		
		if (meghpiregularizationReportsPage.RegularizationReportButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

			logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiattendancereport.LocationDropdown()) {

			logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

			logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.LocationDropdownSearchResult()) {

			logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

			logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
		}

		if (meghpiattendancereport.YearDropDown(year)) {

			logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.MonthDropDown(monthName)) {

			logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
		}
		
		
		if (meghpiattendancereport.FilterSaveButton()) {

			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
		}
	
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		
		if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdCheckBox()) {

			logResults.createLogs("Y", "PASS", "Emp Id Search Option Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp ID Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		
		if (meghpitimelog.LateInDateCheckBox()) {

			logResults.createLogs("Y", "PASS", "Date Search Option Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Date Search Option Checkbox." + meghpitimelog.getExceptionDesc());
		}
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(EmpID)) {

			logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + EmpID );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		
		if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdSearchResult(EmpID)) {

			logResults.createLogs("Y", "PASS", "Emp ID Displayed Successfully." + EmpID );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Inputted Emp ID Record." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(formattedPrevDate)) {

			logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + formattedPrevDate );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Regularization Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResult(Regulizationdate)) {

			logResults.createLogs("Y", "PASS", "Regularization Date Record Displayed Successfully." + Regulizationdate );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Inputted Regularization Date Record." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		
		if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdCheckBox()) {

			logResults.createLogs("Y", "PASS", "Emp Id Search Option Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp ID Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		
		if (meghpitimelog.LateInDateCheckBox()) {

			logResults.createLogs("Y", "PASS", "Date Search Option Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Date Search Option Checkbox." + meghpitimelog.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationAllReportStatusCheckBox()) {

			logResults.createLogs("Y", "PASS", "Status Search Option Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Status Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + regularizationstatus );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		
		if (meghpiregularizationReportsPage.RegularizationAllReportStatusSearchResult(regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "Inputted Status Displayed Successfully." + regularizationstatus );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Inputted Status Record." + meghpiregularizationReportsPage.getExceptionDesc());
		}
	}
	
	// MPI_1116_All_Reports_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_1116_All_Reports_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

		ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
				"password,captcha,subject,ccmail");

		
		String password = data.get(0);
		String captcha = data.get(1);
		String subject = data.get(2);
		String ccmail = data.get(3) ;

		 Map<String, String> details = Pramod.getPrevious12WorkingDates();
	        String monthName = details.get("monthName");
	        String year = details.get("year");

		
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		
    
    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
MeghLoginTest meghlogintest = new MeghLoginTest();
MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegularizationReportButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

			logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiattendancereport.LocationDropdown()) {

			logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

			logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.LocationDropdownSearchResult()) {

			logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

			logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
		}

		if (meghpiattendancereport.YearDropDown(year)) {

			logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.MonthDropDown(monthName)) {

			logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
		}
		
		
		if (meghpiattendancereport.FilterSaveButton()) {

			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.MailReportButton()) {

			logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On MailReportButton." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiattendancereport.SubjectTextField(subject)) {

			logResults.createLogs("Y", "PASS", "Mail Subject Inputted Successfully." + subject);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Mail Subject." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiattendancereport.CCTextField(ccmail)) {

			logResults.createLogs("Y", "PASS", "Mail CC Inputted Successfully." + ccmail);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Mail CC." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiattendancereport.BCCTextField(ccmail)) {

			logResults.createLogs("Y", "PASS", "Mail BCC Inputted Successfully." + ccmail);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Mail BCC." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiattendancereport.FrequencySelection()) {

			logResults.createLogs("Y", "PASS", "Frequency Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Mail sending Frequency." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiattendancereport.MailSendSaveButton()) {

			logResults.createLogs("Y", "PASS", "MailSendSaveButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On MailSendSaveButton." + meghpiattendancereport.getExceptionDesc());
		}
	}
	
	// MPI_1118_All_Reports_04
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_1118_All_Reports_04()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of filter feature by selecting specific team");

		ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);
		
		 Map<String, String> details = Pramod.getPrevious12WorkingDates();
	        String monthName = details.get("monthName");
	        String year = details.get("year");
		

		
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);

    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
MeghLoginTest meghlogintest = new MeghLoginTest();

		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegularizationReportButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

			logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiattendancereport.LocationDropdown()) {

			logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

			logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.LocationDropdownSearchResult()) {

			logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

			logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiattendancereport.TeamDropDown(teamname)) {

			logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
		}

		if (meghpiattendancereport.YearDropDown(year)) {

			logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.MonthDropDown(monthName)) {

			logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
		}
		
		
		if (meghpiattendancereport.FilterSaveButton()) {

			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

			logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
		}
	}
	
	// MPI_1119_All_Reports_05
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_1119_All_Reports_05()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

		ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
				"password,captcha");

		
		String password = data.get(0);
		String captcha = data.get(1);
		 Map<String, String> details = Pramod.getPrevious12WorkingDates();
	        String monthName = details.get("monthName");
	        String year = details.get("year");
		

		
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
MeghLoginTest meghlogintest = new MeghLoginTest();

		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}

		
		if (meghpiregularizationReportsPage.RegularizationReportButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

			logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiattendancereport.LocationDropdown()) {

			logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

			logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.LocationDropdownSearchResult()) {

			logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

			logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiattendancereport.TeamDropDown(teamname)) {

			logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiattendancereport.DesignationDropDown(designationname)) {

			logResults.createLogs("Y", "PASS", "Designation Selected Successfully." + designationname );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Designation Name." + meghpiattendancereport.getExceptionDesc());
		}

		if (meghpiattendancereport.YearDropDown(year)) {

			logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.MonthDropDown(monthName)) {

			logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
		}
		
		
		if (meghpiattendancereport.FilterSaveButton()) {

			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

			logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
		}
	}
	
	// MPI_1121_All_Reports_06
		@Test(enabled = true, priority = 5, groups = { "Smoke" })
		public void MPI_1121_All_Reports_06()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of filter feature by selecting employee type");

			ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
					"password,captcha");

			
			String password = data.get(0);
			String captcha = data.get(1);
			 Map<String, String> details = Pramod.getPrevious12WorkingDates();

		        String monthName = details.get("monthName");
		        String year = details.get("year");
			

			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
	    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	MeghLoginTest meghlogintest = new MeghLoginTest();

			
			if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
				logResults.createLogs("Y", "PASS", "Login Done Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Login Is Failed." + MeghLoginPage.getExceptionDesc());
			}
			if (RolePermissionpage.ReprtButton()) {

				logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.RegularizationReportButton()) {

				logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

				logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiattendancereport.LocationDropdown()) {

				logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

				logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.LocationDropdownSearchResult()) {

				logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

				logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiattendancereport.EntityTypeDropDown(entityname)) {

				logResults.createLogs("Y", "PASS", "EntityType Selected Successfully." + entityname );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting EntityType Name." + meghpiattendancereport.getExceptionDesc());
			}

			if (meghpiattendancereport.YearDropDown(year)) {

				logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.MonthDropDown(monthName)) {

				logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
			}
			
			
			if (meghpiattendancereport.FilterSaveButton()) {

				logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

				logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
			}
		}
	
		// MPI_1122_All_Reports_07
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_1122_All_Reports_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					 Map<String, String> details = Pramod.getPrevious12WorkingDates();
				        String monthName = details.get("monthName");
				        String year = details.get("year");
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

						logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1123_All_Reports_08
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_1123_All_Reports_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					 Map<String, String> details = Pramod.getPrevious12WorkingDates();

				        String Regulizationdate = details.get("month1WorkingDate");
				        String monthName = details.get("monthName");
				        String year = details.get("year");
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

						logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + Regulizationdate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + Regulizationdate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
	
				// MPI_1126_All_Reports_11
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_1126_All_Reports_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, as a employee raise the regularization request and ensure request is displayed and status is pending.");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,regularizationreason");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					String regularizationintime = data.get(3);
					String regularizationouttime = data.get(4);
					String regularizationreason = data.get(5);
					
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
					String day2 = dateDetails.get("month2WorkingDate");
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					    String formattedPrevDate = Pramod.convertToUIFormat(day2);
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			
					
			if (meghlogintest.verifyCompanyLogin(cmpcode, EmpID, EmpID, captcha, logResults, driver)) {
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
			


			if (RolePermissionpage.EmpRegulationRequest()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His EmpRegulationRequest Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying EmpRegulationRequest Tab." + RolePermissionpage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.ApplyRegularizationButtonEmp()) {

				logResults.createLogs("Y", "PASS", "Apply Regularization Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Apply Regularization Button." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

				logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(day2)) {

				logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + day2);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting From Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInTime()) {

				logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On From Time." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInTimeInputted(regularizationintime)) {

				logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + regularizationintime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting In Time." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutDate()) {

				logResults.createLogs("Y", "PASS", "To Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On To Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(day2)) {

				logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + day2);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(day2)) {

				logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + day2);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.EmpRegularizationOutTime()) {

				logResults.createLogs("Y", "PASS", "Out Time TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Out Time." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(regularizationouttime)) {

				logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + regularizationouttime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Out TIme." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(regularizationouttime)) {

				logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + regularizationouttime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Out TIme." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.EmpRegularizationType()) {

				logResults.createLogs("Y", "PASS", "Regularization Type Selected Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Regularization Type." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.EmpRegularizationReason(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Regularization Reason." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

				logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Regularization Apply Save Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
								"Login Is Failed." + meghloginpage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

						logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
				
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdCheckBox()) {

						logResults.createLogs("Y", "PASS", "Emp Id Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp ID Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(EmpID)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Regularization Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.ValidationEmpWithDateAndStatus(regularizationstatus,formattedPrevDate,EmpID)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Record Displayed Successfully." + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Regularization Date Record." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1127_All_Reports_12
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_1127_All_Reports_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, as a employee raise the regularization request and as a admin approve the request and ensure request is displayed and status is approved");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,regularizationstatus");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
					String day2 = dateDetails.get("month2WorkingDate");
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					    String formattedPrevDate = Pramod.convertToUIFormat(day2);
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}

					if (RolePermissionpage.EmployeeAttendanceButton()) {

						logResults.createLogs("Y", "PASS", " Attendance Module Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendane Module." + RolePermissionpage.getExceptionDesc());
					}
					

					if (RolePermissionpage.HrAccountRegulationRequestTab()) {

						logResults.createLogs("Y", "PASS", "Regularization Rquest Tab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Rquest In Attendance Module."
										+ RolePermissionpage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportMonthFilterButton(monthName)) {

						logResults.createLogs("Y", "PASS", "Regularization Month Data Displayed Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Regularization Month Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Regularization Emp Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.AdminApprovedToRegulirization(formattedPrevDate,EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Admin Approved To Regulirization Request Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Approving Regularization Request." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.ApproveButton()) {

						logResults.createLogs("Y", "PASS", " Approved Button Clicked Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approve Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

						logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(day2)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + day2);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(day2)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + day2);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Regularization Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.ValidationEmpWithDateAndStatus(regularizationstatus,formattedPrevDate,EmpID)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Record Displayed Successfully." + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Regularization Date Record." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1128_All_Reports_13
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_1128_All_Reports_13()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, as a employee raise the regularization request and as a admin reject the request and ensure request is displayed and status is rejected");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,regularizationstatus,rejectreason,regularizationintime,regularizationouttime,regularizationreason");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					String RejectReason = data.get(3);
					String regularizationintime = data.get(4);
					String regularizationouttime = data.get(5);
					String regularizationreason = data.get(6);

					
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
					String day3 = dateDetails.get("month3WorkingDate");
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					    String formattedPrevDate = Pramod.convertToUIFormat(day3);
				        
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

			
			if (meghlogintest.verifyCompanyLogin(cmpcode, EmpID, EmpID, captcha, logResults, driver)) {
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
			


			if (RolePermissionpage.EmpRegulationRequest()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His EmpRegulationRequest Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying EmpRegulationRequest Tab." + RolePermissionpage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.ApplyRegularizationButtonEmp()) {

				logResults.createLogs("Y", "PASS", "Apply Regularization Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Apply Regularization Button." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

				logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(day3)) {

				logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + day3);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting From Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInTime()) {

				logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On From Time." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInTimeInputted(regularizationintime)) {

				logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + regularizationintime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting In Time." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutDate()) {

				logResults.createLogs("Y", "PASS", "To Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On To Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(day3)) {

				logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + day3);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(day3)) {

				logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + day3);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.EmpRegularizationOutTime()) {

				logResults.createLogs("Y", "PASS", "Out Time TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Out Time." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(regularizationouttime)) {

				logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + regularizationouttime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Out TIme." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(regularizationouttime)) {

				logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + regularizationouttime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Out TIme." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.EmpRegularizationType()) {

				logResults.createLogs("Y", "PASS", "Regularization Type Selected Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Regularization Type." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.EmpRegularizationReason(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Regularization Reason." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

				logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Regularization Apply Save Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
								"Login Is Failed." + meghloginpage.getExceptionDesc());
					}

					if (RolePermissionpage.EmployeeAttendanceButton()) {

						logResults.createLogs("Y", "PASS", " Attendance Module Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendane Module." + RolePermissionpage.getExceptionDesc());
					}
					

					if (RolePermissionpage.HrAccountRegulationRequestTab()) {

						logResults.createLogs("Y", "PASS", "Regularization Rquest Tab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Rquest In Attendance Module."
										+ RolePermissionpage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportMonthFilterButton(monthName)) {

						logResults.createLogs("Y", "PASS", "Regularization Month Data Displayed Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Regularization Month Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Regularization Emp Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.AdminRejectedToRegulirization(formattedPrevDate,EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Admin Approved To Regulirization Request Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Approving Regularization Request." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RejectReasonTextField(RejectReason)) {

						logResults.createLogs("Y", "PASS", " Reject Reason Inputted Successfully." + RejectReason );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Reject Reason." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiregularizationReportsPage.RejectButton()) {

						logResults.createLogs("Y", "PASS", " Reject Button Clicked Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Reject Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

						logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(day3)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + day3);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(day3)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + day3);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Regularization Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.ValidationEmpWithDateAndStatus(regularizationstatus,formattedPrevDate,EmpID)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Record Displayed Successfully." + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Regularization Date Record." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1129_All_Reports_14
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_1129_All_Reports_14()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, As a admin raise the regularization request for the employee and ensure record is displayed and status is approved");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,regularizationreason");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String regularizationstatus = data.get(2);
					String regularizationintime = data.get(3);
					String regularizationouttime = data.get(4);
					String regularizationreason = data.get(5);
					
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
					String day3 = dateDetails.get("month5WorkingDate");
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					    String formattedPrevDate = Pramod.convertToUIFormat(day3);
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}

					if (RolePermissionpage.EmployeeAttendanceButton()) {

						logResults.createLogs("Y", "PASS", " Attendance Module Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendane Module." + RolePermissionpage.getExceptionDesc());
					}
			

					if (RolePermissionpage.HrAccountRegulationRequestTab()) {

						logResults.createLogs("Y", "PASS", "Regularization Rquest Tab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Rquest In Attendance Module."
										+ RolePermissionpage.getExceptionDesc());
					}
				
					if (meghpiregularizationReportsPage.ApplyRegularizationButton()) {

						logResults.createLogs("Y", "PASS", "Apply Regularization Button Clicked Successfully."  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Apply Regularization Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.ApplyRegularizationForOthersButton()) {

						logResults.createLogs("Y", "PASS", "Apply Regularization For Others Button Clicked Successfully."  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Apply Regularization For Others Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmployeeSelectionDropDown()) {

						logResults.createLogs("Y", "PASS", "Emp Selection DropDown Clicked Successfully."  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Emp Selection DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmployeeSelectionInputField(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + EmpFirstName  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmployeeSelectedFromDrp()) {

						logResults.createLogs("Y", "PASS", "Emp Name Selected Successfully." + EmpFirstName  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationFromDateClick()) {

						logResults.createLogs("Y", "PASS", "Regularization FromDate Clicked Successfully."   );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationFromDateInputted(day3)) {

						logResults.createLogs("Y", "PASS", "Regularization FromDate Inputted Successfully." + day3  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationFromTimeClicked()) {

						logResults.createLogs("Y", "PASS", "Regularization From Time Clicked Successfully."  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationFromTimeInputted(regularizationintime)) {

						logResults.createLogs("Y", "PASS", "Regularization From Time Inputted Successfully." + regularizationintime  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting From Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationToDateClicked()) {

						logResults.createLogs("Y", "PASS", "Regularization To Date Clicked Successfully."   );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationToDateInputted(day3)) {

						logResults.createLogs("Y", "PASS", "Regularization To Date Inputted Successfully." + day3  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationToTimeClicked()) {

						logResults.createLogs("Y", "PASS", "Regularization To Time Clicked Successfully." + EmpFirstName  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On To Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationToTimeInputted(regularizationouttime)) {

						logResults.createLogs("Y", "PASS", "Regularization End Time Inputted Successfully." + regularizationouttime  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting End Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationType()) {

						logResults.createLogs("Y", "PASS", "Regularization Type Selected Successfully."  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Regularization Type." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationReason(regularizationreason)) {

						logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regularizationreason  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Regularization Reason." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationApplyButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Apply Button Clicked Successfully."   );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Apply Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

						logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(day3)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + day3);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(day3)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + day3);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Regularization Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.ValidationEmpWithDateAndStatus(regularizationstatus,formattedPrevDate,EmpID)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Record Displayed Successfully." + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Regularization Date Record." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
	
				// MPI_1130_Approved_reports_01
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_1130_Approved_reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature using each search option");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,regularizationstatus");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
	
	
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
					String day3 = dateDetails.get("month2WorkingDate");
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					    String formattedPrevDate = Pramod.convertToUIFormat(day3);
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
	
	
	
	
	
	
	
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationApprovedTab()) {

						logResults.createLogs("Y", "PASS", "Approved Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdCheckBox()) {

						logResults.createLogs("Y", "PASS", "Emp Id Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp ID Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", "Date Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Date Search Option Checkbox." + meghpitimelog.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(EmpID)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdSearchResult(EmpID)) {

						logResults.createLogs("Y", "PASS", "Emp ID Displayed Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Emp ID Record." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(formattedPrevDate)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + formattedPrevDate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Regularization Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResult(day3)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Record Displayed Successfully." + day3 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Regularization Date Record." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdCheckBox()) {

						logResults.createLogs("Y", "PASS", "Emp Id Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp ID Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", "Date Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Date Search Option Checkbox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportStatusCheckBox()) {

						logResults.createLogs("Y", "PASS", "Status Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Status Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportStatusSearchResult(regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "Inputted Status Displayed Successfully." + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Status Record." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				}
	
				// MPI_1131_Approved_reports_02
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_1131_Approved_reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,subject,ccmail");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String subject = data.get(2);
					String ccmail = data.get(3) ;
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					
			    
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationApprovedTab()) {

						logResults.createLogs("Y", "PASS", "Approved Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MailReportButton()) {

						logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailReportButton." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.SubjectTextField(subject)) {

						logResults.createLogs("Y", "PASS", "Mail Subject Inputted Successfully." + subject);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail Subject." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.CCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail CC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail CC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.BCCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail BCC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail BCC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FrequencySelection()) {

						logResults.createLogs("Y", "PASS", "Frequency Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Mail sending Frequency." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MailSendSaveButton()) {

						logResults.createLogs("Y", "PASS", "MailSendSaveButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailSendSaveButton." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1133_Approved_reports_04
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_1133_Approved_reports_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);

					
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);

			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationApprovedTab()) {

						logResults.createLogs("Y", "PASS", "Approved Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1134_Approved_reports_05
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_1134_Approved_reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationApprovedTab()) {

						logResults.createLogs("Y", "PASS", "Approved Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DesignationDropDown(designationname)) {

						logResults.createLogs("Y", "PASS", "Designation Selected Successfully." + designationname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Designation Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1135_Approved_reports_06
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
				public void MPI_1135_Approved_reports_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationApprovedTab()) {

						logResults.createLogs("Y", "PASS", "Approved Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.EntityTypeDropDown(entityname)) {

						logResults.createLogs("Y", "PASS", "EntityType Selected Successfully." + entityname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EntityType Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1136_Approved_reports_07
				@Test(enabled = true, priority = 17, groups = { "Smoke" })
				public void MPI_1136_Approved_reports_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationApprovedTab()) {

						logResults.createLogs("Y", "PASS", "Approved Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1137_Approved_reports_08
				@Test(enabled = true, priority = 18, groups = { "Smoke" })
				public void MPI_1137_Approved_reports_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
					String day3 = dateDetails.get("month2WorkingDate");
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationApprovedTab()) {

						logResults.createLogs("Y", "PASS", "Approved Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(day3)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + day3);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(day3)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + day3);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1140_Approved_reports_11
				@Test(enabled = true, priority = 19, groups = { "Smoke" })
				public void MPI_1140_Approved_reports_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, as a employee raise the regularization request and as a admin approve the request and ensure request is displayed and status is approved");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,regularizationstatus");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
					String day3 = dateDetails.get("month2WorkingDate");
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					    String formattedPrevDate = Pramod.convertToUIFormat(day3);

					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationApprovedTab()) {

						logResults.createLogs("Y", "PASS", "Approved Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(day3)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + day3);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(day3)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + day3);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdCheckBox()) {

						logResults.createLogs("Y", "PASS", "Emp Id Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp ID Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(EmpID)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.ValidationEmpWithDateAndApproveStatus(regularizationstatus,formattedPrevDate,EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Emp Regulirization Apporved Record Validated Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Emp Approved Record." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
				}
	
				// MPI_1141_Rejected_reports_01
				@Test(enabled = true, priority = 20, groups = { "Smoke" })
				public void MPI_1141_Rejected_reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature using each search option");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,regularizationstatus");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
	
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
					String day3 = dateDetails.get("month3WorkingDate");
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					    String formattedPrevDate = Pramod.convertToUIFormat(day3);
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
	
	
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationRejectedTab()) {

						logResults.createLogs("Y", "PASS", "Rejected Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdCheckBox()) {

						logResults.createLogs("Y", "PASS", "Emp Id Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp ID Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", "Date Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Date Search Option Checkbox." + meghpitimelog.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(EmpID)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdSearchResult(EmpID)) {

						logResults.createLogs("Y", "PASS", "Emp ID Displayed Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Emp ID Record." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(formattedPrevDate)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + formattedPrevDate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Regularization Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResult(day3)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Record Displayed Successfully." + formattedPrevDate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Regularization Date Record." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdCheckBox()) {

						logResults.createLogs("Y", "PASS", "Emp Id Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp ID Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", "Date Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Date Search Option Checkbox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportStatusCheckBox()) {

						logResults.createLogs("Y", "PASS", "Status Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Status Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportStatusSearchResult(regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "Inputted Status Displayed Successfully." + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Status Record." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				}
	
				// MPI_1142_Rejected_reports_02
				@Test(enabled = true, priority = 21, groups = { "Smoke" })
				public void MPI_1142_Rejected_reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,subject,ccmail");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String subject = data.get(2);
					String ccmail = data.get(3) ;
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					
			    
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationRejectedTab()) {

						logResults.createLogs("Y", "PASS", "Rejected Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MailReportButton()) {

						logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailReportButton." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.SubjectTextField(subject)) {

						logResults.createLogs("Y", "PASS", "Mail Subject Inputted Successfully." + subject);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail Subject." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.CCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail CC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail CC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.BCCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail BCC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail BCC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FrequencySelection()) {

						logResults.createLogs("Y", "PASS", "Frequency Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Mail sending Frequency." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MailSendSaveButton()) {

						logResults.createLogs("Y", "PASS", "MailSendSaveButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailSendSaveButton." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1144_Rejected_reports_04
				@Test(enabled = true, priority = 22, groups = { "Smoke" })
				public void MPI_1144_Rejected_reports_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);

			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationRejectedTab()) {

						logResults.createLogs("Y", "PASS", "Rejected Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1145_Rejected_reports_05
				@Test(enabled = true, priority = 23, groups = { "Smoke" })
				public void MPI_1145_Rejected_reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationRejectedTab()) {

						logResults.createLogs("Y", "PASS", "Rejected Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DesignationDropDown(designationname)) {

						logResults.createLogs("Y", "PASS", "Designation Selected Successfully." + designationname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Designation Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
	
				// MPI_1146_Rejected_reports_06
				@Test(enabled = true, priority = 24, groups = { "Smoke" })
				public void MPI_1146_Rejected_reports_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationRejectedTab()) {

						logResults.createLogs("Y", "PASS", "Rejected Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.EntityTypeDropDown(entityname)) {

						logResults.createLogs("Y", "PASS", "EntityType Selected Successfully." + entityname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EntityType Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
				
				// MPI_1147_Rejected_reports_07
				@Test(enabled = true, priority = 25, groups = { "Smoke" })
				public void MPI_1147_Rejected_reports_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationRejectedTab()) {

						logResults.createLogs("Y", "PASS", "Rejected Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}	
			
				// MPI_1148_Rejected_reports_08
				@Test(enabled = true, priority = 26, groups = { "Smoke" })
				public void MPI_1148_Rejected_reports_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
					String day3 = dateDetails.get("month3WorkingDate");
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationRejectedTab()) {

						logResults.createLogs("Y", "PASS", "Rejected Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(day3)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + day3);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(day3)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + day3);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
				
				// MPI_1151_Rejected_reports_11
				@Test(enabled = true, priority = 27, groups = { "Smoke" })
				public void MPI_1151_Rejected_reports_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, as a employee raise the regularization request and as a admin reject the request and ensure request is displayed and status is rejected.");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,regularizationstatus");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
					String day3 = dateDetails.get("month3WorkingDate");
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					    String formattedPrevDate = Pramod.convertToUIFormat(day3);
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegulirizationRejectedTab()) {

						logResults.createLogs("Y", "PASS", "Rejected Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(day3)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + day3);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(day3)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + day3);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdCheckBox()) {

						logResults.createLogs("Y", "PASS", "Emp Id Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp ID Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(EmpID)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.ValidationEmpWithDateAndApproveStatus(regularizationstatus,formattedPrevDate,EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Emp Regulirization Apporved Record Validated Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Emp Approved Record." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
				}	
				
				// MPI_1152_Pending_reports_01
				@Test(enabled = true, priority = 28, groups = { "Smoke" })
				public void MPI_1152_Pending_reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature using each search option");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,regularizationreason");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					String regularizationintime = data.get(3);
					String regularizationouttime = data.get(4);
					String regularizationreason = data.get(5);
				
					Map<String, String> details = Pramod.getPrevious12WorkingDates();

			        String Regulizationdate = details.get("month6WorkingDate");
			        String formattedPrevDate = Pramod.convertToUIFormat(Regulizationdate);
			        String monthName = details.get("monthName");
			        String year = details.get("year");
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

			
			if (meghlogintest.verifyCompanyLogin(cmpcode, EmpID, EmpID, captcha, logResults, driver)) {
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
			


			if (RolePermissionpage.EmpRegulationRequest()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His EmpRegulationRequest Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying EmpRegulationRequest Tab." + RolePermissionpage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.ApplyRegularizationButtonEmp()) {

				logResults.createLogs("Y", "PASS", "Apply Regularization Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Apply Regularization Button." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

				logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + Regulizationdate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting From Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInTime()) {

				logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On From Time." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInTimeInputted(regularizationintime)) {

				logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + regularizationintime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting In Time." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutDate()) {

				logResults.createLogs("Y", "PASS", "To Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On To Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + Regulizationdate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + Regulizationdate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.EmpRegularizationOutTime()) {

				logResults.createLogs("Y", "PASS", "Out Time TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Out Time." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(regularizationouttime)) {

				logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + regularizationouttime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Out TIme." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(regularizationouttime)) {

				logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + regularizationouttime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Out TIme." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.EmpRegularizationType()) {

				logResults.createLogs("Y", "PASS", "Regularization Type Selected Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Regularization Type." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.EmpRegularizationReason(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Regularization Reason." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

				logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Regularization Apply Save Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
								"Login Is Failed." + meghloginpage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationPendingTab()) {

						logResults.createLogs("Y", "PASS", "Pending Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdCheckBox()) {

						logResults.createLogs("Y", "PASS", "Emp Id Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp ID Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", "Date Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Date Search Option Checkbox." + meghpitimelog.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(EmpID)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdSearchResult(EmpID)) {

						logResults.createLogs("Y", "PASS", "Emp ID Displayed Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Emp ID Record." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(formattedPrevDate)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + formattedPrevDate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Regularization Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResult(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Record Displayed Successfully." + Regulizationdate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Regularization Date Record." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdCheckBox()) {

						logResults.createLogs("Y", "PASS", "Emp Id Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp ID Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", "Date Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Date Search Option Checkbox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportStatusCheckBox()) {

						logResults.createLogs("Y", "PASS", "Status Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Status Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportStatusSearchResult(regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "Inputted Status Displayed Successfully." + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Status Record." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				}		
				
				// MPI_1153_Pending_reports_02
				@Test(enabled = true, priority = 29, groups = { "Smoke" })
				public void MPI_1153_Pending_reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,subject,ccmail");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String subject = data.get(2);
					String ccmail = data.get(3) ;
					Map<String, String> details = Pramod.getPrevious12WorkingDates();

			        String monthName = details.get("monthName");
			        String year = details.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					
			    
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationPendingTab()) {

						logResults.createLogs("Y", "PASS", "Pending Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MailReportButton()) {

						logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailReportButton." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.SubjectTextField(subject)) {

						logResults.createLogs("Y", "PASS", "Mail Subject Inputted Successfully." + subject);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail Subject." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.CCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail CC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail CC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.BCCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail BCC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail BCC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FrequencySelection()) {

						logResults.createLogs("Y", "PASS", "Frequency Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Mail sending Frequency." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MailSendSaveButton()) {

						logResults.createLogs("Y", "PASS", "MailSendSaveButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailSendSaveButton." + meghpiattendancereport.getExceptionDesc());
					}
				}	
				
				// MPI_1155_Pending_reports_04
				@Test(enabled = true, priority = 30, groups = { "Smoke" })
				public void MPI_1155_Pending_reports_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> details = Pramod.getPrevious12WorkingDates();

			        String monthName = details.get("monthName");
			        String year = details.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);

			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationPendingTab()) {

						logResults.createLogs("Y", "PASS", "Pending Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}	
				
				// MPI_1156_Pending_reports_05
				@Test(enabled = true, priority = 31, groups = { "Smoke" })
				public void MPI_1156_Pending_reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> details = Pramod.getPrevious12WorkingDates();

			        String monthName = details.get("monthName");
			        String year = details.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegulirizationPendingTab()) {

						logResults.createLogs("Y", "PASS", "Pending Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DesignationDropDown(designationname)) {

						logResults.createLogs("Y", "PASS", "Designation Selected Successfully." + designationname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Designation Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}	
				
				// MPI_1157_Pending_reports_06
				@Test(enabled = true, priority = 32, groups = { "Smoke" })
				public void MPI_1157_Pending_reports_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> details = Pramod.getPrevious12WorkingDates();

			        String monthName = details.get("monthName");
			        String year = details.get("year");                // Year
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationPendingTab()) {

						logResults.createLogs("Y", "PASS", "Pending Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.EntityTypeDropDown(entityname)) {

						logResults.createLogs("Y", "PASS", "EntityType Selected Successfully." + entityname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EntityType Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}		
				
				// MPI_1158_Pending_reports_07
				@Test(enabled = true, priority = 33, groups = { "Smoke" })
				public void MPI_1158_Pending_reports_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> details = Pramod.getPrevious12WorkingDates();
			        String monthName = details.get("monthName");
			        String year = details.get("year");
			
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegulirizationPendingTab()) {

						logResults.createLogs("Y", "PASS", "Pending Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}	
				
				// MPI_1159_Pending_reports_08
				@Test(enabled = true, priority = 34, groups = { "Smoke" })
				public void MPI_1159_Pending_reports_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> details = Pramod.getPrevious12WorkingDates();

			        String Regulizationdate = details.get("month1WorkingDate");
			        String monthName = details.get("monthName");
			        String year = details.get("year");		

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationPendingTab()) {

						logResults.createLogs("Y", "PASS", "Pending Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + Regulizationdate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + Regulizationdate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}		
				
				// MPI_1162_Pending_reports_11
				@Test(enabled = true, priority = 35, groups = { "Smoke" })
				public void MPI_1162_Pending_reports_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, as a employee raise the regularization request and ensure request is displayed and status is pending.");

					ArrayList<String> data = initBase.loadExcelData("Regularization_Reports", currTC,
							"password,captcha,regularizationstatus");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					Map<String, String> details = Pramod.getPrevious12WorkingDates();

			        String Regulizationdate = details.get("month1WorkingDate");
			        String formattedPrevDate = Pramod.convertToUIFormat(Regulizationdate);
			        String monthName = details.get("monthName");
			        String year = details.get("year");
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationPendingTab()) {

						logResults.createLogs("Y", "PASS", "Pending Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + Regulizationdate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + Regulizationdate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportEmpIdCheckBox()) {

						logResults.createLogs("Y", "PASS", "Emp Id Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp ID Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(EmpID)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.ValidationEmpWithDateAndApproveStatus(regularizationstatus,formattedPrevDate,EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Emp Regulirization Apporved Record Validated Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Emp Approved Record." + meghpiregularizationReportsPage.getExceptionDesc());
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
