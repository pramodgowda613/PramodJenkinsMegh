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
import com.MeghPI.Attendance.pages.MeghMasterDepartmentMappingPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamMappingPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIOverTimeRequestPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationRequestPage;
import com.MeghPI.Attendance.pages.MeghPIWeekOffRequestPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;


public class MeghPIAttendanceReprocessTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	

	private String cmpcode = "";
	private String Emailid = "";
	private String designationname = "";
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
		designationname = "AutoDESN" + initBase.executionRunTime;
		entityname = "AutoEN" + initBase.executionRunTime;
		officename = "AutoON" + initBase.executionRunTime;

		AdminFirstName = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminFirstName", "");
	}
	
	
	// MPI_1682_Attendance_Reprocess_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
public void MPI_1682_Attendance_Reprocess_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of search feature by selecting each search option.");

		ArrayList<String> data = initBase.loadExcelData("Attendance_Reprocess", currTC,
				"password,captcha,deptname,officename,state,teamname,pin,longitude,firstname,baseuri,loginendpoint,endpointoftransaction,cardnumber,cardtype,bio1finger,bio2finger,locationid,inouttime,mode,photo,secondinouttime,secondmode,updateattendanceendpoint,empid,tablefieldendpoint,createentityendpoint,enrollendpoint,lastname,doj,phoneno,email,sendemailinvite");

		MeghMasterTeamMappingPage TeamMappingPage = new MeghMasterTeamMappingPage(driver);
	int i = 0;
		String password = data.get(i++);
		String captcha = data.get(i++);
		String deptname = data.get(i++);
		String officeName = data.get(i++);

		String state = data.get(i++);
		String teamname = data.get(i++) ;
		String pin = data.get(i++);
		String longitude = data.get(i++);
		String firstname = data.get(i++);
		
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
	       
	        String tablefieldendpoint = data.get(i++);
	        String createentityendpoint = data.get(i++);
	        String enrollendpoint = data.get(i++);
	        String lastname = data.get(i++);
	        String doj = data.get(i++);
	        String phoneno = data.get(i++);
	        String email = data.get(i++);
	        String sendEmailInviteStr = data.get(i++);  

	        boolean sendemailinvite = Boolean.parseBoolean(sendEmailInviteStr);

            MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);
			 MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);
		
		MeghLoginPage meghloginpage =  new MeghLoginPage(driver);

	
		String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;
        
		 Map<String, String> dateInfo = Pramod.getMonthFirstWorkingDateDetails();

	        String monthFirstWorkingDate = dateInfo.get("monthFirstWorkingDate");

		LocalDate localDate = LocalDate.parse(monthFirstWorkingDate);
		 String firstDayOfMonth = localDate.withDayOfMonth(1).toString();
		
		String inouttime = monthFirstWorkingDate + " " + inouttime1;
		String secondInOutTime = monthFirstWorkingDate + " " + secondInOutTime2;
	    
			 MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
	            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

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



