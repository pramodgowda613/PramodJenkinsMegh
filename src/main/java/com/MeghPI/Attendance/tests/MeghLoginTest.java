package com.MeghPI.Attendance.tests;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.LoadDriver;
import base.LogResults;
import base.initBase;
import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterDepartmentPage;
import com.MeghPI.Attendance.pages.MeghMasterEmployeePage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;
import com.MeghPI.Attendance.pages.MeghShiftPage;

import utils.Pramod;
import utils.Utils;

public class MeghLoginTest {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	private String companycode = "";
	private String confirmpassword = "Pramod@1234";
	private String emailss = "";
	private String emailids = "";
	private String cmpcode = "";
	private String Emailid = "";
	private String EmailForMailinator = "";
	private String phonenumber = "";

	@Parameters({ "device" })
	@BeforeMethod(alwaysRun = true)
	public void launchDriver(int device) { // String param1
		initBase.browser = "chrome";
		driver = loadDriver.getDriver(device);

		logResults.setDriver(driver);
		logResults.setScenarioName("");
	}

	@Parameters({ "device" })
	@BeforeClass(alwaysRun = true)
	void runOnce(int device) {
		logResults.createReport(device);
		logResults.setTestMethodErrorCount(0);
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		phonenumber = Utils.propsReadWrite("data/addmaster.properties", "get", "phonenumber", "");

		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		EmailForMailinator = "AutoE" + initBase.executionRunTime;

	}

