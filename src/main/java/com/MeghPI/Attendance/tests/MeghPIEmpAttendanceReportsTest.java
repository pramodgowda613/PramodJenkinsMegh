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
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeRequestPage;
import com.MeghPI.Attendance.pages.MeghPIShiftRequestPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIEmpAttendanceReportsTest {


	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	

	private String cmpcode = "";
	private String Emailid = "";
	private String officename = "";
    private String AdminFirstName ="";
	  private String entityname = "";
	  private String designationname= "";
	  private String devicename = "";
		private String departmentname = "";
		private String teamname = "";
	    private String gradename = ""; 

	
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
		designationname = "AutoDESN" +  initBase.executionRunTime;
		devicename = Utils.propsReadWrite("data/addmaster.properties", "get", "DeviceName", "");
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		gradename = "AutoGN" + initBase.executionRunTime;


	}
	
	
	// MPI_1713_Punch_Reports_01
				@Test(enabled = true, priority = 1, groups = { "Smoke" })
				public void MPI_1713_Punch_Reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that the search functionality works correctly and ensure that employees can search their punch records using all available search options.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,captcha");


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
				        
				       
				     
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
					    
						String inouttime = monthFirstWorkingDate + " " + inouttime1;
						String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
					
						String firstDayOfMonth = dateInfo.get("firstDayOfMonth");
						String monthName = dateInfo.get("monthName");
						String year = dateInfo.get("year");
						
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
						MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		                     MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
	                            MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
				        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

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
				    if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.PunchReport()) {

						logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
			
					if (meghpiattendancereport.PunchModeCheckbox()) {

						logResults.createLogs("Y", "PASS", "Punch Mode Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch Mode Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.PunchReportSearchTextField(inouttime1)) {

						logResults.createLogs("Y", "PASS", "Punch In Time Inputted Successfully." + inouttime1 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch In Time." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FirstRecordDateAndTimeOnEmp(inouttime1)) {

						logResults.createLogs("Y", "PASS", "Punch In Record Displayed Successfully." + inouttime1 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch In Time Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.PunchReportSearchTextField(secondMode)) {

						logResults.createLogs("Y", "PASS", "Punch Mode Inputted Successfully." + secondMode );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch Mode." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.PunchModeInFirstRecordOnEmp(secondMode)) {

						logResults.createLogs("Y", "PASS", "Punch Mode Record Displayed Successfully." + secondMode );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch Mode Record." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchTimeCheckbox()) {

						logResults.createLogs("Y", "PASS", "PunchTime Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting PunchTime Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchModeCheckbox()) {

						logResults.createLogs("Y", "PASS", "Punch Mode Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch Mode Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DeviceNameCheckBox()) {

						logResults.createLogs("Y", "PASS", "Device Name Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Device Name Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.CompanyLocationNameCheckBox()) {

						logResults.createLogs("Y", "PASS", "Company Location Name Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Company Location Name Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.PunchReportSearchTextField(devicename)) {

						logResults.createLogs("Y", "PASS", "Device Name Inputted Successfully." + devicename );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Device Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DeviceNameRowOnEmp(devicename)) {

						logResults.createLogs("Y", "PASS", "Device Name Record Displayed Successfully." + devicename );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Device Name Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.PunchReportSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Office Name Inputted Successfully." + officename );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Office Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.CompanyLocationNameRowOnEmp(officename)) {

						logResults.createLogs("Y", "PASS", "Office Name Record Displayed Successfully." + officename );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Office Name Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.DeviceNameCheckBox()) {

						logResults.createLogs("Y", "PASS", "Device Name Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Device Name Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.CompanyLocationNameCheckBox()) {

						logResults.createLogs("Y", "PASS", "Company Location Name Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Company Location Name Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.EntityTypeNameCheckBox()) {

						logResults.createLogs("Y", "PASS", "Entity Type Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Entity Type Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.PunchReportSearchTextField(entityname)) {

						logResults.createLogs("Y", "PASS", "Entity Type Name Inputted Successfully." + entityname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Entity Type Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EntityTypeNameRowOnEmp(entityname)) {

						logResults.createLogs("Y", "PASS", "Entity Type Name Record Displayed Successfully." + entityname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Entity Type Name Record." + meghpiattendancereport.getExceptionDesc());
					}
				    
				}
				
				
				// MPI_1714_punch_Reports_02
				@Test(enabled = true, priority = 2, groups = { "Smoke" })
				public void MPI_1714_punch_Reports_02() {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"captcha,subject,ccmail,empid");

					
					String captcha = data.get(0);
					String subject = data.get(1);
					String ccmail = data.get(2) ;
					String empid = data.get(3);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
					
		        
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

				
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.PunchReport()) {

						logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
				
				
				// MPI_1716_Punch_Reports_04
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_1716_Punch_Reports_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the filter feature functionality by selecting year and last month (punch in month)");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"captcha,empid");

					
					String captcha = data.get(0);
					String empid = data.get(1);

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.PunchReport()) {

						logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FirstRecordResultOnEmp()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}	
				
				// MPI_1717_punch_Reports_05
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_1717_punch_Reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, punch in as an employee during the last week of the previous month and then, in the punch reports, validate the filter functionality by selecting the last week. Ensure that the corresponding punch record is displayed.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha");


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
				        String empid = data.get(i++);
				       String captcha = data.get(i++);
				       
				       
				     
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String month20WorkingDate = dateInfo.get("month20WorkingDate");
					    
						String inouttime = month20WorkingDate + " " + inouttime1;
						String secondInOutTime = month20WorkingDate + " " + secondInOutTime2;
					
						String firstDayOfMonth = dateInfo.get("firstDayOfMonth");
						String monthName = dateInfo.get("monthName");
						String year = dateInfo.get("year");
						
						
						
						
						
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		

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
				    if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.PunchReport()) {

						logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
					}	
					
					if (meghpiattendancereport.FilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
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
					
					if (meghpiattendancereport.FirstRecordDateAndTimeOnEmp(inouttime1)) {

						logResults.createLogs("Y", "PASS", "Punch In Record Displayed Successfully." + inouttime1 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch In Time Record." + meghpiattendancereport.getExceptionDesc());
					}

				}
				
				
				// MPI_1718_Punch_Reports_06
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_1718_Punch_Reports_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date and select punch in and out time ");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"captcha,inouttime,secondinouttime,empid");

					
					String captcha = data.get(0);
					String inouttime = data.get(1);
					String secondinouttime = data.get(2);
					String empid = data.get(3);

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");
			String month20WorkingDate  = dateDetails.get("month20WorkingDate");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.PunchReport()) {

						logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month20WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month20WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month20WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month20WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromTimeSelected(inouttime)) {

						logResults.createLogs("Y", "PASS", "FromTime Selected Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToTimeTextField()) {

						logResults.createLogs("Y", "PASS", "To Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On To TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToTimeSelected(secondinouttime)) {

						logResults.createLogs("Y", "PASS", "ToTime Selected Successfully." + secondinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Time." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FirstRecordDateAndTimeOnEmp(inouttime)) {

						logResults.createLogs("Y", "PASS", "Punch In Record Displayed Successfully." + inouttime );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch In Time Record." + meghpiattendancereport.getExceptionDesc());
					}
				}
				
				
				//MPI_1719_In_Out_Reports_01
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_1719_In_Out_Reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that the search functionality works correctly and ensure that employees can search their punch records using all available search options.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"inouttime,mode,captcha,empid");


					  int i = 0;
					    String inouttime1 = data.get(i++);     // Punch In time
					    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
					   String captcha = data.get(i++);
					   String empid = data.get(i++);
				        
				       
				     

				        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				       
					
						String monthName = dateInfo.get("monthName");
						String year = dateInfo.get("year");
						
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
						MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
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
				    if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

				    if (RolePermissionpage.InOutReport()) {

						logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
					}
				    if (meghpiattendancereport.FilterButtonInOut()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					
					
					if (meghpiattendancereport.PunchModeCheckbox()) {

						logResults.createLogs("Y", "PASS", "Punch Mode Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch Mode Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldInOut(inouttime1)) {

						logResults.createLogs("Y", "PASS", "Punch In Time Inputted Successfully." + inouttime1 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch In Time." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchTimeFirstRowinoutOnEmp(inouttime1)) {

						logResults.createLogs("Y", "PASS", "Punch In Record Displayed Successfully." + inouttime1 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch In Time Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldInOut(mode)) {

						logResults.createLogs("Y", "PASS", "Punch Mode Inputted Successfully." + mode );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch Mode." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.PunchModeFirstRowInoutOnEmp(mode)) {

						logResults.createLogs("Y", "PASS", "Punch Mode Record Displayed Successfully." + mode );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch Mode Record." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchTimeCheckbox()) {

						logResults.createLogs("Y", "PASS", "PunchTime Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting PunchTime Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchModeCheckbox()) {

						logResults.createLogs("Y", "PASS", "Punch Mode Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch Mode Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DeviceNameCheckBox()) {

						logResults.createLogs("Y", "PASS", "Device Name Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Device Name Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.CompanyLocationNameCheckBox()) {

						logResults.createLogs("Y", "PASS", "Company Location Name Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Company Location Name Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldInOut(devicename)) {

						logResults.createLogs("Y", "PASS", "Device Name Inputted Successfully." + devicename );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Device Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DeviceNamrFirstRowInoutOnEmp(devicename)) {

						logResults.createLogs("Y", "PASS", "Device Name Record Displayed Successfully." + devicename );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Device Name Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldInOut(officename)) {

						logResults.createLogs("Y", "PASS", "Office Name Inputted Successfully." + officename );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Office Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationNamrFirstRowInoutOnEmp(officename)) {

						logResults.createLogs("Y", "PASS", "Office Name Record Displayed Successfully." + officename );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Office Name Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.DeviceNameCheckBox()) {

						logResults.createLogs("Y", "PASS", "Device Name Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Device Name Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.CompanyLocationNameCheckBox()) {

						logResults.createLogs("Y", "PASS", "Company Location Name Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Company Location Name Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.EntityTypeNameCheckBox()) {

						logResults.createLogs("Y", "PASS", "Entity Type Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Entity Type Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldInOut(entityname)) {

						logResults.createLogs("Y", "PASS", "Entity Type Name Inputted Successfully." + entityname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Entity Type Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EntityTypeNamrFirstRowInoutOnEmp(entityname)) {

						logResults.createLogs("Y", "PASS", "Entity Type Name Record Displayed Successfully." + entityname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Entity Type Name Record." + meghpiattendancereport.getExceptionDesc());
					}
				    
				}	
				
				// MPI_1720_In_Out_Reports_02
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_1720_In_Out_Reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,subject,ccmail");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					String subject = data.get(2);
					String ccmail = data.get(3) ;
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
					
		        
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.InOutReport()) {

						logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterButtonInOut()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.InoutReportMailButton()) {

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
				
				
				// MPI_1722_In_Out_Reports_04
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_1722_In_Out_Reports_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, punch in as an employee during the last week of the previous month and then, in the punch reports, validate the filter functionality by selecting the last week. Ensure that the corresponding punch record is displayed.");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,inouttime");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					String inouttime1 = data.get(2);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
					
		        
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.InOutReport()) {

						logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterButtonInOut()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.PunchTimeFirstRowinoutOnEmp(inouttime1)) {

						logResults.createLogs("Y", "PASS", "Punch In Record Displayed Successfully." + inouttime1 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch In Time Record." + meghpiattendancereport.getExceptionDesc());
					}

				}
				
				// MPI_1723_In_Out_Reports_05
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_1723_In_Out_Reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date and select punch in and out time .");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,inouttime,secondinouttime");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					String inouttime1 = data.get(2);
					String secondinouttime = data.get(3);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
			        String monthFirstWorkingDate = dateDetails.get("month1WorkingDate");


					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
					
		        
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.InOutReport()) {

						logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterButtonInOut()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromTimeSelected(inouttime1)) {

						logResults.createLogs("Y", "PASS", "FromTime Selected Successfully." + inouttime1);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToTimeTextField()) {

						logResults.createLogs("Y", "PASS", "To Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On To TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToTimeSelected(secondinouttime)) {

						logResults.createLogs("Y", "PASS", "ToTime Selected Successfully." + secondinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Time." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchTimeFirstRowinoutOnEmp(inouttime1)) {

						logResults.createLogs("Y", "PASS", "Punch In Record Displayed Successfully." + inouttime1 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch In Time Record." + meghpiattendancereport.getExceptionDesc());
					}

				}	
				
				//Attendance Report
				// MPI_1724_Attendance_Reports_01
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_1724_Attendance_Reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that the search functionality works correctly and ensure that employees can search their punch records using all available search options.");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"password,captcha,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,fourthinouttime,fourthmode,updateattendanceendpoint,empstatusonadmin,shiftstatus,empid");


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

					    String thirdinouttime3 = data.get(i++); // Punch Out time
					    String thirdmode = data.get(i++);    
				
					    
					   
					    String fourthinouttime4 = data.get(i++);
					    String fourthmode = data.get(i++);
				       
				      
				        String updateattendanceendpoint = data.get(i++);
				        String reportstatus = data.get(i++);
				        String shift = data.get(i++);
				       String empid = data.get(i++);
				        
				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month2WorkingDate = dateDetails.get("month2WorkingDate");

				       String UIdate = Pramod.convertToUIFormat(month2WorkingDate);
				        
						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
						
						
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

						String inouttime = month2WorkingDate + " " + inouttime1;
						String secondInOutTime = month2WorkingDate + " " + secondInOutTime2;
						
						String thirdinouttime =  month2WorkingDate + " " + thirdinouttime3;
						String fourthinouttime = month2WorkingDate + " " + fourthinouttime4;
						
						// get first day of month
											
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();


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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, empid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, empid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        } // Thread.sleep(5000);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.PunchInTimeCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Punch In Time Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch In Time Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchOutTimeCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Punch out Time Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch Out Time Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldAttendancerpt(inouttime1)) {

						logResults.createLogs("Y", "PASS", "Punch In Time Inputted Successfully." + inouttime1 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch In Time." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchinTimeRecordAttendancerptOnEmp(inouttime1)) {

						logResults.createLogs("Y", "PASS", "Punch In Record Displayed Successfully." + inouttime1 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch In Time Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldAttendancerpt(fourthinouttime4)) {

						logResults.createLogs("Y", "PASS", "Punch Out Inputted Successfully." + fourthinouttime4 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch Out Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.PunchOutTimeRecordAttendancerptOnEmp(fourthinouttime4)) {

						logResults.createLogs("Y", "PASS", "Punch OUt Record Displayed Successfully." + fourthinouttime4 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch Out Record." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchInTimeCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Punch In Time Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch In Time Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchOutTimeCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Punch out Time Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch Out Time Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ShiftCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Shift Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Shift Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.AttenStatusCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Attendance Status Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Attendance Status Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldAttendancerpt(shift)) {

						logResults.createLogs("Y", "PASS", "Punch Out Inputted Successfully." + shift );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch Out Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ShiftRecordAttendancerptOnEmp(shift)) {

						logResults.createLogs("Y", "PASS", "Inputted Shift Records Displayed Successfully." + shift );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Shift Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldAttendancerpt(reportstatus)) {

						logResults.createLogs("Y", "PASS", "Punch Out Inputted Successfully." + reportstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch Out Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.AttendanceStatusAttendancerptOnEmp(reportstatus)) {

						logResults.createLogs("Y", "PASS", "Inputted Status Records Displayed Successfully." + reportstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Status Record." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.ShiftCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Shift Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Shift Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.AttenStatusCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Attendance Status Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Attendance Status Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldAttendancerpt(UIdate)) {

						logResults.createLogs("Y", "PASS", "Attendance Date Inputted Successfully." + UIdate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Attendance Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.AttendanceDateRowOnEmp(UIdate)) {

						logResults.createLogs("Y", "PASS", "Inputted Attendance Date Records Displayed Successfully." + UIdate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Attendance Date Record." + meghpiattendancereport.getExceptionDesc());
					}
					
				}	
				
				
				// MPI_1725_Attendance_Reports_02
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_1725_Attendance_Reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,subject,ccmail");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					String subject = data.get(2);
					String ccmail = data.get(3) ;
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
					
		        
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}
					

					
					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
				
				
				
				// MPI_1727_Attendance_Reports_04
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_1727_Attendance_Reports_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, punch in as an employee during the last week of the previous month and then, in the punch reports, validate the filter functionality by selecting the last week. Ensure that the corresponding punch record is displayed.");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
			        String month2WorkingDate = dateDetails.get("month20WorkingDate");

				       String UIdate = Pramod.convertToUIFormat(month2WorkingDate);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		        
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.SearchTextFieldAttendancerpt(UIdate)) {

						logResults.createLogs("Y", "PASS", "Attendance Date Inputted Successfully." + UIdate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Attendance Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.AttendanceDateRowOnEmp(UIdate)) {

						logResults.createLogs("Y", "PASS", "Inputted Attendance Date Records Displayed Successfully." + UIdate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Attendance Date Record." + meghpiattendancereport.getExceptionDesc());
					}
				}		
				
				
				
				// MPI_1728_Attendance_Reports_05
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_1728_Attendance_Reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date and select punch in and out time .");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,inouttime,secondinouttime");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					String inouttime1 = data.get(2);
					String secondinouttime = data.get(3);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
			        String monthFirstWorkingDate = dateDetails.get("month1WorkingDate");

				       String UIdate = Pramod.convertToUIFormat(monthFirstWorkingDate);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);

		        
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromTimeSelected(inouttime1)) {

						logResults.createLogs("Y", "PASS", "FromTime Selected Successfully." + inouttime1);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToTimeTextField()) {

						logResults.createLogs("Y", "PASS", "To Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On To TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToTimeSelected(secondinouttime)) {

						logResults.createLogs("Y", "PASS", "ToTime Selected Successfully." + secondinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Time." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.AttendanceDateRowOnEmp(UIdate)) {

						logResults.createLogs("Y", "PASS", "Inputted Attendance Date Records Displayed Successfully." + UIdate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Attendance Date Record." + meghpiattendancereport.getExceptionDesc());
					}
				}			
				
				
				// MPI_1729_Attendance_Reports_06
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_1729_Attendance_Reports_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, select role, grade, team columns from edit columns feature and ensure selected columns are displayed in the table");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,role,password,firstname,pin");

					
					String empid = data.get(0);
					String captcha = data.get(1);
				
					String role = data.get(2);
					String password = data.get(3);
					String firstname = data.get(4);
					String pin = data.get(5);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
			MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
			MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
			
			
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

			
				if (EmployeePage.SearchTextField(firstname)) {
					logResults.createLogs("Y", "PASS",
							"Clicked On Search Text Field And Pass The Created Employee LastName Successfully." + firstname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On Search Text Field." + EmployeePage.getExceptionDesc());
				}
				

				if (EmployeePage.SearchResult()) {
					logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully." + firstname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Employee." + EmployeePage.getExceptionDesc());
				}

				if (EmployeePage.DotsMenu()) {
					logResults.createLogs("Y", "PASS", "Clicked On 3Dots Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error while Clicking On 3Dots." + EmployeePage.getExceptionDesc());
				}

				if (DepartmentPage.ManageProfileButton()) {
					logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton ."
							+ DepartmentPage.getExceptionDesc());
				}
				

				if (DepartmentPage.EditIcon()) {
					logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile ."
							+ DepartmentPage.getExceptionDesc());
				}

				if (AttendancePolicyPage.DeptDropdown(departmentname)) {
					logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + departmentname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Selecting Dept ." + AttendancePolicyPage.getExceptionDesc());
				}
		

				if (AttendancePolicyPage.DesignationDropdown(designationname)) {
					logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Selecting Designation From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
				}
		

				if (AttendancePolicyPage.TeamDropDown(teamname)) {
					logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Selecting Team From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
				}
				
				if (AttendancePolicyPage.GradeDropDown(gradename)) {
					logResults.createLogs("Y", "PASS", "Successfully Selected  Grade From Dropdown." + gradename);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Selecting Grade From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
				}

				if (RolePermissionpage.PinTextField(pin)) {
					logResults.createLogs("Y", "PASS", "Successfully Enter The Pin." + pin);
				} else {
					logResults.createLogs("Y", "FAIL", "Error while Inputting Pin ." + RolePermissionpage.getExceptionDesc());
				}
			

				if (RolePermissionpage.SaveChanges()) {
					logResults.createLogs("Y", "PASS", "SaveChanges Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SaveChanges Button." + RolePermissionpage.getExceptionDesc());
				}


				if (RolePermissionpage.AutoSetPolicy()) {
					logResults.createLogs("Y", "PASS", "AutoSetPolicy Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On AutoSetPolicy Button." + RolePermissionpage.getExceptionDesc());
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.RoleCheckbox()) {

						logResults.createLogs("Y", "PASS", "Role Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Role CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DepartmentCheckbox()) {

						logResults.createLogs("Y", "PASS", "Dept Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DesignationCheckbox()) {

						logResults.createLogs("Y", "PASS", "Designation Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Designation CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.TeamCheckbox()) {

						logResults.createLogs("Y", "PASS", "Team Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateRoleColumnOnEmp(role)) {

						logResults.createLogs("Y", "PASS", "Role Column Data Displayed Successfully." + role);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Role Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateDesignationColumnOnEmp( designationname)) {

						logResults.createLogs("Y", "PASS", "Designation Column Data Displayed Successfully." + designationname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Designation Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateDepartmentColumnOnEmp( departmentname)) {

						logResults.createLogs("Y", "PASS", "Department Column Data Displayed Successfully." + departmentname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Department Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateTeamColumnOnEmp(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Column Data Displayed Successfully." + teamname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Team Column Data." + meghpiattendancereport.getExceptionDesc());
					}
				}	
				
				// MPI_1731_Attendance_Reports_08
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_1731_Attendance_Reports_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the working hours and effective hours is displaying accurately by taking break.");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"captcha,workinghours,effectivehours,empid");

					
					String captcha = data.get(0);
					
				
					String workinghours = data.get(1);
					String effectivehours = data.get(2);
					String empid = data.get(3);
					
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
				    String month2WorkingDate = dateDetails.get("month2WorkingDate");
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
		
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.WorkingHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "WorkingHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting WorkingHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EffectiveHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "EffectiveHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EffectiveHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateEffectiveHourColumnOnEmp( effectivehours)) {

						logResults.createLogs("Y", "PASS", "EffectiveHour Column Data Displayed Successfully." + effectivehours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  EffectiveHour Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateWorkingHourColumnOnEmp( workinghours)) {

						logResults.createLogs("Y", "PASS", "WorkingHour Column Data Displayed Successfully." + workinghours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  WorkingHour Column Data." + meghpiattendancereport.getExceptionDesc());
					}
			
				}			
				
				
				// MPI_1732_Attendance_Reports_09
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
				public void MPI_1732_Attendance_Reports_09()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the early in and early out is displaying accurately by doing early in punch and early out punch.");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,earlyinhour,earlyouthour");

					
					String empid = data.get(0);
					String captcha = data.get(1);
				
					String earlyinhours = data.get(2);
					String earlyouthours = data.get(3);
					
					  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String month2WorkingDate = dateDetails.get("month2WorkingDate");

						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EarlyInHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "EarlyInHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EarlyInHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EarlyOutHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "EarlyOutHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EarlyOutHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateEarlyInHourColumnOnEmp( earlyinhours)) {

						logResults.createLogs("Y", "PASS", "Early in hours Column Data Displayed Successfully." + earlyinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Early in hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateEarlyOutHourColumnOnEmp( earlyouthours)) {

						logResults.createLogs("Y", "PASS", "Early Out Hours Column Data Displayed Successfully." + earlyouthours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Early Out Hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
			
				}	
				
				// MPI_1733_Attendance_Reports_10
				@Test(enabled = true, priority = 17, groups = { "Smoke" })
				public void MPI_1733_Attendance_Reports_10()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the late in and late out is displaying accurately by doing late in punch and late out punch");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"password,captcha,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,fourthinouttime,fourthmode,updateattendanceendpoint,empid,lateinhours,lateouthours");


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

					    String thirdinouttime3 = data.get(i++); // Punch Out time
					    String thirdmode = data.get(i++);    
				
					    
					   
					    String fourthinouttime4 = data.get(i++);
					    String fourthmode = data.get(i++);
				       
				      
				        String updateattendanceendpoint = data.get(i++);
				       
				       String empid = data.get(i++);
				       String lateinhours = data.get(i++);
				       String lateouthours = data.get(i++);
				        
				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month3WorkingDate = dateDetails.get("month3WorkingDate");

				        
						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
						
						
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

						String inouttime = month3WorkingDate + " " + inouttime1;
						String secondInOutTime = month3WorkingDate + " " + secondInOutTime2;
						
						String thirdinouttime =  month3WorkingDate + " " + thirdinouttime3;
						String fourthinouttime = month3WorkingDate + " " + fourthinouttime4;
						
						// get first day of month
											
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
					
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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, empid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, empid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        } // Thread.sleep(5000);
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
								"Login Is Failed." + meghloginpage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month3WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month3WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month3WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month3WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.LateInHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "LateInHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting LateInHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.LateOutHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "LateOutHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting LateOutHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateLateInHourColumnOnEmp( lateinhours)) {

						logResults.createLogs("Y", "PASS", "Late in hours Column Data Displayed Successfully." + lateinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Late in hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateLateOutHourColumnOnEmp( lateouthours)) {

						logResults.createLogs("Y", "PASS", "late Out Hours Column Data Displayed Successfully." + lateouthours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Late Out Hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
			
				}		
				
				
				// MPI_1734_Attendance_Reports_11
				@Test(enabled = true, priority = 18, groups = { "Smoke" })
				public void MPI_1734_Attendance_Reports_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, Take a break as a employee and ensure lunch in and lunch out time is displayed as per recorded");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,lunchintime,lunchouttime");

					
					String empid = data.get(0);
					String captcha = data.get(1);
				
					String lunchintime = data.get(2);
					String lunchouttime = data.get(3);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
			        String month3WorkingDate = dateDetails.get("month3WorkingDate");

					String monthName = dateDetails.get("monthName");
					String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month3WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month3WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month3WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month3WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}


					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.LunchInTimeCheckbox()) {

						logResults.createLogs("Y", "PASS", "LunchInTime Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting LunchInTime CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.LunchOutTimeCheckbox()) {

						logResults.createLogs("Y", "PASS", "LunchOutTime Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting LunchOutTime CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateLunchInTimeColumnOnEmp( lunchintime)) {

						logResults.createLogs("Y", "PASS", "Lunch Time in hours Column Data Displayed Successfully." + lunchintime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Lunch Time in hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateLunchOutTimeColumnOnEmp( lunchouttime)) {

						logResults.createLogs("Y", "PASS", "Lunch Time Out Hours Column Data Displayed Successfully." + lunchouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Lunch Time Out Hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
			
				}		
				
				
				// MPI_1735_Attendance_Reports_12
				@Test(enabled = true, priority = 19, groups = { "Smoke" })
				public void MPI_1735_Attendance_Reports_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check that the status is present and that overtime is displayed accurately.");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,empstatusonadmin,totothour");

					
					String empid = data.get(0);
					String captcha = data.get(1);
				
					String status = data.get(2);
					String totothour = data.get(3);
					
					  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month3WorkingDate = dateDetails.get("month3WorkingDate");

						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
					
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month3WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month3WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month3WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month3WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.OTHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "OTHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting OTHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.OverStayCheckBox()) {

						logResults.createLogs("Y", "PASS", "OverStay Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting OverStay CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateOverStayHourColumnOnEmp( totothour)) {

						logResults.createLogs("Y", "PASS", "OverStay Hours Column Data Displayed Successfully." + totothour);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Over Stay hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateOverTimeHourColumnOnEmp( totothour)) {

						logResults.createLogs("Y", "PASS", "OT Hours Column Data Displayed Successfully." + totothour);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  OT Hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateFinalStatusColumnOnEmp( status)) {

						logResults.createLogs("Y", "PASS", "Status Column Data Displayed Successfully." + status);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Status Column Data." + meghpiattendancereport.getExceptionDesc());
					}
			
				}	
				
				
				// MPI_1736_Attendance_Reports_13
				@Test(enabled = true, priority = 20, groups = { "Smoke" })
				public void MPI_1736_Attendance_Reports_13()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check that the device name is displayed in the â€˜In Deviceâ€™ column where the employee punched in, and that the break hours are displayed accurately in break hour column.");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,breakhours");

					
					String empid = data.get(0);
					String captcha = data.get(1);
				
					String breakhours = data.get(2);
					  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month3WorkingDate = dateDetails.get("month3WorkingDate");

						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
						
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month3WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month3WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month3WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month3WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DeviceInCheckbox()) {

						logResults.createLogs("Y", "PASS", "DeviceIn Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting DeviceIn CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.BreakHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "BreakHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting BreakHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateBreakHourColumnOnEmp( breakhours)) {

						logResults.createLogs("Y", "PASS", "Break Hours Column Data Displayed Successfully." + breakhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Break hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateDeviceInColumnOnEmp( devicename)) {

						logResults.createLogs("Y", "PASS", "Device Name Column Data Displayed Successfully." + devicename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Device Name Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					
				}	
				
				
				// MPI_1737_Attendance_Reports_14
				@Test(enabled = true, priority = 21, groups = { "Smoke" })
				public void MPI_1737_Attendance_Reports_14()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, apply a sick leave and ensure that the same leave type is displayed for the employee in the â€˜Leave Typeâ€™ column.");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"password,captcha,leavereason,leavename,firstname,empid");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String LeaveReason = data.get(2);
					
					String leavename = data.get(3);
					String  firstname = data.get(4);
					String empid = data.get(5);
					
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month4WorkingDate = dateDetails.get("month4WorkingDate");

						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();

		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
		
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);
	
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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
		
		if (meghleavepage.FromDateOnAdminSelected(month4WorkingDate)) {

			logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully ." + month4WorkingDate);
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
		
		if (meghleavepage.ToDateTextFieldInAdminSelected(month4WorkingDate)) {

			logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully ." + month4WorkingDate);
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

			
			if (RolePermissionpage.AttendanceReport()) {

				logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
			}

			if (meghpiattendancereport.AttendanceReportButton()) {

				logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.FilterButtonAttendancerpt()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
			if (meghpiattendancereport.FromDateDropDown()) {

				logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.FromDateSelected(month4WorkingDate)) {

				logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month4WorkingDate);
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
			
			if (meghpiattendancereport.ToDateSelected(month4WorkingDate)) {

				logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month4WorkingDate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiattendancereport.FromTimeTextField()) {

				logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
			}


			if (meghpiattendancereport.FilterSaveButton()) {

				logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiattendancereport.EditColumnButton()) {

				logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiattendancereport.LeaveTypeCheckbox()) {

				logResults.createLogs("Y", "PASS", "LeaveType Checkbox Selected Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting LeaveType CheckBox." + meghpiattendancereport.getExceptionDesc());
			}
			
			
			if (meghpiattendancereport.SaveChangesButton()) {

				logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
			}

			if (meghpiattendancereport.validateLeaveTypeColumnOnEmp( leavename)) {

				logResults.createLogs("Y", "PASS", "Break Hours Column Data Displayed Successfully." + leavename);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Break hours Column Data." + meghpiattendancereport.getExceptionDesc());
			}	
		}			
				
				
				// MPI_1738_Attendance_Reports_15
				@Test(enabled = true, priority = 22, groups = { "Smoke" })
				public void MPI_1738_Attendance_Reports_15()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check that the assigned shift and its timings are displayed accurately.");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"captcha,shiftstatus,shiftintime,shiftouttime,empid");

					
					String captcha = data.get(0);
				
					String shift = data.get(1);
					String shiftintime = data.get(2);
					String shiftouttime = data.get(3);
					String empid = data.get(4);
					
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month3WorkingDate = dateDetails.get("month3WorkingDate");

						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month3WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month3WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month3WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month3WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}


					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ShiftInTimeCheckbox()) {

						logResults.createLogs("Y", "PASS", "ShiftInTime Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting ShiftInTime CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ShiftOutTimeCheckbox()) {

						logResults.createLogs("Y", "PASS", "ShiftOutTime Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting ShiftOutTime CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateShiftColumnOnEmp( shift)) {

						logResults.createLogs("Y", "PASS", "shift Column Data Displayed Successfully." + shift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  shift Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateShiftOutTimeColumnOnEmp( shiftouttime)) {

						logResults.createLogs("Y", "PASS", "shift Out time Column Data Displayed Successfully." + shiftouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  shift Out time Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateShiftInTimeColumnOnEmp( shiftintime)) {

						logResults.createLogs("Y", "PASS", "shift intime Column Data Displayed Successfully." + shiftintime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  shift intime Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					
				}				
				
				/*    by me because these tab isn't access by emp
				 
				// MPI_1741_MissingPunch_Reports_01
				@Test(enabled = true, priority = 23, groups = { "Smoke" })
				public void MPI_1741_MissingPunch_Reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that the search functionality works correctly and ensure that employees can search their punch records using all available search options.");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,shiftstatus,empstatusonadmin");

					
					String empid = data.get(0);
					String captcha = data.get(1);
				
					String shift = data.get(2);
					String reportstatus = data.get(3);
					
			
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ShiftCheckBoxMissingPunchRpt()) {

						logResults.createLogs("Y", "PASS", "Shift Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Shift Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.AttenFinalStatusCheckBoxMissingPunchrpt()) {

						logResults.createLogs("Y", "PASS", "Final Status Search Option Checkbox Selected Successfully:" );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Final Status Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.MissingPunchSearchField(shift)) {

						logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully." + shift );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ShiftRecordInMissingPunch(shift)) {

						logResults.createLogs("Y", "PASS", "Assigned Shift Record Displayed Successfully." + shift );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Assigned Shift Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MissingPunchSearchField(reportstatus)) {

						logResults.createLogs("Y", "PASS", "Status Name Inputted Successfully." + reportstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Status Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.AttendanceFinalStatusRecordInMissingPunch(reportstatus)) {

						logResults.createLogs("Y", "PASS", "Final Status Record Displayed Successfully." + reportstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Final Status Record." + meghpiattendancereport.getExceptionDesc());
					}
		
				}		
				
				// MPI_1742_MissingPunch_Reports_02
				@Test(enabled = true, priority = 24, groups = { "Smoke" })
				public void MPI_1742_MissingPunch_Reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,subject,ccmail");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					String subject = data.get(2);
					String ccmail = data.get(3) ;



					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
					
		        
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.MailButtonMissingPunch()) {

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
				
				
				// MPI_1744_MissingPunch_Reports_04
				@Test(enabled = true, priority = 25, groups = { "Smoke" })
				public void MPI_1744_MissingPunch_Reports_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, Do single punch in last month and validate the missing punch record is displayed.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,updateattendanceendpoint,empid,captcha,shiftstatus,empstatusonadmin");


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
					  

				      
				        String updateattendanceendpoint = data.get(i++);
				        String empid = data.get(i++);
				       String captcha = data.get(i++);
				       String shift = data.get(i++);
				       String reportstatus  = data.get(i++);
				       
				       
				     
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String month5WorkingDate = dateInfo.get("month5WorkingDate");
					    
						String inouttime = month5WorkingDate + " " + inouttime1;
					
						String firstDayOfMonth = dateInfo.get("firstDayOfMonth");
						String monthName = dateInfo.get("monthName");
						String year = dateInfo.get("year");
						
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);



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
					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
					}
					
				
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.ShiftCheckBoxMissingPunchRpt()) {

						logResults.createLogs("Y", "PASS", "Shift Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Shift Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.MissingPunchSearchField(shift)) {

						logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully." + shift );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ShiftRecordInMissingPunch(shift)) {

						logResults.createLogs("Y", "PASS", "Assigned Shift Record Displayed Successfully." + shift );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Assigned Shift Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.AttendanceFinalStatusRecordInMissingPunch(reportstatus)) {

						logResults.createLogs("Y", "PASS", "Final Status Record Displayed Successfully." + reportstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Final Status Record." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MissPunchEmpAttendanceTime(inouttime1)) {

						logResults.createLogs("Y", "PASS", "In Punch Validated Successfully." + inouttime1 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp In Punch." + meghpiattendancereport.getExceptionDesc());
					}
				
				}
				
				
				// MPI_1745_MissingPunch_Reports_05
				@Test(enabled = true, priority = 26, groups = { "Smoke" })
				public void MPI_1745_MissingPunch_Reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific date ensure specific date missing punch record is displayed ");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,inouttime,secondinouttime,empstatusonadmin");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					String inouttime = data.get(2);
					String secondinouttime = data.get(3);
					String reportstatus  = data.get(4);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
			        String month5WorkingDate = dateDetails.get("month5WorkingDate");

					String monthName = dateDetails.get("monthName");
					String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FromDateSelected(month5WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month5WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month5WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month5WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromTimeSelected(inouttime)) {

						logResults.createLogs("Y", "PASS", "FromTime Selected Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToTimeTextField()) {

						logResults.createLogs("Y", "PASS", "To Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On To TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToTimeSelected(secondinouttime)) {

						logResults.createLogs("Y", "PASS", "ToTime Selected Successfully." + secondinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Time." + meghpiattendancereport.getExceptionDesc());
					}
				
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.AttendanceFinalStatusRecordInMissingPunch(reportstatus)) {

						logResults.createLogs("Y", "PASS", "Final Status Record Displayed Successfully." + reportstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Final Status Record." + meghpiattendancereport.getExceptionDesc());
					}
				}	
				
				// MPI_1746_MissingPunch_Reports_06
				@Test(enabled = true, priority = 27, groups = { "Smoke" })
				public void MPI_1746_MissingPunch_Reports_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, do punch in from web and ensure \"in punch from\" displaying as \"web\" ");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,inpunchfrom,baseuri,loginendpoint,updateattendanceendpoint,password");

					
					String empid = data.get(0);
					String captcha = data.get(1);
		
					String inpunchfrom = data.get(2);
					String baseuri = data.get(3);
					String loginendpoint = data.get(4);
				String updateattendanceendpoint = data.get(5);
				String password = data.get(6);
				
				Map<String, String> dateInfo = Pramod.getCurrentDateDetails();

				String currentDate = dateInfo.get("currentDate");
				String monthName = dateInfo.get("monthName");
				String year = dateInfo.get("year");
				
				Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
			String firstdayofMonth = dateDetails.get("firstDayOfMonth");
				
				
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
				MeghLoginTest meghlogintest = new MeghLoginTest();

			 if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed." + meghloginpage.getExceptionDesc());
				}
		
			if (meghpiattendancereport.CheckInButton()) {

				logResults.createLogs("Y", "PASS", "CheckIn Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CheckIn Button." + meghpiattendancereport.getExceptionDesc());
			}

			
			
			
			 // Trigger attendance update first
		    Response updateResp = apiPage.executeUpdateAttendance(
		            baseuri, loginendpoint,
		            Emailid, password, cmpcode,
		            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
		            empid, firstdayofMonth + "T00:00:00.000Z"
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
					
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(currentDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + currentDate);
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
					
					if (meghpiattendancereport.ToDateSelected(currentDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + currentDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EditColumnButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.InPunchFromCheckBox()) {

						logResults.createLogs("Y", "PASS", "InPunchFrom Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting InPunchFrom CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.validateInPunchFromRowInMissingPunchReport(empid, inpunchfrom)) {

						logResults.createLogs("Y", "PASS", "In Punch From Column Data Displayed Successfully." + inpunchfrom);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  In Punch From Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					
				}	
				
				// MPI_1747_MissingPunch_Reports_07
				@Test(enabled = true, priority = 28, groups = { "Smoke" })
				public void MPI_1747_MissingPunch_Reports_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, for the single punch in Missing Punch, validate that the In-Time is displayed correctly for the given date. ");

					ArrayList<String> data = initBase.loadExcelData("EmpAttendanceReports", currTC,
							"empid,captcha,inouttime,empstatusonadmin");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					String inouttime = data.get(2);
					String reportstatus  = data.get(3);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
			        String month5WorkingDate = dateDetails.get("month5WorkingDate");
			     String uidate = Pramod.convertToUIFormat(month5WorkingDate);

					String monthName = dateDetails.get("monthName");
					String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage= new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FromDateSelected(month5WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month5WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month5WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month5WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.AttendanceFinalStatusRecordInMissingPunch(reportstatus)) {

						logResults.createLogs("Y", "PASS", "Final Status Record Displayed Successfully." + reportstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Final Status Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MissPunchEmpAttendanceTime(inouttime)) {

						logResults.createLogs("Y", "PASS", "In Punch Validated Successfully." + inouttime );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp In Punch." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MissPunchEmpAttendanceDate(uidate)) {

						logResults.createLogs("Y", "PASS", "Attendance Date Validated Successfully." + uidate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Attendance Date." + meghpiattendancereport.getExceptionDesc());
					}
				}	
					
				*/
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
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
	
	

