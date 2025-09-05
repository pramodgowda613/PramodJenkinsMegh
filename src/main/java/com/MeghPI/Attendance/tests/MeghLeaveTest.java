package com.MeghPI.Attendance.tests;


import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.pages.MeghAttendancePolicyPage;
import com.MeghPI.Attendance.pages.MeghLeavePage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;


public class MeghLeaveTest {

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
    private String gradename = ""; 
    private String Empid = "";
    private String Empfirstname = "";
	
	
	
	@Parameters({ "device" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device) { // String param1
		initBase.browser = "chrome";
		driver = loadDriver.getDriver(device);

		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

	@Parameters({ "device" })
	@BeforeClass(alwaysRun = true)
	void runOnce(int device) {
		logResults.createReport(device);
		logResults.setTestMethodErrorCount(0);
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		entityname = "AutoEN" + initBase.executionRunTime;
		gradename = "AutoGN" + initBase.executionRunTime;
		Empid = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpID", "");
		Empfirstname = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpFirstName", "");
	}
	
	
	
	// MPI_612_Leave_14
		@Test(enabled = true, priority = 1, groups = { "Smoke" })
		public void MPI_612_Leave_14() throws InterruptedException {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, configure the sick leave on leave policy with 2 per month from joining date and as a employee apply sick leave and ensure status is pending ");

			ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
					"password,captcha,leavereason,date,leavename,leavestatus");

		
			String password = data.get(0);
			String captcha = data.get(1);
			String LeaveReason = data.get(2);
			String date = data.get(3);
			String leavename = data.get(4);
			String leavestatus = data.get(5);

			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			 MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
			
			if (meghlogintest.verifyCompanyLogin(cmpcode, Empid, Empid, captcha, logResults, driver)) {
				logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
			}
			
			if (RolePermissionpage.LeaveButton()) {

				logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
			}
			Thread.sleep(2000);
			if (RolePermissionpage.LeaveRequestsTab()) {

				logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
						+ RolePermissionpage.getExceptionDesc());
			}
			Thread.sleep(2000);

			

			if (meghleavepage.ApplyLeaveButton()) {

				logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully Of Employee Account: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button: "
						+ meghleavepage.getExceptionDesc());
			}	
		
			if (meghleavepage.LeaveTypeDropdown()) {

				logResults.createLogs("Y", "PASS", " LeaveTypeDropdown Clicked Successfully Of Employee Account: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveTypeDropdown: "
						+ meghleavepage.getExceptionDesc());
			}
	
			if (meghleavepage.SickLeaveSelected()) {

				logResults.createLogs("Y", "PASS", " SickLeave Selected  Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Sick Leave: "
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.FromDateTextField()) {

				logResults.createLogs("Y", "PASS", " FromDateTextField Clicked Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On FromDateTextField: "
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.FromDateSelected(date)) {

				logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting From From Date: "
						+ meghleavepage.getExceptionDesc());
			}
			Thread.sleep(4000);
	
			if (meghleavepage.LeaveDurationOne()) {

				logResults.createLogs("Y", "PASS", " LeaveDurationOne Clicked Successfully Of Employee Account: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationOne: "
						+ meghleavepage.getExceptionDesc());
			}
	
			if (meghleavepage.FullDayDurationOne()) {

				logResults.createLogs("Y", "PASS", " FullDay DurationOne Selected  Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting FullDay DurationOne: "
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.ToDateTextField()) {

				logResults.createLogs("Y", "PASS", " ToDateTextField Clicked Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On ToDateTextField: "
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.ToDateSelected(date)) {

				logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully : ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting From ToDate: "
						+ meghleavepage.getExceptionDesc());
			}
		
			if (meghleavepage.LeaveDurationTwo()) {

				logResults.createLogs("Y", "PASS", " LeaveDurationTwo Clicked Successfully Of Employee Account: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationTwo: "
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.FullDayDurationTwo()) {

				logResults.createLogs("Y", "PASS", " FullDayDurationTwo Clicked Successfully Of Employee Account: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On FullDayDurationTwo: "
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.ReasonTextField(LeaveReason)) {

				logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account: " + LeaveReason);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Reason: "
						+ meghleavepage.getExceptionDesc());
			}
		
			if (meghleavepage.RequestApplyButton()) {

				logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully Of Employee Account: " );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button: "
						+ meghleavepage.getExceptionDesc());
			}
			
			
			
			if (meghleavepage.LeaveModulePaginationInEmp()) {

				logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully Of Employee Account: " );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
						+ meghleavepage.getExceptionDesc());
			}
		
			if (meghleavepage.LeaveSearchTextField(leavename)) {

				logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
						+ meghleavepage.getExceptionDesc());
			}
			
			
	
