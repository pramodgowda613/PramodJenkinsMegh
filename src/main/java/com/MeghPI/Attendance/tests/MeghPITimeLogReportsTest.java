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
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeRequestPage;
import com.MeghPI.Attendance.pages.MeghPITimeLogReportsPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPITimeLogReportsTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	private String cmpcode = "";
	private String Emailid = "";
	private String entityname = "";
	private String officename = "";
	private String departmentname = "";
	private String teamname = "";
	private String designationname= "";
    private String AdminFirstName ="";

	
	@Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
	//	driver = loadDriver.getDriver(device, hubnodeip);

		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

	@Parameters({ "device", "hubnodeip" })
	@BeforeClass(alwaysRun = true)
	void runOnce(int device, @Optional String hubnodeip) {
		logResults.createReport(device);
		logResults.setTestMethodErrorCount(0);
		driver = loadDriver.getDriver(device, hubnodeip);
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		
		
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		entityname = "AutoEN" + initBase.executionRunTime;
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");

	}
	
	
	// MPI_1039_Punch_statistics_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_1039_Punch_statistics_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionalities of search feature by selecting each search option");

		

		 // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
				"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,captcha,regularizationstatus,policyname,othours,duration,pin");


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
	        
	        String regularizationstatus  = data.get(i++);
	        String policyname  = data.get(i++);
	        String othours  = data.get(i++);
	        String duration  = data.get(i++);
	        String pin = data.get(i++);
	     
	        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

	        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

	        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
	        String monthName = dateInfo.get("monthName");
			String year = dateInfo.get("year");
	      
		   
		    
			String inouttime = monthFirstWorkingDate + " " + inouttime1;
			String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
		
			
			// get first day of month
			LocalDate localDate = LocalDate.parse(monthFirstWorkingDate);
			 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();

				MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
				MeghLoginTest meghlogintest = new MeghLoginTest();
		        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

                 MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		 MeghLoginPage meghloginpage = new MeghLoginPage(driver);

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
		

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		
    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
                    MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
            		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}
		 if (EmployeePage.DirectoryButton()) {
				logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Directery Button." + EmployeePage.getExceptionDesc());
			}

			if (EmployeePage.DirectoryPageLoaded()) {
				logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
			}

			if (EmployeePage.EmployeeTab()) {
				logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On EmployeeTab." + EmployeePage.getExceptionDesc());
			}

			if (EmployeePage.DirectoryPageLoaded()) {
				logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Directory Page Isn't Loaded Completely." + EmployeePage.getExceptionDesc());
			}

		
			if (EmployeePage.SearchTextField(firstname)) {
				logResults.createLogs("Y", "PASS",
						"Clicked On Search Text Field And Pass The Created Employee LastName Successfully." + firstname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Search Text Field." + EmployeePage.getExceptionDesc());
			}
			

			if (EmployeePage.SearchResult()) {
				logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully." + firstname);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Displaying Employee." + EmployeePage.getExceptionDesc());
			}

			if (EmployeePage.DotsMenu()) {
				logResults.createLogs("Y", "PASS", "Clicked On 3Dots Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error while Clicking On 3Dots." + EmployeePage.getExceptionDesc());
			}

			if (DepartmentPage.ManageProfileButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton ."
						+ DepartmentPage.getExceptionDesc());
			}
			

			if (DepartmentPage.EditIcon()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Manage Profile EditIcon.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Edit Icon Of Manage Profile ."
						+ DepartmentPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.DeptDropdown(departmentname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + departmentname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Dept ." + AttendancePolicyPage.getExceptionDesc());
			}
	

			if (AttendancePolicyPage.DesignationDropdown(designationname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Designation From Dropdown." + designationname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Designation From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
			}
	

			if (AttendancePolicyPage.TeamDropDown(teamname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Team From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
			}
		

			if (RolePermissionpage.PinTextField(pin)) {
				logResults.createLogs("Y", "PASS", "Successfully Enter The Pin." + pin);
			} else {
				logResults.createLogs("Y", "FAIL", "Error while Inputting Pin ." + RolePermissionpage.getExceptionDesc());
			}
		

			if (RolePermissionpage.SaveChanges()) {
				logResults.createLogs("Y", "PASS", "SaveChanges Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SaveChanges Button." + RolePermissionpage.getExceptionDesc());
			}


			if (RolePermissionpage.AutoSetPolicy()) {
				logResults.createLogs("Y", "PASS", "AutoSetPolicy Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On AutoSetPolicy Button." + RolePermissionpage.getExceptionDesc());
			}
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}

		
		if (meghpitimelog.TimeLogReportButton()) {

			logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
		}

		if (meghpitimelog.PunchStatisticsTab()) {

			logResults.createLogs("Y", "PASS", "PunchStatisticsTab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PunchStatisticsTab." + meghpitimelog.getExceptionDesc());
		}
		if (meghpiattendancereport.LocationDropdown()) {

			logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

			logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.LocationDropdownSearchResult()) {

			logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpitimelog.FilterButtonPunchStatistics()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
		}

		if (meghpiattendancereport.YearDropDown(year)) {

			logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.MonthDropDown(monthName)) {

			logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiattendancereport.FilterSaveButton()) {

			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
		}
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitimelog.EmpIDCheckBoxStatistics()) {

			logResults.createLogs("Y", "PASS", "EmpID CheckBOx Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EmpID CheckBox." + meghpitimelog.getExceptionDesc());
		}
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitimelog.PunchStatisticsSearchField(empid)) {

			logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + empid);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting  EmpID." + meghpitimelog.getExceptionDesc());
		}

		if (meghpitimelog.EmpIDRecordInResult(empid)) {

			logResults.createLogs("Y", "PASS", "Inputted Emp ID Data Displayed Successfully." + empid);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying  Inputted Emp ID Row." + meghpitimelog.getExceptionDesc());
		}
	
	}
	
	// MPI_1040_Punch_statistics_02
		@Test(enabled = true, priority = 2, groups = { "Smoke" })
		public void MPI_1040_Punch_statistics_02()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

			ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
					"subject,ccmail,date");

			
			
			String subject = data.get(0);
			String ccmail = data.get(1);	
			Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			String monthName = dateInfo.get("monthName");
			String year = dateInfo.get("year");

			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		
	    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

			
	                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


	            		if (meghloginpage.MainLandingPage()) {
	            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
	            		} else {
	            			logResults.createLogs("Y", "FAIL",
	            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
	            		}
			if (RolePermissionpage.ReprtButton()) {

				logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
			}

			
			if (meghpitimelog.TimeLogReportButton()) {

				logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
			}

			if (meghpitimelog.PunchStatisticsTab()) {

				logResults.createLogs("Y", "PASS", "PunchStatisticsTab Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On PunchStatisticsTab." + meghpitimelog.getExceptionDesc());
			}
			if (meghpitimelog.FilterButtonPunchStatistics()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
			}

			if (meghpiattendancereport.YearDropDown(year)) {

				logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.MonthDropDown(monthName)) {

				logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiattendancereport.FilterSaveButton()) {

				logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpitimelog.EmailButtonPunchStatistics()) {

				logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On MailReportButton." + meghpitimelog.getExceptionDesc());
			}
			if (meghpiattendancereport.SubjectTextField(subject)) {

				logResults.createLogs("Y", "PASS", "Mail Subject Inputted Successfully." + subject);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Mail Subject." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiattendancereport.CCTextField(ccmail)) {

				logResults.createLogs("Y", "PASS", "Mail CC Inputted Successfully." + ccmail);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Mail CC." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiattendancereport.BCCTextField(ccmail)) {

				logResults.createLogs("Y", "PASS", "Mail BCC Inputted Successfully." + ccmail);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Mail BCC." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiattendancereport.FrequencySelection()) {

				logResults.createLogs("Y", "PASS", "Frequency Selected Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Mail sending Frequency." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiattendancereport.MailSendSaveButton()) {

				logResults.createLogs("Y", "PASS", "MailSendSaveButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On MailSendSaveButton." + meghpiattendancereport.getExceptionDesc());
			}
			
		
		}
	
		// MPI_1042_Punch_statistics_04
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_1042_Punch_statistics_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

					
				
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

					String monthName = dateInfo.get("monthName");
					String year = dateInfo.get("year");
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.PunchStatisticsTab()) {

						logResults.createLogs("Y", "PASS", "PunchStatisticsTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On PunchStatisticsTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.FilterButtonPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
				

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.FirstRecordPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
	
				// MPI_1043_Punch_statistics_05
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_1043_Punch_statistics_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");



					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

					String monthName = dateInfo.get("monthName");
					String year = dateInfo.get("year");
			
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			  MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
					
			  MeghLoginPage meghloginpage = new MeghLoginPage(driver);


				if (meghloginpage.MainLandingPage()) {
					logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.PunchStatisticsTab()) {

						logResults.createLogs("Y", "PASS", "PunchStatisticsTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On PunchStatisticsTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.FilterButtonPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DesignationDropDown(designationname)) {

						logResults.createLogs("Y", "PASS", "Designation Selected Successfully." + designationname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Designation Name." + meghpiattendancereport.getExceptionDesc());
					}
			
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.FirstRecordPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				
				}
	
				// MPI_1044_Punch_statistics_06
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_1044_Punch_statistics_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

			
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

					String monthName = dateInfo.get("monthName");
					String year = dateInfo.get("year");
			
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			  MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
					
			  MeghLoginPage meghloginpage = new MeghLoginPage(driver);


				if (meghloginpage.MainLandingPage()) {
					logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.PunchStatisticsTab()) {

						logResults.createLogs("Y", "PASS", "PunchStatisticsTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On PunchStatisticsTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.FilterButtonPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.EntityTypeDropDown(entityname)) {

						logResults.createLogs("Y", "PASS", "EntityType Selected Successfully." + entityname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EntityType Name." + meghpiattendancereport.getExceptionDesc());
					}
			

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.FirstRecordPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				
				}
	
				// MPI_1045_Punch_statistics_07
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_1045_Punch_statistics_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year");

					
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

					String monthName = dateInfo.get("monthName");
					String year = dateInfo.get("year");
			
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			  MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
					
			  MeghLoginPage meghloginpage = new MeghLoginPage(driver);


				if (meghloginpage.MainLandingPage()) {
					logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.PunchStatisticsTab()) {

						logResults.createLogs("Y", "PASS", "PunchStatisticsTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On PunchStatisticsTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename  + " " + year);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.FilterButtonPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.FirstRecordPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				
				}		
	
				// MPI_1046_Punch_statistics_08
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_1046_Punch_statistics_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to dateÂ ");

					

					
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
					String month1WorkingDate  = dateInfo.get("month1WorkingDate");

					String monthName = dateInfo.get("monthName");
					String year = dateInfo.get("year");
			
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
		        	MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
					
			  MeghLoginPage meghloginpage = new MeghLoginPage(driver);


				if (meghloginpage.MainLandingPage()) {
					logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.PunchStatisticsTab()) {

						logResults.createLogs("Y", "PASS", "PunchStatisticsTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On PunchStatisticsTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.FilterButtonPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month1WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month1WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(month1WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month1WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.FirstRecordPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				
				}	
	
				// MPI_1049_Punch_statistics_11
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_1049_Punch_statistics_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, as a employee do early punch in and early punch out for 2 days and ensure \"early in count\" and \"early out count\" displayed for the employee");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,earlyincount,earlyoutcount,empid,firstname");

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

					    String thirdinouttime3 = data.get(i++); // Punch Out time
					    String thirdmode = data.get(i++);    
				
					    
					    String fourthinouttime4 = data.get(i++);
					    String fourthmode = data.get(i++);
					    String statusEndpoint    = data.get(i++);
				        String fromDateofuserstatus1          = data.get(i++);
				        String toDateofuserstatus2            = data.get(i++);
				        String expectedStatus    = data.get(i++);
				        String description = data.get(i++);
				        String totalOTHour = data.get(i++);
				        String updateattendanceendpoint = data.get(i++);
				      
				        String earlyincount = data.get(i++);
				        String earlyoutcount = data.get(i++);
				        String empid = data.get(i++);
				        String firstname = data.get(i++);
				        
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
				        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
				        
				        
				        
				        
				        String fromDateofstatus = monthFirstWorkingDate + fromDateofuserstatus1;
				        String toDateofstatus = monthFirstWorkingDate + toDateofuserstatus2;
					   
					    
						String inouttime = monthFirstWorkingDate + " " + inouttime1;
						String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
						
						String thirdinouttime =  monthFirstWorkingDate + " " + thirdinouttime3;
						String fourthinouttime = monthFirstWorkingDate + " " + fourthinouttime4;
						
						
		
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

					 
					
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
					    
					    // Punch in 2nd time
					    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
					            baseuri, loginendpoint,
					            Emailid, password, cmpcode,
					            baseuri, endpointoftransaction,
					            cardnumber, cardtype, deviceuniqueid,
					            bio1finger, bio2finger, empid,
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
					            Emailid, password, cmpcode,
					            baseuri, endpointoftransaction,
					            cardnumber, cardtype, deviceuniqueid,
					            bio1finger, bio2finger, empid,
					            locationid, fourthinouttime, fourthmode, photo
					    );

					    if (fourthpunchOutResponse.getStatusCode() == 200) {
				            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
				        } else {
				            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
				            return;
				        } try {
							Thread.sleep(5000);
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
							Thread.sleep(23000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 // Get User Status
				        Response validation = apiPage.executeGetUserStatus(
				                baseuri, loginendpoint,
				                Emailid, password, cmpcode,
				                baseuri, statusEndpoint,
				                empid, fromDateofstatus, toDateofstatus
				        );

				        if (validation.statusCode() == 200) {
				            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

				            // Handle null OTHours â†’ default to 00:00
				            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
				            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

				            // Make sentence using excel inputs
				            String finalSentence = String.format(
				                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
				                description, empid, monthFirstWorkingDate, inouttime1, secondInOutTime2,
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
				    
			    
			  MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
					
			  MeghLoginPage meghloginpage = new MeghLoginPage(driver);


				if (meghloginpage.MainLandingPage()) {
					logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.PunchStatisticsTab()) {

						logResults.createLogs("Y", "PASS", "PunchStatisticsTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On PunchStatisticsTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpitimelog.FilterButtonPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.PunchStatisticsSearchField(firstname)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + firstname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  EmpID." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyInRecordPunchStatistics(earlyincount)) {

						logResults.createLogs("Y", "PASS", "Early In Total Count Displayed Successfully." + earlyincount);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Early In Count Data." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutRecordPunchStatistics(earlyoutcount)) {

						logResults.createLogs("Y", "PASS", "Early Out Total Count Displayed Successfully." + earlyoutcount);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Early Out Count Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1050_Punch_statistics_12
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_1050_Punch_statistics_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, as a employee do late punch in and late punch out for 2 days and ensure \"late in count\" and \"late out count\" displayed for the employee");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,lateincount,lateoutcount,empid,firstname");

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

					    String thirdinouttime3 = data.get(i++); // Punch Out time
					    String thirdmode = data.get(i++);    
				
					    
					    String fourthinouttime4 = data.get(i++);
					    String fourthmode = data.get(i++);
					    String statusEndpoint    = data.get(i++);
				        String fromDateofuserstatus1          = data.get(i++);
				        String toDateofuserstatus2            = data.get(i++);
				        String expectedStatus    = data.get(i++);
				        String description = data.get(i++);
				        String totalOTHour = data.get(i++);
				        String updateattendanceendpoint = data.get(i++);
				      
				        String lateincount = data.get(i++);
				        String lateoutcount = data.get(i++);
				        String empid = data.get(i++);
				        String firstname = data.get(i++);
				        
				        
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
				        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthsecondWorkingDate = dateInfo.get("month2WorkingDate");
				        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
				        
				        
				        
				        
				        
				        String fromDateofstatus = monthsecondWorkingDate + fromDateofuserstatus1;
				        String toDateofstatus = monthsecondWorkingDate + toDateofuserstatus2;
					   
					    
						String inouttime = monthsecondWorkingDate + " " + inouttime1;
						String secondInOutTime = monthsecondWorkingDate + " " + secondInOutTime2;
						
						String thirdinouttime =  monthsecondWorkingDate + " " + thirdinouttime3;
						String fourthinouttime = monthsecondWorkingDate + " " + fourthinouttime4;
						
						
			
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

					 
					
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
					    
					    // Punch in 2nd time
					    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
					            baseuri, loginendpoint,
					            Emailid, password, cmpcode,
					            baseuri, endpointoftransaction,
					            cardnumber, cardtype, deviceuniqueid,
					            bio1finger, bio2finger, empid,
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
					            Emailid, password, cmpcode,
					            baseuri, endpointoftransaction,
					            cardnumber, cardtype, deviceuniqueid,
					            bio1finger, bio2finger, empid,
					            locationid, fourthinouttime, fourthmode, photo
					    );

					    if (fourthpunchOutResponse.getStatusCode() == 200) {
				            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
				        } else {
				            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
				            return;
				        } try {
							Thread.sleep(10000);
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
							Thread.sleep(27000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 // Get User Status
				        Response validation = apiPage.executeGetUserStatus(
				                baseuri, loginendpoint,
				                Emailid, password, cmpcode,
				                baseuri, statusEndpoint,
				                empid, fromDateofstatus, toDateofstatus
				        );

				        if (validation.statusCode() == 200) {
				            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

				            // Handle null OTHours â†’ default to 00:00
				            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
				            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

				            // Make sentence using excel inputs
				            String finalSentence = String.format(
				                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
				                description, empid, monthsecondWorkingDate, inouttime1, fourthinouttime4,
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
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    
			  MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
					
			  MeghLoginPage meghloginpage = new MeghLoginPage(driver);


				if (meghloginpage.MainLandingPage()) {
					logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.PunchStatisticsTab()) {

						logResults.createLogs("Y", "PASS", "PunchStatisticsTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On PunchStatisticsTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.FilterButtonPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.PunchStatisticsSearchField(firstname)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + firstname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  EmpID." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateInRecordPunchStatistics(lateincount)) {

						logResults.createLogs("Y", "PASS", "late In Total Count Displayed Successfully." + lateincount);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Late In Count Data." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateOutRecordPunchStatistics(lateoutcount)) {

						logResults.createLogs("Y", "PASS", "Late Out Total Count Displayed Successfully." + lateoutcount);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Late Out Count Data." + meghpitimelog.getExceptionDesc());
					}
				}
			
				// MPI_1051_Punch_statistics_13
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_1051_Punch_statistics_13()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, ensure only approved OT are displaying for the employeeÂ ");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,otcount,empid,firstname");

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

					    String thirdinouttime3 = data.get(i++); // Punch Out time
					    String thirdmode = data.get(i++);    
				
					    
					    String fourthinouttime4 = data.get(i++);
					    String fourthmode = data.get(i++);
					    String statusEndpoint    = data.get(i++);
				        String fromDateofuserstatus1          = data.get(i++);
				        String toDateofuserstatus2            = data.get(i++);
				        String expectedStatus    = data.get(i++);
				        String description = data.get(i++);
				        String totalOTHour = data.get(i++);
				        String updateattendanceendpoint = data.get(i++);
				      
				        String otcount = data.get(i++);
				        String empid = data.get(i++);
				        String firstname = data.get(i++);
				        
				        
				        
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
				        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monththirdWorkingDate = dateInfo.get("month3WorkingDate");
				        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
				        
				        
				        
				        
				        String fromDateofstatus = monththirdWorkingDate + fromDateofuserstatus1;
				        String toDateofstatus = monththirdWorkingDate + toDateofuserstatus2;
					   
					    
						String inouttime = monththirdWorkingDate + " " + inouttime1;
						String secondInOutTime = monththirdWorkingDate + " " + secondInOutTime2;
						
						String thirdinouttime =  monththirdWorkingDate + " " + thirdinouttime3;
						String fourthinouttime = monththirdWorkingDate + " " + fourthinouttime4;
						
						
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

					 
					
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
					    
					    // Punch in 2nd time
					    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
					            baseuri, loginendpoint,
					            Emailid, password, cmpcode,
					            baseuri, endpointoftransaction,
					            cardnumber, cardtype, deviceuniqueid,
					            bio1finger, bio2finger, empid,
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
					            Emailid, password, cmpcode,
					            baseuri, endpointoftransaction,
					            cardnumber, cardtype, deviceuniqueid,
					            bio1finger, bio2finger, empid,
					            locationid, fourthinouttime, fourthmode, photo
					    );
					    if (fourthpunchOutResponse.getStatusCode() == 200) {
				            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
				        } else {
				            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
				            return;
				        } try {
							Thread.sleep(10000);
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
					 // Get User Status
				        Response validation = apiPage.executeGetUserStatus(
				                baseuri, loginendpoint,
				                Emailid, password, cmpcode,
				                baseuri, statusEndpoint,
				                empid, fromDateofstatus, toDateofstatus
				        );

				        if (validation.statusCode() == 200) {
				            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

				            // Handle null OTHours â†’ default to 00:00
				            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
				            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

				            // Make sentence using excel inputs
				            String finalSentence = String.format(
				                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
				                description, empid, monththirdWorkingDate, inouttime1, fourthinouttime4,
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
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    
			  MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
					
			  MeghLoginPage meghloginpage = new MeghLoginPage(driver);


				if (meghloginpage.MainLandingPage()) {
					logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
				}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.PunchStatisticsTab()) {

						logResults.createLogs("Y", "PASS", "PunchStatisticsTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On PunchStatisticsTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.FilterButtonPunchStatistics()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.PunchStatisticsSearchField(firstname)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + firstname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  EmpID." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.OTCountRecordPunchStatistics(otcount)) {

						logResults.createLogs("Y", "PASS", "OT Total Count Displayed Successfully." + otcount);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  OT Count Data." + meghpitimelog.getExceptionDesc());
					}
				}
	
				// MPI_1052_LateIn_01
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_1052_LateIn_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature using each search option");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"inouttime,totalhours,lateinhours,shift,firstname");

					
					
					String inouttime = data.get(0);
					String totalhours = data.get(1);
					String lateinhours = data.get(2);
					String shift = data.get(3);
					String firstname = data.get(4);
					
					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthsecondWorkingDate = dateInfo.get("month2WorkingDate");

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
				        String formattedDate = Pramod.convertToUIFormat(monthsecondWorkingDate);
			
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateInTab()) {

						logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabInTimeCheckBox()) {

						logResults.createLogs("Y", "PASS", " InTime CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On In Time CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabSearchField(formattedDate)) {

						logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + formattedDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.LateInTabDateSearchResult(monthsecondWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Inputted Date Attendance Data Displayed Successfully." + monthsecondWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Date Data." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabSearchField(inouttime)) {

						logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  In Time." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.LateInTabInTimeSearchResult(inouttime)) {

						logResults.createLogs("Y", "PASS", "Inputted Late In Time Attendance Data Displayed Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Late In Time Data." + meghpitimelog.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabInTimeCheckBox()) {

						logResults.createLogs("Y", "PASS", " InTime CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On In Time CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabTotalHourCheckBox()) {

						logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabLateInHoursCheckBox()) {

						logResults.createLogs("Y", "PASS", " In Hours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On In Hours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabSearchField(totalhours)) {

						logResults.createLogs("Y", "PASS", "Totalhours Inputted Successfully." + totalhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Totalhours." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.LateInTabTotalHoursSearchResult(totalhours)) {

						logResults.createLogs("Y", "PASS", "Inputted Total Hours Attendance Data Displayed Successfully." + totalhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Total Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabSearchField(lateinhours)) {

						logResults.createLogs("Y", "PASS", "Late In Hours Inputted Successfully." + lateinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Late In Hours." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.LateInTabLateInHoursSearchResult(lateinhours)) {

						logResults.createLogs("Y", "PASS", "Inputted Late In Hours Attendance Data Displayed Successfully." + lateinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Late In Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabTotalHourCheckBox()) {

						logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabLateInHoursCheckBox()) {

						logResults.createLogs("Y", "PASS", " In Hours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On In Hours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabShiftCheckBox()) {

						logResults.createLogs("Y", "PASS", " Shift CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabSearchField(shift)) {

						logResults.createLogs("Y", "PASS", "Shift Inputted Successfully." + shift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Shift." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.LateInTabShiftSearchResult(shift)) {

						logResults.createLogs("Y", "PASS", "Inputted Shift Attendance Data Displayed Successfully." + shift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Shift Attendance Data." + meghpitimelog.getExceptionDesc());
					}
				}
	
				// MPI_1053_LateIn_02
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_1053_LateIn_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"subject,ccmail");

					
				
					String subject = data.get(0);
					String ccmail = data.get(1);	
					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
			

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateInTab()) {

						logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabMailButton()) {

						logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailReportButton." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.SubjectTextField(subject)) {

						logResults.createLogs("Y", "PASS", "Mail Subject Inputted Successfully." + subject);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail Subject." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.CCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail CC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail CC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.BCCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail BCC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail BCC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FrequencySelection()) {

						logResults.createLogs("Y", "PASS", "Frequency Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Mail sending Frequency." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MailSendSaveButton()) {

						logResults.createLogs("Y", "PASS", "MailSendSaveButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailSendSaveButton." + meghpiattendancereport.getExceptionDesc());
					}
				}
				
				// MPI_1055_LateIn_04
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_1055_LateIn_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

					

					
				
					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
			
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateInTab()) {

						logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabTeamSearchResult(teamname)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + teamname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1056_LateIn_05
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_1056_LateIn_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");


					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
			
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateInTab()) {

						logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DesignationDropDown(designationname)) {

						logResults.createLogs("Y", "PASS", "Designation Selected Successfully." + designationname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Designation Name." + meghpiattendancereport.getExceptionDesc());
					}
			
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabDesignationSearchResult(designationname)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + designationname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}		
				
				// MPI_1057_LateIn_06
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_1057_LateIn_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

					

					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
			
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateInTab()) {

						logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					
					if (meghpiattendancereport.EntityTypeDropDown(entityname)) {

						logResults.createLogs("Y", "PASS", "EntityType Selected Successfully." + entityname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EntityType Name." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1058_LateIn_07
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
				public void MPI_1058_LateIn_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year");

					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
			
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateInTab()) {

						logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabAttenDateSearchResult(monthName, year)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1059_LateIn_08
				@Test(enabled = true, priority = 17, groups = { "Smoke" })
				public void MPI_1059_LateIn_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to dateÂ ");

					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthsecondWorkingDate = dateInfo.get("month2WorkingDate");

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
				        String formattedDate = Pramod.convertToUIFormat(monthsecondWorkingDate);
			
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateInTab()) {

						logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(monthsecondWorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + monthsecondWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(monthsecondWorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + monthsecondWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabDateSearchResult(monthsecondWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1062_LateIn_11
				@Test(enabled = true, priority = 18, groups = { "Smoke" })
				public void MPI_1062_LateIn_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, perform a late punch-in and ensure that the â€œLate Inâ€ records are displayed, and the total â€œLate In Hoursâ€ for the day is accurate.");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"lateinhours");

					
					
					String lateinhours =  data.get(0);
					
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthsecondWorkingDate = dateInfo.get("month2WorkingDate");

			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
			        String formattedDate = Pramod.convertToUIFormat(monthsecondWorkingDate);
					
			
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateInTab()) {

						logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabInTimeCheckBox()) {

						logResults.createLogs("Y", "PASS", " InTime CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On In Time CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabSearchField(formattedDate)) {

						logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + formattedDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
					}
				
					if (meghpitimelog.LateInTabLateInHoursSearchResult(lateinhours)) {

						logResults.createLogs("Y", "PASS", "Inputted Late In Hours Attendance Data Displayed Successfully." + lateinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Late In Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1063_LateIn_12
				@Test(enabled = true, priority = 19, groups = { "Smoke" })
				public void MPI_1063_LateIn_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, ensure that the In Time and Total Hours are displayed accurately.");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"totalhours,inouttime");

					
					
					String totalhours =  data.get(0);
					String inouttime =  data.get(1);
					
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthsecondWorkingDate = dateInfo.get("month2WorkingDate");

			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
			        String formattedDate = Pramod.convertToUIFormat(monthsecondWorkingDate);
					
			
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateInTab()) {

						logResults.createLogs("Y", "PASS", "LateInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabInTimeCheckBox()) {

						logResults.createLogs("Y", "PASS", " InTime CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On In Time CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabSearchField(formattedDate)) {

						logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + formattedDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
					}
				
					if (meghpitimelog.LateInTabTotalHoursSearchResult(totalhours)) {

						logResults.createLogs("Y", "PASS", "Inputted Total Hours Attendance Data Displayed Successfully." + totalhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Total Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabInTimeSearchResult(inouttime)) {

						logResults.createLogs("Y", "PASS", "Inputted Late In Time Attendance Data Displayed Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Late In Time Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				//late out
				// MPI_1064_LateOut_01
				@Test(enabled = true, priority = 20, groups = { "Smoke" })
				public void MPI_1064_LateOut_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature using each search option");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"fourthinouttime,totalhours,lateinhours,shift");

					
				
					String fourthinouttime = data.get(0);
					String totalhours = data.get(1);
					String lateinhours = data.get(2);
					String shift = data.get(3);
					
					  Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthsecondWorkingDate = dateInfo.get("month2WorkingDate");
				        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
				        String formattedDate = Pramod.convertToUIFormat(monthsecondWorkingDate);
			
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateOutTab()) {

						logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabOutTimeCheckBox()) {

						logResults.createLogs("Y", "PASS", " OUt Time CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Out Time CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateOutTabSearchTextfield(formattedDate)) {

						logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + formattedDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.LateOutTabDateSearchResult(monthsecondWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Inputted Date Attendance Data Displayed Successfully." + monthsecondWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Date Data." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabSearchTextfield(fourthinouttime)) {

						logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + fourthinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  In Time." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.LateOutTabOutTimeSearchResult(fourthinouttime)) {

						logResults.createLogs("Y", "PASS", "Inputted Late OUT Time Attendance Data Displayed Successfully." + fourthinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Late Out Time Data." + meghpitimelog.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabOutTimeCheckBox()) {

						logResults.createLogs("Y", "PASS", " OUt Time CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Out Time CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabTotalHourCheckBox()) {

						logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabLatehoursCheckbox()) {

						logResults.createLogs("Y", "PASS", " out Hours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Out Hours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabSearchTextfield(totalhours)) {

						logResults.createLogs("Y", "PASS", "Totalhours Inputted Successfully." + totalhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Totalhours." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.LateOutTabTotalhoursSearchResult(totalhours)) {

						logResults.createLogs("Y", "PASS", "Inputted Total Hours Attendance Data Displayed Successfully." + totalhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Total Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabSearchTextfield(lateinhours)) {

						logResults.createLogs("Y", "PASS", "Late Out Hours Inputted Successfully." + lateinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Late Out Hours." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.LateOutTabLateOuthoursSearchResult(lateinhours)) {

						logResults.createLogs("Y", "PASS", "Inputted Late Out Hours Attendance Data Displayed Successfully." + lateinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Late Out Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabTotalHourCheckBox()) {

						logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabLatehoursCheckbox()) {

						logResults.createLogs("Y", "PASS", " Out Hours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Out Hours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabShiftCheckBox()) {

						logResults.createLogs("Y", "PASS", " Shift CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabSearchTextfield(shift)) {

						logResults.createLogs("Y", "PASS", "Shift Inputted Successfully." + shift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Shift." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.LateOutTabShiftSearchResult(shift)) {

						logResults.createLogs("Y", "PASS", "Inputted Shift Attendance Data Displayed Successfully." + shift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Shift Attendance Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1065_LateOut_02
				@Test(enabled = true, priority = 21, groups = { "Smoke" })
				public void MPI_1065_LateOut_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"subject,ccmail,date");

					
					String subject = data.get(0);
					String ccmail = data.get(1);	
					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			           			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateOutTab()) {

						logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabEmailButton()) {

						logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailReportButton." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.SubjectTextField(subject)) {

						logResults.createLogs("Y", "PASS", "Mail Subject Inputted Successfully." + subject);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail Subject." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.CCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail CC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail CC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.BCCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail BCC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail BCC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FrequencySelection()) {

						logResults.createLogs("Y", "PASS", "Frequency Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Mail sending Frequency." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MailSendSaveButton()) {

						logResults.createLogs("Y", "PASS", "MailSendSaveButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailSendSaveButton." + meghpiattendancereport.getExceptionDesc());
					}
				}
				
				// MPI_1067_LateOut_04
				@Test(enabled = true, priority = 22, groups = { "Smoke" })
				public void MPI_1067_LateOut_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateOutTab()) {

						logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.LateOutTabTeamSearchResult(teamname)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + teamname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1068_LateOut_05
				@Test(enabled = true, priority = 23, groups = { "Smoke" })
				public void MPI_1068_LateOut_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateOutTab()) {

						logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DesignationDropDown(designationname)) {

						logResults.createLogs("Y", "PASS", "Designation Selected Successfully." + designationname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Designation Name." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.LateOutTabDesignationSearchResult(designationname)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + designationname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1069_LateOut_06
				@Test(enabled = true, priority = 24, groups = { "Smoke" })
				public void MPI_1069_LateOut_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateOutTab()) {

						logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.EntityTypeDropDown(entityname)) {

						logResults.createLogs("Y", "PASS", "EntityType Selected Successfully." + entityname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EntityType Name." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.LateOutTabSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1070_LateOut_07
				@Test(enabled = true, priority = 25, groups = { "Smoke" })
				public void MPI_1070_LateOut_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year");

					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateOutTab()) {

						logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.LateOutTabAttenDateSearchResult(monthName, year)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1071_LateOut_08
				@Test(enabled = true, priority = 26, groups = { "Smoke" })
				public void MPI_1071_LateOut_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to dateÂ ");

					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthsecondWorkingDate = dateInfo.get("month2WorkingDate");
				        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
				        String formattedDate = Pramod.convertToUIFormat(monthsecondWorkingDate);
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateOutTab()) {

						logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(monthsecondWorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + monthsecondWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(monthsecondWorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + monthsecondWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.LateOutTabDateSearchResultValidation(monthsecondWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1074_LateOut_11
				@Test(enabled = true, priority = 27, groups = { "Smoke" })
				public void MPI_1074_LateOut_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, perform a late punch-out and ensure that the â€œLate outâ€ records are displayed, and the total â€œLate out Hoursâ€ for the day is accurate.");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"lateinhours");

					
				
					String lateouthours =  data.get(0);
					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthsecondWorkingDate = dateInfo.get("month2WorkingDate");

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
				        String formattedDate = Pramod.convertToUIFormat(monthsecondWorkingDate);
					
			
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateOutTab()) {

						logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateOutTabSearchTextfield(formattedDate)) {

						logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + formattedDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
					}
					
				
					if (meghpitimelog.LateOutTabLateOuthoursSearchResultValidation(lateouthours)) {

						logResults.createLogs("Y", "PASS", "Inputted Late Out Hours Attendance Data Displayed Successfully." + lateouthours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Late Out Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1075_LateOut_12
				@Test(enabled = true, priority = 28, groups = { "Smoke" })
				public void MPI_1075_LateOut_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, ensure that the Out Time and Total Hours are displayed accurately.");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"totalhours,fourthinouttime");

					
					
					String totalhours =  data.get(0);
					String fourthinouttime =  data.get(1);
					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthsecondWorkingDate = dateInfo.get("month2WorkingDate");

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
				        String formattedDate = Pramod.convertToUIFormat(monthsecondWorkingDate);
			
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.LateOutTab()) {

						logResults.createLogs("Y", "PASS", "LateOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On LateOutTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.LateOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabOutTimeCheckBox()) {

						logResults.createLogs("Y", "PASS", " OUt Time CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Out Time CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateOutTabSearchTextfield(formattedDate)) {

						logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + formattedDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
					}
					
				
					if (meghpitimelog.LateOutTabTotalhoursSearchResult(totalhours)) {

						logResults.createLogs("Y", "PASS", "Inputted Total Hours Attendance Data Displayed Successfully." + totalhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Total Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
					
					if (meghpitimelog.LateOutTabOutTimeSearchResult(fourthinouttime)) {

						logResults.createLogs("Y", "PASS", "Inputted Late OUT Time Attendance Data Displayed Successfully." + fourthinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Late Out Time Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1076_EarlyIn_01
				@Test(enabled = true, priority = 29, groups = { "Smoke" })
				public void MPI_1076_EarlyIn_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature using each search option");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"inouttime,totalhours,lateinhours,shift");

					
				
					String inouttime = data.get(0);
					String totalhours = data.get(1);
					String earlyinhours = data.get(2);
					String shift = data.get(3);
				
					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
				        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
				        String formattedDate = Pramod.convertToUIFormat(monthFirstWorkingDate);
					
			
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyInTab()) {

						logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabInTimeCheckBox()) {

						logResults.createLogs("Y", "PASS", " InTime CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On In Time CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyInTabSearchTextField(formattedDate)) {

						logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + formattedDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.EarlyInTabDateSearchResult(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Inputted Date Attendance Data Displayed Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Date Data." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabSearchTextField(inouttime)) {

						logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  In Time." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.EarlyInTabInTImeSearchResult(inouttime)) {

						logResults.createLogs("Y", "PASS", "Inputted Early In Time Attendance Data Displayed Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Early In Time Data." + meghpitimelog.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabInTimeCheckBox()) {

						logResults.createLogs("Y", "PASS", " InTime CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On In Time CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabTotalHourCheckBox()) {

						logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabEarlyInHoursCheckBox()) {

						logResults.createLogs("Y", "PASS", " In Hours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On In Hours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabSearchTextField(totalhours)) {

						logResults.createLogs("Y", "PASS", "Totalhours Inputted Successfully." + totalhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Totalhours." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.EarlyInTabTotalHoursSearchResult(totalhours)) {

						logResults.createLogs("Y", "PASS", "Inputted Total Hours Attendance Data Displayed Successfully." + totalhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Total Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabSearchTextField(earlyinhours)) {

						logResults.createLogs("Y", "PASS", "Late In Hours Inputted Successfully." + earlyinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Late In Hours." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.EarlyInTabEarlyInHoursSearchResult(earlyinhours)) {

						logResults.createLogs("Y", "PASS", "Inputted Early In Hours Attendance Data Displayed Successfully." + earlyinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Early In Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabTotalHourCheckBox()) {

						logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabEarlyInHoursCheckBox()) {

						logResults.createLogs("Y", "PASS", " In Hours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On In Hours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabShiftCheckBox()) {

						logResults.createLogs("Y", "PASS", " Shift CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabSearchTextField(shift)) {

						logResults.createLogs("Y", "PASS", "Shift Inputted Successfully." + shift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Shift." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.EarlyInTabShiftSearchResult(shift)) {

						logResults.createLogs("Y", "PASS", "Inputted Shift Attendance Data Displayed Successfully." + shift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Shift Attendance Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1077_EarlyIn_02
				@Test(enabled = true, priority = 30, groups = { "Smoke" })
				public void MPI_1077_EarlyIn_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"subject,ccmail,date");

					
				
					String subject = data.get(0);
					String ccmail = data.get(1);
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();


			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyInTab()) {

						logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabEmailButton()) {

						logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailReportButton." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.SubjectTextField(subject)) {

						logResults.createLogs("Y", "PASS", "Mail Subject Inputted Successfully." + subject);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail Subject." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.CCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail CC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail CC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.BCCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail BCC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail BCC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FrequencySelection()) {

						logResults.createLogs("Y", "PASS", "Frequency Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Mail sending Frequency." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MailSendSaveButton()) {

						logResults.createLogs("Y", "PASS", "MailSendSaveButton Clicked Successfully.");
		 			} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailSendSaveButton." + meghpiattendancereport.getExceptionDesc());
					}
				}
				
				// MPI_1079_EarlyIn_04
				@Test(enabled = true, priority = 31, groups = { "Smoke" })
				public void MPI_1079_EarlyIn_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();


			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyInTab()) {

						logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
			
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyInTabTeamSearchResult(teamname)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + teamname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1080_EarlyIn_05
				@Test(enabled = true, priority = 32, groups = { "Smoke" })
				public void MPI_1080_EarlyIn_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyInTab()) {

						logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DesignationDropDown(designationname)) {

						logResults.createLogs("Y", "PASS", "Designation Selected Successfully." + designationname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Designation Name." + meghpiattendancereport.getExceptionDesc());
					}
			
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
			
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyInTabDesignationSearchResult(designationname)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + designationname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}		
				
				// MPI_1081_EarlyIn_06
				@Test(enabled = true, priority = 33, groups = { "Smoke" })
				public void MPI_1081_EarlyIn_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyInTab()) {

						logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.EntityTypeDropDown(entityname)) {

						logResults.createLogs("Y", "PASS", "EntityType Selected Successfully." + entityname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EntityType Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyInTabSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1082_EarlyIn_07
				@Test(enabled = true, priority = 34, groups = { "Smoke" })
				public void MPI_1082_EarlyIn_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year");

					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyInTab()) {

						logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyInTabAttenDateSearchResult(monthName, year)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1083_EarlyIn_08
				@Test(enabled = true, priority = 35, groups = { "Smoke" })
				public void MPI_1083_EarlyIn_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to dateÂ ");

					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
			        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");

			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
			        String formattedDate = Pramod.convertToUIFormat(monthFirstWorkingDate);
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyInTab()) {

						logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyInTabDateSearchResultValidation(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1086_EarlyIn_11
				@Test(enabled = true, priority = 36, groups = { "Smoke" })
				public void MPI_1086_EarlyIn_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, perform a early punch-in and ensure that the â€œearly Inâ€ records are displayed, and the total â€œearly In Hoursâ€ for the day is accurate.");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"lateinhours");

					
				
					String EarlyInHours =  data.get(0);
				
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");

			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
			        String formattedDate = Pramod.convertToUIFormat(monthFirstWorkingDate);
					
			
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyInTab()) {

						logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyInTabSearchTextField(formattedDate)) {

						logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + formattedDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
					}
					
				
					if (meghpitimelog.EarlyInTabEarlyInhoursSearchResultValidation(EarlyInHours)) {

						logResults.createLogs("Y", "PASS", "Inputted Late Out Hours Attendance Data Displayed Successfully." + EarlyInHours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Late Out Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1087_EarlyIn_12
				@Test(enabled = true, priority = 37, groups = { "Smoke" })
				public void MPI_1087_EarlyIn_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, ensure that the In Time and Total Hours are displayed accurately.");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"totalhours,inouttime");

				
					String totalhours =  data.get(0);
					String inouttime =  data.get(1);
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");

			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
			        String formattedDate = Pramod.convertToUIFormat(monthFirstWorkingDate);
					
			
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyInTab()) {

						logResults.createLogs("Y", "PASS", "EarlyInTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyInTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyInTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabInTimeCheckBox()) {

						logResults.createLogs("Y", "PASS", " InTime CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On In Time CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyInTabSearchTextField(formattedDate)) {

						logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + formattedDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
					}
				
					if (meghpitimelog.EarlyInTabTotalHoursSearchResult(totalhours)) {

						logResults.createLogs("Y", "PASS", "Total Hours  Data Displayed Successfully." + totalhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Total Hours Data." + meghpitimelog.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyInTabInTImeSearchResult(inouttime)) {

						logResults.createLogs("Y", "PASS", "Inputted Early In Time Attendance Data Displayed Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Early In Time Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				//Early out
				// MPI_1088_EarlyOut_01
				@Test(enabled = true, priority = 38, groups = { "Smoke" })
				public void MPI_1088_EarlyOut_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature using each search option");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"fourthinouttime,totalhours,lateinhours,shift");

					
				
					String fourthinouttime = data.get(0);
					String totalhours = data.get(1);
					String lateinhours = data.get(2);
					String shift = data.get(3);
					 Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

				        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
				        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");

				        String monthName = dateInfo.get("monthName");
				        String year = dateInfo.get("year");
				        String formattedDate = Pramod.convertToUIFormat(monthFirstWorkingDate);
					
			
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			    	
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyOutTab()) {

						logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabOutTimeCheckBox()) {

						logResults.createLogs("Y", "PASS", " OUt Time CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Out Time CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyOutTabSearchTextField(formattedDate)) {

						logResults.createLogs("Y", "PASS", "Date Inputted Successfully." + formattedDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Date." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.EarlyOutTabDateSearchResult(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Inputted Date Attendance Data Displayed Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Date Data." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabSearchTextField(fourthinouttime)) {

						logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + fourthinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Out Time." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.EarlyOutTabOutTimeSearchResult(fourthinouttime)) {

						logResults.createLogs("Y", "PASS", "Inputted Early OUT Time Attendance Data Displayed Successfully." + fourthinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Early Out Time Data." + meghpitimelog.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.LateInDateCheckBox()) {

						logResults.createLogs("Y", "PASS", " Date CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Date CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabOutTimeCheckBox()) {

						logResults.createLogs("Y", "PASS", " OUt Time CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Out Time CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabTotalHourCheckBox()) {

						logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabEarlyOutHoursCheckBox()) {

						logResults.createLogs("Y", "PASS", " Early out Hours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Early Out Hours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabSearchTextField(totalhours)) {

						logResults.createLogs("Y", "PASS", "Totalhours Inputted Successfully." + totalhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Totalhours." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.EarlyOutTabTotalHoursSearchResult(totalhours)) {

						logResults.createLogs("Y", "PASS", "Inputted Total Hours Attendance Data Displayed Successfully." + totalhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Total Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabSearchTextField(lateinhours)) {

						logResults.createLogs("Y", "PASS", "Early Out Hours Inputted Successfully." + lateinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Early Out Hours." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.EarlyOutTabEarlyOutHoursSearchResult(lateinhours)) {

						logResults.createLogs("Y", "PASS", "Inputted Early Out Hours Attendance Data Displayed Successfully." + lateinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Early Out Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpitimelog.LateInTabTotalHourCheckBox()) {

						logResults.createLogs("Y", "PASS", " TotalHours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TotalHours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabEarlyOutHoursCheckBox()) {

						logResults.createLogs("Y", "PASS", " Out Hours CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Out Hours CheckBox." + meghpitimelog.getExceptionDesc());
					}
					if (meghpitimelog.LateInTabShiftCheckBox()) {

						logResults.createLogs("Y", "PASS", " Shift CheckBOx Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Shift CheckBox." + meghpitimelog.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabSearchTextField(shift)) {

						logResults.createLogs("Y", "PASS", "Shift Inputted Successfully." + shift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Shift." + meghpitimelog.getExceptionDesc());
					}
					
					
					if (meghpitimelog.EarlyOutTabShiftSearchResult(shift)) {

						logResults.createLogs("Y", "PASS", "Inputted Shift Attendance Data Displayed Successfully." + shift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Inputted Shift Attendance Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1089_EarlyOut_02
				@Test(enabled = true, priority = 39, groups = { "Smoke" })
				public void MPI_1089_EarlyOut_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"subject,ccmail,date");

					
					
					String subject = data.get(0);
					String ccmail = data.get(1);
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			    	
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyOutTab()) {

						logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
					}	
					if (meghpitimelog.EarlyOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabEmailButton()) {

						logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailReportButton." + meghpitimelog.getExceptionDesc());
					}
					if (meghpiattendancereport.SubjectTextField(subject)) {

						logResults.createLogs("Y", "PASS", "Mail Subject Inputted Successfully." + subject);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail Subject." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.CCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail CC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail CC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.BCCTextField(ccmail)) {

						logResults.createLogs("Y", "PASS", "Mail BCC Inputted Successfully." + ccmail);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Mail BCC." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FrequencySelection()) {

						logResults.createLogs("Y", "PASS", "Frequency Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Mail sending Frequency." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MailSendSaveButton()) {

						logResults.createLogs("Y", "PASS", "MailSendSaveButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailSendSaveButton." + meghpiattendancereport.getExceptionDesc());
					}
				}
			
				// MPI_1091_EarlyOut_04
				@Test(enabled = true, priority = 40, groups = { "Smoke" })
				public void MPI_1091_EarlyOut_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyOutTab()) {

						logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
					}	
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyOutTabTeamSearchResult(teamname)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + teamname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1092_EarlyOut_05
				@Test(enabled = true, priority = 41, groups = { "Smoke" })
				public void MPI_1092_EarlyOut_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();


			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyOutTab()) {

						logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
					}	
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DesignationDropDown(designationname)) {

						logResults.createLogs("Y", "PASS", "Designation Selected Successfully." + designationname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Designation Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyOutTabDesignationSearchResult(designationname)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + designationname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}	
				
				// MPI_1093_EarlyOut_06
				@Test(enabled = true, priority = 42, groups = { "Smoke" })
				public void MPI_1093_EarlyOut_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyOutTab()) {

						logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
					}	
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpiattendancereport.TeamDropDown(teamname)) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.EntityTypeDropDown(entityname)) {

						logResults.createLogs("Y", "PASS", "EntityType Selected Successfully." + entityname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EntityType Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyOutTabSearchResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}		
				

				// MPI_1094_Earlyout_07
				@Test(enabled = true, priority = 43, groups = { "Smoke" })
				public void MPI_1094_Earlyout_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year");

					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyOutTab()) {

						logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
					}	
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}
	
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.LateOutTabAttenDateSearchResultValidation(monthName, year)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
				
				// MPI_1095_EarlyOut_08
				@Test(enabled = true, priority = 44, groups = { "Smoke" })
				public void MPI_1095_EarlyOut_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to dateÂ ");

					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
			        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");

			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
			        String formattedDate = Pramod.convertToUIFormat(monthFirstWorkingDate);
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyOutTab()) {

						logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
					}	
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}
	
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyOutTabDateSearchResultFilterValidation(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpitimelog.getExceptionDesc());
					}
				}
			
				// MPI_1098_EarlyOut_11
				@Test(enabled = true, priority = 45, groups = { "Smoke" })
				public void MPI_1098_EarlyOut_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, perform a early punch-out and ensure that the â€œearly outâ€ records are displayed, and the total â€œearly out Hoursâ€ for the day is accurate.");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"lateinhours");

					
					
					String EarlyOutHours = data.get(0);
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
			        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");

			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
			        String formattedDate = Pramod.convertToUIFormat(monthFirstWorkingDate);
					
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyOutTab()) {

						logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
					}	
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}
	
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabEarlyOutHoursSearchResult(EarlyOutHours)) {

						logResults.createLogs("Y", "PASS", " Early Out Hours Attendance Data Displayed Successfully." + EarlyOutHours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Early Out Hours Attendance Data." + meghpitimelog.getExceptionDesc());
					}
					
				}
			
				// MPI_1099_EarlyOut_12
				@Test(enabled = true, priority = 46, groups = { "Smoke" })
				public void MPI_1099_EarlyOut_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, ensure that the Out Time and Total Hours are displayed accurately.");

					ArrayList<String> data = initBase.loadExcelData("TimeLog_Reports", currTC,
							"fourthinouttime,totalhours");

					
					
					String fourthinouttime = data.get(0);
					String totalhours = data.get(1);
					Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();

			        String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
			        String firstDayOfMonth = dateInfo.get("firstDayOfMonth");

			        String monthName = dateInfo.get("monthName");
			        String year = dateInfo.get("year");
			        String formattedDate = Pramod.convertToUIFormat(monthFirstWorkingDate);
						
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			                    MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

					
			                    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


			            		if (meghloginpage.MainLandingPage()) {
			            			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
			            		} else {
			            			logResults.createLogs("Y", "FAIL",
			            					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
			            		}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpitimelog.TimeLogReportButton()) {

						logResults.createLogs("Y", "PASS", "TimeLogReport Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On TimeLog Report Button." + meghpitimelog.getExceptionDesc());
					}

					if (meghpitimelog.EarlyOutTab()) {

						logResults.createLogs("Y", "PASS", "EarlyOutTab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On EarlyOutTab." + meghpitimelog.getExceptionDesc());
					}	
					if (meghpiattendancereport.LocationDropdown()) {

						logResults.createLogs("Y", "PASS", "Location DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Location Dropdown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchTextField(officename)) {

						logResults.createLogs("Y", "PASS", "Location Name Inputted Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.LocationDropdownSearchResult()) {

						logResults.createLogs("Y", "PASS", "Location Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Location Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.DepartmentDropdown(departmentname)) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully." + departmentname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpitimelog.getExceptionDesc());
					}
	
					if (meghpiattendancereport.YearDropDown(year)) {

						logResults.createLogs("Y", "PASS", "Year Selected Successfully." + year );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Year." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.MonthDropDown(monthName)) {

						logResults.createLogs("Y", "PASS", "Month Selected Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Month." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.WeeklyDropDown()) {

						logResults.createLogs("Y", "PASS", "Week Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Week." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToDateDropDown()) {

						logResults.createLogs("Y", "PASS", "ToDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On ToDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToDateSelected(monthFirstWorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + monthFirstWorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpitimelog.EarlyOutTabTotalHoursSearchResult(totalhours)) {

						logResults.createLogs("Y", "PASS", "Total Hours  Data Displayed Successfully." + totalhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Total Hours Data." + meghpitimelog.getExceptionDesc());
					}
					
					if (meghpitimelog.EarlyOutTabOutTimeSearchResult(fourthinouttime)) {

						logResults.createLogs("Y", "PASS", " Early Out Time Attendance Data Displayed Successfully." + fourthinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Early Out Time Data." + meghpitimelog.getExceptionDesc());
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
					}
				}
}