//Punch IN
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
					"Login Is Failed." + meghloginpage.getExceptionDesc());
		}
		


		// OfficeName Logic
	
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

			if (EmployeePage.OfficeName(officeName)) {
				logResults.createLogs("Y", "PASS", " OfficeName Entered In the Office Name TextField ." + officeName);
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
			if (EmployeePage.GeoFencingRange(longitude)) {
				logResults.createLogs("Y", "PASS", " Geo Fencing Range Inputted Successfully." + longitude);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Inputting Geo Fencing Range ." + EmployeePage.getExceptionDesc());
			}
			if (EmployeePage.LatitudeTextField(longitude)) {
				logResults.createLogs("Y", "PASS", " Latitude Inputted Successfully." + longitude);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Inputting Latitude ." + EmployeePage.getExceptionDesc());
			}
			if (EmployeePage.LongitudeTextField(longitude)) {
				logResults.createLogs("Y", "PASS", " Longitude Inputted Successfully." + longitude);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Inputting Longitude ." + EmployeePage.getExceptionDesc());
			}
	
			if (EmployeePage.CompanyLocationSaveButton()) {
				logResults.createLogs("Y", "PASS", " Successfully Clicked On CompanyLocationSaveButton .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On CompanyLocationSaveButton ." + EmployeePage.getExceptionDesc());
			}

		 // End of office Name

		// Dept Name
		
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

			if (DepartmentMappingpage.OfficeSelectDropDown(officeName)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected OfficeName." + officeName);
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

			
		 // End of Dept Name
	
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
			}	
			
			if (EmployeePage.DirectoryButton()) {
				logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Directery Button." + EmployeePage.getExceptionDesc());
			}

			if (EmployeePage.EmployeeTab()) {
				logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On EmployeeTab." + EmployeePage.getExceptionDesc());
			}

		
			if (EmployeePage.SearchTextField(firstname)) {
				logResults.createLogs("Y", "PASS",
						"Clicked On Search Text Field And Pass The Created Employee LastName Successfully." + firstname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On Search Text Field." + EmployeePage.getExceptionDesc());
			}
			

			if (EmployeePage.SearchResult()) {
				logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully." );
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Displaying Employee." + EmployeePage.getExceptionDesc());
			}

			if (DepartmentPage.Employee3dotsFirstRecord()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On First Employee Record 3dots ." + DepartmentPage.getExceptionDesc());
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
			
			if (DepartmentPage.CompanyLocationSelected(officeName)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected Office Name ." + officeName);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting Office Name." + DepartmentPage.getExceptionDesc());
			}

			if (DepartmentPage.DepartmentDropDown()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On DepartmentDropDown .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On DepartmentDropDown  ." + DepartmentPage.getExceptionDesc());
			}

			if (TeamMappingPage.DeptDropDownTextField(deptname)) {
				logResults.createLogs("Y", "PASS", "Successfully Input The DeptName ." + deptname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Inputting  Department Name  ." + TeamMappingPage.getExceptionDesc());
			}
			
			if (TeamMappingPage.DeptSearchResult()) {
				logResults.createLogs("Y", "PASS", "Successfully Selected DeptName .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Selecting  Department Name  ." + TeamMappingPage.getExceptionDesc());
			}

			if (AttendancePolicyPage.TeamDropDown(teamname)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
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
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.EmpIDSearchOption()) {

				logResults.createLogs("Y", "PASS", "Emp ID Search Option Selected Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Emp ID Search Option." + meghpiweekoffrequestpage.getExceptionDesc());
			}
		
			
			if (OfficePage.SearchDropDown()) {
				logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
			}
			
			if (meghpiweekoffrequestpage.AttendanceProcessSearchTextField(empid)) {

				logResults.createLogs("Y", "PASS", "Emp ID Inputted Successfully." + empid);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp ID."
						+ meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.AttendanceProcessEmpIDSearchResult(empid)) {

				logResults.createLogs("Y", "PASS", "Emp ID Validated Successfully." + empid);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Emp ID."
						+ meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.AttendanceProcessSearchTextField(firstname)) {

				logResults.createLogs("Y", "PASS", "Emp Name Inputted Successfully." + firstname);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name."
						+ meghpiweekoffrequestpage.getExceptionDesc());
			}
			if (meghpiweekoffrequestpage.AttendanceProcessEmpNameSearchResult(firstname)) {

				logResults.createLogs("Y", "PASS", "Emp Name Validated Successfully." + firstname);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Validating Emp Name."
						+ meghpiweekoffrequestpage.getExceptionDesc());
			}
			
	}
	
	
	
	
	// MPI_1683_Attendance_Reprocess_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_1683_Attendance_Reprocess_02()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create an attendance policy with “No Punch”, assign the employee to that policy, then run the attendance process by selecting the first day of last month for that employee, and ensure the employee’s attendance is updated with “P” status for the entire month..");

		ArrayList<String> data = initBase.loadExcelData("Attendance_Reprocess", currTC,
				"password,captcha,empid,policyname,duration,othours,officename,empstatusonadmin,firstname");
		
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		
		  MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);

		
		String password = data.get(0);
		String captcha = data.get(1);
		String empid = data.get(2);
		String policyname = data.get(3);
		String duration = data.get(4);
		String othours = data.get(5);
		String officename = data.get(6);
		String empstatusonadmin = data.get(7);
		String firstname = data.get(8);
			
			
		 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();
	        String firstsunday = (String) datas.get("firstDayLastMonth");

		
	        MeghLeavePage meghleavepage = new MeghLeavePage(driver);
			 MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

	        MeghLoginPage meghloginpage = new MeghLoginPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();
				MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
				MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
	            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

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
	
	if (AttendancePolicyPage.AddAttendancePolicyButton()) {

		logResults.createLogs("Y", "PASS", "AddAttendancePolicyButton Clicked Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On AddAttendancePolicyButton." + AttendancePolicyPage.getExceptionDesc());
	}

	if (AttendancePolicyPage.PolicyNameTextField(policyname)) {

		logResults.createLogs("Y", "PASS", "AttendancePolicy Name Entered Successfully ." + policyname);
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Inputting AttendancePolicy Name." + AttendancePolicyPage.getExceptionDesc());
	}

	if (AttendancePolicyPage.WebPunchButton()) {

		logResults.createLogs("Y", "PASS", "WebPunchButton Button Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On WebPunchButton ." + AttendancePolicyPage.getExceptionDesc());
	}

	

	if (AttendancePolicyPage.RegulationRequestCheckBox()) {

		logResults.createLogs("Y", "PASS", "RegulationRequest CheckBox Clicked  Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On RegulationRequest CheckBox ." + AttendancePolicyPage.getExceptionDesc());
	}
	
	if (AttendancePolicyPage.RegulirizationPerMonthTextField(duration)) {

		logResults.createLogs("Y", "PASS", "RegulationRequest No Of Days Inputted  Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Inputting RegulationRequest No Of Days ." + AttendancePolicyPage.getExceptionDesc());
	}
	if (AttendancePolicyPage.RegulirizationUptoTextField(duration)) {

		logResults.createLogs("Y", "PASS", "Regulirization Upto 31days Inputted  Successfully ." + duration);
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Inputting 31 day On Regulirization TextField ." + AttendancePolicyPage.getExceptionDesc());
	}
	
	if (AttendancePolicyPage.OverTimeCheckBox()) {

		logResults.createLogs("Y", "PASS", "OverTime CheckBox Clicked  Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On OverTime CheckBox ." + AttendancePolicyPage.getExceptionDesc());
	}
	if (AttendancePolicyPage.OverTimeOnWeekDay()) {

		logResults.createLogs("Y", "PASS", "OverTimeOnWeekDay Clicked  Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On OverTimeOnWeekDay ." + AttendancePolicyPage.getExceptionDesc());
	}
	if (AttendancePolicyPage.OTApprovedBySystem()) {

		logResults.createLogs("Y", "PASS", "OTApprovedBySystem Clicked Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On OTApprovedBySystem." + AttendancePolicyPage.getExceptionDesc());
	}
	if (AttendancePolicyPage.OTMinutesTextField()) {

		logResults.createLogs("Y", "PASS", "OTMinutesTextField Clicked  Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On OTMinutesTextField ." + AttendancePolicyPage.getExceptionDesc());
	}
	if (AttendancePolicyPage.OTMinutesTextFieldInput(othours)) {

		logResults.createLogs("Y", "PASS", "OTMinutes Inputted Successfully ." + othours);
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Inputting  OT Minutes." + AttendancePolicyPage.getExceptionDesc());
	}
	if (meghpiweekoffrequestpage.AttendanceNoPunchSelected()) {

		logResults.createLogs("Y", "PASS", "No Punch Selected Successfully." );
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Selecting No Punch Option ." + meghpiweekoffrequestpage.getExceptionDesc());
	}
	

	if (AttendancePolicyPage.AddEmpCriteria()) {

		logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On AddEmpCriteria Button ." + AttendancePolicyPage.getExceptionDesc());
	}

	if (AttendancePolicyPage.OfficeDropDown(officename)) {

		logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully ." + officename);
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Selecting Office Name ." + AttendancePolicyPage.getExceptionDesc());
	}

	if (AttendancePolicyPage.DeptDropDownClick()) {
		logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error while Clicking On Dept DropDown." + AttendancePolicyPage.getExceptionDesc());
	}
	
	if (AttendancePolicyPage.DeptOptionSelected()) {
		logResults.createLogs("Y", "PASS", "Successfully Selected  Dept.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error while Selecting  Dept ." + AttendancePolicyPage.getExceptionDesc());
	}
	
	if (AttendancePolicyPage.TeamDropDownClick()) {
		logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error while Clicking On Team  Dropdown ." + AttendancePolicyPage.getExceptionDesc());
	}
	
	if (AttendancePolicyPage.TeamOptionSelected()) {
		logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown.");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error while Selecting Team From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
	}

	if (AttendancePolicyPage.ApplyButton()) {

		logResults.createLogs("Y", "PASS", "Clicked On Apply Button Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On Apply Button ." + AttendancePolicyPage.getExceptionDesc());
	}

	if (AttendancePolicyPage.CreatePolicyButton()) {

		logResults.createLogs("Y", "PASS", "CreatePolicyButton Button Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On CreatePolicyButton ." + AttendancePolicyPage.getExceptionDesc());
	}

	if (AttendancePolicyPage.YesButton()) {

		logResults.createLogs("Y", "PASS", "Yes Button Button Successfully .");
	} else {
		logResults.createLogs("Y", "FAIL",
				"Error While Clicking On Yes Button ." + AttendancePolicyPage.getExceptionDesc());
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
		if (meghpiOTpage.AddAttendanceProcessDateTextFieldInput(firstsunday)) {

			logResults.createLogs("Y", "PASS", "First Day Of the Month Selected Successfully ." + firstsunday);
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
		
		if (RolePermissionpage.EmployeeAttendanceButton()) {

			logResults.createLogs("Y", "PASS", " Attendance Module Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Attendane Module." + RolePermissionpage.getExceptionDesc());
		}
		
		if (meghleavepage.MonthFilterContains(firstsunday)) {

			logResults.createLogs("Y", "PASS", "Week Off Request Applied Month Selected  Successfully Of Employee Account." + firstsunday );
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
		if (meghpiweekoffrequestpage.validateWeekdayStatus(empid, empstatusonadmin )) {

			logResults.createLogs("Y", "PASS", "Emp Attendance Status Validated Successfully After Attendance Reprocess." + empstatusonadmin);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Emp Attendance Status ." + meghpiweekoffrequestpage.getExceptionDesc());
		}	
	}
	
	
	// MPI_1684_Attendance_Reprocess_03
		@Test(enabled = true, priority = 3, groups = { "Smoke" })
		public void MPI_1684_Attendance_Reprocess_03()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, add OT in the attendance policy, then select a specific office, department, team, designation, entity type, and device type in Attendance Reprocess, and ensure that the attendance of employees matching the selected criteria is reprocessed and the OT is updated.");

			ArrayList<String> data = initBase.loadExcelData("Attendance_Reprocess", currTC,
					"password,captcha,empid,othours,officename,firstname,devicename");
			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			
			  MeghPIWeekOffRequestPage meghpiweekoffrequestpage = new MeghPIWeekOffRequestPage(driver);

			
			String password = data.get(0);
			String captcha = data.get(1);
			String empid = data.get(2);
			String othours = data.get(3);
			String officename = data.get(4);
			String firstname = data.get(5);
			String devicename = data.get(6);
				
				
			 Map<String, Object> datas = Pramod.getLastMonthWeekendAndWorkingDetails();
		        String firstsunday = (String) datas.get("firstDayLastMonth");

			
				 MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

		        MeghLoginPage meghloginpage = new MeghLoginPage(driver);
				MeghLoginTest meghlogintest = new MeghLoginTest();
				
		            MeghPIOverTimeRequestPage meghpiOTpage = new MeghPIOverTimeRequestPage(driver);

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
					if (meghpiOTpage.AddAttendanceProcessDateTextFieldInput(firstsunday)) {

						logResults.createLogs("Y", "PASS", "First Day Of the Month Selected Successfully ." + firstsunday);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Selecting First Day Of The Month."
								+ meghpiOTpage.getExceptionDesc());
					}
					
					if (meghpiweekoffrequestpage.AttendanceProcessOfficeSelection(officename)) {

						logResults.createLogs("Y", "PASS", "Office Name Selected Successfully." + officename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Office Name." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.AttendanceProcessDeptDropDown()) {

						logResults.createLogs("Y", "PASS", "Dept Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Dept Drop Down." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.AttendanceProcessDeptSelected()) {

						logResults.createLogs("Y", "PASS", "Dept Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting  Dept." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					
					if (meghpiweekoffrequestpage.AttendanceProcessTeamDropDown()) {

						logResults.createLogs("Y", "PASS", "Team Drop DOwn Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Team DropDown." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.AttendanceProcessTeamSelected()) {

						logResults.createLogs("Y", "PASS", "Team Selected Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Team." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.AttendanceProcessEntitySelected(entityname)) {

						logResults.createLogs("Y", "PASS", "Entity Selected Successfully." + entityname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Entity." + meghpiweekoffrequestpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.AttendanceProcessDeviceTypeSelected(devicename)) {

						logResults.createLogs("Y", "PASS", "Device Selected Successfully." + devicename);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Selecting Device." + meghpiweekoffrequestpage.getExceptionDesc());
					}
				
					if (meghpiOTpage.AddAttendanceProcessApplyButton()) {

						logResults.createLogs("Y", "PASS", "Add Attendance Process Apply Button Clicked Successfully In HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Clicking On Process Button."
								+ meghpiOTpage.getExceptionDesc());
					}
					if (meghpiweekoffrequestpage.AttendanceProcessEmpNameSearchResult(firstname)) {

						logResults.createLogs("Y", "PASS", "Emp Name Validated Successfully." + firstname);
					} else {
						logResults.createLogs("Y", "FAIL", "Error While Validating Emp Name."
								+ meghpiweekoffrequestpage.getExceptionDesc());
					}
			
					if (meghpiOTpage.AddAttendanceProcessValidation()) {

						logResults.createLogs("Y", "PASS", "Waiting For Attendance Process To Get Complete  Successfully In HR Account.");
					} else {
						logResults.createLogs("Y", "FAIL", "Attendance Process Is Not Completed ."
								+ meghpiOTpage.getExceptionDesc());
					}
					if (RolePermissionpage.EmployeeAttendanceButton()) {

						logResults.createLogs("Y", "PASS", " Attendance Module Clicked Successfully.");
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Clicking On Attendane Module." + RolePermissionpage.getExceptionDesc());
					}
					if (meghregularizationrequestpage.SearchTextFieldOnEmpTabOnAdmin(firstname)) {

						logResults.createLogs("Y", "PASS", " Emp Name Inputted Successfully." + firstname);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Inputting Emp Name." + meghregularizationrequestpage.getExceptionDesc());
					}	
					if (meghpiOTpage.ValidateEmpOTHoursCountAdminEmployeeTab(empid, othours)) {

						logResults.createLogs("Y", "PASS", "OT Hours Of Emp Record Displayed Successfully." + othours);
					} else {
						logResults.createLogs("Y", "FAIL",
								"Error While Displaying OT Hours Of Emp Record." + meghpiOTpage.getExceptionDesc());
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
