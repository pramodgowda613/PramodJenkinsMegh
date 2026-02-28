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
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPITimeLogReportsPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIEmpTimeLogReportsTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	private String cmpcode = "";
	private String Emailid = "";
	private String officename = "";
    private String AdminFirstName ="";
	  private String entityname = "";
	 

	
	@Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
		driver = loadDriver.getDriver(device, hubnodeip);

		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

	@Parameters({ "device", "hubnodeip" })
	@BeforeClass(alwaysRun = true)
	void runOnce(int device, @Optional String hubnodeip) {
		logResults.createReport(device);
		logResults.setTestMethodErrorCount(0);
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		Utils.propsReadWrite("data/addmaster.properties", "get", "EmpFirstName", "");
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		


	}
	
	
	// MPI_1758_Emp_LateIn_Reports_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_1758_Emp_LateIn_Reports_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of search feature using each search option.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
				"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,captcha,totalhours,lateinhours,shiftstatus,lateintime");


		  int i = 0;
		String password = data.get(i++);
		
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
	      
	        String captcha = data.get(i++);
	        
	        String totalhours = data.get(i++);
			String lateinhours = data.get(i++);
			String shift = data.get(i++);
			String lateintime = data.get(i++);
			
	        
	       
	     
	        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

	        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

	        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
	   String uidate = Pramod.convertToUIFormat(monthFirstWorkingDate);
		    
			String inouttime = monthFirstWorkingDate + " " + inouttime1;
			String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
		
			String firstDayOfMonth = dateInfo.get("firstDayOfMonth");
			String monthName = dateInfo.get("monthName");
			String year = dateInfo.get("year");
			
			MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
            MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

		      TableFieldIds ids = MeghPIAttenAPIPage.executeGetTableFieldIds(
			        baseuri, loginendpoint,
			        Emailid, password, cmpcode,
			        baseuri, tablefieldendpoint,
			        officename,   // company location name
			        AdminFirstName, // entity name
			        entityname    // entity type name
			);
		     
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
			Thread.sleep(15000);
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
	    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

		MeghLoginTest meghlogintest = new MeghLoginTest();
	    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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
	    
	    if (meghpitimelog.TimeLogReportButton()) {

			logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
		}

		if (meghpitimelog.LateInTab()) {

			logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
	
		if (meghpitimelog.LateInTabInTimeCheckBox()) {

			logResults.createLogs("Y", "PASS", " InTime CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On In Time CheckBox." + meghpitimelog.getExceptionDesc());
		}
		
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		
		if (meghpitimelog.LateInTabSearchField(uidate)) {

			logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + uidate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
		}
		
		
		if (meghpitimelog.LateInTabDateSearchResultOnEmp(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "Inputted Date Attendance Data Displayed Successfully." + monthFirstWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Inputted Date Data." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabSearchField(lateintime)) {

			logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + lateintime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  In Time." + meghpitimelog.getExceptionDesc());
		}
		
		
		if (meghpitimelog.LateInTabInTimeSearchResultOnEmp(lateintime)) {

			logResults.createLogs("Y", "PASS", "Inputted Late In Time Attendance Data Displayed Successfully." + lateintime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Inputted Late In Time Data." + meghpitimelog.getExceptionDesc());
		}
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitimelog.LateInDateCheckBox()) {

			logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabInTimeCheckBox()) {

			logResults.createLogs("Y", "PASS", " InTime CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On In Time CheckBox." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabTotalHourCheckBox()) {

			logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabLateInHoursCheckBox()) {

			logResults.createLogs("Y", "PASS", " In Hours CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On In Hours CheckBox." + meghpitimelog.getExceptionDesc());
		}
		
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabSearchField(totalhours)) {

			logResults.createLogs("Y", "PASS", "Totalhours Inputted Successfully." + totalhours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Totalhours." + meghpitimelog.getExceptionDesc());
		}
		
		
		if (meghpitimelog.LateInTabTotalHoursSearchResultOnEmp(totalhours)) {

			logResults.createLogs("Y", "PASS", "Inputted Total Hours Attendance Data Displayed Successfully." + totalhours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Inputted Total Hours Attendance Data." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabSearchField(lateinhours)) {

			logResults.createLogs("Y", "PASS", "Late In Hours Inputted Successfully." + lateinhours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Late In Hours." + meghpitimelog.getExceptionDesc());
		}
		
		
		if (meghpitimelog.LateInTabLateInHoursSearchResultOnEmp(lateinhours)) {

			logResults.createLogs("Y", "PASS", "Inputted Late In Hours Attendance Data Displayed Successfully." + lateinhours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Inputted Late In Hours Attendance Data." + meghpitimelog.getExceptionDesc());
		}
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		
		if (meghpitimelog.LateInTabTotalHourCheckBox()) {

			logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabLateInHoursCheckBox()) {

			logResults.createLogs("Y", "PASS", " In Hours CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On In Hours CheckBox." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabShiftCheckBox()) {

			logResults.createLogs("Y", "PASS", " Shift CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Shift CheckBox." + meghpitimelog.getExceptionDesc());
		}
		
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabSearchField(shift)) {

			logResults.createLogs("Y", "PASS", "Shift Inputted Successfully." + shift);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Shift." + meghpitimelog.getExceptionDesc());
		}
		
		
		if (meghpitimelog.LateInTabShiftSearchResultOnEmp(shift)) {

			logResults.createLogs("Y", "PASS", "Inputted Shift Attendance Data Displayed Successfully." + shift);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Inputted Shift Attendance Data." + meghpitimelog.getExceptionDesc());
		}
	}

	
	// MPI_1759_Emp_LateIn_Reports_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_1759_Emp_LateIn_Reports_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

		ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
				"subject,ccmail,empid,captcha");

		
	
		String subject = data.get(0);
		String ccmail = data.get(1);	
		String empid = data.get(2);
		String captcha = data.get(3);
		
		 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

	        String monthName = dateInfo.get("monthName");
	        String year = dateInfo.get("year");
	        MeghLoginPage meghloginpage = new MeghLoginPage(driver);

			MeghLoginTest meghlogintest = new MeghLoginTest();

		
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
	
    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

		

                    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

		
		if (meghpitimelog.TimeLogReportButton()) {

			logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
		}

		if (meghpitimelog.LateInTab()) {

			logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
		if (meghpitimelog.LateInTabMailButton()) {

			logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On MailReportButton." + meghpitimelog.getExceptionDesc());
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
	
	
	// MPI_1761_Emp_LateIn_Reports_04
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_1761_Emp_LateIn_Reports_04()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

		ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
				"empid,captcha");

		
	
		
		String empid = data.get(0);
		String captcha = data.get(1);
		
		 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

	        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");

	        String monthName = dateInfo.get("monthName");
	        String year = dateInfo.get("year");

			
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
	
    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

        			MeghLoginTest meghlogintest = new MeghLoginTest();

                    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

		
		if (meghpitimelog.TimeLogReportButton()) {

			logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
		}

		if (meghpitimelog.LateInTab()) {

			logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
		}
		
		if (meghpitimelog.LateInTabFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
		
		if (meghpitimelog.LateInTabDateSearchResultOnEmp(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
		}
	}
	
	
	// MPI_1762_Emp_LateIn_Reports_05
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_1762_Emp_LateIn_Reports_05()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, Do Late punch in as an employee during the last week of the previous month and then, in the late in reports, validate the filter functionality by selecting the last week. Ensure that the corresponding late punch in record is displayed.");

		ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
				"password,captcha,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid");


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

		  
	      
	        String updateattendanceendpoint = data.get(i++);
	       
	       String empid = data.get(i++);
	     
	        
	       Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

	        String month20WorkingDate = dateInfo.get("month20WorkingDate");

	        
			String firstDayOfMonth = dateInfo.get("firstDayOfMonth");
			String monthName = dateInfo.get("monthName");
			String year = dateInfo.get("year");
			
			
	        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

			String inouttime = month20WorkingDate + " " + inouttime1;
			String secondInOutTime = month20WorkingDate + " " + secondInOutTime2;
			
			
			
			// get first day of month
								
            MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		
		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
		 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
			MeghLoginTest meghlogintest = new MeghLoginTest();


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
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

		
		if (meghpitimelog.TimeLogReportButton()) {

			logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
		}

		if (meghpitimelog.LateInTab()) {

			logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
		}
		
		if (meghpitimelog.LateInTabFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
		// Step-1: Get Week Number
		if (meghpiattendancereport.getLastMonthWeekNumber("month20WorkingDate")) {

		    // Step-2: Convert saved static week number to string
		    String weekcount = Integer.toString(MeghPIAttendanceReportsPage.lastMonthWeekNumber);

		    // Step-3: Select week in dropdown
		    if (meghpiattendancereport.WeeklyDropDownSelected(weekcount)) {
		        logResults.createLogs("Y", "PASS", "Week selected successfully: " + weekcount);
		    } else {
		        logResults.createLogs("Y", "FAIL",
		                "Error while selecting week. " + meghpiattendancereport.getExceptionDesc());
		    }

		} else {
		    logResults.createLogs("Y", "FAIL",
		            "Failed to calculate week number. " + meghpiattendancereport.getExceptionDesc());
		}

		if (meghpiattendancereport.FilterSaveButton()) {

			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpitimelog.LateInTabDateSearchResultOnEmp(month20WorkingDate)) {

			logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + month20WorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
		}
	}
	
	
	//late out
	// MPI_1763_Emp_LateOut_Reports_01
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_1763_Emp_LateOut_Reports_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of search feature using each search option");

		ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
				"lateintime,totalhours,lateinhours,shiftstatus,empid,captcha");

		
	
		String lateintime = data.get(0);
		String totalhours = data.get(1);
		String lateinhours = data.get(2);
		String shift = data.get(3);
		String empid = data.get(4);
		String captcha = data.get(5);
		
		  Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

	        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");

	        String monthName = dateInfo.get("monthName");
	        String year = dateInfo.get("year");
	        String formattedDate = Pramod.convertToUIFormat(monthFirstWorkingDate);

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghloginPage = new MeghLoginPage(driver);

    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
        			MeghLoginTest meghlogintest = new MeghLoginTest();

	
                    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
            			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
            		} else {
            			logResults.createLogs("Y", "FAIL",
            					"Login Is Failed." + MeghloginPage.getExceptionDesc());
            		}
            	
            		
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}

		
		if (meghpitimelog.TimeLogReportButton()) {

			logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
		}

		if (meghpitimelog.LateOutTab()) {

			logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateOutTabFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
		
		if (meghpitimelog.LateInTabOutTimeCheckBox()) {

			logResults.createLogs("Y", "PASS", " OUt Time CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Out Time CheckBox." + meghpitimelog.getExceptionDesc());
		}
		
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		
		if (meghpitimelog.LateOutTabSearchTextfield(formattedDate)) {

			logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + formattedDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
		}
		
		
		if (meghpitimelog.LateOutTabDateSearchResultOnEmp(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "Inputted Date Attendance Data Displayed Successfully." + monthFirstWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Inputted Date Data." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateOutTabSearchTextfield(lateintime)) {

			logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + lateintime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  In Time." + meghpitimelog.getExceptionDesc());
		}
		
		
		if (meghpitimelog.LateOutTabOutTimeSearchResultOnEmp(lateintime)) {

			logResults.createLogs("Y", "PASS", "Inputted Late OUT Time Attendance Data Displayed Successfully." + lateintime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Inputted Late Out Time Data." + meghpitimelog.getExceptionDesc());
		}
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		
		if (meghpitimelog.LateInTabOutTimeCheckBox()) {

			logResults.createLogs("Y", "PASS", " OUt Time CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Out Time CheckBox." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabTotalHourCheckBox()) {

			logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateOutTabLatehoursCheckbox()) {

			logResults.createLogs("Y", "PASS", " out Hours CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Out Hours CheckBox." + meghpitimelog.getExceptionDesc());
		}
		
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitimelog.LateOutTabSearchTextfield(totalhours)) {

			logResults.createLogs("Y", "PASS", "Totalhours Inputted Successfully." + totalhours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Totalhours." + meghpitimelog.getExceptionDesc());
		}
		
		
		if (meghpitimelog.LateOutTabTotalhoursSearchResultOnEmp(totalhours)) {

			logResults.createLogs("Y", "PASS", "Inputted Total Hours Attendance Data Displayed Successfully." + totalhours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Inputted Total Hours Attendance Data." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateOutTabSearchTextfield(lateinhours)) {

			logResults.createLogs("Y", "PASS", "Late Out Hours Inputted Successfully." + lateinhours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Late Out Hours." + meghpitimelog.getExceptionDesc());
		}
		
		
		if (meghpitimelog.LateOutTabLateOuthoursSearchResultOnEmp(lateinhours)) {

			logResults.createLogs("Y", "PASS", "Inputted Late Out Hours Attendance Data Displayed Successfully." + lateinhours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Inputted Late Out Hours Attendance Data." + meghpitimelog.getExceptionDesc());
		}
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		
		if (meghpitimelog.LateInTabTotalHourCheckBox()) {

			logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateOutTabLatehoursCheckbox()) {

			logResults.createLogs("Y", "PASS", " Out Hours CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Out Hours CheckBox." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateInTabShiftCheckBox()) {

			logResults.createLogs("Y", "PASS", " Shift CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Shift CheckBox." + meghpitimelog.getExceptionDesc());
		}
		
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitimelog.LateOutTabSearchTextfield(shift)) {

			logResults.createLogs("Y", "PASS", "Shift Inputted Successfully." + shift);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Shift." + meghpitimelog.getExceptionDesc());
		}
		
		
		if (meghpitimelog.LateOutTabShiftSearchResultOnEmp(shift)) {

			logResults.createLogs("Y", "PASS", "Inputted Shift Attendance Data Displayed Successfully." + shift);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Inputted Shift Attendance Data." + meghpitimelog.getExceptionDesc());
		}
	}
	
	
	// MPI_1764_Emp_LateOut_Reports_02
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_1764_Emp_LateOut_Reports_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

		ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
				"subject,ccmail,empid,captcha");

		
		String subject = data.get(0);
		String ccmail = data.get(1);	
		String empid = data.get(2);
		String captcha = data.get(3);
		
		 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
	        String monthName = dateInfo.get("monthName");
	        String year = dateInfo.get("year");

		
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
	
    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

		


            		MeghLoginPage MeghloginPage = new MeghLoginPage(driver);

                    			MeghLoginTest meghlogintest = new MeghLoginTest();

            	
                                if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
                        			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
                        		} else {
                        			logResults.createLogs("Y", "FAIL",
                        					"Login Is Failed." + MeghloginPage.getExceptionDesc());
                        		}
                        	
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}

		
		if (meghpitimelog.TimeLogReportButton()) {

			logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
		}

		if (meghpitimelog.LateOutTab()) {

			logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
		}
		if (meghpitimelog.LateOutTabFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
		if (meghpitimelog.LateOutTabEmailButton()) {

			logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On MailReportButton." + meghpitimelog.getExceptionDesc());
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
	
	// MPI_1766_Emp_LateOut_Reports_04
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_1766_Emp_LateOut_Reports_04()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

		ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
				"empid,captcha");

		
		
		String empid = data.get(0);
		String captcha = data.get(1);
		
		 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

	        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");

	        String monthName = dateInfo.get("monthName");
	        String year = dateInfo.get("year");
			
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
	
    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

		
                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

        			MeghLoginTest meghlogintest = new MeghLoginTest();

                    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

		
		if (meghpitimelog.TimeLogReportButton()) {

			logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
		}

		if (meghpitimelog.LateOutTab()) {

			logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
		}
		
		if (meghpitimelog.LateOutTabFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
		
		if (meghpitimelog.LateOutTabDateSearchResultOnEmp(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
		}
	}
	
	
	// MPI_1767_Emp_LateOut_Reports_05
		@Test(enabled = true, priority = 8, groups = { "Smoke" })
		public void MPI_1767_Emp_LateOut_Reports_05()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, Do Late punch out as an employee during the last week of the previous month and then, in the late out reports, validate the filter functionality by selecting the last week. Ensure that the corresponding late punch out record is displayed..");

			ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
					"empid,captcha");

			  int i = 0;
			String empid = data.get(i++);
			String captcha = data.get(i++);

		       Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
		        String month20WorkingDate = dateInfo.get("month20WorkingDate");

				String monthName = dateInfo.get("monthName");
				String year = dateInfo.get("year");
				
				

				
				
				
				
				// get first day of month
									
	            MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			
			MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				MeghLoginTest meghlogintest = new MeghLoginTest();

			if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

			
			if (meghpitimelog.TimeLogReportButton()) {

				logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
			}

			if (meghpitimelog.LateOutTab()) {

				logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
			}
			
			if (meghpitimelog.LateOutTabFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
			// Step-1: Get Week Number
			if (meghpiattendancereport.getLastMonthWeekNumber("month20WorkingDate")) {

			    // Step-2: Convert saved static week number to string
			    String weekcount = Integer.toString(MeghPIAttendanceReportsPage.lastMonthWeekNumber);

			    // Step-3: Select week in dropdown
			    if (meghpiattendancereport.WeeklyDropDownSelected(weekcount)) {
			        logResults.createLogs("Y", "PASS", "Week selected successfully: " + weekcount);
			    } else {
			        logResults.createLogs("Y", "FAIL",
			                "Error while selecting week. " + meghpiattendancereport.getExceptionDesc());
			    }

			} else {
			    logResults.createLogs("Y", "FAIL",
			            "Failed to calculate week number. " + meghpiattendancereport.getExceptionDesc());
			}

			if (meghpiattendancereport.FilterSaveButton()) {

				logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpitimelog.LateOutTabDateSearchResultOnEmp(month20WorkingDate)) {

				logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + month20WorkingDate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
			}
		}
	
	
	
		// MPI_1768_Emp_Early_In_Reports_01
		@Test(enabled = true, priority = 9, groups = { "Smoke" })
		public void MPI_1768_Emp_Early_In_Reports_01()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of search feature using each search option.");

			  // Load Excel data
			ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
					"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,captcha,totalhours,lateinhours,shiftstatus,lateintime,empid");


			  int i = 0;
			String password = data.get(i++);
			
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

		      
		        String updateattendanceendpoint = data.get(i++);
		        
		     
		      
		        String captcha = data.get(i++);
		        
		        String totalhours = data.get(i++);
				String earlyinhours = data.get(i++);
				String shift = data.get(i++);
				String lateintime = data.get(i++);
				String empid = data.get(i++);
				
		        
		       
		     
		        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

		        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

		        String month2WorkingDate = dateInfo.get("month2WorkingDate");
		   String uidate = Pramod.convertToUIFormat(month2WorkingDate);
			    
				String inouttime = month2WorkingDate + " " + inouttime1;
				String secondInOutTime = month2WorkingDate + " " + secondInOutTime2;
			
				String firstDayOfMonth = dateInfo.get("firstDayOfMonth");
				String monthName = dateInfo.get("monthName");
				String year = dateInfo.get("year");
				
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
	            MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
	            MeghLoginPage meghloginpage = new MeghLoginPage(driver);

    			MeghLoginTest meghlogintest = new MeghLoginTest();
			     

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
				Thread.sleep(15000);
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

		    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

			
			if (meghpitimelog.TimeLogReportButton()) {

				logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
			}

			if (meghpitimelog.EarlyInTab()) {

				logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.EarlyInTabFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
			
			if (meghpitimelog.LateInTabInTimeCheckBox()) {

				logResults.createLogs("Y", "PASS", " InTime CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On In Time CheckBox." + meghpitimelog.getExceptionDesc());
			}
			
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			
			if (meghpitimelog.EarlyInTabSearchTextField(uidate)) {

				logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + uidate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
			}
			
			
			if (meghpitimelog.EarlyInTabDateSearchResultOnEmp(month2WorkingDate)) {

				logResults.createLogs("Y", "PASS", "Inputted Date Attendance Data Displayed Successfully." + month2WorkingDate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Inputted Date Data." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.EarlyInTabSearchTextField(lateintime)) {

				logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + lateintime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  In Time." + meghpitimelog.getExceptionDesc());
			}
			
			
			if (meghpitimelog.EarlyInTabInTImeSearchResultOnEmp(lateintime)) {

				logResults.createLogs("Y", "PASS", "Inputted Early In Time Attendance Data Displayed Successfully." + lateintime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Inputted Early In Time Data." + meghpitimelog.getExceptionDesc());
			}
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			
			if (meghpitimelog.LateInTabInTimeCheckBox()) {

				logResults.createLogs("Y", "PASS", " InTime CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On In Time CheckBox." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.LateInTabTotalHourCheckBox()) {

				logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.EarlyInTabEarlyInHoursCheckBox()) {

				logResults.createLogs("Y", "PASS", " In Hours CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On In Hours CheckBox." + meghpitimelog.getExceptionDesc());
			}
			
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			if (meghpitimelog.EarlyInTabSearchTextField(totalhours)) {

				logResults.createLogs("Y", "PASS", "Totalhours Inputted Successfully." + totalhours);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Totalhours." + meghpitimelog.getExceptionDesc());
			}
			
			
			if (meghpitimelog.EarlyInTabTotalHoursSearchResultOnEmp(totalhours)) {

				logResults.createLogs("Y", "PASS", "Inputted Total Hours Attendance Data Displayed Successfully." + totalhours);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Inputted Total Hours Attendance Data." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.EarlyInTabSearchTextField(earlyinhours)) {

				logResults.createLogs("Y", "PASS", "Late In Hours Inputted Successfully." + earlyinhours);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Late In Hours." + meghpitimelog.getExceptionDesc());
			}
			
			
			if (meghpitimelog.EarlyInTabEarlyInHoursSearchResultOnEmp(earlyinhours)) {

				logResults.createLogs("Y", "PASS", "Inputted Early In Hours Attendance Data Displayed Successfully." + earlyinhours);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Inputted Early In Hours Attendance Data." + meghpitimelog.getExceptionDesc());
			}
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			
			if (meghpitimelog.LateInTabTotalHourCheckBox()) {

				logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.EarlyInTabEarlyInHoursCheckBox()) {

				logResults.createLogs("Y", "PASS", " In Hours CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On In Hours CheckBox." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.LateInTabShiftCheckBox()) {

				logResults.createLogs("Y", "PASS", " Shift CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Shift CheckBox." + meghpitimelog.getExceptionDesc());
			}
			
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			if (meghpitimelog.EarlyInTabSearchTextField(shift)) {

				logResults.createLogs("Y", "PASS", "Shift Inputted Successfully." + shift);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Shift." + meghpitimelog.getExceptionDesc());
			}
			
			
			if (meghpitimelog.EarlyInTabShiftSearchResultOnEmp(shift)) {

				logResults.createLogs("Y", "PASS", "Inputted Shift Attendance Data Displayed Successfully." + shift);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Inputted Shift Attendance Data." + meghpitimelog.getExceptionDesc());
			}
		}
	
		// MPI_1769_Emp_Early_In_Reports_02
		@Test(enabled = true, priority = 10, groups = { "Smoke" })
		public void MPI_1769_Emp_Early_In_Reports_02()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

			ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
					"subject,ccmail,empid,captcha");

			
		
			String subject = data.get(0);
			String ccmail = data.get(1);
			String empid = data.get(2);
			String captcha = data.get(3);
			
			Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();


	        String monthName = dateInfo.get("monthName");
	        String year = dateInfo.get("year");

			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		
	    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

	                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

	        			MeghLoginTest meghlogintest = new MeghLoginTest();

	                    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

			
			if (meghpitimelog.TimeLogReportButton()) {

				logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
			}

			if (meghpitimelog.EarlyInTab()) {

				logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.EarlyInTabFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
			if (meghpitimelog.EarlyInTabEmailButton()) {

				logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On MailReportButton." + meghpitimelog.getExceptionDesc());
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
		
	
		// MPI_1771_Emp_Early_In_Reports_04
		@Test(enabled = true, priority = 11, groups = { "Smoke" })
		public void MPI_1771_Emp_Early_In_Reports_04()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date  ");

			ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
					"empid,captcha");

			
			
			String empid = data.get(0);
			String captcha = data.get(1);
			
			 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

		        String month2WorkingDate = dateInfo.get("month2WorkingDate");

		        String monthName = dateInfo.get("monthName");
		        String year = dateInfo.get("year");
				
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		
	    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

			
	                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

	        			MeghLoginTest meghlogintest = new MeghLoginTest();

	                    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

						
						if (meghpitimelog.TimeLogReportButton()) {

							logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
						}

						if (meghpitimelog.EarlyInTab()) {

							logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
						}
						if (meghpitimelog.EarlyInTabFilterButton()) {

							logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
			
			if (meghpiattendancereport.FromDateSelected(month2WorkingDate)) {

				logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month2WorkingDate);
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
			
			if (meghpiattendancereport.ToDateSelected(month2WorkingDate)) {

				logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month2WorkingDate);
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
			
			if (meghpitimelog.EarlyInTabDateSearchResultOnEmp(month2WorkingDate)) {

				logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + month2WorkingDate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
			}
		}
	
		// MPI_1772_Emp_Early_In_Reports_05
		@Test(enabled = true, priority = 12, groups = { "Smoke" })
		public void MPI_1772_Emp_Early_In_Reports_05()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, Do early punch in as an employee during the last week of the previous month and then, in the early in reports, validate the filter functionality by selecting the last week. Ensure that the corresponding early punch in record is displayed.");

			ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
					"password,captcha,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid");


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

			  
		     
		        String updateattendanceendpoint = data.get(i++);
		       
		       String empid = data.get(i++);
		     
		        
		       Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

		        String month19WorkingDate = dateInfo.get("month19WorkingDate");

		        
				String firstDayOfMonth = dateInfo.get("firstDayOfMonth");
				String monthName = dateInfo.get("monthName");
				String year = dateInfo.get("year");
				
				
		        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				String inouttime = month19WorkingDate + " " + inouttime1;
				String secondInOutTime = month19WorkingDate + " " + secondInOutTime2;
				
				
				
				// get first day of month
									
	            MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			
			MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
				MeghLoginTest meghlogintest = new MeghLoginTest();


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
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

				
				if (meghpitimelog.TimeLogReportButton()) {

					logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
				}

				if (meghpitimelog.EarlyInTab()) {

					logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
				}
				if (meghpitimelog.EarlyInTabFilterButton()) {

					logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
			// Step-1: Get Week Number
			if (meghpiattendancereport.getLastMonthWeekNumber("month19WorkingDate")) {

			    // Step-2: Convert saved static week number to string
			    String weekcount = Integer.toString(MeghPIAttendanceReportsPage.lastMonthWeekNumber);

			    // Step-3: Select week in dropdown
			    if (meghpiattendancereport.WeeklyDropDownSelected(weekcount)) {
			        logResults.createLogs("Y", "PASS", "Week selected successfully: " + weekcount);
			    } else {
			        logResults.createLogs("Y", "FAIL",
			                "Error while selecting week. " + meghpiattendancereport.getExceptionDesc());
			    }

			} else {
			    logResults.createLogs("Y", "FAIL",
			            "Failed to calculate week number. " + meghpiattendancereport.getExceptionDesc());
			}

			if (meghpiattendancereport.FilterSaveButton()) {

				logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpitimelog.EarlyInTabDateSearchResultOnEmp(month19WorkingDate)) {

				logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + month19WorkingDate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
			}
		}
		
		
		//Early out
		// MPI_1774_Emp_EarlyOut_Reports_01
		@Test(enabled = true, priority = 13, groups = { "Smoke" })
		public void MPI_1774_Emp_EarlyOut_Reports_01()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of search feature using each search option");

			ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
					"lateintime,totalhours,lateinhours,shiftstatus,empid,captcha");

			
		
			String fourthinouttime = data.get(0);
			String totalhours = data.get(1);
			String lateinhours = data.get(2);
			String shift = data.get(3);
			String empid = data.get(4);
			String captcha = data.get(5);
			
			Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

	        String month2WorkingDate = dateInfo.get("month2WorkingDate");

		        String monthName = dateInfo.get("monthName");
		        String year = dateInfo.get("year");
		        String formattedDate = Pramod.convertToUIFormat(month2WorkingDate);
			
	
			MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

	                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

			
	                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

	                    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

			
			if (meghpitimelog.TimeLogReportButton()) {

				logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
			}

			if (meghpitimelog.EarlyOutTab()) {

				logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.EarlyOutTabFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
			
			if (meghpitimelog.LateInTabOutTimeCheckBox()) {

				logResults.createLogs("Y", "PASS", " OUt Time CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Out Time CheckBox." + meghpitimelog.getExceptionDesc());
			}
			
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			
			if (meghpitimelog.EarlyOutTabSearchTextField(formattedDate)) {

				logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + formattedDate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
			}
			
			
			if (meghpitimelog.EarlyOutTabDateSearchResultOnEmp(month2WorkingDate)) {

				logResults.createLogs("Y", "PASS", "Inputted Date Attendance Data Displayed Successfully." + month2WorkingDate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Inputted Date Data." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.EarlyOutTabSearchTextField(fourthinouttime)) {

				logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + fourthinouttime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Out Time." + meghpitimelog.getExceptionDesc());
			}
			
			
			if (meghpitimelog.EarlyOutTabOutTimeSearchResultOnEmp(fourthinouttime)) {

				logResults.createLogs("Y", "PASS", "Inputted Early OUT Time Attendance Data Displayed Successfully." + fourthinouttime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Inputted Early Out Time Data." + meghpitimelog.getExceptionDesc());
			}
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			
			if (meghpitimelog.LateInTabOutTimeCheckBox()) {

				logResults.createLogs("Y", "PASS", " OUt Time CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Out Time CheckBox." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.LateInTabTotalHourCheckBox()) {

				logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.EarlyOutTabEarlyOutHoursCheckBox()) {

				logResults.createLogs("Y", "PASS", " Early out Hours CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Early Out Hours CheckBox." + meghpitimelog.getExceptionDesc());
			}
			
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			if (meghpitimelog.EarlyOutTabSearchTextField(totalhours)) {

				logResults.createLogs("Y", "PASS", "Totalhours Inputted Successfully." + totalhours);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Totalhours." + meghpitimelog.getExceptionDesc());
			}
			
			
			if (meghpitimelog.EarlyOutTabTotalHoursSearchResultOnEmp(totalhours)) {

				logResults.createLogs("Y", "PASS", "Inputted Total Hours Attendance Data Displayed Successfully." + totalhours);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Inputted Total Hours Attendance Data." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.EarlyOutTabSearchTextField(lateinhours)) {

				logResults.createLogs("Y", "PASS", "Early Out Hours Inputted Successfully." + lateinhours);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Early Out Hours." + meghpitimelog.getExceptionDesc());
			}
			
			
			if (meghpitimelog.EarlyOutTabEarlyOutHoursSearchResultOnEmp(lateinhours)) {

				logResults.createLogs("Y", "PASS", "Inputted Early Out Hours Attendance Data Displayed Successfully." + lateinhours);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Inputted Early Out Hours Attendance Data." + meghpitimelog.getExceptionDesc());
			}
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			
			if (meghpitimelog.LateInTabTotalHourCheckBox()) {

				logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.EarlyOutTabEarlyOutHoursCheckBox()) {

				logResults.createLogs("Y", "PASS", " Out Hours CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Out Hours CheckBox." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.LateInTabShiftCheckBox()) {

				logResults.createLogs("Y", "PASS", " Shift CheckBOx Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Shift CheckBox." + meghpitimelog.getExceptionDesc());
			}
			
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			if (meghpitimelog.EarlyOutTabSearchTextField(shift)) {

				logResults.createLogs("Y", "PASS", "Shift Inputted Successfully." + shift);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Shift." + meghpitimelog.getExceptionDesc());
			}
			
			
			if (meghpitimelog.EarlyOutTabShiftSearchResultOnEmp(shift)) {

				logResults.createLogs("Y", "PASS", "Inputted Shift Attendance Data Displayed Successfully." + shift);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Inputted Shift Attendance Data." + meghpitimelog.getExceptionDesc());
			}
		}
		
		
		// MPI_1775_Emp_EarlyOut_Reports_02
		@Test(enabled = true, priority = 14, groups = { "Smoke" })
		public void MPI_1775_Emp_EarlyOut_Reports_02()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

			ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
					"subject,ccmail,empid,captcha");

			
			
			String subject = data.get(0);
			String ccmail = data.get(1);
			String empid = data.get(2);
			String captcha = data.get(3);
			
			
			Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
	        String monthName = dateInfo.get("monthName");
	        String year = dateInfo.get("year");
			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	    	
	                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

			
	                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

	                    MeghLoginTest meghlogintest = new MeghLoginTest();

	                    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

			
			if (meghpitimelog.TimeLogReportButton()) {

				logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
			}

			if (meghpitimelog.EarlyOutTab()) {

				logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
			}	
			if (meghpitimelog.EarlyOutTabFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
			if (meghpitimelog.EarlyOutTabEmailButton()) {

				logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On MailReportButton." + meghpitimelog.getExceptionDesc());
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
		
		
		// MPI_1777_Emp_EarlyOut_Reports_04
		@Test(enabled = true, priority = 15, groups = { "Smoke" })
		public void MPI_1777_Emp_EarlyOut_Reports_04()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date  ");


			ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
					"empid,captcha");

			
			
			
			String empid = data.get(0);
			String captcha = data.get(1);
	
			Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

        String month2WorkingDate = dateInfo.get("month2WorkingDate");


	        String monthName = dateInfo.get("monthName");
	        String year = dateInfo.get("year");
				
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);				
	    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

			
	                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);




	                    MeghLoginTest meghlogintest = new MeghLoginTest();

	                    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

			
			if (meghpitimelog.TimeLogReportButton()) {

				logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
			}

			if (meghpitimelog.EarlyOutTab()) {

				logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
			}	
			
			if (meghpitimelog.EarlyOutTabFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
			
			if (meghpiattendancereport.FromDateSelected(month2WorkingDate)) {

				logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month2WorkingDate);
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
			
			if (meghpiattendancereport.ToDateSelected(month2WorkingDate)) {

				logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month2WorkingDate);
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
			
			if (meghpitimelog.EarlyOutTabDateSearchResultOnEmp(month2WorkingDate)) {

				logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
			}
		}
		
		
		// MPI_1778_Emp_EarlyOut_Reports_05
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
				public void MPI_1778_Emp_EarlyOut_Reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, Do early punch out as an employee during the last week of the previous month and then, in the early out reports, validate the filter functionality by selecting the last week. Ensure that the corresponding early punch out record is displayed.");

					ArrayList<String> data = initBase.loadExcelData("EmpTimeLogReports", currTC,
							"empid,captcha");

					  int i = 0;
					String empid = data.get(i++);
					String captcha = data.get(i++);
					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String month19WorkingDate = dateInfo.get("month19WorkingDate");

						String monthName = dateInfo.get("monthName");
						String year = dateInfo.get("year");
				
						// get first day of month
											
			            MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
						MeghLoginTest meghlogintest = new MeghLoginTest();

					if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyOutTab()) {

						logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
					}	
					
					if (meghpitimelog.EarlyOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
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
					// Step-1: Get Week Number
					if (meghpiattendancereport.getLastMonthWeekNumber("month19WorkingDate")) {

					    // Step-2: Convert saved static week number to string
					    String weekcount = Integer.toString(MeghPIAttendanceReportsPage.lastMonthWeekNumber);

					    // Step-3: Select week in dropdown
					    if (meghpiattendancereport.WeeklyDropDownSelected(weekcount)) {
					        logResults.createLogs("Y", "PASS", "Week selected successfully: " + weekcount);
					    } else {
					        logResults.createLogs("Y", "FAIL",
					                "Error while selecting week. " + meghpiattendancereport.getExceptionDesc());
					    }

					} else {
					    logResults.createLogs("Y", "FAIL",
					            "Failed to calculate week number. " + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyOutTabDateSearchResultOnEmp(month19WorkingDate)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + month19WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
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
