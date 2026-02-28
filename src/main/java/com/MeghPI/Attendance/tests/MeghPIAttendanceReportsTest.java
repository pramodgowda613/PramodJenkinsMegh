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

import com.MeghPI.Attendance.pages.MeghLeavePage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIAttendanceReportsTest {

	
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
    private String DeviceName = "";
    private String EmpFirstName = "";
  
	
	
	
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
		EmpID = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpID", "");
		DeviceName = Utils.propsReadWrite("data/addmaster.properties", "get", "DeviceName", "");
		EmpFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "EmpFirstName", "");
		
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;
		departmentname ="AutoDN" + initBase.executionRunTime;
		teamname = "AutoTN" +  initBase.executionRunTime;
		designationname = "AutoDESN" +  initBase.executionRunTime;
		entityname = "AutoEN" + initBase.executionRunTime;
		
	}
	
	
	
	// MPI_934_punch_report_01
		@Test(enabled = true, priority = 1, groups = { "Smoke" })
		public void MPI_934_punch_report_01()   {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"check the functionality of search feature and ensure using all search options admin can search the punch records");

			ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
					"password,captcha,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint");


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
		        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

		        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

				String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
				String monthName = dateDetails.get("monthName");
				String year = dateDetails.get("year");
				String month13WorkingDate  = dateDetails.get("month13WorkingDate");

		        
		        
		        String fromDateofstatus = month13WorkingDate + fromDateofuserstatus1;
		        String toDateofstatus = month13WorkingDate + toDateofuserstatus2;
			   
			    
				String inouttime = month13WorkingDate + " " + inouttime1;
				String secondInOutTime = month13WorkingDate + " " + secondInOutTime2;
				
				String thirdinouttime =  month13WorkingDate + " " + thirdinouttime3;
				String fourthinouttime = month13WorkingDate + " " + fourthinouttime4;

				
			
			MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage meghloginpage = new MeghLoginPage(driver);
			
			MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

			 
			

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
		    
		    // Punch in 2nd time
		    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
		            baseuri, loginendpoint,
		            Emailid, password, cmpcode,
		            baseuri, endpointoftransaction,
		            cardnumber, cardtype, deviceuniqueid,
		            bio1finger, bio2finger, EmpID,
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
		            bio1finger, bio2finger, EmpID,
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
		            Emailid, password, cmpcode,
		            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
		            EmpID, firstDayOfMonth + "T00:00:00.000Z"
		    );

		    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
		        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + EmpID);
		    } else {
		        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		    }
		  //  Thread.sleep(20000);
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
	                description, EmpID, month13WorkingDate, inouttime1, fourthinouttime4,
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
			MeghLoginTest meghlogintest = new MeghLoginTest();

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

			
			if (RolePermissionpage.AttendanceReport()) {

				logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
			}

			if (RolePermissionpage.PunchReport()) {

				logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
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
			if (meghpiattendancereport.FilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
			
			if (meghpiattendancereport.PunchTimeCheckbox()) {

				logResults.createLogs("Y", "PASS", "PunchTime Search Option Checkbox Selected Successfully." );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting PunchTime Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.PunchModeCheckbox()) {

				logResults.createLogs("Y", "PASS", "Punch Mode Search Option Checkbox Selected Successfully." );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Punch Mode Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
			}
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			if (meghpiattendancereport.PunchReportSearchTextField(inouttime1)) {

				logResults.createLogs("Y", "PASS", "Punch In Time Inputted Successfully." + inouttime1 );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Punch In Time." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.FirstRecordDateAndTime(inouttime1)) {

				logResults.createLogs("Y", "PASS", "Punch In Record Displayed Successfully." + inouttime1 );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying Inputted Punch In Time Record." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiattendancereport.PunchReportSearchTextField(secondMode)) {

				logResults.createLogs("Y", "PASS", "Punch Mode Inputted Successfully." + secondMode );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Punch Mode." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiattendancereport.PunchModeInFirstRecord(secondMode)) {

				logResults.createLogs("Y", "PASS", "Punch Mode Record Displayed Successfully." + secondMode );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying Inputted Punch Mode Record." + meghpiattendancereport.getExceptionDesc());
			}
		
		}
	
		// MPI_935_punch_report_02
				@Test(enabled = true, priority = 2, groups = { "Smoke" })
				public void MPI_935_punch_report_02() {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,subject,ccmail");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String subject = data.get(2);
					String ccmail = data.get(3) ;
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					
		        
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

				
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.PunchReport()) {

						logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.MailReportButton()) {

						logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailReportButton." + meghpiattendancereport.getExceptionDesc());
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
	
				// MPI_937_punch_report_04
				@Test(enabled = true, priority = 3, groups = { "Smoke" })
				public void MPI_937_punch_report_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.PunchReport()) {

						logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}
	
				// MPI_938_punch_report_05
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
				public void MPI_938_punch_report_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.PunchReport()) {

						logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}
				
				// MPI_939_punch_report_06
				@Test(enabled = true, priority = 5, groups = { "Smoke" })
				public void MPI_939_punch_report_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");
				
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.PunchReport()) {

						logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}
	
				// MPI_940_punch_report_07
				@Test(enabled = true, priority = 6, groups = { "Smoke" })
				public void MPI_940_punch_report_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the filter feature functionality by selecting year and month");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.PunchReport()) {

						logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}
				
				// MPI_941_punch_report_08
				@Test(enabled = true, priority = 7, groups = { "Smoke" })
				public void MPI_941_punch_report_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date and select punch in and out time");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,inouttime,secondinouttime");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String inouttime = data.get(2);
					String secondinouttime = data.get(3);

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");
			String month13WorkingDate  = dateDetails.get("month13WorkingDate");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.PunchReport()) {

						logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FromDateSelected(month13WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month13WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month13WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month13WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromTimeSelected(inouttime)) {

						logResults.createLogs("Y", "PASS", "FromTime Selected Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToTimeTextField()) {

						logResults.createLogs("Y", "PASS", "To Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On To TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToTimeSelected(secondinouttime)) {

						logResults.createLogs("Y", "PASS", "ToTime Selected Successfully." + secondinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EmpTypeDrpDownClick()) {

						logResults.createLogs("Y", "PASS", "Emp Type Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On  Emp Type." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FirstRecordResult()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}
				// MPI_1000_punch_report_12
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_1000_punch_report_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, Ensure employee record is displayed after employee did punch in and punch out.");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,secondmode,secondinouttime,inouttime");

					
					String password = data.get(0);
					String captcha = data.get(1);
					
				
					String secondmode = data.get(3);
					String secondinouttime = data.get(4);
					String inouttime = data.get(5);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
					String month13WorkingDate  = dateDetails.get("month13WorkingDate");

			
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.PunchReport()) {

						logResults.createLogs("Y", "PASS", "PunchReport Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Punch Report." + RolePermissionpage.getExceptionDesc());
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
					
					if (meghpiattendancereport.FilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FromDateSelected(month13WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month13WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month13WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month13WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromTimeSelected(inouttime)) {

						logResults.createLogs("Y", "PASS", "FromTime Selected Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToTimeTextField()) {

						logResults.createLogs("Y", "PASS", "To Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On To TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToTimeSelected(secondinouttime)) {

						logResults.createLogs("Y", "PASS", "ToTime Selected Successfully." + secondinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EmpTypeDrpDownClick()) {

						logResults.createLogs("Y", "PASS", "Emp Type Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On  Emp Type." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.EmployeeIDCheckBox()) {

						logResults.createLogs("Y", "PASS", "EmployeeID Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EmployeeID Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
		
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.PunchReportSearchTextField(EmpID)) {

						logResults.createLogs("Y", "PASS", "Employee ID Inputted Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Employee ID." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.validatePunchRow(EmpID, month13WorkingDate, inouttime, secondmode)) {

						logResults.createLogs("Y", "PASS", "Punch In Record Displayed Successfully." + EmpID + month13WorkingDate + inouttime + secondmode );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch In Time Record." + meghpiattendancereport.getExceptionDesc());
					}
			
				}
				
					
				// MPI_945_inout_reports_01
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_945_inout_reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"check the functionality of search feature and ensure using all search options admin can search the punch records.");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,secondmode,inouttime");

					
					String password = data.get(0);
					String captcha = data.get(1);
					
				
					String secondmode = data.get(2);
					
					String inouttime = data.get(3);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.InOutReport()) {

						logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonInOut()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.PunchTimeCheckBoxInOut()) {

						logResults.createLogs("Y", "PASS", "PunchTime Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting PunchTime Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchModeInOut()) {

						logResults.createLogs("Y", "PASS", "Punch Mode Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch Mode Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldInOut(inouttime)) {

						logResults.createLogs("Y", "PASS", "Punch In Time Inputted Successfully." + inouttime );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch In Time." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchTimeFirstRowinout(inouttime)) {

						logResults.createLogs("Y", "PASS", "Punch In Record Displayed Successfully." + inouttime );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch In Time Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldInOut(secondmode)) {

						logResults.createLogs("Y", "PASS", "Punch Mode Inputted Successfully." + secondmode );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch Mode." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.PunchModeFirstRowInout(secondmode)) {

						logResults.createLogs("Y", "PASS", "Punch Mode Record Displayed Successfully." + secondmode );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch Mode Record." + meghpiattendancereport.getExceptionDesc());
					}
				}
		
				// MPI_946_inout_reports_02
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_946_inout_reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,subject,ccmail");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String subject = data.get(2);
					String ccmail = data.get(3) ;
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					
		        
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.InOutReport()) {

						logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterButtonInOut()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.InoutReportMailButton()) {

						logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailReportButton." + meghpiattendancereport.getExceptionDesc());
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
				
				// MPI_948_inout_reports_04
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_948_inout_reports_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.InOutReport()) {

						logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonInOut()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordResultInout()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}
				
				// MPI_949_inout_reports_05
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_949_inout_reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.InOutReport()) {

						logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonInOut()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordResultInout()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
				
				// MPI_950_inout_reports_06
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_950_inout_reports_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
				
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.InOutReport()) {

						logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonInOut()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordResultInout()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}
				
				// MPI_951_inout_reports_07
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_951_inout_reports_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the filter feature functionality by selecting year and month");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.InOutReport()) {

						logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonInOut()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordResultInout()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
				
				// MPI_952_inout_reports_08
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_952_inout_reports_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date and select punch in and out timeÂ ");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,inouttime,secondinouttime");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String inouttime = data.get(2);
					String secondinouttime = data.get(3);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
					String month13WorkingDate  = dateDetails.get("month13WorkingDate");

		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.InOutReport()) {

						logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonInOut()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FromDateSelected(month13WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month13WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month13WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month13WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromTimeSelected(inouttime)) {

						logResults.createLogs("Y", "PASS", "FromTime Selected Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToTimeTextField()) {

						logResults.createLogs("Y", "PASS", "To Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On To TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToTimeSelected(secondinouttime)) {

						logResults.createLogs("Y", "PASS", "ToTime Selected Successfully." + secondinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EmpTypeDrpDownClick()) {

						logResults.createLogs("Y", "PASS", "Emp Type Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On  Emp Type." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FirstRecordResultInout()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
				
				// MPI_1004_inout_reports_12
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
				public void MPI_1004_inout_reports_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the IN and OUT modes along with the corresponding time logs for the respective employee ID.");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,secondmode,secondinouttime,inouttime,mode,thirdinouttime,thirdmode,fourthinouttime,fourthmode");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String secondmode = data.get(2);
					String secondinouttime = data.get(3);
					String inouttime = data.get(4);
					String mode = data.get(5);
					String thirdinouttime = data.get(6);
					String thirdmode = data.get(7);
					String fourthinouttime = data.get(8);
					String fourthmode = data.get(9);
					
					  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
						String month13WorkingDate  = dateDetails.get("month13WorkingDate");
			
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (RolePermissionpage.InOutReport()) {

						logResults.createLogs("Y", "PASS", "InOutReport Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying InOut Report." + RolePermissionpage.getExceptionDesc());
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
					
					if (meghpiattendancereport.FilterButtonInOut()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FromDateSelected(month13WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month13WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month13WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month13WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromTimeSelected(inouttime)) {

						logResults.createLogs("Y", "PASS", "FromTime Selected Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToTimeTextField()) {

						logResults.createLogs("Y", "PASS", "To Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On To TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToTimeSelected(secondinouttime)) {

						logResults.createLogs("Y", "PASS", "ToTime Selected Successfully." + secondinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EmpTypeDrpDownClick()) {

						logResults.createLogs("Y", "PASS", "Emp Type Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On  Emp Type." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.EmpIDCheckboxInout()) {

						logResults.createLogs("Y", "PASS", "EmployeeID Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EmployeeID Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
		
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldInOut(EmpID)) {

						logResults.createLogs("Y", "PASS", "Employee ID Inputted Successfully." + EmpID );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Employee ID." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.validateInOutTimes(EmpID, inouttime, mode, secondinouttime, secondmode, thirdinouttime, thirdmode, fourthinouttime, fourthmode)) {

						logResults.createLogs("Y", "PASS", "2nd Punch Out Record Displayed Successfully." + EmpID + month13WorkingDate + " " + fourthinouttime + " " + fourthmode );
						logResults.createLogs("Y", "PASS", "2nd Punch In Record Displayed Successfully." + EmpID + month13WorkingDate + " " + thirdinouttime + " " + thirdmode );
						logResults.createLogs("Y", "PASS", "1st Punch Out Record Displayed Successfully." + EmpID + month13WorkingDate + " " + secondinouttime + " " + secondmode );
						logResults.createLogs("Y", "PASS", "1st Punch In Record Displayed Successfully." + EmpID + month13WorkingDate + " " + inouttime + " " + mode );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch In Time Record." + meghpiattendancereport.getExceptionDesc());
					}
			
				}
				
				//Attendance Report
				// MPI_956_Attendance_report_01
				@Test(enabled = true, priority = 17, groups = { "Smoke" })
				public void MPI_956_Attendance_report_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"check the functionality of search feature and ensure using all search options admin can search the punch records");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,fourthinouttime,fourthmode,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,totothour,updateattendanceendpoint,reportstatus,shift");


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
				        String reportstatus = data.get(i++);
				        String shift = data.get(i++);
				       
				        
				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month14WorkingDate = dateDetails.get("month14WorkingDate");

						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
						
						
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

				        String fromDateofstatus = month14WorkingDate + fromDateofuserstatus1;
				        String toDateofstatus = month14WorkingDate + toDateofuserstatus2;
					   
					    
						String inouttime = month14WorkingDate + " " + inouttime1;
						String secondInOutTime = month14WorkingDate + " " + secondInOutTime2;
						
						String thirdinouttime =  month14WorkingDate + " " + thirdinouttime3;
						String fourthinouttime = month14WorkingDate + " " + fourthinouttime4;
						
						// get first day of month
											
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();

					MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

					 
					

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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, EmpID,
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
				            bio1finger, bio2finger, EmpID,
				            locationid, fourthinouttime, fourthmode, photo
				    );

				    if (fourthpunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "Last Punch OUT executed successfully at " + fourthinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch OUT failed." + fourthpunchOutResponse.asString());
			            return;
			        } // Thread.sleep(5000);
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
				  //  Thread.sleep(20000);
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
			                description, EmpID, month14WorkingDate, inouttime1, secondInOutTime2,
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.PunchInTimeCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Punch In Time Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch In Time Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchOutTimeCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Punch out Time Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch Out Time Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldAttendancerpt(inouttime1)) {

						logResults.createLogs("Y", "PASS", "Punch In Time Inputted Successfully." + inouttime1 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch In Time." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchinTimeRecordAttendancerpt(inouttime1)) {

						logResults.createLogs("Y", "PASS", "Punch In Record Displayed Successfully." + inouttime1 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch In Time Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldAttendancerpt(fourthinouttime4)) {

						logResults.createLogs("Y", "PASS", "Punch Out Inputted Successfully." + fourthinouttime4 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch Out Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.PunchOutTimeRecordAttendancerpt(fourthinouttime4)) {

						logResults.createLogs("Y", "PASS", "Punch OUt Record Displayed Successfully." + fourthinouttime4 );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Punch Out Record." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchInTimeCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Punch In Time Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch In Time Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.PunchOutTimeCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Punch out Time Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Punch Out Time Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ShiftCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Shift Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Shift Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.AttenStatusCheckboxAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Attendance Status Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Attendance Status Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldAttendancerpt(shift)) {

						logResults.createLogs("Y", "PASS", "Punch Out Inputted Successfully." + shift );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch Out Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ShiftRecordAttendancerpt(shift)) {

						logResults.createLogs("Y", "PASS", "Inputted Shift Records Displayed Successfully." + shift );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Shift Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.SearchTextFieldAttendancerpt(reportstatus)) {

						logResults.createLogs("Y", "PASS", "Punch Out Inputted Successfully." + reportstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Punch Out Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.AttendanceStatusAttendancerpt(reportstatus)) {

						logResults.createLogs("Y", "PASS", "Inputted Status Records Displayed Successfully." + reportstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Status Record." + meghpiattendancereport.getExceptionDesc());
					}

				}
				
				// MPI_957_Attendance_report_02
				@Test(enabled = true, priority = 18, groups = { "Smoke" })
				public void MPI_957_Attendance_report_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,subject,ccmail");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String subject = data.get(2);
					String ccmail = data.get(3) ;
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					
		        
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}
					

					
					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.MailReportButton()) {

						logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailReportButton." + meghpiattendancereport.getExceptionDesc());
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
				
				// MPI_959_Attendance_report_04
				@Test(enabled = true, priority = 19, groups = { "Smoke" })
				public void MPI_959_Attendance_report_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}	
				
				// MPI_967_Attendance_report_05
				@Test(enabled = true, priority = 20, groups = { "Smoke" })
				public void MPI_967_Attendance_report_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}
				
				// MPI_970_Attendance_report_06
				@Test(enabled = true, priority = 21, groups = { "Smoke" })
				public void MPI_970_Attendance_report_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
				
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}
	
				// MPI_971_Attendance_report_07
				@Test(enabled = true, priority = 22, groups = { "Smoke" })
				public void MPI_971_Attendance_report_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the filter feature functionality by selecting year and month");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}
				
				// MPI_974_Attendance_report_09
				@Test(enabled = true, priority = 23, groups = { "Smoke" })
				public void MPI_974_Attendance_report_09()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date and select punch in and out timeÂ ");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");	
			        String month14WorkingDate = dateDetails.get("month14WorkingDate");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FromDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month14WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month14WorkingDate);
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
					
					if (meghpiattendancereport.FirstRecordAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}

				// MPI_973_Attendance_report_08
				@Test(enabled = true, priority = 24, groups = { "Smoke" })
				public void MPI_973_Attendance_report_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, select role, grade, team columns from edit columns feature and ensure selected columns are displayed in the table");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,role");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String role = data.get(2);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.RoleCheckbox()) {

						logResults.createLogs("Y", "PASS", "Role Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Role CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DepartmentCheckbox()) {

						logResults.createLogs("Y", "PASS", "Dept Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DesignationCheckbox()) {

						logResults.createLogs("Y", "PASS", "Designation Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Designation CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.TeamCheckbox()) {

						logResults.createLogs("Y", "PASS", "Team Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateRoleColumn(EmpID, role)) {

						logResults.createLogs("Y", "PASS", "Role Column Data Displayed Successfully." + role);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Role Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateDesignationColumn(EmpID, designationname)) {

						logResults.createLogs("Y", "PASS", "Designation Column Data Displayed Successfully." + designationname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Designation Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateDepartmentColumn(EmpID, departmentname)) {

						logResults.createLogs("Y", "PASS", "Department Column Data Displayed Successfully." + departmentname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Department Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateTeamColumn(EmpID, teamname)) {

						logResults.createLogs("Y", "PASS", "Team Column Data Displayed Successfully." + teamname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Team Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					
				
				}		
				
				// MPI_983_Attendance_report_13
				@Test(enabled = true, priority = 25, groups = { "Smoke" })
				public void MPI_983_Attendance_report_13()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the working hours and effective hours is displaying accurately by taking break.");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,workinghours,effectivehours");

					
					String password = data.get(0);
					String captcha = data.get(1);
					
				
					String workinghours = data.get(2);
					String effectivehours = data.get(3);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
			        String month14WorkingDate = dateDetails.get("month14WorkingDate");

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month14WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month14WorkingDate);
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
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.WorkingHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "WorkingHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting WorkingHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EffectiveHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "EffectiveHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EffectiveHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateEffectiveHourColumn(EmpID, effectivehours)) {

						logResults.createLogs("Y", "PASS", "EffectiveHour Column Data Displayed Successfully." + effectivehours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  EffectiveHour Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateWorkingHourColumn(EmpID, workinghours)) {

						logResults.createLogs("Y", "PASS", "WorkingHour Column Data Displayed Successfully." + workinghours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  WorkingHour Column Data." + meghpiattendancereport.getExceptionDesc());
					}
			
				}		
				
				// MPI_984_Attendance_report_14
				@Test(enabled = true, priority = 26, groups = { "Smoke" })
				public void MPI_984_Attendance_report_14()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the early in and early out is displaying accurately by doing early in punch and early out punch.");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,earlyinhour,earlyouthour");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String earlyinhours = data.get(2);
					String earlyouthours = data.get(3);
					  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month14WorkingDate = dateDetails.get("month14WorkingDate");

						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month14WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month14WorkingDate);
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
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EarlyInHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "EarlyInHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EarlyInHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EarlyOutHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "EarlyOutHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting EarlyOutHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateEarlyInHourColumn(EmpID, earlyinhours)) {

						logResults.createLogs("Y", "PASS", "Early in hours Column Data Displayed Successfully." + earlyinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Early in hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateEarlyOutHourColumn(EmpID, earlyouthours)) {

						logResults.createLogs("Y", "PASS", "Early Out Hours Column Data Displayed Successfully." + earlyouthours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Early Out Hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
			
				}			
				
				// MPI_985_Attendance_report_15
				@Test(enabled = true, priority = 27, groups = { "Smoke" })
				public void MPI_985_Attendance_report_15()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the late in and late out is displaying accurately by doing late in punch and late out punch");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,lateinhour,lateouthour");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String lateinhours = data.get(2);
					String lateouthours = data.get(3);
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();

						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
						String month13WorkingDate  = dateDetails.get("month13WorkingDate");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month13WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month13WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month13WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month13WorkingDate);
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
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.LateInHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "LateInHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting LateInHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.LateOutHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "LateOutHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting LateOutHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateLateInHourrColumn(EmpID, lateinhours)) {

						logResults.createLogs("Y", "PASS", "Late in hours Column Data Displayed Successfully." + lateinhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Late in hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateLateOutHourColumn(EmpID, lateouthours)) {

						logResults.createLogs("Y", "PASS", "late Out Hours Column Data Displayed Successfully." + lateouthours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Late Out Hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
			
				}	
				
				
				// MPI_986_Attendance_report_16
				@Test(enabled = true, priority = 28, groups = { "Smoke" })
				public void MPI_986_Attendance_report_16()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, Take a break as a employee and ensure lunch in and lunch outÂ time is displayed as per recorded");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,lunchintime,lunchouttime");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String lunchintime = data.get(2);
					String lunchouttime = data.get(3);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
			        String month14WorkingDate = dateDetails.get("month14WorkingDate");

					String monthName = dateDetails.get("monthName");
					String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month14WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month14WorkingDate);
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
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.LunchInTimeCheckbox()) {

						logResults.createLogs("Y", "PASS", "LunchInTime Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting LunchInTime CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.LunchOutTimeCheckbox()) {

						logResults.createLogs("Y", "PASS", "LunchOutTime Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting LunchOutTime CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validatelunchintimeColumn(EmpID, lunchintime)) {

						logResults.createLogs("Y", "PASS", "Lunch Time in hours Column Data Displayed Successfully." + lunchintime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Lunch Time in hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validatelunchouttimeColumn(EmpID, lunchouttime)) {

						logResults.createLogs("Y", "PASS", "Lunch Time Out Hours Column Data Displayed Successfully." + lunchouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Lunch Time Out Hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
			
				}	
			
				// MPI_1005_Attendance_report_17
				@Test(enabled = true, priority = 29, groups = { "Smoke" })
				public void MPI_1005_Attendance_report_17()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check that the status is present and that overtime is displayed accurately.");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,reportstatus,totothour");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String status = data.get(2);
					String totothour = data.get(3);
					
					  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month14WorkingDate = dateDetails.get("month14WorkingDate");

						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
					
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month14WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month14WorkingDate);
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
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.OTHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "OTHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting OTHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.OverStayCheckBox()) {

						logResults.createLogs("Y", "PASS", "OverStay Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting OverStay CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateOverStayHourColumn(EmpID, totothour)) {

						logResults.createLogs("Y", "PASS", "OverStay Hours Column Data Displayed Successfully." + totothour);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Over Stay hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateOverTimeHourColumn(EmpID, totothour)) {

						logResults.createLogs("Y", "PASS", "OT Hours Column Data Displayed Successfully." + totothour);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  OT Hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateFinalStatusColumn(EmpID, status)) {

						logResults.createLogs("Y", "PASS", "Status Column Data Displayed Successfully." + status);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Status Column Data." + meghpiattendancereport.getExceptionDesc());
					}
			
				}
				
				// MPI_1006_Attendance_report_18
				@Test(enabled = true, priority = 30, groups = { "Smoke" })
				public void MPI_1006_Attendance_report_18()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check that the device name is displayed in the â€˜In Deviceâ€™ column where the employee punched in, and that the break hours are displayed accurately in break hour column.");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,breakhours");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String breakhours = data.get(2);
					  Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month14WorkingDate = dateDetails.get("month14WorkingDate");

						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
						
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month14WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month14WorkingDate);
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
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DeviceInCheckbox()) {

						logResults.createLogs("Y", "PASS", "DeviceIn Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting DeviceIn CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.BreakHourCheckbox()) {

						logResults.createLogs("Y", "PASS", "BreakHour Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting BreakHour CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateBreakHourColumn(EmpID, breakhours)) {

						logResults.createLogs("Y", "PASS", "Break Hours Column Data Displayed Successfully." + breakhours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Break hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateDeviceInColumn(EmpID, DeviceName)) {

						logResults.createLogs("Y", "PASS", "Device Name Column Data Displayed Successfully." + DeviceName);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Device Name Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					
				}	
				
				// MPI_1007_Attendance_report_19
				@Test(enabled = true, priority = 31, groups = { "Smoke" })
				public void MPI_1007_Attendance_report_19()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, apply a sick leave and ensure that the same leave type is displayed for the employee in the â€˜Leave Typeâ€™ column.");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,leavereason,leavename");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String LeaveReason = data.get(2);
					
					String leavename = data.get(3);
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month15WorkingDate = dateDetails.get("month15WorkingDate");

						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();

		MeghLeavePage meghleavepage = new MeghLeavePage(driver);
	
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
		
		if (meghleavepage.EmployeeInputField(EmpFirstName)) {

			logResults.createLogs("Y", "PASS", " Emp Name Inputted  Successfully ." + EmpFirstName);
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
		
		if (meghleavepage.FromDateOnAdminSelected(month15WorkingDate)) {

			logResults.createLogs("Y", "PASS", "  FromDate Inputted On TextField   Successfully ." + month15WorkingDate);
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
		
		if (meghleavepage.ToDateTextFieldInAdminSelected(month15WorkingDate)) {

			logResults.createLogs("Y", "PASS", "  ToDate Inputted On TextField   Successfully ." + month15WorkingDate);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month15WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month15WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month15WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month15WorkingDate);
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
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.LeaveTypeCheckbox()) {

						logResults.createLogs("Y", "PASS", "LeaveType Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting LeaveType CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateLeaveTypeColumn(EmpID, leavename)) {

						logResults.createLogs("Y", "PASS", "Break Hours Column Data Displayed Successfully." + leavename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Break hours Column Data." + meghpiattendancereport.getExceptionDesc());
					}	
				}		
				
				
				// MPI_1008_Attendance_report_20
				@Test(enabled = true, priority = 32, groups = { "Smoke" })
				public void MPI_1008_Attendance_report_20()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check that the assigned shift and its timings are displayed accurately.");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,shift,shiftintime,shiftouttime");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String shift = data.get(2);
					String shiftintime = data.get(3);
					String shiftouttime = data.get(4);
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month14WorkingDate = dateDetails.get("month14WorkingDate");

						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.AttendanceReportButton()) {

						logResults.createLogs("Y", "PASS", "Attendance Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendance Report Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonAttendancerpt()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month14WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month14WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month14WorkingDate);
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
					if (meghpiattendancereport.EditColumnButton()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ShiftInTimeCheckbox()) {

						logResults.createLogs("Y", "PASS", "ShiftInTime Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting ShiftInTime CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ShiftOutTimeCheckbox()) {

						logResults.createLogs("Y", "PASS", "ShiftOutTime Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting ShiftOutTime CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateShiftColumn(EmpID, shift)) {

						logResults.createLogs("Y", "PASS", "shift Column Data Displayed Successfully." + shift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  shift Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateShiftOutTimeColumn(EmpID, shiftouttime)) {

						logResults.createLogs("Y", "PASS", "shift Out time Column Data Displayed Successfully." + shiftouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  shift Out time Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateShiftInTimeColumn(EmpID, shiftintime)) {

						logResults.createLogs("Y", "PASS", "shift intime Column Data Displayed Successfully." + shiftintime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  shift intime Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					
				}			
				
				// MPI_989_missingpunch_report_01
				@Test(enabled = true, priority = 33, groups = { "Smoke" })
				public void MPI_989_missingpunch_report_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature for each search option");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,shift,reportstatus");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String shift = data.get(2);
					String reportstatus = data.get(3);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
			
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.ShiftCheckBoxMissingPunchRpt()) {

						logResults.createLogs("Y", "PASS", "Shift Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Shift Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.AttenFinalStatusCheckBoxMissingPunchrpt()) {

						logResults.createLogs("Y", "PASS", "Final Status Search Option Checkbox Selected Successfully:" );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Final Status Search Option Checkbox." + meghpiattendancereport.getExceptionDesc());
					}
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiattendancereport.MissingPunchSearchField(shift)) {

						logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully." + shift );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Shift Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ShiftRecordInMissingPunch(shift)) {

						logResults.createLogs("Y", "PASS", "Assigned Shift Record Displayed Successfully." + shift );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Assigned Shift Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MissingPunchSearchField(reportstatus)) {

						logResults.createLogs("Y", "PASS", "Status Name Inputted Successfully." + reportstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Status Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.AttendanceFinalStatusRecordInMissingPunch(reportstatus)) {

						logResults.createLogs("Y", "PASS", "Final Status Record Displayed Successfully." + reportstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Final Status Record." + meghpiattendancereport.getExceptionDesc());
					}
				}		
				
				// MPI_990_missingpunch_report_02
				@Test(enabled = true, priority = 34, groups = { "Smoke" })
				public void MPI_990_missingpunch_report_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality ofÂ Schedule Report by adding email and selecting frequency as \"daily\"Â  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,subject,ccmail");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String subject = data.get(2);
					String ccmail = data.get(3) ;

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					
		        
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.MailButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "MailReportButton Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On MailReportButton." + meghpiattendancereport.getExceptionDesc());
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
				
				// MPI_992_missingpunch_report_04
				@Test(enabled = true, priority = 35, groups = { "Smoke" })
				public void MPI_992_missingpunch_report_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting specific team");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,date");

					
					String password = data.get(0);
					String captcha = data.get(1);

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}	
				// MPI_993_missingpunch_report_05
				@Test(enabled = true, priority = 36, groups = { "Smoke" })
				public void MPI_993_missingpunch_report_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting assigned designation name of employee");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}	
				
				// MPI_994_missingpunch_report_06
				@Test(enabled = true, priority = 37, groups = { "Smoke" })
				public void MPI_994_missingpunch_report_06()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting employee type");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");
				
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}	
				
				// MPI_995_missingpunch_report_07
				@Test(enabled = true, priority = 38, groups = { "Smoke" })
				public void MPI_995_missingpunch_report_07()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the filter feature functionality by selecting year and month");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha");

					
					String password = data.get(0);
					String captcha = data.get(1);

Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
		    String monthName = dateDetails.get("monthName");
		    String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FirstRecordMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				
				}	
				
				// MPI_996_missingpunch_report_08
				@Test(enabled = true, priority = 39, groups = { "Smoke" })
				public void MPI_996_missingpunch_report_08()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date and select punch in and out timeÂ ");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,inouttime,secondinouttime");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String inouttime = data.get(2);
					String secondinouttime = data.get(3);
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
			        String month15WorkingDate = dateDetails.get("month15WorkingDate");

					String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
					String monthName = dateDetails.get("monthName");
					String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					if (meghpiattendancereport.FromDateSelected(month15WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month15WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month15WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month15WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FromTimeTextField()) {

						logResults.createLogs("Y", "PASS", "From Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On From Time TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromTimeSelected(inouttime)) {

						logResults.createLogs("Y", "PASS", "FromTime Selected Successfully." + inouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting From Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.ToTimeTextField()) {

						logResults.createLogs("Y", "PASS", "To Time TextField Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On To TextField." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.ToTimeSelected(secondinouttime)) {

						logResults.createLogs("Y", "PASS", "ToTime Selected Successfully." + secondinouttime);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Time." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EmpTypeDrpDownClick()) {

						logResults.createLogs("Y", "PASS", "Emp Type Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On  Emp Type." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FirstRecordMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
				
				// MPI_1029_missingpunch_report_12
				@Test(enabled = true, priority = 40, groups = { "Smoke" })
				public void MPI_1029_missingpunch_report_12()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("To verify this, perform only a punch-out and ensure that the â€˜Punch In Missingâ€™ record is displayed.");

					 
					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,shiftouttime");

				  int i = 0;
			
					String password = data.get(i++);
					String captcha = data.get(i++);
					String baseuri =  data.get(i++);
					String loginendpoint = data.get(i++);
					String endpointoftransaction = data.get(i++);
					String cardnumber = data.get(i++);
					int cardtype = Integer.parseInt(data.get(i++));
					
					String bio1finger = data.get(i++);
					String bio2finger  = data.get(i++);
					
					String locationid = data.get(i++);
							String inouttime1 = data.get(i++);
							String mode = data.get(i++);
							String photo = data.get(i++);
				

							 String statusEndpoint    = data.get(i++);
						        String fromDateofuserstatus1          = data.get(i++);
						        String toDateofuserstatus2            = data.get(i++);
						        String expectedStatus    = data.get(i++);
						        String description = data.get(i++);
						        String updateattendanceendpoint = data.get(i++);
						        String shiftouttime = data.get(i++);
						        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

						        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
						        String month16WorkingDate = dateDetails.get("month16WorkingDate");

								String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
								String monthName = dateDetails.get("monthName");
								String year = dateDetails.get("year");
						        
						        
						        
						        String fromDateofstatus = month16WorkingDate + fromDateofuserstatus1;
						        String toDateofstatus = month16WorkingDate + toDateofuserstatus2; 
						        
							 
										String inouttime = month16WorkingDate + " " + inouttime1;

				
										
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
										MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
									
							        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
								MeghLoginTest meghlogintest = new MeghLoginTest();
							
					 // Create API Page object
			MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

			// Call combined method
			Response punchInResponse = apiPage.executeSuccessTransaction(
			        baseuri, loginendpoint,
			        Emailid, password, cmpcode,
			        baseuri, endpointoftransaction,
			        cardnumber, cardtype, deviceuniqueid,
			        bio1finger, bio2finger, EmpID,
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
	                Emailid, password, cmpcode,
	                baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
	                EmpID, firstDayOfMonth + "T00:00:00.000Z"
	        );

	        if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
	            logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + EmpID);
	        } else {
	            logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
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

	            // Make sentence using excel inputs
	            String finalSentence = String.format(
	                "%s â€“ This Employee %s on %s, punched Out at %s. Final Status = %s (Expected = %s)",
	                description, EmpID, month16WorkingDate, inouttime1, actualStatus, expectedStatus
	            );

	            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
	                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
	            } else {
	                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
	            }
	        } else {
	            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
	        }
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

			
			if (RolePermissionpage.AttendanceReport()) {

				logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
			}

			if (meghpiattendancereport.MissingPunchTab()) {

				logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
			if (meghpiattendancereport.FilterButtonMissingPunch()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
			
		

			if (meghpiattendancereport.FromDateDropDown()) {

				logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.FromDateSelected(month16WorkingDate)) {

				logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month16WorkingDate);
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
			
			if (meghpiattendancereport.ToDateSelected(month16WorkingDate)) {

				logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month16WorkingDate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
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
			if (meghpiattendancereport.MissingPunchSearchField(EmpFirstName)) {

				logResults.createLogs("Y", "PASS", "Emp  Name Inputted Successfully." + EmpFirstName );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Emp Name." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.validateOutTimeRowInMissingPunchReport(EmpID, shiftouttime)) {

				logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
			}
		
				}			
				// MPI_1030_missingpunch_report_13
				@Test(enabled = true, priority = 41, groups = { "Smoke" })
				public void MPI_1030_missingpunch_report_13()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName("To verify this, perform only a punch-in and ensure that the â€˜Punch Out Missingâ€™ record is displayed.");

					 
					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,getuserstatusendpoint,fromdateofstatus,todateofstatus,status,description,updateattendanceendpoint,shiftouttime");

				  int i = 0;
			
					String password = data.get(i++);
					String captcha = data.get(i++);
					String baseuri =  data.get(i++);
					String loginendpoint = data.get(i++);
					String endpointoftransaction = data.get(i++);
					String cardnumber = data.get(i++);
					int cardtype = Integer.parseInt(data.get(i++));
					
					String bio1finger = data.get(i++);
					String bio2finger  = data.get(i++);
					
					String locationid = data.get(i++);
							String inouttime1 = data.get(i++);
							String mode = data.get(i++);
							String photo = data.get(i++);
							 String statusEndpoint    = data.get(i++);
						        String fromDateofuserstatus1          = data.get(i++);
						        String toDateofuserstatus2            = data.get(i++);
						        String expectedStatus    = data.get(i++);
						        String description = data.get(i++);
						        String updateattendanceendpoint = data.get(i++);
						        String shiftouttime = data.get(i++);
						        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

						        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
						        String month17WorkingDate = dateDetails.get("month17WorkingDate");

								String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
								String monthName = dateDetails.get("monthName");
								String year = dateDetails.get("year");
						        
						        
						        String fromDateofstatus = month17WorkingDate + fromDateofuserstatus1;
						        String toDateofstatus = month17WorkingDate + toDateofuserstatus2; 
							 
										String inouttime = month17WorkingDate + " " + inouttime1;
										
								
										MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
										MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
									
							        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
								MeghLoginTest meghlogintest = new MeghLoginTest();
							
					 // Create API Page object
			MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

			// Call combined method
			Response punchInResponse = apiPage.executeSuccessTransaction(
			        baseuri, loginendpoint,
			        Emailid, password, cmpcode,
			        baseuri, endpointoftransaction,
			        cardnumber, cardtype, deviceuniqueid,
			        bio1finger, bio2finger, EmpID,
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
	                Emailid, password, cmpcode,
	                baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
	                EmpID, firstDayOfMonth + "T00:00:00.000Z"
	        );

	        if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
	            logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + EmpID);
	        } else {
	            logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
	        }
	      //  Thread.sleep(6000);
			// Get User Status
	        Response validation = apiPage.executeGetUserStatus(
	                baseuri, loginendpoint,
	                Emailid, password, cmpcode,
	                baseuri, statusEndpoint,
	                EmpID, fromDateofstatus, toDateofstatus
	        );

	        if (validation.statusCode() == 200) {
	            String actualStatus = validation.jsonPath().getString("[0].AttnFinalStatus");

	            // Make sentence using excel inputs
	            String finalSentence = String.format(
	                "%s â€“ This Employee %s on %s, punched Out at %s. Final Status = %s (Expected = %s)",
	                description, EmpID, month17WorkingDate, inouttime1, actualStatus, expectedStatus
	            );

	            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
	                logResults.createLogs("N", "PASS", "âœ… " + finalSentence);
	            } else {
	                logResults.createLogs("N", "FAIL", "âŒ " + finalSentence);
	            }
	        } else {
	            logResults.createLogs("N", "FAIL", "âŒ Failed to fetch final status. API Response." + validation.asString());
	        }
	       
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

			
			if (RolePermissionpage.AttendanceReport()) {

				logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
			}

			if (meghpiattendancereport.MissingPunchTab()) {

				logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
			if (meghpiattendancereport.FilterButtonMissingPunch()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
			
		

			if (meghpiattendancereport.FromDateDropDown()) {

				logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.FromDateSelected(month17WorkingDate)) {

				logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month17WorkingDate);
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
			
			if (meghpiattendancereport.ToDateSelected(month17WorkingDate)) {

				logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month17WorkingDate);
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
			if (meghpiattendancereport.MissingPunchSearchField(EmpFirstName)) {

				logResults.createLogs("Y", "PASS", "Emp  Name Inputted Successfully." + EmpFirstName );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Emp Name." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (meghpiattendancereport.validateOutTimeRowInMissingPunchReport(EmpID, shiftouttime)) {

				logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
			}
		
				}		
				
				// MPI_1031_missingpunch_report_14
				@Test(enabled = true, priority = 42, groups = { "Smoke" })
				public void MPI_1031_missingpunch_report_14()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, punch in at the shift start, punch out at the break start time, and then perform the last punch-in at the end of the shift. Ensure no record displayed for that date.");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,thirdinouttime,thirdmode,updateattendanceendpoint");


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

					    String thirdinouttime3 = data.get(i++); // Punch Out time
					    String thirdmode = data.get(i++);    
				
				        String updateattendanceendpoint = data.get(i++);
				       
				        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
				        Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month18WorkingDate = dateDetails.get("month18WorkingDate");

						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
				      
					   
					    
						String inouttime = month18WorkingDate + " " + inouttime1;
						String secondInOutTime = month18WorkingDate + " " + secondInOutTime2;
						
						String thirdinouttime =  month18WorkingDate + " " + thirdinouttime3;
					
						
						MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
						MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					
			        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
				MeghLoginTest meghlogintest = new MeghLoginTest();
					
					
					
					 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();

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
				    
				    // Punch in 2nd time
				    Response SecondTimepunchOutResponse = apiPage.executeSuccessTransaction(
				            baseuri, loginendpoint,
				            Emailid, password, cmpcode,
				            baseuri, endpointoftransaction,
				            cardnumber, cardtype, deviceuniqueid,
				            bio1finger, bio2finger, EmpID,
				            locationid, thirdinouttime, thirdmode, photo
				    );

				    if (SecondTimepunchOutResponse.getStatusCode() == 200) {
			            logResults.createLogs("N", "PASS", "2nd Punch In executed successfully at " + thirdinouttime);
			        } else {
			            logResults.createLogs("N", "FAIL", "Punch In failed." + SecondTimepunchOutResponse.asString());
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
				

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month18WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month18WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month18WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month18WorkingDate);
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
					if (meghpiattendancereport.MissingPunchSearchField(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Emp  Name Inputted Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.NoRecordFoundMsg()) {

						logResults.createLogs("Y", "PASS", "NoRecordFoundMsg Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  NoRecordFoundMsg." + meghpiattendancereport.getExceptionDesc());
					}
		
				}			
				
				// MPI_1034_missingpunch_report_17
				@Test(enabled = true, priority = 43, groups = { "Smoke" })
				public void MPI_1034_missingpunch_report_17()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the status when the employee is absent");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,reportstatus");

					
					String password = data.get(0);
					String captcha = data.get(1);
					
					String reportstatus = data.get(2);
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month19WorkingDate = dateDetails.get("month19WorkingDate");

						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month19WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month19WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month19WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month19WorkingDate);
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
					if (meghpiattendancereport.MissingPunchSearchField(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Emp  Name Inputted Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.validateFinalStatusRowInMissingPunchReport(EmpID, reportstatus)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + reportstatus);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
				
				
				// MPI_1036_missingpunch_report_18
				@Test(enabled = true, priority = 44, groups = { "Smoke" })
				public void MPI_1036_missingpunch_report_18()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check that the attendance date, shift, and device in dataÂ areÂ displayed when the employee has not done an Out PunchÂ ");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,shift");

					
					String password = data.get(0);
					String captcha = data.get(1);
					
				
					String shift = data.get(2);
					
					 Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				        String month17WorkingDate = dateDetails.get("month17WorkingDate");

						String firstDayOfMonth = dateDetails.get("firstDayOfMonth");
						String monthName = dateDetails.get("monthName");
						String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					
					

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(month17WorkingDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + month17WorkingDate);
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
					
					if (meghpiattendancereport.ToDateSelected(month17WorkingDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + month17WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting To Date." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EmpTypeDrpDownClick()) {

						logResults.createLogs("Y", "PASS", "Emp Type Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On  Emp Type." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.EditColumnButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DeviceInCheckbox()) {

						logResults.createLogs("Y", "PASS", "DeviceIn Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting DeviceIn CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MissingPunchSearchField(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Emp  Name Inputted Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateShiftRowInMissingPunchReport(EmpID, shift)) {

						logResults.createLogs("Y", "PASS", "Shift Column Data Displayed Successfully." + shift);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Shift Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateDeviceInRowInMissingPunchReport(EmpID, DeviceName)) {

						logResults.createLogs("Y", "PASS", "Device Name Column Data Displayed Successfully." + DeviceName);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Device Name Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.validateAttnDateRowInMissingPunchReport(EmpID, month17WorkingDate)) {

						logResults.createLogs("Y", "PASS", "Date Column Data Displayed Successfully." + month17WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Date Column Data." + meghpiattendancereport.getExceptionDesc());
					}
				}	
				
				// MPI_1037_missingpunch_report_19
				@Test(enabled = true, priority = 45, groups = { "Smoke" })
				public void MPI_1037_missingpunch_report_19()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check that the assigned Department, Role, Designation, and Team Name are displayed in the table after the respective columns are selected.");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,role");

					
					String password = data.get(0);
					String captcha = data.get(1);
				
					String role = data.get(2);
					
					Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
				    String monthName = dateDetails.get("monthName");
				    String year = dateDetails.get("year");
		
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.EditColumnButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.RoleCheckbox()) {

						logResults.createLogs("Y", "PASS", "Role Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Role CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DepartmentCheckbox()) {

						logResults.createLogs("Y", "PASS", "Dept Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Dept CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.DesignationCheckbox()) {

						logResults.createLogs("Y", "PASS", "Designation Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Designation CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.TeamCheckbox()) {

						logResults.createLogs("Y", "PASS", "Team Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MissingPunchSearchField(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Emp  Name Inputted Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateRoleRowInMissingPunchReport(EmpID, role)) {

						logResults.createLogs("Y", "PASS", "Role Column Data Displayed Successfully." + role);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Role Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateDesignationRowInMissingPunchReport(EmpID, designationname)) {

						logResults.createLogs("Y", "PASS", "Designation Column Data Displayed Successfully." + designationname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Designation Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateDepartmentRowInMissingPunchReport(EmpID, departmentname)) {

						logResults.createLogs("Y", "PASS", "Department Column Data Displayed Successfully." + departmentname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Department Column Data." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.validateTeamRowInMissingPunchReport(EmpID, teamname)) {

						logResults.createLogs("Y", "PASS", "Team Column Data Displayed Successfully." + teamname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Team Column Data." + meghpiattendancereport.getExceptionDesc());
					}
				}	
				
				// MPI_1038_missingpunch_report_20
				@Test(enabled = true, priority = 46, groups = { "Smoke" })
				public void MPI_1038_missingpunch_report_20()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, do punch in from web and ensure \"in punch from\" displaying as web");

					ArrayList<String> data = initBase.loadExcelData("Attendance_Reports", currTC,
							"password,captcha,inpunchfrom,baseuri,loginendpoint,updateattendanceendpoint");

					
					String password = data.get(0);
					String captcha = data.get(1);
		
					String inpunchfrom = data.get(2);
					String baseuri = data.get(3);
					String loginendpoint = data.get(4);
				String updateattendanceendpoint = data.get(5);
				Map<String, String> dateInfo = Pramod.getCurrentDateDetails();

				String currentDate = dateInfo.get("currentDate");
				String monthName = dateInfo.get("monthName");
				String year = dateInfo.get("year");
				
				Map<String, String> dateDetails = Pramod.getLastMonthWorkingDatesDetails();
			String firstdayofMonth = dateDetails.get("firstDayOfMonth");
				
				
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);
				
		        	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
	
			
			if (meghlogintest.verifyCompanyLogin(cmpcode, EmpID, EmpID, captcha, logResults, driver)) {
				logResults.createLogs("Y", "PASS", "Login Done Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Login Is Failed." + meghloginpage.getExceptionDesc());
			}

			
			if (meghpiattendancereport.CheckInButton()) {

				logResults.createLogs("Y", "PASS", "CheckIn Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CheckIn Button." + meghpiattendancereport.getExceptionDesc());
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
			
			
			 // Trigger attendance update first
		    Response updateResp = apiPage.executeUpdateAttendance(
		            baseuri, loginendpoint,
		            Emailid, password, cmpcode,
		            baseuri, updateattendanceendpoint,  // <-- add endpoint in excel
		            EmpID, firstdayofMonth + "T00:00:00.000Z"
		    );

		    if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
		        logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + EmpID);
		    } else {
		        logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
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

					
					if (RolePermissionpage.AttendanceReport()) {

						logResults.createLogs("Y", "PASS", "AttendanceReport Displayed Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Attendance Report." + RolePermissionpage.getExceptionDesc());
					}

					if (meghpiattendancereport.MissingPunchTab()) {

						logResults.createLogs("Y", "PASS", "MissingPunchTab Displayed Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying MissingPunchTab." + meghpiattendancereport.getExceptionDesc());
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
					if (meghpiattendancereport.FilterButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiattendancereport.getExceptionDesc());
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

					if (meghpiattendancereport.FromDateDropDown()) {

						logResults.createLogs("Y", "PASS", "FromDate DropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On FromDate DropDown." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiattendancereport.FromDateSelected(currentDate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + currentDate);
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
					
					if (meghpiattendancereport.ToDateSelected(currentDate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + currentDate);
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
					if (meghpiattendancereport.EditColumnButtonMissingPunch()) {

						logResults.createLogs("Y", "PASS", "Edit Column Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Edit Column Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.InPunchFromCheckBox()) {

						logResults.createLogs("Y", "PASS", "InPunchFrom Checkbox Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting InPunchFrom CheckBox." + meghpiattendancereport.getExceptionDesc());
					}
				
					if (meghpiattendancereport.SaveChangesButton()) {

						logResults.createLogs("Y", "PASS", "Save Changes Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Save Changes Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.MissingPunchSearchField(EmpFirstName)) {

						logResults.createLogs("Y", "PASS", "Emp  Name Inputted Successfully." + EmpFirstName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name." + meghpiattendancereport.getExceptionDesc());
					}

					if (meghpiattendancereport.validateInPunchFromRowInMissingPunchReport(EmpID, inpunchfrom)) {

						logResults.createLogs("Y", "PASS", "In Punch From Column Data Displayed Successfully." + inpunchfrom);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  In Punch From Column Data." + meghpiattendancereport.getExceptionDesc());
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