	// MPI-1_Login_Page_01
	@Test(enabled = true, priority = 1, groups = { "Smoke" })
	public void MPI_1_Login_Page_01() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults
				.setScenarioName("Verify that, after entering the company code user is able redirect to sign in page");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "cmpcode,message");

		String cmpcode = data.get(0);
		String message = data.get(1);

		System.out.println(cmpcode + message);
		System.out.println(message);

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while entering Companycode name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login with password button is clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while clicking on Login with password button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}
	}

	// MPI_2_Login_Page_02
	@Test(enabled = true, priority = 2, groups = { "Sanity" })
	public void MPI_2_Login_Page_02() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Verify that, validation message display when keep company code field blank and click on login button");

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);
		ArrayList<String> data = initBase.loadExcelData("login", currTC, "message");

		String message = data.get(0);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.companyCodeErrorMsg()) {
			logResults.createLogs("Y", "PASS", "Error Message Is Displayed: " + message);
		} else {
			logResults.createLogs("Y", "FAIL", "Error Message Is Not Displayed: " + meghLoginPage.getExceptionDesc());
		}

	}

	// MPI_3_Login_Page_03
	@Test(enabled = true, priority = 3, groups = { "Sanity" })
	public void MPI_3_Login_Page_03() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Verify that, after entering invalid company code field user able to redirect to sign in page or not");

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);
		ArrayList<String> data = initBase.loadExcelData("login", currTC, "cmpcode,message");

		String cmpcode = data.get(0);
		String message = data.get(1);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.companyCodeValidationMsg()) {
			logResults.createLogs("Y", "PASS", "Error Message Is Displayed For Invalid Company Code: " + message);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error Message Is Not Displayed For Invalid Company Code: " + meghLoginPage.getExceptionDesc());
		}

	}

	// MPI_4_Login_Page_04
	@Test(enabled = true, priority = 4, groups = { "Smoke", "AddMaster" })
	public void MPI_4_Login_Page_04() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Verify that upon entering a valid company code and clicking the Login with OTP button, the user is redirected to the Login with OTP page and an OTP is sent to the provided email address.");

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);

		ArrayList<String> data = initBase.loadExcelData("login", currTC,
				"firstname,lastname,emailid,phonenumber,createpassword,confirmpassword,companyname,companycode,headcount,industrytype,yourrole,captcha,cardnumber,cardexpiry,cardcvv,cardownername,paymentotp,companywebsite,country,state,city,companyaddress,zipcode,officename,officetype,deptname,mailinator,imagepath,topassmailinator");

		String firstname = data.get(0) + Pramod.generateRandomAlpha(3);
		String lastname = data.get(1) + Pramod.generateRandomAlpha(4);

		String phonenumbers = data.get(3) + Pramod.generateRandomNumber(5);
		String createpassword = data.get(4);
		confirmpassword = data.get(5);
		String companyname = data.get(6) + Pramod.generateRandomAlpha(5);

		companycode = data.get(7) + Pramod.generateRandomAlpha(3);

		String headcount = data.get(8);
		String industrytype = data.get(9);
		String yourrole = data.get(10);
		String captcha = data.get(11);
		String cardnumber = data.get(12);
		String cardexpiry = data.get(13);
		String cardcvv = data.get(14);
		String cardownername = data.get(15);
		String paymentotp = data.get(16);
		String companywebsite = data.get(17);

		String country = data.get(18);
		String state = data.get(19);
		String companyaddress = data.get(21);
		String zipcode = data.get(22);
		String officename = data.get(23) + Pramod.generateRandomAlpha(5);
		String officetype = data.get(24);
		String deptname = data.get(25) + Pramod.generateRandomAlpha(4);

		String domain = data.get(26);
		String imagepath = data.get(27);
		String URL = data.get(28);

		String LoginOTP = "";

		String signupOTPfrommail = "";

		String emailid = data.get(2);
		emailss = emailid + initBase.executionRunTime;
		emailids = emailss + domain;

		if (initBase.stopNewData) { // Tapan 5 July 25
			String propscmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
			if (propscmpcode.trim().isEmpty() != true) {
				if (verifyCompanyLogin(propscmpcode, "AutoE" + initBase.executionRunTime + "@mailinator.com",
						"Pramod@1234", "92349", logResults, driver)) {
					logResults.createLogs("Y", "INFO", "Skip Adding New Data. Continue with Old Test Data.");
					companycode = propscmpcode;
					throw new SkipException("Skip Adding New Data. Continue with Old Test Data.");
				} else {
					logResults.createLogs("Y", "INFO", "Created New addMaster.Properties with NEW executionid");
					new File("data/addmaster.properties").delete();
					initBase.executionRunTime = new SimpleDateFormat("ddMMyyHHmmss").format(new Date());
					emailss = emailid + initBase.executionRunTime;
					emailids = "AutoE" + initBase.executionRunTime + "@mailinator.com";
					EmailForMailinator = "AutoE" + initBase.executionRunTime;
					Utils.propsReadWrite("data/addmaster.properties", "set", "executionID", initBase.executionRunTime);
				}
			}
		}

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CreateAnAccount()) {
			logResults.createLogs("Y", "PASS", "Create An Account Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Create An Account Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.FirstName(firstname)) {
			logResults.createLogs("Y", "PASS", "First Name Entered in Text Box: " + firstname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering First Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.LastName(lastname)) {
			logResults.createLogs("Y", "PASS", "Last Name Entered in Text Box: " + lastname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Last Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.Email(emailids)) {
			logResults.createLogs("Y", "PASS", "Email  Entered in Text Box: " + emailids);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Email ID: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PhoneNumber(phonenumbers)) {
			logResults.createLogs("Y", "PASS", "Phone Number Entered in Text Box: " + phonenumbers);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Phone Number: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CreatePassword(createpassword)) {
			logResults.createLogs("Y", "PASS", "Create Password Entered in Text Box: " + createpassword);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Create Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ConfirmPassword(confirmpassword)) {
			logResults.createLogs("Y", "PASS", "Confirm Password Entered in Text Box: " + confirmpassword);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Confirm Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.SignUpButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Cliked On SignUp Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SignUp Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CompanyName(companyname + initBase.executionRunTime)) {
			logResults.createLogs("Y", "PASS", "Company Name Entered in Text Box: " + companyname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Company Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(companycode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + companycode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Company Code: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.selectCompanyHeadCount(headcount)) {
			logResults.createLogs("Y", "PASS", "Company HeadCount Selected In the Dropdown: " + headcount);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Company Headcount: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.IndustryType(industrytype)) {
			logResults.createLogs("Y", "PASS", "Industry Type Selected In the Dropdown: " + industrytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Industry Type: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.YourRole(yourrole)) {
			logResults.createLogs("Y", "PASS", "Your Role Selected In the Dropdown: " + yourrole);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Role: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CompleteButton()) {
			logResults.createLogs("Y", "PASS", "Your Role Selected In the Dropdown: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Role: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.EnterOTPSignUp1()) {
			logResults.createLogs("Y", "PASS", "OTP Sent To YOP Mail: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While OTP Sent To YOP Mail: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NewTabOpenAndMailinatorOpen(URL)) {
			logResults.createLogs("Y", "PASS", "Successfully Open The New Tab And URL Is Passed: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Opening New Tab URL Inputting: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetFreeTrailButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On GetFreeTrailButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GetFreeTrailButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PopupCloseButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On PopupCloseButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PopupCloseButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.InBoxTextField(emailss)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Emp Name: " + emailss);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.SearchButtonOfMailinator()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchButtonOfMailinator: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchButtonOfMailinator: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OTPForSignUpInMailinator()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SignUp OTP Mail: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Recieved OTP In Mail: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetOTPFromMailForSignUp()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated To IFrame: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Navigating To IFrame: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OTPForSignUpExtracted()) {
			signupOTPfrommail = meghLoginPage.SignupOTP;

			logResults.createLogs("Y", "PASS", "Successfully Extracted The OTP: " + signupOTPfrommail);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Extracting The OTP: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TabSwitchBack()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated Back To Application: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Switching Between Tabs: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.EnterOTPSignUp(signupOTPfrommail)) {
			logResults.createLogs("Y", "PASS", "OTP Sent To YOP Mail: " + signupOTPfrommail);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While OTP Sent To YOP Mail: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.EnterCaptchaSignUp(captcha)) {
			logResults.createLogs("Y", "PASS", "Successfully Entered The Captcha: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering The Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.VerifyAndLoginButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On VerifyAndLoginButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On VerifyAndLoginButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.VMSSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected VMS: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting VMS: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CMSSelected()) {
			logResults.createLogs("Y", "PASS", "Successfully Selected CMS: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting CMS: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.SaveButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SaveButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SaveButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PlanF()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On PlanF: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On PlanF: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.MakePaymentButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On MakePaymentButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On MakePaymentButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CardsCredit()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Cards For Payment: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Cards/Credit: " + meghLoginPage.getExceptionDesc());
		}

		System.out.println(cardnumber);

		if (meghLoginPage.CardNumber(cardnumber)) {
			logResults.createLogs("Y", "PASS", "Successfully Entered The Card Number: " + cardnumber);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Card Number: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CardExpiry(cardexpiry)) {
			logResults.createLogs("Y", "PASS", "Successfully Entered The Card ExpiryDate: " + cardexpiry);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering The Card ExpiryDate: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CardCVV(cardcvv)) {
			logResults.createLogs("Y", "PASS", "Successfully Entered The Card CVV: " + cardcvv);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering The Card CVV: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.cardOwnerName(cardownername)) {
			logResults.createLogs("Y", "PASS", "Successfully Entered The Card OwnerName: " + cardownername);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering The Card OwnerName: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ProceedButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On ProccedButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ProccedButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ContinueWithoutSavingButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Continue Without Saving Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Continue Without Saving Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.EnterPaymentOTP(paymentotp)) {
			logResults.createLogs("Y", "PASS", "Successfully Entered The Payment OTP: " + paymentotp);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering The Payment OTP: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OTPConfirmButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On OTPConfirmButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On OTPConfirmButton: " + meghLoginPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (meghLoginPage.BackToLoginButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On BackToLoginButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On BackToLoginButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(companycode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + companycode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(emailids)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + emailids);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(createpassword)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + createpassword);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.MailCheck()) {

			logResults.createLogs("Y", "PASS", "Clicked On MailCheck Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On MailCheck Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NewTabOpenAndMailinatorOpen(URL)) {
			logResults.createLogs("Y", "PASS", "Successfully Open The New Tab And URL Is Passed: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Opening New Tab URL Inputting: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetFreeTrailButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On GetFreeTrailButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GetFreeTrailButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PopupCloseButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On PopupCloseButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PopupCloseButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.InBoxTextField(emailss)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Emp Name: " + emailss);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.SearchButtonOfMailinator()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchButtonOfMailinator: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchButtonOfMailinator: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.closeAllOtherTabsExceptCurrent()) {
			logResults.createLogs("Y", "PASS", "Except Application All Other Tabs Are Closed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Other Tabs Aren't Closed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ActivationLinkRecieved()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Activation Mail Link: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Activation Mail Link: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetOTPFromMailForSignUp()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated To IFrame: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Navigating To IFrame: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ActivationLink()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On ActivationLink: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ActivationLink: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.AccountActivationToLoginPage()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated Back To Application: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Navigating From Mailinator To Application: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.LoginHere()) {

			logResults.createLogs("Y", "PASS", "Clicked On LoginHere Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On LoginHere In New Tab: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(companycode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + companycode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(emailids)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + emailids);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(createpassword)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + createpassword);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		Thread.sleep(2000);

		if (meghLoginPage.CompanyDetails()) {

			logResults.createLogs("Y", "PASS", "Clicked On CompanyDetails: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CompanyDetails Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.IndustryType(industrytype)) {
			logResults.createLogs("Y", "PASS", "Industry Type Selected In the Dropdown: " + industrytype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Industry Type: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.UploadButton(imagepath)) {
			logResults.createLogs("Y", "PASS", "Image Is Selected As Logo: " + imagepath);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting Image: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CompanyWebsite(companywebsite)) {
			logResults.createLogs("Y", "PASS", "Entered CompanyWebsite Successfully: " + companywebsite);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Company Website: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.AddDetails()) {
			logResults.createLogs("Y", "PASS", "AddDetails Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddDetails: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.AddAddress()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Add AddreessButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Add AddreessButton : " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CountryIndiaSelected(country)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected Country From CountryDropdown: " + country);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Country From CountryDropdown : " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.StateDropdownSelected(state)) {
			logResults.createLogs("Y", "PASS", "Successfully Selected State From StateDropdown: " + state);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting State From StateDropdown : " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.AddressTextfield(companyaddress)) {
			logResults.createLogs("Y", "PASS", "Successfully Enter The Company Address: " + companyaddress);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering The Company Address : " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PinCode(zipcode)) {
			logResults.createLogs("Y", "PASS", "Successfully Enter The Company Address Zipcode: " + zipcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering The Company Address Zipcode : " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.AddAddressButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On AddAddress Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddAddress Button : " + meghLoginPage.getExceptionDesc());
		}
		Thread.sleep(4000);

		if (meghLoginPage.ContactDetails()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On ContactDetails: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicked On ContactDetails: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PrimaryContactName(firstname)) {
			logResults.createLogs("Y", "PASS", "Contact Name Entered in Text Box: " + firstname);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Contact Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PrimaryContactNumber(phonenumbers)) {
			logResults.createLogs("Y", "PASS", "Phone Number Entered in Text Box: " + phonenumbers);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Phone Number: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PrimaryContactEmail(emailids)) {
			logResults.createLogs("Y", "PASS", "Email Id Entered in Text Box: " + emailids);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Email ID: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.AddContactButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On AddContactButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddContactButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OfficeSetup()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On OfficeSetup: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On OfficeSetup: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.AddOffice()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On AddOffice: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddOffice: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OfficeName(officename)) {
			logResults.createLogs("Y", "PASS", "Office Name Entered in Text Box: " + officename);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Office Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CompanyLocationOfficeType(officetype)) {
			logResults.createLogs("Y", "PASS", "Company OfficeType Selected In the Dropdown: " + officetype);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Company OfficeType: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OfficeAddress(companyaddress)) {
			logResults.createLogs("Y", "PASS", "Office Address Entered in Text Box: " + companyaddress);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Office Address: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OfficeCountry(country)) {
			logResults.createLogs("Y", "PASS", "Company's Country Selected In the Dropdown: " + country);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Company's Country: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OfficeState(state)) {
			logResults.createLogs("Y", "PASS", "Company's State Selected In the Dropdown: " + state);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Company's State: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.AddOfficeSave()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On AddOfficeSave: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddOfficeSave: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.AddDepartment()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On AddDept: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Clicking On AddDept: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.DepartmentName(deptname)) {
			logResults.createLogs("Y", "PASS", "Dept Name Entered in Text Box: " + deptname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Dept Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.AssignOffice(officename)) {
			logResults.createLogs("Y", "PASS", "Company's Assign Office Selected In the Dropdown: " + officename);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting Company's Assign Office: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.AddDeptSave()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On AddDeptSave: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On AddDeptSave: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PoliciesButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Policy Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Successfully Clicked On Policy Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.UseAsDefaultButton()) {

			logResults.createLogs("Y", "PASS", "Successfully Clicked On UseAsDefaultButton Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking on  UseAsDefaultButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GeneralSettings()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On GeneralSettings Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking on  GeneralSettings: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TimeFormat()) {
			logResults.createLogs("Y", "PASS", "TimeFormat Selected In the Dropdown: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting  TimeFormat: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TimeZone()) {
			logResults.createLogs("Y", "PASS", " TimeZone Selected In the Dropdown: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting TimeZone: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TwoStepVerification()) {
			logResults.createLogs("Y", "PASS", "TwoStepVerification Selected In the Dropdown: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting TwoStepVerification: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.DateFormat()) {
			logResults.createLogs("Y", "PASS", "DateFormat Selected In the Dropdown: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Selecting DateFormat: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.FinancialYear()) {
			logResults.createLogs("Y", "PASS", "FinancialYear Selected In the Dropdown: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting FinancialYear: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GeneralSettingsSave()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On GeneralSettingsSave: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GeneralSettingsSave: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PersonalSetting()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On PersonalSetting: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PersonalSetting: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.EmployeeIDDropdown()) {
			logResults.createLogs("Y", "PASS", "EmployeeIDDropdown Selected In the Dropdown: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Selecting EmployeeIDDropdown: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.EmployeeID(deptname)) {
			logResults.createLogs("Y", "PASS", "Employee id Entered In The Text Field: " + deptname);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputing Employee ID: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PersonalSettingSave()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On PersonalSettingSave: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On  PersonalSettingSave: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GoToDashBoard()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On GoToDashBoard: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On  GoToDashBoard: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.SignupValidation()) {

			logResults.createLogs("Y", "PASS", "Login Done  And Validated Successfully: ");
			Utils.propsReadWrite("data/addmaster.properties", "set", "cmpcode", companycode); // Tapan 5th July 25
			Utils.propsReadWrite("data/addmaster.properties", "set", "cmpmst", "pass");
			Utils.propsReadWrite("data/addmaster.properties", "set", "phonenumber", phonenumbers);
			
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Login Validation: " + meghLoginPage.getExceptionDesc());

		}

		if (meghLoginPage.ErrorToastIsClicked()) {

			logResults.createLogs("Y", "PASS", "Error Message is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error Message isn't Clickable: " + meghLoginPage.getExceptionDesc());
		}

		if (RolePermissionpage.ManageButton()) {
			logResults.createLogs("Y", "PASS", "Admin Manage Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Manage Button : " + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LogoutButton()) {
			logResults.createLogs("Y", "PASS", "Admin Logout Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Logout Button : " + RolePermissionpage.getExceptionDesc());
		}

		if (meghLoginPage.closeAllOtherTabsExceptCurrent()) {
			logResults.createLogs("Y", "PASS", "Except Application All Other Tabs Are Closed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Other Tabs Aren't Closed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(companycode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + companycode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithOTP()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Login With OTP Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With OTP Button: " + meghLoginPage.getExceptionDesc());
		}

		Thread.sleep(3000);

		if (meghLoginPage.LoginWithOTPPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Successfully Loaded LoginWithOTPPage: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Loading Login With OTP Page: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithOtpUserName(emailids)) {
			logResults.createLogs("Y", "PASS", "Successfully Entered The Username: " + emailids);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering The Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithOtpCaptcha(captcha)) {
			logResults.createLogs("Y", "PASS", "Successfully Entered The Captcha: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering The Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.requestOTPButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked on RequestOTP Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On The RequestOTP Button: " + meghLoginPage.getExceptionDesc());
		}

		// getOTPfrommail method pending because OTP not recieved

		if (meghLoginPage.NewTabOpenAndMailinatorOpen(URL)) {
			logResults.createLogs("Y", "PASS", "Successfully Open The New Tab And URL Is Passed: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Opening New Tab URL Inputting: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetFreeTrailButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On GetFreeTrailButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GetFreeTrailButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PopupCloseButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On PopupCloseButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PopupCloseButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.InBoxTextField(emailss)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Emp Name: " + emailss);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.SearchButtonOfMailinator()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchButtonOfMailinator: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchButtonOfMailinator: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.FirstRecievedMailClick()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On FirstRecievedMailClick: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On FirstRecievedMailClick: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetOTPFromMailForLogin()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated To IFrame: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Navigating To IFrame: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OTPExtractionFromBody()) {
			LoginOTP = meghLoginPage.extractedLoginOTP;
			logResults.createLogs("Y", "PASS", "Successfully Fetch The OTP From Mail: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Fetching OTP From Mail: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TabSwitchBack()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated Back To Application: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Switching Between Tabs: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.EnterOTPLogin(LoginOTP)) {
			logResults.createLogs("Y", "PASS", "OTP Entered In The Enter OTP Text Field: " + LoginOTP);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputing The OTP: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.EnterCaptchaOTPLogin(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered In The Enter Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputing The Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.VerifyAndLoginButtonOTP()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked on VerifyAndLoginButtonOTP Button: ");
			

		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On The VerifyAndLoginButtonOTP Button: " + meghLoginPage.getExceptionDesc());
		}

	}

	// MPI_7_Login_Page_07
	@Test(enabled = true, priority = 7, groups = { "Smoke" })
	public void MPI_7_Login_Page_07() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Verify that user able to do login with valid company code ,valid emai address and valid password");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "password,captcha");

		String password = data.get(0);
		String captcha = data.get(1);

		if (verifyCompanyLogin(companycode, "AutoE" + initBase.executionRunTime + "@mailinator.com", password, captcha,
				logResults, driver) != true) {
			Assert.fail();
		}
	}

	public boolean verifyCompanyLogin(String propscmpcode, String user, String password, String captcha,
			LogResults plogResults, WebDriver pdriver) {
		MeghLoginPage meghLoginPage = new MeghLoginPage(pdriver);
		if (meghLoginPage.ApplicationLoaded()) {
			plogResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			plogResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(propscmpcode)) {
			plogResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + propscmpcode);
		} else {
			plogResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			plogResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			plogResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			plogResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			plogResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(user)) {
			plogResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + user);
		} else {
			plogResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(password)) {
			plogResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + password);
		} else {
			plogResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			plogResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			plogResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {
			plogResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			plogResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginValidation()) {
			plogResults.createLogs("Y", "PASS", "Login Done  And Validated Successfully: ");
			return true;
		} else {
			if (captcha.equals("92349") != true) { // Don't fail the TC hence kept this check Tapan 5July 25
				plogResults.createLogs("Y", "FAIL",
						"Error While Login Validation: " + meghLoginPage.getExceptionDesc());
			}
		}
		return false;
	}

	// MPI_8_Login_Page_08
	@Test(enabled = true, priority = 8, groups = { "Sanity" })
	public void MPI_8_Login_Page_08() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Verify that , User able to login with invalid company code, invalid email address and valid password");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "cmpcode,user,password,captcha");

		String cmpcode = data.get(0);
		String user = data.get(1);
		String password = data.get(2);
		String captcha = data.get(3);

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(user)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + user);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(password)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + password);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.invalidMsg()) {

			logResults.createLogs("Y", "PASS", "Error Message is Displayed: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error Message isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

	}

	// MPI_9_Login_Page_09
	@Test(enabled = true, priority = 9, groups = { "Sanity" })
	public void MPI_9_Login_Page_09() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Verify that user able to login with valid comapany code , valid email address and inavalid password");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "cmpcode,user,password,captcha");

		String cmpcode = data.get(0);
		String user = data.get(1);
		String password = data.get(2);
		String captcha = data.get(3);

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(user)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + user);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(password)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + password);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.InvalidPasswordValidation()) {

			logResults.createLogs("Y", "PASS", "Error Message is Displayed: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error Message isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}
	}

	// MPI_10_Login_Page_10
	@Test(enabled = true, priority = 10, groups = { "Sanity" })
	public void MPI_10_Login_Page_10() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"verify that user able to login with valid company code , inavalid email address and valid password");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "cmpcode,user,password,captcha");

		String cmpcode = data.get(0);
		String user = data.get(1);
		String password = data.get(2);
		String captcha = data.get(3);

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(user)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + user);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(password)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + password);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.invalidMsg()) {

			logResults.createLogs("Y", "PASS", "Error Message is Displayed: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error Message isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}
	}

	// MPI_11_Login_Page_11
	@Test(enabled = true, priority = 11, groups = { "Sanity" })
	public void MPI_11_Login_Page_11() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Verify that user able to login with inavalid company code valid email address and valid password.");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "cmpcode,user,password,captcha");

		String cmpcode = data.get(0);
		String user = data.get(1);
		String password = data.get(2);
		String captcha = data.get(3);

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(user)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + user);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(password)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + password);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NavigateBackToCompanyCode()) {
			logResults.createLogs("Y", "PASS", "User redicted Back To Company Code Page Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While User redirected Back To The Company Code Page: " + meghLoginPage.getExceptionDesc());
		}
	}

	// MPI_12_Login_Page_12 
	@Test(enabled = true, priority = 12, groups = { "Smoke" })
	public void MPI_12_Login_Page_12() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Verify that after clicking the Forgot Password link, the user is redirected to the Forgot Password page. After entering a valid company code and username/phone number, an OTP should be sent to the provided contact details.");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "captcha");

		String captcha = data.get(0);

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);
		
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ForgotPassword()) {
			logResults.createLogs("Y", "PASS", "Forgot Password Link is Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Forgot Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TxtEmailID(Emailid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Email Text Field: " + Emailid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Email: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.RequestOTPFP()) {
			logResults.createLogs("Y", "PASS", "Request OTP is Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Request OTP Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OTPSentSuccess()) {
			logResults.createLogs("Y", "PASS", " OTP Sent Success Msg Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Recieving OTP Success Msg: " + meghLoginPage.getExceptionDesc());
		}

	}

	// MPI_13_Login_Page_13 
	@Test(enabled = true, priority = 13, groups = { "Sanity" })
	public void MPI_13_Login_Page_13() throws InterruptedException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Verify that, Password must contain least 8 char, 1 special character(@, #, $, %), 1 uppercase letter, 1 lowercase character and 1 number.");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "cmpcode,user,password,captcha");

		String cmpcode = data.get(0);
		String user = data.get(1);
		String password = data.get(2);
		String captcha = data.get(3);

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(user)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + user);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(password)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + password);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.InvalidPasswordValidation()) {

			logResults.createLogs("Y", "PASS", "Password Criteria Error Message Is Displayed: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Password Criteria Error Message Isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}
	}
	// AutoBAN11226666112222
	// AutoUN11226666112222@yopmail.com

	// MPI_14_Login_Page_14
	@Test(enabled = true, priority = 14, groups = { "Smoke" })
	public void MPI_14_Login_Page_14() throws InterruptedException, AWTException, IOException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Verify that, when clicks on create an account link user is redirect to Get free trial page and after entering the mandatory details then click on submit button new account is created or not");
		logResults.createLogs("Y", "PASS", "This Test Case Already passed In MPI_4_Login_Page_04 TestCase: ");
	}

//MPI_15_Login_Page_15
	@Test(enabled = true, priority = 15, groups = { "Sanity" })
	public void MPI_15_Login_Page_15() throws InterruptedException, AWTException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Verify that user able to login with blank input fields");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "cmpcode,captcha");

		String cmpcode = data.get(0);
		String captcha = data.get(1);

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName("")) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword("")) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.UserNameEmpty()) {

			logResults.createLogs("Y", "PASS", "UserName Error Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Error Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}
	}

//MPI_16_Login_Page_16
	@Test(enabled = true, priority = 16, groups = { "Sanity" })
	public void MPI_16_Login_Page_16() throws InterruptedException, AWTException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName("Verify that, company code shouldnt contains more than 4 characters.");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "cmpcode");

		String cmpcode = data.get(0);

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}
	}

	// MPI_49_Login_Page_18
	@Test(enabled = true, priority = 18, groups = { "Smoke" })
	public void MPI_49_Login_Page_18() throws InterruptedException, AWTException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"Verify that, After logging into the application, the dashboard page should be displayed successfully.");

		logResults.createLogs("Y", "PASS", "This Case Already passed In PI_7_Login_Page_07 TestCase: ");

	}

	// MPI_137_Login_page_19
	@Test(enabled = true, priority = 19, groups = { "Sanity" })
	public void MPI_137_Login_page_19() throws InterruptedException, AWTException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enter invalid email id in theForgot Company Code?Email Id* text field and check whether proper error message is display");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "cmpcode,user,captcha");

		String user = data.get(1);
		String captcha = data.get(2);

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ForgotCompanyCode()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On ForgotCompanyCode: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ForgotCompanyCode: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ForgotCompanyEmailID(user)) {
			logResults.createLogs("Y", "PASS", "Successfully Entered The Username: " + user);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering The Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.RequestCompanyCode()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked on RequestCompanyCode Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On The RequestCompanyCode Button: " + meghLoginPage.getExceptionDesc());
		}

	}

	// MPI_138_Login_page_20
	@Test(enabled = true, priority = 20, groups = { "Sanity" })
	public void MPI_138_Login_page_20() throws InterruptedException, AWTException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enter an invalid username and check whether the proper error message is displayed in the Login with OTP flow.");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "cmpcode,user,captcha");

		String cmpcode = data.get(0);
		String user = data.get(1);
		String captcha = data.get(2);

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithOTP()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On Login With OTP Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With OTP Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithOtpUserName(user)) {
			logResults.createLogs("Y", "PASS", "Successfully Entered The Username: " + user);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering The Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithOtpCaptcha(captcha)) {
			logResults.createLogs("Y", "PASS", "Successfully Entered The Captcha: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering The Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.requestOTPButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked on RequestOTP Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On The RequestOTP Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.InvalidPasswordValidation()) {
			logResults.createLogs("Y", "PASS", "Error Message Displayed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error Message Isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}
	}

	// MPI_139_Login_page_21 
	@Test(enabled = true, priority = 21, groups = { "Smoke" })
	public void MPI_139_Login_page_21() throws InterruptedException, AWTException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enter a email in the User Name / Phone Number text field of the Forgot Password step, check whether the user receives an OTP to change the password, and then change the password.");

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "captcha,topassmailinator,password");

		String captcha = data.get(0);
		String URL = data.get(1);
		String password = data.get(2);
		String OTP = "";
		
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");

		
		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);
		MeghShiftPage ShiftPage = new MeghShiftPage(driver);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ForgotPassword()) {
			logResults.createLogs("Y", "PASS", "Forgot Password Link is Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Forgot Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TxtEmailID(Emailid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Email Text Field: " + Emailid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Email: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.RequestOTPFP()) {
			logResults.createLogs("Y", "PASS", "Request OTP is Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Request OTP Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NewTabOpenAndMailinatorOpen(URL)) {
			logResults.createLogs("Y", "PASS", "Successfully Open The New Tab And URL Is Passed: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Opening New Tab URL Inputting: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetFreeTrailButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On GetFreeTrailButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GetFreeTrailButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PopupCloseButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On PopupCloseButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PopupCloseButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.InBoxTextField(EmailForMailinator)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Emp Name: " + EmailForMailinator);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.SearchButtonOfMailinator()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchButtonOfMailinator: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchButtonOfMailinator: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OTPForForgotPassword()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On ForgotPasswordMail: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ForgotPasswordMail: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetOTPFromMailForLogin()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated To IFrame: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Navigating To IFrame: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ForgotPasswordOTPExtracted()) {

			logResults.createLogs("Y", "PASS", "Get The OTP from Email Successfully: ");
			OTP = meghLoginPage.extractedOTP;
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Get The OTP From Email: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.delMailinatorMsg()) {

			logResults.createLogs("Y", "PASS", "DeleteButton Clicked Successfully: ");

		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TabSwitchBack()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated Back To Application: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Switching Between Tabs: " + meghLoginPage.getExceptionDesc());
		}

		System.out.println(OTP + "testOTP");
		if (meghLoginPage.OTPTextField(OTP)) {
			logResults.createLogs("Y", "PASS", "OTP Entered On OTP Text Field: " + OTP);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  OTP: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.VerifyAndContinueButton()) {
			logResults.createLogs("Y", "PASS", "Verify And Continue Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Verify And Continue: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NewPassword(password)) {
			logResults.createLogs("Y", "PASS", "New Password Entered Successfully: " + password);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputing New Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NewConfirmPassword(password)) {
			logResults.createLogs("Y", "PASS", "New Confirm Password Entered Successfully: " + password);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputing Confirm Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PasswordSetButton()) {
			logResults.createLogs("Y", "PASS", "PasswordSetButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PasswordSetButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ContinueToLogin()) {
			logResults.createLogs("Y", "PASS", "ContinueToLogin Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ContinueToLogin: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(Emailid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + Emailid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(password)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + password);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		if (ShiftPage.loginValidate()) {

			logResults.createLogs("Y", "PASS", "login Validated Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Login Completion: " + ShiftPage.getExceptionDesc());
		}

	}

	// MPI_140_Login_page_22 
	@Test(enabled = true, priority = 22, groups = { "Sanity" })
	public void MPI_140_Login_page_22() throws InterruptedException, AWTException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enter a phone number in the User Name / Phone Number text field of the Forgot Password step, check whether the user receives an OTP to change the password, and then change the password.");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "topassmailinator,captcha,password");

		String URL = data.get(0);
		String captcha = data.get(1);
		String password = data.get(2);
		
		

		String OTP = "";
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");
		phonenumber = Utils.propsReadWrite("data/addmaster.properties", "get", "phonenumber", "");
		
		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ForgotPassword()) {
			logResults.createLogs("Y", "PASS", "Forgot Password Link is Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Forgot Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TxtEmailID(phonenumber)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Email Text Field: " + phonenumber);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Email: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.RequestOTPFP()) {
			logResults.createLogs("Y", "PASS", "Request OTP is Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Request OTP Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NewTabOpenAndMailinatorOpen(URL)) {
			logResults.createLogs("Y", "PASS", "Successfully Open The New Tab And URL Is Passed: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Opening New Tab URL Inputting: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetFreeTrailButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On GetFreeTrailButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GetFreeTrailButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PopupCloseButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On PopupCloseButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PopupCloseButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.InBoxTextField(EmailForMailinator)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Emp Name: " + EmailForMailinator);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.SearchButtonOfMailinator()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchButtonOfMailinator: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchButtonOfMailinator: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OTPForForgotPassword()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On ForgotPasswordMail: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ForgotPasswordMail: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetOTPFromMailForLogin()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated To IFrame: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Navigating To IFrame: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ForgotPasswordOTPExtracted()) {

			logResults.createLogs("Y", "PASS", "Get The OTP from Email Successfully: ");
			OTP = meghLoginPage.extractedOTP;
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Get The OTP From Email: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.delMailinatorMsg()) {

			logResults.createLogs("Y", "PASS", "DeleteButton Clicked Successfully: ");

		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TabSwitchBack()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated Back To Application: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Switching Between Tabs: " + meghLoginPage.getExceptionDesc());
		}

		System.out.println(OTP + "testOTP");
		if (meghLoginPage.OTPTextField(OTP)) {
			logResults.createLogs("Y", "PASS", "OTP Entered On OTP Text Field: " + OTP);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  OTP: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.VerifyAndContinueButton()) {
			logResults.createLogs("Y", "PASS", "Verify And Continue Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Verify And Continue: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NewPassword(password)) {
			logResults.createLogs("Y", "PASS", "New Password Entered Successfully: " + password);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputing New Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NewConfirmPassword(password)) {
			logResults.createLogs("Y", "PASS", "New Confirm Password Entered Successfully: " + password);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputing Confirm Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PasswordSetButton()) {
			logResults.createLogs("Y", "PASS", "PasswordSetButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PasswordSetButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ContinueToLogin()) {
			logResults.createLogs("Y", "PASS", "ContinueToLogin Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ContinueToLogin: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(Emailid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + Emailid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(password)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + password);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		if (ShiftPage.loginValidate()) {

			logResults.createLogs("Y", "PASS", "login Validated Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Login Completion: " + ShiftPage.getExceptionDesc());
		}

	}

	// MPI_141_Login_page_23 
	@Test(enabled = true, priority = 23, groups = { "Sanity" })
	public void MPI_141_Login_page_23() throws InterruptedException, AWTException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, enter a employee id in the User Name / Phone Number text field of the Forgot Password step, check whether the user receives an OTP to change the password, and then change the password.");

		MeghShiftPage ShiftPage = new MeghShiftPage(driver);
		MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);
		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "topassmailinator,captcha,password");

		String URL = data.get(0);
		String captcha = data.get(1);
		String password = data.get(2);

		String OTP = "";

		String Empids = "";
		
		cmpcode = Utils.propsReadWrite("data/addmaster.properties", "get", "cmpcode", "");

		MeghMasterDepartmentPage DepartmentPage = new MeghMasterDepartmentPage(driver);
		MeghMasterEmployeePage EmployeePage = new MeghMasterEmployeePage(driver);

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(Emailid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + Emailid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(password)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + password);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginValidation()) {

			logResults.createLogs("Y", "PASS", "Login Done  And Validated Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Login Validation: " + meghLoginPage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryButton()) {
			logResults.createLogs("Y", "PASS", "Directory Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Directery Button: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.EmployeeTab()) {
			logResults.createLogs("Y", "PASS", "Clicked On EmployeeTab Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On EmployeeTab: " + EmployeePage.getExceptionDesc());
		}

		if (EmployeePage.DirectoryPageLoaded()) {
			logResults.createLogs("Y", "PASS", "Directory Page Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Directory Page Isn't Loaded Completely: " + EmployeePage.getExceptionDesc());
		}

		if (DepartmentPage.Employee3dotsFirstRecord()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record 3dots: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On First Employee Record 3dots : " + DepartmentPage.getExceptionDesc());
		}

		if (DepartmentPage.ManageProfileButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On First Employee Record ManageProfileButton: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error while Clicking On First Employee Record ManageProfileButton : "
					+ DepartmentPage.getExceptionDesc());
		}
		Thread.sleep(3000);

		if (meghLoginPage.GetEmpID()) {
			Empids = meghLoginPage.Empid;
			logResults.createLogs("Y", "PASS", "Emp Code Extracted Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Extracting Emp Code: " + meghLoginPage.getExceptionDesc());
		}

		if (RolePermissionpage.ManageButton()) {
			logResults.createLogs("Y", "PASS", "Admin Manage Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Manage Button : " + RolePermissionpage.getExceptionDesc());
		}

		if (RolePermissionpage.LogoutButton()) {
			logResults.createLogs("Y", "PASS", "Admin Logout Button Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error while Clicking On Logout Button : " + RolePermissionpage.getExceptionDesc());
		}

		if (meghLoginPage.closeAllOtherTabsExceptCurrent()) {
			logResults.createLogs("Y", "PASS", "Except Application All Other Tabs Are Closed Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Other Tabs Aren't Closed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ForgotPassword()) {
			logResults.createLogs("Y", "PASS", "Forgot Password Link is Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Forgot Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TxtEmailID(Empids)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Email Text Field: " + Empids);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Email: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.RequestOTPFP()) {
			logResults.createLogs("Y", "PASS", "Request OTP is Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Request OTP Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NewTabOpenAndMailinatorOpen(URL)) {
			logResults.createLogs("Y", "PASS", "Successfully Open The New Tab And URL Is Passed: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Opening New Tab URL Inputting: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetFreeTrailButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On GetFreeTrailButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GetFreeTrailButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PopupCloseButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On PopupCloseButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PopupCloseButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.InBoxTextField(EmailForMailinator)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Emp Name: " + EmailForMailinator);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.SearchButtonOfMailinator()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchButtonOfMailinator: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchButtonOfMailinator: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.OTPForForgotPassword()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On ForgotPasswordMail: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ForgotPasswordMail: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetOTPFromMailForLogin()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated To IFrame: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Navigating To IFrame: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ForgotPasswordOTPExtracted()) {

			logResults.createLogs("Y", "PASS", "Get The OTP from Email Successfully: ");
			OTP = meghLoginPage.extractedOTP;
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Get The OTP From Email: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.delMailinatorMsg()) {

			logResults.createLogs("Y", "PASS", "DeleteButton Clicked Successfully: ");

		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TabSwitchBack()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated Back To Application: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Switching Between Tabs: " + meghLoginPage.getExceptionDesc());
		}

		System.out.println(OTP + "testOTP");
		if (meghLoginPage.OTPTextField(OTP)) {
			logResults.createLogs("Y", "PASS", "OTP Entered On OTP Text Field: " + OTP);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  OTP: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.VerifyAndContinueButton()) {
			logResults.createLogs("Y", "PASS", "Verify And Continue Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Verify And Continue: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NewPassword(password)) {
			logResults.createLogs("Y", "PASS", "New Password Entered Successfully: " + password);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputing New Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NewConfirmPassword(password)) {
			logResults.createLogs("Y", "PASS", "New Confirm Password Entered Successfully: " + password);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Inputing Confirm Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PasswordSetButton()) {
			logResults.createLogs("Y", "PASS", "PasswordSetButton Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PasswordSetButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ContinueToLogin()) {
			logResults.createLogs("Y", "PASS", "ContinueToLogin Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ContinueToLogin: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(cmpcode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + cmpcode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(Emailid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + Emailid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(password)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + password);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

		if (ShiftPage.loginValidate()) {

			logResults.createLogs("Y", "PASS", "login Validated Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Validating Login Completion: " + ShiftPage.getExceptionDesc());
		}

		Thread.sleep(3000);
	}

	// MPI_144_Login_Page_26
	@Test(enabled = true, priority = 26, groups = { "Smoke" })
	public void MPI_144_Login_Page_26() throws InterruptedException, AWTException {
		String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
		logResults.createExtentReport(currTC);
		logResults.setScenarioName(
				"To verify this, check the functionality of the 'Forgot Company Code?' feature by entering a valid email ID, ensure that the user receives the company code, and use it to log in");

		MeghLoginPage meghLoginPage = new MeghLoginPage(driver);

		ArrayList<String> data = initBase.loadExcelData("login", currTC, "topassmailinator,captcha,password");

		String URL = data.get(0);
		String captcha = data.get(1);
		String password = data.get(2);

		String MailCompanyCode = "";

		// String Unique = initBase.executionRunTime;

		if (meghLoginPage.ApplicationLoaded()) {
			logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Application Is Not Loaded Completely: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ForgotCompanyCode()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On ForgotCompanyCode: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ForgotCompanyCode: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ForgotCompanyEmailID(Emailid)) {
			logResults.createLogs("Y", "PASS", "Successfully Entered The Username: " + Emailid);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering The Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.RequestCompanyCode()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked on RequestCompanyCode Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On The RequestCompanyCode Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.NewTabOpenAndMailinatorOpen(URL)) {
			logResults.createLogs("Y", "PASS", "Successfully Open The New Tab And URL Is Passed: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Opening New Tab URL Inputting: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetFreeTrailButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On GetFreeTrailButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On GetFreeTrailButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.PopupCloseButton()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On PopupCloseButton: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On PopupCloseButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.InBoxTextField(EmailForMailinator)) {
			logResults.createLogs("Y", "PASS", "Successfully Inputted Emp Name: " + EmailForMailinator);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.SearchButtonOfMailinator()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchButtonOfMailinator: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On SearchButtonOfMailinator: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CompanyCodeRetrievalLink()) {
			logResults.createLogs("Y", "PASS", "Successfully Clicked On CompanyCodeRetrievalLink: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On CompanyCodeRetrievalLink: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.GetOTPFromMailForSignUp()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated To IFrame: ");
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Navigating To IFrame: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.CompanyCodeFetching()) {
			MailCompanyCode = meghLoginPage.companyCodes;

			logResults.createLogs("Y", "PASS", "Get The Company Code from Email Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Get The Company Code From Email: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.delMailinatorMsg()) {

			logResults.createLogs("Y", "PASS", "DeleteButton Clicked Successfully: ");

		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On DeleteButton: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.TabSwitchBack()) {
			logResults.createLogs("Y", "PASS", "Successfully Navigated Back To Application: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Switching Between Tabs: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.ContinueToLogin()) {
			logResults.createLogs("Y", "PASS", "ContinueToLogin Clicked Successfully: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On ContinueToLogin: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterCompanyCode(MailCompanyCode)) {
			logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + MailCompanyCode);
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Entering Companycode Name: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.loginWithPassButton()) {
			logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login With Password Button: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.signinTextValidation()) {

			logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

		} else {
			logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterUserName(Emailid)) {
			logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + Emailid);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.enterPassword(password)) {
			logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + password);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.captchaText(captcha)) {
			logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
		} else {
			logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + meghLoginPage.getExceptionDesc());
		}

		if (meghLoginPage.clickLoginButton()) {

			logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
		} else {
			logResults.createLogs("Y", "FAIL",
					"Error While Clicking On Login Button: " + meghLoginPage.getExceptionDesc());
		}

	}

	@AfterMethod(alwaysRun = true)
	void AfterEachmethod() {
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

		if (driver != null) {
			driver.quit();
		}
	}

}
