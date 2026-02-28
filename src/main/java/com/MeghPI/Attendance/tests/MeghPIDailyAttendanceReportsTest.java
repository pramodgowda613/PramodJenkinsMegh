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

import com.MeghPI.Attendance.pages.MeghAttendancePolicyPage;
import com.MeghPI.Attendance.pages.MeghLeavePage;
import com.MeghPI.Attendance.pages.MeghLeavePolicyPage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIDailyAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghWeekOffPolicyPage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIDailyAttendanceReportsTest {

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
    private String AdminFirstName ="";
   
	
	
	@Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
	//	driver = loadDriver.getDriver(device);

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
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");
		
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
	
	}
	
	// MPI_1288_DailyAttendance_Reports_11
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_1288_DailyAttendance_Reports_11()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, perform punch in and punch out actions, and ensure that the attendance status, in time, out time, total working hours, and detected shift name are displayed correctly.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("DailyAttendance_Reports", currTC,
				"password,captcha,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,workinghour,reportstatus,intime,outtime,policyname");


		  int i = 0;
		String password = data.get(i++);
		String captcha = data.get(i++);
		  String baseuri = data.get(i++);
		    String loginendpoint = data.get(i++);
		    String endpointoftransaction = data.get(i++);
		    String cardnumber = data.get(i++);
		    int cardtype = Integer.parseInt(data.get(i++));
		   
		    String bio1finger = data.get(i++);
		    String bio2finger  = data.get(i++);
		   
		    String locationid = data.get(i++);
		    String inouttime1 = data.get(i++);     // Punch In time
		    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
		    String photo = data.get(i++);
		    String secondInOutTime2 = data.get(i++); // Punch Out time
		    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

		    String statusEndpoint    = data.get(i++);
	        String fromDateofuserstatus1          = data.get(i++);
	        String toDateofuserstatus2            = data.get(i++);
	        String expectedStatus    = data.get(i++);
	        String description = data.get(i++);
	        String totalOTHour = data.get(i++);
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
	        String workinghour = data.get(i++);
	        String reportstatus = data.get(i++);
