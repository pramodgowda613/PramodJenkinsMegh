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
import com.MeghPI.Attendance.pages.MeghPIRegularizationReportsPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationRequestPage;
import com.MeghPI.Attendance.pages.MeghPITimeLogReportsPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;
public class MeghPIEmpRegularizationRequestReportTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	private String cmpcode = "";
	private String Emailid = "";
	private String officename = "";
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
			entityname = "AutoEN" + initBase.executionRunTime;
			officename = "AutoON" + initBase.executionRunTime;
			AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");

		}
	
	// MPI_1779_Emp_Regularization_Reports_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_1779_Emp_Regularization_Reports_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of search feature using each search option.");

		  // Load Excel data
		ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
				"password,baseuri,loginendpoint,photo,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,captcha,regularizationstatus,regularizationintime,regularizationouttime,regularizationreason");


		  int i = 0;
		String password = data.get(i++);
		
		  String baseuri = data.get(i++);
		    String loginendpoint = data.get(i++);
		    
		    String photo = data.get(i++);
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
	        
	        String regularizationstatus = data.get(i++);
			String regularizationintime =data.get(i++);
			String regularizationouttime = data.get(i++);
			String regularizationreason = data.get(i++);
			
	        
	       
	     
	        String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

	        Map<String, String> dateInfo = Pramod.getLastMonthWorkingDatesDetails();
			String firstDayOfMonth = dateInfo.get("firstDayOfMonth");
			
			  Map<String, String> details = Pramod.getPrevious12WorkingDates();

		        String Regulizationdate = details.get("month1WorkingDate");
		        String formattedPrevDate = Pramod.convertToUIFormat(Regulizationdate);
		        String monthName = details.get("monthName");
		        String year = details.get("year");
			
			MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
            MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
            MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
        	MeghLoginTest meghlogintest = new MeghLoginTest();

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
			    try {
					Thread.sleep(25000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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



			    if (RolePermissionpage.EmpRegulationRequest()) {

			    	logResults.createLogs("Y", "PASS", "Employee Can Access His EmpRegulationRequest Successfully.");
			    } else {
			    	logResults.createLogs("Y", "FAIL",
			    			"Error While Displaying EmpRegulationRequest Tab." + RolePermissionpage.getExceptionDesc());
			    }

			    if (meghpiregularizationReportsPage.ApplyRegularizationButtonEmp()) {

			    	logResults.createLogs("Y", "PASS", "Apply Regularization Button Clicked Successfully.");
			    } else {
			    	logResults.createLogs("Y", "FAIL",
			    			"Error While Clicking On Apply Regularization Button." + meghpiregularizationReportsPage.getExceptionDesc());
			    }
			    if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

			    	logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
			    } else {
			    	logResults.createLogs("Y", "FAIL",
			    			"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
			    }
			    if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(Regulizationdate)) {

			    	logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + Regulizationdate);
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
			    if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(Regulizationdate)) {

			    	logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + Regulizationdate);
			    } else {
			    	logResults.createLogs("Y", "FAIL",
			    			"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
			    }
			    if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(Regulizationdate)) {

			    	logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + Regulizationdate);
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

			    		if (RolePermissionpage.ReprtButton()) {

			    			logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
			    		} else {
			    			logResults.createLogs("Y", "FAIL",
			    					"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
			    		}

			    		
			    		if (meghpiregularizationReportsPage.RegularizationReportButton()) {

			    			logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
			    		} else {
			    			logResults.createLogs("Y", "FAIL",
			    					"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
			    		}

			    		if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

			    			logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
			    		} else {
			    			logResults.createLogs("Y", "FAIL",
			    					"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
			    		}
			    		
			    		if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

			    			logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			    		} else {
			    			logResults.createLogs("Y", "FAIL",
			    					"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
			    	
			    		
			    		
			    		if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(formattedPrevDate)) {

			    			logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + formattedPrevDate );
			    		} else {
			    			logResults.createLogs("Y", "FAIL",
			    					"Error While Inputting  Regularization Date." + meghpiregularizationReportsPage.getExceptionDesc());
			    		}
			    		if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResultOnEmp(Regulizationdate)) {

			    			logResults.createLogs("Y", "PASS", "Regularization Date Record Displayed Successfully." + Regulizationdate );
			    		} else {
			    			logResults.createLogs("Y", "FAIL",
			    					"Error While Displaying Inputted Regularization Date Record." + meghpiattendancereport.getExceptionDesc());
			    		}
			    		
			    		if (OfficePage.SearchDropDown()) {
			    			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			    		} else {
			    			logResults.createLogs("Y", "FAIL",
			    					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			    		}
			    		
			    		
			    		if (meghpiregularizationReportsPage.RegularizationAllReportStatusCheckBox()) {

			    			logResults.createLogs("Y", "PASS", "Status Search Option Checkbox Selected Successfully." );
			    		} else {
			    			logResults.createLogs("Y", "FAIL",
			    					"Error While Selecting Status Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
			    		}
			    		
			    		if (OfficePage.SearchDropDown()) {
			    			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			    		} else {
			    			logResults.createLogs("Y", "FAIL",
			    					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			    		}
			    		if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(regularizationstatus)) {

			    			logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + regularizationstatus );
			    		} else {
			    			logResults.createLogs("Y", "FAIL",
			    					"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
			    		}
			    		
			    		if (meghpiregularizationReportsPage.RegularizationAllReportStatusSearchResultOnEmp(regularizationstatus)) {

			    			logResults.createLogs("Y", "PASS", "Inputted Status Displayed Successfully." + regularizationstatus );
			    		} else {
			    			logResults.createLogs("Y", "FAIL",
			    					"Error While Displaying Inputted Status Record." + meghpiregularizationReportsPage.getExceptionDesc());
			    		}
			    	}
	
	
	// MPI_1780_Emp_Regularization_Reports_02
		@Test(enabled = true, priority = 2, groups = { "Smoke" })
		public void MPI_1780_Emp_Regularization_Reports_02()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

			ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
					"empid,captcha,subject,ccmail");

			
			String empid = data.get(0);
			String captcha = data.get(1);
			String subject = data.get(2);
			String ccmail = data.get(3) ;

			 Map<String, String> details = Pramod.getPrevious12WorkingDates();
		        String monthName = details.get("monthName");
		        String year = details.get("year");

			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			
	    
	    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	MeghLoginTest meghlogintest = new MeghLoginTest();
	MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			
			if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

			if (meghpiregularizationReportsPage.RegularizationReportButton()) {

				logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

				logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			
			if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
	
	
		// MPI_1782_Emp_Regularization_Reports_04
		@Test(enabled = true, priority = 3, groups = { "Smoke" })
		public void MPI_1782_Emp_Regularization_Reports_04()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

			ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
					"empid,captcha");

			
			String empid = data.get(0);
			String captcha = data.get(1);
			 Map<String, String> details = Pramod.getPrevious12WorkingDates();

		        String Regulizationdate = details.get("month1WorkingDate");
		        String monthName = details.get("monthName");
		        String year = details.get("year");
			
                   String uidate = Pramod.convertToUIFormat(Regulizationdate);
			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
	    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	MeghLoginTest meghlogintest = new MeghLoginTest();

			
			if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

			
			if (meghpiregularizationReportsPage.RegularizationReportButton()) {

				logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

				logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}
		
			if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
			
			if (meghpiattendancereport.FromDateSelected(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + Regulizationdate);
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
			
			if (meghpiattendancereport.ToDateSelected(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + Regulizationdate);
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
			
			if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResultOnEmp(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + Regulizationdate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
			}
		}
	
		
		// MPI_1783_Emp_Regularization_Reports_05
		@Test(enabled = true, priority = 4, groups = { "Smoke" })
		public void MPI_1783_Emp_Regularization_Reports_05()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, raise a regularization request during the last week. In the Regularization Request Reports module, apply the filter for the last week and ensure that the corresponding regularization request record is displayed.");

			ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
					"empid,captcha,regularizationintime,regularizationouttime,regularizationreason");

			
			String empid = data.get(0);
			String captcha = data.get(1);
			String regularizationintime = data.get(2);
			String regularizationouttime = data.get(3);
			String regularizationreason = data.get(4);
		
			  Map<String, String> details = Pramod.getPrevious12WorkingDates();

		        String Regulizationdate = details.get("month7WorkingDate");
		        String formattedPrevDate = Pramod.convertToUIFormat(Regulizationdate);
		        String monthName = details.get("monthName");
		        String year = details.get("year");
			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		
	    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	    	MeghLoginTest meghlogintest = new MeghLoginTest();

	MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);

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



	if (RolePermissionpage.EmpRegulationRequest()) {

		logResults.createLogs("Y", "PASS", "Employee Can Access His EmpRegulationRequest Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Displaying EmpRegulationRequest Tab." + RolePermissionpage.getExceptionDesc());
	}

	if (meghpiregularizationReportsPage.ApplyRegularizationButtonEmp()) {

		logResults.createLogs("Y", "PASS", "Apply Regularization Button Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On Apply Regularization Button." + meghpiregularizationReportsPage.getExceptionDesc());
	}
	if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

		logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
	}
	if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(Regulizationdate)) {

		logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + Regulizationdate);
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
	if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(Regulizationdate)) {

		logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + Regulizationdate);
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
	}
	if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(Regulizationdate)) {

		logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + Regulizationdate);
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
	if (RolePermissionpage.ReprtButton()) {

		logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
	}

	
	if (meghpiregularizationReportsPage.RegularizationReportButton()) {

		logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
	}

	if (meghpiregularizationReportsPage.RegularizationAllReportButton()) {

		logResults.createLogs("Y", "PASS", "All Report Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On All Report." + meghpiregularizationReportsPage.getExceptionDesc());
	}

	if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

		logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
	
	if (meghpiattendancereport.getPrevious12WorkingDateWeekNumber("month7WorkingDate")) {

	    // Step-2: Convert saved static week number to string
	    String weekcount = Integer.toString(MeghPIAttendanceReportsPage.previous12WorkingDateWeekNumber);

	    // Step-3: Select week in dropdown
	    if (meghpiattendancereport.WeeklyDropDownSelected(weekcount)) {
	        logResults.createLogs("Y", "PASS", "Week selected successfully: " + weekcount);
	    } else {
	        logResults.createLogs("Y", "FAIL",
	                "Error while selecting week. " + meghpiattendancereport.getExceptionDesc());
	    }

	} else {
	    logResults.createLogs("Y", "FAIL",
	            "Failed to calculate week number. " + meghpiattendancereport.getExceptionDesc());
	}
	
	if (meghpiattendancereport.FilterSaveButton()) {

		logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
	}
	
	if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResultOnEmp(Regulizationdate)) {

		logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + formattedPrevDate);
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
	}
		}
		
		
		// MPI_1784_Emp_PendingRegularization_Reports_01
		@Test(enabled = true, priority = 5, groups = { "Smoke" })
		public void MPI_1784_Emp_PendingRegularization_Reports_01()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of search feature using each search option");

			ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
					"empid,captcha,regularizationstatus");

			
			String empid = data.get(0);
			String captcha = data.get(1);
			String regularizationstatus = data.get(2);
			
		
			Map<String, String> details = Pramod.getPrevious12WorkingDates();

	        String Regulizationdate = details.get("month1WorkingDate");
	        String formattedPrevDate = Pramod.convertToUIFormat(Regulizationdate);
	        String monthName = details.get("monthName");
	        String year = details.get("year");
			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		
	    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
	MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
	MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
	MeghLoginTest meghlogintest = new MeghLoginTest();

	if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

			
			if (meghpiregularizationReportsPage.RegularizationReportButton()) {

				logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.RegulirizationPendingTab()) {

				logResults.createLogs("Y", "PASS", "Pending Report Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Pending Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			
			if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
		
			if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(formattedPrevDate)) {

				logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + formattedPrevDate );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Regularization Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResultOnEmp(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "Regularization Date Record Displayed Successfully." + Regulizationdate );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying Inputted Regularization Date Record." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			
			
			if (meghpiregularizationReportsPage.RegularizationAllReportStatusCheckBox()) {

				logResults.createLogs("Y", "PASS", "Status Search Option Checkbox Selected Successfully." );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Status Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(regularizationstatus)) {

				logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + regularizationstatus );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			
			if (meghpiregularizationReportsPage.RegularizationAllReportStatusSearchResultOnEmp(regularizationstatus)) {

				logResults.createLogs("Y", "PASS", "Inputted Status Displayed Successfully." + regularizationstatus );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying Inputted Status Record." + meghpiregularizationReportsPage.getExceptionDesc());
			}
		}		
		
		
		// MPI_1785_Emp_PendingRegularization_Reports_02
		@Test(enabled = true, priority = 6, groups = { "Smoke" })
		public void MPI_1785_Emp_PendingRegularization_Reports_02()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

			ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
					"empid,captcha,subject,ccmail");

			
			String empid = data.get(0);
			String captcha = data.get(1);
			String subject = data.get(2);
			String ccmail = data.get(3) ;
			Map<String, String> details = Pramod.getPrevious12WorkingDates();

	        String monthName = details.get("monthName");
	        String year = details.get("year");

			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			
	    
	    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	MeghLoginTest meghlogintest = new MeghLoginTest();
	MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			
			if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

			
			if (meghpiregularizationReportsPage.RegularizationReportButton()) {

				logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.RegulirizationPendingTab()) {

				logResults.createLogs("Y", "PASS", "Pending Report Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Pending Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			
			if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
		
		
		// MPI_1787_Emp_PendingRegularization_Reports_04
		@Test(enabled = true, priority = 7, groups = { "Smoke" })
		public void MPI_1787_Emp_PendingRegularization_Reports_04()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

			ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
					"empid,captcha");

			
			String empid = data.get(0);
			String captcha = data.get(1);
			Map<String, String> details = Pramod.getPrevious12WorkingDates();

	        String Regulizationdate = details.get("month1WorkingDate");
	      String uidate = Pramod.convertToUIFormat(Regulizationdate);
	        String monthName = details.get("monthName");
	        String year = details.get("year");		

			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
	    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
	MeghLoginTest meghlogintest = new MeghLoginTest();

			
			if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

			
			if (meghpiregularizationReportsPage.RegularizationReportButton()) {

				logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.RegulirizationPendingTab()) {

				logResults.createLogs("Y", "PASS", "Pending Report Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Pending Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			
			if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
			
			if (meghpiattendancereport.FromDateSelected(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + Regulizationdate);
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
			
			if (meghpiattendancereport.ToDateSelected(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + Regulizationdate);
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
			if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResultOnEmp(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + Regulizationdate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
			}
			
			
		}			
		
		// MPI_1788_Emp_PendingRegularization_Reports_05
				@Test(enabled = true, priority = 8, groups = { "Smoke" })
				public void MPI_1788_Emp_PendingRegularization_Reports_05()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise a regularization request during the last week. In the Regularization Request Reports module, apply the filter for the last week and ensure that the corresponding regularization request record is displayed.");

					ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
							"empid,captcha");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					 Map<String, String> details = Pramod.getPrevious12WorkingDates();

				        String Regulizationdate = details.get("month7WorkingDate");
				        String formattedPrevDate = Pramod.convertToUIFormat(Regulizationdate);
				        String monthName = details.get("monthName");
				        String year = details.get("year");

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationPendingTab()) {

						logResults.createLogs("Y", "PASS", "Pending Report Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
					if (meghpiattendancereport.getPrevious12WorkingDateWeekNumber("month7WorkingDate")) {

					    // Step-2: Convert saved static week number to string
					    String weekcount = Integer.toString(MeghPIAttendanceReportsPage.previous12WorkingDateWeekNumber);

					    // Step-3: Select week in dropdown
					    if (meghpiattendancereport.WeeklyDropDownSelected(weekcount)) {
					        logResults.createLogs("Y", "PASS", "Week selected successfully: " + weekcount);
					    } else {
					        logResults.createLogs("Y", "FAIL",
					                "Error while selecting week. " + meghpiattendancereport.getExceptionDesc());
					    }

					} else {
					    logResults.createLogs("Y", "FAIL",
					            "Failed to calculate week number. " + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResultOnEmp(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + formattedPrevDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
					
					
				}			
				
		
				// MPI_1790_Emp_ApprovedRegularization_Reports_01
				@Test(enabled = true, priority = 9, groups = { "Smoke" })
				public void MPI_1790_Emp_ApprovedRegularization_Reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature using each search option");

					ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
							"password,captcha,regularizationstatus,firstname,empid");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String regularizationstatus = data.get(2);
					String firstname = data.get(3);
					String empid = data.get(4);
					
					 Map<String, String> details = Pramod.getPrevious12WorkingDates();

				        String Regulizationdate = details.get("month1WorkingDate");
				        String formattedPrevDate = Pramod.convertToUIFormat(Regulizationdate);
				        String monthName = details.get("monthName");
				        String year = details.get("year");
					
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
            MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
			MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
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
					if (meghpiregularizationReportsPage.RegularizationAllReportMonthFilterButton(monthName)) {

						logResults.createLogs("Y", "PASS", "Regularization Month Data Displayed Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Regularization Month Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Regularization Emp Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.AdminApprovedToRegulirization(formattedPrevDate,firstname)) {

						logResults.createLogs("Y", "PASS", "Admin Approved To Regulirization Request Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Approving Regularization Request." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.ApproveButton()) {

						logResults.createLogs("Y", "PASS", " Approved Button Clicked Successfully."  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approve Button." + meghpiattendancereport.getExceptionDesc());
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
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationApprovedTab()) {

						logResults.createLogs("Y", "PASS", "Approved Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
				
					
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(formattedPrevDate)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + formattedPrevDate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Regularization Date." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResultOnEmp(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "Regularization Date Record Displayed Successfully." + Regulizationdate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Regularization Date Record." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportStatusCheckBox()) {

						logResults.createLogs("Y", "PASS", "Status Search Option Checkbox Selected Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Status Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (OfficePage.SearchDropDown()) {
						logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportStatusSearchResultOnEmp(regularizationstatus)) {

						logResults.createLogs("Y", "PASS", "Inputted Status Displayed Successfully." + regularizationstatus );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Inputted Status Record." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				}
		
		
				// MPI_1791_Emp_ApprovedRegularization_Reports_02
				@Test(enabled = true, priority = 10, groups = { "Smoke" })
				public void MPI_1791_Emp_ApprovedRegularization_Reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
							"empid,captcha,subject,ccmail");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					String subject = data.get(2);
					String ccmail = data.get(3) ;
					
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					

					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					
			    
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
					
					if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationApprovedTab()) {

						logResults.createLogs("Y", "PASS", "Approved Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
		
		
				// MPI_1792_Emp_ApprovedRegularization_Reports_03
				@Test(enabled = true, priority = 11, groups = { "Smoke" })
				public void MPI_1792_Emp_ApprovedRegularization_Reports_03()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of filter feature by selecting 1st week and select from date and to date ");

					ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
							"empid,captcha");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String Regulizationdate = dateDetails.get("month1WorkingDate");
			       String uidate = Pramod.convertToUIFormat(Regulizationdate);
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
			if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationApprovedTab()) {

						logResults.createLogs("Y", "PASS", "Approved Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
				
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
					
					if (meghpiattendancereport.FromDateSelected(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + Regulizationdate);
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
					
					if (meghpiattendancereport.ToDateSelected(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + Regulizationdate);
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
					if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResultOnEmp(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + uidate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
					
				}
		
		
				// MPI_1793_Emp_ApprovedRegularization_Reports_04
				@Test(enabled = true, priority = 12, groups = { "Smoke" })
				public void MPI_1793_Emp_ApprovedRegularization_Reports_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise a regularization request during the last week get approved. In the approved Regularization Request Reports module, apply the filter for the last week and ensure that the corresponding approved regularization request record is displayed.");

					ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
							"password,captcha,firstname,empid");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String firstname = data.get(2);
					String empid = data.get(3);
					
					 Map<String, String> details = Pramod.getPrevious12WorkingDates();

				        String Regulizationdate = details.get("month7WorkingDate");
				        String formattedPrevDate = Pramod.convertToUIFormat(Regulizationdate);
				        String monthName = details.get("monthName");
				        String year = details.get("year");
					
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
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
					if (meghpiregularizationReportsPage.RegularizationAllReportMonthFilterButton(monthName)) {

						logResults.createLogs("Y", "PASS", "Regularization Month Data Displayed Successfully." + monthName );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying Regularization Month Record." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

						logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully On Admin.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghregularizationrequestpage.PendingTabClickonAdmin()) {

						logResults.createLogs("Y", "PASS", "Pending Tab Clicked On Admin Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Summary Button." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Regularization Emp Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.AdminApprovedToRegulirization(formattedPrevDate,firstname)) {

						logResults.createLogs("Y", "PASS", "Admin Approved To Regulirization Request Successfully." );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Approving Regularization Request." + meghpiattendancereport.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.ApproveButton()) {

						logResults.createLogs("Y", "PASS", " Approved Button Clicked Successfully."  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approve Button." + meghpiattendancereport.getExceptionDesc());
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
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationApprovedTab()) {

						logResults.createLogs("Y", "PASS", "Approved Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Approved Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
					if (meghpiattendancereport.getPrevious12WorkingDateWeekNumber("month7WorkingDate")) {

					    // Step-2: Convert saved static week number to string
					    String weekcount = Integer.toString(MeghPIAttendanceReportsPage.previous12WorkingDateWeekNumber);

					    // Step-3: Select week in dropdown
					    if (meghpiattendancereport.WeeklyDropDownSelected(weekcount)) {
					        logResults.createLogs("Y", "PASS", "Week selected successfully: " + weekcount);
					    } else {
					        logResults.createLogs("Y", "FAIL",
					                "Error while selecting week. " + meghpiattendancereport.getExceptionDesc());
					    }

					} else {
					    logResults.createLogs("Y", "FAIL",
					            "Failed to calculate week number. " + meghpiattendancereport.getExceptionDesc());
					}
		
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResultOnEmp(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + formattedPrevDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
						}
		
				// MPI_1794_Emp_RejectedRegularization_Reports_01
				@Test(enabled = true, priority = 13, groups = { "Smoke" })
				public void MPI_1794_Emp_RejectedRegularization_Reports_01()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of search feature using each search option.");

					ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
							"empid,captcha,regularizationintime,regularizationouttime,regularizationreason,password,firstname,rejectreason,regularizationstatus");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					String regularizationintime = data.get(2);
					String regularizationouttime = data.get(3);
					String regularizationreason = data.get(4);
					String password = data.get(5);
					String firstname = data.get(6);
					String rejectreason = data.get(7);
					String regularizationstatus = data.get(8);
				
					  Map<String, String> details = Pramod.getPrevious12WorkingDates();

				        String Regulizationdate = details.get("month2WorkingDate");
				        String month8WorkingDate = details.get("month8WorkingDate");

				        String formattedPrevDate = Pramod.convertToUIFormat(Regulizationdate);
				        String monthName = details.get("monthName");
				        String year = details.get("year");
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage meghloginpage = new MeghLoginPage(driver);
					MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
					MeghLoginTest meghlogintest = new MeghLoginTest();
			        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
					MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		            MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);

			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);

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



			if (RolePermissionpage.EmpRegulationRequest()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His EmpRegulationRequest Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying EmpRegulationRequest Tab." + RolePermissionpage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.ApplyRegularizationButtonEmp()) {

				logResults.createLogs("Y", "PASS", "Apply Regularization Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Apply Regularization Button." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

				logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + Regulizationdate);
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
			if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + Regulizationdate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + Regulizationdate);
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
	
			if (RolePermissionpage.EmpRegulationRequest()) {

				logResults.createLogs("Y", "PASS", "Employee Can Access His EmpRegulationRequest Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying EmpRegulationRequest Tab." + RolePermissionpage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.ApplyRegularizationButtonEmp()) {

				logResults.createLogs("Y", "PASS", "Apply Regularization Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Apply Regularization Button." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

				logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(month8WorkingDate)) {

				logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + month8WorkingDate);
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
			if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(month8WorkingDate)) {

				logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + month8WorkingDate);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(month8WorkingDate)) {

				logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + Regulizationdate);
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
			if (meghleavepage.MonthFilterContains(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
						+ meghleavepage.getExceptionDesc());
			}
			if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

				logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully On Admin.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Regularization Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
			}
			if (meghregularizationrequestpage.PendingTabClickonAdmin()) {

				logResults.createLogs("Y", "PASS", "Pending Tab Clicked On Admin Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Pending Summary Button." + meghregularizationrequestpage.getExceptionDesc());
			}
			
			if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

				logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Regularization Emp Name." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.AdminRejectedToRegulirization(formattedPrevDate,firstname)) {

				logResults.createLogs("Y", "PASS", "Admin Rejected To Regulirization Request Successfully." + firstname + " " + formattedPrevDate );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Rejecting Regularization Request." + meghpiattendancereport.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.RejectReasonTextField(rejectreason)) {

				logResults.createLogs("Y", "PASS", " Reject Reason Inputted Successfully." + rejectreason );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting Reject Reason." + meghpiattendancereport.getExceptionDesc());
			}
			
			
			if (meghpiregularizationReportsPage.RejectButton()) {

				logResults.createLogs("Y", "PASS", " Reject Button Clicked Successfully."  );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Reject Button." + meghpiattendancereport.getExceptionDesc());
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
			if (RolePermissionpage.ReprtButton()) {

				logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
			}

			
			if (meghpiregularizationReportsPage.RegularizationReportButton()) {

				logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
			}

			if (meghpiregularizationReportsPage.RegulirizationRejectedTab()) {

				logResults.createLogs("Y", "PASS", "Rejected Report Tab Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Rejected Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			
			if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

				logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
		
			
			
			if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(formattedPrevDate)) {

				logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + formattedPrevDate );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Regularization Date." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResultOnEmp(Regulizationdate)) {

				logResults.createLogs("Y", "PASS", "Regularization Date Record Displayed Successfully." + Regulizationdate );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying Inputted Regularization Date Record." + meghpiattendancereport.getExceptionDesc());
			}
			
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			
			
			if (meghpiregularizationReportsPage.RegularizationAllReportStatusCheckBox()) {

				logResults.createLogs("Y", "PASS", "Status Search Option Checkbox Selected Successfully." );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Status Search Option Checkbox." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			if (meghpiregularizationReportsPage.RegularizationAllReportSearchTextField(regularizationstatus)) {

				logResults.createLogs("Y", "PASS", "EmpID Inputted Successfully." + regularizationstatus );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Inputting  Emp ID." + meghpiregularizationReportsPage.getExceptionDesc());
			}
			
			if (meghpiregularizationReportsPage.RegularizationAllReportStatusSearchResultOnEmp(regularizationstatus)) {

				logResults.createLogs("Y", "PASS", "Inputted Status Displayed Successfully." + regularizationstatus );
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Displaying Inputted Status Record." + meghpiregularizationReportsPage.getExceptionDesc());
			}
		}	
			
				// MPI_1795_Emp_RejectedRegularization_Reports_02
				@Test(enabled = true, priority = 14, groups = { "Smoke" })
				public void MPI_1795_Emp_RejectedRegularization_Reports_02()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, check the functionality of Schedule Report by adding email and selecting frequency as \"daily\"  and ensure report is delivered to inputted email");

					ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
							"empid,captcha,subject,ccmail");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					String subject = data.get(2);
					String ccmail = data.get(3) ;
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					
			    
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
					
					if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationRejectedTab()) {

						logResults.createLogs("Y", "PASS", "Rejected Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
			
			
			
				// MPI_1796_Emp_RejectedRegularization_Reports_03
				@Test(enabled = true, priority = 15, groups = { "Smoke" })
				public void MPI_1796_Emp_RejectedRegularization_Reports_03()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, reject the employee’s regularization request. Then, in the emp Rejected Regularization Request Reports module, apply the filter by selecting the 1st week and setting the From Date and To Date. Ensure that the rejected regularization request record is displayed.");

					ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
							"empid,captcha");

					
					String empid = data.get(0);
					String captcha = data.get(1);
					Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
			        String Regulizationdate = dateDetails.get("month2WorkingDate");
			     String uidate = Pramod.convertToUIFormat(Regulizationdate);
			        String monthName = dateDetails.get("monthName");
			        String year = dateDetails.get("year");
			
					

					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, empid, empid, captcha, logResults, driver)) {
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

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationRejectedTab()) {

						logResults.createLogs("Y", "PASS", "Rejected Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
					
					if (meghpiattendancereport.FromDateSelected(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "From Date Selected Successfully." + Regulizationdate);
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
					
					if (meghpiattendancereport.ToDateSelected(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "To Date Selected Successfully." + Regulizationdate);
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
					if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResultOnEmp(Regulizationdate)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + uidate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
					}
				}
			
				// MPI_1797_Emp_RejectedRegularization_Reports_04
				@Test(enabled = true, priority = 16, groups = { "Smoke" })
				public void MPI_1797_Emp_RejectedRegularization_Reports_04()  {
					String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
					logResults.createExtentReport(currTC);
					logResults.setScenarioName(
							"To verify this, raise a regularization request during the last week get rejected. In the emp account rejected Regularization Request Reports module, apply the filter for the last week and ensure that the corresponding rejected regularization request record is displayed.");

					ArrayList<String> data = initBase.loadExcelData("EmpRegularizationRequestReports", currTC,
							"password,captcha,firstname,empid,rejectreason");

					
					String password = data.get(0);
					String captcha = data.get(1);
					String firstname = data.get(2);
					String empid = data.get(3);
					String rejectreason = data.get(4);
					
					 Map<String, String> details = Pramod.getPrevious12WorkingDates();

				        String month8WorkingDate = details.get("month8WorkingDate");
				        String formattedPrevDate = Pramod.convertToUIFormat(month8WorkingDate);
				        String monthName = details.get("monthName");
				        String year = details.get("year");
					
					
					MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
					MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
					MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
			    	MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
			MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
	        MeghLeavePage meghleavepage = new MeghLeavePage(driver);

					
					if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
						logResults.createLogs("Y", "PASS", "Login Done Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
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
					if (meghleavepage.MonthFilterContains(month8WorkingDate)) {

						logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + month8WorkingDate );
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting Emp Applied WeekOff Requested Month."
								+ meghleavepage.getExceptionDesc());
					}
					if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

						logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully On Admin.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Summary Button On Admin." + meghregularizationrequestpage.getExceptionDesc());
					}
					if (meghregularizationrequestpage.PendingTabClickonAdmin()) {

						logResults.createLogs("Y", "PASS", "Pending Tab Clicked On Admin Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Pending Summary Button." + meghregularizationrequestpage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

						logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Regularization Emp Name." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.AdminRejectedToRegulirization(formattedPrevDate,firstname)) {

						logResults.createLogs("Y", "PASS", "Admin Rejected To Regulirization Request Successfully." + firstname + " " + formattedPrevDate );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Rejecting Regularization Request." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RejectReasonTextField(rejectreason)) {

						logResults.createLogs("Y", "PASS", " Reject Reason Inputted Successfully." + rejectreason );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Reject Reason." + meghpiattendancereport.getExceptionDesc());
					}
					
					
					if (meghpiregularizationReportsPage.RejectButton()) {

						logResults.createLogs("Y", "PASS", " Reject Button Clicked Successfully."  );
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Reject Button." + meghpiattendancereport.getExceptionDesc());
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
								"Login Is Failed." + MeghLoginPage.getExceptionDesc());
					}
					if (RolePermissionpage.ReprtButton()) {

						logResults.createLogs("Y", "PASS", "Report Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Report Button." + RolePermissionpage.getExceptionDesc());
					}

					
					if (meghpiregularizationReportsPage.RegularizationReportButton()) {

						logResults.createLogs("Y", "PASS", "Regularization Report Button Clicked Successfully .");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Regularization Report." + meghpiregularizationReportsPage.getExceptionDesc());
					}

					if (meghpiregularizationReportsPage.RegulirizationRejectedTab()) {

						logResults.createLogs("Y", "PASS", "Rejected Report Tab Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Rejected Report Tab." + meghpiregularizationReportsPage.getExceptionDesc());
					}
					
					if (meghpiregularizationReportsPage.RegularizationAllReportFilterButton()) {

						logResults.createLogs("Y", "PASS", "Filter Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Button." + meghpiregularizationReportsPage.getExceptionDesc());
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
					if (meghpiattendancereport.getPrevious12WorkingDateWeekNumber("month8WorkingDate")) {

					    // Step-2: Convert saved static week number to string
					    String weekcount = Integer.toString(MeghPIAttendanceReportsPage.previous12WorkingDateWeekNumber);

					    // Step-3: Select week in dropdown
					    if (meghpiattendancereport.WeeklyDropDownSelected(weekcount)) {
					        logResults.createLogs("Y", "PASS", "Week selected successfully: " + weekcount);
					    } else {
					        logResults.createLogs("Y", "FAIL",
					                "Error while selecting week. " + meghpiattendancereport.getExceptionDesc());
					    }

					} else {
					    logResults.createLogs("Y", "FAIL",
					            "Failed to calculate week number. " + meghpiattendancereport.getExceptionDesc());
					}
		
					if (meghpiattendancereport.FilterSaveButton()) {

						logResults.createLogs("Y", "PASS", "Filter Save Button Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Filter Save Button." + meghpiattendancereport.getExceptionDesc());
					}
					if (meghpiregularizationReportsPage.RegularizationAllReportDateSearchResultOnEmp(month8WorkingDate)) {

						logResults.createLogs("Y", "PASS", "Filtered Data Displayed Successfully." + month8WorkingDate);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying  Filtered Data." + meghpiattendancereport.getExceptionDesc());
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
