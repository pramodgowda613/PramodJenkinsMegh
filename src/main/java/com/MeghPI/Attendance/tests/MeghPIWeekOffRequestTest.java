package com.MeghPI.Attendance.tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import com.MeghPI.Attendance.pages.MeghPIRegularizationRequestPage;
import com.MeghPI.Attendance.pages.MeghPIWeekOffReportPage;
import com.MeghPI.Attendance.pages.MeghPIWeekOffRequestPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghWeekOffPolicyPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import com.MeghPI.Attendance.pages.MeghPIAttendanceEmployeePage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIWeekOffRequestTest {
	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	

	private String cmpcode = "";
	private String Emailid = "";
	private String officename = "";
    private String AdminFirstName ="";
	  private String entityname = "";
	  private String AttendanceEmpid = "";
	  private String designationname= "";
	
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

	}

	
	// MPI_577_WeekOff_Policy_02
		@Test(enabled = true, priority = 1, groups = { "Smoke" })
		public void MPI_577_WeekOff_Policy_02()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, navigate to the Week Off Policy module and edit an existing policy. Update the week-off configuration by setting Saturday as a 2nd half day off and Sunday as a full day off for the 1st and 4th weeks. For the 2nd and 3rd weeks, configure both Saturday and Sunday as full day offs. Save the changes to the policy and ensure that the system reflects the updated configuration accurately, confirming that the record has been successfully updated as per the changes made.");

			ArrayList<String> data = initBase.loadExcelData("week_off_policy", currTC,
					"weekoffpolicyname,captcha,password");
			
			String weekoffpolicyname = data.get(0);
			String captcha = data.get(1);
			String password = data.get(2);
			
			MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
			
			MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
			MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		
			 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
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

			if (WeekOffPolicyPage.WeekOffPolicyButton()) {

				logResults.createLogs("Y", "PASS", "WeekOffPolicyButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffPolicyButton." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.WeekOffPolicySearchTextField(weekoffpolicyname)) {

				logResults.createLogs("Y", "PASS",
						"WeekOffPolicy Name Inputted In Search TextField Successfully." + weekoffpolicyname);
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

			if (WeekOffPolicyPage.SaturdayOption()) {

				logResults.createLogs("Y", "PASS", "SaturdayButton1stWeek Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On SaturdayButton1stWeek." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.FullDayButton()) {

				logResults.createLogs("Y", "PASS", "FullDayButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On FullDayButton ." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.SundayButton1stWeek()) {

				logResults.createLogs("Y", "PASS", "SundayButton1stWeek Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On SundayButton1stWeek ." + WeekOffPolicyPage.getExceptionDesc());
			}

			if (WeekOffPolicyPage.FullDayButton()) {

				logResults.createLogs("Y", "PASS", "FullDayButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On FullDayButton ." + WeekOffPolicyPage.getExceptionDesc());
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
	
		}
	
		// MPI_1639_WeekoffRequest_Tab_01
				@Test(enabled = true, priority = 2, groups = { "Smoke" })
				public void MPI_1639_WeekoffRequest_Tab_01()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, log in as an employee, raise a week-off request , and ensure that the request is successfully created.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"password,baseuri,loginendpoint,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,photo,captcha,regularizationstatus,regularizationreason");


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
				        String regularizationstatus = data.get(i++);
						
						String regularizationreason = data.get(i++);
				   
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
				
				        Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String firstDayLastMonth = (String) datas.get("firstDayLastMonth");
				        String firstsunday = (String) datas.get("1stSunday");
				       String firstworkingday = (String) datas.get("workingAfter1stSaturday");
					  
					MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

			
                                                   MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);
			
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
					if (RolePermissionpage.EmployeeAttendanceButton()) {

						logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
					}
					if (RolePermissionpage.EmployeeAttendanceButton()) {

						logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
					}
					if (RolePermissionpage.EmpWeekOffRequest()) {

						logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeButtonOnEmp()) {

						logResults.createLogs("Y", "PASS", "Week Off For Me Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOffRequest For Me Button." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeOldWODate()) {

						logResults.createLogs("Y", "PASS", "Week Off For Me Old Date TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeOldWODateInputted(firstsunday)) {

						logResults.createLogs("Y", "PASS", "On Week Off For Me Old Date TextField Last Month First Saturday Selected Successfully." + firstsunday);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Last Month First Sat on WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

						logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeNewWODate()) {

						logResults.createLogs("Y", "PASS", "Week Off For Me New Date TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOffRequest New Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeNewWODateInputted(firstworkingday)) {

						logResults.createLogs("Y", "PASS", "On Week Off For Me New WeekOff Date TextField Last Month For First Saturday Following Monday Selected Successfully." + firstworkingday);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Last Month First Sat Following Monday Date." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

						logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffApplyButtonOnEmp()) {

						logResults.createLogs("Y", "PASS", "Week Off Apply Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOffRequest Apply Button." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(firstsunday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffOfEmpMonthlySummaryBtn()) {

						logResults.createLogs("Y", "PASS", "Week Off Monthly Summary Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOffRequest Monthly Summary." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffOfEmpMonthlyPendingSummaryCount()) {

						logResults.createLogs("Y", "PASS", "Week Off Monthly Pending Summary Count Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOffRequest Monthly Pending Summary Count." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					
					if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnEmpAccountList(firstsunday, firstworkingday, regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "Week Off Monthly Pending Summary Count Validated Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating On WeekOffRequest Monthly Pending Summary Row." + meghpiweekoffrequestpage.getExceptionDesc());
					}
				}
	
	
	
				// MPI_1640_WeekoffRequest_Tab_02
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_1640_WeekoffRequest_Tab_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, approve the week-off request raised by the employee and ensure that the status shows as Approved on both the employee and admin accounts and validate attendance status for old weekoff date and new weekoff date.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"password,captcha,regularizationstatus,empid,firstname");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					
					String empid = data.get(3);
					
					String firstname = data.get(4);
					
				
					
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String firstsunday = (String) datas.get("1stSunday");
				       String firstworkingday = (String) datas.get("workingAfter1stSaturday");
                       MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);

		           
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

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
			if (RolePermissionpage.HrAccountWeekOffRequestTab()) {

				logResults.createLogs("Y", "PASS", "HrAccountWeekOffRequestTab Displayed Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying HrAccountWeekOffRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
			}
		
			if (meghleavepage.MonthFilterContains(firstsunday)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			

			if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

				logResults.createLogs("Y", "PASS", "Week Off Request Summary Clicked Successfully On Admin.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Week Off Request Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
			}
			
			if (meghpiweekoffrequestpage.WeekOffOfAdminPendingSummaryCount()) {

				logResults.createLogs("Y", "PASS", "Admin Account WeekOff Request Pending Summary Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOff Request Pending Summary Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			
			if (meghpiweekoffrequestpage.WeekOffRequestSearchTextField(firstname)) {

				logResults.createLogs("Y", "PASS", "On Admin Account WeekOff Requested Emp Name Inputted On Search Textfield   Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Emp Name Search TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}

			 if (meghpiweekoffrequestpage.WeekOffRequestEmpApprove(firstname,firstsunday, firstworkingday)) {

					logResults.createLogs("Y", "PASS", "Emp WeekOff Request Approve Button Clicked Successfully." + firstname + " " + firstsunday);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Request Approve Button  Of Emp ." + meghpiweekoffrequestpage.getExceptionDesc());
				}
		    
		     if (meghpiweekoffrequestpage.WeekOffRequestApproveButton()) {

					logResults.createLogs("Y", "PASS", "Approve Confirm Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Approve Confirm Button." + meghpiweekoffrequestpage.getExceptionDesc());
				} 
		     if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

		 		logResults.createLogs("Y", "PASS", "OT Summary Clicked Successfully On Admin.");
		 	} else {
		 		logResults.createLogs("Y", "FAIL",
		 				"Error While Clicking On OT Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
		 	}
		 	if (meghpiweekoffrequestpage.WeekOffRequestApproveSummaryCount()) {

		 		logResults.createLogs("Y", "PASS", "Admin Account WeekOff Request Approved Summary Clicked Successfully.");
		 	} else {
		 		logResults.createLogs("Y", "FAIL",
		 				"Error While Clicking On Admin WeekOff Request Approved Summary Button." + meghpiweekoffrequestpage.getExceptionDesc());
		 	}
		 	if (meghpiweekoffrequestpage.WeekOffRequestSearchTextField(firstname)) {

				logResults.createLogs("Y", "PASS", "On Admin Account WeekOff Requested Emp Name Inputted On Search Textfield   Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Emp Name Search TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
		 	if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnAdminAccountList(firstsunday, firstworkingday, regularizationstatus, empid)) {

				logResults.createLogs("Y", "PASS", "On Employee Account WeekOff Request Approved Record Validated Successfully." + firstsunday +" and status is " + regularizationstatus);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Emp WeekOff Request Approved Summary Count And Record." + meghpiweekoffrequestpage.getExceptionDesc());
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
				if (RolePermissionpage.EmpWeekOffRequest()) {

					logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
				}
				if (meghleavepage.MonthFilterContains(firstsunday)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.WeekOffOfEmpMonthlySummaryBtn()) {

					logResults.createLogs("Y", "PASS", "Week Off Monthly Summary Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On WeekOffRequest Monthly Summary." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				
				if (meghpiweekoffrequestpage.WeekOffRequestApproveSummaryCountOnEmp()) {

					logResults.createLogs("Y", "PASS", "Week Off Monthly Approved Summary Count Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On WeekOffRequest Monthly Approved Summary Count." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnEmpAccountList(firstsunday, firstworkingday, regularizationstatus)) {

					logResults.createLogs("Y", "PASS", "Week Off Monthly Approved Summary Record Validated Successfully." + firstsunday + "and status is" + regularizationstatus);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating On WeekOffRequest Monthly Approved Record." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				}
	
	
	
				// MPI_1641_WeekoffRequest_Tab_03
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_1641_WeekoffRequest_Tab_03()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, ensure that the old week-off day is now displayed as a working day, and the newly assigned week-off day is displayed with the Week Off day type.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"password,captcha,regularizationstatus,empid,empstatusonadmin");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					
					String empid = data.get(3);
					String EmpStatusForNewWeekOffDate = data.get(4); 
					
					
				
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();
				        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

				        String firstsunday = (String) datas.get("1stSunday");
				       String firstworkingday = (String) datas.get("workingAfter1stSaturday");
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
	        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
	   	 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
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
			if (meghleavepage.MonthFilterContains(firstsunday)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
		
			
			
			if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(firstsunday, regularizationstatus )) {

				logResults.createLogs("Y", "PASS", "Old WeekOff Date Attendance Status Validated Successfully." + firstsunday +  "status is" + regularizationstatus );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Old WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
			}
			if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(firstworkingday, EmpStatusForNewWeekOffDate )) {

				logResults.createLogs("Y", "PASS", "New WeekOff Date Attendance Status Validated Successfully." + firstsunday +  "status is" + EmpStatusForNewWeekOffDate );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating New WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
			}
			
				}
	
	
				// MPI_1642_WeekoffRequest_Tab_04
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_1642_WeekoffRequest_Tab_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, perform Punch-In on the old week-off day and on the new week-off day, and ensure the statuses displayed are ‘P’ and ‘PW’ respectively.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
				            "password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,bio1finger,bio2finger," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha,regularizationstatus,empstatusonadmin");

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
			        String EmpStatusForNewWeekOffDate = data.get(i++);
			        
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
					
			        Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

			        String firstDayLastMonth = (String) datas.get("firstDayLastMonth");
			        String firstsunday = (String) datas.get("1stSunday");
			       String firstworkingday = (String) datas.get("workingAfter1stSaturday");
			       
					String inouttime = firstsunday + " " + inouttime1;
					String secondInOutTime = firstsunday + " " + secondInOutTime2;
					
					String inouttimetwo = firstworkingday + " " + inouttime1;
					String secondInOutTimetwo = firstworkingday + " " + secondInOutTime2;
				    
					
					MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
			        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
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
				            empid, firstDayLastMonth + "T00:00:00.000Z"
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
					if (meghleavepage.MonthFilterContains(firstsunday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
				
					
					
					if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(firstsunday, regularizationstatus )) {

						logResults.createLogs("Y", "PASS", "Old WeekOff Date Attendance Status Validated Successfully." + firstsunday +  "status is" + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Old WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(firstworkingday, EmpStatusForNewWeekOffDate )) {

						logResults.createLogs("Y", "PASS", "New WeekOff Date Attendance Status Validated Successfully." + firstsunday +  "status is" + EmpStatusForNewWeekOffDate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating New WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
					}
				}
	
	
				// MPI_1643_WeekoffRequest_Tab_05
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_1643_WeekoffRequest_Tab_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise the week-off request and ensure the status is displayed as ‘Pending’ on both the employee and admin accounts.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"captcha,regularizationstatus,empid,regularizationreason");

					
					String captcha = data.get(0);
					String regularizationstatus = data.get(1);
					
					String empid = data.get(2);
					String regularizationreason = data.get(3); 
					
					
				
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();
				        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

				        String secondsaturday = (String) datas.get("2ndSaturday");
				       String secondweekfirstworkingday = (String) datas.get("workingAfter2ndSaturday");
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();

			
                                                   MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);
			

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
			if (RolePermissionpage.EmployeeAttendanceButton()) {

				logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
			}
			if (RolePermissionpage.EmpWeekOffRequest()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeButtonOnEmp()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest For Me Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeOldWODate()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me Old Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeOldWODateInputted(secondsaturday)) {

				logResults.createLogs("Y", "PASS", "On Week Off For Me Old Date TextField Last Month First Saturday Selected Successfully." + secondsaturday);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Last Month First Sat on WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeNewWODate()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me New Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest New Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeNewWODateInputted(secondweekfirstworkingday)) {

				logResults.createLogs("Y", "PASS", "On Week Off For Me New WeekOff Date TextField Last Month For First Saturday Following Monday Selected Successfully." + secondweekfirstworkingday);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Last Month First Sat Following Monday Date." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffApplyButtonOnEmp()) {

				logResults.createLogs("Y", "PASS", "Week Off Apply Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Apply Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghleavepage.MonthFilterContains(secondsaturday)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + secondsaturday );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffOfEmpMonthlySummaryBtn()) {

				logResults.createLogs("Y", "PASS", "Week Off Monthly Summary Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Monthly Summary." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffOfEmpMonthlyPendingSummaryCount()) {

				logResults.createLogs("Y", "PASS", "Week Off Monthly Pending Summary Count Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Monthly Pending Summary Count." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			
			if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnEmpAccountList(secondsaturday, secondweekfirstworkingday, regularizationstatus)) {

				logResults.createLogs("Y", "PASS", "Week Off Monthly Pending Summary Count Validated Successfully."+ secondsaturday +"new weekoff date is " + secondweekfirstworkingday + "and status is " + regularizationstatus );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating On WeekOffRequest Monthly Pending Summary Row." + meghpiweekoffrequestpage.getExceptionDesc());
			}
		}
	
	
				// MPI_1644_WeekoffRequest_Tab_06
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_1644_WeekoffRequest_Tab_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, reject the week-off request raised by the employee and ensure that the status shows as rejected on both the employee and admin accounts and ensure attendance status remain same.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"password,captcha,regularizationstatus,empid,firstname,regularizationreason,secondstatusofemp,empstatusonadmin");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					
					String empid = data.get(3);
					
					String firstname = data.get(4);
					String regularizationreason = data.get(5);
					String secondstatusofemp = data.get(6);
					String empstatusonadmin = data.get(7);
					
					
				
					
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String secondsaturday = (String) datas.get("2ndSaturday");
				       String secondweekfirstworkingday = (String) datas.get("workingAfter2ndSaturday");
                       MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);

		           
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 

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
			if (RolePermissionpage.HrAccountWeekOffRequestTab()) {

				logResults.createLogs("Y", "PASS", "HrAccountWeekOffRequestTab Displayed Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying HrAccountWeekOffRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
			}
		
			if (meghleavepage.MonthFilterContains(secondsaturday)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + secondsaturday );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			

			if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

				logResults.createLogs("Y", "PASS", "Week Off Request Summary Clicked Successfully On Admin.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Week Off Request Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
			}
			
			if (meghpiweekoffrequestpage.WeekOffOfAdminPendingSummaryCount()) {

				logResults.createLogs("Y", "PASS", "Admin Account WeekOff Request Pending Summary Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOff Request Pending Summary Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			
			if (meghpiweekoffrequestpage.WeekOffRequestSearchTextField(firstname)) {

				logResults.createLogs("Y", "PASS", "On Admin Account WeekOff Requested Emp Name Inputted On Search Textfield   Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Emp Name Search TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}

			 if (meghpiweekoffrequestpage.WeekOffRequestEmpRejectRequest(firstname,secondsaturday, secondweekfirstworkingday)) {

					logResults.createLogs("Y", "PASS", "Emp WeekOff Request Reject Button Clicked Successfully." + firstname + " " + secondsaturday);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Request Reject Button  Of Emp ." + meghpiweekoffrequestpage.getExceptionDesc());
				}
			 if (meghpiweekoffrequestpage.WeekOffRequestRejectReason(regularizationreason)) {

					logResults.createLogs("Y", "PASS", "Emp WeekOff Request Reject Reason Inputted Successfully." + regularizationreason );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting  WeekOff Request Reject Reason." + meghpiweekoffrequestpage.getExceptionDesc());
				}
			 
		     if (meghpiweekoffrequestpage.WeekOffRequestRejectConfirmButton()) {

					logResults.createLogs("Y", "PASS", "Reject Confirm Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Reject Confirm Button." + meghpiweekoffrequestpage.getExceptionDesc());
				} 
		     if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

		 		logResults.createLogs("Y", "PASS", "OT Summary Clicked Successfully On Admin.");
		 	} else {
		 		logResults.createLogs("Y", "FAIL",
		 				"Error While Clicking On OT Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
		 	}
		 	if (meghpiweekoffrequestpage.WeekOffRequestRejectSummaryCountOnAdmin()) {

		 		logResults.createLogs("Y", "PASS", "Admin Account WeekOff Request Rejected Summary Clicked Successfully.");
		 	} else {
		 		logResults.createLogs("Y", "FAIL",
		 				"Error While Clicking On Admin WeekOff Request Rejected Summary Button." + meghpiweekoffrequestpage.getExceptionDesc());
		 	}
		 	if (meghpiweekoffrequestpage.WeekOffRequestSearchTextField(firstname)) {

				logResults.createLogs("Y", "PASS", "On Admin Account WeekOff Requested Emp Name Inputted On Search Textfield   Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Emp Name Search TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
		 	if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnAdminAccountList(secondsaturday, secondweekfirstworkingday, regularizationstatus, empid)) {

				logResults.createLogs("Y", "PASS", "On Employee Account WeekOff Request Rejected Record Validated Successfully." + secondsaturday +" and status is " + regularizationstatus);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Emp WeekOff Request Rejected Summary Count And Record." + meghpiweekoffrequestpage.getExceptionDesc());
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
				if (RolePermissionpage.EmpWeekOffRequest()) {

					logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
				}
				if (meghleavepage.MonthFilterContains(secondsaturday)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + secondsaturday );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.WeekOffOfEmpMonthlySummaryBtn()) {

					logResults.createLogs("Y", "PASS", "Week Off Monthly Summary Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On WeekOffRequest Monthly Summary." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				
				if (meghpiweekoffrequestpage.WeekOffRequestRejectSummaryCountOnEmp()) {

					logResults.createLogs("Y", "PASS", "Week Off Monthly Rejected Summary Count Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On WeekOffRequest Monthly Rejected Summary Count." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnEmpAccountList(secondsaturday, secondweekfirstworkingday, regularizationstatus)) {

					logResults.createLogs("Y", "PASS", "Week Off Monthly Rejected Summary Record Validated Successfully." + secondsaturday + "and status is" + regularizationstatus);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating On WeekOffRequest Monthly Rejected Record." + meghpiweekoffrequestpage.getExceptionDesc());
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
				if (meghleavepage.MonthFilterContains(secondsaturday)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + secondsaturday );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
							+ meghleavepage.getExceptionDesc());
				}
			
				
				
				if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(secondsaturday, empstatusonadmin  )) {

					logResults.createLogs("Y", "PASS", "Old WeekOff Date Attendance Status Validated Successfully." + secondsaturday +  "status is" + empstatusonadmin );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating Old WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
				}
				if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(secondweekfirstworkingday, secondstatusofemp )) {

					logResults.createLogs("Y", "PASS", "New WeekOff Date Attendance Status Validated Successfully." + secondweekfirstworkingday +  "status is" + secondstatusofemp );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating New WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
				}
				
				}
	
				// MPI_1646_WeekoffRequest_Tab_08
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_1646_WeekoffRequest_Tab_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise a Weekoff request, then revoke it, and ensure the record is deleted successfully and the status is displayed as Revoked in the admin account.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"captcha,regularizationstatus,empid,regularizationreason,password");

					
					String captcha = data.get(0);
					String regularizationstatus = data.get(1);
					
					String empid = data.get(2);
					String regularizationreason = data.get(3); 
					String password = data.get(4);
					
					
				
			 Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();

			 String week2Saturday = (String) details.get("2ndSaturday");
					       List<String> week2WorkingDays = (List<String>) details.get("workingAfter2Weekoff");

					       String week2Tuesday = week2WorkingDays.get(1);
					       
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
					MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

			
                                                   MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);
			

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
			if (RolePermissionpage.EmployeeAttendanceButton()) {

				logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
			}
			if (RolePermissionpage.EmpWeekOffRequest()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
			} 
			if (meghpiweekoffrequestpage.WeekOffForMeButtonOnEmp()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest For Me Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeOldWODate()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me Old Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeOldWODateInputted(week2Saturday)) {

				logResults.createLogs("Y", "PASS", "On Week Off For Me Old Date TextField Last Month First Saturday Selected Successfully." + week2Saturday);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Last Month First Sat on WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeNewWODate()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me New Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest New Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeNewWODateInputted(week2Tuesday)) {

				logResults.createLogs("Y", "PASS", "On Week Off For Me New WeekOff Date TextField Last Month For First Saturday Following Monday Selected Successfully." + week2Tuesday);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Last Month First Sat Following Monday Date." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffApplyButtonOnEmp()) {

				logResults.createLogs("Y", "PASS", "Week Off Apply Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Apply Button." + meghpiweekoffrequestpage.getExceptionDesc());
			} 
			if (meghleavepage.MonthFilterContains(week2Saturday)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + week2Saturday );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffOfEmpMonthlySummaryBtn()) {

				logResults.createLogs("Y", "PASS", "Week Off Monthly Summary Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Monthly Summary." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffOfEmpMonthlyPendingSummaryCount()) {

				logResults.createLogs("Y", "PASS", "Week Off Monthly Pending Summary Count Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Monthly Pending Summary Count." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			
			if (meghpiweekoffrequestpage.WeekOffRequestEmpRevoking(week2Saturday, week2Tuesday)) {

				logResults.createLogs("Y", "PASS", "Week Off Revoke Clicked Successfully." + week2Saturday);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Revoke Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffRequestRevokeConfirmButton()) {

				logResults.createLogs("Y", "PASS", "Week Off Revoke Confirm Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Revoke Confirm Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffOfEmpMonthlySummaryBtn()) {

				logResults.createLogs("Y", "PASS", "Week Off Monthly Summary Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Monthly Summary." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffRequestRevokeSummaryClicked()) {

				logResults.createLogs("Y", "PASS", "Week Off Monthly Revoked Summary Count Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Monthly Revoked Summary Count." + meghpiweekoffrequestpage.getExceptionDesc());
			}

			if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnEmpAccountList(week2Saturday, week2Tuesday, regularizationstatus)) {

				logResults.createLogs("Y", "PASS", "Week Off Monthly Revoked Summary Count Validated Successfully."+ week2Saturday +"new weekoff date is " + week2Tuesday + "and status is " + regularizationstatus );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating On WeekOffRequest Monthly Revoked Summary Row." + meghpiweekoffrequestpage.getExceptionDesc());
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
			if (RolePermissionpage.HrAccountWeekOffRequestTab()) {

				logResults.createLogs("Y", "PASS", "HrAccountWeekOffRequestTab Displayed Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying HrAccountWeekOffRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
			}
		
			if (meghleavepage.MonthFilterContains(week2Saturday)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + week2Saturday );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			

			if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

				logResults.createLogs("Y", "PASS", "Week Off Request Summary Clicked Successfully On Admin.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Week Off Request Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
			}
			
			if (meghpiweekoffrequestpage.WeekOffRequestRevokeSummaryClickedOnAdmin()) {

				logResults.createLogs("Y", "PASS", "Admin Account WeekOff Request Revoked Summary Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOff Request Revoked Summary Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnAdminAccountList(week2Saturday, week2Tuesday, regularizationstatus, empid)) {

				logResults.createLogs("Y", "PASS", "On Employee Account WeekOff Request Revoked Record Validated Successfully." + week2Saturday +" and status is " + regularizationstatus);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Emp WeekOff Request Revoked Summary Count And Record." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			
		}
		
			
				// MPI_1650_WeekoffRequest_Tab_12
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_1650_WeekoffRequest_Tab_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, create a week-off request for multiple days and, as an admin, reject all using the “Reject All” button. Ensure that the Day Type remains unchanged for all the rejected dates.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"captcha,regularizationstatus,empid,regularizationreason,password,rejectreason,empstatusonadmin,secondstatusofemp");

					
					String captcha = data.get(0);
					String regularizationstatus = data.get(1);
					
					String empid = data.get(2);
					String regularizationreason = data.get(3); 
					String password = data.get(4);
					String rejectreason = data.get(5);
					String empstatusonadmin = data.get(6);
					String secondstatusofemp = data.get(7);

				        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

				      
				        Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();

				        String week3Saturday = (String) details.get("3rdSaturday");
				        String week2Sunday   = (String) details.get("2ndSunday");

				        List<String> week3WorkingDays = (List<String>) details.get("workingAfter3Weekoff");
				        String week2Monday    = week3WorkingDays.get(0);

				        List<String> week2WorkingDays = (List<String>) details.get("workingAfter2Weekoff");
				        String week2Wednesday = week2WorkingDays.get(2);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
					MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
  MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);
  MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 

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
			if (RolePermissionpage.EmployeeAttendanceButton()) {

				logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
			}
			if (RolePermissionpage.EmpWeekOffRequest()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeButtonOnEmp()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest For Me Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeOldWODate()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me Old Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeOldWODateInputted(week3Saturday )) {

				logResults.createLogs("Y", "PASS", "On Week Off For Me Old Date TextField Last Month 3rd Saturday Selected Successfully." + week3Saturday );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Last Month 3rd Sat on WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeNewWODate()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me New Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest New Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeNewWODateInputted(week2Monday)) {

				logResults.createLogs("Y", "PASS", "On Week Off For Me New WeekOff Date TextField Last Month For First Saturday Following Monday Selected Successfully." + week2Monday);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Last Month First Sat Following Monday Date." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffApplyButtonOnEmp()) {

				logResults.createLogs("Y", "PASS", "Week Off Apply Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Apply Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			
			if (meghpiweekoffrequestpage.WeekOffForMeButtonOnEmp()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest For Me Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeOldWODate()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me Old Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeOldWODateInputted(week2Sunday )) {

				logResults.createLogs("Y", "PASS", "On Week Off For Me Old Date TextField Last Month 3rd Saturday Selected Successfully." + week2Sunday );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Last Month 3rd Sat on WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeNewWODate()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me New Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest New Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeNewWODateInputted(week2Wednesday)) {

				logResults.createLogs("Y", "PASS", "On Week Off For Me New WeekOff Date TextField Last Month For First Saturday Following Monday Selected Successfully." + week2Wednesday);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Last Month First Sat Following Monday Date." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffApplyButtonOnEmp()) {

				logResults.createLogs("Y", "PASS", "Week Off Apply Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Apply Button." + meghpiweekoffrequestpage.getExceptionDesc());
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
			if (RolePermissionpage.HrAccountWeekOffRequestTab()) {

				logResults.createLogs("Y", "PASS", "HrAccountWeekOffRequestTab Displayed Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying HrAccountWeekOffRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
			}
		
			if (meghleavepage.MonthFilterContains(week2Sunday)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + week2Sunday );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
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

			if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

				logResults.createLogs("Y", "PASS", "Week Off Request Summary Clicked Successfully On Admin.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Week Off Request Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
			}
			
			if (meghpiweekoffrequestpage.WeekOffRequestRejectSummaryCountOnAdmin()) {

				logResults.createLogs("Y", "PASS", "Admin Account WeekOff Request Rejected Summary Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOff Request Rejected Summary Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnAdminAccountList(week2Sunday, week2Wednesday, regularizationstatus, empid)) {

				logResults.createLogs("Y", "PASS", "On Employee Account WeekOff Request Rejected Record Validated Successfully." + week2Sunday +" and status is " + regularizationstatus);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Emp WeekOff Request Rejected Summary Count And Record." + meghpiweekoffrequestpage.getExceptionDesc());
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
				if (meghpiattendanceemployeepage.EmpAttendanceLoaded()) {

					logResults.createLogs("Y", "PASS", "Employee Attendance Loaded Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Loading My Attendance Table Data." + meghpiattendanceemployeepage.getExceptionDesc());
				}
				if (meghleavepage.MonthFilterContains(week2Sunday)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + week2Sunday );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
							+ meghleavepage.getExceptionDesc());
				}
			
				
				
				if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(week2Sunday, empstatusonadmin )) {

					logResults.createLogs("Y", "PASS", "Old WeekOff Date Attendance Status Validated Successfully." + week2Sunday +  "status is" + empstatusonadmin );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating Old WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
				}
				if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(week2Wednesday, secondstatusofemp )) {

					logResults.createLogs("Y", "PASS", "New WeekOff Date Attendance Status Validated Successfully." + week2Wednesday +  "status is" + secondstatusofemp );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating New WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
				}
				if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(week3Saturday, empstatusonadmin )) {

					logResults.createLogs("Y", "PASS", "Old WeekOff Date Attendance Status Validated Successfully." + week3Saturday +  "status is" + empstatusonadmin );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating Old WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
				}
				if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(week2Monday, secondstatusofemp )) {

					logResults.createLogs("Y", "PASS", "New WeekOff Date Attendance Status Validated Successfully." + week2Monday +  "status is" + secondstatusofemp );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating New WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
				}
			
		}		
				
				
				// MPI_1651_WeekoffRequest_Tab_13
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_1651_WeekoffRequest_Tab_13()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, create a week-off request for multiple days and, as an admin, Approve all using the “Approve All” button. Ensure that the all requests are approved and oll weekoff  Day Type updated for all the approved dates.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"captcha,regularizationstatus,empid,regularizationreason,password,empstatusonadmin,secondstatusofemp,baseuri,loginendpoint,updateattendanceendpoint");

					
					String captcha = data.get(0);
					String regularizationstatus = data.get(1);
					
					String empid = data.get(2);
					String regularizationreason = data.get(3); 
					String password = data.get(4);
					String empstatusonadmin = data.get(5);
					String secondstatusofemp = data.get(6);
					String baseuri = data.get(7);
					String loginendpoint = data.get(8);
					String updateattendanceendpoint = data.get(9);

				        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

				      
				        Map<String, Object> details = Pramod.getLast2ndMonthWeekendAndWorkingDetails();

				        LocalDate last2ndmonthfirstsaturday = (LocalDate) details.getOrDefault("1stSaturday", null);
				        String firstsaturday = last2ndmonthfirstsaturday.toString();
				        
				        LocalDate firstmonday = (LocalDate) details.getOrDefault("workingAfter1stSaturday", null);
				        String mondayfirst = firstmonday.toString();
				        String firstDayLast2ndMonth = details.get("firstDayLast2ndMonth").toString();

				        

				      
				        LocalDate last2ndmonthfirstsundday = (LocalDate) details.getOrDefault("1stSunday", null);
				        String firstsunday = last2ndmonthfirstsundday.toString();
				        LocalDate firsttuesday = (LocalDate) details.getOrDefault("workingAfter1stSunday", null);
				        String tuesfirst = firsttuesday.toString();

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
					MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
  MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);
  MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
	MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();


  
  // Trigger attendance update first
  Response updateResp = apiPage.executeUpdateAttendance(
          baseuri, loginendpoint,
          Emailid, password, cmpcode,
          baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
          empid, firstDayLast2ndMonth + "T00:00:00.000Z"
  );

  if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
      logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + empid + firstsaturday);
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
			if (RolePermissionpage.EmployeeAttendanceButton()) {

				logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
			}
			if (RolePermissionpage.EmpWeekOffRequest()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeButtonOnEmp()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest For Me Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeOldWODate()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me Old Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeOldWODateInputted(firstsaturday )) {

				logResults.createLogs("Y", "PASS", "On Week Off For Me Old Date TextField Last Month 3rd Saturday Selected Successfully." + firstsaturday );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Last Month 3rd Sat on WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeNewWODate()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me New Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest New Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeNewWODateInputted(mondayfirst)) {

				logResults.createLogs("Y", "PASS", "On Week Off For Me New WeekOff Date TextField Last Month For First Saturday Following Monday Selected Successfully." + mondayfirst);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Last Month First Sat Following Monday Date." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffApplyButtonOnEmp()) {

				logResults.createLogs("Y", "PASS", "Week Off Apply Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Apply Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			
			if (meghpiweekoffrequestpage.WeekOffForMeButtonOnEmp()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest For Me Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeOldWODate()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me Old Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeOldWODateInputted(firstsunday )) {

				logResults.createLogs("Y", "PASS", "On Week Off For Me Old Date TextField Last Month 3rd Saturday Selected Successfully." + firstsunday );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Last Month 3rd Sat on WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeNewWODate()) {

				logResults.createLogs("Y", "PASS", "Week Off For Me New Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest New Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeNewWODateInputted(tuesfirst)) {

				logResults.createLogs("Y", "PASS", "On Week Off For Me New WeekOff Date TextField Last Month For First Saturday Following Monday Selected Successfully." + tuesfirst);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Last Month First Sat Following Monday Date." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

				logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.WeekOffApplyButtonOnEmp()) {

				logResults.createLogs("Y", "PASS", "Week Off Apply Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOffRequest Apply Button." + meghpiweekoffrequestpage.getExceptionDesc());
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
			if (RolePermissionpage.HrAccountWeekOffRequestTab()) {

				logResults.createLogs("Y", "PASS", "HrAccountWeekOffRequestTab Displayed Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying HrAccountWeekOffRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
			}
		
			if (meghleavepage.MonthFilterContains(firstsunday)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
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

				logResults.createLogs("Y", "PASS", "Week Off Request Summary Clicked Successfully On Admin.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Week Off Request Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
			}
			
			if (meghpiweekoffrequestpage.WeekOffRequestApproveSummaryCount()) {

				logResults.createLogs("Y", "PASS", "Admin Account WeekOff Request Approved Summary Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOff Request Approved Summary Button." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnAdminAccountList(firstsaturday, mondayfirst, regularizationstatus, empid)) {

				logResults.createLogs("Y", "PASS", "On Employee Account WeekOff Request Rejected Record Validated Successfully." + firstsaturday +" and status is " + regularizationstatus);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Emp WeekOff Request Rejected Summary Count And Record." + meghpiweekoffrequestpage.getExceptionDesc());
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
				if (meghpiattendanceemployeepage.EmpAttendanceLoaded()) {

					logResults.createLogs("Y", "PASS", "Employee Attendance Loaded Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Loading My Attendance Table Data." + meghpiattendanceemployeepage.getExceptionDesc());
				}
				if (meghleavepage.MonthFilterContains(firstsaturday)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsaturday );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
							+ meghleavepage.getExceptionDesc());
				}
			
				
				
				if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(firstsaturday, empstatusonadmin )) {

					logResults.createLogs("Y", "PASS", "Old WeekOff Date Attendance Status Validated Successfully." + firstsaturday +  "status is" + empstatusonadmin );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating Old WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
				}
				if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(mondayfirst, secondstatusofemp )) {

					logResults.createLogs("Y", "PASS", "New WeekOff Date Attendance Status Validated Successfully." + mondayfirst +  "status is" + secondstatusofemp );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating New WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
				}
				if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(firstsunday, empstatusonadmin )) {

					logResults.createLogs("Y", "PASS", "Old WeekOff Date Attendance Status Validated Successfully." + firstsunday +  "status is" + empstatusonadmin );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating Old WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
				}
				if (meghpiattendanceemployeepage.validateAttendanceRowForWeekOff(tuesfirst, secondstatusofemp )) {

					logResults.createLogs("Y", "PASS", "New WeekOff Date Attendance Status Validated Successfully." + tuesfirst +  "status is" + secondstatusofemp );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating New WeekOff Date Attendance Status." + meghregularizationrequestpage.getExceptionDesc());
				}	
		}		
				
				
				// MPI_1645_WeekoffRequest_Tab_07
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_1645_WeekoffRequest_Tab_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the \"All\", \"Pending\", \"Approved\",  and \"Rejected\" summary counts on the admin account.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"password,captcha,approvedcount,empid,pendingfilter,rejectedfilter,revokedfilter");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String approvedcount = data.get(2);
					String empid  = data.get(3);
					String pendingfilter = data.get(4);
					String rejectedfilter = data.get(5);
					String revokedfilter = data.get(6);
					
				
					
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String firstsunday = (String) datas.get("1stSunday");
                       MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);

		           
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

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
			if (RolePermissionpage.HrAccountWeekOffRequestTab()) {

				logResults.createLogs("Y", "PASS", "HrAccountWeekOffRequestTab Displayed Successfully In HR Account.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying HrAccountWeekOffRequestTab In Attendance Module."
								+ RolePermissionpage.getExceptionDesc());
			}
		
			if (meghleavepage.MonthFilterContains(firstsunday)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			

			if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

				logResults.createLogs("Y", "PASS", "WeekOff Request Summary Clicked Successfully On Admin.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On WeekOff Request Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
			}	
			if (meghpiweekoffrequestpage.EmpWeekOffApprovedCountValidation(approvedcount)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Approved Summary Count Validated Successfully On Admin." + approvedcount);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Approved Summary Count On Week Off Request." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.EmpWeekOffRejectedCountValidation(rejectedfilter)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Rejected Summary Count Validated Successfully On Admin." + rejectedfilter);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Rejected Summary Count On Week Off Request." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.EmpWeekOffRevokedCountValidation(revokedfilter)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Revoked Summary Count Validated Successfully On Admin." + revokedfilter);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Revoked Summary Count On Week Off Request." + meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.EmpWeekOffPendingCountValidation(pendingfilter)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Pending Summary Count Validated Successfully On Admin." + pendingfilter);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Validating Pending Summary Count On Week Off Request." + meghpiweekoffrequestpage.getExceptionDesc());
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
				if (RolePermissionpage.EmpWeekOffRequest()) {

					logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
				}
				if (meghleavepage.MonthFilterContains(firstsunday)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
							+ meghleavepage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.WeekOffOfEmpMonthlySummaryBtn()) {

					logResults.createLogs("Y", "PASS", "Week Off Monthly Summary Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On WeekOffRequest Monthly Summary." + meghpiweekoffrequestpage.getExceptionDesc());
				}
			
				if (meghpiweekoffrequestpage.EmpWeekOffApprovedCountValidationOnEmp(approvedcount)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Approved Summary Count Validated Successfully On Admin." + approvedcount);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating Approved Summary Count On Week Off Request." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.EmpWeekOffRejectedCountValidationOnEmp(rejectedfilter)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Rejected Summary Count Validated Successfully On Admin." + rejectedfilter);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating Rejected Summary Count On Week Off Request." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.EmpWeekOffRevokedCountValidationOnEmp(revokedfilter)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Revoked Summary Count Validated Successfully On Admin." + revokedfilter);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating Revoked Summary Count On Week Off Request." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.EmpWeekOffPendingCountValidationOnEmp(pendingfilter)) {

					logResults.createLogs("Y", "PASS", "Week Off Request Pending Summary Count Validated Successfully On Admin." + pendingfilter);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating Pending Summary Count On Week Off Request." + meghpiweekoffrequestpage.getExceptionDesc());
				}

				}	
				
				
				// MPI_1647_WeekoffRequest_Tab_09
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_1647_WeekoffRequest_Tab_09() {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the summary count details in the employee account for pending, rejected, approved, and total requests..");
					logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_1645_WeekoffRequest_Tab_07 TestCase.");
				}
				
				// MPI_1648_WeekoffRequest_Tab_10
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_1648_WeekoffRequest_Tab_10()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise a weekoff request from the admin account for an employee and ensure that the request is created with the “Approved” status and is displayed on the employee side.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"password,captcha,firstname,regularizationreason,empid,regularizationstatus");
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);
					  MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);
						MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

					
					String password = data.get(0);
					String captcha = data.get(1);
					String firstname = data.get(2);
					String regularizationreason = data.get(3);
					String empid = data.get(4);
					String regularizationstatus = data.get(5);
						
					
					 Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();

				        String week3Sunday = (String) details.get("3rdSunday");

				        List<String> week3WorkingDays = (List<String>) details.get("workingAfter3Weekoff");
				        String week2Tuesday    = week3WorkingDays.get(1);
				        
				        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

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
					if (meghpiweekoffreport.FromDateSelected(week3Sunday)) {

						logResults.createLogs("Y", "PASS", "Old WeekoffDate Inputted Successfully." + week3Sunday);
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
					if (meghpiweekoffreport.NewWeekOffDateSelected(week2Tuesday)) {

						logResults.createLogs("Y", "PASS", "New WeekoffDate Inputted Successfully." + week2Tuesday);
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
					if (meghleavepage.MonthFilterContains(week3Sunday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + week3Sunday );
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
					 	if (meghpiweekoffrequestpage.WeekOffRequestApproveSummaryCount()) {

					 		logResults.createLogs("Y", "PASS", "Admin Account WeekOff Request Approved Summary Clicked Successfully.");
					 	} else {
					 		logResults.createLogs("Y", "FAIL",
					 				"Error While Clicking On Admin WeekOff Request Approved Summary Button." + meghpiweekoffrequestpage.getExceptionDesc());
					 	}
					 	if (meghpiweekoffrequestpage.WeekOffRequestSearchTextField(firstname)) {

							logResults.createLogs("Y", "PASS", "On Admin Account WeekOff Requested Emp Name Inputted On Search Textfield   Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting Emp Name Search TextField." + meghpiweekoffrequestpage.getExceptionDesc());
						}
					 	if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnAdminAccountList(week3Sunday, week2Tuesday, regularizationstatus, empid)) {

							logResults.createLogs("Y", "PASS", "On Employee Account WeekOff Request Approved Record Validated Successfully." + week3Sunday +" and status is " + regularizationstatus);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating Emp WeekOff Request Approved Summary Count And Record." + meghpiweekoffrequestpage.getExceptionDesc());
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
							if (RolePermissionpage.EmpWeekOffRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
							}
							if (meghleavepage.MonthFilterContains(week3Sunday)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + week3Sunday );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}
							if (meghpiweekoffrequestpage.WeekOffOfEmpMonthlySummaryBtn()) {

								logResults.createLogs("Y", "PASS", "Week Off Monthly Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On WeekOffRequest Monthly Summary." + meghpiweekoffrequestpage.getExceptionDesc());
							}
							
							if (meghpiweekoffrequestpage.WeekOffRequestApproveSummaryCountOnEmp()) {

								logResults.createLogs("Y", "PASS", "Week Off Monthly Approved Summary Count Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On WeekOffRequest Monthly Approved Summary Count." + meghpiweekoffrequestpage.getExceptionDesc());
							}
							if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnEmpAccountList(week3Sunday, week2Tuesday, regularizationstatus)) {

								logResults.createLogs("Y", "PASS", "Week Off Monthly Approved Summary Record Validated Successfully." + week3Sunday + "and status is" + regularizationstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating On WeekOffRequest Monthly Approved Record." + meghpiweekoffrequestpage.getExceptionDesc());
							}
							}
				
				// MPI_1649_WeekoffRequest_Tab_11
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_1649_WeekoffRequest_Tab_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise a week-off request as an admin for yourself, switch to the employee view using the admin credentials, and ensure that the week-off request is approved. Also verify that the Day Type is updated correctly for both the old week-off date and the newly assigned week-off date.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"password,captcha,regularizationreason,regularizationstatus");
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);
					  MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationreason = data.get(2);
					String regularizationstatus = data.get(3);
						
					
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String firstsunday = (String) datas.get("1stSunday");
				       String firstworkingday = (String) datas.get("workingAfter1stSaturday");
				        
				        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

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
					if (meghpiweekoffrequestpage.WeekOffForMeClickedInAdmin()) {

						logResults.createLogs("Y", "PASS", "Apply  WeekOff For Me  Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply  WeekOff For Me  Button." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeOldWODate()) {

						logResults.createLogs("Y", "PASS", "Week Off For Me Old Date TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeOldWODateInputted(firstsunday)) {

						logResults.createLogs("Y", "PASS", "On Week Off For Me Old Date TextField Last Month First Saturday Selected Successfully." + firstsunday);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Last Month First Sat on WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

						logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeNewWODate()) {

						logResults.createLogs("Y", "PASS", "Week Off For Me New Date TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOffRequest New Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeNewWODateInputted(firstworkingday)) {

						logResults.createLogs("Y", "PASS", "On Week Off For Me New WeekOff Date TextField Last Month For First Saturday Following Monday Selected Successfully." + firstworkingday);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Last Month First Sat Following Monday Date." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

						logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffApplyButtonOnEmp()) {

						logResults.createLogs("Y", "PASS", "Week Off Apply Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOffRequest Apply Button." + meghpiweekoffrequestpage.getExceptionDesc());
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
							if (RolePermissionpage.EmpWeekOffRequest()) {

								logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
							}
							if (meghleavepage.MonthFilterContains(firstsunday)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}
							if (meghpiweekoffrequestpage.WeekOffOfEmpMonthlySummaryBtn()) {

								logResults.createLogs("Y", "PASS", "Week Off Monthly Summary Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On WeekOffRequest Monthly Summary." + meghpiweekoffrequestpage.getExceptionDesc());
							}
							
							if (meghpiweekoffrequestpage.WeekOffRequestApproveSummaryCountOnEmp()) {

								logResults.createLogs("Y", "PASS", "Week Off Monthly Approved Summary Count Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On WeekOffRequest Monthly Approved Summary Count." + meghpiweekoffrequestpage.getExceptionDesc());
							}
							if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnEmpAccountList(firstsunday, firstworkingday, regularizationstatus)) {

								logResults.createLogs("Y", "PASS", "Week Off Monthly Approved Summary Record Validated Successfully." + firstsunday + "and status is" + regularizationstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating On WeekOffRequest Monthly Approved Record." + meghpiweekoffrequestpage.getExceptionDesc());
							}
							}
				
				
				
				// MPI_1652_WeekoffRequest_Tab_14
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_1652_WeekoffRequest_Tab_14()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature for each search option on both employee and admin account.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"password,captcha,empid,regularizationstatus,revokedfilter,rejectedfilter");
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);
					  MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);

					
					String password = data.get(0);
					String captcha = data.get(1);
					String empid = data.get(2);
					String regularizationstatus = data.get(3);
					String revokedfilter = data.get(4);
					String rejectedfilter = data.get(5);
						
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String firstsunday = (String) datas.get("1stSunday");
					
				        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

				        MeghLoginPage meghloginpage = new MeghLoginPage(driver);
						MeghLoginTest meghlogintest = new MeghLoginTest();
						 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

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
					if (meghleavepage.MonthFilterContains(firstsunday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
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
					if (meghpiweekoffrequestpage.EmpIDSearchOption()) {

						logResults.createLogs("Y", "PASS", "Emp ID Search Option Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp ID Search Option." + meghpiweekoffrequestpage.getExceptionDesc());
					}
				
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiweekoffrequestpage.WeekOffRequestSearchTextField(empid)) {

						logResults.createLogs("Y", "PASS", "On Admin Account WeekOff Requested Emp ID Inputted On Search Textfield   Successfully." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp ID Search TextField." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					
					if (meghpiweekoffrequestpage.EmpNameRowInAdmin(empid)) {

						logResults.createLogs("Y", "PASS", "On Admin Account WeekOff Requested Emp ID Validated  Successfully." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp ID." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.EmpIDSearchOption()) {

						logResults.createLogs("Y", "PASS", "Emp ID Search Option Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp ID Search Option." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.EnrollIDSearchOption()) {

						logResults.createLogs("Y", "PASS", "Enroll ID Search Option Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Enroll ID Search Option." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
	
					if (meghpiweekoffrequestpage.WeekOffRequestSearchTextField(empid)) {

						logResults.createLogs("Y", "PASS", "On Admin Account WeekOff Requested Emp ID Inputted On Search Textfield   Successfully." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp ID Search TextField." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					
					if (meghpiweekoffrequestpage.EmpNameRowInAdmin(empid)) {

						logResults.createLogs("Y", "PASS", "On Admin Account WeekOff Requested Emp ID Validated  Successfully." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp ID." + meghpiweekoffrequestpage.getExceptionDesc());
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
						if (RolePermissionpage.EmpWeekOffRequest()) {

							logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
						}
						if (meghleavepage.MonthFilterContains(firstsunday)) {

							logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
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
					
					if (meghpiweekoffrequestpage.WeekOffStatusSearchOption()) {

						logResults.createLogs("Y", "PASS", "Status Search Option Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Status Search Option." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
			
					if (meghpiweekoffrequestpage.EmpAccountWOSearchTextField(regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "WeekOff Status Inputted Successfully." + regularizationstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting WeekOff Status." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffStatusRowValidation(regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "WeekOff Status Validated Successfully." + regularizationstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating WeekOff Status." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.EmpAccountWOSearchTextField(revokedfilter)) {

						logResults.createLogs("Y", "PASS", "WeekOff Status Inputted Successfully." + revokedfilter);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting WeekOff Status." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffStatusRowValidation(revokedfilter)) {

						logResults.createLogs("Y", "PASS", "WeekOff Status Validated Successfully." + revokedfilter);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating WeekOff Status." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.EmpAccountWOSearchTextField(rejectedfilter)) {
						logResults.createLogs("Y", "PASS", "WeekOff Status Inputted Successfully." + rejectedfilter);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting WeekOff Status." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.WeekOffStatusRowValidation(rejectedfilter)) {

						logResults.createLogs("Y", "PASS", "WeekOff Status Validated Successfully." + rejectedfilter);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating WeekOff Status." + meghpiweekoffrequestpage.getExceptionDesc());
					}
				
				}
				// MPI_1653_WeekoffRequest_Tab_15
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
				public void MPI_1653_WeekoffRequest_Tab_15() {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, go to the Admin Weekoff Request tab and validate that the Old Weekoff Date, New Weekoff Date, Weekoff Type, and Status columns display the correct data as per the submitted request.");
					logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_1640_WeekoffRequest_Tab_02 TestCase.");
				}
				
				// MPI_1654_WeekoffRequest_Tab_16
				@Test(enabled = true, priority = 17, groups = { "Smoke" })
				public void MPI_1654_WeekoffRequest_Tab_16()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, test the filter functionality by selecting a specific office, department, team, designation, and employee type, and ensure that only the employee records matching the selected criteria are displayed in the list.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"captcha,regularizationstatus,regularizationreason,password,baseuri,loginendpoint,updateattendanceendpoint");

					
					String captcha = data.get(0);
					String regularizationstatus = data.get(1);
					
					String regularizationreason = data.get(2); 
					String password = data.get(3);
					
					String baseuri = data.get(4);
					String loginendpoint = data.get(5);
					String updateattendanceendpoint = data.get(6);						
					
					
				
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String firstDayLastMonth = (String) datas.get("firstDayLastMonth");
				        String firstsunday = (String) datas.get("1stSunday");
				       String firstworkingday = (String) datas.get("workingAfter1stSaturday");
					
			        MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);
					  MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
	        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
			 MeghLoginPage meghloginpage = new MeghLoginPage(driver);

				MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

				// Trigger attendance update first
			    Response updateResp = apiPage.executeUpdateAttendance(
			            baseuri, loginendpoint,
			            Emailid, password, cmpcode,
			            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
			            AttendanceEmpid, firstDayLastMonth + "T00:00:00.000Z"
			    );

			    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + AttendanceEmpid + firstDayLastMonth);
			    } else {
			        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
			    } 
				
			    if (meghlogintest.verifyCompanyLogin(cmpcode, AttendanceEmpid, AttendanceEmpid, captcha, logResults, driver)) {
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
				if (RolePermissionpage.EmployeeAttendanceButton()) {

					logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
				}
				if (RolePermissionpage.EmpWeekOffRequest()) {

					logResults.createLogs("Y", "PASS", "Employee Can Access His EmpWeekOffRequest Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Displaying EmpWeekOffRequest Tab." + RolePermissionpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.WeekOffForMeButtonOnEmp()) {

					logResults.createLogs("Y", "PASS", "Week Off For Me Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On WeekOffRequest For Me Button." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.WeekOffForMeOldWODate()) {

					logResults.createLogs("Y", "PASS", "Week Off For Me Old Date TextField Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.WeekOffForMeOldWODateInputted(firstsunday)) {

					logResults.createLogs("Y", "PASS", "On Week Off For Me Old Date TextField Last Month First Saturday Selected Successfully." + firstsunday);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Last Month First Sat on WeekOffRequest Old Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

					logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.WeekOffForMeNewWODate()) {

					logResults.createLogs("Y", "PASS", "Week Off For Me New Date TextField Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On WeekOffRequest New Date TextField." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.WeekOffForMeNewWODateInputted(firstworkingday)) {

					logResults.createLogs("Y", "PASS", "On Week Off For Me New WeekOff Date TextField Last Month For First Saturday Following Monday Selected Successfully." + firstworkingday);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Selecting Last Month First Sat Following Monday Date." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.WeekOffForMeReasonTextField(regularizationreason)) {

					logResults.createLogs("Y", "PASS", "Week Off Reason Inputted Successfully." + regularizationreason);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting  WeekOffRequest Reason." + meghpiweekoffrequestpage.getExceptionDesc());
				}
				if (meghpiweekoffrequestpage.WeekOffApplyButtonOnEmp()) {

					logResults.createLogs("Y", "PASS", "Week Off Apply Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On WeekOffRequest Apply Button." + meghpiweekoffrequestpage.getExceptionDesc());
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
			if (meghpiweekoffreport.WeekOffButtonInAttendance()) {

				logResults.createLogs("Y", "PASS", "WeekOff  Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff  Button." + meghpiweekoffreport.getExceptionDesc());
			}
			if (meghleavepage.MonthFilterContains(firstsunday)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
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

				if (meghregularizationrequestpage.FilterApplyButtonOnRegularizationRequestTab()) {

					logResults.createLogs("Y", "PASS", "Filter Apply Button Clicked On Admin Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Filter Apply Button." + meghregularizationrequestpage.getExceptionDesc());
				}
						
				if (meghpiweekoffrequestpage.EmpNameRowInAdmin(AttendanceEmpid)) {

					logResults.createLogs("Y", "PASS", "On Admin Account WeekOff Request Module Filtered Row Validated  Successfully." + AttendanceEmpid + regularizationstatus);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Validating Filter Applied Record." + meghpiweekoffrequestpage.getExceptionDesc());
				}
					}
				
				
				// MPI_1655_WeekoffRequest_Tab_17
				@Test(enabled = true, priority = 18, groups = { "Smoke" })
				public void MPI_1655_WeekoffRequest_Tab_17()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, select the Pending, Approved, Rejected, and Revoked filter options from the filter panel, and ensure that only the records matching the selected status are displayed.");

					ArrayList<String> data = initBase.loadExcelData("WeekOffRequest_Tab", currTC,
							"password,captcha,regularizationstatus,empid,pendingfilter,rejectedfilter,revokedfilter");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					String empid  = data.get(3);
					String pendingfilter = data.get(4);
					String rejectedfilter = data.get(5);
					String  revokedfilter = data.get(6);
					
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String firstsunday = (String) datas.get("1stSunday");
						MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
                            MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);
        			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);

					
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
					if (meghpiweekoffreport.WeekOffButtonInAttendance()) {

						logResults.createLogs("Y", "PASS", "WeekOff  Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff  Button." + meghpiweekoffreport.getExceptionDesc());
					}
				
					if (meghleavepage.MonthFilterContains(firstsunday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
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
				
					if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnAdminAccountForFilter(regularizationstatus, empid)) {

						logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Approved Summary Validated Successfully."  +" and status is " + regularizationstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Shift Request Approved Summary Count And Record." + meghpiweekoffrequestpage.getExceptionDesc());
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
					if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnAdminAccountForFilter(pendingfilter, AttendanceEmpid)) {

						logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Pending Summary Validated Successfully."  +" and status is " + AttendanceEmpid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Shift Request Pending Summary Count And Record." + meghpiweekoffrequestpage.getExceptionDesc());
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
					if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnAdminAccountForFilter(rejectedfilter, empid)) {

						logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Rejected Summary Validated Successfully."  +" and status is " + rejectedfilter);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Shift Request Rejected Summary Count And Record." + meghpiweekoffrequestpage.getExceptionDesc());
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
					if (meghpiweekoffrequestpage.ValidateEmpWeekOffRequestStatusOnAdminAccountForFilter(revokedfilter, empid)) {

						logResults.createLogs("Y", "PASS", "On Employee Account Shift Request Revoked Summary Validated Successfully."  +" and status is " + revokedfilter);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Shift Request Revoked Summary Count And Record." + meghpiweekoffrequestpage.getExceptionDesc());
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
