package com.MeghPI.Attendance.Android.tests;
import java.time.LocalDate;
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
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidShiftPage;
import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidWeekOffPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.appium.java_client.HasSettings;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIAttendAndroidShiftTest {

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
	
	
	// MPI_1540_Android_Shift_09
				@Test(enabled = true, priority = 1, groups = { "Smoke" })
				public void MPI_1540_Android_Shift_09()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that when a shift request is applied from the Shift module for the present date, the shift request is successfully created.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Shift", currTC,
							"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,regulizationreason");


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
				      
				        String regulizationreason  = data.get(i++);
				        
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

				        String monthFirstWorkingDate = dateDetails.get("month1WorkingDate");
				        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
				      
					   
					    
						String inouttime = monthFirstWorkingDate + " " + inouttime1;
						String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
					
						
						
						
						 MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
                        MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
               MeghPIAttendAndroidShiftPage meghpiandroidshiftpage = new MeghPIAttendAndroidShiftPage(driver);     
                  
                  
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
					    	    return; // ❌ stop execution if enrollment fails
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
		
					if (meghpiandroidshiftpage.ShiftButtonOnEmpDashBoard()) {
						logResults.createLogs("Y", "PASS", "Emp Shift Button On DashBoard Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Emp DashBoard Shift Button." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.navigateToRequiredMonthofshift(monthFirstWorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating To Punch In Did Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.clickShiftByDate(monthFirstWorkingDate)) {
						logResults.createLogs("Y", "PASS", "Clicked On First Working Date  Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On First Working Date." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.SelectShiftPolicyDropDown()) {
						logResults.createLogs("Y", "PASS", "Shift Policy DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Policy DropDown." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.MorningShiftPolicySelected()) {
						logResults.createLogs("Y", "PASS", "Shift Policy  Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Shift Policy." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.SelectShiftTypeDropDown()) {
						logResults.createLogs("Y", "PASS", "Shift  DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift  DropDown." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.MorningShiftTypeSelected()) {
						logResults.createLogs("Y", "PASS", "Morning Shift  Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Morning Shift." + meghpiandroidattendancepage.getExceptionDesc());
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
	
				}
	
	
				// MPI_1541_Android_Shift_10
				@Test(enabled = true, priority = 2, groups = { "Smoke" })
				public void MPI_1541_Android_Shift_10()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that when the admin approves a shift request, the approved status is correctly displayed in the employee’s Shift module.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Shift", currTC,
							"password,empid,regulizationstatus");
					
					String password = data.get(0);
					String empid = data.get(1);
					String shiftstatus = data.get(2);
					

					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String monthFirstWorkingDate = dateDetails.get("month1WorkingDate");
				        
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		               MeghPIAttendAndroidShiftPage meghpiandroidshiftpage = new MeghPIAttendAndroidShiftPage(driver);     
					MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
	       	                  MeghPIAttendAndroidOverTimePage meghpiandroidovertimepage = new MeghPIAttendAndroidOverTimePage(driver);
	       	               
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
					
					if (meghpiandroidshiftpage.ShiftButtonAdminModule()) {
						logResults.createLogs("Y", "PASS", "Shift Button Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.navigateToRequiredMonthOnAdminShift(monthFirstWorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Shift Requested Data Month." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating To Shift Requested Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.PendingCountLabel()) {
						logResults.createLogs("Y", "PASS", "Pending Shift Request Button Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Shift Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					
					if (meghpiandroidshiftpage.approveShiftByEmpAndDate(empid, monthFirstWorkingDate)) {
						logResults.createLogs("Y", "PASS", "Approve Button Clicked On Emp Shift Request." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Emp Shift Request Approve Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidovertimepage.OTApproveButton()) {
						logResults.createLogs("Y", "PASS", "Shift Request Approve Confirm Button Clicked On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Request Approve Confirm Button On Admin Account." + meghpiandroidovertimepage.getExceptionDesc());
					}
					
					if (meghpiandroidshiftpage.ApprovedCountLabel()) {
						logResults.createLogs("Y", "PASS", "Approved Shift Request Button Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Shift Request Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					
					if (meghpiandroidshiftpage.validateShiftCardByEmpAndStatus(empid, shiftstatus)) {
						logResults.createLogs("Y", "PASS", "Approved Validated For Emp Shift Request." + shiftstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating On Emp Shift Request Approve Status ." + meghpiandroidshiftpage.getExceptionDesc());
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
					
					if (meghpiandroidshiftpage.ShiftTabOnEmp()) {
						logResults.createLogs("Y", "PASS", " Shift Tab Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift  Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.navigateToRequiredMonthOnAdminShift(monthFirstWorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Shift Requested Data Month." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating To Shift Requested Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.ApprovedCountLabel()) {
						logResults.createLogs("Y", "PASS", "Approved Shift Request Button Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Shift Request Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.validateShiftCardByDateAndStatus( monthFirstWorkingDate, shiftstatus )) {
						logResults.createLogs("Y", "PASS", "Approved Shift Request Validated Successfully On Emp Account." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating On Approved Shift Request On Emp Account ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					
				}		
					
				// MPI_1529_Android_Shift_01
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_1529_Android_Shift_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that when a shift request is applied from the Shift module for the present date, the shift request is successfully created.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Shift", currTC,
							"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,regulizationreason,regulizationstatus");


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
				   
				        String regulizationreason  = data.get(i++);
				        String shiftstatus = data.get(i++);
				        
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

				        String month2WorkingDate = dateDetails.get("month2WorkingDate");
				        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
				      
					   
					    
						String inouttime = month2WorkingDate + " " + inouttime1;
						String secondInOutTime = month2WorkingDate + " " + secondInOutTime2;
					
						
						
						
						 MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
                        MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
               MeghPIAttendAndroidShiftPage meghpiandroidshiftpage = new MeghPIAttendAndroidShiftPage(driver);     
                  
                  
					     
	  
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
		
					if (meghpiandroidshiftpage.ShiftButtonOnEmpDashBoard()) {
						logResults.createLogs("Y", "PASS", "Emp Shift Button On DashBoard Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Emp DashBoard Shift Button." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.navigateToRequiredMonthofshift(month2WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + month2WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating To Punch In Did Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.clickShiftByDate(month2WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Clicked On First Working Date  Successfully." + month2WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On First Working Date." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.SelectShiftPolicyDropDown()) {
						logResults.createLogs("Y", "PASS", "Shift Policy DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Policy DropDown." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.MorningShiftPolicySelected()) {
						logResults.createLogs("Y", "PASS", "Shift Policy  Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Shift Policy." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.SelectShiftTypeDropDown()) {
						logResults.createLogs("Y", "PASS", "Shift  DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift  DropDown." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.MorningShiftTypeSelected()) {
						logResults.createLogs("Y", "PASS", "Morning Shift  Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Morning Shift." + meghpiandroidattendancepage.getExceptionDesc());
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
					if (meghpiandroidregularizationpage.OTTab()) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					
					if (meghpiandroidshiftpage.ShiftTabOnEmp()) {
						logResults.createLogs("Y", "PASS", " Shift Tab Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift  Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.navigateToRequiredMonthOnAdminShift(month2WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Shift Requested Data Month." + month2WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating To Shift Requested Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.PendingCountLabel()) {
						logResults.createLogs("Y", "PASS", "Pending Shift Request Button Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Shift Request Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.validateShiftCardByDateAndStatus(month2WorkingDate, shiftstatus )) {
						logResults.createLogs("Y", "PASS", "Pedning Shift Request Validated Successfully On Emp Account." + shiftstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating On Pending Shift Request On Emp Account ." + meghpiandroidshiftpage.getExceptionDesc());
					}
	
				}			
					
					
				// MPI_1531_Android_Shift_03
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_1531_Android_Shift_03()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that, complete general shift  and as employee apply a shift-change request to Morning Shift for the same date, approve the request, and ensure that the attendance status is displayed correctly with the updated Morning Shift label.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Shift", currTC,
							"password,empid,lastname");
					
					String password = data.get(0);
					String empid = data.get(1);
					String shiftstatus = data.get(2);
					

					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String monthFirstWorkingDate = dateDetails.get("month1WorkingDate");
				        
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		               MeghPIAttendAndroidShiftPage meghpiandroidshiftpage = new MeghPIAttendAndroidShiftPage(driver);     
					
	       	               
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
						logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
					}

					if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
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
					if (meghpiandroidshiftpage.navigateToExpectedMonth(monthFirstWorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Shift Requested Month Successfully On Emp Account." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating Shift Requested Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.clickCalendarDate(monthFirstWorkingDate)) {
						logResults.createLogs("Y", "PASS", "Clicked On Shift Requested Day Successfully On Emp Account." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Requested Day ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.validateShiftNameInCard(shiftstatus)) {
						logResults.createLogs("Y", "PASS", "Approved New Shift validated Successfully On Emp Account." + shiftstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Approved Shift ." + meghpiandroidshiftpage.getExceptionDesc());
					}
				}
					
					
				// MPI_1532_Android_Shift_04
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_1532_Android_Shift_04()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify that, complete general shift  and as employee apply a shift-change request to Morning Shift for the same date, reject the request, and ensure that the attendance status is displayed correctly with the General Shift label and status is rejected.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Shift", currTC,
							"password,empid,regulizationstatus,regulizationreason,lastname");
					
					String password = data.get(0);
					String empid = data.get(1);
					String shiftstatus = data.get(2);
					String regulizationreason = data.get(3);
					String lastname = data.get(4);

					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month2WorkingDate = dateDetails.get("month2WorkingDate");
				        
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		               MeghPIAttendAndroidShiftPage meghpiandroidshiftpage = new MeghPIAttendAndroidShiftPage(driver);     
					MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
	       	                  MeghPIAttendAndroidOverTimePage meghpiandroidovertimepage = new MeghPIAttendAndroidOverTimePage(driver);
	   					   MeghPIAttendAndroidWeekOffPage meghpiandroidweekoffpage = new MeghPIAttendAndroidWeekOffPage(driver);

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
					
					if (meghpiandroidshiftpage.ShiftButtonAdminModule()) {
						logResults.createLogs("Y", "PASS", "Shift Button Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.navigateToRequiredMonthOnAdminShift(month2WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Shift Requested Data Month." + month2WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating To Shift Requested Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.PendingCountLabel()) {
						logResults.createLogs("Y", "PASS", "Pending Shift Request Button Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Shift Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					
					if (meghpiandroidshiftpage.rejectShiftByEmpAndDate(empid, month2WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Reject Button Clicked On Emp Shift Request." + month2WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Emp Shift Request Reject Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.RegulirizationReasonSearchTextField(regulizationreason)) {
						logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regulizationreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Regularization Reason." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					if (meghpiandroidweekoffpage.ApplyWeekOffRejectButton()) {
					logResults.createLogs("Y", "PASS", "Shift Request Reject Confirm Button Clicked On Admin Account." );
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Shift Request Reject Confirm Button On Admin Account." + meghpiandroidweekoffpage.getExceptionDesc());
				}
					
					if (meghpiandroidshiftpage.RejectedCountLabel()) {
						logResults.createLogs("Y", "PASS", "Rejected Shift Request Button Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Shift Request Summary ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					
					if (meghpiandroidshiftpage.validateShiftCardByEmpAndStatus(empid, shiftstatus)) {
						logResults.createLogs("Y", "PASS", "Approved Validated For Emp Shift Request." + shiftstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating On Emp Shift Request Approve Status ." + meghpiandroidshiftpage.getExceptionDesc());
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
					
					if (meghpiandroidshiftpage.ShiftTabOnEmp()) {
						logResults.createLogs("Y", "PASS", " Shift Tab Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift  Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.navigateToRequiredMonthOnAdminShift(month2WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Shift Requested Data Month." + month2WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating To Shift Requested Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.RejectedCountLabel()) {
						logResults.createLogs("Y", "PASS", "Rejected Shift Request Button Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Shift Request Summary ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.validateShiftCardByDateAndStatus( month2WorkingDate, shiftstatus )) {
						logResults.createLogs("Y", "PASS", "Rejected Shift Request Validated Successfully On Emp Account." + month2WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating On Rejected Shift Request On Emp Account ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidattendancepage.BackButton()) {
						logResults.createLogs("Y", "PASS", "Back Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Back Button." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.navigateToExpectedMonth(month2WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Shift Requested Month Successfully On Emp Account." + month2WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating Shift Requested Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.clickCalendarDate(month2WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Clicked On Shift Requested Day Successfully On Emp Account." + month2WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Requested Day ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.validateShiftNameInCard(lastname)) {
						logResults.createLogs("Y", "PASS", "Approved New Shift validated Successfully On Emp Account." + lastname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Approved Shift ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					
				}			
					
					
				// MPI_1533_Android_Shift_05
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_1533_Android_Shift_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise the shift request and revoke back and ensure status is revoked.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Shift", currTC,
							"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,regulizationreason,regulizationstatus");


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
				   
				        String regulizationreason  = data.get(i++);
				        String shiftstatus = data.get(i++);
				        
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

				        String month3WorkingDate = dateDetails.get("month4WorkingDate");
				        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
				      
					   
					    
						String inouttime = month3WorkingDate + " " + inouttime1;
						String secondInOutTime = month3WorkingDate + " " + secondInOutTime2;
					
						
						
						
						 MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
                        MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
               MeghPIAttendAndroidShiftPage meghpiandroidshiftpage = new MeghPIAttendAndroidShiftPage(driver);     
                  
                  
				
	  
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
		
					if (meghpiandroidshiftpage.ShiftButtonOnEmpDashBoard()) {
						logResults.createLogs("Y", "PASS", "Emp Shift Button On DashBoard Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Emp DashBoard Shift Button." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.navigateToRequiredMonthofshift(month3WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Punch In Data Month." + month3WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating To Punch In Did Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.clickShiftByDate(month3WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Clicked On First Working Date  Successfully." + month3WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On First Working Date." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.SelectShiftPolicyDropDown()) {
						logResults.createLogs("Y", "PASS", "Shift Policy DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Policy DropDown." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.MorningShiftPolicySelected()) {
						logResults.createLogs("Y", "PASS", "Shift Policy  Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Shift Policy." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.SelectShiftTypeDropDown()) {
						logResults.createLogs("Y", "PASS", "Shift  DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift  DropDown." + meghpiandroidattendancepage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.MorningShiftTypeSelected()) {
						logResults.createLogs("Y", "PASS", "Morning Shift  Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Morning Shift." + meghpiandroidattendancepage.getExceptionDesc());
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
					if (meghpiandroidregularizationpage.OTTab()) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab On Emp Attendance.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab On Emp Attendance." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					
					if (meghpiandroidshiftpage.ShiftTabOnEmp()) {
						logResults.createLogs("Y", "PASS", " Shift Tab Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift  Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.navigateToRequiredMonthOnAdminShift(month3WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Shift Requested Data Month." + month3WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating To Shift Requested Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.PendingCountLabel()) {
						logResults.createLogs("Y", "PASS", "Pending Shift Request Button Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Shift Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.RegulirizationRequestRevokeButton()) {
     					logResults.createLogs("Y", "PASS", "Clicked On Revoke Button Of Shift Request.");
     				} else {
     					logResults.createLogs("Y", "FAIL",
     							"Error While Clicking On Revoke Button." + meghpiandroidregularizationpage.getExceptionDesc());
     				}
					if (meghpiandroidregularizationpage.RegulirizationReasonSearchTextField(regulizationreason)) {
						logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regulizationreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Regularization Reason." + meghpiandroidregularizationpage.getExceptionDesc());
					}
     				if (meghpiandroidattendancepage.RegularizationConfirmButton()) {
						logResults.createLogs("Y", "PASS", "Clicked On Shift Request Revoke Confirm Button.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Request Revoke Confirm Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
					}
			
					if (meghpiandroidshiftpage.RevokedCountLabel()) {
						logResults.createLogs("Y", "PASS", "Revoked Shift Request Button Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Revoked Shift Request Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.validateShiftCardByDateAndStatus(month3WorkingDate, shiftstatus )) {
						logResults.createLogs("Y", "PASS", "Pedning Shift Request Validated Successfully On Emp Account." + shiftstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating On Pending Shift Request On Emp Account ." + meghpiandroidshiftpage.getExceptionDesc());
					}
	
				}			
					
					
				// MPI_1534_Android_Shift_06
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_1534_Android_Shift_06()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify the shift request summary count for Approved, Rejected, Revoked, and Pending statuses.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Shift", currTC,
							"password,empid,approved,approvedcount,revoked,revokedcount,rejected,rejectedcount");
					
					String password = data.get(0);
					String empid = data.get(1);
					String approved = data.get(2);
					String approvedcount = data.get(3);
					String revoked = data.get(4);
					String revokedcount = data.get(5);
					String rejected = data.get(6);
					String rejectedcount = data.get(7);
					
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month2WorkingDate = dateDetails.get("month2WorkingDate");
				        
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		               MeghPIAttendAndroidShiftPage meghpiandroidshiftpage = new MeghPIAttendAndroidShiftPage(driver);     
					MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
	                             MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
	       	                  MeghPIAttendAndroidOverTimePage meghpiandroidovertimepage = new MeghPIAttendAndroidOverTimePage(driver);
	   					   MeghPIAttendAndroidWeekOffPage meghpiandroidweekoffpage = new MeghPIAttendAndroidWeekOffPage(driver);

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
						logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
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
					
					if (meghpiandroidshiftpage.ShiftTabOnEmp()) {
						logResults.createLogs("Y", "PASS", " Shift Tab Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift  Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.navigateToRequiredMonthOnAdminShift(month2WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Shift Requested Data Month." + month2WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating To Shift Requested Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.validateStatusCount(approved, approvedcount)) {
						logResults.createLogs("Y", "PASS", "Approved Count Validated Successfully." + approvedcount);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Approved Count ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.validateStatusCount(rejected, rejectedcount)) {
						logResults.createLogs("Y", "PASS", "Rejected Count Validated Successfully." + rejectedcount);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Rejected Count ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.validateStatusCount(revoked, revokedcount)) {
						logResults.createLogs("Y", "PASS", "Revoked Count Validated Successfully." + revokedcount);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Revoked Count ." + meghpiandroidshiftpage.getExceptionDesc());
					}
				}	
					
					
				// MPI_1535_Android_Shift_07
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_1535_Android_Shift_07()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify the shift request summary count for Approved, Rejected, Revoked, and Pending statuses on admin account.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("Android_Shift", currTC,
							"password,approved,approvedcount,revoked,revokedcount,rejected,rejectedcount");
					
					String password = data.get(0);
					String approved = data.get(1);
					String approvedcount = data.get(2);
					String revoked = data.get(3);
					String revokedcount = data.get(4);
					String rejected = data.get(5);
					String rejectedcount = data.get(6);
					
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month2WorkingDate = dateDetails.get("month2WorkingDate");
				        
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
		               MeghPIAttendAndroidShiftPage meghpiandroidshiftpage = new MeghPIAttendAndroidShiftPage(driver);     
				

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
					
					if (meghpiandroidshiftpage.ShiftButtonAdminModule()) {
						logResults.createLogs("Y", "PASS", "Shift Button Clicked Successfully On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Button ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.navigateToRequiredMonthOnAdminShift(month2WorkingDate)) {
						logResults.createLogs("Y", "PASS", "Navigated To Shift Requested Data Month." + month2WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Navigating To Shift Requested Month ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.validateStatusCount(approved, approvedcount)) {
						logResults.createLogs("Y", "PASS", "Approved Count Validated Successfully." + approvedcount);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Approved Count ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.validateStatusCount(rejected, rejectedcount)) {
						logResults.createLogs("Y", "PASS", "Rejected Count Validated Successfully." + rejectedcount);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Rejected Count ." + meghpiandroidshiftpage.getExceptionDesc());
					}
					if (meghpiandroidshiftpage.validateStatusCount(revoked, revokedcount)) {
						logResults.createLogs("Y", "PASS", "Revoked Count Validated Successfully." + revokedcount);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Revoked Count ." + meghpiandroidshiftpage.getExceptionDesc());
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
