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

public class MeghPIAttendAndroidLeaveTest {

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

	// MPI_1506_Android_Leave_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_1506_Android_Leave_01()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, apply a casual leave as an employee and ensure that the leave record is created with the Pending status.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
				"password,baseuri,loginendpoint,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,photo,leavereason,alltab,totaldays,leavestatus");


		  int i = 0;
		String password = data.get(i++);
		  String baseuri = data.get(i++);
		    String loginendpoint = data.get(i++);

	        String updateattendanceendpoint = data.get(i++);
	        
	        String createentityendpoint = data.get(i++);
	        String firstname = data.get(i++);
	        String lastname = data.get(i++);
	        String empid = data.get(i++);
	        String phoneno = data.get(i++);
	        String email = data.get(i++);
	        String doj = data.get(i++);
	        String sendEmailInviteStr = data.get(i++);  
	        boolean sendemailinvite = Boolean.parseBoolean(sendEmailInviteStr);
	        String enrollendpoint = data.get(i++);
	        String tablefieldendpoint = data.get(i++);
	        String photo = data.get(i++);
	        
	        String leavereason = data.get(i++);
	        String alltab = data.get(i++);
	        String totaldays = data.get(i++);
	        String leavestatus = data.get(i++);
	     
	        
	        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
	        

	        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

	        String month1WorkingDate  = dateDetails.get("month1WorkingDate");
	        String uiDate = Pramod.convertToUIFormat(month1WorkingDate) ;
	        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
		    
			
			MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
                     MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
                  MeghPIAttendAndroidLeavePage meghpiattendandroidleavepage = new MeghPIAttendAndroidLeavePage(driver);

                     
         

		      TableFieldIds ids = MeghPIAttenAPIPage.executeGetTableFieldIds(
			        baseuri, loginendpoint,
			        Emailid, password, cmpcode,
			        baseuri, tablefieldendpoint,
			        officename,   // company location name
			        AdminFirstName, // entity name
			        entityname    // entity type name
			);
		      System.out.println(ids.companyLocationId + "love");
		      System.out.println( ids.entityId);
		      System.out.println(ids.entityTypeId);
		      Integer companylocation = ids.companyLocationId;
		      Integer reportingto = ids.entityId;
		      Integer entitytypeid = ids.entityTypeId;
		
		      // Execute the combined method
		      Response enrollResponse = MeghPIAttenAPIPage.executeCreateEntityAndEnroll(
		    	        baseuri, loginendpoint,
		    	        Emailid, password, cmpcode,
		    	        baseuri, createentityendpoint,
		    	        baseuri, enrollendpoint,
		    	        companylocation, firstname, lastname,
		    	        reportingto, doj, phoneno,
		    	        email, empid, entitytypeid,
		    	        sendemailinvite,
		    	        deviceuniqueid, photo
		    	);

		    	if (enrollResponse.getStatusCode() == 200) {
		    	    String errorCode = enrollResponse.jsonPath().getString("ErrorCode");
		    	    String errorDesc = enrollResponse.jsonPath().getString("ErrorDescription");

		    	    if ("0".equals(errorCode) && "Success".equalsIgnoreCase(errorDesc)) {
		    	        logResults.createLogs("N", "PASS", "✅ Enrollment successful for Employee ID: " + empid);
		    	    } else {
		    	        logResults.createLogs("N", "FAIL", "❌ Enrollment failed for Employee ID: " + empid +
		    	                " | ErrorCode: " + errorCode + " | ErrorDescription: " + errorDesc);
		    	        return; // stop execution if API error
		    	    }
		    	} else {
		    	    logResults.createLogs("N", "FAIL", "❌ Enrollment API failed for Employee ID: " + empid +
		    	            " | HTTP Status: " + enrollResponse.getStatusCode() +
		    	            " | Response: " + enrollResponse.asString());
		    	    return;
		    	}

	  
	 // Trigger attendance update first
	    Response updateResp = apiPage.executeUpdateAttendance(
	            baseuri, loginendpoint,
	            Emailid, password, cmpcode,
	            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
	            empid, firstDayOfMonth + "T00:00:00.000Z"
	    );

	    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
	        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + empid);
	    } else {
	        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
	    }  


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
		if (meghpiattendandroidleavepage.LeaveButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Leave Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
		}
		
		if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
			logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
		}
		if (meghpiattendandroidleavepage.QuickActionLeaveOption()) {
			logResults.createLogs("Y", "PASS", "Clicked On Apply Leave Button From Quick Action.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Apply Leave Button From Quick Action." + meghpiattendandroidleavepage.getExceptionDesc());
		}
	
		if (meghpiandroidattendancepage.LeaveApplyConfirmDisplayed()) {
			logResults.createLogs("Y", "PASS", "Leave Apply Page Loaded Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Leave Apply Button." + meghpiandroidattendancepage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.LeaveTypeDropDown()) {
			logResults.createLogs("Y", "PASS", "Leave Type DropDown Clicked Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Type DropDown." + meghpiandroidattendancepage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.SickLeaveTypeSelected()) {
			logResults.createLogs("Y", "PASS", "Sick Leave Type Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Sick Leave Type." + meghpiandroidattendancepage.getExceptionDesc());
		}
		
		
		if (meghpiandroidattendancepage.RegulirizationFromDate()) {
			logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Date.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
		}
		
		if (meghpiandroidattendancepage.clickDateInCalendar(uiDate)) {
			logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day." + uiDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
		}
		
		if (meghpiandroidattendancepage.RegulirizationFromTimeTextField()) {
			logResults.createLogs("Y", "PASS", "Clicked On Leave Apply TO Date Widget.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Apply To Date Widget." + meghpiandroidattendancepage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.selectToDate(uiDate)) {
			logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day For Leave." + uiDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.DurationOne()) {
			logResults.createLogs("Y", "PASS", "Leave Duration Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Duration 2nd One." + meghpiandroidattendancepage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.FullDaySelected()) {
			logResults.createLogs("Y", "PASS", "Full Day Leave Duration Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Full Day Leave Duration." + meghpiandroidattendancepage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.RegularizationTypeClicked()) {
			logResults.createLogs("Y", "PASS", "Leave Duration 2nd One Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Duration 2nd One." + meghpiandroidattendancepage.getExceptionDesc());
		}
		if (meghpiandroidattendancepage.FullDaySelected()) {
			logResults.createLogs("Y", "PASS", "Full Day Leave Duration Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Full Day Leave Duration." + meghpiandroidattendancepage.getExceptionDesc());
		}
		
		if (meghpiandroidattendancepage.RegulirizationReasonTextField(leavereason)) {
			logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Reason." + meghpiandroidattendancepage.getExceptionDesc());
		}
		
		if (meghpiandroidattendancepage.LeaveApplyConfirmButton()) {
			logResults.createLogs("Y", "PASS", "Leave Apply Confirm Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Apply Confirm Button." + meghpiandroidattendancepage.getExceptionDesc());
		} 
		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
		}
		if (meghpiattendandroidleavepage.validateLeaveCard(uiDate, uiDate, totaldays, leavestatus )) {
			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." + uiDate + " "+ totaldays + " " + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
		}
	}
	
	// MPI_1507_Android_Leave_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_1507_Android_Leave_02()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, approve the employee’s leave request and ensure that the leave is approved and the status is updated to Approved.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
				"password,empid,firstname,alltab,leavestatus,pendingtab,leavename,totaldays");
		
		String password = data.get(0);
		String empid = data.get(1);
		String firstname = data.get(2);
		String alltab = data.get(3);
		String leavestatus = data.get(4);
		String pendingtab = data.get(5);
		String leavename = data.get(6);
		String totaldays = data.get(7);
		
		 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

	        String month2WorkingDate  = dateDetails.get("month2WorkingDate");
	        String uiDate = Pramod.convertToUIFormat(month2WorkingDate) ;
	

		MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
          MeghPIAttendAndroidOverTimePage meghpiandroidovertimepage = new MeghPIAttendAndroidOverTimePage(driver);
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
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghpiattendandroidleavepage.LeaveButtonOnAdminDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Leave Module Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Module Button." + meghpiattendandroidleavepage.getExceptionDesc());
		}

		if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Filter Button In Leave Tab." + empid);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button On Leave Tab." + meghpiandroidovertimepage.getExceptionDesc());
		}
		if (meghpiattendandroidleavepage.clickLeaveTab(pendingtab)) {
			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count." + pendingtab);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
		}
		
		
		if (meghpiattendandroidleavepage.approveLeaveCard(empid, leavename, uiDate, uiDate )) {
			logResults.createLogs("Y", "PASS", "Verified  Leave Request On Admin Account." + empid + " " + leavename + " " +  uiDate + " " +  uiDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Verifying Leave Request On Admin Account." + meghpiattendandroidleavepage.getExceptionDesc());
		}

		if (meghpiandroidovertimepage.OTApproveButton()) {
			logResults.createLogs("Y", "PASS", "Leave Approve Confirm Button Clicked On Admin Account." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Approve Confirm Button On Admin Account." + meghpiandroidovertimepage.getExceptionDesc());
		}
		
		

		if (meghpiandroidattendancepage.BackButton()) {
			logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
		}
		if (meghPIAttendAndroidLoginPage.ManageButton()) {
			logResults.createLogs("Y", "PASS", "Manage Profile Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Manage Profile Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghPIAttendAndroidLoginPage.LogoutButton()) {
			logResults.createLogs("Y", "PASS", "LogOut Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LogOut Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		if (meghPIAttendAndroidLoginPage.LogoutConfirmButton()) {
			logResults.createLogs("Y", "PASS", "Logout Confirm Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Logout Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		} 
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
		if (meghpiattendandroidleavepage.LeaveButtonOnEmpDashBoard()) {
			logResults.createLogs("Y", "PASS", "Clicked On Leave Button On Emp DashBoard.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
		}
		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
		}
		if (meghpiattendandroidleavepage.validateLeaveCard(uiDate, uiDate, totaldays, leavestatus )) {
			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." + uiDate + " "+ totaldays + " " + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
		}	
	}
	
	// MPI_1508_Android_Leave_03
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_1508_Android_Leave_03()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, apply a leave for 2 days and ensure that the total day count, as well as the From Date and To Date, are displayed correctly.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
				"password,empid,alltab,leavereason,leavestatus,totaldays");
		
		String password = data.get(0);
		String empid = data.get(1);
		String alltab = data.get(2);
		
		String leavereason = data.get(3);
		String leavestatus = data.get(4);
		String totaldays = data.get(5);
		
		 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

	        String month3WorkingDate  = dateDetails.get("month3WorkingDate");
	        String thirdUI = Pramod.convertToUIFormat(month3WorkingDate) ;
	        String month4WorkingDate  = dateDetails.get("month4WorkingDate");
	        String fourthUI = Pramod.convertToUIFormat(month4WorkingDate) ;
	        
		

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
             		if (meghpiattendandroidleavepage.LeaveButtonOnEmpDashBoard()) {
             			logResults.createLogs("Y", "PASS", "Clicked On Leave Button On Emp DashBoard.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Leave Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
             		}
             		
             		if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
             			logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
             		}
             		if (meghpiattendandroidleavepage.QuickActionLeaveOption()) {
             			logResults.createLogs("Y", "PASS", "Clicked On Apply Leave Button From Quick Action.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Apply Leave Button From Quick Action." + meghpiattendandroidleavepage.getExceptionDesc());
             		}
             	
             		if (meghpiandroidattendancepage.LeaveApplyConfirmDisplayed()) {
             			logResults.createLogs("Y", "PASS", "Leave Apply Page Loaded Successfully." );
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Displaying Leave Apply Button." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		if (meghpiandroidattendancepage.LeaveTypeDropDown()) {
             			logResults.createLogs("Y", "PASS", "Leave Type DropDown Clicked Successfully." );
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Leave Type DropDown." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		if (meghpiandroidattendancepage.SickLeaveTypeSelected()) {
             			logResults.createLogs("Y", "PASS", "Sick Leave Type Selected Successfully." );
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Selecting Sick Leave Type." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		
             		
             		if (meghpiandroidattendancepage.RegulirizationFromDate()) {
             			logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Date.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		
             		if (meghpiandroidattendancepage.clickDateInCalendar(thirdUI)) {
             			logResults.createLogs("Y", "PASS", "Clicked On 3rd Working Day." + thirdUI);
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On 3rd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
             			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		
             		if (meghpiandroidattendancepage.RegulirizationFromTimeTextField()) {
             			logResults.createLogs("Y", "PASS", "Clicked On Leave Apply TO Date Widget.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Leave Apply To Date Widget." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		if (meghpiandroidattendancepage.selectToDate(fourthUI)) {
             			logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day For Leave." + fourthUI);
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
             			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		if (meghpiandroidattendancepage.DurationOne()) {
             			logResults.createLogs("Y", "PASS", "Leave Duration Clicked Successfully.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Leave Duration 2nd One." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		if (meghpiandroidattendancepage.FullDaySelected()) {
             			logResults.createLogs("Y", "PASS", "Full Day Leave Duration Selected Successfully.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Selecting Full Day Leave Duration." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		if (meghpiandroidattendancepage.RegularizationTypeClicked()) {
             			logResults.createLogs("Y", "PASS", "Leave Duration 2nd One Clicked Successfully.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Leave Duration 2nd One." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		if (meghpiandroidattendancepage.FullDaySelected()) {
             			logResults.createLogs("Y", "PASS", "Full Day Leave Duration Selected Successfully.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Selecting Full Day Leave Duration." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		
             		if (meghpiandroidattendancepage.RegulirizationReasonTextField(leavereason)) {
             			logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Inputting Leave Reason." + meghpiandroidattendancepage.getExceptionDesc());
             		}
             		
             		if (meghpiandroidattendancepage.LeaveApplyConfirmButton()) {
             			logResults.createLogs("Y", "PASS", "Leave Apply Confirm Button Clicked Successfully.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Leave Apply Confirm Button." + meghpiandroidattendancepage.getExceptionDesc());
             		} 
             		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
             			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count.");
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
             		}
             		if (meghpiattendandroidleavepage.validateLeaveCard(thirdUI, fourthUI, totaldays, leavestatus )) {
             			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." + thirdUI + "to "+ fourthUI + " totaldays " + totaldays );
             		} else {
             			logResults.createLogs("Y", "FAIL",
             					"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
             		}
	}
	
	
	// MPI_1509_Android_Leave_04
		@Test(enabled = true, priority = 4, groups = { "Smoke" })
		public void MPI_1509_Android_Leave_04()   {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, reject the employee's leave request and ensure the record is displayed with the Rejected status.");

			  // Load Excel data
			ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
					"password,empid,firstname,alltab,leavestatus,pendingtab,leavename,totaldays,action");
			
			String password = data.get(0);
			String empid = data.get(1);
			String firstname = data.get(2);
			String alltab = data.get(3);
			String leavestatus = data.get(4);
			String pendingtab = data.get(5);
			String leavename = data.get(6);
			String totaldays = data.get(7);
			String action = data.get(8);
			
			Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

	        String month3WorkingDate  = dateDetails.get("month3WorkingDate");
	        String thirdUI = Pramod.convertToUIFormat(month3WorkingDate) ;
	        String month4WorkingDate  = dateDetails.get("month4WorkingDate");
	        String fourthUI = Pramod.convertToUIFormat(month4WorkingDate) ;
		

			MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
	          MeghPIAttendAndroidOverTimePage meghpiandroidovertimepage = new MeghPIAttendAndroidOverTimePage(driver);
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
				logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			}


			if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
				logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			}

			if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
				logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			}

			if (meghPIAttendAndroidLoginPage.LoginButton()) {
				logResults.createLogs("Y", "PASS", "Clicked On Login Button." + firstname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			}
			if (meghpiattendandroidleavepage.LeaveButtonOnAdminDashBoard()) {
				logResults.createLogs("Y", "PASS", "Clicked On Leave Module Button.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Leave Module Button." + meghpiattendandroidleavepage.getExceptionDesc());
			}

			if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
				logResults.createLogs("Y", "PASS", "Clicked On Filter Button In Leave Tab." + empid);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button On Leave Tab." + meghpiandroidovertimepage.getExceptionDesc());
			}
			if (meghpiattendandroidleavepage.clickLeaveTab(pendingtab)) {
				logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count." + pendingtab);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
			}
			
			
			if (meghpiattendandroidleavepage.handleLeaveCard(empid, leavename, thirdUI, fourthUI, action )) {
				logResults.createLogs("Y", "PASS", "Verified  Leave Request On Admin Account And Clicked On Reject Button." + empid + " " + leavename + " " +  thirdUI + " " +  fourthUI);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Verifying Leave Request On Admin Account And Clicked On Reject Button." + meghpiattendandroidleavepage.getExceptionDesc());
			}

			if (meghpiandroidovertimepage.RejectConfirmButton()) {
				logResults.createLogs("Y", "PASS", "Leave Reject Button Clicked On Admin Account." );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Leave Reject Confirm Button On Admin Account." + meghpiandroidovertimepage.getExceptionDesc());
			}
			if (meghpiandroidattendancepage.BackButton()) {
				logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
			}
			if (meghPIAttendAndroidLoginPage.ManageButton()) {
				logResults.createLogs("Y", "PASS", "Manage Profile Button Is Clicked.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Manage Profile Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			}
			if (meghPIAttendAndroidLoginPage.LogoutButton()) {
				logResults.createLogs("Y", "PASS", "LogOut Button Is Clicked.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On LogOut Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			}
			if (meghPIAttendAndroidLoginPage.LogoutConfirmButton()) {
				logResults.createLogs("Y", "PASS", "Logout Confirm Button Is Clicked.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Logout Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			} 
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
			if (meghpiattendandroidleavepage.LeaveButtonOnEmpDashBoard()) {
				logResults.createLogs("Y", "PASS", "Clicked On Leave Button On Emp DashBoard.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Leave Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
			}
			if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
				logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
			}
			if (meghpiattendandroidleavepage.validateLeaveCard(thirdUI, fourthUI, totaldays, leavestatus )) {
				logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." + thirdUI + " to "+ fourthUI + " totaldays " +  totaldays + " status is " + leavestatus);
					
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
			}	
		}
	
		// MPI_1510_Android_Leave_05
		@Test(enabled = true, priority = 5, groups = { "Smoke" })
		public void MPI_1510_Android_Leave_05()   {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, raise any leave request for the same date that the admin previously rejected, and ensure the new leave request is created with the Pending status.");

			  // Load Excel data
			ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
					"password,empid,alltab,leavereason,leavestatus,totaldays");
			
			String password = data.get(0);
			String empid = data.get(1);
			String alltab = data.get(2);
			
			String leavereason = data.get(3);
			String leavestatus = data.get(4);
			String totaldays = data.get(5);
			
			Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

	        String month3WorkingDate  = dateDetails.get("month3WorkingDate");
	        String thirdUI = Pramod.convertToUIFormat(month3WorkingDate) ;
	        String month4WorkingDate  = dateDetails.get("month4WorkingDate");
	        String fourthUI = Pramod.convertToUIFormat(month4WorkingDate) ;

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
	             		if (meghpiattendandroidleavepage.LeaveButtonOnEmpDashBoard()) {
	             			logResults.createLogs("Y", "PASS", "Clicked On Leave Button On Emp DashBoard.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On Leave Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
	             		}
	             		
	             		if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
	             			logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
	             		}
	             		if (meghpiattendandroidleavepage.QuickActionLeaveOption()) {
	             			logResults.createLogs("Y", "PASS", "Clicked On Apply Leave Button From Quick Action.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On Apply Leave Button From Quick Action." + meghpiattendandroidleavepage.getExceptionDesc());
	             		}
	             	
	             		if (meghpiandroidattendancepage.LeaveApplyConfirmDisplayed()) {
	             			logResults.createLogs("Y", "PASS", "Leave Apply Page Loaded Successfully." );
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Displaying Leave Apply Button." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		if (meghpiandroidattendancepage.LeaveTypeDropDown()) {
	             			logResults.createLogs("Y", "PASS", "Leave Type DropDown Clicked Successfully." );
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On Leave Type DropDown." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		if (meghpiandroidattendancepage.SickLeaveTypeSelected()) {
	             			logResults.createLogs("Y", "PASS", "Sick Leave Type Selected Successfully." );
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Selecting Sick Leave Type." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		
	             		
	             		if (meghpiandroidattendancepage.RegulirizationFromDate()) {
	             			logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Date.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		
	             		if (meghpiandroidattendancepage.clickDateInCalendar(thirdUI)) {
	             			logResults.createLogs("Y", "PASS", "Clicked On 3rd Working Day." + thirdUI);
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On 3rd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
	             			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		
	             		if (meghpiandroidattendancepage.RegulirizationFromTimeTextField()) {
	             			logResults.createLogs("Y", "PASS", "Clicked On Leave Apply TO Date Widget.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On Leave Apply To Date Widget." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		if (meghpiandroidattendancepage.selectToDate(fourthUI)) {
	             			logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day For Leave." + fourthUI);
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
	             			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		if (meghpiandroidattendancepage.DurationOne()) {
	             			logResults.createLogs("Y", "PASS", "Leave Duration Clicked Successfully.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On Leave Duration 2nd One." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		if (meghpiandroidattendancepage.FullDaySelected()) {
	             			logResults.createLogs("Y", "PASS", "Full Day Leave Duration Selected Successfully.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Selecting Full Day Leave Duration." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		if (meghpiandroidattendancepage.RegularizationTypeClicked()) {
	             			logResults.createLogs("Y", "PASS", "Leave Duration 2nd One Clicked Successfully.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On Leave Duration 2nd One." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		if (meghpiandroidattendancepage.FullDaySelected()) {
	             			logResults.createLogs("Y", "PASS", "Full Day Leave Duration Selected Successfully.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Selecting Full Day Leave Duration." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		
	             		if (meghpiandroidattendancepage.RegulirizationReasonTextField(leavereason)) {
	             			logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Inputting Leave Reason." + meghpiandroidattendancepage.getExceptionDesc());
	             		}
	             		
	             		if (meghpiandroidattendancepage.LeaveApplyConfirmButton()) {
	             			logResults.createLogs("Y", "PASS", "Leave Apply Confirm Button Clicked Successfully.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On Leave Apply Confirm Button." + meghpiandroidattendancepage.getExceptionDesc());
	             		} 
	             		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
	             			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count.");
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
	             		}
	             		if (meghpiattendandroidleavepage.validateLeaveCard(thirdUI, fourthUI, totaldays, leavestatus )) {
	             			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." + thirdUI + "to "+ fourthUI + " totaldays " + totaldays );
	             		} else {
	             			logResults.createLogs("Y", "FAIL",
	             					"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
	             		}
		}
	
	
		// MPI_1511_Android_Leave_06
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_1511_Android_Leave_06()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To Verify that when a leave request is raised and then revoked, the leave entry for that specific date is displayed in the system with the status “Revoked”.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
							"password,empid,pendingtab,alltab,leavestatus,totaldays,leavename,regulizationstatus");
					
					String password = data.get(0);
					String empid = data.get(1);
					String pendingtab = data.get(2);
					
					String alltab = data.get(3);
					String leavestatus = data.get(4);
					String totaldays = data.get(5);
					String leavename = data.get(5);
					String regulizationstatus = data.get(6);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

			        String month3WorkingDate  = dateDetails.get("month3WorkingDate");
			        String thirdUI = Pramod.convertToUIFormat(month3WorkingDate) ;
			        String month4WorkingDate  = dateDetails.get("month4WorkingDate");
			        String fourthUI = Pramod.convertToUIFormat(month4WorkingDate) ;

					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
			                  MeghPIAttendAndroidLeavePage meghpiattendandroidleavepage = new MeghPIAttendAndroidLeavePage(driver);
			                     MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);

			                     
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
			             		if (meghpiattendandroidleavepage.LeaveButtonOnEmpDashBoard()) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Leave Button On Emp DashBoard.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
			             		} 
			             		if (meghpiattendandroidleavepage.clickLeaveTab(pendingtab)) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count." + pendingtab);
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
			             		}
			             		if (meghpiattendandroidleavepage.validateLeaveCardAndClickOnRevoke(thirdUI, fourthUI, totaldays, leavestatus )) {
			             			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully And Clicked On Revoke Button." + thirdUI + "to "+ fourthUI + " totaldays " + totaldays );
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Validating Applied Leave And While Clicking On Revoke Button." + meghpiattendandroidleavepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.RegularizationConfirmButton()) {
									logResults.createLogs("Y", "PASS", "Clicked On Leave Revoke Confirm Button.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Leave Request Revoke Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
								}  
			             		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Revoked Summary Count." + pendingtab);
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Tab Revoked Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
			             		}
			             		if (meghpiattendandroidleavepage.validateLeaveCardInRevokedcard(thirdUI, fourthUI, totaldays, regulizationstatus)) {
			             			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully For Revoke Status." + thirdUI + "to "+ fourthUI + " totaldays " + totaldays + " "  + regulizationstatus );
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Validating Applied Leave And Revoke Status." + meghpiattendandroidleavepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.BackButton()) {
			            			logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
			            		}
			            		if (meghPIAttendAndroidLoginPage.ManageButton()) {
			            			logResults.createLogs("Y", "PASS", "Manage Profile Button Is Clicked.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Clicking On Manage Profile Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			            		}
			            		if (meghPIAttendAndroidLoginPage.LogoutButton()) {
			            			logResults.createLogs("Y", "PASS", "LogOut Button Is Clicked.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Clicking On LogOut Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			            		}
			            		if (meghPIAttendAndroidLoginPage.LogoutConfirmButton()) {
			            			logResults.createLogs("Y", "PASS", "Logout Confirm Button Is Clicked.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Clicking On Logout Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			            		} 
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


			            		if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
			            			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
			            		} else {
			            			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			            		}

			            		if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
			            			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
			            		} else {
			            			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			            		}

			            		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			            			logResults.createLogs("Y", "PASS", "Clicked On Login Button." );
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			            		}
			            		if (meghpiattendandroidleavepage.LeaveButtonOnAdminDashBoard()) {
			            			logResults.createLogs("Y", "PASS", "Clicked On Leave Module Button.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Clicking On Leave Module Button." + meghpiattendandroidleavepage.getExceptionDesc());
			            		}

			            		if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
			            			logResults.createLogs("Y", "PASS", "Clicked On Filter Button In Leave Tab." + empid);
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Clicking On Filter Button On Leave Tab." + meghpiandroidregularizationpage.getExceptionDesc());
			            		}
			            		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
			            			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Revoked Summary Count." + alltab);
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Clicking On Leave Tab Revoked Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
			            		}
			            		
			            		if (meghpiattendandroidleavepage.validateLeaveCardForRevoked(empid, leavename, thirdUI, fourthUI, regulizationstatus )) {
			            			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." +empid + " From Date " +  thirdUI + "To "+ fourthUI + " " +  totaldays + " " + regulizationstatus);
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
			            		}
			            		
				}
	
				// MPI_1512_Android_Leave_07
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_1512_Android_Leave_07()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the \"All\", \"Pending\", \"Approved\", \"revoked\",  and \"Rejected\" summary counts on the admin account.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
							"password,empid,pending,pendingcount,approved,approvedcount,revoked,revokedcount,rejected,rejectedcount");
					
					String password = data.get(0);
					String empid = data.get(1);
					String pending = data.get(2);		
					String pendingcount = data.get(3);
					String approved = data.get(4);
					String approvedcount = data.get(5);
					String revoked = data.get(6);
					String revokedcount = data.get(7);
					String rejected = data.get(8);
					String rejectedcount = data.get(9);
					
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					   MeghPIAttendAndroidWeekOffPage meghpiandroidweekoffpage = new MeghPIAttendAndroidWeekOffPage(driver);
	                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
	                             MeghPIAttendAndroidLeavePage meghpiattendandroidleavepage = new MeghPIAttendAndroidLeavePage(driver);

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


				            		if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
				            			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
				            		} else {
				            			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}

				            		if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
				            			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
				            		} else {
				            			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}

				            		if (meghPIAttendAndroidLoginPage.LoginButton()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Login Button." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.LeaveButtonOnAdminDashBoard()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Module Button.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Module Button." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}

				            		if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Filter Button In Leave Tab." + empid);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Filter Button On Leave Tab." + meghpiandroidregularizationpage.getExceptionDesc());
				            		}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(pending, pendingcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Pending Count Validated Successfully." + pending + " " + pendingcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Pending Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(approved, approvedcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Approved Count Validated Successfully." + approved + " " + approvedcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Approved Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(rejected, rejectedcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Rejected Count Validated Successfully." + rejected + " " + rejectedcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Rejected Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(revoked, revokedcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Revoked Count Validated Successfully." + revoked + " " + revokedcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Revoked Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        		
	        				}
				
				// MPI_1513_Android_Leave_08
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_1513_Android_Leave_08()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the \"All\", \"Pending\", \"Approved\", \"revoked\", and \"Rejected\" summary counts on the Employee account.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
							"password,empid,pending,pendingcount,approved,approvedcount,revoked,revokedcount,rejected,rejectedcount");
					
					String password = data.get(0);
					String empid = data.get(1);
					String pending = data.get(2);		
					String pendingcount = data.get(3);
					String approved = data.get(4);
					String approvedcount = data.get(5);
					String revoked = data.get(6);
					String revokedcount = data.get(7);
					String rejected = data.get(8);
					String rejectedcount = data.get(9);
					
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					   MeghPIAttendAndroidWeekOffPage meghpiandroidweekoffpage = new MeghPIAttendAndroidWeekOffPage(driver);
	                             MeghPIAttendAndroidLeavePage meghpiattendandroidleavepage = new MeghPIAttendAndroidLeavePage(driver);
	                     		MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);

	                             
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
			             		if (meghpiattendandroidleavepage.LeaveButtonOnEmpDashBoard()) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Leave Button On Emp DashBoard.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
			             		}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(pending, pendingcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Pending Count Validated Successfully." + pending + " " + pendingcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Pending Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(approved, approvedcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Approved Count Validated Successfully." + approved + " " + approvedcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Approved Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(rejected, rejectedcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Rejected Count Validated Successfully." + rejected + " " + rejectedcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Rejected Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(revoked, revokedcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Revoked Count Validated Successfully." + revoked + " " + revokedcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Revoked Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        		
	        				}
				
				
				// MPI_1515_Android_Leave_09
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_1515_Android_Leave_09()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that when an admin applies leave on behalf of an employee, the leave is auto-approved and the employee’s attendance status for the applied leave date is displayed as “On Leave”.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
							"password,empid,leavereason,totaldays,alltab,leavestatus,firstname");
					
					String password = data.get(0);
					String empid = data.get(1);
					String leavereason = data.get(2);
					String totaldays = data.get(3);
					String alltab = data.get(4);
					String leavestatus = data.get(5);
					String firstname = data.get(6);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

			        String month3WorkingDate  = dateDetails.get("month3WorkingDate");
			        String thirdUI = Pramod.convertToUIFormat(month3WorkingDate) ;
			      
					
					MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
	                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
	                             MeghPIAttendAndroidLeavePage meghpiattendandroidleavepage = new MeghPIAttendAndroidLeavePage(driver);

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


				            		if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
				            			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
				            		} else {
				            			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}

				            		if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
				            			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
				            		} else {
				            			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}

				            		if (meghPIAttendAndroidLoginPage.LoginButton()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Login Button." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.LeaveButtonOnAdminDashBoard()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Module Button.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Module Button." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.QuickActionLeaveOption()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Apply Leave Button From Quick Action.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Apply Leave Button From Quick Action." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            	
				            		if (meghpiandroidattendancepage.LeaveApplyConfirmDisplayed()) {
				            			logResults.createLogs("Y", "PASS", "Leave Apply Page Loaded Successfully." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Displaying Leave Apply Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidregularizationpage.RegulirizationRequestForOthersButton()) {
										logResults.createLogs("Y", "PASS", " Apply Leave For OThers Button.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Apply Leave For Others." + meghpiandroidregularizationpage.getExceptionDesc());
									}
									if (meghpiandroidregularizationpage.inputRegularizationEmpName(firstname)) {
										logResults.createLogs("Y", "PASS", " To Apply Leave For OThers Emp Name Inputted." + firstname);
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Inputting Emp Name While Applying Leave ." + meghpiandroidregularizationpage.getExceptionDesc());
									}
									if (meghpiattendandroidleavepage.EmpSelectedForLeaveForOthers()) {
										logResults.createLogs("Y", "PASS", "To Apply Leave For OThers Emp Name Selected.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Selecting Emp Name." + meghpiattendandroidleavepage.getExceptionDesc());
									}
									if (meghpiattendandroidleavepage.LeaveTypeDropDownOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Leave Type DropDown Clicked Successfully." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Type DropDown." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.SickLeaveTypeSelected()) {
				            			logResults.createLogs("Y", "PASS", "Sick Leave Type Selected Successfully." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Selecting Sick Leave Type." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		
				            		
				            		if (meghpiattendandroidleavepage.LeaveFromDateOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave From Date.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave From Date." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		
				            		if (meghpiandroidattendancepage.clickDateInCalendar(thirdUI)) {
				            			logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day." + thirdUI);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
				            			logResults.createLogs("Y", "PASS", "Leave Apply Date Selected Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		
				            		if (meghpiattendandroidleavepage.LeaveToDateOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Apply TO Date Widget.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Apply To Date Widget." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.selectToDate(thirdUI)) {
				            			logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day For Leave." + thirdUI);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
				            			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.LeaveDurationOneOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Leave Duration Clicked Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Duration 2nd One." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.FullDaySelected()) {
				            			logResults.createLogs("Y", "PASS", "Full Day Leave Duration Selected Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Selecting Full Day Leave Duration." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.LeaveDurationTwoOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Leave Duration 2nd One Clicked Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Duration 2nd One." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.FullDaySelected()) {
				            			logResults.createLogs("Y", "PASS", "Full Day Leave Duration Selected Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Selecting Full Day Leave Duration." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		
				            		if (meghpiandroidattendancepage.RegulirizationReasonTextField(leavereason)) {
				            			logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Inputting Leave Reason." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		
				            		
				            		if (meghpiandroidattendancepage.LeaveApplyConfirmButton()) {
				            			logResults.createLogs("Y", "PASS", "Leave Apply Confirm Button Clicked Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Apply Confirm Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		} 
				            		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.validateLeaveCard(thirdUI, thirdUI, totaldays, leavestatus )) {
				            			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." + thirdUI + " "+ totaldays + " " + leavestatus);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.BackButton()) {
				            			logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghPIAttendAndroidLoginPage.ManageButton()) {
				            			logResults.createLogs("Y", "PASS", "Manage Profile Button Is Clicked.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Manage Profile Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}
				            		if (meghPIAttendAndroidLoginPage.LogoutButton()) {
				            			logResults.createLogs("Y", "PASS", "LogOut Button Is Clicked.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On LogOut Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}
				            		if (meghPIAttendAndroidLoginPage.LogoutConfirmButton()) {
				            			logResults.createLogs("Y", "PASS", "Logout Confirm Button Is Clicked.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Logout Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}  
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
				            		if (meghpiattendandroidleavepage.LeaveButtonOnEmpDashBoard()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Button On Emp DashBoard.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.validateLeaveCard(thirdUI, thirdUI, totaldays, leavestatus )) {
				            			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." + thirdUI + " "+ totaldays + " " + leavestatus);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}	
				
				
				}
				
				// MPI_1516_Android_Leave_10
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_1516_Android_Leave_10()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that applying a half-day leave by selecting First Half as duration and ensure leave is applied .");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
							"password,empid,leavereason,totaldays,alltab,leavestatus,firstname");
					
					String password = data.get(0);
					String empid = data.get(1);
					String leavereason = data.get(2);
					String totaldays = data.get(3);
					String alltab = data.get(4);
					String leavestatus = data.get(5);
					String firstname = data.get(6);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

			       
			        String month4WorkingDate  = dateDetails.get("month4WorkingDate");
			        String fourthUI = Pramod.convertToUIFormat(month4WorkingDate) ;
					
					MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
	                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
	                             MeghPIAttendAndroidLeavePage meghpiattendandroidleavepage = new MeghPIAttendAndroidLeavePage(driver);

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


				            		if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
				            			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
				            		} else {
				            			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}

				            		if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
				            			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
				            		} else {
				            			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}

				            		if (meghPIAttendAndroidLoginPage.LoginButton()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Login Button." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.LeaveButtonOnAdminDashBoard()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Module Button.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Module Button." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.QuickActionLeaveOption()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Apply Leave Button From Quick Action.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Apply Leave Button From Quick Action." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            	
				            		if (meghpiandroidattendancepage.LeaveApplyConfirmDisplayed()) {
				            			logResults.createLogs("Y", "PASS", "Leave Apply Page Loaded Successfully." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Displaying Leave Apply Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidregularizationpage.RegulirizationRequestForOthersButton()) {
										logResults.createLogs("Y", "PASS", " Apply Leave For OThers Button.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Apply Leave For Others." + meghpiandroidregularizationpage.getExceptionDesc());
									}
									if (meghpiandroidregularizationpage.inputRegularizationEmpName(firstname)) {
										logResults.createLogs("Y", "PASS", " To Apply Leave For OThers Emp Name Inputted." + firstname);
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Inputting Emp Name While Applying Leave ." + meghpiandroidregularizationpage.getExceptionDesc());
									}
									if (meghpiattendandroidleavepage.EmpSelectedForLeaveForOthers()) {
										logResults.createLogs("Y", "PASS", "To Apply Leave For OThers Emp Name Selected.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Selecting Emp Name." + meghpiattendandroidleavepage.getExceptionDesc());
									}
									if (meghpiattendandroidleavepage.LeaveTypeDropDownOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Leave Type DropDown Clicked Successfully." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Type DropDown." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.CasualLeaveTypeSelected()) {
				            			logResults.createLogs("Y", "PASS", "Casual Leave Type Selected Successfully." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Selecting Casual Leave Type." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		
				            		
				            		if (meghpiattendandroidleavepage.LeaveFromDateOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave From Date.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave From Date." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		
				            		if (meghpiandroidattendancepage.clickDateInCalendar(fourthUI)) {
				            			logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day." + fourthUI);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
				            			logResults.createLogs("Y", "PASS", "Leave Apply Date Selected Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		
				            		if (meghpiattendandroidleavepage.LeaveToDateOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Apply TO Date Widget.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Apply To Date Widget." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.selectToDate(fourthUI)) {
				            			logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day For Leave." + fourthUI);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
				            			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.LeaveDurationOneOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Leave Duration Clicked Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Duration 2nd One." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.FirstHalfDayLeaveDuration()) {
				            			logResults.createLogs("Y", "PASS", "First Half Day Leave Duration Selected Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Selecting First Half Day Leave Duration." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.LeaveDurationTwoOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Leave Duration 2nd One Clicked Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Duration 2nd One." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.FirstHalfDayLeaveDuration()) {
				            			logResults.createLogs("Y", "PASS", "First Half Day Leave Duration Selected Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Selecting First Half Day Leave Duration." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		
				            		if (meghpiandroidattendancepage.RegulirizationReasonTextField(leavereason)) {
				            			logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Inputting Leave Reason." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		
				            		
				            		if (meghpiandroidattendancepage.LeaveApplyConfirmButton()) {
				            			logResults.createLogs("Y", "PASS", "Leave Apply Confirm Button Clicked Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Apply Confirm Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		} 
				            		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.validateLeaveCard(fourthUI, fourthUI, totaldays, leavestatus )) {
				            			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." + fourthUI + " "+ totaldays + " " + leavestatus);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.BackButton()) {
				            			logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghPIAttendAndroidLoginPage.ManageButton()) {
				            			logResults.createLogs("Y", "PASS", "Manage Profile Button Is Clicked.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Manage Profile Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}
				            		if (meghPIAttendAndroidLoginPage.LogoutButton()) {
				            			logResults.createLogs("Y", "PASS", "LogOut Button Is Clicked.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On LogOut Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}
				            		if (meghPIAttendAndroidLoginPage.LogoutConfirmButton()) {
				            			logResults.createLogs("Y", "PASS", "Logout Confirm Button Is Clicked.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Logout Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}  
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
				            		if (meghpiattendandroidleavepage.LeaveButtonOnEmpDashBoard()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Button On Emp DashBoard.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.validateLeaveCard(fourthUI, fourthUI, totaldays, leavestatus )) {
				            			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." + fourthUI + " "+ totaldays + " " + leavestatus);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}	
				
				
				}	
				
				
				// MPI_1517_Android_Leave_11
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_1517_Android_Leave_11()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that applying a half-day leave by selecting 2nd Half as duration and ensure leave is applied.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
							"password,empid,leavereason,totaldays,alltab,leavestatus,firstname");
					
					String password = data.get(0);
					String empid = data.get(1);
					String leavereason = data.get(2);
					String totaldays = data.get(3);
					String alltab = data.get(4);
					String leavestatus = data.get(5);
					String firstname = data.get(6);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

			   
			        String month4WorkingDate  = dateDetails.get("month4WorkingDate");
			        String fourthUI = Pramod.convertToUIFormat(month4WorkingDate) ;
					
					MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
	                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
	                             MeghPIAttendAndroidLeavePage meghpiattendandroidleavepage = new MeghPIAttendAndroidLeavePage(driver);

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


				            		if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
				            			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
				            		} else {
				            			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}

				            		if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
				            			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
				            		} else {
				            			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}

				            		if (meghPIAttendAndroidLoginPage.LoginButton()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Login Button." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.LeaveButtonOnAdminDashBoard()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Module Button.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Module Button." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.QuickActionLeaveOption()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Apply Leave Button From Quick Action.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Apply Leave Button From Quick Action." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            	
				            		if (meghpiandroidattendancepage.LeaveApplyConfirmDisplayed()) {
				            			logResults.createLogs("Y", "PASS", "Leave Apply Page Loaded Successfully." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Displaying Leave Apply Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidregularizationpage.RegulirizationRequestForOthersButton()) {
										logResults.createLogs("Y", "PASS", " Apply Leave For OThers Button.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Apply Leave For Others." + meghpiandroidregularizationpage.getExceptionDesc());
									}
									if (meghpiandroidregularizationpage.inputRegularizationEmpName(firstname)) {
										logResults.createLogs("Y", "PASS", " To Apply Leave For OThers Emp Name Inputted." + firstname);
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Inputting Emp Name While Applying Leave ." + meghpiandroidregularizationpage.getExceptionDesc());
									}
									if (meghpiattendandroidleavepage.EmpSelectedForLeaveForOthers()) {
										logResults.createLogs("Y", "PASS", "To Apply Leave For OThers Emp Name Selected.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Selecting Emp Name." + meghpiattendandroidleavepage.getExceptionDesc());
									}
									if (meghpiattendandroidleavepage.LeaveTypeDropDownOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Leave Type DropDown Clicked Successfully." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Type DropDown." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
									if (meghpiattendandroidleavepage.CasualLeaveTypeSelected()) {
				            			logResults.createLogs("Y", "PASS", "Casual Leave Type Selected Successfully." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Selecting Casual Leave Type." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		
				            		
				            		if (meghpiattendandroidleavepage.LeaveFromDateOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave From Date.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave From Date." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		
				            		if (meghpiandroidattendancepage.clickDateInCalendar(fourthUI)) {
				            			logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day." + fourthUI);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
				            			logResults.createLogs("Y", "PASS", "Leave Apply Date Selected Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		
				            		if (meghpiattendandroidleavepage.LeaveToDateOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Apply TO Date Widget.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Apply To Date Widget." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.selectToDate(fourthUI)) {
				            			logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day For Leave." + fourthUI);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
				            			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.LeaveDurationOneOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Leave Duration Clicked Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Duration 2nd One." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.SecondHalfDayLeaveDuration()) {
				            			logResults.createLogs("Y", "PASS", "Second Half Day Leave Duration Selected Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Selecting Second Half Day Leave Duration." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.LeaveDurationTwoOnAdmin()) {
				            			logResults.createLogs("Y", "PASS", "Leave Duration 2nd One Clicked Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Duration 2nd One." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.SecondHalfDayLeaveDuration()) {
				            			logResults.createLogs("Y", "PASS", "Second Half Day Leave Duration Selected Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Selecting Second Half Day Leave Duration." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		
				            		if (meghpiandroidattendancepage.RegulirizationReasonTextField(leavereason)) {
				            			logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Inputting Leave Reason." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		
				            		
				            		if (meghpiandroidattendancepage.LeaveApplyConfirmButton()) {
				            			logResults.createLogs("Y", "PASS", "Leave Apply Confirm Button Clicked Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Apply Confirm Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		} 
				            		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.validateLeaveCard(fourthUI, fourthUI, totaldays, leavestatus )) {
				            			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." + fourthUI + " "+ totaldays + " " + leavestatus);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiandroidattendancepage.BackButton()) {
				            			logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		if (meghPIAttendAndroidLoginPage.ManageButton()) {
				            			logResults.createLogs("Y", "PASS", "Manage Profile Button Is Clicked.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Manage Profile Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}
				            		if (meghPIAttendAndroidLoginPage.LogoutButton()) {
				            			logResults.createLogs("Y", "PASS", "LogOut Button Is Clicked.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On LogOut Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}
				            		if (meghPIAttendAndroidLoginPage.LogoutConfirmButton()) {
				            			logResults.createLogs("Y", "PASS", "Logout Confirm Button Is Clicked.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Logout Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}  
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
				            		if (meghpiattendandroidleavepage.LeaveButtonOnEmpDashBoard()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Button On Emp DashBoard.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.validateLeaveCard(fourthUI, fourthUI, totaldays, leavestatus )) {
				            			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." + fourthUI + " "+ totaldays + " " + leavestatus);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}	
				}			
	
				// MPI_1518_Android_Leave_12
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_1518_Android_Leave_12()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the search text field functionality in admin account ensure inputted employee record is displayed.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
							"password,firstname");
					
					String password = data.get(0);
				
					String firstname = data.get(1);
				
					MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
	                             MeghPIAttendAndroidLeavePage meghpiattendandroidleavepage = new MeghPIAttendAndroidLeavePage(driver);

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


				            		if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
				            			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
				            		} else {
				            			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}

				            		if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
				            			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
				            		} else {
				            			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}

				            		if (meghPIAttendAndroidLoginPage.LoginButton()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Login Button." );
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				            		}
				            		if (meghpiattendandroidleavepage.LeaveButtonOnAdminDashBoard()) {
				            			logResults.createLogs("Y", "PASS", "Clicked On Leave Module Button.");
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Clicking On Leave Module Button." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
	

				            		if (meghpiandroidattendancepage.RegulirizationReasonTextField(firstname)) {
				            			logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Inputting Emp Name." + meghpiandroidattendancepage.getExceptionDesc());
				            		}
				            		
				            		if (meghpiattendandroidleavepage.validateEmployeeNameInLeaveCard(firstname)) {
				            			logResults.createLogs("Y", "PASS", "Emp Name Validated Successfully." + firstname);
				            		} else {
				            			logResults.createLogs("Y", "FAIL",
				            					"Error While Validating Inputted Emp Name." + meghpiattendandroidleavepage.getExceptionDesc());
				            		}
				}
	
				
				// MPI_1520_Android_Leave_14
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_1520_Android_Leave_14()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that when an employee applies a First Half or Second Half day leave, the leave request is successfully created in the system.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
							"password,empid,alltab,leavereason,leavestatus,totaldays");
					
					String password = data.get(0);
					String empid = data.get(1);
					String alltab = data.get(2);
					
					String leavereason = data.get(3);
					String leavestatus = data.get(4);
					String totaldays = data.get(5);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

			        String month5WorkingDate  = dateDetails.get("month5WorkingDate");
			        String fifthUI = Pramod.convertToUIFormat(month5WorkingDate) ;
			       
					
					
					

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
			             		if (meghpiattendandroidleavepage.LeaveButtonOnEmpDashBoard()) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Leave Button On Emp DashBoard.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
			             		}
			             		
			             		if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
			             		}
			             		if (meghpiattendandroidleavepage.QuickActionLeaveOption()) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Apply Leave Button From Quick Action.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Apply Leave Button From Quick Action." + meghpiattendandroidleavepage.getExceptionDesc());
			             		}
			             	
			             		if (meghpiandroidattendancepage.LeaveApplyConfirmDisplayed()) {
			             			logResults.createLogs("Y", "PASS", "Leave Apply Page Loaded Successfully." );
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Displaying Leave Apply Button." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.LeaveTypeDropDown()) {
			             			logResults.createLogs("Y", "PASS", "Leave Type DropDown Clicked Successfully." );
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Type DropDown." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.SickLeaveTypeSelected()) {
			             			logResults.createLogs("Y", "PASS", "Sick Leave Type Selected Successfully." );
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Selecting Sick Leave Type." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		
			             		
			             		if (meghpiandroidattendancepage.RegulirizationFromDate()) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Date.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		
			             		if (meghpiandroidattendancepage.clickDateInCalendar(fifthUI)) {
			             			logResults.createLogs("Y", "PASS", "Clicked On 3rd Working Day." + fifthUI);
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On 3rd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
			             			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		
			             		if (meghpiandroidattendancepage.RegulirizationFromTimeTextField()) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Leave Apply TO Date Widget.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Apply To Date Widget." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.selectToDate(fifthUI)) {
			             			logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day For Leave." + fifthUI);
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
			             			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.DurationOne()) {
			             			logResults.createLogs("Y", "PASS", "Leave Duration Clicked Successfully.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Duration 2nd One." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiattendandroidleavepage.SecondHalfDayLeaveDuration()) {
			            			logResults.createLogs("Y", "PASS", "Second Half Day Leave Duration Selected Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Selecting Second Half Day Leave Duration." + meghpiattendandroidleavepage.getExceptionDesc());
			            		}
			             		if (meghpiandroidattendancepage.RegularizationTypeClicked()) {
			             			logResults.createLogs("Y", "PASS", "Leave Duration 2nd One Clicked Successfully.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Duration 2nd One." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiattendandroidleavepage.SecondHalfDayLeaveDuration()) {
			            			logResults.createLogs("Y", "PASS", "Second Half Day Leave Duration Selected Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Selecting Second Half Day Leave Duration." + meghpiattendandroidleavepage.getExceptionDesc());
			            		}
			             		
			             		if (meghpiandroidattendancepage.RegulirizationReasonTextField(leavereason)) {
			             			logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Inputting Leave Reason." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		
			             		if (meghpiandroidattendancepage.LeaveApplyConfirmButton()) {
			             			logResults.createLogs("Y", "PASS", "Leave Apply Confirm Button Clicked Successfully.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Apply Confirm Button." + meghpiandroidattendancepage.getExceptionDesc());
			             		} 
			             		if (meghpiattendandroidleavepage.clickLeaveTab(alltab)) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Leave Tab Pending Summary Count.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Tab Pending Summary Count." + meghpiattendandroidleavepage.getExceptionDesc());
			             		}
			             		if (meghpiattendandroidleavepage.validateLeaveCard(fifthUI, fifthUI, totaldays, leavestatus )) {
			             			logResults.createLogs("Y", "PASS", "Applied Leave Status Validated Successfully." + fifthUI + "to "+ fifthUI + " totaldays " + totaldays );
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Validating Applied Leave Status." + meghpiattendandroidleavepage.getExceptionDesc());
			             		}
				}	
				
				// MPI_1519_Android_Leave_13
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_1519_Android_Leave_13()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that when an employee attempts to apply a leave type with zero leave balance, the system prevents the submission and displays the proper error message.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
							"password,empid,totaldays");
					
					String password = data.get(0);
					String empid = data.get(1);
					String totaldays = data.get(2);
					
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

			        String month6WorkingDate  = dateDetails.get("month6WorkingDate");
			        String sixthUI = Pramod.convertToUIFormat(month6WorkingDate) ;

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
			             		if (meghpiattendandroidleavepage.LeaveButtonOnEmpDashBoard()) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Leave Button On Emp DashBoard.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Button On Emp DashBoard." + meghpiattendandroidleavepage.getExceptionDesc());
			             		}
			             		
			             		if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
			             		}
			             		if (meghpiattendandroidleavepage.QuickActionLeaveOption()) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Apply Leave Button From Quick Action.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Apply Leave Button From Quick Action." + meghpiattendandroidleavepage.getExceptionDesc());
			             		}
			             	
			             		if (meghpiandroidattendancepage.LeaveApplyConfirmDisplayed()) {
			             			logResults.createLogs("Y", "PASS", "Leave Apply Page Loaded Successfully." );
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Displaying Leave Apply Button." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.LeaveTypeDropDown()) {
			             			logResults.createLogs("Y", "PASS", "Leave Type DropDown Clicked Successfully." );
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Type DropDown." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiattendandroidleavepage.CasualLeaveTypeSelected()) {
			            			logResults.createLogs("Y", "PASS", "Casual Leave Type Selected Successfully." );
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Selecting Casual Leave Type." + meghpiattendandroidleavepage.getExceptionDesc());
			            		}
			             		
			             		
			             		if (meghpiandroidattendancepage.RegulirizationFromDate()) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Date.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		
			             		if (meghpiandroidattendancepage.clickDateInCalendar(sixthUI)) {
			             			logResults.createLogs("Y", "PASS", "Clicked On 3rd Working Day." + sixthUI);
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On 3rd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
			             			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		
			             		if (meghpiandroidattendancepage.RegulirizationFromTimeTextField()) {
			             			logResults.createLogs("Y", "PASS", "Clicked On Leave Apply TO Date Widget.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Apply To Date Widget." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.selectToDate(sixthUI)) {
			             			logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day For Leave." + sixthUI);
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
			             			logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.DurationOne()) {
			             			logResults.createLogs("Y", "PASS", "Leave Duration Clicked Successfully.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Duration 2nd One." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.FullDaySelected()) {
			            			logResults.createLogs("Y", "PASS", "Full Day Leave Duration Selected Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Selecting Full Day Leave Duration." + meghpiandroidattendancepage.getExceptionDesc());
			            		}
			             		if (meghpiandroidattendancepage.RegularizationTypeClicked()) {
			             			logResults.createLogs("Y", "PASS", "Leave Duration 2nd One Clicked Successfully.");
			             		} else {
			             			logResults.createLogs("Y", "FAIL",
			             					"Error While Clicking On Leave Duration 2nd One." + meghpiandroidattendancepage.getExceptionDesc());
			             		}
			             		if (meghpiandroidattendancepage.FullDaySelected()) {
			            			logResults.createLogs("Y", "PASS", "Full Day Leave Duration Selected Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Selecting Full Day Leave Duration." + meghpiandroidattendancepage.getExceptionDesc());
			            		}
			             		if (meghpiattendandroidleavepage.ZeroLeaveBalance(totaldays)) {
			            			logResults.createLogs("Y", "PASS", "Zero Leave Balance Displayed Successfully." );
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Error While Displaying Zero Leave Balance." + meghpiattendandroidleavepage.getExceptionDesc());
			            		}
				}
				
				
				// MPI_1526_Android_Leave_15
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_1526_Android_Leave_15()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the leave balance to an employee and ensure leave balance displayed properly on admin account.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Leave", currTC,
							"password,empid,firstname,pendingcount,approvedcount,revokedcount");
					
					String password = data.get(0);
					String empid = data.get(1);
					String firstname = data.get(2);
					String Available = data.get(3);
					String Deducted = data.get(4);
					String total = data.get(5);
					
					
				

					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
			                     MeghPIAttendAndroidLeavePage meghpiattendandroidleavepage = new MeghPIAttendAndroidLeavePage(driver);

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


					if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
						logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
					}

					if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
						logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
					}

					if (meghPIAttendAndroidLoginPage.LoginButton()) {
						logResults.createLogs("Y", "PASS", "Clicked On Login Button." + firstname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
					}
					if (meghpiattendandroidleavepage.LeaveButtonOnAdminDashBoard()) {
						logResults.createLogs("Y", "PASS", "Clicked On Leave Module Button.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Leave Module Button." + meghpiattendandroidleavepage.getExceptionDesc());
					}
					if (meghpiattendandroidleavepage.BalanceTabOnAdmin()) {
						logResults.createLogs("Y", "PASS", "Clicked On Leave Module BalanceTab Button.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Leave Module BalanceTab Button." + meghpiattendandroidleavepage.getExceptionDesc());
					}
					if (meghpiandroidattendancepage.RegulirizationReasonTextField(firstname)) {
						logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiattendandroidleavepage.validateTotals(firstname, total, Deducted, Available )) {
						logResults.createLogs("Y", "PASS", "Emp Leave Balance Validated Successfully." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Leave Balance." + meghpiattendandroidleavepage.getExceptionDesc());
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
