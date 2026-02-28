package com.MeghPI.Attendance.Android.tests;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.Android.pages.MeghPIAttendAndroidLoginPage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import utils.Utils;

public class MeghPIAttendAndroidLoginTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();


	private String cmpcode = "";
	private String Emailid = "";
	private String phonenumber = "";
	private String AdminEmpID = "";
	
	@Parameters({ "device" })
	@BeforeMethod
	public void launchDriver(int device) { // String param1
		driver = loadDriver.getDriver(device);
		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

	@Parameters({ "device" })
	@BeforeClass
	void runOnce(int device) {
		logResults.createReport(1);
		logResults.setTestMethodErrorCount(0);
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		 phonenumber = Utils.propsReadWrite("data/addmaster.properties", "get", "phonenumber", "");
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		AdminEmpID = Utils.propsReadWrite("data/addmaster.properties", "get", "AdminEmpID", "");
	}
	
	
//MPI_1376_Mobile_Login_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_1376_Mobile_Login_01()  {
		
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Scenario.To verify that the user is able to successfully log in to the mobile application using valid credentials and company code.");
		ArrayList<String> data = initBase.loadExcelData("login", currTC, "password");

		String password = data.get(0);
		MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass

		// Step 1.Enter Company Code
//		driver.activateApp("com.mantra.praveshsaas");
		if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
		
		if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.UserNameTextField(Emailid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field." + Emailid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}

		if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}


		if (meghPIAttendAndroidLoginPage.LoginButton()) {
			logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
		}
	}

	//MPI_1377_Mobile_Login_02
	@Test(enabled = true, priority = 2, groups = { "Smoke" })
		public void MPI_1377_Mobile_Login_02()  {
			
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"To verify that the employee is able to successfully log in to the mobile application using a valid registered mobile number.");
			ArrayList<String> data = initBase.loadExcelData("login", currTC, "password");

			String password = data.get(0);
			MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass

			// Step 1.Enter Company Code
//			driver.activateApp("com.mantra.praveshsaas");
			
			
			if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
				logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			}
			
			if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
				logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			}


			if (meghPIAttendAndroidLoginPage.UserNameTextField(phonenumber)) {
				logResults.createLogs("Y", "PASS", "Phone Number Is Entered In Username Text Field." + phonenumber);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Phone Number." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			}

			if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
				logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			}


			if (meghPIAttendAndroidLoginPage.LoginButton()) {
				logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
			}
		}
	
	//MPI_1378_Mobile_Login_03
		@Test(enabled = true, priority = 3, groups = { "Smoke" })
			public void MPI_1378_Mobile_Login_03()  {
				
				String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
				logResults.createExtentReport(currTC);
				logResults.setScenarioName(
						"To verify that the employee is able to successfully log in to the mobile application using a valid employee code.");
				ArrayList<String> data = initBase.loadExcelData("login", currTC, "password");

				String password = data.get(0);
				MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass

				// Step 1.Enter Company Code
//				driver.activateApp("com.mantra.praveshsaas");
				
				if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
					logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
				} else {
					logResults.createLogs("Y", "FAIL",
							"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				}
				
				if (meghPIAttendAndroidLoginPage.clickLoginWithPassword()) {
					logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Login With Password Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				}


				if (meghPIAttendAndroidLoginPage.UserNameTextField(AdminEmpID)) {
					logResults.createLogs("Y", "PASS", "Employee ID Entered In Username Text Field." + AdminEmpID);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Entering Employee ID." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				}

				if (meghPIAttendAndroidLoginPage.PasswordTextField(password)) {
					logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field." + password);
				} else {
					logResults.createLogs("Y", "FAIL", "Error While Entering Password." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				}


				if (meghPIAttendAndroidLoginPage.LoginButton()) {
					logResults.createLogs("Y", "PASS", "Clicked On Login Button.");
				} else {
					logResults.createLogs("Y", "FAIL",
							"Error While Clicking On Login Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
				}
			}
	
	
		//MPI_1379_Mobile_Login_04
				@Test(enabled = true, priority = 4, groups = { "Smoke" })
					public void MPI_1379_Mobile_Login_04()  {
						
						String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
						logResults.createExtentReport(currTC);
						logResults.setScenarioName(
								"To verify that the employee is able to successfully log in to the mobile application using a valid email ID and OTP authentication.");
				
						MeghPIAttendAndroidLoginPage meghPIAttendAndroidLoginPage = new MeghPIAttendAndroidLoginPage(driver); // Driver from LoginBaseClass

						// Step 1.Enter Company Code
//						driver.activateApp("com.mantra.praveshsaas");
						
						
						if (meghPIAttendAndroidLoginPage.enterCompanyCode(cmpcode)) {
							logResults.createLogs("Y", "PASS", "Company Code Inputted Successfully." + cmpcode);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Company Code Inputting Is Failed." + meghPIAttendAndroidLoginPage.getExceptionDesc());
						}
						if (meghPIAttendAndroidLoginPage.LoginWithOTPClicked()) {
							logResults.createLogs("Y", "PASS", "Login With OTP Button Clicked.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Login With OTP Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
						}
						if (meghPIAttendAndroidLoginPage.MailIDForOTPLogin(Emailid)) {
							logResults.createLogs("Y", "PASS", "Email Is Inputted Successfully." + Emailid);
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Inputting Email ID." + meghPIAttendAndroidLoginPage.getExceptionDesc());
						}
						if (meghPIAttendAndroidLoginPage.RequestOTPButton()) {
							logResults.createLogs("Y", "PASS", "Request OTP Button Clicked Clicked.");
						} else {
							logResults.createLogs("Y", "FAIL",
									"Error While Clicking On Request OTP Button." + meghPIAttendAndroidLoginPage.getExceptionDesc());
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
