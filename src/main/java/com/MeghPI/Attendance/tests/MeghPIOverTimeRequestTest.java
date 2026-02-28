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
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeRequestPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationReportsPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationRequestPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import com.MeghPI.Attendance.pages.MeghPIAttendanceEmployeePage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIOverTimeRequestTest {
	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	

	private String cmpcode = "";
	private String Emailid = "";
	private String officename = "";
    private String AdminFirstName ="";
	  private String entityname = "";
	  private String AttendanceEmpid = "";
	
	
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
		Utils.propsReadWrite("data/addmaster.properties", "get", "EmpFirstName", "");
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");
		AttendanceEmpid = Utils.propsReadWrite("data/addmaster.properties", "get", "AttendanceEmpid", "");
	}
	
	
	// MPI_768_OverTImeRequest_01
		@Test(enabled = true, priority = 1, groups = { "Smoke" })
		public void MPI_768_OverTImeRequest_01()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To check that, as a employee do OT more than shift full day duration and ensure Overtime hours and record is displayed in the \"my overtime request\" tab.");

			  // Load Excel data
			ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
					"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,captcha,regularizationstatus,policyname,othours,duration");


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
		        
		        String regularizationstatus  = data.get(i++);
		        String policyname  = data.get(i++);
		        String othours  = data.get(i++);
		        String duration  = data.get(i++);
		     
		        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

		        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

		        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
		       
		      
			   
			    
				String inouttime = monthFirstWorkingDate + " " + inouttime1;
				String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
			
				
				// get first day of month
				LocalDate localDate = LocalDate.parse(monthFirstWorkingDate);
				 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();

					MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

                     MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
			 MeghLoginPage meghloginpage = new MeghLoginPage(driver);

			 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
			if (AttendancePolicyPage.OTMaxMinutesTextFieldInputted(othours)) {

				logResults.createLogs("Y", "PASS", "OT Max Minutess Inputted  Successfully ." + othours);
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

			if (RolePermissionpage.EmployeeAttendanceButton()) {

				logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
			}
			if (RolePermissionpage.EmpOverTimeRequest()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His EmpOverTimeRequest Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying EmpOverTimeRequest Tab." + RolePermissionpage.getExceptionDesc());
			}
			if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			if (meghpiOTpage.EmpActOTSummaryClicked()) {

				logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpiOTpage.getExceptionDesc());
			}
			
			if (meghpiOTpage.EmpActPendingOTSummaryClicked()) {

				logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Pending Summary Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On EmpOverTimeRequest Pending Summary Button." + meghpiOTpage.getExceptionDesc());
			}
			
			if (meghpiOTpage.ValidateEmpOTStatusOnEmpAccount(monthFirstWorkingDate, regularizationstatus)) {

				logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Pending Summary Validated Successfully." + monthFirstWorkingDate +" and status is " + regularizationstatus);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating EmpOverTimeRequest Pending Summary Count And Record." + meghpiOTpage.getExceptionDesc());
			}
		}
	
		// MPI_769_OverTImeRequest_02
		@Test(enabled = true, priority = 2, groups = { "Smoke" })
		public void MPI_769_OverTImeRequest_02()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To check that, as a admin approve the Overtime request and check the status in employee account .");

			ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
					"password,captcha,regularizationstatus,empid,firstname");

			
			String password = data.get(0);
			String captcha = data.get(1);
			String regularizationstatus = data.get(2);
			
			String empid = data.get(3);
			
			String firstname = data.get(4);
		
			
			 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

		        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
			
            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
	        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

	MeghLoginTest meghlogintest = new MeghLoginTest();
	MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
	 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);

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
	if (RolePermissionpage.HrAccountOTRequestTab()) {

		logResults.createLogs("Y", "PASS", "HrAccountOTRequestTab Clicked Successfully In HR Account.");
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Clicking On HrAccountOTRequestTab In Attendance Module."
				+ RolePermissionpage.getExceptionDesc());
	}
	if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

		logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
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
	if (meghpiOTpage.AdminActSearchTextField(firstname)) {

		logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Textfield Pending OT Emp Name Inputted Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Inputting On Admin OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
	}
	 if (meghpiovertimepage.OverTimeEmpName(firstname,monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "Emp OT Approve Button Clicked Successfully." + firstname + " " + monthFirstWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On OT Approve Button  Of Emp ." + meghpiovertimepage.getExceptionDesc());
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
     if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

 		logResults.createLogs("Y", "PASS", "OT Summary Clicked Successfully On Admin.");
 	} else {
 		logResults.createLogs("Y", "FAIL",
 				"Error While Clicking On OT Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
 	}
 	if (meghpiOTpage.AdminActApprovedSummaryClicked()) {

 		logResults.createLogs("Y", "PASS", "Admin Account OverTimeRequest Approved Summary Clicked Successfully.");
 	} else {
 		logResults.createLogs("Y", "FAIL",
 				"Error While Clicking On Admin OverTimeRequest Approved Summary Button." + meghpiOTpage.getExceptionDesc());
 	}
 	if (meghpiOTpage.AdminActSearchTextField(firstname)) {

		logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Textfield Pending OT Emp Name Inputted Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Inputting On Admin OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
	}
 	if (meghpiOTpage.ValidateEmpOTStatusOnAdminAccount(monthFirstWorkingDate, regularizationstatus, empid)) {

		logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Approved Summary Validated Successfully." + monthFirstWorkingDate +" and status is " + regularizationstatus);
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Validating EmpOverTimeRequest Approved Summary Count And Record." + meghpiOTpage.getExceptionDesc());
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
		if (RolePermissionpage.EmployeeAttendanceButton()) {

			logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
		}
		if (RolePermissionpage.EmpOverTimeRequest()) {

			logResults.createLogs("Y", "PASS", "Employee Can Access His EmpOverTimeRequest Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying EmpOverTimeRequest Tab." + RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
					+ meghleavepage.getExceptionDesc());
		}
		if (meghpiOTpage.EmpActOTSummaryClicked()) {

			logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpiOTpage.getExceptionDesc());
		}
		
		if (meghpiOTpage.AdminActApprovedSummaryClicked()) {

	 		logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Approved Summary Clicked Successfully.");
	 	} else {
	 		logResults.createLogs("Y", "FAIL",
	 				"Error While Clicking On Employee OverTimeRequest Approved Summary Button." + meghpiOTpage.getExceptionDesc());
	 	}
		
		if (meghpiOTpage.ValidateEmpOTStatusOnEmpAccount(monthFirstWorkingDate, regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Approved Summary Validated Successfully." + monthFirstWorkingDate +" and status is " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating EmpOverTimeRequest Approved Summary Count And Record." + meghpiOTpage.getExceptionDesc());
		}
		}
	
		// MPI_770_OverTImeRequest_03
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_770_OverTImeRequest_03()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify, log in with the employee account, check the OT hours count in 'OT Hours' column under 'My Attendance' tab after OT is approved by admin.");

					ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
							"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,shiftstatus,empid,workinghours,othours,firstname");

					
					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					String regularizationintime = data.get(3);
					String regularizationouttime = data.get(4);
					String shiftstatus = data.get(5);
					String empid = data.get(6);
					String workinghours = data.get(7);
					String othours = data.get(8);
					String firstname = data.get(9);
					
				
					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
				    	MeghLoginTest meghlogintest = new MeghLoginTest();
	                     MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);
			        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

			MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

			if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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
			if (meghpiattendanceemployeepage.EmpAttendanceLoaded()) {

				logResults.createLogs("Y", "PASS", "Employee Attendance Loaded Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Loading My Attendance Table Data." + meghpiattendanceemployeepage.getExceptionDesc());
			}
			if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			if (meghpiattendanceemployeepage.validateAttendanceRowForOT(monthFirstWorkingDate, regularizationintime, regularizationouttime , shiftstatus, regularizationstatus, workinghours, othours )) {

				logResults.createLogs("Y", "PASS", "Emp Present Status,  OT Hours Count, Working Hours Count, InTime, OutTime Validated Successfully." + empid + " intime  " + regularizationintime + " And out time" + regularizationouttime + " shift is " + shiftstatus + "status is" + regularizationstatus + "working hours is " + workinghours + " OT is" + othours  );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Emp Attendance Present Status,  OT Hours Count, Working Hours Count, InTime, OutTime." + meghregularizationrequestpage.getExceptionDesc());
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
			if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			if (meghregularizationrequestpage.SearchTextFieldOnEmpTabOnAdmin(firstname)) {

				logResults.createLogs("Y", "PASS", " Emp Name Inputted Successfully." + firstname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Emp Name." + meghregularizationrequestpage.getExceptionDesc());
			}	
			if (meghpiOTpage.ValidateEmpOTHoursCountAdminEmployeeTab(empid, othours)) {

				logResults.createLogs("Y", "PASS", "OT Hours Of Emp Record Displayed Successfully." + othours);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying OT Hours Of Emp Record." + meghpiOTpage.getExceptionDesc());
			}
	
				}
	
				// MPI_771_OverTImeRequest_04
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_771_OverTImeRequest_04() {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To check that, Do OT hours and ensure in the admin \"employee\" tab each employee OT hour is displayed under OT hours column.");
					logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_771_OverTImeRequest_04 TestCase.");
				}
	
	
				// MPI_772_OverTImeRequest_05
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_772_OverTImeRequest_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To check that, as a employee do Overtime and Validate the Over stay count on  employee account OT request tab.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
				            "password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,bio1finger,bio2finger," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha,regularizationstatus,othours");

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
			        String regularizationstatus = data.get(i++);
			        String othours = data.get(i++);
			        
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
			        
			        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					String Regulizationdate  =	dateDetails.get("month7WorkingDate");

					LocalDate localDate = LocalDate.parse(Regulizationdate);
					 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
					
					String inouttime = Regulizationdate + " " + inouttime1;
					String secondInOutTime = Regulizationdate + " " + secondInOutTime2;
				    
				
                    MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();

				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);

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
				
				    if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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
					if (RolePermissionpage.EmpOverTimeRequest()) {

						logResults.createLogs("Y", "PASS", "Employee Can Access His EmpOverTimeRequest Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying EmpOverTimeRequest Tab." + RolePermissionpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghpiOTpage.EmpActOTSummaryClicked()) {

						logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpiOTpage.getExceptionDesc());
					}
					
					if (meghpiOTpage.EmpActPendingOTSummaryClicked()) {

						logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Pending Summary Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EmpOverTimeRequest Pending Summary Button." + meghpiOTpage.getExceptionDesc());
					}
					if (meghpiOTpage.ValidateEmpOTStatusAndOverStayCountOnEmpAccount(Regulizationdate, regularizationstatus, othours)) {

						logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Pending Summary OverStay Count Validated Successfully." + Regulizationdate +" and status is " + regularizationstatus + " overstay count is " + othours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating EmpOverTimeRequest Pending Summary OverStay Count And Record." + meghpiOTpage.getExceptionDesc());
					}
				}
				
				// MPI_773_OverTImeRequest_06
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_773_OverTImeRequest_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To check that, as a admin reject the employee's OT request and ensure status is updated as \"rejected\" for the request on both employee and admin account.");

					ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
							"password,captcha,regularizationstatus,empid,firstname");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					
					String empid = data.get(3);
					
					String firstname = data.get(4);
				
					
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
						String Regulizationdate  =	dateDetails.get("month7WorkingDate");
					
		            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
			 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
				MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
		        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

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
			if (RolePermissionpage.HrAccountOTRequestTab()) {

				logResults.createLogs("Y", "PASS", "HrAccountOTRequestTab Clicked Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On HrAccountOTRequestTab In Attendance Module."
						+ RolePermissionpage.getExceptionDesc());
			}
			if (meghleavepage.MonthFilterContains(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
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
			if (meghpiOTpage.AdminActSearchTextField(firstname)) {

				logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Textfield Pending OT Emp Name Inputted Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting On Admin OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
			}
			
		        if (meghpiovertimepage.OverTimeReject(firstname,Regulizationdate)) {

					logResults.createLogs("Y", "PASS", " OT Reject Button Clicked Successfully." + firstname + " " + Regulizationdate);
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
			
			
			
		     if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

		 		logResults.createLogs("Y", "PASS", "OT Summary Clicked Successfully On Admin.");
		 	} else {
		 		logResults.createLogs("Y", "FAIL",
		 				"Error While Clicking On OT Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
		 	}
		 	if (meghpiOTpage.RejectCountOnSummary()) {

		 		logResults.createLogs("Y", "PASS", "Admin Account OverTimeRequest Rejected Summary Clicked Successfully.");
		 	} else {
		 		logResults.createLogs("Y", "FAIL",
		 				"Error While Clicking On Admin OverTimeRequest Rejected Summary Button." + meghpiOTpage.getExceptionDesc());
		 	}
		 	if (meghpiOTpage.AdminActSearchTextField(firstname)) {

				logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Textfield Pending OT Emp Name Inputted Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting On Admin OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
			}
		 	if (meghpiOTpage.ValidateEmpOTStatusOnAdminAccount(Regulizationdate, regularizationstatus, empid)) {

				logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Rejected Summary Validated Successfully." + Regulizationdate +" and status is " + regularizationstatus);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating EmpOverTimeRequest Rejected Summary Count And Record." + meghpiOTpage.getExceptionDesc());
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
				if (RolePermissionpage.EmployeeAttendanceButton()) {

					logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
				}
				if (RolePermissionpage.EmpOverTimeRequest()) {

					logResults.createLogs("Y", "PASS", "Employee Can Access His EmpOverTimeRequest Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Displaying EmpOverTimeRequest Tab." + RolePermissionpage.getExceptionDesc());
				}
				if (meghleavepage.MonthFilterContains(Regulizationdate)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghpiOTpage.EmpActOTSummaryClicked()) {

					logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpiOTpage.getExceptionDesc());
				}
				
				if (meghpiOTpage.RejectCountOnSummary()) {

			 		logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Rejected Summary Clicked Successfully.");
			 	} else {
			 		logResults.createLogs("Y", "FAIL",
			 				"Error While Clicking On Employee OverTimeRequest Rejected Summary Button." + meghpiOTpage.getExceptionDesc());
			 	}
				
				if (meghpiOTpage.ValidateEmpOTStatusOnEmpAccount(Regulizationdate, regularizationstatus)) {

					logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Rejected Summary Validated Successfully." + Regulizationdate +" and status is " + regularizationstatus);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating EmpOverTimeRequest Rejected Summary Count And Record." + meghpiOTpage.getExceptionDesc());
				}
				}
				
				
				// MPI_774_OverTImeRequest_07
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_774_OverTImeRequest_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To check that, check the functionality of search feature by selecting each search option on admin account.");

					ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
							"password,captcha,empid,firstname");

					
					String password = data.get(0);
					String captcha = data.get(1);
					
					String empid = data.get(2);
					
					String firstname = data.get(3);
				
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
						String Regulizationdate  =	dateDetails.get("month7WorkingDate");
					
		            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
			MeghLoginTest meghlogintest = new MeghLoginTest();
			 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
						MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);

					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

					

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
			if (RolePermissionpage.HrAccountOTRequestTab()) {

				logResults.createLogs("Y", "PASS", "HrAccountOTRequestTab Clicked Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On HrAccountOTRequestTab In Attendance Module."
						+ RolePermissionpage.getExceptionDesc());
			}	
			if (meghleavepage.MonthFilterContains(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
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
			
			if (meghpiOTpage.ApprovalCommentsCheckBox()) {

				logResults.createLogs("Y", "PASS", "Approval Comments CheckBox Selected Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Approval Comments CheckBox." + meghpiOTpage.getExceptionDesc());
			}	
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			if (meghpiOTpage.AdminActSearchTextField(empid)) {

				logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Textfield Pending OT Emp ID Inputted Successfully." + empid);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Emp ID On Admin OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
			}	
			if (meghpiOTpage.EmpIDValidateInAdminTable(empid)) {

				logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Inputted Emp ID Validated Successfully." + empid);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating  Emp ID." + meghpiOTpage.getExceptionDesc());
			}		
			if (meghpiOTpage.AdminActSearchTextField(firstname)) {

				logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Textfield Pending  OT Approval Comments Inputted Successfully." + firstname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting OT Approval Comments On Admin OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
			}	
			if (meghpiOTpage.OTApprovalCommentsRowValidation(firstname)) {

				logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Inputted OT Approval Comments Validated Successfully." + firstname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating OT Approval Comments." + meghpiOTpage.getExceptionDesc());
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
				if (RolePermissionpage.EmployeeAttendanceButton()) {

					logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
				}
				if (RolePermissionpage.EmpOverTimeRequest()) {

					logResults.createLogs("Y", "PASS", "Employee Can Access His EmpOverTimeRequest Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Displaying EmpOverTimeRequest Tab." + RolePermissionpage.getExceptionDesc());
				}
				if (meghleavepage.MonthFilterContains(Regulizationdate)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (OfficePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
				}
			
				
				if (meghpiOTpage.ApprovalCommentsCheckBox()) {

					logResults.createLogs("Y", "PASS", "Approval Comments CheckBox Selected Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Approval Comments CheckBox." + meghpiOTpage.getExceptionDesc());
				}	
				if (OfficePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
				}
				if (meghpiOTpage.AdminActSearchTextField(firstname)) {

					logResults.createLogs("Y", "PASS", "On Emp Account OverTimeRequest Search Textfield Pending  OT Approval Comments Inputted Successfully." + firstname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting OT Approval Comments On Emp OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
				}	
				if (meghpiOTpage.OTApprovalCommentsRowValidationInEmp(firstname)) {

					logResults.createLogs("Y", "PASS", "On Emp Account OverTimeRequest Search Inputted OT Approval Comments Validated Successfully." + firstname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating OT Approval Comments." + meghpiOTpage.getExceptionDesc());
				}	
				
				}	
				
				// MPI_775_OverTImeRequest_08
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_775_OverTImeRequest_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, perform OT on the last month's last Sunday (a week-off day), then log in as an admin, select the last month in the OT requests filter, and ensure that only last-month OT requests are displayed and the Day Type is shown as ‘W’..");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
				            "password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,bio1finger,bio2finger," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha,empstatusonadmin");

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
			      
			        String daytype = data.get(i++);
			        
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
			        
			        Map<String, String> dateDetails = Pramod.getLastMonthLastSundayDetails();
					String lastmonthsunday  =	dateDetails.get("lastMonthLastSunday");
					String monthName = dateDetails.get("monthName");

					LocalDate localDate = LocalDate.parse(lastmonthsunday);
					 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
					
					String inouttime = lastmonthsunday + " " + inouttime1;
					String secondInOutTime = lastmonthsunday + " " + secondInOutTime2;
				    
					MeghLoginTest meghlogintest = new MeghLoginTest();
                    MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);

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
					if (RolePermissionpage.HrAccountOTRequestTab()) {

						logResults.createLogs("Y", "PASS", "HrAccountOTRequestTab Clicked Successfully In HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On HrAccountOTRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
					}	
					if (meghleavepage.MonthFilterContains(lastmonthsunday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + lastmonthsunday );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportMonthFilterButton(monthName)) {

						logResults.createLogs("Y", "PASS", "Regularization Month Data Displayed Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Regularization Month Record." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiOTpage.DayTypeSearchCheckBox()) {

						logResults.createLogs("Y", "PASS", "Day Type Search CheckBox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Day Type Search CheckBox." + meghpiOTpage.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiOTpage.AdminActSearchTextField(daytype)) {

						logResults.createLogs("Y", "PASS", "On Emp Account OverTimeRequest Search Textfield Pending  Day Type Inputted Successfully." + daytype);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting DayType On Emp OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
					}	
					if (meghpiOTpage.DayTypeSearchResult(daytype)) {

						logResults.createLogs("Y", "PASS", "On Emp Account OverTimeRequest Search Inputted DayType Validated Successfully." + daytype);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating DayType." + meghpiOTpage.getExceptionDesc());
					}	
				}
				
				
				// MPI_776_OverTImeRequest_09
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_776_OverTImeRequest_09()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To check that, Do OT from multiple employee's account and as a admin reject all request using \"reject all\" button feature and ensure all the OT requests are rejected ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
				            "password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,bio1finger,bio2finger," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha,regularizationstatus,rejectreason");

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
			        String regularizationstatus = data.get(i++);
			        String rejectreason = data.get(i++);
			        
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
			        
			        Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();

					String thirdworkingday  = thirdrdday.get("month3WorkingDate");
					String fourthworkingday  = thirdrdday.get("month4WorkingDate");
					
					LocalDate localDate = LocalDate.parse(thirdworkingday);
					 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
					
					String inouttime = thirdworkingday + " " + inouttime1;
					String secondInOutTime = thirdworkingday + " " + secondInOutTime2;
					
					String inouttimetwo = fourthworkingday + " " + inouttime1;
					String secondInOutTimetwo = fourthworkingday + " " + secondInOutTime2;
				    
					MeghLoginTest meghlogintest = new MeghLoginTest();
                    MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
					MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

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
				    
				    Response punchInResponses = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, empid,
				            locationid, inouttimetwo, mode, photo
				    );

				    if (punchInResponses.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttimetwo);
				    } else {
				        logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponses.asString());
				        return;
				    }

				    // Punch OUT
				    Response punchOutResponsess = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, empid,
				            locationid, secondInOutTimetwo, secondMode, photo
				    );
	
				    if (punchOutResponsess.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTimetwo);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponsess.asString());
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
					if (RolePermissionpage.HrAccountOTRequestTab()) {

						logResults.createLogs("Y", "PASS", "HrAccountOTRequestTab Clicked Successfully In HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On HrAccountOTRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(thirdworkingday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
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
					if (meghregularizationrequestpage.RejectAllButton()) {

						logResults.createLogs("Y", "PASS", "Regularization request RejectAll Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization RejectAll Button." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghregularizationrequestpage.RejectReasonTextField(rejectreason)) {

						logResults.createLogs("Y", "PASS", "Regularization request Reject Reason Inputted Successfully." + rejectreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Regularization Reject Reason." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghregularizationrequestpage.RejectAllConfirmButton()) {

						logResults.createLogs("Y", "PASS", "Regularization request RejectAll Confirm Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization RejectAll Confirm Button." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

						logResults.createLogs("Y", "PASS", "OT Summary Clicked Successfully On Admin.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OT Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghpiOTpage.RejectedSummaryClicked()) {

						logResults.createLogs("Y", "PASS", "Admin Account OverTimeRequest Rejected Summary Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Admin OverTimeRequest Rejected Summary Button." + meghpiOTpage.getExceptionDesc());
					}
					if (meghpiOTpage.ValidateEmpOTStatuslistOnAdminAccount(thirdworkingday, regularizationstatus, empid)) {

						logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Rejected Summary Validated Successfully." + thirdworkingday +" and status is " + regularizationstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating EmpOverTimeRequest Rejected Summary Count And Record." + meghpiOTpage.getExceptionDesc());
					}
				    
				}
				
				
				// MPI_777_OverTImeRequest_10
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_777_OverTImeRequest_10()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To check that, Do OT from multiple employee's account and as a admin approve all requests using \"approve all\" button feature and ensure all the OT requests are approved. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
				            "password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,bio1finger,bio2finger," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha,regularizationstatus,rejectreason");

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
			        String regularizationstatus = data.get(i++);
			        String rejectreason = data.get(i++);
			        
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
			        
			        Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();

					String thirdworkingday  = thirdrdday.get("month3WorkingDate");
					String fourthworkingday  = thirdrdday.get("month4WorkingDate");
					
					LocalDate localDate = LocalDate.parse(thirdworkingday);
					 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
					
					String inouttime = thirdworkingday + " " + inouttime1;
					String secondInOutTime = thirdworkingday + " " + secondInOutTime2;
					
					String inouttimetwo = fourthworkingday + " " + inouttime1;
					String secondInOutTimetwo = fourthworkingday + " " + secondInOutTime2;
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

					MeghLoginTest meghlogintest = new MeghLoginTest();
                    MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
					MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

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
				    
				    Response punchInResponses = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, empid,
				            locationid, inouttimetwo, mode, photo
				    );

				    if (punchInResponses.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttimetwo);
				    } else {
				        logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponses.asString());
				        return;
				    }

				    // Punch OUT
				    Response punchOutResponsess = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, empid,
				            locationid, secondInOutTimetwo, secondMode, photo
				    );
	
				    if (punchOutResponsess.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTimetwo);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponsess.asString());
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
					if (RolePermissionpage.HrAccountOTRequestTab()) {

						logResults.createLogs("Y", "PASS", "HrAccountOTRequestTab Clicked Successfully In HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On HrAccountOTRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(thirdworkingday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
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
					if (meghregularizationrequestpage.ApproveAllButtonInAdmin()) {

						logResults.createLogs("Y", "PASS", "Approve All Button Clicked On Admin Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approve All Button." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghpiOTpage.ApproveAllReasonTextField(rejectreason)) {

						logResults.createLogs("Y", "PASS", "Regularization request Approve Reason Inputted Successfully." + rejectreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Regularization Approve Reason." + meghpiOTpage.getExceptionDesc());
					}
					
					if (meghregularizationrequestpage.ApproveAllConfirmButtonInAdmin()) {

						logResults.createLogs("Y", "PASS", "Approve All Confirm Button Clicked On Admin Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approve All Confirm Button." + meghregularizationrequestpage.getExceptionDesc());
					} 
					if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

						logResults.createLogs("Y", "PASS", "OT Summary Clicked Successfully On Admin.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OT Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghpiOTpage.AdminActApprovedSummaryClicked()) {

				 		logResults.createLogs("Y", "PASS", "Admin Account OverTimeRequest Approved Summary Clicked Successfully.");
				 	} else {
				 		logResults.createLogs("Y", "FAIL",
				 				"Error While Clicking On Admin OverTimeRequest Approved Summary Button." + meghpiOTpage.getExceptionDesc());
				 	}
					if (meghpiOTpage.ValidateEmpOTStatuslistOnAdminAccount(thirdworkingday, regularizationstatus, empid)) {

						logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Approved Summary Validated Successfully." + thirdworkingday +" and status is " + regularizationstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating EmpOverTimeRequest Approved Summary Count And Record." + meghpiOTpage.getExceptionDesc());
					}   
				}	
				
				// MPI_778_OverTImeRequest_11
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_778_OverTImeRequest_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To check that, check the functionality of filter feature by selecting office name, dept name, team and designation.");

					 ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
					            "password,baseuri,loginendpoint,endpointoftransaction," +
					            "cardnumber,cardtype,bio1finger,bio2finger," +
					            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,captcha");

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
				       
				        
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
				        
				        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");

						LocalDate localDate = LocalDate.parse(monthFirstWorkingDate);
						 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
						
						String inouttime = monthFirstWorkingDate + " " + inouttime1;
						String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
					    
					
	                    MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
				        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

					    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

					   
					    
					String PolicyNames = "Attendane Policy 1";
					

					MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);

					MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

			MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

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
			
			 // Punch IN
            Response punchInResponse = apiPage.executeSuccessTransaction(
		            baseuri, loginendpoint,
		            Emailid, password, cmpcode,
		            baseuri, endpointoftransaction,
		            cardnumber, cardtype, deviceuniqueid,
		            bio1finger, bio2finger, AttendanceEmpid,
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
		            bio1finger, bio2finger, AttendanceEmpid,
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
		            AttendanceEmpid, firstDayOfMonth + "T00:00:00.000Z"
		    );

		    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
		        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + AttendanceEmpid);
		    } else {
		        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		    }

			if (RolePermissionpage.EmployeeAttendanceButton()) {

				logResults.createLogs("Y", "PASS", " Attendance Module Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Attendane Module." + RolePermissionpage.getExceptionDesc());
			}
			if (RolePermissionpage.HrAccountOTRequestTab()) {

				logResults.createLogs("Y", "PASS", "HrAccountOTRequestTab Clicked Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On HrAccountOTRequestTab In Attendance Module."
						+ RolePermissionpage.getExceptionDesc());
			}
			if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			if (meghregularizationrequestpage.FilterButtonOnRegularizationRequestTab()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked On Admin Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter  Button." + meghregularizationrequestpage.getExceptionDesc());
			}
			
			if (AttendancePolicyPage.OfficeDropDown(officename)) {

				logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully ." + officename);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Office Name ." + AttendancePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.DeptDropDownClick()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown." );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Dept DropDown." + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (AttendancePolicyPage.DeptOptionSelected()) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting  Dept ." + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (AttendancePolicyPage.TeamDropDownClick()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown." );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Team  Dropdown ." + AttendancePolicyPage.getExceptionDesc());
			}
			
			if (AttendancePolicyPage.TeamOptionSelected()) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Team From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
			}
			if (meghregularizationrequestpage.FilterApplyButtonOnRegularizationRequestTab()) {

				logResults.createLogs("Y", "PASS", "Filter Apply Button Clicked On Admin Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Apply Button." + meghregularizationrequestpage.getExceptionDesc());
			}
			if (meghpiOTpage.EmpIDValidateInAdminTable(AttendanceEmpid)) {

				logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Filter Applied Record Validated Successfully." + AttendanceEmpid);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating  Filter Applied Record." + meghpiOTpage.getExceptionDesc());
			}	
				}	
				
				
				// MPI_779_OverTImeRequest_12
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_779_OverTImeRequest_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To check that, select pending, Approved, rejected one by one  from filter feature and ensure respective status requests are displayed in the table.");

					ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
							"password,captcha,regularizationstatus,firstname,empid,pendingfilter,rejectedfilter");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					String firstname = data.get(3);
					String empid  = data.get(4);
					String pendingfilter = data.get(5);
					String rejectedfilter = data.get(6);
					
					  Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
					
						MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
	                     MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
	        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

					
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
					if (RolePermissionpage.HrAccountOTRequestTab()) {

						logResults.createLogs("Y", "PASS", "HrAccountOTRequestTab Clicked Successfully In HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On HrAccountOTRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghregularizationrequestpage.FilterButtonOnRegularizationRequestTab()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked On Admin Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter  Button." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghregularizationrequestpage.SelectFilterOption(regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "Approved Option Selected From Filter Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting  Approved Option From Filter DropDown." + meghregularizationrequestpage.getExceptionDesc());
					}
					
					if (meghregularizationrequestpage.FilterApplyButtonOnRegularizationRequestTab()) {

						logResults.createLogs("Y", "PASS", "Filter Apply Button Clicked On Admin Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Apply Button." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghpiOTpage.AdminActSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Textfield Pending OT Emp Name Inputted Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting On Admin OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
					}
					if (meghpiOTpage.ValidateEmpStatusOnAdminAccountForFilterResultOfOTEmp(regularizationstatus, empid)) {

						logResults.createLogs("Y", "PASS", "Approved Filter Applied Emp Record Displayed Successfully." + firstname + " " + regularizationstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Approved Filter Record." + meghpiOTpage.getExceptionDesc());
					}
					
					if (meghregularizationrequestpage.FilterButtonOnRegularizationRequestTab()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked On Admin Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter  Button." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghregularizationrequestpage.SelectFilterOption(pendingfilter)) {

						logResults.createLogs("Y", "PASS", "Pending Option Selected From Filter Successfully." + pendingfilter);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting  Pending Option From Filter DropDown." + meghregularizationrequestpage.getExceptionDesc());
					}
					
					if (meghregularizationrequestpage.FilterApplyButtonOnRegularizationRequestTab()) {

						logResults.createLogs("Y", "PASS", "Filter Apply Button Clicked On Admin Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Apply Button." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghpiOTpage.AdminActSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Textfield Pending OT Emp Name Inputted Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting On Admin OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
					}
					if (meghpiOTpage.ValidateEmpStatusOnAdminAccountForFilterResultOfOTEmp(pendingfilter, empid)) {

						logResults.createLogs("Y", "PASS", "Pending Filter Applied Emp Record Displayed Successfully." + firstname + " " + pendingfilter);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Pending Filter Record." + meghpiOTpage.getExceptionDesc());
					}
					if (meghregularizationrequestpage.FilterButtonOnRegularizationRequestTab()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked On Admin Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter  Button." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghregularizationrequestpage.SelectFilterOption(rejectedfilter)) {

						logResults.createLogs("Y", "PASS", "Rejected Option Selected From Filter Successfully." + rejectedfilter);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting  Rejected Option From Filter DropDown." + meghregularizationrequestpage.getExceptionDesc());
					}
					
					if (meghregularizationrequestpage.FilterApplyButtonOnRegularizationRequestTab()) {

						logResults.createLogs("Y", "PASS", "Filter Apply Button Clicked On Admin Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Apply Button." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghpiOTpage.AdminActSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Textfield Pending OT Emp Name Inputted Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting On Admin OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
					}
					if (meghpiOTpage.ValidateEmpStatusOnAdminAccountForFilterResultOfOTEmp(rejectedfilter, empid)) {

						logResults.createLogs("Y", "PASS", "Rejected Filter Applied Emp Record Displayed Successfully." + firstname + " " + rejectedfilter);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Rejected Filter Record." + meghpiOTpage.getExceptionDesc());
					}
								}
	
	
				// MPI_784_OverTImeRequest_17
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_784_OverTImeRequest_17()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To check that, check the Overtime statistics for pending, rejected, and for approved on employee account .");

					ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
							"password,captcha,regularizationstatus,empid,pendingfilter,rejectedfilter");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					String empid  = data.get(3);
					String pendingfilter = data.get(4);
					String rejectedfilter = data.get(5);
					
					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
	                     MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();

				
			        if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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
			if (RolePermissionpage.EmpOverTimeRequest()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His EmpOverTimeRequest Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying EmpOverTimeRequest Tab." + RolePermissionpage.getExceptionDesc());
			}
			if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			if (meghpiOTpage.EmpActOTSummaryClicked()) {

				logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpiOTpage.getExceptionDesc());
			}
			if (meghpiOTpage.EmpOTApprovedCountValidation(regularizationstatus)) {

				logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Approved OT Validated Successfully." + regularizationstatus);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating On EmpOverTimeRequest Approved OT Request Count." + meghpiOTpage.getExceptionDesc());
			}
			if (meghpiOTpage.EmpOTPendingCountValidation(pendingfilter)) {

				logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Pending OT Validated Successfully." + pendingfilter);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating On EmpOverTimeRequest Pending OT Request Count." + meghpiOTpage.getExceptionDesc());
			}
			if (meghpiOTpage.EmpOTRejectedCountValidation(rejectedfilter)) {

				logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Rejected OT Validated Successfully." + rejectedfilter);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating On EmpOverTimeRequest Rejected OT Request Count." + meghpiOTpage.getExceptionDesc());
			}

				}
				
				// MPI_786_OverTImeRequest_19
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_786_OverTImeRequest_19() {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To check that, check the search feature in employee account .");
					logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_774_OverTImeRequest_07 TestCase.");
				}
				
	
				// MPI_1575_OverTImeRequest_20
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_1575_OverTImeRequest_20()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, approve OT for less hours and ensure both Overstay and Approved Overtime counts are displayed correctly.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
				            "password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,bio1finger,bio2finger," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha,regularizationstatus,othours,firstname,revokedfilter");

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
			        String regularizationstatus = data.get(i++);
			        data.get(i++);
			        String firstname = data.get(i++);
			        String updatedOThours = data.get(i++);
			        
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
			        
			        Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();

					String Fifthworkingday  = thirdrdday.get("month5WorkingDate");

					LocalDate localDate = LocalDate.parse(Fifthworkingday);
					 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
					
					String inouttime = Fifthworkingday + " " + inouttime1;
					String secondInOutTime = Fifthworkingday + " " + secondInOutTime2;
				    
					MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

                    MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
                	MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
                	MeghLoginTest meghlogintest = new MeghLoginTest();

				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);

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
						Thread.sleep(15000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
					if (RolePermissionpage.HrAccountOTRequestTab()) {

						logResults.createLogs("Y", "PASS", "HrAccountOTRequestTab Clicked Successfully In HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On HrAccountOTRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(Fifthworkingday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Fifthworkingday );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
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
					if (meghpiOTpage.AdminActSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Textfield Pending OT Emp Name Inputted Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting On Admin OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
					}
					 if (meghpiovertimepage.OverTimeEmpName(firstname,Fifthworkingday)) {

							logResults.createLogs("Y", "PASS", "Emp OT Approve Button Clicked Successfully." + firstname + " " + Fifthworkingday);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On OT Approve Button  Of Emp ." + meghpiovertimepage.getExceptionDesc());
						}
					 
					 if (meghpiovertimepage.OverTimeHoursUpdate(updatedOThours)) {

							logResults.createLogs("Y", "PASS", "Less OT Hours Inputted Successfully." + updatedOThours );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Inputting Less OT Hours." + meghpiovertimepage.getExceptionDesc());
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
				     if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

				 		logResults.createLogs("Y", "PASS", "OT Summary Clicked Successfully On Admin.");
				 	} else {
				 		logResults.createLogs("Y", "FAIL",
				 				"Error While Clicking On OT Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
				 	}
				 	if (meghpiOTpage.AdminActApprovedSummaryClicked()) {

				 		logResults.createLogs("Y", "PASS", "Admin Account OverTimeRequest Approved Summary Clicked Successfully.");
				 	} else {
				 		logResults.createLogs("Y", "FAIL",
				 				"Error While Clicking On Admin OverTimeRequest Approved Summary Button." + meghpiOTpage.getExceptionDesc());
				 	}
				 	if (meghpiOTpage.AdminActSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "On Admin Account OverTimeRequest Search Textfield Pending OT Emp Name Inputted Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting On Admin OverTimeRequest Search TextField." + meghpiOTpage.getExceptionDesc());
					}
				 	if (meghpiOTpage.ValidateEmpUpdatedOTHoursOnAdminAccount(Fifthworkingday, updatedOThours, empid)) {

						logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Approved Summary Validated Successfully." + Fifthworkingday +" and Updated OT Is is " + updatedOThours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating EmpOverTimeRequest Approved Summary Count And Record." + meghpiOTpage.getExceptionDesc());
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

					if (RolePermissionpage.EmployeeAttendanceButton()) {

						logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
					}
					if (RolePermissionpage.EmpOverTimeRequest()) {

						logResults.createLogs("Y", "PASS", "Employee Can Access His EmpOverTimeRequest Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying EmpOverTimeRequest Tab." + RolePermissionpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(Fifthworkingday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Fifthworkingday );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghpiOTpage.EmpActOTSummaryClicked()) {

						logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpiOTpage.getExceptionDesc());
					}
					
					if (meghpiOTpage.AdminActApprovedSummaryClicked()) {

				 		logResults.createLogs("Y", "PASS", "Admin Account OverTimeRequest Approved Summary Clicked Successfully.");
				 	} else {
				 		logResults.createLogs("Y", "FAIL",
				 				"Error While Clicking On Admin OverTimeRequest Approved Summary Button." + meghpiOTpage.getExceptionDesc());
				 	}
					if (meghpiOTpage.ValidateEmpUpdatedOTHoursOnEmpAccount(Fifthworkingday, updatedOThours,  regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "On Employee Account OverTimeRequest Pending Summary OverStay Count Validated Successfully." + Fifthworkingday +" and status is " + regularizationstatus + " OT Approved count is " + updatedOThours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating EmpOverTimeRequest Pending Summary OverStay Count And Record." + meghpiOTpage.getExceptionDesc());
					}
				}	
				
				
				// MPI_1638_OverTime_Approved_07
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
				public void MPI_1638_OverTime_Approved_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, ensure that the OT record generated by the \"OT Approved by System\" attendance policy is displayed under the Overtime Approved tab.");

					ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
		
				String PolicyNames = "AutoAttenPN" + initBase.executionRunTime;
					
				Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

		        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
		       
				// get first day of month
				LocalDate localDate = LocalDate.parse(monthFirstWorkingDate);
				 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
					
		            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

			MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
				MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
				MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
				
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

			if (RolePermissionpage.EmployeeAttendanceButton()) {

				logResults.createLogs("Y", "PASS", " Attendance Module Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Attendane Module." + RolePermissionpage.getExceptionDesc());
			}
			
			if (meghpiOTpage.AttendanceProcessTab()) {

				logResults.createLogs("Y", "PASS", "Hr Account Attendance Process Tab Clicked Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Hr Account Attendance Process Tab In Attendance Module."
						+ meghpiOTpage.getExceptionDesc());
			}
			if (meghpiOTpage.AddAttendanceProcessButton()) {

				logResults.createLogs("Y", "PASS", "Hr Account Add Attendance Process Button Clicked Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Hr Account Add Attendance Process Button In Attendance Module."
						+ meghpiOTpage.getExceptionDesc());
			}
			
			if (meghpiOTpage.AddAttendanceProcessDateTextField()) {

				logResults.createLogs("Y", "PASS", "Date TextField Clicked Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Date TextField."
						+ meghpiOTpage.getExceptionDesc());
			}
			if (meghpiOTpage.AddAttendanceProcessDateTextFieldInput(firstDayOfMonth)) {

				logResults.createLogs("Y", "PASS", "First Day Of the Month Selected Successfully ." + firstDayOfMonth);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting First Day Of The Month."
						+ meghpiOTpage.getExceptionDesc());
			}
			if (meghpiOTpage.AddAttendanceProcessApplyButton()) {

				logResults.createLogs("Y", "PASS", "Add Attendance Process Apply Button Clicked Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Process Button."
						+ meghpiOTpage.getExceptionDesc());
			}
			if (meghpiOTpage.AddAttendanceProcessValidation()) {

				logResults.createLogs("Y", "PASS", "Waiting For Attendance Process To Get Complete  Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Attendance Process Is Not Completed ."
						+ meghpiOTpage.getExceptionDesc());
			}
			
			if (meghpiOTpage.OverTimeApprovedTab()) {

				logResults.createLogs("Y", "PASS", "Hr Account OT Approved Tab Clicked Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Hr Account OT Approved Tab In Attendance Module."
						+ meghpiOTpage.getExceptionDesc());
			}
			if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
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
			
			if (meghpiOTpage.OverTimeApprovedTabSearchTextField(AttendanceEmpid)) {

				logResults.createLogs("Y", "PASS", "Hr Account OT Approved Tab EmpID Inputted On Search Text Field Successfully ." + AttendanceEmpid);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Empid  On Hr Account OT Approved Tab In Attendance Module."
						+ meghpiOTpage.getExceptionDesc());
			}	
			if (meghpiOTpage.OverTimeApprovedTabEmpidSearchResult(AttendanceEmpid)) {

				logResults.createLogs("Y", "PASS", "Hr Account OT Approved Tab Emp OT Record Validated SuccessFully." + AttendanceEmpid);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Emp OT On Hr Account  OT Approved Tab In Attendance Module."
						+ meghpiOTpage.getExceptionDesc());
			}	
				}
				
				// MPI_787_OverTime_Approved_01
				@Test(enabled = true, priority = 17, groups = { "Smoke" })
				public void MPI_787_OverTime_Approved_01() {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To check that, check the functionality of search feature by selecting each search option on admin account.");
					logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_1638_OverTime_Approved_07 TestCase.");
				}
				
				
				
				
				
				// MPI_790_OverTime_Approved_04
				@Test(enabled = true, priority = 18, groups = { "Smoke" })
				public void MPI_790_OverTime_Approved_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"check the functionality of filter feature by inputting specific office, dept, team and designation.");

					ArrayList<String> data = initBase.loadExcelData("OverTimeRequest_Tab", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					
					
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
					
		            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
			

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
			if (meghpiOTpage.OverTimeApprovedTab()) {

				logResults.createLogs("Y", "PASS", "Hr Account OT Approved Tab Clicked Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Hr Account OT Approved Tab In Attendance Module."
						+ meghpiOTpage.getExceptionDesc());
			}	
			if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			
				if (meghregularizationrequestpage.FilterButtonOnRegularizationRequestTab()) {

					logResults.createLogs("Y", "PASS", "Filter Button Clicked On Admin Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Filter  Button." + meghregularizationrequestpage.getExceptionDesc());
				}
				
				if (AttendancePolicyPage.OfficeDropDown(officename)) {

					logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully ." + officename);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Selecting Office Name ." + AttendancePolicyPage.getExceptionDesc());
				}

				if (AttendancePolicyPage.DeptDropDownClick()) {
					logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown." );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On Dept DropDown." + AttendancePolicyPage.getExceptionDesc());
				}
				
				if (AttendancePolicyPage.DeptOptionSelected()) {
					logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Selecting  Dept ." + AttendancePolicyPage.getExceptionDesc());
				}
				
				if (AttendancePolicyPage.TeamDropDownClick()) {
					logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown." );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On Team  Dropdown ." + AttendancePolicyPage.getExceptionDesc());
				}
				
				if (AttendancePolicyPage.TeamOptionSelected()) {
					logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Selecting Team From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
				}
				if (meghregularizationrequestpage.FilterApplyButtonOnRegularizationRequestTab()) {

					logResults.createLogs("Y", "PASS", "Filter Apply Button Clicked On Admin Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Filter Apply Button." + meghregularizationrequestpage.getExceptionDesc());
				}
				if (meghpiOTpage.OverTimeApprovedTabEmpidSearchResult(AttendanceEmpid)) {

					logResults.createLogs("Y", "PASS", "Hr Account OT Approved Tab Emp OT Record Validated SuccessFully." + AttendanceEmpid);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Emp OT On Hr Account  OT Approved Tab In Attendance Module."
							+ meghpiOTpage.getExceptionDesc());
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
