 
package com.MeghPI.Attendance.tests;


import java.time.LocalDate;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;


public class MeghPISandwichAttendanceTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	@Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
		//driver = loadDriver.getDriver(device, hubnodeip);

		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

	@Parameters({ "device", "hubnodeip" })
	@BeforeClass(alwaysRun = true)
	void runOnce(int device, @Optional String hubnodeip) {
		logResults.createReport(device);
		logResults.setTestMethodErrorCount(0);
		
	}
	
	// MPI_879_Normal_Attendance_71
				@Test(enabled = true, priority = 1, invocationCount = 2, groups = { "Smoke" })
				public void MPI_879_Normal_Attendance_71()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " +  "Morning" +
							"Clear Data for Month-Year");
					
					
					ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
							"cmpcode,emailid,password,baseuri,loginendpoint,date,cleardataendpoint");

					
					String cmpcode           = data.get(0);
					String emailid           = data.get(1);
					String password          = data.get(2);
					String baseuri           = data.get(3);
					String loginendpoint     = data.get(4);
					String date              = data.get(5);
					String cleardataendpoint = data.get(6);

					
					System.out.println(data);
					System.out.println("cmpcode=" + cmpcode);
					System.out.println("emailid=" + emailid);
					System.out.println("password=" + password);
					System.out.println("baseuri=" + baseuri);
					System.out.println("loginendpoint=" + loginendpoint);
					System.out.println("date=" + date);
					System.out.println("cleardataendpoint=" + cleardataendpoint);

					
					MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
					  Response clearResponse = apiPage.executeClearData(
					            baseuri, loginendpoint,
					            emailid, password, cmpcode,
					            baseuri, cleardataendpoint,
					            date
					        );
					  System.out.println("method called");
					
					        // Validate status code & response
					  if (clearResponse.getStatusCode() == 200) {
						    String raw = clearResponse.asString().trim();
						    if ("true".equalsIgnoreCase(raw)) {
						        logResults.createLogs("N","PASS","âœ… ClearData successful for date."+date);
						    } else {
						        logResults.createLogs("N","FAIL","âŒ ClearData failed. Response."+raw);
						        Assert.fail("ClearData failed. Response."+raw);
						    }
						} else {
						    logResults.createLogs("N","FAIL","âŒ ClearData API failed with status."+clearResponse.getStatusCode());
						    Assert.fail("ClearData API failed with status."+clearResponse.getStatusCode());
						}
				}
	
	// MPI_687_Normal_Attendance_35
				@Test(enabled = true, priority = 1, groups = { "Smoke" })
				public void MPI_687_Normal_Attendance_35()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Check the status for weekoff day when the employee is on Leave on fridayÂ and \"Sandwich Weekend as part of Leave\" is not enabled in the policy.");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        String Tomorrow = currentDate.plusDays(1).toString();

			        // Day after tomorrow (two days after)
			        String dayAfterTomorrow = currentDate.plusDays(2).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = dayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = dayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfour = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusfour = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = previousDate  + fromdate1;
					String todate = previousDate  + todate2;
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				  
				    // Create API Page object
		
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }
			        
			        // --- Week-off Day ---
			        Response currRespone = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfour, toDateofstatusfour);

			        if (currRespone.statusCode() == 200) {
			            String currStatus = currRespone.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + currRespone.asString());
			        }
			        
			        
			        
			        

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, dayAfterTomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + dayAfterTomorrow +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
	
				// MPI_688_Normal_Attendance_36
				@Test(enabled = true, priority = 2, groups = { "Smoke" })
				public void MPI_688_Normal_Attendance_36()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Check the status for the weekoff day when the employee is Leave on Monday and \"Sandwich Weekend as part of Leave\" is not enabled in the policy.");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        String Tomorrow = currentDate.plusDays(1).toString();
			        // Day after tomorrow (two days after)
			        String dayAfterTomorrow = currentDate.plusDays(2).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = dayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = dayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfour = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusfour = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = dayAfterTomorrow  + fromdate1;
					String todate = dayAfterTomorrow  + todate2;
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				  
				    // Create API Page object
		
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }
			        // --- Week-off Day ---
			        Response currRespone = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfour, toDateofstatusfour);

			        if (currRespone.statusCode() == 200) {
			            String currStatus = currRespone.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + currRespone.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, dayAfterTomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + dayAfterTomorrow +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
	
				// MPI_689_Normal_Attendance_37
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_689_Normal_Attendance_37()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Check the status for weekoff day when the employee is on Leave on both Monday and Friday, and \"Sandwich Weekend as part of Leave\" is not enabled in the policy. ");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        String Tomorrow = currentDate.plusDays(1).toString();
			        // Day after tomorrow (two days after)
			        String dayAfterTomorrow = currentDate.plusDays(2).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = dayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = dayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfour = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusfour = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = dayAfterTomorrow  + fromdate1;
					String todate = dayAfterTomorrow  + todate2;
					
					 String fromdatetwo = previousDate  + fromdate1;
						String todatetwo = previousDate  + todate2;
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				  
				    // Create API Page object
		
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				    Response LeaveAppliedformonday = apiPage.executeCreateLeaves(
					        baseuri, loginendpoint, emailid, password, cmpcode,
					        baseuri, leaveendpoint,
					        type, entityid, leavetypeid,
					        totaldays, fromdatetwo, todatetwo,
					        fromdurationtype, todurationtype,
					        status, remarks, documentname, document
					    );

					    // Debugging - Print response details
					    System.out.println("Create Leave API Response Code." + LeaveAppliedformonday.getStatusCode());
					    System.out.println("Create Leave API Response Body." + LeaveAppliedformonday.asString());

					    if (LeaveAppliedformonday.getStatusCode() == 200) {
					        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdatetwo);
					    } else {
					        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveAppliedformonday.asString());
					    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }
			        // --- Week-off Day ---
			        Response currRespone = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfour, toDateofstatusfour);

			        if (currRespone.statusCode() == 200) {
			            String currStatus = currRespone.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + currRespone.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, dayAfterTomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + dayAfterTomorrow +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
	
	
				// MPI_690_Normal_Attendance_49
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_690_Normal_Attendance_49()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Check the status for holiday when the employee is on Leave on the day before the holiday and \"Sandwich Holiday as part of Leave\" is not enabled in the policy.");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				    String holidayendpoint = data.get(i++);
			        String holidayname = data.get(i++);
			        String applicableto = data.get(i++);
			        String holidaydescription = data.get(i++);
			        String states = data.get(i++);
			        String stateid = data.get(i++);
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        // Day after tomorrow (two days after)
			        String Tomorrow = currentDate.plusDays(1).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = previousDate  + fromdate1;
					String todate = previousDate  + todate2;
				
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				    Response holidayResponse = apiPage.executeCreateHoliday(
			                baseuri, loginendpoint, emailid, password, cmpcode,
			                baseuri, holidayendpoint,
			                holidayname, date, applicableto,
			                holidaydescription, states, stateid
			        );

			        if (holidayResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Holiday created successfully." + holidayname + "on" + date);
			        } else {
			            logResults.createLogs("N", "FAIL", "Holiday creation failed." + holidayResponse.asString());
			        }
			        
				    // Create API Page object
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
	
				// MPI_691_Normal_Attendance_50
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_691_Normal_Attendance_50()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Check the status for holiday when the employee is on Leave on the day after the holiday and \"Sandwich Holiday as part of Leave\" is not enabled in the policy.");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        // Day after tomorrow (two days after)
			        String Tomorrow = currentDate.plusDays(1).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = Tomorrow  + fromdate1;
					String todate = Tomorrow  + todate2;
				
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				   
			        
				    // Create API Page object
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
	
				// MPI_693_Normal_Attendance_51
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_693_Normal_Attendance_51()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Check the status for holiday when the employee is On leave on the day before and after the holiday, and \"Sandwich Holiday as part of Leave\" is not enabled in the policy.");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        // Day after tomorrow (two days after)
			        String Tomorrow = currentDate.plusDays(1).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = Tomorrow  + fromdate1;
					String todate = Tomorrow  + todate2;
					String fromdatetwo = previousDate  + fromdate1;
					String todatetwo = previousDate  + todate2;
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				   
			        
				    // Create API Page object
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				    Response LeaveAppliedtwo = apiPage.executeCreateLeaves(
					        baseuri, loginendpoint, emailid, password, cmpcode,
					        baseuri, leaveendpoint,
					        type, entityid, leavetypeid,
					        totaldays, fromdatetwo, todatetwo,
					        fromdurationtype, todurationtype,
					        status, remarks, documentname, document
					    );

					    // Debugging - Print response details
					    System.out.println("Create Leave API Response Code." + LeaveAppliedtwo.getStatusCode());
					    System.out.println("Create Leave API Response Body." + LeaveAppliedtwo.asString());

					    if (LeaveApplied.getStatusCode() == 200) {
					        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdatetwo);
					    } else {
					        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveAppliedtwo.asString());
					    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
	
				// MPI_564_Leave_Policy_21
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_564_Leave_Policy_21()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Check the status for weekoff days when the employee is on leave only on Friday and \"Sandwich Weekend as part of Leave\" is enabled in the policy ");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				    
				  
			        
				 // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        String Tomorrow = currentDate.plusDays(1).toString();

			        // Day after tomorrow (two days after)
			        String dayAfterTomorrow = currentDate.plusDays(2).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = dayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = dayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfour = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusfour = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = previousDate  + fromdate1;
					String todate = previousDate  + todate2;
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				  
				    // Create API Page object
		
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }
			        
			        // --- Week-off Day ---
			        Response currRespone = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfour, toDateofstatusfour);

			        if (currRespone.statusCode() == 200) {
			            String currStatus = currRespone.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + currRespone.asString());
			        }
			        

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, dayAfterTomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + dayAfterTomorrow +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
	
				// MPI_565_Leave_Policy_22
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_565_Leave_Policy_22()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Check the status for weekoff days when the employee is on leave on Friday and Monday, and \"Sandwich Weekend as part of Leave\" is enabled in the policy.");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        String Tomorrow = currentDate.plusDays(1).toString();
			        // Day after tomorrow (two days after)
			        String dayAfterTomorrow = currentDate.plusDays(2).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = dayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = dayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfour = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusfour = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = dayAfterTomorrow  + fromdate1;
					String todate = dayAfterTomorrow  + todate2;
					
					 String fromdatetwo = previousDate  + fromdate1;
						String todatetwo = previousDate  + todate2;
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				  
				    // Create API Page object
		
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				    Response LeaveAppliedformonday = apiPage.executeCreateLeaves(
					        baseuri, loginendpoint, emailid, password, cmpcode,
					        baseuri, leaveendpoint,
					        type, entityid, leavetypeid,
					        totaldays, fromdatetwo, todatetwo,
					        fromdurationtype, todurationtype,
					        status, remarks, documentname, document
					    );

					    // Debugging - Print response details
					    System.out.println("Create Leave API Response Code." + LeaveAppliedformonday.getStatusCode());
					    System.out.println("Create Leave API Response Body." + LeaveAppliedformonday.asString());

					    if (LeaveAppliedformonday.getStatusCode() == 200) {
					        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdatetwo);
					    } else {
					        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveAppliedformonday.asString());
					    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }
			        // --- Week-off Day ---
			        Response currRespone = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfour, toDateofstatusfour);

			        if (currRespone.statusCode() == 200) {
			            String currStatus = currRespone.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + currRespone.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, dayAfterTomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + dayAfterTomorrow +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
	
				// MPI_669_Leave_Policy_35
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_669_Leave_Policy_35()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Check the status for weekoff days when the employee is on leave only on Monday, and \"Sandwich Weekend as part of Leave\" is enabled in the policy ");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        String Tomorrow = currentDate.plusDays(1).toString();
			        
			        // Day after tomorrow (two days after)
			        String dayAfterTomorrow = currentDate.plusDays(2).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = dayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = dayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfour = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusfour = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = dayAfterTomorrow  + fromdate1;
					String todate = dayAfterTomorrow  + todate2;
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				  
				    // Create API Page object
		
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }
			        // --- Week-off Day ---
			        Response currRespone = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfour, toDateofstatusfour);

			        if (currRespone.statusCode() == 200) {
			            String currStatus = currRespone.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + currRespone.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, dayAfterTomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + dayAfterTomorrow +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
	
				// MPI_566_Leave_Policy_23
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_566_Leave_Policy_23()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Check the status for holiday  when the employee is on leave only on the day before the holiday, and \"Sandwich Holiday as part of Leave\" is enabled in the policy.");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				    String holidayendpoint = data.get(i++);
			        String holidayname = data.get(i++);
			        String applicableto = data.get(i++);
			        String holidaydescription = data.get(i++);
			        String states = data.get(i++);
			        String stateid = data.get(i++);
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        // Day after tomorrow (two days after)
			        String Tomorrow = currentDate.plusDays(1).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = previousDate  + fromdate1;
					String todate = previousDate  + todate2;
				
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				    Response holidayResponse = apiPage.executeCreateHoliday(
			                baseuri, loginendpoint, emailid, password, cmpcode,
			                baseuri, holidayendpoint,
			                holidayname, date, applicableto,
			                holidaydescription, states, stateid
			        );

			        if (holidayResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Holiday created successfully." + holidayname + "on" + date);
			        } else {
			            logResults.createLogs("N", "FAIL", "Holiday creation failed." + holidayResponse.asString());
			        }
			        
				    // Create API Page object
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
				
				// MPI_670_Leave_Policy_36
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_670_Leave_Policy_36()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Check the status for the holiday when the employee is on leave only on the day after the holiday, and \"Sandwich Holiday as part of Leave\" is enabled in the policy.");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        // Day after tomorrow (two days after)
			        String Tomorrow = currentDate.plusDays(1).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = Tomorrow  + fromdate1;
					String todate = Tomorrow  + todate2;
				
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				   
			        
				    // Create API Page object
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- holiday Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }

			        // ---  Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
				
				// MPI_567_Leave_Policy_24
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_567_Leave_Policy_24()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Check the status for theÂ holidayÂ when the employee is on leave onÂ both the day before and after the holiday, andÂ \"Sandwich Holiday as part of Leave\"Â is enabled in the policy.");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        // Day after tomorrow (two days after)
			        String Tomorrow = currentDate.plusDays(1).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = Tomorrow  + fromdate1;
					String todate = Tomorrow  + todate2;
					String fromdatetwo = previousDate  + fromdate1;
					String todatetwo = previousDate  + todate2;
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				   
			        
				    // Create API Page object
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				    Response LeaveAppliedtwo = apiPage.executeCreateLeaves(
					        baseuri, loginendpoint, emailid, password, cmpcode,
					        baseuri, leaveendpoint,
					        type, entityid, leavetypeid,
					        totaldays, fromdatetwo, todatetwo,
					        fromdurationtype, todurationtype,
					        status, remarks, documentname, document
					    );

					    // Debugging - Print response details
					    System.out.println("Create Leave API Response Code." + LeaveAppliedtwo.getStatusCode());
					    System.out.println("Create Leave API Response Body." + LeaveAppliedtwo.asString());

					    if (LeaveApplied.getStatusCode() == 200) {
					        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdatetwo);
					    } else {
					        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveAppliedtwo.asString());
					    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
				
				// MPI_925_Leave_Policy_42
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_925_Leave_Policy_42()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Verify that 'A' status display in report, when user is Present on Week Off but is on leave on prefix and  Suffix of Week Off and Holiday in parallel.");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,endpointoftransaction,cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid,twodayaftertommorrwostatus,sundaystatus");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				   
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")
				    
				    String holidayendpoint = data.get(i++);
			        String holidayname = data.get(i++);
			        String applicableto = data.get(i++);
			        String holidaydescription = data.get(i++);
			        String states = data.get(i++);
			        String stateid = data.get(i++);
			        String twodayaftertommorrwostatus = data.get(i++);
			        String sundaystatus = data.get(i++);
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        String Tomorrow = currentDate.plusDays(1).toString();
			        // Day after tomorrow (two days after)
			        String dayAfterTomorrow = currentDate.plusDays(2).toString();
			        String twodayAfterTomorrow = currentDate.plusDays(3).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = dayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = dayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfive = twodayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusfive = twodayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfour = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusfour = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = previousDate  + fromdate1;
					String todate = previousDate  + todate2;
					
					 String fromdatetwo = twodayAfterTomorrow  + fromdate1;
						String todatetwo = twodayAfterTomorrow  + todate2;
						String inouttime = date + " " + inouttime1;
						String secondInOutTime = date + " " + secondInOutTime2;
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				  
				    // Create API Page object
		
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				 // Punch IN
				    Response punchInResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
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
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, secondInOutTime, secondMode, photo
				    );

				    if (punchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				    
				    Response holidayResponse = apiPage.executeCreateHoliday(
			                baseuri, loginendpoint, emailid, password, cmpcode,
			                baseuri, holidayendpoint,
			                holidayname, dayAfterTomorrow, applicableto,
			                holidaydescription, states, stateid
			        );

			        if (holidayResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Holiday created successfully." + holidayname + dayAfterTomorrow);
			        } else {
			            logResults.createLogs("N", "FAIL", "Holiday creation failed." + holidayResponse.asString());
			        }
				    
				    Response LeaveAppliedfortuesday = apiPage.executeCreateLeaves(
					        baseuri, loginendpoint, emailid, password, cmpcode,
					        baseuri, leaveendpoint,
					        type, entityid, leavetypeid,
					        totaldays, fromdatetwo, todatetwo,
					        fromdurationtype, todurationtype,
					        status, remarks, documentname, document
					    );

					    // Debugging - Print response details
					    System.out.println("Create Leave API Response Code." + LeaveAppliedfortuesday.getStatusCode());
					    System.out.println("Create Leave API Response Body." + LeaveAppliedfortuesday.asString());

					    if (LeaveAppliedfortuesday.getStatusCode() == 200) {
					        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdatetwo);
					    } else {
					        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveAppliedfortuesday.asString());
					    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s punch in at %s and punch out at %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime, secondInOutTime, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }
			        // --- Week-off Day ---
			        Response currRespone = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfour, toDateofstatusfour);

			        if (currRespone.statusCode() == 200) {
			            String currStatus = currRespone.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, currStatus, sundaystatus);
			            if (sundaystatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + currRespone.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, dayAfterTomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + dayAfterTomorrow +
			                    ". API Response." + nextResp.asString());
			        }
			        
			        // --- TwoDay After Tomorrow ---
			        Response nextResponse = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfive, toDateofstatusfive);

			        if (nextResponse.statusCode() == 200) {
			            String nextStatus = nextResponse.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, twodayAfterTomorrow, nextStatus, twodayaftertommorrwostatus);
			            if (twodayaftertommorrwostatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + twodayaftertommorrwostatus +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
				
				// MPI_926_Leave_Policy_43
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_926_Leave_Policy_43()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Verify that A status display when user is  on leave on Prefix and Suffix day of Week off when Holiday and WeekOff is on Parallel Day,user has applied leave on Both Prefix and Suffix.");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,twodayaftertommorrwostatus,sundaystatus");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				  
			        String twodayaftertommorrwostatus = data.get(i++);
			        String sundaystatus = data.get(i++);
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        String Tomorrow = currentDate.plusDays(1).toString();
			        // Day after tomorrow (two days after)
			        String dayAfterTomorrow = currentDate.plusDays(2).toString();
			        String twodayAfterTomorrow = currentDate.plusDays(3).toString();

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = dayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = dayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfive = twodayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusfive = twodayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfour = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusfour = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = previousDate  + fromdate1;
					String todate = previousDate  + todate2;
					
					 String fromdatetwo = twodayAfterTomorrow  + fromdate1;
						String todatetwo = twodayAfterTomorrow  + todate2;
						
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				  
				    // Create API Page object
		
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
			
				    Response LeaveAppliedfortuesday = apiPage.executeCreateLeaves(
					        baseuri, loginendpoint, emailid, password, cmpcode,
					        baseuri, leaveendpoint,
					        type, entityid, leavetypeid,
					        totaldays, fromdatetwo, todatetwo,
					        fromdurationtype, todurationtype,
					        status, remarks, documentname, document
					    );

					    // Debugging - Print response details
					    System.out.println("Create Leave API Response Code." + LeaveAppliedfortuesday.getStatusCode());
					    System.out.println("Create Leave API Response Body." + LeaveAppliedfortuesday.asString());

					    if (LeaveAppliedfortuesday.getStatusCode() == 200) {
					        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdatetwo);
					    } else {
					        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveAppliedfortuesday.asString());
					    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s . Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }
			        // --- Week-off Day ---
			        Response currRespone = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfour, toDateofstatusfour);

			        if (currRespone.statusCode() == 200) {
			            String currStatus = currRespone.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, currStatus, sundaystatus);
			            if (sundaystatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + currRespone.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, dayAfterTomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + dayAfterTomorrow +
			                    ". API Response." + nextResp.asString());
			        }
			        
			        // --- TwoDay After Tomorrow ---
			        Response nextResponse = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfive, toDateofstatusfive);

			        if (nextResponse.statusCode() == 200) {
			            String nextStatus = nextResponse.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, twodayAfterTomorrow, nextStatus, twodayaftertommorrwostatus);
			            if (twodayaftertommorrwostatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + twodayaftertommorrwostatus +
			                    ". API Response." + nextResp.asString());
			        }

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
				
				// MPI_927_Leave_Policy_44
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_927_Leave_Policy_44()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Verify that 'A' status display in the reports, when user is on leave on Prefix AND completed half day on Suffix day of Week off");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,endpointoftransaction,cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,sundaystatus,fromdurationtypetwo,todurationtypetwo");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				   
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")
				    
				
			        String sundaystatus = data.get(i++);
			        int fromdurationtypetwo = Integer.parseInt(data.get(i++));
			        int todurationtypetwo = Integer.parseInt(data.get(i++));
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        String Tomorrow = currentDate.plusDays(1).toString();
			        // Day after tomorrow (two days after)
			        String dayAfterTomorrow = currentDate.plusDays(2).toString();
			     

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = dayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = dayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfour = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusfour = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = previousDate  + fromdate1;
					String todate = previousDate  + todate2;
					
					  String fromdatetwo = dayAfterTomorrow  + fromdate1;
						String todatetwo = dayAfterTomorrow  + todate2;
					
					
						String inouttime = dayAfterTomorrow + " " + inouttime1;
						String secondInOutTime = dayAfterTomorrow + " " + secondInOutTime2;
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				  
				    // Create API Page object
		
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				 // Punch IN
				    Response punchInResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
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
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, secondInOutTime, secondMode, photo
				    );

				    if (punchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				    
				    Response LeaveAppliedon2ndhalf = apiPage.executeCreateLeaves(
					        baseuri, loginendpoint, emailid, password, cmpcode,
					        baseuri, leaveendpoint,
					        type, entityid, leavetypeid,
					        totaldays, fromdatetwo, todatetwo,
					        fromdurationtypetwo, todurationtypetwo,
					        status, remarks, documentname, document
					    );

					    // Debugging - Print response details
					    System.out.println("Create Leave API Response Code." + LeaveAppliedon2ndhalf.getStatusCode());
					    System.out.println("Create Leave API Response Body." + LeaveAppliedon2ndhalf.asString());

					    if (LeaveAppliedon2ndhalf.getStatusCode() == 200) {
					        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdatetwo);
					    } else {
					        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveAppliedon2ndhalf.asString());
					    }
				    
				  
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }
			        // --- Week-off Day ---
			        Response currRespone = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfour, toDateofstatusfour);

			        if (currRespone.statusCode() == 200) {
			            String currStatus = currRespone.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, currStatus, sundaystatus);
			            if (sundaystatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + currRespone.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s punch in at %s and punch out at %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, dayAfterTomorrow, inouttime, secondInOutTime, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + dayAfterTomorrow +
			                    ". API Response." + nextResp.asString());
			        }
			        
			       

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
				
				// MPI_928_Leave_Policy_45
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
				public void MPI_928_Leave_Policy_45()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Verify that 'A' status display in the reports, when user is Absent on Prefix AND Suffix day of Week off & Holiday together, as user has Applied leave on Prefix and Suffix");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid,sundaystatus");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				   
				    
				    String holidayendpoint = data.get(i++);
			        String holidayname = data.get(i++);
			        String applicableto = data.get(i++);
			        String holidaydescription = data.get(i++);
			        String states = data.get(i++);
			        String stateid = data.get(i++);
			     
			        String sundaystatus = data.get(i++);
				    
				  
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        String Tomorrow = currentDate.plusDays(1).toString();
			        // Day after tomorrow (two days after)
			        String dayAfterTomorrow = currentDate.plusDays(2).toString();
			    
			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = dayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = dayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfour = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusfour = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = previousDate  + fromdate1;
					String todate = previousDate  + todate2;
					
					 String fromdatetwo = dayAfterTomorrow  + fromdate1;
						String todatetwo = dayAfterTomorrow  + todate2;
					
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				  
				    // Create API Page object
		
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				
				    
				    Response holidayResponse = apiPage.executeCreateHoliday(
			                baseuri, loginendpoint, emailid, password, cmpcode,
			                baseuri, holidayendpoint,
			                holidayname, Tomorrow, applicableto,
			                holidaydescription, states, stateid
			        );

			        if (holidayResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Holiday created successfully." + holidayname + Tomorrow);
			        } else {
			            logResults.createLogs("N", "FAIL", "Holiday creation failed." + holidayResponse.asString());
			        }
				    
				    Response LeaveAppliedfortuesday = apiPage.executeCreateLeaves(
					        baseuri, loginendpoint, emailid, password, cmpcode,
					        baseuri, leaveendpoint,
					        type, entityid, leavetypeid,
					        totaldays, fromdatetwo, todatetwo,
					        fromdurationtype, todurationtype,
					        status, remarks, documentname, document
					    );

					    // Debugging - Print response details
					    System.out.println("Create Leave API Response Code." + LeaveAppliedfortuesday.getStatusCode());
					    System.out.println("Create Leave API Response Body." + LeaveAppliedfortuesday.asString());

					    if (LeaveAppliedfortuesday.getStatusCode() == 200) {
					        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdatetwo);
					    } else {
					        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveAppliedfortuesday.asString());
					    }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s . Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }
			        // --- Week-off Day ---
			        Response currRespone = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfour, toDateofstatusfour);

			        if (currRespone.statusCode() == 200) {
			            String currStatus = currRespone.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, currStatus, sundaystatus);
			            if (sundaystatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + currRespone.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, dayAfterTomorrow, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + dayAfterTomorrow +
			                    ". API Response." + nextResp.asString());
			        }
			        
			       

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
			
				// MPI_929_Leave_Policy_46
				@Test(enabled = true, priority = 17, groups = { "Smoke" })
				public void MPI_929_Leave_Policy_46()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("sandwich " + 
							"Verify that 'L' status display in the reports, when user is on leave on Prefix AND applied 1st half leave and completed 2nd half   on Suffix day of Week off");

					 try {
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("sandwich_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,employeeuniqueid,previousdatestatus,dayaftertommorrowstatus,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,endpointoftransaction,cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,sundaystatus,fromdurationtypetwo,todurationtypetwo");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String employeeuniqueid = data.get(i++);
			        String previousdatestatus = data.get(i++);
			        String dayaftertommorrowstatus = data.get(i++);
			        String leaveendpoint = data.get(i++);		  
				    int type = Integer.parseInt(data.get(i++));
				    int entityid = Integer.parseInt(data.get(i++));
				    int leavetypeid = Integer.parseInt(data.get(i++));
				  
				    int totaldays = Integer.parseInt(data.get(i++));
				    String fromdate1 = data.get(i++);
				   
				    String todate2 = data.get(i++);
				  
				    int fromdurationtype = Integer.parseInt(data.get(i++));
				  
				    int todurationtype = Integer.parseInt(data.get(i++));
				   
				    int status = Integer.parseInt(data.get(i++));
				   
				    String remarks = data.get(i++);
				  
				    String documentname = data.get(i++);
				    String document = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				   
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")
				    
				
			        String sundaystatus = data.get(i++);
			        int fromdurationtypetwo = Integer.parseInt(data.get(i++));
			        int todurationtypetwo = Integer.parseInt(data.get(i++));
			        
			     // Parse the date string
			        LocalDate currentDate = LocalDate.parse(date);

			        // Previous day (one day before)
			        String previousDate = currentDate.minusDays(1).toString();
			        
			        String Tomorrow = currentDate.plusDays(1).toString();
			        // Day after tomorrow (two days after)
			        String dayAfterTomorrow = currentDate.plusDays(2).toString();
			     

			        String fromDateofstatus = previousDate + fromDateofuserstatus1;
			        String toDateofstatus = previousDate + toDateofuserstatus2;
			        
			        String fromDateofstatustwo = date + fromDateofuserstatus1;
			        String toDateofstatustwo = date + toDateofuserstatus2;
			        
			        String fromDateofstatusthree = dayAfterTomorrow + fromDateofuserstatus1;
			        String toDateofstatusthree = dayAfterTomorrow + toDateofuserstatus2;
			        
			        String fromDateofstatusfour = Tomorrow + fromDateofuserstatus1;
			        String toDateofstatusfour = Tomorrow + toDateofuserstatus2;
			        
			        String fromdate = previousDate  + fromdate1;
					String todate = previousDate  + todate2;
					
					  String fromdatetwo = dayAfterTomorrow  + fromdate1;
						String todatetwo = dayAfterTomorrow  + todate2;
					
					
						String inouttime = dayAfterTomorrow + " " + inouttime1;
						String secondInOutTime = dayAfterTomorrow + " " + secondInOutTime2;
			
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				  
				    // Create API Page object
		
				    Response LeaveApplied = apiPage.executeCreateLeaves(
				        baseuri, loginendpoint, emailid, password, cmpcode,
				        baseuri, leaveendpoint,
				        type, entityid, leavetypeid,
				        totaldays, fromdate, todate,
				        fromdurationtype, todurationtype,
				        status, remarks, documentname, document
				    );

				    // Debugging - Print response details
				    System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
				    System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

				    if (LeaveApplied.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdate);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				 // Punch IN
				    Response punchInResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
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
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, secondInOutTime, secondMode, photo
				    );

				    if (punchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				    
				    Response LeaveAppliedon2ndhalf = apiPage.executeCreateLeaves(
					        baseuri, loginendpoint, emailid, password, cmpcode,
					        baseuri, leaveendpoint,
					        type, entityid, leavetypeid,
					        totaldays, fromdatetwo, todatetwo,
					        fromdurationtypetwo, todurationtypetwo,
					        status, remarks, documentname, document
					    );

					    // Debugging - Print response details
					    System.out.println("Create Leave API Response Code." + LeaveAppliedon2ndhalf.getStatusCode());
					    System.out.println("Create Leave API Response Body." + LeaveAppliedon2ndhalf.asString());

					    if (LeaveAppliedon2ndhalf.getStatusCode() == 200) {
					        logResults.createLogs("N", "PASS", "Leave Applied successfully" + fromdatetwo);
					    } else {
					        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveAppliedon2ndhalf.asString());
					    }
				    
				  
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, previousDate + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response prevResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (prevResp.statusCode() == 200) {
			            String prevStatus = prevResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on this date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, previousDate, prevStatus, previousdatestatus);
			            if (previousdatestatus.equalsIgnoreCase(prevStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + previousDate +
			                    ". API Response." + prevResp.asString());
			        }

			        // --- Week-off Day ---
			        Response currResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo);

			        if (currResp.statusCode() == 200) {
			            String currStatus = currResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, currStatus, expectedStatus);
			            if (expectedStatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + date +
			                    ". API Response." + currResp.asString());
			        }
			        // --- Week-off Day ---
			        Response currRespone = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusfour, toDateofstatusfour);

			        if (currRespone.statusCode() == 200) {
			            String currStatus = currRespone.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, Tomorrow, currStatus, sundaystatus);
			            if (sundaystatus.equalsIgnoreCase(currStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + Tomorrow +
			                    ". API Response." + currRespone.asString());
			        }

			        // --- Day After Tomorrow ---
			        Response nextResp = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusthree, toDateofstatusthree);

			        if (nextResp.statusCode() == 200) {
			            String nextStatus = nextResp.jsonPath().getString("[0].AttnFinalStatus");
			            String msg = String.format("%s â€“ Employee %s on %s punch in at %s and punch out at %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, dayAfterTomorrow, inouttime, secondInOutTime, nextStatus, dayaftertommorrowstatus);
			            if (dayaftertommorrowstatus.equalsIgnoreCase(nextStatus))
			                logResults.createLogs("N", "PASS", "âœ… " + msg);
			            else
			                logResults.createLogs("N", "FAIL", "âŒ " + msg);
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status for " + dayAfterTomorrow +
			                    ". API Response." + nextResp.asString());
			        }
			        
			       

			    } catch (Exception e) {
			        logResults.createLogs("N", "FAIL", "âŒ Exception occurred." + e.getMessage());
			    }
				}
				
	
	
	
	
	
	@AfterMethod(alwaysRun = true)
	void Aftermethod() {
		logResults.onlyLog();
		
	}

	@AfterClass(alwaysRun = true)
	void cleanUp() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
	
}

