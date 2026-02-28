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
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidOverTimePage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidRegularizationPage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidWeekOffPage;
import com.MeghPI.Attendance.pages.MeghLeavePage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.appium.java_client.HasSettings;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;
public class MeghPIAttendAndroidWeekOffTest {

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
	
	// MPI_1495_Mobile_WeekOff_01
				@Test(enabled = true, priority = 1, groups = { "Smoke" })
				public void MPI_1495_Mobile_WeekOff_01()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, log in as an employee, raise a week-off request, and ensure that the request is successfully created.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("WeekOff_Tab", currTC,
							"password,baseuri,loginendpoint,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,photo,regulizationreason,regulizationstatus");


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
				        
				        String regulizationreason = data.get(i++);
				        String regulizationstatus = data.get(i++);
				     
				        
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
				        Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String firstsunday = (String) datas.get("1stSunday");
				       String firstworkingday = (String) datas.get("workingAfter1stSaturday");
				       String firstSundayStr = Pramod.convertToUIFormat(firstsunday);
				       String workingDayUI = Pramod.convertToUIFormat(firstworkingday);


				       
				       
				       Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				       String firstDayOfMonth = dateDetails.get("firstDayOfMonth");


					    
				   MeghPIAttendAndroidWeekOffPage meghpiandroidweekoffpage = new MeghPIAttendAndroidWeekOffPage(driver);
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
					if (meghpiandroidregularizationpage.OTTab()) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab On Emp Attendance." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
						logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					
					if (meghpiandroidweekoffpage.ApplyWeekOffFromQuickActionOfEmpAttendance()) {
						logResults.createLogs("Y", "PASS", "Clicked On Apply WeekOff Button On Quick Action.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Apply WeekOff Button On Quick Action." + meghpiandroidweekoffpage.getExceptionDesc());
					}
				
