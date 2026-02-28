package com.MeghPI.Attendance.Android.tests;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidAttendancePage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidLeavePage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidLoginPage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidOverTimePage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidRegularizationPage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidWeekOffPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.appium.java_client.HasSettings;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIAttendAndroidHolidayTest {
	
	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();


	private String cmpcode = "";
	private String Emailid = "";

	private String officename = "";
	  private String AdminFirstName ="";
	  private String entityname = "";
	
	@Parameters({ "device" })
	@BeforeMethod
	public void launchDriver(int device) { // String param1
		driver = loadDriver.getDriver(device);
		((HasSettings) driver).setSetting("enforceXPath1", true);

		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

	@Parameters({ "device" })
	@BeforeClass
	void runOnce(int device) {
		logResults.createReport(1);
		logResults.setTestMethodErrorCount(0);
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");
		
	}
	
	
	// MPI_1537_Android_Holiday_01
	@Test(enabled = true, priority = 01, groups = { "Smoke" })
	public void MPI_1537_Android_Holiday_01()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the default holiday policy all holiday are displaying.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("Android_Holiday", currTC,
				"password,empid");
		
		String password = data.get(0);
		String empid = data.get(1);
		
		
		

		MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
                     MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
                  MeghPIAttendAndroidLeavePage meghpiattendandroidleavepage = new MeghPIAttendAndroidLeavePage(driver);
		
                     
                     if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
             			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
             		}
             		
             		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
             			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked." + password);
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
             		if (meghpiattendandroidleavepage.clickHolidayCard()) {
             			logResults.createLogs("Y", "PASS", "Clicked On Holiday Button On Emp DashBoard.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Holiday Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
             		}
             		if (meghpiattendandroidleavepage.validateFestivalsPresent()) {
             			logResults.createLogs("Y", "PASS", "All Holidays Are Validated Of Default Holiday Policy.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Validating Default Holiday Policy Holidays ." + meghpiattendandroidleavepage.getExceptionDesc());
             		}       		
	}
	
	
	
	// MPI_1538_Android_Holiday_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_1538_Android_Holiday_02()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify that on a holiday date, the attendance status is displayed correctly as “Holiday.”");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
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
