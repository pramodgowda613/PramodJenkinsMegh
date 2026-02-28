package com.MeghPI.Attendance.tests;

import java.time.LocalDate;
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
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationReportsPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIOverTimeReportsTest {


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
    private String AdminFirstName ="";
	
	
	
    @Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
    public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
		//driver = loadDriver.getDriver(device);

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
		EmpID = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpID", "");
		EmpFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpFirstName", "");
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");
		
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		
	}

	// MPI_1179_OT_All_Reports_11
			@Test(enabled = true, priority = 2, groups = { "Smoke" })
			public void MPI_1179_OT_All_Reports_11()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, from the newly created employee account, perform OT and ensure the OT record is displayed with a pending status");

				  // Load Excel data
				ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
						"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,overtimestatus");


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
			        String overtimestatus = data.get(i++);
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

			        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

			        String PunchinDate = dateDetails.get("month1WorkingDate");
			        String punchinDateUI = Pramod.convertToUIFormat(PunchinDate);
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
			        
			        
			        String fromDateofstatus = PunchinDate + fromDateofuserstatus1;
			        String toDateofstatus = PunchinDate + toDateofuserstatus2;
				   
				    
					String inouttime = PunchinDate + " " + inouttime1;
					String secondInOutTime = PunchinDate + " " + secondInOutTime2;
				
					
					// get first day of month
					LocalDate localDate = LocalDate.parse(PunchinDate);
					 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
					 
				
				
				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
			MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
					
			  

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
		                description, empid, PunchinDate, inouttime1, secondInOutTime,
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
		        if (meghpiovertimepage.OverTimeButton()) {

					logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
				}
		        if (meghpiovertimepage.OverTimeReportAllTabButton()) {

					logResults.createLogs("Y", "PASS", "OverTime Report All Tab button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report All Tab Button." + meghpiovertimepage.getExceptionDesc());
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
				if (meghpiovertimepage.OverTimeReportFilterButton()) {

					logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
		        if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(firstname)) {

					logResults.createLogs("Y", "PASS", "Emp FirstName Inputted Successfully." + firstname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Emp First Name." + meghpiovertimepage.getExceptionDesc());
				}
		        if (meghpiovertimepage.verifyStatusByDate(punchinDateUI,overtimestatus)) {

					logResults.createLogs("Y", "PASS", "Emp OverTime Record Displayed Successfully With The Status." + overtimestatus);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Emp OverTime Record And Status." + meghpiovertimepage.getExceptionDesc());
				}
		        
		        
			}
				
			
			// MPI_1182_OT_All_Reports_14
			@Test(enabled = true, priority = 1, groups = { "Smoke" })
			public void MPI_1182_OT_All_Reports_14()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, configure OT approval as manual in the default attendance policy.");

				ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
						"password,captcha,policyname,duration,otmaxminutes");

				
				MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
				MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
				int i = 0;
				String password = data.get(i++);
				String captcha = data.get(i++);
				String policyname = data.get(i++);
				String duration = data.get(i++);
				String otmaxminutes = data.get(i++);
				String PolicyNames = "AutoAttenPN" + initBase.executionRunTime;
				
				
				
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

				if (RolePermissionpage.AttendancePolicy()) {

					logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On AttendancePolicy." + RolePermissionpage.getExceptionDesc());
				}

				if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

					logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Attendance Policy Page Is Not Loaded Completely." + RolePermissionpage.getExceptionDesc());
				}
				
				if (AttendancePolicyPage.SearchTextField(policyname)) {

					logResults.createLogs("Y", "PASS", "On SearchTextField Policy Name Inputted Successfully ." + policyname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Policy Name On Search TextField ."
							+ AttendancePolicyPage.getExceptionDesc());
				}
				

				if (ShiftPolicyPage.EditIcon()) {

					logResults.createLogs("Y", "PASS", "Shift Policy Edit Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On ShiftPolicy Edit Button." + ShiftPolicyPage.getExceptionDesc());
				}


				if (AttendancePolicyPage.RegulirizationUpto31days()) {

					logResults.createLogs("Y", "PASS", "Regulirization Upto 31days CheckBox Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Regulirization Upto Checkbox ." + AttendancePolicyPage.getExceptionDesc());
				}
				
				if (AttendancePolicyPage.RegulirizationUptoTextField(duration)) {

					logResults.createLogs("Y", "PASS", "Regulirization Upto 31days Inputted  Successfully ." + duration);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting 31 day On Regulirization TextField ." + AttendancePolicyPage.getExceptionDesc());
				}
				if (AttendancePolicyPage.OverTimeEligibilityDropDown()) {

					logResults.createLogs("Y", "PASS", "OverTime Eligibility DropDown Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On OverTime Eligibility DropDown ." + AttendancePolicyPage.getExceptionDesc());
				}
				if (AttendancePolicyPage.OTAllowCheckbox()) {

					logResults.createLogs("Y", "PASS", "OT Allow CheckBox Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On OT Allow Checkbox ." + AttendancePolicyPage.getExceptionDesc());
				}
				if (AttendancePolicyPage.OTMaxMinutesTextField()) {

					logResults.createLogs("Y", "PASS", "OT MaxMinutes TextField Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On OT MaxMinutes TextField ." + AttendancePolicyPage.getExceptionDesc());
				}
				if (AttendancePolicyPage.OTMaxMinutesTextFieldInputted(otmaxminutes)) {

					logResults.createLogs("Y", "PASS", "OT Max Minutess Inputted  Successfully ." + otmaxminutes);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting OT Max Minutes ." + AttendancePolicyPage.getExceptionDesc());
				}

				if (AttendancePolicyPage.CreatePolicyButton()) {

					logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On CreatePolicyButton ." + AttendancePolicyPage.getExceptionDesc());
				}

				if (AttendancePolicyPage.YesButton()) {

					logResults.createLogs("Y", "PASS", "YesButton Button Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On YesButton ." + AttendancePolicyPage.getExceptionDesc());
				}
				if (AttendancePolicyPage.SearchTextField(PolicyNames)) {

					logResults.createLogs("Y", "PASS", "On SearchTextField Policy Name Inputted Successfully ." + PolicyNames);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Policy Name On Search TextField ."
							+ AttendancePolicyPage.getExceptionDesc());
				}
				

				if (ShiftPolicyPage.EditIcon()) {

					logResults.createLogs("Y", "PASS", "Shift Policy Edit Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On ShiftPolicy Edit Button." + ShiftPolicyPage.getExceptionDesc());
				}
				if (AttendancePolicyPage.CreatePolicyButton()) {

					logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On CreatePolicyButton ." + AttendancePolicyPage.getExceptionDesc());
				}

				if (AttendancePolicyPage.YesButton()) {

					logResults.createLogs("Y", "PASS", "YesButton Button Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On YesButton ." + AttendancePolicyPage.getExceptionDesc());
				}
			}
				
			// MPI_1168_OT_All_Reports_01
						@Test(enabled = true, priority = 3, groups = { "Smoke" })
						public void MPI_1168_OT_All_Reports_01()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionalities of search feature by selecting each search option");

						//	ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

							MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
						
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
							
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
							
							
							String EmpIDRow ="";
							String EmpLastName ="";
							Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();


						    String monthName = details.get("monthName");
						    String year = details.get("year");
				
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
						        if (meghpiovertimepage.OverTimeButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
								}
						        if (meghpiovertimepage.OverTimeReportAllTabButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Report All Tab button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report All Tab Button." + meghpiovertimepage.getExceptionDesc());
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
								if (meghpiovertimepage.OverTimeReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
								if (meghpiovertimepage.OverTimeReportEmpIDRecord()) {
									EmpIDRow =  meghpiovertimepage.getempid;
									logResults.createLogs("Y", "PASS", "Emp ID Extracted Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Extracting Emp ID." + meghpiovertimepage.getExceptionDesc());
								}
								if (meghpiovertimepage.OverTimeReportEmpNameRecord()) {
									EmpLastName = meghpiovertimepage.getemplastname;
									logResults.createLogs("Y", "PASS", "Emp Last Name Extracted Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Extracting Emp Last Name." + meghpiovertimepage.getExceptionDesc());
								}
								 if (meghpiovertimepage.OverTimeReportEnrollIDCheckBox()) {
									 
									 
										logResults.createLogs("Y", "PASS", "Emp ID Search Option CheckBox Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp id CheckBox." + meghpiovertimepage.getExceptionDesc());
									}
								 if (meghpiovertimepage.OverTimeReportLastNameCheckBox()) {

										logResults.createLogs("Y", "PASS", "Emp LastName Search Option CheckBox Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp LastName CheckBox." + meghpiovertimepage.getExceptionDesc());
									}
								 if (OfficePage.SearchDropDown()) {
										logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
									}
								
						        if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(EmpIDRow)) {

									logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + EmpIDRow);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpiovertimepage.getExceptionDesc());
								}			
						        
						        if (meghpiovertimepage.OverTimeReportEmpIDRecordValidation(EmpIDRow)) {

									logResults.createLogs("Y", "PASS", "Emp ID Displayed Successfully." + EmpIDRow);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Displaying Emp ID." + meghpiovertimepage.getExceptionDesc());
								}	
						        if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(EmpLastName)) {

									logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + EmpLastName);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpiovertimepage.getExceptionDesc());
								}	
						        if (meghpiovertimepage.OverTimeReportEmpNameRecordValidation(EmpLastName)) {

									logResults.createLogs("Y", "PASS", "Inputted Emp Last Name Record Displayed Successfully." + EmpLastName);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Displaying Emp Last Name Record." + meghpiovertimepage.getExceptionDesc());
								}
				
						}	
				
						// MPI_1169_OT_All_Reports_02
						@Test(enabled = true, priority = 4, groups = { "Smoke" })
						public void MPI_1169_OT_All_Reports_02()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

							ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
									"subject,ccmail");
						
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
							
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
						
						
							String subject =data.get(0);
							String ccmail =data.get(1);
							
							Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

						    String monthName = details.get("monthName");
						    String year = details.get("year");	
						    
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
						        if (meghpiovertimepage.OverTimeButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
								}
						        if (meghpiovertimepage.OverTimeReportAllTabButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Report All Tab button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report All Tab Button." + meghpiovertimepage.getExceptionDesc());
								}
						       
								if (meghpiovertimepage.OverTimeReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
				
						// MPI_1172_OT_All_Reports_04
						@Test(enabled = true, priority = 5, groups = { "Smoke" })
						public void MPI_1172_OT_All_Reports_04()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of filter feature by selecting specific team");

							ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
									"firstname,pin");

							
						
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
							
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
							MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
							MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
							
						
						
							String firstname =data.get(0);
							String pin =data.get(1);
							Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

						    String monthName = details.get("monthName");
						    String year = details.get("year");
				
						    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


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
								 if (RolePermissionpage.ReprtButton()) {

										logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeButton()) {

										logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeReportAllTabButton()) {

										logResults.createLogs("Y", "PASS", "OverTime Report All Tab button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report All Tab Button." + meghpiovertimepage.getExceptionDesc());
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
									if (meghpiovertimepage.OverTimeReportFilterButton()) {

										logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
									
									if (meghpiovertimepage.OverTimeReportAllTabSearchResult()) {

										logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
									}     
						}					        
						        
						// MPI_1173_OT_All_Reports_05
						@Test(enabled = true, priority = 6, groups = { "Smoke" })
						public void MPI_1173_OT_All_Reports_05()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

					//		ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

							
						
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
						
							
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
						
					
						
							Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

						    String monthName = details.get("monthName");
						    String year = details.get("year");
				
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
						        if (meghpiovertimepage.OverTimeButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
								}
						        if (meghpiovertimepage.OverTimeReportAllTabButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Report All Tab button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report All Tab Button." + meghpiovertimepage.getExceptionDesc());
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
								if (meghpiovertimepage.OverTimeReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
								
								if (meghpiovertimepage.OverTimeReportAllTabSearchResult()) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
								}     
					}        
						        
						// MPI_1174_OT_All_Reports_06
						@Test(enabled = true, priority = 7, groups = { "Smoke" })
						public void MPI_1174_OT_All_Reports_06()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of filter feature by selecting employee type");

						//	ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

							
						
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
						
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
						
							
							
							  Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
							    String monthName = details.get("monthName");
							    String year = details.get("year");
				
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
						        if (meghpiovertimepage.OverTimeButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
								}
						        if (meghpiovertimepage.OverTimeReportAllTabButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Report All Tab button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report All Tab Button." + meghpiovertimepage.getExceptionDesc());
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
								if (meghpiovertimepage.OverTimeReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
								
								if (meghpiovertimepage.OverTimeReportAllTabSearchResult()) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
								}     
					} 		        
						        
						// MPI_1176_OT_All_Reports_07
						@Test(enabled = true, priority = 8, groups = { "Smoke" })
						public void MPI_1176_OT_All_Reports_07()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of filter feature by selecting month and year");

							
						//	ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
						
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
							
							
							  Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

							    String monthName = details.get("monthName");
							    String year = details.get("year");
				
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
						        if (meghpiovertimepage.OverTimeButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
								}
						        if (meghpiovertimepage.OverTimeReportAllTabButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Report All Tab button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report All Tab Button." + meghpiovertimepage.getExceptionDesc());
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
								if (meghpiovertimepage.OverTimeReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
								
								if (meghpiovertimepage.OverTimeReportDateRow(monthName)) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + monthName);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
								}     
					}
				
						// MPI_1175_OT_All_Reports_08
						@Test(enabled = true, priority = 9, groups = { "Smoke" })
						public void MPI_1175_OT_All_Reports_08()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

					//		ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
							
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
							
							
							  Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

							    String PunchinDate = details.get("month1WorkingDate");
							    String PunchinDateSearchUI = Pramod.convertToUIFormat(PunchinDate);
							    String monthName = details.get("monthName");
							    String year = details.get("year");
				
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
						        if (meghpiovertimepage.OverTimeButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
								}
						        if (meghpiovertimepage.OverTimeReportAllTabButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Report All Tab button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report All Tab Button." + meghpiovertimepage.getExceptionDesc());
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
								if (meghpiovertimepage.OverTimeReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
								
								if (meghpiattendancereport.FromDateSelected(PunchinDate)) {

									logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + PunchinDate);
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
								
								if (meghpiattendancereport.ToDateSelected(PunchinDate)) {

									logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + PunchinDate);
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
								
								if (meghpiovertimepage.OverTimeReportDateRowValidation(PunchinDateSearchUI)) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + PunchinDateSearchUI);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
								}     
					}
						
						// MPI_1180_OT_All_Reports_12
						@Test(enabled = true, priority = 10, groups = { "Smoke" })
						public void MPI_1180_OT_All_Reports_12()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, ensure that the OT hours and date match with respect to the employee.");

							ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
									"firstname,totothour");

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
						
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
							
							String firstname = data.get(0);
							String totothour = data.get(1);
							 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

							    String PunchinDate = details.get("month1WorkingDate");
							    String PunchinDateSearchUI = Pramod.convertToUIFormat(PunchinDate);
							    String monthName = details.get("monthName");
							    String year = details.get("year");
				
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
						        if (meghpiovertimepage.OverTimeButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
								}
						        if (meghpiovertimepage.OverTimeReportAllTabButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Report All Tab button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report All Tab Button." + meghpiovertimepage.getExceptionDesc());
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
								if (meghpiovertimepage.OverTimeReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
								
								if (meghpiattendancereport.FromDateSelected(PunchinDate)) {

									logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + PunchinDate);
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
								
								if (meghpiattendancereport.ToDateSelected(PunchinDate)) {

									logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + PunchinDate);
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
								 if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(firstname)) {

										logResults.createLogs("Y", "PASS", "Emp FirstName Inputted Successfully." + firstname);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Emp First Name." + meghpiovertimepage.getExceptionDesc());
									}
								
								if (meghpiovertimepage.ValidationEmpWithDateAndOT(totothour,PunchinDateSearchUI,firstname)) {

									logResults.createLogs("Y", "PASS", "Emp OT Hours Is Validated With Respect To Date Successfully." + PunchinDateSearchUI + firstname + totothour);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Validating Emp OT Hours With Respect TO Date." + meghpiovertimepage.getExceptionDesc());
								}     
					}	
						
						// MPI_1181_OT_All_Reports_13
						@Test(enabled = true, priority = 11, groups = { "Smoke" })
						public void MPI_1181_OT_All_Reports_13()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, do overtime on a week-off day and ensure the day type is displayed as PW.");

							ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
									"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,daytype");