			if (meghleavepage.verifyLeaveRowInEmp(leavename, date, leavestatus)) {

				logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully : " + leavename + date + leavestatus);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status: "
						+ meghleavepage.getExceptionDesc());
			}
			
			if (RolePermissionpage.ManageButton()) {
				logResults.createLogs("Y", "PASS", "Admin Manage Button Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Manage Button : " + RolePermissionpage.getExceptionDesc());
			}

			if (RolePermissionpage.LogoutButton()) {
				logResults.createLogs("Y", "PASS", "Admin Logout Button Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Logout Button : " + RolePermissionpage.getExceptionDesc());
			}
			
			if (MeghLoginPage.LoginVerify(cmpcode, Emailid, password, captcha, logResults)) {
				logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
			}
			
			if (RolePermissionpage.LeaveButton()) {

				logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
			}
			Thread.sleep(2000);
			if (RolePermissionpage.LeaveRequestsTab()) {

				logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
						+ RolePermissionpage.getExceptionDesc());
			}
			Thread.sleep(2000);
			
			if (EmployeePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
			}
			
			
			if (meghleavepage.LeaveTypeOptionSelected()) {

				logResults.createLogs("Y", "PASS", " Leave Type Option Selected  Successfully : " + leavename);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type Option From Search Dropdown: "
						+ meghleavepage.getExceptionDesc());
			}
			
			if (EmployeePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
			}
			
			if (meghleavepage.LeaveModulePaginationInAdmin()) {

				logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully : " );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
						+ meghleavepage.getExceptionDesc());
			}
			
			
			if (meghleavepage.LeaveSearchTextField(leavename)) {

				logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
						+ meghleavepage.getExceptionDesc());
			}
			
			if (meghleavepage.verifyLeaveRowInAdmin(Empfirstname, leavename, date, leavestatus)) {

				logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully : " + leavename + date + leavestatus + Empfirstname );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status: "
						+ meghleavepage.getExceptionDesc());
			}
		}
		
		// MPI_852_Leave_28
				@Test(enabled = true, priority = 2, groups = { "Smoke" })
				public void MPI_852_Leave_28() throws InterruptedException {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, revoke the leave request and ensure status is updated as \"revoked\" on both emp and admin side");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,date,leavename,leavestatus");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String date = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Empid, Empid, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
					}
					
					if (RolePermissionpage.LeaveButton()) {

						logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
					}
					Thread.sleep(2000);
					if (RolePermissionpage.LeaveRequestsTab()) {

						logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
								+ RolePermissionpage.getExceptionDesc());
					}
					Thread.sleep(2000);

					

					if (meghleavepage.ApplyLeaveButton()) {

						logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button: "
								+ meghleavepage.getExceptionDesc());
					}	
				
					if (meghleavepage.LeaveTypeDropdown()) {

						logResults.createLogs("Y", "PASS", " LeaveTypeDropdown Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveTypeDropdown: "
								+ meghleavepage.getExceptionDesc());
					}
			
					if (meghleavepage.CasualLeaveSelected()) {

						logResults.createLogs("Y", "PASS", " Casual Selected  Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Casual Leave: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.FromDateTextField()) {

						logResults.createLogs("Y", "PASS", " FromDateTextField Clicked Successfully : ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On FromDateTextField: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.FromDateSelected(date)) {

						logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully : ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting From From Date: "
								+ meghleavepage.getExceptionDesc());
					}
					Thread.sleep(4000);
			
					if (meghleavepage.LeaveDurationOne()) {

						logResults.createLogs("Y", "PASS", " LeaveDurationOne Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationOne: "
								+ meghleavepage.getExceptionDesc());
					}
			
					if (meghleavepage.FullDayDurationOne()) {

						logResults.createLogs("Y", "PASS", " FullDay DurationOne Selected  Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting FullDay DurationOne: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.ToDateTextField()) {

						logResults.createLogs("Y", "PASS", " ToDateTextField Clicked Successfully : ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On ToDateTextField: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.ToDateSelected(date)) {

						logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully : ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting From ToDate: "
								+ meghleavepage.getExceptionDesc());
					}
				
					if (meghleavepage.LeaveDurationTwo()) {

						logResults.createLogs("Y", "PASS", " LeaveDurationTwo Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationTwo: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.FullDayDurationTwo()) {

						logResults.createLogs("Y", "PASS", " FullDayDurationTwo Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On FullDayDurationTwo: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.ReasonTextField(LeaveReason)) {

						logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account: " + LeaveReason);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Reason: "
								+ meghleavepage.getExceptionDesc());
					}
				
					if (meghleavepage.RequestApplyButton()) {

						logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully Of Employee Account: " );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button: "
								+ meghleavepage.getExceptionDesc());
					}
					
					
					
					if (meghleavepage.LeaveModulePaginationInEmp()) {

						logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully Of Employee Account: " );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
								+ meghleavepage.getExceptionDesc());
					}
				
					if (meghleavepage.LeaveSearchTextField(leavename)) {

						logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
								+ meghleavepage.getExceptionDesc());
					}
					
					
					
					if (meghleavepage.clickRevokeButtonByDate(date)) {

						logResults.createLogs("Y", "PASS", " Revoke Button Clicked  Successfully : " + date);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Revoke Button : "
								+ meghleavepage.getExceptionDesc());
					}
					
					
					if (meghleavepage.RevokeButton()) {

						logResults.createLogs("Y", "PASS", " RevokeButton Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On RevokeButton: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveModulePaginationInEmp()) {

						logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully Of Employee Account: " );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
								+ meghleavepage.getExceptionDesc());
					}
				
					if (meghleavepage.LeaveSearchTextField(leavename)) {

						logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
								+ meghleavepage.getExceptionDesc());
					}
					
			
					if (meghleavepage.verifyLeaveRowInEmp(leavename, date, leavestatus)) {

						logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully : " + leavename + date + leavestatus);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (RolePermissionpage.ManageButton()) {
						logResults.createLogs("Y", "PASS", "Admin Manage Button Clicked Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On Manage Button : " + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.LogoutButton()) {
						logResults.createLogs("Y", "PASS", "Admin Logout Button Clicked Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On Logout Button : " + RolePermissionpage.getExceptionDesc());
					}
					
					if (MeghLoginPage.LoginVerify(cmpcode, Emailid, password, captcha, logResults)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
					}
					
					if (RolePermissionpage.LeaveButton()) {

						logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
					}
					Thread.sleep(2000);
					if (RolePermissionpage.LeaveRequestsTab()) {

						logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
								+ RolePermissionpage.getExceptionDesc());
					}
					Thread.sleep(2000);
					
					if (EmployeePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
					}
					
					
					if (meghleavepage.LeaveTypeOptionSelected()) {

						logResults.createLogs("Y", "PASS", " Leave Type Option Selected  Successfully : " + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type Option From Search Dropdown: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (EmployeePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveModulePaginationInAdmin()) {

						logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully : " );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
								+ meghleavepage.getExceptionDesc());
					}
					
					
					if (meghleavepage.LeaveSearchTextField(leavename)) {

						logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.verifyLeaveRowInAdmin(Empfirstname, leavename, date, leavestatus)) {

						logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully : " + leavename + date + leavestatus + Empfirstname);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status: "
								+ meghleavepage.getExceptionDesc());
					}
				}
	
		// MPI_851_Leave_27
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_851_Leave_27() throws InterruptedException {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, reject employee's leave request and check the status is  \"rejected\" ");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,date,leavename,leavestatus");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String date = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Empid, Empid, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
					}
					
					if (RolePermissionpage.LeaveButton()) {

						logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
					}
					Thread.sleep(2000);
					if (RolePermissionpage.LeaveRequestsTab()) {

						logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
								+ RolePermissionpage.getExceptionDesc());
					}
					Thread.sleep(2000);

					

					if (meghleavepage.ApplyLeaveButton()) {

						logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button: "
								+ meghleavepage.getExceptionDesc());
					}	
				
					if (meghleavepage.LeaveTypeDropdown()) {

						logResults.createLogs("Y", "PASS", " LeaveTypeDropdown Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveTypeDropdown: "
								+ meghleavepage.getExceptionDesc());
					}
			
					if (meghleavepage.SickLeaveSelected()) {

						logResults.createLogs("Y", "PASS", " SickLeave Selected  Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Sick Leave: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.FromDateTextField()) {

						logResults.createLogs("Y", "PASS", " FromDateTextField Clicked Successfully : ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On FromDateTextField: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.FromDateSelected(date)) {

						logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully : ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting From From Date: "
								+ meghleavepage.getExceptionDesc());
					}
					Thread.sleep(4000);
			
					if (meghleavepage.LeaveDurationOne()) {

						logResults.createLogs("Y", "PASS", " LeaveDurationOne Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationOne: "
								+ meghleavepage.getExceptionDesc());
					}
			
					if (meghleavepage.FullDayDurationOne()) {

						logResults.createLogs("Y", "PASS", " FullDay DurationOne Selected  Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting FullDay DurationOne: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.ToDateTextField()) {

						logResults.createLogs("Y", "PASS", " ToDateTextField Clicked Successfully : ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On ToDateTextField: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.ToDateSelected(date)) {

						logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully : ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting From ToDate: "
								+ meghleavepage.getExceptionDesc());
					}
				
					if (meghleavepage.LeaveDurationTwo()) {

						logResults.createLogs("Y", "PASS", " LeaveDurationTwo Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationTwo: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.FullDayDurationTwo()) {

						logResults.createLogs("Y", "PASS", " FullDayDurationTwo Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On FullDayDurationTwo: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.ReasonTextField(LeaveReason)) {

						logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account: " + LeaveReason);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Reason: "
								+ meghleavepage.getExceptionDesc());
					}
				
					if (meghleavepage.RequestApplyButton()) {

						logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully Of Employee Account: " );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button: "
								+ meghleavepage.getExceptionDesc());
					}
					
					
				
					
					
					if (RolePermissionpage.ManageButton()) {
						logResults.createLogs("Y", "PASS", "Admin Manage Button Clicked Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On Manage Button : " + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.LogoutButton()) {
						logResults.createLogs("Y", "PASS", "Admin Logout Button Clicked Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On Logout Button : " + RolePermissionpage.getExceptionDesc());
					}
					
					if (MeghLoginPage.LoginVerify(cmpcode, Emailid, password, captcha, logResults)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
					}
					
					if (RolePermissionpage.LeaveButton()) {

						logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
					}
					Thread.sleep(2000);
					if (RolePermissionpage.LeaveRequestsTab()) {

						logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
								+ RolePermissionpage.getExceptionDesc());
					}
					Thread.sleep(2000);
					
					if (EmployeePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
					}
					
					
					if (meghleavepage.LeaveTypeOptionSelected()) {

						logResults.createLogs("Y", "PASS", " Leave Type Option Selected  Successfully : " + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type Option From Search Dropdown: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (EmployeePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveModulePaginationInAdmin()) {

						logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully : " );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
								+ meghleavepage.getExceptionDesc());
					}
					
					
					if (meghleavepage.LeaveSearchTextField(leavename)) {

						logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
								+ meghleavepage.getExceptionDesc());
					}
					
				
					if (meghleavepage.clickRejectButtonByDate(date)) {

						logResults.createLogs("Y", "PASS", " Leave Rejected   Successfully : " + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Rejecting Leave: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveRejectionReason(LeaveReason)) {

						logResults.createLogs("Y", "PASS", " Leave Rejection Reason Inputted  Successfully : " + LeaveReason);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Rejection Reason: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveRejectButton()) {

						logResults.createLogs("Y", "PASS", " Leave Rejection  Button Clicked  Successfully : " + LeaveReason);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Reject Button: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (EmployeePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
					}
					
					
					if (meghleavepage.LeaveTypeOptionSelected()) {

						logResults.createLogs("Y", "PASS", " Leave Type Option Selected  Successfully : " + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type Option From Search Dropdown: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (EmployeePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
					}
					
					
					if (meghleavepage.LeaveSearchTextField(leavename)) {

						logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
								+ meghleavepage.getExceptionDesc());
					}
					
					
					
					if (meghleavepage.verifyLeaveRowInAdmin(Empfirstname, leavename, date, leavestatus)) {

						logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully : " + leavename + date + leavestatus + Empfirstname);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (RolePermissionpage.ManageButton()) {
						logResults.createLogs("Y", "PASS", "Admin Manage Button Clicked Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On Manage Button : " + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.LogoutButton()) {
						logResults.createLogs("Y", "PASS", "Admin Logout Button Clicked Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On Logout Button : " + RolePermissionpage.getExceptionDesc());
					}
					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Empid, Empid, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
					}
					
					if (RolePermissionpage.LeaveButton()) {

						logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
					}
					Thread.sleep(2000);
					if (RolePermissionpage.LeaveRequestsTab()) {

						logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account: ");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
								+ RolePermissionpage.getExceptionDesc());
					}
					Thread.sleep(2000);
					
					if (meghleavepage.LeaveModulePaginationInEmp()) {

						logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully : " );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
								+ meghleavepage.getExceptionDesc());
					}
					
					if (meghleavepage.LeaveSearchTextField(leavename)) {

						logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
								+ meghleavepage.getExceptionDesc());
					}
					
					
			
					if (meghleavepage.verifyLeaveRowInEmp(leavename, date, leavestatus)) {

						logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully : " + leavename + date + leavestatus);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status: "
								+ meghleavepage.getExceptionDesc());
					}
					
				}
	
	
				// MPI_854_Leave_30
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_854_Leave_30() throws InterruptedException {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, approve the leave request and ensure status is \"aproved\" on both admin and employee account  ");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,date,leavename,leavestatus");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String date = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
					
				if (meghlogintest.verifyCompanyLogin(cmpcode, Empid, Empid, captcha, logResults, driver)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
				}
				
				if (RolePermissionpage.LeaveButton()) {

					logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				if (RolePermissionpage.LeaveRequestsTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
							+ RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);

				

				if (meghleavepage.ApplyLeaveButton()) {

					logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button: "
							+ meghleavepage.getExceptionDesc());
				}	
			
				if (meghleavepage.LeaveTypeDropdown()) {

					logResults.createLogs("Y", "PASS", " LeaveTypeDropdown Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveTypeDropdown: "
							+ meghleavepage.getExceptionDesc());
				}
		
				if (meghleavepage.SickLeaveSelected()) {

					logResults.createLogs("Y", "PASS", " SickLeave Selected  Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Sick Leave: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromDateTextField()) {

					logResults.createLogs("Y", "PASS", " FromDateTextField Clicked Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On FromDateTextField: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromDateSelected(date)) {

					logResults.createLogs("Y", "PASS", " FromDate Inputted Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting From From Date: "
							+ meghleavepage.getExceptionDesc());
				}
				Thread.sleep(4000);
		
				if (meghleavepage.LeaveDurationOne()) {

					logResults.createLogs("Y", "PASS", " LeaveDurationOne Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationOne: "
							+ meghleavepage.getExceptionDesc());
				}
		
				if (meghleavepage.FullDayDurationOne()) {

					logResults.createLogs("Y", "PASS", " FullDay DurationOne Selected  Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting FullDay DurationOne: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToDateTextField()) {

					logResults.createLogs("Y", "PASS", " ToDateTextField Clicked Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On ToDateTextField: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToDateSelected(date)) {

					logResults.createLogs("Y", "PASS", " ToDate Selected Inputted Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting From ToDate: "
							+ meghleavepage.getExceptionDesc());
				}
			
				if (meghleavepage.LeaveDurationTwo()) {

					logResults.createLogs("Y", "PASS", " LeaveDurationTwo Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveDurationTwo: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FullDayDurationTwo()) {

					logResults.createLogs("Y", "PASS", " FullDayDurationTwo Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On FullDayDurationTwo: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ReasonTextField(LeaveReason)) {

					logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account: " + LeaveReason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Reason: "
							+ meghleavepage.getExceptionDesc());
				}
			
				if (meghleavepage.RequestApplyButton()) {

					logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully Of Employee Account: " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
			
				
				
				if (RolePermissionpage.ManageButton()) {
					logResults.createLogs("Y", "PASS", "Admin Manage Button Clicked Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On Manage Button : " + RolePermissionpage.getExceptionDesc());
				}

				if (RolePermissionpage.LogoutButton()) {
					logResults.createLogs("Y", "PASS", "Admin Logout Button Clicked Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On Logout Button : " + RolePermissionpage.getExceptionDesc());
				}
				
				if (MeghLoginPage.LoginVerify(cmpcode, Emailid, password, captcha, logResults)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
				}
				
				if (RolePermissionpage.LeaveButton()) {

					logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				if (RolePermissionpage.LeaveRequestsTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
							+ RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				
				if (EmployeePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveTypeOptionSelected()) {

					logResults.createLogs("Y", "PASS", " Leave Type Option Selected  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type Option From Search Dropdown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (EmployeePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveModulePaginationInAdmin()) {

					logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveSearchTextField(leavename)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
							+ meghleavepage.getExceptionDesc());
				}
				
			
				if (meghleavepage.approveLeaveByDate(date)) {

					logResults.createLogs("Y", "PASS", " Leave Approved   Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Aprroving Leave: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveApproveButton()) {

					logResults.createLogs("Y", "PASS", " Leave Approve  Button Clicked  Successfully : " + LeaveReason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Approve Button: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (EmployeePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveTypeOptionSelected()) {

					logResults.createLogs("Y", "PASS", " Leave Type Option Selected  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type Option From Search Dropdown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (EmployeePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveModulePaginationInAdmin()) {

					logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveSearchTextField(leavename)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.verifyLeaveRowInAdmin(Empfirstname, leavename, date, leavestatus)) {

					logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully : " + leavename + date + leavestatus + Empfirstname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (RolePermissionpage.ManageButton()) {
					logResults.createLogs("Y", "PASS", "Admin Manage Button Clicked Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On Manage Button : " + RolePermissionpage.getExceptionDesc());
				}

				if (RolePermissionpage.LogoutButton()) {
					logResults.createLogs("Y", "PASS", "Admin Logout Button Clicked Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On Logout Button : " + RolePermissionpage.getExceptionDesc());
				}
				
				if (meghlogintest.verifyCompanyLogin(cmpcode, Empid, Empid, captcha, logResults, driver)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
				}
				
				if (RolePermissionpage.LeaveButton()) {

					logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				if (RolePermissionpage.LeaveRequestsTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
							+ RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				
				if (meghleavepage.LeaveModulePaginationInEmp()) {

					logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveSearchTextField(leavename)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
		
				if (meghleavepage.verifyLeaveRowInEmp(leavename, date, leavestatus)) {

					logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully : " + leavename + date + leavestatus);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status: "
							+ meghleavepage.getExceptionDesc());
				}
				}
	
	
				// MPI_618_Leave_20
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_618_Leave_20() throws InterruptedException {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise a leave request for an employee from the admin account and ensure the leave is approved automatically, since the reporting person is the same admin who raised the request.");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,date,leavename,leavestatus");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String date = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
	
				
				if (MeghLoginPage.LoginVerify(cmpcode, Emailid, password, captcha, logResults)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
				}
				
				if (RolePermissionpage.LeaveButton()) {

					logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				if (RolePermissionpage.LeaveRequestsTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
							+ RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				
				if (meghleavepage.ApplyLeaveButton()) {

					logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button: "
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.LeaveForOthersButton()) {

					logResults.createLogs("Y", "PASS", "  Leave For Others Button Clicked Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave For Others Button: "
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.EmployeeSelectionDropDown()) {

					logResults.createLogs("Y", "PASS", "  Employee Selection DropDown Clicked Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Employee Selection DropDown: "
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.EmployeeInputField(Empfirstname)) {

					logResults.createLogs("Y", "PASS", " Emp Name Inputted  Successfully : " + Empfirstname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.EmpNameDisplayedAndSelected()) {

					logResults.createLogs("Y", "PASS", "  Employee Selected  Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Employee From DropDown: "
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.LeaveTypeDropDownOnAdmin()) {

					logResults.createLogs("Y", "PASS", "  LeaveType DropDown OnAdmin Clicked Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking  LeaveType DropDown OnAdmin: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveTypeInputtedOnAdmin(leavename)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Name: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveTypeSelectedOnAdmin()) {

					logResults.createLogs("Y", "PASS", "  Leave Type Selected  Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type From DropDown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromDateOnAdmin()) {

					logResults.createLogs("Y", "PASS", " Clicked On FromDate TextField In Admin  Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On From Date TextField: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromDateOnAdminSelected(date)) {

					logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully : " + date);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting  From Date in TextField: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromLeaveDurationDropdownAdmin()) {

					logResults.createLogs("Y", "PASS", " Clicked On FromLeaveDuration Dropdown In Admin  Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On FromLeave Duration Dropdown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromLeaveDurationSelectedInAdmin()) {

					logResults.createLogs("Y", "PASS", " Selected  FromLeaveDuration From Dropdown In Admin  Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting  FromLeave Duration From Dropdown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToDateTextFieldInAdmin()) {

					logResults.createLogs("Y", "PASS", " Clicked On ToDate TextField In Admin  Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On To Date TextField: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToDateTextFieldInAdminSelected(date)) {

					logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully : " + date);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting  TO Date in TextField: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToLeaveDurationDropDownInAdmin()) {

					logResults.createLogs("Y", "PASS", " Clicked On ToLeaveDuration Dropdown In Admin  Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On ToLeave Duration Dropdown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToLeaveDurationFullDaySelectedInAdmin()) {

					logResults.createLogs("Y", "PASS", " Selected  ToLeaveDuration From Dropdown In Admin  Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting  ToLeave Duration From Dropdown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ReasonTextField(LeaveReason)) {

					logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account: " + LeaveReason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Reason: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ApplyForOthersButton()) {

					logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (EmployeePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveTypeOptionSelected()) {

					logResults.createLogs("Y", "PASS", " Leave Type Option Selected  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type Option From Search Dropdown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (EmployeePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveModulePaginationInAdmin()) {

					logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveSearchTextField(leavename)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.verifyLeaveRowInAdmin(Empfirstname, leavename, date, leavestatus)) {

					logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully : " + leavename + date + leavestatus + Empfirstname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (RolePermissionpage.ManageButton()) {
					logResults.createLogs("Y", "PASS", "Admin Manage Button Clicked Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On Manage Button : " + RolePermissionpage.getExceptionDesc());
				}

				if (RolePermissionpage.LogoutButton()) {
					logResults.createLogs("Y", "PASS", "Admin Logout Button Clicked Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On Logout Button : " + RolePermissionpage.getExceptionDesc());
				}
				
				if (meghlogintest.verifyCompanyLogin(cmpcode, Empid, Empid, captcha, logResults, driver)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
				}
				
				if (RolePermissionpage.LeaveButton()) {

					logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				if (RolePermissionpage.LeaveRequestsTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
							+ RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				
				if (meghleavepage.LeaveModulePaginationInEmp()) {

					logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveSearchTextField(leavename)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
		
				if (meghleavepage.verifyLeaveRowInEmp(leavename, date, leavestatus)) {

					logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully : " + leavename + date + leavestatus);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status: "
							+ meghleavepage.getExceptionDesc());
				}
				}
				
				
				
				// MPI_625_Leave_26
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_625_Leave_26() throws InterruptedException {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, as a admin apply leave for himself and ensure leave is approved and leave is displaying for selected date on employee module");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,date,leavename,leavestatus,leaveduration");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String date = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);
					String LeaveDuration = data.get(6);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
	
				
				if (MeghLoginPage.LoginVerify(cmpcode, Emailid, password, captcha, logResults)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
				}
				
				if (RolePermissionpage.LeaveButton()) {

					logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				if (RolePermissionpage.LeaveRequestsTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
							+ RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				
				if (meghleavepage.ApplyLeaveButton()) {

					logResults.createLogs("Y", "PASS", " Apply Leave Button Clicked Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Leave Button: "
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.ApplyForMeButton()) {

					logResults.createLogs("Y", "PASS", "  Leave For Me Button Clicked Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave For Me Button: "
							+ meghleavepage.getExceptionDesc());
				}	
				
					
				
				
				
				if (meghleavepage.LeaveTypeSelectionDropDownOfLeaveForMe()) {

					logResults.createLogs("Y", "PASS", "  LeaveType DropDown OnAdmin Clicked Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking  LeaveType DropDown OnAdmin: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveTypeNameTextFieldOfLeaveForMe(leavename)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Name: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveTypeSelectedOfLeaveForMe()) {

					logResults.createLogs("Y", "PASS", "  Leave Type Selected  Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type From DropDown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromDateClickedLeaveForMe()) {

					logResults.createLogs("Y", "PASS", " Clicked On FromDate TextField In Admin  Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On From Date TextField: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromDateSelectedLeaveForMe(date)) {

					logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully : " + date);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting  From Date in TextField: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FromLeaveDurationSelectedOfLeaveForMe(LeaveDuration)) {

					logResults.createLogs("Y", "PASS", " Clicked On FromLeaveDuration Dropdown In Admin  Successfully : " + LeaveDuration);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On FromLeave Duration Dropdown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				
				if (meghleavepage.ToDateClickedLeaveForMe()) {

					logResults.createLogs("Y", "PASS", " Clicked On ToDate TextField In Admin  Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On To Date TextField: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ToDateSelectedLeaveForMe(date)) {

					logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully : " + date);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting  TO Date in TextField: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				
				if (meghleavepage.ToLeaveDurationSelectedOfLeaveForMe(LeaveDuration)) {

					logResults.createLogs("Y", "PASS", " Selected  ToLeaveDuration From Dropdown In Admin  Successfully : " + LeaveDuration);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting  ToLeave Duration From Dropdown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveReasonOfLeaveForMe(LeaveReason)) {

					logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account: " + LeaveReason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Reason: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ApplyButtonOfLeaveForMe()) {

					logResults.createLogs("Y", "PASS", "Leave Request Apply Button Clicked  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Button: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (RolePermissionpage.EmployeeToggleSwitch()) {

					logResults.createLogs("Y", "PASS", "Employee Toggle Switch Successfully To Switch To Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Toggle Switch: " + RolePermissionpage.getExceptionDesc());
				}
				
				

				
				if (RolePermissionpage.LeaveButton()) {

					logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				if (RolePermissionpage.LeaveRequestsTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
							+ RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				
				if (meghleavepage.LeaveModulePaginationInEmp()) {

					logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveSearchTextField(leavename)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
		
				if (meghleavepage.verifyLeaveRowInEmp(leavename, date, leavestatus)) {

					logResults.createLogs("Y", "PASS", " Applied Leave Validated  Successfully : " + leavename + date + leavestatus);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Validating Created Leave and Its status: "
							+ meghleavepage.getExceptionDesc());
				}
				}	
				
				
				// MPI_599_Leave_01
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_599_Leave_01() throws InterruptedException {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature by inputting employee id, last name and first name");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,date,leavename,leavestatus,leaveduration");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String date = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);
					String LeaveDuration = data.get(6);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
	
				
				if (MeghLoginPage.LoginVerify(cmpcode, Emailid, password, captcha, logResults)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
				}
				
				if (RolePermissionpage.LeaveButton()) {

					logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				if (RolePermissionpage.LeaveRequestsTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
							+ RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				
				if (EmployeePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
				}
				
				
				if (meghleavepage.EmpIDCheckBoxSearchOption()) {

					logResults.createLogs("Y", "PASS", " Emp Id  Option Selected  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Emp ID  Option From Search Dropdown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.ReasonCheckBoxSearchOption()) {

					logResults.createLogs("Y", "PASS", "Reason CheckBox  Option Selected  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Reason  Option From Search Dropdown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (EmployeePage.SearchDropDown()) {
					logResults.createLogs("Y", "PASS", "Clicked On SearchDropDown Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error while Clicking On SearchDropDown: " + EmployeePage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveSearchTextField(Empid)) {

					logResults.createLogs("Y", "PASS", " Leave Name Inputted  Successfully : " + Empid);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting LeaveName: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.EmpIDInTable(Empid)) {

					logResults.createLogs("Y", "PASS", " Based On Emp Id Search Result Displayed  Successfully : " + Empid);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Search Result For Emp Id: "
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.LeaveSearchTextField(LeaveReason)) {

					logResults.createLogs("Y", "PASS", " Leave Reason Inputted  Successfully : " + LeaveReason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Leave Reason: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.EmpIDInTable(LeaveReason)) {

					logResults.createLogs("Y", "PASS", " Based On Leave Reason Search Result Displayed  Successfully : " + LeaveReason);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Search Result For Leave Reason: "
							+ meghleavepage.getExceptionDesc());
				}	
	
				}
				
				// MPI_621_Leave_23
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_621_Leave_23() throws InterruptedException {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by filtering office name, dept, team, designation, employee type and leave types");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,date,leavename,leavestatus,leaveduration,filterleavetype");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String date = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);
					String LeaveDuration = data.get(6);
					String filterleavetype = data.get(7);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
	
				
				if (MeghLoginPage.LoginVerify(cmpcode, Emailid, password, captcha, logResults)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
				}
				
				if (RolePermissionpage.LeaveButton()) {

					logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				if (RolePermissionpage.LeaveRequestsTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
							+ RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				
				
				if (meghleavepage.FilterButton()) {

					logResults.createLogs("Y", "PASS", " Filter Button Clicked Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button: "
							+ meghleavepage.getExceptionDesc());
				}	
				
				
				
				if (meghleavepage.OfficeDrpDownClicked()) {

					logResults.createLogs("Y", "PASS", " Office DrpDown Clicked Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Office DropDown: "
							+ meghleavepage.getExceptionDesc());
				}	

				if (meghleavepage.OfficeNameInputted(officename)) {

					logResults.createLogs("Y", "PASS", " Office Name Inputted Successfully : " + officename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Office Name: "
							+ meghleavepage.getExceptionDesc());
				}		
				
				
				
				if (meghleavepage.OfficeNameSelectedOfFilter()) {

					logResults.createLogs("Y", "PASS", " Office Name Selected Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting  Office Name: "
							+ meghleavepage.getExceptionDesc());
				}	
				
				
				if (meghleavepage.DeptDropDownClicked()) {

					logResults.createLogs("Y", "PASS", " Dept DropDown Clicked   Successfully : " + departmentname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Dept DropDown: "
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.DeptDropDownSelected()) {

					logResults.createLogs("Y", "PASS", " Dept Selected Successfully : " + departmentname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Dept Name: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.TeamDropDownClicked()) {

					logResults.createLogs("Y", "PASS", " Team DropDown Clicked   Successfully : " + teamname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Team DropDown: "
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.TeamDropDownSelected()) {

					logResults.createLogs("Y", "PASS", " Team Selected Successfully : " + teamname);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Team Name: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.SaveFilterButton()) {

					logResults.createLogs("Y", "PASS", " Clicked On Save Filter Button Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button : "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FilterResults()) {

					logResults.createLogs("Y", "PASS", "Applied Filted Results Displayed Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Applied Filter Results : "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FilterButton()) {

					logResults.createLogs("Y", "PASS", " Filter Button Clicked Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveTypesFilter(leavename)) {

					logResults.createLogs("Y", "PASS", "  Leave Type Selected  Successfully : " + leavename);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Leave Type From DropDown: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.SaveFilterButton()) {

					logResults.createLogs("Y", "PASS", " Clicked On Save Filter Button Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button : "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.LeaveTypesearchResults(filterleavetype)) {

					logResults.createLogs("Y", "PASS", "Applied Filted Results Displayed Successfully For Leave Name : " + filterleavetype);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Applied Filter Results For Leave Type: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.FilterButton()) {

					logResults.createLogs("Y", "PASS", " Filter Button Clicked Successfully : ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.LeaveStatusFilter(leavestatus)) {

					logResults.createLogs("Y", "PASS", "Selected Leave Status Filter Successfully : " + leavestatus);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting  Leave Status Filter : "
							+ meghleavepage.getExceptionDesc());
				}	
				
				if (meghleavepage.SaveFilterButton()) {

					logResults.createLogs("Y", "PASS", " Clicked On Save Filter Button Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Filter Button : "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				
				if (meghleavepage.LeaveStatusSearchResults(leavestatus)) {

					logResults.createLogs("Y", "PASS", "Applied Filted Results Displayed Successfully For Leave Status : " + leavestatus);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Applied Filter Results For Leave Status: "
							+ meghleavepage.getExceptionDesc());
				}
		
				}
				
				// MPI_603_Leave_05
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_603_Leave_05() throws InterruptedException {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check whether the counts for 'Pending', 'Approved', 'Rejected', 'Revoked', and the total 'Applied Leaves' are displayed accurately according to the number of records.");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,date,leavename,leavestatus");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String date = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
					
				if (MeghLoginPage.LoginVerify(cmpcode, Emailid, password, captcha, logResults)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
				}
				
				if (RolePermissionpage.LeaveButton()) {

					logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of HR Account: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				if (RolePermissionpage.LeaveRequestsTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of HR Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
							+ RolePermissionpage.getExceptionDesc());
				}
			
	
				if (meghleavepage.LeaveModulePaginationInAdmin()) {

					logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				
				if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.compareLeaveStatistics()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary Count Is Verified With No Of Records Present In Table  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Verifying  Monthly Leave Summary With Table: "
							+ meghleavepage.getExceptionDesc());
				}
		
				}
				
				// MPI_856_Leave_31
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_856_Leave_31() throws InterruptedException {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check whether the counts for 'Pending', 'Approved', 'Rejected', 'Revoked', and the total 'Applied Leaves' are displayed accurately according to the number of records in employee account");

					ArrayList<String> data = initBase.loadExcelData("Leave", currTC,
							"password,captcha,leavereason,date,leavename,leavestatus");

				
					String password = data.get(0);
					String captcha = data.get(1);
					String LeaveReason = data.get(2);
					String date = data.get(3);
					String leavename = data.get(4);
					String leavestatus = data.get(5);

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					 MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghLeavePage meghleavepage = new MeghLeavePage(driver);
				MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
					
				if (meghlogintest.verifyCompanyLogin(cmpcode, Empid, Empid, captcha, logResults, driver)) {
					logResults.createLogs("Y", "PASS", "Login Done Successfully: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Login Is Failed: " + MeghLoginPage.getExceptionDesc());
				}
				
				if (RolePermissionpage.LeaveButton()) {

					logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Leave Button: " + RolePermissionpage.getExceptionDesc());
				}
				Thread.sleep(2000);
				if (RolePermissionpage.LeaveRequestsTab()) {

					logResults.createLogs("Y", "PASS", " EmpLeaveRequestsTab Clicked Successfully Of Employee Account: ");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module EmpLeaveRequestsTab: "
							+ RolePermissionpage.getExceptionDesc());
				}
	
				if (meghleavepage.LeaveModulePaginationInEmp()) {

					logResults.createLogs("Y", "PASS", "Leave Request Page 100 Per Page Selected  Successfully Of Employee Account: " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Selecting Pagination: "
							+ meghleavepage.getExceptionDesc());
				}
				
				
				if (meghleavepage.MonthlyLeaveSummaryDrpIcon()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary DrpIcon Clicked  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Monthly Leave Summary: "
							+ meghleavepage.getExceptionDesc());
				}
				
				if (meghleavepage.compareLeaveStatisticsOfEmp()) {

					logResults.createLogs("Y", "PASS", "Monthly Leave Summary Count Is Verified With No Of Records Present In Table  Successfully : " );
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Verifying  Monthly Leave Summary With Table: "
							+ meghleavepage.getExceptionDesc());
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
