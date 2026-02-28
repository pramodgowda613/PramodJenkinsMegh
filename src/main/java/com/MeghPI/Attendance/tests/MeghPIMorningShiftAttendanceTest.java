package com.MeghPI.Attendance.tests;

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

public class MeghPIMorningShiftAttendanceTest {

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
			public void MPI_879_Normal_Attendance_71()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName( "Morning" +
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
					        logResults.createLogs("N","PASS"," ClearData successful for date."+date);
					    } else {
					        logResults.createLogs("N","FAIL"," ClearData failed. Response."+raw);
					        Assert.fail("ClearData failed. Response."+raw);
					    }
					} else {
					    logResults.createLogs("N","FAIL"," ClearData API failed with status."+clearResponse.getStatusCode());
					    Assert.fail("ClearData API failed with status."+clearResponse.getStatusCode());
					}
			}
				   
	

	// MPI_630_Normal_Attendance_02
		@Test(enabled = true, priority = 1, invocationCount = 2, groups = { "Smoke" })
		public void MPI_630_Normal_Attendance_02()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
		
			logResults.setScenarioName("Morning " +
					"Check the status when the employee performs only a single punch in a day.");
			
		
			String cmpcode;
			String emailid;
			String password;
			String baseuri;
			String loginendpoint;
			String endpointoftransaction;
			String cardnumber;
			int cardtype;
			String deviceuniqueid;
			String bio1finger;
			String bio2finger;
			String employeeuniqueid;
			String locationid;
			String inouttime1;
			String mode;
			String photo;
			String date;
			String statusEndpoint;
			String expectedStatus;
			String description;
			String updateattendanceendpoint;
			String fromDateofstatus;
			String toDateofstatus;
			String inouttime;
			ArrayList<String> data =null;
			try {
				data = initBase.loadExcelData("MorningShift_Attendance", currTC,
						"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,locationid,inouttime,mode,photo,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
				
int i = 0;

cmpcode = data.get(i);
emailid = data.get(++i);
				password = data.get(++i);
				
				baseuri = data.get(++i);
				loginendpoint = data.get(++i);
				endpointoftransaction = data.get(++i);
				cardnumber = data.get(++i);
				cardtype = Integer.parseInt(data.get(++i));
				deviceuniqueid = data.get(++i);
				bio1finger = data.get(++i);
				bio2finger = data.get(++i);
				employeeuniqueid = data.get(++i);
				locationid = data.get(++i);
						inouttime1 = data.get(++i);
						mode = data.get(++i);
						photo = data.get(++i);
						date = data.get(++i);
						 statusEndpoint = data.get(++i);
					        String fromDateofuserstatus1          = data.get(++i);
					        String toDateofuserstatus2            = data.get(++i);
					        expectedStatus = data.get(++i);
					        description = data.get(++i);
					        updateattendanceendpoint = data.get(++i);
					      
					        fromDateofstatus = date + fromDateofuserstatus1;
					        toDateofstatus = date + toDateofuserstatus2;
						
						inouttime = date + " " + inouttime1;
				

		
			 // Create API Page object
	MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

	// Call combined method
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
	    	    "%s â€“ Employee %s on %s punched IN at %s and did not perform any further punch OUT. Final Status = %s (Expected = %s)",
	    	    description, employeeuniqueid, date, inouttime1, actualStatus, expectedStatus
	    	);


	    if (expectedStatus.equalsIgnoreCase(actualStatus)) {
	        logResults.createLogs("N", "PASS", " " + finalSentence);
	    } else {
	        logResults.createLogs("N", "FAIL", " " + finalSentence);
	    }
	} else {
	    logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
	}
	
			
		}

	//MPI_632_Normal_Attendance_04
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_632_Normal_Attendance_04()  {
	    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
	    logResults.createExtentReport(currTC);
	    logResults.setScenarioName( "Morning" +
	            "Check status when employee has not completed minimum hours required for half day.");

	    
	   
	    // Load all data including punch-out time and mode
	    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
                "%s â€“ This Employee %s on %s, punched IN at %s and OUT at %s. Final Status = %s (Expected = %s)",
                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
            );

            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
                logResults.createLogs("N", "PASS", " " + finalSentence);
            } else {
                logResults.createLogs("N", "FAIL", " " + finalSentence);
            }
        } else {
            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
        }

	}
	
	// MPI_637_Normal_Attendance_09
		@Test(enabled = true, priority = 3, groups = { "Smoke" })
		public void MPI_637_Normal_Attendance_09()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName( "Morning" +
					"Check the status after the employee raises a regularization request for an absent day and it is approved. ");

			 
			 // Load all data including punch-out time and mode
		    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
	                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
	                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
	            );

	            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
	                logResults.createLogs("N", "PASS", " " + finalSentence);
	            } else {
	                logResults.createLogs("N", "FAIL", " " + finalSentence);
	            }
	        } else {
	            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
	        }

		}
			
			
	
		
		//MPI_638_Normal_Attendance_10
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_638_Normal_Attendance_10()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when the employee has applied for leave and it has been approved for that day."
				    );

				    
				    ArrayList<String> data = initBase.loadExcelData(
				        "MorningShift_Attendance",
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
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
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
			            	    "%s â€“ Employee %s applied leave on %s. Final Status = %s (Expected = %s)",
			            	    description, employeeuniqueid, date, actualStatus, expectedStatus
			            	);


			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}

		//MPI_641_Normal_Attendance_13
		@Test(enabled = true, priority = 5, groups = { "Smoke" })
		public void MPI_641_Normal_Attendance_13()  {
		    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		    logResults.createExtentReport(currTC);
		    logResults.setScenarioName( "Morning" +
		        "Check the status when the employee is present on a week off "
		    );

		    
		 // Load all data including punch-out time and mode
		    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
	                "%s â€“ This Employee %s on %s,  punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
	                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
	            );

	            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
	                logResults.createLogs("N", "PASS", " " + finalSentence);
	            } else {
	                logResults.createLogs("N", "FAIL", " " + finalSentence);
	            }
	        } else {
	            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
	        }

		}
		
		//MPI_644_Normal_Attendance_16
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_644_Normal_Attendance_16()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when the employee completes a half day.  "
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}
		
				//MPI_646_Normal_Attendance_18
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_646_Normal_Attendance_18()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when the employee has not completed the minimum hours required for half day on a week off "
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}
		
				//MPI_647_Normal_Attendance_19
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_647_Normal_Attendance_19()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when the employee completes a half day on a week off. "
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}
		
				
		
				// MPI_655_Normal_Attendance_27
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_655_Normal_Attendance_27()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName( "Morning" +
							"Check the status when the employee did a single punch in on a week off");

					 
					ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
							"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,locationid,inouttime,mode,photo,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				int i = 0;
				String cmpcode = data.get(i);
				String emailid = data.get(++i);
					String password = data.get(++i);
					
					String baseuri =  data.get(++i);
					String loginendpoint = data.get(++i);
					String endpointoftransaction = data.get(++i);
					String cardnumber = data.get(++i);
					int cardtype = Integer.parseInt(data.get(++i));
					String deviceuniqueid = data.get(++i);
					String bio1finger = data.get(++i);
					String bio2finger  = data.get(++i);
					String employeeuniqueid = data.get(++i);
					String locationid = data.get(++i);
							String inouttime1 = data.get(++i);
							String mode = data.get(++i);
							String photo = data.get(++i);
							
							 String date =  data.get(++i);
							 String statusEndpoint    = data.get(++i);
						        String fromDateofuserstatus1          = data.get(++i);
						        String toDateofuserstatus2            = data.get(++i);
						        String expectedStatus    = data.get(++i);
						        String description = data.get(++i);
						        String updateattendanceendpoint = data.get(++i);

						        String fromDateofstatus = date + fromDateofuserstatus1;
						        String toDateofstatus = date + toDateofuserstatus2;
							 
								String inouttime = date + " " + inouttime1;
						
				
					


			// Create API Page object
			MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

			// Call combined method
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
	                "%s â€“ This Employee %s on %s, punched IN at %s and No Further Punch So Final Status = %s (Expected = %s)",
	                description, employeeuniqueid, date, inouttime1, actualStatus, expectedStatus
	            );

	            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
	                logResults.createLogs("N", "PASS", " " + finalSentence);
	            } else {
	                logResults.createLogs("N", "FAIL", " " + finalSentence);
	            }
	        } else {
	            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
	        }

				}
		
	
				
				//MPI_440_Normal_Attendance_48
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_440_Normal_Attendance_48()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when 'First Clock In & Last Clock Out' is applied in the policy and the employee did last punch in at the end of shift "
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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

				    // Punch In
				    Response punchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, secondInOutTime, secondMode, photo
				    );

				    if (punchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch In executed successfully at " + secondInOutTime2);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + punchOutResponse.asString());
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Last Punch In at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}
		
				//MPI_657_Normal_Attendance_47
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_657_Normal_Attendance_47()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when 'First Clock In & Last Clock Out' is applied in the policy and the employee did last punch out before end of shift"
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}
	
	
				//MPI_659_Normal_Attendance_46
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_659_Normal_Attendance_46()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when '17 hr' is set in the Maximum Duration from shift start time in the policy and the employee did last punch out before 17 hours from shift start time of that day"
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and OUT On  %s at  %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, date, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}			
				
		
				//MPI_442_Normal_Attendance_45
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_442_Normal_Attendance_45()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when '17 hr' is set in the Maximum Duration from shift start time in the policy and the employee only completes half day and punches out after 17 hours"
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					
					String thirdinouttime =  date + " " + thirdinouttime3;
				    
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
				    
				    // Punch OUT 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + SecondTimepunchOutResponse.asString());
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
				                "%s â€“ This Employee %s on %s, punched IN at %s and OUT On  %s at  %s and did Punch Out After 17hr On %s at %s . Final Status = %s (Expected = %s)",
				                description, employeeuniqueid, date, inouttime1, date, secondInOutTime2,date, thirdinouttime, actualStatus, expectedStatus
				            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}	
				
				
				//MPI_683_Normal_Attendance_29
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_683_Normal_Attendance_29()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				            "Check that when the user is present for more than the specified shift duration, but overtime is not configured and 'Multiple Shift Detection' is set to 'yes'.");

				    
				    // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}		
				
				//MPI_684_Normal_Attendance_30
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
				public void MPI_684_Normal_Attendance_30()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				            "Check the status when \"Ignore Punch Minutes\" is set to 0 and the employee punches in and out within 1 minute without any further punches on the same day.");

				    
				    // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}	
				
	
				//MPI_656_Normal_Attendance_44
				@Test(enabled = true, priority = 17, groups = { "Smoke" })
				public void MPI_656_Normal_Attendance_44()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				            "Check the status when Shift Type is set to \"Nearest\" in the policy. As an employee, punch in at 7:00 AM and punch out at 3:00 PM while assigned to a General Shift ");

				    
				    // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}	
				
				//MPI_668_Normal_Attendance_43
				@Test(enabled = true, priority = 18, groups = { "Smoke" })
				public void MPI_668_Normal_Attendance_43()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				            "Check the attendance status whenÂ Shift TypeÂ is set toÂ \"Nearest\"Â and the employeeÂ punches in 2hr before of shift start time and punch out at shift end ");

				    
				    // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}			
				
				
				//MPI_685_Normal_Attendance_31
				@Test(enabled = true, priority = 19, groups = { "Smoke" })
				public void MPI_685_Normal_Attendance_31()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when the employee performs multiple punches in a day and completes the shift as per the defined shift policy."
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
					String thirdinouttime = date + " " + thirdinouttime3;
				    
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
				    
				    // Punch OUT 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, employeeuniqueid,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch OUT executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + SecondTimepunchOutResponse.asString());
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s and Last Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, thirdinouttime, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}			
				
				//MPI_686_Normal_Attendance_42
				@Test(enabled = true, priority = 20, groups = { "Smoke" })
				public void MPI_686_Normal_Attendance_42()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when Casual Leave is applied for a day marked as Absent."
				    );

				    
				    ArrayList<String> data = initBase.loadExcelData(
				        "MorningShift_Attendance",
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
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
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
			                "%s â€“ This Employee %s Applied Full Day Leave On %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}	
				
				//MPI_666_Normal_Attendance_41
				@Test(enabled = true, priority = 21, groups = { "Smoke" })
				public void MPI_666_Normal_Attendance_41()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when user is Absent but is on Paid Leave"
				    );

				    
				    ArrayList<String> data = initBase.loadExcelData(
				        "MorningShift_Attendance",
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
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
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
			                "%s â€“ This Employee %s Is on Leave On This Date %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }
   
				}		
				
				// MPI_678_Normal_Attendance_28
				@Test(enabled = true, priority = 22, groups = { "Smoke" })
				public void MPI_678_Normal_Attendance_28()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName( "Morning" +
							"Check the status when Out punch is there but no IN punch but employee is present on that day");

					 
					ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
							"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,locationid,inouttime,mode,photo,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

				int i = 0;
				String cmpcode = data.get(i);
				String emailid = data.get(++i);
					String password = data.get(++i);
					
					String baseuri =  data.get(++i);
					String loginendpoint = data.get(++i);
					String endpointoftransaction = data.get(++i);
					String cardnumber = data.get(++i);
					int cardtype = Integer.parseInt(data.get(++i));
					String deviceuniqueid = data.get(++i);
					String bio1finger = data.get(++i);
					String bio2finger  = data.get(++i);
					String employeeuniqueid = data.get(++i);
					String locationid = data.get(++i);
							String inouttime1 = data.get(++i);
							String mode = data.get(++i);
							String photo = data.get(++i);
				

							 String date =  data.get(++i);
							 String statusEndpoint    = data.get(++i);
						        String fromDateofuserstatus1          = data.get(++i);
						        String toDateofuserstatus2            = data.get(++i);
						        String expectedStatus    = data.get(++i);
						        String description = data.get(++i);
						        String updateattendanceendpoint = data.get(++i);

						        String fromDateofstatus = date + fromDateofuserstatus1;
						        String toDateofstatus = date + toDateofuserstatus2; 
							 
										String inouttime = date + " " + inouttime1;
										
							
							
					 // Create API Page object
			MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

			// Call combined method
			Response punchInResponse = apiPage.executeSuccessTransaction(
			        baseuri, loginendpoint,
			        emailid, password, cmpcode,
			        baseuri, endpointoftransaction,
			        cardnumber, cardtype, deviceuniqueid,
			        bio1finger, bio2finger, employeeuniqueid,
			        locationid, inouttime, mode, photo
			);

			if (punchInResponse.getStatusCode() == 200) {
			    logResults.createLogs("N", "PASS", "Punch Out executed successfully at " + inouttime1);
			} else {
			    logResults.createLogs("N", "FAIL", "Punch Out failed." + punchInResponse.asString());
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
	                "%s â€“ This Employee %s on %s, punched Out at %s. Final Status = %s (Expected = %s)",
	                description, employeeuniqueid, date, inouttime1, actualStatus, expectedStatus
	            );

	            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
	                logResults.createLogs("N", "PASS", " " + finalSentence);
	            } else {
	                logResults.createLogs("N", "FAIL", " " + finalSentence);
	            }
	        } else {
	            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
	        }
			
				}				
				
				// MPI_697_Normal_Attendance_52
				@Test(enabled = true, priority = 23, groups = { "Smoke" })
				public void MPI_697_Normal_Attendance_52()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName( "Morning" +
							"Check the status When user arrives Late");

					 
					// Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}


					
					
							
		

				// MPI_698_Normal_Attendance_53
				@Test(enabled = true, priority = 24, groups = { "Smoke" })
				public void MPI_698_Normal_Attendance_53()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName( "Morning" +
							"Check the status when the employee arrives earlyÂ ");

					 
					// Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}
				
				//MPI_724_Normal_Attendance_54
				@Test(enabled = true, priority = 25, groups = { "Smoke" })
				public void MPI_724_Normal_Attendance_54()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				            "Check the status when 'First Clock In & Last Clock Out' is applied in the policy and the employee performs the last punch-in 1 minute before the shift end time.");

				    
				    // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}	
				
				// MPI_727_Normal_Attendance_55
				@Test(enabled = true, priority = 26, groups = { "Smoke" })
				public void MPI_727_Normal_Attendance_55()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName( "Morning" +
							"Check the status when the employee applies a Half Day Regularization Request ");

					 
					// Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}
					
					
					
					
					
					
					
					
					
					
					
				
				//MPI_671_Normal_Attendance_38
				@Test(enabled = true, priority = 27, groups = { "Smoke" })
				public void MPI_671_Normal_Attendance_38()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when the employee applies for first-half leave without working during the second half of the day"
				    );

				    
				    ArrayList<String> data = initBase.loadExcelData(
				        "MorningShift_Attendance",
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
			        String employeeuniqueid =  data.get(++i);
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
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
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
			                "%s â€“ This Employee %s on %s, Applied First half Leave. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date,  actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

				}			
				
				
				//MPI_672_Normal_Attendance_39
				@Test(enabled = true, priority = 28, groups = { "Smoke" })
				public void MPI_672_Normal_Attendance_39()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when the employee completes half day and apply half day leave  "
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");
				    

				    
				    
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
				    
				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
					String fromdate = date  + fromdate1;
					String todate = date  + todate2;
					
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
					        logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
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
				                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s And Applied 2nd Half Leave. Final Status = %s (Expected = %s)",
				                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
				            );

				            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				                logResults.createLogs("N", "PASS", " " + finalSentence);
				            } else {
				                logResults.createLogs("N", "FAIL", " " + finalSentence);
				            }
				        } else {
				            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
				        }

				}	
				
				
			
				//MPI_673_Normal_Attendance_40
				@Test(enabled = true, priority = 29, groups = { "Smoke" })
				public void MPI_673_Normal_Attendance_40()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when the employee applies for the first half as a leave and completes the second half (half-day) "
				    );

				    
				    ArrayList<String> data = initBase.loadExcelData(
				        "MorningShift_Attendance",
				        currTC,
				        "cmpcode,emailid,password,baseuri,loginendpoint,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,endpointoftransaction,cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint"
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
				    
				    
				    
				    String endpointoftransaction = data.get(++i);
				    
				    System.out.println(endpointoftransaction);
				    
				    String cardnumber = data.get(++i);
				    int cardtype = Integer.parseInt(data.get(++i));
				    String deviceuniqueid = data.get(++i);
				    String bio1finger = data.get(++i);
				    String bio2finger  = data.get(++i);
				    String employeeuniqueid = data.get(++i);
				    String locationid = data.get(++i);
				    String inouttime1 = data.get(++i);     // Punch In time
				    String mode = data.get(++i);          // Punch In mode (e.g., "IN")
				    String photo = data.get(++i);
				    String secondInOutTime2 = data.get(++i); // Punch Out time
				    String secondMode = data.get(++i);  
				   
				    String date =  data.get(++i);
				    String statusEndpoint    = data.get(++i);
			        String fromDateofuserstatus1          = data.get(++i);
			        String toDateofuserstatus2            = data.get(++i);
			        String expectedStatus    = data.get(++i);
			        String description = data.get(++i);
			        String updateattendanceendpoint = data.get(++i);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
					String fromdate = date  + fromdate1;
					String todate = date  + todate2;
					
					String inouttime = date + " " + inouttime1;
					String secondInOutTime = date + " " + secondInOutTime2;
				    
				    
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
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
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
			                "%s â€“ This Employee %s on %s, Applied First Half Leave And punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }
  
				}
				
				
				// MPI_728_Normal_Attendance_56
				@Test(enabled = true, priority = 30, groups = { "Smoke" })
				public void MPI_728_Normal_Attendance_56()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName( "Morning" +
							"Check the status for emp by applying half day regulization request and half day leave");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
				   
				    // Debugging - Print extracted values
			
				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
					String fromdate = date  + fromdate1;
					String todate = date  + todate2;
					
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
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and OUT at %s Applied 2nd Half Leave. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }
	
				}	
				
				
				//MPI_729_Normal_Attendance_57
				@Test(enabled = true, priority = 31, groups = { "Smoke" })
				public void MPI_729_Normal_Attendance_57()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when the employee applies for second-half leave without working during the first half of the day "
				    );

				    
				    ArrayList<String> data = initBase.loadExcelData(
				        "MorningShift_Attendance",
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
			        String updateattendanceendpoint =  data.get(++i);

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
				        logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
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
			                "%s â€“ This Employee %s on %s, Applied 2nd Half Leave. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }
   
				}
				
		
				//MPI_730_Normal_Attendance_58
				@Test(enabled = true, priority = 32, groups = { "Smoke" })
				public void MPI_730_Normal_Attendance_58()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when the employee applies for a half-day leave but performs only a single punch-in  "
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");
				    

				    
				    
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
				    
				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
					String fromdate = date  + fromdate1;
					String todate = date  + todate2;
					
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
					        logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
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
				                "%s â€“ This Employee %s on %s, punched IN at %s and Applied 2nd Half Leave. Final Status = %s (Expected = %s)",
				                description, employeeuniqueid, date, inouttime1, actualStatus, expectedStatus
				            );

				            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				                logResults.createLogs("N", "PASS", " " + finalSentence);
				            } else {
				                logResults.createLogs("N", "FAIL", " " + finalSentence);
				            }
				        } else {
				            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
				        }

					    
				}
		
				
				//MPI_731_Normal_Attendance_59
				@Test(enabled = true, priority = 33, groups = { "Smoke" })
				public void MPI_731_Normal_Attendance_59()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "Check the status when the employee works fewer hours and applies for a half-day leave.  "
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
				            "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				            "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				            "locationid,inouttime,mode,photo,secondinouttime,secondmode,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");
				    

				    
				    
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
				    
				    String date =  data.get(i++);
				    String statusEndpoint    = data.get(i++);
			        String fromDateofuserstatus1          = data.get(i++);
			        String toDateofuserstatus2            = data.get(i++);
			        String expectedStatus    = data.get(i++);
			        String description = data.get(i++);
			        String updateattendanceendpoint = data.get(i++);

			        String fromDateofstatus = date + fromDateofuserstatus1;
			        String toDateofstatus = date + toDateofuserstatus2;
				    
					String fromdate = date  + fromdate1;
					String todate = date  + todate2;
					
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
					        logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
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
				                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s And Applied 2nd Half Leave. Final Status = %s (Expected = %s)",
				                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
				            );

				            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				                logResults.createLogs("N", "PASS", " " + finalSentence);
				            } else {
				                logResults.createLogs("N", "FAIL", " " + finalSentence);
				            }
				        } else {
				            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
				        }

				    
				        
				    
					    
				}
				
				
				// MPI_860_Normal_Attendance_69
				@Test(enabled = true, priority = 34, groups = { "Smoke" })
				public void MPI_860_Normal_Attendance_69()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName( "Morning" +
							"To verify this, check the total working hours of an employee who worked half an hour more than the shift duration ");

					 
					 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
			                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
			            );

			            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

			    
			        
			    
				}
				
				//MPI_861_Normal_Attendance_70
				@Test(enabled = true, priority = 35, groups = { "Smoke" })
				public void MPI_861_Normal_Attendance_70()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +
				        "To verify this, check the working hours of an employee who took a 2-hour break and did punch out at the end of the shift. "
				    );

				    
				 // Load all data including punch-out time and mode
				    ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
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
			                logResults.createLogs("N", "PASS", " " + finalSentence);
			            } else {
			                logResults.createLogs("N", "FAIL", " " + finalSentence);
			            }
			        } else {
			            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
			        }

			    
			        
			    
			   
				}	
				
				//MPI_652_Normal_Attendance_24
				@Test(enabled = true, priority = 9, groups = {"Smoke"})
				public void MPI_652_Normal_Attendance_24()  {
				    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				    logResults.createExtentReport(currTC);
				    logResults.setScenarioName( "Morning" +"Check the status when the employee present on day");

				    
				        // Load Excel data
				        ArrayList<String> data = initBase.loadExcelData("MorningShift_Attendance", currTC,
				                "description,cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
				                "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid," +
				                "locationid,inouttime,mode,photo,secondinouttime,secondmode,date," +
				                "getuserstatusendpoint,fromdateofstatus,todateofstatus,status,updateattendanceendpoint");

				        
				        int i = 0;
				        String description       = data.get(i++);
				        String cmpcode           = data.get(i++);
				        String emailid           = data.get(i++);
				        String password          = data.get(i++);
				        String baseuri           = data.get(i++);
				        String loginendpoint     = data.get(i++);
				        String endpointoftransaction = data.get(i++);
				        String cardnumber        = data.get(i++);
				        int cardtype             = Integer.parseInt(data.get(i++));
				        String deviceuniqueid    = data.get(i++);
				        String bio1finger        = data.get(i++);
				        String bio2finger        = data.get(i++);
				        String employeeuniqueid  = data.get(i++);
				        String locationid        = data.get(i++);
				        String inouttime1        = data.get(i++);
				        String mode              = data.get(i++);
				        String photo             = data.get(i++);
				        String secondInOutTime2  = data.get(i++);
				        String secondMode        = data.get(i++);
				        String date              = data.get(i++);
				        String statusEndpoint    = data.get(i++);
				        String fromDateofuserstatus1          = data.get(i++);
				        String toDateofuserstatus2            = data.get(i++);
				        String expectedStatus    = data.get(i++);
				        String updateattendanceendpoint = data.get(i++);

				        String punchInTime  = date + " " + inouttime1;
				        String punchOutTime = date + " " + secondInOutTime2;
				        String fromDateofstatus = date + fromDateofuserstatus1;
				        String toDateofstatus = date + toDateofuserstatus2;

				        MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

				        // Punch IN
				        Response punchInResponse = apiPage.executeSuccessTransaction(
				                baseuri, loginendpoint,
				                emailid, password, cmpcode,
				                baseuri, endpointoftransaction,
				                cardnumber, cardtype, deviceuniqueid,
				                bio1finger, bio2finger, employeeuniqueid,
				                locationid, punchInTime, mode, photo
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
				                locationid, punchOutTime, secondMode, photo
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
				                "%s â€“ This Employee %s on %s, punched IN at %s and OUT at %s. Final Status = %s (Expected = %s)",
				                description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus
				            );

				            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				                logResults.createLogs("N", "PASS", " " + finalSentence);
				            } else {
				                logResults.createLogs("N", "FAIL", " " + finalSentence);
				            }
				        } else {
				            logResults.createLogs("N", "FAIL", " Failed to fetch final status. API Response." + validation.asString());
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
