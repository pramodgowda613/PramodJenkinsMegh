package com.MeghPI.Attendance.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIDailyAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import com.MeghPI.Attendance.pages.MeghPIWeekOffReportPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import utils.Pramod;
import utils.Utils;

public class MeghPIWeekOffReportTest {


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
		
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		
	}
	
	// MPI_1335_WeekOff_Reports_01
			@Test(enabled = true, priority = 1, groups = { "Smoke" })
			public void MPI_1335_WeekOff_Reports_01()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionality of search feature for each search option.");

				  // Load Excel data
				ArrayList<String> data = initBase.loadExcelData("WeekOff_Reports", currTC,
						"password,captcha,day,daytwo");
				
				  int i = 0;
					String password = data.get(i++);
					String captcha = data.get(i++);
					String day = data.get(i++);
					String daytwo = data.get(i++);
					
					  Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();
					  String week1Saturday = (String) details.get("1stSaturday");
					 String uidate = Pramod.convertToUIFormat(week1Saturday);

					  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");

					
						MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);

 MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);
				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
	MeghPIDailyAttendanceReportsPage meghpidailyattendancereport = new MeghPIDailyAttendanceReportsPage(driver);
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
	if (meghpiweekoffreport.WeekOffReportButton()) {

		logResults.createLogs("Y", "PASS", "WeekOff Report Button Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Report Button." + meghpiweekoffreport.getExceptionDesc());
	}

	if (meghpiweekoffreport.WeekOffReportRowResult()) {

		logResults.createLogs("Y", "PASS", "WeekOff Report Page Loaded Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Loading  WeekOff Report Page." + meghpiweekoffreport.getExceptionDesc());
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
	if (meghpiweekoffreport.WeekOffReportFilterButton()) {

		logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On Filter Button." + meghpiweekoffreport.getExceptionDesc());
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

	
	if (meghpiweekoffreport.WeekOffReportDateSearchCheckBox()) {

		logResults.createLogs("Y", "PASS", "Date CheckBox Selected Successfully." );
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Selecting Date CheckBox." + meghpiweekoffreport.getExceptionDesc());
	}


	if (OfficePage.SearchDropDown()) {
		logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
	}
	
	
	if (meghpiweekoffreport.WeekOffReportSearchTextField(uidate)) {

		logResults.createLogs("Y", "PASS", "Emp WeekOff Date Inputted Successfully." + uidate);
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Inputting Emp WeekOff Date." + meghpiweekoffreport.getExceptionDesc());
	}	
	if (meghpiweekoffreport.WeekOffReportDateResult(week1Saturday)) {

		logResults.createLogs("Y", "PASS", "Emp WeekOff Date  Validated Successfully." + week1Saturday);
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Validating Emp WeekOff Date." + meghpiweekoffreport.getExceptionDesc());
	}
	if (OfficePage.SearchDropDown()) {
		logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
	}
	
	if (meghpiweekoffreport.WeekOffReportDateSearchCheckBox()) {

		logResults.createLogs("Y", "PASS", "Date CheckBox Selected Successfully." );
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Selecting Date CheckBox." + meghpiweekoffreport.getExceptionDesc());
	}
	if (meghpiweekoffreport.WeekOffReportDeptSearchCheckBox()) {

		logResults.createLogs("Y", "PASS", "Dept CheckBox Selected Successfully." );
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Selecting Dept CheckBox." + meghpiweekoffreport.getExceptionDesc());
	}
	if (meghpiweekoffreport.WeekOffReportDesignationSearchCheckBox()) {

		logResults.createLogs("Y", "PASS", "Designation CheckBox Selected Successfully." );
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Selecting Designation CheckBox." + meghpidailyattendancereport.getExceptionDesc());
	}
	
	if (OfficePage.SearchDropDown()) {
		logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
	}

	if (meghpiweekoffreport.WeekOffReportSearchTextField(designationname)) {

		logResults.createLogs("Y", "PASS", "Emp Assigned Designation Inputted Successfully." + designationname);
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Assigned Designation ." + meghpiweekoffreport.getExceptionDesc());
	}	
	if (meghpiweekoffreport.WeekOffReportDesignationResult(designationname)) {

		logResults.createLogs("Y", "PASS", "Emp Assigned Designation Validated Successfully." + designationname);
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Validating Emp Assigned Designation." + meghpiweekoffreport.getExceptionDesc());
	}

	if (meghpiweekoffreport.WeekOffReportSearchTextField(departmentname)) {

		logResults.createLogs("Y", "PASS", "Emp Assigned Dept Inputted Successfully." + departmentname);
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Assigned Dept." + meghpiweekoffreport.getExceptionDesc());
	}	
	if (meghpiweekoffreport.WeekOffReportDeptResult(departmentname)) {

		logResults.createLogs("Y", "PASS", "Emp Assigned Dept Validated Successfully." + departmentname);
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Validating Emp Assigned Dept." + meghpiweekoffreport.getExceptionDesc());
	}
	if (OfficePage.SearchDropDown()) {
		logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
	}
	
			if (meghpiweekoffreport.WeekOffReportDeptSearchCheckBox()) {

				logResults.createLogs("Y", "PASS", "Dept CheckBox Selected Successfully." );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Dept CheckBox." + meghpiweekoffreport.getExceptionDesc());
			}
			if (meghpiweekoffreport.WeekOffReportDesignationSearchCheckBox()) {

				logResults.createLogs("Y", "PASS", "Designation CheckBox Selected Successfully." );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Designation CheckBox." + meghpidailyattendancereport.getExceptionDesc());
			}
			
			if (meghpiweekoffreport.WeekOffReportCmpLocationSearchCheckBox()) {

				logResults.createLogs("Y", "PASS", "Cmp Location CheckBox Selected Successfully." );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Cmp Location  CheckBox." + meghpidailyattendancereport.getExceptionDesc());
			}
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}	
			
			if (meghpiweekoffreport.WeekOffReportSearchTextField(officename)) {

				logResults.createLogs("Y", "PASS", "Emp Assigned Office Name Inputted Successfully." + officename);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Assigned Office Name." + meghpiweekoffreport.getExceptionDesc());
			}	
			if (meghpiweekoffreport.WeekOffReportCmpLocationResult(officename)) {

				logResults.createLogs("Y", "PASS", "Emp Assigned Office Name Validated Successfully." + officename);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Emp Assigned Office Name." + meghpiweekoffreport.getExceptionDesc());
			}
			
			}
			
			// MPI_1336_WeekOff_Reports_02
			@Test(enabled = true, priority = 2, groups = { "Smoke" })
			public void MPI_1336_WeekOff_Reports_02()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

				ArrayList<String> data = initBase.loadExcelData("WeekOff_Reports", currTC,
						"subject,ccmail");

				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			
				MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				 MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);

			
				String subject =data.get(0);
				String ccmail =data.get(1);
			
				Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
			    String monthName = dateDetails.get("monthName");
			    String year = dateDetails.get("year");
				

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
				  if (meghpiweekoffreport.WeekOffReportButton()) {

						logResults.createLogs("Y", "PASS", "WeekOff Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Report Button." + meghpiweekoffreport.getExceptionDesc());
					}

					if (meghpiweekoffreport.WeekOffReportRowResult()) {

						logResults.createLogs("Y", "PASS", "WeekOff Report Page Loaded Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Loading  WeekOff Report Page." + meghpiweekoffreport.getExceptionDesc());
					}
					if (meghpiweekoffreport.WeekOffReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiweekoffreport.getExceptionDesc());
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
			
			// MPI_1338_WeekOff_Reports_04
			@Test(enabled = true, priority = 3, groups = { "Smoke" })
			public void MPI_1338_WeekOff_Reports_04()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionality of filter feature by selecting specific team");

			//	ArrayList<String> data = initBase.loadExcelData("WeekOff_Reports", currTC,"password,captcha");

				
				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);

			
				

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");

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
				  if (meghpiweekoffreport.WeekOffReportButton()) {

						logResults.createLogs("Y", "PASS", "WeekOff Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Report Button." + meghpiweekoffreport.getExceptionDesc());
					}

					if (meghpiweekoffreport.WeekOffReportRowResult()) {

						logResults.createLogs("Y", "PASS", "WeekOff Report Page Loaded Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Loading  WeekOff Report Page." + meghpiweekoffreport.getExceptionDesc());
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
						if (meghpiweekoffreport.WeekOffReportFilterButton()) {

							logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Filter Button." + meghpiweekoffreport.getExceptionDesc());
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
						if (meghpiweekoffreport.WeekOffReportSearchTextField(EmpFirstName)) {

							logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + EmpFirstName);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpiweekoffreport.getExceptionDesc());
						}	
						
						if (meghpiweekoffreport.WeekOffReportFilterResultValidation(EmpFirstName)) {

							logResults.createLogs("Y", "PASS", "Filtered Applied Emp Record Displayed Successfully." + EmpFirstName);
						} else {
							logResults.createLogs("Y", "FAIL", "Error While Displaying Filter Applied Emp Records." + meghpiweekoffreport.getExceptionDesc());
						} 	
			}
			
			// MPI_1339_WeekOff_Reports_05
						@Test(enabled = true, priority = 4, groups = { "Smoke" })
						public void MPI_1339_WeekOff_Reports_05()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

					//		ArrayList<String> data = initBase.loadExcelData("WeekOff_Reports", currTC, "password,captcha");

							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);

							
							Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
						    String monthName = dateDetails.get("monthName");
						    String year = dateDetails.get("year");

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
							  if (meghpiweekoffreport.WeekOffReportButton()) {

									logResults.createLogs("Y", "PASS", "WeekOff Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Report Button." + meghpiweekoffreport.getExceptionDesc());
								}

								if (meghpiweekoffreport.WeekOffReportRowResult()) {

									logResults.createLogs("Y", "PASS", "WeekOff Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  WeekOff Report Page." + meghpiweekoffreport.getExceptionDesc());
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
									if (meghpiweekoffreport.WeekOffReportFilterButton()) {

										logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Filter Button." + meghpiweekoffreport.getExceptionDesc());
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
									if (meghpiweekoffreport.WeekOffReportSearchTextField(EmpFirstName)) {

										logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + EmpFirstName);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpiweekoffreport.getExceptionDesc());
									}	
									
									if (meghpiweekoffreport.WeekOffReportFilterResultValidation(EmpFirstName)) {

										logResults.createLogs("Y", "PASS", "Filtered Applied Emp Record Displayed Successfully." + EmpFirstName);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Displaying Filter Applied Emp Records." + meghpiweekoffreport.getExceptionDesc());
									} 	
						}		
			
			
						// MPI_1340_WeekOff_Reports_06
						@Test(enabled = true, priority = 5, groups = { "Smoke" })
						public void MPI_1340_WeekOff_Reports_06()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of filter feature by selecting employee type");

						//	ArrayList<String> data = initBase.loadExcelData("WeekOff_Reports", currTC, "password,captcha");

							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);

						
							
							Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
						    String monthName = dateDetails.get("monthName");
						    String year = dateDetails.get("year");

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
							  if (meghpiweekoffreport.WeekOffReportButton()) {

									logResults.createLogs("Y", "PASS", "WeekOff Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Report Button." + meghpiweekoffreport.getExceptionDesc());
								}

								if (meghpiweekoffreport.WeekOffReportRowResult()) {

									logResults.createLogs("Y", "PASS", "WeekOff Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  WeekOff Report Page." + meghpiweekoffreport.getExceptionDesc());
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
									if (meghpiweekoffreport.WeekOffReportFilterButton()) {

										logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Filter Button." + meghpiweekoffreport.getExceptionDesc());
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
									if (meghpiweekoffreport.WeekOffReportSearchTextField(EmpFirstName)) {

										logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + EmpFirstName);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpiweekoffreport.getExceptionDesc());
									}	
									
									if (meghpiweekoffreport.WeekOffReportFilterResultValidation(EmpFirstName)) {

										logResults.createLogs("Y", "PASS", "Filtered Applied Emp Record Displayed Successfully." + EmpFirstName);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Displaying Filter Applied Emp Records." + meghpiweekoffreport.getExceptionDesc());
									} 	
						}	
			
						// MPI_1341_WeekOff_Reports_07
						@Test(enabled = true, priority = 6, groups = { "Smoke" })
						public void MPI_1341_WeekOff_Reports_07()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the filter feature functionality by selecting year and month");

				//			ArrayList<String> data = initBase.loadExcelData("WeekOff_Reports", currTC, "password,captcha");
							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);

						
							Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
						    String monthName = dateDetails.get("monthName");
						    String year = dateDetails.get("year");


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
							  if (meghpiweekoffreport.WeekOffReportButton()) {

									logResults.createLogs("Y", "PASS", "WeekOff Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Report Button." + meghpiweekoffreport.getExceptionDesc());
								}

								if (meghpiweekoffreport.WeekOffReportRowResult()) {

									logResults.createLogs("Y", "PASS", "WeekOff Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  WeekOff Report Page." + meghpiweekoffreport.getExceptionDesc());
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
									if (meghpiweekoffreport.WeekOffReportFilterButton()) {

										logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Filter Button." + meghpiweekoffreport.getExceptionDesc());
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
									if (meghpiweekoffreport.WeekOffReportSearchTextField(EmpFirstName)) {

										logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + EmpFirstName);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpiweekoffreport.getExceptionDesc());
									}	
									
									if (meghpiweekoffreport.FilteredDateValidation(year, monthName)) {

										logResults.createLogs("Y", "PASS", "Filtered Applied Year And Month Record Displayed Successfully." + year + " " + monthName);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Displaying Filter Applied Year And Month Records." + meghpiweekoffreport.getExceptionDesc());
									} 	
						}
			
						// MPI_1342_WeekOff_Reports_08
						@Test(enabled = true, priority = 7, groups = { "Smoke" })
						public void MPI_1342_WeekOff_Reports_08()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date");

				//			ArrayList<String> data = initBase.loadExcelData("WeekOff_Reports", currTC, "password,captcha");

							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);

							
							 Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();
							  String week1Saturday = (String) details.get("1stSaturday");

							  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
							    String monthName = dateDetails.get("monthName");
							    String year = dateDetails.get("year");
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
							  if (meghpiweekoffreport.WeekOffReportButton()) {

									logResults.createLogs("Y", "PASS", "WeekOff Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Report Button." + meghpiweekoffreport.getExceptionDesc());
								}

								if (meghpiweekoffreport.WeekOffReportRowResult()) {

									logResults.createLogs("Y", "PASS", "WeekOff Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  WeekOff Report Page." + meghpiweekoffreport.getExceptionDesc());
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
									if (meghpiweekoffreport.WeekOffReportFilterButton()) {

										logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error While Clicking On Filter Button." + meghpiweekoffreport.getExceptionDesc());
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
									
									if (meghpiattendancereport.FromDateSelected(week1Saturday)) {

										logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + week1Saturday);
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
									
									if (meghpiattendancereport.ToDateSelected(week1Saturday)) {

										logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + week1Saturday);
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
									if (meghpiweekoffreport.WeekOffReportSearchTextField(EmpFirstName)) {

										logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + EmpFirstName);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpiweekoffreport.getExceptionDesc());
									}	
									
									if (meghpiweekoffreport.WeekOffRptFilteredDateValidation(week1Saturday)) {

										logResults.createLogs("Y", "PASS", "Filtered Applied Date Record Displayed Successfully." + week1Saturday);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Displaying Filter Applied Date Records." + meghpiweekoffreport.getExceptionDesc());
									} 	
						}
			
						// MPI_1345_WeekOff_Reports_11
						@Test(enabled = true, priority = 8, groups = { "Smoke" })
						public void MPI_1345_WeekOff_Reports_11()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, check the employee assigned weekoff day and date.");

							ArrayList<String> data = initBase.loadExcelData("WeekOff_Reports", currTC,
									"day");
							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);
							MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

						
							String day = data.get(0);
							  Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();
							  String week1Sunday   = (String) details.get("1stSunday");
							  String firstSundayStr = Pramod.convertToUIFormat(week1Sunday);
							  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
							    String monthName = dateDetails.get("monthName");
							    String year = dateDetails.get("year");
						
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
							  if (meghpiweekoffreport.WeekOffReportButton()) {

									logResults.createLogs("Y", "PASS", "WeekOff Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Report Button." + meghpiweekoffreport.getExceptionDesc());
								}

								if (meghpiweekoffreport.WeekOffReportRowResult()) {

									logResults.createLogs("Y", "PASS", "WeekOff Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  WeekOff Report Page." + meghpiweekoffreport.getExceptionDesc());
								}
								if (meghpiweekoffreport.WeekOffReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpiweekoffreport.getExceptionDesc());
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
									if (OfficePage.SearchDropDown()) {
										logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
									}

									
									if (meghpiweekoffreport.WeekOffReportDateSearchCheckBox()) {

										logResults.createLogs("Y", "PASS", "Date CheckBox Selected Successfully." );
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Selecting Date CheckBox." + meghpiweekoffreport.getExceptionDesc());
									}


									if (OfficePage.SearchDropDown()) {
										logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
									}
									
									
									
									if (meghpiweekoffreport.WeekOffReportSearchTextField(firstSundayStr)) {

										logResults.createLogs("Y", "PASS", "Emp WeekOff Date Inputted Successfully." + firstSundayStr);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Emp WeekOff Date." + meghpiweekoffreport.getExceptionDesc());
									}	
									if (meghpiweekoffreport.WeekOffReportDateResult(firstSundayStr)) {

										logResults.createLogs("Y", "PASS", "Emp WeekOff Date  Validated Successfully." + firstSundayStr);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Validating Emp WeekOff Date." + meghpiweekoffreport.getExceptionDesc());
									}	
									if (meghpiweekoffreport.WeekOffReportDayResult(day)) {

										logResults.createLogs("Y", "PASS", "Emp WeekOff Day Validated Successfully." + day);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Validating Emp WeekOff Day." + meghpiweekoffreport.getExceptionDesc());
									}
									}
			
						// MPI_1346_WeekOff_Reports_12
						@Test(enabled = true, priority = 9, groups = { "Smoke" })
						public void MPI_1346_WeekOff_Reports_12()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, request a weekoff change request as an employee and approve the week-off change request as an admin. Then, ensure that the Week-Off Report displays the updated week-off day and date.");

							ArrayList<String> data = initBase.loadExcelData("WeekOff_Reports", currTC,
									"day,reason");
							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							MeghPIWeekOffReportPage meghpiweekoffreport = new MeghPIWeekOffReportPage(driver);
							MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

							
							String day = data.get(0);
							String reason = data.get(1);
							
							

							    
							    Map<String, Object> details = Pramod.getLastMonthWeekendAndWorkingDetailsfoentiremonth();
							    String saturday = (String) details.get("1stSaturday");
							    List<String> week1WorkingDays = (List<String>) details.get("workingAfter1Weekoff");
							    String monday     = week1WorkingDays.get(0);
							    String workingDayUI =   Pramod.convertToUIFormat(monday);
							    
							    Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
							    String monthName = dateDetails.get("monthName");
							    String year = dateDetails.get("year");
							    
							    MeghLoginPage meghloginpage = new MeghLoginPage(driver);


								if (meghloginpage.MainLandingPage()) {
									logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Dashboard Page Not Displayed." + meghloginpage.getExceptionDesc());
								}	
							if (RolePermissionpage.EmployeeAttendanceButton()) {

								logResults.createLogs("Y", "PASS", " Attendance Module Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL",
										"Error While Clicking On Attendane Module." + RolePermissionpage.getExceptionDesc());
							}
							if (meghpiweekoffreport.WeekOffButtonInAttendance()) {

								logResults.createLogs("Y", "PASS", "WeekOff  Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff  Button." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.ApplyWeekOffButtonInAttendance()) {

								logResults.createLogs("Y", "PASS", "Apply WeekOff  Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply WeekOff  Button." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.ApplyWeekOffForOthersButtonInAttendance()) {

								logResults.createLogs("Y", "PASS", "Apply  WeekOff For Others  Button Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply  WeekOff For Others  Button." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.EmpDrpDownWeekoffApply()) {

								logResults.createLogs("Y", "PASS", "Emp Drop Down Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp Selection DropDown." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.EmpNameInputWeekoffApply(EmpFirstName)) {

								logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + EmpFirstName);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.EmpNameSelectedWeekoffApply()) {

								logResults.createLogs("Y", "PASS", "Emp Name Selected Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Name." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.OldWeekoffDateTextField()) {

								logResults.createLogs("Y", "PASS", "Old WeekoffDate TextField Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Old WeekoffDate TextField." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.FromDateSelected(saturday)) {

								logResults.createLogs("Y", "PASS", "Old WeekoffDate Inputted Successfully." + saturday);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting On Old WeekoffDate ." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.EmpDrpDownWeekoffApply()) {

								logResults.createLogs("Y", "PASS", "Emp Drop Down Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Emp Selection DropDown." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.NewWeekoffDateTextField()) {

								logResults.createLogs("Y", "PASS", "New WeekoffDate TextField Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On New WeekoffDate TextField." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.NewWeekOffDateSelected(monday)) {

								logResults.createLogs("Y", "PASS", "New WeekoffDate Inputted Successfully." + monday);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting On New WeekoffDate ." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.WeekOffReason(reason)) {

								logResults.createLogs("Y", "PASS", " Weekoff Reason Inputted Successfully." + reason);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting Weekoff Reason." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.WeekOffReason(reason)) {

								logResults.createLogs("Y", "PASS", " Weekoff Reason Inputted Successfully." + reason);
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Inputting Weekoff Reason." + meghpiweekoffreport.getExceptionDesc());
							}
							if (meghpiweekoffreport.WeekOffApplySaveButton()) {

								logResults.createLogs("Y", "PASS", "Weekoff Apply Clicked Successfully.");
							} else {
								logResults.createLogs("Y", "FAIL", "Error While Clicking On Weekoff Apply Button." + meghpiweekoffreport.getExceptionDesc());
							}
						
							  if (RolePermissionpage.ReprtButton()) {

									logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
								}
							  if (meghpiweekoffreport.WeekOffReportButton()) {

									logResults.createLogs("Y", "PASS", "WeekOff Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On WeekOff Report Button." + meghpiweekoffreport.getExceptionDesc());
								}

								if (meghpiweekoffreport.WeekOffReportRowResult()) {

									logResults.createLogs("Y", "PASS", "WeekOff Report Page Loaded Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Loading  WeekOff Report Page." + meghpiweekoffreport.getExceptionDesc());
								}

								if (meghpiweekoffreport.WeekOffReportFilterButton()) {

													logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
												} else {
													logResults.createLogs("Y", "FAIL",
															"Error While Clicking On Filter Button." + meghpiweekoffreport.getExceptionDesc());
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
									if (OfficePage.SearchDropDown()) {
										logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
									}

									
									if (meghpiweekoffreport.WeekOffReportDateSearchCheckBox()) {

										logResults.createLogs("Y", "PASS", "Date CheckBox Selected Successfully." );
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Selecting Date CheckBox." + meghpiweekoffreport.getExceptionDesc());
									}


									if (OfficePage.SearchDropDown()) {
										logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
									} else {
										logResults.createLogs("Y", "FAIL",
												"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
									}
										
									
									
									if (meghpiweekoffreport.WeekOffReportSearchTextField(workingDayUI)) {

										logResults.createLogs("Y", "PASS", "Emp WeekOff Date Inputted Successfully." + workingDayUI);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Inputting Emp WeekOff Date." + meghpiweekoffreport.getExceptionDesc());
									}	
									if (meghpiweekoffreport.WeekOffReportDateResult(workingDayUI)) {

										logResults.createLogs("Y", "PASS", "Emp WeekOff Date  Validated Successfully." + workingDayUI);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Validating Emp WeekOff Date." + meghpiweekoffreport.getExceptionDesc());
									}	
									if (meghpiweekoffreport.WeekOffReportDayResult(day)) {

										logResults.createLogs("Y", "PASS", "Emp WeekOff Day Validated Successfully." + day);
									} else {
										logResults.createLogs("Y", "FAIL", "Error While Validating Emp WeekOff Day." + meghpiweekoffreport.getExceptionDesc());
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
