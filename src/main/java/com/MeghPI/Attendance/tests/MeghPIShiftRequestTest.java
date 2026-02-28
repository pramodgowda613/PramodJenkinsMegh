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
import com.MeghPI.Attendance.pages.MeghMasterDepartmentMappingPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamMappingPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeRequestPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationReportsPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationRequestPage;
import com.MeghPI.Attendance.pages.MeghPIShiftRequestPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import com.MeghPI.Attendance.pages.MeghPIAttendanceEmployeePage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIShiftRequestTest {
	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	

	private String cmpcode = "";
	private String Emailid = "";
	private String officename = "";
    private String AdminFirstName ="";
	  private String entityname = "";
	  private String designationname= "";
	  private String EmpFirstName = "";
	
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
		EmpFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpFirstName", "");
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");
		designationname = "AutoDESN" +  initBase.executionRunTime;

	}
	
	
	// MPI_1542_MeghPi_Shift_32
	@Test(enabled = true, priority = 01, groups = { "Smoke" })
	public void MPI_1542_MeghPi_Shift_32()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create morning shift and assigned the shift to new shift policy ");

		ArrayList<String> data = initBase.loadExcelData("shift", currTC,
				"shiftname,shiftcode,starttime,endtime,buffertime,fullday,halfday,breakname,breakstarttime,breakendtime,breakbuffertime,password,captcha,policyname");

	
		
		String shiftname = data.get(0);
		String shiftcode = data.get(1) + Pramod.generateRandomNumber(3);
		String starttime = data.get(2);
		String endtime = data.get(3);
		String buffertime = data.get(4);
		String fullday = data.get(5);
		String halfday = data.get(6);
		String breakname = data.get(7) + Pramod.generateRandomAlpha(5);
		String breakstarttime = data.get(8);
		String breakendtime = data.get(9);
		String breakbuffertime = data.get(10);
		String password = data.get(11);
		String captcha = data.get(12);
		String shiftpolicyname  = data.get(13);
		

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

		MeghLoginTest meghlogintest = new MeghLoginTest();
	
		
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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPage.ShiftButton()) {

			logResults.createLogs("Y", "PASS", "ShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On ShiftButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftPageLoaded()) {

			logResults.createLogs("Y", "PASS", "Shift Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Shift Page Isn't Loaded Completely." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.AddShiftButton()) {

			logResults.createLogs("Y", "PASS", "CreateShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateShiftButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftNameTextField(shiftname)) {

			logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully ." + shiftname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Name." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftCodeTextField(shiftcode)) {

			logResults.createLogs("Y", "PASS", "Shift Code Inputted Successfully ." + shiftcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Code." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftStartTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.ShiftStartTime(starttime)) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Inputted Successfully ." + starttime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftEndTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Inputted Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftEndTime(endtime)) {

			logResults.createLogs("Y", "PASS", "Shift End Time Inputted Successfully ." + endtime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift End Time." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.BufferAllTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Buffer Time Inputted Successfully ." + buffertime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Buffer Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.BufferAllTime(buffertime)) {

			logResults.createLogs("Y", "PASS", "Shift Buffer Time Inputted Successfully ." + buffertime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Buffer Time." + ShiftPolicyPage.getExceptionDesc());
		}
		
		if (ShiftPolicyPage.FullDayTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Full Day Time Inputted Successfully ." + fullday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Full Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.FullDayTime(fullday)) {

			logResults.createLogs("Y", "PASS", "Shift Full Day Time Inputted Successfully ." + fullday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Full Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		

		if (ShiftPolicyPage.HalfDayTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Half Day Time Inputted Successfully ." + halfday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Half Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.HalfDayTime(halfday)) {

			logResults.createLogs("Y", "PASS", "Shift Half Day Time Inputted Successfully ." + halfday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Half Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPage.AddBreakButton()) {

			logResults.createLogs("Y", "PASS", "AddBreakButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddBreakButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakNameTextField(breakname)) {

			logResults.createLogs("Y", "PASS", "Break Name Inputted Successfully ." + breakname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Break Name." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakStartTimeClick()) {

			logResults.createLogs("Y", "PASS", "BreakStartTime Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On BreakStartTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakStartTime(breakstarttime)) {

			logResults.createLogs("Y", "PASS", "BreakStartTime Inputted Successfully ." + breakstarttime);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting BreakStartTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakEndTimeClick()) {

			logResults.createLogs("Y", "PASS", "BreakEndTime Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On BreakEndTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakEndTime(breakendtime)) {

			logResults.createLogs("Y", "PASS", "BreakEndTime Clicked Successfully ." + breakendtime);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On BreakEndTime." + ShiftPage.getExceptionDesc());
		}
		
		if (ShiftPage.BreakNameTextField(breakname)) {

			logResults.createLogs("Y", "PASS", "Break Name Inputted Successfully ." + breakname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Break Name." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakBufferTimeClick()) {

			logResults.createLogs("Y", "PASS", "BreakBufferTime Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On BreakBufferTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakBufferTime(breakbuffertime)) {

			logResults.createLogs("Y", "PASS", "BreakBufferTime Inputted Successfully ." + breakbuffertime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On BreakBufferTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.CreateShiftSaveButton()) {

			logResults.createLogs("Y", "PASS", "Create Shift Save Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateShift Save Button." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPage.ShiftPolicyTab()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyTab Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyTab." + ShiftPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.AddShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.PolicyNameTextField(shiftpolicyname)) {

			logResults.createLogs("Y", "PASS", "Shift PolicyName Inputted Successfully ." + shiftpolicyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPage.AllShiftsInShiftPolicy(shiftname)) {

			logResults.createLogs("Y", "PASS", "Created Shift Selected Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Created Shift." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftSaveButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftSaveButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftSaveButton." + ShiftPolicyPage.getExceptionDesc());
		}

		
		if (ShiftPolicyPage.CreatePolicySave()) {

			logResults.createLogs("Y", "PASS", "CreatePolicySave Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicySave Button." + ShiftPolicyPage.getExceptionDesc());
		}
		
		if (ShiftPage.NoOptionSelected()) {
			logResults.createLogs("Y", "PASS", "No Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On No Button ." + ShiftPage.getExceptionDesc());
		}
	}
	
	
	// MPI_793_Shift_request_01
			@Test(enabled = true, priority = 2, groups = { "Smoke" })
			public void MPI_793_Shift_request_01()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify that, complete general shift  and as employee apply a shift-change request to Morning Shift for the same date and ensure request is created with pending status.");

				  // Load Excel data
				ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
						"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,captcha,regularizationstatus,policyname,shiftstatus,regularizationreason");


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
			        String policyname = data.get(i++);
			        String shiftstatus = data.get(i++);
			        String regularizationreason = data.get(i++);
			     
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

			        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
				    
					String inouttime = monthFirstWorkingDate + " " + inouttime1;
					String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
				
					
					// get first day of month
					LocalDate localDate = LocalDate.parse(monthFirstWorkingDate);
					 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
		
	                     MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				MeghLoginPage meghloginpage = new MeghLoginPage(driver);
                            MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);
				 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
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

				if (RolePermissionpage.EmployeeAttendanceButton()) {

					logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
				}
				if (RolePermissionpage.EmpShiftRequest()) {

					logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.ApplyShiftRequest()) {

					logResults.createLogs("Y", "PASS", "Employee Account Apply Shift Request Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Apply Shift Request Button." + meghpishiftrequestpage.getExceptionDesc());
				}
				
				if (meghpishiftrequestpage.SelectDateTextField()) {

					logResults.createLogs("Y", "PASS", "Date TextField Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Date TextField ." + meghpishiftrequestpage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.SelectDateTextFieldDateSelected(monthFirstWorkingDate)) {

					logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + monthFirstWorkingDate);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Date." + meghpishiftrequestpage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

					logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.SelectShiftPolicyDropDown(policyname)) {

					logResults.createLogs("Y", "PASS", "Morning Shift Policy Selected Successfully." + policyname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Selecting Morning Shift Policy." + meghpishiftrequestpage.getExceptionDesc());
				}
				
				if (meghpishiftrequestpage.SelectShiftDropDown()) {

					logResults.createLogs("Y", "PASS", "Shift DropDown Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Shift DropDown ." + meghpishiftrequestpage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.SelectShiftSearchInput(shiftstatus)) {

					logResults.createLogs("Y", "PASS", "Morning Shift Name Inputted Successfully." + shiftstatus);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Morning Shift Name." + meghpishiftrequestpage.getExceptionDesc());
				}
				
				if (meghpishiftrequestpage.SelectShiftFromOption()) {

					logResults.createLogs("Y", "PASS", "Morning Shift  Selected Successfully." + shiftstatus);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Selecting Morning Shift ." + meghpishiftrequestpage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

					logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
				}
				
				if (meghpishiftrequestpage.ShiftApplyButton()) {

					logResults.createLogs("Y", "PASS", "Shift Request Apply Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Shift Request Apply Button ." + meghpishiftrequestpage.getExceptionDesc());
				}
				if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

					logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
				}
				
				if (meghpishiftrequestpage.PendingShiftRequestCard()) {

					logResults.createLogs("Y", "PASS", "Employee Account Shift Request Pending Summary Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Shift Request Pending Summary Button." + meghpishiftrequestpage.getExceptionDesc());
				}
				
				if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnEmpAccountForList(monthFirstWorkingDate, regularizationstatus)) {

					logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Pending Summary Validated Successfully." + monthFirstWorkingDate +" and status is " + regularizationstatus);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating Shift Request Pending Summary Count And Record." + meghpiOTpage.getExceptionDesc());
				}
			}
	
	
			// MPI_797_Shift_request_05
			@Test(enabled = true, priority = 3, groups = { "Smoke" })
			public void MPI_797_Shift_request_05()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To check that, approve the shift request as an admin and ensure the attendance status is updated correctly with the Morning Shift label, and the shift request status is displayed as Approved.");

				ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
						"password,captcha,regularizationstatus,empid,firstname,shiftstatus,regularizationintime,regularizationouttime,empstatusonadmin");

				
				String password = data.get(0);
				String captcha = data.get(1);
				String regularizationstatus = data.get(2);
				
				String empid = data.get(3);
				
				String firstname = data.get(4);
				String shiftstatus = data.get(5);
				String regularizationintime = data.get(6);
				String regularizationouttime = data.get(7);
				String empstatusonadmin = data.get(8);
			
				
				 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
			      String uidate = Pramod.convertToUIFormat(monthFirstWorkingDate);
				
	            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
		        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
		        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
                MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);

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
		if (RolePermissionpage.HrAccountShiftRequestTab()) {

			logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
					+ meghleavepage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

			logResults.createLogs("Y", "PASS", "Shift Request Summary Clicked Successfully On Admin.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Shift Request Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
		}
		
		if (meghpishiftrequestpage.PendingShiftRequestCard()) {

			logResults.createLogs("Y", "PASS", "Employee Account Shift Request Pending Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Shift Request Pending Summary Button." + meghpishiftrequestpage.getExceptionDesc());
		}
		
		if (meghpishiftrequestpage.ShiftRequestSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "On Admin Account Shift Requested Emp Name Inputted On Search Textfield   Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Name Search TextField." + meghpishiftrequestpage.getExceptionDesc());
		}

		 if (meghpishiftrequestpage.ShiftRequestEmpApprove(firstname,monthFirstWorkingDate)) {

				logResults.createLogs("Y", "PASS", "Emp Shift Request Approve Button Clicked Successfully." + firstname + " " + monthFirstWorkingDate);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Shift Request Approve Button  Of Emp ." + meghpiovertimepage.getExceptionDesc());
			}
	    
	     if (meghpishiftrequestpage.ShiftRequestApproveButton()) {

				logResults.createLogs("Y", "PASS", "Approve Confirm Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Approve Confirm Button." + meghpishiftrequestpage.getExceptionDesc());
			} 
	     if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

	 		logResults.createLogs("Y", "PASS", "OT Summary Clicked Successfully On Admin.");
	 	} else {
	 		logResults.createLogs("Y", "FAIL",
	 				"Error While Clicking On OT Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
	 	}
	 	if (meghpishiftrequestpage.ApprovedShiftRequestSummaryCount()) {

	 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Approved Summary Clicked Successfully.");
	 	} else {
	 		logResults.createLogs("Y", "FAIL",
	 				"Error While Clicking On Admin Shift Request Approved Summary Button." + meghregularizationrequestpage.getExceptionDesc());
	 	}
	 	if (meghpishiftrequestpage.ShiftRequestSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "On Admin Account Shift Requested Emp Name Inputted On Search Textfield   Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Name Search TextField." + meghpishiftrequestpage.getExceptionDesc());
		}
	 	if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnAdminAccountList(monthFirstWorkingDate, regularizationstatus, empid)) {

			logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Approved Summary Validated Successfully." + monthFirstWorkingDate +" and status is " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Shift Request Approved Summary Count And Record." + meghpishiftrequestpage.getExceptionDesc());
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
			if (RolePermissionpage.EmpShiftRequest()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
			}
			if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

				logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
			}
			
			if (meghpishiftrequestpage.ApprovedShiftRequestSummaryCount()) {

		 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Approved Summary Clicked Successfully.");
		 	} else {
		 		logResults.createLogs("Y", "FAIL",
		 				"Error While Clicking On Admin Shift Request Approved Summary Button." + meghregularizationrequestpage.getExceptionDesc());
		 	}
			
			if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnEmpAccountForList(monthFirstWorkingDate, regularizationstatus)) {

				logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Approved Summary Validated Successfully." + monthFirstWorkingDate +" and status is " + regularizationstatus);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Shift Request Approved Summary Count And Record." + meghpiOTpage.getExceptionDesc());
			}
			
			if (RolePermissionpage.EmpAttendanceTab()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His My Attendance Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying My Attendance Tab." + RolePermissionpage.getExceptionDesc());
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
			
			if (meghpiattendanceemployeepage.EmpAttendanceTabSearchTextField(uidate)) {

				logResults.createLogs("Y", "PASS", "Employee Shift Applied Date Inputted Successfully." + uidate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Emp Applied Shift Date In Search TextField." + meghpiattendanceemployeepage.getExceptionDesc());
			}
			
			
			if (meghpishiftrequestpage.validateAttendanceRowForShiftRequest(monthFirstWorkingDate, regularizationintime, regularizationouttime , shiftstatus, empstatusonadmin )) {

				logResults.createLogs("Y", "PASS", "Emp Present Status, Assigned Shift, InTime, OutTime Validated Successfully." + empid + " intime  " + regularizationintime + " And out time" + regularizationouttime + " shift is " + shiftstatus + "status is" + empstatusonadmin   );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Emp Attendance Present Status, Assigned Shift, InTime, OutTime." + meghpishiftrequestpage.getExceptionDesc());
			}
			}
	
			// MPI_794_Shift_request_02
			@Test(enabled = true, priority = 4, groups = { "Smoke" })
			public void MPI_794_Shift_request_02()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify that, complete morning shift  and as employee apply a shift-change request to Morning Shift for the same date.");

				 
				 // Load all data including punch-out time and mode
			    ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
			            "password,baseuri,loginendpoint,endpointoftransaction," +
			            "cardnumber,cardtype,bio1finger,bio2finger," +
			            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha,regularizationstatus,policyname,shiftstatus,regularizationreason");

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
		       
		        String policyname = data.get(i++);
		        String shiftstatus = data.get(i++);
		        String regularizationreason = data.get(i++);
		       
		        
		        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
		        
		        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				String Regulizationdate  =	dateDetails.get("month2WorkingDate");

				LocalDate localDate = LocalDate.parse(Regulizationdate);
				 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
				
				String inouttime = Regulizationdate + " " + inouttime1;
				String secondInOutTime = Regulizationdate + " " + secondInOutTime2;
			    
				  MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);
	                            MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
				        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
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
				if (RolePermissionpage.EmpShiftRequest()) {

					logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.ApplyShiftRequest()) {

					logResults.createLogs("Y", "PASS", "Employee Account Apply Shift Request Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Apply Shift Request Button." + meghpishiftrequestpage.getExceptionDesc());
				}
				
				if (meghpishiftrequestpage.SelectDateTextField()) {

					logResults.createLogs("Y", "PASS", "Date TextField Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Date TextField ." + meghpishiftrequestpage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.SelectDateTextFieldDateSelected(Regulizationdate)) {

					logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + Regulizationdate);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Date." + meghpishiftrequestpage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

					logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.SelectShiftPolicyDropDown(policyname)) {

					logResults.createLogs("Y", "PASS", "Morning Shift Policy Selected Successfully." + policyname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Selecting Morning Shift Policy." + meghpishiftrequestpage.getExceptionDesc());
				}
				
				if (meghpishiftrequestpage.SelectShiftDropDown()) {

					logResults.createLogs("Y", "PASS", "Shift DropDown Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Shift DropDown ." + meghpishiftrequestpage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.SelectShiftSearchInput(shiftstatus)) {

					logResults.createLogs("Y", "PASS", "Morning Shift Name Inputted Successfully." + shiftstatus);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Morning Shift Name." + meghpishiftrequestpage.getExceptionDesc());
				}
				
				if (meghpishiftrequestpage.SelectShiftFromOption()) {

					logResults.createLogs("Y", "PASS", "Morning Shift  Selected Successfully." + shiftstatus);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Selecting Morning Shift ." + meghpishiftrequestpage.getExceptionDesc());
				}
				if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

					logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
				}
				
				if (meghpishiftrequestpage.ShiftApplyButton()) {

					logResults.createLogs("Y", "PASS", "Shift Request Apply Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Shift Request Apply Button ." + meghpishiftrequestpage.getExceptionDesc());
				}
				if (meghleavepage.MonthFilterContains(Regulizationdate)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
							+ meghleavepage.getExceptionDesc());
				}
					
				if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

					logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
				}
				
				if (meghpishiftrequestpage.PendingShiftRequestCard()) {

					logResults.createLogs("Y", "PASS", "Employee Account Shift Request Pending Summary Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Shift Request Pending Summary Button." + meghpishiftrequestpage.getExceptionDesc());
				}
				
				if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnEmpAccountForList(Regulizationdate, regularizationstatus)) {

					logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Pending Summary Validated Successfully." + Regulizationdate +" and status is " + regularizationstatus);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating Shift Request Pending Summary Count And Record." + meghpiOTpage.getExceptionDesc());
				}
			}
	
			// MPI_798_Shift_request_06
						@Test(enabled = true, priority = 5, groups = { "Smoke" })
						public void MPI_798_Shift_request_06()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this,  reject the request, and ensure that the attendance status is displayed correctly with the General Shift label and shift request status is rejected.");

							ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
									"password,captcha,regularizationstatus,empid,firstname,regularizationreason,shiftstatus,regularizationintime,regularizationouttime,empstatusonadmin");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String regularizationstatus = data.get(2);
							
							String empid = data.get(3);
							
							String firstname = data.get(4);
							String regularizationreason = data.get(5);
							String shiftstatus = data.get(6);
							String regularizationintime = data.get(7);
							String regularizationouttime = data.get(8);
							String empstatusonadmin = data.get(9);
						
							 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
								String Regulizationdate  =	dateDetails.get("month2WorkingDate");
							String uidate = Pramod.convertToUIFormat(Regulizationdate);
							
				            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			                MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);
					        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
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
					if (RolePermissionpage.HrAccountShiftRequestTab()) {

						logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}

					if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

						logResults.createLogs("Y", "PASS", "Shift Request Summary Clicked Successfully On Admin.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Request Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
					}
					
					if (meghpishiftrequestpage.PendingShiftRequestCard()) {

						logResults.createLogs("Y", "PASS", "Employee Account Shift Request Pending Summary Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Request Pending Summary Button." + meghpishiftrequestpage.getExceptionDesc());
					}
					
					if (meghpishiftrequestpage.ShiftRequestSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Shift Requested Emp Name Inputted On Search Textfield   Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name Search TextField." + meghpishiftrequestpage.getExceptionDesc());
					}

					 if (meghpishiftrequestpage.ShiftRequestEmpReject(firstname,Regulizationdate)) {

							logResults.createLogs("Y", "PASS", "Emp Shift Request Reject Button Clicked Successfully." + firstname + " " + Regulizationdate);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Shift Request Reject Button  Of Emp ." + meghpiovertimepage.getExceptionDesc());
						}
					 
					 if (meghpishiftrequestpage.ShiftRequestEmpRejectReasonTextField(regularizationreason)) {

							logResults.createLogs("Y", "PASS", "Shift Request Reject Reason Inputted Successfully." + regularizationreason);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting  Shift Request Reject Reason." + meghpishiftrequestpage.getExceptionDesc());
						}
						if (meghpishiftrequestpage.ShiftRequestEmpRejectConfirmButton()) {

							logResults.createLogs("Y", "PASS", "Shift Request Reject Confirm Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Shift Request Confirm Button." + meghpishiftrequestpage.getExceptionDesc());
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
				 	if (meghpishiftrequestpage.ShiftRequestRejectedSummaryCount()) {

				 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Reject Summary Clicked Successfully.");
				 	} else {
				 		logResults.createLogs("Y", "FAIL",
				 				"Error While Clicking On Admin Shift Request Reject Summary Button." + meghregularizationrequestpage.getExceptionDesc());
				 	}
				 	if (meghpishiftrequestpage.ShiftRequestSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Shift Requested Emp Name Inputted On Search Textfield   Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name Search TextField." + meghpishiftrequestpage.getExceptionDesc());
					}
				 	if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnAdminAccountList(Regulizationdate, regularizationstatus, empid)) {

						logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Rejected Summary Validated Successfully." + Regulizationdate +" and status is " + regularizationstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Shift Request Rejected Summary Count And Record." + meghpishiftrequestpage.getExceptionDesc());
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
						if (RolePermissionpage.EmpShiftRequest()) {

							logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
						}
						if (meghleavepage.MonthFilterContains(Regulizationdate)) {

							logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
									+ meghleavepage.getExceptionDesc());
						}
						if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

							logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
						}
						
						if (meghpishiftrequestpage.ShiftRequestRejectedSummaryCount()) {

					 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Reject Summary Clicked Successfully.");
					 	} else {
					 		logResults.createLogs("Y", "FAIL",
					 				"Error While Clicking On Admin Shift Request Reject Summary Button." + meghregularizationrequestpage.getExceptionDesc());
					 	}
						
						if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnEmpAccountForList(Regulizationdate, regularizationstatus)) {

							logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Rejected Summary Validated Successfully." + Regulizationdate +" and status is " + regularizationstatus);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating Shift Request Rejected Summary Count And Record." + meghpiOTpage.getExceptionDesc());
						}
						if (RolePermissionpage.EmpAttendanceTab()) {

							logResults.createLogs("Y", "PASS", "Employee Can Access His My Attendance Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Displaying My Attendance Tab." + RolePermissionpage.getExceptionDesc());
						}
						if (meghpiattendanceemployeepage.EmpAttendanceLoaded()) {

							logResults.createLogs("Y", "PASS", "Employee Attendance Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Loading My Attendance Table Data." + meghpiattendanceemployeepage.getExceptionDesc());
						}
						if (meghleavepage.MonthFilterContains(Regulizationdate)) {

							logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
									+ meghleavepage.getExceptionDesc());
						}
						
						if (meghpiattendanceemployeepage.EmpAttendanceTabSearchTextField(uidate)) {

							logResults.createLogs("Y", "PASS", "Employee Shift Applied Date Inputted Successfully." + uidate);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting Emp Shift Applied Date In Search TextField." + meghpiattendanceemployeepage.getExceptionDesc());
						}
						
						
						if (meghpishiftrequestpage.validateAttendanceRowForShiftRequest(Regulizationdate, regularizationintime, regularizationouttime , shiftstatus, empstatusonadmin )) {

							logResults.createLogs("Y", "PASS", "Emp Present Status, Assigned Shift, InTime, OutTime Validated Successfully." + empid + " intime  " + regularizationintime + " And out time" + regularizationouttime + " shift is " + shiftstatus + "status is" + empstatusonadmin   );
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating Emp Attendance Present Status, Assigned Shift, InTime, OutTime." + meghpishiftrequestpage.getExceptionDesc());
						}
						}
	
	
						// MPI_795_Shift_request_03
						@Test(enabled = true, priority = 6, groups = { "Smoke" })
						public void MPI_795_Shift_request_03()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, raise a shift request and then revoke it. Ensure the status is updated to \"Revoked\" and the shift remains unchanged.");

							 
							 // Load all data including punch-out time and mode
						    ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
						            "password,baseuri,loginendpoint,endpointoftransaction," +
						            "cardnumber,cardtype,bio1finger,bio2finger," +
						            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha,regularizationstatus,policyname,shiftstatus,regularizationreason");

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
					       
					        String policyname = data.get(i++);
					        String shiftstatus = data.get(i++);
					        String regularizationreason = data.get(i++);
					       
					        
					        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
					        
					        Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();

							String thirdworkingday  = thirdrdday.get("month3WorkingDate");

							LocalDate localDate = LocalDate.parse(thirdworkingday);
							 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
							
							String inouttime = thirdworkingday + " " + inouttime1;
							String secondInOutTime = thirdworkingday + " " + secondInOutTime2;
							
							MeghLoginTest meghlogintest = new MeghLoginTest();

							  MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
								MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								MeghLoginPage meghloginpage = new MeghLoginPage(driver);
				                            MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);
								 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
									MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
							        MeghLeavePage meghleavepage = new MeghLeavePage(driver);


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
							if (RolePermissionpage.EmpShiftRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ApplyShiftRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Account Apply Shift Request Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Apply Shift Request Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectDateTextField()) {

								logResults.createLogs("Y", "PASS", "Date TextField Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Date TextField ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectDateTextFieldDateSelected(thirdworkingday)) {

								logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + thirdworkingday);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Date." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

								logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectShiftPolicyDropDown(policyname)) {

								logResults.createLogs("Y", "PASS", "Morning Shift Policy Selected Successfully." + policyname);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Morning Shift Policy." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectShiftDropDown()) {

								logResults.createLogs("Y", "PASS", "Shift DropDown Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift DropDown ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectShiftSearchInput(shiftstatus)) {

								logResults.createLogs("Y", "PASS", "Morning Shift Name Inputted Successfully." + shiftstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Morning Shift Name." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectShiftFromOption()) {

								logResults.createLogs("Y", "PASS", "Morning Shift  Selected Successfully." + shiftstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Morning Shift ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

								logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.ShiftApplyButton()) {

								logResults.createLogs("Y", "PASS", "Shift Request Apply Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Apply Button ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghleavepage.MonthFilterContains(thirdworkingday)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

								logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.PendingShiftRequestCard()) {

								logResults.createLogs("Y", "PASS", "Employee Account Shift Request Pending Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Pending Summary Button." + meghpishiftrequestpage.getExceptionDesc());
							}
						
							if (meghregularizationrequestpage.RevokeButton()) {

								logResults.createLogs("Y", "PASS", "Regularization Request Revoke Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regularization Request Revoke Button." + meghregularizationrequestpage.getExceptionDesc());
							}
							
							if (meghregularizationrequestpage.ShiftRequestRevokeConfirmButton()) {

								logResults.createLogs("Y", "PASS", "Regularization Request Revoke Confirm Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regularization Request Revoke Confirm Button." + meghregularizationrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

								logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.ShiftRequestRevokedSummaryCount()) {

								logResults.createLogs("Y", "PASS", "Employee Account Shift Request Revoked Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Revoked Summary Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnEmpAccountForList(thirdworkingday, regularizationstatus)) {

								logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Revoked Summary Validated Successfully." + thirdworkingday +" and status is " + regularizationstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Shift Request Revoked Summary Count And Record." + meghpiOTpage.getExceptionDesc());
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
							if (RolePermissionpage.HrAccountShiftRequestTab()) {

								logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
										+ RolePermissionpage.getExceptionDesc());
							}
							if (meghleavepage.MonthFilterContains(thirdworkingday)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}

							if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

								logResults.createLogs("Y", "PASS", "Shift Request Summary Clicked Successfully On Admin.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.ShiftRequestRevokedSummaryCount()) {

								logResults.createLogs("Y", "PASS", "Admin Account Shift Request Revoked Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Revoked Summary Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnAdminAccountList(thirdworkingday, regularizationstatus, empid)) {

								logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Revoked Summary Validated Successfully." + thirdworkingday +" and status is " + regularizationstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Shift Request Revoked Summary Count And Record." + meghpishiftrequestpage.getExceptionDesc());
							}
						}
						
						
	
						// MPI_796_Shift_request_04
						@Test(enabled = true, priority = 7, groups = { "Smoke" })
						public void MPI_796_Shift_request_04()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, raise a new shift request for the same date on which the previous request was revoked or rejected, and ensure the new request is successfully created.");

							 
							 // Load all data including punch-out time and mode
						    ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
						            "password,baseuri,loginendpoint,endpointoftransaction," +
						            "cardnumber,cardtype,bio1finger,bio2finger," +
						            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha,regularizationstatus,policyname,shiftstatus,regularizationreason");

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
					       
					        String policyname = data.get(i++);
					        String shiftstatus = data.get(i++);
					        String regularizationreason = data.get(i++);
					       
					        
					        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
					        
					        Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();
							String thirdworkingday  = thirdrdday.get("month3WorkingDate");
							
							
								
								String inouttimetwo = thirdworkingday + " " + inouttime1;
								String secondInOutTimetwo = thirdworkingday + " " + secondInOutTime2;

							LocalDate localDate = LocalDate.parse(thirdworkingday);
							 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
							
							String inouttime = thirdworkingday + " " + inouttime1;
							String secondInOutTime = thirdworkingday + " " + secondInOutTime2;
							

							  MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
								MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								MeghLoginPage meghloginpage = new MeghLoginPage(driver);
				                            MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);
								 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
							        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
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
						 
						    
						    
						    // Punch IN
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
							if (RolePermissionpage.EmpShiftRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ApplyShiftRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Account Apply Shift Request Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Apply Shift Request Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectDateTextField()) {

								logResults.createLogs("Y", "PASS", "Date TextField Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Date TextField ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectDateTextFieldDateSelected(thirdworkingday)) {

								logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + thirdworkingday);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Date." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

								logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectShiftPolicyDropDown(policyname)) {

								logResults.createLogs("Y", "PASS", "Morning Shift Policy Selected Successfully." + policyname);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Morning Shift Policy." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectShiftDropDown()) {

								logResults.createLogs("Y", "PASS", "Shift DropDown Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift DropDown ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectShiftSearchInput(shiftstatus)) {

								logResults.createLogs("Y", "PASS", "Morning Shift Name Inputted Successfully." + shiftstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Morning Shift Name." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectShiftFromOption()) {

								logResults.createLogs("Y", "PASS", "Morning Shift  Selected Successfully." + shiftstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Morning Shift ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

								logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.ShiftApplyButton()) {

								logResults.createLogs("Y", "PASS", "Shift Request Apply Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Apply Button ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghleavepage.MonthFilterContains(thirdworkingday)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

								logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.PendingShiftRequestCard()) {

								logResults.createLogs("Y", "PASS", "Employee Account Shift Request Pending Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Pending Summary Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnEmpAccountForList(thirdworkingday, regularizationstatus)) {

								logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Pending Summary Validated Successfully." + thirdworkingday +" and status is " + regularizationstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Shift Request Pending Summary Count And Record." + meghpiOTpage.getExceptionDesc());
							}    
				
		
							if (RolePermissionpage.EmployeeAttendanceButton()) {

								logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
							}
							if (RolePermissionpage.EmpShiftRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ApplyShiftRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Account Apply Shift Request Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Apply Shift Request Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectDateTextField()) {

								logResults.createLogs("Y", "PASS", "Date TextField Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Date TextField ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectDateTextFieldDateSelected(thirdworkingday)) {

								logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + thirdworkingday);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Date." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

								logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectShiftPolicyDropDown(policyname)) {

								logResults.createLogs("Y", "PASS", "Morning Shift Policy Selected Successfully." + policyname);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Morning Shift Policy." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectShiftDropDown()) {

								logResults.createLogs("Y", "PASS", "Shift DropDown Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift DropDown ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectShiftSearchInput(shiftstatus)) {

								logResults.createLogs("Y", "PASS", "Morning Shift Name Inputted Successfully." + shiftstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Morning Shift Name." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectShiftFromOption()) {

								logResults.createLogs("Y", "PASS", "Morning Shift  Selected Successfully." + shiftstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Morning Shift ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

								logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.ShiftApplyButton()) {

								logResults.createLogs("Y", "PASS", "Shift Request Apply Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Apply Button ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghleavepage.MonthFilterContains(thirdworkingday)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}
								
							if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

								logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.PendingShiftRequestCard()) {

								logResults.createLogs("Y", "PASS", "Employee Account Shift Request Pending Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Pending Summary Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnEmpAccountForList(thirdworkingday, regularizationstatus)) {

								logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Pending Summary Validated Successfully." + thirdworkingday +" and status is " + regularizationstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Shift Request Pending Summary Count And Record." + meghpiOTpage.getExceptionDesc());
							}  
						}
						
						
						// MPI_799_Shift_request_07
						@Test(enabled = true, priority = 8, groups = { "Smoke" })
						public void MPI_799_Shift_request_07()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To check that, as a admin \"reject all\" request using \"reject all\" button feature and ensure all the pending requests are processed and updated as rejected.");

							ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
									"password,captcha,regularizationstatus,empid,firstname,shiftstatus,regularizationintime,regularizationouttime,rejectreason,empstatusonadmin");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String regularizationstatus = data.get(2);
							
							String empid = data.get(3);
							
							String firstname = data.get(4);
							String shiftstatus = data.get(5);
							String regularizationintime = data.get(6);
							String regularizationouttime = data.get(7);
							String rejectreason = data.get(8);
							String empstatusonadmin = data.get(9);
						
							 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
								String Regulizationdate  =	dateDetails.get("month2WorkingDate");
						String uidate = Pramod.convertToUIFormat(Regulizationdate);
							
				            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			                MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);
					        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
					        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

					MeghLoginTest meghlogintest = new MeghLoginTest();
					MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);

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
					if (RolePermissionpage.HrAccountShiftRequestTab()) {

						logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
					}
if (meghleavepage.MonthFilterContains(Regulizationdate)) {

	logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
} else {
	logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
			+ meghleavepage.getExceptionDesc());
}
					if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

						logResults.createLogs("Y", "PASS", "Shift Request Summary Clicked Successfully On Admin.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Request Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
					}
					
					if (meghpishiftrequestpage.PendingShiftRequestCard()) {

						logResults.createLogs("Y", "PASS", "Employee Account Shift Request Pending Summary Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Request Pending Summary Button." + meghpishiftrequestpage.getExceptionDesc());
					}
					
					if (meghpishiftrequestpage.ShiftRequestSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Shift Requested Emp Name Inputted On Search Textfield   Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name Search TextField." + meghpishiftrequestpage.getExceptionDesc());
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
				 	if (meghpishiftrequestpage.ShiftRequestRejectedSummaryCount()) {

				 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Reject Summary Clicked Successfully.");
				 	} else {
				 		logResults.createLogs("Y", "FAIL",
				 				"Error While Clicking On Admin Shift Request Reject Summary Button." + meghregularizationrequestpage.getExceptionDesc());
				 	}
				 	if (meghpishiftrequestpage.ShiftRequestSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Shift Requested Emp Name Inputted On Search Textfield   Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name Search TextField." + meghpishiftrequestpage.getExceptionDesc());
					}
				 	if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnAdminAccountList(Regulizationdate, regularizationstatus, empid)) {

						logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Rejected Summary Validated Successfully." + Regulizationdate +" and status is " + regularizationstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Shift Request Rejected Summary Count And Record." + meghpishiftrequestpage.getExceptionDesc());
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
						if (RolePermissionpage.EmpShiftRequest()) {

							logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
						}
						if (meghleavepage.MonthFilterContains(Regulizationdate)) {

							logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
									+ meghleavepage.getExceptionDesc());
						}
						if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

							logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
						}
						
						if (meghpishiftrequestpage.ShiftRequestRejectedSummaryCount()) {

					 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Reject Summary Clicked Successfully.");
					 	} else {
					 		logResults.createLogs("Y", "FAIL",
					 				"Error While Clicking On Admin Shift Request Reject Summary Button." + meghregularizationrequestpage.getExceptionDesc());
					 	}
						
						if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnEmpAccountForList(Regulizationdate, regularizationstatus)) {

							logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Rejected Summary Validated Successfully." + Regulizationdate +" and status is " + regularizationstatus);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating Shift Request Rejected Summary Count And Record." + meghpiOTpage.getExceptionDesc());
						}
						if (RolePermissionpage.EmpAttendanceTab()) {

							logResults.createLogs("Y", "PASS", "Employee Can Access His My Attendance Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Displaying My Attendance Tab." + RolePermissionpage.getExceptionDesc());
						}
						if (meghpiattendanceemployeepage.EmpAttendanceLoaded()) {

							logResults.createLogs("Y", "PASS", "Employee Attendance Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Loading My Attendance Table Data." + meghpiattendanceemployeepage.getExceptionDesc());
						}
						if (meghleavepage.MonthFilterContains(Regulizationdate)) {

							logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
									+ meghleavepage.getExceptionDesc());
						}
						
						if (meghpiattendanceemployeepage.EmpAttendanceTabSearchTextField(uidate)) {

							logResults.createLogs("Y", "PASS", "Employee Shift Applied Date Inputted Successfully." + uidate);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting Emp  Shift Applied Date In Search TextField." + meghpiattendanceemployeepage.getExceptionDesc());
						}
						
						
						if (meghpishiftrequestpage.validateAttendanceRowForShiftRequest(Regulizationdate, regularizationintime, regularizationouttime , shiftstatus, empstatusonadmin )) {

							logResults.createLogs("Y", "PASS", "Emp Present Status, Assigned Shift, InTime, OutTime Validated Successfully." + empid + " intime  " + regularizationintime + " And out time" + regularizationouttime + " shift is " + shiftstatus + "status is" + empstatusonadmin   );
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating Emp Attendance Present Status, Assigned Shift, InTime, OutTime." + meghpishiftrequestpage.getExceptionDesc());
						}
						}
						
						
						
						// MPI_800_Shift_request_08
						@Test(enabled = true, priority = 9, groups = { "Smoke" })
						public void MPI_800_Shift_request_08()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To check that, as a admin \"approve all\" using \"approve all\" button feature and ensure all the pending requests irrespective of page all the requests are processed and approved.");

							 
							 // Load all data including punch-out time and mode
						    ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
						            "password,baseuri,loginendpoint,endpointoftransaction," +
						            "cardnumber,cardtype,bio1finger,bio2finger," +
						            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha,regularizationstatus,policyname,shiftstatus,regularizationreason,firstname,regularizationintime,regularizationouttime,empstatusonadmin");

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
					       
					        String policyname = data.get(i++);
					        String shiftstatus = data.get(i++);
					        String regularizationreason = data.get(i++);
					        String firstname = data.get(i++);

					        String regularizationintime = data.get(i++);
					        String regularizationouttime = data.get(i++);
					      
					        String empstatusonadmin = data.get(i++);
					        
					        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
					        
					        Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();
							String thirdworkingday  = thirdrdday.get("month3WorkingDate");
							
							 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
								String Regulizationdate  =	dateDetails.get("month2WorkingDate");
							String uidate = Pramod.convertToUIFormat(Regulizationdate);
								
								String inouttimetwo = Regulizationdate + " " + inouttime1;
								String secondInOutTimetwo = Regulizationdate + " " + secondInOutTime2;

							LocalDate localDate = LocalDate.parse(thirdworkingday);
							 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
							
							String inouttime = thirdworkingday + " " + inouttime1;
							String secondInOutTime = thirdworkingday + " " + secondInOutTime2;
							
					        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 

							  MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
								MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
								MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				                            MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);
								 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
									MeghLoginTest meghlogintest = new MeghLoginTest();
									
									MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
									 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
								        MeghLeavePage meghleavepage = new MeghLeavePage(driver);


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
						 
						    
						    
						    // Punch IN
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
							if (RolePermissionpage.EmpShiftRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ApplyShiftRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Account Apply Shift Request Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Apply Shift Request Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectDateTextField()) {

								logResults.createLogs("Y", "PASS", "Date TextField Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Date TextField ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectDateTextFieldDateSelected(Regulizationdate)) {

								logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + Regulizationdate);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Date." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

								logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectShiftPolicyDropDown(policyname)) {

								logResults.createLogs("Y", "PASS", "Morning Shift Policy Selected Successfully." + policyname);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Morning Shift Policy." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectShiftDropDown()) {

								logResults.createLogs("Y", "PASS", "Shift DropDown Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift DropDown ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectShiftSearchInput(shiftstatus)) {

								logResults.createLogs("Y", "PASS", "Morning Shift Name Inputted Successfully." + shiftstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Morning Shift Name." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectShiftFromOption()) {

								logResults.createLogs("Y", "PASS", "Morning Shift  Selected Successfully." + shiftstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Morning Shift ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

								logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.ShiftApplyButton()) {

								logResults.createLogs("Y", "PASS", "Shift Request Apply Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Apply Button ." + meghpishiftrequestpage.getExceptionDesc());
							}
						
							if (RolePermissionpage.EmployeeAttendanceButton()) {

								logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
							}
							if (RolePermissionpage.EmpShiftRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ApplyShiftRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Account Apply Shift Request Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Apply Shift Request Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectDateTextField()) {

								logResults.createLogs("Y", "PASS", "Date TextField Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Date TextField ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectDateTextFieldDateSelected(thirdworkingday)) {

								logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + thirdworkingday);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Date." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

								logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectShiftPolicyDropDown(policyname)) {

								logResults.createLogs("Y", "PASS", "Morning Shift Policy Selected Successfully." + policyname);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Morning Shift Policy." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectShiftDropDown()) {

								logResults.createLogs("Y", "PASS", "Shift DropDown Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift DropDown ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.SelectShiftSearchInput(shiftstatus)) {

								logResults.createLogs("Y", "PASS", "Morning Shift Name Inputted Successfully." + shiftstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Morning Shift Name." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.SelectShiftFromOption()) {

								logResults.createLogs("Y", "PASS", "Morning Shift  Selected Successfully." + shiftstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Morning Shift ." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftReasonTextField(regularizationreason)) {

								logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + regularizationreason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.ShiftApplyButton()) {

								logResults.createLogs("Y", "PASS", "Shift Request Apply Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Apply Button ." + meghpishiftrequestpage.getExceptionDesc());
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
										"Login Is Failed." + MeghLoginPage.getExceptionDesc());
							}

							if (RolePermissionpage.EmployeeAttendanceButton()) {

								logResults.createLogs("Y", "PASS", " Attendance Module Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Attendane Module." + RolePermissionpage.getExceptionDesc());
							}
							if (RolePermissionpage.HrAccountShiftRequestTab()) {

								logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
										+ RolePermissionpage.getExceptionDesc());
							}
if (meghleavepage.MonthFilterContains(thirdworkingday)) {

	logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday );
} else {
	logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
			+ meghleavepage.getExceptionDesc());
}
							if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

								logResults.createLogs("Y", "PASS", "Shift Request Summary Clicked Successfully On Admin.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.PendingShiftRequestCard()) {

								logResults.createLogs("Y", "PASS", "Employee Account Shift Request Pending Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Pending Summary Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghregularizationrequestpage.ApproveAllButtonInAdmin()) {

								logResults.createLogs("Y", "PASS", "Approve All Button Clicked On Admin Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Approve All Button." + meghregularizationrequestpage.getExceptionDesc());
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
						 	if (meghpishiftrequestpage.ApprovedShiftRequestSummaryCount()) {

						 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Approved Summary Clicked Successfully.");
						 	} else {
						 		logResults.createLogs("Y", "FAIL",
						 				"Error While Clicking On Admin Shift Request Approved Summary Button." + meghregularizationrequestpage.getExceptionDesc());
						 	}
						 	if (meghpishiftrequestpage.ShiftRequestSearchTextField(firstname)) {

								logResults.createLogs("Y", "PASS", "On Admin Account Shift Requested Emp Name Inputted On Search Textfield   Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Emp Name Search TextField." + meghpishiftrequestpage.getExceptionDesc());
							}
						 	if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnAdminAccountList(thirdworkingday, regularizationstatus, empid)) {

								logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Approved Summary Validated Successfully." + thirdworkingday +" and status is " + regularizationstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Shift Request Approved Summary Count And Record." + meghpishiftrequestpage.getExceptionDesc());
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
								if (RolePermissionpage.EmpShiftRequest()) {

									logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
								}
								if (meghleavepage.MonthFilterContains(thirdworkingday)) {

									logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday );
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
											+ meghleavepage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

									logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
								}
								
								if (meghpishiftrequestpage.ApprovedShiftRequestSummaryCount()) {

							 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Approved Summary Clicked Successfully.");
							 	} else {
							 		logResults.createLogs("Y", "FAIL",
							 				"Error While Clicking On Admin Shift Request Approved Summary Button." + meghregularizationrequestpage.getExceptionDesc());
							 	}
								
								if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnEmpAccountForList(thirdworkingday, regularizationstatus)) {

									logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Pending Summary Validated Successfully." + thirdworkingday +" and status is " + regularizationstatus);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Validating Shift Request Pending Summary Count And Record." + meghpiOTpage.getExceptionDesc());
								}
								
								if (RolePermissionpage.EmpAttendanceTab()) {

									logResults.createLogs("Y", "PASS", "Employee Can Access His My Attendance Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying My Attendance Tab." + RolePermissionpage.getExceptionDesc());
								}
								if (meghpiattendanceemployeepage.EmpAttendanceLoaded()) {

									logResults.createLogs("Y", "PASS", "Employee Attendance Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Loading My Attendance Table Data." + meghpiattendanceemployeepage.getExceptionDesc());
								}
								if (meghleavepage.MonthFilterContains(thirdworkingday)) {

									logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday );
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
											+ meghleavepage.getExceptionDesc());
								}

								
								if (meghpiattendanceemployeepage.EmpAttendanceTabSearchTextField(uidate)) {

									logResults.createLogs("Y", "PASS", "Employee Shift Applied Date Inputted Successfully." + uidate);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Inputting Emp Applied Shift Date In Search TextField." + meghpiattendanceemployeepage.getExceptionDesc());
								}
								
								
								if (meghpishiftrequestpage.validateAttendanceRowForShiftRequest(thirdworkingday, regularizationintime, regularizationouttime , shiftstatus, empstatusonadmin )) {

									logResults.createLogs("Y", "PASS", "Emp Present Status, Assigned Shift, InTime, OutTime Validated Successfully." + empid + " intime  " + regularizationintime + " And out time" + regularizationouttime + " shift is " + shiftstatus + "status is" + empstatusonadmin   );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Validating Emp Attendance Present Status, Assigned Shift, InTime, OutTime." + meghpishiftrequestpage.getExceptionDesc());
								}
						}	
						
						
						
						// MPI_802_Shift_request_10
						@Test(enabled = true, priority = 10, groups = { "Smoke" })
						public void MPI_802_Shift_request_10()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To validate, check the statistics for Shift Requests — Approved, Rejected, Revoked, and Pending counts on employee account.");

							ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
									"password,captcha,empid,approvedcount,pendingfilter,rejectedfilter,revokedfilter");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String empid = data.get(2);
							
							String approvedcount = data.get(3);
							String pendingfilter = data.get(4);
							String rejectedfilter = data.get(5);
							String revokedfilter = data.get(6);

							  Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();
								String thirdworkingday  = thirdrdday.get("month3WorkingDate");
							
							

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage meghloginpage = new MeghLoginPage(driver);
			                MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);

					MeghLoginTest meghlogintest = new MeghLoginTest();
					MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

						
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
							if (RolePermissionpage.EmpShiftRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
							}
							if (meghleavepage.MonthFilterContains(thirdworkingday)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}
						
							if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

								logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
							}
							
							if (meghpishiftrequestpage.ApprovedShiftRequestSummaryCountGetText(approvedcount)) {

								logResults.createLogs("Y", "PASS", "Employee Account Approved Shift Request Count Validated Successfully." + approvedcount);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating  Approved Shift Request Count." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.PendingShiftRequestCardGetText(pendingfilter)) {

								logResults.createLogs("Y", "PASS", "Employee Account Pending Shift Request Count Validated Successfully." + pendingfilter);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating  Pending Shift Request Count." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftRequestRejectedSummaryCountGetText(rejectedfilter)) {

								logResults.createLogs("Y", "PASS", "Employee Account Rejected Shift Request Count Validated Successfully." + rejectedfilter);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating  Rejected Shift Request Count." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftRequestRevokedSummaryCountGetText(revokedfilter)) {

								logResults.createLogs("Y", "PASS", "Employee Account Revoked Shift Request Count Validated Successfully." + revokedfilter);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating  Revoked Shift Request Count." + meghpishiftrequestpage.getExceptionDesc());
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
							if (RolePermissionpage.HrAccountShiftRequestTab()) {

								logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
										+ RolePermissionpage.getExceptionDesc());
							}
							if (meghleavepage.MonthFilterContains(thirdworkingday)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}

							if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

								logResults.createLogs("Y", "PASS", "Shift Request Summary Clicked Successfully On Admin.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Shift Request Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ApprovedShiftRequestSummaryCountGetText(approvedcount)) {

								logResults.createLogs("Y", "PASS", "Employee Account Approved Shift Request Count Validated Successfully." + approvedcount);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating  Approved Shift Request Count." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.PendingShiftRequestCardGetText(pendingfilter)) {

								logResults.createLogs("Y", "PASS", "Employee Account Pending Shift Request Count Validated Successfully." + pendingfilter);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating  Pending Shift Request Count." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftRequestRejectedSummaryCountGetText(rejectedfilter)) {

								logResults.createLogs("Y", "PASS", "Employee Account Rejected Shift Request Count Validated Successfully." + rejectedfilter);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating  Rejected Shift Request Count." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ShiftRequestRevokedSummaryCountGetText(revokedfilter)) {

								logResults.createLogs("Y", "PASS", "Employee Account Revoked Shift Request Count Validated Successfully." + revokedfilter);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating  Revoked Shift Request Count." + meghpishiftrequestpage.getExceptionDesc());
							}
						}
						
						
						// MPI_806_Shift_request_12
						@Test(enabled = true, priority = 11, groups = { "Smoke" })
						public void MPI_806_Shift_request_12()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To check that, check the functionality of search feature by selecting each search option.");

							ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
									"password,captcha,empid,shiftstatus,policyname,assignshift,reason");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String empid = data.get(2);
							
							String appliedshift = data.get(3);
							String assignedpolicy = data.get(4);
							String assignshift = data.get(5);
							String reason = data.get(6);

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			                MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);

					MeghLoginTest meghlogintest = new MeghLoginTest();
							
								MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
							MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
					        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

					        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
							
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);

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
					
					if (RolePermissionpage.HrAccountShiftRequestTab()) {

						logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
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
					
					if (meghpishiftrequestpage.EnrollIDSearchOption()) {

						logResults.createLogs("Y", "PASS", "EnrollID CheckBox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On AEnrollID CheckBox." + meghpishiftrequestpage.getExceptionDesc());
					}	
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.ShiftRequestSearchTextField(empid)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Shift Requested Emp Name Inputted On Search Textfield   Successfully." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name Search TextField." + meghpishiftrequestpage.getExceptionDesc());
					}	
					if (meghpishiftrequestpage.EmpIDSearchResult(empid)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Shift Request Search Inputted Emp ID Validated Successfully." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Emp ID." + meghpishiftrequestpage.getExceptionDesc());
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
					
					if (meghpishiftrequestpage.EnrollIDSearchOption()) {

						logResults.createLogs("Y", "PASS", "EnrollID CheckBox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On AEnrollID CheckBox." + meghpishiftrequestpage.getExceptionDesc());
					}	
					if (meghpishiftrequestpage.AssignShiftPolicySearchOption()) {

						logResults.createLogs("Y", "PASS", "Assign Shift Policy CheckBox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Assign Shift Policy CheckBox." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.AssignShiftSearchOption()) {

						logResults.createLogs("Y", "PASS", "Assign Shift CheckBox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Assign Shift CheckBox." + meghpishiftrequestpage.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					
					
					if (meghpishiftrequestpage.ShiftRequestSearchTextField(assignedpolicy)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Assigned Shift Policy Inputted On Search Textfield   Successfully." + assignedpolicy);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Assigned Shift Policy On Search TextField." + meghpishiftrequestpage.getExceptionDesc());
					}	
					if (meghpishiftrequestpage.AssignShiftPolicySearchResult(assignedpolicy)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Assigned Shift Policy Validated Successfully." + assignedpolicy);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Assigned Shift Policy." + meghpishiftrequestpage.getExceptionDesc());
					}	
					if (meghpishiftrequestpage.ShiftRequestSearchTextField(assignshift)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Assigned Shift  Inputted On Search Textfield   Successfully." + assignshift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Assigned Shift  On Search TextField." + meghpishiftrequestpage.getExceptionDesc());
					}	
					if (meghpishiftrequestpage.AssignShiftSearchResult(assignshift)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Assigned Shift  Validated Successfully." + assignshift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Assigned Shift." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					
					if (meghpishiftrequestpage.AssignShiftPolicySearchOption()) {

						logResults.createLogs("Y", "PASS", "Assign Shift Policy CheckBox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Assign Shift Policy CheckBox." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.AssignShiftSearchOption()) {

						logResults.createLogs("Y", "PASS", "Assign Shift CheckBox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Assign Shift CheckBox." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.AppliedShiftSearchOption()) {

						logResults.createLogs("Y", "PASS", "Applied Shift CheckBox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Applied Shift CheckBox." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.ShiftRequestReasonSearchOption()) {

						logResults.createLogs("Y", "PASS", " Shift Request Reason CheckBox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Request Reason CheckBox." + meghpishiftrequestpage.getExceptionDesc());
					}
				
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.ShiftRequestSearchTextField(appliedshift)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Applied Shift  Inputted On Search Textfield   Successfully." + appliedshift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Applied Shift  On Search TextField." + meghpishiftrequestpage.getExceptionDesc());
					}	
					if (meghpishiftrequestpage.AppliedShiftSearchResult(appliedshift)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Applied Shift  Validated Successfully." + appliedshift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Applied Shift." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.ShiftRequestSearchTextField(reason)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Shift Request Reason  Inputted On Search Textfield   Successfully." + reason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Request Reason  On Search TextField." + meghpishiftrequestpage.getExceptionDesc());
					}	
					if (meghpishiftrequestpage.ReasonSearchResult(reason)) {

						logResults.createLogs("Y", "PASS", "On Admin Account Shift Request Reason  Validated Successfully." + reason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
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
						
						if (RolePermissionpage.EmpShiftRequest()) {

							logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
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
					
						if (meghpishiftrequestpage.AssignShiftPolicySearchOption()) {

							logResults.createLogs("Y", "PASS", "Assign Shift Policy CheckBox Selected Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Assign Shift Policy CheckBox." + meghpishiftrequestpage.getExceptionDesc());
						}
						if (meghpishiftrequestpage.AssignShiftSearchOption()) {

							logResults.createLogs("Y", "PASS", "Assign Shift CheckBox Selected Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Assign Shift CheckBox." + meghpishiftrequestpage.getExceptionDesc());
						}
						
						if (OfficePage.SearchDropDown()) {
							logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
						}
				
						if (meghpishiftrequestpage.ShiftRequestSearchTextField(assignedpolicy)) {

							logResults.createLogs("Y", "PASS", "On Admin Account Assigned Shift Policy Inputted On Search Textfield   Successfully." + assignedpolicy);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting Assigned Shift Policy On Search TextField." + meghpishiftrequestpage.getExceptionDesc());
						}	
						if (meghpishiftrequestpage.AssignShiftPolicySearchResult(assignedpolicy)) {

							logResults.createLogs("Y", "PASS", "On Admin Account Assigned Shift Policy Validated Successfully." + assignedpolicy);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating  Assigned Shift Policy." + meghpishiftrequestpage.getExceptionDesc());
						}	
						if (meghpishiftrequestpage.ShiftRequestSearchTextField(assignshift)) {

							logResults.createLogs("Y", "PASS", "On Admin Account Assigned Shift  Inputted On Search Textfield   Successfully." + assignshift);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting Assigned Shift  On Search TextField." + meghpishiftrequestpage.getExceptionDesc());
						}	
						if (meghpishiftrequestpage.AssignShiftSearchResult(assignshift)) {

							logResults.createLogs("Y", "PASS", "On Admin Account Assigned Shift  Validated Successfully." + assignshift);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating  Assigned Shift." + meghpishiftrequestpage.getExceptionDesc());
						}
						if (OfficePage.SearchDropDown()) {
							logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
						}
						
						
						if (meghpishiftrequestpage.AssignShiftPolicySearchOption()) {

							logResults.createLogs("Y", "PASS", "Assign Shift Policy CheckBox Selected Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Assign Shift Policy CheckBox." + meghpishiftrequestpage.getExceptionDesc());
						}
						if (meghpishiftrequestpage.AssignShiftSearchOption()) {

							logResults.createLogs("Y", "PASS", "Assign Shift CheckBox Selected Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Assign Shift CheckBox." + meghpishiftrequestpage.getExceptionDesc());
						}
						if (meghpishiftrequestpage.AppliedShiftSearchOption()) {

							logResults.createLogs("Y", "PASS", "Applied Shift CheckBox Selected Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Applied Shift CheckBox." + meghpishiftrequestpage.getExceptionDesc());
						}
						if (meghpishiftrequestpage.ShiftRequestReasonSearchOption()) {

							logResults.createLogs("Y", "PASS", " Shift Request Reason CheckBox Selected Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Shift Request Reason CheckBox." + meghpishiftrequestpage.getExceptionDesc());
						}
					
						if (OfficePage.SearchDropDown()) {
							logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
						}
						if (meghpishiftrequestpage.ShiftRequestSearchTextField(appliedshift)) {

							logResults.createLogs("Y", "PASS", "On Admin Account Applied Shift  Inputted On Search Textfield   Successfully." + appliedshift);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting Applied Shift  On Search TextField." + meghpishiftrequestpage.getExceptionDesc());
						}	
						if (meghpishiftrequestpage.AppliedShiftSearchResult(appliedshift)) {

							logResults.createLogs("Y", "PASS", "On Admin Account Applied Shift  Validated Successfully." + appliedshift);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating  Applied Shift." + meghpishiftrequestpage.getExceptionDesc());
						}
						if (meghpishiftrequestpage.ShiftRequestSearchTextField(reason)) {

							logResults.createLogs("Y", "PASS", "On Admin Account Shift Request Reason  Inputted On Search Textfield   Successfully." + reason);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting Shift Request Reason  On Search TextField." + meghpishiftrequestpage.getExceptionDesc());
						}	
						if (meghpishiftrequestpage.ReasonSearchResult(reason)) {

							logResults.createLogs("Y", "PASS", "On Admin Account Shift Request Reason  Validated Successfully." + reason);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating  Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
						}
						
						}			
						
						
						
						// MPI_1624_Shift_request_14
						@Test(enabled = true, priority = 12, groups = { "Smoke" })
						public void MPI_1624_Shift_request_14()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, raise a shift request for the employee from the admin account and ensure the request is auto-approved and the shift is updated on the employee account.");

							ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
									"password,captcha,regularizationstatus,empid,firstname,policyname,shiftstatus");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String regularizationstatus = data.get(2);
							
							String empid = data.get(3);
							
							String firstname = data.get(4);
							String policyname = data.get(5);
							String shiftstatus = data.get(6);
						
						
							
							 Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();
								String fifthworkingday  = thirdrdday.get("month5WorkingDate");
						        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

				            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			                MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);

					MeghLoginTest meghlogintest = new MeghLoginTest();
					MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);

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
					if (RolePermissionpage.HrAccountShiftRequestTab()) {

						logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
					}	
					if (meghpishiftrequestpage.ApplyShiftRequestOnAdmin()) {

						logResults.createLogs("Y", "PASS", "On Admin Account Apply Shift Request Clicked  Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Apply Shift Request Button." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.ApplyShiftRequestForOthersOnAdmin()) {

						logResults.createLogs("Y", "PASS", "On Admin Account Apply Shift Request For Others Clicked  Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Apply Shift Request For Others Button." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.SelectEmpDropDownOnAdmin()) {

						logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Emp DropDown Clicked Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Emp ID DropDown." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.SelectEmpSearchFieldOnAdmin(firstname)) {

						logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Emp Name Inputted Successfully." + firstname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.SelectedEmpFromDropDownOnAdmin()) {

						logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Emp Name Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp Name." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.SelectShiftDateForOthersOnAdmin()) {

						logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Date Clicked Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date Text Field." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.SelectShiftDateForOthersOnAdminInputted(fifthworkingday)) {

						logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Date Selected Successfully." + fifthworkingday );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Date." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.SelectShiftPolicyForOthersOnAdmin(policyname)) {

						logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Shift Policy Selected Successfully." + policyname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Shift Policy." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.SelectShiftDropDownForOthersOnAdmin()) {

						logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Shift DropDown Clicked Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift DropDown." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.SelectShiftSearchFieldForOthersOnAdmin(shiftstatus)) {

						logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Shift Name Inputted Successfully." + shiftstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Name." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.SelectShiftResultForOthersOnAdmin()) {

						logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Shift Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting  Shift." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.ShiftReasonForOthersOnAdmin(shiftstatus)) {

						logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Reason Inputted Successfully." + shiftstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Reason." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.ShiftRequestApplyButtonForOthers()) {

						logResults.createLogs("Y", "PASS", "On Admin Account  Apply Shift Request Button Clicked Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Apply Button." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(fifthworkingday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + fifthworkingday );
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
					 	if (meghpishiftrequestpage.ApprovedShiftRequestSummaryCount()) {

					 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Approved Summary Clicked Successfully.");
					 	} else {
					 		logResults.createLogs("Y", "FAIL",
					 				"Error While Clicking On Admin Shift Request Approved Summary Button." + meghregularizationrequestpage.getExceptionDesc());
					 	}
					 	if (meghpishiftrequestpage.ShiftRequestSearchTextField(firstname)) {

							logResults.createLogs("Y", "PASS", "On Admin Account Shift Requested Emp Name Inputted On Search Textfield   Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting Emp Name Search TextField." + meghpishiftrequestpage.getExceptionDesc());
						}
					 	if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnAdminAccountList(fifthworkingday, regularizationstatus, empid)) {

							logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Approved Summary Validated Successfully." + fifthworkingday +" and status is " + regularizationstatus);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating Emp Shift Request Approved Summary Count And Record." + meghpishiftrequestpage.getExceptionDesc());
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
						if (RolePermissionpage.EmpShiftRequest()) {

							logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
						}
						if (meghleavepage.MonthFilterContains(fifthworkingday)) {

							logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + fifthworkingday );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
									+ meghleavepage.getExceptionDesc());
						}
						if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

							logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
						}
						
						if (meghpishiftrequestpage.ApprovedShiftRequestSummaryCount()) {

					 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Approved Summary Clicked Successfully.");
					 	} else {
					 		logResults.createLogs("Y", "FAIL",
					 				"Error While Clicking On Admin Shift Request Approved Summary Button." + meghregularizationrequestpage.getExceptionDesc());
					 	}
						
						if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnEmpAccountForList(fifthworkingday, regularizationstatus)) {

							logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Pending Summary Validated Successfully." + fifthworkingday +" and status is " + regularizationstatus);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating Shift Request Pending Summary Count And Record." + meghpiOTpage.getExceptionDesc());
						}
					
						}			
						
						
						// MPI_1625_Shift_request_15
						@Test(enabled = true, priority = 13, groups = { "Smoke" })
						public void MPI_1625_Shift_request_15()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, raise a shift request for yourself from the admin account, switch to the employee view of the same account, and ensure the updated shift is displayed for the selected date.");

							ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
									"password,captcha,regularizationstatus,policyname,shiftstatus");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String regularizationstatus = data.get(2);
						
							String policyname = data.get(3);
							String shiftstatus = data.get(4);
						
						
							
							 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

						        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
							
				            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			                MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);
					        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

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
					if (RolePermissionpage.HrAccountShiftRequestTab()) {

						logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
					}	
					if (meghpishiftrequestpage.ApplyShiftRequestOnAdmin()) {

						logResults.createLogs("Y", "PASS", "On Admin Account Apply Shift Request Clicked  Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Apply Shift Request Button." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.ShiftRequestApplyButtonForMe()) {

						logResults.createLogs("Y", "PASS", "On Admin Account Apply Shift Request For Others Clicked  Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Apply Shift Request For Others Button." + meghpishiftrequestpage.getExceptionDesc());
					}
					
					if (meghpishiftrequestpage.SelectDateTextField()) {

						logResults.createLogs("Y", "PASS", "Date TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date TextField ." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.SelectDateTextFieldDateSelected(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Date." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.ShiftReasonTextField(shiftstatus)) {

						logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + shiftstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.SelectShiftPolicyDropDown(policyname)) {

						logResults.createLogs("Y", "PASS", "Morning Shift Policy Selected Successfully." + policyname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Morning Shift Policy." + meghpishiftrequestpage.getExceptionDesc());
					}
					
					if (meghpishiftrequestpage.SelectShiftDropDown()) {

						logResults.createLogs("Y", "PASS", "Shift DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift DropDown ." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.SelectShiftSearchInput(shiftstatus)) {

						logResults.createLogs("Y", "PASS", "Morning Shift Name Inputted Successfully." + shiftstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Morning Shift Name." + meghpishiftrequestpage.getExceptionDesc());
					}
					
					if (meghpishiftrequestpage.SelectShiftFromOption()) {

						logResults.createLogs("Y", "PASS", "Morning Shift  Selected Successfully." + shiftstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Morning Shift ." + meghpishiftrequestpage.getExceptionDesc());
					}
					if (meghpishiftrequestpage.ShiftReasonTextField(shiftstatus)) {

						logResults.createLogs("Y", "PASS", "Shift Request Reason Inputted Successfully." + shiftstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Request Reason." + meghpishiftrequestpage.getExceptionDesc());
					}
					
					if (meghpishiftrequestpage.ShiftApplyButton()) {

						logResults.createLogs("Y", "PASS", "Shift Request Apply Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Request Apply Button ." + meghpishiftrequestpage.getExceptionDesc());
					}
					
					if (RolePermissionpage.EmployeeToggleSwitch()) {

						logResults.createLogs("Y", "PASS", "Employee Toggle Switch Successfully To Switch To Employee Account.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Toggle Switch." + RolePermissionpage.getExceptionDesc());
					}
				
						if (RolePermissionpage.EmployeeAttendanceButton()) {

							logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
						}
						if (RolePermissionpage.EmpShiftRequest()) {

							logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
						}
						if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

							logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
									+ meghleavepage.getExceptionDesc());
						}
					
						if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

							logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
						}
						
						if (meghpishiftrequestpage.ApprovedShiftRequestSummaryCount()) {

					 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Approved Summary Clicked Successfully.");
					 	} else {
					 		logResults.createLogs("Y", "FAIL",
					 				"Error While Clicking On Admin Shift Request Approved Summary Button." + meghregularizationrequestpage.getExceptionDesc());
					 	}
						
						if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnEmpAccountForList(monthFirstWorkingDate, regularizationstatus)) {

							logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Pending Summary Validated Successfully." + monthFirstWorkingDate +" and status is " + regularizationstatus);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating Shift Request Pending Summary Count And Record." + meghpiOTpage.getExceptionDesc());
						}
					
						}		
						
						// MPI_1626_Shift_request_16
						@Test(enabled = true, priority = 14, groups = { "Smoke" })
						public void MPI_1626_Shift_request_16() {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To validate, check the statistics for Shift Requests — Approved, Rejected, Revoked, and Pending counts on admin account.");
							logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_802_Shift_request_10 TestCase.");
						}
						
						
						// MPI_818_Shift_request_13
						@Test(enabled = true, priority = 15, groups = { "Smoke" })
					public void MPI_818_Shift_request_13()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To check that, as a employee complete working hours on general shift where employee was assigned with morning shift > now raise the regularization request and get approve and check the attendance status .");

							ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
									"password,captcha,deptname,officename,state,teamname,pin,longitude,firstname,policyname,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,shiftstatus,regularizationstatus,shiftpolicy");

							MeghMasterTeamMappingPage TeamMappingPage = new MeghMasterTeamMappingPage(driver);
						int i = 0;
							String password = data.get(i++);
							String captcha = data.get(i++);
							String deptname = data.get(i++);
							String officeName = data.get(i++);

							String state = data.get(i++);
							String teamname = data.get(i++) ;
							String pin = data.get(i++);
							String longitude = data.get(i++);
							String firstname = data.get(i++);
							String policyname = data.get(i++);
							
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
						        String shiftstatus = data.get(i++);
						        String regularizationstatus = data.get(i++);
						        String shiftpolicy = data.get(i++);
						        


							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
							MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);
							MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
							MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
							MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);
							MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

							MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
							MeghLoginPage meghloginpage =  new MeghLoginPage(driver);

						
							String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
					        
							 Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();
								String fourthworkingday  = thirdrdday.get("month4WorkingDate");

							LocalDate localDate = LocalDate.parse(fourthworkingday);
							 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
							
							String inouttime = fourthworkingday + " " + inouttime1;
							String secondInOutTime = fourthworkingday + " " + secondInOutTime2;
						    
							  MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
				                            MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);
								 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
							        MeghLeavePage meghleavepage = new MeghLeavePage(driver);


						    
							

					MeghLoginTest meghlogintest = new MeghLoginTest();
							
							if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
								logResults.createLogs("Y", "PASS", "Login Done Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Login Is Failed." + meghloginpage.getExceptionDesc());
							}
							
			

							// OfficeName Logic
						
								if (EmployeePage.DirectoryButton()) {
									logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Directery Button." + EmployeePage.getExceptionDesc());
								}

								if (EmployeePage.CompanyTab()) {
									logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Company Tab ." + EmployeePage.getExceptionDesc());
								}

								if (EmployeePage.OfficeButton()) {
									logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
								}

								if (EmployeePage.AddOfficeButton()) {
									logResults.createLogs("Y", "PASS", " Successfully Clicked On AddOfficeButton .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On AddOfficeButton ." + EmployeePage.getExceptionDesc());
								}

								if (EmployeePage.OfficeName(officeName)) {
									logResults.createLogs("Y", "PASS", " OfficeName Entered In the Office Name TextField ." + officeName);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Inputting The Office Name ." + EmployeePage.getExceptionDesc());
								}

								if (EmployeePage.OfficeTypeDropdown()) {
									logResults.createLogs("Y", "PASS", " Office Type Selected In the Office Type Dropdown .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Selecting The Office Type ." + EmployeePage.getExceptionDesc());
								}

								if (EmployeePage.CountryDropdown()) {
									logResults.createLogs("Y", "PASS", " Country Selected In the Country  Dropdown .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Selecting The Country ." + EmployeePage.getExceptionDesc());
								}

								if (EmployeePage.StateDropdown(state)) {
									logResults.createLogs("Y", "PASS", " State Selected In the State  Dropdown ." + state);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Selecting The State ." + EmployeePage.getExceptionDesc());
								}
								if (EmployeePage.GeoFencingRange(longitude)) {
									logResults.createLogs("Y", "PASS", " Geo Fencing Range Inputted Successfully." + longitude);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Inputting Geo Fencing Range ." + EmployeePage.getExceptionDesc());
								}
								if (EmployeePage.LatitudeTextField(longitude)) {
									logResults.createLogs("Y", "PASS", " Latitude Inputted Successfully." + longitude);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Inputting Latitude ." + EmployeePage.getExceptionDesc());
								}
								if (EmployeePage.LongitudeTextField(longitude)) {
									logResults.createLogs("Y", "PASS", " Longitude Inputted Successfully." + longitude);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Inputting Longitude ." + EmployeePage.getExceptionDesc());
								}
						
								if (EmployeePage.CompanyLocationSaveButton()) {
									logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
								}

							 // End of office Name

							// Dept Name
							
								if (EmployeePage.CompanyTab()) {
									logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Company Tab ." + EmployeePage.getExceptionDesc());
								}
								
								

								if (DepartmentPage.DepartmentDropIcon()) {
									logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
								}
								
								if (DepartmentPage.DepartmentButton()) {
									logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Department Tab ." + DepartmentPage.getExceptionDesc());
								}

								if (DepartmentPage.AddDepartmentButton()) {
									logResults.createLogs("Y", "PASS", "Add Department Tab  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Add Department Tab ." + DepartmentPage.getExceptionDesc());
								}

								if (DepartmentPage.DepartmentName(deptname)) {
									logResults.createLogs("Y", "PASS", "Department Name Entered Successfully." + deptname);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Inputting Department Name ." + DepartmentPage.getExceptionDesc());
								}

								if (DepartmentPage.AddDepartmentSaveButton()) {
									logResults.createLogs("Y", "PASS", "Successfully Clicked On Add Department Save Button.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Add Department Save Button ." + DepartmentPage.getExceptionDesc());
								}
								
								
								if (DepartmentPage.DepartmentDropIcon()) {
									logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
								}

								if (DepartmentPage.DepartmentMappingButton()) {
									logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On DepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
								}

								if (DepartmentPage.AddDepartmentMappingButton()) {
									logResults.createLogs("Y", "PASS", "Successfully Clicked On AddDepartmentMapping Button.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On AddDepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
								}

								if (DepartmentMappingpage.DepartmentSelectDropDown(deptname)) {
									logResults.createLogs("Y", "PASS", "Successfully Selected Department." + deptname);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Selecting Department ." + DepartmentMappingpage.getExceptionDesc());
								}

								if (DepartmentMappingpage.OfficeSelectDropDown(officeName)) {
									logResults.createLogs("Y", "PASS", "Successfully Selected OfficeName." + officeName);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Selecting OfficeName ." + DepartmentMappingpage.getExceptionDesc());
								}

								if (DepartmentMappingpage.AddDeptMappingSaveButton()) {
									
									logResults.createLogs("Y", "PASS", "AddDeptMappingSaveButton Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error while Clicking On AddDeptMappingSaveButton."
											+ DepartmentMappingpage.getExceptionDesc());
								}

								
							 // End of Dept Name
						
								if (EmployeePage.CompanyTab()) {
									logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Company Tab ." + EmployeePage.getExceptionDesc());
								}
								
								
								
								if (TeamPage.TeamDropIcon()) {
									logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Team Drop Icon ." + TeamPage.getExceptionDesc());
								}

								if (TeamPage.TeamButton()) {
									logResults.createLogs("Y", "PASS", "Team Button  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Team Button ." + TeamPage.getExceptionDesc());
								}

								if (TeamPage.AddTeamButton()) {
									logResults.createLogs("Y", "PASS", "Add Team Button  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Add Team Button ." + TeamPage.getExceptionDesc());
								}

								if (TeamPage.TeamNameTextField(teamname)) {
									logResults.createLogs("Y", "PASS", "Team Name Is Entered In Team Name Text Field." + teamname);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Entering Team Name." + TeamPage.getExceptionDesc());
								}

								if (TeamPage.TeamSaveButton()) {
									logResults.createLogs("Y", "PASS", "Team Save Button  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On TeamSaveButton ." + TeamPage.getExceptionDesc());
								}

								
								
								if (TeamPage.TeamDropIcon()) {
									logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Team Drop Icon ." + TeamPage.getExceptionDesc());
								}

								if (TeamPage.TeamMappingButton()) {
									logResults.createLogs("Y", "PASS", "TeamMapping Button  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On TeamMapping Button ." + TeamPage.getExceptionDesc());
								}

								if (TeamPage.AddTeamMappingButton()) {
									logResults.createLogs("Y", "PASS", "AddTeamMapping Button  Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On AddTeamMapping Button ." + TeamPage.getExceptionDesc());
								}

								if (TeamMappingPage.TeamDropDownOfForm(teamname)) {
									logResults.createLogs("Y", "PASS", "Team Name Selected Successfully." + teamname);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Selecting Team Name ." + TeamMappingPage.getExceptionDesc());
								}

								if (TeamMappingPage.TeamDeptDropDown()) {
									logResults.createLogs("Y", "PASS", "Department DropDown Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Department DropDown ." + TeamMappingPage.getExceptionDesc());
								}

								if (TeamMappingPage.TeamDeptSearchField(deptname)) {
									logResults.createLogs("Y", "PASS", "Department Name Inputted Successfully." + deptname);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Inputting Department Name ." + TeamMappingPage.getExceptionDesc());
								}
								

								if (TeamMappingPage.TeamDeptSearchResult()) {
									logResults.createLogs("Y", "PASS", "Department Name Selected Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Selecting Department Name ." + TeamMappingPage.getExceptionDesc());
								}

								if (TeamMappingPage.AddTeamMappingSaveButton()) {
									
									logResults.createLogs("Y", "PASS", "Add Team Mapping Saved Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Saving The Team Mapping ." + TeamMappingPage.getExceptionDesc());
								}	
								
								if (EmployeePage.DirectoryButton()) {
									logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Directery Button." + EmployeePage.getExceptionDesc());
								}

								if (EmployeePage.EmployeeTab()) {
									logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On EmployeeTab." + EmployeePage.getExceptionDesc());
								}

							
								if (EmployeePage.SearchTextField(firstname)) {
									logResults.createLogs("Y", "PASS",
											"Clicked On Search Text Field And Pass The Created Employee LastName Successfully." + firstname);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Search Text Field." + EmployeePage.getExceptionDesc());
								}
								

								if (EmployeePage.SearchResult()) {
									logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully." );
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Displaying Employee." + EmployeePage.getExceptionDesc());
								}

								if (DepartmentPage.Employee3dotsFirstRecord()) {
									logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On First Employee Record 3dots ." + DepartmentPage.getExceptionDesc());
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
								
								if (DepartmentPage.CompanyLocationSelected(officeName)) {
									logResults.createLogs("Y", "PASS", "Successfully Selected Office Name ." + officeName);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Selecting Office Name." + DepartmentPage.getExceptionDesc());
								}

								if (DepartmentPage.DepartmentDropDown()) {
									logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentDropDown .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On DepartmentDropDown  ." + DepartmentPage.getExceptionDesc());
								}

								if (TeamMappingPage.DeptDropDownTextField(deptname)) {
									logResults.createLogs("Y", "PASS", "Successfully Input The DeptName ." + deptname);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Inputting  Department Name  ." + TeamMappingPage.getExceptionDesc());
								}
								
								if (TeamMappingPage.DeptSearchResult()) {
									logResults.createLogs("Y", "PASS", "Successfully Selected DeptName .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Selecting  Department Name  ." + TeamMappingPage.getExceptionDesc());
								}

								if (AttendancePolicyPage.TeamDropDown(teamname)) {
									logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Selecting Team From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
								}

								if (AttendancePolicyPage.DesignationDropdown(designationname)) {
									logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Selecting Designation From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
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

								if (RolePermissionpage.ShiftDropDown()) {

									logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
								}

								if (ShiftPolicyPage.ShiftPolicyButton()) {

									logResults.createLogs("Y", "PASS", "ShiftPolicyButton Clicked Successfully .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On ShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
								}

								if (ShiftPolicyPage.ShiftPolicyPageLoaded()) {

									logResults.createLogs("Y", "PASS", "ShiftPolicy Page Loaded Successfully .");
								} else {
									logResults.createLogs("Y", "FAIL",
											" ShiftPolicy Page Is Not Loaded Completely." + ShiftPolicyPage.getExceptionDesc());
								}

								if (ShiftPolicyPage.ShiftSearchTextField(shiftpolicy)) {

									logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + shiftpolicy);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
								}


								if (ShiftPolicyPage.EditIcon()) {

									logResults.createLogs("Y", "PASS", "Shift Policy Edit Button Clicked Successfully .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On ShiftPolicy Edit Button." + ShiftPolicyPage.getExceptionDesc());
								}


								if (AttendancePolicyPage.AddEmpCriteria()) {

									logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On AddEmpCriteria Button ." + AttendancePolicyPage.getExceptionDesc());
								}

								if (AttendancePolicyPage.OfficeDropDown(officeName)) {

									logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully ." + officeName);
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
									logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown." + teamname);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Clicking On Team  Dropdown ." + AttendancePolicyPage.getExceptionDesc());
								}
								
								if (AttendancePolicyPage.TeamOptionSelected()) {
									logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error while Selecting Team From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
								}

								if (ShiftPolicyPage.ShiftAddEmpCriteriaApplyButton()) {

									logResults.createLogs("Y", "PASS", "Clicked On Apply Button Successfully .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Apply Button ." + ShiftPolicyPage.getExceptionDesc());
								}

								if (ShiftPolicyPage.CreatePolicySave()) {

									logResults.createLogs("Y", "PASS", "CreatePolicySave Button Clicked Successfully .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On CreatePolicySave Button." + ShiftPolicyPage.getExceptionDesc());
								}

								if (AttendancePolicyPage.YesButton()) {

									logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
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
							     
							    
							   

								if (RolePermissionpage.EmployeeAttendanceButton()) {

									logResults.createLogs("Y", "PASS", " Attendance Module Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Attendane Module." + RolePermissionpage.getExceptionDesc());
								}
								if (RolePermissionpage.HrAccountShiftRequestTab()) {

									logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
											+ RolePermissionpage.getExceptionDesc());
								}	
								if (meghpishiftrequestpage.ApplyShiftRequestOnAdmin()) {

									logResults.createLogs("Y", "PASS", "On Admin Account Apply Shift Request Clicked  Successfully." );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Apply Shift Request Button." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.ApplyShiftRequestForOthersOnAdmin()) {

									logResults.createLogs("Y", "PASS", "On Admin Account Apply Shift Request For Others Clicked  Successfully." );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Apply Shift Request For Others Button." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.SelectEmpDropDownOnAdmin()) {

									logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Emp DropDown Clicked Successfully." );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Emp ID DropDown." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.SelectEmpSearchFieldOnAdmin(firstname)) {

									logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Emp Name Inputted Successfully." + firstname );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Inputting Emp Name." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.SelectedEmpFromDropDownOnAdmin()) {

									logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Emp Name Selected Successfully." );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Selecting Emp Name." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.SelectShiftDateForOthersOnAdmin()) {

									logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Date Clicked Successfully." );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Date Text Field." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.SelectShiftDateForOthersOnAdminInputted(fourthworkingday)) {

									logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Date Selected Successfully." + fourthworkingday );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Selecting Date." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.SelectShiftPolicyForOthersOnAdmin(policyname)) {

									logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Shift Policy Selected Successfully." + policyname );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Selecting Shift Policy." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.SelectShiftDropDownForOthersOnAdmin()) {

									logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Shift DropDown Clicked Successfully." );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Shift DropDown." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.SelectShiftSearchFieldForOthersOnAdmin(shiftstatus)) {

									logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Shift Name Inputted Successfully." + shiftstatus );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Inputting Shift Name." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.SelectShiftResultForOthersOnAdmin()) {

									logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Shift Selected Successfully." );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Selecting  Shift." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.ShiftReasonForOthersOnAdmin(shiftstatus)) {

									logResults.createLogs("Y", "PASS", "On Admin Account To Apply Shift Request Reason Inputted Successfully." + shiftstatus );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Inputting Reason." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghpishiftrequestpage.ShiftRequestApplyButtonForOthers()) {

									logResults.createLogs("Y", "PASS", "On Admin Account  Apply Shift Request Button Clicked Successfully." );
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Apply Button." + meghpishiftrequestpage.getExceptionDesc());
								}
								if (meghleavepage.MonthFilterContains(fourthworkingday)) {

									logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + fourthworkingday );
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
								 	if (meghpishiftrequestpage.ApprovedShiftRequestSummaryCount()) {

								 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Approved Summary Clicked Successfully.");
								 	} else {
								 		logResults.createLogs("Y", "FAIL",
								 				"Error While Clicking On Admin Shift Request Approved Summary Button." + meghregularizationrequestpage.getExceptionDesc());
								 	}
								 	if (meghpishiftrequestpage.ShiftRequestSearchTextField(firstname)) {

										logResults.createLogs("Y", "PASS", "On Admin Account Shift Requested Emp Name Inputted On Search Textfield   Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Inputting Emp Name Search TextField." + meghpishiftrequestpage.getExceptionDesc());
									}
								 	if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnAdminAccountList(fourthworkingday, regularizationstatus, empid)) {

										logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Approved Summary Validated Successfully." + fourthworkingday +" and status is " + regularizationstatus);
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Validating Emp Shift Request Approved Summary Count And Record." + meghpishiftrequestpage.getExceptionDesc());
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
									if (RolePermissionpage.EmpShiftRequest()) {

										logResults.createLogs("Y", "PASS", "Employee Can Access His EmpShiftRequest Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Displaying EmpShiftRequest Tab." + RolePermissionpage.getExceptionDesc());
									}
									if (meghleavepage.MonthFilterContains(fourthworkingday)) {

										logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + fourthworkingday );
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
												+ meghleavepage.getExceptionDesc());
									}
									if (meghpishiftrequestpage.MonthlyShiftSummaryDropDown()) {

										logResults.createLogs("Y", "PASS", "Employee Account OverTimeRequest Summary Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On EmpOverTimeRequest Summary Button." + meghpishiftrequestpage.getExceptionDesc());
									}
									
									if (meghpishiftrequestpage.ApprovedShiftRequestSummaryCount()) {

								 		logResults.createLogs("Y", "PASS", "Admin Account Shift Request Approved Summary Clicked Successfully.");
								 	} else {
								 		logResults.createLogs("Y", "FAIL",
								 				"Error While Clicking On Admin Shift Request Approved Summary Button." + meghregularizationrequestpage.getExceptionDesc());
								 	}
									
									if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnEmpAccountForList(fourthworkingday, regularizationstatus)) {

										logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Pending Summary Validated Successfully." + fourthworkingday +" and status is " + regularizationstatus);
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Validating Shift Request Pending Summary Count And Record." + meghpiOTpage.getExceptionDesc());
									}
						}	
					
						
						// MPI_1637_Shift_request_18
						@Test(enabled = true, priority = 16, groups = { "Smoke" })
						public void MPI_1637_Shift_request_18()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To check that, check the functionality of filter feature by selecting office name, dept name, team and designation.");

							ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
									"password,captcha,empid,officename");

							
							String password = data.get(0);
							String captcha = data.get(1);							
							String empid = data.get(2);
							String officename = data.get(3);
							
						
							 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

						        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
							
						        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			                MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);

					MeghLoginTest meghlogintest = new MeghLoginTest();
					MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
				
						MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

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
						if (RolePermissionpage.HrAccountShiftRequestTab()) {

							logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
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
							
						if (meghpishiftrequestpage.EmpIDSearchResult(empid)) {

							logResults.createLogs("Y", "PASS", "On Admin Account Shift Request Filter  Applied Emp Criteria Result Validated Successfully." + empid);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating Filter Applied Result." + meghpishiftrequestpage.getExceptionDesc());
						}	
							}	
						
						
						// MPI_1636_Shift_request_17
						@Test(enabled = true, priority = 17, groups = { "Smoke" })
						public void MPI_1636_Shift_request_17()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To check that, select pending, Approved, rejected one by one  from filter feature and ensure respective status requests are displayed in the table.");

							ArrayList<String> data = initBase.loadExcelData("ShiftRequest_Tab", currTC,
									"password,captcha,regularizationstatus,empid,pendingfilter,rejectedfilter,revokedfilter");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String regularizationstatus = data.get(2);
							String empid  = data.get(3);
							String pendingfilter = data.get(4);
							String rejectedfilter = data.get(5);
							String  revokedfilter = data.get(6);
							
							
								MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		                            MeghPIShiftRequestPage meghpishiftrequestpage = new MeghPIShiftRequestPage(driver);

							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
					
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
							if (RolePermissionpage.HrAccountShiftRequestTab()) {

								logResults.createLogs("Y", "PASS", "HrAccountShiftRequestTab Displayed Successfully In HR Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Displaying HrAccountShiftRequestTab In Attendance Module."
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
						
							if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnAdminAccountForFilter(regularizationstatus, empid)) {

								logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Approved Summary Validated Successfully."  +" and status is " + regularizationstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Shift Request Approved Summary Count And Record." + meghpishiftrequestpage.getExceptionDesc());
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
							if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnAdminAccountForFilter(pendingfilter, empid)) {

								logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Pending Summary Validated Successfully."  +" and status is " + pendingfilter);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Shift Request Pending Summary Count And Record." + meghpishiftrequestpage.getExceptionDesc());
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
							if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnAdminAccountForFilter(rejectedfilter, empid)) {

								logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Rejected Summary Validated Successfully."  +" and status is " + rejectedfilter);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Shift Request Rejected Summary Count And Record." + meghpishiftrequestpage.getExceptionDesc());
							}
							if (meghregularizationrequestpage.FilterButtonOnRegularizationRequestTab()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked On Admin Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter  Button." + meghregularizationrequestpage.getExceptionDesc());
							}
							if (meghregularizationrequestpage.SelectFilterOption(revokedfilter)) {

								logResults.createLogs("Y", "PASS", "Revoked Option Selected From Filter Successfully." + revokedfilter);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting  Revoked Option From Filter DropDown." + meghregularizationrequestpage.getExceptionDesc());
							}
							
							if (meghregularizationrequestpage.FilterApplyButtonOnRegularizationRequestTab()) {

								logResults.createLogs("Y", "PASS", "Filter Apply Button Clicked On Admin Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Apply Button." + meghregularizationrequestpage.getExceptionDesc());
							}
							if (meghpishiftrequestpage.ValidateEmpShiftRequestStatusOnAdminAccountForFilter(revokedfilter, empid)) {

								logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Revoked Summary Validated Successfully."  +" and status is " + revokedfilter);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Shift Request Revoked Summary Count And Record." + meghpishiftrequestpage.getExceptionDesc());
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
