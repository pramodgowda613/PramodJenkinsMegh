package com.MeghPI.Attendance.Android.tests;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidAttendancePage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidLoginPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
public class MeghPIAndroidNightShiftAttendanceTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();


	
	
	@Parameters({ "device" })
	@BeforeMethod
	public void launchDriver(int device) { // String param1
		driver = loadDriver.getDriver(device);
		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

	@Parameters({ "device" })
	@BeforeClass
	void runOnce(int device) {
		logResults.createReport(1);
		logResults.setTestMethodErrorCount(0);
		
		
	}
	
	
	// MPI_630_Normal_Attendance_02
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_630_Normal_Attendance_02()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee performs only a single punch in a day.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}
	
	// MPI_632_Normal_Attendance_04
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_632_Normal_Attendance_04()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee has not completed minimum hours required for half day.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}
		
		
		
	// MPI_637_Normal_Attendance_09
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_637_Normal_Attendance_09()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status after the employee raises a regularization request for an absent day and it is approved.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}	
	// MPI_638_Normal_Attendance_10
	@Test(enabled = true, priority = 8, groups = { "Smoke" })
	public void MPI_638_Normal_Attendance_10()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee has applied for leave and it has been approved for that day.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		
		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}
	
	
	// MPI_641_Normal_Attendance_13
	@Test(enabled = true, priority = 9, groups = { "Smoke" })
	public void MPI_641_Normal_Attendance_13()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee is present on a week off.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}			
	// MPI_644_Normal_Attendance_16
	@Test(enabled = true, priority = 10, groups = { "Smoke" })
	public void MPI_644_Normal_Attendance_16()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee completes a half day.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}
	
	// MPI_646_Normal_Attendance_18
	@Test(enabled = true, priority = 11, groups = { "Smoke" })
	public void MPI_646_Normal_Attendance_18()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee has not completed the minimum hours required for half day on a week off.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}		
	
	// MPI_647_Normal_Attendance_19
	@Test(enabled = true, priority = 12, groups = { "Smoke" })
	public void MPI_647_Normal_Attendance_19()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee completes a half day on a week off.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}		
	
	// MPI_652_Normal_Attendance_24
	@Test(enabled = true, priority = 13, groups = { "Smoke" })
	public void MPI_652_Normal_Attendance_24()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee present on day.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}		
	
	// MPI_655_Normal_Attendance_27
	@Test(enabled = true, priority = 14, groups = { "Smoke" })
	public void MPI_655_Normal_Attendance_27()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee did a single punch in on a week off.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}	
	
	// MPI_440_Normal_Attendance_48
	@Test(enabled = true, priority = 15, groups = { "Smoke" })
	public void MPI_440_Normal_Attendance_48()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when 'First Clock In & Last Clock Out' is applied in the policy and the employee did last punch in at the end of shift.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);

		
		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}		
	
	// MPI_657_Normal_Attendance_47
	@Test(enabled = true, priority = 16, groups = { "Smoke" })
	public void MPI_657_Normal_Attendance_47()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when 'First Clock In & Last Clock Out' is applied in the policy and the employee did last punch out before end of shift.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}	
	
	// MPI_659_Normal_Attendance_46
	@Test(enabled = true, priority = 17, groups = { "Smoke" })
	public void MPI_659_Normal_Attendance_46()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when '17 hr' is set in the Maximum Duration from shift start time in the policy and the employee did last punch out before 17 hours (next day morning 1AM).");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}		
	
	// MPI_442_Normal_Attendance_45
	@Test(enabled = true, priority = 18, groups = { "Smoke" })
	public void MPI_442_Normal_Attendance_45()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when '17 hr' is set in the Maximum Duration from shift start time in the policy and the employee only completes half day and punches out after 17 hours.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		
		
		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}		
	
	// MPI_683_Normal_Attendance_29
	@Test(enabled = true, priority = 19, groups = { "Smoke" })
	public void MPI_683_Normal_Attendance_29()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check that when the user is present for more than the specified shift duration, but overtime is not configured and 'Multiple Shift Detection' is set to 'yes'.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}	
	
	// MPI_684_Normal_Attendance_30
	@Test(enabled = true, priority = 20, groups = { "Smoke" })
	public void MPI_684_Normal_Attendance_30()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when \"Ignore Punch Minutes\" is set to 0 and the employee punches in and out within 1 minute without any further punches on the same day.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		
		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}			
	
	// MPI_656_Normal_Attendance_44
	@Test(enabled = true, priority = 21, groups = { "Smoke" })
	public void MPI_656_Normal_Attendance_44()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when Shift Type is set to \"Nearest\" in the policy. As an employee, punch in at 7:00 AM and punch out at 3:00 PM while assigned to a General Shift.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}				
	
	// MPI_668_Normal_Attendance_43
	@Test(enabled = true, priority = 22, groups = { "Smoke" })
	public void MPI_668_Normal_Attendance_43()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the attendance status when Shift Type is set to \"Nearest\" and the employee punches in 2hr before of shift start time and punch out at shift end.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}	
	
	// MPI_685_Normal_Attendance_31
	@Test(enabled = true, priority = 23, groups = { "Smoke" })
	public void MPI_685_Normal_Attendance_31()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee performs multiple punches in a day and completes the shift as per the defined shift policy.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}		

	// MPI_686_Normal_Attendance_42
	@Test(enabled = true, priority = 24, groups = { "Smoke" })
	public void MPI_686_Normal_Attendance_42()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when Casual Leave is applied for a day marked as Absent.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateleave(date, empstatus)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}
	}
	
	// MPI_666_Normal_Attendance_41
	@Test(enabled = true, priority = 25, groups = { "Smoke" })
	public void MPI_666_Normal_Attendance_41()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when Casual Leave is applied for a day marked as Absent.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


	

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}
	}		
	
	// MPI_671_Normal_Attendance_38
	@Test(enabled = true, priority = 26, groups = { "Smoke" })
	public void MPI_671_Normal_Attendance_38()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee applies for first-half leave without working during the second half of the day.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}
	}		
	
	// MPI_672_Normal_Attendance_39
	@Test(enabled = true, priority = 27, groups = { "Smoke" })
	public void MPI_672_Normal_Attendance_39()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee completes half day and apply half day leave.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}
	}	
	
	// MPI_673_Normal_Attendance_40
	@Test(enabled = true, priority = 28, groups = { "Smoke" })
	public void MPI_673_Normal_Attendance_40()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee applies for the first half as a leave and completes the second half (half-day).");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}
	}	
	
	// MPI_678_Normal_Attendance_28
	@Test(enabled = true, priority = 29, groups = { "Smoke" })
	public void MPI_678_Normal_Attendance_28()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when Out punch is there but no IN punch but employee is present on that day.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		
		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}
	
	// MPI_697_Normal_Attendance_52
	@Test(enabled = true, priority = 30, groups = { "Smoke" })
	public void MPI_697_Normal_Attendance_52()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status When user arrives Late.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		
		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}	
	
	// MPI_698_Normal_Attendance_53
	@Test(enabled = true, priority = 31, groups = { "Smoke" })
	public void MPI_698_Normal_Attendance_53()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee arrives early.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}		
	
	// MPI_724_Normal_Attendance_54
	@Test(enabled = true, priority = 32, groups = { "Smoke" })
	public void MPI_724_Normal_Attendance_54()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when 'First Clock In & Last Clock Out' is applied in the policy and the employee performs the last punch-in 1 minute before the shift end time.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}	
	
	// MPI_727_Normal_Attendance_55
	@Test(enabled = true, priority = 33, groups = { "Smoke" })
	public void MPI_727_Normal_Attendance_55()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee applies a Half Day Regularization Request.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}		
	
	// MPI_728_Normal_Attendance_56
	@Test(enabled = true, priority = 34, groups = { "Smoke" })
	public void MPI_728_Normal_Attendance_56()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status for emp by applying half day regulization request and half day leave.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);


		

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}		
	
	// MPI_729_Normal_Attendance_57
	@Test(enabled = true, priority = 35, groups = { "Smoke" })
	public void MPI_729_Normal_Attendance_57()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee applies for second-half leave without working during the first half of the day.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}
	}	
	
	// MPI_730_Normal_Attendance_58
	@Test(enabled = true, priority = 36, groups = { "Smoke" })
	public void MPI_730_Normal_Attendance_58()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee applies for a half-day leave but performs only a single punch-in.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}
	}	
	
	// MPI_731_Normal_Attendance_59
	@Test(enabled = true, priority = 37, groups = { "Smoke" })
	public void MPI_731_Normal_Attendance_59()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Check the status when the employee works fewer hours and applies for a half-day leave.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}	
	
	// MPI_860_Normal_Attendance_69
	@Test(enabled = true, priority = 38, groups = { "Smoke" })
	public void MPI_860_Normal_Attendance_69()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the total  hours of an employee who worked half an hour more than the shift duration.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype,totalhours");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);
		String totalhours = data.get(5);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytypeAndTotalHr(date, empstatus, daytype, totalhours)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + totalhours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
		}	
	}
	
	// MPI_861_Normal_Attendance_70
	@Test(enabled = true, priority = 39, groups = { "Smoke" })
	public void MPI_861_Normal_Attendance_70()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the working hours of an employee who took a 2-hour break and did punch out at the end of the shift.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("NightShiftAttendance", currTC,
				"employeeuniqueid,empstatus,date,cmpcode,daytype,totalhours");
		
		
		String empid = data.get(0);
		String empstatus = data.get(1);
		String date =  data.get(2);
		String cmpcode = data.get(3);
		String daytype = data.get(4);
		String totalhours = data.get(5);


				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);

		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}  
		
		if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}	
		if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
		}		
		
		if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
			logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.validateAttendanceByDateondaytypeAndTotalHr(date, empstatus, daytype, totalhours)) {
			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + totalhours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
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
