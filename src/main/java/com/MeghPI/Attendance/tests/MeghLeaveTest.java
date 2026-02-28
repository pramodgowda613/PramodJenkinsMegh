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
import com.MeghPI.Attendance.pages.MeghMasterDepartmentMappingPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterLeaveTypePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamMappingPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationRequestPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghWeekOffPolicyPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;


public class MeghLeaveTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	

	private String cmpcode = "";
	private String Emailid = "";

	private String officename = "";
	private String departmentname = "";
	private String teamname = "";
 
    private String AdminFirstName ="";
	  private String entityname = "";
	  private String AttendanceEmpid = "";
	  private String designationname= "";
	  private String AdminEmpID = "";	
	
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
		designationname = "AutoDESN" +  initBase.executionRunTime;
		AdminEmpID = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminEmpID", "");
	
		
	}
	
	
	// MPI_1692_Leave_32
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_1692_Leave_32()   {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, as a employee apply casual leave and ensure leave request is created and displayed in admin account.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
				"password,baseuri,loginendpoint,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,photo,captcha,leavereason,leavestatus");


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
	        String captcha = data.get(i++);
			
			String leavereason = data.get(i++);
			String leavestatus = data.get(i++);
	   
	        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
	
	        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

	        String month1WorkingDate  = dateDetails.get("month1WorkingDate");
	        String month2WorkingDate  = dateDetails.get("month2WorkingDate");

	        String firstDayLastMonth = dateDetails.get("firstDayOfMonth");
		  
		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
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
	            empid, firstDayLastMonth + "T00:00:00.000Z"
	    );

	    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
	        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + empid + firstDayLastMonth);
	    } else {
	        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
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
		
		if (meghleavepage.FromDateSelected(month1WorkingDate)) {

			logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + month1WorkingDate);
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
		
		if (meghleavepage.ToDateSelected(month1WorkingDate)) {

			logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + month1WorkingDate);
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
		//2nd leave
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
		
		if (meghleavepage.FromDateSelected(month2WorkingDate)) {

			logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + month2WorkingDate);
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
		
		if (meghleavepage.ToDateSelected(month2WorkingDate)) {

			logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + month2WorkingDate);
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
		

		if (meghleavepage.MonthFilterContains(month2WorkingDate)) {

			logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month2WorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
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
		if (meghleavepage.LeaveValidationInEmp(month1WorkingDate, leavestatus)) {

			logResults.createLogs("Y", "PASS", "Monthly Pending Leave Status Validated  Successfully ." + month1WorkingDate +  " and status is " + leavestatus );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Pending Leave Status."
					+ meghleavepage.getExceptionDesc());
		} 
	}
	
	
	// MPI_619_Leave_21
			@Test(enabled = true, priority = 2, groups = { "Smoke" })
			public void MPI_619_Leave_21()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionality fo \"reject all\" and ensure all the page leave requests are rejected successfully.");

				ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
						"password,captcha,rejectreason,filterleavetype,empid,leavestatus");

			
				String password = data.get(0);
				String captcha = data.get(1);
				String rejectreason = data.get(2);
				String leavetype = data.get(3);
				String empid = data.get(4);
				String leavestatus = data.get(5);
				
				 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

			        String month1WorkingDate  = dateDetails.get("month1WorkingDate");
			        String month2WorkingDate  = dateDetails.get("month2WorkingDate");


				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
				MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghLeavePage meghleavepage = new MeghLeavePage(driver);
			MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

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
			if (meghleavepage.MonthFilterContains(month1WorkingDate)) {

				logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month1WorkingDate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
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
			
			if (meghregularizationrequestpage.RejectAllButton()) {

				logResults.createLogs("Y", "PASS", "WeekOff request RejectAll Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOff Request RejectAll Button." + meghregularizationrequestpage.getExceptionDesc());
			}
			if (meghregularizationrequestpage.RejectReasonTextField(rejectreason)) {

				logResults.createLogs("Y", "PASS", "WeekOff request Reject Reason Inputted Successfully." + rejectreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOff Request Reject Reason." + meghregularizationrequestpage.getExceptionDesc());
			}
			if (meghregularizationrequestpage.RejectAllConfirmButton()) {

				logResults.createLogs("Y", "PASS", "WeekOff request RejectAll Confirm Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOff Requests RejectAll Confirm Button." + meghregularizationrequestpage.getExceptionDesc());
			}
			if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

				logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
						+ meghleavepage.getExceptionDesc());
			}
			if (meghleavepage.RejectSummaryCount()) {

				logResults.createLogs("Y", "PASS", "Monthly Rejected Leave Count Clicked  Successfully ." );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Rejected Leave Count."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.LeaveValidationInAdmin(month1WorkingDate, leavestatus, empid, leavetype )) {

				logResults.createLogs("Y", "PASS", "Rejected Leave Validated Successfully ." + month1WorkingDate + " Leave Status is " +  leavestatus + " for the empid " + empid + " and leave type is "  + leavetype);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Rejected Leave Status."
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
				if (meghleavepage.MonthFilterContains(month2WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month2WorkingDate);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.RejectSummaryCount()) {

					logResults.createLogs("Y", "PASS", "Monthly Rejected Leave Count Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Rejected Leave Count."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.LeaveValidationInEmp(month1WorkingDate, leavestatus)) {

					logResults.createLogs("Y", "PASS", "Monthly Rejected Leave Status Validated  Successfully ." + month1WorkingDate +  " and status is " + leavestatus );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Rejected Leave Status."
							+ meghleavepage.getExceptionDesc());
				} 
				if (meghleavepage.LeaveValidationInEmp(month2WorkingDate, leavestatus)) {

					logResults.createLogs("Y", "PASS", "Monthly Rejected Leave Status Validated  Successfully ." + month2WorkingDate +  " and status is " + leavestatus );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Rejected Leave Status."
							+ meghleavepage.getExceptionDesc());
				}	
			}
			
			
	
			// MPI_620_Leave_22
						@Test(enabled = true, priority = 3, groups = { "Smoke" })
						public void MPI_620_Leave_22()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of \"approve all\" button by clicking on that button ensure all the requests are approved .");

							ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
									"password,captcha,filterleavetype,empid,leavestatus,leavereason");

						
							String password = data.get(0);
							String captcha = data.get(1);
							String leavetype = data.get(2);
							String empid = data.get(3);
							String leavestatus = data.get(4);
							String leavereason = data.get(5);
							
							 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

						        String month1WorkingDate  = dateDetails.get("month1WorkingDate");
						        String month2WorkingDate  = dateDetails.get("month2WorkingDate");


							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
							MeghLoginTest meghlogintest = new MeghLoginTest();
						MeghLeavePage meghleavepage = new MeghLeavePage(driver);

						
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
						
						if (meghleavepage.FromDateSelected(month1WorkingDate)) {

							logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + month1WorkingDate);
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
						
						if (meghleavepage.ToDateSelected(month1WorkingDate)) {

							logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + month1WorkingDate);
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
						//2nd leave
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
						
						if (meghleavepage.FromDateSelected(month2WorkingDate)) {

							logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + month2WorkingDate);
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
						
						if (meghleavepage.ToDateSelected(month2WorkingDate)) {

							logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + month2WorkingDate);
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
						if (meghleavepage.MonthFilterContains(month1WorkingDate)) {

							logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month1WorkingDate );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
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
						
						if (meghleavepage.ApproveAllButtonOnAdmin()) {

							logResults.createLogs("Y", "PASS", "Approve All Button Clicked On Admin Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Approve All Button." + meghleavepage.getExceptionDesc());
						}
						if (meghleavepage.ApproveAllConfirmButtonOnAdmin()) {

							logResults.createLogs("Y", "PASS", "Approve All Confirm Button Clicked On Admin Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Approve All Confirm Button." + meghleavepage.getExceptionDesc());
						} 
				
						if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

							logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
									+ meghleavepage.getExceptionDesc());
						}
						if (meghleavepage.ApproveSummaryCount()) {

							logResults.createLogs("Y", "PASS", "Monthly Approved Leave Count Clicked  Successfully ." );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Approved Leave Count."
									+ meghleavepage.getExceptionDesc());
						}
						
						if (meghleavepage.LeaveValidationInAdmin(month1WorkingDate, leavestatus, empid, leavetype )) {

							logResults.createLogs("Y", "PASS", "Approved Leave Validated Successfully ." + month1WorkingDate + " Leave Status is " +  leavestatus + " for the empid " + empid + " and leave type is "  + leavetype);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Validating Approved Leave Status."
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
							if (meghleavepage.MonthFilterContains(month2WorkingDate)) {

								logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month2WorkingDate);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
										+ meghleavepage.getExceptionDesc());
							}
							if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

								logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghleavepage.ApproveSummaryCount()) {

								logResults.createLogs("Y", "PASS", "Monthly Approved Leave Count Clicked  Successfully ." );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Approved Leave Count."
										+ meghleavepage.getExceptionDesc());
							}
							if (meghleavepage.LeaveValidationInEmp(month1WorkingDate, leavestatus)) {

								logResults.createLogs("Y", "PASS", "Monthly Approved Leave Status Validated  Successfully ." + month1WorkingDate +  " and status is " + leavestatus );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Validating Approved Leave Status."
										+ meghleavepage.getExceptionDesc());
							} 
							if (meghleavepage.LeaveValidationInEmp(month2WorkingDate, leavestatus)) {

								logResults.createLogs("Y", "PASS", "Monthly Approved Leave Status Validated  Successfully ." + month2WorkingDate +  " and status is " + leavestatus );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Validating Approved Leave Status."
										+ meghleavepage.getExceptionDesc());
							}
						}
	
			
			
			
			
			
			
			
			
			
			
	
	
	
	
	
	
	
	
	
	
	
	
	// MPI_612_Leave_14
		@Test(enabled = true, priority = 4, groups = { "Smoke" })
		public void MPI_612_Leave_14()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, configure the sick leave on leave policy with 2 per month from joining date and as a employee apply sick leave and ensure status is pending ");

			ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
					"password,captcha,leavereason,empid,leavename,leavestatus,filterleavetype");

		
			String password = data.get(0);
			String captcha = data.get(1);
			String LeaveReason = data.get(2);
			String empid = data.get(3);
			String leavename = data.get(4);
			String leavestatus = data.get(5);
			String leavetype = data.get(6);
			
			Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
			String month3WorkingDate  = dateDetails.get("month3WorkingDate");


			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);
		
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
			
			if (meghleavepage.FromDateSelected(month3WorkingDate)) {

				logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + month3WorkingDate);
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
			
			if (meghleavepage.ToDateSelected(month3WorkingDate)) {

				logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + month3WorkingDate);
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
			
			if (meghleavepage.ReasonTextField(LeaveReason)) {

				logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + LeaveReason);
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
			
			if (meghleavepage.MonthFilterContains(month3WorkingDate)) {

				logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month3WorkingDate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.LeaveModulePaginationInEmp()) {

				logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully Of Employee Account." );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination."
						+ meghleavepage.getExceptionDesc());
			}
		
			if (meghleavepage.LeaveSearchTextField(leavename)) {

				logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully ." + leavename);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName."
						+ meghleavepage.getExceptionDesc());
			}
			
			
	
			if (meghleavepage.verifyLeaveRowInEmp( leavename, month3WorkingDate, leavestatus)) {

				logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully ." + leavename + month3WorkingDate + leavestatus);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status."
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
			if (meghleavepage.MonthFilterContains(month3WorkingDate)) {

				logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month3WorkingDate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
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
			
			if (meghleavepage.LeaveValidationInAdmin(month3WorkingDate, leavestatus, empid, leavetype )) {

				logResults.createLogs("Y", "PASS", "Rejected Leave Validated Successfully ." + month3WorkingDate + " Leave Status is " +  leavestatus + " for the empid " + empid + " and leave type is "  + leavetype);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Rejected Leave Status."
						+ meghleavepage.getExceptionDesc());
			}
		}
		
		// MPI_852_Leave_28
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_852_Leave_28()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, revoke the leave request and ensure status is updated as \"revoked\" on both emp and admin side");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,empid,leavename,leavestatus");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String empid = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					String month4WorkingDate  = dateDetails.get("month4WorkingDate");


					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
			
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
			
					if (meghleavepage.CasualLeaveSelected()) {

						logResults.createLogs("Y", "PASS", " Casual Selected  Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Casual Leave."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.FromDateTextField()) {

						logResults.createLogs("Y", "PASS", " FromDateTextField Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On FromDateTextField."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.FromDateSelected(month4WorkingDate)) {

						logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + month4WorkingDate);
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
					
					if (meghleavepage.ToDateSelected(month4WorkingDate)) {

						logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + month4WorkingDate);
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
					
					if (meghleavepage.ReasonTextField(LeaveReason)) {

						logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + LeaveReason);
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
					if (meghleavepage.MonthFilterContains(month4WorkingDate)) {

						logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month4WorkingDate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
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
				
					
					if (meghleavepage.clickRevokeButtonByDate(month4WorkingDate)) {

						logResults.createLogs("Y", "PASS", " Revoke Button Clicked  Successfully ." + month4WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Revoke Button ."
								+ meghleavepage.getExceptionDesc());
					}
					
					
					if (meghleavepage.RevokeButton()) {

						logResults.createLogs("Y", "PASS", " RevokeButton Clicked Successfully Of Employee Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On RevokeButton."
								+ meghleavepage.getExceptionDesc());
					} 
					if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

						logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.RevokedSummaryCount()) {

						logResults.createLogs("Y", "PASS", "Monthly Revoked Leave Count Clicked  Successfully ." );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Revoked Leave Count."
								+ meghleavepage.getExceptionDesc());
					}
				
			
					if (meghleavepage.verifyLeaveRowInEmp( leavename, month4WorkingDate, leavestatus)) {

						logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully ." + leavename + month4WorkingDate + leavestatus);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status."
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
					if (meghleavepage.MonthFilterContains(month4WorkingDate)) {

						logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month4WorkingDate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

						logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghleavepage.RevokedSummaryCount()) {

						logResults.createLogs("Y", "PASS", "Monthly Revoked Leave Count Clicked  Successfully ." );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Revoked Leave Count."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveValidationInAdmin(month4WorkingDate, leavestatus, empid, leavename )) {

						logResults.createLogs("Y", "PASS", "Rejected Leave Validated Successfully ." + month4WorkingDate + " Leave Status is " +  leavestatus + " for the empid " + empid + " and leave type is "  + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Validating Rejected Leave Status."
								+ meghleavepage.getExceptionDesc());
					}
				}
	
		// MPI_851_Leave_27
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_851_Leave_27()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, reject employee's leave request and check the status isÂ  \"rejected\" ");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,empid,leavename,leavestatus");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String empid = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					String month5WorkingDate  = dateDetails.get("month5WorkingDate");
					

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
					
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
					
					if (meghleavepage.FromDateSelected(month5WorkingDate)) {

						logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + month5WorkingDate );
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
					
					if (meghleavepage.ToDateSelected(month5WorkingDate)) {

						logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + month5WorkingDate);
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
					
					if (meghleavepage.ReasonTextField(LeaveReason)) {

						logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + LeaveReason);
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
					if (meghleavepage.MonthFilterContains(month5WorkingDate)) {

						logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month5WorkingDate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
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
			
					if (meghleavepage.clickRejectButtonByEmpAndDate(empid, month5WorkingDate)) {

						logResults.createLogs("Y", "PASS", " Leave Rejected   Successfully ." + month5WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Rejecting Leave."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveRejectionReason(LeaveReason)) {

						logResults.createLogs("Y", "PASS", " Leave Rejection Reason Inputted  Successfully ." + LeaveReason);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Rejection Reason."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveRejectButton()) {

						logResults.createLogs("Y", "PASS", " Leave Rejection  Button Clicked  Successfully ." + LeaveReason);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Reject Button."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(month5WorkingDate)) {

						logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month5WorkingDate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

						logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghleavepage.RejectSummaryCount()) {

						logResults.createLogs("Y", "PASS", "Monthly Rejected Leave Count Clicked  Successfully ." );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Rejected Leave Count."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghleavepage.LeaveValidationInAdmin(month5WorkingDate, leavestatus, empid, leavename )) {

						logResults.createLogs("Y", "PASS", "Rejected Leave Validated Successfully ." + month5WorkingDate + " Leave Status is " +  leavestatus + " for the empid " + empid + " and leave type is "  + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Validating Rejected Leave Status."
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
					if (meghleavepage.MonthFilterContains(month5WorkingDate)) {

						logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month5WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

						logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.RejectSummaryCount()) {

						logResults.createLogs("Y", "PASS", "Monthly Rejected Leave Count Clicked  Successfully ." );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Rejected Leave Count."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghleavepage.LeaveValidationInEmp(month5WorkingDate, leavestatus)) {

						logResults.createLogs("Y", "PASS", "Monthly Rejected Leave Status Validated  Successfully ." + month5WorkingDate +  " and status is " + leavestatus );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Validating Rejected Leave Status."
								+ meghleavepage.getExceptionDesc());
					} 
					
					
			

					
				}
	
	
				// MPI_854_Leave_30
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_854_Leave_30()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, approve the leave request and ensure status is \"aproved\" on both admin and employee account  ");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,empid,leavename,leavestatus");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String empid = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					String month6WorkingDate  = dateDetails.get("month6WorkingDate");


					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
					
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
				
				if (meghleavepage.FromDateSelected(month6WorkingDate)) {

					logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + month6WorkingDate);
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
				
				if (meghleavepage.ToDateSelected(month6WorkingDate)) {

					logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + month6WorkingDate);
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
				
				if (meghleavepage.ReasonTextField(LeaveReason)) {

					logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + LeaveReason);
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
				if (meghleavepage.MonthFilterContains(month6WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month6WorkingDate);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
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
				
				if (meghleavepage.approveLeaveByDateAndEmp(empid, month6WorkingDate)) {

					logResults.createLogs("Y", "PASS", " Leave Approved   Successfully ." + month6WorkingDate);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Aprroving Leave."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveApproveButton()) {

					logResults.createLogs("Y", "PASS", " Leave Approve  Button Clicked  Successfully ." + LeaveReason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Approve Button."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.MonthFilterContains(month6WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month6WorkingDate);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.ApproveSummaryCount()) {

					logResults.createLogs("Y", "PASS", "Monthly Approved Leave Count Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Approved Leave Count."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.LeaveValidationInAdmin(month6WorkingDate, leavestatus, empid, leavename )) {

					logResults.createLogs("Y", "PASS", "Rejected Leave Validated Successfully ." + month6WorkingDate + " Leave Status is " +  leavestatus + " for the empid " + empid + " and leave type is "  + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Rejected Leave Status."
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
				if (meghleavepage.MonthFilterContains(month6WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month6WorkingDate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.ApproveSummaryCount()) {

					logResults.createLogs("Y", "PASS", "Monthly Approved Leave Count Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Approved Leave Count."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveValidationInEmp( month6WorkingDate, leavestatus)) {

					logResults.createLogs("Y", "PASS", " Applied Leave Status Validated  Successfully ." + month6WorkingDate + " and status is " + leavestatus);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Applied Leave and Its status."
							+ meghleavepage.getExceptionDesc());
				}
				}
	
	
				// MPI_618_Leave_20
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_618_Leave_20()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise a leave request for an employee from the admin account and ensure the leave is approved automatically, since the reporting person is the same admin who raised the request.");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,firstname,leavename,leavestatus,empid");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String firstname = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);
					String empid = data.get(6);
					
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					String month7WorkingDate  = dateDetails.get("month7WorkingDate");


					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
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
				
				if (meghleavepage.FromDateOnAdminSelected(month7WorkingDate)) {

					logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully ." + month7WorkingDate);
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
				
				if (meghleavepage.ToDateTextFieldInAdminSelected(month7WorkingDate)) {

					logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully ." + month7WorkingDate);
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
				if (meghleavepage.MonthFilterContains(month7WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month7WorkingDate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.ApproveSummaryCount()) {

					logResults.createLogs("Y", "PASS", "Monthly Approved Leave Count Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Approved Leave Count."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveValidationInAdmin(month7WorkingDate, leavestatus, empid, leavename )) {

					logResults.createLogs("Y", "PASS", "Approved Leave Validated Successfully ." + month7WorkingDate + " Leave Status is " +  leavestatus + " for the empid " + empid + " and leave type is "  + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Approved Leave Status."
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
				if (meghleavepage.MonthFilterContains(month7WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month7WorkingDate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.ApproveSummaryCount()) {

					logResults.createLogs("Y", "PASS", "Monthly Approved Leave Count Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Approved Leave Count."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.LeaveValidationInEmp(month7WorkingDate, leavestatus)) {

					logResults.createLogs("Y", "PASS", "Monthly Approved Leave Status Validated  Successfully ." + month7WorkingDate +  " and status is " + leavestatus );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Approved Leave Status."
							+ meghleavepage.getExceptionDesc());
				} 
				
		
				}
				
				
				
				// MPI_625_Leave_26
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_625_Leave_26()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, as a admin apply leave for himself and ensure leave is approved and leave is displaying for selected date on employee module");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,leavename,leavestatus,leaveduration,date");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String leavename = data.get(3);
					String leavestatus = data.get(4);
					String LeaveDuration = data.get(5);
					String date = data.get(6);

					
					  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

				        String month1WorkingDate  = dateDetails.get("month1WorkingDate");
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
						MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
						MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghLoginTest meghlogintest = new MeghLoginTest();

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

				if (EmployeePage.EmployeeTab()) {
					logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On EmployeeTab." + EmployeePage.getExceptionDesc());
				}

			
				if (EmployeePage.SearchTextField(AdminFirstName)) {
					logResults.createLogs("Y", "PASS",
							"Clicked On Search Text Field And Pass The Created Employee LastName Successfully." + AdminFirstName);
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
				if (DepartmentPage.ManageProfileDateField()) {
					logResults.createLogs("Y", "PASS", "Successfully Clicked On Date TextField.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error while Clicking On Date TextField ."
							+ DepartmentPage.getExceptionDesc());
				}
				if (DepartmentPage.FirstDateSelection(date)) {
					logResults.createLogs("Y", "PASS", "Successfully Input The Joining Date." + date);
				} else {
					logResults.createLogs("Y", "FAIL", "Error while Inputting The Joining Date ."
							+ DepartmentPage.getExceptionDesc());
				}
				
			

				if (RolePermissionpage.SaveChanges()) {
					logResults.createLogs("Y", "PASS", "SaveChanges Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SaveChanges Button." + RolePermissionpage.getExceptionDesc());
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
				
				if (meghleavepage.ApplyForMeButton()) {

					logResults.createLogs("Y", "PASS", "  Leave For Me Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave For Me Button."
							+ meghleavepage.getExceptionDesc());
				}	
			
				
				if (meghleavepage.LeaveTypeSelectionDropDownOfLeaveForMe()) {

					logResults.createLogs("Y", "PASS", "  LeaveType DropDown OnAdmin Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking  LeaveType DropDown OnAdmin."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveTypeNameTextFieldOfLeaveForMe(leavename)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully ." + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Name."
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveTypeSelectedOfLeaveForMe()) {

					logResults.createLogs("Y", "PASS", "  Leave Type Selected  Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type From DropDown."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromDateClickedLeaveForMe()) {

					logResults.createLogs("Y", "PASS", " Clicked On FromDate TextField In Admin  Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On From Date TextField."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromDateSelectedLeaveForMe(month1WorkingDate)) {

					logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully ." + month1WorkingDate);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting  From Date in TextField."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromLeaveDurationSelectedOfLeaveForMe(LeaveDuration)) {

					logResults.createLogs("Y", "PASS", " Clicked On FromLeaveDuration Dropdown In Admin  Successfully ." + LeaveDuration);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On FromLeave Duration Dropdown."
							+ meghleavepage.getExceptionDesc());
				}
				
				
				
				if (meghleavepage.ToDateClickedLeaveForMe()) {

					logResults.createLogs("Y", "PASS", " Clicked On ToDate TextField In Admin  Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On To Date TextField."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToDateSelectedLeaveForMe(month1WorkingDate)) {

					logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully ." + month1WorkingDate);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting  TO Date in TextField."
							+ meghleavepage.getExceptionDesc());
				}
				
				
				
				if (meghleavepage.ToLeaveDurationSelectedOfLeaveForMe(LeaveDuration)) {

					logResults.createLogs("Y", "PASS", " Selected  ToLeaveDuration From Dropdown In Admin  Successfully ." + LeaveDuration);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting  ToLeave Duration From Dropdown."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveReasonOfLeaveForMe(LeaveReason)) {

					logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + LeaveReason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Reason."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ApplyButtonOfLeaveForMe()) {

					logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (RolePermissionpage.EmployeeToggleSwitch()) {

					logResults.createLogs("Y", "PASS", "Employee Toggle Switch Successfully To Switch To Employee Account.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Toggle Switch." + RolePermissionpage.getExceptionDesc());
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
				if (meghleavepage.MonthFilterContains(month1WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month1WorkingDate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.ApproveSummaryCount()) {

					logResults.createLogs("Y", "PASS", "Monthly Approved Leave Count Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Approved Leave Count."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.LeaveValidationInEmp(month1WorkingDate, leavestatus)) {

					logResults.createLogs("Y", "PASS", "Monthly Approved Leave Status Validated  Successfully ." + month1WorkingDate +  " and status is " + leavestatus );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Approved Leave Status."
							+ meghleavepage.getExceptionDesc());
				} 
				
				}	
				
				
				// MPI_599_Leave_01
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_599_Leave_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature by for each search option in admin and employee account");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,leavename,empid,rejectreason,leavestatus");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
		
					String leavename = data.get(3);
					String empid = data.get(4);
					String rejectreason = data.get(5);
					String leavestatus = data.get(6);

					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					String month1WorkingDate  = dateDetails.get("month1WorkingDate");

					

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
						MeghLoginTest meghlogintest = new MeghLoginTest();

				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
	
				
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
				if (meghleavepage.MonthFilterContains(month1WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month1WorkingDate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (EmployeePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown." + EmployeePage.getExceptionDesc());
				}
				
				
				if (meghleavepage.EmpIDCheckBoxSearchOption()) {

					logResults.createLogs("Y", "PASS", " Emp Id  Option Selected  Successfully ." + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp ID  Option From Search Dropdown."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ReasonCheckBoxSearchOption()) {

					logResults.createLogs("Y", "PASS", "Reason CheckBox  Option Selected  Successfully ." + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Reason  Option From Search Dropdown."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (EmployeePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown." + EmployeePage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveSearchTextField(empid)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully ." + empid);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.EmpIDInTable(empid)) {

					logResults.createLogs("Y", "PASS", " Based On Emp Id Search Result Displayed  Successfully ." + empid);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Search Result For Emp Id."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.LeaveSearchTextField(LeaveReason)) {

					logResults.createLogs("Y", "PASS", " Leave Reason Inputted  Successfully ." + LeaveReason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Reason."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ReasonInTable(LeaveReason)) {

					logResults.createLogs("Y", "PASS", " Based On Leave Reason Search Result Displayed  Successfully ." + LeaveReason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Search Result For Leave Reason."
							+ meghleavepage.getExceptionDesc());
				}	
				if (EmployeePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown." + EmployeePage.getExceptionDesc());
				}
		
				if (meghleavepage.EmpIDCheckBoxSearchOption()) {

					logResults.createLogs("Y", "PASS", " Emp Id  Option Selected  Successfully ." + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp ID  Option From Search Dropdown."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ReasonCheckBoxSearchOption()) {

					logResults.createLogs("Y", "PASS", "Reason CheckBox  Option Selected  Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Reason  Option From Search Dropdown."
							+ meghleavepage.getExceptionDesc());
				}
			
				if (meghleavepage.LeaveTypeSearchOption()) {

					logResults.createLogs("Y", "PASS", "Leave Type CheckBox  Option Selected  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type  Option From Search Dropdown."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.LeaveRemarksSearchOption()) {

					logResults.createLogs("Y", "PASS", "Leave Reason Remarks CheckBox  Option Selected  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Reason Remarks  Option From Search Dropdown."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (EmployeePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown." + EmployeePage.getExceptionDesc());
				}
				if (meghleavepage.LeaveSearchTextField(leavename)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully ." + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Name."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveNameInTable(leavename)) {

					logResults.createLogs("Y", "PASS", " Based On Leave Name Search Result Displayed  Successfully ." + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Search Result For Leave Name."
							+ meghleavepage.getExceptionDesc());
				}	
				if (meghleavepage.LeaveSearchTextField(rejectreason)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully ." + rejectreason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Name."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveRejectReasonInTable(rejectreason)) {

					logResults.createLogs("Y", "PASS", " Based On Leave Reject Reason Search Result Displayed  Successfully ." + rejectreason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Search Result For Leave Reject Reason."
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
					if (meghleavepage.MonthFilterContains(month1WorkingDate)) {

						logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month1WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (EmployeePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown." + EmployeePage.getExceptionDesc());
					}
			
					if (meghleavepage.LeaveStatusInTable()) {

						logResults.createLogs("Y", "PASS", " Emp Leave Status  Option Selected  Successfully ." + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Leave Status  Option From Search Dropdown."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.ReasonCheckBoxSearchOption()) {

						logResults.createLogs("Y", "PASS", "Reason CheckBox  Option Selected  Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Reason  Option From Search Dropdown."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (EmployeePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown." + EmployeePage.getExceptionDesc());
					}
					if (meghleavepage.LeaveSearchTextField(LeaveReason)) {

						logResults.createLogs("Y", "PASS", " Leave Reason Inputted  Successfully ." + LeaveReason);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Reason."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghleavepage.LeaveReasonInEmpTable(LeaveReason)) {

						logResults.createLogs("Y", "PASS", " Based On Leave  Reason Search Result Displayed  Successfully ." + LeaveReason);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Displaying Search Result For Leave Reason."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghleavepage.LeaveSearchTextField(leavestatus)) {

						logResults.createLogs("Y", "PASS", " Leave Status Inputted  Successfully ." + leavestatus);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Status."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghleavepage.LeaveStatusInEmpTable(leavestatus)) {

						logResults.createLogs("Y", "PASS", " Based On Leave  Status Search Result Displayed  Successfully ." + leavestatus);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Displaying Search Result For Leave Status."
								+ meghleavepage.getExceptionDesc());
					}
	
				}
				
				
				
				
				
				// MPI_621_Leave_23
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_621_Leave_23()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by filtering office name, dept, team, designation, employee type and leave types");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavename,leavestatus,filterleavetype,leavereason");

				
					String password = data.get(0);
					String captcha = data.get(1);
		
					String leavename = data.get(2);
										String LeaveReason = data.get(3);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					String month13WorkingDate  = dateDetails.get("month13WorkingDate");

					MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
				
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
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
				
				if (meghleavepage.EmployeeInputField(AttendanceEmpid)) {

					logResults.createLogs("Y", "PASS", " Emp Name Inputted  Successfully ." + AttendanceEmpid);
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
				
				if (meghleavepage.FromDateOnAdminSelected(month13WorkingDate)) {

					logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully ." + month13WorkingDate);
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
				
				if (meghleavepage.ToDateTextFieldInAdminSelected(month13WorkingDate)) {

					logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully ." + month13WorkingDate);
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
				
				if (meghleavepage.MonthFilterContains(month13WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month13WorkingDate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
		
				
				if (meghleavepage.FilterButton()) {

					logResults.createLogs("Y", "PASS", " Filter Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button."
							+ meghleavepage.getExceptionDesc());
				}	
				
				
				
				if (meghleavepage.OfficeDrpDownClicked()) {

					logResults.createLogs("Y", "PASS", " Office DrpDown Clicked Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Office DropDown."
							+ meghleavepage.getExceptionDesc());
				}	

				if (meghleavepage.OfficeNameInputted(officename)) {

					logResults.createLogs("Y", "PASS", " Office Name Inputted Successfully ." + officename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Office Name."
							+ meghleavepage.getExceptionDesc());
				}		

				if (meghleavepage.OfficeNameSelectedOfFilter()) {

					logResults.createLogs("Y", "PASS", " Office Name Selected Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting  Office Name."
							+ meghleavepage.getExceptionDesc());
				}	
				
				
				if (meghleavepage.DeptDropDownClicked()) {

					logResults.createLogs("Y", "PASS", " Dept DropDown Clicked   Successfully ." + departmentname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Dept DropDown."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.DeptDropDownSelected()) {

					logResults.createLogs("Y", "PASS", " Dept Selected Successfully ." + departmentname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Dept Name."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.TeamDropDownClicked()) {

					logResults.createLogs("Y", "PASS", " Team DropDown Clicked   Successfully ." + teamname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Team DropDown."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.TeamDropDownSelected()) {

					logResults.createLogs("Y", "PASS", " Team Selected Successfully ." + teamname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Team Name."
							+ meghleavepage.getExceptionDesc());
				}
				if (AttendancePolicyPage.DesignationDropdown(designationname)) {
					logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Selecting Designation From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
				}

				if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
					logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown." + entityname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Selecting Employee Type From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
				}
				
				
				if (meghleavepage.SaveFilterButton()) {

					logResults.createLogs("Y", "PASS", " Clicked On Save Filter Button Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button ."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.EmpIDInTable(AttendanceEmpid)) {

					logResults.createLogs("Y", "PASS", " Based On Emp Id Search Result Displayed  Successfully ." + AttendanceEmpid);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Search Result For Emp Id."
							+ meghleavepage.getExceptionDesc());
				}	
		
				}
				
				// MPI_603_Leave_05
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_603_Leave_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check whether the counts for 'Pending', 'Approved', 'Rejected', 'Revoked', and the total 'Applied Leaves' are displayed accurately according to the number of records.");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha");

				
					String password = data.get(0);
					String captcha = data.get(1);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					String month1WorkingDate  = dateDetails.get("month1WorkingDate");

				

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
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
				if (meghleavepage.MonthFilterContains(month1WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month1WorkingDate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
	
				if (meghleavepage.LeaveModulePaginationInAdmin()) {

					logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination."
							+ meghleavepage.getExceptionDesc());
				}
				
				
				
				if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.compareLeaveStatistics()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary Count Is Verified With No Of Records Present In Table  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Verifying  Monthly Leave Summary With Table."
							+ meghleavepage.getExceptionDesc());
				}
		
				}
				
				// MPI_856_Leave_31
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_856_Leave_31()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check whether the counts for 'Pending', 'Approved', 'Rejected', 'Revoked', and the total 'Applied Leaves' are displayed accurately according to the number of records in employee account");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"captcha,empid");

				
					String captcha = data.get(0);
					String empid = data.get(1);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					String month1WorkingDate  = dateDetails.get("month1WorkingDate");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				
					
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
				if (meghleavepage.MonthFilterContains(month1WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month1WorkingDate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.LeaveModulePaginationInEmp()) {

					logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully Of Employee Account." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination."
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.compareLeaveStatisticsOfEmp()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary Count Is Verified With No Of Records Present In Table  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Verifying  Monthly Leave Summary With Table."
							+ meghleavepage.getExceptionDesc());
				}

				}	
				
				
				
				// MPI_1695_Leave_33
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_1695_Leave_33()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the filter functionality for leave type and all statuses — Approved, Rejected, Pending, and Revoked — and ensure that the respective records are displayed.");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavename,filterleavetype,leavestatus,revokedfilter,rejectfilter,pendingfilter");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String leavename = data.get(2);
					String filterleavetype = data.get(3);
					String leavestatus = data.get(4);
					String revokedfilter = data.get(5);
					String rejectfilter = data.get(6);
					String pendingfilter = data.get(7);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					String month1WorkingDate  = dateDetails.get("month1WorkingDate");

				

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
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
				if (meghleavepage.MonthFilterContains(month1WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month1WorkingDate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.FilterButton()) {

					logResults.createLogs("Y", "PASS", " Filter Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button."
							+ meghleavepage.getExceptionDesc());
				}	
				
				
				if (meghleavepage.LeaveTypesFilter(leavename)) {

					logResults.createLogs("Y", "PASS", "  Leave Type Selected  Successfully ." + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type From DropDown."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.SaveFilterButton()) {

					logResults.createLogs("Y", "PASS", " Clicked On Save Filter Button Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button ."
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveTypesearchResults(filterleavetype)) {

					logResults.createLogs("Y", "PASS", "Applied Filted Results Displayed Successfully For Leave Name ." + filterleavetype);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Applied Filter Results For Leave Type."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.MonthFilterContains(month1WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month1WorkingDate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FilterButton()) {

					logResults.createLogs("Y", "PASS", " Filter Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveStatusFilter(leavestatus)) {

					logResults.createLogs("Y", "PASS", " Approved Leave Status Filter Applied Successfully ." + leavestatus);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Approve Leave Status Filter ."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.SaveFilterButton()) {

					logResults.createLogs("Y", "PASS", " Clicked On Save Filter Button Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button ."
							+ meghleavepage.getExceptionDesc());
				}
	
				if (meghleavepage.LeaveStatusSearchResults(leavestatus)) {

					logResults.createLogs("Y", "PASS", "Applied Approved Filted Results Displayed Successfully For Leave Status ." + leavestatus);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Applied Approved Leave  Filter Results ."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.FilterButton()) {

					logResults.createLogs("Y", "PASS", " Filter Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveStatusFilter(revokedfilter)) {

					logResults.createLogs("Y", "PASS", " Revoked Leave Status Filter Applied Successfully ." + revokedfilter);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Revoked Leave Status Filter ."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.SaveFilterButton()) {

					logResults.createLogs("Y", "PASS", " Clicked On Save Filter Button Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button ."
							+ meghleavepage.getExceptionDesc());
				}
	
				if (meghleavepage.LeaveStatusSearchResults(revokedfilter)) {

					logResults.createLogs("Y", "PASS", "Applied Revoked Filted Results Displayed Successfully For Leave Status ." + revokedfilter);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Applied Revoked Leave  Filter Results ."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.FilterButton()) {

					logResults.createLogs("Y", "PASS", " Filter Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveStatusFilter(rejectfilter)) {

					logResults.createLogs("Y", "PASS", " Rejected Leave Status Filter Applied Successfully ." + rejectfilter);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Rejected Leave Status Filter ."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.SaveFilterButton()) {

					logResults.createLogs("Y", "PASS", " Clicked On Save Filter Button Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button ."
							+ meghleavepage.getExceptionDesc());
				}
	
				if (meghleavepage.LeaveStatusSearchResults(rejectfilter)) {

					logResults.createLogs("Y", "PASS", "Applied Rejected Filted Results Displayed Successfully For Leave Status ." + rejectfilter);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Applied Rejected Leave  Filter Results ."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.FilterButton()) {

					logResults.createLogs("Y", "PASS", " Filter Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveStatusFilter(pendingfilter)) {

					logResults.createLogs("Y", "PASS", " Pending Leave Status Filter Applied Successfully ." + pendingfilter);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Pending Leave Status Filter ."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.SaveFilterButton()) {

					logResults.createLogs("Y", "PASS", " Clicked On Save Filter Button Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button ."
							+ meghleavepage.getExceptionDesc());
				}
	
				if (meghleavepage.LeaveStatusSearchResults(pendingfilter)) {

					logResults.createLogs("Y", "PASS", "Applied Pending Filted Results Displayed Successfully For Leave Status ." + pendingfilter);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Applied Pending Leave  Filter Results ."
							+ meghleavepage.getExceptionDesc());
				}
				}
				
				
				
		
				
				// MPI_1702_Leave_39
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_1702_Leave_39()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, create a new employee and assign the newly created leave policy that provides 2 leaves per month.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,baseuri,loginendpoint,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,photo");


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
				        
				   
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
				
				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();


				        String firstDayLastMonth = dateDetails.get("firstDayOfMonth");
					  
					MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
				

			     
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
				            empid, firstDayLastMonth + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + empid + firstDayLastMonth);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				
				}
				
				
				
				
				
				
				
				
				
				
				
				// MPI_600_Leave_02
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
			public void MPI_600_Leave_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, edit the leave policy and add only 2 sick  leave per month.");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,deptname,officename,state,teamname,pin,longitude,firstname,policyname,sickleave,travelleave,casualleave");

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
				        String sickleave = data.get(i++);
				        String travelleave = data.get(i++);
				        String casualleave = data.get(i++);
				      
				        
						MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
						MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
						MeghMasterLeaveTypePage LeaveTypePage = new MeghMasterLeaveTypePage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
					MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);
					MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
					MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
					MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);

					MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
					MeghLoginPage meghloginpage =  new MeghLoginPage(driver);

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

						if (LeaveTypePage.AddLeavePolicyButton()) {

							logResults.createLogs("Y", "PASS", "AddLeavePolicyButton Clicked Successfully .");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On AddLeavePolicyButton." + LeaveTypePage.getExceptionDesc());
						}

						if (LeavePolicyPage.LeavePolicyNameTextField(policyname)) {

							logResults.createLogs("Y", "PASS", "LeavePolicy Name Inputted Successfully." + policyname);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting LeavePolicy Name." + LeavePolicyPage.getExceptionDesc());
						}

						if (LeaveTypePage.SelectLeavePolicyButton()) {

							logResults.createLogs("Y", "PASS", "SelectLeavePolicyButton Clicked Successfully .");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On SelectLeavePolicyButton." + LeaveTypePage.getExceptionDesc());
						}

						if (LeavePolicyPage.AllLeaves(sickleave)) {

							logResults.createLogs("Y", "PASS", "SickLeave Selected Successfully." + sickleave);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Selecting Sick Leave." + LeavePolicyPage.getExceptionDesc());
						}
						

						if (LeavePolicyPage.AllLeaves2(travelleave)) {

							logResults.createLogs("Y", "PASS", "Travel Leave Selected Successfully." + travelleave);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Selecting Travel Leave." + LeavePolicyPage.getExceptionDesc());
						}

						if (LeavePolicyPage.AllLeaves3(casualleave)) {

							logResults.createLogs("Y", "PASS", "Casual Leave Selected Successfully." + casualleave);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Selecting Casual Leave." + LeavePolicyPage.getExceptionDesc());
						}
						

						if (LeavePolicyPage.AddLeaveSaveButton()) {

							logResults.createLogs("Y", "PASS", "AddLeaveSaveButton Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On AddLeaveSaveButton." + LeavePolicyPage.getExceptionDesc());
						}

						if (AttendancePolicyPage.AddEmpCriteria()) {

							logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully .");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On AddEmpCriteria Button ." + AttendancePolicyPage.getExceptionDesc());
						}

						if (LeavePolicyPage.OfficeDropDown(officeName)) {

							logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully ." + officeName);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Selecting Office Name ." + LeavePolicyPage.getExceptionDesc());
						}


						if (AttendancePolicyPage.DeptDropDownClick()) {
							logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown." + departmentname);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error while Clicking On Dept DropDown." + AttendancePolicyPage.getExceptionDesc());
						}
				
						if (LeavePolicyPage.DeptOptionSelected()) {
							logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + departmentname);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error while Selecting  Dept ." + LeavePolicyPage.getExceptionDesc());
						}
						
						if (AttendancePolicyPage.TeamDropDownClick()) {
							logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown." + teamname);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error while Clicking On Team  Dropdown ." + AttendancePolicyPage.getExceptionDesc());
						}
						
						if (WeekOffPolicyPage.TeamOptionSelected()) {
							logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error while Selecting Team From Dropdown ." + WeekOffPolicyPage.getExceptionDesc());
						}

						if (ShiftPolicyPage.ShiftAddEmpCriteriaApplyButton()) {

							logResults.createLogs("Y", "PASS", "Clicked On Apply Button Successfully .");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Apply Button ." + ShiftPolicyPage.getExceptionDesc());
						}

						if (LeavePolicyPage.CreateLeavePolicyButton()) {

							logResults.createLogs("Y", "PASS", "CreateLeavePolicyButton Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On CreateLeavePolicyButton." + LeavePolicyPage.getExceptionDesc());
						}

						if (AttendancePolicyPage.YesButton()) {
							
							logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
						}	
				}
				
				
				// MPI_1696_Leave_34
				@Test(enabled = true, priority = 17, groups = { "Smoke" })
				public void MPI_1696_Leave_34()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, assign the employee to a newly created leave policy that provides 2 sick leaves per month, and ensure the unused leaves are carried forward to the next month. Also verify that the employee can apply up to 4 sick leaves in a single month.");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,policyname,leaveduration,firstname,leavename,leavereason,leavestatus,empid");

					String password = data.get(0);
					String captcha = data.get(1);
					String policyname = data.get(2);
					String leaveduration = data.get(3);
					String firstname = data.get(4);
					String leavename = data.get(5);
					String leavereason  = data.get(6);
					String leavestatus = data.get(7);
					String empid = data.get(8);

					
					Map<String, String> dateDetails = Pramod.getLastMonthMonToFriWeek();

					String mondayDate = dateDetails.get("mondayDate");
					String fridayDate = dateDetails.get("fridayDate");

					
						MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);

				    	MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
				MeghLoginTest meghlogintest = new MeghLoginTest();

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

				if (LeavePolicyPage.LeavePolicySearchTextFields(policyname)) {

					logResults.createLogs("Y", "PASS", "Created Policy Name Inputted Successfully." + policyname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Leave Policy Name." + LeavePolicyPage.getExceptionDesc());
				}

				if (LeavePolicyPage.SearchInputtedPolicyvalidation(policyname)) {

					logResults.createLogs("Y", "PASS", "Created Policy Name Displayed Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Displaying Created Leave Policy Name." + LeavePolicyPage.getExceptionDesc());
				}

				if (LeavePolicyPage.LeavePolicyEditIcon()) {

					logResults.createLogs("Y", "PASS", "Created Policy Edit Icon Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Policy Edit Icon." + LeavePolicyPage.getExceptionDesc());
				}
				if (meghleavepage.EditLeaveBalanceForSickLeave()) {

					logResults.createLogs("Y", "PASS", "Sick Leave Edit Button Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Sick Leave Edit Button."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.FixedBalanceButton()) {

					logResults.createLogs("Y", "PASS", "Fixed Balance Button Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On   Fixed Balance Button."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.FixedBalanceConfirmButton()) {

					logResults.createLogs("Y", "PASS", "Fixed Balance Confirm Button Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On   Fixed Balance Confirm Button."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.NoOfCreditInput(leaveduration)) {

					logResults.createLogs("Y", "PASS", "Leave No Of Credit Inputted Successfully ." + leaveduration );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Credit Count."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.RepeatCreditSelected()) {

					logResults.createLogs("Y", "PASS", "Monthly Credit Selected Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Monthly Leave Credit."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.AllowhalfDayLeave()) {

					logResults.createLogs("Y", "PASS", "Allow HalfDay Leave Selected Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Allow HalfDay Leave."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.EditLeaveSaveButton()) {

					logResults.createLogs("Y", "PASS", "Edit Leave Save Button Clicked Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Edit Leave Save Button."
							+ meghleavepage.getExceptionDesc());
				}
				
			
				if (meghleavepage.EditLeaveBalanceForTravelLeave()) {

					logResults.createLogs("Y", "PASS", "Travel Leave Edit Button Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Travel Leave Edit Button."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.FixedBalanceButton()) {

					logResults.createLogs("Y", "PASS", "Fixed Balance Button Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On   Fixed Balance Button."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.FixedBalanceConfirmButton()) {

					logResults.createLogs("Y", "PASS", "Fixed Balance Confirm Button Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On   Fixed Balance Confirm Button."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.NoOfCreditInput(leaveduration)) {

					logResults.createLogs("Y", "PASS", "Leave No Of Credit Inputted Successfully ." + leaveduration );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Credit Count."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.RepeatCreditSelected()) {

					logResults.createLogs("Y", "PASS", "Monthly Credit Selected Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Monthly Leave Credit."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.AllowhalfDayLeave()) {

					logResults.createLogs("Y", "PASS", "Allow HalfDay Leave Selected Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Allow HalfDay Leave."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.EditLeaveSaveButton()) {

					logResults.createLogs("Y", "PASS", "Edit Leave Save Button Clicked Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Edit Leave Save Button."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.EditLeaveBalanceForCasualLeave()) {

					logResults.createLogs("Y", "PASS", "Travel Leave Edit Button Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Travel Leave Edit Button."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.FixedBalanceButton()) {

					logResults.createLogs("Y", "PASS", "Fixed Balance Button Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On   Fixed Balance Button."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.FixedBalanceConfirmButton()) {

					logResults.createLogs("Y", "PASS", "Fixed Balance Confirm Button Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On   Fixed Balance Confirm Button."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.NoOfCreditInput(leaveduration)) {

					logResults.createLogs("Y", "PASS", "Leave No Of Credit Inputted Successfully ." + leaveduration );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Credit Count."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.RepeatCreditSelected()) {

					logResults.createLogs("Y", "PASS", "Monthly Credit Selected Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Monthly Leave Credit."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.AllowhalfDayLeave()) {

					logResults.createLogs("Y", "PASS", "Allow HalfDay Leave Selected Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Allow HalfDay Leave."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.EditLeaveSaveButton()) {

					logResults.createLogs("Y", "PASS", "Edit Leave Save Button Clicked Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Edit Leave Save Button."
							+ meghleavepage.getExceptionDesc());
				}
				if (LeavePolicyPage.CreateLeavePolicyButton()) {

					logResults.createLogs("Y", "PASS", "CreateLeavePolicyButton Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On CreateLeavePolicyButton." + LeavePolicyPage.getExceptionDesc());
				}

				if (AttendancePolicyPage.YesButton()) {
					
					logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
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
				
				if (meghleavepage.FromDateOnAdminSelected(mondayDate)) {

					logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully ." + mondayDate);
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
				
				if (meghleavepage.ToDateTextFieldInAdminSelected(fridayDate)) {

					logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully ." + fridayDate);
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
				
				if (meghleavepage.ReasonTextField(leavereason)) {

					logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + leavereason);
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
				if (meghleavepage.MonthFilterContains(mondayDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + mondayDate );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghleavepage.ApproveSummaryCount()) {

					logResults.createLogs("Y", "PASS", "Monthly Approved Leave Count Clicked  Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Approved Leave Count."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveValidationInAdmin(mondayDate, leavestatus, empid, leavename )) {

					logResults.createLogs("Y", "PASS", "Approved Leave Validated Successfully ." + mondayDate + " Leave Status is " +  leavestatus + " for the empid " + empid + " and leave type is "  + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Approved Leave Status."
							+ meghleavepage.getExceptionDesc());
				}
				}	
				
				
				//MPI_1697_Leave_35
				@Test(enabled = true, priority = 18, groups = { "Smoke" })
				public void MPI_1697_Leave_35()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature in leave balance module and ensure criteria matching employee record is displayed.");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,officename,leavename,empid,leaveduration,casualleave,firstname");

				
					String password = data.get(0);
					String captcha = data.get(1);
		
					String officeName = data.get(2);
					String leavename = data.get(3);
					String empid = data.get(4); 
					String leaveduration = data.get(5); 
					String  casualleave = data.get(6);
					String firstname = data.get(7);
										
					
				

					MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
						MeghLoginTest meghlogintest = new MeghLoginTest();

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
		
				if (RolePermissionpage.LeaveBalanceTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveBalanceTab Clicked Successfully Of Employee Account.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveBalanceTab."
							+ RolePermissionpage.getExceptionDesc());
				}
				if (meghleavepage.FilterButton()) {

					logResults.createLogs("Y", "PASS", " Filter Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button."
							+ meghleavepage.getExceptionDesc());
				}	
				
				
				
				if (meghleavepage.OfficeDrpDownClicked()) {

					logResults.createLogs("Y", "PASS", " Office DrpDown Clicked Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Office DropDown."
							+ meghleavepage.getExceptionDesc());
				}	

				if (meghleavepage.OfficeNameInputted(officeName)) {

					logResults.createLogs("Y", "PASS", " Office Name Inputted Successfully ." + officename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Office Name."
							+ meghleavepage.getExceptionDesc());
				}		

				if (meghleavepage.OfficeNameSelectedOfFilter()) {

					logResults.createLogs("Y", "PASS", " Office Name Selected Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting  Office Name."
							+ meghleavepage.getExceptionDesc());
				}	
				
				
				if (meghleavepage.DeptDropDownClicked()) {

					logResults.createLogs("Y", "PASS", " Dept DropDown Clicked   Successfully ." + departmentname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Dept DropDown."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.DeptDropDownSelected()) {

					logResults.createLogs("Y", "PASS", " Dept Selected Successfully ." + departmentname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Dept Name."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.TeamDropDownClicked()) {

					logResults.createLogs("Y", "PASS", " Team DropDown Clicked   Successfully ." + teamname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Team DropDown."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.TeamDropDownSelected()) {

					logResults.createLogs("Y", "PASS", " Team Selected Successfully ." + teamname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Team Name."
							+ meghleavepage.getExceptionDesc());
				}
				if (AttendancePolicyPage.DesignationDropdown(designationname)) {
					logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Selecting Designation From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
				}

				if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
					logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown." + entityname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Selecting Employee Type From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
				}
				if (meghleavepage.LeaveTypesFilter(leavename)) {

					logResults.createLogs("Y", "PASS", "  Leave Type Selected  Successfully ." + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type From DropDown."
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.SaveFilterButton()) {

					logResults.createLogs("Y", "PASS", " Clicked On Save Filter Button Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button ."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveBalanceSearchTextField(firstname)) {

					logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully ." + firstname );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.validateLeaveBalance(empid,casualleave,leaveduration)) {

					logResults.createLogs("Y", "PASS", "Filter Applied Record Validated Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Filter Applied Row."
							+ meghleavepage.getExceptionDesc());
				}
				
				}
				
				//MPI_606_Leave_08
				@Test(enabled = true, priority = 19, groups = { "Smoke" })
				public void MPI_606_Leave_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of the filter feature on weekly leave calendar by selecting values for Office, Department, Designation, Teams, Employee Types, and Leave Type and ensure criteria matching employee record are displayed.");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavename,leaveduration,casualleave");

				
					String password = data.get(0);
					String captcha = data.get(1);
		
					String leavename = data.get(2);
					String leaveduration = data.get(3); 
					String  casualleave = data.get(4);
										
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

			        String month1WorkingDate  = dateDetails.get("month1WorkingDate");
				

					MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
						MeghLoginTest meghlogintest = new MeghLoginTest();

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
		
				if (RolePermissionpage.HrAccountLeaveWeeklyLeaveTab()) {

					logResults.createLogs("Y", "PASS", " HrAccountLeaveWeeklyLeaveTab Clicked Successfully Of HR Account.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module HrAccountLeaveWeeklyLeaveTab."
							+ RolePermissionpage.getExceptionDesc());
				}
				if (meghleavepage.MonthFilterContains(month1WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Leave Request Applied Month Selected  Successfully Of Employee Account." + month1WorkingDate);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied Leave Request Month."
							+ meghleavepage.getExceptionDesc());
				}

				if (meghleavepage.FilterButton()) {

					logResults.createLogs("Y", "PASS", " Filter Button Clicked Successfully .");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button."
							+ meghleavepage.getExceptionDesc());
				}	
				
				
				
				if (meghleavepage.OfficeDrpDownClicked()) {

					logResults.createLogs("Y", "PASS", " Office DrpDown Clicked Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Office DropDown."
							+ meghleavepage.getExceptionDesc());
				}	

				if (meghleavepage.OfficeNameInputted(officename)) {

					logResults.createLogs("Y", "PASS", " Office Name Inputted Successfully ." + officename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Office Name."
							+ meghleavepage.getExceptionDesc());
				}		

				if (meghleavepage.OfficeNameSelectedOfFilter()) {

					logResults.createLogs("Y", "PASS", " Office Name Selected Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting  Office Name."
							+ meghleavepage.getExceptionDesc());
				}	
				
				
				if (meghleavepage.DeptDropDownClicked()) {

					logResults.createLogs("Y", "PASS", " Dept DropDown Clicked   Successfully ." + departmentname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Dept DropDown."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.DeptDropDownSelected()) {

					logResults.createLogs("Y", "PASS", " Dept Selected Successfully ." + departmentname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Dept Name."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.TeamDropDownClicked()) {

					logResults.createLogs("Y", "PASS", " Team DropDown Clicked   Successfully ." + teamname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Team DropDown."
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.TeamDropDownSelected()) {

					logResults.createLogs("Y", "PASS", " Team Selected Successfully ." + teamname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Team Name."
							+ meghleavepage.getExceptionDesc());
				}
				if (AttendancePolicyPage.DesignationDropdown(designationname)) {
					logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Selecting Designation From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
				}

				if (AttendancePolicyPage.EmpTypeDropDown(entityname)) {
					logResults.createLogs("Y", "PASS", "Successfully Selected  Employee Type From Dropdown." + entityname);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Selecting Employee Type From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
				}
				if (meghleavepage.LeaveTypesFilter(leavename)) {

					logResults.createLogs("Y", "PASS", "  Leave Type Selected  Successfully ." + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type From DropDown."
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.SaveFilterButton()) {

					logResults.createLogs("Y", "PASS", " Clicked On Save Filter Button Successfully ." );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button ."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.WeeklyLeaveCalendarSearchTextField(AdminFirstName)) {

					logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully ." + AdminFirstName );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name."
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.validateLeaveBalanceRow(AdminEmpID,casualleave,leaveduration, month1WorkingDate)) {

					logResults.createLogs("Y", "PASS", "Filter Applied Record Validated Successfully ." + AdminFirstName + " On the date of " + month1WorkingDate + " leave is " + casualleave  );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Filter Applied Row."
							+ meghleavepage.getExceptionDesc());
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
