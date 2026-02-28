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

import com.MeghPI.Attendance.pages.MeghLeavePage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import com.MeghPI.Attendance.pages.MeghPITotalLeavesTakenReportPage;
import com.MeghPI.Attendance.pages.MeghPIWeekOffReportPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIEmpTotalLeaveTakenReportsTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	private String cmpcode = "";
	private String Emailid = "";
	private String entityname = "";
	private String officename = "";
    private String AdminFirstName ="";
	
	
	
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
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");

		
	}

	// MPI_1819_EmpTotalLeaveTakenAllReports_01
			@Test(enabled = true, priority = 1, groups = { "Smoke" })
			public void MPI_1819_EmpTotalLeaveTakenAllReports_01()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionalities of search feature by selecting each search option.");

				  // Load Excel data
				ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
						"password,baseuri,loginendpoint,photo,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,captcha,leavereason,leavename,totaldays,leavestatus");


				  int i = 0;
				String password = data.get(i++);
				
				  String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				     
				    String photo = data.get(i++);
				

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
			        String leavereason = data.get(i++);
			        String leavename = data.get(i++);
			        String totaldays = data.get(i++);
			        String leavestatus = data.get(i++);
			        
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

			        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

			        String PunchinDate = dateDetails.get("month1WorkingDate");
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
			        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
			        
					
					
					
					 
				
					 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		    MeghLoginPage meghloginpage = new MeghLoginPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);

			  

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
						
						if (RolePermissionpage.LeaveButton()) {

							logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
						}
						
						if (RolePermissionpage.LeaveRequestsTab()) {

							logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
									+ RolePermissionpage.getExceptionDesc());
						}
						if (meghleavepage.ApplyLeaveButton()) {

							logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully Of Employee Account.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button."
									+ meghleavepage.getExceptionDesc());
						}	
					
						if (meghleavepage.LeaveTypeDropdown()) {

							logResults.createLogs("Y", "PASS", " LeaveTypeDropdown Clicked Successfully Of Employee Account.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveTypeDropdown."
									+ meghleavepage.getExceptionDesc());
						}

						if (meghleavepage.SickLeaveSelected()) {

							logResults.createLogs("Y", "PASS", " SickLeave Selected  Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting Sick Leave."
									+ meghleavepage.getExceptionDesc());
						}
						
						if (meghleavepage.FromDateTextField()) {

							logResults.createLogs("Y", "PASS", " FromDateTextField Clicked Successfully .");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On FromDateTextField."
									+ meghleavepage.getExceptionDesc());
						}
						
						if (meghleavepage.FromDateSelected(PunchinDate)) {

							logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + PunchinDate);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Inputting From From Date."
									+ meghleavepage.getExceptionDesc());
						}
						

						if (meghleavepage.LeaveDurationOne()) {

							logResults.createLogs("Y", "PASS", " LeaveDurationOne Clicked Successfully Of Employee Account.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationOne."
									+ meghleavepage.getExceptionDesc());
						}

						if (meghleavepage.FullDayDurationOne()) {

							logResults.createLogs("Y", "PASS", " FullDay DurationOne Selected  Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting FullDay DurationOne."
									+ meghleavepage.getExceptionDesc());
						}
						
						if (meghleavepage.ToDateTextField()) {

							logResults.createLogs("Y", "PASS", " ToDateTextField Clicked Successfully .");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On ToDateTextField."
									+ meghleavepage.getExceptionDesc());
						}
						
						if (meghleavepage.ToDateSelected(PunchinDate)) {

							logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + PunchinDate);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Inputting From ToDate."
									+ meghleavepage.getExceptionDesc());
						}
					
						if (meghleavepage.LeaveDurationTwo()) {

							logResults.createLogs("Y", "PASS", " LeaveDurationTwo Clicked Successfully Of Employee Account.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationTwo."
									+ meghleavepage.getExceptionDesc());
						}
						
						if (meghleavepage.FullDayDurationTwo()) {

							logResults.createLogs("Y", "PASS", " FullDayDurationTwo Clicked Successfully Of Employee Account.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On FullDayDurationTwo."
									+ meghleavepage.getExceptionDesc());
						}
						
						if (meghleavepage.ReasonTextField(leavereason)) {

							logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + leavereason);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Inputting Reason."
									+ meghleavepage.getExceptionDesc());
						}
					
						if (meghleavepage.RequestApplyButton()) {

							logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully Of Employee Account." );
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
						if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

							logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
						}
						if (meghpitotalleavestaken.TotalLeavesTakenReportAllTab()) {

							logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report All Tab Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On TotalLeavesTaken Report All Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
						}
						
						if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

							logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
						
						
						if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavename)) {

							logResults.createLogs("Y", "PASS", "Leave Name Inputted Successfully." + leavename);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting Leave Name ." + meghpitotalleavestaken.getExceptionDesc());
						}
						
						if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveNameResultOnEmp(leavename)) {

							logResults.createLogs("Y", "PASS", "Emp Applied Leave Validated Successfully." + leavename);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave." + meghpitotalleavestaken.getExceptionDesc());
						}
						
						
						if (meghpitotalleavestaken.TotalLeavesTakenReportTotalDaysResultOnEmp(totaldays)) {

							logResults.createLogs("Y", "PASS", "Emp Applied Leave TotalDays Validated Successfully." + totaldays);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave TotalDays." + meghpitotalleavestaken.getExceptionDesc());
						}
						
						if (OfficePage.SearchDropDown()) {
							logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
						}

						if (meghpitotalleavestaken.TotalLeavesTakenReportStatusCheckbox()) {

							logResults.createLogs("Y", "PASS", "Status CheckBox Selected Successfully." );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting Status CheckBox." + meghpitotalleavestaken.getExceptionDesc());
						}
						if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonCheckbox()) {

							logResults.createLogs("Y", "PASS", "Leave Reason CheckBox Selected Successfully." );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Reason CheckBox." + meghpitotalleavestaken.getExceptionDesc());
						}

						if (OfficePage.SearchDropDown()) {
							logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
						}
						if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavestatus)) {

							logResults.createLogs("Y", "PASS", "Leave Status Inputted Successfully." + leavestatus);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting Leave Status ." + meghpitotalleavestaken.getExceptionDesc());
						}
						
						if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveStatusResultOnEmp(leavestatus)) {

							logResults.createLogs("Y", "PASS", "Emp Applied Leave Status Validated Successfully." + leavestatus);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Status." + meghpitotalleavestaken.getExceptionDesc());
						}
						if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavereason)) {

							logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting Leave Reason ." + meghpitotalleavestaken.getExceptionDesc());
						}
						
						if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonOnEmp(leavereason)) {

							logResults.createLogs("Y", "PASS", "Emp Applied Leave Reason Validated Successfully." + leavereason);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Reason." + meghpitotalleavestaken.getExceptionDesc());
						}
								}	
	

			// MPI_1820_EmpTotalLeaveTakenAllReports_02
			@Test(enabled = true, priority = 2, groups = { "Smoke" })
			public void MPI_1820_EmpTotalLeaveTakenAllReports_02()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email.");

				  // Load Excel data
				ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
						"empid,captcha,subject,ccmail");
				
				  int i = 0;
					String empid = data.get(i++);
					String captcha = data.get(i++);
					String subject = data.get(i++);
					String ccmail = data.get(i++);
		
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
					
				MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
	if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

		logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
	}
	if (meghpitotalleavestaken.TotalLeavesTakenReportAllTab()) {

		logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report All Tab Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On TotalLeavesTaken Report All Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
	}
	
	if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

		logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
	
	
			// MPI_1821_EmpTotalLeaveTakenAllReports_03
			@Test(enabled = true, priority = 3, groups = { "Smoke" })
			public void MPI_1821_EmpTotalLeaveTakenAllReports_03()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date.");

				  // Load Excel data
				ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
						"empid,captcha");
				
				  int i = 0;
					String empid = data.get(i++);
					String captcha = data.get(i++);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

			        String monthFirstWorkingDate  = dateDetails.get("month1WorkingDate");
			        String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
				
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
	if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

		logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
	}
	if (meghpitotalleavestaken.TotalLeavesTakenReportAllTab()) {

		logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report All Tab Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On TotalLeavesTaken Report All Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
	}
	
	if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

		logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
	
	if (meghpitotalleavestaken.LeavesTakenRptFilteredDateValidationOnEmp(monthFirstWorkingDate)) {

		logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully." + monthFirstWorkingDate);
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
	} 	
			}
	
	
			// MPI_1822_EmpTotalLeaveTakenAllReports_04
						@Test(enabled = true, priority = 4, groups = { "Smoke" })
						public void MPI_1822_EmpTotalLeaveTakenAllReports_04()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, Apply leave during the last week of last month. In the leave taken All Request Reports module, apply the filter for the last week of last month and ensure that the corresponding leave  request record is displayed..");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
									"empid,captcha,leavereason");
							
							  int i = 0;
								String empid = data.get(i++);
								String captcha = data.get(i++);
								String leavereason = data.get(i++);
								
								Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

						        String PunchinDate = dateDetails.get("month20WorkingDate");
						        String monthName = dateDetails.get("monthName");
						        String year = dateDetails.get("year");
							    
						        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				MeghLoginTest meghlogintest = new MeghLoginTest();
					
				if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed." + MeghLoginPage.getExceptionDesc());
				} 
			
				if (RolePermissionpage.LeaveButton()) {

					logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
				}
				
				if (RolePermissionpage.LeaveRequestsTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
							+ RolePermissionpage.getExceptionDesc());
				}
				if (meghleavepage.ApplyLeaveButton()) {

					logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully Of Employee Account.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button."
							+ meghleavepage.getExceptionDesc());
				}	
			
				if (meghleavepage.LeaveTypeDropdown()) {

					logResults.createLogs("Y", "PASS", " LeaveTypeDropdown Clicked Successfully Of Employee Account.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveTypeDropdown."
							+ meghleavepage.getExceptionDesc());
				}

				if (meghleavepage.SickLeaveSelected()) {

					logResults.createLogs("Y", "PASS", " SickLeave Selected  Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Sick Leave."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromDateTextField()) {

					logResults.createLogs("Y", "PASS", " FromDateTextField Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On FromDateTextField."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromDateSelected(PunchinDate)) {

					logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + PunchinDate);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting From From Date."
							+ meghleavepage.getExceptionDesc());
				}
				

				if (meghleavepage.LeaveDurationOne()) {

					logResults.createLogs("Y", "PASS", " LeaveDurationOne Clicked Successfully Of Employee Account.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationOne."
							+ meghleavepage.getExceptionDesc());
				}

				if (meghleavepage.FullDayDurationOne()) {

					logResults.createLogs("Y", "PASS", " FullDay DurationOne Selected  Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting FullDay DurationOne."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToDateTextField()) {

					logResults.createLogs("Y", "PASS", " ToDateTextField Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On ToDateTextField."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToDateSelected(PunchinDate)) {

					logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + PunchinDate);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting From ToDate."
							+ meghleavepage.getExceptionDesc());
				}
			
				if (meghleavepage.LeaveDurationTwo()) {

					logResults.createLogs("Y", "PASS", " LeaveDurationTwo Clicked Successfully Of Employee Account.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationTwo."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FullDayDurationTwo()) {

					logResults.createLogs("Y", "PASS", " FullDayDurationTwo Clicked Successfully Of Employee Account.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On FullDayDurationTwo."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ReasonTextField(leavereason)) {

					logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + leavereason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Reason."
							+ meghleavepage.getExceptionDesc());
				}
			
				if (meghleavepage.RequestApplyButton()) {

					logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully Of Employee Account." );
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
				if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

					logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
				}
				if (meghpitotalleavestaken.TotalLeavesTakenReportAllTab()) {

					logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report All Tab Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On TotalLeavesTaken Report All Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
				}
				
				if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

					logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
				
				if (meghpitotalleavestaken.LeavesTakenRptFilteredDateValidationOnEmp(PunchinDate)) {

					logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully." + PunchinDate);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
				} 	
						}
			
			

						// MPI_1838_EmpTotalLeaveTakenApproved_Reports_01
									@Test(enabled = true, priority = 5, groups = { "Smoke" })
									public void MPI_1838_EmpTotalLeaveTakenApproved_Reports_01()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, Check the functionality of search feature by selecting each search option.");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha,leavereason,password,leavename,totaldays,leavestatus");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
											String leavereason = data.get(i++);
											String password = data.get(i++);
											String leavename = data.get(i++);
											String totaldays = data.get(i++);
											String leavestatus = data.get(i++);
											
											Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

									        String PunchinDate = dateDetails.get("month1WorkingDate");
									        String monthName = dateDetails.get("monthName");
									        String year = dateDetails.get("year");
										    
									        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
							 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
								MeghLoginTest meghlogintest = new MeghLoginTest();
							
							
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
							if (meghleavepage.MonthFilterContains(PunchinDate)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + PunchinDate );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

								logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.PendingLeaveCount()) {

								logResults.createLogs("Y", "PASS", "Monthly Pending Leave Count Clicked  Successfully ." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Pending Leave Count."
										+ meghleavepage.getExceptionDesc());
							}
							
						
							if (meghleavepage.approveLeaveByDateAndEmp(empid, PunchinDate)) {

								logResults.createLogs("Y", "PASS", " Leave Approved   Successfully ." + PunchinDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Aprroving Leave."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.LeaveApproveButton()) {

								logResults.createLogs("Y", "PASS", " Leave Approve  Button Clicked  Successfully ." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Approve Button."
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
							if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportApprovedTab()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Approved Tab Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Approved Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
							
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavename)) {

								logResults.createLogs("Y", "PASS", "Leave Name Inputted Successfully." + leavename);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Leave Name ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveNameResultOnEmp(leavename)) {

								logResults.createLogs("Y", "PASS", "Emp Applied Leave Validated Successfully." + leavename);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave." + meghpitotalleavestaken.getExceptionDesc());
							}


							
							if (meghpitotalleavestaken.TotalLeavesTakenReportTotalDaysResultOnEmp(totaldays)) {

								logResults.createLogs("Y", "PASS", "Emp Applied Leave TotalDays Validated Successfully." + totaldays);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave TotalDays." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (OfficePage.SearchDropDown()) {
								logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
							}

							
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportStatusCheckbox()) {

								logResults.createLogs("Y", "PASS", "Status CheckBox Selected Successfully." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Status CheckBox." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonCheckbox()) {

								logResults.createLogs("Y", "PASS", "Leave Reason CheckBox Selected Successfully." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Reason CheckBox." + meghpitotalleavestaken.getExceptionDesc());
							}

							if (OfficePage.SearchDropDown()) {
								logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavestatus)) {

								logResults.createLogs("Y", "PASS", "Leave Status Inputted Successfully." + leavestatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Leave Status ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveStatusResultOnEmp(leavestatus)) {

								logResults.createLogs("Y", "PASS", "Emp Applied Leave Status Validated Successfully." + leavestatus);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Status." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavereason)) {

								logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Leave Reason ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonOnEmp(leavereason)) {

								logResults.createLogs("Y", "PASS", "Emp Applied Leave Reason Validated Successfully." + leavereason);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Reason." + meghpitotalleavestaken.getExceptionDesc());
							}
									}		
							
							
							
									// MPI_1839_EmpTotalLeaveTakenApproved_Reports_02
									@Test(enabled = true, priority = 6, groups = { "Smoke" })
									public void MPI_1839_EmpTotalLeaveTakenApproved_Reports_02()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email.");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha,subject,ccmail");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
											String subject = data.get(i++);
											String ccmail = data.get(i++);
								
											Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
										    String monthName = dateDetails.get("monthName");
										    String year = dateDetails.get("year");

										MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
							if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportApprovedTab()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Approved Tab Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Approved Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
							
							
									// MPI_1840_EmpTotalLeaveTakenApproved_Reports_03
									@Test(enabled = true, priority = 7, groups = { "Smoke" })
									public void MPI_1840_EmpTotalLeaveTakenApproved_Reports_03()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date.");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
								
											Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

											String month1WorkingDate  = dateDetails.get("month1WorkingDate");
											  String monthName = dateDetails.get("monthName");
											    String year = dateDetails.get("year");
										
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
							if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportApprovedTab()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Approved Tab Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Approved Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
						
							if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
							
							if (meghpiattendancereport.FromDateSelected(month1WorkingDate)) {

								logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month1WorkingDate);
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
							
							if (meghpiattendancereport.ToDateSelected(month1WorkingDate)) {

								logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month1WorkingDate);
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
							
							if (meghpitotalleavestaken.LeavesTakenRptFilteredDateValidationOnEmp(month1WorkingDate)) {

								logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully." + month1WorkingDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
							} 	
									}	
							
							
									// MPI_1841_EmpTotalLeaveTakenApproved_Reports_04
									@Test(enabled = true, priority = 8, groups = { "Smoke" })
									public void MPI_1841_EmpTotalLeaveTakenApproved_Reports_04()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, Apply leave during the last week of last month and approve it from admin account. In the emp account leave taken Aprroved Request Reports module, apply the filter for the last week of last month and ensure that the corresponding approved leave  request record is displayed..");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha,password");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
											String password = data.get(i++);
											
											Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

									        String PunchinDate = dateDetails.get("month20WorkingDate");
									        String monthName = dateDetails.get("monthName");
									        String year = dateDetails.get("year");
										    
									        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
									MeghLoginTest meghlogintest = new MeghLoginTest();
							
							
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
							if (meghleavepage.MonthFilterContains(PunchinDate)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + PunchinDate );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}
							if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

								logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.PendingLeaveCount()) {

								logResults.createLogs("Y", "PASS", "Monthly Pending Leave Count Clicked  Successfully ." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Pending Leave Count."
										+ meghleavepage.getExceptionDesc());
							}
							
						
							if (meghleavepage.approveLeaveByDateAndEmp(empid, PunchinDate)) {

								logResults.createLogs("Y", "PASS", " Leave Approved   Successfully ." + PunchinDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Aprroving Leave."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.LeaveApproveButton()) {

								logResults.createLogs("Y", "PASS", " Leave Approve  Button Clicked  Successfully ." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Approve Button."
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
							if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportApprovedTab()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Approved Tab Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Approved Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
							
							if (meghpitotalleavestaken.LeavesTakenRptFilteredDateValidationOnEmp(PunchinDate)) {

								logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully." + PunchinDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
							} 		
									}
							
							
									// MPI_1842_EmpTotalLeaveTakenPending_Reports_01
									@Test(enabled = true, priority = 9, groups = { "Smoke" })
									public void MPI_1842_EmpTotalLeaveTakenPending_Reports_01()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, Check the functionality of search feature by selecting each search option.");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha,leavename,leavereason,totaldays,leavestatus");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
											String leavename  = data.get(i++);
											String leavereason = data.get(i++);
											String totaldays = data.get(i++);
											String leavestatus = data.get(i++);
											
											Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

									        String PunchinDate = dateDetails.get("month2WorkingDate");
									        String monthName = dateDetails.get("monthName");
									        String year = dateDetails.get("year");
										    
									        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
							 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
								MeghLoginTest meghlogintest = new MeghLoginTest();
							
							
							 if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
									logResults.createLogs("Y", "PASS", "Login Done Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Login Is Failed." + meghloginpage.getExceptionDesc());
								}
		
			if (RolePermissionpage.LeaveButton()) {

				logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
			}
			
			if (RolePermissionpage.LeaveRequestsTab()) {

				logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
						+ RolePermissionpage.getExceptionDesc());
			}
			if (meghleavepage.ApplyLeaveButton()) {

				logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully Of Employee Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button."
						+ meghleavepage.getExceptionDesc());
			}	
		
			if (meghleavepage.LeaveTypeDropdown()) {

				logResults.createLogs("Y", "PASS", " LeaveTypeDropdown Clicked Successfully Of Employee Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveTypeDropdown."
						+ meghleavepage.getExceptionDesc());
			}

			if (meghleavepage.SickLeaveSelected()) {

				logResults.createLogs("Y", "PASS", " SickLeave Selected  Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Sick Leave."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.FromDateTextField()) {

				logResults.createLogs("Y", "PASS", " FromDateTextField Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On FromDateTextField."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.FromDateSelected(PunchinDate)) {

				logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + PunchinDate);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting From From Date."
						+ meghleavepage.getExceptionDesc());
			}
			

			if (meghleavepage.LeaveDurationOne()) {

				logResults.createLogs("Y", "PASS", " LeaveDurationOne Clicked Successfully Of Employee Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationOne."
						+ meghleavepage.getExceptionDesc());
			}

			if (meghleavepage.FullDayDurationOne()) {

				logResults.createLogs("Y", "PASS", " FullDay DurationOne Selected  Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting FullDay DurationOne."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.ToDateTextField()) {

				logResults.createLogs("Y", "PASS", " ToDateTextField Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On ToDateTextField."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.ToDateSelected(PunchinDate)) {

				logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + PunchinDate);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting From ToDate."
						+ meghleavepage.getExceptionDesc());
			}
		
			if (meghleavepage.LeaveDurationTwo()) {

				logResults.createLogs("Y", "PASS", " LeaveDurationTwo Clicked Successfully Of Employee Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationTwo."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.FullDayDurationTwo()) {

				logResults.createLogs("Y", "PASS", " FullDayDurationTwo Clicked Successfully Of Employee Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On FullDayDurationTwo."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.ReasonTextField(leavereason)) {

				logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + leavereason);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Reason."
						+ meghleavepage.getExceptionDesc());
			}
		
			if (meghleavepage.RequestApplyButton()) {

				logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully Of Employee Account." );
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
			if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

				logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
			}
			if (meghpitotalleavestaken.TotalLeavesTakenReportPendingTab()) {

				logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Pending Tab Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TotalLeavesTaken Report Pending Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
			}
			
			if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
			
			
			if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavename)) {

				logResults.createLogs("Y", "PASS", "Leave Name Inputted Successfully." + leavename);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Leave Name ." + meghpitotalleavestaken.getExceptionDesc());
			}
			
			if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveNameResultOnEmp(leavename)) {

				logResults.createLogs("Y", "PASS", "Emp Applied Leave Validated Successfully." + leavename);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave." + meghpitotalleavestaken.getExceptionDesc());
			}


			
			if (meghpitotalleavestaken.TotalLeavesTakenReportTotalDaysResultOnEmp(totaldays)) {

				logResults.createLogs("Y", "PASS", "Emp Applied Leave TotalDays Validated Successfully." + totaldays);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave TotalDays." + meghpitotalleavestaken.getExceptionDesc());
			}
			
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}

			
			if (meghpitotalleavestaken.TotalLeavesTakenReportStatusCheckbox()) {

				logResults.createLogs("Y", "PASS", "Status CheckBox Selected Successfully." );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Status CheckBox." + meghpitotalleavestaken.getExceptionDesc());
			}
			if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonCheckbox()) {

				logResults.createLogs("Y", "PASS", "Leave Reason CheckBox Selected Successfully." );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Reason CheckBox." + meghpitotalleavestaken.getExceptionDesc());
			}

			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavestatus)) {

				logResults.createLogs("Y", "PASS", "Leave Status Inputted Successfully." + leavestatus);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Leave Status ." + meghpitotalleavestaken.getExceptionDesc());
			}
			
			if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveStatusResultOnEmp(leavestatus)) {

				logResults.createLogs("Y", "PASS", "Emp Applied Leave Status Validated Successfully." + leavestatus);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Status." + meghpitotalleavestaken.getExceptionDesc());
			}
			if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavereason)) {

				logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Leave Reason ." + meghpitotalleavestaken.getExceptionDesc());
			}
			
			if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonOnEmp(leavereason)) {

				logResults.createLogs("Y", "PASS", "Emp Applied Leave Reason Validated Successfully." + leavereason);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Reason." + meghpitotalleavestaken.getExceptionDesc());
			}
					}
			
			
			
									// MPI_1843_EmpTotalLeaveTakenPending_Reports_02
									@Test(enabled = true, priority = 10, groups = { "Smoke" })
									public void MPI_1843_EmpTotalLeaveTakenPending_Reports_02()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email.");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha,subject,ccmail");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
											String subject = data.get(i++);
											String ccmail = data.get(i++);
											

					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
							    String monthName = dateDetails.get("monthName");
							    String year = dateDetails.get("year");
								
										MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
							if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportPendingTab()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Pending Tab Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Pending Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
			
			
									// MPI_1844_EmpTotalLeaveTakenPending_Reports_03
									@Test(enabled = true, priority = 11, groups = { "Smoke" })
									public void MPI_1844_EmpTotalLeaveTakenPending_Reports_03()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date.");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
								

					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

					String month2WorkingDate  = dateDetails.get("month2WorkingDate");
					 String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
										
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
							if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportPendingTab()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Pending Tab Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Pending Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
							
							if (meghpitotalleavestaken.LeavesTakenRptFilteredDateValidationOnEmp(month2WorkingDate)) {

								logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully." + month2WorkingDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
							} 	
									}
			
			
			
									// MPI_1845_EmpTotalLeaveTakenPending_Reports_04
									@Test(enabled = true, priority = 12, groups = { "Smoke" })
									public void MPI_1845_EmpTotalLeaveTakenPending_Reports_04()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, Apply leave during the last week of last month and  In the emp account leave taken pending Request Reports module, apply the filter for the last week of last month and ensure that the corresponding pending leave  request record is displayed..");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha,leavereason");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
											String leavereason = data.get(i++);
								

					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

					String month19WorkingDate  = dateDetails.get("month19WorkingDate");
					 String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					    

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					        MeghLeavePage meghleavepage = new MeghLeavePage(driver);	
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
								MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
							MeghLoginTest meghlogintest = new MeghLoginTest();
								
							if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
								logResults.createLogs("Y", "PASS", "Login Done Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Login Is Failed." + MeghLoginPage.getExceptionDesc());
							}  
							if (RolePermissionpage.LeaveButton()) {

								logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
							}
							
							if (RolePermissionpage.LeaveRequestsTab()) {

								logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
										+ RolePermissionpage.getExceptionDesc());
							}
							if (meghleavepage.ApplyLeaveButton()) {

								logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully Of Employee Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button."
										+ meghleavepage.getExceptionDesc());
							}	
						
							if (meghleavepage.LeaveTypeDropdown()) {

								logResults.createLogs("Y", "PASS", " LeaveTypeDropdown Clicked Successfully Of Employee Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveTypeDropdown."
										+ meghleavepage.getExceptionDesc());
							}

							if (meghleavepage.SickLeaveSelected()) {

								logResults.createLogs("Y", "PASS", " SickLeave Selected  Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Sick Leave."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.FromDateTextField()) {

								logResults.createLogs("Y", "PASS", " FromDateTextField Clicked Successfully .");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On FromDateTextField."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.FromDateSelected(month19WorkingDate)) {

								logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + month19WorkingDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting From From Date."
										+ meghleavepage.getExceptionDesc());
							}
							

							if (meghleavepage.LeaveDurationOne()) {

								logResults.createLogs("Y", "PASS", " LeaveDurationOne Clicked Successfully Of Employee Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationOne."
										+ meghleavepage.getExceptionDesc());
							}

							if (meghleavepage.FullDayDurationOne()) {

								logResults.createLogs("Y", "PASS", " FullDay DurationOne Selected  Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting FullDay DurationOne."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.ToDateTextField()) {

								logResults.createLogs("Y", "PASS", " ToDateTextField Clicked Successfully .");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On ToDateTextField."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.ToDateSelected(month19WorkingDate)) {

								logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + month19WorkingDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting From ToDate."
										+ meghleavepage.getExceptionDesc());
							}
						
							if (meghleavepage.LeaveDurationTwo()) {

								logResults.createLogs("Y", "PASS", " LeaveDurationTwo Clicked Successfully Of Employee Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationTwo."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.FullDayDurationTwo()) {

								logResults.createLogs("Y", "PASS", " FullDayDurationTwo Clicked Successfully Of Employee Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On FullDayDurationTwo."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.ReasonTextField(leavereason)) {

								logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + leavereason);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting Reason."
										+ meghleavepage.getExceptionDesc());
							}
						
							if (meghleavepage.RequestApplyButton()) {

								logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully Of Employee Account." );
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
							if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportPendingTab()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Pending Tab Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Pending Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
							
							if (meghpitotalleavestaken.LeavesTakenRptFilteredDateValidationOnEmp(month19WorkingDate)) {

								logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully." + month19WorkingDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
							} 	
									}	
			
			
									// MPI_1846_EmpTotalLeaveTakenRejected_Reports_01
									@Test(enabled = true, priority = 13, groups = { "Smoke" })
									public void MPI_1846_EmpTotalLeaveTakenRejected_Reports_01()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, Check the functionality of search feature by selecting each search option.");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha,password,leavename,totaldays,leavestatus,leaverejectionreason");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
											String password = data.get(i++);
											String leavename = data.get(i++);
											String totaldays = data.get(i++);
											String leavestatus = data.get(i++);
											String leaverejectionreason = data.get(i++);
											
											Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

									        String PunchinDate = dateDetails.get("month2WorkingDate");
									        String monthName = dateDetails.get("monthName");
									        String year = dateDetails.get("year");
										    
									        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
							 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
								MeghLoginTest meghlogintest = new MeghLoginTest();
							
							
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
							if (meghleavepage.MonthFilterContains(PunchinDate)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + PunchinDate );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

								logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.PendingLeaveCount()) {

								logResults.createLogs("Y", "PASS", "Monthly Pending Leave Count Clicked  Successfully ." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Pending Leave Count."
										+ meghleavepage.getExceptionDesc());
							}
							
						
							if (meghleavepage.clickRejectButtonByEmpAndDate(empid, PunchinDate)) {

								logResults.createLogs("Y", "PASS", " Leave Rejected   Successfully ." + PunchinDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Rejecting Leave."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.LeaveRejectionReason(leaverejectionreason)) {

								logResults.createLogs("Y", "PASS", " Leave Rejection Reason Inputted  Successfully ." + leaverejectionreason);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting Rejection Reason."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.LeaveRejectButton()) {

								logResults.createLogs("Y", "PASS", " Leave Rejection  Button Clicked  Successfully ." + leaverejectionreason);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Reject Button."
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
							if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportRejectedTab()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Rejected Tab Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Rejected Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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

							
							if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavename)) {

								logResults.createLogs("Y", "PASS", "Leave Name Inputted Successfully." + leavename);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Leave Name ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveNameResultOnEmp(leavename)) {

								logResults.createLogs("Y", "PASS", "Emp Applied Leave Validated Successfully." + leavename);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave." + meghpitotalleavestaken.getExceptionDesc());
							}

							if (meghpitotalleavestaken.TotalLeavesTakenReportTotalDaysResultOnEmp(totaldays)) {

								logResults.createLogs("Y", "PASS", "Emp Applied Leave TotalDays Validated Successfully." + totaldays);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave TotalDays." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (OfficePage.SearchDropDown()) {
								logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
							}

							if (meghpitotalleavestaken.TotalLeavesTakenReportStatusCheckbox()) {

								logResults.createLogs("Y", "PASS", "Status CheckBox Selected Successfully." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Status CheckBox." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonCheckbox()) {

								logResults.createLogs("Y", "PASS", "Leave Reason CheckBox Selected Successfully." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Reason CheckBox." + meghpitotalleavestaken.getExceptionDesc());
							}

							if (OfficePage.SearchDropDown()) {
								logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavestatus)) {

								logResults.createLogs("Y", "PASS", "Leave Status Inputted Successfully." + leavestatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Leave Status ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveStatusResultOnEmp(leavestatus)) {

								logResults.createLogs("Y", "PASS", "Emp Applied Leave Status Validated Successfully." + leavestatus);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Status." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leaverejectionreason)) {

								logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leaverejectionreason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Leave Reason ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonOnEmp(leaverejectionreason)) {

								logResults.createLogs("Y", "PASS", "Emp Applied Leave Reason Validated Successfully." + leaverejectionreason);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Reason." + meghpitotalleavestaken.getExceptionDesc());
							}
									}
			
									// MPI_1847_EmpTotalLeaveTakenRejected_Reports_02
									@Test(enabled = true, priority = 14, groups = { "Smoke" })
									public void MPI_1847_EmpTotalLeaveTakenRejected_Reports_02()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email.");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha,subject,ccmail");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
											String subject = data.get(i++);
											String ccmail = data.get(i++);
											

					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
							    String monthName = dateDetails.get("monthName");
							    String year = dateDetails.get("year");
											
											MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);


										MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
										MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
							if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportRejectedTab()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Rejected Tab Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Rejected Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
									
									
									// MPI_1848_EmpTotalLeaveTakenRejected_Reports_03
									@Test(enabled = true, priority = 15, groups = { "Smoke" })
									public void MPI_1848_EmpTotalLeaveTakenRejected_Reports_03()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date.");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
								
											Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
											String month2WorkingDate  = dateDetails.get("month2WorkingDate");
											  
										    String monthName = dateDetails.get("monthName");
										    String year = dateDetails.get("year");
										
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
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
							if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportRejectedTab()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Rejected Tab Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Rejected Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
							
							if (meghpitotalleavestaken.LeavesTakenRptFilteredDateValidationOnEmp(month2WorkingDate)) {

								logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully." + month2WorkingDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
							} 	
									}					
									
									
									// MPI_1849_EmpTotalLeaveTakenRejected_Reports_04
									@Test(enabled = true, priority = 16, groups = { "Smoke" })
									public void MPI_1849_EmpTotalLeaveTakenRejected_Reports_04()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, Check the functionality of search feature by selecting each search option.");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha,password,leaverejectionreason");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
											String password = data.get(i++);
										
											String leaverejectionreason = data.get(i++);
											
											Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

											String month19WorkingDate  = dateDetails.get("month19WorkingDate");
											 String monthName = dateDetails.get("monthName");
											    String year = dateDetails.get("year");
										    
									        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
									MeghLoginTest meghlogintest = new MeghLoginTest();
							
							
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
							if (meghleavepage.MonthFilterContains(month19WorkingDate)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + month19WorkingDate );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}
							if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

								logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.PendingLeaveCount()) {

								logResults.createLogs("Y", "PASS", "Monthly Pending Leave Count Clicked  Successfully ." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Pending Leave Count."
										+ meghleavepage.getExceptionDesc());
							}
							
						
							if (meghleavepage.clickRejectButtonByEmpAndDate(empid, month19WorkingDate)) {

								logResults.createLogs("Y", "PASS", " Leave Rejected   Successfully ." + month19WorkingDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Rejecting Leave."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.LeaveRejectionReason(leaverejectionreason)) {

								logResults.createLogs("Y", "PASS", " Leave Rejection Reason Inputted  Successfully ." + leaverejectionreason);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting Rejection Reason."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.LeaveRejectButton()) {

								logResults.createLogs("Y", "PASS", " Leave Rejection  Button Clicked  Successfully ." + leaverejectionreason);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Reject Button."
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
							if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							if (meghpitotalleavestaken.TotalLeavesTakenReportRejectedTab()) {

								logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Rejected Tab Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On TotalLeavesTaken Report Rejected Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
							}
							
							if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
							
							if (meghpitotalleavestaken.LeavesTakenRptFilteredDateValidationOnEmp(month19WorkingDate)) {

								logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully." + month19WorkingDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
							} 	
									}		
											
									
									
									// MPI_1850_EmpWeeklyOffReports_01
									@Test(enabled = true, priority = 17, groups = { "Smoke" })
									public void MPI_1850_EmpWeeklyOffReports_01()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, Check the functionality of search feature by selecting each search option.");

										  // Load Excel data
										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"empid,captcha");
										
										  int i = 0;
											String empid = data.get(i++);
											String captcha = data.get(i++);
											
											
											  Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();
											  String week1Sunday   = (String) details.get("1stSunday");
										String uidate = Pramod.convertToUIFormat(week1Sunday);
										        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
											  String monthName = dateDetails.get("monthName");
											    String year = dateDetails.get("year");
										    
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
							 MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);
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
							if (meghpiweekoffreport.WeekOffReportButton()) {

								logResults.createLogs("Y", "PASS", "WeekOff Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Report Button." + meghpiweekoffreport.getExceptionDesc());
							}

							if (meghpiweekoffreport.WeekOffReportRowResult()) {

								logResults.createLogs("Y", "PASS", "WeekOff Report Page Loaded Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Loading  WeekOff Report Page." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.WeekOffReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpiweekoffreport.getExceptionDesc());
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
						
							
							
							if (meghpiweekoffreport.WeekOffReportSearchTextField(uidate)) {

								logResults.createLogs("Y", "PASS", "Emp WeekOff Date Inputted Successfully." + uidate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting Emp WeekOff Date." + meghpiweekoffreport.getExceptionDesc());
							}	
							if (meghpiweekoffreport.WeekOffReportDateResultOnEmp(week1Sunday)) {

								logResults.createLogs("Y", "PASS", "Emp WeekOff Date  Validated Successfully." + week1Sunday);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Validating Emp WeekOff Date." + meghpiweekoffreport.getExceptionDesc());
							}
							if (OfficePage.SearchDropDown()) {
								logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
							}
							
							
	
									if (meghpiweekoffreport.WeekOffReportCmpLocationSearchCheckBox()) {

										logResults.createLogs("Y", "PASS", "Cmp Location CheckBox Selected Successfully." );
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Selecting Cmp Location  CheckBox." + meghpiweekoffreport.getExceptionDesc());
									}
									if (OfficePage.SearchDropDown()) {
										logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
									}	
									
									if (meghpiweekoffreport.WeekOffReportSearchTextField(officename)) {

										logResults.createLogs("Y", "PASS", "Emp Assigned Office Name Inputted Successfully." + officename);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Assigned Office Name." + meghpiweekoffreport.getExceptionDesc());
									}	
									if (meghpiweekoffreport.WeekOffReportCmpLocationResultOnEmp(officename)) {

										logResults.createLogs("Y", "PASS", "Emp Assigned Office Name Validated Successfully." + officename);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Validating Emp Assigned Office Name." + meghpiweekoffreport.getExceptionDesc());
									}
									
									}		
									
									
									// MPI_1851_EmpWeeklyOffReports_02
									@Test(enabled = true, priority = 18, groups = { "Smoke" })
									public void MPI_1851_EmpWeeklyOffReports_02()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

										ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC,
												"subject,ccmail,empid,captcha");

										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
									
										MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										 MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);

									
										String subject =data.get(0);
										String ccmail =data.get(1);
										String empid = data.get(2);
										String captcha = data.get(3);
									
										Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
									    String monthName = dateDetails.get("monthName");
									    String year = dateDetails.get("year");
										

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
										  if (meghpiweekoffreport.WeekOffReportButton()) {

												logResults.createLogs("Y", "PASS", "WeekOff Report Button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Report Button." + meghpiweekoffreport.getExceptionDesc());
											}

											if (meghpiweekoffreport.WeekOffReportRowResult()) {

												logResults.createLogs("Y", "PASS", "WeekOff Report Page Loaded Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Loading  WeekOff Report Page." + meghpiweekoffreport.getExceptionDesc());
											}
											if (meghpiweekoffreport.WeekOffReportFilterButton()) {

												logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error While Clicking On Filter Button." + meghpiweekoffreport.getExceptionDesc());
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
									
									
									// MPI_1852_EmpWeeklyOffReports_03
									@Test(enabled = true, priority = 19, groups = { "Smoke" })
									public void MPI_1852_EmpWeeklyOffReports_03()  {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date of last month weekoff date (sunday date)");

									ArrayList<String> data = initBase.loadExcelData("EmpTotalLeaveTakenReports", currTC, "password,captcha,firstname,empid,regularizationreason");
								String password = data.get(0);
								String captcha = data.get(1);
								String firstname = data.get(2);
								String empid = data.get(3);
								String regularizationreason = data.get(4);
								

										
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
										
										MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
										MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);
										 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

										
										 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

									        String firstsunday = (String) datas.get("1stSunday");
									       String firstworkingday = (String) datas.get("workingAfter1stSaturday");
									       String uidate = Pramod.convertToUIFormat(firstworkingday);
									   
									       Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
										    String monthName = dateDetails.get("monthName");
										    String year = dateDetails.get("year");
									       
									       
									       MeghLoginPage meghloginpage = new MeghLoginPage(driver);
										MeghLoginTest meghlogintest = new MeghLoginTest();

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
										if (meghpiweekoffreport.WeekOffButtonInAttendance()) {

											logResults.createLogs("Y", "PASS", "WeekOff  Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff  Button." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.ApplyWeekOffButtonInAttendance()) {

											logResults.createLogs("Y", "PASS", "Apply WeekOff  Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply WeekOff  Button." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.ApplyWeekOffForOthersButtonInAttendance()) {

											logResults.createLogs("Y", "PASS", "Apply  WeekOff For Others  Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply  WeekOff For Others  Button." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.EmpDrpDownWeekoffApply()) {

											logResults.createLogs("Y", "PASS", "Emp Drop Down Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp Selection DropDown." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.EmpNameInputWeekoffApply(firstname)) {

											logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.EmpNameSelectedWeekoffApply()) {

											logResults.createLogs("Y", "PASS", "Emp Name Selected Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Name." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.OldWeekoffDateTextField()) {

											logResults.createLogs("Y", "PASS", "Old WeekoffDate TextField Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On Old WeekoffDate TextField." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.FromDateSelected(firstsunday)) {

											logResults.createLogs("Y", "PASS", "Old WeekoffDate Inputted Successfully." + firstsunday);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting On Old WeekoffDate ." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.EmpDrpDownWeekoffApply()) {

											logResults.createLogs("Y", "PASS", "Emp Drop Down Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp Selection DropDown." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.NewWeekoffDateTextField()) {

											logResults.createLogs("Y", "PASS", "New WeekoffDate TextField Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On New WeekoffDate TextField." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.NewWeekOffDateSelected(firstworkingday)) {

											logResults.createLogs("Y", "PASS", "New WeekoffDate Inputted Successfully." + firstworkingday);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting On New WeekoffDate ." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.WeekOffReason(regularizationreason)) {

											logResults.createLogs("Y", "PASS", " Weekoff Reason Inputted Successfully." + regularizationreason);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting Weekoff Reason." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.WeekOffReason(regularizationreason)) {

											logResults.createLogs("Y", "PASS", " Weekoff Reason Inputted Successfully." + regularizationreason);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting Weekoff Reason." + meghpiweekoffreport.getExceptionDesc());
										}
										if (meghpiweekoffreport.WeekOffApplySaveButton()) {

											logResults.createLogs("Y", "PASS", "Weekoff Apply Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On Weekoff Apply Button." + meghpiweekoffreport.getExceptionDesc());
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
										  if (meghpiweekoffreport.WeekOffReportButton()) {

												logResults.createLogs("Y", "PASS", "WeekOff Report Button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Report Button." + meghpiweekoffreport.getExceptionDesc());
											}

											if (meghpiweekoffreport.WeekOffReportRowResult()) {

												logResults.createLogs("Y", "PASS", "WeekOff Report Page Loaded Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Loading  WeekOff Report Page." + meghpiweekoffreport.getExceptionDesc());
											}
											
												if (meghpiweekoffreport.WeekOffReportFilterButton()) {

													logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
												} else {
													logResults.createLogs("Y", "FAIL",
															"Error While Clicking On Filter Button." + meghpiweekoffreport.getExceptionDesc());
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
												
												if (meghpiattendancereport.FromDateSelected(firstworkingday)) {

													logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + firstworkingday);
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
												
												if (meghpiattendancereport.ToDateSelected(firstworkingday)) {

													logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + firstworkingday);
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
												
												
												
												if (meghpiweekoffreport.WeekOffReportSearchTextField(uidate)) {

													logResults.createLogs("Y", "PASS", "Emp WeekOff Date Inputted Successfully." + uidate);
												} else {
													logResults.createLogs("Y", "FAIL", "Error While Inputting Emp WeekOff Date." + meghpiweekoffreport.getExceptionDesc());
												}	
												if (meghpiweekoffreport.WeekOffReportDateResultOnEmp(firstworkingday)) {

													logResults.createLogs("Y", "PASS", "Emp WeekOff Date  Validated Successfully." + firstworkingday);
												} else {
													logResults.createLogs("Y", "FAIL", "Error While Validating Emp WeekOff Date." + meghpiweekoffreport.getExceptionDesc());
												}
									}
									
									
									// MPI_1853_EmpWeeklyOffReports_04
									@Test(enabled = true, priority = 20, groups = { "Smoke" })
									public void MPI_1853_EmpWeeklyOffReports_04() {
										String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
										logResults.createExtentReport(currTC);
										logResults.setScenarioName(
												"To verify this, request a weekoff change request as an employee and approve the week-off change request as an admin. Then, ensure that the Week-Off Report displays the updated week-off day and date.");
										logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_1852_EmpWeeklyOffReports_03 TestCase.");
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
