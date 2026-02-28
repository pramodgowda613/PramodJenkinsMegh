package com.MeghPI.Attendance.tests;

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
import com.MeghPI.Attendance.pages.MeghLeavePolicyPage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import com.MeghPI.Attendance.pages.MeghPITotalLeavesTakenReportPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPITotalLeavesTakenReportTest {

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
    private String LeavePolicyName = "";
 
	
	
	
    @Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
		driver = loadDriver.getDriver(device);

		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

    @Parameters({ "device", "hubnodeip" })
	@BeforeClass(alwaysRun = true)
	void runOnce(int device, @Optional String hubnodeip) {
		logResults.createReport(device);
		logResults.setTestMethodErrorCount(0);
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");
		
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		LeavePolicyName = "AutoLeavePN" +  initBase.executionRunTime;
		
	}
	
	// MPI_1291_LeavesTaken_Reports_01
		@Test(enabled = true, priority = 1, groups = { "Smoke" })
		public void MPI_1291_LeavesTaken_Reports_01()   {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, Create an new emp and apply leave for 2 days check the total days count is 2 is displayed .");

			  // Load Excel data
			ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
					"password,captcha,baseuri,loginendpoint,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,photo,pin,leavename,leavereason,totaldays,leavestatus");


			  int i = 0;
			String password = data.get(i++);
			String captcha = data.get(i++);
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
		        String pin = data.get(i++);
		        String leavename =  data.get(i++);
		        String leavereason = data.get(i++);
		        String totaldays = data.get(i++);
		        String leavestatus =data.get(i++);
		     
		        
		        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

		        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

		        String monthFirstWorkingDate  = dateDetails.get("month1WorkingDate");
		        String monthSecondWorkingDate  = dateDetails.get("month2WorkingDate");
		        String monthName = dateDetails.get("monthName");
			    String year = dateDetails.get("year");
		        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");

		       
				 
				MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
				 MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
					MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
			MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
			 MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
			MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
			MeghLeavePage meghleavepage = new MeghLeavePage(driver);
			MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

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
			if (LeavePolicyPage.LeavePolicySearchTextFields(LeavePolicyName)) {

				logResults.createLogs("Y", "PASS", "Created Policy Name Inputted Successfully." + LeavePolicyName);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Leave Policy Name." + LeavePolicyPage.getExceptionDesc());
			}

			if (LeavePolicyPage.SearchInputtedPolicyvalidation(LeavePolicyName)) {

				logResults.createLogs("Y", "PASS", "Created Policy Name Displayed Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying Created Leave Policy Name." + LeavePolicyPage.getExceptionDesc());
			}

			if (LeavePolicyPage.LeavePolicyEditIcon()) {

				logResults.createLogs("Y", "PASS", "Created Policy Edit Icon Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Leave Policy Edit Icon." + LeavePolicyPage.getExceptionDesc());
			}

			if (LeavePolicyPage.CreateLeavePolicyButton()) {

				logResults.createLogs("Y", "PASS", "CreateLeavePolicyButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CreateLeavePolicyButton." + LeavePolicyPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.YesButton()) {
				Utils.propsReadWrite("data/addmaster.properties", "set", "LeavePolicyName", "pass");
				
				logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
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
		
			
			if (meghleavepage.ApplyLeaveButton()) {

				logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button."
						+ meghleavepage.getExceptionDesc());
			}	
			
			if (meghleavepage.LeaveForOthersButton()) {

				logResults.createLogs("Y", "PASS", "  Leave For Others Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave For Others Button."
						+ meghleavepage.getExceptionDesc());
			}	
			
			if (meghleavepage.EmployeeSelectionDropDown()) {

				logResults.createLogs("Y", "PASS", "  Employee Selection DropDown Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Employee Selection DropDown."
						+ meghleavepage.getExceptionDesc());
			}	
			
			if (meghleavepage.EmployeeInputField(firstname)) {

				logResults.createLogs("Y", "PASS", " Emp Name Inputted  Successfully ." + firstname);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.EmpNameDisplayedAndSelected()) {

				logResults.createLogs("Y", "PASS", "  Employee Selected  Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Employee From DropDown."
						+ meghleavepage.getExceptionDesc());
			}	
			
			if (meghleavepage.LeaveTypeDropDownOnAdmin()) {

				logResults.createLogs("Y", "PASS", "  LeaveType DropDown OnAdmin Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking  LeaveType DropDown OnAdmin."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.LeaveTypeInputtedOnAdmin(leavename)) {

				logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully ." + leavename);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Name."
						+ meghleavepage.getExceptionDesc());
			}
			
			
			if (meghleavepage.LeaveTypeSelectedOnAdmin()) {

				logResults.createLogs("Y", "PASS", "  Leave Type Selected  Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type From DropDown."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.FromDateOnAdmin()) {

				logResults.createLogs("Y", "PASS", " Clicked On FromDate TextField In Admin  Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On From Date TextField."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.FromDateOnAdminSelected(monthFirstWorkingDate)) {

				logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully ." + monthFirstWorkingDate);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting  From Date in TextField."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.FromLeaveDurationDropdownAdmin()) {

				logResults.createLogs("Y", "PASS", " Clicked On FromLeaveDuration Dropdown In Admin  Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On FromLeave Duration Dropdown."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.FromLeaveDurationSelectedInAdmin()) {

				logResults.createLogs("Y", "PASS", " Selected  FromLeaveDuration From Dropdown In Admin  Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting  FromLeave Duration From Dropdown."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.ToDateTextFieldInAdmin()) {

				logResults.createLogs("Y", "PASS", " Clicked On ToDate TextField In Admin  Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On To Date TextField."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.ToDateTextFieldInAdminSelected(monthSecondWorkingDate)) {

				logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully ." + monthSecondWorkingDate);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting  TO Date in TextField."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.ToLeaveDurationDropDownInAdmin()) {

				logResults.createLogs("Y", "PASS", " Clicked On ToLeaveDuration Dropdown In Admin  Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On ToLeave Duration Dropdown."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.ToLeaveDurationFullDaySelectedInAdmin()) {

				logResults.createLogs("Y", "PASS", " Selected  ToLeaveDuration From Dropdown In Admin  Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting  ToLeave Duration From Dropdown."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.ReasonTextField(leavereason)) {

				logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + leavereason);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Reason."
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.ApplyForOthersButton()) {

				logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully ." );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button."
						+ meghleavepage.getExceptionDesc());
			}
			
			 
						if (RolePermissionpage.ReprtButton()) {

							logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
						}
						
						if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

							logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
						}
						if (meghpitotalleavestaken.TotalLeavesTakenReportAllTab()) {

							logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report All Tab Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On TotalLeavesTaken Report All Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
						}
						if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

							logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
						if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

							logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
						
						if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(firstname)) {

							logResults.createLogs("Y", "PASS", "Leave Applied Emp Name Inputted Successfully." + firstname);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting LeavesTaken Emp Name ." + meghpitotalleavestaken.getExceptionDesc());
						}
						
						if (meghpitotalleavestaken.LeaveStatusDateStatusValidation(firstname, leavename,monthFirstWorkingDate,monthSecondWorkingDate, totaldays, leavestatus )) {

							logResults.createLogs("Y", "PASS", "Leave Applied Emp Record All Data Validated Successfully." + firstname + " " + leavename + " " + monthFirstWorkingDate + " " + monthSecondWorkingDate + " " + totaldays + " " + leavestatus);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Validating LeavesTaken Emp Record ." + meghpitotalleavestaken.getExceptionDesc());
						}	
		}
	
		// MPI_1312_LeavesTaken_Reports_11
				@Test(enabled = true, priority = 2, groups = { "Smoke" })
				public void MPI_1312_LeavesTaken_Reports_11()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature for each search option.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha,leavename,totaldays,leavestatus,leavereason");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						String leavename = data.get(i++);
						String totaldays = data.get(i++);
						String leavestatus = data.get(i++);
						String leavereason = data.get(i++);
						
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					    
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportAllTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report All Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report All Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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

		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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

		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveTypeCheckbox()) {

			logResults.createLogs("Y", "PASS", "LeaveType Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting LeaveType CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}
		

		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavename)) {

			logResults.createLogs("Y", "PASS", "Leave Name Inputted Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Name ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveNameResultValidation(leavename)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave Validated Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportTotalDaysResultValidation(totaldays)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave TotalDays Validated Successfully." + totaldays);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave TotalDays." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}

		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveTypeCheckbox()) {

			logResults.createLogs("Y", "PASS", "LeaveType Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting LeaveType CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportStatusCheckbox()) {

			logResults.createLogs("Y", "PASS", "Status CheckBox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Status CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonCheckbox()) {

			logResults.createLogs("Y", "PASS", "Leave Reason CheckBox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Reason CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}

		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavestatus)) {

			logResults.createLogs("Y", "PASS", "Leave Status Inputted Successfully." + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Status ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveStatusResultValidation(leavestatus)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave Status Validated Successfully." + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Status." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavereason)) {

			logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Reason ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonValidation(leavereason)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave Reason Validated Successfully." + leavereason);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Reason." + meghpitotalleavestaken.getExceptionDesc());
		}
				}	
		
				// MPI_1292_LeavesTaken_Reports_02
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_1292_LeavesTaken_Reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha,subject,ccmail");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						String subject = data.get(i++);
						String ccmail = data.get(i++);
			
						
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
						
					MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportAllTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report All Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report All Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		
		if (meghpiovertimepage.OverTimeReportEmailButton()) {

			logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On MailReportButton." + meghpiovertimepage.getExceptionDesc());
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
		
				// MPI_1294_LeavesTaken_Reports_04
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_1294_LeavesTaken_Reports_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportAllTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report All Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report All Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1295_LeavesTaken_Reports_05
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_1295_LeavesTaken_Reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
				
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportAllTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report All Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report All Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1296_LeavesTaken_Reports_06
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_1296_LeavesTaken_Reports_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type .");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportAllTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report All Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report All Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1297_LeavesTaken_Reports_07
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_1297_LeavesTaken_Reports_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year .");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportAllTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report All Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report All Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.FilteredDateValidation(year, monthName )) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1298_LeavesTaken_Reports_08
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_1298_LeavesTaken_Reports_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

				        String monthFirstWorkingDate  = dateDetails.get("month1WorkingDate");
				        String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportAllTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report All Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report All Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpiattendancereport.FromDateDropDown()) {

			logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiattendancereport.FilterSaveButton()) {

			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.LeavesTakenRptFilteredDateValidation(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully." + monthFirstWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}	
		
				// MPI_1301_LeaveTakenApproved_Reports_01
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_1301_LeaveTakenApproved_Reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, apply for leave as an employee, approve the leave, and ensure the approved leave request is displayed under the Approved tab..");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha,empid,leavereason,leavename,totaldays,leavestatus,firstname");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						String empid = data.get(i++);
						String leavereason = data.get(i++);
						String leavename = data.get(i++);
						String totaldays =  data.get(i++);
						String leavestatus =  data.get(i++);
						String firstname =  data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

						String month3WorkingDate  = dateDetails.get("month3WorkingDate");
						  String monthName = dateDetails.get("monthName");
						    String year = dateDetails.get("year");
						
			
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.LeaveButton()) {

			logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.LeaveRequestsTab()) {

			logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
					+ RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.ApplyLeaveButton()) {

			logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button."
					+ meghleavepage.getExceptionDesc());
		}	
	
		if (meghleavepage.LeaveTypeDropdown()) {

			logResults.createLogs("Y", "PASS", " LeaveTypeDropdown Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveTypeDropdown."
					+ meghleavepage.getExceptionDesc());
		}

		if (meghleavepage.SickLeaveSelected()) {

			logResults.createLogs("Y", "PASS", " SickLeave Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Sick Leave."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.FromDateTextField()) {

			logResults.createLogs("Y", "PASS", " FromDateTextField Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On FromDateTextField."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.FromDateSelected(month3WorkingDate)) {

			logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting From From Date."
					+ meghleavepage.getExceptionDesc());
		}
		

		if (meghleavepage.LeaveDurationOne()) {

			logResults.createLogs("Y", "PASS", " LeaveDurationOne Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationOne."
					+ meghleavepage.getExceptionDesc());
		}

		if (meghleavepage.FullDayDurationOne()) {

			logResults.createLogs("Y", "PASS", " FullDay DurationOne Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting FullDay DurationOne."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.ToDateTextField()) {

			logResults.createLogs("Y", "PASS", " ToDateTextField Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On ToDateTextField."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.ToDateSelected(month3WorkingDate)) {

			logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + month3WorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting From ToDate."
					+ meghleavepage.getExceptionDesc());
		}
	
		if (meghleavepage.LeaveDurationTwo()) {

			logResults.createLogs("Y", "PASS", " LeaveDurationTwo Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationTwo."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.FullDayDurationTwo()) {

			logResults.createLogs("Y", "PASS", " FullDayDurationTwo Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On FullDayDurationTwo."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.ReasonTextField(leavereason)) {

			logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + leavereason);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Reason."
					+ meghleavepage.getExceptionDesc());
		}
	
		if (meghleavepage.RequestApplyButton()) {

			logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully Of Employee Account." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button."
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
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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
		if (meghleavepage.MonthFilterContains(month3WorkingDate)) {

			logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + month3WorkingDate );
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
		
	
		if (meghleavepage.approveLeaveByDateAndEmp(empid, month3WorkingDate)) {

			logResults.createLogs("Y", "PASS", " Leave Approved   Successfully ." + month3WorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Aprroving Leave."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.LeaveApproveButton()) {

			logResults.createLogs("Y", "PASS", " Leave Approve  Button Clicked  Successfully ." + month3WorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Approve Button."
					+ meghleavepage.getExceptionDesc());
		}
 
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportApprovedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Approved Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Approved Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "Leave Applied Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Applied Emp Name ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.ApprovedLeaveValidation(firstname, month3WorkingDate, leavestatus )) {

			logResults.createLogs("Y", "PASS", "Leave Applied Emp Record All Data Validated Successfully." + firstname + " " + leavename + " " + month3WorkingDate + " " + month3WorkingDate + " " + totaldays + " " + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating LeavesTaken Emp Record ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
				}
		
				// MPI_1302_LeaveTakenApproved_Reports_02
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_1302_LeaveTakenApproved_Reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature for each search option.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha,leavename,totaldays,leavestatus,leavereason");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						String leavename = data.get(i++);
						String totaldays = data.get(i++);
						String leavestatus = data.get(i++);
						String leavereason = data.get(i++);
						
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
			
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportApprovedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Approved Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Approved Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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

		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveTypeCheckbox()) {

			logResults.createLogs("Y", "PASS", "LeaveType Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting LeaveType CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}
		


		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavename)) {

			logResults.createLogs("Y", "PASS", "Leave Name Inputted Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Name ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveNameResultValidation(leavename)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave Validated Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave." + meghpitotalleavestaken.getExceptionDesc());
		}


		
		if (meghpitotalleavestaken.TotalLeavesTakenReportTotalDaysResultValidation(totaldays)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave TotalDays Validated Successfully." + totaldays);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave TotalDays." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}

		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveTypeCheckbox()) {

			logResults.createLogs("Y", "PASS", "LeaveType Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting LeaveType CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportStatusCheckbox()) {

			logResults.createLogs("Y", "PASS", "Status CheckBox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Status CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonCheckbox()) {

			logResults.createLogs("Y", "PASS", "Leave Reason CheckBox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Reason CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}

		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavestatus)) {

			logResults.createLogs("Y", "PASS", "Leave Status Inputted Successfully." + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Status ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveStatusResultValidation(leavestatus)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave Status Validated Successfully." + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Status." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavereason)) {

			logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Reason ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonValidation(leavereason)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave Reason Validated Successfully." + leavereason);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Reason." + meghpitotalleavestaken.getExceptionDesc());
		}
				}
		
				// MPI_1303_LeaveTakenApproved_Reports_03
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_1303_LeaveTakenApproved_Reports_03()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha,subject,ccmail");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						String subject = data.get(i++);
						String ccmail = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");

					MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportApprovedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Approved Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Approved Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		
		if (meghpiovertimepage.OverTimeReportEmailButton()) {

			logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On MailReportButton." + meghpiovertimepage.getExceptionDesc());
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
		
				// MPI_1305_LeaveTakenApproved_Reports_05
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_1305_LeaveTakenApproved_Reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportApprovedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Approved Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Approved Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1306_LeaveTakenApproved_Reports_06
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_1306_LeaveTakenApproved_Reports_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportApprovedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Approved Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Approved Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1307_LeaveTakenApproved_Reports_07
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_1307_LeaveTakenApproved_Reports_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type .");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportApprovedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Approved Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Approved Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1308_LeaveTakenApproved_Reports_08
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_1308_LeaveTakenApproved_Reports_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year .");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportApprovedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Approved Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Approved Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.FilteredDateValidation(year, monthName )) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1309_LeaveTakenApproved_Reports_09
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
				public void MPI_1309_LeaveTakenApproved_Reports_09()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

						String month3WorkingDate  = dateDetails.get("month3WorkingDate");
						  String monthName = dateDetails.get("monthName");
						    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportApprovedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Approved Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Approved Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpiattendancereport.FromDateSelected(month3WorkingDate)) {

			logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month3WorkingDate);
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
		
		if (meghpiattendancereport.ToDateSelected(month3WorkingDate)) {

			logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month3WorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiattendancereport.FromDateDropDown()) {

			logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.FilterSaveButton()) {

			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.LeavesTakenRptFilteredDateValidation(month3WorkingDate)) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully." + month3WorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}			
		
				// MPI_1313_LeavesTakenRejected_Reports_01
				@Test(enabled = true, priority = 17, groups = { "Smoke" })
				public void MPI_1313_LeavesTakenRejected_Reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, apply for leave as an employee, Reject the leave, and ensure the Rejected leave request is displayed under the Rejected tab.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha,empid,leavereason,leavename,totaldays,leavestatus,firstname,leaverejectionreason");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						String empid = data.get(i++);
						String leavereason = data.get(i++);
						String leavename = data.get(i++);
						String totaldays =  data.get(i++);
						String leavestatus =  data.get(i++);
						String firstname =  data.get(i++);
						String leaverejectionreason = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
						String month4WorkingDate  = dateDetails.get("month4WorkingDate");
						  
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
						  
			
				
					
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

		if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.LeaveButton()) {

			logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LeaveRequestsTab()) {

			logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
					+ RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.ApplyLeaveButton()) {

			logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button."
					+ meghleavepage.getExceptionDesc());
		}	
	
		if (meghleavepage.LeaveTypeDropdown()) {

			logResults.createLogs("Y", "PASS", " LeaveTypeDropdown Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveTypeDropdown."
					+ meghleavepage.getExceptionDesc());
		}

		if (meghleavepage.SickLeaveSelected()) {

			logResults.createLogs("Y", "PASS", " SickLeave Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Sick Leave."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.FromDateTextField()) {

			logResults.createLogs("Y", "PASS", " FromDateTextField Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On FromDateTextField."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.FromDateSelected(month4WorkingDate)) {

			logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting From From Date."
					+ meghleavepage.getExceptionDesc());
		}
		

		if (meghleavepage.LeaveDurationOne()) {

			logResults.createLogs("Y", "PASS", " LeaveDurationOne Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationOne."
					+ meghleavepage.getExceptionDesc());
		}

		if (meghleavepage.FullDayDurationOne()) {

			logResults.createLogs("Y", "PASS", " FullDay DurationOne Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting FullDay DurationOne."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.ToDateTextField()) {

			logResults.createLogs("Y", "PASS", " ToDateTextField Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On ToDateTextField."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.ToDateSelected(month4WorkingDate)) {

			logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + month4WorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting From ToDate."
					+ meghleavepage.getExceptionDesc());
		}
	
		if (meghleavepage.LeaveDurationTwo()) {

			logResults.createLogs("Y", "PASS", " LeaveDurationTwo Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationTwo."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.FullDayDurationTwo()) {

			logResults.createLogs("Y", "PASS", " FullDayDurationTwo Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On FullDayDurationTwo."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.ReasonTextField(leavereason)) {

			logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + leavereason);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Reason."
					+ meghleavepage.getExceptionDesc());
		}
	
		if (meghleavepage.RequestApplyButton()) {

			logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully Of Employee Account." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button."
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
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
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
		if (meghleavepage.MonthFilterContains(month4WorkingDate)) {

			logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + month4WorkingDate );
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
		
	
		if (meghleavepage.clickRejectButtonByEmpAndDate(empid, month4WorkingDate)) {

			logResults.createLogs("Y", "PASS", " Leave Rejected   Successfully ." + month4WorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Rejecting Leave."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.LeaveRejectionReason(leaverejectionreason)) {

			logResults.createLogs("Y", "PASS", " Leave Rejection Reason Inputted  Successfully ." + leaverejectionreason);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Rejection Reason."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.LeaveRejectButton()) {

			logResults.createLogs("Y", "PASS", " Leave Rejection  Button Clicked  Successfully ." + leaverejectionreason);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Reject Button."
					+ meghleavepage.getExceptionDesc());
		}
 
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportRejectedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Rejected Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Rejected Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		
	    
	    if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "Leave Applied Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Applied Emp Name ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.ApprovedLeaveValidation(firstname, month4WorkingDate, leavestatus )) {

			logResults.createLogs("Y", "PASS", "Leave Applied Emp Record All Data Validated Successfully." + firstname + " " + leavename + " " + month4WorkingDate + " " + month4WorkingDate + " " + totaldays + " " + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating LeavesTaken Emp Record ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
				}
		
				// MPI_1314_LeavesTakenRejected_Reports_02
				@Test(enabled = true, priority = 18, groups = { "Smoke" })
				public void MPI_1314_LeavesTakenRejected_Reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature for each search option.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha,leavename,totaldays,leavestatus,leavereason");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						String leavename = data.get(i++);
						String totaldays = data.get(i++);
						String leavestatus = data.get(i++);
						String leaverejectionreason = data.get(i++);
						

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");
		    
		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportRejectedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Rejected Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Rejected Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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

		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveTypeCheckbox()) {

			logResults.createLogs("Y", "PASS", "LeaveType Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting LeaveType CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}



		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavename)) {

			logResults.createLogs("Y", "PASS", "Leave Name Inputted Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Name ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveNameResultValidation(leavename)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave Validated Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave." + meghpitotalleavestaken.getExceptionDesc());
		}

		if (meghpitotalleavestaken.TotalLeavesTakenReportTotalDaysResultValidation(totaldays)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave TotalDays Validated Successfully." + totaldays);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave TotalDays." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}

		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveTypeCheckbox()) {

			logResults.createLogs("Y", "PASS", "LeaveType Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting LeaveType CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportStatusCheckbox()) {

			logResults.createLogs("Y", "PASS", "Status CheckBox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Status CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonCheckbox()) {

			logResults.createLogs("Y", "PASS", "Leave Reason CheckBox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Reason CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}

		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavestatus)) {

			logResults.createLogs("Y", "PASS", "Leave Status Inputted Successfully." + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Status ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveStatusResultValidation(leavestatus)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave Status Validated Successfully." + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Status." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leaverejectionreason)) {

			logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leaverejectionreason);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Reason ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonValidation(leaverejectionreason)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave Reason Validated Successfully." + leaverejectionreason);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Reason." + meghpitotalleavestaken.getExceptionDesc());
		}
				}
		
				// MPI_1315_LeavesTakenRejected_Reports_03
				@Test(enabled = true, priority = 19, groups = { "Smoke" })
				public void MPI_1315_LeavesTakenRejected_Reports_03()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha,subject,ccmail");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						String subject = data.get(i++);
						String ccmail = data.get(i++);
						

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");
						
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);


					MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportRejectedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Rejected Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Rejected Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		
		if (meghpiovertimepage.OverTimeReportEmailButton()) {

			logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On MailReportButton." + meghpiovertimepage.getExceptionDesc());
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
		
				// MPI_1317_LeavesTakenRejected_Reports_05
				@Test(enabled = true, priority = 20, groups = { "Smoke" })
				public void MPI_1317_LeavesTakenRejected_Reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportRejectedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Rejected Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Rejected Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1318_LeavesTakenRejected_Reports_06
				@Test(enabled = true, priority = 21, groups = { "Smoke" })
				public void MPI_1318_LeavesTakenRejected_Reports_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportRejectedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Rejected Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Rejected Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1319_LeavesTakenRejected_Reports_07
				@Test(enabled = true, priority = 22, groups = { "Smoke" })
				public void MPI_1319_LeavesTakenRejected_Reports_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type .");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportRejectedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Rejected Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Rejected Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1320_LeavesTakenRejected_Reports_08
				@Test(enabled = true, priority = 23, groups = { "Smoke" })
				public void MPI_1320_LeavesTakenRejected_Reports_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year .");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportRejectedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Rejected Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Rejected Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.FilteredDateValidation(year, monthName )) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1321_LeavesTakenRejected_Reports_09
				@Test(enabled = true, priority = 24, groups = { "Smoke" })
				public void MPI_1321_LeavesTakenRejected_Reports_09()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
						String monthFourthWorkingDate  = dateDetails.get("month4WorkingDate");
						  
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportRejectedTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Rejected Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Rejected Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpiattendancereport.FromDateSelected(monthFourthWorkingDate)) {

			logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + monthFourthWorkingDate);
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
		
		if (meghpiattendancereport.ToDateSelected(monthFourthWorkingDate)) {

			logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + monthFourthWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiattendancereport.FromDateDropDown()) {

			logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiattendancereport.FilterSaveButton()) {

			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.LeavesTakenRptFilteredDateValidation(monthFourthWorkingDate)) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully." + monthFourthWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1324_LeavesTakenPending_Reports_01
				@Test(enabled = true, priority = 25, groups = { "Smoke" })
				public void MPI_1324_LeavesTakenPending_Reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, apply for leave as an employee,ensure the the Pending leave request is displayed under the Pending tab..");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha,empid,leavereason,leavename,totaldays,leavestatus,firstname");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						String empid = data.get(i++);
						String leavereason = data.get(i++);
						String leavename = data.get(i++);
						String totaldays =  data.get(i++);
						String leavestatus =  data.get(i++);
						String firstname =  data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

						String monthFifthWorkingDate  = dateDetails.get("month5WorkingDate");
						 String monthName = dateDetails.get("monthName");
						    String year = dateDetails.get("year");
						
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}
		
		if (RolePermissionpage.LeaveButton()) {

			logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
		}
	
		if (RolePermissionpage.LeaveRequestsTab()) {

			logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab."
					+ RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.ApplyLeaveButton()) {

			logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button."
					+ meghleavepage.getExceptionDesc());
		}	
	
		if (meghleavepage.LeaveTypeDropdown()) {

			logResults.createLogs("Y", "PASS", " LeaveTypeDropdown Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveTypeDropdown."
					+ meghleavepage.getExceptionDesc());
		}

		if (meghleavepage.SickLeaveSelected()) {

			logResults.createLogs("Y", "PASS", " SickLeave Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Sick Leave."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.FromDateTextField()) {

			logResults.createLogs("Y", "PASS", " FromDateTextField Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On FromDateTextField."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.FromDateSelected(monthFifthWorkingDate)) {

			logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully ." + monthFifthWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting From From Date."
					+ meghleavepage.getExceptionDesc());
		}
		

		if (meghleavepage.LeaveDurationOne()) {

			logResults.createLogs("Y", "PASS", " LeaveDurationOne Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationOne."
					+ meghleavepage.getExceptionDesc());
		}

		if (meghleavepage.FullDayDurationOne()) {

			logResults.createLogs("Y", "PASS", " FullDay DurationOne Selected  Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting FullDay DurationOne."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.ToDateTextField()) {

			logResults.createLogs("Y", "PASS", " ToDateTextField Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On ToDateTextField."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.ToDateSelected(monthFifthWorkingDate)) {

			logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully ." + monthFifthWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting From ToDate."
					+ meghleavepage.getExceptionDesc());
		}
	
		if (meghleavepage.LeaveDurationTwo()) {

			logResults.createLogs("Y", "PASS", " LeaveDurationTwo Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationTwo."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.FullDayDurationTwo()) {

			logResults.createLogs("Y", "PASS", " FullDayDurationTwo Clicked Successfully Of Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On FullDayDurationTwo."
					+ meghleavepage.getExceptionDesc());
		}
		
		if (meghleavepage.ReasonTextField(leavereason)) {

			logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + leavereason);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Reason."
					+ meghleavepage.getExceptionDesc());
		}
	
		if (meghleavepage.RequestApplyButton()) {

			logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully Of Employee Account." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button."
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
		
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}

 
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportPendingTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Pending Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Pending Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "Leave Applied Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Applied Emp Name ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.ApprovedLeaveValidation(firstname, monthFifthWorkingDate, leavestatus )) {

			logResults.createLogs("Y", "PASS", "Leave Applied Emp Record All Data Validated Successfully." + firstname + " " + leavename + " " + monthFifthWorkingDate + " " + monthFifthWorkingDate + " " + totaldays + " " + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating LeavesTaken Emp Record ." + meghpitotalleavestaken.getExceptionDesc());
		}
				}	
		
				// MPI_1325_LeavesTakenPending_Reports_02
				@Test(enabled = true, priority = 26, groups = { "Smoke" })
				public void MPI_1325_LeavesTakenPending_Reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature for each search option.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha,leavename,totaldays,leavestatus,leavereason");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						String leavename = data.get(i++);
						String totaldays = data.get(i++);
						String leavestatus = data.get(i++);
						String leavereason = data.get(i++);
						

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");
					
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportPendingTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Pending Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Pending Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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

		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveTypeCheckbox()) {

			logResults.createLogs("Y", "PASS", "LeaveType Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting LeaveType CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}




		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavename)) {

			logResults.createLogs("Y", "PASS", "Leave Name Inputted Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Name ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveNameResultValidation(leavename)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave Validated Successfully." + leavename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave." + meghpitotalleavestaken.getExceptionDesc());
		}


		
		if (meghpitotalleavestaken.TotalLeavesTakenReportTotalDaysResultValidation(totaldays)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave TotalDays Validated Successfully." + totaldays);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave TotalDays." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}

		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveTypeCheckbox()) {

			logResults.createLogs("Y", "PASS", "LeaveType Checkbox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting LeaveType CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportStatusCheckbox()) {

			logResults.createLogs("Y", "PASS", "Status CheckBox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Status CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonCheckbox()) {

			logResults.createLogs("Y", "PASS", "Leave Reason CheckBox Selected Successfully." );
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Reason CheckBox." + meghpitotalleavestaken.getExceptionDesc());
		}

		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavestatus)) {

			logResults.createLogs("Y", "PASS", "Leave Status Inputted Successfully." + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Status ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveStatusResultValidation(leavestatus)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave Status Validated Successfully." + leavestatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Status." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportSearchTextField(leavereason)) {

			logResults.createLogs("Y", "PASS", "Leave Reason Inputted Successfully." + leavereason);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Leave Reason ." + meghpitotalleavestaken.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportLeaveReasonValidation(leavereason)) {

			logResults.createLogs("Y", "PASS", "Emp Applied Leave Reason Validated Successfully." + leavereason);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Applied Leave Reason." + meghpitotalleavestaken.getExceptionDesc());
		}
				}
		
				// MPI_1315_LeavesTakenRejected_Reports_03
				@Test(enabled = true, priority = 27, groups = { "Smoke" })
				public void MPI_1326_LeavesTakenPending_Reports_03()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha,subject,ccmail");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
						String subject = data.get(i++);
						String ccmail = data.get(i++);
						

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");
			
					MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportPendingTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Pending Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Pending Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		
		if (meghpiovertimepage.OverTimeReportEmailButton()) {

			logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On MailReportButton." + meghpiovertimepage.getExceptionDesc());
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
		
				// MPI_1328_LeavesTakenPending_Reports_05
				@Test(enabled = true, priority = 28, groups = { "Smoke" })
				public void MPI_1328_LeavesTakenPending_Reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportPendingTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Pending Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Pending Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1329_LeavesTakenPending_Reports_06
				@Test(enabled = true, priority = 29, groups = { "Smoke" })
				public void MPI_1329_LeavesTakenPending_Reports_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportPendingTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Pending Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Pending Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1330_LeavesTakenPending_Reports_07
				@Test(enabled = true, priority = 30, groups = { "Smoke" })
				public void MPI_1330_LeavesTakenPending_Reports_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type .");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportPendingTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Pending Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Pending Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1331_LeavesTakenPending_Reports_08
				@Test(enabled = true, priority = 31, groups = { "Smoke" })
				public void MPI_1331_LeavesTakenPending_Reports_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting month and year .");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportPendingTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Pending Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Pending Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpitotalleavestaken.FilteredDateValidation(year, monthName )) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
		} 	
				}
		
				// MPI_1332_LeavesTakenPending_Reports_09
				@Test(enabled = true, priority = 32, groups = { "Smoke" })
				public void MPI_1332_LeavesTakenPending_Reports_09()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date.");

					  // Load Excel data
					ArrayList<String> data = initBase.loadExcelData("TotalLeavesTaken_Reports", currTC,
							"password,captcha");
					
					  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
			

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