String intime = data.get(i++);	     
String outtime = data.get(i++);	
String policyname = data.get(i++);
	        
	        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

	        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

	        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
	        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");

	        String monthName = dateInfo.get("monthName");
	        String year = dateInfo.get("year");
	        
	        String fromDateofstatus = monthFirstWorkingDate + fromDateofuserstatus1;
	        String toDateofstatus = monthFirstWorkingDate + toDateofuserstatus2;
		   
		    
			String inouttime = monthFirstWorkingDate + " " + inouttime1;
			String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
		

			 
			MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
	
		 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		 MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);

	MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
	MeghPIDailyAttendanceReportsPage meghpidailyattendancereport = new MeghPIDailyAttendanceReportsPage(driver);
	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
	MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);



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
		    	    logResults.createLogs("N", "PASS", "Enrollment executed successfully for Employee ID." + empid);
		    	} else {
		    	    logResults.createLogs("N", "FAIL", "Enrollment failed for Employee ID." + empid +
		    	                          " | Response." + enrollResponse.asString());
		    	    
		    	}

	    // Punch IN
	    Response punchInResponse = apiPage.executeSuccessTransaction(
	            baseuri, loginendpoint,
	            Emailid, password, cmpcode,
	            baseuri, endpointoftransaction,
	            cardnumber, cardtype, deviceuniqueid,
	            bio1finger, bio2finger, empid,
	            locationid, inouttime, mode, photo
	    );

	    if (punchInResponse.getStatusCode() == 200) {
		    logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
		    logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
		    return;
		}

	    // Punch OUT
	    Response punchOutResponse = apiPage.executeSuccessTransaction(
	            baseuri, loginendpoint,
	            Emailid, password, cmpcode,
	            baseuri, endpointoftransaction,
	            cardnumber, cardtype, deviceuniqueid,
	            bio1finger, bio2finger, empid,
	            locationid, secondInOutTime, secondMode, photo
	    );

	    if (punchOutResponse.getStatusCode() == 200) {
            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
        } else {
            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
            return;
        }
	    try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	    try {
			Thread.sleep(25000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 // Get User Status
        Response validation = apiPage.executeGetUserStatus(
                baseuri, loginendpoint,
                Emailid, password, cmpcode,
                baseuri, statusEndpoint,
                empid, fromDateofstatus, toDateofstatus
        );

        if (validation.statusCode() == 200) {
            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

            // Handle null OTHours → default to 00:00
            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

            // Make sentence using excel inputs
            String finalSentence = String.format(
                "%s – This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
                description, empid, monthFirstWorkingDate, inouttime1, secondInOutTime,
                actualStatus, expectedStatus, actualOTHours, totalOTHour
            );

            // Validate both Final Status & OT Hours
            if (expectedStatus.equalsIgnoreCase(actualStatus) &&
                totalOTHour.equalsIgnoreCase(actualOTHours)) {
                logResults.createLogs("N", "PASS", "✅ " + finalSentence);
            } else {
                logResults.createLogs("N", "FAIL", "❌ " + finalSentence);
            }
        } else {
            logResults.createLogs("N", "FAIL", "❌ Failed to fetch attendance. API Response." + validation.asString());
        }

        if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
        
        if (RolePermissionpage.PolicyIcon()) {

			logResults.createLogs("Y", "PASS", "PolicyIcon Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PolicyIcon." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftMasterLoaded()) {

			logResults.createLogs("Y", "PASS", "PolicyMaster Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "PolicyMaster Isn't Loaded." + ShiftPolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeaveDropDownFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeaveDropDownFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LeaveDropDownFromSideBar Button." + LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyButtonFromSideBar()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyButtonFromSideBar Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeavePolicyButtonFromSideBar Button."
					+ LeavePolicyPage.getExceptionDesc());
		}

		if (LeavePolicyPage.LeavePolicyPageLoaded()) {

			logResults.createLogs("Y", "PASS", "LeavePolicyPageLoaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading LeavePolicyPage." + LeavePolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicySearchTextField(policyname)) {

			logResults.createLogs("Y", "PASS",
					"WeekOffPolicy Name Inputted In Search TextField Successfully." + policyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffPolicyFirstRecord()) {

			logResults.createLogs("Y", "PASS", "WeekOffPolicy First Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying WeekOffPolicy Name." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (WeekOffPolicyPage.WeekOffEditButton()) {

			logResults.createLogs("Y", "PASS", "WeekOffEditButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On WeekOffEditButton." + WeekOffPolicyPage.getExceptionDesc());
		}
		if (WeekOffPolicyPage.DefaultCheckBoxifenabled()) {

			logResults.createLogs("Y", "PASS", "DefaultCheckBox Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DefaultCheckBox ." + WeekOffPolicyPage.getExceptionDesc());
		}
		if (WeekOffPolicyPage.CreateWeekOffPolicyButton()) {

			logResults.createLogs("Y", "PASS", "CreateWeekOffPolicyButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateWeekOffPolicyButton ." + WeekOffPolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.YesButton()) {
			logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
		}
   
        
        if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
        if (meghpidailyattendancereport.DailyAttendanceReportButton()) {

			logResults.createLogs("Y", "PASS", "Daily Attendance Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Daily Attendance Report Button." + meghpidailyattendancereport.getExceptionDesc());
		}
        
        if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Daily Attendance Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Loading  Daily Attendance Report Page." + meghpidailyattendancereport.getExceptionDesc());
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
        if (meghpidailyattendancereport.DailyAttendanceRptFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpidailyattendancereport.getExceptionDesc());
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
        
        if (meghpidailyattendancereport.WorkingHourCheckBox()) {

			logResults.createLogs("Y", "PASS", "WorkingHour CheckBox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting WorkingHour CheckBox." + meghpidailyattendancereport.getExceptionDesc());
		}
        if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
        if (meghpidailyattendancereport.DailyAttendanceReportSearchTextField(workinghour)) {

			logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + workinghour);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpidailyattendancereport.getExceptionDesc());
		}	
        
        if (meghpidailyattendancereport.Attendancestatusvalidation(firstname, monthFirstWorkingDate, workinghour, intime, outtime, reportstatus  )) {

			logResults.createLogs("Y", "PASS", "Emp Attendance Date, Working Hour, InTime, OutTime, Attendance Status Validated Successfully." + firstname + " " + monthFirstWorkingDate + " " +workinghour + " "+ inouttime1 + " " + secondInOutTime2 + " " +reportstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Date, Working Hour, InTime, OutTime, Attendance Status." + meghpidailyattendancereport.getExceptionDesc());
		}	
	}
	
	// MPI_1278_DailyAttendance_Reports_01
		@Test(enabled = true, priority = 2, groups = { "Smoke" })
		public void MPI_1278_DailyAttendance_Reports_01()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of search feature for each search option.");

			  // Load Excel data
			ArrayList<String> data = initBase.loadExcelData("DailyAttendance_Reports", currTC,
					"reportstatus,intime,outtime");
			
			  int i = 0;
				
				String reportstatus = data.get(i++);
				String intime = data.get(i++);
				String outtime = data.get(i++);
	
				Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				String monthFirstWorkingDate = dateDetails.get("month1WorkingDate");
				String PunchinDateSearchUI = Pramod.convertToUIFormat(monthFirstWorkingDate);
				
		        String monthName = dateDetails.get("monthName");
		        String year = dateDetails.get("year");
	
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
MeghPIDailyAttendanceReportsPage meghpidailyattendancereport = new MeghPIDailyAttendanceReportsPage(driver);
MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
MeghLoginPage meghloginpage = new MeghLoginPage(driver);

if (meghloginpage.MainLandingPage()) {
	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
}  
if (RolePermissionpage.ReprtButton()) {

	logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
}
if (meghpidailyattendancereport.DailyAttendanceReportButton()) {

	logResults.createLogs("Y", "PASS", "Daily Attendance Report Button Clicked Successfully.");
} else {
	logResults.createLogs("Y", "FAIL", "Error While Clicking On Daily Attendance Report Button." + meghpidailyattendancereport.getExceptionDesc());
}

if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

	logResults.createLogs("Y", "PASS", "Daily Attendance Report Page Loaded Successfully.");
} else {
	logResults.createLogs("Y", "FAIL", "Error While Loading  Daily Attendance Report Page." + meghpidailyattendancereport.getExceptionDesc());
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
if (meghpidailyattendancereport.DailyAttendanceRptFilterButton()) {

	logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error While Clicking On Filter Button." + meghpidailyattendancereport.getExceptionDesc());
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

if (meghpidailyattendancereport.AttendanceDateCheckBox()) {

	logResults.createLogs("Y", "PASS", "AttendanceDate CheckBox Selected Successfully." );
} else {
	logResults.createLogs("Y", "FAIL", "Error While Selecting AttendanceDate CheckBox." + meghpidailyattendancereport.getExceptionDesc());
}
if (meghpidailyattendancereport.InTimeCheckBox()) {

	logResults.createLogs("Y", "PASS", "InTime CheckBox Selected Successfully." );
} else {
	logResults.createLogs("Y", "FAIL", "Error While Selecting InTime CheckBox." + meghpidailyattendancereport.getExceptionDesc());
}


if (OfficePage.SearchDropDown()) {
	logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
}
if (meghpidailyattendancereport.DailyAttendanceReportSearchTextField(PunchinDateSearchUI)) {

	logResults.createLogs("Y", "PASS", "Emp Attendance Date Inputted Successfully." + PunchinDateSearchUI);
} else {
	logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Attendance Date." + meghpidailyattendancereport.getExceptionDesc());
}	
if (meghpidailyattendancereport.AttendanceDateValidation(PunchinDateSearchUI)) {

	logResults.createLogs("Y", "PASS", "Emp Attendance Date Validated Successfully." + PunchinDateSearchUI);
} else {
	logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Date." + meghpidailyattendancereport.getExceptionDesc());
}
if (meghpidailyattendancereport.DailyAttendanceReportSearchTextField(intime)) {

	logResults.createLogs("Y", "PASS", "Emp Attendance Date Punch In Time Inputted Successfully." + intime);
} else {
	logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Attendance Date Punch In Time." + meghpidailyattendancereport.getExceptionDesc());
}	
if (meghpidailyattendancereport.AttendanceInTimeValidation(intime)) {

	logResults.createLogs("Y", "PASS", "Emp Attendance Date InTime Validated Successfully." + intime);
} else {
	logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Date In Time." + meghpidailyattendancereport.getExceptionDesc());
}
if (OfficePage.SearchDropDown()) {
	logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
}

if (meghpidailyattendancereport.AttendanceDateCheckBox()) {

	logResults.createLogs("Y", "PASS", "AttendanceDate CheckBox Selected Successfully." );
} else {
	logResults.createLogs("Y", "FAIL", "Error While Selecting AttendanceDate CheckBox." + meghpidailyattendancereport.getExceptionDesc());
}
if (meghpidailyattendancereport.InTimeCheckBox()) {

	logResults.createLogs("Y", "PASS", "InTime CheckBox Selected Successfully." );
} else {
	logResults.createLogs("Y", "FAIL", "Error While Selecting InTime CheckBox." + meghpidailyattendancereport.getExceptionDesc());
}
if (meghpidailyattendancereport.OutTimeCheckBox()) {

	logResults.createLogs("Y", "PASS", "OutTime CheckBox Selected Successfully." );
} else {
	logResults.createLogs("Y", "FAIL", "Error While Selecting OutTime CheckBox." + meghpidailyattendancereport.getExceptionDesc());
}
if (meghpidailyattendancereport.StatusCheckBox()) {

	logResults.createLogs("Y", "PASS", "Status CheckBox Selected Successfully." );
} else {
	logResults.createLogs("Y", "FAIL", "Error While Selecting Status CheckBox." + meghpidailyattendancereport.getExceptionDesc());
}


if (OfficePage.SearchDropDown()) {
	logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
} else {
	logResults.createLogs("Y", "FAIL",
			"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
}

if (meghpidailyattendancereport.DailyAttendanceReportSearchTextField(outtime)) {

	logResults.createLogs("Y", "PASS", "Emp Attendance Date Punch OUt Time Inputted Successfully." + outtime);
} else {
	logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Attendance Date Punch Out Time." + meghpidailyattendancereport.getExceptionDesc());
}	
if (meghpidailyattendancereport.AttendanceOutTimeValidation(outtime)) {

	logResults.createLogs("Y", "PASS", "Emp Attendance Date OUT Time Validated Successfully." + outtime);
} else {
	logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Date Out Time." + meghpidailyattendancereport.getExceptionDesc());
}

if (meghpidailyattendancereport.DailyAttendanceReportSearchTextField(reportstatus)) {

	logResults.createLogs("Y", "PASS", "Emp Attendance Status Inputted Successfully." + reportstatus);
} else {
	logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Attendance Status." + meghpidailyattendancereport.getExceptionDesc());
}	
if (meghpidailyattendancereport.AttendanceStatusValidation(reportstatus)) {

	logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully." + reportstatus);
} else {
	logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status." + meghpidailyattendancereport.getExceptionDesc());
}
		}
		
		// MPI_1279_DailyAttendance_Reports_02
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_1279_DailyAttendance_Reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("DailyAttendance_Reports", currTC,
							"subject,ccmail");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPIDailyAttendanceReportsPage meghpidailyattendancereport = new MeghPIDailyAttendanceReportsPage(driver);

					
					
					String subject =data.get(0);
					String ccmail =data.get(1);
					
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
			        System.out.println(monthName + "= monthname");
			
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);

					if (meghloginpage.MainLandingPage()) {
						logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
					}		

					  if (RolePermissionpage.ReprtButton()) {

							logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
						}
					  if (meghpidailyattendancereport.DailyAttendanceReportButton()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Daily Attendance Report Button." + meghpidailyattendancereport.getExceptionDesc());
						}

						if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Page Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Loading  Daily Attendance Report Page." + meghpidailyattendancereport.getExceptionDesc());
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
						if (meghpidailyattendancereport.DailyAttendanceRptFilterButton()) {

							logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Filter Button." + meghpidailyattendancereport.getExceptionDesc());
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
						
						if (meghpiovertimepage.OverTimeReportEmailButton()) {

							logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On MailReportButton." + meghpiovertimepage.getExceptionDesc());
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
	
				// MPI_1281_DailyAttendance_Reports_04
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_1281_DailyAttendance_Reports_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

				//	ArrayList<String> data = initBase.loadExcelData("DailyAttendance_Reports", currTC,"password,captcha");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPIDailyAttendanceReportsPage meghpidailyattendancereport = new MeghPIDailyAttendanceReportsPage(driver);

				
					
					Map<String, String> monthDetails = Pramod.getLastMonthWorkingDatesDetails();

				    // Extract values
				   
				    String monthName = monthDetails.get("monthName");
				    String year = monthDetails.get("year");
			        
					 

				    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

				    if (meghloginpage.MainLandingPage()) {
				    	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				    } else {
				    	logResults.createLogs("Y", "FAIL",
				    			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				    }	

					  if (RolePermissionpage.ReprtButton()) {

							logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
						}
					  if (meghpidailyattendancereport.DailyAttendanceReportButton()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Daily Attendance Report Button." + meghpidailyattendancereport.getExceptionDesc());
						}

						if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Page Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Loading  Daily Attendance Report Page." + meghpidailyattendancereport.getExceptionDesc());
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
							if (meghpidailyattendancereport.DailyAttendanceRptFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpidailyattendancereport.getExceptionDesc());
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
							
							if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

								logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpidailyattendancereport.getExceptionDesc());
							} 	
				}
	
				// MPI_1282_DailyAttendance_Reports_05
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_1282_DailyAttendance_Reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

				//	ArrayList<String> data = initBase.loadExcelData("DailyAttendance_Reports", currTC, "password,captcha");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPIDailyAttendanceReportsPage meghpidailyattendancereport = new MeghPIDailyAttendanceReportsPage(driver);

					
					
					Map<String, String> monthDetails = Pramod.getLastMonthWorkingDatesDetails();

				    // Extract values
				  
				    String monthName = monthDetails.get("monthName");
				    String year = monthDetails.get("year");
				    
				    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

				    if (meghloginpage.MainLandingPage()) {
				    	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				    } else {
				    	logResults.createLogs("Y", "FAIL",
				    			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				    }		

					  if (RolePermissionpage.ReprtButton()) {

							logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
						}
					  if (meghpidailyattendancereport.DailyAttendanceReportButton()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Daily Attendance Report Button." + meghpidailyattendancereport.getExceptionDesc());
						}

						if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Page Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Loading  Daily Attendance Report Page." + meghpidailyattendancereport.getExceptionDesc());
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
							if (meghpidailyattendancereport.DailyAttendanceRptFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpidailyattendancereport.getExceptionDesc());
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
							
							if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

								logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpidailyattendancereport.getExceptionDesc());
							} 	
				}
	
				// MPI_1283_DailyAttendance_Reports_06
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_1283_DailyAttendance_Reports_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

				//	ArrayList<String> data = initBase.loadExcelData("DailyAttendance_Reports", currTC, "password,captcha");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPIDailyAttendanceReportsPage meghpidailyattendancereport = new MeghPIDailyAttendanceReportsPage(driver);

					
					
					Map<String, String> monthDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = monthDetails.get("monthName");
				    String year = monthDetails.get("year");

				    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

				    if (meghloginpage.MainLandingPage()) {
				    	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				    } else {
				    	logResults.createLogs("Y", "FAIL",
				    			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				    }		

					  if (RolePermissionpage.ReprtButton()) {

							logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
						}
					  if (meghpidailyattendancereport.DailyAttendanceReportButton()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Daily Attendance Report Button." + meghpidailyattendancereport.getExceptionDesc());
						}

						if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Page Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Loading  Daily Attendance Report Page." + meghpidailyattendancereport.getExceptionDesc());
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
							if (meghpidailyattendancereport.DailyAttendanceRptFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpidailyattendancereport.getExceptionDesc());
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
							
							if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

								logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpidailyattendancereport.getExceptionDesc());
							} 	
				}
	
	
				// MPI_1284_DailyAttendance_Reports_07
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_1284_DailyAttendance_Reports_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the filter feature functionality by selecting year and month");

				//	ArrayList<String> data = initBase.loadExcelData("DailyAttendance_Reports", currTC, "password,captcha");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPIDailyAttendanceReportsPage meghpidailyattendancereport = new MeghPIDailyAttendanceReportsPage(driver);

				
					
					Map<String, String> monthDetails = Pramod.getLastMonthWorkingDatesDetails();

				    String monthName = monthDetails.get("monthName");
				    String year = monthDetails.get("year");

				    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

				    if (meghloginpage.MainLandingPage()) {
				    	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				    } else {
				    	logResults.createLogs("Y", "FAIL",
				    			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				    }		

					  if (RolePermissionpage.ReprtButton()) {

							logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
						}
					  if (meghpidailyattendancereport.DailyAttendanceReportButton()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Daily Attendance Report Button." + meghpidailyattendancereport.getExceptionDesc());
						}

						if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Page Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Loading  Daily Attendance Report Page." + meghpidailyattendancereport.getExceptionDesc());
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
							if (meghpidailyattendancereport.DailyAttendanceRptFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpidailyattendancereport.getExceptionDesc());
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
							
							if (meghpidailyattendancereport.FilteredDateValidation(year, monthName )) {

								logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpidailyattendancereport.getExceptionDesc());
							} 	
				}
	
				// MPI_1285_DailyAttendance_Reports_08
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_1285_DailyAttendance_Reports_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date");

				//	ArrayList<String> data = initBase.loadExcelData("DailyAttendance_Reports", currTC, "password,captcha");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPIDailyAttendanceReportsPage meghpidailyattendancereport = new MeghPIDailyAttendanceReportsPage(driver);

					
					
					Map<String, String> monthDetails = Pramod.getLastMonthWorkingDatesDetails();

				    // Extract values   month1WorkingDate
				    String monthFirstWorkingDate = monthDetails.get("month1WorkingDate");

				    String monthName = monthDetails.get("monthName");
				    String year = monthDetails.get("year");

				    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

				    if (meghloginpage.MainLandingPage()) {
				    	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				    } else {
				    	logResults.createLogs("Y", "FAIL",
				    			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				    }		

					  if (RolePermissionpage.ReprtButton()) {

							logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
						}
					  if (meghpidailyattendancereport.DailyAttendanceReportButton()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Daily Attendance Report Button." + meghpidailyattendancereport.getExceptionDesc());
						}

						if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Page Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Loading  Daily Attendance Report Page." + meghpidailyattendancereport.getExceptionDesc());
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
							if (meghpidailyattendancereport.DailyAttendanceRptFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpidailyattendancereport.getExceptionDesc());
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
							
							if (meghpiattendancereport.FromDateSelected(monthFirstWorkingDate)) {

								logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + monthFirstWorkingDate);
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
							
							if (meghpiattendancereport.ToDateSelected(monthFirstWorkingDate)) {

								logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + monthFirstWorkingDate);
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
							
							if (meghpidailyattendancereport.DailyAttendanceRptFilteredDateValidation(monthFirstWorkingDate)) {

								logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpidailyattendancereport.getExceptionDesc());
							} 	
				}
	
	
				// MPI_1289_DailyAttendance_Reports_12
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_1289_DailyAttendance_Reports_12()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, mark the employee as present on the first Sunday and ensure that the status “Present on Week Off” is displayed.");

					ArrayList<String> data = initBase.loadExcelData("DailyAttendance_Reports", currTC,
							"password,baseuri,loginendpoint,updateattendanceendpoint,empid,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,reportstatus,workinghour");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					System.out.println(data);
					
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					MeghPIDailyAttendanceReportsPage meghpidailyattendancereport = new MeghPIDailyAttendanceReportsPage(driver);
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

					 

					int i = 0;
					String password = data.get(i++);
					String baseuri = data.get(i++);
					String loginendpoint = data.get(i++);
					String updateattendanceendpoint = data.get(i++);
					String empid = data.get(i++);
			        String endpointoftransaction = data.get(i++);
				    String cardnumber =data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));

				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				   
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				  
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String reportstatus = data.get(i++);
			        String workinghour =  data.get(i++);
			      
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
	System.out.println("no issue");

	  Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();

	  String week2Sunday   = (String) details.get("2ndSunday");
	  String firstDayOfMonth = (String) details.get("firstDayLastMonth");

	  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
	  String monthName = dateDetails.get("monthName");
	  String year = dateDetails.get("year");
				        String fromDateofstatus = week2Sunday + fromDateofuserstatus1;
				        String toDateofstatus = week2Sunday + toDateofuserstatus2;
					   
					    
						String inouttime = week2Sunday + " " + inouttime1;
						String secondInOutTime = week2Sunday + " " + secondInOutTime2;
					
						
						// get first day of month
				
						 
				        // Punch IN
					    Response punchInResponse = apiPage.executeSuccessTransaction(
					            baseuri, loginendpoint,
					            Emailid, password, cmpcode,
					            baseuri, endpointoftransaction,
					            cardnumber, cardtype, deviceuniqueid,
					            bio1finger, bio2finger, empid,
					            locationid, inouttime, mode, photo
					    );
					    System.out.println("PunchInResponse Status Code." + punchInResponse.getStatusCode());

					    if (punchInResponse.getStatusCode() == 200) {
					        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
					    } else {
					        logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
					        return;
					    }
					    // Punch OUT
					    Response punchOutResponse = apiPage.executeSuccessTransaction(
					            baseuri, loginendpoint,
					            Emailid, password, cmpcode,
					            baseuri, endpointoftransaction,
					            cardnumber, cardtype, deviceuniqueid,
					            bio1finger, bio2finger, empid,
					            locationid, secondInOutTime, secondMode, photo
					    );

					    if (punchOutResponse.getStatusCode() == 200) {
				            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
				        } else {
				            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
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
					 // Get User Status
				        Response validation = apiPage.executeGetUserStatus(
				                baseuri, loginendpoint,
				                Emailid, password, cmpcode,
				                baseuri, statusEndpoint,
				                empid, fromDateofstatus, toDateofstatus
				        );

				        if (validation.statusCode() == 200) {
				            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

				            // Make sentence using excel inputs
				            String finalSentence = String.format(
				                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
				                description, empid, week2Sunday, inouttime1, secondInOutTime2, actualStatus, expectedStatus
				            );

				            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
				            } else {
				                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
				            }
				        } else {
				            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
				        }
				 
						
					 
				        MeghLoginPage meghloginpage = new MeghLoginPage(driver);

				        if (meghloginpage.MainLandingPage()) {
				        	logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				        } else {
				        	logResults.createLogs("Y", "FAIL",
				        			"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				        } 
				        if (RolePermissionpage.ReprtButton()) {

							logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
						}
				        if (meghpidailyattendancereport.DailyAttendanceReportButton()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Daily Attendance Report Button." + meghpidailyattendancereport.getExceptionDesc());
						}
				        
				        if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

							logResults.createLogs("Y", "PASS", "Daily Attendance Report Page Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Loading  Daily Attendance Report Page." + meghpidailyattendancereport.getExceptionDesc());
						}
				        if (meghpiattendancereport.DepartmentDropdown("--Select--")) {

							logResults.createLogs("Y", "PASS", "Dept Selected Successfully.  --Select--"  );
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
						}
				        if (meghpidailyattendancereport.DailyAttendanceRptFilterButton()) {

							logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Filter Button." + meghpidailyattendancereport.getExceptionDesc());
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
				        if (meghpidailyattendancereport.WorkingHourCheckBox()) {

							logResults.createLogs("Y", "PASS", "WorkingHour CheckBox Selected Successfully." );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting WorkingHour CheckBox." + meghpidailyattendancereport.getExceptionDesc());
						}
				  
				        if (OfficePage.SearchDropDown()) {
				        	logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
				        } else {
				        	logResults.createLogs("Y", "FAIL",
				        			"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
				        }
				        if (meghpidailyattendancereport.DailyAttendanceReportSearchTextField(workinghour)) {

				        	logResults.createLogs("Y", "PASS", "Emp Attendance Date Working Hours Inputted Successfully." + workinghour);
				        } else {
				        	logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Attendance Date workinghour." + meghpidailyattendancereport.getExceptionDesc());
				        }	
				        if (meghpidailyattendancereport.DailyAttendanceRptWorkingHourValidation(workinghour)) {

				        	logResults.createLogs("Y", "PASS", "Emp Attendance WorkingHours Validated Successfully." + workinghour);
				        } else {
				        	logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Date Working Hours." + meghpidailyattendancereport.getExceptionDesc());
				        }
					}
	
				// MPI_1290_DailyAttendance_Reports_13
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_1290_DailyAttendance_Reports_13()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, apply a leave and ensure that the leave status is displayed for the respective date.");

					ArrayList<String> data = initBase.loadExcelData("DailyAttendance_Reports", currTC,
							"leavereason,leavename,firstname,reportstatus");

				
					
					String LeaveReason = data.get(0);
					String leavename = data.get(1);
				
					String firstname = data.get(2);
					String reportstatus = data.get(3);
					
					Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

				    String monthSecondWorkingDate = details.get("month2WorkingDate");
				    
			        String monthName = details.get("monthName");
			        String year = details.get("year");
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

				
				MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
				MeghPIDailyAttendanceReportsPage meghpidailyattendancereport = new MeghPIDailyAttendanceReportsPage(driver);
				
				MeghLoginPage meghloginpage = new MeghLoginPage(driver);

				if (meghloginpage.MainLandingPage()) {
					logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
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
				
				
				if (meghleavepage.ApplyLeaveButton()) {

					logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.LeaveForOthersButton()) {

					logResults.createLogs("Y", "PASS", "  Leave For Others Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave For Others Button."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.EmployeeSelectionDropDown()) {

					logResults.createLogs("Y", "PASS", "  Employee Selection DropDown Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Employee Selection DropDown."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.EmployeeInputField(firstname)) {

					logResults.createLogs("Y", "PASS", " Emp Name Inputted  Successfully ." + firstname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.EmpNameDisplayedAndSelected()) {

					logResults.createLogs("Y", "PASS", "  Employee Selected  Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Employee From DropDown."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.LeaveTypeDropDownOnAdmin()) {

					logResults.createLogs("Y", "PASS", "  LeaveType DropDown OnAdmin Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking  LeaveType DropDown OnAdmin."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveTypeInputtedOnAdmin(leavename)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully ." + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Name."
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveTypeSelectedOnAdmin()) {

					logResults.createLogs("Y", "PASS", "  Leave Type Selected  Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type From DropDown."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromDateOnAdmin()) {

					logResults.createLogs("Y", "PASS", " Clicked On FromDate TextField In Admin  Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On From Date TextField."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromDateOnAdminSelected(monthSecondWorkingDate)) {

					logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully ." + monthSecondWorkingDate);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting  From Date in TextField."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromLeaveDurationDropdownAdmin()) {

					logResults.createLogs("Y", "PASS", " Clicked On FromLeaveDuration Dropdown In Admin  Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On FromLeave Duration Dropdown."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromLeaveDurationSelectedInAdmin()) {

					logResults.createLogs("Y", "PASS", " Selected  FromLeaveDuration From Dropdown In Admin  Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting  FromLeave Duration From Dropdown."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToDateTextFieldInAdmin()) {

					logResults.createLogs("Y", "PASS", " Clicked On ToDate TextField In Admin  Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On To Date TextField."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToDateTextFieldInAdminSelected(monthSecondWorkingDate)) {

					logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully ." + monthSecondWorkingDate);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting  TO Date in TextField."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToLeaveDurationDropDownInAdmin()) {

					logResults.createLogs("Y", "PASS", " Clicked On ToLeaveDuration Dropdown In Admin  Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On ToLeave Duration Dropdown."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToLeaveDurationFullDaySelectedInAdmin()) {

					logResults.createLogs("Y", "PASS", " Selected  ToLeaveDuration From Dropdown In Admin  Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting  ToLeave Duration From Dropdown."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ReasonTextField(LeaveReason)) {

					logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + LeaveReason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Reason."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ApplyForOthersButton()) {

					logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button."
							+ meghleavepage.getExceptionDesc());
				}	 
				 if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}
				 if (meghpidailyattendancereport.DailyAttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Daily Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Daily Attendance Report Button." + meghpidailyattendancereport.getExceptionDesc());
					}
			        
			        if (meghpidailyattendancereport.DailyAttendanceReportEmpNameRowDisplay()) {

						logResults.createLogs("Y", "PASS", "Daily Attendance Report Page Loaded Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Loading  Daily Attendance Report Page." + meghpidailyattendancereport.getExceptionDesc());
					}
			        if (meghpiattendancereport.DepartmentDropdown("--Select--")) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully.  --Select--"  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
			        if (meghpidailyattendancereport.DailyAttendanceRptFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpidailyattendancereport.getExceptionDesc());
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
			        if (meghpidailyattendancereport.StatusCheckBox()) {

			        	logResults.createLogs("Y", "PASS", "Status CheckBox Selected Successfully." );
			        } else {
			        	logResults.createLogs("Y", "FAIL", "Error While Selecting Status CheckBox." + meghpidailyattendancereport.getExceptionDesc());
			        }
			  
			        if (OfficePage.SearchDropDown()) {
			        	logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			        } else {
			        	logResults.createLogs("Y", "FAIL",
			        			"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			        }
			        if (meghpidailyattendancereport.DailyAttendanceReportSearchTextField(reportstatus)) {

			        	logResults.createLogs("Y", "PASS", "Emp Attendance Date Punch In Time Inputted Successfully." + reportstatus);
			        } else {
			        	logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Attendance Date Punch In Time." + meghpidailyattendancereport.getExceptionDesc());
			        }	
			        if (meghpidailyattendancereport.AttendanceStatusValidation(reportstatus)) {

			        	logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully." + reportstatus);
			        } else {
			        	logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status." + meghpidailyattendancereport.getExceptionDesc());
			        }
				 
	
				}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
				@AfterMethod(alwaysRun = true)
				void Aftermethod() {
					logResults.onlyLog();
					
				}

				@AfterClass(alwaysRun = true)
				void cleanUp() {
					if (driver != null) {
						driver.quit();
					}
				}

}
