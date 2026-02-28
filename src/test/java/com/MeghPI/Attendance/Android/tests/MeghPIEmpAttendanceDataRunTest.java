
package com.MeghPI.Attendance.Android.tests;

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

import com.MeghPI.Attendance.pages.MeghAttendancePolicyPage;
import com.MeghPI.Attendance.pages.MeghLeavePolicyPage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghWeekOffPolicyPage;
import com.MeghPI.Attendance.tests.MeghLoginTest;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIEmpAttendanceDataRunTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	private String cmpcode = "";
	private String Emailid = "";

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
	}

	// MPI_879_Normal_Attendance_71
	@Test(enabled = true, priority = 0, invocationCount = 2, groups = { "Smoke" })
	public void MPI_879_Normal_Attendance_71()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General " + "Morning" + "Clear Data for Month-Year");

		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,date,cleardataendpoint");

		String cmpcode = data.get(0);
		String emailid = data.get(1);
		String password = data.get(2);
		String baseuri = data.get(3);
		String loginendpoint = data.get(4);
		String date = data.get(5);
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
		Response clearResponse = apiPage.executeClearData(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				cleardataendpoint, date);
		System.out.println("method called");

		// Validate status code & response
		if (clearResponse.getStatusCode() == 200) {
			String raw = clearResponse.asString().trim();
			if ("true".equalsIgnoreCase(raw)) {
				logResults.createLogs("N", "PASS", "âœ… ClearData successful for date." + date);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ ClearData failed. Response." + raw);
				Assert.fail("ClearData failed. Response." + raw);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ ClearData API failed with status." + clearResponse.getStatusCode());
			Assert.fail("ClearData API failed with status." + clearResponse.getStatusCode());
		}
	}

	// MPI_630_Normal_Attendance_02
	@Test(enabled = true, priority = 1, invocationCount = 2, groups = { "Smoke" })
	public void MPI_630_Normal_Attendance_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);

		logResults.setScenarioName(
				"General " + "Check the status when the employee performs only a single punch in a day.");

		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,locationid,inouttime,mode,photo,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

		int i = 0;
		String cmpcode = data.get(i);
		String emailid = data.get(++i);
		String password = data.get(++i);

		String baseuri = data.get(++i);
		String loginendpoint = data.get(++i);
		String endpointoftransaction = data.get(++i);
		String cardnumber = data.get(++i);
		int cardtype = Integer.parseInt(data.get(++i));
		String deviceuniqueid = data.get(++i);
		String bio1finger = data.get(++i);
		String bio2finger = data.get(++i);
		String employeeuniqueid = data.get(++i);
		String locationid = data.get(++i);
		String inouttime1 = data.get(++i);
		String mode = data.get(++i);
		String photo = data.get(++i);
		String date = data.get(++i);
		String statusEndpoint = data.get(++i);
		String fromDateofuserstatus1 = data.get(++i);
		String toDateofuserstatus2 = data.get(++i);
		String expectedStatus = data.get(++i);
		String description = data.get(++i);
		String updateattendanceendpoint = data.get(++i);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;

		// Create API Page object
		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Call combined method
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ Employee %s on %s punched IN at %s and did not perform any further punch OUT. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_632_Normal_Attendance_04
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_632_Normal_Attendance_04()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"General " + "Check status when employee has not completed minimum hours required for half day.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);

		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}

		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_637_Normal_Attendance_09
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_637_Normal_Attendance_09()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status after the employee raises a regularization request for an absent day and it is approved. ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}

		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_638_Normal_Attendance_10
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_638_Normal_Attendance_10()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when the employee has applied for leave and it has been approved for that day.");

		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,employeeuniqueid,updateattendanceendpoint");
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

		String date = data.get(++i);
		String statusEndpoint = data.get(++i);
		String fromDateofuserstatus1 = data.get(++i);
		String toDateofuserstatus2 = data.get(++i);
		String expectedStatus = data.get(++i);
		String description = data.get(++i);
		String employeeuniqueid = data.get(++i);
		String updateattendanceendpoint = data.get(++i);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String fromdate = date + fromdate1;
		String todate = date + todate2;

		// Debugging - Print extracted values

		// Create API Page object
		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		Response LeaveApplied = apiPage.executeCreateLeaves(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				leaveendpoint, type, entityid, leavetypeid, totaldays, fromdate, todate, fromdurationtype,
				todurationtype, status, remarks, documentname, document);

		// Debugging - Print response details
		System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
		System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

		if (LeaveApplied.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
		} else {
			logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
		}

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}

		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ Employee %s applied leave on %s. Final Status = %s (Expected = %s)", description,
					employeeuniqueid, date, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_641_Normal_Attendance_13
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_641_Normal_Attendance_13()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General " + "Check the status when the employee is present on a week off ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);

		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s,  punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_644_Normal_Attendance_16
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_644_Normal_Attendance_16()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General " + "Check the status when the employee completes a half day.  ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_646_Normal_Attendance_18
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_646_Normal_Attendance_18()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when the employee has not completed the minimum hours required for half day on a week off ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_647_Normal_Attendance_19
	@Test(enabled = true, priority = 8, groups = { "Smoke" })
	public void MPI_647_Normal_Attendance_19()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"General " + "Check the status when the employee completes a half day on a week off. ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	/*
	 * //MPI_652_Normal_Attendance_24
	 * 
	 * @Test(enabled = true, priority = 9, groups = { "Smoke" }) public void
	 * MPI_652_Normal_Attendance_24()  { String currTC =
	 * Thread.currentThread().getStackTrace()[1].getMethodName();
	 * logResults.createExtentReport(currTC); logResults.setScenarioName("General "
	 * + "Check the status when the employee present on day " );
	 * 
	 * 
	 * // Load all data including punch-out time and mode ArrayList<String> data =
	 * initBase.loadExcelData("GeneralShift_Attendance", currTC,
	 * "cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction," +
	 * "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
	 * + "locationid,inouttime,mode,photo,secondinouttime,secondmode,date");
	 * 
	 * int i = 0; String cmpcode = data.get(i++); String emailid = data.get(i++);
	 * String password = data.get(i++);
	 * 
	 * String baseuri = data.get(i++); String loginendpoint = data.get(i++); String
	 * endpointoftransaction = data.get(i++); String cardnumber = data.get(i++); int
	 * cardtype = Integer.parseInt(data.get(i++)); String deviceuniqueid =
	 * data.get(i++); String bio1finger = data.get(i++); String bio2finger =
	 * data.get(i++); String employeeuniqueid = data.get(i++); String locationid =
	 * data.get(i++); String inouttime1 = data.get(i++); // Punch In time String
	 * mode = data.get(i++); // Punch In mode (e.g., "IN") String photo =
	 * data.get(i++); String secondInOutTime2 = data.get(i++); // Punch Out time
	 * String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")
	 * 
	 * String date = data.get(i++); String inouttime = date + " " + inouttime1;
	 * String secondInOutTime = date + " " + secondInOutTime2;
	 * 
	 * MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
	 * 
	 * // Punch IN Response punchInResponse = apiPage.executeSuccessTransaction(
	 * baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
	 * endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
	 * bio2finger, employeeuniqueid, locationid, inouttime, mode, photo );
	 * 
	 * if (punchInResponse.getStatusCode() == 200) { logResults.createLogs("N",
	 * "PASS", "Punch IN executed successfully"); } else {
	 * logResults.createLogs("N", "FAIL", "Punch IN failed." +
	 * punchInResponse.asString()); }
	 * 
	 * // Punch OUT Response punchOutResponse = apiPage.executeSuccessTransaction(
	 * baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
	 * endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
	 * bio2finger, employeeuniqueid, locationid, secondInOutTime, secondMode, photo
	 * );
	 * 
	 * if (punchOutResponse.getStatusCode() == 200) { logResults.createLogs("N",
	 * "PASS", "Punch OUT executed successfully"); } else {
	 * logResults.createLogs("N", "FAIL", "Punch OUT failed." +
	 * punchOutResponse.asString()); } }
	 * 
	 */

	// MPI_655_Normal_Attendance_27
	@Test(enabled = true, priority = 10, groups = { "Smoke" })
	public void MPI_655_Normal_Attendance_27()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults
				.setScenarioName("General " + "Check the status when the employee did a single punch in on a week off");

		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,locationid,inouttime,mode,photo,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

		int i = 0;
		String cmpcode = data.get(i);
		String emailid = data.get(++i);
		String password = data.get(++i);

		String baseuri = data.get(++i);
		String loginendpoint = data.get(++i);
		String endpointoftransaction = data.get(++i);
		String cardnumber = data.get(++i);
		int cardtype = Integer.parseInt(data.get(++i));
		String deviceuniqueid = data.get(++i);
		String bio1finger = data.get(++i);
		String bio2finger = data.get(++i);
		String employeeuniqueid = data.get(++i);
		String locationid = data.get(++i);
		String inouttime1 = data.get(++i);
		String mode = data.get(++i);
		String photo = data.get(++i);

		String date = data.get(++i);
		String statusEndpoint = data.get(++i);
		String fromDateofuserstatus1 = data.get(++i);
		String toDateofuserstatus2 = data.get(++i);
		String expectedStatus = data.get(++i);
		String description = data.get(++i);
		String updateattendanceendpoint = data.get(++i);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;

		// Create API Page object
		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Call combined method
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}

		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and No Further Punch So Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_440_Normal_Attendance_48
	@Test(enabled = true, priority = 11, groups = { "Smoke" })
	public void MPI_440_Normal_Attendance_48()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when 'First Clock In & Last Clock Out' is applied in the policy and the employee did last punch in at the end of shift ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch In
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch In executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch In failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Last Punch In at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_657_Normal_Attendance_47
	@Test(enabled = true, priority = 12, groups = { "Smoke" })
	public void MPI_657_Normal_Attendance_47()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when 'First Clock In & Last Clock Out' is applied in the policy and the employee did last punch out before end of shift");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_659_Normal_Attendance_46
	@Test(enabled = true, priority = 13, groups = { "Smoke" })
	public void MPI_659_Normal_Attendance_46()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when '17 hr' is set in the Maximum Duration from shift start time in the policy and the employee did last punch out before 17 hours from shift start time of that day");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);

		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		LocalDate localDate = LocalDate.parse(date);
		String date2 = localDate.plusDays(1).toString();

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date2 + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and OUT On  %s at  %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, date2, secondInOutTime2, actualStatus,
					expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_442_Normal_Attendance_45
	@Test(enabled = true, priority = 14, groups = { "Smoke" })
	public void MPI_442_Normal_Attendance_45()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when '17 hr' is set in the Maximum Duration from shift start time in the policy and the employee only completes half day and punches out after 17 hours");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String thirdinouttime3 = data.get(i++); // Punch Out time
		String thirdmode = data.get(i++);

		String date = data.get(i++);

		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		LocalDate localDate = LocalDate.parse(date);
		String date2 = localDate.plusDays(1).toString();

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		String thirdinouttime = date2 + " " + thirdinouttime3;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Punch OUT 2nd time
		Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid,
				password, cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
				bio2finger, employeeuniqueid, locationid, thirdinouttime, thirdmode, photo);

		if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + thirdinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + SecondTimepunchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and OUT On  %s at  %s and did Punch Out After 17hr On %s at %s . Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, date, secondInOutTime2, date2, thirdinouttime,
					actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_683_Normal_Attendance_29
	@Test(enabled = true, priority = 15, groups = { "Smoke" })
	public void MPI_683_Normal_Attendance_29()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check that when the user is present for more than the specified shift duration, but overtime is not configured and 'Multiple Shift Detection' is set to 'yes'.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_684_Normal_Attendance_30
	@Test(enabled = true, priority = 16, groups = { "Smoke" })
	public void MPI_684_Normal_Attendance_30()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when \"Ignore Punch Minutes\" is set to 0 and the employee punches in and out within 1 minute without any further punches on the same day.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_656_Normal_Attendance_44
	@Test(enabled = true, priority = 17, groups = { "Smoke" })
	public void MPI_656_Normal_Attendance_44()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when Shift Type is set to \"Nearest\" in the policy. As an employee, punch in at 7:00 AM and punch out at 3:00 PM while assigned to a General Shift ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_668_Normal_Attendance_43
	@Test(enabled = true, priority = 18, groups = { "Smoke" })
	public void MPI_668_Normal_Attendance_43()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the attendance status whenÂ Shift TypeÂ is set toÂ \"Nearest\"Â and the employeeÂ punches in 2hr before of shift start time and punch out at shift end ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);
		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_685_Normal_Attendance_31
	@Test(enabled = true, priority = 19, groups = { "Smoke" })
	public void MPI_685_Normal_Attendance_31()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when the employee performs multiple punches in a day and completes the shift as per the defined shift policy.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String thirdinouttime3 = data.get(i++); // Punch Out time
		String thirdmode = data.get(i++);

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;
		String thirdinouttime = date + " " + thirdinouttime3;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Punch OUT 2nd time
		Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid,
				password, cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
				bio2finger, employeeuniqueid, locationid, thirdinouttime, thirdmode, photo);

		if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "2nd Punch OUT executed successfully at " + thirdinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + SecondTimepunchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s and Last Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, thirdinouttime, actualStatus,
					expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_686_Normal_Attendance_42
	@Test(enabled = true, priority = 20, groups = { "Smoke" })
	public void MPI_686_Normal_Attendance_42()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"General " + "Check the status when Casual Leave is applied for a day marked as Absent.");

		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,employeeuniqueid,updateattendanceendpoint");
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

		String date = data.get(++i);
		String statusEndpoint = data.get(++i);
		String fromDateofuserstatus1 = data.get(++i);
		String toDateofuserstatus2 = data.get(++i);
		String expectedStatus = data.get(++i);
		String description = data.get(++i);
		String employeeuniqueid = data.get(++i);
		String updateattendanceendpoint = data.get(++i);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String fromdate = date + fromdate1;
		String todate = date + todate2;

		// Debugging - Print extracted values

		// Create API Page object
		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		Response LeaveApplied = apiPage.executeCreateLeaves(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				leaveendpoint, type, entityid, leavetypeid, totaldays, fromdate, todate, fromdurationtype,
				todurationtype, status, remarks, documentname, document);

		// Debugging - Print response details
		System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
		System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

		if (LeaveApplied.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
		} else {
			logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
		}

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}

		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s Applied Full Day Leave On %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_666_Normal_Attendance_41
	@Test(enabled = true, priority = 21, groups = { "Smoke" })
	public void MPI_666_Normal_Attendance_41()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General " + "Check the status when user is Absent but is on Paid Leave");

		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,employeeuniqueid,updateattendanceendpoint");
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

		String date = data.get(++i);
		String statusEndpoint = data.get(++i);
		String fromDateofuserstatus1 = data.get(++i);
		String toDateofuserstatus2 = data.get(++i);
		String expectedStatus = data.get(++i);
		String description = data.get(++i);
		String employeeuniqueid = data.get(++i);
		String updateattendanceendpoint = data.get(++i);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String fromdate = date + fromdate1;
		String todate = date + todate2;

		// Debugging - Print extracted values

		// Create API Page object
		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		Response LeaveApplied = apiPage.executeCreateLeaves(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				leaveendpoint, type, entityid, leavetypeid, totaldays, fromdate, todate, fromdurationtype,
				todurationtype, status, remarks, documentname, document);

		// Debugging - Print response details
		System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
		System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

		if (LeaveApplied.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
		} else {
			logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
		}

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}

		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s Is on Leave On This Date %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}
	}

	// MPI_678_Normal_Attendance_28
	@Test(enabled = true, priority = 22, groups = { "Smoke" })
	public void MPI_678_Normal_Attendance_28()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when Out punch is there but no IN punch but employee is present on that day");

		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,locationid,inouttime,mode,photo,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

		int i = 0;
		String cmpcode = data.get(i);
		String emailid = data.get(++i);
		String password = data.get(++i);

		String baseuri = data.get(++i);
		String loginendpoint = data.get(++i);
		String endpointoftransaction = data.get(++i);
		String cardnumber = data.get(++i);
		int cardtype = Integer.parseInt(data.get(++i));
		String deviceuniqueid = data.get(++i);
		String bio1finger = data.get(++i);
		String bio2finger = data.get(++i);
		String employeeuniqueid = data.get(++i);
		String locationid = data.get(++i);
		String inouttime1 = data.get(++i);
		String mode = data.get(++i);
		String photo = data.get(++i);

		String date = data.get(++i);
		String statusEndpoint = data.get(++i);
		String fromDateofuserstatus1 = data.get(++i);
		String toDateofuserstatus2 = data.get(++i);
		String expectedStatus = data.get(++i);
		String description = data.get(++i);
		String updateattendanceendpoint = data.get(++i);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;

		// Create API Page object
		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Call combined method
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch Out executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch Out failed." + punchInResponse.asString());
			return;
		}

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}

		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched Out at %s. Final Status = %s (Expected = %s)", description,
					employeeuniqueid, date, inouttime1, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_697_Normal_Attendance_52
	@Test(enabled = true, priority = 23, groups = { "Smoke" })
	public void MPI_697_Normal_Attendance_52()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General " + "Check the status When user arrives Late");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}

		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_698_Normal_Attendance_53
	@Test(enabled = true, priority = 24, groups = { "Smoke" })
	public void MPI_698_Normal_Attendance_53()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General " + "Check the status when the employee arrives earlyÂ ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_724_Normal_Attendance_54
	@Test(enabled = true, priority = 25, groups = { "Smoke" })
	public void MPI_724_Normal_Attendance_54()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when 'First Clock In & Last Clock Out' is applied in the policy and the employee performs the last punch-in 1 minute before the shift end time.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_727_Normal_Attendance_55
	@Test(enabled = true, priority = 26, groups = { "Smoke" })
	public void MPI_727_Normal_Attendance_55()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"General " + "Check the status when the employee applies a Half Day Regularization Request ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_671_Normal_Attendance_38
	@Test(enabled = true, priority = 27, groups = { "Smoke" })
	public void MPI_671_Normal_Attendance_38()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when the employee applies for first-half leave without working during the second half of the day");

		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,employeeuniqueid,updateattendanceendpoint");
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

		String date = data.get(++i);
		String statusEndpoint = data.get(++i);
		String fromDateofuserstatus1 = data.get(++i);
		String toDateofuserstatus2 = data.get(++i);
		String expectedStatus = data.get(++i);
		String description = data.get(++i);
		String employeeuniqueid = data.get(++i);
		String updateattendanceendpoint = data.get(++i);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String fromdate = date + fromdate1;
		String todate = date + todate2;

		// Debugging - Print extracted values

		// Create API Page object
		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		Response LeaveApplied = apiPage.executeCreateLeaves(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				leaveendpoint, type, entityid, leavetypeid, totaldays, fromdate, todate, fromdurationtype,
				todurationtype, status, remarks, documentname, document);

		// Debugging - Print response details
		System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
		System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

		if (LeaveApplied.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
		} else {
			logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
		}

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}

		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, Applied First half Leave. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_672_Normal_Attendance_39
	@Test(enabled = true, priority = 28, groups = { "Smoke" })
	public void MPI_672_Normal_Attendance_39()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"General " + "Check the status when the employee completes half day and apply half day leave  ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

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

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String fromdate = date + fromdate1;
		String todate = date + todate2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		Response LeaveApplied = apiPage.executeCreateLeaves(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				leaveendpoint, type, entityid, leavetypeid, totaldays, fromdate, todate, fromdurationtype,
				todurationtype, status, remarks, documentname, document);

		// Debugging - Print response details
		System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
		System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

		if (LeaveApplied.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
		} else {
			logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s And Applied 2nd Half Leave. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_673_Normal_Attendance_40
	@Test(enabled = true, priority = 29, groups = { "Smoke" })
	public void MPI_673_Normal_Attendance_40()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when the employee applies for the first half as a leave and completes the second half (half-day) ");

		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,endpointoftransaction,cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");
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
		String bio2finger = data.get(++i);
		String employeeuniqueid = data.get(++i);
		String locationid = data.get(++i);
		String inouttime1 = data.get(++i); // Punch In time
		String mode = data.get(++i); // Punch In mode (e.g., "IN")
		String photo = data.get(++i);
		String secondInOutTime2 = data.get(++i); // Punch Out time
		String secondMode = data.get(++i);

		String date = data.get(++i);
		String statusEndpoint = data.get(++i);
		String fromDateofuserstatus1 = data.get(++i);
		String toDateofuserstatus2 = data.get(++i);
		String expectedStatus = data.get(++i);
		String description = data.get(++i);
		String updateattendanceendpoint = data.get(++i);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String fromdate = date + fromdate1;
		String todate = date + todate2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		// Debugging - Print extracted values

		// Create API Page object
		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		Response LeaveApplied = apiPage.executeCreateLeaves(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				leaveendpoint, type, entityid, leavetypeid, totaldays, fromdate, todate, fromdurationtype,
				todurationtype, status, remarks, documentname, document);

		// Debugging - Print response details
		System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
		System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

		if (LeaveApplied.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
		} else {
			logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
		}

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}

		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, Applied First Half Leave And punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_728_Normal_Attendance_56
	@Test(enabled = true, priority = 30, groups = { "Smoke" })
	public void MPI_728_Normal_Attendance_56()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"General " + "Check the status for emp by applying half day regulization request and half day leave");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

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

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String fromdate = date + fromdate1;
		String todate = date + todate2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Create API Page object

		Response LeaveApplied = apiPage.executeCreateLeaves(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				leaveendpoint, type, entityid, leavetypeid, totaldays, fromdate, todate, fromdurationtype,
				todurationtype, status, remarks, documentname, document);

		// Debugging - Print response details
		System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
		System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

		if (LeaveApplied.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
		} else {
			logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and OUT at %s Applied 2nd Half Leave. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_729_Normal_Attendance_57
	@Test(enabled = true, priority = 31, groups = { "Smoke" })
	public void MPI_729_Normal_Attendance_57()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when the employee applies for second-half leave without working during the first half of the day ");

		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,employeeuniqueid,updateattendanceendpoint");
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

		String date = data.get(++i);
		String statusEndpoint = data.get(++i);
		String fromDateofuserstatus1 = data.get(++i);
		String toDateofuserstatus2 = data.get(++i);
		String expectedStatus = data.get(++i);
		String description = data.get(++i);
		String employeeuniqueid = data.get(++i);
		String updateattendanceendpoint = data.get(++i);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String fromdate = date + fromdate1;
		String todate = date + todate2;

		// Debugging - Print extracted values

		// Create API Page object
		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		Response LeaveApplied = apiPage.executeCreateLeaves(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				leaveendpoint, type, entityid, leavetypeid, totaldays, fromdate, todate, fromdurationtype,
				todurationtype, status, remarks, documentname, document);

		// Debugging - Print response details
		System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
		System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

		if (LeaveApplied.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
		} else {
			logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}

		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, Applied 2nd Half Leave. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_730_Normal_Attendance_58
	@Test(enabled = true, priority = 32, groups = { "Smoke" })
	public void MPI_730_Normal_Attendance_58()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when the employee applies for a half-day leave but performs only a single punch-in  ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
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

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String fromdate = date + fromdate1;
		String todate = date + todate2;

		String inouttime = date + " " + inouttime1;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		Response LeaveApplied = apiPage.executeCreateLeaves(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				leaveendpoint, type, entityid, leavetypeid, totaldays, fromdate, todate, fromdurationtype,
				todurationtype, status, remarks, documentname, document);

		// Debugging - Print response details
		System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
		System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

		if (LeaveApplied.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
		} else {
			logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Applied 2nd Half Leave. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_731_Normal_Attendance_59
	@Test(enabled = true, priority = 33, groups = { "Smoke" })
	public void MPI_731_Normal_Attendance_59()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "Check the status when the employee works fewer hours and applies for a half-day leave.  ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,leaveendpoint,type,entityid,leavetypeid,totaldays,fromdate,todate,fromdurationtype,todurationtype,statusofleave,remarks,documentname,document,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

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

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String fromdate = date + fromdate1;
		String todate = date + todate2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		Response LeaveApplied = apiPage.executeCreateLeaves(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				leaveendpoint, type, entityid, leavetypeid, totaldays, fromdate, todate, fromdurationtype,
				todurationtype, status, remarks, documentname, document);

		// Debugging - Print response details
		System.out.println("Create Leave API Response Code." + LeaveApplied.getStatusCode());
		System.out.println("Create Leave API Response Body." + LeaveApplied.asString());

		if (LeaveApplied.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Leave Applied successfully" + date);
		} else {
			logResults.createLogs("N", "FAIL", "Leave Creation failed." + LeaveApplied.asString());
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s And Applied 2nd Half Leave. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_860_Normal_Attendance_69
	@Test(enabled = true, priority = 34, groups = { "Smoke" })
	public void MPI_860_Normal_Attendance_69()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "To verify this, check the total working hours of an employee who worked half an hour more than the shift duration ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_861_Normal_Attendance_70
	@Test(enabled = true, priority = 35, groups = { "Smoke" })
	public void MPI_861_Normal_Attendance_70()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General "
				+ "To verify this, check the working hours of an employee who took a 2-hour break and did punch out at the end of the shift. ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String thirdinouttime3 = data.get(i++); // Punch Out time
		String thirdmode = data.get(i++);

		String date = data.get(i++);
		String fourthinouttime4 = data.get(i++);
		String fourthmode = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		String thirdinouttime = date + " " + thirdinouttime3;
		String fourthinouttime = date + " " + fourthinouttime4;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Punch in 2nd time
		Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid,
				password, cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
				bio2finger, employeeuniqueid, locationid, thirdinouttime, thirdmode, photo);

		if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			return;
		}

		// Punch OUT
		Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, fourthinouttime, fourthmode, photo);

		if (fourthpunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s And Punch In at %s and Last Punch Out at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, thirdinouttime, fourthinouttime,
					actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}

	}

	// MPI_652_Normal_Attendance_24
	@Test(enabled = true, priority = 36, groups = { "Smoke" })
	public void MPI_652_Normal_Attendance_24()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("General " + "Check the status when the employee present on day");

		// Load Excel data
		ArrayList<String> data = initBase.loadExcelData("GeneralShift_Attendance", currTC,
				"description,cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,"
						+ "getuserstatusendpoint,fromdateofstatus,todateofstatus,status,updateattendanceendpoint");

		int i = 0;
		String description = data.get(i++);
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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++);
		String mode = data.get(i++);
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++);
		String secondMode = data.get(i++);
		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String punchInTime = date + " " + inouttime1;
		String punchOutTime = date + " " + secondInOutTime2;
		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, punchInTime, mode, photo);
		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, punchOutTime, secondMode, photo);
		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and OUT at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

			if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL",
					"âŒ Failed to fetch final status. API Response." + validation.asString());
		}
	}

	// MPI_639_Normal_Attendance_11
	@Test(enabled = true, priority = 37, groups = { "Smoke" })
	public void MPI_639_Normal_Attendance_11()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Policy " + "Check the status when the employee performs only a single punch on a holiday");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
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

		Response holidayResponse = apiPage.executeCreateHoliday(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, holidayendpoint, holidayname, date, applicableto, holidaydescription, states, stateid);

		if (holidayResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Holiday created successfully." + holidayname);
		} else {
			logResults.createLogs("N", "FAIL", "Holiday creation failed." + holidayResponse.asString());
		}

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s . Final Status = %s (Expected = %s)", description,
					employeeuniqueid, date, inouttime1, actualStatus, expectedStatus);

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

	// MPI_650_Normal_Attendance_22
	@Test(enabled = true, priority = 38, groups = { "Smoke" })
	public void MPI_650_Normal_Attendance_22()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Check the status when the employee has not completed the minimum hours required for half day on a holiday.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);

		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

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
	@Test(enabled = true, priority = 39, groups = { "Smoke" })
	public void MPI_640_Normal_Attendance_12()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy " + "Check the status when the employee is present on a holiday");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);

		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

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
	@Test(enabled = true, priority = 40, groups = { "Smoke" })
	public void MPI_642_Normal_Attendance_14()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy " + "Check the status when there is a holiday.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,employeeuniqueid,"
						+ "date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

		int i = 0;
		String cmpcode = data.get(i++);
		String emailid = data.get(i++);
		String password = data.get(i++);

		String baseuri = data.get(i++);
		String loginendpoint = data.get(i++);

		String employeeuniqueid = data.get(i++);
		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);
		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format("%s â€“ This Employee %s on %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, actualStatus, expectedStatus);

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
	@Test(enabled = true, priority = 41, groups = { "Smoke" })
	public void MPI_643_Normal_Attendance_15()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults
				.setScenarioName("Policy " + "Check the status when there is a holiday and a week off on the same day");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,employeeuniqueid,"
						+ "date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid");

		int i = 0;
		String cmpcode = data.get(i++);
		String emailid = data.get(i++);
		String password = data.get(i++);

		String baseuri = data.get(i++);
		String loginendpoint = data.get(i++);

		String employeeuniqueid = data.get(i++);
		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
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
		// Create holiday On Weekoff
		Response holidayResponse = apiPage.executeCreateHoliday(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, holidayendpoint, holidayname, date, applicableto, holidaydescription, states, stateid);

		if (holidayResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Holiday created On Weekoff successfully." + holidayname);
		} else {
			logResults.createLogs("N", "FAIL", "Holiday creation failed." + holidayResponse.asString());
		}

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ For This Employee %s on This date %s. Final Status = %s (Expected = %s)", description,
					employeeuniqueid, date, actualStatus, expectedStatus);

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
	@Test(enabled = true, priority = 42, groups = { "Smoke" })
	public void MPI_648_Normal_Attendance_20()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy " + "Check the status when the employee completes a half day on holiday.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);

		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

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
	@Test(enabled = true, priority = 43, groups = { "Smoke" })
	public void MPI_649_Normal_Attendance_21()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Check the status when the employee completes a half day on a day that is both a holiday and a weekoff. ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);

		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

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
	@Test(enabled = true, priority = 44, groups = { "Smoke" })
	public void MPI_651_Normal_Attendance_23()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Check the status when the employee has not completed the minimum hours required for half day on a day that is both a week off and a holiday. ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);

		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

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
	@Test(enabled = true, priority = 45, groups = { "Smoke" })
	public void MPI_653_Normal_Attendance_25()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Check the status when the employee is present on a day that is both a holiday and a week off. ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);

		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus);

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
	@Test(enabled = true, priority = 46, groups = { "Smoke" })
	public void MPI_654_Normal_Attendance_26()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Check the status when the employee did a single punch in on a day that is both a week off and a holiday. ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);

		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s. Final Status = %s (Expected = %s)", description,
					employeeuniqueid, date, inouttime1, actualStatus, expectedStatus);

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

	// MPI_465_Attendance_Policy_31
	@Test(enabled = true, priority = 47, groups = { "Smoke" })
	public void MPI_465_Attendance_Policy_31()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Policy " + "Calculate overtime hours using the 'Total Hours minus Shift Duration' calculation method");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus,
					actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_466_Attendance_Policy_32
	@Test(enabled = true, priority = 48, groups = { "Smoke" })
	public void MPI_466_Attendance_Policy_32()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Calculate overtime hours using the '(Total Effective Hours) - (Shift Duration)' calculation method.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,effectivehours");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String thirdinouttime3 = data.get(i++); // Punch Out time
		String thirdmode = data.get(i++);

		String date = data.get(i++);
		String fourthinouttime4 = data.get(i++);
		String fourthmode = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);
		String effectivehours = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		String thirdinouttime = date + " " + thirdinouttime3;
		String fourthinouttime = date + " " + fourthinouttime4;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Punch in 2nd time
		Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid,
				password, cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
				bio2finger, employeeuniqueid, locationid, thirdinouttime, thirdmode, photo);

		if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			return;
		}

		// Punch OUT
		Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, fourthinouttime, fourthmode, photo);

		if (fourthpunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Handle null EffectiveHours â†’ default to 00:00
			String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00"
					: actualEffectiveHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus,
					actualEffectiveHours, effectivehours, actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)
					&& effectivehours.equalsIgnoreCase(actualEffectiveHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_467_Attendance_Policy_33
	@Test(enabled = true, priority = 49, groups = { "Smoke" })
	public void MPI_467_Attendance_Policy_33()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Policy " + "Calculate overtime hours using the 'Shift End Time to Out Punch' calculation method.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus,
					actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_468_Attendance_Policy_34
	@Test(enabled = true, priority = 50, groups = { "Smoke" })
	public void MPI_468_Attendance_Policy_34()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Calculate overtime hours using the 'In Punch to Shift Start Time' calculation method. (approved by system selected in attendance policy)");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus,
					actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_469_Attendance_Policy_35
	@Test(enabled = true, priority = 51, groups = { "Smoke" })
	public void MPI_469_Attendance_Policy_35()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Calculate overtime hours using the '(In Punch to Shift Start Time) + (Shift End Time to Out Punch)' calculation method. ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus,
					actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_470_Attendance_Policy_36
	@Test(enabled = true, priority = 52, groups = { "Smoke" })
	public void MPI_470_Attendance_Policy_36()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Calculate OT hours when the calculation method is '(Total Hours â€“ Shift Duration)', with OT starting after 00:15 is configured. The employee punches out 45 minutes after the shift end time");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus,
					actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_876_Attendance_Policy_72
	@Test(enabled = true, priority = 53, groups = { "Smoke" })
	public void MPI_876_Attendance_Policy_72()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Calculate OT hours when the calculation method is '(Total Hours â€“ Shift Duration)', with OT starting before 00:15 is configured. The employee punches In 45 minutes before the shift start time");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus,
					actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_885_Attendance_Policy_77
		@Test(enabled = true, priority = 54, groups = { "Smoke" })
		public void MPI_885_Attendance_Policy_77()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName("Policy "
					+ "Calculate overtime hours using the 'Shift End Time to Out Punch' calculation method by taking the break");

			// Load all data including punch-out time and mode
			ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
					"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
							+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
							+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,effectivehours");

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
			String bio2finger = data.get(i++);
			String employeeuniqueid = data.get(i++);
			String locationid = data.get(i++);
			String inouttime1 = data.get(i++); // Punch In time
			String mode = data.get(i++); // Punch In mode (e.g., "IN")
			String photo = data.get(i++);
			String secondInOutTime2 = data.get(i++); // Punch Out time
			String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

			String thirdinouttime3 = data.get(i++); // Punch Out time
			String thirdmode = data.get(i++);

			String date = data.get(i++);
			String fourthinouttime4 = data.get(i++);
			String fourthmode = data.get(i++);
			String statusEndpoint = data.get(i++);
			String fromDateofuserstatus1 = data.get(i++);
			String toDateofuserstatus2 = data.get(i++);
			String expectedStatus = data.get(i++);
			String description = data.get(i++);
			String totalOTHour = data.get(i++);
			String updateattendanceendpoint = data.get(i++);
			String effectivehours = data.get(i++);

			String fromDateofstatus = date + fromDateofuserstatus1;
			String toDateofstatus = date + toDateofuserstatus2;

			String inouttime = date + " " + inouttime1;
			String secondInOutTime = date + " " + secondInOutTime2;

			String thirdinouttime = date + " " + thirdinouttime3;
			String fourthinouttime = date + " " + fourthinouttime4;

			MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

			// Punch IN
			Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
					baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
					employeeuniqueid, locationid, inouttime, mode, photo);

			if (punchInResponse.getStatusCode() == 200) {
				logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
			} else {
				logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
				return;
			}

			// Punch OUT
			Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
					cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
					employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

			if (punchOutResponse.getStatusCode() == 200) {
				logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
			} else {
				logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
				return;
			}

			// Punch in 2nd time
			Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid,
					password, cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
					bio2finger, employeeuniqueid, locationid, thirdinouttime, thirdmode, photo);

			if (SecondTimepunchOutResponse.getStatusCode() == 200) {
				logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			} else {
				logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
				return;
			}

			// Punch OUT
			Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
					cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
					employeeuniqueid, locationid, fourthinouttime, fourthmode, photo);

			if (fourthpunchOutResponse.getStatusCode() == 200) {
				logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			} else {
				logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
				return;
			}
			// Trigger attendance update first
			Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
					baseuri, updateattendanceendpoint, // <-- add endpoint in excel
					employeeuniqueid, date + "T00:00:00.000Z");

			if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
				logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
			} else {
				logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
			}
			// Get User Status
			Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
					statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

			if (validation.statusCode() == 200) {
				String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

				// Handle null OTHours â†’ default to 00:00
				String actualOTHours = validation.jsonPath().getString("[0].OTHours");
				actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

				// Handle null EffectiveHours â†’ default to 00:00
				String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
				actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00"
						: actualEffectiveHours;

				// Make sentence using excel inputs
				String finalSentence = String.format(
						"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s  punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s), OT Hours = %s (Expected = %s)",
						description, employeeuniqueid, date, inouttime1, secondInOutTime2, thirdinouttime, fourthinouttime,
						actualStatus, expectedStatus, actualEffectiveHours, effectivehours, actualOTHours, totalOTHour);

				// Validate both Final Status & OT Hours
				if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)
						&& effectivehours.equalsIgnoreCase(actualEffectiveHours)) {
					logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
				} else {
					logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
				}
			} else {
				logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
			}

		}
	
	// MPI_878_Attendance_Policy_73
	@Test(enabled = true, priority = 55, groups = { "Smoke" })
	public void MPI_878_Attendance_Policy_73()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Calculate OT hours when the calculation method is '(Total Hours â€“ Shift Duration)', with OT starting after 00:15 is configured. The employee punches out 10 minutes after the shift end time");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus,
					actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_878_Attendance_Policy_74
	@Test(enabled = true, priority = 56, groups = { "Smoke" })
	public void MPI_878_Attendance_Policy_74()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Calculate OT hours when the calculation method is '(Total Hours â€“ Shift Duration)', with OT starting before 00:15 is configured. The employee punches In 10 minutes before the shift start time");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus,
					actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_882_Attendance_Policy_76
	@Test(enabled = true, priority = 57, groups = { "Smoke" })
	public void MPI_882_Attendance_Policy_76()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Calculate overtime hours using the 'Total Hours minus Shift Duration' calculation method by taking break");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String thirdinouttime3 = data.get(i++); // Punch Out time
		String thirdmode = data.get(i++);

		String date = data.get(i++);
		String fourthinouttime4 = data.get(i++);
		String fourthmode = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		String thirdinouttime = date + " " + thirdinouttime3;
		String fourthinouttime = date + " " + fourthinouttime4;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Punch in 2nd time
		Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid,
				password, cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
				bio2finger, employeeuniqueid, locationid, thirdinouttime, thirdmode, photo);

		if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			return;
		}

		// Punch OUT
		Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, fourthinouttime, fourthmode, photo);

		if (fourthpunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus,
					actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	

	// MPI_886_Attendance_Policy_78
	@Test(enabled = true, priority = 58, groups = { "Smoke" })
	public void MPI_886_Attendance_Policy_78()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Calculate overtime hours using the 'In Punch to Shift Start Time' calculation method by taking the break");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,effectivehours");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String thirdinouttime3 = data.get(i++); // Punch Out time
		String thirdmode = data.get(i++);

		String date = data.get(i++);
		String fourthinouttime4 = data.get(i++);
		String fourthmode = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);
		String effectivehours = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		String thirdinouttime = date + " " + thirdinouttime3;
		String fourthinouttime = date + " " + fourthinouttime4;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Punch in 2nd time
		Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid,
				password, cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
				bio2finger, employeeuniqueid, locationid, thirdinouttime, thirdmode, photo);

		if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			return;
		}

		// Punch OUT
		Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, fourthinouttime, fourthmode, photo);

		if (fourthpunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Handle null EffectiveHours â†’ default to 00:00
			String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00"
					: actualEffectiveHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s  punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, thirdinouttime, fourthinouttime,
					actualStatus, expectedStatus, actualEffectiveHours, effectivehours, actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)
					&& effectivehours.equalsIgnoreCase(actualEffectiveHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_887_Attendance_Policy_79
	@Test(enabled = true, priority = 59, groups = { "Smoke" })
	public void MPI_887_Attendance_Policy_79()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Calculate overtime hours using the '(In Punch to Shift Start Time) + (Shift End Time to Out Punch)' calculation method by taking the break");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,effectivehours");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String thirdinouttime3 = data.get(i++); // Punch Out time
		String thirdmode = data.get(i++);

		String date = data.get(i++);
		String fourthinouttime4 = data.get(i++);
		String fourthmode = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);
		String effectivehours = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		String thirdinouttime = date + " " + thirdinouttime3;
		String fourthinouttime = date + " " + fourthinouttime4;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Punch in 2nd time
		Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid,
				password, cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
				bio2finger, employeeuniqueid, locationid, thirdinouttime, thirdmode, photo);

		if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			return;
		}

		// Punch OUT
		Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, fourthinouttime, fourthmode, photo);

		if (fourthpunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Handle null EffectiveHours â†’ default to 00:00
			String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00"
					: actualEffectiveHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s  punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, thirdinouttime, fourthinouttime,
					actualStatus, expectedStatus, actualEffectiveHours, effectivehours, actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)
					&& effectivehours.equalsIgnoreCase(actualEffectiveHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_888_Attendance_Policy_80
	@Test(enabled = true, priority = 60, groups = { "Smoke" })
	public void MPI_888_Attendance_Policy_80()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Check the status of the employee when \"Ignore Punch Minutes\" is set to 1 minute, and the employee performs punch in and punch out within 1 minute and punch out after half an of shift end and Calculate overtime hours using the '(Total Effective Hours) - (Shift Duration)' calculation method.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,effectivehours");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String fourthinouttime4 = data.get(i++);
		String fourthmode = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
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
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Punch OUT
		Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, fourthinouttime, fourthmode, photo);

		if (fourthpunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Handle null EffectiveHours â†’ default to 00:00
			String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00"
					: actualEffectiveHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s   and Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, fourthinouttime, actualStatus,
					expectedStatus, actualEffectiveHours, effectivehours, actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)
					&& effectivehours.equalsIgnoreCase(actualEffectiveHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_742_Attendance_Policy_69
	@Test(enabled = true, priority = 61, groups = { "Smoke" })
	public void MPI_742_Attendance_Policy_69()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Check that, for theÂ Shift End Time to Out Punch attendance policy configuration do early punch in and check the OT status.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus,
					actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_743_Attendance_Policy_70
	@Test(enabled = true, priority = 62, groups = { "Smoke" })
	public void MPI_743_Attendance_Policy_70()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Check that, for the In Punch to Shift Start Time attendance policy configuration do late punch out and check the OT status ");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String date = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, actualStatus, expectedStatus,
					actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_889_Attendance_Policy_81
	@Test(enabled = true, priority = 63, groups = { "Smoke" })
	public void MPI_889_Attendance_Policy_81()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Verify the attendance status for an employee who completes both the first and second shifts when the â€˜Multiple Shift Detectionâ€™ option is set to â€˜Yesâ€™ in the attendance policy.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String thirdinouttime3 = data.get(i++); // Punch Out time
		String thirdmode = data.get(i++);

		String date = data.get(i++);
		String fourthinouttime4 = data.get(i++);
		String fourthmode = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		String thirdinouttime = date + " " + thirdinouttime3;
		String fourthinouttime = date + " " + fourthinouttime4;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Punch in 2nd time
		Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid,
				password, cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
				bio2finger, employeeuniqueid, locationid, thirdinouttime, thirdmode, photo);

		if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			return;
		}

		// Punch OUT
		Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, fourthinouttime, fourthmode, photo);

		if (fourthpunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

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
					"%s â€“ This Employee %s on %s, worked in 1st Shift (IN.%s, OUT.%s, Status.%s, OT.%s) "
							+ "and in 2nd Shift (IN.%s, OUT.%s, Status.%s, OT.%s). Expected Status = %s, Expected OT = %s",
					description, employeeuniqueid, date, firstIn, firstOut, firstStatus, firstOT, secondIn, secondOut,
					secondStatus, secondOT, expectedStatus, totalOTHour);

			// Validate both shifts (you can adjust conditions if OT differs per shift)
			if (expectedStatus.equalsIgnoreCase(firstStatus) && expectedStatus.equalsIgnoreCase(secondStatus)
					&& totalOTHour.equalsIgnoreCase(firstOT) && totalOTHour.equalsIgnoreCase(secondOT)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}

		}
	}

	// MPI_890_Attendance_Policy_82
	@Test(enabled = true, priority = 64, groups = { "Smoke" })
	public void MPI_890_Attendance_Policy_82()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Verify the attendance status for an employee who completes both the first and second shifts when the â€˜Multiple Shift Detectionâ€™ option is set to â€˜Noâ€™ in the attendance policy.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,multipleshiftstatus");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String thirdinouttime3 = data.get(i++); // Punch Out time
		String thirdmode = data.get(i++);

		String date = data.get(i++);
		String fourthinouttime4 = data.get(i++);
		String fourthmode = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);
		String multipleshiftstatus = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		String thirdinouttime = date + " " + thirdinouttime3;
		String fourthinouttime = date + " " + fourthinouttime4;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Punch in 2nd time
		Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid,
				password, cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
				bio2finger, employeeuniqueid, locationid, thirdinouttime, thirdmode, photo);

		if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			return;
		}

		// Punch OUT
		Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, fourthinouttime, fourthmode, photo);

		if (fourthpunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

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
						"%s â€“ Employee %s on %s (Multiple Shift Detection = NO) combined shift "
								+ "(IN.%s, OUT.%s, Final Status.%s, OT.%s). "
								+ "Expected First Shift Status = %s, Expected Second Shift Status = %s, Expected OT = %s",
						description, employeeuniqueid, date, inTime, outTime, status, otHours, expectedStatus,
						multipleshiftstatus, totalOTHour);

				// Validate:
				// 1. API returns combined AttnFinalStatus = expectedStatus (first shift)
				// 2. Because second shift should be absent, multipleshiftstatus must be "A"
				// 3. OT hours match expected
				if (expectedStatus.equalsIgnoreCase(status) && "A".equalsIgnoreCase(multipleshiftstatus)
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
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_891_Attendance_Policy_83
	@Test(enabled = true, priority = 65, groups = { "Smoke" })
	public void MPI_891_Attendance_Policy_83()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "Verify that when an employee completes the first shift and a half day of the second shift on the same day, the attendance status for the first shift is â€˜Pâ€™ and for the second shift is â€˜HDâ€™.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,multipleshiftstatus");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String thirdinouttime3 = data.get(i++); // Punch Out time
		String thirdmode = data.get(i++);

		String date = data.get(i++);
		String fourthinouttime4 = data.get(i++);
		String fourthmode = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String updateattendanceendpoint = data.get(i++);
		String multipleshiftstatus = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		String thirdinouttime = date + " " + thirdinouttime3;
		String fourthinouttime = date + " " + fourthinouttime4;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Punch in 2nd time
		Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid,
				password, cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
				bio2finger, employeeuniqueid, locationid, thirdinouttime, thirdmode, photo);

		if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			return;
		}

		// Punch OUT
		Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, fourthinouttime, fourthmode, photo);

		if (fourthpunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			return;
		}
		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				employeeuniqueid, date + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);
		if (validation.statusCode() == 200) {
			int rowCount = validation.jsonPath().getList("$").size();

			if (rowCount > 1) {
				// First shift row
				String firstStatus = validation.jsonPath().getString("[0].AttnFinalStatus");
				String firstIn = validation.jsonPath().getString("[0].InTime");
				String firstOut = validation.jsonPath().getString("[0].OutTime");
				String firstOT = validation.jsonPath().getString("[0].OTHours");
				if (firstOT == null || firstOT.isEmpty() || firstOT.equals("0"))
					firstOT = "00:00";

				// Second shift row
				String secondStatus = validation.jsonPath().getString("[1].AttnFinalStatus");
				String secondIn = validation.jsonPath().getString("[1].InTime");
				String secondOut = validation.jsonPath().getString("[1].OutTime");
				String secondOT = validation.jsonPath().getString("[1].OTHours");
				if (secondOT == null || secondOT.isEmpty() || secondOT.equals("0"))
					secondOT = "00:00";

				String msg = String.format(
						"%s â€“ Employee %s on %s (Multiple Shift Detection = YES, multiple rows) "
								+ "1st Shift (IN.%s, OUT.%s, Status.%s, OT.%s) | "
								+ "2nd Shift (IN.%s, OUT.%s, Status.%s, OT.%s)",
						description, employeeuniqueid, date, firstIn, firstOut, firstStatus, firstOT, secondIn,
						secondOut, secondStatus, secondOT);

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
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}

	// MPI_1190_Normal_Attendance_73
	@Test(enabled = true, priority = 66, groups = { "Smoke" })
	public void MPI_1190_Normal_Attendance_73()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Policy "
				+ "To verify this, ensure that the ‘P’ status is displayed when the employee is present for consecutive days.");

		// Load all data including punch-out time and mode
		ArrayList<String> data = initBase.loadExcelData("Policy_Attendance", currTC,
				"cmpcode,emailid,password,baseuri,loginendpoint,endpointoftransaction,"
						+ "cardnumber,cardtype,deviceuniqueid,bio1finger,bio2finger,employeeuniqueid,"
						+ "locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,date,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,effectivehours");

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
		String bio2finger = data.get(i++);
		String employeeuniqueid = data.get(i++);
		String locationid = data.get(i++);
		String inouttime1 = data.get(i++); // Punch In time
		String mode = data.get(i++); // Punch In mode (e.g., "IN")
		String photo = data.get(i++);
		String secondInOutTime2 = data.get(i++); // Punch Out time
		String secondMode = data.get(i++); // Punch Out mode (e.g., "OUT")

		String thirdinouttime3 = data.get(i++); // Punch Out time
		String thirdmode = data.get(i++);

		String date = data.get(i++);
		String fourthinouttime4 = data.get(i++);
		String fourthmode = data.get(i++);
		String statusEndpoint = data.get(i++);
		String fromDateofuserstatus1 = data.get(i++);
		String toDateofuserstatus2 = data.get(i++);
		String expectedStatus = data.get(i++);
		String description = data.get(i++);
		String totalOTHour = data.get(i++);
		String updateattendanceendpoint = data.get(i++);
		String effectivehours = data.get(i++);

		String fromDateofstatus = date + fromDateofuserstatus1;
		String toDateofstatus = date + toDateofuserstatus2;

		String inouttime = date + " " + inouttime1;
		String secondInOutTime = date + " " + secondInOutTime2;

		String thirdinouttime = date + " " + thirdinouttime3;
		String fourthinouttime = date + " " + fourthinouttime4;

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		LocalDate localDate = LocalDate.parse(date);
		

		// get first day of month
		 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();

		// Punch IN
		Response punchInResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password, cmpcode,
				baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, inouttime, mode, photo);

		if (punchInResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponse.asString());
			return;
		}

		// Punch OUT
		Response punchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, secondInOutTime, secondMode, photo);

		if (punchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
			return;
		}

		// Punch in 2nd time
		Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid,
				password, cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger,
				bio2finger, employeeuniqueid, locationid, thirdinouttime, thirdmode, photo);

		if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
			return;
		}

		// Punch OUT
		Response fourthpunchOutResponse = apiPage.executeSuccessTransaction(baseuri, loginendpoint, emailid, password,
				cmpcode, baseuri, endpointoftransaction, cardnumber, cardtype, deviceuniqueid, bio1finger, bio2finger,
				employeeuniqueid, locationid, fourthinouttime, fourthmode, photo);

		if (fourthpunchOutResponse.getStatusCode() == 200) {
			logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
		} else {
			logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			return;
		}  
		// Trigger attendance update first
					Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, emailid, password, cmpcode,
							baseuri, updateattendanceendpoint, // <-- add endpoint in excel
							employeeuniqueid, firstDayOfMonth + "T00:00:00.000Z");

					if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
						logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
					} else {
						logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
					}
		
		// Get User Status
		Response validation = apiPage.executeGetUserStatus(baseuri, loginendpoint, emailid, password, cmpcode, baseuri,
				statusEndpoint, employeeuniqueid, fromDateofstatus, toDateofstatus);

		if (validation.statusCode() == 200) {
			String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

			// Handle null OTHours â†’ default to 00:00
			String actualOTHours = validation.jsonPath().getString("[0].OTHours");
			actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

			// Handle null EffectiveHours â†’ default to 00:00
			String actualEffectiveHours = validation.jsonPath().getString("[0].TotalEffectiveHours");
			actualEffectiveHours = (actualEffectiveHours == null || actualEffectiveHours.isEmpty()) ? "00:00"
					: actualEffectiveHours;

			// Make sentence using excel inputs
			String finalSentence = String.format(
					"%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s  punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), Total Effective Hours = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					description, employeeuniqueid, date, inouttime1, secondInOutTime2, thirdinouttime, fourthinouttime,
					actualStatus, expectedStatus, actualEffectiveHours, effectivehours, actualOTHours, totalOTHour);

			// Validate both Final Status & OT Hours
			if (expectedStatus.equalsIgnoreCase(actualStatus) && totalOTHour.equalsIgnoreCase(actualOTHours)
					&& effectivehours.equalsIgnoreCase(actualEffectiveHours)) {
				logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
			} else {
				logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
			}
		} else {
			logResults.createLogs("N", "FAIL", "âŒ Failed to fetch attendance. API Response." + validation.asString());
		}

	}
	
	//MPI_1394_Attendance_Tab_07
	@Test(enabled = true, priority = 67, groups = { "Smoke" })
	public void MPI_1394_Attendance_Tab_07()  {
	    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
	    logResults.createExtentReport(currTC);
	    logResults.setScenarioName("Policy " + 
	        "To verify this,Apply leave for an employee and ensure leave is displayed in the calendar "
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
	
	// MPI_1182_OT_All_Reports_14
				@Test(enabled = true, priority = 68, groups = { "Smoke" })
				public void MPI_1182_OT_All_Reports_14()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, configure OT approval as manual in the default attendance policy.");

					ArrayList<String> data = initBase.loadExcelData("OverTime_Reports", currTC,
							"password,captcha,policyname,duration,otmaxminutes");

					
					MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					
					int i = 0;
					String password = data.get(i);
					String captcha = data.get(++i);
					String policyname = data.get(++i);
					String duration = data.get(++i);
					String otmaxminutes = data.get(++i);
					String PolicyNames = "AutoAttenPN" + initBase.executionRunTime;
					
					
					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
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

					if (RolePermissionpage.AttendancePolicy()) {

						logResults.createLogs("Y", "PASS", "AttendancePolicy Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On AttendancePolicy." + RolePermissionpage.getExceptionDesc());
					}

					if (AttendancePolicyPage.AttendancePolicyPageLoaded()) {

						logResults.createLogs("Y", "PASS", "AttendancePolicy Page Loaded Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Attendance Policy Page Is Not Loaded Completely." + RolePermissionpage.getExceptionDesc());
					}
					
					if (AttendancePolicyPage.SearchTextField(policyname)) {

						logResults.createLogs("Y", "PASS", "On SearchTextField Policy Name Inputted Successfully ." + policyname);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Policy Name On Search TextField ."
								+ AttendancePolicyPage.getExceptionDesc());
					}
					

					if (ShiftPolicyPage.EditIcon()) {

						logResults.createLogs("Y", "PASS", "Shift Policy Edit Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ShiftPolicy Edit Button." + ShiftPolicyPage.getExceptionDesc());
					}


					if (AttendancePolicyPage.RegulirizationUpto31days()) {

						logResults.createLogs("Y", "PASS", "Regulirization Upto 31days CheckBox Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regulirization Upto Checkbox ." + AttendancePolicyPage.getExceptionDesc());
					}
					
					if (AttendancePolicyPage.RegulirizationUptoTextField(duration)) {

						logResults.createLogs("Y", "PASS", "Regulirization Upto 31days Inputted  Successfully ." + duration);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting 31 day On Regulirization TextField ." + AttendancePolicyPage.getExceptionDesc());
					}
					if (AttendancePolicyPage.OverTimeEligibilityDropDown()) {

						logResults.createLogs("Y", "PASS", "OverTime Eligibility DropDown Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OverTime Eligibility DropDown ." + AttendancePolicyPage.getExceptionDesc());
					}
					if (AttendancePolicyPage.OTAllowCheckbox()) {

						logResults.createLogs("Y", "PASS", "OT Allow CheckBox Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OT Allow Checkbox ." + AttendancePolicyPage.getExceptionDesc());
					}
					if (AttendancePolicyPage.OTMaxMinutesTextField()) {

						logResults.createLogs("Y", "PASS", "OT MaxMinutes TextField Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On OT MaxMinutes TextField ." + AttendancePolicyPage.getExceptionDesc());
					}
					if (AttendancePolicyPage.OTMaxMinutesTextFieldInputted(otmaxminutes)) {

						logResults.createLogs("Y", "PASS", "OT Max Minutess Inputted  Successfully ." + otmaxminutes);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting OT Max Minutes ." + AttendancePolicyPage.getExceptionDesc());
					}

					if (AttendancePolicyPage.CreatePolicyButton()) {

						logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On CreatePolicyButton ." + AttendancePolicyPage.getExceptionDesc());
					}

					if (AttendancePolicyPage.YesButton()) {

						logResults.createLogs("Y", "PASS", "YesButton Button Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On YesButton ." + AttendancePolicyPage.getExceptionDesc());
					}
					if (AttendancePolicyPage.SearchTextField(PolicyNames)) {

						logResults.createLogs("Y", "PASS", "On SearchTextField Policy Name Inputted Successfully ." + PolicyNames);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Policy Name On Search TextField ."
								+ AttendancePolicyPage.getExceptionDesc());
					}
					

					if (ShiftPolicyPage.EditIcon()) {

						logResults.createLogs("Y", "PASS", "Shift Policy Edit Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ShiftPolicy Edit Button." + ShiftPolicyPage.getExceptionDesc());
					}
					if (AttendancePolicyPage.CreatePolicyButton()) {

						logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On CreatePolicyButton ." + AttendancePolicyPage.getExceptionDesc());
					}

					if (AttendancePolicyPage.YesButton()) {

						logResults.createLogs("Y", "PASS", "YesButton Button Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On YesButton ." + AttendancePolicyPage.getExceptionDesc());
					}
				}

				// MPI_848_WeekOff_Policy_12
				@Test(enabled = true, priority = 69, groups = { "Smoke", "AddMaster" })
				public void MPI_848_WeekOff_Policy_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, create an policy and add emp criteria to the policy and ensure matching criteria employees are assigned to the created policy");

					ArrayList<String> data = initBase.loadExcelData("week_off_policy", currTC,
							"password,captcha,weekoffpolicyname");

					MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
					MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);

					
					int i = 0;
					
					String password = data.get(i++);
					String captcha = data.get(i++);
					
				String	weekoffpolicyname = "Default Weekoff Policy";

					MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);
					MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
					
					
					
					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
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

						logResults.createLogs("Y", "PASS", "Saturday Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On SaturdayButton ." + WeekOffPolicyPage.getExceptionDesc());
					}

					if (WeekOffPolicyPage.FullDayButton()) {

						logResults.createLogs("Y", "PASS", "FullDay Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Full Day ." + WeekOffPolicyPage.getExceptionDesc());
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
	
	
	
				
				// MPI_1542_MeghPi_Shift_32
				@Test(enabled = true, priority = 70, groups = { "Smoke" })
				public void MPI_1542_MeghPi_Shift_32()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, create morning shift and assigned the shift to new shift policy ");

					ArrayList<String> data = initBase.loadExcelData("shift", currTC,
							"shiftname,shiftcode,starttime,endtime,buffertime,fullday,halfday,breakname,breakstarttime,breakendtime,breakbuffertime,password,captcha,policyname");

				
					
					String shiftname = data.get(0);
					String shiftcode = data.get(1) + Pramod.generateRandomNumber(3);
					String starttime = data.get(2);
					String endtime = data.get(3);
					String buffertime = data.get(4);
					String fullday = data.get(5);
					String halfday = data.get(6);
					String breakname = data.get(7) + Pramod.generateRandomAlpha(5);
					String breakstarttime = data.get(8);
					String breakendtime = data.get(9);
					String breakbuffertime = data.get(10);
					String password = data.get(11);
					String captcha = data.get(12);
					String shiftpolicyname  = data.get(13);
					

					MeghShiftPage ShiftPage = new MeghShiftPage(driver);
					MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

					MeghLoginTest meghlogintest = new MeghLoginTest();
				
					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
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

					if (RolePermissionpage.ShiftDropDown()) {

						logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
					}

					if (ShiftPage.ShiftButton()) {

						logResults.createLogs("Y", "PASS", "ShiftButton Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On ShiftButton." + ShiftPage.getExceptionDesc());
					}

					if (ShiftPage.ShiftPageLoaded()) {

						logResults.createLogs("Y", "PASS", "Shift Page Loaded Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL", "Shift Page Isn't Loaded Completely." + ShiftPage.getExceptionDesc());
					}

					if (ShiftPage.AddShiftButton()) {

						logResults.createLogs("Y", "PASS", "CreateShiftButton Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On CreateShiftButton." + ShiftPage.getExceptionDesc());
					}

					if (ShiftPolicyPage.ShiftNameTextField(shiftname)) {

						logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully ." + shiftname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Name." + ShiftPolicyPage.getExceptionDesc());
					}

					if (ShiftPolicyPage.ShiftCodeTextField(shiftcode)) {

						logResults.createLogs("Y", "PASS", "Shift Code Inputted Successfully ." + shiftcode);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Code." + ShiftPolicyPage.getExceptionDesc());
					}

					if (ShiftPolicyPage.ShiftStartTimeClick()) {

						logResults.createLogs("Y", "PASS", "Shift Start Time Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
					}
					

					if (ShiftPolicyPage.ShiftStartTime(starttime)) {

						logResults.createLogs("Y", "PASS", "Shift Start Time Inputted Successfully ." + starttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
					}

					if (ShiftPolicyPage.ShiftEndTimeClick()) {

						logResults.createLogs("Y", "PASS", "Shift Start Time Inputted Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
					}

					if (ShiftPolicyPage.ShiftEndTime(endtime)) {

						logResults.createLogs("Y", "PASS", "Shift End Time Inputted Successfully ." + endtime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift End Time." + ShiftPolicyPage.getExceptionDesc());
					}
					

					if (ShiftPolicyPage.BufferAllTimeClick()) {

						logResults.createLogs("Y", "PASS", "Shift Buffer Time Inputted Successfully ." + buffertime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Buffer Time." + ShiftPolicyPage.getExceptionDesc());
					}

					if (ShiftPolicyPage.BufferAllTime(buffertime)) {

						logResults.createLogs("Y", "PASS", "Shift Buffer Time Inputted Successfully ." + buffertime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Buffer Time." + ShiftPolicyPage.getExceptionDesc());
					}
					

					if (ShiftPolicyPage.FullDayTimeClick()) {

						logResults.createLogs("Y", "PASS", "Shift Full Day Time Inputted Successfully ." + fullday);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Full Day Time." + ShiftPolicyPage.getExceptionDesc());
					}

					if (ShiftPolicyPage.FullDayTime(fullday)) {

						logResults.createLogs("Y", "PASS", "Shift Full Day Time Inputted Successfully ." + fullday);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Full Day Time." + ShiftPolicyPage.getExceptionDesc());
					}

					

					if (ShiftPolicyPage.HalfDayTimeClick()) {

						logResults.createLogs("Y", "PASS", "Shift Half Day Time Inputted Successfully ." + halfday);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Half Day Time." + ShiftPolicyPage.getExceptionDesc());
					}

					if (ShiftPolicyPage.HalfDayTime(halfday)) {

						logResults.createLogs("Y", "PASS", "Shift Half Day Time Inputted Successfully ." + halfday);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Half Day Time." + ShiftPolicyPage.getExceptionDesc());
					}

					if (ShiftPage.AddBreakButton()) {

						logResults.createLogs("Y", "PASS", "AddBreakButton Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On AddBreakButton." + ShiftPage.getExceptionDesc());
					}

					if (ShiftPage.BreakNameTextField(breakname)) {

						logResults.createLogs("Y", "PASS", "Break Name Inputted Successfully ." + breakname);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Break Name." + ShiftPage.getExceptionDesc());
					}

					if (ShiftPage.BreakStartTimeClick()) {

						logResults.createLogs("Y", "PASS", "BreakStartTime Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On BreakStartTime." + ShiftPage.getExceptionDesc());
					}

					if (ShiftPage.BreakStartTime(breakstarttime)) {

						logResults.createLogs("Y", "PASS", "BreakStartTime Inputted Successfully ." + breakstarttime);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting BreakStartTime." + ShiftPage.getExceptionDesc());
					}

					if (ShiftPage.BreakEndTimeClick()) {

						logResults.createLogs("Y", "PASS", "BreakEndTime Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On BreakEndTime." + ShiftPage.getExceptionDesc());
					}

					if (ShiftPage.BreakEndTime(breakendtime)) {

						logResults.createLogs("Y", "PASS", "BreakEndTime Clicked Successfully ." + breakendtime);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On BreakEndTime." + ShiftPage.getExceptionDesc());
					}
					
					if (ShiftPage.BreakNameTextField(breakname)) {

						logResults.createLogs("Y", "PASS", "Break Name Inputted Successfully ." + breakname);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Break Name." + ShiftPage.getExceptionDesc());
					}

					if (ShiftPage.BreakBufferTimeClick()) {

						logResults.createLogs("Y", "PASS", "BreakBufferTime Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On BreakBufferTime." + ShiftPage.getExceptionDesc());
					}

					if (ShiftPage.BreakBufferTime(breakbuffertime)) {

						logResults.createLogs("Y", "PASS", "BreakBufferTime Inputted Successfully ." + breakbuffertime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On BreakBufferTime." + ShiftPage.getExceptionDesc());
					}

					if (ShiftPolicyPage.CreateShiftSaveButton()) {

						logResults.createLogs("Y", "PASS", "Create Shift Save Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On CreateShift Save Button." + ShiftPolicyPage.getExceptionDesc());
					}
					

					if (ShiftPage.ShiftPolicyTab()) {

						logResults.createLogs("Y", "PASS", "ShiftPolicyTab Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ShiftPolicyTab." + ShiftPage.getExceptionDesc());
					}
					

					if (ShiftPolicyPage.AddShiftPolicyButton()) {

						logResults.createLogs("Y", "PASS", "AddShiftPolicyButton Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On AddShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
					}
					

					if (ShiftPolicyPage.PolicyNameTextField(shiftpolicyname)) {

						logResults.createLogs("Y", "PASS", "Shift PolicyName Inputted Successfully ." + shiftpolicyname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
					}

					if (ShiftPolicyPage.AddShiftButton()) {

						logResults.createLogs("Y", "PASS", "AddShiftButton Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On AddShiftButton." + ShiftPolicyPage.getExceptionDesc());
					}

					if (ShiftPage.AllShiftsInShiftPolicy(shiftname)) {

						logResults.createLogs("Y", "PASS", "Created Shift Selected Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Created Shift." + ShiftPage.getExceptionDesc());
					}

					if (ShiftPolicyPage.AddShiftSaveButton()) {

						logResults.createLogs("Y", "PASS", "AddShiftSaveButton Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On AddShiftSaveButton." + ShiftPolicyPage.getExceptionDesc());
					}

					
					if (ShiftPolicyPage.CreatePolicySave()) {

						logResults.createLogs("Y", "PASS", "CreatePolicySave Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On CreatePolicySave Button." + ShiftPolicyPage.getExceptionDesc());
					}

					
					
					if (ShiftPage.NoOptionSelected()) {
						logResults.createLogs("Y", "PASS", "No Button Button Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On No Button ." + ShiftPage.getExceptionDesc());
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

}// FP6 IAINS014X22C20433 DEFAULT Attendance Device Mantra 0 0
