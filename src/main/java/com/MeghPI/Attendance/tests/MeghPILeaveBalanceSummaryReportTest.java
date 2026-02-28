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
import com.MeghPI.Attendance.pages.MeghPILeaveBalanceSummaryReportPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeMonthlyReportPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPILeaveBalanceSummaryReportTest {

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
    private String EmpFirstName = "";
    private String AdminFirstName ="";
    private String LeavePolicyName = "";
	
	
    @Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
    public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
		//driver = loadDriver.getDriver(device);

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
		
		EmpFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpFirstName", "");
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");
		
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		LeavePolicyName = "AutoLeavePN" +  initBase.executionRunTime;
		
	}
	
 // MPI_1362_LeaveBalanceSummaryReport_01
 	@Test(enabled = true, priority = 01, groups = { "Smoke" })
 	public void MPI_1362_LeaveBalanceSummaryReport_01()  {
 		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
 		logResults.createExtentReport(currTC);
 		logResults.setScenarioName(
 				"To verify this, check the functionalities of search feature by selecting each search option");

 		ArrayList<String> data = initBase.loadExcelData("LeaveBalanceSummary_Reports", currTC,
 				"password,captcha");

 		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
 	
 		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
 	
 		MeghLoginTest meghlogintest = new MeghLoginTest();
 		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
 		MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
 		
 		MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);
 		MeghPILeaveBalanceSummaryReportPage meghpileavebalancesummaryreport = new MeghPILeaveBalanceSummaryReportPage(driver);
 		
 		int i = 0;
 		String password = data.get(i++);
 		String captcha = data.get(i++);
 		String EmpIDRow ="";
 		String EmpLastName ="";
 		

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
 	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportButton()) {

 				logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Button Clicked Successfully.");
 			} else {
 				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveBalanceSummary Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
 			}
 	       
 			if (OfficePage.SearchDropDown()) {
 				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
 			} else {
 				logResults.createLogs("Y", "FAIL",
 						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
 			}
 			if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportEmpIDSearchResult()) {
 				EmpIDRow =  meghpileavebalancesummaryreport.getempid;
 				logResults.createLogs("Y", "PASS", "Emp ID Extracted Successfully.");
 			} else {
 				logResults.createLogs("Y", "FAIL", "Error While Extracting Emp ID." + meghpileavebalancesummaryreport.getExceptionDesc());
 			}
 			if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportEmpNameSearchResult()) {
 				EmpLastName = meghpileavebalancesummaryreport.getemplastname;
 				logResults.createLogs("Y", "PASS", "Emp Last Name Extracted Successfully.");
 			} else {
 				logResults.createLogs("Y", "FAIL", "Error While Extracting Emp Last Name." + meghpileavebalancesummaryreport.getExceptionDesc());
 			}
 			 if (meghpiovertimepage.OverTimeReportEnrollIDCheckBox()) {
 				 
 				 
 					logResults.createLogs("Y", "PASS", "Emp ID Search Option CheckBox Clicked Successfully.");
 				} else {
 					logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp id CheckBox." + meghpiovertimepage.getExceptionDesc());
 				}
 			 if (meghpiovertimepage.OverTimeReportLastNameCheckBox()) {

 					logResults.createLogs("Y", "PASS", "Emp LastName Search Option CheckBox Clicked Successfully.");
 				} else {
 					logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp LastName CheckBox." + meghpiovertimepage.getExceptionDesc());
 				}
 			 if (OfficePage.SearchDropDown()) {
 					logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
 				} else {
 					logResults.createLogs("Y", "FAIL",
 							"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
 				}
 			
 	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportSearchTextField(EmpIDRow)) {

 				logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + EmpIDRow);
 			} else {
 				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpileavebalancesummaryreport.getExceptionDesc());
 			}			
 	        
 	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportEmpIDRecordValidation(EmpIDRow)) {

 				logResults.createLogs("Y", "PASS", "Emp ID Displayed Successfully." + EmpIDRow);
 			} else {
 				logResults.createLogs("Y", "FAIL", "Error While Displaying Emp ID." + meghpileavebalancesummaryreport.getExceptionDesc());
 			}	
 	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportSearchTextField(EmpLastName)) {

 				logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + EmpLastName);
 			} else {
 				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpileavebalancesummaryreport.getExceptionDesc());
 			}	
 	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportEmpNameRecordValidation(EmpLastName)) {

 				logResults.createLogs("Y", "PASS", "Inputted Emp Last Name Record Displayed Successfully." + EmpLastName);
 			} else {
 				logResults.createLogs("Y", "FAIL", "Error While Displaying Emp Last Name Record." + meghpileavebalancesummaryreport.getExceptionDesc());
 			}
 	} 
    
 // MPI_1363_LeaveBalanceSummaryReport_02
  	@Test(enabled = true, priority = 02, groups = { "Smoke" })
  	public void MPI_1363_LeaveBalanceSummaryReport_02()  {
  		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
  		logResults.createExtentReport(currTC);
  		logResults.setScenarioName(
  				"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

  		ArrayList<String> data = initBase.loadExcelData("LeaveBalanceSummary_Reports", currTC,
  				"subject,ccmail");

  	
  		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
  	
  		
  		MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
  		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
  		MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);
  		MeghPILeaveBalanceSummaryReportPage meghpileavebalancesummaryreport = new MeghPILeaveBalanceSummaryReportPage(driver);
  		
  		
  		String subject = data.get(0);
  		String ccmail = data.get(1);
  		

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
  	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportButton()) {

  				logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Button Clicked Successfully.");
  			} else {
  				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveBalanceSummary Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
  			}
  	      if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportRowResult()) {

  			logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Page Loaded Successfully.");
  		} else {
  			logResults.createLogs("Y", "FAIL", "Error While Loading  LeaveBalanceSummary Report Page." + meghpileavebalancesummaryreport.getExceptionDesc());
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
    
  	 // MPI_1365_LeaveBalanceSummaryReport_04
  	@Test(enabled = true, priority = 03, groups = { "Smoke" })
  	public void MPI_1365_LeaveBalanceSummaryReport_04()  {
  		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
  		logResults.createExtentReport(currTC);
  		logResults.setScenarioName(
  				"To verify this, check the functionality of filter feature by selecting specific team");

  		//ArrayList<String> data = initBase.loadExcelData("LeaveBalanceSummary_Reports", currTC, "password,captcha");

  	
  		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
  	
  		
  		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
  		MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);
  		MeghPILeaveBalanceSummaryReportPage meghpileavebalancesummaryreport = new MeghPILeaveBalanceSummaryReportPage(driver);
  		
  		
  		

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
  	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportButton()) {

  				logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Button Clicked Successfully.");
  			} else {
  				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveBalanceSummary Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
  			}
  	      if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportRowResult()) {

  			logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Page Loaded Successfully.");
  		} else {
  			logResults.createLogs("Y", "FAIL", "Error While Loading  LeaveBalanceSummary Report Page." + meghpileavebalancesummaryreport.getExceptionDesc());
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
		if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportFilterButton()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Button." + meghpileavebalancesummaryreport.getExceptionDesc());
		}
		if (meghpiattendancereport.TeamDropDown(teamname)) {

			logResults.createLogs("Y", "PASS", "Team Selected Successfully." + teamname );
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Team Name." + meghpiattendancereport.getExceptionDesc());
		}
		
		if (meghpiattendancereport.FilterSaveButton()) {

			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportSearchTextField(EmpFirstName)) {

			logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + EmpFirstName);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpileavebalancesummaryreport.getExceptionDesc());
		}	
		
		if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportEmpNameRecordValidation(EmpFirstName)) {

			logResults.createLogs("Y", "PASS", "Filtered Applied Emp Record Displayed Successfully." + EmpFirstName);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filter Applied Emp Records." + meghpileavebalancesummaryreport.getExceptionDesc());
		} 	
  	}
    
 // MPI_1366_LeaveBalanceSummaryReport_05
   	@Test(enabled = true, priority = 04, groups = { "Smoke" })
   	public void MPI_1366_LeaveBalanceSummaryReport_05()  {
   		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
   		logResults.createExtentReport(currTC);
   		logResults.setScenarioName(
   				"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

   	//	ArrayList<String> data = initBase.loadExcelData("LeaveBalanceSummary_Reports", currTC, "password,captcha");

   	
   		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
   	
   	
   		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
   		MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);
   		MeghPILeaveBalanceSummaryReportPage meghpileavebalancesummaryreport = new MeghPILeaveBalanceSummaryReportPage(driver);
   		
   		
   		

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
   	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportButton()) {

   				logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Button Clicked Successfully.");
   			} else {
   				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveBalanceSummary Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
   			}
   	      if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportRowResult()) {

   			logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Page Loaded Successfully.");
   		} else {
   			logResults.createLogs("Y", "FAIL", "Error While Loading  LeaveBalanceSummary Report Page." + meghpileavebalancesummaryreport.getExceptionDesc());
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
 		if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportFilterButton()) {

 			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
 		} else {
 			logResults.createLogs("Y", "FAIL",
 					"Error While Clicking On Filter Button." + meghpileavebalancesummaryreport.getExceptionDesc());
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
 		
 		if (meghpiattendancereport.FilterSaveButton()) {

 			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
 		} else {
 			logResults.createLogs("Y", "FAIL",
 					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
 		}
 		if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportSearchTextField(EmpFirstName)) {

 			logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + EmpFirstName);
 		} else {
 			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpileavebalancesummaryreport.getExceptionDesc());
 		}	
 		
 		if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportEmpNameRecordValidation(EmpFirstName)) {

 			logResults.createLogs("Y", "PASS", "Filtered Applied Emp Record Displayed Successfully." + EmpFirstName);
 		} else {
 			logResults.createLogs("Y", "FAIL", "Error While Displaying Filter Applied Emp Records." + meghpileavebalancesummaryreport.getExceptionDesc());
 		} 	
   	}
    
    // MPI_1367_LeaveBalanceSummaryReport_06
   	@Test(enabled = true, priority = 05, groups = { "Smoke" })
   	public void MPI_1367_LeaveBalanceSummaryReport_06()  {
   		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
   		logResults.createExtentReport(currTC);
   		logResults.setScenarioName(
   				"To verify this, check the functionality of filter feature by selecting employee type");

   	//	ArrayList<String> data = initBase.loadExcelData("LeaveBalanceSummary_Reports", currTC, "password,captcha");

   	
   		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
   	
   		
   		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
   		MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);
   		MeghPILeaveBalanceSummaryReportPage meghpileavebalancesummaryreport = new MeghPILeaveBalanceSummaryReportPage(driver);
   		
   		
   		

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
   	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportButton()) {

   				logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Button Clicked Successfully.");
   			} else {
   				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveBalanceSummary Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
   			}
   	      if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportRowResult()) {

   			logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Page Loaded Successfully.");
   		} else {
   			logResults.createLogs("Y", "FAIL", "Error While Loading  LeaveBalanceSummary Report Page." + meghpileavebalancesummaryreport.getExceptionDesc());
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
 		if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportFilterButton()) {

 			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
 		} else {
 			logResults.createLogs("Y", "FAIL",
 					"Error While Clicking On Filter Button." + meghpileavebalancesummaryreport.getExceptionDesc());
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

 		
 		if (meghpiattendancereport.FilterSaveButton()) {

 			logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
 		} else {
 			logResults.createLogs("Y", "FAIL",
 					"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
 		}
 		if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportSearchTextField(EmpFirstName)) {

 			logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + EmpFirstName);
 		} else {
 			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpileavebalancesummaryreport.getExceptionDesc());
 		}	
 		
 		if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportEmpNameRecordValidation(EmpFirstName)) {

 			logResults.createLogs("Y", "PASS", "Filtered Applied Emp Record Displayed Successfully." + EmpFirstName);
 		} else {
 			logResults.createLogs("Y", "FAIL", "Error While Displaying Filter Applied Emp Records." + meghpileavebalancesummaryreport.getExceptionDesc());
 		} 	
   	}
    
    // MPI_1368_LeaveBalanceSummaryReport_07
   	@Test(enabled = true, priority = 06, groups = { "Smoke" })
   	public void MPI_1368_LeaveBalanceSummaryReport_07()  {
   		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
   		logResults.createExtentReport(currTC);
   		logResults.setScenarioName(
   				"To verify this, check the filter feature functionality by selecting year and month");

   	//	ArrayList<String> data = initBase.loadExcelData("LeaveBalanceSummary_Reports", currTC, "password,captcha");

   		
   	
   		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
   	
   		
   		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
   		MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);
   		MeghPILeaveBalanceSummaryReportPage meghpileavebalancesummaryreport = new MeghPILeaveBalanceSummaryReportPage(driver);
   		
   	
   	 Map<String, String> details = Pramod.getPreviousWorkingDayDetails();

  
     String monthName = details.get("monthName");
     String year = details.get("year");
   		

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
   	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportButton()) {

   				logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Button Clicked Successfully.");
   			} else {
   				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveBalanceSummary Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
   			}
   	      if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportRowResult()) {

   			logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Page Loaded Successfully.");
   		} else {
   			logResults.createLogs("Y", "FAIL", "Error While Loading  LeaveBalanceSummary Report Page." + meghpileavebalancesummaryreport.getExceptionDesc());
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
 		if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportFilterButton()) {

 			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
 		} else {
 			logResults.createLogs("Y", "FAIL",
 					"Error While Clicking On Filter Button." + meghpileavebalancesummaryreport.getExceptionDesc());
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
 		if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportSearchTextField(EmpFirstName)) {

 			logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + EmpFirstName);
 		} else {
 			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpileavebalancesummaryreport.getExceptionDesc());
 		}	
 		
 		if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportEmpNameRecordValidation(EmpFirstName)) {

 			logResults.createLogs("Y", "PASS", "Filtered Applied Emp Record Displayed Successfully." + EmpFirstName);
 		} else {
 			logResults.createLogs("Y", "FAIL", "Error While Displaying Filter Applied Emp Records." + meghpileavebalancesummaryreport.getExceptionDesc());
 		} 	
   	}
    
 // MPI_1372_LeaveBalanceSummaryReport_11
 		@Test(enabled = true, priority = 7, groups = { "Smoke" })
 		public void MPI_1372_LeaveBalanceSummaryReport_11()   {
 			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
 			logResults.createExtentReport(currTC);
 			logResults.setScenarioName(
 					"To verify this, apply leave and ensure leave taken count is increased as per approved leaves count");

 			  // Load Excel data
 			ArrayList<String> data = initBase.loadExcelData("LeaveBalanceSummary_Reports", currTC,
 					"password,baseuri,loginendpoint,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,photo,pin,leavename,leavereason,totaldays,leavestatus");


 			  int i = 0;
 			String password = data.get(i++);
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
 		       
 		     
 		        
 		        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

 		       Map<String, String> details = Pramod.getFirstAndSecondWorkingDatesOfMonth();

 		      String monthFirstWorkingDate = details.get("monthFirstWorkingDate");
 		      String monthSecondWorkingDate = details.get("monthSecondWorkingDate");
 		      String firstDayOfMonth = details.get("firstDayOfMonth");
 				 
 				 MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
 					MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
 					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
 			MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
 			MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
 		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
 			MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
 			MeghLeavePage meghleavepage = new MeghLeavePage(driver);
 	 		MeghPILeaveBalanceSummaryReportPage meghpileavebalancesummaryreport = new MeghPILeaveBalanceSummaryReportPage(driver);

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
 		    
 		
 		   MeghLoginPage meghloginpage = new MeghLoginPage(driver);


 			if (meghloginpage.MainLandingPage()) {
 				logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
 			} else {
 				logResults.createLogs("Y", "FAIL",
 						"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
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
 						  if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportButton()) {

 				 				logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Button Clicked Successfully.");
 				 			} else {
 				 				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveBalanceSummary Report Button." + meghpileavebalancesummaryreport.getExceptionDesc());
 				 			}
 						 if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportRowResult()) {

 				  			logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Page Loaded Successfully.");
 				  		} else {
 				  			logResults.createLogs("Y", "FAIL", "Error While Loading  LeaveBalanceSummary Report Page." + meghpileavebalancesummaryreport.getExceptionDesc());
 				  		}
 						 if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportSearchTextField(firstname)) {

 			 				logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + firstname);
 			 			} else {
 			 				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpileavebalancesummaryreport.getExceptionDesc());
 			 			}
 						
 						 if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportLeaveTakenCount(totaldays)) {

  			 				logResults.createLogs("Y", "PASS", "Emp Leaves Taken Validated Successfully." + totaldays);
  			 			} else {
  			 				logResults.createLogs("Y", "FAIL", "Error While Validating Emp Leave Taken Count." + meghpileavebalancesummaryreport.getExceptionDesc());
  			 			}
 		}
    
 	// MPI_1373_LeaveBalanceSummaryReport_12
 	 	@Test(enabled = true, priority = 8, groups = { "Smoke" })
 	 	public void MPI_1373_LeaveBalanceSummaryReport_12()  {
 	 		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
 	 		logResults.createExtentReport(currTC);
 	 		logResults.setScenarioName(
 	 				"To verify this, apply sick leave for an employee and ensure leave taken count displayed correctly against total sick leaves");

 	 		ArrayList<String> data = initBase.loadExcelData("LeaveBalanceSummary_Reports", currTC,
 	 				"firstname,leavename,leavepending");

 	 	
 	 		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
 	 	
 	 		
 	 		MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);
 	 		MeghPILeaveBalanceSummaryReportPage meghpileavebalancesummaryreport = new MeghPILeaveBalanceSummaryReportPage(driver);
 	 		
 	 		
 	 		
 	 		String firstname = data.get(0);
 	 		String leavename = data.get(1);
 	 		String leavepending = data.get(2);
 	 		
 	 		

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
 	 	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportButton()) {

 	 				logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Button Clicked Successfully.");
 	 			} else {
 	 				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveBalanceSummary Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
 	 			}
 	 	      if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportRowResult()) {

 	   			logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Page Loaded Successfully.");
 	   		} else {
 	   			logResults.createLogs("Y", "FAIL", "Error While Loading  LeaveBalanceSummary Report Page." + meghpileavebalancesummaryreport.getExceptionDesc());
 	   		}
 	 	      if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportSearchTextField(firstname)) {

	 				logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + firstname);
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
				
				 if (meghpileavebalancesummaryreport.verifyLeaveTypeValue(leavename, leavepending)) {

	 				logResults.createLogs("Y", "PASS", "Emp Sick Leave Balance Validated Successfully." + leavename + " " + leavepending );
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Validating Emp Sick Leave Balance Count." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
	
 	 	}   
 	 	        
 	// MPI_1374_LeaveBalanceSummaryReport_13
 	 	@Test(enabled = true, priority = 9, groups = { "Smoke" })
 	 	public void MPI_1374_LeaveBalanceSummaryReport_13()  {
 	 		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
 	 		logResults.createExtentReport(currTC);
 	 		logResults.setScenarioName(
 	 				"To verify this, edit the leave balance for an employee and add leaves and check whether count is incremented in leave balance summary report");

 	 		ArrayList<String> data = initBase.loadExcelData("LeaveBalanceSummary_Reports", currTC,
 	 				"firstname,leavename,leavepending,leavecount");

 	 		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
 	 	
 	 	
 	 	
 	 		MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);
 	 		MeghPILeaveBalanceSummaryReportPage meghpileavebalancesummaryreport = new MeghPILeaveBalanceSummaryReportPage(driver);
 	 		
 	 		int i = 0;
 	 	
 	 		String firstname = data.get(i++);
 	 		String leavename = data.get(i++);
 	 		String leavepending = data.get(i++);
 	 		String leavecount = data.get(i++);
 	 		
 	 		

 	 		 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


 			if (meghloginpage.MainLandingPage()) {
 				logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
 			} else {
 				logResults.createLogs("Y", "FAIL",
 						"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
 			}		
 	 		
 	 		if (RolePermissionpage.LeaveButton()) {

				logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
			}
		
			if (RolePermissionpage.LeaveBalanceTab()) {

				logResults.createLogs("Y", "PASS", " LeaveBalanceTab Clicked Successfully Of Employee Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module LeaveBalanceTab."
						+ RolePermissionpage.getExceptionDesc());
			}
	
			 if (meghpileavebalancesummaryreport.LeaveBalanceSearchTextField(firstname)) {

	 				logResults.createLogs("Y", "PASS", "Emp First Name Inputted Successfully." + firstname);
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp First Name." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
			 
			 if (meghpileavebalancesummaryreport.clickEditIconForLeaveType(leavename)) {

	 				logResults.createLogs("Y", "PASS", "Sick Leave Edit Icon Clicked Successfully." + leavename);
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Clicking On Sick Leave Edit Icon." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
			 if (meghpileavebalancesummaryreport.LeaveBalanceAddLeaveCheckBox()) {

	 				logResults.createLogs("Y", "PASS", "Add Leave CheckBox Clicked Successfully.");
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Clicking On Add Leave CheckBox." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
			 if (meghpileavebalancesummaryreport.LeaveBalanceAddLeaveTextField(leavecount)) {

	 				logResults.createLogs("Y", "PASS", "Extra Leaves Added Successfully." + leavecount);
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Inputting Additional Leaves." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
			 if (meghpileavebalancesummaryreport.LeaveBalanceAddLeaveSaveChanges()) {

	 				logResults.createLogs("Y", "PASS", "Leave Changes Save Button Clicked Successfully.");
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Changes Save Button." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
	
 	 		  if (RolePermissionpage.ReprtButton()) {

 	 				logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
 	 			} else {
 	 				logResults.createLogs("Y", "FAIL",
 	 						"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
 	 			}
 	 	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportButton()) {

 	 				logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Button Clicked Successfully.");
 	 			} else {
 	 				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveBalanceSummary Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
 	 			}
 	 	      if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportRowResult()) {

 	   			logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Page Loaded Successfully.");
 	   		} else {
 	   			logResults.createLogs("Y", "FAIL", "Error While Loading  LeaveBalanceSummary Report Page." + meghpileavebalancesummaryreport.getExceptionDesc());
 	   		}
 	 	      if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportSearchTextField(firstname)) {

	 				logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + firstname);
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
				
				 if (meghpileavebalancesummaryreport.verifyLeaveTypeValue(leavename, leavepending)) {

	 				logResults.createLogs("Y", "PASS", " Added Additional Sick Leave Balance Displayed Successfully." + leavename + " " + leavepending );
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Validating Additional Added Sick Leave Balance Count." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
	
 	 	} 	        
 	 	        
 	// MPI_1375_LeaveBalanceSummaryReport_14
 	 	@Test(enabled = true, priority = 10, groups = { "Smoke" })
 	 	public void MPI_1375_LeaveBalanceSummaryReport_14()  {
 	 		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
 	 		logResults.createExtentReport(currTC);
 	 		logResults.setScenarioName(
 	 				"To verify this, edit the leave balance for an employee and deduct leaves and check whether count is decremented in leave balance summary report");

 	 		ArrayList<String> data = initBase.loadExcelData("LeaveBalanceSummary_Reports", currTC,
 	 				"firstname,leavename,leavepending,leavecount");

 	 		
 	 	
 	 		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
 	 	
 	 	
 	 		
 	 		MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);
 	 		MeghPILeaveBalanceSummaryReportPage meghpileavebalancesummaryreport = new MeghPILeaveBalanceSummaryReportPage(driver);
 	 		
 	 		int i = 0;
 	 		String firstname = data.get(i++);
 	 		String leavename = data.get(i++);
 	 		String leavepending = data.get(i++);
 	 		String leavecount = data.get(i++);
 	 		
 	 		

 	 		 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


 			if (meghloginpage.MainLandingPage()) {
 				logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
 			} else {
 				logResults.createLogs("Y", "FAIL",
 						"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
 			}		
 	 		
 	 		if (RolePermissionpage.LeaveButton()) {

				logResults.createLogs("Y", "PASS", "Leave Button Clicked Successfully Of Employee Account.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Leave Button." + RolePermissionpage.getExceptionDesc());
			}
		
			if (RolePermissionpage.LeaveBalanceTab()) {

				logResults.createLogs("Y", "PASS", " LeaveBalanceTab Clicked Successfully Of Employee Account.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Module LeaveBalanceTab."
						+ RolePermissionpage.getExceptionDesc());
			}
	
			 if (meghpileavebalancesummaryreport.LeaveBalanceSearchTextField(firstname)) {

	 				logResults.createLogs("Y", "PASS", "Emp First Name Inputted Successfully." + firstname);
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp First Name." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
			 
			 if (meghpileavebalancesummaryreport.clickEditIconForLeaveType(leavename)) {

	 				logResults.createLogs("Y", "PASS", "Sick Leave Edit Icon Clicked Successfully." + leavename);
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Clicking On Sick Leave Edit Icon." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
			 if (meghpileavebalancesummaryreport.LeaveBalanceDeductLeaveCheckBox()) {

	 				logResults.createLogs("Y", "PASS", "Deduct Leave CheckBox Clicked Successfully.");
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Clicking On Deduct Leave CheckBox." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
			 if (meghpileavebalancesummaryreport.LeaveBalanceDeductLeaveTextField(leavecount)) {

	 				logResults.createLogs("Y", "PASS", " Leaves Deducted Successfully." + leavecount);
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Inputting Deduction Leave Count." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
			 if (meghpileavebalancesummaryreport.LeaveBalanceAddLeaveSaveChanges()) {

	 				logResults.createLogs("Y", "PASS", "Leave Changes Save Button Clicked Successfully.");
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Clicking On Leave Changes Save Button." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
	
 	 		  if (RolePermissionpage.ReprtButton()) {

 	 				logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
 	 			} else {
 	 				logResults.createLogs("Y", "FAIL",
 	 						"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
 	 			}
 	 	        if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportButton()) {

 	 				logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Button Clicked Successfully.");
 	 			} else {
 	 				logResults.createLogs("Y", "FAIL", "Error While Clicking On LeaveBalanceSummary Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
 	 			}
 	 	      if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportRowResult()) {

 	   			logResults.createLogs("Y", "PASS", "LeaveBalanceSummary Report Page Loaded Successfully.");
 	   		} else {
 	   			logResults.createLogs("Y", "FAIL", "Error While Loading  LeaveBalanceSummary Report Page." + meghpileavebalancesummaryreport.getExceptionDesc());
 	   		}
 	 	      if (meghpileavebalancesummaryreport.LeaveBalanceSummaryReportSearchTextField(firstname)) {

	 				logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + firstname);
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpileavebalancesummaryreport.getExceptionDesc());
	 			}
				
				 if (meghpileavebalancesummaryreport.verifyLeaveTypeValue(leavename, leavepending)) {

	 				logResults.createLogs("Y", "PASS", " Added Additional Sick Leave Balance Displayed Successfully." + leavename + " " + leavepending );
	 			} else {
	 				logResults.createLogs("Y", "FAIL", "Error While Validating Additional Added Sick Leave Balance Count." + meghpileavebalancesummaryreport.getExceptionDesc());
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
