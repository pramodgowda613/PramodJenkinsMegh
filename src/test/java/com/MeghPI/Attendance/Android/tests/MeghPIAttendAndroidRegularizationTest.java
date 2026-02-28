package com.MeghPI.Attendance.Android.tests;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidAttendancePage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidLoginPage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidRegularizationPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.appium.java_client.HasSettings;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;
public class MeghPIAttendAndroidRegularizationTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();


	private String cmpcode = "";
	private String Emailid = "";

	private String officename = "";
	  private String AdminFirstName ="";
	  private String entityname = "";
	
	@Parameters({ "device" })
	@BeforeMethod
	public void launchDriver(int device) { // String param1
		driver = loadDriver.getDriver(device);
		((HasSettings) driver).setSetting("enforceXPath1", true);

		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

	@Parameters({ "device" })
	@BeforeClass
	void runOnce(int device) {
		logResults.createReport(1);
		logResults.setTestMethodErrorCount(0);
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");
		
	}
	
	
	// MPI_1450_Mobile_Regularization_01
			@Test(enabled = true, priority = 1, groups = { "Smoke" })
			public void MPI_1450_Mobile_Regularization_01()   {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, create regularization from employee account and ensure request  is created.");

				  // Load Excel data
				ArrayList<String> data = initBase.loadExcelData("Regularization_Tab", currTC,
						"password,baseuri,loginendpoint,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,photo,starthour,startmin,endhour,regulizationreason,regulizationstatus");


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
			        String starthour = data.get(i++);
			        String startmin = data.get(i++);
			        String endhour = data.get(i++);
			        String regulizationreason = data.get(i++);
			        String regulizationstatus = data.get(i++);
			     
			        
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
			        

			        Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
					String monthFirstWorkingDate = dateDetails.get("month1WorkingDate");
					
			        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

					String firstDayOfMonth = dateInfo.get("firstDayOfMonth");
			        
			        
			        
			        
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
				MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
				MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);

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
			            empid, firstDayOfMonth + "T00:00:00.000Z"
			    );

			    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + empid);
			    } else {
			        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
			    }
	
	
			    if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
					logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				}
				
				if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
					logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				}


				if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
					logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				}

				if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
					logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				}

				if (meghPIAttendAndroidLoginPage.LoginButton()) {
					logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				}
				if (meghpiandroidattendancepage.EmpAccountLoaded()) {
					logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
				}
				if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
					logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
				}
				if (meghpiandroidregularizationpage.RegularizationTabOnEmpAttendance()) {
					logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab On Emp Attendance.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Regulirization Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
				}
				if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
					logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
				}
				if (meghpiandroidregularizationpage.ApplyRegularizationTabOnEmpAttendance()) {
					logResults.createLogs("Y", "PASS", "Clicked On Apply Regulirization Button On Quick Action.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Apply Regulirization Button On Quick Action." + meghpiandroidregularizationpage.getExceptionDesc());
				}
			
				if (meghpiandroidattendancepage.RegulirizationFromDate()) {
					logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Date.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
				}
				
				if (meghpiandroidregularizationpage.selectDate(monthFirstWorkingDate)) {
 					logResults.createLogs("Y", "PASS", "Clicked On 2nd Date." + monthFirstWorkingDate);
 				} else {
 					logResults.createLogs("Y", "FAIL",
 							"Error While Clicking On 2nd Date." + meghpiandroidregularizationpage.getExceptionDesc());
 				}
 				if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
 					logResults.createLogs("Y", "PASS", "Regulirization From Date Selected Successfully.");
 				} else {
 					logResults.createLogs("Y", "FAIL",
 							"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
 				}
 				
 				if (meghpiandroidattendancepage.RegulirizationFromTimeTextField()) {
 					logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Time TextField.");
 				} else {
 					logResults.createLogs("Y", "FAIL",
 							"Error While Clicking On Regulirization From Time TextField." + meghpiandroidattendancepage.getExceptionDesc());
 				}
 				if (meghpiandroidattendancepage.RegulirizationFromTimeKeyboardEnable()) {
 					logResults.createLogs("Y", "PASS", "Regulirization From Time Keyboard Enabled Successfully.");
 				} else {
 					logResults.createLogs("Y", "FAIL",
 							"Error While Clicking On Regulirization From Time Keyboard." + meghpiandroidattendancepage.getExceptionDesc());
 				}
 				if (meghpiandroidattendancepage.clickTime(starthour, startmin)) {
 					logResults.createLogs("Y", "PASS", "Regulirization From Time And Min Inputted Successfully." + starthour + " " + startmin);
 				} else {
 					logResults.createLogs("Y", "FAIL",
 							"Error While Inputting Regulirization From Time And Min." + meghpiandroidattendancepage.getExceptionDesc());
 				}
 				
 				if (meghpiandroidattendancepage.RegulirizationFromTimeHoursSelected()) {
 					logResults.createLogs("Y", "PASS", "Regulirization From Time Selected Successfully.");
 				} else {
 					logResults.createLogs("Y", "FAIL",
 							"Error While Selecting Regulirization From Time." + meghpiandroidattendancepage.getExceptionDesc());
 				}
 				if (meghpiandroidattendancepage.RegulirizationToDateClicked()) {
 					logResults.createLogs("Y", "PASS", "Clicked On Regulirization To Date.");
 				} else {
 					logResults.createLogs("Y", "FAIL",
 							"Error While Clicking On Regulirization To Date." + meghpiandroidattendancepage.getExceptionDesc());
 				}
 				if (meghpiandroidregularizationpage.selectToDate(monthFirstWorkingDate)) {
 					logResults.createLogs("Y", "PASS", "Clicked On To Date." + monthFirstWorkingDate);
 				} else {
 					logResults.createLogs("Y", "FAIL",
 							"Error While Clicking On To Date." + meghpiandroidregularizationpage.getExceptionDesc());
 				}
				if (meghpiandroidattendancepage.RegulirizationToDateSelected()) {
					logResults.createLogs("Y", "PASS", "Regulirization To Date Selected Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Regulirization To Date." + meghpiandroidattendancepage.getExceptionDesc());
				}
				
				if (meghpiandroidattendancepage.RegulirizationToTimeClicked()) {
					logResults.createLogs("Y", "PASS", "Clicked On Regulirization To Time TextField.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Regulirization To Time TextField." + meghpiandroidattendancepage.getExceptionDesc());
				}
				if (meghpiandroidattendancepage.RegulirizationToTimeKeyBoardEnabled()) {
					logResults.createLogs("Y", "PASS", "Regulirization To Time Keyboard Enabled Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Regulirization To Time Keyboard." + meghpiandroidattendancepage.getExceptionDesc());
				}
				if (meghpiandroidattendancepage.clickToTime(endhour, startmin)) {
					logResults.createLogs("Y", "PASS", "Regulirization To Time And Min Inputted Successfully." + endhour + " " + startmin);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Regulirization To Time And Min." + meghpiandroidattendancepage.getExceptionDesc());
				}
				
				if (meghpiandroidattendancepage.RegulirizationToTimeSelected()) {
					logResults.createLogs("Y", "PASS", "Regulirization To Time Selected Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Selecting Regulirization To Time." + meghpiandroidattendancepage.getExceptionDesc());
				}
				if (meghpiandroidattendancepage.RegularizationTypeClicked()) {
					logResults.createLogs("Y", "PASS", "Regularization Type Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Regularization Type." + meghpiandroidattendancepage.getExceptionDesc());
				}
				if (meghpiandroidattendancepage.RegulirizationTypeSelected()) {
					logResults.createLogs("Y", "PASS", "Regularization Type Selected Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Selecting Regularization Type." + meghpiandroidattendancepage.getExceptionDesc());
				}
				if (meghpiandroidregularizationpage.RegulirizationReasonSearchTextField(regulizationreason)) {
					logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regulizationreason);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Regularization Reason." + meghpiandroidregularizationpage.getExceptionDesc());
				}
				if (meghpiandroidattendancepage.RegulirizationApplyButton()) {
					logResults.createLogs("Y", "PASS", "Regulirization Apply Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Regulirization Apply Button" + meghpiandroidattendancepage.getExceptionDesc());
				}	
			
				if (meghpiandroidregularizationpage.validateAttendanceCardByDateAndStatus(monthFirstWorkingDate, regulizationstatus)) {
					logResults.createLogs("Y", "PASS", "Applied Regulirization Request Displayed In Employee Account." + monthFirstWorkingDate + " " + regulizationstatus);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Displaying Applied Regulirization Request." + meghpiandroidregularizationpage.getExceptionDesc());
				}	
			}
	
	
			// MPI_1452_Mobile_Regularization_02
						@Test(enabled = true, priority = 2, groups = { "Smoke" })
						public void MPI_1452_Mobile_Regularization_02()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify that  approved the  regularization request from the admin account, validate the request status updates to “Approved” in both the admin and employee accounts.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Regularization_Tab", currTC,
									"password,empid,regulizationstatus,firstname");
							
							String password = data.get(0);
							String empid = data.get(1);
							String regulizationstatus = data.get(2);
							String firstname = data.get(3);
							
							  Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
								String monthFirstWorkingDate = dateDetails.get("month1WorkingDate");
	
							MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
						
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
			                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
			                             
							if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
								logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							
							if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
								logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}


							if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
								logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
								logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.LoginButton()) {
								logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.AttendanceModuleInAdmin()) {
								logResults.createLogs("Y", "PASS", "Clicked On Attendance Module Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Attendance Module Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.RegulirizationTabInAdmin()) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization Tab Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.RegulirizationSearchTextField(firstname)) {
             					logResults.createLogs("Y", "PASS", "Regulirization Request Applied Employee  Name Inputted Successfully."  + " " + firstname);
             				} else {
             					logResults.createLogs("Y", "FAIL",
             							"Error While Inputting  Applied Regulirization Request Emp Name." + meghpiandroidregularizationpage.getExceptionDesc());
             				}
							if (meghpiandroidattendancepage.RegularizationApproveButton(empid, monthFirstWorkingDate)) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization Approve Button." + empid + " " + monthFirstWorkingDate);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization Approve Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							
							
							if (meghpiandroidattendancepage.RegularizationConfirmButton()) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization Approve Confirm Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization Request Approve Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}  
							if (meghpiandroidattendancepage.BackButton()) {
								logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghPIAttendAndroidLoginPage.ManageButton()) {
								logResults.createLogs("Y", "PASS", "Manage Profile Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Manage Profile Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghPIAttendAndroidLoginPage.LogoutButton()) {
								logResults.createLogs("Y", "PASS", "LogOut Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On LogOut Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghPIAttendAndroidLoginPage.LogoutConfirmButton()) {
								logResults.createLogs("Y", "PASS", "Logout Confirm Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Logout Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							} 
							if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
								logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							
							if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
								logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
								logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
								logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.LoginButton()) {
								logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.EmpAccountLoaded()) {
								logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
								logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.RegularizationTabOnEmpAttendance()) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab On Emp Attendance.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.validateAttendanceCardByDateAndStatus(monthFirstWorkingDate, regulizationstatus)) {
								logResults.createLogs("Y", "PASS", "Applied Regulirization Request Displayed In Employee Account." + monthFirstWorkingDate + " " + regulizationstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying Applied Regulirization Request." + meghpiandroidregularizationpage.getExceptionDesc());
							}	
						}
	
	
						// MPI_1453_Mobile_Regularization_03
						@Test(enabled = true, priority = 3, groups = { "Smoke" })
						public void MPI_1453_Mobile_Regularization_03()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, raise a regularization request and ensure the status is displayed as “Pending” in both the admin and employee accounts.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Regularization_Tab", currTC,
									"password,empid,regulizationstatus,starthour,startmin,endhour,regulizationreason,firstname");
							
							String password = data.get(0);
							String empid = data.get(1);
							String regulizationstatus = data.get(2);
							String starthour = data.get(3);
							String startmin = data.get(4);
							String endhour = data.get(5);
							String regulizationreason = data.get(6);
							String firstname = data.get(7);
							
							Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
							String monthSecondWorkingDate = dateDetails.get("month2WorkingDate");
	
							MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
						
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
			                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
			                             
			                             if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			             					logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}
			             				
			             				if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			             					logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}


			             				if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			             					logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
			             				} else {
			             					logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}

			             				if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			             					logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
			             				} else {
			             					logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}

			             				if (meghPIAttendAndroidLoginPage.LoginButton()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			             					logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.RegularizationTabOnEmpAttendance()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab On Emp Attendance.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.ApplyRegularizationTabOnEmpAttendance()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Apply Regulirization Button On Quick Action.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Apply Regulirization Button On Quick Action." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             			
			             				if (meghpiandroidattendancepage.RegulirizationFromDate()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Date.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				
			             				if (meghpiandroidregularizationpage.selectDate(monthSecondWorkingDate)) {
			             					logResults.createLogs("Y", "PASS", "Clicked On 2nd Date." + monthSecondWorkingDate);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On 2nd Date." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization From Date Selected Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				
			             				if (meghpiandroidattendancepage.RegulirizationFromTimeTextField()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Time TextField.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization From Time TextField." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationFromTimeKeyboardEnable()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization From Time Keyboard Enabled Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization From Time Keyboard." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.clickTime(starthour, startmin)) {
			             					logResults.createLogs("Y", "PASS", "Regulirization From Time And Min Inputted Successfully." + starthour + " " + startmin);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Inputting Regulirization From Time And Min." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				
			             				if (meghpiandroidattendancepage.RegulirizationFromTimeHoursSelected()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization From Time Selected Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Selecting Regulirization From Time." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationToDateClicked()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization To Date.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization To Date." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.selectToDate(monthSecondWorkingDate)) {
			             					logResults.createLogs("Y", "PASS", "Clicked On To Date." + monthSecondWorkingDate);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On To Date." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationToDateSelected()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization To Date Selected Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization To Date." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				
			             				if (meghpiandroidattendancepage.RegulirizationToTimeClicked()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization To Time TextField.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization To Time TextField." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationToTimeKeyBoardEnabled()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization To Time Keyboard Enabled Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization To Time Keyboard." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.clickToTime(endhour, startmin)) {
			             					logResults.createLogs("Y", "PASS", "Regulirization To Time And Min Inputted Successfully." + endhour + " " + startmin);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Inputting Regulirization To Time And Min." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				
			             				if (meghpiandroidattendancepage.RegulirizationToTimeSelected()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization To Time Selected Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Selecting Regulirization To Time." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegularizationTypeClicked()) {
			             					logResults.createLogs("Y", "PASS", "Regularization Type Clicked Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regularization Type." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationTypeSelected()) {
			             					logResults.createLogs("Y", "PASS", "Regularization Type Selected Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Selecting Regularization Type." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.RegulirizationReasonSearchTextField(regulizationreason)) {
			             					logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regulizationreason);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Inputting Regularization Reason." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationApplyButton()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization Apply Button Clicked Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization Apply Button" + meghpiandroidattendancepage.getExceptionDesc());
			             				}	
			             				
			             				if (meghpiandroidregularizationpage.validateAttendanceCardByDateAndStatus(monthSecondWorkingDate, regulizationstatus)) {
			             					logResults.createLogs("Y", "PASS", "Applied Regulirization Request Displayed In Employee Account." + monthSecondWorkingDate + " " + regulizationstatus);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Displaying Applied Regulirization Request." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}	
			             				if (meghpiandroidattendancepage.BackButton()) {
											logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
										}
			             				if (meghPIAttendAndroidLoginPage.ManageButton()) {
											logResults.createLogs("Y", "PASS", "Manage Profile Button Is Clicked.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Manage Profile Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										if (meghPIAttendAndroidLoginPage.LogoutButton()) {
											logResults.createLogs("Y", "PASS", "LogOut Button Is Clicked.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On LogOut Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										if (meghPIAttendAndroidLoginPage.LogoutConfirmButton()) {
											logResults.createLogs("Y", "PASS", "Logout Confirm Button Is Clicked.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Logout Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
			             				if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
											logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
										} else {
											logResults.createLogs("Y", "FAIL",
													"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										
										if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
											logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}


										if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
											logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}

										if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
											logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}

										if (meghPIAttendAndroidLoginPage.LoginButton()) {
											logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										if (meghpiandroidattendancepage.AttendanceModuleInAdmin()) {
											logResults.createLogs("Y", "PASS", "Clicked On Attendance Module Button.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Attendance Module Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										if (meghpiandroidattendancepage.RegulirizationTabInAdmin()) {
											logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Regulirization Tab Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										
										if (meghpiandroidregularizationpage.RegulirizationSearchTextField(firstname)) {
			             					logResults.createLogs("Y", "PASS", "Regulirization Request Applied Employee  Name Inputted Successfully."  + " " + firstname);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Inputting  Applied Regulirization Request Emp Name." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}	
										if (meghpiandroidregularizationpage.validateAttendanceCardByDateAndStatusOnAmin(monthSecondWorkingDate, regulizationstatus)) {
											logResults.createLogs("Y", "PASS", "Applied Regulirization Request Displayed In Employee Account." + monthSecondWorkingDate + " " + regulizationstatus);
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying Applied Regulirization Request." + meghpiandroidregularizationpage.getExceptionDesc());
										}	
						}
	
	
						// MPI_1454_Mobile_Regularization_04
						@Test(enabled = true, priority = 4, groups = { "Smoke" })
						public void MPI_1454_Mobile_Regularization_04()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, reject the employee’s regularization request as an admin and ensure the status is displayed as “Rejected” on both the employee and admin accounts.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Regularization_Tab", currTC,
									"password,empid,regulizationstatus,firstname");
							
							String password = data.get(0);
							String empid = data.get(1);
							String regulizationstatus = data.get(2);
							String firstname = data.get(3);
							
							Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
							String monthSecondWorkingDate = dateDetails.get("month2WorkingDate");
	
							MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
						
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
			                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
			                            
							if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
								logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							
							if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
								logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}


							if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
								logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
								logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.LoginButton()) {
								logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.AttendanceModuleInAdmin()) {
								logResults.createLogs("Y", "PASS", "Clicked On Attendance Module Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Attendance Module Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.RegulirizationTabInAdmin()) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization Tab Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.RegulirizationSearchTextField(firstname)) {
             					logResults.createLogs("Y", "PASS", "Regulirization Request Applied Employee  Name Inputted Successfully."  + " " + firstname);
             				} else {
             					logResults.createLogs("Y", "FAIL",
             							"Error While Inputting  Applied Regulirization Request Emp Name." + meghpiandroidregularizationpage.getExceptionDesc());
             				}
							
							if (meghpiandroidattendancepage.RegularizationRejectButton(empid, monthSecondWorkingDate)) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization Reject Button." + empid + " " + monthSecondWorkingDate);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization Reject Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							
							
							if (meghpiandroidattendancepage.RegularizationConfirmButton()) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization Reject Confirm Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization Request Reject Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}  
							if (meghpiandroidregularizationpage.validateAttendanceCardByDateAndStatusOnAmin(monthSecondWorkingDate, regulizationstatus)) {
								logResults.createLogs("Y", "PASS", "Applied Regulirization Request Displayed In Employee Account." + monthSecondWorkingDate + " " + regulizationstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying Applied Regulirization Request." + meghpiandroidregularizationpage.getExceptionDesc());
							}	
							if (meghpiandroidattendancepage.BackButton()) {
								logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghPIAttendAndroidLoginPage.ManageButton()) {
								logResults.createLogs("Y", "PASS", "Manage Profile Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Manage Profile Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghPIAttendAndroidLoginPage.LogoutButton()) {
								logResults.createLogs("Y", "PASS", "LogOut Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On LogOut Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghPIAttendAndroidLoginPage.LogoutConfirmButton()) {
								logResults.createLogs("Y", "PASS", "Logout Confirm Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Logout Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}   
							if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
								logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							
							if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
								logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
								logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
								logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.LoginButton()) {
								logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.EmpAccountLoaded()) {
								logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
								logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.RegularizationTabOnEmpAttendance()) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab On Emp Attendance.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							
							if (meghpiandroidregularizationpage.clickRejectedCountOnMonthlyCard()) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization Reject Tab.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization Reject Tab." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							
							if (meghpiandroidregularizationpage.validateAttendanceCardByDateAndStatus(monthSecondWorkingDate, regulizationstatus)) {
								logResults.createLogs("Y", "PASS", "Applied Regulirization Request Displayed In Employee Account." + monthSecondWorkingDate + " " + regulizationstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying Applied Regulirization Request." + meghpiandroidregularizationpage.getExceptionDesc());
							}	
						}			
						
						// MPI_1456_Mobile_Regularization_05
						@Test(enabled = true, priority = 5, groups = { "Smoke" })
						public void MPI_1456_Mobile_Regularization_05()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, raise a regularization request, then revoke it, and ensure the record is deleted successfully and the status is displayed as Revoked in the admin account.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Regularization_Tab", currTC,
									"password,empid,regulizationstatus,starthour,startmin,endhour,regulizationreason,firstname");
							
							String password = data.get(0);
							String empid = data.get(1);
							String regulizationstatus = data.get(2);
							String starthour = data.get(3);
							String startmin = data.get(4);
							String endhour = data.get(5);
							String regulizationreason = data.get(6);
							String firstname = data.get(7);
							
							Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
							String monthThirdWorkingDate = dateDetails.get("month3WorkingDate");

	
							MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
						
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
			                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
			                             
			                             if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			             					logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}
			             				
			             				if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			             					logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}


			             				if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			             					logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
			             				} else {
			             					logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}

			             				if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			             					logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
			             				} else {
			             					logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}

			             				if (meghPIAttendAndroidLoginPage.LoginButton()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			             					logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.RegularizationTabOnEmpAttendance()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab On Emp Attendance.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.ApplyRegularizationTabOnEmpAttendance()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Apply Regulirization Button On Quick Action.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Apply Regulirization Button On Quick Action." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             			
			             				if (meghpiandroidattendancepage.RegulirizationFromDate()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Date.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				
			             				if (meghpiandroidregularizationpage.selectDate(monthThirdWorkingDate)) {
			             					logResults.createLogs("Y", "PASS", "Clicked On 2nd Date." + monthThirdWorkingDate);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On 2nd Date." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization From Date Selected Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				
			             				if (meghpiandroidattendancepage.RegulirizationFromTimeTextField()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Time TextField.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization From Time TextField." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationFromTimeKeyboardEnable()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization From Time Keyboard Enabled Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization From Time Keyboard." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.clickTime(starthour, startmin)) {
			             					logResults.createLogs("Y", "PASS", "Regulirization From Time And Min Inputted Successfully." + starthour + " " + startmin);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Inputting Regulirization From Time And Min." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				
			             				if (meghpiandroidattendancepage.RegulirizationFromTimeHoursSelected()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization From Time Selected Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Selecting Regulirization From Time." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationToDateClicked()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization To Date.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization To Date." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.selectToDate(monthThirdWorkingDate)) {
			             					logResults.createLogs("Y", "PASS", "Clicked On To Date." + monthThirdWorkingDate);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On To Date." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationToDateSelected()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization To Date Selected Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization To Date." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				
			             				if (meghpiandroidattendancepage.RegulirizationToTimeClicked()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization To Time TextField.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization To Time TextField." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationToTimeKeyBoardEnabled()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization To Time Keyboard Enabled Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization To Time Keyboard." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.clickToTime(endhour, startmin)) {
			             					logResults.createLogs("Y", "PASS", "Regulirization To Time And Min Inputted Successfully." + endhour + " " + startmin);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Inputting Regulirization To Time And Min." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				
			             				if (meghpiandroidattendancepage.RegulirizationToTimeSelected()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization To Time Selected Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Selecting Regulirization To Time." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegularizationTypeClicked()) {
			             					logResults.createLogs("Y", "PASS", "Regularization Type Clicked Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regularization Type." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationTypeSelected()) {
			             					logResults.createLogs("Y", "PASS", "Regularization Type Selected Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Selecting Regularization Type." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.RegulirizationReasonSearchTextField(regulizationreason)) {
			             					logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regulizationreason);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Inputting Regularization Reason." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegulirizationApplyButton()) {
			             					logResults.createLogs("Y", "PASS", "Regulirization Apply Button Clicked Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization Apply Button" + meghpiandroidattendancepage.getExceptionDesc());
			             				}	
			             				if (meghpiandroidregularizationpage.clickPendingCountOnMonthlyCard()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Pending Card.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Pending Card." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.RegulirizationRequestRevokeButton    ()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Revoke Button Of Regularization Request.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Revoke Button." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.RegularizationConfirmButton()) {
											logResults.createLogs("Y", "PASS", "Clicked On Regulirization Revoke Confirm Button.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Regulirization Request Revoke Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}  
			             				
			             				if (meghpiandroidregularizationpage.clickRevokedTabOnEmpAttendance()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Revoked Card.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Revoked Card." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.validateAttendanceStatusByDate(monthThirdWorkingDate, regulizationstatus)) {
			             					logResults.createLogs("Y", "PASS", "Revoked Request Displayed Successfully." + regulizationstatus + " " + monthThirdWorkingDate );
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Validating Revoked Request." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				
			             				
			             				
			             				if (meghpiandroidattendancepage.BackButton()) {
											logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
										}
			             				if (meghPIAttendAndroidLoginPage.ManageButton()) {
											logResults.createLogs("Y", "PASS", "Manage Profile Button Is Clicked.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Manage Profile Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										if (meghPIAttendAndroidLoginPage.LogoutButton()) {
											logResults.createLogs("Y", "PASS", "LogOut Button Is Clicked.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On LogOut Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										if (meghPIAttendAndroidLoginPage.LogoutConfirmButton()) {
											logResults.createLogs("Y", "PASS", "Logout Confirm Button Is Clicked.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Logout Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
			             				if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
											logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
										} else {
											logResults.createLogs("Y", "FAIL",
													"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										
										if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
											logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}


										if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
											logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}

										if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
											logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
										} else {
											logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}

										if (meghPIAttendAndroidLoginPage.LoginButton()) {
											logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										if (meghpiandroidattendancepage.AttendanceModuleInAdmin()) {
											logResults.createLogs("Y", "PASS", "Clicked On Attendance Module Button.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Attendance Module Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										if (meghpiandroidattendancepage.RegulirizationTabInAdmin()) {
											logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Regulirization Tab Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										
										if (meghpiandroidregularizationpage.RegulirizationSearchTextField(firstname)) {
			             					logResults.createLogs("Y", "PASS", "Regulirization Request Applied Employee  Name Inputted Successfully."  + " " + firstname);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Inputting  Applied Regulirization Request Emp Name." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}	
										if (meghpiandroidregularizationpage.validateAttendanceCardByDateAndStatusOnAmin(monthThirdWorkingDate, regulizationstatus)) {
											logResults.createLogs("Y", "PASS", "Applied Regulirization Request Displayed In Employee Account." + monthThirdWorkingDate + " " + regulizationstatus);
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Displaying Applied Regulirization Request." + meghpiandroidregularizationpage.getExceptionDesc());
										}	
						}
						
						
						// MPI_1461_Mobile_Regularization_08
						@Test(enabled = true, priority = 6, groups = { "Smoke" })
						public void MPI_1461_Mobile_Regularization_08()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, configure the Regularization Request limit up to 31 days and ensure that the employee cannot raise a request beyond 31 days.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Regularization_Tab", currTC,
									"empid");
							
							
							String empid = data.get(0);
							
							
							String backDate = Pramod.getDateNDaysBack(35);


	
							MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
						
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
			                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
			                             
			                             if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			             					logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}
			             				
			             				if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			             					logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}


			             				if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			             					logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
			             				} else {
			             					logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}

			             				if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			             					logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
			             				} else {
			             					logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}

			             				if (meghPIAttendAndroidLoginPage.LoginButton()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			             					logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.RegularizationTabOnEmpAttendance()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab On Emp Attendance.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.ApplyRegularizationTabOnEmpAttendance()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Apply Regulirization Button On Quick Action.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Apply Regulirization Button On Quick Action." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             			
			             				if (meghpiandroidattendancepage.RegulirizationFromDate()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Date.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				
			             				if (meghpiandroidregularizationpage.select31beforeDate(backDate)) {
			             					logResults.createLogs("Y", "PASS", "Try To Clicked On 31 days Back Date." + backDate);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On 31days Back Date." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}		
						
						}
						
						// MPI_1462_Mobile_Regularization_09
						@Test(enabled = true, priority = 7, groups = { "Smoke" })
						public void MPI_1462_Mobile_Regularization_09()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the summary count details in the employee account for pending, rejected, approved, and total requests.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Regularization_Tab", currTC,
									"empid,pending,pendingcount,approved,approvedcount,revoked,revokedcount,rejected,rejectedcount,taken,takencount");
							
							
							String empid = data.get(0);
							String pending = data.get(1);
							String pendingcount = data.get(2);
							String approved = data.get(3);
							String approvedcount = data.get(4);
							String revoked = data.get(5);
							String revokedcount = data.get(6);
							String rejected = data.get(7);
							String rejectedcount = data.get(8);
							String taken = data.get(9);
							String takencount = data.get(10);
							
					
							MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
						
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
			                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
			                             
			                             if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			             					logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}
			             				
			             				if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			             					logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}


			             				if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
			             					logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
			             				} else {
			             					logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}

			             				if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
			             					logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
			             				} else {
			             					logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}

			             				if (meghPIAttendAndroidLoginPage.LoginButton()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.EmpAccountLoaded()) {
			             					logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.RegularizationTabOnEmpAttendance()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab On Emp Attendance.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Regulirization Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.validateLeaveCardCount(pending, pendingcount)) {
			             					logResults.createLogs("Y", "PASS", "Pending Regularization Request Count Validated Successfully." + pending + " " + pendingcount);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Validating Pending Regularization Count." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.validateLeaveCardCount(approved, approvedcount)) {
			             					logResults.createLogs("Y", "PASS", "Approved Regularization Request Count Validated Successfully." + approved + " " + approvedcount);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Validating Approved Regularization Count." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.validateLeaveCardCount(revoked, revokedcount)) {
			             					logResults.createLogs("Y", "PASS", "Revoked Regularization Request Count Validated Successfully." + revoked + " " + revokedcount);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Validating Revoked Regularization Count." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.validateLeaveCardCount(taken, takencount)) {
			             					logResults.createLogs("Y", "PASS", "Taken Regularization Request Count Validated Successfully." + taken + " " + takencount);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Validating Taken Regularization Count." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.validateLeaveCardCount(rejected, rejectedcount)) {
			             					logResults.createLogs("Y", "PASS", "Rejected Regularization Request Count Validated Successfully." + rejected + " " + rejectedcount);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Validating Rejected Regularization Count." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
						
						}
						
						
						// MPI_1463_Mobile_Regularization_10
						@Test(enabled = true, priority = 8, groups = { "Smoke" })
						public void MPI_1463_Mobile_Regularization_10()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the summary count details in the Admin account for pending, rejected, approved, and total requests.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Regularization_Tab", currTC,
									"empid,pending,pendingcount,approved,approvedcount,revoked,revokedcount,rejected,rejectedcount,taken,takencount,password");
							
							
							String empid = data.get(0);
							String pending = data.get(1);
							String pendingcount = data.get(2);
							String approved = data.get(3);
							String approvedcount = data.get(4);
							String revoked = data.get(5);
							String revokedcount = data.get(6);
							String rejected = data.get(7);
							String rejectedcount = data.get(8);
							String taken = data.get(9);
							String takencount = data.get(10);
							String password = data.get(11);
							
					
							MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
						
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
			                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
			                             
			                             if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			             					logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}
			             				
			             				if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			             					logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}


			             				if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
			             					logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
			             				} else {
			             					logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}

			             				if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
			             					logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
			             				} else {
			             					logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}

			             				if (meghPIAttendAndroidLoginPage.LoginButton()) {
			             					logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			             				}
			             				if (meghpiandroidattendancepage.AttendanceModuleInAdmin()) {
											logResults.createLogs("Y", "PASS", "Clicked On Attendance Module Button.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Attendance Module Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										if (meghpiandroidattendancepage.RegulirizationTabInAdmin()) {
											logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab.");
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Regulirization Tab Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
										}
										if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
											logResults.createLogs("Y", "PASS", "Clicked On Filter Button In Regulirization Tab." + empid);
										} else {
											logResults.createLogs("Y", "FAIL",
													"Error While Clicking On Filter Button On Regulirization Tab." + meghpiandroidregularizationpage.getExceptionDesc());
										}
									
			             				if (meghpiandroidregularizationpage.validateLeaveCardCount(pending, pendingcount)) {
			             					logResults.createLogs("Y", "PASS", "Pending Regularization Request Count Validated Successfully." + pending + " " + pendingcount);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Validating Pending Regularization Count." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.validateLeaveCardCount(approved, approvedcount)) {
			             					logResults.createLogs("Y", "PASS", "Approved Regularization Request Count Validated Successfully." + approved + " " + approvedcount);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Validating Approved Regularization Count." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.validateLeaveCardCount(revoked, revokedcount)) {
			             					logResults.createLogs("Y", "PASS", "Revoked Regularization Request Count Validated Successfully." + revoked + " " + revokedcount);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Validating Revoked Regularization Count." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.validateLeaveCardCount(taken, takencount)) {
			             					logResults.createLogs("Y", "PASS", "Taken Regularization Request Count Validated Successfully." + taken + " " + takencount);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Validating Taken Regularization Count." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
			             				if (meghpiandroidregularizationpage.validateLeaveCardCount(rejected, rejectedcount)) {
			             					logResults.createLogs("Y", "PASS", "Rejected Regularization Request Count Validated Successfully." + rejected + " " + rejectedcount);
			             				} else {
			             					logResults.createLogs("Y", "FAIL",
			             							"Error While Validating Rejected Regularization Count." + meghpiandroidregularizationpage.getExceptionDesc());
			             				}
						
						}		
						
						// MPI_1494_Mobile_Regularization_11
						@Test(enabled = true, priority = 9, groups = { "Smoke" })
						public void MPI_1494_Mobile_Regularization_11()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, raise a regularization request from the admin account for an employee and ensure that the request is created with the “Approved” status and is displayed on the employee side.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Regularization_Tab", currTC,
									"password,empid,regulizationstatus,starthour,startmin,endhour,regulizationreason,firstname");
							
							String password = data.get(0);
							String empid = data.get(1);
							String regulizationstatus = data.get(2);
							String starthour = data.get(3);
							String startmin = data.get(4);
							String endhour = data.get(5);
							String regulizationreason = data.get(6);
							String firstname = data.get(7);
							
							Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
							String fourthWorkingDay = dateDetails.get("month4WorkingDate");
							
	
							MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
						
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
			                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
			                         
							if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
								logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							
							if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
								logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}


							if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
								logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
								logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.LoginButton()) {
								logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.AttendanceModuleInAdmin()) {
								logResults.createLogs("Y", "PASS", "Clicked On Attendance Module Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Attendance Module Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.RegulirizationTabInAdmin()) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization Tab Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
								logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.ApplyRegularizationTabOnEmpAttendance()) {
								logResults.createLogs("Y", "PASS", "Clicked On Apply Regulirization Button On Quick Action.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Apply Regulirization Button On Quick Action." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.RegulirizationRequestForOthersButton()) {
								logResults.createLogs("Y", "PASS", " Apply Regulirization For OThers Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Apply Regulirization For Others." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.inputRegularizationEmpName(firstname)) {
								logResults.createLogs("Y", "PASS", " To Apply Regulirization For OThers Emp Name Inputted." + firstname);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Emp Name While Applying Regulirization ." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.RegulirizationRequestEmpNameSelected()) {
								logResults.createLogs("Y", "PASS", "To Apply Regulirization For OThers Emp Name Selected.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Emp Name." + meghpiandroidregularizationpage.getExceptionDesc());
							}
						
							if (meghpiandroidregularizationpage.RegulirizationRequestFromDateAdmin()) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Date.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization From Date." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							
							if (meghpiandroidregularizationpage.selectDate(fourthWorkingDay)) {
			 					logResults.createLogs("Y", "PASS", "Clicked On 2nd Date." + fourthWorkingDay);
			 				} else {
			 					logResults.createLogs("Y", "FAIL",
			 							"Error While Clicking On 2nd Date." + meghpiandroidregularizationpage.getExceptionDesc());
			 				}
			 				if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
			 					logResults.createLogs("Y", "PASS", "Regulirization From Date Selected Successfully.");
			 				} else {
			 					logResults.createLogs("Y", "FAIL",
			 							"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
			 				}
			 				
			 				if (meghpiandroidregularizationpage.RegulirizationRequestFromTimeAdmin()) {
			 					logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Time TextField.");
			 				} else {
			 					logResults.createLogs("Y", "FAIL",
			 							"Error While Clicking On Regulirization From Time TextField." + meghpiandroidregularizationpage.getExceptionDesc());
			 				}
			 				if (meghpiandroidattendancepage.RegulirizationFromTimeKeyboardEnable()) {
			 					logResults.createLogs("Y", "PASS", "Regulirization From Time Keyboard Enabled Successfully.");
			 				} else {
			 					logResults.createLogs("Y", "FAIL",
			 							"Error While Clicking On Regulirization From Time Keyboard." + meghpiandroidattendancepage.getExceptionDesc());
			 				}
			 				if (meghpiandroidattendancepage.clickTime(starthour, startmin)) {
			 					logResults.createLogs("Y", "PASS", "Regulirization From Time And Min Inputted Successfully." + starthour + " " + startmin);
			 				} else {
			 					logResults.createLogs("Y", "FAIL",
			 							"Error While Inputting Regulirization From Time And Min." + meghpiandroidattendancepage.getExceptionDesc());
			 				}
			 				
			 				if (meghpiandroidattendancepage.RegulirizationFromTimeHoursSelected()) {
			 					logResults.createLogs("Y", "PASS", "Regulirization From Time Selected Successfully.");
			 				} else {
			 					logResults.createLogs("Y", "FAIL",
			 							"Error While Selecting Regulirization From Time." + meghpiandroidattendancepage.getExceptionDesc());
			 				}
			 				if (meghpiandroidregularizationpage.RegulirizationRequestToDateAdmin()) {
			 					logResults.createLogs("Y", "PASS", "Clicked On Regulirization To Date.");
			 				} else {
			 					logResults.createLogs("Y", "FAIL",
			 							"Error While Clicking On Regulirization To Date." + meghpiandroidregularizationpage.getExceptionDesc());
			 				}
			 				if (meghpiandroidregularizationpage.selectToDate(fourthWorkingDay)) {
			 					logResults.createLogs("Y", "PASS", "Clicked On To Date." + fourthWorkingDay);
			 				} else {
			 					logResults.createLogs("Y", "FAIL",
			 							"Error While Clicking On To Date." + meghpiandroidregularizationpage.getExceptionDesc());
			 				}
							if (meghpiandroidattendancepage.RegulirizationToDateSelected()) {
								logResults.createLogs("Y", "PASS", "Regulirization To Date Selected Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization To Date." + meghpiandroidattendancepage.getExceptionDesc());
							}
							
							if (meghpiandroidregularizationpage.RegulirizationRequestToTimeAdmin()) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization To Time TextField.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization To Time TextField." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.RegulirizationToTimeKeyBoardEnabled()) {
								logResults.createLogs("Y", "PASS", "Regulirization To Time Keyboard Enabled Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization To Time Keyboard." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.clickToTime(endhour, startmin)) {
								logResults.createLogs("Y", "PASS", "Regulirization To Time And Min Inputted Successfully." + endhour + " " + startmin);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Regulirization To Time And Min." + meghpiandroidattendancepage.getExceptionDesc());
							}
							
							if (meghpiandroidattendancepage.RegulirizationToTimeSelected()) {
								logResults.createLogs("Y", "PASS", "Regulirization To Time Selected Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Regulirization To Time." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.RegulirizationReasonSearchTextField(regulizationreason)) {
								logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regulizationreason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Regularization Reason." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.RegulirizationRequestTypeDropDownAdmin()) {
								logResults.createLogs("Y", "PASS", "Regularization Type Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regularization Type." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.RegulirizationTypeSelected()) {
								logResults.createLogs("Y", "PASS", "Regularization Type Selected Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Regularization Type." + meghpiandroidattendancepage.getExceptionDesc());
							}
							
							if (meghpiandroidattendancepage.RegulirizationApplyButton()) {
								logResults.createLogs("Y", "PASS", "Regulirization Apply Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization Apply Button" + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.BackButton()) {
								logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghPIAttendAndroidLoginPage.ManageButton()) {
								logResults.createLogs("Y", "PASS", "Manage Profile Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Manage Profile Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghPIAttendAndroidLoginPage.LogoutButton()) {
								logResults.createLogs("Y", "PASS", "LogOut Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On LogOut Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghPIAttendAndroidLoginPage.LogoutConfirmButton()) {
								logResults.createLogs("Y", "PASS", "Logout Confirm Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Logout Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							} 
							if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
								logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							
							if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
								logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
								logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
								logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}

							if (meghPIAttendAndroidLoginPage.LoginButton()) {
								logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.EmpAccountLoaded()) {
								logResults.createLogs("Y", "PASS", "Emp Attendance DashBoard Loaded Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Loading Emp Attendance DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
								logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.RegularizationTabOnEmpAttendance()) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization Tab On Emp Attendance.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							if (meghpiandroidregularizationpage.validateAttendanceCardByDateAndStatus(fourthWorkingDay, regulizationstatus)) {
								logResults.createLogs("Y", "PASS", "Applied Regulirization Request Displayed In Employee Account." + fourthWorkingDay + " " + regulizationstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying Applied Regulirization Request." + meghpiandroidregularizationpage.getExceptionDesc());
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
