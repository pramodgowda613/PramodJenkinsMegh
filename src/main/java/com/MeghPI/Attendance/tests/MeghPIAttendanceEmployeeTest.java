package com.MeghPI.Attendance.tests;


import java.time.LocalDate;
import java.util.ArrayList;
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
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationReportsPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationRequestPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import com.MeghPI.Attendance.pages.MeghPIAttendanceEmployeePage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIAttendanceEmployeeTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	

	private String cmpcode = "";
	private String Emailid = "";
	private String designationname = "";
	private String officename = "";
private String EmpFirstName = "";

    private String AdminFirstName ="";
	  private String entityname = "";
	
	
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
		designationname = "AutoDESN" + initBase.executionRunTime;
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		EmpFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpFirstName", "");
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");
	}
	
	
	// MPI_1546_Attendance_Employee_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_1546_Attendance_Employee_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a new employee, mark one day as Present, and ensure the Present count increases correctly and the Absent count adjusts accordingly for the current month.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("Attendance_EmployeeTab", currTC,
				"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,captcha,empstatusonadmin,presentcount,othours,leavecount");


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
	      
	        String captcha = data.get(i++);
	        String empstatusonadmin  = data.get(i++);
	        String presentcount  = data.get(i++);
	        String othours  = data.get(i++);
	        
	        String leavecount  = data.get(i++);
	     
	        int holidayint = 0;
	         String absentcount= "";
	        
	        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 

	        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

	         Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

	        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
	        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");
	        
	      
		   
		    
			String inouttime = monthFirstWorkingDate + " " + inouttime1;
			String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
		
			
			
			 
			 Map<String, String> datas = Pramod.getLastMonthWorkingDays();

			 int workingDays = Integer.parseInt(datas.get("workingDays"));
			int presentinint = Integer.parseInt(presentcount);
			
		

			
			 MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage  = new MeghLoginPage(driver);
