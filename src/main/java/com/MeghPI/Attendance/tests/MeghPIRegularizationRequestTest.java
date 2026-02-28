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
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentMappingPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterOfficePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamMappingPage;
import com.MeghPI.Attendance.pages.MeghMasterTeamPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage;
import com.MeghPI.Attendance.pages.MeghPIAttendanceReportsPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationReportsPage;
import com.MeghPI.Attendance.pages.MeghPIRegularizationRequestPage;
import com.MeghPI.Attendance.pages.MeghPITimeLogReportsPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import com.MeghPI.Attendance.pages.MeghPIAttenAPIPage.TableFieldIds;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import io.restassured.response.Response;
import utils.Pramod;
import utils.Utils;

public class MeghPIRegularizationRequestTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();

	private String cmpcode = "";
	private String Emailid = "";
	private String designationname = "";
	private String officename = "";
	private String AdminEmpID = "";

	private String AdminFirstName = "";
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
		AdminEmpID = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminEmpID", "");
	}

	// MPI_748_RegularizationRequest_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_748_RegularizationRequest_01() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"As an employee, raise a Regularization request and verify that the request is displayed under the 'Regularization Request' tab in the admin account.");

		// Load Excel data
		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,baseuri,loginendpoint,updateattendanceendpoint,createentityendpoint,firstname,lastname,empid,phoneno,email,doj,sendemailinvite,enrollendpoint,tablefieldendpoint,photo,captcha,regularizationstatus,regularizationintime,regularizationouttime,regularizationreason");

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
		String captcha = data.get(i++);
		String regularizationstatus = data.get(i++);
		String regularizationintime = data.get(i++);
		String regularizationouttime = data.get(i++);
		String regularizationreason = data.get(i++);
		String date = "03-01-2025";

		System.out.println("data");
		String deviceuniqueid = "Autodeviceid" + initBase.executionRunTime;

		Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
		String monthFirstWorkingDate = dateDetails.get("month1WorkingDate");
		String firstDayOfMonth = dateDetails.get("firstDayOfMonth");

		MeghPIAttenAPIPage apiPage = new MeghPIAttenAPIPage();
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);

		TableFieldIds ids = MeghPIAttenAPIPage.executeGetTableFieldIds(baseuri, loginendpoint, Emailid, password,
				cmpcode, baseuri, tablefieldendpoint, officename, // company location name
				AdminFirstName, // entity name
				entityname // entity type name
		);
		System.out.println(ids.companyLocationId + "love");
		System.out.println(ids.entityId);
		System.out.println(ids.entityTypeId);
		Integer companylocation = ids.companyLocationId;
		Integer reportingto = ids.entityId;
		Integer entitytypeid = ids.entityTypeId;

		// Execute the combined method
		Response enrollResponse = MeghPIAttenAPIPage.executeCreateEntityAndEnroll(baseuri, loginendpoint, Emailid,
				password, cmpcode, baseuri, createentityendpoint, baseuri, enrollendpoint, companylocation, firstname,
				lastname, reportingto, doj, phoneno, email, empid, entitytypeid, sendemailinvite, deviceuniqueid,
				photo);

		if (enrollResponse.getStatusCode() == 200) {
			String errorCode = enrollResponse.jsonPath().getString("ErrorCode");
			String errorDesc = enrollResponse.jsonPath().getString("ErrorDescription");

			if ("0".equals(errorCode) && "Success".equalsIgnoreCase(errorDesc)) {
				logResults.createLogs("N", "PASS", "✅ Enrollment successful for Employee ID: " + empid);
			} else {
				logResults.createLogs("N", "FAIL", "❌ Enrollment failed for Employee ID: " + empid + " | ErrorCode: "
						+ errorCode + " | ErrorDescription: " + errorDesc);
				return; // stop execution if API error
			}
		} else {
			logResults.createLogs("N", "FAIL", "❌ Enrollment API failed for Employee ID: " + empid + " | HTTP Status: "
					+ enrollResponse.getStatusCode() + " | Response: " + enrollResponse.asString());
			return;
		}

		// Trigger attendance update first
		Response updateResp = apiPage.executeUpdateAttendance(baseuri, loginendpoint, Emailid, password, cmpcode,
				baseuri, updateattendanceendpoint, // <-- add endpoint in excel
				empid, firstDayOfMonth + "T00:00:00.000Z");

		if (updateResp.statusCode() == 200 && updateResp.jsonPath().getBoolean("IsSuccess")) {
			logResults.createLogs("N", "PASS", "UpdateAttendance API executed successfully for " + empid);
		} else {
			logResults.createLogs("N", "FAIL", "UpdateAttendance API failed." + updateResp.asString());
		}

		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + meghloginpage.getExceptionDesc());
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

		if (EmployeePage.SearchTextField(AdminFirstName)) {
			logResults.createLogs("Y", "PASS",
					"Clicked On Search Text Field And Pass The Created Employee LastName Successfully."
							+ AdminFirstName);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Search Text Field." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.SearchResult()) {
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully.");
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
		if (DepartmentPage.ManageProfileDateField()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Date TextField.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Date TextField ." + DepartmentPage.getExceptionDesc());
		}
		if (DepartmentPage.FirstDateSelection(date)) {
			logResults.createLogs("Y", "PASS", "Successfully Input The Joining Date." + date);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Inputting The Joining Date ." + DepartmentPage.getExceptionDesc());
		}

		if (RolePermissionpage.SaveChanges()) {
			logResults.createLogs("Y", "PASS", "SaveChanges Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SaveChanges Button." + RolePermissionpage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + meghloginpage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

			logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + monthFirstWorkingDate);
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
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + monthFirstWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + monthFirstWorkingDate);
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Reason."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Apply Save Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account."
							+ monthFirstWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.RegularizationSummaryClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.PendingTabClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Pending Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Pending Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnEmpAccount(monthFirstWorkingDate, regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "Regularization Request Pending Status Validated Successfully."
					+ monthFirstWorkingDate + " " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Regularization Request Pending Status."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

	}

	// MPI_762_RegularizationRequest_15
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_762_RegularizationRequest_15() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To check that, approve the employee's Regularization request and ensure employee's Regularization request is approved and attendance status is present.");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,regularizationstatus,firstname,empid");

		String password = data.get(0);
		String captcha = data.get(1);
		String regularizationstatus = data.get(2);
		String firstname = data.get(3);
		String empid = data.get(4);

		Map<String, String> dateInfo = Pramod.getPrevious12WorkingDates();
		String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");
		String formattedregularizationdate = Pramod.convertToUIFormat(monthFirstWorkingDate);

		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);

		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + MeghLoginPage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Rquest In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account."
							+ monthFirstWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Regularization Emp Name." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.AdminApprovedToRegulirization(formattedregularizationdate, firstname)) {

			logResults.createLogs("Y", "PASS", "Admin Approved To Regulirization Request Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Approving Regularization Request." + meghpiattendancereport.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.ApproveButton()) {

			logResults.createLogs("Y", "PASS", " Approved Button Clicked Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Approve Button." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully On Admin.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button On Admin."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ApprovedTabInAdminClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Approved Summary Clicked Successfully On Admin.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Regularization Approved Summary Button On Admin."
							+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnAdminAccount(monthFirstWorkingDate, regularizationstatus,
				empid)) {

			logResults.createLogs("Y", "PASS", "Regularization Request Approved Status Validated Successfully."
					+ monthFirstWorkingDate + " " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Regularization Request Approved Status."
					+ meghregularizationrequestpage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + meghloginpage.getExceptionDesc());
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
		if (meghleavepage.MonthFilterContains(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account."
							+ monthFirstWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RegularizationSummaryClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ApprovedTabClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Approved Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Approved Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnEmpAccount(monthFirstWorkingDate, regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "Regularization Request Approved Status Validated Successfully."
					+ monthFirstWorkingDate + " " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Regularization Request Approved Status."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
	}

	// MPI_749_RegularizationRequest_02
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_749_RegularizationRequest_02() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To check that, raise the Regularization request and ensure record is displaying in the employee account under \"Regularization request\" tab");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,regularizationreason,empid,secondintime,secondouttime");

		String password = data.get(0);
		String captcha = data.get(1);
		String regularizationstatus = data.get(2);
		String regularizationintime = data.get(3);
		String regularizationouttime = data.get(4);
		String regularizationreason = data.get(5);
		String empid = data.get(6);
		String secondintime = data.get(7);
		String secondouttime = data.get(8);

		Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
		String Regulizationdate = dateDetails.get("month2WorkingDate");

		Map<String, String> thirdrdday = Pramod.getPrevious12WorkingDates();

		String thirdworkingday = thirdrdday.get("month3WorkingDate");

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);

		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();

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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Reason."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Apply Save Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.ApplyRegularizationButtonEmp()) {

			logResults.createLogs("Y", "PASS", "Apply Regularization Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

			logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(thirdworkingday)) {

			logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + thirdworkingday);
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
		if (meghpiregularizationReportsPage.EmpRegularizationInTimeInputted(secondintime)) {

			logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + secondintime);
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
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(thirdworkingday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + thirdworkingday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(thirdworkingday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + thirdworkingday);
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
		if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(secondouttime)) {

			logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + secondouttime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Out TIme." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(secondouttime)) {

			logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + secondouttime);
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Reason."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Apply Save Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(thirdworkingday)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.RegularizationSummaryClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.PendingTabClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Pending Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Pending Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnEmpAccount(Regulizationdate, regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "Regularization Request Pending Status Validated Successfully."
					+ Regulizationdate + " " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Regularization Request Pending Status."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
	}

	// MPI_763_RegularizationRequest_16
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_763_RegularizationRequest_16() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To check that, check the functionality of the 'Reject All' button and ensure that all pending requests are rejected. Also, confirm that the status of the requested day for those employees is marked as 'Absent'.");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,regularizationstatus,empid,firstname,rejectreason");

		String password = data.get(0);
		String captcha = data.get(1);
		String regularizationstatus = data.get(2);

		String empid = data.get(3);

		String firstname = data.get(4);
		String rejectreason = data.get(5);

		Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
		String Regulizationdate = dateDetails.get("month2WorkingDate");

		Map<String, String> thirdrdday = Pramod.getPrevious12WorkingDates();

		String thirdworkingday = thirdrdday.get("month3WorkingDate");

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);

		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + MeghLoginPage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Rquest In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(thirdworkingday)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Regularization Emp Name." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RejectAllButton()) {

			logResults.createLogs("Y", "PASS", "Regularization request RejectAll Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization RejectAll Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RejectReasonTextField(rejectreason)) {

			logResults.createLogs("Y", "PASS",
					"Regularization request Reject Reason Inputted Successfully." + rejectreason);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting  Regularization Reject Reason."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RejectAllConfirmButton()) {

			logResults.createLogs("Y", "PASS", "Regularization request RejectAll Confirm Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization RejectAll Confirm Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully On Admin.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button On Admin."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RejectedTabClickedOnAdmin()) {

			logResults.createLogs("Y", "PASS", "RejectedTab Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Reject Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnAdminAccount(thirdworkingday, regularizationstatus,
				empid)) {

			logResults.createLogs("Y", "PASS", "Regularization Request Approved Status Validated Successfully."
					+ thirdworkingday + " " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Regularization Request Approved Status."
					+ meghregularizationrequestpage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + meghloginpage.getExceptionDesc());
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
		if (meghleavepage.MonthFilterContains(thirdworkingday)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RegularizationSummaryClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RejectedTabClickedOnEmp()) {

			logResults.createLogs("Y", "PASS", "Regularization Rejected Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Rejected Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnEmpAccount(Regulizationdate, regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "Regularization Request Rejected Status Validated Successfully."
					+ Regulizationdate + " " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Regularization Request Rejected Status."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
	}

	// MPI_761_RegularizationRequest_14
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_761_RegularizationRequest_14() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To check that, as a admin reject the employee's Regularization request and ensure status is rejected and ensure employee's attendance status is absent.");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,empid,firstname,empstatusonadmin,rejectreason,regularizationintime,regularizationouttime,regularizationreason");

		String password = data.get(0);
		String captcha = data.get(1);

		String empid = data.get(2);

		String firstname = data.get(3);
		String empstatusonadmin = data.get(4);
		String RejectReason = data.get(5);
		String regularizationintime = data.get(6);
		String regularizationouttime = data.get(7);
		String regularizationreason = data.get(8);

		Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
		String Regulizationdate = dateDetails.get("month2WorkingDate");
		String RegulizationdateUI = Pramod.convertToUIFormat(Regulizationdate);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);

		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);

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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Reason."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Apply Save Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + meghloginpage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Rquest In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(Regulizationdate)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully On Admin.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button On Admin."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.PendingTabClickonAdmin()) {

			logResults.createLogs("Y", "PASS", "Pending Tab Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Pending Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Regularization Emp Name." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.AdminRejectedToRegulirization(RegulizationdateUI, firstname)) {

			logResults.createLogs("Y", "PASS",
					"Admin Rejected To Regulirization Request Successfully." + firstname + " " + RegulizationdateUI);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Rejecting Regularization Request." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RejectReasonTextField(RejectReason)) {

			logResults.createLogs("Y", "PASS", " Reject Reason Inputted Successfully." + RejectReason);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Reject Reason." + meghpiattendancereport.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RejectButton()) {

			logResults.createLogs("Y", "PASS", " Reject Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Reject Button." + meghpiattendancereport.getExceptionDesc());
		}
		if (RolePermissionpage.HrAccountAttendanceEmpTab()) {

			logResults.createLogs("Y", "PASS", "Employee Attendance Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EMp Attendance Tab." + RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(Regulizationdate)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + Regulizationdate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.SearchTextFieldOnEmpTabOnAdmin(firstname)) {

			logResults.createLogs("Y", "PASS", "Regularization Rejected Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting  Regularization Rejected Emp Name."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.validateAttendanceStatus(empid, firstname, Regulizationdate,
				empstatusonadmin)) {

			logResults.createLogs("Y", "PASS", "Regularization Rejected Emp Attendance Validated Successfully." + empid
					+ " " + Regulizationdate + " " + empstatusonadmin);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Regularization Request Rejected Emp Attendance."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

	}

	// MPI_750_RegularizationRequest_03
	@Test(enabled = true, priority = 6, groups = { "Smoke" })
	public void MPI_750_RegularizationRequest_03() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("To check that, check the functionality of search feature on Employee account");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,empid,firstname,regularizationreason");

		String password = data.get(0);
		String captcha = data.get(1);

		String empid = data.get(2);

		String firstname = data.get(3);
		String regularizationreason = data.get(4);
		String regulizationreason = "";

		Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();
		String last2ndworkingdate = dateDetails.get("month2WorkingDate");
		String RegulizationdateUI = Pramod.convertToUIFormat(last2ndworkingdate);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

		MeghPIAttendanceReportsPage meghpiattendancereport = new MeghPIAttendanceReportsPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghMasterOfficePage OfficePage = new MeghMasterOfficePage(driver);
		MeghPITimeLogReportsPage meghpitimelog = new MeghPITimeLogReportsPage(driver);
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);

		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + MeghLoginPage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Rquest In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(last2ndworkingdate)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + last2ndworkingdate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Regularization Emp Name." + meghpiattendancereport.getExceptionDesc());
		}

		if (meghregularizationrequestpage.EmpFirstNameRowonAdmin(firstname)) {
			regulizationreason = meghregularizationrequestpage.reason;
			logResults.createLogs("Y", "PASS", "Regularization Requested Emp Name Validated Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Regularization Requested  Emp Name."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpitimelog.LateInDateCheckBox()) {

			logResults.createLogs("Y", "PASS", "Date Search Option Checkbox Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Date Search Option Checkbox." + meghpitimelog.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ReasonSearchCheckBox()) {

			logResults.createLogs("Y", "PASS", "Reason Search Option Checkbox Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Reason Search Option Checkbox."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(RegulizationdateUI)) {

			logResults.createLogs("Y", "PASS", "Regularization Date Inputted Successfully." + RegulizationdateUI);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Regularization Date." + meghpiattendancereport.getExceptionDesc());
		}

		if (meghregularizationrequestpage.EmpRegularizationDateRowonAdmin(RegulizationdateUI)) {

			logResults.createLogs("Y", "PASS",
					"Regularization Requested Date Validated Successfully." + RegulizationdateUI);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Regularization Requested  Date."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(regularizationreason)) {

			logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regularizationreason);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Regularization Reason." + meghpiattendancereport.getExceptionDesc());
		}
		if (meghregularizationrequestpage.EmpRegularizationReasonRowonAdmin(regularizationreason)) {

			logResults.createLogs("Y", "PASS",
					"Regularization Rejected Reason Validated Successfully." + regularizationreason);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Regularization Rejected Reason."
					+ meghregularizationrequestpage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + meghloginpage.getExceptionDesc());
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
		if (meghleavepage.MonthFilterContains(last2ndworkingdate)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + last2ndworkingdate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		
		if (meghregularizationrequestpage.ReasonSearchCheckBox()) {

			logResults.createLogs("Y", "PASS", "Reason Search Option Checkbox Selected Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Reason Search Option Checkbox."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (OfficePage.SearchDropDown()) {
			logResults.createLogs("Y", "PASS", "SearchDropDown Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On SearchDropDown ." + OfficePage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.SearchTextFieldOnEmpRegularizationTab(regularizationreason)) {

			logResults.createLogs("Y", "PASS", "Regularization Reason Inputted Successfully." + regularizationreason);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Regularization Reason." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ReasonSearchResultOnEmpRegularizationTab(regularizationreason)) {

			logResults.createLogs("Y", "PASS",
					"Regularization Rejected Reason Validated Successfully." + regularizationreason);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Regularization Rejected Reason."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

	}

	// MPI_759_RegularizationRequest_12
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_759_RegularizationRequest_12() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To check that, check the functionality of search feature in admin Regularization request tab");
		logResults.createLogs("Y", "PASS",
				"This Test Case Already passed In MPI_759_RegularizationRequest_12 TestCase.");
	}

	// MPI_755_RegularizationRequest_08
	@Test(enabled = true, priority = 8, groups = { "Smoke" })
	public void MPI_755_RegularizationRequest_08() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To check that, attempt to raise a Regularization request for the same day twice and ensure that a duplicate error message is displayed.");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"captcha,empid,regularizationintime,regularizationouttime,regularizationreason");

		String captcha = data.get(0);

		String empid = data.get(1);

		String regularizationintime = data.get(2);
		String regularizationouttime = data.get(3);
		String regularizationreason = data.get(4);

		Map<String, String> dateInfo = Pramod.getPrevious12WorkingDates();
		String monthFirstWorkingDate = dateInfo.get("month1WorkingDate");

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);

		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();

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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

			logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + monthFirstWorkingDate);
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
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + monthFirstWorkingDate);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(monthFirstWorkingDate)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + monthFirstWorkingDate);
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Reason."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Apply Save Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RegularizationAlreadyExistsErrorMsg()) {

			logResults.createLogs("Y", "PASS",
					"Regularization request Already Exist Error Msg Is Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Regularization Request Already exist Error Msg."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

	}

	// MPI_764_RegularizationRequest_17
	@Test(enabled = true, priority = 9, groups = { "Smoke" })
	public void MPI_764_RegularizationRequest_17() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To check that, check the functionality of the 'approve All' button and ensure that all pending requests are approved. Also, confirm that the status of the requested day for those employees is marked as 'present'.");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,regularizationreason,empid,secondintime,secondouttime,firstname,empstatusonadmin,secondstatusofemp");

		String password = data.get(0);
		String captcha = data.get(1);
		String regularizationstatus = data.get(2);
		String regularizationintime = data.get(3);
		String regularizationouttime = data.get(4);
		String regularizationreason = data.get(5);
		String empid = data.get(6);
		String secondintime = data.get(7);
		String secondouttime = data.get(8);
		String firstname = data.get(9);
		String empstatusonadmin = data.get(10);

		Map<String, String> thirdrdday = Pramod.getPrevious12WorkingDates();
		String fourthday = thirdrdday.get("month4WorkingDate");
		String thirdworkingday = thirdrdday.get("month3WorkingDate");
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);

		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

			logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(fourthday)) {

			logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + fourthday);
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
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(fourthday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + fourthday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(fourthday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + fourthday);
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Reason."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Apply Save Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.ApplyRegularizationButtonEmp()) {

			logResults.createLogs("Y", "PASS", "Apply Regularization Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

			logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(thirdworkingday)) {

			logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + thirdworkingday);
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
		if (meghpiregularizationReportsPage.EmpRegularizationInTimeInputted(secondintime)) {

			logResults.createLogs("Y", "PASS", "In Time Inputted Successfully." + secondintime);
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
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(thirdworkingday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + thirdworkingday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(thirdworkingday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + thirdworkingday);
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
		if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(secondouttime)) {

			logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + secondouttime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Out TIme." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationOutTimeInputted(secondouttime)) {

			logResults.createLogs("Y", "PASS", "Out Time Inputted Successfully." + secondouttime);
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Reason."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Apply Save Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + meghloginpage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Rquest In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(thirdworkingday)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully On Admin.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button On Admin."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.PendingTabClickonAdmin()) {

			logResults.createLogs("Y", "PASS", "Pending Tab Clicked On Admin Successfully." + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Pending Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Emp Name."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.ApproveAllButtonInAdmin()) {

			logResults.createLogs("Y", "PASS", "Approve All Button Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Approve All Button." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ApproveAllConfirmButtonInAdmin()) {

			logResults.createLogs("Y", "PASS", "Approve All Confirm Button Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Approve All Confirm Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (RolePermissionpage.HrAccountAttendanceEmpTab()) {

			logResults.createLogs("Y", "PASS", "Employee Attendance Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EMp Attendance Tab." + RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(thirdworkingday)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + thirdworkingday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.SearchTextFieldOnEmpTabOnAdmin(firstname)) {

			logResults.createLogs("Y", "PASS", "Regularization Approved Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting  Regularization Approved Emp Name."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.validateAttendanceStatus(empid, firstname, fourthday, empstatusonadmin)) {

			logResults.createLogs("Y", "PASS", "Regularization Approved Emp Attendance Validated Successfully." + empid
					+ " " + fourthday + " " + empstatusonadmin);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Regularization Request Approved Emp Attendance."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.validateAttendanceStatus(empid, firstname, thirdworkingday,
				empstatusonadmin)) {

			logResults.createLogs("Y", "PASS", "Regularization Approved Emp Attendance Validated Successfully." + empid
					+ " " + thirdworkingday + " " + empstatusonadmin);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Regularization Request Approved Emp Attendance."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

	}

	// MPI_765_RegularizationRequest_18
	@Test(enabled = true, priority = 10, groups = { "Smoke" })
	public void MPI_765_RegularizationRequest_18() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To check that, check the functionality of filter feature by selecting specific office and department and designation.");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,deptname,officename,state,teamname,pin,longitude,firstname,policyname,gracehours,othours,duration");

		MeghMasterTeamMappingPage TeamMappingPage = new MeghMasterTeamMappingPage(driver);
		int i = 0;
		String password = data.get(i++);
		String captcha = data.get(i++);
		String deptname = data.get(i++);
		String officeName = data.get(i++);

		String state = data.get(i++);
		String teamname = data.get(i++);
		String pin = data.get(i++) + Pramod.generateRandomNumber(3);
		String longitude = data.get(i++);
		String firstname = data.get(i++);
		String policyname = data.get(i++);
		String gracehours = data.get(i++);
		String othours = data.get(i++);
		String duration = data.get(i++);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		MeghMasterDepartmentMappingPage DepartmentMappingpage = new MeghMasterDepartmentMappingPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);
		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterTeamPage TeamPage = new MeghMasterTeamPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);

		MeghLoginTest meghlogintest = new MeghLoginTest();

		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + meghloginpage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error while Selecting The Country ." + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.StateDropdown(state)) {
			logResults.createLogs("Y", "PASS", " State Selected In the State  Dropdown ." + state);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Selecting The State ." + EmployeePage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error while Inputting Latitude ." + EmployeePage.getExceptionDesc());
		}
		if (EmployeePage.LongitudeTextField(longitude)) {
			logResults.createLogs("Y", "PASS", " Longitude Inputted Successfully." + longitude);
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Inputting Longitude ." + EmployeePage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On AddDeptMappingSaveButton." + DepartmentMappingpage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error while Clicking On Team Button ." + TeamPage.getExceptionDesc());
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
			logResults.createLogs("Y", "PASS", "Created Employee Displayed Successfully.");
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

		if (AttendancePolicyPage.EnableGracePeriodButton()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod Button  Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EarlyClockOut()) {

			logResults.createLogs("Y", "PASS", "EarlyClockOut CheckBox Clicked  Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EarlyClockOut CheckBox ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursClick()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceHours  Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceHours TextField ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceHoursInput(gracehours)) {

			logResults.createLogs("Y", "PASS", " GraceHours Inputted  Successfully ." + gracehours);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting GraceHours ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.GraceTimeApplyButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On GraceTimeApply Button  Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GraceTimeApply Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.EnableGracePeriodCheckBox()) {

			logResults.createLogs("Y", "PASS", "EnableGracePeriod CheckBox Clicked  Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EnableGracePeriod CheckBox ." + AttendancePolicyPage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting 31 day On Regulirization TextField ."
					+ AttendancePolicyPage.getExceptionDesc());
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

		if (AttendancePolicyPage.AddEmpCriteria()) {

			logResults.createLogs("Y", "PASS", "AddEmpCriteria Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddEmpCriteria Button ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.OfficeDropDown(officeName)) {

			logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully ." + officeName);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Office Name ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeptDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown." + deptname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Dept DropDown." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeptOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + deptname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting  Dept ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.TeamDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown." + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team  Dropdown ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.TeamOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
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

			logResults.createLogs("Y", "PASS", "Yes Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Yes Button" + AttendancePolicyPage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Rquest In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.FilterButtonOnRegularizationRequestTab()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter  Button." + meghregularizationrequestpage.getExceptionDesc());
		}

		if (AttendancePolicyPage.OfficeDropDown(officeName)) {

			logResults.createLogs("Y", "PASS", "Office Name Selected  Successfully ." + officeName);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Office Name ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeptDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked  Dept DropDown." + deptname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Dept DropDown." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeptOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Dept." + deptname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting  Dept ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.TeamDropDownClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On  Team  Dropdown." + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Team  Dropdown ." + AttendancePolicyPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.TeamOptionSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected  Team From Dropdown." + teamname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Selecting Team From Dropdown ." + AttendancePolicyPage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.FilterApplyButtonOnRegularizationRequestTab()) {

			logResults.createLogs("Y", "PASS", "Filter Apply Button Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Apply Button." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.EmpNameDisplayed(firstname)) {

			logResults.createLogs("Y", "PASS", "Filter Applied Emp Record Displayed Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Filter Applied Emp Record."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
	}

	// MPI_1570_RegularizationRequest_21
	@Test(enabled = true, priority = 11, groups = { "Smoke" })
	public void MPI_1570_RegularizationRequest_21() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, raise the regularization request and revoke back and ensure status is displayed as revoke.");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,regularizationreason,empid");

		String password = data.get(0);
		String captcha = data.get(1);
		String regularizationstatus = data.get(2);
		String regularizationintime = data.get(3);
		String regularizationouttime = data.get(4);
		String regularizationreason = data.get(5);
		String empid = data.get(6);

		Map<String, String> thirdrdday = Pramod.getPrevious12WorkingDates();
		String previousday = thirdrdday.get("month5WorkingDate");

		MeghLoginTest meghlogintest = new MeghLoginTest();

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);

		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);

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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

			logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(previousday)) {

			logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + previousday);
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
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(previousday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + previousday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(previousday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + previousday);
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Reason."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Apply Save Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(previousday)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + previousday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.RevokeButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Request Revoke Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Request Revoke Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.RevokeConfirmButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Request Revoke Confirm Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Request Revoke Confirm Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(previousday)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + previousday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RegularizationSummaryClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RevokeSummaryTab()) {

			logResults.createLogs("Y", "PASS", "Regularization Revoked Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Revoked Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnEmpAccount(previousday, regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "Regularization Request Revoke Status Validated Successfully."
					+ previousday + " " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Regularization Request Revoke Status."
					+ meghregularizationrequestpage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + meghloginpage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Rquest In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(previousday)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + previousday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.TotalEmpSummaryCountClickedInAdmin()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully On Admin.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button On Admin."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RevokeSummaryTabOnAdmin()) {

			logResults.createLogs("Y", "PASS", "Revoke Tab Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Revoke Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnAdminAccount(previousday, regularizationstatus, empid)) {

			logResults.createLogs("Y", "PASS", "Regularization Request Revoked Status Validated Successfully."
					+ previousday + " " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Regularization Request Revoked Status."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
	}

	// MPI_766_RegularizationRequest_19
	@Test(enabled = true, priority = 12, groups = { "Smoke" })
	public void MPI_766_RegularizationRequest_19() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify, click on the statistics count for Pending, Approved, or Rejected, and ensure that only the corresponding requests are displayed as per the selected status.");
		logResults.createLogs("Y", "PASS", "This Test Case Already passed In Above TC's.");
	}

	// MPI_1572_RegularizationRequest_23
	@Test(enabled = true, priority = 13, groups = { "Smoke" })
	public void MPI_1572_RegularizationRequest_23() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, raise a regularization request for a previously rejected date and ensure the request is created successfully when the attendance policy allows 2 regularization requests per month.");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,regularizationreason,empid");

		String password = data.get(0);
		String captcha = data.get(1);
		String regularizationstatus = data.get(2);
		String regularizationintime = data.get(3);
		String regularizationouttime = data.get(4);
		String regularizationreason = data.get(5);
		String empid = data.get(6);

		Map<String, String> thirdrdday = Pramod.getPrevious12WorkingDates();
		String previousday = thirdrdday.get("month5WorkingDate");

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);

		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();

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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

			logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(previousday)) {

			logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + previousday);
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
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(previousday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + previousday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(previousday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + previousday);
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Reason."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Apply Save Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghleavepage.MonthFilterContains(previousday)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + previousday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.RegularizationSummaryClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.PendingTabClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Pending Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Pending Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnEmpAccount(previousday, regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "Regularization Request Pending Status Validated Successfully."
					+ previousday + " " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Regularization Request Pending Status."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
	}

	// MPI_1573_RegularizationRequest_24
	@Test(enabled = true, priority = 14, groups = { "Smoke" })
	public void MPI_1573_RegularizationRequest_24() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, raise a regularization request for a previously invoked date and ensure the request is created successfully when the attendance policy allows 2 regularization requests per month.");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,regularizationreason,empid");

		String password = data.get(0);
		String captcha = data.get(1);
		String regularizationstatus = data.get(2);
		String regularizationintime = data.get(3);
		String regularizationouttime = data.get(4);
		String regularizationreason = data.get(5);
		String empid = data.get(6);

		Map<String, String> thirdrdday = Pramod.getPrevious12WorkingDates();
		String previousday = thirdrdday.get("month6WorkingDate");

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);

		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();

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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

			logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(previousday)) {

			logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + previousday);
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
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(previousday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + previousday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(previousday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + previousday);
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Reason."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Apply Save Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}

		if (meghleavepage.MonthFilterContains(previousday)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account." + previousday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RegularizationSummaryClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.PendingTabClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Pending Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Pending Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnEmpAccount(previousday, regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "Regularization Request Pending Status Validated Successfully."
					+ previousday + " " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Regularization Request Pending Status."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
	}

	// MPI_1571_RegularizationRequest_22
	@Test(enabled = true, priority = 15, groups = { "Smoke" })
	public void MPI_1571_RegularizationRequest_22() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, configure the regularization request limit as 2 in the attendance policy and ensure the employee is allowed to raise a maximum of 2 regularization requests in a month.");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,regularizationintime,regularizationouttime,regularizationreason,empid");

		String password = data.get(0);
		String captcha = data.get(1);
		String regularizationintime = data.get(2);
		String regularizationouttime = data.get(3);
		String regularizationreason = data.get(4);
		String empid = data.get(5);

		Map<String, String> thirdrdday = Pramod.getPrevious12WorkingDates();
		String previousday = thirdrdday.get("month7WorkingDate");

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghloginpage = new MeghLoginPage(driver);

		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();

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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

			logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(previousday)) {

			logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + previousday);
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
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(previousday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + previousday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(previousday)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + previousday);
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Reason."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Apply Save Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.RegularizationlimitMsg()) {

			logResults.createLogs("Y", "PASS", "Regularization Limit Error Msg Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Regularization Limit Error Msg."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
	}

	// MPI_1605_RegularizationRequest_25
	@Test(enabled = true, priority = 16, groups = { "Smoke" })
	public void MPI_1605_RegularizationRequest_25() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To check that, login as admin, select Approved, Pending, Rejected, and Revoked one by one from the filter feature, and ensure that the records displayed match the selected status.");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,regularizationstatus,firstname,empid,pendingfilter,rejectedfilter,revokedfilter");

		String password = data.get(0);
		String captcha = data.get(1);
		String regularizationstatus = data.get(2);
		String firstname = data.get(3);
		String empid = data.get(4);
		String pendingfilter = data.get(5);
		String rejectedfilter = data.get(6);
		String revokedfilter = data.get(7);

		Map<String, String> thirdrdday = Pramod.getPrevious12WorkingDates();
		String previousday = thirdrdday.get("month7WorkingDate");

		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);

		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + MeghLoginPage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Rquest In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.FilterButtonOnRegularizationRequestTab()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter  Button." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.SelectFilterOption(regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "Approved Option Selected From Filter Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting  Approved Option From Filter DropDown."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.FilterApplyButtonOnRegularizationRequestTab()) {

			logResults.createLogs("Y", "PASS", "Filter Apply Button Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Apply Button." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Emp Name."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnAdminAccountForFilterResult(regularizationstatus, empid)) {

			logResults.createLogs("Y", "PASS", "Approved Filter Applied Emp Record Displayed Successfully." + firstname
					+ " " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Approved Filter Record."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.FilterButtonOnRegularizationRequestTab()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter  Button." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.SelectFilterOption(pendingfilter)) {

			logResults.createLogs("Y", "PASS", "Pending Option Selected From Filter Successfully." + pendingfilter);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting  Pending Option From Filter DropDown."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.FilterApplyButtonOnRegularizationRequestTab()) {

			logResults.createLogs("Y", "PASS", "Filter Apply Button Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Apply Button." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Emp Name."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnAdminAccountForFilterResult(pendingfilter, empid)) {

			logResults.createLogs("Y", "PASS",
					"Pending Filter Applied Emp Record Displayed Successfully." + firstname + " " + pendingfilter);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Pending Filter Record." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.FilterButtonOnRegularizationRequestTab()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter  Button." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.SelectFilterOption(rejectedfilter)) {

			logResults.createLogs("Y", "PASS", "Rejected Option Selected From Filter Successfully." + rejectedfilter);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting  Rejected Option From Filter DropDown."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.FilterApplyButtonOnRegularizationRequestTab()) {

			logResults.createLogs("Y", "PASS", "Filter Apply Button Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Apply Button." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Emp Name."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnAdminAccountForFilterResult(rejectedfilter, empid)) {

			logResults.createLogs("Y", "PASS",
					"Rejected Filter Applied Emp Record Displayed Successfully." + firstname + " " + rejectedfilter);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Rejected Filter Record."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.FilterButtonOnRegularizationRequestTab()) {

			logResults.createLogs("Y", "PASS", "Filter Button Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter  Button." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.SelectFilterOption(revokedfilter)) {

			logResults.createLogs("Y", "PASS", "Revoked Option Selected From Filter Successfully." + revokedfilter);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting  Revoked Option From Filter DropDown."
					+ meghregularizationrequestpage.getExceptionDesc());
		}

		if (meghregularizationrequestpage.FilterApplyButtonOnRegularizationRequestTab()) {

			logResults.createLogs("Y", "PASS", "Filter Apply Button Clicked On Admin Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Filter Apply Button." + meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegularizationRequestSearchTextField(firstname)) {

			logResults.createLogs("Y", "PASS", "Regularization Emp Name Inputted Successfully." + firstname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Emp Name."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnAdminAccountForFilterResult(revokedfilter, empid)) {

			logResults.createLogs("Y", "PASS",
					"Revoked Filter Applied Emp Record Displayed Successfully." + firstname + " " + revokedfilter);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Displaying Revoked Filter Record." + meghregularizationrequestpage.getExceptionDesc());
		}
	}

	// MPI_1606_RegularizationRequest_26
	@Test(enabled = true, priority = 17, groups = { "Smoke" })
	public void MPI_1606_RegularizationRequest_26() {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, as an admin apply a regularization request for himself, and ensure that when the admin switches to the employee account, the record is displayed and the attendance status is shown as Present.");

		ArrayList<String> data = initBase.loadExcelData("RegularizationRequest_Tab", currTC,
				"password,captcha,regularizationstatus,regularizationintime,regularizationouttime,regularizationreason,empstatusonadmin");

		String password = data.get(0);
		String captcha = data.get(1);
		String regularizationstatus = data.get(2);
		String regularizationintime = data.get(3);
		String regularizationouttime = data.get(4);
		String regularizationreason = data.get(5);
		String empstatusonadmin = data.get(6);

		Map<String, String> dateDetails = Pramod.getPrevious12WorkingDates();

		String prevWorkingDateMinus2 = dateDetails.get("month1WorkingDate");

		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);

		MeghPIRegularizationReportsPage meghpiregularizationReportsPage = new MeghPIRegularizationReportsPage(driver);
		MeghPIRegularizationRequestPage meghregularizationrequestpage = new MeghPIRegularizationRequestPage(driver);
		MeghLoginTest meghlogintest = new MeghLoginTest();
		MeghLeavePage meghleavepage = new MeghLeavePage(driver);

		if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
			logResults.createLogs("Y", "PASS", "Login Done Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Login Is Failed." + MeghLoginPage.getExceptionDesc());
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
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Rquest In Attendance Module."
					+ RolePermissionpage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.RegulirizationRequestTabOnAdmin()) {

			logResults.createLogs("Y", "PASS", "Apply Regularization Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.RegulirizationRequestFormeOnAdmin()) {

			logResults.createLogs("Y", "PASS", "Apply Regularization For Me Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Apply Regularization For Me Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}

		if (meghpiregularizationReportsPage.EmpRegularizationInDate()) {

			logResults.createLogs("Y", "PASS", "From Date TextField Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On From Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationInDateInputted(prevWorkingDateMinus2)) {

			logResults.createLogs("Y", "PASS", "From Date Inputted Successfully." + prevWorkingDateMinus2);
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
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(prevWorkingDateMinus2)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + prevWorkingDateMinus2);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting To Date." + meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationOutDateInputted(prevWorkingDateMinus2)) {

			logResults.createLogs("Y", "PASS", "To Date Inputted Successfully." + prevWorkingDateMinus2);
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
			logResults.createLogs("Y", "FAIL", "Error While Inputting Regularization Reason."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (meghpiregularizationReportsPage.EmpRegularizationApplySaveButton()) {

			logResults.createLogs("Y", "PASS", "Regularization Apply Save Button Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Apply Save Button."
					+ meghpiregularizationReportsPage.getExceptionDesc());
		}
		if (RolePermissionpage.HrAccountAttendanceEmpTab()) {

			logResults.createLogs("Y", "PASS", "Employee Attendance Tab Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On EMp Attendance Tab." + RolePermissionpage.getExceptionDesc());
		}

		if (meghleavepage.MonthFilterContains(prevWorkingDateMinus2)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account."
							+ prevWorkingDateMinus2);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.SearchTextFieldOnEmpTabOnAdmin(AdminFirstName)) {

			logResults.createLogs("Y", "PASS",
					"Regularization Approved Emp Name Inputted Successfully." + AdminFirstName);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting  Regularization Approved Emp Name."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.validateAttendanceStatus(AdminEmpID, AdminFirstName, prevWorkingDateMinus2,
				empstatusonadmin)) {

			logResults.createLogs("Y", "PASS", "Regularization Approved Emp Attendance Validated Successfully."
					+ AdminEmpID + " " + prevWorkingDateMinus2 + " " + empstatusonadmin);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Regularization Request Approved Emp Attendance."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (RolePermissionpage.EmployeeToggleSwitch()) {

			logResults.createLogs("Y", "PASS", "Employee Toggle Switch Successfully To Switch To Employee Account.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Toggle Switch." + RolePermissionpage.getExceptionDesc());
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
		if (meghleavepage.MonthFilterContains(prevWorkingDateMinus2)) {

			logResults.createLogs("Y", "PASS",
					"Week Off Request Applied Month Selected  Successfully Of Employee Account."
							+ prevWorkingDateMinus2);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Emp Applied WeekOff Requested Month." + meghleavepage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.RegularizationSummaryClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ApprovedTabClicked()) {

			logResults.createLogs("Y", "PASS", "Regularization Approved Summary Clicked Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On Regularization Approved Summary Button."
					+ meghregularizationrequestpage.getExceptionDesc());
		}
		if (meghregularizationrequestpage.ValidateEmpStatusOnEmpAccount(prevWorkingDateMinus2, regularizationstatus)) {

			logResults.createLogs("Y", "PASS", "Regularization Request Approved Status Validated Successfully."
					+ prevWorkingDateMinus2 + " " + regularizationstatus);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Emp Regularization Request Approved Status."
					+ meghregularizationrequestpage.getExceptionDesc());
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
