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

import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeMonthlyReportPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeReportsPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIOverTimeMonthlyReportTest {


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
   
    private String EmpID = "";
   
    private String EmpFirstName = "";
 
    private String PunchinDate = "";
	
	
	
    @Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
	//	driver = loadDriver.getDriver(device);

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
		EmpID = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpID", "");
		EmpFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpFirstName", "");
		
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		
	}
	
	
	// MPI_1225_OverTimeReport_01
	@Test(enabled = true, priority = 01, groups = { "Smoke" })
	public void MPI_1225_OverTimeReport_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionalities of search feature by selecting each search option");

		ArrayList<String> data = initBase.loadExcelData("OverTimeMonthly_Reports", currTC,
				"password,captcha");

		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
	
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
	
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
		MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);
		
		
		int i = 0;
		String password = data.get(i++);
		String captcha = data.get(i++);
		String EmpIDRow ="";
		String EmpLastName ="";
		
		Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");

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
	        if (meghpiovertimemonthlyreport.OverTimeReportMainButton()) {

				logResults.createLogs("Y", "PASS", "OverTime Report Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
			}
	       
			if (meghpiovertimemonthlyreport.OverTimeMonthlyReportFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
			if (meghpiovertimemonthlyreport.OverTimeMonthlyReportEmpIDRecord()) {
				EmpIDRow =  meghpiovertimemonthlyreport.getempid;
				logResults.createLogs("Y", "PASS", "Emp ID Extracted Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Extracting Emp ID." + meghpiovertimemonthlyreport.getExceptionDesc());
			}
			if (meghpiovertimemonthlyreport.OverTimeMonthlyReportEmpNameRecord()) {
				EmpLastName = meghpiovertimemonthlyreport.getemplastname;
				logResults.createLogs("Y", "PASS", "Emp Last Name Extracted Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Extracting Emp Last Name." + meghpiovertimemonthlyreport.getExceptionDesc());
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
			
	        if (meghpiovertimemonthlyreport.OverTimeMonthlyReportSearchTextField(EmpIDRow)) {

				logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + EmpIDRow);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpiovertimemonthlyreport.getExceptionDesc());
			}			
	        
	        if (meghpiovertimemonthlyreport.OverTimeReportEmpIDRecordValidation(EmpIDRow)) {

				logResults.createLogs("Y", "PASS", "Emp ID Displayed Successfully." + EmpIDRow);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Displaying Emp ID." + meghpiovertimemonthlyreport.getExceptionDesc());
			}	
	        if (meghpiovertimemonthlyreport.OverTimeMonthlyReportSearchTextField(EmpLastName)) {

				logResults.createLogs("Y", "PASS", "Emp Last Name Inputted Successfully." + EmpLastName);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp LastName." + meghpiovertimemonthlyreport.getExceptionDesc());
			}	
	        if (meghpiovertimemonthlyreport.OverTimeReportEmpNameRecordValidation(EmpLastName)) {

				logResults.createLogs("Y", "PASS", "Inputted Emp Last Name Record Displayed Successfully." + EmpLastName);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Displaying Emp Last Name Record." + meghpiovertimemonthlyreport.getExceptionDesc());
			}
	}	
	
	// MPI_1226_OverTimeReport_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_1226_OverTimeReport_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

		ArrayList<String> data = initBase.loadExcelData("OverTimeMonthly_Reports", currTC,
				"subject,ccmail");

	
	
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
	
		
		MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
		MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);


	
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
		  if (meghpiovertimemonthlyreport.OverTimeReportMainButton()) {

				logResults.createLogs("Y", "PASS", "OverTime Report Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
			}
	       
			if (meghpiovertimemonthlyreport.OverTimeMonthlyReportFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
	
	// MPI_1234_OverTimeReport_10
			@Test(enabled = true, priority = 3, groups = { "Smoke" })
			public void MPI_1234_OverTimeReport_10()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, ensure that only the approved OT count data is displayed for the employee with the corresponding OT date.");

				ArrayList<String> data = initBase.loadExcelData("OverTimeMonthly_Reports", currTC,
						"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,othours");


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
			        String othours = data.get(i++);
			        
			        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

					// get first day of month
				
					 
			        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
			         PunchinDate  = dateDetails.get("month9WorkingDate");

					    String monthName = dateDetails.get("monthName");
					    String year = dateDetails.get("year");
					    String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
					 String fromDateofstatus = PunchinDate + fromDateofuserstatus1;
				        String toDateofstatus = PunchinDate + toDateofuserstatus2;
					   
					    
						String inouttime = PunchinDate + " " + inouttime1;
						String secondInOutTime = PunchinDate + " " + secondInOutTime2;
					
					
				
				
				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
					MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);

		
			    // Punch IN
			    Response punchInResponse = apiPage.executeSuccessTransaction(
			            baseuri, loginendpoint,
			            Emailid, password, cmpcode,
			            baseuri, endpointoftransaction,
			            cardnumber, cardtype, deviceuniqueid,
			            bio1finger, bio2finger, EmpID,
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
			            bio1finger, bio2finger, EmpID,
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
			            Emailid, password, cmpcode,
			            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
			            EmpID, firstDayOfMonth + "T00:00:00.000Z"
			    );

			    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + EmpID);
			    } else {
			        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
			    }
			    try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 // Get User Status
		        Response validation = apiPage.executeGetUserStatus(
		                baseuri, loginendpoint,
		                Emailid, password, cmpcode,
		                baseuri, statusEndpoint,
		                EmpID, fromDateofstatus, toDateofstatus
		        );

		        if (validation.statusCode() == 200) {
		            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

		            // Handle null OTHours â†’ default to 00:00
		            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
		            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

		            // Make sentence using excel inputs
		            String finalSentence = String.format(
		                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
		                description, EmpID, inouttime, inouttime1, secondInOutTime,
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
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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
			        if (meghpiovertimemonthlyreport.OverTimeReportMainButton()) {

						logResults.createLogs("Y", "PASS", "OverTime Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
					if (meghpiovertimemonthlyreport.OverTimeMonthlyReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
		
			        if (meghpiovertimemonthlyreport.OverTimeMonthlyReportSearchTextField(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + EmpFirstName);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpiovertimemonthlyreport.getExceptionDesc());
					}		
			        
			        if (meghpiovertimemonthlyreport.OverTimeDateHeaderMatchWithTableData(PunchinDate,othours)) {

						logResults.createLogs("Y", "PASS", "Emp OT Hours Is Matched With Respective Date Successfully." + othours + " " +PunchinDate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Matching Emp OT Hours With Respctive OT Date." + meghpiovertimemonthlyreport.getExceptionDesc());
					}
				
			}
	
			// MPI_1228_OverTimeReport_04
			@Test(enabled = true, priority = 4, groups = { "Smoke" })
			public void MPI_1228_OverTimeReport_04()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionality of filter feature by selecting specific team");

			//	ArrayList<String> data = initBase.loadExcelData("OverTimeMonthly_Reports", currTC, "password,captcha");

				
			
				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
				MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);

			
				MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			
				
			
				
				
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

 if (meghpiovertimemonthlyreport.OverTimeReportMainButton()) {

		logResults.createLogs("Y", "PASS", "OverTime Report Button Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
						if (meghpiovertimemonthlyreport.OverTimeMonthlyReportFilterButton()) {

							logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Filter Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
						
						if (meghpiovertimemonthlyreport.OverTimeReportEmpIDRecord()) {

							logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
						}     
			}	
			
			// MPI_1229_OverTimeReport_05
			@Test(enabled = true, priority = 5, groups = { "Smoke" })
			public void MPI_1229_OverTimeReport_05()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

			//	ArrayList<String> data = initBase.loadExcelData("OverTimeMonthly_Reports", currTC, "password,captcha");

				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			
			
				MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);

				
			
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
			       
			        if (meghpiovertimemonthlyreport.OverTimeReportMainButton()) {

			    		logResults.createLogs("Y", "PASS", "OverTime Report Button Clicked Successfully.");
			    	} else {
			    		logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
					if (meghpiovertimemonthlyreport.OverTimeMonthlyReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
					
					if (meghpiovertimemonthlyreport.OverTimeReportEmpIDRecord()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
					}      
		}  	
	
			// MPI_1230_OverTimeReport_06
			@Test(enabled = true, priority = 6, groups = { "Smoke" })
			public void MPI_1230_OverTimeReport_06()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionality of filter feature by selecting employee type");

			//	ArrayList<String> data = initBase.loadExcelData("OverTimeMonthly_Reports", currTC, "password,captcha");

				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			
			
				MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);

				
			
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
			       
			        if (meghpiovertimemonthlyreport.OverTimeReportMainButton()) {

			    		logResults.createLogs("Y", "PASS", "OverTime Report Button Clicked Successfully.");
			    	} else {
			    		logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
					if (meghpiovertimemonthlyreport.OverTimeMonthlyReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
					
					if (meghpiovertimemonthlyreport.OverTimeReportEmpIDRecord()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
					}      
		} 
	
			// MPI_1231_OverTimeReport_07
			@Test(enabled = true, priority = 7, groups = { "Smoke" })
			public void MPI_1231_OverTimeReport_07()  {
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify this, check the functionality of filter feature by selecting month and year");

			//	ArrayList<String> data = initBase.loadExcelData("OverTimeMonthly_Reports", currTC, "password,captcha");

				MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			
			
				MeghPIOverTimeReportsPage meghpiovertimepage = new MeghPIOverTimeReportsPage(driver);
				MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);

		
				
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
			       
			        if (meghpiovertimemonthlyreport.OverTimeReportMainButton()) {

			    		logResults.createLogs("Y", "PASS", "OverTime Report Button Clicked Successfully.");
			    	} else {
			    		logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
					if (meghpiovertimemonthlyreport.OverTimeMonthlyReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
					
					if (meghpiovertimemonthlyreport.OverTimeReportEmpIDRecord()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiovertimepage.getExceptionDesc());
					}      
		} 
			
			// MPI_1237_OverTimeReport_11
						@Test(enabled = true, priority = 8, groups = { "Smoke" })
						public void MPI_1237_OverTimeReport_11()  {
							String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
							logResults.createExtentReport(currTC);
							logResults.setScenarioName(
									"To verify this, perform OT for the same employee on another day and ensure that the OT count for both days is displayed under their respective date columns.");

							ArrayList<String> data = initBase.loadExcelData("OverTimeMonthly_Reports", currTC,
									"password,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,othours,othourstwo");


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
						        String othours = data.get(i++);
						        String othourstwo = data.get(i++);					        
						        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

						      Map<String, String> details = Pramod.getLastMonthWorkingDatesDetails();
						      String PunchinDate2 = details.get("month10WorkingDate");
						      String monthName = details.get("monthName");
							    String year = details.get("year");
							    String firstDayOfMonth = details.get("firstDayOfMonth");
						        
								 String fromDateofstatus = PunchinDate2 + fromDateofuserstatus1;
							        String toDateofstatus = PunchinDate2 + toDateofuserstatus2;
								   
								    
									String inouttime = PunchinDate2 + " " + inouttime1;
									String secondInOutTime = PunchinDate2 + " " + secondInOutTime2;
								
								
							
							MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
							MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
							 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
								MeghPIOverTimeMonthlyReportPage meghpiovertimemonthlyreport = new MeghPIOverTimeMonthlyReportPage(driver);

					
						    // Punch IN
						    Response punchInResponse = apiPage.executeSuccessTransaction(
						            baseuri, loginendpoint,
						            Emailid, password, cmpcode,
						            baseuri, endpointoftransaction,
						            cardnumber, cardtype, deviceuniqueid,
						            bio1finger, bio2finger, EmpID,
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
						            bio1finger, bio2finger, EmpID,
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
						            Emailid, password, cmpcode,
						            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
						            EmpID, firstDayOfMonth + "T00:00:00.000Z"
						    );

						    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
						        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + EmpID);
						    } else {
						        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
						    }
						    try {
								Thread.sleep(20000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						 // Get User Status
					        Response validation = apiPage.executeGetUserStatus(
					                baseuri, loginendpoint,
					                Emailid, password, cmpcode,
					                baseuri, statusEndpoint,
					                EmpID, fromDateofstatus, toDateofstatus
					        );

					        if (validation.statusCode() == 200) {
					            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

					            // Handle null OTHours â†’ default to 00:00
					            String actualOTHours = validation.jsonPath().getString("[0].OTHours");
					            actualOTHours = (actualOTHours == null || actualOTHours.isEmpty()) ? "00:00" : actualOTHours;

					            // Make sentence using excel inputs
					            String finalSentence = String.format(
					                "%s â€“ This Employee %s on %s, punched IN at %s and Punch Out at %s. Final Status = %s (Expected = %s), OT Hours = %s (Expected = %s)",
					                description, EmpID, inouttime, inouttime1, secondInOutTime,
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
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
						        if (meghpiovertimemonthlyreport.OverTimeReportMainButton()) {

									logResults.createLogs("Y", "PASS", "OverTime Report Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Clicking On OverTime Report Button." + meghpiovertimemonthlyreport.getExceptionDesc());
								}
						       
								if (meghpiovertimemonthlyreport.OverTimeMonthlyReportFilterButton()) {

									logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
								} else {
									logResults.createLogs("Y", "FAIL",
											"Error While Clicking On Filter Button." + meghpiovertimemonthlyreport.getExceptionDesc());
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
					
						        if (meghpiovertimemonthlyreport.OverTimeMonthlyReportSearchTextField(EmpFirstName)) {

									logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + EmpFirstName);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID." + meghpiovertimemonthlyreport.getExceptionDesc());
								}		
						        
						        if (meghpiovertimemonthlyreport.OverTimeDateHeaderMatchWithTableDatas(PunchinDate, othours, PunchinDate2,othourstwo)) {

									logResults.createLogs("Y", "PASS", "Emp OT Hours Is Matched With Respective Date Successfully." + othours + " " +PunchinDate + " " + PunchinDate2 + " " + othourstwo);
								} else {
									logResults.createLogs("Y", "FAIL", "Error While Matching Emp OT Hours With Respctive OT Date." + meghpiovertimemonthlyreport.getExceptionDesc());
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
