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
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeRequestPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationReportsPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationRequestPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIEmpOTRequestReportsTest {


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
   		teamname = "AutoTN" + initBase.executionRunTime;
   		designationname = "AutoDESN" + initBase.executionRunTime;
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");

   	}
	

	// MPI_1801_EmpOTAllTab_Reports_01
			@Test(enabled = true, priority = 1, groups = { "Smoke" })
			public void MPI_1801_EmpOTAllTab_Reports_01()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionalities of search feature by selecting each search option.");

				  // Load Excel data
				ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC,
						"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,policyname,duration,otmaxminutes,captcha");


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
			        String policyname = data.get(i++);
			        String duration = data.get(i++);
			        String otmaxminutes = data.get(i++);
			        String captcha = data.get(i++);
			        
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

			        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

			        String PunchinDate = dateDetails.get("month1WorkingDate");
			        String punchinDateUI = Pramod.convertToUIFormat(PunchinDate);
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
			        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
			        
					String inouttime = PunchinDate + " " + inouttime1;
					String secondInOutTime = PunchinDate + " " + secondInOutTime2;
				
					
					
					 
				
					 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
				MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
			MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
		    MeghLoginPage meghloginpage = new MeghLoginPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

			  

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
			    
			    if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed." + meghloginpage.getExceptionDesc());
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
					
					
			        if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(punchinDateUI)) {

						logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + punchinDateUI);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpiovertimepage.getExceptionDesc());
					}			
			        
			        if (meghpiovertimepage.OverTimeReportDateRowValidationOnEmp(punchinDateUI)) {

						logResults.createLogs("Y", "PASS", "Emp ID Displayed Successfully." + PunchinDate);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Displaying Emp ID." + meghpiovertimepage.getExceptionDesc());
					}	
			       
	
			}	

			    
			// MPI_1802_EmpOTAllTab_Reports_02
			@Test(enabled = true, priority = 2, groups = { "Smoke" })
			public void MPI_1802_EmpOTAllTab_Reports_02()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

				ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC,
						"subject,ccmail,empid,captcha");
			
				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			
				
				MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				MeghLoginTest meghlogintest = new MeghLoginTest();

			
			
				String subject =data.get(0);
				String ccmail =data.get(1);
				String empid =data.get(2);
				String captcha=data.get(3);
				
				Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

			    String monthName = details.get("monthName");
			    String year = details.get("year");	
			    
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
			    
			// MPI_1803_EmpOTAllTab_Reports_03
			@Test(enabled = true, priority = 3, groups = { "Smoke" })
			public void MPI_1803_EmpOTAllTab_Reports_03()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

		ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC,"empid,captcha");

		String empid = data.get(0);
		String captcha = data.get(1);
		
				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			
				
				MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				
				
				
				  Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

				    String PunchinDate = details.get("month1WorkingDate");
				    String PunchinDateSearchUI = Pramod.convertToUIFormat(PunchinDate);
				    String monthName = details.get("monthName");
				    String year = details.get("year");
	
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
					
					if (meghpiovertimepage.OverTimeReportDateRowValidationOnEmp(PunchinDateSearchUI)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + PunchinDateSearchUI);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
					}     
		}    
				    
			    
			// MPI_1804_EmpOTAllTab_Reports_04
						@Test(enabled = true, priority = 4, groups = { "Smoke" })
						public void MPI_1804_EmpOTAllTab_Reports_04()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, do OT during the last week of last month. In the OT All Request Reports module, apply the filter for the last week of last month and ensure that the corresponding OT request record is displayed.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC,
									"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,firstname,empid,captcha");


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
						       
						        String firstname = data.get(i++);
						        String empid = data.get(i++);
						        String captcha = data.get(i++);
						        
						        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

						        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

						        String PunchinDate = dateDetails.get("month20WorkingDate");
						        String punchinDateUI = Pramod.convertToUIFormat(PunchinDate);
						        String monthName = dateDetails.get("monthName");
						        String year = dateDetails.get("year");
						        
						        
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
					    MeghLoginPage meghloginpage = new MeghLoginPage(driver);
						MeghLoginTest meghlogintest = new MeghLoginTest();
	                    MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
						MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

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
						    
						    if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
								logResults.createLogs("Y", "PASS", "Login Done Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Login Is Failed." + meghloginpage.getExceptionDesc());
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
					        if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

								logResults.createLogs("Y", "PASS", "OT Summary Clicked Successfully On Admin.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On OT Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
							}
							if (meghpiOTpage.AdminActPendingSummaryClicked()) {

								logResults.createLogs("Y", "PASS", "Admin Account OverTimeRequest Pending Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Admin OverTimeRequest Pending Summary Button." + meghpiOTpage.getExceptionDesc());
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
								
								if (meghpiovertimepage.OverTimeReportDateRowValidationOnEmp(punchinDateUI)) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + punchinDateUI);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());

								}								
						}
			    
			    
			    
						// MPI_1805_EmpOTApproved_Reports_01
						@Test(enabled = true, priority = 5, groups = { "Smoke" })
						public void MPI_1805_EmpOTApproved_Reports_01()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionalities of search feature by selecting each search option ");

					ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC,"empid,captcha,overtimestatus,firstname");

					String empid = data.get(0);
					String captcha = data.get(1);
					String overtimestatus = data.get(2);
					String firstname = data.get(3);
					
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
							
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

							  Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

							    String PunchinDate = details.get("month1WorkingDate");
							    String punchinDateUI = Pramod.convertToUIFormat(PunchinDate);
							    String monthName = details.get("monthName");
							    String year = details.get("year");
				
							    MeghLoginPage meghloginpage = new MeghLoginPage(driver);
								MeghLoginTest meghlogintest = new MeghLoginTest();
								MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);


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
						
							
							 if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(punchinDateUI)) {

									logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + punchinDateUI);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpiovertimepage.getExceptionDesc());
								}
							if (meghpiovertimepage.ValidationEmpWithDateAndApproveStatusOnEmp(overtimestatus,punchinDateUI)) {

								logResults.createLogs("Y", "PASS", "Emp Regulirization Apporved Record Validated Successfully." + overtimestatus + " " + punchinDateUI );
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating  Emp Approved Record." + meghpiovertimepage.getExceptionDesc());
							}
				
						}
			    
			    
			    
			    
						// MPI_1806_EmpOTApproved_Reports_02
						@Test(enabled = true, priority = 6, groups = { "Smoke" })
						public void MPI_1806_EmpOTApproved_Reports_02()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

							ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC,
									"subject,ccmail,empid,captcha");

						
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
							
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
							
							String subject =data.get(0);
							String ccmail =data.get(1);
							String empid = data.get(2);
							String captcha = data.get(3);
							
							 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
							    String monthName = details.get("monthName");
							    String year = details.get("year");
				
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
			    
			    
						// MPI_1807_EmpOTApproved_Reports_03
						@Test(enabled = true, priority = 7, groups = { "Smoke" })
						public void MPI_1807_EmpOTApproved_Reports_03()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

							ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC, "empid,captcha");
                                   String   empid = data.get(0);
                                   String captcha = data.get(1);
							
                       			MeghLoginTest meghlogintest = new MeghLoginTest();

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
						
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
							
						
							 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

							    String PunchinDate = details.get("month1WorkingDate");
							    String punchinDateUI = Pramod.convertToUIFormat(PunchinDate);
							    String monthName = details.get("monthName");
							    String year = details.get("year");
							
				
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
								
								if (meghpiovertimepage.OverTimeReportDateRowValidationOnEmp(punchinDateUI)) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + punchinDateUI);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
								}     
					}
			    
			    
						// MPI_1808_EmpOTApproved_Reports_04
						@Test(enabled = true, priority = 8, groups = { "Smoke" })
						public void MPI_1808_EmpOTApproved_Reports_04()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, do OT during the last week of last month and as a admin approve it. login as employee In the OT Approved Request Reports module, apply the filter for the last week of last month and ensure that the corresponding Approved OT request record is displayed.");

							ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC, "empid,captcha");
                                   String   empid = data.get(0);
                                   String captcha = data.get(1);
							
                       			MeghLoginTest meghlogintest = new MeghLoginTest();

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
						
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
							
						
							  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

						        String PunchinDate = dateDetails.get("month20WorkingDate");
						        String punchinDateUI = Pramod.convertToUIFormat(PunchinDate);
						        String monthName = dateDetails.get("monthName");
						        String year = dateDetails.get("year");
							
				
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
								
								if (meghpiovertimepage.OverTimeReportDateRowValidationOnEmp(punchinDateUI)) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + punchinDateUI);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
								}     
					}   
			    
						// MPI_1809_EmpPendingOT_Reports_01
						@Test(enabled = true, priority = 9, groups = { "Smoke" })
						public void MPI_1809_EmpPendingOT_Reports_01()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionalities of search feature by selecting each search option.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC,
									"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,firstname,empid,captcha");


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
						       
						        String firstname = data.get(i++);
						        String empid = data.get(i++);
						        String captcha = data.get(i++);
						        
						        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

						        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

						        String month2WorkingDate = dateDetails.get("month2WorkingDate");
						     String uidate = Pramod.convertToUIFormat(month2WorkingDate);
						        String monthName = dateDetails.get("monthName");
						        String year = dateDetails.get("year");
						        
						        
								String inouttime = month2WorkingDate + " " + inouttime1;
								String secondInOutTime = month2WorkingDate + " " + secondInOutTime2;
							
								
								// get first day of month
								LocalDate localDate = LocalDate.parse(month2WorkingDate);
								 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
						
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
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
						    
						    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
								logResults.createLogs("Y", "PASS", "Login Done Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Login Is Failed." + meghloginpage.getExceptionDesc());
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
							
							
					        
					        if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(uidate)) {

								logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + uidate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpiovertimepage.getExceptionDesc());
							}	
					        if (meghpiovertimepage.OverTimeReportDateRowValidationOnEmp(uidate)) {

								logResults.createLogs("Y", "PASS", "Inputted Emp Last Name Record Displayed Successfully." + uidate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying Emp Last Name Record." + meghpiovertimepage.getExceptionDesc());
							}
			
					}		
						    
						    
						    
						// MPI_1810_EmpPendingOT_Reports_02
						@Test(enabled = true, priority = 10, groups = { "Smoke" })
						public void MPI_1810_EmpPendingOT_Reports_02()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

							ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC,
									"subject,ccmail,empid,captcha");
							
							String subject = data.get(0);
							String ccmail = data.get(1);
							String empid = data.get(2);
							String captcha = data.get(3);

						
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
							
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
						
							 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
							    String monthName = details.get("monthName");
							    String year = details.get("year");
				
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
											    
						    
						// MPI_1811_EmpPendingOT_Reports_03
						@Test(enabled = true, priority = 11, groups = { "Smoke" })
						public void MPI_1811_EmpPendingOT_Reports_03()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

							ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC,"empid,captcha");
                                                 String  empid = data.get(0);
                                                 String captcha = data.get(1);
							
							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
						
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
							
							
							 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

							    String PunchinDate = details.get("month2WorkingDate");
							    String PunchinDateSearchUI = Pramod.convertToUIFormat(PunchinDate);
							    String monthName = details.get("monthName");
							    String year = details.get("year");
				
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
								
								if (meghpiovertimepage.OverTimeReportDateRowValidationOnEmp(PunchinDateSearchUI)) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + PunchinDateSearchUI);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
								}   
				
					}	    
						    
						    
						// MPI_1812_EmpPendingOT_Reports_04
						@Test(enabled = true, priority = 12, groups = { "Smoke" })
						public void MPI_1812_EmpPendingOT_Reports_04()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, do OT during the last week of last month. In the OT Pending Request Reports module, apply the filter for the last week of last month and ensure that the corresponding Pending OT request record is displayed..");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC,
									"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,firstname,empid,captcha");


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
						       
						        String firstname = data.get(i++);
						        String empid = data.get(i++);
						        String captcha = data.get(i++);
						        
						        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

						        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

						        String PunchinDate = dateDetails.get("month19WorkingDate");
						        String punchinDateUI = Pramod.convertToUIFormat(PunchinDate);
						        String monthName = dateDetails.get("monthName");
						        String year = dateDetails.get("year");
						        
						        
								String inouttime = PunchinDate + " " + inouttime1;
								String secondInOutTime = PunchinDate + " " + secondInOutTime2;
							
								
								// get first day of month
								LocalDate localDate = LocalDate.parse(PunchinDate);
								 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
						
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);

							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
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
								
								if (meghpiovertimepage.OverTimeReportDateRowValidationOnEmp(punchinDateUI)) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + punchinDateUI);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());

								}								
						}				    
						    
						    
						    
						// MPI_1814_EmpRejectedOT_Reports_01
						@Test(enabled = true, priority = 13, groups = { "Smoke" })
						public void MPI_1814_EmpRejectedOT_Reports_01()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionalities of search feature by selecting each search option ");

					ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC,"empid,captcha,firstname,password");

					String empid = data.get(0);
					String captcha = data.get(1);
					String firstname = data.get(2);
					String password = data.get(3);
					
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
							
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

							  Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

							    String PunchinDate = details.get("month2WorkingDate");
							String uidate = Pramod.convertToUIFormat(PunchinDate);
							    String monthName = details.get("monthName");
							    String year = details.get("year");
				
							    MeghLoginPage meghloginpage = new MeghLoginPage(driver);
								MeghLoginTest meghlogintest = new MeghLoginTest();
						        MeghLeavePage meghleavepage = new MeghLeavePage(driver);


								  if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
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
								
								
						       	
						        if (meghpiovertimepage.OverTimeReportAllTabSearchTextField(uidate)) {

									logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + uidate);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpiovertimepage.getExceptionDesc());
								}	
						        if (meghpiovertimepage.OverTimeReportDateRowValidationOnEmp(uidate)) {

									logResults.createLogs("Y", "PASS", "Inputted Emp Last Name Record Displayed Successfully." + uidate);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Displaying Emp Last Name Record." + meghpiovertimepage.getExceptionDesc());
								}
						}
						    
						    
						// MPI_1815_EmpRejectedOT_Reports_02
						@Test(enabled = true, priority = 14, groups = { "Smoke" })
						public void MPI_1815_EmpRejectedOT_Reports_02()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

							ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC,
									"subject,ccmail,empid,captcha");

						
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
						
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
							
							String subject =data.get(0);
							String ccmail =data.get(1);
							String empid = data.get(2);
							String captcha = data.get(3);
							
							 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
							    String monthName = details.get("monthName");
							    String year = details.get("year");
				
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
						    
						    
						// MPI_1816_EmpRejectedOT_Reports_03
						@Test(enabled = true, priority = 15, groups = { "Smoke" })
						public void MPI_1816_EmpRejectedOT_Reports_03()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

							ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC, "empid,captcha");
