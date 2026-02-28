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
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidLeavePage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidLoginPage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidRegularizationPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;
public class MeghPIAttendAndroidAttendanceTest {
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
	
	
	// MPI_1388_Attendance_Tab_01
			@Test(enabled = false, priority = 80, groups = { "Smoke" })
			public void MPI_1388_Attendance_Tab_01()   {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, login as employee and Click on quick action and choose apply regularization and raise the regularization request and ensure request is created.");

				  // Load Excel data
				ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
						"password,baseuri,loginendpoint,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,photo,starthour,startmin,endhour,regulizationreason");


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
				    	    logResults.createLogs("N", "PASS", "Enrollment executed successfully for Employee ID." + empid);
				    	} else {
				    	    logResults.createLogs("N", "FAIL", "Enrollment failed for Employee ID." + empid +
				    	                          " | Response." + enrollResponse.asString());
				    	    return; // ❌ stop execution if enrollment fails
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
				if (meghpiandroidattendancepage.RegulirizationReasonTextField(regulizationreason)) {
					logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regulizationreason);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Inputting Regularization Reason." + meghpiandroidattendancepage.getExceptionDesc());
				}
				if (meghpiandroidattendancepage.RegulirizationApplyButton()) {
					logResults.createLogs("Y", "PASS", "Regulirization Apply Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Regulirization Apply Button" + meghpiandroidattendancepage.getExceptionDesc());
				}	
			}
	
			// MPI_1389_Attendance_Tab_02
						@Test(enabled = false, priority = 81, groups = { "Smoke" })
						public void MPI_1389_Attendance_Tab_02()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, approve the regularization request and ensure that the employee’s regularization request date is marked as present.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"password,empid,empstatus,firstname");
							
							String password = data.get(0);
							String empid = data.get(1);
							String empstatus = data.get(2);
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
							
							if (meghpiandroidattendancepage.isFirstDayOfMonthVisible()) {
								logResults.createLogs("Y", "PASS", "Attendance Page Loaded Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying Attendance Page." + meghpiandroidattendancepage.getExceptionDesc());
							}
							
							if (meghpiandroidattendancepage.validateFirstDayRecord(empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully." + empid + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status." + meghpiandroidattendancepage.getExceptionDesc());
							}
							
						}				
							
						// MPI_630_Normal_Attendance_02
						@Test(enabled = false, priority = 3, groups = { "Smoke" })
						public void MPI_630_Normal_Attendance_02()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee performs only a single punch in a day.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"cmpcode,employeeuniqueid,empstatus,date");
							
							String cmpcode = data.get(0);
							String empid = data.get(1);
							String empstatus = data.get(2);
							String date =  data.get(3);
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}
						
						// MPI_632_Normal_Attendance_04
						@Test(enabled = false, priority = 4, groups = { "Smoke" })
						public void MPI_632_Normal_Attendance_04()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee has not completed minimum hours required for half day.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"cmpcode,employeeuniqueid,empstatus,date");
							
							String cmpcode = data.get(0);
							String empid = data.get(1);
							String empstatus = data.get(2);
							String date =  data.get(3);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}
							
						// MPI_635_Normal_Attendance_07
						@Test(enabled = false, priority = 5, groups = { "Smoke" })
						public void MPI_635_Normal_Attendance_07()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee is on a week off.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"cmpcode,employeeuniqueid,empstatus,date");
							
							String cmpcode = data.get(0);
							String empid = data.get(1);
							String empstatus = data.get(2);
							String date =  data.get(3);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateweekoff(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
							
						// MPI_636_Normal_Attendance_08
						@Test(enabled = false, priority = 6, groups = { "Smoke" })
						public void MPI_636_Normal_Attendance_08()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee is absent on a working day.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"cmpcode,employeeuniqueid,empstatus,date");
							
							String cmpcode = data.get(0);
							String empid = data.get(1);
							String empstatus = data.get(2);
							String date =  data.get(3);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
							
						// MPI_637_Normal_Attendance_09
						@Test(enabled = false, priority = 7, groups = { "Smoke" })
						public void MPI_637_Normal_Attendance_09()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status after the employee raises a regularization request for an absent day and it is approved.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"cmpcode,employeeuniqueid,empstatus,date");
							
							String cmpcode = data.get(0);
							String empid = data.get(1);
							String empstatus = data.get(2);
							String date =  data.get(3);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						// MPI_638_Normal_Attendance_10
						@Test(enabled = false, priority = 8, groups = { "Smoke" })
						public void MPI_638_Normal_Attendance_10()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee has applied for leave and it has been approved for that day.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}
						
						
						// MPI_641_Normal_Attendance_13
						@Test(enabled = false, priority = 9, groups = { "Smoke" })
						public void MPI_641_Normal_Attendance_13()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee is present on a week off.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}			
						// MPI_644_Normal_Attendance_16
						@Test(enabled = false, priority = 10, groups = { "Smoke" })
						public void MPI_644_Normal_Attendance_16()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee completes a half day.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}
						
						// MPI_646_Normal_Attendance_18
						@Test(enabled = false, priority = 11, groups = { "Smoke" })
						public void MPI_646_Normal_Attendance_18()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee has not completed the minimum hours required for half day on a week off.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_647_Normal_Attendance_19
						@Test(enabled = false, priority = 12, groups = { "Smoke" })
						public void MPI_647_Normal_Attendance_19()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee completes a half day on a week off.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_652_Normal_Attendance_24
						@Test(enabled = false, priority = 13, groups = { "Smoke" })
						public void MPI_652_Normal_Attendance_24()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee present on day.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_655_Normal_Attendance_27
						@Test(enabled = false, priority = 14, groups = { "Smoke" })
						public void MPI_655_Normal_Attendance_27()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee did a single punch in on a week off.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_440_Normal_Attendance_48
						@Test(enabled = false, priority = 15, groups = { "Smoke" })
						public void MPI_440_Normal_Attendance_48()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when 'First Clock In & Last Clock Out' is applied in the policy and the employee did last punch in at the end of shift.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
							
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_657_Normal_Attendance_47
						@Test(enabled = false, priority = 16, groups = { "Smoke" })
						public void MPI_657_Normal_Attendance_47()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when 'First Clock In & Last Clock Out' is applied in the policy and the employee did last punch out before end of shift.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_659_Normal_Attendance_46
						@Test(enabled = false, priority = 17, groups = { "Smoke" })
						public void MPI_659_Normal_Attendance_46()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when '17 hr' is set in the Maximum Duration from shift start time in the policy and the employee did last punch out before 17 hours (next day morning 1AM).");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_442_Normal_Attendance_45
						@Test(enabled = false, priority = 18, groups = { "Smoke" })
						public void MPI_442_Normal_Attendance_45()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when '17 hr' is set in the Maximum Duration from shift start time in the policy and the employee only completes half day and punches out after 17 hours.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
							
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_683_Normal_Attendance_29
						@Test(enabled = false, priority = 19, groups = { "Smoke" })
						public void MPI_683_Normal_Attendance_29()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check that when the user is present for more than the specified shift duration, but overtime is not configured and 'Multiple Shift Detection' is set to 'yes'.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_684_Normal_Attendance_30
						@Test(enabled = false, priority = 20, groups = { "Smoke" })
						public void MPI_684_Normal_Attendance_30()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when \"Ignore Punch Minutes\" is set to 0 and the employee punches in and out within 1 minute without any further punches on the same day.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}			
						
						// MPI_656_Normal_Attendance_44
						@Test(enabled = false, priority = 21, groups = { "Smoke" })
						public void MPI_656_Normal_Attendance_44()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when Shift Type is set to \"Nearest\" in the policy. As an employee, punch in at 7:00 AM and punch out at 3:00 PM while assigned to a General Shift.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}				
						
						// MPI_668_Normal_Attendance_43
						@Test(enabled = false, priority = 22, groups = { "Smoke" })
						public void MPI_668_Normal_Attendance_43()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the attendance status when Shift Type is set to \"Nearest\" and the employee punches in 2hr before of shift start time and punch out at shift end.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_685_Normal_Attendance_31
						@Test(enabled = false, priority = 23, groups = { "Smoke" })
						public void MPI_685_Normal_Attendance_31()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee performs multiple punches in a day and completes the shift as per the defined shift policy.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
				
						// MPI_686_Normal_Attendance_42
						@Test(enabled = false, priority = 24, groups = { "Smoke" })
						public void MPI_686_Normal_Attendance_42()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when Casual Leave is applied for a day marked as Absent.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}
						}
						
						// MPI_666_Normal_Attendance_41
						@Test(enabled = false, priority = 25, groups = { "Smoke" })
						public void MPI_666_Normal_Attendance_41()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when Casual Leave is applied for a day marked as Absent.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
						
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}
						}		
						
						// MPI_671_Normal_Attendance_38
						@Test(enabled = false, priority = 26, groups = { "Smoke" })
						public void MPI_671_Normal_Attendance_38()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee applies for first-half leave without working during the second half of the day.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}
						}		
						
						// MPI_672_Normal_Attendance_39
						@Test(enabled = false, priority = 27, groups = { "Smoke" })
						public void MPI_672_Normal_Attendance_39()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee completes half day and apply half day leave.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}
						}	
						
						// MPI_673_Normal_Attendance_40
						@Test(enabled = false, priority = 28, groups = { "Smoke" })
						public void MPI_673_Normal_Attendance_40()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee applies for the first half as a leave and completes the second half (half-day).");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}
						}	
						
						// MPI_678_Normal_Attendance_28
						@Test(enabled = false, priority = 29, groups = { "Smoke" })
						public void MPI_678_Normal_Attendance_28()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when Out punch is there but no IN punch but employee is present on that day.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}
						
						// MPI_697_Normal_Attendance_52
						@Test(enabled = false, priority = 30, groups = { "Smoke" })
						public void MPI_697_Normal_Attendance_52()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status When user arrives Late.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_698_Normal_Attendance_53
						@Test(enabled = false, priority = 31, groups = { "Smoke" })
						public void MPI_698_Normal_Attendance_53()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee arrives early.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_724_Normal_Attendance_54
						@Test(enabled = false, priority = 32, groups = { "Smoke" })
						public void MPI_724_Normal_Attendance_54()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when 'First Clock In & Last Clock Out' is applied in the policy and the employee performs the last punch-in 1 minute before the shift end time.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_727_Normal_Attendance_55
						@Test(enabled = false, priority = 33, groups = { "Smoke" })
						public void MPI_727_Normal_Attendance_55()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee applies a Half Day Regularization Request.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_728_Normal_Attendance_56
						@Test(enabled = false, priority = 34, groups = { "Smoke" })
						public void MPI_728_Normal_Attendance_56()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status for emp by applying half day regulization request and half day leave.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_729_Normal_Attendance_57
						@Test(enabled = false, priority = 35, groups = { "Smoke" })
						public void MPI_729_Normal_Attendance_57()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee applies for second-half leave without working during the first half of the day.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}
						}	
						
						// MPI_730_Normal_Attendance_58
						@Test(enabled = false, priority = 36, groups = { "Smoke" })
						public void MPI_730_Normal_Attendance_58()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee applies for a half-day leave but performs only a single punch-in.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDate(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}
						}	
						
						// MPI_731_Normal_Attendance_59
						@Test(enabled = false, priority = 37, groups = { "Smoke" })
						public void MPI_731_Normal_Attendance_59()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee works fewer hours and applies for a half-day leave.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_860_Normal_Attendance_69
						@Test(enabled = false, priority = 38, groups = { "Smoke" })
						public void MPI_860_Normal_Attendance_69()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the total  hours of an employee who worked half an hour more than the shift duration.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,totalhours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String totalhours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytypeAndTotalHr(date, empstatus, daytype, totalhours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + totalhours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}
						
						// MPI_861_Normal_Attendance_70
						@Test(enabled = false, priority = 39, groups = { "Smoke" })
						public void MPI_861_Normal_Attendance_70()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the working hours of an employee who took a 2-hour break and did punch out at the end of the shift.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,totalhours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String totalhours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytypeAndTotalHr(date, empstatus, daytype, totalhours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + totalhours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}
						
						// MPI_639_Normal_Attendance_11
						@Test(enabled = false, priority = 40, groups = { "Smoke" })
						public void MPI_639_Normal_Attendance_11()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee performs only a single punch on a holiday.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}			
					
						// MPI_650_Normal_Attendance_22
						@Test(enabled = false, priority = 41, groups = { "Smoke" })
						public void MPI_650_Normal_Attendance_22()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee has not completed the minimum hours required for half day on a holiday.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}
						
						// MPI_640_Normal_Attendance_12
						@Test(enabled = false, priority = 42, groups = { "Smoke" })
						public void MPI_640_Normal_Attendance_12()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee is present on a holiday.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_642_Normal_Attendance_14
						@Test(enabled = false, priority = 43, groups = { "Smoke" })
						public void MPI_642_Normal_Attendance_14()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when there is a holiday.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
					
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateweekoff(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_643_Normal_Attendance_15
						@Test(enabled = false, priority = 44, groups = { "Smoke" })
						public void MPI_643_Normal_Attendance_15()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when there is a holiday and a week off on the same day.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateweekoff(date, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date." + date + " " + empstatus);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}		
						}	
						
						// MPI_648_Normal_Attendance_20
						@Test(enabled = false, priority = 45, groups = { "Smoke" })
						public void MPI_648_Normal_Attendance_20()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee completes a half day on holiday.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_649_Normal_Attendance_21
						@Test(enabled = false, priority = 46, groups = { "Smoke" })
						public void MPI_649_Normal_Attendance_21()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee completes a half day on a day that is both a holiday and a weekoff.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_651_Normal_Attendance_23
						@Test(enabled = false, priority = 47, groups = { "Smoke" })
						public void MPI_651_Normal_Attendance_23()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee has not completed the minimum hours required for half day on a day that is both a week off and a holiday.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_653_Normal_Attendance_25
						@Test(enabled = false, priority = 48, groups = { "Smoke" })
						public void MPI_653_Normal_Attendance_25()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee is present on a day that is both a holiday and a week off.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
				
						// MPI_654_Normal_Attendance_26
						@Test(enabled = false, priority = 49, groups = { "Smoke" })
						public void MPI_654_Normal_Attendance_26()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status when the employee did a single punch in on a day that is both a week off and a holiday.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(date, empstatus, daytype)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_1390_Attendance_Tab_03
						@Test(enabled = false, priority = 82, groups = { "Smoke" })
						public void MPI_1390_Attendance_Tab_03()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, login as employee and apply leave and ensure leave request is displayed in admin side.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"empid,leavereason");
							
							
							String empid = data.get(0);
							String leavereason = data.get(1);
							Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
							String month1WorkingDate  = dateDetails.get("month1WorkingDate");
  String uiDate = Pramod.convertToUIFormat(month1WorkingDate);
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
		                     MeghPIAttendAndroidLeavePage meghpiattendandroidleavepage = new MeghPIAttendAndroidLeavePage(driver);
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
							
							
							if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
								logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
							}
							if (meghpiattendandroidleavepage.QuickActionLeaveOption()) {
								logResults.createLogs("Y", "PASS", "Clicked On Apply Leave Button From Quick Action.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Apply Leave Button From Quick Action." + meghpiattendandroidleavepage.getExceptionDesc());
							}
							
							
							if (meghpiandroidattendancepage.LeaveApplyConfirmDisplayed()) {
								logResults.createLogs("Y", "PASS", "Leave Apply Page Loaded Successfully." );
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Displaying Leave Apply Button." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.LeaveTypeDropDown()) {
								logResults.createLogs("Y", "PASS", "Leave Type DropDown Clicked Successfully." );
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Leave Type DropDown." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.SickLeaveTypeSelected()) {
								logResults.createLogs("Y", "PASS", "Sick Leave Type Selected Successfully." );
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Sick Leave Type." + meghpiandroidattendancepage.getExceptionDesc());
							}
							
							
							if (meghpiandroidattendancepage.RegulirizationFromDate()) {
								logResults.createLogs("Y", "PASS", "Clicked On Regulirization From Date.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Regulirization From Date." + meghpiandroidattendancepage.getExceptionDesc());
							}
							
							if (meghpiandroidattendancepage.clickDateInCalendar(uiDate)) {
								logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day." + uiDate);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
								logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
							}
							
							if (meghpiandroidattendancepage.RegulirizationFromTimeTextField()) {
								logResults.createLogs("Y", "PASS", "Clicked On Leave Apply TO Date Widget.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Leave Apply To Date Widget." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.selectToDate(uiDate)) {
								logResults.createLogs("Y", "PASS", "Clicked On 2nd Working Day For Leave." + uiDate);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On 2nd Working Day." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
								logResults.createLogs("Y", "PASS", "Leave Apply Ok Button Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Ok Button." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.DurationOne()) {
								logResults.createLogs("Y", "PASS", "Leave Duration Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Leave Duration 2nd One." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.FullDaySelected()) {
								logResults.createLogs("Y", "PASS", "Full Day Leave Duration Selected Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Full Day Leave Duration." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.RegularizationTypeClicked()) {
								logResults.createLogs("Y", "PASS", "Leave Duration 2nd One Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Leave Duration 2nd One." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.FullDaySelected()) {
								logResults.createLogs("Y", "PASS", "Full Day Leave Duration Selected Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Selecting Full Day Leave Duration." + meghpiandroidattendancepage.getExceptionDesc());
							}
							
							if (meghpiandroidattendancepage.RegulirizationReasonTextField(leavereason)) {
								logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Leave Reason." + meghpiandroidattendancepage.getExceptionDesc());
							}
							
							if (meghpiandroidattendancepage.LeaveApplyConfirmButton()) {
								logResults.createLogs("Y", "PASS", "Leave Apply Confirm Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Leave Apply Confirm Button." + meghpiandroidattendancepage.getExceptionDesc());
							}	
							
						}
						
						// MPI_1391_Attendance_Tab_04
						@Test(enabled = true, priority = 83, groups = { "Smoke" })
						public void MPI_1391_Attendance_Tab_04()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, approve the employee’s leave request as an admin and ensure that, on the employee side, the approved leave date is marked as leave.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"empid,leavename,password,firstname,leavestatus");
							
							
							String empid = data.get(0);
							String leavename = data.get(1);
							String password = data.get(2);
							String firstname = data.get(3);
							String leavestatus = data.get(4);
							Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
							String month1WorkingDate  = dateDetails.get("month1WorkingDate");
  String uiDate = Pramod.convertToUIFormat(month1WorkingDate);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);

							
		
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
							if (meghpiandroidattendancepage.LeaveModuleInAdmin()) {
								logResults.createLogs("Y", "PASS", "Leave Module Clicked Successfully On Admin Account.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Leave Module On Admin Account." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.LeavePageLoaded()) {
								logResults.createLogs("Y", "PASS", "Leave Page Loaded Successfully On Admin Account.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Loading Leave Page On Admin Account." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.RegulirizationReasonTextField(firstname)) {
								logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Emp Name." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateLeaveAndApprove(empid, uiDate)) {
								logResults.createLogs("Y", "PASS", "Emp Applied Leave Approved Successfully." + uiDate);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Approving Emp Applied Leave." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.LeaveApproveButton()) {
								logResults.createLogs("Y", "PASS", "Leave Approve Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Leave Approve Button." + meghpiandroidattendancepage.getExceptionDesc());
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
							if (meghpiandroidattendancepage.clickLeaveBalanceCard()) {
								logResults.createLogs("Y", "PASS", "Leave Module In Employee Account Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Leave Module In Emp Account." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateLeaveRecord(leavename, leavestatus, uiDate, uiDate)) {
								logResults.createLogs("Y", "PASS", "Leave Status Validated In Employee Account." + leavename + " " + leavestatus + " " + uiDate);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Applied Leave In Emp Account." + meghpiandroidattendancepage.getExceptionDesc());
							}
						
						}	
						
						// MPI_465_Attendance_Policy_31
						@Test(enabled = false, priority = 52, groups = { "Smoke" })
						public void MPI_465_Attendance_Policy_31()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate overtime hours using the 'Total Hours minus Shift Duration' calculation method.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
					
							
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						
						// MPI_466_Attendance_Policy_32
						@Test(enabled = false, priority = 53, groups = { "Smoke" })
						public void MPI_466_Attendance_Policy_32()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate overtime hours using the '(Total Effective Hours) - (Shift Duration)' calculation method.(approved by system selected in attendance policy).");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}				
						
						// MPI_467_Attendance_Policy_33
						@Test(enabled = false, priority = 54, groups = { "Smoke" })
						public void MPI_467_Attendance_Policy_33()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate overtime hours using the 'Shift End Time to Out Punch' calculation method. (approved by system selected in attendance policy).");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_468_Attendance_Policy_34
						@Test(enabled = false, priority = 55, groups = { "Smoke" })
						public void MPI_468_Attendance_Policy_34()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate overtime hours using the 'In Punch to Shift Start Time' calculation method. (approved by system selected in attendance policy).");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}			
						
						// MPI_469_Attendance_Policy_35
						@Test(enabled = false, priority = 56, groups = { "Smoke" })
						public void MPI_469_Attendance_Policy_35()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate overtime hours using the '(In Punch to Shift Start Time) + (Shift End Time to Out Punch)' calculation method.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_470_Attendance_Policy_36
						@Test(enabled = false, priority = 57, groups = { "Smoke" })
						public void MPI_470_Attendance_Policy_36()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate OT hours when the calculation method is 'Shift End Time to Out Punch', with OT starting after 00:15 is configured. The employee punches out 45 minutes after the shift end time.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_876_Attendance_Policy_72
						@Test(enabled = false, priority = 58, groups = { "Smoke" })
						public void MPI_876_Attendance_Policy_72()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate OT hours when the calculation method is '(In Punch to Shift Start Time)', with OT starting before 00:15 is configured. The employee punches In 45 minutes before the shift start time.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}
						
						// MPI_878_Attendance_Policy_73
						@Test(enabled = false, priority = 59, groups = { "Smoke" })
						public void MPI_878_Attendance_Policy_73()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate OT hours when the calculation method is 'Shift End Time to Out Punch', with OT starting after 00:15 is configured. The employee punches out 10 minutes after the shift end time.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}			
						
						// MPI_878_Attendance_Policy_74
						@Test(enabled = false, priority = 60, groups = { "Smoke" })
						public void MPI_878_Attendance_Policy_74()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate OT hours when the calculation method is 'Shift End Time to Out Punch', with OT starting after 00:15 is configured. The employee punches out 10 minutes after the shift end time.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}			
						
						// MPI_882_Attendance_Policy_76
						@Test(enabled = false, priority = 61, groups = { "Smoke" })
						public void MPI_882_Attendance_Policy_76()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate overtime hours using the 'Total Hours minus Shift Duration' calculation method by taking break.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_885_Attendance_Policy_77
						@Test(enabled = false, priority = 62, groups = { "Smoke" })
						public void MPI_885_Attendance_Policy_77()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate overtime hours using the 'Shift End Time to Out Punch' calculation method by taking the break.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_886_Attendance_Policy_78
						@Test(enabled = false, priority = 63, groups = { "Smoke" })
						public void MPI_886_Attendance_Policy_78()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate overtime hours using the 'In Punch to Shift Start Time' calculation method by taking the break.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_887_Attendance_Policy_79
						@Test(enabled = false, priority = 64, groups = { "Smoke" })
						public void MPI_887_Attendance_Policy_79()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Calculate overtime hours using the '(In Punch to Shift Start Time) + (Shift End Time to Out Punch)' calculation method by taking the break.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_888_Attendance_Policy_80
						@Test(enabled = false, priority = 65, groups = { "Smoke" })
						public void MPI_888_Attendance_Policy_80()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check the status of the employee when \"Ignore Punch Minutes\" is set to 1 minute, and the employee performs punch in and punch out within 1 minute and punch out after half an of shift end and Calculate overtime hours using the '(Total Effective Hours) - (Shift Duration)' calculation method.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_742_Attendance_Policy_69
						@Test(enabled = false, priority = 66, groups = { "Smoke" })
						public void MPI_742_Attendance_Policy_69()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check that, for the Shift End Time to Out Punch attendance policy configuration do early punch in and check the OT status.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_743_Attendance_Policy_70
						@Test(enabled = false, priority = 67, groups = { "Smoke" })
						public void MPI_743_Attendance_Policy_70()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Check that, for the In Punch to Shift Start Time attendance policy configuration do late punch out and check the OT status.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndOvertime(date, empstatus, daytype, othours)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And OThours Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}	
						
						// MPI_889_Attendance_Policy_81
						@Test(enabled = false, priority = 68, groups = { "Smoke" })
						public void MPI_889_Attendance_Policy_81()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Verify the attendance status for an employee who completes both the first and second shifts when the ‘Multiple Shift Detection’ option is set to ‘Yes’ in the attendance policy..");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,inouttime,secondinouttime,noonintime,noonouttime");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String inouttime = data.get(5);
							String secondinouttime = data.get(6);
							String noonintime = data.get(7);
							String noonouttime = data.get(8);
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndMultipleShifts(date, empstatus, daytype, inouttime, secondinouttime,noonintime,noonouttime)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status With Multiple Shift Is Validated Successfully With Respect To Date" + date + " " + empstatus + " For First Shift " + daytype + " and timings is " + inouttime + " " + secondinouttime + " and 2nd shift status is" + empstatus + "and timing is " + noonintime + " " + noonouttime);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Morning And Noon Shift Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}		
						
						// MPI_890_Attendance_Policy_82
						@Test(enabled = false, priority = 69, groups = { "Smoke" })
						public void MPI_890_Attendance_Policy_82()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Verify the attendance status for an employee who completes both the first and second shifts when the ‘Multiple Shift Detection’ option is set to ‘No’ in the attendance policy..");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,othours,inouttime,secondinouttime");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String othours = data.get(5);
							String inouttime = data.get(6);
							String secondinouttime = data.get(7);	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateWhenMultipleShiftNo(date, empstatus, daytype, othours, inouttime, secondinouttime)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status And In Time And Out Time Validated Successfully With Respect To Date and Day Type." + date + " " + empstatus + " " + daytype + " " + othours + " " + inouttime + " " + secondinouttime);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status And OT Hours With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}			
						
						// MPI_891_Attendance_Policy_83
						@Test(enabled = false, priority = 70, groups = { "Smoke" })
						public void MPI_891_Attendance_Policy_83()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"Verify that when an employee completes the first shift and a half day of the second shift on the same day, the attendance status for the first shift is ‘P’ and for the second shift is ‘HD’.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,daytype,inouttime,secondinouttime,noonintime,noonouttime,secondstatus");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String daytype = data.get(4);
							String inouttime = data.get(5);
							String secondinouttime = data.get(6);
							String noonintime = data.get(7);
							String noonouttime = data.get(8);
							String secondstatus = data.get(9);
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceByDateAndMultipleShiftsStatus(date, empstatus, secondstatus, daytype, inouttime, secondinouttime,noonintime,noonouttime)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status With Multiple Shift Is Validated Successfully With Respect To Date" + date + " " + empstatus + " For First Shift " + daytype + " and timings is " + inouttime + " " + secondinouttime + " and 2nd shift status is" + empstatus + "and timing is " + noonintime + " " + noonouttime);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Morning And Noon Shift Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}	
						}
						
						
						// MPI_1190_Normal_Attendance_73
						@Test(enabled = false, priority = 71, groups = { "Smoke" })
						public void MPI_1190_Normal_Attendance_73()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, ensure that the ‘P’ status is displayed when the employee is present for consecutive days.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,seconddate");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String seconddate = data.get(4);
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceForTwoConsecutiveDates(date, empstatus, seconddate, empstatus)) {
								logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Each Date." );
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Status With Date." + meghpiandroidattendancepage.getExceptionDesc());
							}		
						}		
						

						// MPI_1392_Attendance_Tab_05
						@Test(enabled = false, priority = 72, groups = { "Smoke" })
						public void MPI_1392_Attendance_Tab_05()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the employee’s attendance status in the admin account Emp calendar.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,seconddate,email,password,emplastfourid,holidaydate,holidaystatus");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String seconddate = data.get(4);
							String email = data.get(5);
							String password =  data.get(6);
							String emplastfourid  =  data.get(7);
							String holidaydate  =  data.get(8);
							String holidaystatus  =  data.get(9);
							
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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


							if (meghPIAttendAndroidLoginPage.UserNameTextField(email)) {
								logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + email);
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
							if (meghpiandroidattendancepage.navigateToRequiredMonthAdmin(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.RegulirizationReasonTextField(emplastfourid)) {
								logResults.createLogs("Y", "PASS", "Employee ID Inputted Successfully." + empid);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Employee ID." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.clickOnEmployeeCard(empid)) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Card Clicked Successfully." + empid);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Employee Attendance Card." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceStatusByDate(date, empstatus )) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Present Days Validated Successfully." + date + " " + empstatus);
								
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Employee Attendance Present Status." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceStatusByDate(seconddate, empstatus )) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Present Days Validated Successfully." + seconddate + " " + empstatus);
								
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Employee Attendance Present Status." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateAttendanceStatusByDate(holidaydate, holidaystatus )) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Present Days Validated Successfully." + holidaydate + " " + holidaystatus);
								
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Employee Attendance Present Status." + meghpiandroidattendancepage.getExceptionDesc());
							}
			
						}
						
						//MPI_1393_Attendance_Tab_06
						@Test(enabled = false, priority = 73, groups = { "Smoke" })
						public void MPI_1393_Attendance_Tab_06()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this,  validate the employee’s OT hours along with the present, absent, and leave counts.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,presentcount,date,cmpcode,absentcount,email,password,emplastfourid,leavecount,othours");
							
							
							String empid = data.get(0);
							String presentcount = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String absentcount = data.get(4);
							String email = data.get(5);
							String password =  data.get(6);
							String emplastfourid  =  data.get(7);
							String leavecount  =  data.get(8);
							String othours  =  data.get(9);
							
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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


							if (meghPIAttendAndroidLoginPage.UserNameTextField(email)) {
								logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + email);
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
							if (meghpiandroidattendancepage.navigateToRequiredMonthAdmin(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.RegulirizationReasonTextField(emplastfourid)) {
								logResults.createLogs("Y", "PASS", "Employee ID Inputted Successfully." + empid);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Employee ID." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.clickOnEmployeeCard(empid)) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Card Clicked Successfully." + empid);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Employee Attendance Card." + meghpiandroidattendancepage.getExceptionDesc());
							}
							
							if (meghpiandroidattendancepage.validateEmployeeAttendanceSummary(empid, presentcount, absentcount, leavecount, othours )) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Present, Absent, Leave And OT Hours Count Validated Successfully." + empid + " " + presentcount + " " + absentcount + " " + leavecount + " " + othours);
								
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Employee Attendance Present Count." + meghpiandroidattendancepage.getExceptionDesc());
							}
			
						}		
						
						//MPI_1394_Attendance_Tab_07
						@Test(enabled = false, priority = 74, groups = { "Smoke" })
						public void MPI_1394_Attendance_Tab_07()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this,Apply leave for an employee and ensure leave is displayed in the calendar.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,date,cmpcode,email,password,emplastfourid,empstatus");
							
							
							String empid = data.get(0);
							String date =  data.get(1);
							String cmpcode = data.get(2);
							String email = data.get(3);
							String password =  data.get(4);
							String emplastfourid  =  data.get(5);
							String empstatus  =  data.get(6);
			
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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


							if (meghPIAttendAndroidLoginPage.UserNameTextField(email)) {
								logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + email);
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
							if (meghpiandroidattendancepage.navigateToRequiredMonthAdmin(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.RegulirizationReasonTextField(emplastfourid)) {
								logResults.createLogs("Y", "PASS", "Employee ID Inputted Successfully." + empid);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Inputting Employee ID." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.clickOnEmployeeCard(empid)) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Card Clicked Successfully." + empid);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Employee Attendance Card." + meghpiandroidattendancepage.getExceptionDesc());
							}
							
							if (meghpiandroidattendancepage.validateAttendanceStatusByDate(date, empstatus )) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Leave Date Validated Successfully." + date + " " + empstatus);
								
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Employee Leave Date On Calendar." + meghpiandroidattendancepage.getExceptionDesc());
							}
			
						}				
						
						//MPI_1395_Attendance_Tab_08
						@Test(enabled = false, priority = 75, groups = { "Smoke" })
						public void MPI_1395_Attendance_Tab_08()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check employee present, absent OT count and leave count on employee account.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,presentcount,date,cmpcode,absentcount,leavecount,othours");
							
							
							String empid = data.get(0);
							String presentcount = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String absentcount = data.get(4);
							String leavecount  =  data.get(5);
							String othours  =  data.get(6);
							
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							if (meghpiandroidattendancepage.navigateToRequiredMonth(date)) {
								logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + date);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating To Punch In Did Month ." + meghPIAttendAndroidLoginPage.getExceptionDesc());
							}
	
							if (meghpiandroidattendancepage.validateEmployeeAttendanceSummaryOnEmp( presentcount, absentcount, leavecount, othours )) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Present, Absent, Leave And OT Hours Count Validated Successfully." + empid + " " + presentcount + " " + absentcount + " " + leavecount + " " + othours);
								
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Employee Attendance Present Count." + meghpiandroidattendancepage.getExceptionDesc());
							}
			
						}		
						
						// MPI_1440_Attendance_Tab_09
						@Test(enabled = false, priority = 76, groups = { "Smoke" })
						public void MPI_1440_Attendance_Tab_09()   {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the attendance status in the employee calendar in employee account.");

							  // Load Excel data
							ArrayList<String> data = initBase.loadExcelData("Attendance", currTC,
									"employeeuniqueid,empstatus,date,cmpcode,seconddate,holidaydate,holidaystatus,leavedate,leavestatusonemp");
							
							
							String empid = data.get(0);
							String empstatus = data.get(1);
							String date =  data.get(2);
							String cmpcode = data.get(3);
							String seconddate = data.get(4);
							
							String holidaydate  =  data.get(5);
							String holidaystatus  =  data.get(6);
							String leavedate  =  data.get(7);
							String leavestatusonemp  =  data.get(8);
							
							
							
	
	
									MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
							MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	
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
							
							
							if (meghpiandroidattendancepage.navigateToRequiredMonthemps(driver, date )) {
								logResults.createLogs("Y", "PASS", "Navigated To Emp Attendance Month Successfully." + date);
								
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Navigating Employee Attendance Date Month." + meghpiandroidattendancepage.getExceptionDesc());
							}
						
							if (meghpiandroidattendancepage.validateEmployeeAttendanceStatusByDateOnEMp(date, empstatus )) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Present Days Validated Successfully." + date + " " + empstatus);
								
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Employee Attendance Present Status." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateEmployeeAttendanceStatusByDateOnEMp(seconddate, empstatus )) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Present Days Validated Successfully." + seconddate + " " + empstatus);
								
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Employee Attendance Present Status." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateEmployeeAttendanceStatusByDateOnEMp(holidaydate, holidaystatus )) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Present Days Validated Successfully." + holidaydate + " " + holidaystatus);
								
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Employee Attendance Present Status." + meghpiandroidattendancepage.getExceptionDesc());
							}
							if (meghpiandroidattendancepage.validateEmployeeAttendanceStatusByDateOnEMp(leavedate, leavestatusonemp )) {
								logResults.createLogs("Y", "PASS", "Employee Attendance Leave Date Validated Successfully." + leavedate + " " + leavestatusonemp);
								
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Employee Leave Date On Calendar." + meghpiandroidattendancepage.getExceptionDesc());
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
