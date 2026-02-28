package com.MeghPI.Attendance.tests;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import com.MeghPI.Attendance.pages.MeghAttendancePolicyPage;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;

import utils.Pramod;
import utils.Utils;

public class MeghShiftTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	
	private String shiftname = "";
	private String shiftnames = "";

	private String cmpcode = "";
	private String Emailid = "";

	
	
	
	@Parameters({ "device", "hubnodeip" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device, @Optional String hubnodeip) { // String param1
		initBase.browser = "chrome";
		//driver = loadDriver.getDriver(device, hubnodeip);

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
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		
		
	}

	// MPI_162_MeghPi_Shift_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_162_MeghPi_Shift_01()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create an 'A shift' by selecting 6 to 2 timings and check whether the created shift is displayed in the shift policy module");

		ArrayList<String> data = initBase.loadExcelData("shift", currTC,
				"shiftname,shiftcode,starttime,endtime,buffertime,fullday,halfday,breakname,breakstarttime,breakendtime,breakbuffertime");

	
		
		shiftname = data.get(0) + Pramod.generateRandomAlpha(7);
		String shiftcode = data.get(1) + Pramod.generateRandomNumber(3);
		String starttime = data.get(2);
		String endtime = data.get(3);
		String buffertime = data.get(4);
		String fullday = data.get(5);
		String halfday = data.get(6);
		String breakname = data.get(7) + Pramod.generateRandomAlpha(5);
		String breakstarttime = data.get(8);
		String breakendtime = data.get(9);
		String breakbuffertime = data.get(10);

		

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
		


		if (MeghLoginPage.MainLandingPage()) {
			logResults.createLogs("Y", "PASS", "Dashboard Page Displayed Successfully.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Dashboard Page Not Displayed." + MeghLoginPage.getExceptionDesc());
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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPage.ShiftButton()) {

			logResults.createLogs("Y", "PASS", "ShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On ShiftButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftPageLoaded()) {

			logResults.createLogs("Y", "PASS", "Shift Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Shift Page Isn't Loaded Completely." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.AddShiftButton()) {

			logResults.createLogs("Y", "PASS", "CreateShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateShiftButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftNameTextField(shiftname)) {

			logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully ." + shiftname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Name." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftCodeTextField(shiftcode)) {

			logResults.createLogs("Y", "PASS", "Shift Code Inputted Successfully ." + shiftcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Code." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftStartTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.ShiftStartTime(starttime)) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Inputted Successfully ." + starttime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftEndTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Inputted Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftEndTime(endtime)) {

			logResults.createLogs("Y", "PASS", "Shift End Time Inputted Successfully ." + endtime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift End Time." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.BufferAllTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Buffer Time Inputted Successfully ." + buffertime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Buffer Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.BufferAllTime(buffertime)) {

			logResults.createLogs("Y", "PASS", "Shift Buffer Time Inputted Successfully ." + buffertime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Buffer Time." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.FullDayTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Full Day Time Inputted Successfully ." + fullday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Full Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.FullDayTime(fullday)) {

			logResults.createLogs("Y", "PASS", "Shift Full Day Time Inputted Successfully ." + fullday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Full Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		

		if (ShiftPolicyPage.HalfDayTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Half Day Time Inputted Successfully ." + halfday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Half Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.HalfDayTime(halfday)) {

			logResults.createLogs("Y", "PASS", "Shift Half Day Time Inputted Successfully ." + halfday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Half Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPage.AddBreakButton()) {

			logResults.createLogs("Y", "PASS", "AddBreakButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddBreakButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakNameTextField(breakname)) {

			logResults.createLogs("Y", "PASS", "Break Name Inputted Successfully ." + breakname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Break Name." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakStartTimeClick()) {

			logResults.createLogs("Y", "PASS", "BreakStartTime Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On BreakStartTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakStartTime(breakstarttime)) {

			logResults.createLogs("Y", "PASS", "BreakStartTime Inputted Successfully ." + breakstarttime);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting BreakStartTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakEndTimeClick()) {

			logResults.createLogs("Y", "PASS", "BreakEndTime Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On BreakEndTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakEndTime(breakendtime)) {

			logResults.createLogs("Y", "PASS", "BreakEndTime Clicked Successfully ." + breakendtime);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On BreakEndTime." + ShiftPage.getExceptionDesc());
		}
		
		if (ShiftPage.BreakNameTextField(breakname)) {

			logResults.createLogs("Y", "PASS", "Break Name Inputted Successfully ." + breakname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Break Name." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakBufferTimeClick()) {

			logResults.createLogs("Y", "PASS", "BreakBufferTime Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On BreakBufferTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakBufferTime(breakbuffertime)) {

			logResults.createLogs("Y", "PASS", "BreakBufferTime Inputted Successfully ." + breakbuffertime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On BreakBufferTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.CreateShiftSaveButton()) {

			logResults.createLogs("Y", "PASS", "Create Shift Save Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateShift Save Button." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPage.ShiftPolicyTab()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyTab Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyTab." + ShiftPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.AddShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPage.AllShiftNames(shiftname)) {

			logResults.createLogs("Y", "PASS",
					"Shift Name Validated And Present In The Shift Policy 'add shift' Successfully ." + shiftname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Created Shift Name ." + ShiftPage.getExceptionDesc());
		}

	}

	// MPI_537_MeghPi_Shift_13
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
	public void MPI_537_MeghPi_Shift_13()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, edit the shift and ensure that the updated shift name, timings, and other changes are saved and reflected correctly.");

		ArrayList<String> data = initBase.loadExcelData("shift", currTC,
				"shiftname,starttime,endtime");

		
		shiftnames = data.get(0) + Pramod.generateRandomAlpha(7);
		
		String starttime = data.get(1);
		String endtime = data.get(2);
		

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		

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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPage.ShiftButton()) {

			logResults.createLogs("Y", "PASS", "ShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On ShiftButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftPageLoaded()) {

			logResults.createLogs("Y", "PASS", "Shift Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Shift Page Isn't Loaded Completely." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftSearchTextField(shiftname)) {

			logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully ." + shiftname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Shift Name." + ShiftPage.getExceptionDesc());
		}

	

		if (ShiftPage.ShiftEditButton()) {

			logResults.createLogs("Y", "PASS", "ShiftEditButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftEditButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftNameTextField(shiftnames)) {

			logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully ." + shiftnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Name." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftStartTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.ShiftStartTime(starttime)) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Inputted Successfully ." + starttime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftEndTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Inputted Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftEndTime(endtime)) {

			logResults.createLogs("Y", "PASS", "Shift End Time Inputted Successfully ." + endtime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift End Time." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.CreateShiftSaveButton()) {

			logResults.createLogs("Y", "PASS", "Create Shift Save Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateShift Save Button." + ShiftPolicyPage.getExceptionDesc());
		}
		

		if (ShiftPage.ShiftSearchTextField(shiftnames)) {

			logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully ." + shiftnames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Shift Name." + ShiftPage.getExceptionDesc());
		}

		

		if (ShiftPage.ShiftFirstRowName(shiftnames)) {

			logResults.createLogs("Y", "PASS", "Updated Shift Name Validated Successfully ." + shiftnames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Shift Name." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftFirstRowStartTime(starttime)) {

			logResults.createLogs("Y", "PASS", "Updated Shift Start Time Validated Successfully ." + starttime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Shift Start Time." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftFirstRowEndTime(endtime)) {

			logResults.createLogs("Y", "PASS", "Updated Shift End Time Validated Successfully ." + endtime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Shift End Time." + ShiftPage.getExceptionDesc());
		}

	}

	// MPI_538_MeghPi_Shift_14
	@Test(enabled = true, priority = 3, groups = { "Smoke" })
	public void MPI_538_MeghPi_Shift_14()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, create a shift without assigning it to any shift policy, then delete it, and ensure that the deleted shift is not displayed in the shift policy.");


	

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		

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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPage.ShiftButton()) {

			logResults.createLogs("Y", "PASS", "ShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On ShiftButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftPageLoaded()) {

			logResults.createLogs("Y", "PASS", "Shift Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Shift Page Isn't Loaded Completely." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftSearchTextField(shiftnames)) {

			logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully ." + shiftnames);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Shift Name." + ShiftPage.getExceptionDesc());
		}


		if (ShiftPage.ShiftDeleteIcon()) {

			logResults.createLogs("Y", "PASS", "ShiftDeleteIcon Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDeleteIcon." + ShiftPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeleteButton()) {

			logResults.createLogs("Y", "PASS", "DeleteButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteButton." + AttendancePolicyPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftPolicyTab()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyTab Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyTab." + ShiftPage.getExceptionDesc());
		}
	

		if (ShiftPolicyPage.AddShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftNotFound(shiftnames)) {

			logResults.createLogs("Y", "PASS",
					"Shift Name Validated And Not Present In The Shift Policy 'add shift' Module ." + shiftnames);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Disabled Shift Name ." + ShiftPage.getExceptionDesc());
		}

	}

	// MPI_540_MeghPi_Shift_16
	@Test(enabled = true, priority = 4, groups = { "Smoke" })
	public void MPI_540_MeghPi_Shift_16()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, assign the created shift to any shift policy, and ensure that when attempting to delete the shift after assignment, an appropriate error message is displayed.");

		ArrayList<String> data = initBase.loadExcelData("shift", currTC,
				"shiftname,shiftcode,starttime,endtime,buffertime,fullday,halfday,breakname,breakstarttime,breakendtime,breakbuffertime,policyname");

	
		
		String shiftnamess = data.get(0) + Pramod.generateRandomAlpha(7);
		String shiftcode = data.get(1) + Pramod.generateRandomNumber(3);
		String starttime = data.get(2);
		String endtime = data.get(3);
		String buffertime = data.get(4);
		String fullday = data.get(5);
		String halfday = data.get(6);
		String breakname = data.get(7) + Pramod.generateRandomAlpha(5);
		String breakstarttime = data.get(8);
		String breakendtime = data.get(9);
		String breakbuffertime = data.get(10);
		String policyname = data.get(11) + Pramod.generateRandomAlpha(5);

		

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghAttendancePolicyPage AttendancePolicyPage = new MeghAttendancePolicyPage(driver);
		
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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPage.ShiftButton()) {

			logResults.createLogs("Y", "PASS", "ShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On ShiftButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftPageLoaded()) {

			logResults.createLogs("Y", "PASS", "Shift Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Shift Page Isn't Loaded Completely." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.AddShiftButton()) {

			logResults.createLogs("Y", "PASS", "CreateShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateShiftButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftNameTextField(shiftnamess)) {

			logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully ." + shiftnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Name." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftCodeTextField(shiftcode)) {

			logResults.createLogs("Y", "PASS", "Shift Code Inputted Successfully ." + shiftcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Code." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftStartTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}
	

		if (ShiftPolicyPage.ShiftStartTime(starttime)) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Inputted Successfully ." + starttime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftEndTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Start Time Inputted Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Start Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.ShiftEndTime(endtime)) {

			logResults.createLogs("Y", "PASS", "Shift End Time Inputted Successfully ." + endtime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift End Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.BufferAllTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Buffer Time Inputted Successfully ." + buffertime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Buffer Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.BufferAllTime(buffertime)) {

			logResults.createLogs("Y", "PASS", "Shift Buffer Time Inputted Successfully ." + buffertime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Buffer Time." + ShiftPolicyPage.getExceptionDesc());
		}
	

		if (ShiftPolicyPage.FullDayTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Full Day Time Inputted Successfully ." + fullday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Full Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.FullDayTime(fullday)) {

			logResults.createLogs("Y", "PASS", "Shift Full Day Time Inputted Successfully ." + fullday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Full Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

	

		if (ShiftPolicyPage.HalfDayTimeClick()) {

			logResults.createLogs("Y", "PASS", "Shift Half Day Time Inputted Successfully ." + halfday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Half Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.HalfDayTime(halfday)) {

			logResults.createLogs("Y", "PASS", "Shift Half Day Time Inputted Successfully ." + halfday);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting Shift Half Day Time." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPage.AddBreakButton()) {

			logResults.createLogs("Y", "PASS", "AddBreakButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddBreakButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakNameTextField(breakname)) {

			logResults.createLogs("Y", "PASS", "Break Name Inputted Successfully ." + breakname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Break Name." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakStartTimeClick()) {

			logResults.createLogs("Y", "PASS", "BreakStartTime Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On BreakStartTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakStartTime(breakstarttime)) {

			logResults.createLogs("Y", "PASS", "BreakStartTime Inputted Successfully ." + breakstarttime);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting BreakStartTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakEndTimeClick()) {

			logResults.createLogs("Y", "PASS", "BreakEndTime Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On BreakEndTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakEndTime(breakendtime)) {

			logResults.createLogs("Y", "PASS", "BreakEndTime Clicked Successfully ." + breakendtime);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On BreakEndTime." + ShiftPage.getExceptionDesc());
		}
		
		if (ShiftPage.BreakNameTextField(breakname)) {

			logResults.createLogs("Y", "PASS", "Break Name Inputted Successfully ." + breakname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Break Name." + ShiftPage.getExceptionDesc());
		}
		

		if (ShiftPage.BreakBufferTimeClick()) {

			logResults.createLogs("Y", "PASS", "BreakBufferTime Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On BreakBufferTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.BreakBufferTime(breakbuffertime)) {

			logResults.createLogs("Y", "PASS", "BreakBufferTime Clicked Successfully ." + breakbuffertime);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On BreakBufferTime." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.CreateShiftSaveButton()) {

			logResults.createLogs("Y", "PASS", "Create Shift Save Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreateShift Save Button." + ShiftPolicyPage.getExceptionDesc());
		}
	

		if (ShiftPage.ShiftPolicyTab()) {

			logResults.createLogs("Y", "PASS", "ShiftPolicyTab Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftPolicyTab." + ShiftPage.getExceptionDesc());
		}
		

		if (ShiftPolicyPage.AddShiftPolicyButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftPolicyButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftPolicyButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.PolicyNameTextField(policyname)) {

			logResults.createLogs("Y", "PASS", "PolicyName Inputted Successfully ." + policyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputting PolicyName." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPage.CreatedShiftClick(shiftnamess)) {

			logResults.createLogs("Y", "PASS", "Created Shift Selected Successfully ." + shiftnamess);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Created Shift." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.AddShiftSaveButton()) {

			logResults.createLogs("Y", "PASS", "AddShiftSaveButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddShiftSaveButton." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPolicyPage.CreatePolicySave()) {

			logResults.createLogs("Y", "PASS", "CreatePolicySave Button Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CreatePolicySave Button." + ShiftPolicyPage.getExceptionDesc());
		}

		if (ShiftPage.NoOptionSelected()) {
			logResults.createLogs("Y", "PASS", "No Button Button Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On No Button ." + ShiftPage.getExceptionDesc());
		}

		
		if (ShiftPage.ShiftTab()) {

			logResults.createLogs("Y", "PASS", "ShiftTab Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On ShiftTab." + ShiftPage.getExceptionDesc());
		}
		

		if (ShiftPage.ShiftSearchTextField(shiftnamess)) {

			logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully ." + shiftnamess);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Shift Name." + ShiftPage.getExceptionDesc());
		}


		if (ShiftPage.ShiftDeleteIcon()) {

			logResults.createLogs("Y", "PASS", "ShiftDeleteIcon Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDeleteIcon." + ShiftPage.getExceptionDesc());
		}

		if (AttendancePolicyPage.DeleteButton()) {

			logResults.createLogs("Y", "PASS", "DeleteButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteButton." + AttendancePolicyPage.getExceptionDesc());
		}

		if (ShiftPage.ErrorMsg()) {

			logResults.createLogs("Y", "PASS", "Error Message Displayed Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Displaying Error Message." + ShiftPage.getExceptionDesc());
		}

	}

	// MPI_543_MeghPi_Shift_19
	@Test(enabled = true, priority = 5, groups = { "Smoke" })
	public void MPI_543_MeghPi_Shift_19()  {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("To verify this, check the functionality of search feature by selecting shift code");

		
	
		String firstrowshiftname = "";

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		
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

		if (RolePermissionpage.ShiftDropDown()) {

			logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
		}

		if (ShiftPage.ShiftButton()) {

			logResults.createLogs("Y", "PASS", "ShiftButton Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On ShiftButton." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftPageLoaded()) {

			logResults.createLogs("Y", "PASS", "Shift Page Loaded Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Shift Page Isn't Loaded Completely." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftFirstRowNameFetch()) {
			firstrowshiftname = ShiftPage.firstrow;
			logResults.createLogs("Y", "PASS", "First Row Name Fetched Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Fetching ShiftName." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftSearchDropDwon()) {

			logResults.createLogs("Y", "PASS", "ShiftSearchDropDwon Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftSearchDropDwon." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftCodeCheckBox()) {

			logResults.createLogs("Y", "PASS", "ShiftCodeCheckBox Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftCodeCheckBox." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftSearchDropDwon()) {

			logResults.createLogs("Y", "PASS", "ShiftSearchDropDwon Clicked Successfully .");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ShiftSearchDropDwon." + ShiftPage.getExceptionDesc());
		}

		if (ShiftPage.ShiftSearchTextField(firstrowshiftname)) {

			logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully ." + firstrowshiftname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Shift Name." + ShiftPage.getExceptionDesc());
		}

	

		if (ShiftPage.ShiftCodeValidation(firstrowshiftname)) {

			logResults.createLogs("Y", "PASS", "Shift Code Validated Successfully ." + firstrowshiftname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Validating Shift Code." + ShiftPage.getExceptionDesc());
		}

	}
	
	// MPI_1171_MeghPi_Shift_31
		@Test(enabled = true, priority = 0, groups = { "Smoke", "AddMaster" })
		public void MPI_1171_MeghPi_Shift_31()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify this, edit the shift and ensure that the updated shift name, timings, and other changes are saved and reflected correctly.");

			ArrayList<String> data = initBase.loadExcelData("shift", currTC,
					"password,captcha,shiftname,breakname,breakstarttime,breakendtime,breakbuffertime");

			String password = data.get(0);
			String captcha = data.get(1);
			
			String shiftnames = data.get(2); 
			String breakname = data.get(3);
			String breakstarttime = data.get(4);
			String breakendtime = data.get(5);
			String breakbuffertime = data.get(6);

			MeghShiftPage ShiftPage = new MeghShiftPage(driver);
			MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghLoginTest meghlogintest = new MeghLoginTest();


			if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
				logResults.createLogs("Y", "PASS", "Login Done Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Login Is Failed." + MeghLoginPage.getExceptionDesc());
			}

			if (initBase.stopNewData) { // Tapan 5 July 25
				String Shift = Utils.propsReadWrite("data/addmaster.properties", "get", "Shift", "");
				if (Shift.equals("pass")) {
					logResults.createLogs("Y", "INFO", "Skip Adding New Data. Continue with Old Test Data.");
				return;
				}
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

			if (RolePermissionpage.ShiftDropDown()) {

				logResults.createLogs("Y", "PASS", "ShiftDropDown Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On ShiftDropDown." + RolePermissionpage.getExceptionDesc());
			}

			if (ShiftPage.ShiftButton()) {

				logResults.createLogs("Y", "PASS", "ShiftButton Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On ShiftButton." + ShiftPage.getExceptionDesc());
			}

			if (ShiftPage.ShiftPageLoaded()) {

				logResults.createLogs("Y", "PASS", "Shift Page Loaded Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Shift Page Isn't Loaded Completely." + ShiftPage.getExceptionDesc());
			}

			if (ShiftPage.ShiftSearchTextField(shiftnames)) {

				logResults.createLogs("Y", "PASS", "Shift Name Inputted Successfully ." + shiftnames);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Shift Name." + ShiftPage.getExceptionDesc());
			}

		

			if (ShiftPage.ShiftEditButton()) {

				logResults.createLogs("Y", "PASS", "ShiftEditButton Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On ShiftEditButton." + ShiftPage.getExceptionDesc());
			}
			if (ShiftPage.AddBreakButton()) {

				logResults.createLogs("Y", "PASS", "AddBreakButton Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddBreakButton." + ShiftPage.getExceptionDesc());
			}

			if (ShiftPage.BreakNameTextField(breakname)) {

				logResults.createLogs("Y", "PASS", "Break Name Inputted Successfully ." + breakname);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Break Name." + ShiftPage.getExceptionDesc());
			}

			if (ShiftPage.BreakStartTimeClick()) {

				logResults.createLogs("Y", "PASS", "BreakStartTime Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On BreakStartTime." + ShiftPage.getExceptionDesc());
			}

			if (ShiftPage.BreakStartTime(breakstarttime)) {

				logResults.createLogs("Y", "PASS", "BreakStartTime Inputted Successfully ." + breakstarttime);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting BreakStartTime." + ShiftPage.getExceptionDesc());
			}

			if (ShiftPage.BreakEndTimeClick()) {

				logResults.createLogs("Y", "PASS", "BreakEndTime Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On BreakEndTime." + ShiftPage.getExceptionDesc());
			}

			if (ShiftPage.BreakEndTime(breakendtime)) {

				logResults.createLogs("Y", "PASS", "BreakEndTime Clicked Successfully ." + breakendtime);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On BreakEndTime." + ShiftPage.getExceptionDesc());
			}
			
			if (ShiftPage.BreakNameTextField(breakname)) {

				logResults.createLogs("Y", "PASS", "Break Name Inputted Successfully ." + breakname);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Break Name." + ShiftPage.getExceptionDesc());
			}

			if (ShiftPage.BreakBufferTimeClick()) {

				logResults.createLogs("Y", "PASS", "BreakBufferTime Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On BreakBufferTime." + ShiftPage.getExceptionDesc());
			}

			if (ShiftPage.BreakBufferTime(breakbuffertime)) {

				logResults.createLogs("Y", "PASS", "BreakBufferTime Inputted Successfully ." + breakbuffertime);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On BreakBufferTime." + ShiftPage.getExceptionDesc());
			}
						

			if (ShiftPolicyPage.CreateShiftSaveButton()) {
				Utils.propsReadWrite("data/addmaster.properties", "set", "Shift", "pass");
				logResults.createLogs("Y", "PASS", "Create Shift Save Button Clicked Successfully .");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CreateShift Save Button." + ShiftPolicyPage.getExceptionDesc());
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