String empid = data.get(0);
String captcha = data.get(1);
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
							
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							
							
							
							 Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

							    String PunchinDate = details.get("month2WorkingDate");
							    String PunchinDateSearchUI = Pramod.convertToUIFormat(PunchinDate);
							    String monthName = details.get("monthName");
							    String year = details.get("year");
				
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
								
								if (meghpiovertimepage.OverTimeReportDateRowValidationOnEmp(PunchinDateSearchUI)) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + PunchinDateSearchUI);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
								}     
					}    
						    
						    
						    
						// MPI_1817_EmpRejectedOT_Reports_04
						@Test(enabled = true, priority = 16, groups = { "Smoke" })
						public void MPI_1817_EmpRejectedOT_Reports_04()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, perform an OT request during the last week of the previous month. Log in as an admin and reject the OT request. Then, log in as the employee and navigate to Reports → OT Rejected Request Reports. Apply the filter for the last week of the previous month and verify that the corresponding rejected OT request record is displayed.");

							ArrayList<String> data = initBase.loadExcelData("EmpOTRequestReports", currTC, "empid,captcha,firstname,password");
                                   String   empid = data.get(0);
                                   String captcha = data.get(1);
                                   String firstname = data.get(2);
                                   String password = data.get(3);
							
                       			MeghLoginTest meghlogintest = new MeghLoginTest();

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
						
							MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

							  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
						        String PunchinDate = dateDetails.get("month19WorkingDate");
						        String PunchinDateSearchUI = Pramod.convertToUIFormat(PunchinDate);
						        String monthName = dateDetails.get("monthName");
						        String year = dateDetails.get("year");
							
				
							    MeghLoginPage meghloginpage = new MeghLoginPage(driver);

							    if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
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
								
								if (meghpiovertimepage.OverTimeReportDateRowValidationOnEmp(PunchinDateSearchUI)) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + PunchinDateSearchUI);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
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