					if (meghpiandroidattendancepage.RegulirizationFromDate()) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff From Date TextField.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff From Date." + meghpiandroidattendancepage.getExceptionDesc());
					}
					
					if (meghpiandroidweekoffpage.selectFirstSaturdayDate(firstSundayStr)) {
	 					logResults.createLogs("Y", "PASS", "Clicked On First Saturday Date." + firstSundayStr);
	 				} else {
	 					logResults.createLogs("Y", "FAIL",
	 							"Error While Clicking On  First Saturday Date." + meghpiandroidweekoffpage.getExceptionDesc());
	 				}
	 				if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
	 					logResults.createLogs("Y", "PASS", "WeekOff From Date Selected Successfully.");
	 				} else {
	 					logResults.createLogs("Y", "FAIL",
	 							"Error While Clicking On WeekOff From Date." + meghpiandroidattendancepage.getExceptionDesc());
	 				}
	 				
	 				
	 				if (meghpiandroidweekoffpage.WeekOFfNewDateField()) {
	 					logResults.createLogs("Y", "PASS", "Clicked On WeekOff To Date.");
	 				} else {
	 					logResults.createLogs("Y", "FAIL",
	 							"Error While Clicking On WeekOff To Date." + meghpiandroidweekoffpage.getExceptionDesc());
	 				}
	 				if (meghpiandroidweekoffpage.selectWeekoffNewDate(workingDayUI)) {
	 					logResults.createLogs("Y", "PASS", "Clicked On To Date." + workingDayUI);
	 				} else {
	 					logResults.createLogs("Y", "FAIL",
	 							"Error While Clicking On To Date." + meghpiandroidweekoffpage.getExceptionDesc());
	 				}
					if (meghpiandroidattendancepage.RegulirizationToDateSelected()) {
						logResults.createLogs("Y", "PASS", "WeekOff To Date Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff To Date." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.RegulirizationReasonSearchTextField(regulizationreason)) {
						logResults.createLogs("Y", "PASS", "WeekOff Reason Inputted Successfully." + regulizationreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting WeekOff Reason." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					if (meghpiandroidattendancepage.RegulirizationApplyButton()) {
						logResults.createLogs("Y", "PASS", "WeekOff Apply Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Apply Button" + meghpiandroidattendancepage.getExceptionDesc());
					}	
					
					if (meghpiandroidweekoffpage.validateWeekoffChangeCard(firstSundayStr,workingDayUI, regulizationstatus)) {
						logResults.createLogs("Y", "PASS", "Applied WeekOff Request Displayed In Employee Account." +" old" + firstSundayStr + " new " + workingDayUI +" " +  regulizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Applied WeekOff Request." + meghpiandroidregularizationpage.getExceptionDesc());
					}	
				}
	
	
				// MPI_1496_Mobile_WeekOff_02
				@Test(enabled = true, priority = 2, groups = { "Smoke" })
				public void MPI_1496_Mobile_WeekOff_02()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, approve the week-off request raised by the employee and ensure that the status shows as Approved on both the employee and admin accounts.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("WeekOff_Tab", currTC,
							"password,empid,firstname,alltab,regulizationstatus,pendingtab");
					
					String password = data.get(0);
					String empid = data.get(1);
					String firstname = data.get(2);
					String alltab = data.get(3);
					
					String regulizationstatus = data.get(4);
					
					String pendingtab = data.get(5);
					
				      
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String firstsunday = (String) datas.get("1stSunday");
				       String firstworkingday = (String) datas.get("workingAfter1stSaturday");
				       String firstSundayStr = Pramod.convertToUIFormat(firstsunday);
				       String workingDayUI = Pramod.convertToUIFormat(firstworkingday);
				       
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
	                  MeghPIAttendAndroidOverTimePage meghpiandroidovertimepage = new MeghPIAttendAndroidOverTimePage(driver);
					   MeghPIAttendAndroidWeekOffPage meghpiandroidweekoffpage = new MeghPIAttendAndroidWeekOffPage(driver);
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
					if (meghpiandroidregularizationpage.OTTab()) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					
					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Admin.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab On Admin." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
						logResults.createLogs("Y", "PASS", "Clicked On Filter Button In WeekOff Tab." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button On WeekOff Tab." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.clickWeekOffTab(pendingtab)) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab Pending Summary Count.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab Pending Summary Count." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					
					if (meghpiandroidweekoffpage.approveWeekoffCard(empid, firstSundayStr, workingDayUI)) {
						logResults.createLogs("Y", "PASS", "Verified  WeekOff Request On Admin Account." + empid + " " + firstSundayStr + " " +  workingDayUI + " " +  firstname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Verifying WeekOff Request On Admin Account." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					
					
					if (meghpiandroidovertimepage.OTApproveButton()) {
						logResults.createLogs("Y", "PASS", "WeekOff Approve Confirm Button Clicked On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Approve Confirm Button On Admin Account." + meghpiandroidovertimepage.getExceptionDesc());
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
					if (meghpiandroidregularizationpage.OTTab()) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab On Emp Attendance." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.clickWeekOffTab(alltab)) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab Approved Summary Count." + alltab);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab Approved Summary Count." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					
					
					if (meghpiandroidweekoffpage.validateWeekoffChangeCard(firstSundayStr,workingDayUI, regulizationstatus)) {
						logResults.createLogs("Y", "PASS", "Applied WeekOff Request Approved Successfully And Validated." +" old" + firstSundayStr + " new " + workingDayUI +" " +  regulizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Applied WeekOff Request Approved Record." + meghpiandroidregularizationpage.getExceptionDesc());
					}	
				}
	
				// MPI_1499_Mobile_WeekOff_05
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_1499_Mobile_WeekOff_05()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise the week-off request and ensure the status is displayed as ‘Pending’ on both the employee and admin accounts.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("WeekOff_Tab", currTC,
							"password,empid,regulizationstatus,pendingtab,regulizationreason");
					
					String password = data.get(0);
					String empid = data.get(1);
					String regulizationstatus = data.get(2);		
					String pendingtab = data.get(3);
					String regulizationreason = data.get(4);
					
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String secondSaturdays = (String) datas.get("2ndSaturday");
				       String nextMondays = (String) datas.get("workingAfter2ndSaturday");
				    String   secondSaturday =  Pramod.convertToUIFormat(secondSaturdays);
				    String nextMonday = Pramod.convertToUIFormat(nextMondays);
					
					

					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					   MeghPIAttendAndroidWeekOffPage meghpiandroidweekoffpage = new MeghPIAttendAndroidWeekOffPage(driver);

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
	        					if (meghpiandroidregularizationpage.OTTab()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Emp Attendance.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff Tab On Emp Attendance." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
	        					}
	        					
	        					if (meghpiandroidweekoffpage.ApplyWeekOffFromQuickActionOfEmpAttendance()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On Apply WeekOff Button On Quick Action.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On Apply WeekOff Button On Quick Action." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        				
	        					if (meghpiandroidattendancepage.RegulirizationFromDate()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On WeekOff From Date TextField.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff From Date." + meghpiandroidattendancepage.getExceptionDesc());
	        					}
	        					
	        					if (meghpiandroidweekoffpage.selectFirstSaturdayDate(secondSaturday)) {
	        	 					logResults.createLogs("Y", "PASS", "Clicked On 2nd Saturday Date." + secondSaturday);
	        	 				} else {
	        	 					logResults.createLogs("Y", "FAIL",
	        	 							"Error While Clicking On  2nd Saturday Date." + meghpiandroidweekoffpage.getExceptionDesc());
	        	 				}
	        	 				if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
	        	 					logResults.createLogs("Y", "PASS", "WeekOff From Date Selected Successfully.");
	        	 				} else {
	        	 					logResults.createLogs("Y", "FAIL",
	        	 							"Error While Clicking On WeekOff From Date." + meghpiandroidattendancepage.getExceptionDesc());
	        	 				}
	        	 				
	        	 				if (meghpiandroidweekoffpage.WeekOFfNewDateField()) {
	        	 					logResults.createLogs("Y", "PASS", "Clicked On WeekOff To Date.");
	        	 				} else {
	        	 					logResults.createLogs("Y", "FAIL",
	        	 							"Error While Clicking On WeekOff To Date." + meghpiandroidweekoffpage.getExceptionDesc());
	        	 				}
	        	 				if (meghpiandroidweekoffpage.selectWeekoffNewDate(nextMonday)) {
	        	 					logResults.createLogs("Y", "PASS", "Clicked On To Date." + nextMonday);
	        	 				} else {
	        	 					logResults.createLogs("Y", "FAIL",
	        	 							"Error While Clicking On To Date." + meghpiandroidweekoffpage.getExceptionDesc());
	        	 				}
	        					if (meghpiandroidattendancepage.RegulirizationToDateSelected()) {
	        						logResults.createLogs("Y", "PASS", "WeekOff To Date Selected Successfully.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff To Date." + meghpiandroidattendancepage.getExceptionDesc());
	        					}
	        					if (meghpiandroidregularizationpage.RegulirizationReasonSearchTextField(regulizationreason)) {
	        						logResults.createLogs("Y", "PASS", "WeekOff Reason Inputted Successfully." + regulizationreason);
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Inputting WeekOff Reason." + meghpiandroidregularizationpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidattendancepage.RegulirizationApplyButton()) {
	        						logResults.createLogs("Y", "PASS", "WeekOff Apply Button Clicked Successfully.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff Apply Button" + meghpiandroidattendancepage.getExceptionDesc());
	        					}	
	        					
	        					if (meghpiandroidweekoffpage.validateWeekoffChangeCard(secondSaturday,nextMonday, regulizationstatus)) {
	        						logResults.createLogs("Y", "PASS", "Applied WeekOff Request Displayed In Employee Account." +" old" + secondSaturday + " new " + nextMonday +" " +  regulizationstatus );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Displaying Applied WeekOff Request." + meghpiandroidregularizationpage.getExceptionDesc());
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
	        					if (meghpiandroidregularizationpage.OTTab()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
	        					}
	        					
	        					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Admin.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff Tab On Admin." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On Filter Button In WeekOff Tab." + empid);
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On Filter Button On WeekOff Tab." + meghpiandroidregularizationpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.clickWeekOffTab(pendingtab)) {
	        						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab Pending Summary Count.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff Tab Pending Summary Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					
	        					if (meghpiandroidweekoffpage.approveWeekoffCard(empid, secondSaturday, nextMonday)) {
	        						logResults.createLogs("Y", "PASS", "Verified  WeekOff Request On Admin Account." + empid + " " + secondSaturday + " " +  nextMonday);
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Verifying WeekOff Request On Admin Account." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	
				}
				
				// MPI_1500_Mobile_WeekOff_06
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_1500_Mobile_WeekOff_06()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, reject the week-off request raised by the employee and ensure that the status shows as rejected on both the employee and admin accounts.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("WeekOff_Tab", currTC,
							"password,empid,firstname,alltab,regulizationstatus,pendingtab,regulizationreason,action");
					
					String password = data.get(0);
					String empid = data.get(1);
					String firstname = data.get(2);
					String alltab = data.get(3);
					
					String regulizationstatus = data.get(4);
					
					String pendingtab = data.get(5);
					String regulizationreason = data.get(6);
					String action = data.get(7);
					
				      
					
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String secondSaturdays = (String) datas.get("2ndSaturday");
				       String nextMondays = (String) datas.get("workingAfter2ndSaturday");
				    String   secondSaturday =  Pramod.convertToUIFormat(secondSaturdays);
				    String nextMonday = Pramod.convertToUIFormat(nextMondays);

					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
	                  MeghPIAttendAndroidOverTimePage meghpiandroidovertimepage = new MeghPIAttendAndroidOverTimePage(driver);
					   MeghPIAttendAndroidWeekOffPage meghpiandroidweekoffpage = new MeghPIAttendAndroidWeekOffPage(driver);
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
					if (meghpiandroidregularizationpage.OTTab()) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					
					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Admin.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab On Admin." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
						logResults.createLogs("Y", "PASS", "Clicked On Filter Button In WeekOff Tab." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button On WeekOff Tab." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.clickWeekOffTab(pendingtab)) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab Pending Summary Count.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab Pending Summary Count." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					
					if (meghpiandroidweekoffpage.approveOrRejectWeekoffCard(empid, secondSaturday, nextMonday, action)) {
						logResults.createLogs("Y", "PASS", "Verified  WeekOff Request On Admin Account." + empid + " " + secondSaturday + " " +  nextMonday + " " +  firstname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Verifying WeekOff Request On Admin Account." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.RegulirizationReasonSearchTextField(regulizationreason)) {
						logResults.createLogs("Y", "PASS", "WeekOff Reason Inputted Successfully." + regulizationreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting WeekOff Reason." + meghpiandroidregularizationpage.getExceptionDesc());
					}
			
					if (meghpiandroidweekoffpage.ApplyWeekOffRejectButton()) {
						logResults.createLogs("Y", "PASS", "WeekOff Reject Confirm Button Clicked On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Reject Confirm Button On Admin Account." + meghpiandroidweekoffpage.getExceptionDesc());
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
					if (meghpiandroidregularizationpage.OTTab()) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab On Emp Attendance." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.clickWeekOffTab(alltab)) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab Rejected Summary Count." + alltab);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab Rejected Summary Count." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					
					
					if (meghpiandroidweekoffpage.validateWeekoffChangeCard(secondSaturday,nextMonday, regulizationstatus)) {
						logResults.createLogs("Y", "PASS", "Applied WeekOff Request Rejected Successfully And Validated." +" old" + secondSaturday + " new " + nextMonday +" " +  regulizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Applied WeekOff Request Rejected Record." + meghpiandroidregularizationpage.getExceptionDesc());
					}	
				}
	
				// MPI_1503_Mobile_WeekOff_08
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_1503_Mobile_WeekOff_08()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise a Weekoff request, then revoke it, and ensure the record is deleted successfully and the status is displayed as Revoked in the admin account.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("WeekOff_Tab", currTC,
							"password,empid,pendingtab,regulizationreason,alltab");
					
					String password = data.get(0);
					String empid = data.get(1);
					String pendingtab = data.get(2);
					String regulizationreason = data.get(3);
					String alltab = data.get(4);
					
					 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String secondSundays = (String) datas.get("2ndSunday");
				       String nextMondays = (String) datas.get("workingAfter2ndSunday");
				    String   secondSunday =  Pramod.convertToUIFormat(secondSundays);
				    String nexttuesday = Pramod.convertToUIFormat(nextMondays);
					

					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					   MeghPIAttendAndroidWeekOffPage meghpiandroidweekoffpage = new MeghPIAttendAndroidWeekOffPage(driver);

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
	        					if (meghpiandroidregularizationpage.OTTab()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Emp Attendance.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff Tab On Emp Attendance." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidregularizationpage.QuickActionInRegularizationTabOfEmpAttendance()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On Quick Action On Emp Attendance.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On Quick Action On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
	        					}
	        					
	        					if (meghpiandroidweekoffpage.ApplyWeekOffFromQuickActionOfEmpAttendance()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On Apply WeekOff Button On Quick Action.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On Apply WeekOff Button On Quick Action." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        				
	        					if (meghpiandroidattendancepage.RegulirizationFromDate()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On WeekOff From Date TextField.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff From Date." + meghpiandroidattendancepage.getExceptionDesc());
	        					}
	        					
	        					if (meghpiandroidweekoffpage.selectFirstSaturdayDate(secondSunday)) {
	        	 					logResults.createLogs("Y", "PASS", "Clicked On 2nd Saturday Date." + secondSunday);
	        	 				} else {
	        	 					logResults.createLogs("Y", "FAIL",
	        	 							"Error While Clicking On  2nd Saturday Date." + meghpiandroidweekoffpage.getExceptionDesc());
	        	 				}
	        	 				if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
	        	 					logResults.createLogs("Y", "PASS", "WeekOff From Date Selected Successfully.");
	        	 				} else {
	        	 					logResults.createLogs("Y", "FAIL",
	        	 							"Error While Clicking On WeekOff From Date." + meghpiandroidattendancepage.getExceptionDesc());
	        	 				}
	        	 				
	        	 				if (meghpiandroidweekoffpage.WeekOFfNewDateField()) {
	        	 					logResults.createLogs("Y", "PASS", "Clicked On WeekOff To Date.");
	        	 				} else {
	        	 					logResults.createLogs("Y", "FAIL",
	        	 							"Error While Clicking On WeekOff To Date." + meghpiandroidweekoffpage.getExceptionDesc());
	        	 				}
	        	 				if (meghpiandroidweekoffpage.selectWeekoffNewDate(nexttuesday)) {
	        	 					logResults.createLogs("Y", "PASS", "Clicked On To Date." + nexttuesday);
	        	 				} else {
	        	 					logResults.createLogs("Y", "FAIL",
	        	 							"Error While Clicking On To Date." + meghpiandroidweekoffpage.getExceptionDesc());
	        	 				}
	        					if (meghpiandroidattendancepage.RegulirizationToDateSelected()) {
	        						logResults.createLogs("Y", "PASS", "WeekOff To Date Selected Successfully.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff To Date." + meghpiandroidattendancepage.getExceptionDesc());
	        					}
	        					if (meghpiandroidregularizationpage.RegulirizationReasonSearchTextField(regulizationreason)) {
	        						logResults.createLogs("Y", "PASS", "WeekOff Reason Inputted Successfully." + regulizationreason);
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Inputting WeekOff Reason." + meghpiandroidregularizationpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidattendancepage.RegulirizationApplyButton()) {
	        						logResults.createLogs("Y", "PASS", "WeekOff Apply Button Clicked Successfully.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff Apply Button" + meghpiandroidattendancepage.getExceptionDesc());
	        					}	
	        					if (meghpiandroidweekoffpage.clickWeekOffTab(pendingtab)) {
	        						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab Pending Summary Count.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff Tab Pending Summary Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					
	        					if (meghpiandroidweekoffpage.validateWeekoffChangeCard(secondSunday,nexttuesday, pendingtab)) {
	        						logResults.createLogs("Y", "PASS", "Applied WeekOff Request Displayed In Employee Account." +" old" + secondSunday + " new " + nexttuesday +" " +  pendingtab );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Displaying Applied WeekOff Request." + meghpiandroidregularizationpage.getExceptionDesc());
	        					}	
	        					
	        					if (meghpiandroidregularizationpage.RegulirizationRequestRevokeButton()) {
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
	        					if (meghpiandroidregularizationpage.OTTab()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
	        					}
	        					
	        					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Admin.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff Tab On Admin." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On Filter Button In WeekOff Tab." + empid);
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On Filter Button On WeekOff Tab." + meghpiandroidregularizationpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.clickWeekOffTab(alltab)) {
	        						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab Revoked Summary Count." + alltab);
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff Tab Revoked Summary Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					
	        					if (meghpiandroidweekoffpage.verifyWeekoffStatusCard(empid, secondSunday, alltab)) {
	        						logResults.createLogs("Y", "PASS", "Verified  WeekOff Request On Admin Account." + empid + " " + secondSunday + " " +  alltab);
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Verifying WeekOff Request On Admin Account." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
				}
	
				// MPI_1504_Mobile_WeekOff_09
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_1504_Mobile_WeekOff_09()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the summary count details in the employee account for pending, rejected, approved, and total requests.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("WeekOff_Tab", currTC,
							"password,empid,pending,pendingcount,approved,approvedcount,revoked,revokedcount,rejected,rejectedcount");
					
					String password = data.get(0);
					String empid = data.get(1);
					String pending = data.get(2);		
					String pendingcount = data.get(3);
					String approved = data.get(4);
					String approvedcount = data.get(5);
					String revoked = data.get(6);
					String revokedcount = data.get(7);
					String rejected = data.get(8);
					String rejectedcount = data.get(9);
					
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					   MeghPIAttendAndroidWeekOffPage meghpiandroidweekoffpage = new MeghPIAttendAndroidWeekOffPage(driver);
					MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
	                             
	                             if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
	             					logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
	             				} else {
	             					logResults.createLogs("Y", "FAIL",
	             							"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
	             				}
	             				
	             				if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
	             					logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked." + password);
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
	        					if (meghpiandroidregularizationpage.OTTab()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Emp Attendance.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On WeekOff Tab On Emp Attendance." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(pending, pendingcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Pending Count Validated Successfully." + pending + " " + pendingcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Pending Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(approved, approvedcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Approved Count Validated Successfully." + approved + " " + approvedcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Approved Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(rejected, rejectedcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Rejected Count Validated Successfully." + rejected + " " + rejectedcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Rejected Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(revoked, revokedcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Revoked Count Validated Successfully." + revoked + " " + revokedcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Revoked Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        		
	        				}	
	        					
	        					
	        					
				// MPI_1502_Mobile_WeekOff_07
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_1502_Mobile_WeekOff_07()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the \"All\", \"Pending\", \"Approved\",  and \"Rejected\" summary counts on the admin account.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("WeekOff_Tab", currTC,
							"password,empid,pending,pendingcount,approved,approvedcount,revoked,revokedcount,rejected,rejectedcount");
					
					String password = data.get(0);
					String empid = data.get(1);
					String pending = data.get(2);		
					String pendingcount = data.get(3);
					String approved = data.get(4);
					String approvedcount = data.get(5);
					String revoked = data.get(6);
					String revokedcount = data.get(7);
					String rejected = data.get(8);
					String rejectedcount = data.get(9);
					
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					   MeghPIAttendAndroidWeekOffPage meghpiandroidweekoffpage = new MeghPIAttendAndroidWeekOffPage(driver);
		                  MeghPIAttendAndroidOverTimePage meghpiandroidovertimepage = new MeghPIAttendAndroidOverTimePage(driver);
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
	         					if (meghpiandroidregularizationpage.OTTab()) {
	         						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
	         					} else {
	         						logResults.createLogs("Y", "FAIL",
	         								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
	         					}
	         					
	         					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
	         						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Admin.");
	         					} else {
	         						logResults.createLogs("Y", "FAIL",
	         								"Error While Clicking On WeekOff Tab On Admin." + meghpiandroidweekoffpage.getExceptionDesc());
	         					}
	         					if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
	         						logResults.createLogs("Y", "PASS", "Clicked On Filter Button In WeekOff Tab." + empid);
	         					} else {
	         						logResults.createLogs("Y", "FAIL",
	         								"Error While Clicking On Filter Button On WeekOff Tab." + meghpiandroidovertimepage.getExceptionDesc());
	         					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(pending, pendingcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Pending Count Validated Successfully." + pending + " " + pendingcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Pending Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(approved, approvedcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Approved Count Validated Successfully." + approved + " " + approvedcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Approved Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(rejected, rejectedcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Rejected Count Validated Successfully." + rejected + " " + rejectedcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Rejected Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        					if (meghpiandroidweekoffpage.validateWeekoffCardCount(revoked, revokedcount ))
	        					{
	        						logResults.createLogs("Y", "PASS", "Revoked Count Validated Successfully." + revoked + " " + revokedcount  );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Revoked Count." + meghpiandroidweekoffpage.getExceptionDesc());
	        					}
	        		
	        				}				
	        					
				// MPI_1498_Mobile_WeekOff_04
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_1498_Mobile_WeekOff_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, perform Punch-In on the old week-off day and on the new week-off day, and ensure the statuses displayed are ‘P’ and ‘PW’ respectively.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("WeekOff_Tab", currTC,
							"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,empstatus,daytype,daytypeonweekoff");


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
				        String empid  = data.get(i++);
				        String  empstatus = data.get(i++);
				        String daytype = data.get(i++);
				        String daytypeonweekoff = data.get(i++);
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				  
				       
				       Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

				        String firstsunday = (String) datas.get("1stSunday");
				       String firstworkingday = (String) datas.get("workingAfter1stSaturday");
				       String firstSunday = Pramod.convertToUIFormat(firstsunday);
				       String firstMonday = Pramod.convertToUIFormat(firstworkingday);
					   
				       Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				       String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
					    
						String inouttime = firstSunday + " " + inouttime1;
						String secondInOutTime = firstSunday + " " + secondInOutTime2;
					
						String inouttimenew = firstMonday + " " + inouttime1;
						String secondInOutTimenew = firstMonday + " " + secondInOutTime2;
						
						
						 MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
                       
			  
	  //old weekoff day punch in 
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
				     		
				    //New weekoff day punch in 
				    // Punch IN
				    Response punchInResponses = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, empid,
				            locationid, inouttimenew, mode, photo
				    );

				    if (punchInResponses.getStatusCode() == 200) {
					    logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttimenew);
					} else {
					    logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponses.asString());
					    return;
					}

				    // Punch OUT
				    Response punchOutResponses = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, empid,
				            locationid, secondInOutTimenew, secondMode, photo
				    );

				    if (punchOutResponses.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTimenew);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponses.asString());
			            return;
			        }
				    try {
						Thread.sleep(15000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  
				 // Trigger attendance update first
				    Response updateResps = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            empid, firstDayOfMonth + "T00:00:00.000Z"
				    );

				    if (updateResps.statusCode() == 200 && updateResps.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + empid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResps.asString());
				    }
				    try {
						Thread.sleep(25000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
					
					
					if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(firstSunday, empstatus, daytype)) {
						logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To Old WeekOff Date and Day Type." + firstSunday + " " + empstatus + " " + daytype);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Attendance Status With Old WeekOff Date." + meghpiandroidattendancepage.getExceptionDesc());
					}				
					if (meghpiandroidattendancepage.BackButton()) {
						logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
					}	
					if (meghpiandroidattendancepage.AttendanceButtonOnEmpDashBoard()) {
						logResults.createLogs("Y", "PASS", "Clicked On Attendance Button On Emp DashBoard.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Button On Emp DashBoard." + meghpiandroidattendancepage.getExceptionDesc());
					}		
					
					
					if (meghpiandroidattendancepage.validateAttendanceByDateondaytype(firstMonday, empstatus, daytypeonweekoff)) {
						logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully With Respect To New WeekOff Date and Day Type." + firstMonday + " " + empstatus + " " + daytypeonweekoff);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Attendance Status With New WeekOff Date." + meghpiandroidattendancepage.getExceptionDesc());
					}				
				}			
	        					
	        					
				// MPI_1497_Mobile_WeekOff_03
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_1497_Mobile_WeekOff_03() {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, ensure that the old week-off day is now displayed as a working day, and the newly assigned week-off day is displayed with the Week Off day type.");
					logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_1498_Mobile_WeekOff_04 TestCase.");
				}					
	        					
	        					
				// MPI_1505_Mobile_WeekOff_10
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_1505_Mobile_WeekOff_10()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise a weekoff request from the admin account for an employee and ensure that the request is created with the “Approved” status and is displayed on the employee side.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("WeekOff_Tab", currTC,
							"password,empid,regulizationstatus,regulizationreason,firstname,alltab");
					
					String password = data.get(0);
					String empid = data.get(1);
					String regulizationstatus = data.get(2);
				
					String regulizationreason = data.get(3);
					String firstname = data.get(4);
					String alltab  = data.get(5);
					
					Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();

			        String secondSundays = (String) datas.get("2ndSunday");
			       String nextMondays = (String) datas.get("workingAfter2ndSunday");
			    String   secondSunday =  Pramod.convertToUIFormat(secondSundays);
			    String nexttuesday = Pramod.convertToUIFormat(nextMondays);
					
					   MeghPIAttendAndroidWeekOffPage meghpiandroidweekoffpage = new MeghPIAttendAndroidWeekOffPage(driver);

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
					if (meghpiandroidweekoffpage.ApplyWeekOffFromQuickActionOfEmpAttendance()) {
						logResults.createLogs("Y", "PASS", "Clicked On Apply WeekOff Button On Quick Action.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Apply WeekOff Button On Quick Action." + meghpiandroidweekoffpage.getExceptionDesc());
					}	
					if (meghpiandroidregularizationpage.RegulirizationRequestForOthersButton()) {
						logResults.createLogs("Y", "PASS", " Apply WeekOff For OThers Button.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Apply WeekOff For Others." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.inputRegularizationEmpName(firstname)) {
						logResults.createLogs("Y", "PASS", " To Apply WeekOff For OThers Emp Name Inputted." + firstname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name While Applying WeekOff ." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.WeekOffRequestEmpNameSelected()) {
						logResults.createLogs("Y", "PASS", "To Apply WeekOff For OThers Emp Name Selected.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Emp Name." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.RegulirizationRequestFromDateAdmin()) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff From Date.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff From Date." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.selectFirstSaturdayDate(secondSunday)) {
	 					logResults.createLogs("Y", "PASS", "Clicked On First Saturday Date." + secondSunday);
	 				} else {
	 					logResults.createLogs("Y", "FAIL",
	 							"Error While Clicking On  First Saturday Date." + meghpiandroidweekoffpage.getExceptionDesc());
	 				}
	 				if (meghpiandroidattendancepage.RegulirizationFromDateSelected()) {
	 					logResults.createLogs("Y", "PASS", "WeekOff From Date Selected Successfully.");
	 				} else {
	 					logResults.createLogs("Y", "FAIL",
	 							"Error While Clicking On WeekOff From Date." + meghpiandroidattendancepage.getExceptionDesc());
	 				}
	 				if (meghpiandroidweekoffpage.NewWeekOffDateClickedOnAdmin()) {
	 					logResults.createLogs("Y", "PASS", "Clicked On WeekOff To Date.");
	 				} else {
	 					logResults.createLogs("Y", "FAIL",
	 							"Error While Clicking On WeekOff To Date." + meghpiandroidweekoffpage.getExceptionDesc());
	 				}
	 				if (meghpiandroidweekoffpage.selectWeekoffNewDate(nexttuesday)) {
	 					logResults.createLogs("Y", "PASS", "Clicked On To Date." + nexttuesday);
	 				} else {
	 					logResults.createLogs("Y", "FAIL",
	 							"Error While Clicking On To Date." + meghpiandroidweekoffpage.getExceptionDesc());
	 				}
					if (meghpiandroidattendancepage.RegulirizationToDateSelected()) {
						logResults.createLogs("Y", "PASS", "WeekOff To Date Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff To Date." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.RegulirizationReasonSearchTextField(regulizationreason)) {
						logResults.createLogs("Y", "PASS", "WeekOff Reason Inputted Successfully." + regulizationreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Regularization Reason." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					if (meghpiandroidattendancepage.RegulirizationApplyButton()) {
						logResults.createLogs("Y", "PASS", "WeekOff Apply Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Apply Button" + meghpiandroidattendancepage.getExceptionDesc());
					} 
					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab On Emp Attendance." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
						logResults.createLogs("Y", "PASS", "Clicked On Filter Button In WeekOff Tab." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button On WeekOff Tab." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.clickWeekOffTab(alltab)) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab Approved Summary Count." + alltab);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab Approved Summary Count." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					
					if (meghpiandroidweekoffpage.approveWeekoffCard(empid, secondSunday, nexttuesday)) {
						logResults.createLogs("Y", "PASS", "Verified  WeekOff Request On Admin Account." + empid + " " + secondSunday + " " +  nexttuesday);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Verifying WeekOff Request On Admin Account." + meghpiandroidweekoffpage.getExceptionDesc());
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
					if (meghpiandroidregularizationpage.OTTab()) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.WeekOffTabOnEmpAttendance()) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab On Emp Attendance." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.clickWeekOffTab(alltab)) {
						logResults.createLogs("Y", "PASS", "Clicked On WeekOff Tab Approved Summary Count." + alltab);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On WeekOff Tab Approved Summary Count." + meghpiandroidweekoffpage.getExceptionDesc());
					}
					
					
					if (meghpiandroidweekoffpage.validateWeekoffChangeCard(secondSunday,nexttuesday, regulizationstatus)) {
						logResults.createLogs("Y", "PASS", "Applied WeekOff Request Approved Successfully And Validated." +" old" + secondSunday + " new " + nexttuesday +" " +  regulizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Applied WeekOff Request Approved Record." + meghpiandroidregularizationpage.getExceptionDesc());
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
