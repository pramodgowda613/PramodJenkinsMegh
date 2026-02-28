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
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.appium.java_client.HasSettings;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIAttendAndroidOverTimeTest {
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
	
	
	// MPI_1469_Mobile_OverTime_Tab_01
				@Test(enabled = true, priority = 1, groups = { "Smoke" })
				public void MPI_1469_Mobile_OverTime_Tab_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, perform overtime as an employee under an OT-configured policy and ensure that the OT record is displayed.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("OverTime_Tab", currTC,
							"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,totalothour,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,alltab,secondstatus");


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
				        String alltab = data.get(i++);
				        String secondstatus = data.get(i++);
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

				        String monthFirstWorkingDate  = dateDetails.get("month1WorkingDate");
				        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
				      
					   
					    
						String inouttime = monthFirstWorkingDate + " " + inouttime1;
						String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
					
		
						
						
						 MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
                        MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
                  MeghPIAttendAndroidOverTimePage meghpiandroidovertimepage = new MeghPIAttendAndroidOverTimePage(driver);
                       
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
					if (meghpiandroidovertimepage.clickOvertimeTab(alltab)) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab All Summary Count.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab All Summary Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
					
					
					if (meghpiandroidovertimepage.validateAttendanceByDateStatusAndOverstay(monthFirstWorkingDate, secondstatus, totalOTHour)) {
						logResults.createLogs("Y", "PASS", "Emp OT Hour And Status Validated Successfully." +monthFirstWorkingDate + " " + secondstatus + " " + totalOTHour );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Emp OverTime Request Status." + meghpiandroidovertimepage.getExceptionDesc());
					}
			}
			
				
				// MPI_1470_Mobile_OverTime_Tab_02
				@Test(enabled = true, priority = 2, groups = { "Smoke" })
				public void MPI_1470_Mobile_OverTime_Tab_02()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, approve the employee's OT request as an admin and ensure that the OT status is shown as Approved on both the employee and admin sides.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("OverTime_Tab", currTC,
							"password,empid,firstname,alltab,secondstatus,regulizationreason,ottime,totalothour,pendingtab");
					
					String password = data.get(0);
					String empid = data.get(1);
					String firstname = data.get(2);
					String alltab = data.get(3);
					String secondstatus = data.get(4);
					String regulizationreason = data.get(5);
					String ottime = data.get(6); 
					String totalOTHour = data.get(7);
					String pendingtab = data.get(8);
					
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String monthFirstWorkingDate  = dateDetails.get("month1WorkingDate");

					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
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
					if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
						logResults.createLogs("Y", "PASS", "Clicked On Filter Button In OT Tab." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button On OT Tab." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidovertimepage.clickOvertimeTab(pendingtab)) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab Pending Summary Count.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab Pending Summary Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.RegulirizationSearchTextField(firstname)) {
     					logResults.createLogs("Y", "PASS", "OT Request Applied Employee  Name Inputted Successfully."  + " " + firstname);
     				} else {
     					logResults.createLogs("Y", "FAIL",
     							"Error While Inputting  Applied OT Request Emp Name." + meghpiandroidregularizationpage.getExceptionDesc());
     				}
				
					
					if (meghpiandroidovertimepage.verifyOvertimeCardByEmpDateStatus(empid, monthFirstWorkingDate)) {
						logResults.createLogs("Y", "PASS", "Verified  OT Request On Admin Account." + empid + " " + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Verifying OT Request On Admin Account." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidovertimepage.OTApproveButton()) {
						logResults.createLogs("Y", "PASS", "  OT Approve Button Clicked On Admin Account." + empid + " " + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OT Approve Button On Admin Account." + meghpiandroidovertimepage.getExceptionDesc());
					}
					
					if (meghpiandroidovertimepage.ApprovalCommentsInput(regulizationreason)) {
						logResults.createLogs("Y", "PASS", "  OT Approve Comments Inputted Successfully." + regulizationreason );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting OT Approve Comments." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidovertimepage.OTApproveConfirmButton()) {
						logResults.createLogs("Y", "PASS", "  OT Approve Confirm Button Clicked On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OT Approve Confirm Button On Admin Account." + meghpiandroidovertimepage.getExceptionDesc());
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
					if (meghpiandroidovertimepage.clickOvertimeTab(alltab)) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab Approved Summary Count." + ottime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab Approved Summary Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
					
					
					if (meghpiandroidovertimepage.validateAttendanceByDateStatusAndOverstay(monthFirstWorkingDate, secondstatus, totalOTHour))
					{
						logResults.createLogs("Y", "PASS", "Emp OT Hour And Status Validated Successfully." +monthFirstWorkingDate + " " + secondstatus + " " + totalOTHour );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Emp OverTime Request Status." + meghpiandroidovertimepage.getExceptionDesc());
					}
					
				}
		
				// MPI_1471_Mobile_OverTime_Tab_03
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_1471_Mobile_OverTime_Tab_03()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, perform overtime as an employee and ensure the OT record status is displayed as Pending on both the employee and admin accounts.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("OverTime_Tab", currTC,
							"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,totalothour,updateattendanceendpoint,alltab,secondstatus,empid,firstname");


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

					 
					  
				        String totalOTHour = data.get(i++);
				        String updateattendanceendpoint = data.get(i++);
				       
				        String alltab = data.get(i++);
				        String secondstatus = data.get(i++);
				        String empid  = data.get(i++);
				        String firstname = data.get(i++);
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String monthSecondWorkingDate  = dateDetails.get("month2WorkingDate");
				       
				       
					   
					    
						String inouttime = monthSecondWorkingDate + " " + inouttime1;
						String secondInOutTime = monthSecondWorkingDate + " " + secondInOutTime2;
					
						
						// get first day of month
						LocalDate localDate = LocalDate.parse(monthSecondWorkingDate);
						 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
						
						 MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						MeghPIAttendAndroidAttendancePage meghpiandroidattendancepage = new MeghPIAttendAndroidAttendancePage(driver);
                        MeghPIAttendAndroidRegularizationPage meghpiandroidregularizationpage = new MeghPIAttendAndroidRegularizationPage(driver);
                  MeghPIAttendAndroidOverTimePage meghpiandroidovertimepage = new MeghPIAttendAndroidOverTimePage(driver);
                       
				     
	  
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
					if (meghpiandroidovertimepage.clickOvertimeTab(alltab)) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab All Summary Count.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab All Summary Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
					
					
					if (meghpiandroidovertimepage.validateAttendanceByDateStatusAndOverstay(monthSecondWorkingDate, secondstatus, totalOTHour)) {
						logResults.createLogs("Y", "PASS", "Emp OT Hour And Status Validated Successfully." +monthSecondWorkingDate + " " + secondstatus + " " + totalOTHour );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Emp OverTime Request Status." + meghpiandroidovertimepage.getExceptionDesc());
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
					if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
						logResults.createLogs("Y", "PASS", "Clicked On Filter Button In OT Tab." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button On OT Tab." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidovertimepage.clickOvertimeTab(alltab)) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab All Summary Count.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab All Summary Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidregularizationpage.RegulirizationSearchTextField(firstname)) {
     					logResults.createLogs("Y", "PASS", "OT Request Applied Employee  Name Inputted Successfully."  + " " + firstname);
     				} else {
     					logResults.createLogs("Y", "FAIL",
     							"Error While Inputting  Applied OT Request Emp Name." + meghpiandroidregularizationpage.getExceptionDesc());
     				}
				
					
					if (meghpiandroidovertimepage.verifyOvertimeCardByEmpDateStatus(empid, monthSecondWorkingDate)) {
						logResults.createLogs("Y", "PASS", "Verified  OT Request On Admin Account." + empid + " " + monthSecondWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Verifying OT Request On Admin Account." + meghpiandroidovertimepage.getExceptionDesc());
					}
			}	
				
				// MPI_1472_Mobile_OverTime_Tab_04
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_1472_Mobile_OverTime_Tab_04()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, reject the employee's OT request and ensure the OT status is displayed as Rejected on both the employee and admin accounts.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("OverTime_Tab", currTC,
							"password,empid,regulizationstatus,pendingtab,secondstatus,regulizationreason,ottime,totalothour,rejectedtab");
					
					String password = data.get(0);
					String empid = data.get(1);
					String regulizationstatus = data.get(2);
					String alltab = data.get(3);
					String secondstatus = data.get(4);
					String regulizationreason = data.get(5);
					String ottime = data.get(6); 
					String totalOTHour = data.get(7);
					String rejectedtab = data.get(8);
					
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String monthSecondWorkingDate  = dateDetails.get("month2WorkingDate");

					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
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
					if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
						logResults.createLogs("Y", "PASS", "Clicked On Filter Button In OT Tab." + empid);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button On OT Tab." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidovertimepage.clickOvertimeTab(alltab)) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Pending Summary Count.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab Pending Summary Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
				
					if (meghpiandroidovertimepage.verifyOvertimeCardByEmpDateStatus(empid, monthSecondWorkingDate)) {
						logResults.createLogs("Y", "PASS", "Verified  OT Request On Admin Account." + empid + " " + monthSecondWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Verifying OT Request On Admin Account." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidovertimepage.rejectOTCardByEmpAndDate(empid, monthSecondWorkingDate)) {
						logResults.createLogs("Y", "PASS", "  OT Reject First Button Clicked On Admin Account." + empid + " " + monthSecondWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OT Reject First Button On Admin Account." + meghpiandroidovertimepage.getExceptionDesc());
					}
					
					if (meghpiandroidregularizationpage.RegulirizationReasonSearchTextField(regulizationreason)) {
						logResults.createLogs("Y", "PASS", "OT REject Reason Inputted Successfully." + regulizationreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting OT Reject Reason." + meghpiandroidregularizationpage.getExceptionDesc());
					}
					
					if (meghpiandroidovertimepage.RejectConfirmButton()) {
						logResults.createLogs("Y", "PASS", "  OT Reject Button Clicked On Admin Account." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OT Reject Confirm Button On Admin Account." + meghpiandroidovertimepage.getExceptionDesc());
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
					
					
					if (meghpiandroidovertimepage.clickOvertimeTab(rejectedtab)) {
						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab Rejected Summary Count." + secondstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Tab Rejected Summary Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
					
					
					if (meghpiandroidovertimepage.validateAttendanceByDateStatusAndOverstay(monthSecondWorkingDate, regulizationstatus, totalOTHour))
					{
						logResults.createLogs("Y", "PASS", "Emp OT Hour And Status Validated Successfully." +monthSecondWorkingDate + " " + regulizationstatus + " " + totalOTHour  + " " + ottime );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Emp OverTime Request Status." + meghpiandroidovertimepage.getExceptionDesc());
					}
					
				}
				
				// MPI_1477_Mobile_OverTime_Tab_05
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_1477_Mobile_OverTime_Tab_05()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the \"All\", \"Pending\", \"Approved\",  and \"Rejected\" summary counts on the admin account.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("OverTime_Tab", currTC,
							"password,pending,pendingcount,approved,approvedcount,rejected,rejectedcount");
					
					String password = data.get(0);
					String pending = data.get(1);
					String pendingcount = data.get(2);
					String approved = data.get(3);
					String approvedcount = data.get(4);
					String rejected = data.get(5);
					String rejectedcount = data.get(6); 

					
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
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
					if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
						logResults.createLogs("Y", "PASS", "Clicked On Filter Button In OT Tab." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button On OT Tab." + meghpiandroidovertimepage.getExceptionDesc());
					}
					
					if (meghpiandroidovertimepage.validateOTCardCount(pending, pendingcount ))
					{
						logResults.createLogs("Y", "PASS", "Pending Count Validated Successfully." + pending + " " + pendingcount  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Pending Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidovertimepage.validateOTCardCount(approved, approvedcount ))
					{
						logResults.createLogs("Y", "PASS", "Approved Count Validated Successfully." + approved + " " + approvedcount  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Approved Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidovertimepage.validateOTCardCount(rejected, rejectedcount ))
					{
						logResults.createLogs("Y", "PASS", "Rejected Count Validated Successfully." + rejected + " " + rejectedcount  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Rejected Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
				
				
				}
				
				// MPI_1478_Mobile_OverTime_Tab_06
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_1478_Mobile_OverTime_Tab_06()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the \"All\", \"Pending\", \"Approved\",  and \"Rejected\" summary counts on the Employee account.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("OverTime_Tab", currTC,
							"password,pending,pendingcount,approved,approvedcount,rejected,rejectedcount,empid");
					
					String password = data.get(0);
					String pending = data.get(1);
					String pendingcount = data.get(2);
					String approved = data.get(3);
					String approvedcount = data.get(4);
					String rejected = data.get(5);
					String rejectedcount = data.get(6); 
					String empid = data.get(7);

					
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
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

	         					if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
	         						logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
	         					} else {
	         						logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
	         					}

	         					if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
	         						logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid + " or "+  password);
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
	         					
	         					
					
					if (meghpiandroidovertimepage.validateOTCardCount(pending, pendingcount ))
					{
						logResults.createLogs("Y", "PASS", "Pending Count Validated Successfully." + pending + " " + pendingcount  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Pending Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidovertimepage.validateOTCardCount(approved, approvedcount ))
					{
						logResults.createLogs("Y", "PASS", "Approved Count Validated Successfully." + approved + " " + approvedcount  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Approved Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
					if (meghpiandroidovertimepage.validateOTCardCount(rejected, rejectedcount ))
					{
						logResults.createLogs("Y", "PASS", "Rejected Count Validated Successfully." + rejected + " " + rejectedcount  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating  Rejected Count." + meghpiandroidovertimepage.getExceptionDesc());
					}
		
				}	
				
				// MPI_1479_Mobile_OverTime_Tab_07
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_1479_Mobile_OverTime_Tab_07()   {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the OT hours count on both the employee and admin accounts once the request is approved..");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("OverTime_Tab", currTC,
							"password,approved,empid,secondstatus,totalothour");
					
					String password = data.get(0);
					String approved = data.get(1);
					String empid = data.get(2);
					String secondstatus = data.get(3);
					String  totalOTHour = data.get(4);
					
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String monthFirstWorkingDate  = dateDetails.get("month1WorkingDate");

					
					MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass
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

	         					if (meghPIAttendAndroidLoginPage.UserNameTextField(empid)) {
	         						logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + empid);
	         					} else {
	         						logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
	         					}

	         					if (meghPIAttendAndroidLoginPage.PasswordTextField(empid)) {
	         						logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + empid + " or "+  password);
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
	         					if (meghpiandroidovertimepage.clickOvertimeTab(approved)) {
	        						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab Approved Summary Count.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On OverTime Tab Approved Summary Count." + meghpiandroidovertimepage.getExceptionDesc());
	        					}
	         					
					
	         					if (meghpiandroidovertimepage.validateAttendanceByDateStatusOverstayAndApprovedOT(monthFirstWorkingDate, secondstatus, totalOTHour, totalOTHour)) {
	        						logResults.createLogs("Y", "PASS", "Emp OT Hour And Status Validated Successfully." +monthFirstWorkingDate + " " + secondstatus + " " + totalOTHour );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Emp OverTime Request Status." + meghpiandroidovertimepage.getExceptionDesc());
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
	        					if (meghpiandroidregularizationpage.AdminRegularizationFilterButton()) {
	        						logResults.createLogs("Y", "PASS", "Clicked On Filter Button In OT Tab." + empid);
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On Filter Button On OT Tab." + meghpiandroidovertimepage.getExceptionDesc());
	        					}
	        					if (meghpiandroidovertimepage.clickOvertimeTab(approved)) {
	        						logResults.createLogs("Y", "PASS", "Clicked On OverTime Tab Approved Summary Count.");
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Clicking On OverTime Tab Approved Summary Count." + meghpiandroidovertimepage.getExceptionDesc());
	        					}
	         					
					
	         					if (meghpiandroidovertimepage.validateAttendanceByEmpDateStatusOverstayAndOvertime(empid, monthFirstWorkingDate, secondstatus, totalOTHour, totalOTHour)) {
	        						logResults.createLogs("Y", "PASS", "Emp OT Hour And Status Validated Successfully On Admin Account." +monthFirstWorkingDate + " " + secondstatus + " " + totalOTHour );
	        					} else {
	        						logResults.createLogs("Y", "FAIL",
	        								"Error While Validating  Emp OverTime Request Status On Admin Account." + meghpiandroidovertimepage.getExceptionDesc());
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
