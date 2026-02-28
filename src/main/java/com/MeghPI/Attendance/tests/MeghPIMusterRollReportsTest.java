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
import com.MeghPI.Attendance.pages.MeghLeavePolicyPage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIMusterRollReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeRequestPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghWeekOffPolicyPage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIMusterRollReportsTest {

	
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
    private String WeekOffPolicyName = "";
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
		
		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");
		
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		WeekOffPolicyName = "AutoWOPN" +  initBase.executionRunTime;
		LeavePolicyName = "AutoLeavePN" +  initBase.executionRunTime;

		
	}
	
	
	// MPI_1249_MusterRoll_Reports_02
		@Test(enabled = true, priority = 01, groups = { "Smoke" })
		public void MPI_1249_MusterRoll_Reports_02()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of search feature for each search option");

			ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
					"password,captcha");

			MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
			MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
			
			int i = 0;
			String password = data.get(i++);
			String captcha = data.get(i++);
			String EmpIDRow ="";
			String EmpLastName ="";
			
			Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

		    String monthName = details.get("monthName");
		    String year = details.get("year");

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
		        if (meghpimusterrollreport.MusterRollReportButton()) {

					logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
				}
		       
				if (meghpimusterrollreport.MusterRollReportFilterButton()) {

					logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
				if (meghpimusterrollreport.MusterRollMonthlyReportEmpIDRecord()) {
					EmpIDRow =  meghpimusterrollreport.getempid;
					logResults.createLogs("Y", "PASS", "Emp ID Extracted Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Extracting Emp ID." + meghpimusterrollreport.getExceptionDesc());
				}
				if (meghpimusterrollreport.MusterRollMonthlyReportEmpNameRecord()) {
					EmpLastName = meghpimusterrollreport.getemplastname;
					logResults.createLogs("Y", "PASS", "Emp Last Name Extracted Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Extracting Emp Last Name." + meghpimusterrollreport.getExceptionDesc());
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
				
		        if (meghpimusterrollreport.MusterRollReportSearchTextField(EmpIDRow)) {

					logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + EmpIDRow);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpimusterrollreport.getExceptionDesc());
				}			
		        
		        if (meghpimusterrollreport.MusterRollReportEmpIDRecordValidation(EmpIDRow)) {

					logResults.createLogs("Y", "PASS", "Emp ID Displayed Successfully." + EmpIDRow);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Emp ID." + meghpimusterrollreport.getExceptionDesc());
				}	
		        if (meghpimusterrollreport.MusterRollReportSearchTextField(EmpLastName)) {

					logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + EmpLastName);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpimusterrollreport.getExceptionDesc());
				}	
		        if (meghpimusterrollreport.MusterRollReportEmpNameRecordValidation(EmpLastName)) {

					logResults.createLogs("Y", "PASS", "Inputted Emp Last Name Record Displayed Successfully." + EmpLastName);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Displaying Emp Last Name Record." + meghpimusterrollreport.getExceptionDesc());
				}
		}	
	
	
		// MPI_1252_MusterRoll_Reports_04
					@Test(enabled = true, priority = 2, groups = { "Smoke" })
					public void MPI_1252_MusterRoll_Reports_04()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the functionality of filter feature by selecting specific team");

					//	ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,"password,captcha");

						
					
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);

						
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
						
						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);

						Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

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

		 if (meghpimusterrollreport.MusterRollReportButton()) {

				logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
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
								if (meghpimusterrollreport.MusterRollReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
								
								if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpimusterrollreport.getExceptionDesc());
								}     
					}
	
					// MPI_1255_MusterRoll_Reports_05
					@Test(enabled = true, priority = 3, groups = { "Smoke" })
					public void MPI_1255_MusterRoll_Reports_05()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

				//		ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,"password,captcha");

						
					
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						

					
					
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
						
						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);

						
						
						Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

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

		 if (meghpimusterrollreport.MusterRollReportButton()) {

				logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
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
								if (meghpimusterrollreport.MusterRollReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
								
								if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpimusterrollreport.getExceptionDesc());
								}     
					}
	
					// MPI_1256_MusterRoll_Reports_06
					@Test(enabled = true, priority = 4, groups = { "Smoke" })
					public void MPI_1256_MusterRoll_Reports_06()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the functionality of filter feature by selecting employee type");

						//ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,"password,captcha");

						
					
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);

				
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
						
						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);

						
						
						Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

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

		 if (meghpimusterrollreport.MusterRollReportButton()) {

				logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
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
								if (meghpimusterrollreport.MusterRollReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
								
								if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpimusterrollreport.getExceptionDesc());
								}     
					}	
	
					// MPI_1257_MusterRoll_Reports_07
					@Test(enabled = true, priority = 5, groups = { "Smoke" })
					public void MPI_1257_MusterRoll_Reports_07()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the filter feature functionality by selecting year and month");

				//		ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,"password,captcha");

						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					
					
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
						
						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);


Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

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

		 if (meghpimusterrollreport.MusterRollReportButton()) {

				logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
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
								if (meghpimusterrollreport.MusterRollReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
								
								if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

									logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Displaying  Filtered Data." + meghpimusterrollreport.getExceptionDesc());
								}     
					}
	
					// MPI_1273_MusterRoll_Reports_23
					@Test(enabled = true, priority = 6, groups = { "Smoke" })
					public void MPI_1273_MusterRoll_Reports_23()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

						ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
								"subject,ccmail");

						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			
					
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
						MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);

			
						String subject = data.get(0);
						String ccmail = data.get(1);
						
						
						Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

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

		 if (meghpimusterrollreport.MusterRollReportButton()) {

				logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
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
								if (meghpimusterrollreport.MusterRollReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
					
					// MPI_1260_MusterRoll_Reports_10
					@Test(enabled = true, priority = 7, groups = { "Smoke" })
					public void MPI_1260_MusterRoll_Reports_10()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the attendance status \"P\" is displayed when emp is present");

						  // Load Excel data
						ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
								"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,musterrollreportstatus,presentcount,pin");


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

						   
						    String statusEndpoint    = data.get(i++);
					        String fromDateofuserstatus1          = data.get(i++);
					        String toDateofuserstatus2            = data.get(i++);
					        String expectedStatus    = data.get(i++);
					        String description = data.get(i++);
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
					     
					        String musterrollreportstatus = data.get(i++);
					        int presentcount = Integer.parseInt(data.get(i++));
					        String pin = data.get(i++);

					        
					        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

					        Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();

						    String monthName = details.get("monthName");
						    String year = details.get("year");
					        // Extract values
					        String monthFirstWorkingDate = details.get("month1WorkingDate");
					        String firstDayOfMonth = details.get("firstDayOfMonth");
					    
					        String fromDateofstatus = monthFirstWorkingDate + fromDateofuserstatus1;
					        String toDateofstatus = monthFirstWorkingDate + toDateofuserstatus2;
						   
						    
							String inouttime = monthFirstWorkingDate + " " + inouttime1;
							String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
						
							MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);

							MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
							MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
								MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

						 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						
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
							Thread.sleep(12000);
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

				            // Handle null OTHours → default to 00:00
				            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
				            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

				            // Make sentence using excel inputs
				            String finalSentence = String.format(
				                "%s – This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
				                description, empid, monthFirstWorkingDate, inouttime1, secondInOutTime,
				                actualStatus, expectedStatus, actualOTHours, totalOTHour
				            );

				            // Validate both Final Status & OT Hours
				            if (expectedStatus.equalsIgnoreCase(actualStatus) &&
				                totalOTHour.equalsIgnoreCase(actualOTHours)) {
				                logResults.createLogs("N", "PASS", "✅ " + finalSentence);
				            } else {
				                logResults.createLogs("N", "FAIL", "❌ " + finalSentence);
				            }
				        } else {
				            logResults.createLogs("N", "FAIL", "❌ Failed to fetch attendance. API Response." + validation.asString());
				        }

				        
				        MeghLoginPage meghloginpage = new MeghLoginPage(driver);
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);


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
							
							if (RolePermissionpage.EmployeeAttendanceButton()) {

								logResults.createLogs("Y", "PASS", " Attendance Module Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Attendane Module." + RolePermissionpage.getExceptionDesc());
							}
							
							if (meghpiOTpage.AttendanceProcessTab()) {

								logResults.createLogs("Y", "PASS", "Hr Account Attendance Process Tab Clicked Successfully In HR Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Hr Account Attendance Process Tab In Attendance Module."
										+ meghpiOTpage.getExceptionDesc());
							}
							if (meghpiOTpage.AddAttendanceProcessButton()) {

								logResults.createLogs("Y", "PASS", "Hr Account Add Attendance Process Button Clicked Successfully In HR Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Hr Account Add Attendance Process Button In Attendance Module."
										+ meghpiOTpage.getExceptionDesc());
							}
							
							if (meghpiOTpage.AddAttendanceProcessDateTextField()) {

								logResults.createLogs("Y", "PASS", "Date TextField Clicked Successfully In HR Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Date TextField."
										+ meghpiOTpage.getExceptionDesc());
							}
							if (meghpiOTpage.AddAttendanceProcessDateTextFieldInput(firstDayOfMonth)) {

								logResults.createLogs("Y", "PASS", "First Day Of the Month Selected Successfully ." + firstDayOfMonth);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting First Day Of The Month."
										+ meghpiOTpage.getExceptionDesc());
							}
							if (meghpiOTpage.AddAttendanceProcessApplyButton()) {

								logResults.createLogs("Y", "PASS", "Add Attendance Process Apply Button Clicked Successfully In HR Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Process Button."
										+ meghpiOTpage.getExceptionDesc());
							}
							if (meghpiOTpage.AddAttendanceProcessValidation()) {

								logResults.createLogs("Y", "PASS", "Waiting For Attendance Process To Get Complete  Successfully In HR Account.");
							} else {
								logResults.createLogs("Y", "FAIL", "Attendance Process Is Not Completed ."
										+ meghpiOTpage.getExceptionDesc());
							}
				        if (RolePermissionpage.ReprtButton()) {

							logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
						}
				        if (meghpimusterrollreport.MusterRollReportButton()) {

							logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
						}
				        if (meghpimusterrollreport.MusterRollReportFilterButton()) {

							logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
				       
				        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

							logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
						}	
				        
				        
				        if (meghpimusterrollreport.MusterRollDateHeaderMatchWithTableData(monthFirstWorkingDate, musterrollreportstatus, presentcount)) {

							logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And Present Days Count Validated Successfully." + firstname + " " + monthFirstWorkingDate + " " +musterrollreportstatus + " "+ presentcount  );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Present Days Count." + meghpimusterrollreport.getExceptionDesc());
						}	
					}
					
					// MPI_1261_MusterRoll_Reports_11
					@Test(enabled = true, priority = 8, groups = { "Smoke" })
					public void MPI_1261_MusterRoll_Reports_11()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the attendance status \"W\" is displayed when emp is on Weekoff");

						ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
								"password,firstname,baseuri,loginendpoint,updateattendanceendpoint,empid,musterrollreportstatus");

						
						MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
						MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
						
						MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
						
						 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
							MeghWeekOffPolicyPage WeekOffPolicyPage = new MeghWeekOffPolicyPage(driver);

						int i = 0;
						String password = data.get(i++);
						String firstname =data.get(i++);
						String baseuri = data.get(i++);
						String loginendpoint = data.get(i++);
						String updateattendanceendpoint = data.get(i++);
						String empid = data.get(i++);
						String musterrollreportstatus = data.get(i++);
						
						Map<String, Object> details = Pramod.getMonthWeekoffDetails();

					    String monthFirstWeekoffDate = (String) details.get("monthFirstSunday");
					    String firstDayOfMonth = (String) details.get("firstDayOfMonth");
					    int weekoffCount = (int) details.get("weekoffCount");
			
					    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


						if (meghloginpage.MainLandingPage()) {
							logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
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

							if (WeekOffPolicyPage.WeekOffPolicySearchTextField(WeekOffPolicyName)) {

								logResults.createLogs("Y", "PASS",
										"WeekOffPolicy Name Inputted In Search TextField Successfully." + WeekOffPolicyName);
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
								Thread.sleep(20000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}						    
							 if (RolePermissionpage.ReprtButton()) {

									logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
								}
							 if (meghpimusterrollreport.MusterRollReportButton()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
								}
						        
						        if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  Muster Roll Report Page." + meghpimusterrollreport.getExceptionDesc());
								}
						        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

									logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
								}	
						        
						        
						        if (meghpimusterrollreport.MusterRollWeekOFffHeaderMatchWithTableData(monthFirstWeekoffDate, musterrollreportstatus, weekoffCount)) {

									logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And WeekOff Days Count Validated Successfully." + firstname + " " + monthFirstWeekoffDate + " " +musterrollreportstatus + " "+ weekoffCount  );
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Weekoff Days Count." + meghpimusterrollreport.getExceptionDesc());
								}	
							}
							 
					// MPI_1262_MusterRoll_Reports_12
					@Test(enabled = true, priority = 9, groups = { "Smoke" })
					public void MPI_1262_MusterRoll_Reports_12()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the attendance status \"L\" is displayed when emp is on leave");

						ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
								"leavereason,leavename,firstname,musterrollreportstatus,presentcount");

					
				
						String LeaveReason = data.get(0);
						String leavename = data.get(1);
					
						String firstname = data.get(2);
						String musterrollreportstatus = data.get(3);
						int leavecount =  Integer.parseInt(data.get(4));
						
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
						String month2WorkingDate  = dateDetails.get("month2WorkingDate");
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
						
						
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLeavePage meghleavepage = new MeghLeavePage(driver);
					MeghLeavePolicyPage LeavePolicyPage = new MeghLeavePolicyPage(driver);
					MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
					MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
					
					 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


						if (meghloginpage.MainLandingPage()) {
							logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
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
					if (LeavePolicyPage.DefaultCheckBox()) {

						logResults.createLogs("Y", "PASS", "DefaultCheckBox Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On DefaultCheckBox." + LeavePolicyPage.getExceptionDesc());
					}
					
					if (LeavePolicyPage.DefaultConfirmButton()) {

						logResults.createLogs("Y", "PASS", "Confirm Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Cofirm Button." + LeavePolicyPage.getExceptionDesc());
					}
					if (LeavePolicyPage.CreateLeavePolicyButton()) {

						logResults.createLogs("Y", "PASS", "CreateLeavePolicyButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On CreateLeavePolicyButton." + LeavePolicyPage.getExceptionDesc());
					}

					if (AttendancePolicyPage.YesButton()) {
				
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
					
					if (meghleavepage.FromDateOnAdminSelected(month2WorkingDate)) {

						logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully ." + month2WorkingDate);
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
					
					if (meghleavepage.ToDateTextFieldInAdminSelected(month2WorkingDate)) {

						logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully ." + month2WorkingDate);
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
					
					if (meghleavepage.ReasonTextField(LeaveReason)) {

						logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + LeaveReason);
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
					 if (meghpimusterrollreport.MusterRollReportButton()) {

							logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
						}
				        
				        if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

							logResults.createLogs("Y", "PASS", "Muster Roll Report Page Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Loading  Muster Roll Report Page." + meghpimusterrollreport.getExceptionDesc());
						}
				        if (meghpimusterrollreport.MusterRollReportFilterButton()) {

							logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
				        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

							logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
						}	
				        
				        
				        if (meghpimusterrollreport.MusterRollLeaveHeaderMatchWithTableData(month2WorkingDate, musterrollreportstatus, leavecount, firstname)) {

							logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And Leave Days Count Validated Successfully." + firstname + " " + month2WorkingDate + " " +musterrollreportstatus + " "+ leavecount  );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Leave Days Count." + meghpimusterrollreport.getExceptionDesc());
						}	
					}		 
							 
					//MPI_1263_MusterRoll_Reports_13
					@Test(enabled = true, priority = 10, groups = { "Smoke" })
					public void MPI_1263_MusterRoll_Reports_13()  {
					    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					    logResults.createExtentReport(currTC);
					    logResults.setScenarioName( "To verify this, check the attendance status \"HD\" is Displayed when emp completes half day"
					    );

					    
					 // Load all data including punch-out time and mode
					    ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
					            "password,baseuri,loginendpoint,endpointoftransaction," +
					            "cardnumber,cardtype,bio1finger,bio2finger,empid," +
					            "locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,firstname,musterrollreportstatus,presentcount");

					    int i = 0;
					    String password = data.get(i++);
					    
					    String baseuri = data.get(i++);
					    String loginendpoint = data.get(i++);
					    String endpointoftransaction = data.get(i++);
					    String cardnumber = data.get(i++);
					    int cardtype = Integer.parseInt(data.get(i++));

					    String bio1finger = data.get(i++);
					    String bio2finger  = data.get(i++);
					    String employeeuniqueid = data.get(i++);
					    String locationid = data.get(i++);
					    String inouttime1 = data.get(i++);     // Punch In time
					    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
					    String photo = data.get(i++);
					    String secondInOutTime2 = data.get(i++); // Punch Out time
					    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

					  
					    String statusEndpoint    = data.get(i++);
				        String fromDateofuserstatus1          = data.get(i++);
				        String toDateofuserstatus2            = data.get(i++);
				        String expectedStatus    = data.get(i++);
				        String description = data.get(i++);
				        String updateattendanceendpoint = data.get(i++);
				        String firstname  = data.get(i++);
				        String musterrollreportstatus = data.get(i++);
				        int Halfdaycount = Integer.parseInt(data.get(i++));
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month3WorkingDate  = dateDetails.get("month3WorkingDate");
				        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
				        
				        
				        String fromDateofstatus = month3WorkingDate + fromDateofuserstatus1;
				        String toDateofstatus = month3WorkingDate + toDateofuserstatus2;
					   
					    
						String inouttime = month3WorkingDate + " " + inouttime1;
						String secondInOutTime = month3WorkingDate + " " + secondInOutTime2;
					
						
					
						 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						 MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

							
							MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
							
					    // Punch IN
					    Response punchInResponse = apiPage.executeSuccessTransaction(
					            baseuri, loginendpoint,
					            Emailid, password, cmpcode,
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
					            Emailid, password, cmpcode,
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
					            Emailid, password, cmpcode,
					            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
					            employeeuniqueid, firstDayOfMonth + "T00:00:00.000Z"
					    );

					    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
					        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
					    } else {
					        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
					    }
					 // Get User Status
				        Response validation = apiPage.executeGetUserStatus(
				                baseuri, loginendpoint,
				                Emailid, password, cmpcode,
				                baseuri, statusEndpoint,
				                employeeuniqueid, fromDateofstatus, toDateofstatus
				        );

				        if (validation.statusCode() == 200) {
				            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

				            // Make sentence using excel inputs
				            String finalSentence = String.format(
				                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
				                description, employeeuniqueid, firstDayOfMonth, inouttime1, secondInOutTime2, actualStatus, expectedStatus
				            );

				            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
				            } else {
				                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
				            }
				        } else {
				            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
				        }
				        
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
					 if (meghpimusterrollreport.MusterRollReportButton()) {

							logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
						}
				        
				        if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

							logResults.createLogs("Y", "PASS", "Muster Roll Report Page Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Loading  Muster Roll Report Page." + meghpimusterrollreport.getExceptionDesc());
						}
				        if (meghpimusterrollreport.MusterRollReportFilterButton()) {

							logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
				        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

							logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
						}	
				        
				        
				        if (meghpimusterrollreport.MusterRollHalfDayHeaderMatchWithTableData(month3WorkingDate, musterrollreportstatus, Halfdaycount)) {

							logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And Half Days Count Validated Successfully." + firstname + " " + month3WorkingDate + " " +musterrollreportstatus + " "+ Halfdaycount  );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Half Days Count." + meghpimusterrollreport.getExceptionDesc());
						}	
					}	
				        
					//MPI_1264_MusterRoll_Reports_14
					@Test(enabled = true, priority = 11, groups = { "Smoke" })
					public void MPI_1264_MusterRoll_Reports_14()  {
					    String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					    logResults.createExtentReport(currTC);
					    logResults.setScenarioName( "To verify this, check the attendance status \"SP\" is displayed when emp did single punch in"
					    );

					    
					 // Load all data including punch-out time and mode
					    ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
					            "password,baseuri,loginendpoint,endpointoftransaction," +
					            "cardnumber,cardtype,bio1finger,bio2finger,empid," +
					            "locationid,inouttime,mode,photo,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,firstname,musterrollreportstatus,presentcount");

					    int i = 0;
					    String password = data.get(i++);
					    
					    String baseuri = data.get(i++);
					    String loginendpoint = data.get(i++);
					    String endpointoftransaction = data.get(i++);
					    String cardnumber = data.get(i++);
					    int cardtype = Integer.parseInt(data.get(i++));

					    String bio1finger = data.get(i++);
					    String bio2finger  = data.get(i++);
					    String employeeuniqueid = data.get(i++);
					    String locationid = data.get(i++);
					    String inouttime1 = data.get(i++);     // Punch In time
					    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
					    String photo = data.get(i++);
				
					    String statusEndpoint    = data.get(i++);
				        String fromDateofuserstatus1          = data.get(i++);
				        String toDateofuserstatus2            = data.get(i++);
				        String expectedStatus    = data.get(i++);
				        String description = data.get(i++);
				        String updateattendanceendpoint = data.get(i++);
				        String firstname  = data.get(i++);
				        String musterrollreportstatus = data.get(i++);
				        int SPstatusdaycount = Integer.parseInt(data.get(i++));
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month4WorkingDate  = dateDetails.get("month4WorkingDate");
				        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
				
				        String fromDateofstatus = month4WorkingDate + fromDateofuserstatus1;
				        String toDateofstatus = month4WorkingDate + toDateofuserstatus2;
					   
					    
						String inouttime = month4WorkingDate + " " + inouttime1;
					
					
						
						// get first day of month
					
						 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						 MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							
							MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

					    // Punch IN
					    Response punchInResponse = apiPage.executeSuccessTransaction(
					            baseuri, loginendpoint,
					            Emailid, password, cmpcode,
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
					            Emailid, password, cmpcode,
					            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
					            employeeuniqueid, firstDayOfMonth + "T00:00:00.000Z"
					    );

					    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
					        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + employeeuniqueid);
					    } else {
					        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
					    }
					 // Get User Status
				        Response validation = apiPage.executeGetUserStatus(
				                baseuri, loginendpoint,
				                Emailid, password, cmpcode,
				                baseuri, statusEndpoint,
				                employeeuniqueid, fromDateofstatus, toDateofstatus
				        );

				        if (validation.statusCode() == 200) {
				            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

				            // Make sentence using excel inputs
				            String finalSentence = String.format(
				                "%s â€“ This Employee %s on %s, punched IN at %s . Final Status = %s (Expected = %s)",
				                description, employeeuniqueid, firstDayOfMonth, inouttime1, actualStatus, expectedStatus
				            );

				            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
				                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
				            } else {
				                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
				            }
				        } else {
				            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
				        }
				        
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
					 if (meghpimusterrollreport.MusterRollReportButton()) {

							logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
						}
				        
				        if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

							logResults.createLogs("Y", "PASS", "Muster Roll Report Page Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Loading  Muster Roll Report Page." + meghpimusterrollreport.getExceptionDesc());
						}
				        if (meghpimusterrollreport.MusterRollReportFilterButton()) {

							logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
				        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

							logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
						}	
				        
				        
				        if (meghpimusterrollreport.MusterRollSinglePunchHeaderMatchWithTableData(month4WorkingDate, musterrollreportstatus, SPstatusdaycount)) {

							logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And SinglePunch Days Count Validated Successfully." + firstname + " " + month4WorkingDate + " " +musterrollreportstatus + " "+ SPstatusdaycount  );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Single Punch Days Count." + meghpimusterrollreport.getExceptionDesc());
						}	
					}
									 
					// MPI_1265_MusterRoll_Reports_15
					@Test(enabled = true, priority = 12, groups = { "Smoke" })
					public void MPI_1265_MusterRoll_Reports_15()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the status H is displayed when there is a holiday");

						ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
								"password,firstname,baseuri,loginendpoint,updateattendanceendpoint,empid,musterrollreportstatus,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid,presentcount");

						
					
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
						
						
						 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
							

						int i = 0;
						String password = data.get(i++);
						String firstname =data.get(i++);
			
						String baseuri = data.get(i++);
						String loginendpoint = data.get(i++);
						String updateattendanceendpoint = data.get(i++);
						String empid = data.get(i++);
						String musterrollreportstatus = data.get(i++);
						
						 String holidayendpoint = data.get(i++);
				        String holidayname = data.get(i++);
				        String applicableto = data.get(i++);
				        String holidaydescription = data.get(i++);
				        String states = data.get(i++);
				        String stateid = data.get(i++);
				        int holidaycount = Integer.parseInt(data.get(i++)); 
					

				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month12WorkingDate  = dateDetails.get("month12WorkingDate");
				        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");



						    Response holidayResponse = apiPage.executeCreateHoliday(
					                baseuri, loginendpoint, Emailid, password, cmpcode,
					                baseuri, holidayendpoint,
					                holidayname, month12WorkingDate, applicableto,
					                holidaydescription, states, stateid
					        );

					        if (holidayResponse.getStatusCode() == 200) {
					            logResults.createLogs("N", "PASS", "Holiday created successfully." + holidayname);
					        } else {
					            logResults.createLogs("N", "FAIL", "Holiday creation failed." + holidayResponse.asString());
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
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);


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
							 if (meghpimusterrollreport.MusterRollReportButton()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
								}
						        
						        if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  Muster Roll Report Page." + meghpimusterrollreport.getExceptionDesc());
								}
						        
						        if (meghpimusterrollreport.MusterRollReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
						        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

									logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
								}	
						        
						        
						        if (meghpimusterrollreport.MusterRollHolidayHeaderMatchWithTableData(month12WorkingDate, musterrollreportstatus, holidaycount)) {

									logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And Holiday Days Count Validated Successfully." + firstname + " " + month12WorkingDate + " " +musterrollreportstatus + " "+ holidaycount  );
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Holiday Days Count." + meghpimusterrollreport.getExceptionDesc());
								}	
							}				 
					
					// MPI_1266_MusterRoll_Reports_16
					@Test(enabled = true, priority = 13, groups = { "Smoke" })
					public void MPI_1266_MusterRoll_Reports_16()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, ensure that the attendance status \"PHW\" is displayed when the employee is present on a weekoff and the same day is also a holiday.");

						ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
								"password,firstname,baseuri,loginendpoint,updateattendanceendpoint,empid,musterrollreportstatus,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid,presentcount,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description");

						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

						
						 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
							

						int i = 0;
						String password = data.get(i++);
						String firstname =data.get(i++);
			
						String baseuri = data.get(i++);
						String loginendpoint = data.get(i++);
						String updateattendanceendpoint = data.get(i++);
						String empid = data.get(i++);
						String musterrollreportstatus = data.get(i++);
						
						 String holidayendpoint = data.get(i++);
				        String holidayname = data.get(i++);
				        String applicableto = data.get(i++);
				        String holidaydescription = data.get(i++);
				        String states = data.get(i++);
				        String stateid = data.get(i++);
				        int holidaycount = Integer.parseInt(data.get(i++)); 
				        String endpointoftransaction = data.get(i++);
					    String cardnumber =data.get(i++);
					    int cardtype = Integer.parseInt(data.get(i++));

					    String bio1finger = data.get(i++);
					    String bio2finger  = data.get(i++);
					   
					    String locationid = data.get(i++);
					    String inouttime1 = data.get(i++);     // Punch In time
					    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
					    String photo = data.get(i++);
					    String secondInOutTime2 = data.get(i++); // Punch Out time
					    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

					  
					    String statusEndpoint    = data.get(i++);
				        String fromDateofuserstatus1          = data.get(i++);
				        String toDateofuserstatus2            = data.get(i++);
				        String expectedStatus    = data.get(i++);
				        String description = data.get(i++);
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					    
					    Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();
					    String week1Saturday = (String) details.get("1stSaturday");

					
					        String fromDateofstatus = week1Saturday + fromDateofuserstatus1;
					        String toDateofstatus = week1Saturday + toDateofuserstatus2;
						   
						    
							String inouttime = week1Saturday + " " + inouttime1;
							String secondInOutTime = week1Saturday + " " + secondInOutTime2;
						
							
							// get first day of month
							

						    Response holidayResponse = apiPage.executeCreateHoliday(
					                baseuri, loginendpoint, Emailid, password, cmpcode,
					                baseuri, holidayendpoint,
					                holidayname, week1Saturday, applicableto,
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
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, empid,
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
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, empid,
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
						            Emailid, password, cmpcode,
						            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
						            empid, firstDayOfMonth + "T00:00:00.000Z"
						    );

						    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
						        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + empid);
						    } else {
						        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
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

					            // Make sentence using excel inputs
					            String finalSentence = String.format(
					                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					                description, empid, firstDayOfMonth, inouttime1, secondInOutTime2, actualStatus, expectedStatus
					            );

					            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
					                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
					            } else {
					                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
					            }
					        } else {
					            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
					        }
					        
							
						 
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
							 if (meghpimusterrollreport.MusterRollReportButton()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
								}
						        
						        if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  Muster Roll Report Page." + meghpimusterrollreport.getExceptionDesc());
								}
						        if (meghpimusterrollreport.MusterRollReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
						        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

									logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
								}	
						        
						        
						        if (meghpimusterrollreport.MusterRollPresentOnWeekoffHolidayHeaderMatchWithTableData(week1Saturday, musterrollreportstatus, holidaycount)) {

									logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And Present Days Count Validated Successfully For Holiday On Weekoff." + firstname + " " + week1Saturday + " " +musterrollreportstatus + " "+ holidaycount  );
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Present Days Count For Present on Holiday And Weekoff." + meghpimusterrollreport.getExceptionDesc());
								}	
							}
					
					// MPI_1267_MusterRoll_Reports_17
					@Test(enabled = true, priority = 14, groups = { "Smoke" })
					public void MPI_1267_MusterRoll_Reports_17()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the attendance status \"PW\" is displayed when emp is Present on Weekoff.");

						ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
								"password,firstname,baseuri,loginendpoint,updateattendanceendpoint,empid,musterrollreportstatus,presentcount,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description");

						
					
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

						
						 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
							

						int i = 0;
						String password = data.get(i++);
						String firstname =data.get(i++);
			
						String baseuri = data.get(i++);
						String loginendpoint = data.get(i++);
						String updateattendanceendpoint = data.get(i++);
						String empid = data.get(i++);
						String musterrollreportstatus = data.get(i++);
						
				        int holidaycount = Integer.parseInt(data.get(i++)); 
				        String endpointoftransaction = data.get(i++);
					    String cardnumber =data.get(i++);
					    int cardtype = Integer.parseInt(data.get(i++));

					    String bio1finger = data.get(i++);
					    String bio2finger  = data.get(i++);
					   
					    String locationid = data.get(i++);
					    String inouttime1 = data.get(i++);     // Punch In time
					    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
					    String photo = data.get(i++);
					    String secondInOutTime2 = data.get(i++); // Punch Out time
					    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

					  
					    String statusEndpoint    = data.get(i++);
				        String fromDateofuserstatus1          = data.get(i++);
				        String toDateofuserstatus2            = data.get(i++);
				        String expectedStatus    = data.get(i++);
				        String description = data.get(i++);
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
				     
				        Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();
				        String week2Sunday   = (String) details.get("2ndSunday");
			
				       
				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
				     
				    
				     
					        String fromDateofstatus = week2Sunday + fromDateofuserstatus1;
					        String toDateofstatus = week2Sunday + toDateofuserstatus2;
						   
							String inouttime = week2Sunday + " " + inouttime1;
							String secondInOutTime = week2Sunday + " " + secondInOutTime2;
						
							
							// get first day of month
						
					        
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
						        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
					            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
					        } else {
					            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
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
						 // Get User Status
					        Response validation = apiPage.executeGetUserStatus(
					                baseuri, loginendpoint,
					                Emailid, password, cmpcode,
					                baseuri, statusEndpoint,
					                empid, fromDateofstatus, toDateofstatus
					        );

					        if (validation.statusCode() == 200) {
					            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

					            // Make sentence using excel inputs
					            String finalSentence = String.format(
					                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					                description, empid, week2Sunday, inouttime1, secondInOutTime2, actualStatus, expectedStatus
					            );

					            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
					                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
					            } else {
					                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
					            }
					        } else {
					            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
					        }
					        
							
						 
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
							 if (meghpimusterrollreport.MusterRollReportButton()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
								}
						        
						        if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  Muster Roll Report Page." + meghpimusterrollreport.getExceptionDesc());
								}
						        if (meghpimusterrollreport.MusterRollReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
						        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

									logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
								}	
						        
						        
						        if (meghpimusterrollreport.MusterRollPresentOnWeekoffHeaderMatchWithTableData(week2Sunday, musterrollreportstatus, holidaycount)) {

									logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And Present Days Count Validated Successfully For Weekoff." + firstname + " " + week2Sunday + " " +musterrollreportstatus + " "+ holidaycount  );
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Present Days Count For Present on Weekoff." + meghpimusterrollreport.getExceptionDesc());
								}	
							}
					
					// MPI_1268_MusterRoll_Reports_18
					@Test(enabled = true, priority = 15, groups = { "Smoke" })
					public void MPI_1268_MusterRoll_Reports_18()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the attendance status \"PH\" is displayed when emp is Present on holiday.");

						ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
								"password,firstname,baseuri,loginendpoint,updateattendanceendpoint,empid,musterrollreportstatus,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid,presentcount,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description");

						
					
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

						
						 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
							

						int i = 0;
						String password = data.get(i++);
						String firstname =data.get(i++);
			
						String baseuri = data.get(i++);
						String loginendpoint = data.get(i++);
						String updateattendanceendpoint = data.get(i++);
						String empid = data.get(i++);
						String musterrollreportstatus = data.get(i++);
						
						 String holidayendpoint = data.get(i++);
				        String holidayname = data.get(i++);
				        String applicableto = data.get(i++);
				        String holidaydescription = data.get(i++);
				        String states = data.get(i++);
				        String stateid = data.get(i++);
				        int holidaycount = Integer.parseInt(data.get(i++)); 
				        String endpointoftransaction = data.get(i++);
					    String cardnumber =data.get(i++);
					    int cardtype = Integer.parseInt(data.get(i++));

					    String bio1finger = data.get(i++);
					    String bio2finger  = data.get(i++);
					   
					    String locationid = data.get(i++);
					    String inouttime1 = data.get(i++);     // Punch In time
					    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
					    String photo = data.get(i++);
					    String secondInOutTime2 = data.get(i++); // Punch Out time
					    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

					  
					    String statusEndpoint    = data.get(i++);
				        String fromDateofuserstatus1          = data.get(i++);
				        String toDateofuserstatus2            = data.get(i++);
				        String expectedStatus    = data.get(i++);
				        String description = data.get(i++);
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
				      
				        
				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month12WorkingDate  = dateDetails.get("month12WorkingDate");
				        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");

					        String fromDateofstatus = month12WorkingDate + fromDateofuserstatus1;
					        String toDateofstatus = month12WorkingDate + toDateofuserstatus2;
						   
						    
							String inouttime = month12WorkingDate + " " + inouttime1;
							String secondInOutTime = month12WorkingDate + " " + secondInOutTime2;
						
							
							// get first day of month
							

						    Response holidayResponse = apiPage.executeCreateHoliday(
					                baseuri, loginendpoint, Emailid, password, cmpcode,
					                baseuri, holidayendpoint,
					                holidayname, month12WorkingDate, applicableto,
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
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, empid,
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
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, empid,
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
						            Emailid, password, cmpcode,
						            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
						            empid, firstDayOfMonth + "T00:00:00.000Z"
						    );

						    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
						        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + empid);
						    } else {
						        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
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

					            // Make sentence using excel inputs
					            String finalSentence = String.format(
					                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					                description, empid, firstDayOfMonth, inouttime1, secondInOutTime2, actualStatus, expectedStatus
					            );

					            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
					                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
					            } else {
					                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
					            }
					        } else {
					            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
					        }
					        
							
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
							 if (meghpimusterrollreport.MusterRollReportButton()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
								}
						        
						        if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  Muster Roll Report Page." + meghpimusterrollreport.getExceptionDesc());
								}
						        if (meghpimusterrollreport.MusterRollReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
						        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

									logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
								}	
						        
						        
						        if (meghpimusterrollreport.MusterRollPresentOnHolidayHeaderMatchWithTableData(month12WorkingDate, musterrollreportstatus, holidaycount)) {

									logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And Present Days Count Validated Successfully For Holiday." + firstname + " " + month12WorkingDate + " " +musterrollreportstatus + " "+ holidaycount  );
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Present Days Count For Present on Holiday." + meghpimusterrollreport.getExceptionDesc());
								}	
							}
					
					// MPI_1269_MusterRoll_Reports_19
					@Test(enabled = true, priority = 16, groups = { "Smoke" })
					public void MPI_1269_MusterRoll_Reports_19()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the attendance status \"HDW\" is displayed when emp completes half day on Weekoff.");

						ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
								"password,firstname,baseuri,loginendpoint,updateattendanceendpoint,empid,musterrollreportstatus,presentcount,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description");

						
					
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

						
						 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
							

						int i = 0;
						String password = data.get(i++);
						String firstname =data.get(i++);
			
						String baseuri = data.get(i++);
						String loginendpoint = data.get(i++);
						String updateattendanceendpoint = data.get(i++);
						String empid = data.get(i++);
						String musterrollreportstatus = data.get(i++);
						
				        int holidaycount = Integer.parseInt(data.get(i++)); 
				        String endpointoftransaction = data.get(i++);
					    String cardnumber =data.get(i++);
					    int cardtype = Integer.parseInt(data.get(i++));

					    String bio1finger = data.get(i++);
					    String bio2finger  = data.get(i++);
					   
					    String locationid = data.get(i++);
					    String inouttime1 = data.get(i++);     // Punch In time
					    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
					    String photo = data.get(i++);
					    String secondInOutTime2 = data.get(i++); // Punch Out time
					    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

					  
					    String statusEndpoint    = data.get(i++);
				        String fromDateofuserstatus1          = data.get(i++);
				        String toDateofuserstatus2            = data.get(i++);
				        String expectedStatus    = data.get(i++);
				        String description = data.get(i++);
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
				        Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();
				        String week2Saturday = (String) details.get("2ndSaturday");
				        String firstDayOfMonth = (String) details.get("firstDayLastMonth");
				       
				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					
					        String fromDateofstatus = week2Saturday + fromDateofuserstatus1;
					        String toDateofstatus = week2Saturday + toDateofuserstatus2;
						   
						    
							String inouttime = week2Saturday + " " + inouttime1;
							String secondInOutTime = week2Saturday + " " + secondInOutTime2;
						
							
							// get first day of month
						
					        
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
						        logResults.createLogs("N", "PASS", "Punch IN executed successfully at " + inouttime1);
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
					            logResults.createLogs("N", "PASS", "Punch OUT executed successfully at " + secondInOutTime2);
					        } else {
					            logResults.createLogs("N", "FAIL", "Punch OUT failed." + punchOutResponse.asString());
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
						 // Get User Status
					        Response validation = apiPage.executeGetUserStatus(
					                baseuri, loginendpoint,
					                Emailid, password, cmpcode,
					                baseuri, statusEndpoint,
					                empid, fromDateofstatus, toDateofstatus
					        );

					        if (validation.statusCode() == 200) {
					            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

					            // Make sentence using excel inputs
					            String finalSentence = String.format(
					                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					                description, empid, firstDayOfMonth, inouttime1, secondInOutTime2, actualStatus, expectedStatus
					            );

					            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
					                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
					            } else {
					                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
					            }
					        } else {
					            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
					        }
					        
							
						 
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
							 if (meghpimusterrollreport.MusterRollReportButton()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
								}
						        
						        if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  Muster Roll Report Page." + meghpimusterrollreport.getExceptionDesc());
								}
						        if (meghpimusterrollreport.MusterRollReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
						        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

									logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
								}	
						        
						        
						        if (meghpimusterrollreport.MusterRollHalfdayOnWeekoffHeaderMatchWithTableData(week2Saturday, musterrollreportstatus, holidaycount)) {

									logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And Half Day Present Days Count Validated Successfully For Weekoff." + firstname + " " + week2Saturday + " " +musterrollreportstatus + " "+ holidaycount  );
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Half Day Present  Count on Weekoff." + meghpimusterrollreport.getExceptionDesc());
								}	
							}
					
					// MPI_1270_MusterRoll_Reports_20
					@Test(enabled = true, priority = 17, groups = { "Smoke" })
					public void MPI_1270_MusterRoll_Reports_20()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, check the attendance status \"HDH\" is displayed when emp completes half day on holiday.");

						ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
								"password,firstname,baseuri,loginendpoint,updateattendanceendpoint,empid,musterrollreportstatus,holidayendpoint,holidayname,applicableto,holidaydescription,states,stateid,presentcount,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description");

						
					
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

						
						 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
							

						int i = 0;
						String password = data.get(i++);
						String firstname =data.get(i++);
			
						String baseuri = data.get(i++);
						String loginendpoint = data.get(i++);
						String updateattendanceendpoint = data.get(i++);
						String empid = data.get(i++);
						String musterrollreportstatus = data.get(i++);
						
						 String holidayendpoint = data.get(i++);
				        String holidayname = data.get(i++);
				        String applicableto = data.get(i++);
				        String holidaydescription = data.get(i++);
				        String states = data.get(i++);
				        String stateid = data.get(i++);
				        int holidaycount = Integer.parseInt(data.get(i++)); 
				        String endpointoftransaction = data.get(i++);
					    String cardnumber =data.get(i++);
					    int cardtype = Integer.parseInt(data.get(i++));

					    String bio1finger = data.get(i++);
					    String bio2finger  = data.get(i++);
					   
					    String locationid = data.get(i++);
					    String inouttime1 = data.get(i++);     // Punch In time
					    String mode = data.get(i++);          // Punch In mode (e.g., "IN")
					    String photo = data.get(i++);
					    String secondInOutTime2 = data.get(i++); // Punch Out time
					    String secondMode = data.get(i++);      // Punch Out mode (e.g., "OUT")

					  
					    String statusEndpoint    = data.get(i++);
				        String fromDateofuserstatus1          = data.get(i++);
				        String toDateofuserstatus2            = data.get(i++);
				        String expectedStatus    = data.get(i++);
				        String description = data.get(i++);
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
				      
				        
				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month11WorkingDate = dateDetails.get("month11WorkingDate");
				        String firstDayOfMonth = dateDetails.get("firstDayOfMonth");

					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
				        
				        
					        String fromDateofstatus = month11WorkingDate + fromDateofuserstatus1;
					        String toDateofstatus = month11WorkingDate + toDateofuserstatus2;
						   
						    
							String inouttime = month11WorkingDate + " " + inouttime1;
							String secondInOutTime = month11WorkingDate + " " + secondInOutTime2;
						
							
							// get first day of month
							

						    Response holidayResponse = apiPage.executeCreateHoliday(
					                baseuri, loginendpoint, Emailid, password, cmpcode,
					                baseuri, holidayendpoint,
					                holidayname, month11WorkingDate, applicableto,
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
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, empid,
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
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, empid,
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
						            Emailid, password, cmpcode,
						            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
						            empid, firstDayOfMonth + "T00:00:00.000Z"
						    );

						    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
						        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + empid);
						    } else {
						        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
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

					            // Make sentence using excel inputs
					            String finalSentence = String.format(
					                "%s â€“ This Employee %s on %s, punched IN at %s and Punch OUT at %s. Final Status = %s (Expected = %s)",
					                description, empid, firstDayOfMonth, inouttime1, secondInOutTime2, actualStatus, expectedStatus
					            );

					            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
					                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
					            } else {
					                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
					            }
					        } else {
					            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
					        }
					        
							
						 
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
							 if (meghpimusterrollreport.MusterRollReportButton()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
								}
						        
						        if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

									logResults.createLogs("Y", "PASS", "Muster Roll Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  Muster Roll Report Page." + meghpimusterrollreport.getExceptionDesc());
								}
						        if (meghpimusterrollreport.MusterRollReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
						        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

									logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
								}	
						        
						        
						        if (meghpimusterrollreport.MusterRollHalfDayOnHolidayHeaderMatchWithTableData(month11WorkingDate, musterrollreportstatus, holidaycount)) {

									logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And Half Day Present Days Count Validated Successfully For Holiday." + firstname + " " + month11WorkingDate + " " +musterrollreportstatus + " "+ holidaycount  );
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Half Day Present Days Count on Holiday." + meghpimusterrollreport.getExceptionDesc());
								}	
							}
					
					// MPI_1271_MusterRoll_Reports_21
					@Test(enabled = true, priority = 18, groups = { "Smoke" })
					public void MPI_1271_MusterRoll_Reports_21()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, apply unpaid leave and ensure leave isn't calculated as part of paydays.");

						ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
								"leavereason,leavename,firstname,musterrollreportstatus,presentcount");

						
			
					
						String LeaveReason = data.get(0);
						
						String leavename = data.get(1);
						String firstname = data.get(2);
						String musterrollreportstatus = data.get(3);
						int LeaveCount = Integer.parseInt(data.get(4));
				
						Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
						String month6WorkingDate  = dateDetails.get("month6WorkingDate");

						 String monthName = dateDetails.get("monthName");
						    String year = dateDetails.get("year");
						
						
						
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

			        
			MeghLeavePage meghleavepage = new MeghLeavePage(driver);
			MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);

			
			 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


				if (meghloginpage.MainLandingPage()) {
					logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
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
			
			if (meghleavepage.FromDateOnAdminSelected(month6WorkingDate)) {

				logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully ." + month6WorkingDate);
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
			
			if (meghleavepage.ToDateTextFieldInAdminSelected(month6WorkingDate)) {

				logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully ." + month6WorkingDate);
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
			
			if (meghleavepage.ReasonTextField(LeaveReason)) {

				logResults.createLogs("Y", "PASS", " Reason Inputted  Successfully Of Employee Account." + LeaveReason);
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
						 if (meghpimusterrollreport.MusterRollReportButton()) {

								logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
							}
					        
					        if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

								logResults.createLogs("Y", "PASS", "Muster Roll Report Page Loaded Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Loading  Muster Roll Report Page." + meghpimusterrollreport.getExceptionDesc());
							}
					        if (meghpimusterrollreport.MusterRollReportFilterButton()) {

								logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Filter Button." + meghpimusterrollreport.getExceptionDesc());
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
					        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

								logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
							}	
					        
					        
					        if (meghpimusterrollreport.MusterRollLeaveHeaderMatchWithTableData(month6WorkingDate, musterrollreportstatus, LeaveCount, firstname)) {

								logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And Half Day Present Days Count Validated Successfully For Holiday." + firstname + " " + month6WorkingDate + " " +musterrollreportstatus + " "+ LeaveCount  );
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Half Day Present Days Count on Holiday." + meghpimusterrollreport.getExceptionDesc());
							}			
					}
					
					/*   i did it 
					// MPI_1277_MusterRoll_Reports_24
					@Test(enabled = true, priority = 19, groups = { "Smoke", "AddMaster" })
					public void MPI_1277_MusterRoll_Reports_24()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify this, create a department and map it with an office. Then, navigate to Team Mapping, click on 'Add Team Mapping', select the created team, select the mapped department, and save. After saving, create an employee for the selected location. Navigate to the employee's profile, select the department, click on the team field, enter the team name, and ensure that the mapped team is displayed.");

						ArrayList<String> data = initBase.loadExcelData("Master_teammapping", currTC,
								"password,captcha,deptname,officename,teamname,shiftname,shiftcode,starttime,endtime,buffertime,fullday,halfday");

						MeghMasterTeamMappingPage TeamMappingPage = new MeghMasterTeamMappingPage(driver);
						String password = data.get(0);
						String captcha = data.get(1);
						String deptname = data.get(2) + initBase.executionRunTime;
						String officename = data.get(3) + initBase.executionRunTime;

						String teamname = data.get(14) + initBase.executionRunTime;
						String shiftname = data.get(2) + Pramod.generateRandomAlpha(7);
						String shiftcode = data.get(3) + Pramod.generateRandomNumber(3);
						String starttime = data.get(4);
						String endtime = data.get(5);
						String buffertime = data.get(6);
						String fullday = data.get(7);
						String halfday = data.get(8);

						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
						MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);
						MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
						MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
						MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
						MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);


				MeghLoginTest meghlogintest = new MeghLoginTest();
						
						 MeghLoginPage meghloginpage = new MeghLoginPage(driver);


		if (meghloginpage.MainLandingPage()) {
			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
		}
						
						// OfficeName Logic
						
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

							if (EmployeePage.CompanyTab()) {
								logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Company Tab ." + EmployeePage.getExceptionDesc());
							}

							if (EmployeePage.OfficeButton()) {
								logResults.createLogs("Y", "PASS", "Office Button  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Office Button ." + EmployeePage.getExceptionDesc());
							}

							if (EmployeePage.AddOfficeButton()) {
								logResults.createLogs("Y", "PASS", " Successfully Clicked On AddOfficeButton .");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On AddOfficeButton ." + EmployeePage.getExceptionDesc());
							}

							if (EmployeePage.OfficeName(officename)) {
								logResults.createLogs("Y", "PASS", " OfficeName Entered In the Office Name TextField ." + officename);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Inputting The Office Name ." + EmployeePage.getExceptionDesc());
							}

							if (EmployeePage.OfficeTypeDropdown()) {
								logResults.createLogs("Y", "PASS", " Office Type Selected In the Office Type Dropdown .");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Selecting The Office Type ." + EmployeePage.getExceptionDesc());
							}

							if (EmployeePage.CountryDropdown()) {
								logResults.createLogs("Y", "PASS", " Country Selected In the Country  Dropdown .");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Selecting The Country ." + EmployeePage.getExceptionDesc());
							}

							if (EmployeePage.StateDropdown(state)) {
								logResults.createLogs("Y", "PASS", " State Selected In the State  Dropdown ." + state);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Selecting The State ." + EmployeePage.getExceptionDesc());
							}

							if (EmployeePage.CompanyLocationSaveButton()) {
								
								logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
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

							if (EmployeePage.CompanyTab()) {
								logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Company Tab ." + EmployeePage.getExceptionDesc());
							}
				
							if (DepartmentPage.DepartmentDropIcon()) {
								logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
							}
							
							if (DepartmentPage.DepartmentButton()) {
								logResults.createLogs("Y", "PASS", "Department Tab  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Department Tab ." + DepartmentPage.getExceptionDesc());
							}

							if (DepartmentPage.AddDepartmentButton()) {
								logResults.createLogs("Y", "PASS", "Add Department Tab  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Add Department Tab ." + DepartmentPage.getExceptionDesc());
							}

							if (DepartmentPage.DepartmentName(deptname)) {
								logResults.createLogs("Y", "PASS", "Department Name Entered Successfully." + deptname);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Inputting Department Name ." + DepartmentPage.getExceptionDesc());
							}

							if (DepartmentPage.AddDepartmentSaveButton()) {
								logResults.createLogs("Y", "PASS", "Successfully Clicked On Add Department Save Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Add Department Save Button ." + DepartmentPage.getExceptionDesc());
							}
							Thread.sleep(3000);
							
							if (DepartmentPage.DepartmentDropIcon()) {
								logResults.createLogs("Y", "PASS", "Department Drop Icon Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Department Drop Icon ." + DepartmentPage.getExceptionDesc());
							}

							if (DepartmentPage.DepartmentMappingButton()) {
								logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentMapping Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On DepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
							}

							if (DepartmentPage.AddDepartmentMappingButton()) {
								logResults.createLogs("Y", "PASS", "Successfully Clicked On AddDepartmentMapping Button.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On AddDepartmentMapping Button ." + DepartmentPage.getExceptionDesc());
							}

							if (DepartmentMappingpage.DepartmentSelectDropDown(deptname)) {
								logResults.createLogs("Y", "PASS", "Successfully Selected Department." + deptname);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Selecting Department ." + DepartmentMappingpage.getExceptionDesc());
							}

							if (DepartmentMappingpage.OfficeSelectDropDown(officename)) {
								logResults.createLogs("Y", "PASS", "Successfully Selected OfficeName." + officename);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Selecting OfficeName ." + DepartmentMappingpage.getExceptionDesc());
							}

							if (DepartmentMappingpage.AddDeptMappingSaveButton()) {
							
								logResults.createLogs("Y", "PASS", "AddDeptMappingSaveButton Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error while Clicking On AddDeptMappingSaveButton."
										+ DepartmentMappingpage.getExceptionDesc());
							}

						
						if (EmployeePage.DirectoryButton()) {
							logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error while Clicking On Directery Button." + EmployeePage.getExceptionDesc());
						}

							if (EmployeePage.CompanyTab()) {
								logResults.createLogs("Y", "PASS", "Company Tab  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Company Tab ." + EmployeePage.getExceptionDesc());
							}
				
							if (TeamPage.TeamDropIcon()) {
								logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Team Drop Icon ." + TeamPage.getExceptionDesc());
							}

							if (TeamPage.TeamButton()) {
								logResults.createLogs("Y", "PASS", "Team Button  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Team Button ." + TeamPage.getExceptionDesc());
							}

							if (TeamPage.AddTeamButton()) {
								logResults.createLogs("Y", "PASS", "Add Team Button  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Add Team Button ." + TeamPage.getExceptionDesc());
							}

							if (TeamPage.TeamNameTextField(teamname)) {
								logResults.createLogs("Y", "PASS", "Team Name Is Entered In Team Name Text Field." + teamname);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Entering Team Name." + TeamPage.getExceptionDesc());
							}

							if (TeamPage.TeamSaveButton()) {
								logResults.createLogs("Y", "PASS", "Team Save Button  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On TeamSaveButton ." + TeamPage.getExceptionDesc());
							}
							
							if (TeamPage.TeamDropIcon()) {
								logResults.createLogs("Y", "PASS", "Team Drop Icon  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Team Drop Icon ." + TeamPage.getExceptionDesc());
							}

							if (TeamPage.TeamMappingButton()) {
								logResults.createLogs("Y", "PASS", "TeamMapping Button  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On TeamMapping Button ." + TeamPage.getExceptionDesc());
							}

							if (TeamPage.AddTeamMappingButton()) {
								logResults.createLogs("Y", "PASS", "AddTeamMapping Button  Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On AddTeamMapping Button ." + TeamPage.getExceptionDesc());
							}

							if (TeamMappingPage.TeamDropDownOfForm(teamname)) {
								logResults.createLogs("Y", "PASS", "Team Name Selected Successfully." + teamname);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Selecting Team Name ." + TeamMappingPage.getExceptionDesc());
							}

							if (TeamMappingPage.TeamDeptDropDown()) {
								logResults.createLogs("Y", "PASS", "Department DropDown Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Clicking On Department DropDown ." + TeamMappingPage.getExceptionDesc());
							}

							if (TeamMappingPage.TeamDeptSearchField(deptname)) {
								logResults.createLogs("Y", "PASS", "Department Name Inputted Successfully." + deptname);
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Inputting Department Name ." + TeamMappingPage.getExceptionDesc());
							}

							if (TeamMappingPage.TeamDeptSearchResult()) {
								logResults.createLogs("Y", "PASS", "Department Name Selected Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Selecting Department Name ." + TeamMappingPage.getExceptionDesc());
							}

							if (TeamMappingPage.AddTeamMappingSaveButton()) {
								
								logResults.createLogs("Y", "PASS", "Add Team Mapping Saved Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error while Saving The Team Mapping ." + TeamMappingPage.getExceptionDesc());
							}}
					
					
					// MPI_725_MusterRoll_Reports_01
					@Test(enabled = true, priority = 20, groups = { "Smoke" })
					public void MPI_725_MusterRoll_Reports_01()  {
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"Check the status in the Muster Roll Report when the employee completes two shifts in a single day.");

						  // Load Excel data
						ArrayList<String> data = initBase.loadExcelData("MusterRoll_Reports", currTC,
								"password,captcha,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,date,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,overtimestatus,musterrollreportstatus,presentcount");


						  int i = 0;
						String password = data.get(i++);
						String captcha = data.get(i++);
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

						    String date =  data.get(i++);
						    String statusEndpoint    = data.get(i++);
					        String fromDateofuserstatus1          = data.get(i++);
					        String toDateofuserstatus2            = data.get(i++);
					        String expectedStatus    = data.get(i++);
					        String description = data.get(i++);
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
					        String overtimestatus = data.get(i++);
					        String musterrollreportstatus = data.get(i++);
					        int presentcount = Integer.parseInt(data.get(i++));

					        
					        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

					        LocalDate today = LocalDate.now();

					        // Get the first date of the current month
					        LocalDate firstDate = today.withDayOfMonth(1);

					        // If firstDate is Saturday, move to Monday (add 2 days)
					        // If firstDate is Sunday, move to Monday (add 1 day)
					        if (firstDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
					            firstDate = firstDate.plusDays(2);
					        } else if (firstDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
					            firstDate = firstDate.plusDays(1);
					        }

					        String monthFirstWorkingDate = firstDate.toString();
						 
						
					 
							String monthName = firstDate.getMonth()
							        .getDisplayName(TextStyle.FULL, Locale.ENGLISH); // "September"
							
							String[] parts = monthFirstWorkingDate.split("-"); 
							String year = parts[0]; // "2025"
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
							
							String PunchinDateSearchUI = firstDate.format(formatter);
					        
					        
					        String fromDateofstatus = monthFirstWorkingDate + fromDateofuserstatus1;
					        String toDateofstatus = monthFirstWorkingDate + toDateofuserstatus2;
						   
						    
							String inouttime = monthFirstWorkingDate + " " + inouttime1;
							String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
						
							
							// get first day of month
							LocalDate localDate = LocalDate.parse(monthFirstWorkingDate);
							 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
							 
						
								MeghPIMusterRollReportsPage meghpimusterrollreport = new MeghPIMusterRollReportsPage(driver);
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
						
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
						 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
						 MeghLoginTest meghlogintest = new MeghLoginTest();
					MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
							
						TableFieldIds Tablelist = new TableFieldIds();
					  

						      TableFieldIds ids = MeghPIAttenAPIPage.executeGetTableFieldIds(
							        baseuri, loginendpoint,
							        Emailid, password, cmpcode,
							        baseuri, tablefieldendpoint,
							        officename,   // company location name
							        AdminFirstName, // entity name
							        entityname    // entity type name
							);
						      System.out.println(ids.companyLocationId + "locationid");
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
					    Thread.sleep(12000);
					  
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
					    Thread.sleep(25000);
					 // Get User Status
				        Response validation = apiPage.executeGetUserStatus(
				                baseuri, loginendpoint,
				                Emailid, password, cmpcode,
				                baseuri, statusEndpoint,
				                empid, fromDateofstatus, toDateofstatus
				        );

				        if (validation.statusCode() == 200) {
				            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

				            // Handle null OTHours → default to 00:00
				            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
				            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" .actualOTHours;

				            // Make sentence using excel inputs
				            String finalSentence = String.format(
				                "%s – This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
				                description, empid, monthFirstWorkingDate, inouttime1, secondInOutTime,
				                actualStatus, expectedStatus, actualOTHours, totalOTHour
				            );

				            // Validate both Final Status & OT Hours
				            if (expectedStatus.equalsIgnoreCase(actualStatus) &&
				                totalOTHour.equalsIgnoreCase(actualOTHours)) {
				                logResults.createLogs("N", "PASS", "✅ " + finalSentence);
				            } else {
				                logResults.createLogs("N", "FAIL", "❌ " + finalSentence);
				            }
				        } else {
				            logResults.createLogs("N", "FAIL", "❌ Failed to fetch attendance. API Response." + validation.asString());
				        }

				        
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
				        if (meghpimusterrollreport.MusterRollReportButton()) {

							logResults.createLogs("Y", "PASS", "Muster Roll Report Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Clicking On Muster Roll Report Button." + meghpimusterrollreport.getExceptionDesc());
						}
				        
				        if (meghpimusterrollreport.MusterRollReportEmpIDRecord()) {

							logResults.createLogs("Y", "PASS", "Muster Roll Report Page Loaded Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Loading  Muster Roll Report Page." + meghpimusterrollreport.getExceptionDesc());
						}
				        if (meghpimusterrollreport.MusterRollReportSearchTextField(firstname)) {

							logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpimusterrollreport.getExceptionDesc());
						}	
				        
				        
				        if (meghpimusterrollreport.MusterRollDateHeaderMatchWithTableData(monthFirstWorkingDate, musterrollreportstatus, presentcount)) {

							logResults.createLogs("Y", "PASS", "Emp Record Displayed And Status And Present Days Count Validated Successfully." + firstname + " " + monthFirstWorkingDate + " " +musterrollreportstatus + " "+ presentcount  );
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Validating Emp Attendance Status With Date and Present Days Count." + meghpimusterrollreport.getExceptionDesc());
						}	
					}	
					*/
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
	
	
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