try {
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

							    String statusEndpoint    = data.get(i++);
						        String fromDateofuserstatus1          = data.get(i++);
						        String toDateofuserstatus2            = data.get(i++);
						        String expectedStatus    = data.get(i++);
						        String description = data.get(i++);
						        String totalOTHour = data.get(i++);
						        String updateattendanceendpoint = data.get(i++); 
						        String daytype = data.get(i++); 


						        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
						        Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();
						        String week1Sunday   = (String) details.get("1stSunday");
						        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

						        String monthName = dateInfo.get("monthName");
						        String year = dateInfo.get("year");
						        
						        
						        String fromDateofstatus = week1Sunday + fromDateofuserstatus1;
						        String toDateofstatus = week1Sunday + toDateofuserstatus2;
							   
							    
								String inouttime = week1Sunday + " " + inouttime1;
								String secondInOutTime = week1Sunday + " " + secondInOutTime2;
								// get first day of month
								LocalDate localDate = LocalDate.parse(week1Sunday);
								 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
								 
								
									
							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
							 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
							 
							

						    // Punch IN
						    Response punchInResponse = apiPage.executeSuccessTransaction(
						            baseuri, loginendpoint,
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, EmpID,
						            locationid, inouttime, mode, photo
						    );

						    if (punchInResponse.getStatusCode() == 200) {
							    logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
							} else {
							    logResults.createLogs("N", "FAIL", "Punch IN failed: " + punchInResponse.asString());
							    return;
							}

						    // Punch OUT
						    Response punchOutResponse = apiPage.executeSuccessTransaction(
						            baseuri, loginendpoint,
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, EmpID,
						            locationid, secondInOutTime, secondMode, photo
						    );

						    if (punchOutResponse.getStatusCode() == 200) {
					            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
					        } else {
					            logResults.createLogs("N", "FAIL", "Punch OUT failed: " + punchOutResponse.asString());
					            return;
					        }		   
Thread.sleep(12000);
						 // Trigger attendance update first
						    Response updateResp = apiPage.executeUpdateAttendance(
						            baseuri, loginendpoint,
						            Emailid, password, cmpcode,
						            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
						            EmpID, firstDayOfMonth + "T00:00:00.000Z"
						    );

						    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
						        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + EmpID);
						    } else {
						        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed: " + updateResp.asString());
						    }
						    Thread.sleep(20000);
						 // Get User Status
					        Response validation = apiPage.executeGetUserStatus(
					                baseuri, loginendpoint,
					                Emailid, password, cmpcode,
					                baseuri, statusEndpoint,
					                EmpID, fromDateofstatus, toDateofstatus
					        );

					        if (validation.statusCode() == 200) {
					            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

					            // Handle null OTHours → default to 00:00
					            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
					            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

					            // Make sentence using excel inputs
					            String finalSentence = String.format(
					                "%s – This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					                description, EmpID, week1Sunday, inouttime1, secondInOutTime2,
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
					            logResults.createLogs("N", "FAIL", "❌ Failed to fetch attendance. API Response: " + validation.asString());
					        }
				Thread.sleep(2000);
				 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


					if (meghloginpage.MainLandingPage()) {
						logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
					}
							
							
							if (RolePermissionpage.ReprtButton()) {

								logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully: ");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Report Button: " + RolePermissionpage.getExceptionDesc());
							}
							 if (meghpiovertimepage.OverTimeButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully: ");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button: " + meghpiovertimepage.getExceptionDesc());
								}
						        if (meghpiovertimepage.OverTimeReportAllTabButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Report All Tab button Clicked Successfully: ");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report All Tab Button: " + meghpiovertimepage.getExceptionDesc());
								}
						    	if (meghpiattendancereport.LocationDropdown()) {

									logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully: ");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Location Dropdown: " + meghpiattendancereport.getExceptionDesc());
								}
								
								if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

									logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully: " + officename);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Inputting  Location Name: " + meghpiattendancereport.getExceptionDesc());
								}
								
								if (meghpiattendancereport.LocationDropdownSearchResult()) {

									logResults.createLogs("Y", "PASS", "Location Selected Successfully: " );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Inputting  Location Name: " + meghpiattendancereport.getExceptionDesc());
								}
								
								if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

									logResults.createLogs("Y", "PASS", "Dept Selected Successfully: " + departmentname );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Selecting Dept Name: " + meghpiattendancereport.getExceptionDesc());
								}
								if (meghpiovertimepage.OverTimeReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully: ");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button: " + meghpiovertimepage.getExceptionDesc());
								}
								
								if (meghpiattendancereport.YearDropDown(year)) {

									logResults.createLogs("Y", "PASS", "Year Selected Successfully: " + year );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Selecting Year: " + meghpiattendancereport.getExceptionDesc());
								}
								
								if (meghpiattendancereport.MonthDropDown(monthName)) {

									logResults.createLogs("Y", "PASS", "Month Selected Successfully: " + monthName );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Selecting Month: " + meghpiattendancereport.getExceptionDesc());
								}
								if (meghpiattendancereport.WeeklyDropDown()) {

									logResults.createLogs("Y", "PASS", "Week Selected Successfully: ");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Selecting Week: " + meghpiattendancereport.getExceptionDesc());
								}

								if (meghpiattendancereport.FromDateDropDown()) {

									logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully: ");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On FromDate DropDown: " + meghpiattendancereport.getExceptionDesc());
								}
								
								if (meghpiattendancereport.FromDateSelected(week1Sunday)) {

									logResults.createLogs("Y", "PASS", "From Date Selected Successfully: " + week1Sunday);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Selecting From Date: " + meghpiattendancereport.getExceptionDesc());
								}
								if (meghpiattendancereport.ToDateDropDown()) {

									logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully: ");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On ToDate DropDown: " + meghpiattendancereport.getExceptionDesc());
								}
								
								if (meghpiattendancereport.ToDateSelected(week1Sunday)) {

									logResults.createLogs("Y", "PASS", "To Date Selected Successfully: " + week1Sunday);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Selecting To Date: " + meghpiattendancereport.getExceptionDesc());
								}
								if (meghpiattendancereport.FilterSaveButton()) {

									logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully: ");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Save Button: " + meghpiattendancereport.getExceptionDesc());
								}
								if (OfficePage.SearchDropDown()) {
									logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully: ");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On SearchDropDown : " + OfficePage.getExceptionDesc());
								}
								 if (meghpiovertimepage.OverTimeReportEnrollIDCheckBox()) {
									 
									 
										logResults.createLogs("Y", "PASS", "Emp ID Search Option CheckBox Clicked Successfully: ");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp id CheckBox: " + meghpiovertimepage.getExceptionDesc());
									}
								if (OfficePage.SearchDropDown()) {
									logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully: ");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On SearchDropDown : " + OfficePage.getExceptionDesc());
								}
								 if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(EmpID)) {

										logResults.createLogs("Y", "PASS", "Emp FirstName Inputted Successfully: " + EmpID);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Emp First Name: " + meghpiovertimepage.getExceptionDesc());
									}
								
								if (meghpiovertimepage.EmpFirstRecordDayType(daytype)) {

									logResults.createLogs("Y", "PASS", "Emp DayType Validated Successfully: " + daytype);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Validating Emp Day Type: " + meghpiovertimepage.getExceptionDesc());
								} }
								catch (Exception e) {
								    e.printStackTrace();
								  System.out.println("failed");
								}
						}
						

						// MPI_1202_Approved_OT_Reports_11
								@Test(enabled = true, priority = 12, groups = { "Smoke" })
								public void MPI_1202_Approved_OT_Reports_11()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, perform OT, approve it as an admin, and ensure that the record is displayed in the Approved tab.");

									  // Load Excel data
									ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
											"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,overtimestatus");


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
								        String overtimestatus = data.get(i++);
								        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

								        Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

								        String PunchinDate = details.get("month1WorkingDate");
								        
								        String punchinDateUI = Pramod.convertToUIFormat(PunchinDate);
								        String monthName = details.get("monthName");
								        String year = details.get("year");
										
								        
								        
								        String fromDateofstatus = PunchinDate + fromDateofuserstatus1;
								        String toDateofstatus = PunchinDate + toDateofuserstatus2;
									   
									    
										String inouttime = PunchinDate + " " + inouttime1;
										String secondInOutTime = PunchinDate + " " + secondInOutTime2;
									
										
										// get first day of month
										LocalDate localDate = LocalDate.parse(PunchinDate);
										 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
								
									
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
									MeghLeavePage meghleavepage = new MeghLeavePage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
								MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
								MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
								MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
							

									      TableFieldIds ids = MeghPIAttenAPIPage.executeGetTableFieldIds(
										        baseuri, loginendpoint,
										        Emailid, password, cmpcode,
										        baseuri, tablefieldendpoint,
										        officename,   // company location name
										        AdminFirstName, // entity name
										        entityname    // entity type name
										);
									      System.out.println(ids.companyLocationId );
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
							                description, empid, PunchinDate, inouttime1, secondInOutTime,
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

							        
							        MeghLoginPage meghloginpage = new MeghLoginPage(driver);

							        

									if (meghloginpage.MainLandingPage()) {
										logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
									} 
							        
							        if (RolePermissionpage.EmployeeAttendanceButton()) {

							        	logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
							        } else {
							        	logResults.createLogs("Y", "FAIL",
							        			"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
							        }
							        if (meghpiovertimepage.OverTimeTabOnAdmin()) {

										logResults.createLogs("Y", "PASS", "OverTime Tab Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Tab." + meghpiovertimepage.getExceptionDesc());
									}
							        
							        if (meghleavepage.MonthFilterContains(PunchinDate)) {

										logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + PunchinDate );
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
												+ meghleavepage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeTabSearchTextFieldOnAdmin(firstname)) {

										logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpiovertimepage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeEmpName(firstname,PunchinDate)) {

										logResults.createLogs("Y", "PASS", "Emp Approve Button Clicked Successfully." + firstname + " " + PunchinDate);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On Same Emp Approve Button." + meghpiovertimepage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeEmpApprveCmts(firstname)) {

										logResults.createLogs("Y", "PASS", "Approve Comments Inputted Successfully." + firstname);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Approve Comments." + meghpiovertimepage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeEmpApproveConfirmButton()) {

										logResults.createLogs("Y", "PASS", "Approve Confirm Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On Approve Confirm Button." + meghpiovertimepage.getExceptionDesc());
									}
							      
							   
							    
							   	 if (RolePermissionpage.ReprtButton()) {

										logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeButton()) {

										logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeRequestApprovedTab()) {

										logResults.createLogs("Y", "PASS", "OverTime Report Approved Tab button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Approved Tab Button." + meghpiovertimepage.getExceptionDesc());
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
									if (meghpiattendancereport.DepartmentDropdown("--Select--")) {

										logResults.createLogs("Y", "PASS", "Dept Selected Successfully.  --Select--"  );
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
									}
									
									if (meghpiovertimepage.OverTimeReportFilterButton()) {

										logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
									
									if (meghpiattendancereport.FromDateSelected(PunchinDate)) {

										logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + PunchinDate);
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
									
									if (meghpiattendancereport.ToDateSelected(PunchinDate)) {

										logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + PunchinDate);
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
									 if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(empid)) {

											logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + empid);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpiovertimepage.getExceptionDesc());
										}
									if (meghpiovertimepage.ValidationEmpWithDateAndApproveStatus(overtimestatus,punchinDateUI,firstname)) {

										logResults.createLogs("Y", "PASS", "Emp Regulirization Apporved Record Validated Successfully." + firstname );
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Validating  Emp Approved Record." + meghpiovertimepage.getExceptionDesc());
									}
						
								}
						
								// MPI_1192_Approved_OT_Reports_01
								@Test(enabled = true, priority = 13, groups = { "Smoke" })
								public void MPI_1192_Approved_OT_Reports_01()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionalities of search feature by selecting each search option");

							//		ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

									MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
								
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
									
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
									
									String EmpIDRow ="";
									String EmpLastName ="";
									
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestApprovedTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Approved Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Approved Tab Button." + meghpiovertimepage.getExceptionDesc());
										}
								       
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportEmpIDRecord()) {
											EmpIDRow =  meghpiovertimepage.getempid;
											logResults.createLogs("Y", "PASS", "Emp ID Extracted Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Extracting Emp ID." + meghpiovertimepage.getExceptionDesc());
										}
										if (meghpiovertimepage.OverTimeReportEmpNameRecord()) {
											EmpLastName = meghpiovertimepage.getemplastname;
											logResults.createLogs("Y", "PASS", "Emp Last Name Extracted Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Extracting Emp Last Name." + meghpiovertimepage.getExceptionDesc());
										}
										 if (meghpiovertimepage.OverTimeReportEnrollIDCheckBox()) {
											 
											 
												logResults.createLogs("Y", "PASS", "Emp ID Search Option CheckBox Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp id CheckBox." + meghpiovertimepage.getExceptionDesc());
											}
										 if (meghpiovertimepage.OverTimeReportLastNameCheckBox()) {

												logResults.createLogs("Y", "PASS", "Emp LastName Search Option CheckBox Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp LastName CheckBox." + meghpiovertimepage.getExceptionDesc());
											}
										 if (OfficePage.SearchDropDown()) {
												logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
											}
										
								        if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(EmpIDRow)) {

											logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + EmpIDRow);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpiovertimepage.getExceptionDesc());
										}			
								        
								        if (meghpiovertimepage.OverTimeReportEmpIDRecordValidation(EmpIDRow)) {

											logResults.createLogs("Y", "PASS", "Emp ID Displayed Successfully." + EmpIDRow);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Displaying Emp ID." + meghpiovertimepage.getExceptionDesc());
										}	
								        if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(EmpLastName)) {

											logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + EmpLastName);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpiovertimepage.getExceptionDesc());
										}	
								        if (meghpiovertimepage.OverTimeReportEmpNameRecordValidation(EmpLastName)) {

											logResults.createLogs("Y", "PASS", "Inputted Emp Last Name Record Displayed Successfully." + EmpLastName);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Displaying Emp Last Name Record." + meghpiovertimepage.getExceptionDesc());
										}
						
								}		
						
								// MPI_1193_Approved_OT_Reports_02
								@Test(enabled = true, priority = 14, groups = { "Smoke" })
								public void MPI_1193_Approved_OT_Reports_02()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

									ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
											"subject,ccmail");

								
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
									
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
									
									String subject =data.get(0);
									String ccmail =data.get(1);
									
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestApprovedTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Approved Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Approved Tab Button." + meghpiovertimepage.getExceptionDesc());
										}
								       
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
						
								// MPI_1195_Approved_OT_Reports_04
								@Test(enabled = true, priority = 15, groups = { "Smoke" })
								public void MPI_1195_Approved_OT_Reports_04()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting specific team");

									ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"pin,firstname");

									String pin = data.get(0);
									String firstname = data.get(1);
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
								
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
									    String monthName = details.get("monthName");
									    String year = details.get("year");
									    MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
										MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
									    MeghLoginPage meghloginpage = new MeghLoginPage(driver);
										MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);


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
					 if (RolePermissionpage.ReprtButton()) {
												logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
											}
									        if (meghpiovertimepage.OverTimeButton()) {

												logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
											}
									        if (meghpiovertimepage.OverTimeRequestApprovedTab()) {

												logResults.createLogs("Y", "PASS", "OverTime Report Approved Tab button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Approved Tab Button." + meghpiovertimepage.getExceptionDesc());
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
											if (meghpiovertimepage.OverTimeReportFilterButton()) {

												logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
											
											if (meghpiovertimepage.OverTimeReportAllTabSearchResult()) {

												logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
											}     
								}					
						
								// MPI_1196_Approved_OT_Reports_05
								@Test(enabled = true, priority = 16, groups = { "Smoke" })
								public void MPI_1196_Approved_OT_Reports_05()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

								//	ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
									
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
								
									
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestApprovedTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Approved Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Approved Tab Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										
										if (meghpiovertimepage.OverTimeReportAllTabSearchResult()) {

											logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
										}     
							}  	
						
								// MPI_1197_Approved_OT_Reports_06
								@Test(enabled = true, priority = 17, groups = { "Smoke" })
								public void MPI_1197_Approved_OT_Reports_06()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting employee type");

								//	ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC, "password,captcha");

									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
								
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
									
								
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestApprovedTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Approved Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Approved Tab Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										
										if (meghpiovertimepage.OverTimeReportAllTabSearchResult()) {

											logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
										}     
							}
						
								// MPI_1198_Approved_OT_Reports_07
								@Test(enabled = true, priority = 18, groups = { "Smoke" })
								public void MPI_1198_Approved_OT_Reports_07()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting month and year");

									
								//	ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC, "password,captcha");

									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
									
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
									
								
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestApprovedTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Approved Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Approved Tab Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										
										if (meghpiovertimepage.OverTimeReportDateRow(monthName)) {

											logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + monthName);
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
										}     
							}	
								// MPI_1199_Approved_OT_Reports_08
								@Test(enabled = true, priority = 19, groups = { "Smoke" })
								public void MPI_1199_Approved_OT_Reports_08()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

								//	ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC, "password,captcha");

									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
								
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
									
								
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

									    String PunchinDate = details.get("month1WorkingDate");
									    String punchinDateUI = Pramod.convertToUIFormat(PunchinDate);
									    String monthName = details.get("monthName");
									    String year = details.get("year");
									
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestApprovedTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Approved Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Approved Tab Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										
										if (meghpiattendancereport.FromDateSelected(PunchinDate)) {

											logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + PunchinDate);
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
										
										if (meghpiattendancereport.ToDateSelected(PunchinDate)) {

											logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + PunchinDate);
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
										
										if (meghpiovertimepage.OverTimeReportDateRowValidation(punchinDateUI)) {

											logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + punchinDateUI);
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
										}     
							}		
						
								// MPI_1213_Rejected_OT_Reports_11
								@Test(enabled = true, priority = 20, groups = { "Smoke" })
								public void MPI_1213_Rejected_OT_Reports_11()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, perform OT, Reject the OT Request as an admin, and ensure that the record is displayed in the OT Rejected tab..");

									  // Load Excel data
									ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
											"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,overtimestatus");


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
								        String overtimestatus = data.get(i++);
								        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

								        Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

								        String PunchinDate = details.get("month1WorkingDate");
								        String formattedPrevDate = Pramod.convertToUIFormat(PunchinDate);
								        String monthName = details.get("monthName");
								        String year = details.get("year");
										
								        
								        
								        String fromDateofstatus = PunchinDate + fromDateofuserstatus1;
								        String toDateofstatus = PunchinDate + toDateofuserstatus2;
									   
									    
										String inouttime = PunchinDate + " " + inouttime1;
										String secondInOutTime = PunchinDate + " " + secondInOutTime2;
									
										
										// get first day of month
										LocalDate localDate = LocalDate.parse(PunchinDate);
										 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
										 
									
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
									
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
								MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
								MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
								MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
								MeghLeavePage meghleavepage = new MeghLeavePage(driver);


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
							                description, empid, PunchinDate, inouttime1, secondInOutTime,
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
							    	
							        
							        MeghLoginPage meghloginpage = new MeghLoginPage(driver);
							        

									if (meghloginpage.MainLandingPage()) {
										logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
									}
							       
							        if (RolePermissionpage.EmployeeAttendanceButton()) {

							        	logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
							        } else {
							        	logResults.createLogs("Y", "FAIL",
							        			"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
							        }
							        if (meghpiovertimepage.OverTimeTabOnAdmin()) {

										logResults.createLogs("Y", "PASS", "OverTime Tab Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Tab." + meghpiovertimepage.getExceptionDesc());
									}
							        
							        if (meghleavepage.MonthFilterContains(PunchinDate)) {

										logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + PunchinDate );
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
												+ meghleavepage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeTabSearchTextFieldOnAdmin(firstname)) {

										logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpiovertimepage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeReject(firstname,PunchinDate)) {

										logResults.createLogs("Y", "PASS", " OT Reject Button Clicked Successfully." + firstname + " " + PunchinDate);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On  Emp OT Reject Button." + meghpiovertimepage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeEmpRejectReason(firstname)) {

										logResults.createLogs("Y", "PASS", "Reject Comments Inputted Successfully." + firstname);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Reject Comments." + meghpiovertimepage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeEmpRejectConfirmButton()) {

										logResults.createLogs("Y", "PASS", "Reject Confirm Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On Reject Confirm Button." + meghpiovertimepage.getExceptionDesc());
									}
							        
							     
							   	 if (RolePermissionpage.ReprtButton()) {

										logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeButton()) {

										logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeRequestRejectionTab()) {

										logResults.createLogs("Y", "PASS", "OverTime Report Rejected Tab button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Rejected Tab Button." + meghpiovertimepage.getExceptionDesc());
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
									if (meghpiattendancereport.DepartmentDropdown("--Select--")) {

										logResults.createLogs("Y", "PASS", "Dept Selected Successfully.  --Select--"  );
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
									}
									
									if (meghpiovertimepage.OverTimeReportFilterButton()) {

										logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
									
									if (meghpiattendancereport.FromDateSelected(PunchinDate)) {

										logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + PunchinDate);
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
									
									if (meghpiattendancereport.ToDateSelected(PunchinDate)) {

										logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + PunchinDate);
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
									 if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(empid)) {

											logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + empid);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpiovertimepage.getExceptionDesc());
										}
									if (meghpiovertimepage.ValidationEmpWithDateAndApproveStatus(overtimestatus,formattedPrevDate, firstname)) {

										logResults.createLogs("Y", "PASS", "Emp  OT Record Validated Successfully." + firstname );
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Validating  Emp OT Record." + meghpiovertimepage.getExceptionDesc());
									}
								}
	
								// MPI_1203_Rejected_OT_Reports_01
								@Test(enabled = true, priority = 21, groups = { "Smoke" })
								public void MPI_1203_Rejected_OT_Reports_01()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionalities of search feature by selecting each search option");

							//		ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

									MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
								
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
									
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
								
									String EmpIDRow ="";
									String EmpLastName ="";
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestRejectionTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Rejected Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Rejected Tab Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiattendancereport.DepartmentDropdown("--Select--")) {

											logResults.createLogs("Y", "PASS", "Dept Selected Successfully.  --Select--"  );
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
										}
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportEmpIDRecord()) {
											EmpIDRow =  meghpiovertimepage.getempid;
											logResults.createLogs("Y", "PASS", "Emp ID Extracted Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Extracting Emp ID." + meghpiovertimepage.getExceptionDesc());
										}
										if (meghpiovertimepage.OverTimeReportEmpNameRecord()) {
											EmpLastName = meghpiovertimepage.getemplastname;
											logResults.createLogs("Y", "PASS", "Emp Last Name Extracted Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Extracting Emp Last Name." + meghpiovertimepage.getExceptionDesc());
										}
										 if (meghpiovertimepage.OverTimeReportEnrollIDCheckBox()) {
											 
											 
												logResults.createLogs("Y", "PASS", "Emp ID Search Option CheckBox Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp id CheckBox." + meghpiovertimepage.getExceptionDesc());
											}
										 if (meghpiovertimepage.OverTimeReportLastNameCheckBox()) {

												logResults.createLogs("Y", "PASS", "Emp LastName Search Option CheckBox Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp LastName CheckBox." + meghpiovertimepage.getExceptionDesc());
											}
										 if (OfficePage.SearchDropDown()) {
												logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
											}
										
								        if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(EmpIDRow)) {

											logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + EmpIDRow);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpiovertimepage.getExceptionDesc());
										}			
								        
								        if (meghpiovertimepage.OverTimeReportEmpIDRecordValidation(EmpIDRow)) {

											logResults.createLogs("Y", "PASS", "Emp ID Displayed Successfully." + EmpIDRow);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Displaying Emp ID." + meghpiovertimepage.getExceptionDesc());
										}	
								        if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(EmpLastName)) {

											logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + EmpLastName);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpiovertimepage.getExceptionDesc());
										}	
								        if (meghpiovertimepage.OverTimeReportEmpNameRecordValidation(EmpLastName)) {

											logResults.createLogs("Y", "PASS", "Inputted Emp Last Name Record Displayed Successfully." + EmpLastName);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Displaying Emp Last Name Record." + meghpiovertimepage.getExceptionDesc());
										}
								}	
								
								// MPI_1204_Rejected_OT_Reports_02
								@Test(enabled = true, priority = 22, groups = { "Smoke" })
								public void MPI_1204_Rejected_OT_Reports_02()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

									ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
											"subject,ccmail");

								
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
								
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
									
									String subject =data.get(0);
									String ccmail =data.get(1);
									
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestRejectionTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Rejected Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Rejected Tab Button." + meghpiovertimepage.getExceptionDesc());
										}
								       
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
								
								// MPI_1206_Rejected_OT_Reports_04
								@Test(enabled = true, priority = 23, groups = { "Smoke" })
								public void MPI_1206_Rejected_OT_Reports_04()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting specific team");

									ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
											"firstname,pin");

									
								
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
									
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
									MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
									MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
									
									
								
									String firstname =data.get(0);
									String pin =data.get(1);
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
									    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


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
										 if (RolePermissionpage.ReprtButton()) {

												logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
											}
									        if (meghpiovertimepage.OverTimeButton()) {

												logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
											}
									        if (meghpiovertimepage.OverTimeRequestRejectionTab()) {

												logResults.createLogs("Y", "PASS", "OverTime Report Rejected Tab button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Rejected Tab Button." + meghpiovertimepage.getExceptionDesc());
											}
									    
											
											if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

												logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
											}
											if (meghpiovertimepage.OverTimeReportFilterButton()) {

												logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
											
											if (meghpiovertimepage.OverTimeReportAllTabSearchResult()) {

												logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
											}     
								}						
								
								// MPI_1207_Rejected_OT_Reports_05
								@Test(enabled = true, priority = 24, groups = { "Smoke" })
								public void MPI_1207_Rejected_OT_Reports_05()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

								//	ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

									
								
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
								
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
								
									
								
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestRejectionTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Rejected Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Rejected Tab Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										
										if (meghpiovertimepage.OverTimeReportAllTabSearchResult()) {

											logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
										}     
							} 			
								
								// MPI_1208_Rejected_OT_Reports_06
								@Test(enabled = true, priority = 25, groups = { "Smoke" })
								public void MPI_1208_Rejected_OT_Reports_06()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting employee type");

								//	ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

									
								
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
								
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
									
									
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestRejectionTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Rejected Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Rejected Tab Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										
										if (meghpiovertimepage.OverTimeReportAllTabSearchResult()) {

											logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
										}     
							} 		
								
								// MPI_1209_Rejected_OT_Reports_07
								@Test(enabled = true, priority = 26, groups = { "Smoke" })
								public void MPI_1209_Rejected_OT_Reports_07()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting month and year");

									
								//	ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC, "password,captcha");

									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
									
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
									
								
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestRejectionTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Rejected Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Rejected Tab Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										
										if (meghpiovertimepage.OverTimeReportDateRow(monthName)) {

											logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + monthName);
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
										}     
							}		
						
								// MPI_1210_Rejected_OT_Reports_08
								@Test(enabled = true, priority = 27, groups = { "Smoke" })
								public void MPI_1210_Rejected_OT_Reports_08()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

								//	ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC, "password,captcha");

									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
									
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
									
									
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

									    String PunchinDate = details.get("month1WorkingDate");
									    String PunchinDateSearchUI = Pramod.convertToUIFormat(PunchinDate);
									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestRejectionTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Rejected Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Rejected Tab Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										
										if (meghpiattendancereport.FromDateSelected(PunchinDate)) {

											logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + PunchinDate);
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
										
										if (meghpiattendancereport.ToDateSelected(PunchinDate)) {

											logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + PunchinDate);
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
										
										if (meghpiovertimepage.OverTimeReportDateRowValidation(PunchinDateSearchUI)) {

											logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + PunchinDateSearchUI);
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
										}     
							}
								
								// MPI_1224_Pending_OT_Reports_11
								@Test(enabled = true, priority = 28, groups = { "Smoke" })
								public void MPI_1224_Pending_OT_Reports_11()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, perform OT do not approve the OT request, ensure that the record is displayed in the OT Pending Reports tab.");

									  // Load Excel data
									ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
											"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,overtimestatus");


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
								        String overtimestatus = data.get(i++);
								        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

								        Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

								        String PunchinDate = details.get("month1WorkingDate");
								        String punchinDateUI = Pramod.convertToUIFormat(PunchinDate);
								        String monthName = details.get("monthName");
								        String year = details.get("year");

								        // Extract values
								        String firstDayOfMonth = details.get("firstDayOfMonth");
								        
								        
								        String fromDateofstatus = PunchinDate + fromDateofuserstatus1;
								        String toDateofstatus = PunchinDate + toDateofuserstatus2;
									   
									    
										String inouttime = PunchinDate + " " + inouttime1;
										String secondInOutTime = PunchinDate + " " + secondInOutTime2;
					
										 
									
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
									
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
								MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
								MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
								MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
								  

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
							                description, empid, PunchinDate, inouttime1, secondInOutTime,
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
							        if (meghpiovertimepage.OverTimeButton()) {

										logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
									}
							        if (meghpiovertimepage.OverTimeRequestPendingTab()) {

										logResults.createLogs("Y", "PASS", "OverTime Report Pending Tab button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Pending Tab Button." + meghpiovertimepage.getExceptionDesc());
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
									
									if (meghpiattendancereport.DepartmentDropdown("--Select--")) {

										logResults.createLogs("Y", "PASS", "Dept Selected Successfully.  --Select--"  );
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
									}
									if (meghpiovertimepage.OverTimeReportFilterButton()) {

										logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
									
									if (meghpiattendancereport.FromDateSelected(PunchinDate)) {

										logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + PunchinDate);
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
									
									if (meghpiattendancereport.ToDateSelected(PunchinDate)) {

										logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + PunchinDate);
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
									 if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(empid)) {

											logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + empid);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpiovertimepage.getExceptionDesc());
										}
									if (meghpiovertimepage.ValidationEmpWithDateAndApproveStatus(overtimestatus,punchinDateUI,firstname)) {

										logResults.createLogs("Y", "PASS", "Emp Regulirization Apporved Record Validated Successfully." + firstname );
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Validating  Emp Approved Record." + meghpiovertimepage.getExceptionDesc());
									}
								}
								
								// MPI_1214_Pending_OT_Reports_01
								@Test(enabled = true, priority = 29, groups = { "Smoke" })
								public void MPI_1214_Pending_OT_Reports_01()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionalities of search feature by selecting each search option");

							//		ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

									MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
								
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
								
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
								
									String EmpIDRow ="";
									String EmpLastName ="";
									
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestPendingTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Pending Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Pending Tab Button." + meghpiovertimepage.getExceptionDesc());
										}
								       
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportEmpIDRecord()) {
											EmpIDRow =  meghpiovertimepage.getempid;
											logResults.createLogs("Y", "PASS", "Emp ID Extracted Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Extracting Emp ID." + meghpiovertimepage.getExceptionDesc());
										}
										if (meghpiovertimepage.OverTimeReportEmpNameRecord()) {
											EmpLastName = meghpiovertimepage.getemplastname;
											logResults.createLogs("Y", "PASS", "Emp Last Name Extracted Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Extracting Emp Last Name." + meghpiovertimepage.getExceptionDesc());
										}
										 if (meghpiovertimepage.OverTimeReportEnrollIDCheckBox()) {
											 
											 
												logResults.createLogs("Y", "PASS", "Emp ID Search Option CheckBox Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp id CheckBox." + meghpiovertimepage.getExceptionDesc());
											}
										 if (meghpiovertimepage.OverTimeReportLastNameCheckBox()) {

												logResults.createLogs("Y", "PASS", "Emp LastName Search Option CheckBox Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp LastName CheckBox." + meghpiovertimepage.getExceptionDesc());
											}
										 if (OfficePage.SearchDropDown()) {
												logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
											}
										
								        if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(EmpIDRow)) {

											logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + EmpIDRow);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpiovertimepage.getExceptionDesc());
										}			
								        
								        if (meghpiovertimepage.OverTimeReportEmpIDRecordValidation(EmpIDRow)) {

											logResults.createLogs("Y", "PASS", "Emp ID Displayed Successfully." + EmpIDRow);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Displaying Emp ID." + meghpiovertimepage.getExceptionDesc());
										}	
								        if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(EmpLastName)) {

											logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + EmpLastName);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpiovertimepage.getExceptionDesc());
										}	
								        if (meghpiovertimepage.OverTimeReportEmpNameRecordValidation(EmpLastName)) {

											logResults.createLogs("Y", "PASS", "Inputted Emp Last Name Record Displayed Successfully." + EmpLastName);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Displaying Emp Last Name Record." + meghpiovertimepage.getExceptionDesc());
										}
						
								}				
								
								// MPI_1215_Pending_OT_Reports_02
								@Test(enabled = true, priority = 30, groups = { "Smoke" })
								public void MPI_1215_Pending_OT_Reports_02()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

									ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
											"subject,ccmail");

								
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
									
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
								
									String subject =data.get(0);
									String ccmail =data.get(1);
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestPendingTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Pending Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Pending Tab Button." + meghpiovertimepage.getExceptionDesc());
										}
								       
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
								
								
								// MPI_1217_Pending_OT_Reports_04
								@Test(enabled = true, priority = 31, groups = { "Smoke" })
								public void MPI_1217_Pending_OT_Reports_04()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting specific team");

									ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
											"firstname,pin");

									
								
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
									
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
									MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
									MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
									
								
									String firstname =data.get(0);
									String pin =data.get(1);
									
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
									    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


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
										 if (RolePermissionpage.ReprtButton()) {

												logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
											}
									        if (meghpiovertimepage.OverTimeButton()) {

												logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
											}
									        if (meghpiovertimepage.OverTimeRequestPendingTab()) {

												logResults.createLogs("Y", "PASS", "OverTime Report Pending Tab button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Pending Tab Button." + meghpiovertimepage.getExceptionDesc());
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
											if (meghpiovertimepage.OverTimeReportFilterButton()) {

												logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
											
											if (meghpiovertimepage.OverTimeReportAllTabSearchResult()) {

												logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
											} else {
												logResults.createLogs("Y", "FAIL",
														"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
											}     
								}					
								
								// MPI_1218_Pending_OT_Reports_05
								@Test(enabled = true, priority = 32, groups = { "Smoke" })
								public void MPI_1218_Pending_OT_Reports_05()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

							//		ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

									
								
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
									
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
								
									
							
								
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestPendingTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Pending Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Pending Tab Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										
										if (meghpiovertimepage.OverTimeReportAllTabSearchResult()) {

											logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
										}     
							} 			
								
								// MPI_1219_Pending_OT_Reports_06
								@Test(enabled = true, priority = 33, groups = { "Smoke" })
								public void MPI_1219_Pending_OT_Reports_06()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting employee type");

						//			ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

									
								
									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
								
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
									
									
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestPendingTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Pending Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Pending Tab Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										
										if (meghpiovertimepage.OverTimeReportAllTabSearchResult()) {

											logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
										}     
							} 					
								
								// MPI_1220_Pending_OT_Reports_07
								@Test(enabled = true, priority = 34, groups = { "Smoke" })
								public void MPI_1220_Pending_OT_Reports_07()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting month and year");

									
							//		ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
							
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
									
									
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestPendingTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Pending Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Pending Tab Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										
										if (meghpiovertimepage.OverTimeReportDateRow(monthName)) {

											logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + monthName);
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
										}     
							}		
								
								// MPI_1221_Pending_OT_Reports_08
								@Test(enabled = true, priority = 35, groups = { "Smoke" })
								public void MPI_1221_Pending_OT_Reports_08()  {
									String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
									logResults.createExtentReport(currTC);
									logResults.setScenarioName(
											"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

							//		ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,"password,captcha");

									MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								
								
									MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
									MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
									
									
									
									 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

									    String PunchinDate = details.get("month1WorkingDate");
									    String PunchinDateSearchUI = Pramod.convertToUIFormat(PunchinDate);
									    String monthName = details.get("monthName");
									    String year = details.get("year");
						
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
								        if (meghpiovertimepage.OverTimeButton()) {

											logResults.createLogs("Y", "PASS", "OverTime Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Button." + meghpiovertimepage.getExceptionDesc());
										}
								        if (meghpiovertimepage.OverTimeRequestPendingTab()) {

											logResults.createLogs("Y", "PASS", "OverTime Report Pending Tab button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Pending Tab Button." + meghpiovertimepage.getExceptionDesc());
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
										if (meghpiovertimepage.OverTimeReportFilterButton()) {

											logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button." + meghpiovertimepage.getExceptionDesc());
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
										
										if (meghpiattendancereport.FromDateSelected(PunchinDate)) {

											logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + PunchinDate);
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
										
										if (meghpiattendancereport.ToDateSelected(PunchinDate)) {

											logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + PunchinDate);
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
										
										if (meghpiovertimepage.OverTimeReportDateRowValidation(PunchinDateSearchUI)) {

											logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + PunchinDateSearchUI);
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
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