MeghLoginTest meghlogintest = new MeghLoginTest();
MeghLeavePage meghleavepage = new MeghLeavePage(driver);

		 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		 
		 
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
		if (RolePermissionpage.HrAccountAttendanceEmpTab()) {

			logResults.createLogs("Y", "PASS", "Employee Attendance Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EMp Attendance Tab." + RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
					+ meghleavepage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.SearchTextFieldOnEmpTabOnAdmin(firstname)) {

			logResults.createLogs("Y", "PASS", "Regularization Rejected Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Regularization Rejected Emp Name." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.validateAttendanceStatus(empid, firstname, monthFirstWorkingDate, empstatusonadmin )) {

			logResults.createLogs("Y", "PASS", "Emp Present Attendance Validated Successfully." + empid + " " + monthFirstWorkingDate + " " + empstatusonadmin  );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status With Date." + meghregularizationrequestpage.getExceptionDesc());
		}
		
		if (meghpiattendanceemployeepage.getHolidayCountTillToday()) {
			  holidayint = meghpiattendanceemployeepage.holidayCountTillToday;
			logResults.createLogs("Y", "PASS", "Fetch the Current Month Holiday Count Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Regularization Rejected Emp Name." + meghpiattendanceemployeepage.getExceptionDesc());
		}
		int absentCount = Pramod.getAbsentCount(workingDays, presentinint, holidayint );
		String absentCountStr = String.valueOf(absentCount);
		
		
		if (meghpiattendanceemployeepage.getAbsentCountFromRow()) {
		absentcount =	MeghPIAttendanceEmployeePage.lastAbsentCount;
			logResults.createLogs("Y", "PASS", "Emp  Absent Count Fetched Successfully." + empid + " absent count  " + absentCountStr + " And Present count" + presentcount  );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While  Absent Count ." + meghregularizationrequestpage.getExceptionDesc());
		}
		
		if (meghpiattendanceemployeepage.validateHeaderAttendanceValues(empid, firstname, presentcount , absentcount, leavecount, othours )) {

			logResults.createLogs("Y", "PASS", "Emp Present Count, Absent Count And OT Hours Count Validated Successfully." + empid + " absent count  " + absentCountStr + " And Present count" + presentcount  );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Present Count, Absent Count And OT Hours Count." + meghregularizationrequestpage.getExceptionDesc());
		}
		
	
	}
	
	
	// MPI_1547_Attendance_Employee_02
			@Test(enabled = true, priority = 2, groups = { "Smoke" })
			public void MPI_1547_Attendance_Employee_02()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, log in as the employee and check the attendance status for the date on which the employee is marked Present in the My Attendance tab.");

				ArrayList<String> data = initBase.loadExcelData("Attendance_EmployeeTab", currTC,
						"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,shiftstatus,empid");

				
				String password = data.get(0);
				String captcha = data.get(1);
				String regularizationstatus = data.get(2);
				String regularizationintime = data.get(3);
				String regularizationouttime = data.get(4);
				String shiftstatus = data.get(5);
				String empid = data.get(6);
				
				
			
				 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
					MeghLeavePage meghleavepage = new MeghLeavePage(driver);

			        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				MeghLoginPage meghloginpage  = new MeghLoginPage(driver);
				String uidate = Pramod.convertToUIFormat(monthFirstWorkingDate);
			
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
        MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
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
		if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
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
		if (meghpiattendanceemployeepage.EmpAttendanceTabSearchStatusSearchOption()) {

			logResults.createLogs("Y", "PASS", "Status Search Option Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Status Search Option." + meghpiattendanceemployeepage.getExceptionDesc());
		}
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		
		if (meghpiattendanceemployeepage.EmpAttendanceTabSearchTextField(regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "Employee Attendance Status Inputted Successfully." + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Attendance Status In Search TextField." + meghpiattendanceemployeepage.getExceptionDesc());
		}
		
		
		if (meghpiattendanceemployeepage.validateAttendanceRow(monthFirstWorkingDate, regularizationintime, regularizationouttime , shiftstatus, regularizationstatus )) {

			logResults.createLogs("Y", "PASS", "Emp Present Count, Absent Count And OT Hours Count Validated Successfully." + empid + " intime  " + regularizationintime + " And out time" + regularizationouttime + " shift is " + shiftstatus + "status is" + regularizationstatus );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Present Count, Absent Count And OT Hours Count." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghpiattendanceemployeepage.EmpAttendanceTabSearchTextField(uidate)) {

			logResults.createLogs("Y", "PASS", "Employee Attendance Date Inputted Successfully." + uidate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Emp Attendance Date  In Search TextField." + meghpiattendanceemployeepage.getExceptionDesc());
		}
		
		
		if (meghpiattendanceemployeepage.EmpAttendanceShiftRow(shiftstatus)) {

			logResults.createLogs("Y", "PASS", "Emp Assigned Shift Validated Successfully." + empid + " shift is " + shiftstatus );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Assigned Shift." + meghregularizationrequestpage.getExceptionDesc());
		}
		
		
			}
		
		
			// MPI_1548_Attendance_Employee_03
						@Test(enabled = true, priority = 3, groups = { "Smoke" })
						public void MPI_1548_Attendance_Employee_03()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, perform OT as an employee and validate that the in time, out time, working hours, and OT hours are displayed correctly.");

							ArrayList<String> data = initBase.loadExcelData("Attendance_EmployeeTab", currTC,
									"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,shiftstatus,empid,policyname,duration,otmaxminutes,gracehours,totaldays,firstname,workinghours,othours,baseuri,loginendpoint,updateattendanceendpoint");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String regularizationstatus = data.get(2);
							String regularizationintime = data.get(3);
							String regularizationouttime = data.get(4);
							String shiftstatus = data.get(5);
							String empid = data.get(6);
							String policyname = data.get(7);
							String duration = data.get(8);
							String otmaxminutes = data.get(9);
							String gracehours = data.get(10);
							String totaldays = data.get(11);
							String firstname = data.get(12);
							String workinghours = data.get(13);
							String othours = data.get(14);
							String baseuri = data.get(15);
							String loginendpoint = data.get(16);
							String updateattendanceendpoint = data.get(17);
							
							
						
							 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

						        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
						      LocalDate localDate = LocalDate.parse(monthFirstWorkingDate);
								 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
								String uidate = Pramod.convertToUIFormat(monthFirstWorkingDate);
							
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage meghloginpage  = new MeghLoginPage(driver);
							MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

							MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
							MeghLoginTest meghlogintest = new MeghLoginTest();
					MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
			        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
						MeghLeavePage meghleavepage = new MeghLeavePage(driver);

				
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
					if (AttendancePolicyPage.EnableGracePeriodButton()) {

						logResults.createLogs("Y", "PASS", "EnableGracePeriod Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EnableGracePeriod Button ." + AttendancePolicyPage.getExceptionDesc());
					}

					if (AttendancePolicyPage.EarlyClockOut()) {

						logResults.createLogs("Y", "PASS", "EarlyClockOut CheckBox Clicked  Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyClockOut CheckBox ." + AttendancePolicyPage.getExceptionDesc());
					}

					if (AttendancePolicyPage.GraceHoursClick()) {

						logResults.createLogs("Y", "PASS", "Clicked On GraceHours  Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On GraceHours TextField ." + AttendancePolicyPage.getExceptionDesc());
					}

					if (AttendancePolicyPage.GraceHoursInput(gracehours)) {

						logResults.createLogs("Y", "PASS", " GraceHours Inputted  Successfully ." + gracehours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting GraceHours ." + AttendancePolicyPage.getExceptionDesc());
					}
					
					if (AttendancePolicyPage.GraceTimePerMonth(totaldays)) {

						logResults.createLogs("Y", "PASS", "Grace Hours Per Month Selected  Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  GraceHours Per Month ." + AttendancePolicyPage.getExceptionDesc());
					}
					if (AttendancePolicyPage.GraceTimeApplyButton()) {

						logResults.createLogs("Y", "PASS", "Clicked On GraceTimeApply Button  Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On GraceTimeApply Button ." + AttendancePolicyPage.getExceptionDesc());
					}

					if (AttendancePolicyPage.EnableGracePeriodCheckBox()) {

						logResults.createLogs("Y", "PASS", "EnableGracePeriod CheckBox Clicked  Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EnableGracePeriod CheckBox ." + AttendancePolicyPage.getExceptionDesc());
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
						Thread.sleep(8000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
					 if (RolePermissionpage.EmployeeAttendanceButton()) {

				        	logResults.createLogs("Y", "PASS", "Employee Attendance Module Clicked Successfully.");
				        } else {
				        	logResults.createLogs("Y", "FAIL",
				        			"Error While Clicking On Employee Attendane Module." + RolePermissionpage.getExceptionDesc());
				        }
				        if (meghpiovertimepage.OverTimeTabOnAdmin()) {

							logResults.createLogs("Y", "PASS", "OverTime Tab Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Tab." + meghpiovertimepage.getExceptionDesc());
						}
				        if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

							logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
									+ meghleavepage.getExceptionDesc());
						}
				        
				        if (meghpiovertimepage.OverTimeTabSearchTextFieldOnAdmin(firstname)) {

							logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpiovertimepage.getExceptionDesc());
						}
				        if (meghpiovertimepage.OverTimeEmpName(firstname,monthFirstWorkingDate)) {

							logResults.createLogs("Y", "PASS", "Emp Approve Button Clicked Successfully." + firstname + " " + monthFirstWorkingDate);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Same Emp Approve Button." + meghpiovertimepage.getExceptionDesc());
						}
				        if (meghpiovertimepage.OverTimeEmpApprveCmts(firstname)) {

							logResults.createLogs("Y", "PASS", "Approve Comments Inputted Successfully." + firstname);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Inputting Approve Comments." + meghpiovertimepage.getExceptionDesc());
						}
				        if (meghpiovertimepage.OverTimeEmpApproveConfirmButton()) {

							logResults.createLogs("Y", "PASS", "Approve Confirm Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Approve Confirm Button." + meghpiovertimepage.getExceptionDesc());
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
					if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghpiattendanceemployeepage.EmpAttendanceTabSearchTextField(uidate)) {

						logResults.createLogs("Y", "PASS", "Employee Attendance Date Inputted Successfully." + uidate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Attendance Date In Search TextField." + meghpiattendanceemployeepage.getExceptionDesc());
					}
					
					
					if (meghpiattendanceemployeepage.validateAttendanceRowForOT(monthFirstWorkingDate, regularizationintime, regularizationouttime , shiftstatus, regularizationstatus, workinghours, othours )) {

						logResults.createLogs("Y", "PASS", "Emp Present Status,  OT Hours Count, Working Hours Count, InTime, OutTime Validated Successfully." + empid + " intime  " + regularizationintime + " And out time" + regularizationouttime + " shift is " + shiftstatus + "status is" + regularizationstatus + "working hours is " + workinghours + " OT is" + othours  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Attendance Present Status,  OT Hours Count, Working Hours Count, InTime, OutTime." + meghregularizationrequestpage.getExceptionDesc());
					}
						}
		
		
						// MPI_1549_Attendance_Employee_04
						@Test(enabled = true, priority = 4, groups = { "Smoke" })
						public void MPI_1549_Attendance_Employee_04()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, login as employee for absent date click on 3dots and apply leave and ensure leave request is created.");

							ArrayList<String> data = initBase.loadExcelData("Attendance_EmployeeTab", currTC,
									"password,captcha,leavename,LeaveReason,leaveduration,empid");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String leavename = data.get(2);
							String LeaveReason = data.get(3);
							String leaveduration = data.get(4);
							String empid = data.get(5);
							
							
						
							Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
							String Regulizationdate  =	dateDetails.get("month2WorkingDate");
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage meghloginpage  = new MeghLoginPage(driver);
						
			        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
					MeghLeavePage meghleavepage = new MeghLeavePage(driver);
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
					if (meghleavepage.MonthFilterContains(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghpiattendanceemployeepage.click3DotsAndApplyLeave(Regulizationdate )) {

						logResults.createLogs("Y", "PASS", "Employee Attendance Row 3Dots Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Record 3dots Action Button." + meghpiattendanceemployeepage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveTypeSelectionDropDownOfLeaveForMe()) {

						logResults.createLogs("Y", "PASS", "  LeaveType DropDown OnAdmin Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking  LeaveType DropDown OnAdmin."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveTypeNameTextFieldOfLeaveForMe(leavename)) {

						logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully ." + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Name."
								+ meghleavepage.getExceptionDesc());
					}
					
					
					if (meghleavepage.LeaveTypeSelectedOfLeaveForMe()) {

						logResults.createLogs("Y", "PASS", "  Leave Type Selected  Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type From DropDown."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.FromDateClickedLeaveForMe()) {

						logResults.createLogs("Y", "PASS", " Clicked On FromDate TextField In Admin  Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On From Date TextField."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.FromDateSelectedLeaveForMe(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully ." + Regulizationdate);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting  From Date in TextField."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.FromLeaveDurationSelectedOfLeaveForMe(leaveduration)) {

						logResults.createLogs("Y", "PASS", " Clicked On FromLeaveDuration Dropdown In Admin  Successfully ." + leaveduration);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On FromLeave Duration Dropdown."
								+ meghleavepage.getExceptionDesc());
					}
					
					
					
					if (meghleavepage.ToDateClickedLeaveForMe()) {

						logResults.createLogs("Y", "PASS", " Clicked On ToDate TextField In Admin  Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On To Date TextField."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.ToDateSelectedLeaveForMe(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully ." + Regulizationdate);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting  TO Date in TextField."
								+ meghleavepage.getExceptionDesc());
					}
					
					
					
					if (meghleavepage.ToLeaveDurationSelectedOfLeaveForMe(leaveduration)) {

						logResults.createLogs("Y", "PASS", " Selected  ToLeaveDuration From Dropdown In Admin  Successfully ." + leaveduration);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting  ToLeave Duration From Dropdown."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveReasonOfLeaveForMe(LeaveReason)) {

						logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + LeaveReason);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Reason."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.ApplyButtonOfLeaveForMe()) {

						logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully ." );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button."
								+ meghleavepage.getExceptionDesc());
					}
		}
		
		
		
						// MPI_1550_Attendance_Employee_05
						@Test(enabled = true, priority = 5, groups = { "Smoke" })
						public void MPI_1550_Attendance_Employee_05()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, as an employee raise a regularization request using the 3-dots action and ensure the request is created..");

							ArrayList<String> data = initBase.loadExcelData("Attendance_EmployeeTab", currTC,
									"password,captcha,regularizationintime,regularizationouttime,empid,regularizationreason");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String regularizationintime = data.get(2);
							String regularizationouttime = data.get(3);
							String empid = data.get(4);
							String regularizationreason = data.get(5);
							
							
						
							//Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();

							//String thirdworkingday  = thirdrdday.get("month3WorkingDate");
							
							 Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
						        String monthFirstWorkingDate = dateDetails.get("month1WorkingDate");
							
							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage meghloginpage  = new MeghLoginPage(driver);
							MeghLeavePage meghleavepage = new MeghLeavePage(driver);

			        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
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
					if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghpiattendanceemployeepage.click3DotsAndRegularization(monthFirstWorkingDate )) {

						logResults.createLogs("Y", "PASS", "Employee Attendance Row 3Dots Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Record 3dots Action Button." + meghpiattendanceemployeepage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

						logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting From Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmpRegularizationInTime()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmpRegularizationInTimeInputted(regularizationintime)) {

						logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + regularizationintime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting In Time." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmpRegularizationOutDate()) {

						logResults.createLogs("Y", "PASS", "To Date TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On To Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.EmpRegularizationOutTime()) {

						logResults.createLogs("Y", "PASS", "Out Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Out Time." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(regularizationouttime)) {

						logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + regularizationouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Out TIme." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(regularizationouttime)) {

						logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + regularizationouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Out TIme." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.EmpRegularizationType()) {

						logResults.createLogs("Y", "PASS", "Regularization Type Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Regularization Type." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.EmpRegularizationReason(regularizationreason)) {

						logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regularizationreason);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Regularization Reason." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Apply Save Button." + meghpiregularizationReportsPage.getExceptionDesc());
					}  
						}
		
		
						// MPI_1552_Attendance_Employee_07
						@Test(enabled = true, priority = 6, groups = { "Smoke" })
						public void MPI_1552_Attendance_Employee_07()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, apply a leave as an employee and ensure the applied leave count is reflected in the Monthly Summary and the correct leave status is displayed in the employee attendance table.");

							ArrayList<String> data = initBase.loadExcelData("Attendance_EmployeeTab", currTC,
									"password,captcha,leavename,empid,leavecount");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String leavename = data.get(2);
							String empid = data.get(3);
							String leavecount = data.get(4);
							
							
						
							Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
							String Regulizationdate  =	dateDetails.get("month2WorkingDate");
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage meghloginpage  = new MeghLoginPage(driver);
							MeghLeavePage meghleavepage = new MeghLeavePage(driver);
							MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

			        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
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
					if (RolePermissionpage.HrAccountAttendanceEmpTab()) {

						logResults.createLogs("Y", "PASS", "Employee Attendance Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EMp Attendance Tab." + RolePermissionpage.getExceptionDesc());
					}
					
					if (RolePermissionpage.LeaveButton()) {

						logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
					}
					
					if (RolePermissionpage.LeaveRequestsTab()) {

						logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
								+ RolePermissionpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

						logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully ." );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.PendingLeaveCount()) {

						logResults.createLogs("Y", "PASS", "Monthly Pending Leave Count Clicked  Successfully ." );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Pending Leave Count."
								+ meghleavepage.getExceptionDesc());
					}
					
				
					if (meghleavepage.approveLeaveByDateAndEmp(empid, Regulizationdate)) {

						logResults.createLogs("Y", "PASS", " Leave Approved   Successfully ." + Regulizationdate);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Aprroving Leave."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveApproveButton()) {

						logResults.createLogs("Y", "PASS", " Leave Approve  Button Clicked  Successfully ." );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Approve Button."
								+ meghleavepage.getExceptionDesc());
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
				
					if (RolePermissionpage.EmpAttendanceTab()) {

						logResults.createLogs("Y", "PASS", "Employee Can Access His My Attendance Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying My Attendance Tab." + RolePermissionpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummary()) {

						logResults.createLogs("Y", "PASS", "Employee Attendance Summary Clicked  Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Emp Attendance Summary." + meghpiattendanceemployeepage.getExceptionDesc());
					}

					if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummaryLeaveCount(leavecount)) {

						logResults.createLogs("Y", "PASS", "Employee Attendance Summary Leave Count Validated  Successfully." + leavecount );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Attendance Summary Leave Count." + meghpiattendanceemployeepage.getExceptionDesc());
					}
				
						}
		
						// MPI_1553_Attendance_Employee_08
						@Test(enabled = true, priority = 7, groups = { "Smoke" })
						public void MPI_1553_Attendance_Employee_08()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the regularization count on monthly summary.");

							ArrayList<String> data = initBase.loadExcelData("Attendance_EmployeeTab", currTC,
									"password,captcha,firstname,empid,leavecount");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String firstname = data.get(2);
							String empid = data.get(3);
							String leavecount = data.get(4);
						
							
							
						
							//Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();
							
						//	String thirdday = thirdrdday.get("month3WorkingDate");
							Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
					        String monthFirstWorkingDate = dateDetails.get("month1WorkingDate");
							String UIdate = Pramod.convertToUIFormat(monthFirstWorkingDate);
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage meghloginpage  = new MeghLoginPage(driver);
							MeghLeavePage meghleavepage = new MeghLeavePage(driver);

							MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);

			        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
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
					

					if (RolePermissionpage.HrAccountRegulationRequestTab()) {

						logResults.createLogs("Y", "PASS", "Regularization Rquest Tab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Rquest In Attendance Module."
										+ RolePermissionpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Regularization Emp Name." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.AdminApprovedToRegulirization(UIdate,firstname)) {

						logResults.createLogs("Y", "PASS", "Admin Approved To Regulirization Request Successfully." + firstname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Approving Regularization Request." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.ApproveButton()) {

						logResults.createLogs("Y", "PASS", " Approved Button Clicked Successfully." + firstname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approve Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
				
					if (RolePermissionpage.EmpAttendanceTab()) {

						logResults.createLogs("Y", "PASS", "Employee Can Access His My Attendance Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying My Attendance Tab." + RolePermissionpage.getExceptionDesc());
					}
					if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + monthFirstWorkingDate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummary()) {

						logResults.createLogs("Y", "PASS", "Employee Attendance Summary Clicked  Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Emp Attendance Summary." + meghpiattendanceemployeepage.getExceptionDesc());
					}

					if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummaryRegularizationCount(leavecount)) {

						logResults.createLogs("Y", "PASS", "Employee Attendance Summary Regularization Count Validated  Successfully." + leavecount );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Attendance Summary Regularization Count." + meghpiattendanceemployeepage.getExceptionDesc());
					}

						}
		
		
						// MPI_1554_Attendance_Employee_09
						@Test(enabled = true, priority = 8, groups = { "Smoke" })
						public void MPI_1554_Attendance_Employee_09()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, for a policy configured with 2/month grace hours, punch out early two times and ensure the grace usage count is displayed correctly.");

							 
							 // Load all data including punch-out time and mode
						    ArrayList<String> data = initBase.loadExcelData("Attendance_EmployeeTab", currTC,
						            "password,baseuri,loginendpoint,endpointoftransaction," +
						            "cardnumber,cardtype,bio1finger,bio2finger," +
						            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha,leavecount");

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
					        String leavecount = data.get(i++);
					        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
					        
					        Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();

							String fourthworkingday  = thirdrdday.get("month4WorkingDate");
							String fifthworkingday  = thirdrdday.get("month5WorkingDate");

							LocalDate localDate = LocalDate.parse(fourthworkingday);
							 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
							
							String inouttime = fourthworkingday + " " + inouttime1;
							String secondInOutTime = fourthworkingday + " " + secondInOutTime2;
						    
							String inouttimesecondday = fifthworkingday + " " + inouttime1;
							String secondInOutTimesecondday = fifthworkingday + " " + secondInOutTime2;
							MeghLoginTest meghlogintest = new MeghLoginTest();

					        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
						    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage meghloginpage  = new MeghLoginPage(driver);
							MeghLeavePage meghleavepage = new MeghLeavePage(driver);

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
						    
						    
						    // Punch IN
						    Response punchInResponses = apiPage.executeSuccessTransaction(
						            baseuri, loginendpoint,
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, empid,
						            locationid, inouttimesecondday, mode, photo
						    );

						    if (punchInResponses.getStatusCode() == 200) {
						        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttimesecondday);
						    } else {
						        logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponses.asString());
						        return;
						    }

						    // Punch OUT
						    Response punchInResponsess = apiPage.executeSuccessTransaction(
						            baseuri, loginendpoint,
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, empid,
						            locationid, secondInOutTimesecondday, secondMode, photo
						    );

						    if (punchInResponsess.getStatusCode() == 200) {
					            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTimesecondday);
					        } else {
					            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchInResponsess.asString());
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
							if (meghleavepage.MonthFilterContains(fourthworkingday)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + fourthworkingday );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}
							
							if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummary()) {

								logResults.createLogs("Y", "PASS", "Employee Attendance Summary Clicked  Successfully." );
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Emp Attendance Summary." + meghpiattendanceemployeepage.getExceptionDesc());
							}

							if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummaryGraceTimeAllowCount(leavecount)) {

								logResults.createLogs("Y", "PASS", "Employee Attendance Summary Grace Time Allowed Count Validated  Successfully." + leavecount );
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Summary Grace Time Allowed Count." + meghpiattendanceemployeepage.getExceptionDesc());
							}
							if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummaryGraceTimeTakenCount(leavecount)) {

								logResults.createLogs("Y", "PASS", "Employee Attendance Summary Grace Time Taken Count Validated  Successfully." + leavecount );
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Summary Grace Time Taken Count." + meghpiattendanceemployeepage.getExceptionDesc());
							}
						

						}	
						
						
						
						
						// MPI_1555_Attendance_Employee_10
						@Test(enabled = true, priority = 9, groups = { "Smoke" })
						public void MPI_1555_Attendance_Employee_10()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, for a policy configured with 2/month grace hours, punch out early for the third time and ensure the grace usage count is displayed correctly, and the attendance status for the third early-out date is shown as Half Day.");

							 
							 // Load all data including punch-out time and mode
						    ArrayList<String> data = initBase.loadExcelData("Attendance_EmployeeTab", currTC,
						            "password,baseuri,loginendpoint,endpointoftransaction," +
						            "cardnumber,cardtype,bio1finger,bio2finger," +
						            "locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,captcha,leavecount,intimetwo,outtimetwo");

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
					        String leavecount = data.get(i++);
					        String intimetwo = data.get(i++);
					        String outtimetwo = data.get(i++);
					      
					        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
					        
					        Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();

							String sixthworkingday  = thirdrdday.get("month6WorkingDate");
						String seventhworkingday  = thirdrdday.get("month7WorkingDate");

							LocalDate localDate = LocalDate.parse(sixthworkingday);
							 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
							
							String inouttime = sixthworkingday + " " + inouttime1;
							String secondInOutTime = sixthworkingday + " " + secondInOutTime2;
						    
							String inouttimesecondday = seventhworkingday + " " + intimetwo;
							String secondInOutTimesecondday = seventhworkingday + " " + outtimetwo;
							
							MeghLeavePage meghleavepage = new MeghLeavePage(driver);
					        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
						    MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage meghloginpage  = new MeghLoginPage(driver);
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
						    
						    
						    // Punch IN
						    Response punchInResponses = apiPage.executeSuccessTransaction(
						            baseuri, loginendpoint,
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, empid,
						            locationid, inouttimesecondday, mode, photo
						    );

						    if (punchInResponses.getStatusCode() == 200) {
						        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttimesecondday);
						    } else {
						        logResults.createLogs("N", "FAIL", "Punch IN failed." + punchInResponses.asString());
						        return;
						    }

						    // Punch OUT
						    Response punchInResponsess = apiPage.executeSuccessTransaction(
						            baseuri, loginendpoint,
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, empid,
						            locationid, secondInOutTimesecondday, secondMode, photo
						    );

						    if (punchInResponsess.getStatusCode() == 200) {
					            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTimesecondday);
					        } else {
					            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchInResponsess.asString());
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
							if (meghleavepage.MonthFilterContains(sixthworkingday)) {

								logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + sixthworkingday );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
										+ meghleavepage.getExceptionDesc());
							}
							if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummary()) {

								logResults.createLogs("Y", "PASS", "Employee Attendance Summary Clicked  Successfully." );
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Emp Attendance Summary." + meghpiattendanceemployeepage.getExceptionDesc());
							}

							if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummaryGraceTimeAllowCount(leavecount)) {

								logResults.createLogs("Y", "PASS", "Employee Attendance Summary Grace Time Allowed Count Validated  Successfully." + leavecount );
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Summary Grace Time Allowed Count." + meghpiattendanceemployeepage.getExceptionDesc());
							}
							if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummaryGraceTimeTakenCount(leavecount)) {

								logResults.createLogs("Y", "PASS", "Employee Attendance Summary Grace Time Taken Count Validated  Successfully." + leavecount );
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Validating Emp Attendance Summary Grace Time Taken Count." + meghpiattendanceemployeepage.getExceptionDesc());
							}
						}		
						
						// MPI_1557_Attendance_Employee_12
						@Test(enabled = true, priority = 10, groups = { "Smoke" })
						public void MPI_1557_Attendance_Employee_12() {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of search feature");
							logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_1547_Attendance_Employee_02 TestCase.");
						}
						
						
						
						// MPI_1551_Attendance_Employee_06
						@Test(enabled = true, priority = 11, groups = { "Smoke" })
						public void MPI_1551_Attendance_Employee_06()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, log in as the employee and check the Monthly Summary to ensure the counts for Present Days, Absent Days, and total OT hours are displayed correctly..");

							ArrayList<String> data = initBase.loadExcelData("Attendance_EmployeeTab", currTC,
									"password,captcha,empid,othours,presentcount");

							
							String password = data.get(0);
							String captcha = data.get(1);
							String empid = data.get(2);
							String othours = data.get(3);
							String presentcount  = data.get(4);
						int workingdaycount = 0;
							String absentCountStr = "";
							
							  Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();

								String sixthworkingday  = thirdrdday.get("month6WorkingDate");
							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage meghloginpage  = new MeghLoginPage(driver);
							MeghLeavePage meghleavepage = new MeghLeavePage(driver);

							
			        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
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
					if (meghleavepage.MonthFilterContains(sixthworkingday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + sixthworkingday );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummary()) {

						logResults.createLogs("Y", "PASS", "Employee Attendance Summary Clicked  Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Emp Attendance Summary." + meghpiattendanceemployeepage.getExceptionDesc());
					}

					if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummaryPresentDaysCount(presentcount)) {

						logResults.createLogs("Y", "PASS", "Employee Attendance  Present Days Count Validated  Successfully." + presentcount  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Attendance Summary Present Days Count." + meghpiattendanceemployeepage.getExceptionDesc());
					}
					if (meghpiattendanceemployeepage.countAbsentStatus()) {
						workingdaycount = meghpiattendanceemployeepage.absentCount;
						logResults.createLogs("Y", "PASS", "Employee Attendance  Absent Days Count Fetched  Successfully."  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Fetching Emp Attendance Absent Days Count." + meghpiattendanceemployeepage.getExceptionDesc());
					}
					
	
					
					absentCountStr =Integer.toString(workingdaycount);
					
					if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummaryAbsentDaysCount(absentCountStr)) {

						logResults.createLogs("Y", "PASS", "Employee Attendance  Absent Days Count Validated  Successfully." + "Calculated absent days count is " + absentCountStr + " fetched data is " + workingdaycount  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Attendance Summary Absent Days Count." + meghpiattendanceemployeepage.getExceptionDesc());
					}
					if (meghpiattendanceemployeepage.EmpAttendanceTabMonthlySummaryOTHoursCount(othours)) {

						logResults.createLogs("Y", "PASS", "Employee Attendance  OT Hours Count Validated  Successfully." + othours  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Validating Emp Attendance Summary OT Hours Count." + meghpiattendanceemployeepage.getExceptionDesc());
					}

						}	
						
						
						// MPI_1562_Attendance_Admin_Employee_05
						@Test(enabled = true, priority = 12, groups = { "Smoke" })
						public void MPI_1562_Attendance_Admin_Employee_05()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the filter functionality by selecting a specific office, department, and team, and ensure that only the employees matching the selected criteria are displayed.");

							ArrayList<String> data = initBase.loadExcelData("Attendance_EmployeeTab", currTC,
									"password,captcha");

							String password = data.get(0);
							String captcha = data.get(1);
							
							  Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();

								String sixthworkingday  = thirdrdday.get("month6WorkingDate");
							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage meghloginpage  = new MeghLoginPage(driver);
							MeghLeavePage meghleavepage = new MeghLeavePage(driver);
						
							
					        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 

							MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
							
							MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

						
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
					if (meghleavepage.MonthFilterContains(sixthworkingday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + sixthworkingday );
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
						logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." );
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
					if (meghpiattendanceemployeepage.EmpNameDisplayed(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Filter Applied Emp Record Displayed Successfully." + EmpFirstName);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Filter Applied Emp Record." + meghpiattendanceemployeepage.getExceptionDesc());
					}		
						}				
						
						
						
						// MPI_1558_Attendance_Admin_Employee_01
						@Test(enabled = true, priority = 13, groups = { "Smoke" })
						public void MPI_1558_Attendance_Admin_Employee_01()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the search textfield functionality for each search option.");

							ArrayList<String> data = initBase.loadExcelData("Attendance_EmployeeTab", currTC,
									"password,captcha,firstname,othours");

							String password = data.get(0);
							String captcha = data.get(1);
							String firstname = data.get(2);
							String othours = data.get(3);
							
							 Map<String, String> thirdrdday = Pramod.getLastMonthWorkingDatesDetails();

								String sixthworkingday  = thirdrdday.get("month6WorkingDate");
							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghLoginPage meghloginpage  = new MeghLoginPage(driver);
							MeghLeavePage meghleavepage = new MeghLeavePage(driver);
						
							
					        MeghPIAttendanceEmployeePage meghpiattendanceemployeepage = new MeghPIAttendanceEmployeePage(driver); 
							 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

							
							MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

						
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
					if (meghleavepage.MonthFilterContains(sixthworkingday)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + sixthworkingday );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}	
					
					if (meghregularizationrequestpage.SearchTextFieldOnEmpTabOnAdmin(firstname)) {

						logResults.createLogs("Y", "PASS", " Emp Name Inputted Successfully." + firstname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name." + meghregularizationrequestpage.getExceptionDesc());
					}	
					if (meghpiattendanceemployeepage.EmpNameDisplayed(firstname)) {

						logResults.createLogs("Y", "PASS", "Inputted Emp Record Displayed Successfully." + firstname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Emp Record." + meghpiattendanceemployeepage.getExceptionDesc());
					}	
				
					if (meghpiattendanceemployeepage.OTHoursRowValidation(othours)) {

						logResults.createLogs("Y", "PASS", "OT Hours Of Emp Record Displayed Successfully." + othours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying OT Hours Of Emp Record." + meghpiattendanceemployeepage.getExceptionDesc());
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
