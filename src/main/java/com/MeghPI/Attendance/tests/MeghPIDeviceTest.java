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

import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghPIDevicePage;
import com.MeghPI.Attendance.pages.MeghShiftPolicyPage;
import base.LoadDriver;
import base.LogResults;
import base.initBase;
import utils.Pramod;
import utils.Utils;

public class MeghPIDeviceTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	
	private String cmpcode = "";
	private String Emailid = "";
	private String officename = "";
	
    
	
	
	
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
		
		officename = "AutoON" + initBase.executionRunTime;
		
	}
	
	// MPI_392_Devicelist_01
		@Test(enabled = true, priority = 1, groups = { "Smoke", "AddMaster" })
		public void MPI_392_Devicelist_01()  {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"Add a device by entering theÂ device nameÂ andÂ device ID, selectÂ modeÂ asÂ Default, chooseÂ typeÂ asÂ Attendance Device, assignÂ locationÂ , and ensure theÂ added device is displayedÂ in the device list.");

			ArrayList<String> data = initBase.loadExcelData("DeviceList", currTC,
					"password,captcha,devicename,deviceid");

			
			String password = data.get(0);
			String captcha = data.get(1);
			String devicename = data.get(2) + Pramod.generateRandomAlpha(4);
			String deviceid = data.get(3) + initBase.executionRunTime;

			
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			
            MeghPIDevicePage meghpidevicepage = new MeghPIDevicePage(driver);
            MeghShiftPolicyPage ShiftPolicyPage = new MeghShiftPolicyPage(driver);
			
	MeghLoginTest meghlogintest = new MeghLoginTest();
	String DeviceName = Utils.propsReadWrite("data/addmaster.properties", "get", "DeviceName", "");
	if (initBase.stopNewData) { // Tapan 5 July 25
		if (DeviceName.equals("pass")) {
			logResults.createLogs("Y", "INFO", "Skip Adding New Data. Continue with Old Test Data.");
			return;
		}
	}
			
			if (meghlogintest.verifyCompanyLogin(cmpcode, Emailid, password, captcha, logResults, driver)) {
				logResults.createLogs("Y", "PASS", "Login Done Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Login Is Failed." + MeghLoginPage.getExceptionDesc());
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
			
			if (meghpidevicepage.DeviceSetUpButton()) {

				logResults.createLogs("Y", "PASS", "DeviceSetUpButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On DeviceSetUpButton." + meghpidevicepage.getExceptionDesc());
			}
			if (meghpidevicepage.DeviceListButton()) {

				logResults.createLogs("Y", "PASS", "DeviceListButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On DeviceListButton." + meghpidevicepage.getExceptionDesc());
			}
			if (meghpidevicepage.AddDeviceButton()) {

				logResults.createLogs("Y", "PASS", "AddDeviceButton Clicked Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On AddDeviceButton." + meghpidevicepage.getExceptionDesc());
			}
			if (meghpidevicepage.DeviceNameTextField(devicename)) {

				logResults.createLogs("Y", "PASS", "Device Name Inputted Successfully." + devicename);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Device Name." + meghpidevicepage.getExceptionDesc());
			}
			if (meghpidevicepage.DeviceIDTextField(deviceid)) {

				logResults.createLogs("Y", "PASS", "Device ID Inputted Successfully." + deviceid);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Device ID." + meghpidevicepage.getExceptionDesc());
			}
			if (meghpidevicepage.ModeSelection()) {

				logResults.createLogs("Y", "PASS", "Device Mode Selected Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Device Mode." + meghpidevicepage.getExceptionDesc());
			}
			if (meghpidevicepage.TypeSelection()) {

				logResults.createLogs("Y", "PASS", "Device Type Selected Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Device Type." + meghpidevicepage.getExceptionDesc());
			}
			if (meghpidevicepage.AssignedLocationSelection(officename)) {

				logResults.createLogs("Y", "PASS", "Location Selected Successfully." + officename);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Device Location." + meghpidevicepage.getExceptionDesc());
			}
			if (meghpidevicepage.AssignedGroupSelection()) {

				logResults.createLogs("Y", "PASS", "Assigned Group Selected Successfully.");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Assigned Group." + meghpidevicepage.getExceptionDesc());
			}
			
			if (meghpidevicepage.AddDeviceSaveButton()) {

				logResults.createLogs("Y", "PASS", "AddDeviceSaveButton Clicked Successfully.");
				Utils.propsReadWrite("data/addmaster.properties", "set", "DeviceName", devicename);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On AddDeviceSaveButton." + meghpidevicepage.getExceptionDesc());
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
