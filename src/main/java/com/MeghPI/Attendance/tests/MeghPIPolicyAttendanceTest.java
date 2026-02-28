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


public class MeghPIPolicyAttendanceTest {

	
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
				@Test(enabled = true, priority = 0, invocationCount = 2, groups = { "Smoke" })
				public void MPI_879_Normal_Attendance_71() {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " +  "Morning" +
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
				
	
	// MPI_441_Attendance_Policy_08
	@Test(enabled = true, priority = 1, invocationCount = 2, groups = { "Smoke" })
	public void MPI_441_Attendance_Policy_08()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
	
		logResults.setScenarioName("Policy " + 
				"Check the status when 'Every Clock In & Clock Out' is applied in the policy and the employee did last punch in at end of shift.");

		 
		 // Load all data including punch-out time and mode
	    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
	            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
	            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
	            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

	    int i = 0;
	    String cmpcode = data.get(i++);
	    String emailid = data.get(i++);
	    String password = data.get(i++);
	    
	    String baseuri = data.get(i++);
	    String loginendpoint = data.get(i++);
	    String endpointoftransaction = data.get(i++);
	    String cardnumber = data.get(i++);
	    int cardtype = Integer.parseInt(data.get(i++));
	    String deviceuniqueid = data.get(i++);
	    String bio1finger = data.get(i++);
	    String bio2finger  = data.get(i++);
	    String employeeuniqueid = data.get(i++);
	    String locationid = data.get(i++);
	    String inouttime1 = data.get(i++);     // Punch In time
	    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
	    String photo = data.get(i++);
	    String secondInOutTime2 = data.get(i++); // Punch Out time
	    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

	    String date =  data.get(i++);
	    String statusEndpoint    = data.get(i++);
        String fromDateofuserstatus1          = data.get(i++);
        String toDateofuserstatus2            = data.get(i++);
        String expectedStatus    = data.get(i++);
        String description = data.get(i++);
        String updateattendanceendpoint = data.get(i++);
        
       

        String fromDateofstatus = date + fromDateofuserstatus1;
        String toDateofstatus = date + toDateofuserstatus2;
	    
	    
		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;
	    
	    
	    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
	        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
        } else {
            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
            return;
        }
	 // Trigger attendance update first
	    Response updateResp = apiPage.executeUpdateAttendance(
	            baseuri, loginendpoint,
	            emailid, password, cmpcode,
	            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
	            employeeuniqueid, date + "T00:00:00.000Z"
	    );

	    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
	        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
	    } else {
	        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
	    }
	    
	 // Get User Status
        Response validation = apiPage.executeGetUserStatus(
                baseuri, loginendpoint,
                emailid, password, cmpcode,
                baseuri, statusEndpoint,
                employeeuniqueid, fromDateofstatus, toDateofstatus
        );

        if (validation.statusCode() == 200) {
            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

            // Make sentence using excel inputs
            String finalSentence = String.format(
                "%s â€“ This Employee %s on %s, punched IN at %s and Punch IN at %s. Final Status = %s (Expected = %s)",
                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
            );

            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
            } else {
                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
            }
        } else {
            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
        }

	}
	
	
	// MPI_658_Attendance_Policy_58
		@Test(enabled = true, priority = 2, groups = { "Smoke" })
		public void MPI_658_Attendance_Policy_58()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName("Policy " + 
					"Check the status when 'Every Clock In & Clock Out' is applied in the policy and the employee did last punch out at end of shift.");

			 
			 // Load all data including punch-out time and mode
		    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
		            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
		            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
		            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

		    int i = 0;
		    String cmpcode = data.get(i++);
		    String emailid = data.get(i++);
		    String password = data.get(i++);
		    
		    String baseuri = data.get(i++);
		    String loginendpoint = data.get(i++);
		    String endpointoftransaction = data.get(i++);
		    String cardnumber = data.get(i++);
		    int cardtype = Integer.parseInt(data.get(i++));
		    String deviceuniqueid = data.get(i++);
		    String bio1finger = data.get(i++);
		    String bio2finger  = data.get(i++);
		    String employeeuniqueid = data.get(i++);
		    String locationid = data.get(i++);
		    String inouttime1 = data.get(i++);     // Punch In time
		    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
		    String photo = data.get(i++);
		    String secondInOutTime2 = data.get(i++); // Punch Out time
		    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

		    String date =  data.get(i++);
		    String statusEndpoint    = data.get(i++);
	        String fromDateofuserstatus1          = data.get(i++);
	        String toDateofuserstatus2            = data.get(i++);
	        String expectedStatus    = data.get(i++);
	        String description = data.get(i++);
	        String updateattendanceendpoint = data.get(i++);
	        
	        

	        String fromDateofstatus = date + fromDateofuserstatus1;
	        String toDateofstatus = date + toDateofuserstatus2;
		    
		    
			String inouttime = date + " " + inouttime1;
			String secondInOutTime = date + " " + secondInOutTime2;
		    
		    
		    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
		        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
	            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
	        } else {
	            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
	            return;
	        }
		 // Trigger attendance update first
		    Response updateResp = apiPage.executeUpdateAttendance(
		            baseuri, loginendpoint,
		            emailid, password, cmpcode,
		            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
		            employeeuniqueid, date + "T00:00:00.000Z"
		    );

		    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
		        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		    } else {
		        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		    }
		    
		 // Get User Status
	        Response validation = apiPage.executeGetUserStatus(
	                baseuri, loginendpoint,
	                emailid, password, cmpcode,
	                baseuri, statusEndpoint,
	                employeeuniqueid, fromDateofstatus, toDateofstatus
	        );

	        if (validation.statusCode() == 200) {
	            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

	            // Make sentence using excel inputs
	            String finalSentence = String.format(
	                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
	                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
	            );

	            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
	                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
	            } else {
	                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
	            }
	        } else {
	            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
	        }

		}
	
		// MPI_660_Attendance_Policy_60
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_660_Attendance_Policy_60()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"To Verify this, check the OT hours when the user is present for more than the specified shift duration, but overtime is not configured and 'Multiple Shift Detection' is set to 'No' ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");
			            String otHours = validation.jsonPath().getString("[0].OTHours"); // new line
			            

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. " +
			                    "Final Status = %s (Expected = %s), OT Hours = %s",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2, 
			                    actualStatus, expectedStatus, otHours == null ? "00:00" : otHours
			                );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
	
				// MPI_445_Attendance_Policy_12
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_445_Attendance_Policy_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status of the employee when \"Ignore Punch Minutes\" is set to 1 minute, and the employee performs punch in and punch out within 1 minute  ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
	
				// MPI_451_Attendance_Policy_18
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_451_Attendance_Policy_18()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when \"No Punch\" is selected as the Punch Type in the Attendance Policy.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,employeeuniqueid," +
				            "date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String employeeuniqueid = data.get(i++);

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			       
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
			        
			        LocalDate localDate = LocalDate.parse(date);
					

					// get first day of month
					 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				    // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, firstDayOfMonth + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    try {
						Thread.sleep(8000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on This Date %s . Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				// MPI_452_Attendance_Policy_19
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_452_Attendance_Policy_19()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when \"Punch Required\" is selected as \"One\" in the Attendance Policy.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				   

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
			    
					String inouttime = date + " " + inouttime1;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
				    } else {
				        logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
				        return;
				    }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
    
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s . Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				//MPI_453_Attendance_Policy_20
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_453_Attendance_Policy_20()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Check the status when \"Punch Required\" is set to \"Four\" in the Attendance Policy; as an employee, perform 4 punches (In, Out, In, Out) with the 4th punch out at shift end"
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
					    logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s And Punch In at %s and Last Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, thirdinouttime, fourthinouttime, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}	
				
				// MPI_450_Attendance_Policy_17
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_450_Attendance_Policy_17()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the Shift Type is configured as \"Fixed\" in the policy and the employee completes the shift according to the  Shift timings ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}	
				
				// MPI_661_Attendance_Policy_61
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_661_Attendance_Policy_61()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the Shift Type is configured as \"Fixed\" in the policy, and the employee completes the First Shift but is assigned to the General Shift. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				// MPI_455_Attendance_Policy_22
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_455_Attendance_Policy_22()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when Grace Period is enabled, Early Clock Out is selected, Grace Hours is set to 60 minutes, and Allowed Times Per Month is set to 2. The employee leaves early by 1 hour.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}	
				
				// MPI_456_Attendance_Policy_23
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_456_Attendance_Policy_23()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when Grace Period is enabled and Late Clock In is selected, Grace Hours is set to 60 minutes, and Allowed Times Per Month is set to 2. The employee punches in 1 hour after the shift start time.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}			
				
				// MPI_459_Attendance_Policy_26
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_459_Attendance_Policy_26()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when \"Apply Penalty\" is enabled and \"Half Day\" is selected as the penalty type. Set the Late Clock In grace period to 1 hour with 2 allowed instances per month. As an employee, arrive 1 hour late three times in a month. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String Twodaysstatus = "P";
	
			        LocalDate localDate = LocalDate.parse(date); 
					String date2 = localDate.plusDays(1).toString();
					
					 LocalDate localDate2 = LocalDate.parse(date2); 
						String date3 = localDate2.plusDays(1).toString();
						
						 String fromDateofstatusone = date2 + fromDateofuserstatus1;
					        String toDateofstatusone = date2 + toDateofuserstatus2;
					        
					        String fromDateofstatustwo = date3 + fromDateofuserstatus1;
					        String toDateofstatustwo = date3 + toDateofuserstatus2;
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				
					String inouttimeone = date2 + " " + inouttime1;
					String secondInOutTimeone = date2 + " " + secondInOutTime2;
					
					String inouttimetwo = date3 + " " + inouttime1;
					String secondInOutTimetwo = date3 + " " + secondInOutTime2;
					
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				    // Punch IN
				    Response punchInResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, inouttimeone, mode, photo
				    );

				    if (punchInResponse.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttimeone);
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
				            locationid, secondInOutTimeone, secondMode, photo
				    );

				    if (punchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTimeone);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusone, toDateofstatusone
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date2, inouttimeone, secondInOutTimeone, actualStatus, Twodaysstatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }
			        
			        // Punch IN
				    Response punchInResponse2 = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, inouttimetwo, mode, photo
				    );

				    if (punchInResponse2.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttimetwo);
				    } else {
				        logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse2.asString());
				        return;
				    }

				    // Punch OUT
				    Response punchOutResponse2 = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, secondInOutTimetwo, secondMode, photo
				    );

				    if (punchOutResponse2.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTimetwo);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse2.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp2 = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp2.statusCode() == 200 && updateResp2.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp2.asString());
				    }
				 // Get User Status
			        Response validation2 = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo
			        );

			        if (validation2.statusCode() == 200) {
			            String actualStatus = validation2.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date3, inouttimetwo, secondInOutTimetwo, actualStatus, Twodaysstatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

			        // Punch IN
				    Response punchInResponse3 = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, inouttime, mode, photo
				    );

				    if (punchInResponse3.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
				    } else {
				        logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse3.asString());
				        return;
				    }

				    // Punch OUT
				    Response punchOutResponse3 = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, secondInOutTime, secondMode, photo
				    );

				    if (punchOutResponse3.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse3.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp3 = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp3.statusCode() == 200 && updateResp3.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp3.asString());
				    }
				    
				 // Get User Status
			        Response validation3 = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation3.statusCode() == 200) {
			            String actualStatus = validation3.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }   
		 
				}	
				
				// MPI_460_Attendance_Policy_27
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_460_Attendance_Policy_27()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when \"Apply Penalty\" is enabled and \"Full Day\" is selected as the penalty type. Set the Late Clock In grace period to 1 hour with 2 allowed instances per month. As an employee, arrive 1 hour late three times in a month  ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String Twodaysstatus = "P";
	
			        LocalDate localDate = LocalDate.parse(date); 
					String date2 = localDate.plusDays(1).toString();
					
					 LocalDate localDate2 = LocalDate.parse(date2); 
						String date3 = localDate2.plusDays(1).toString();
						
						 String fromDateofstatusone = date2 + fromDateofuserstatus1;
					        String toDateofstatusone = date2 + toDateofuserstatus2;
					        
					        String fromDateofstatustwo = date3 + fromDateofuserstatus1;
					        String toDateofstatustwo = date3 + toDateofuserstatus2;
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				
					String inouttimeone = date2 + " " + inouttime1;
					String secondInOutTimeone = date2 + " " + secondInOutTime2;
					
					String inouttimetwo = date3 + " " + inouttime1;
					String secondInOutTimetwo = date3 + " " + secondInOutTime2;
					
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				    // Punch IN
				    Response punchInResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, inouttimeone, mode, photo
				    );

				    if (punchInResponse.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttimeone);
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
				            locationid, secondInOutTimeone, secondMode, photo
				    );

				    if (punchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTimeone);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				    
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatusone, toDateofstatusone
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date2, inouttimeone, secondInOutTimeone, actualStatus, Twodaysstatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }
			        
			        // Punch IN
				    Response punchInResponse2 = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, inouttimetwo, mode, photo
				    );

				    if (punchInResponse2.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttimetwo);
				    } else {
				        logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse2.asString());
				        return;
				    }

				    // Punch OUT
				    Response punchOutResponse2 = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, secondInOutTimetwo, secondMode, photo
				    );

				    if (punchOutResponse2.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTimetwo);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse2.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp2 = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp2.statusCode() == 200 && updateResp2.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp2.asString());
				    }
				 // Get User Status
			        Response validation2 = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatustwo, toDateofstatustwo
			        );

			        if (validation2.statusCode() == 200) {
			            String actualStatus = validation2.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date3, inouttimetwo, secondInOutTimetwo, actualStatus, Twodaysstatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

			        // Punch IN
				    Response punchInResponse3 = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, inouttime, mode, photo
				    );

				    if (punchInResponse3.getStatusCode() == 200) {
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
				    } else {
				        logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse3.asString());
				        return;
				    }

				    // Punch OUT
				    Response punchOutResponse3 = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, secondInOutTime, secondMode, photo
				    );

				    if (punchOutResponse3.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse3.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp3 = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp3.statusCode() == 200 && updateResp3.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp3.asString());
				    }
				 // Get User Status
			        Response validation3 = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation3.statusCode() == 200) {
			            String actualStatus = validation3.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }   
			 
				}
				
				// MPI_464_Attendance_Policy_30
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_464_Attendance_Policy_30()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when \"Four\" is selected as the Punch Require option in the attendance policy, and the employee punches in at shift start time and punches out at shift end time (total 2 punches). ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				// MPI_465_Attendance_Policy_31
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_465_Attendance_Policy_31()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Calculate overtime hours using the 'Total Hours minus Shift Duration' calculation method");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}


				//MPI_466_Attendance_Policy_32
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
				public void MPI_466_Attendance_Policy_32()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Calculate overtime hours using the '(Total Effective Hours) - (Shift Duration)' calculation method."
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,effectivehours");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String effectivehours = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Handle null OTHours â†’ default to 00:00
			            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			         // Handle null EffectiveHours â†’ default to 00:00
			        	String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			        	actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00" : actualEffectiveHours;
			            
			            
			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                actualStatus, expectedStatus, actualEffectiveHours, effectivehours, actualOTHours, totalOTHour
			            );

			            // Validate both Final Status & OT Hours
			            if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                totalOTHour.equalsIgnoreCase(actualOTHours) &&
			            effectivehours.equalsIgnoreCase(actualEffectiveHours)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			        }

				}	
				
				// MPI_467_Attendance_Policy_33
				@Test(enabled = true, priority = 17, groups = { "Smoke" })
				public void MPI_467_Attendance_Policy_33()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Calculate overtime hours using the 'Shift End Time to Out Punch' calculation method.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_468_Attendance_Policy_34
				@Test(enabled = true, priority = 18, groups = { "Smoke" })
				public void MPI_468_Attendance_Policy_34()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Calculate overtime hours using the 'In Punch to Shift Start Time' calculation method. (approved by system selected in attendance policy)");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_469_Attendance_Policy_35
				@Test(enabled = true, priority = 19, groups = { "Smoke" })
				public void MPI_469_Attendance_Policy_35()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Calculate overtime hours using the '(In Punch to Shift Start Time) + (Shift End Time to Out Punch)' calculation method. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_470_Attendance_Policy_36
				@Test(enabled = true, priority = 20, groups = { "Smoke" })
				public void MPI_470_Attendance_Policy_36()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Calculate OT hours when the calculation method is '(Total Hours â€“ Shift Duration)', with OT starting after 00:15 is configured. The employee punches out 45 minutes after the shift end time");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_876_Attendance_Policy_72
				@Test(enabled = true, priority = 21, groups = { "Smoke" })
				public void MPI_876_Attendance_Policy_72()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Calculate OT hours when the calculation method is '(Total Hours â€“ Shift Duration)', with OT starting before 00:15 is configured. The employee punches In 45 minutes before the shift start time");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_878_Attendance_Policy_73
				@Test(enabled = true, priority = 22, groups = { "Smoke" })
				public void MPI_878_Attendance_Policy_73()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Calculate OT hours when the calculation method is '(Total Hours â€“ Shift Duration)', with OT starting after 00:15 is configured. The employee punches out 10 minutes after the shift end time");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_878_Attendance_Policy_74
				@Test(enabled = true, priority = 23, groups = { "Smoke" })
				public void MPI_878_Attendance_Policy_74()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Calculate OT hours when the calculation method is '(Total Hours â€“ Shift Duration)', with OT starting before 00:15 is configured. The employee punches In 10 minutes before the shift start time");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_472_Attendance_Policy_38
				@Test(enabled = true, priority = 24, groups = { "Smoke" })
				public void MPI_472_Attendance_Policy_38()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"To check that, select \"Allow Overtime OR Comp-off on Week-off\", choose \"Overtime\", set Full Day = 9 hrs and Half Day = 4:30 hrs. As an employee, work on a week-off and complete 4:30 hours â€” ensure the employee is eligible for 4 hours 30 min OT. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}	
				
				// MPI_475_Attendance_Policy_41
				@Test(enabled = true, priority = 25, groups = { "Smoke" })
				public void MPI_475_Attendance_Policy_41()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"To check that, select \"Allow Overtime OR Comp-off on Week-off\", choose \"Overtime\", and set Full Day (Minimum Hours) = 9 hrs, Half Day (Minimum Hours) = 4:30 hrs. As an employee, work 9 hours on a week-off â€” ensure the system displayed 9 hr OT for that weekoff day ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_473_Attendance_Policy_39
				@Test(enabled = true, priority = 26, groups = { "Smoke" })
				public void MPI_473_Attendance_Policy_39()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"To check that, select \"Allow Overtime OR Comp-off on Week-off\", choose \"Overtime\", and set Hourly Max Hours = 12 hrs. As an employee, work on a week-off day and complete 10 hours â€” ensure the system calculates and displays 10 hours of OT.  ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_880_Attendance_Policy_75
				@Test(enabled = true, priority = 27, groups = { "Smoke" })
				public void MPI_880_Attendance_Policy_75()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"To check that, select \"Allow Overtime OR Comp-off on Holiday\", choose \"Overtime\", set Full Day = 9 hrs and Half Day = 4:30 hrs. As an employee, work on a holidayÂ and complete 9 hours â€” ensure the employee is eligible for 9 hours OT.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String holidayendpoint = data.get(i++);
			        String holidayname = data.get(i++);
			        String applicableto = data.get(i++);
			        String holidaydescription = data.get(i++);
			        String states = data.get(i++);
			        String stateid = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				    Response holidayResponse = apiPage.executeCreateHoliday(
			                baseuri, loginendpoint, emailid, password, cmpcode,
			                baseuri, holidayendpoint,
			                holidayname, date, applicableto,
			                holidaydescription, states, stateid
			        );

			        if (holidayResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Holiday created successfully." + holidayname);
			        } else {
			            logResults.createLogs("N", "FAIL", "Holiday creation failed." + holidayResponse.asString());
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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_662_Attendance_Policy_62
				@Test(enabled = true, priority = 28, groups = { "Smoke" })
				public void MPI_662_Attendance_Policy_62()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"To check that, select \"Allow Overtime OR Comp-off on Holiday\", choose \"Overtime\", set Full Day = 9 hrs and Half Day = 4:30 hrs. As an employee, work on a holiday and complete 4:30 hours â€” ensure the employee is eligible for 4 hours 30 minutes OT. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			   
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				   

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}	
				
				// MPI_663_Attendance_Policy_63
				@Test(enabled = true, priority = 29, groups = { "Smoke" })
				public void MPI_663_Attendance_Policy_63()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"To check that, select \"Allow Overtime OR Comp-off on Holiday\", choose \"Overtime\", and set Hourly Maximum Hours = 12 hrs. As an employee, work on a holiday and complete 10 hours â€” ensure the employee is credited with 10 hours of OT. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			      
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				  

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}	
				
				//MPI_667_Leave_Policy_34
				@Test(enabled = true, priority = 30, groups = { "Smoke" })
				public void MPI_667_Leave_Policy_34()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Check the status when the employee absent on unpaid leave "
				    );

				    
				    ArrayList<String> data = initBase.loadExcelData(
				        "Policy_Attendance",
				        currTC,
				        "cmpcode,emailid,password,baseuri,loginendpoint,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,employeeuniqueid,updateattendanceendpoint"
				    );
				    System.out.println("Excel Data Loaded." + data);

				    int i = 0;
				    String cmpcode = data.get(i);
				    String emailid = data.get(++i);		   
				    String password = data.get(++i);	  
				    
				    String baseuri = data.get(++i);		   
				    String loginendpoint = data.get(++i);		   
				    String leaveendpoint = data.get(++i);		  
				    int type = Integer.parseInt(data.get(++i));
				    int entityid = Integer.parseInt(data.get(++i));
				    int leavetypeid = Integer.parseInt(data.get(++i));
				  
				    int totaldays = Integer.parseInt(data.get(++i));
				    String fromdate1 = data.get(++i);
				   
				    String todate2 = data.get(++i);
				  
				    int fromdurationtype = Integer.parseInt(data.get(++i));
				  
				    int todurationtype = Integer.parseInt(data.get(++i));
				   
				    int status = Integer.parseInt(data.get(++i));
				   
				    String remarks = data.get(++i);
				  
				    String documentname = data.get(++i);
				    String document = data.get(++i);
				    
				    String date =  data.get(++i);
				    String statusEndpoint    = data.get(++i);
			        String fromDateofuserstatus1          = data.get(++i);
			        String toDateofuserstatus2            = data.get(++i);
			        String expectedStatus    = data.get(++i);
			        String description = data.get(++i);
			        String employeeuniqueid = data.get(++i);
			        String updateattendanceendpoint = data.get(++i);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				  
					String fromdate = date  + fromdate1;
					String todate = date  + todate2;
				   
				    // Debugging - Print extracted values
			

				    // Create API Page object
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
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
				        logResults.createLogs("N", "PASS", "Unpaid Leave Applied successfully" + date);
				    } else {
				        logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
				    }
				    
				 // Trigger attendance update first
			        Response updateResp = apiPage.executeUpdateAttendance(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
			                employeeuniqueid, date + "T00:00:00.000Z"
			        );

			        if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			            logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
			        } else {
			            logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
			        }
				    
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			            	    "%s â€“ Employee %s applied Unpaid leave on %s. Final Status = %s (Expected = %s)",
			            	    description, employeeuniqueid, date, actualStatus, expectedStatus
			            	);


			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				//MPI_674_Shift_policy_19
				@Test(enabled = true, priority = 31, groups = { "Smoke" })
				public void MPI_674_Shift_policy_19()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Calculate the effective hours when the employee takes a break as per the defined break timings and punches out exactly at the shift end time"
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,effectivehours");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String effectivehours = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
					    logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            // extract fields from API response
			        	String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			        	// Handle null EffectiveHours â†’ default to 00:00
			        	String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			        	actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00" : actualEffectiveHours;

			        	// Make sentence using excel inputs
			        	String finalSentence = String.format(
			        	    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s and Punch In at %s and Last Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s)",
			        	    description, employeeuniqueid, date, inouttime1, secondInOutTime, thirdinouttime, fourthinouttime, actualStatus, expectedStatus, actualEffectiveHours, effectivehours
			        	);

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}	
				
				// MPI_675_Attendance_Policy_67
				@Test(enabled = true, priority = 32, groups = { "Smoke" })
				public void MPI_675_Attendance_Policy_67()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee punches in within 1 hour of the buffer time and the shift type is selected as \"Fixed\" in attendance policy ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
			
				// MPI_164_MeghPi_Shift_03
				@Test(enabled = true, priority = 33, groups = { "Smoke" })
				public void MPI_164_MeghPi_Shift_03()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee punches in early, and the shift is configured with a 30min buffer time by selecting \"Before\" in the buffer time option ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}

				// MPI_708_MeghPi_Shift_21
				@Test(enabled = true, priority = 34, groups = { "Smoke" })
				public void MPI_708_MeghPi_Shift_21()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status for an employee by doing a late punch-in when the buffer time has been set to \"Before Shift Start\" in the policy. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				// MPI_175_MeghPi_Shift_07
				@Test(enabled = true, priority = 35, groups = { "Smoke" })
				public void MPI_175_MeghPi_Shift_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Configure 15 minutes in the 'Before' buffer time and 30 minutes in the 'After' buffer time. Check the status for the employee by punching in 10 minutes before shift start time.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				// MPI_714_MeghPi_Shift_23
				@Test(enabled = true, priority = 36, groups = { "Smoke" })
				public void MPI_714_MeghPi_Shift_23()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee performs a late punch and the shift buffer time is configured as \"After 30 minutes\"");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				// MPI_715_MeghPi_Shift_24
				@Test(enabled = true, priority = 37, groups = { "Smoke" })
				public void MPI_715_MeghPi_Shift_24()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee performs a late punch beyond the defined buffer time and the shift buffer time is configured as \"After 30 minutes of shift start time");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				// MPI_716_MeghPi_Shift_25
				@Test(enabled = true, priority = 38, groups = { "Smoke" })
				public void MPI_716_MeghPi_Shift_25()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee performs a early punch but shift buffer time is configured as \"After 30 minutes\" of shift start time.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				// MPI_717_MeghPi_Shift_26
				@Test(enabled = true, priority = 39, groups = { "Smoke" })
				public void MPI_717_MeghPi_Shift_26()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee punches in earlier than the configured buffer time, and the shift is set with a 30-minute before buffer. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				//MPI_718_MeghPi_Shift_27
				@Test(enabled = true, priority = 40, groups = { "Smoke" })
				public void MPI_718_MeghPi_Shift_27()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Calculate the effective working hours and status of an employee  when the employee took a break before the break start time configured in the shift."
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,effectivehours");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String effectivehours = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
					    logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            // extract fields from API response
			        	String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			        	// Handle null EffectiveHours â†’ default to 00:00
			        	String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			        	actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00" : actualEffectiveHours;

			        	// Make sentence using excel inputs
			        	String finalSentence = String.format(
			        	    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s and Punch In at %s and Last Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s)",
			        	    description, employeeuniqueid, date, inouttime1, secondInOutTime, thirdinouttime, fourthinouttime, actualStatus, expectedStatus, actualEffectiveHours, effectivehours
			        	);

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}	
				
				//MPI_719_MeghPi_Shift_28
				@Test(enabled = true, priority = 41, groups = { "Smoke" })
				public void MPI_719_MeghPi_Shift_28()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Check the status and calculate effective working hours when the employee took a break after the break end time configured in the shift."
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,effectivehours");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String effectivehours = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
					    logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            // extract fields from API response
			        	String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			        	// Handle null EffectiveHours â†’ default to 00:00
			        	String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			        	actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00" : actualEffectiveHours;

			        	// Make sentence using excel inputs
			        	String finalSentence = String.format(
			        	    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s and Punch In at %s and Last Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s)",
			        	    description, employeeuniqueid, date, inouttime1, secondInOutTime, thirdinouttime, fourthinouttime, actualStatus, expectedStatus, actualEffectiveHours, effectivehours
			        	);

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				//MPI_726_Attendance_Policy_68
				@Test(enabled = true, priority = 42, groups = { "Smoke" })
				public void MPI_726_Attendance_Policy_68()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Check the status when 'Every Clock In & Clock Out' is applied in the policy and the employee did last punch in at end of shift and after 2min did punch out"
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,effectivehours,fourthinouttime,fourthmode");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String effectivehours = data.get(i++);
			        String fourthinouttime4 = data.get(i++);
			        String  fourthmode = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime =  date + " " + fourthinouttime4;
					
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
					    logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
					} else {
					    logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
					    return;
					}

				    // Punch out
				    Response punchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, secondInOutTime, secondMode, photo
				    );

				    if (punchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch Out executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch Out failed." + punchOutResponse.asString());
			            return;
			        }
				    
				    // Punch Out at last
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", " Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch Out at last
				    Response LastpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (LastpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", " Punch OUt executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch Out failed." + LastpunchOutResponse.asString());
			            return;
			        }
				    
				
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            // extract fields from API response
			        	String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			        	// Handle null EffectiveHours â†’ default to 00:00
			        	String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			        	actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00" : actualEffectiveHours;

			        	// Make sentence using excel inputs
			        	String finalSentence = String.format(
			        	    "%s â€“ This Employee %s on %s, punched IN at %s and Punch out at %s and Punch In at %s and Punch OUT at %s . Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s)",
			        	    description, employeeuniqueid, date, inouttime1, secondInOutTime, thirdinouttime, fourthinouttime, actualStatus, expectedStatus, actualEffectiveHours, effectivehours
			        	);

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				// MPI_639_Normal_Attendance_11
				@Test(enabled = true, priority = 43, groups = { "Smoke" })
				public void MPI_639_Normal_Attendance_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee performs only a single punch on a holiday");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				  

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			      
			        String holidayendpoint = data.get(i++);
			        String holidayname = data.get(i++);
			        String applicableto = data.get(i++);
			        String holidaydescription = data.get(i++);
			        String states = data.get(i++);
			        String stateid = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
			
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				    Response holidayResponse = apiPage.executeCreateHoliday(
			                baseuri, loginendpoint, emailid, password, cmpcode,
			                baseuri, holidayendpoint,
			                holidayname, date, applicableto,
			                holidaydescription, states, stateid
			        );

			        if (holidayResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Holiday created successfully." + holidayname);
			        } else {
			            logResults.createLogs("N", "FAIL", "Holiday creation failed." + holidayResponse.asString());
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

				   
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			               

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s . Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1,
			                    actualStatus, expectedStatus
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) )
			                   {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_650_Normal_Attendance_22
				@Test(enabled = true, priority = 44, groups = { "Smoke" })
				public void MPI_650_Normal_Attendance_22()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee has not completed the minimum hours required for half day on a holiday.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			       
			        
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_640_Normal_Attendance_12
				@Test(enabled = true, priority = 45, groups = { "Smoke" })
				public void MPI_640_Normal_Attendance_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee is present on a holiday");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			       
			        
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_642_Normal_Attendance_14
				@Test(enabled = true, priority = 46, groups = { "Smoke" })
				public void MPI_642_Normal_Attendance_14()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when there is a holiday.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,employeeuniqueid," +
				            "date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				   
				    String employeeuniqueid = data.get(i++);
				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, actualStatus, expectedStatus
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_643_Normal_Attendance_15
				@Test(enabled = true, priority = 47, groups = { "Smoke" })
				public void MPI_643_Normal_Attendance_15()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when there is a holiday and a week off on the same day");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,employeeuniqueid," +
				            "date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				   
				    String employeeuniqueid = data.get(i++);
				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String holidayendpoint = data.get(i++);
			        String holidayname = data.get(i++);
			        String applicableto = data.get(i++);
			        String holidaydescription = data.get(i++);
			        String states = data.get(i++);
			        String stateid = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
				    //Create holiday On Weekoff
				    Response holidayResponse = apiPage.executeCreateHoliday(
			                baseuri, loginendpoint, emailid, password, cmpcode,
			                baseuri, holidayendpoint,
			                holidayname, date, applicableto,
			                holidaydescription, states, stateid
			        );

			        if (holidayResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Holiday created On Weekoff successfully." + holidayname);
			        } else {
			            logResults.createLogs("N", "FAIL", "Holiday creation failed." + holidayResponse.asString());
			        }
				    
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ For This Employee %s on This date %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, actualStatus, expectedStatus
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_648_Normal_Attendance_20
				@Test(enabled = true, priority = 48, groups = { "Smoke" })
				public void MPI_648_Normal_Attendance_20()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee completes a half day on holiday.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			       
			        
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_649_Normal_Attendance_21
				@Test(enabled = true, priority = 49, groups = { "Smoke" })
				public void MPI_649_Normal_Attendance_21()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee completes a half day on a day that is both a holiday and a weekoff. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			       
			        
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_651_Normal_Attendance_23
				@Test(enabled = true, priority = 50, groups = { "Smoke" })
				public void MPI_651_Normal_Attendance_23()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee has not completed the minimum hours required for half day on a day that is both a week off and a holiday. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			       
			        
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_653_Normal_Attendance_25
				@Test(enabled = true, priority = 51, groups = { "Smoke" })
				public void MPI_653_Normal_Attendance_25()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee is present on a day that is both a holiday and a week off. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			       
			        
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_654_Normal_Attendance_26
				@Test(enabled = true, priority = 52, groups = { "Smoke" })
				public void MPI_654_Normal_Attendance_26()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the employee did a single punch in on a day that is both a week off and a holiday. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				   

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			       
			        
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
				
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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

				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s. Final Status = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1,
			                    actualStatus, expectedStatus
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				//MPI_882_Attendance_Policy_76
				@Test(enabled = true, priority = 53, groups = { "Smoke" })
				public void MPI_882_Attendance_Policy_76()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Calculate overtime hours using the 'Total Hours minus Shift Duration' calculation method by taking break"
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Handle null OTHours â†’ default to 00:00
			            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                actualStatus, expectedStatus, actualOTHours, totalOTHour
			            );

			            // Validate both Final Status & OT Hours
			            if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			        }

				}
				
				//MPI_885_Attendance_Policy_77
				@Test(enabled = true, priority = 54, groups = { "Smoke" })
				public void MPI_885_Attendance_Policy_77()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Calculate overtime hours using the 'Shift End Time to Out Punch' calculation method by taking the break"
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,effectivehours");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String effectivehours = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Handle null OTHours â†’ default to 00:00
			            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			         // Handle null EffectiveHours â†’ default to 00:00
			        	String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			        	actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00" : actualEffectiveHours;
			            
			            
			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s  punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, thirdinouttime, fourthinouttime, 
			                actualStatus, expectedStatus, actualEffectiveHours, effectivehours, actualOTHours, totalOTHour
			            );

			            // Validate both Final Status & OT Hours
			            if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                totalOTHour.equalsIgnoreCase(actualOTHours) &&
			            effectivehours.equalsIgnoreCase(actualEffectiveHours)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			        }

				}	
				
				//MPI_886_Attendance_Policy_78
				@Test(enabled = true, priority = 55, groups = { "Smoke" })
				public void MPI_886_Attendance_Policy_78()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Calculate overtime hours using the 'In Punch to Shift Start Time' calculation method by taking the break"
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,effectivehours");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String effectivehours = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Handle null OTHours â†’ default to 00:00
			            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			         // Handle null EffectiveHours â†’ default to 00:00
			        	String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			        	actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00" : actualEffectiveHours;
			            
			            
			            // Make sentence using excel inputs
			        	  String finalSentence = String.format(
					                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s  punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					                description, employeeuniqueid, date, inouttime1, secondInOutTime2, thirdinouttime, fourthinouttime, 
					                actualStatus, expectedStatus, actualEffectiveHours, effectivehours, actualOTHours, totalOTHour
					            );

			            // Validate both Final Status & OT Hours
			            if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                totalOTHour.equalsIgnoreCase(actualOTHours) &&
			            effectivehours.equalsIgnoreCase(actualEffectiveHours)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			        }

				}	
				
				//MPI_887_Attendance_Policy_79
				@Test(enabled = true, priority = 56, groups = { "Smoke" })
				public void MPI_887_Attendance_Policy_79()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Calculate overtime hours using the '(In Punch to Shift Start Time) + (Shift End Time to Out Punch)' calculation method by taking the break"
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,effectivehours");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String effectivehours = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Handle null OTHours â†’ default to 00:00
			            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			         // Handle null EffectiveHours â†’ default to 00:00
			        	String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			        	actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00" : actualEffectiveHours;
			            
			            
			            // Make sentence using excel inputs
			        	  String finalSentence = String.format(
					                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s  punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					                description, employeeuniqueid, date, inouttime1, secondInOutTime2, thirdinouttime, fourthinouttime, 
					                actualStatus, expectedStatus, actualEffectiveHours, effectivehours, actualOTHours, totalOTHour
					            );

			            // Validate both Final Status & OT Hours
			            if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                totalOTHour.equalsIgnoreCase(actualOTHours) &&
			            effectivehours.equalsIgnoreCase(actualEffectiveHours)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			        }

				}
				
				//MPI_888_Attendance_Policy_80
				@Test(enabled = true, priority = 57, groups = { "Smoke" })
				public void MPI_888_Attendance_Policy_80()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Check the status of the employee when \"Ignore Punch Minutes\" is set to 1 minute, and the employee performs punch in and punch out within 1 minute and punch out after half an of shift end and Calculate overtime hours using the '(Total Effective Hours) - (Shift Duration)' calculation method."
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,effectivehours");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String effectivehours = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				    
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Handle null OTHours â†’ default to 00:00
			            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			         // Handle null EffectiveHours â†’ default to 00:00
			        	String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			        	actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00" : actualEffectiveHours;
			            
			            
			            // Make sentence using excel inputs
			        	  String finalSentence = String.format(
					                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s   and Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					                description, employeeuniqueid, date, inouttime1, secondInOutTime2, fourthinouttime, 
					                actualStatus, expectedStatus, actualEffectiveHours, effectivehours, actualOTHours, totalOTHour
					            );

			            // Validate both Final Status & OT Hours
			            if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                totalOTHour.equalsIgnoreCase(actualOTHours) &&
			            effectivehours.equalsIgnoreCase(actualEffectiveHours)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			        }

				}
				
				// MPI_742_Attendance_Policy_69
				@Test(enabled = true, priority = 58, groups = { "Smoke" })
				public void MPI_742_Attendance_Policy_69()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check that, for theÂ Shift End Time to Out Punch attendance policy configuration do early punch in and check the OT status.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
			
				// MPI_743_Attendance_Policy_70
				@Test(enabled = true, priority = 59, groups = { "Smoke" })
				public void MPI_743_Attendance_Policy_70()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check that, for the In Punch to Shift Start Time attendance policy configuration do late punch out and check the OT status ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_744_MeghPi_Shift_29
				@Test(enabled = true, priority = 60, groups = { "Smoke" })
				public void MPI_744_MeghPi_Shift_29()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Configure 15 minutes in the 'Before' buffer time and 30 minutes in the 'After' buffer time. Check the status for the employee by punching in 10 minutes after shift start time.");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_746_Shift_policy_20
				@Test(enabled = true, priority = 61, groups = { "Smoke" })
				public void MPI_746_Shift_policy_20()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the shift buffer time is configured as 'All' for 1 hour and the employee performs an early punch-in. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				// MPI_747_Shift_policy_21
				@Test(enabled = true, priority = 62, groups = { "Smoke" })
				public void MPI_747_Shift_policy_21()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("Policy " + 
							"Check the status when the shift buffer time is configured as 'All' for 1 hour and the employee performs an late punch-in. ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        
			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
	
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
		   
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			     
			            if (validation.statusCode() == 200) {
			                String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			                // Handle null OTHours â†’ default to 00:00
			                String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			                actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			                // Make sentence using excel inputs
			                String finalSentence = String.format(
			                    "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                    description, employeeuniqueid, date, inouttime1, secondInOutTime2,
			                    actualStatus, expectedStatus, actualOTHours, totalOTHour
			                );

			                // Validate both Final Status & OT Hours
			                if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                    totalOTHour.equalsIgnoreCase(actualOTHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			            }

				}
				
				//MPI_889_Attendance_Policy_81
				@Test(enabled = true, priority = 63, groups = { "Smoke" })
				public void MPI_889_Attendance_Policy_81()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Verify the attendance status for an employee who completes both the first and second shifts when the â€˜Multiple Shift Detectionâ€™ option is set to â€˜Yesâ€™ in the attendance policy."
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            // First shift values
			            String firstStatus = validation.jsonPath().getString("[0].AttnFinalStatus");
			            String firstIn = validation.jsonPath().getString("[0].InTime");
			            String firstOut = validation.jsonPath().getString("[0].OutTime");
			            String firstOT = validation.jsonPath().getString("[0].OTHours");
			            firstOT = (firstOT == null || firstOT.isEmpty()) ? "00:00" : firstOT;

			            // Second shift values
			            String secondStatus = validation.jsonPath().getString("[1].AttnFinalStatus");
			            String secondIn = validation.jsonPath().getString("[1].InTime");
			            String secondOut = validation.jsonPath().getString("[1].OutTime");
			            String secondOT = validation.jsonPath().getString("[1].OTHours");
			            secondOT = (secondOT == null || secondOT.isEmpty()) ? "00:00" : secondOT;

			            // Combine final sentence
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, worked in 1st Shift (IN.%s, OUT.%s, Status.%s, OT.%s) " +
			                "and in 2nd Shift (IN.%s, OUT.%s, Status.%s, OT.%s). Expected Status = %s, Expected OT = %s",
			                description, employeeuniqueid, date,
			                firstIn, firstOut, firstStatus, firstOT,
			                secondIn, secondOut, secondStatus, secondOT,
			                expectedStatus, totalOTHour
			            );

			            // Validate both shifts (you can adjust conditions if OT differs per shift)
			            if (expectedStatus.equalsIgnoreCase(firstStatus) &&
			                expectedStatus.equalsIgnoreCase(secondStatus) &&
			                totalOTHour.equalsIgnoreCase(firstOT) &&
			                totalOTHour.equalsIgnoreCase(secondOT)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			     
			        }
				    }
				
				//MPI_890_Attendance_Policy_82
				@Test(enabled = true, priority = 64, groups = { "Smoke" })
				public void MPI_890_Attendance_Policy_82()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Verify the attendance status for an employee who completes both the first and second shifts when the â€˜Multiple Shift Detectionâ€™ option is set to â€˜Noâ€™ in the attendance policy."
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,multipleshiftstatus");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String multipleshiftstatus = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {

			            int rowCount = validation.jsonPath().getList("$").size();

			            if (rowCount == 1) {
			                // Single combined record when Multiple Shift Detection = NO
			                String status = validation.jsonPath().getString("[0].AttnFinalStatus");
			                String inTime = validation.jsonPath().getString("[0].InTime");
			                String outTime = validation.jsonPath().getString("[0].OutTime");
			                String otHours = validation.jsonPath().getString("[0].OTHours");
			                otHours = (otHours == null || otHours.isEmpty()) ? "00:00" : otHours;

			                // Build sentence
			                String finalSentence = String.format(
			                    "%s â€“ Employee %s on %s (Multiple Shift Detection = NO) combined shift " +
			                    "(IN.%s, OUT.%s, Final Status.%s, OT.%s). " +
			                    "Expected First Shift Status = %s, Expected Second Shift Status = %s, Expected OT = %s",
			                    description, employeeuniqueid, date,
			                    inTime, outTime, status, otHours,
			                    expectedStatus, multipleshiftstatus, totalOTHour
			                );

			                // Validate:
			                // 1. API returns combined AttnFinalStatus = expectedStatus (first shift)
			                // 2. Because second shift should be absent, multipleshiftstatus must be "A"
			                // 3. OT hours match expected
			                if (expectedStatus.equalsIgnoreCase(status)
			                        && "A".equalsIgnoreCase(multipleshiftstatus)
			                        && totalOTHour.equalsIgnoreCase(otHours)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			                }

			            } else if (rowCount == 0) {
			                logResults.createLogs("N", "FAIL",
			                        "âŒ No attendance data returned for " + employeeuniqueid + " on " + date);
			            } else {
			                logResults.createLogs("N", "FAIL",
			                        "âŒ Unexpected multiple rows returned even though Multiple Shift Detection = NO");
			            }

			        } else {
			            logResults.createLogs("N", "FAIL",
			                    "âŒ Failed to fetch attendance. API Response." + validation.asString());
			        }
				
				   

				    }
				
				//MPI_891_Attendance_Policy_83
				@Test(enabled = true, priority = 65, groups = { "Smoke" })
				public void MPI_891_Attendance_Policy_83()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "Verify that when an employee completes the first shift and a half day of the second shift on the same day, the attendance status for the first shift is â€˜Pâ€™ and for the second shift is â€˜HDâ€™."
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,multipleshiftstatus");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String multipleshiftstatus = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );
			        if (validation.statusCode() == 200) {
			            int rowCount = validation.jsonPath().getList("$").size();

			            if (rowCount > 1) {
			                // First shift row
			                String firstStatus  = validation.jsonPath().getString("[0].AttnFinalStatus");
			                String firstIn      = validation.jsonPath().getString("[0].InTime");
			                String firstOut     = validation.jsonPath().getString("[0].OutTime");
			                String firstOT      = validation.jsonPath().getString("[0].OTHours");
			                if (firstOT == null || firstOT.isEmpty() || firstOT.equals("0")) firstOT = "00:00";

			                // Second shift row
			                String secondStatus = validation.jsonPath().getString("[1].AttnFinalStatus");
			                String secondIn     = validation.jsonPath().getString("[1].InTime");
			                String secondOut    = validation.jsonPath().getString("[1].OutTime");
			                String secondOT     = validation.jsonPath().getString("[1].OTHours");
			                if (secondOT == null || secondOT.isEmpty() || secondOT.equals("0")) secondOT = "00:00";

			                String msg = String.format(
			                    "%s â€“ Employee %s on %s (Multiple Shift Detection = YES, multiple rows) " +
			                    "1st Shift (IN.%s, OUT.%s, Status.%s, OT.%s) | " +
			                    "2nd Shift (IN.%s, OUT.%s, Status.%s, OT.%s)",
			                    description, employeeuniqueid, date,
			                    firstIn, firstOut, firstStatus, firstOT,
			                    secondIn, secondOut, secondStatus, secondOT
			                );

			                // only check statuses
			                if (expectedStatus.equalsIgnoreCase(firstStatus)
			                        && multipleshiftstatus.equalsIgnoreCase(secondStatus)) {
			                    logResults.createLogs("N", "PASS", "âœ… " + msg);
			                } else {
			                    logResults.createLogs("N", "FAIL", "âŒ " + msg);
			                }

			            } else {
			                logResults.createLogs("N", "FAIL",
			                        "âŒ No attendance data returned for " + employeeuniqueid + " on " + date);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL",
			                "âŒ Failed to fetch attendance. API Response." + validation.asString());
			        }
			
				    }
			
				//MPI_1190_Normal_Attendance_73
				@Test(enabled = true, priority = 66, groups = { "Smoke" })
				public void MPI_1190_Normal_Attendance_73()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName("Policy " + 
				        "To verify this, ensure that the ‘P’ status is displayed when the employee is present for consecutive days."
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,effectivehours");

				    int i = 0;
				    String cmpcode = data.get(i++);
				    String emailid = data.get(i++);
				    String password = data.get(i++);
				    
				    String baseuri = data.get(i++);
				    String loginendpoint = data.get(i++);
				    String endpointoftransaction = data.get(i++);
				    String cardnumber = data.get(i++);
				    int cardtype = Integer.parseInt(data.get(i++));
				    String deviceuniqueid = data.get(i++);
				    String bio1finger = data.get(i++);
				    String bio2finger  = data.get(i++);
				    String employeeuniqueid = data.get(i++);
				    String locationid = data.get(i++);
				    String inouttime1 = data.get(i++);     // Punch In time
				    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
				    String photo = data.get(i++);
				    String secondInOutTime2 = data.get(i++); // Punch Out time
				    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

				    String thirdinouttime3 = data.get(i++); // Punch Out time
				    String thirdmode = data.get(i++);    
				    
				    String date =  data.get(i++);
				    String fourthinouttime4 = data.get(i++);
				    String fourthmode = data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String totalOTHour = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);
			        String effectivehours = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				   
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
					String fourthinouttime = date + " " + fourthinouttime4;
				    
				    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			            return;
			        }
				    
				 // Punch OUT
				    Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        }
				 // Trigger attendance update first
				    Response updateResp = apiPage.executeUpdateAttendance(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
				            employeeuniqueid, date + "T00:00:00.000Z"
				    );

				    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
				    } else {
				        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
				    }
				 // Get User Status
			        Response validation = apiPage.executeGetUserStatus(
			                baseuri, loginendpoint,
			                emailid, password, cmpcode,
			                baseuri, statusEndpoint,
			                employeeuniqueid, fromDateofstatus, toDateofstatus
			        );

			        if (validation.statusCode() == 200) {
			            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			            // Handle null OTHours â†’ default to 00:00
			            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			         // Handle null EffectiveHours â†’ default to 00:00
			        	String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			        	actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00" : actualEffectiveHours;
			            
			            
			            // Make sentence using excel inputs
			            String finalSentence = String.format(
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s  punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s), OT Hours = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, thirdinouttime, fourthinouttime, 
			                actualStatus, expectedStatus, actualEffectiveHours, effectivehours, actualOTHours, totalOTHour
			            );

			            // Validate both Final Status & OT Hours
			            if (expectedStatus.equalsIgnoreCase(actualStatus) &&
			                totalOTHour.equalsIgnoreCase(actualOTHours) &&
			            effectivehours.equalsIgnoreCase(actualEffectiveHours)) {
			                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
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