String monthFifthWorkingDate  = dateDetails.get("month5WorkingDate");
 String monthName = dateDetails.get("monthName");
    String year = dateDetails.get("year");
					
					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghPITotalLeavesTakenReportPage meghpitotalleavestaken = new MeghPITotalLeavesTakenReportPage(driver);
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
			
		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Login Is Failed." + MeghLoginPage.getExceptionDesc());
		}  
		if (RolePermissionpage.ReprtButton()) {

			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportButton()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportPendingTab()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Pending Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On TotalLeavesTaken Report Pending Tab Button ." + meghpitotalleavestaken.getExceptionDesc());
		}
		if (meghpitotalleavestaken.TotalLeavesTakenReportEmpNameRowDisplay()) {

			logResults.createLogs("Y", "PASS", "TotalLeavesTaken Report Page Loaded Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While TotalLeavesTaken Report Page Loading ." + meghpitotalleavestaken.getExceptionDesc());
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
		if (meghpitotalleavestaken.TotalLeavesTakenReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpitotalleavestaken.getExceptionDesc());
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
		
		if (meghpiattendancereport.FromDateSelected(monthFifthWorkingDate)) {

			logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + monthFifthWorkingDate);
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
		
		if (meghpiattendancereport.ToDateSelected(monthFifthWorkingDate)) {

			logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + monthFifthWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiattendancereport.FromDateDropDown()) {

			logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.FilterSaveButton()) {

			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpitotalleavestaken.LeavesTakenRptFilteredDateValidation(monthFifthWorkingDate)) {

			logResults.createLogs("Y", "PASS", "Filtered Record Displayed Successfully." + monthFifthWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filtered Records." + meghpitotalleavestaken.getExceptionDesc());
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
