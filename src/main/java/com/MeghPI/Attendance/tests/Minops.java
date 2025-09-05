package com.MeghPI.Attendance.tests;



import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.MeghPI.Attendance.pages.MeghLoginPage;
import com.MeghPI.Attendance.pages.MeghMasterRolePermissionPage;

import base.LoadDriver;
import base.LogResults;
import base.initBase;

import utils.Pramod;
import utils.Utils;

public class Minops {

	WebDriver driver;
	LoadDriver loadDriver = new LoadDriver();
	LogResults logResults = new LogResults();
	public String companycode = "";
	public String confirmpassword = "";
	public String emailss = "";
	public String emailids = "";
	public String phonenumber = "";

	private String cmpcode = "";
	private String Emailid = "";
	private String EmailForMailinator = "";
	
	
	
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
		Emailid = "AutoE" + initBase.executionRunTime + "@mailinator.com";
		EmailForMailinator = "AutoE" + initBase.executionRunTime;
		
	}


	
	// MPI_4_Login_Page_04
		@Test(enabled = true, priority = 4, groups = { "Smoke", "AddMaster" })
		public void MPI_4_Login_Page_04() throws InterruptedException {
			String currTC = Thread.currentThread().getStackTrace()[1].getMethodName();
			logResults.createExtentReport(currTC);
			logResults.setScenarioName(
					"Verify that upon entering a valid company code and clicking the Login with OTP button, the user is redirected to the Login with OTP page and an OTP is sent to the provided email address.");

			MeghLoginPage MeghLoginPage = new MeghLoginPage(driver);
			MeghMasterRolePermissionPage RolePermissionpage = new MeghMasterRolePermissionPage(driver);

			ArrayList<String> data = initBase.loadExcelData("login", currTC,
					"firstname,lastname,emailid,phonenumber,createpassword,confirmpassword,companyname,companycode,headcount,industrytype,yourrole,captcha,cardnumber,cardexpiry,cardcvv,cardownername,paymentotp,companywebsite,country,state,city,companyaddress,zipcode,officename,officetype,deptname,mailinator,imagepath,topassmailinator");

			String firstname = data.get(0) + Pramod.generateRandomAlpha(3);
			String lastname = data.get(1) + Pramod.generateRandomAlpha(4);

			phonenumber = data.get(3) + Pramod.generateRandomNumber(5);
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

			// String Unique = initBase.executionRunTime;
			String emailid = data.get(2);
			emailss = emailid + initBase.executionRunTime;
			emailids = emailss + domain;

			String signupOTPfrommail = "";

			
			if (MeghLoginPage.ApplicationLoaded())
			{
				logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Application Is Not Loaded Completely: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.CreateAnAccount()) {
				logResults.createLogs("Y", "PASS", "Create An Account Button Is Clicked: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Create An Account Button: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.FirstName(firstname)) {
				logResults.createLogs("Y", "PASS", "First Name Entered in Text Box: " + firstname);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering First Name: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.LastName(lastname)) {
				logResults.createLogs("Y", "PASS", "Last Name Entered in Text Box: " + lastname);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Last Name: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.Email(emailids)) {
				logResults.createLogs("Y", "PASS", "Email  Entered in Text Box: " + emailids);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Email ID: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.PhoneNumber(phonenumber)) {
				logResults.createLogs("Y", "PASS", "Phone Number Entered in Text Box: " + phonenumber);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering Phone Number: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.CreatePassword(createpassword)) {
				logResults.createLogs("Y", "PASS", "Create Password Entered in Text Box: " + createpassword);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering Create Password: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.ConfirmPassword(confirmpassword)) {
				logResults.createLogs("Y", "PASS", "Confirm Password Entered in Text Box: " + confirmpassword);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering Confirm Password: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.SignUpButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Cliked On SignUp Button: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On SignUp Button: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.CompanyName(companyname + initBase.executionRunTime)) {
				logResults.createLogs("Y", "PASS", "Company Name Entered in Text Box: " + companyname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering Company Name: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.enterCompanyCode(companycode)) {
				logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + companycode);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering Company Code: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.selectCompanyHeadCount(headcount)) {
				logResults.createLogs("Y", "PASS", "Company HeadCount Selected In the Dropdown: " + headcount);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Company Headcount: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.IndustryType(industrytype)) {
				logResults.createLogs("Y", "PASS", "Industry Type Selected In the Dropdown: " + industrytype);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Industry Type: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.YourRole(yourrole)) {
				logResults.createLogs("Y", "PASS", "Your Role Selected In the Dropdown: " + yourrole);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Role: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.CompleteButton()) {
				logResults.createLogs("Y", "PASS", "Your Role Selected In the Dropdown: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Role: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.EnterOTPSignUp1()) {
				logResults.createLogs("Y", "PASS", "OTP Sent To YOP Mail: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While OTP Sent To YOP Mail: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.NewTabOpenAndMailinatorOpen(URL)) {
				logResults.createLogs("Y", "PASS", "Successfully Open The New Tab And URL Is Passed: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Opening New Tab URL Inputting: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.GetFreeTrailButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On GetFreeTrailButton: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On GetFreeTrailButton: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.PopupCloseButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On PopupCloseButton: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On PopupCloseButton: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.InBoxTextField(emailss)) {
				logResults.createLogs("Y", "PASS", "Successfully Inputted Emp Name: " + emailss);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.SearchButtonOfMailinator()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchButtonOfMailinator: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On SearchButtonOfMailinator: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.OTPForSignUpInMailinator()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On SignUp OTP Mail: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Recieved OTP In Mail: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.GetOTPFromMailForSignUp()) {
				logResults.createLogs("Y", "PASS", "Successfully Navigated To IFrame: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Navigating To IFrame: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.OTPForSignUpExtracted()) {
				signupOTPfrommail = MeghLoginPage.SignupOTP;

				logResults.createLogs("Y", "PASS", "Successfully Extracted The OTP: " + signupOTPfrommail);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Extracting The OTP: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.TabSwitchBack()) {
				logResults.createLogs("Y", "PASS", "Successfully Navigated Back To Application: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Switching Between Tabs: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.EnterOTPSignUp(signupOTPfrommail)) {
				logResults.createLogs("Y", "PASS", "OTP Sent To YOP Mail: " + signupOTPfrommail);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While OTP Sent To YOP Mail: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.EnterCaptchaSignUp(captcha)) {
				logResults.createLogs("Y", "PASS", "Successfully Entered The Captcha: " + captcha);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering The Captcha: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.VerifyAndLoginButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On VerifyAndLoginButton: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On VerifyAndLoginButton: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.VMSSelected()) {
				logResults.createLogs("Y", "PASS", "Successfully Selected VMS: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting VMS: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.CMSSelected()) {
				logResults.createLogs("Y", "PASS", "Successfully Selected CMS: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting CMS: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.SaveButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On SaveButton: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On SaveButton: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.PlanF()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On PlanF: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On PlanF: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.MakePaymentButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On MakePaymentButton: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On MakePaymentButton: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.CardsCredit()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On Cards For Payment: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Cards/Credit: " + MeghLoginPage.getExceptionDesc());
			}

			System.out.println(cardnumber);

			if (MeghLoginPage.CardNumber(cardnumber)) {
				logResults.createLogs("Y", "PASS", "Successfully Entered The Card Number: " + cardnumber);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Card Number: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.CardExpiry(cardexpiry)) {
				logResults.createLogs("Y", "PASS", "Successfully Entered The Card ExpiryDate: " + cardexpiry);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering The Card ExpiryDate: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.CardCVV(cardcvv)) {
				logResults.createLogs("Y", "PASS", "Successfully Entered The Card CVV: " + cardcvv);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering The Card CVV: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.cardOwnerName(cardownername)) {
				logResults.createLogs("Y", "PASS", "Successfully Entered The Card OwnerName: " + cardownername);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering The Card OwnerName: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.ProceedButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On ProccedButton: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On ProccedButton: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.EnterPaymentOTP(paymentotp)) {
				logResults.createLogs("Y", "PASS", "Successfully Entered The Payment OTP: " + paymentotp);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering The Payment OTP: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.OTPConfirmButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On OTPConfirmButton: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On OTPConfirmButton: " + MeghLoginPage.getExceptionDesc());
			}

			Thread.sleep(2000);

			if (MeghLoginPage.BackToLoginButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On BackToLoginButton: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On BackToLoginButton: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.ApplicationLoaded()) {
				logResults.createLogs("Y", "PASS", "Application Loaded Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Application Is Not Loaded Completely: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.enterCompanyCode(companycode)) {
				logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + companycode);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering Companycode Name: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.loginWithPassButton()) {
				logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Login With Password Button: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.signinTextValidation()) {

				logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

			} else {
				logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.enterUserName(emailids)) {
				logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + emailids);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.enterPassword(createpassword)) {
				logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + createpassword);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.captchaText(captcha)) {
				logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.clickLoginButton()) {

				logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Login Button: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.MailCheck()) {

				logResults.createLogs("Y", "PASS", "Clicked On MailCheck Button: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On MailCheck Button: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.NewTabOpenAndMailinatorOpen(URL)) {
				logResults.createLogs("Y", "PASS", "Successfully Open The New Tab And URL Is Passed: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Opening New Tab URL Inputting: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.GetFreeTrailButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On GetFreeTrailButton: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On GetFreeTrailButton: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.PopupCloseButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On PopupCloseButton: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On PopupCloseButton: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.InBoxTextField(emailss)) {
				logResults.createLogs("Y", "PASS", "Successfully Inputted Emp Name: " + emailss);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputting Emp Name: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.SearchButtonOfMailinator()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On SearchButtonOfMailinator: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On SearchButtonOfMailinator: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.closeAllOtherTabsExceptCurrent()) {
				logResults.createLogs("Y", "PASS", "Except Application All Other Tabs Are Closed Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Other Tabs Aren't Closed: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.ActivationLinkRecieved()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On Activation Mail Link: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Activation Mail Link: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.GetOTPFromMailForSignUp()) {
				logResults.createLogs("Y", "PASS", "Successfully Navigated To IFrame: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Navigating To IFrame: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.ActivationLink()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On ActivationLink: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On ActivationLink: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.AccountActivationToLoginPage()) {
				logResults.createLogs("Y", "PASS", "Successfully Navigated Back To Application: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Navigating From Mailinator To Application: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.LoginHere()) {

				logResults.createLogs("Y", "PASS", "Clicked On LoginHere Button: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On LoginHere In New Tab: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.enterCompanyCode(companycode)) {
				logResults.createLogs("Y", "PASS", "Company Code Entered in Text Box: " + companycode);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering Companycode Name: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.loginWithPassButton()) {
				logResults.createLogs("Y", "PASS", "Login With Password Button Is Clicked: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Login With Password Button: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.signinTextValidation()) {

				logResults.createLogs("Y", "PASS", "Sign in Text Is Displayed: ");

			} else {
				logResults.createLogs("Y", "FAIL", "Sign in Text isn't Displayed: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.enterUserName(emailids)) {
				logResults.createLogs("Y", "PASS", "Username Is Entered In Username Text Field: " + emailids);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Username: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.enterPassword(createpassword)) {
				logResults.createLogs("Y", "PASS", "Password Is Entered In Password Text Field: " + createpassword);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Password: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.captchaText(captcha)) {
				logResults.createLogs("Y", "PASS", "Captcha Entered On Captcha Text Field: " + captcha);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering  Captcha: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.clickLoginButton()) {

				logResults.createLogs("Y", "PASS", "Clicked On Login Button: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Login Button: " + MeghLoginPage.getExceptionDesc());
			}

			Thread.sleep(2000);

			if (MeghLoginPage.CompanyDetails()) {

				logResults.createLogs("Y", "PASS", "Clicked On CompanyDetails: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On CompanyDetails Button: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.IndustryType(industrytype)) {
				logResults.createLogs("Y", "PASS", "Industry Type Selected In the Dropdown: " + industrytype);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Industry Type: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.UploadButton(imagepath)) {
				logResults.createLogs("Y", "PASS", "Image Is Selected As Logo: " + imagepath);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting Image: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.CompanyWebsite(companywebsite)) {
				logResults.createLogs("Y", "PASS", "Entered CompanyWebsite Successfully: " + companywebsite);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering Company Website: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.AddDetails()) {
				logResults.createLogs("Y", "PASS", "AddDetails Button Clicked Successfully: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddDetails: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.AddAddress()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On Add AddreessButton: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On Add AddreessButton : " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.CountryIndiaSelected(country)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected Country From CountryDropdown: " + country);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Country From CountryDropdown : " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.StateDropdownSelected(state)) {
				logResults.createLogs("Y", "PASS", "Successfully Selected State From StateDropdown: " + state);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting State From StateDropdown : " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.AddressTextfield(companyaddress)) {
				logResults.createLogs("Y", "PASS", "Successfully Enter The Company Address: " + companyaddress);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering The Company Address : " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.PinCode(zipcode)) {
				logResults.createLogs("Y", "PASS", "Successfully Enter The Company Address Zipcode: " + zipcode);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering The Company Address Zipcode : " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.AddAddressButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On AddAddress Button: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddAddress Button : " + MeghLoginPage.getExceptionDesc());
			}
			Thread.sleep(4000);

			if (MeghLoginPage.ContactDetails()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On ContactDetails: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicked On ContactDetails: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.PrimaryContactName(firstname)) {
				logResults.createLogs("Y", "PASS", "Contact Name Entered in Text Box: " + firstname);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering Contact Name: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.PrimaryContactNumber(phonenumber)) {
				logResults.createLogs("Y", "PASS", "Phone Number Entered in Text Box: " + phonenumber);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering Phone Number: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.PrimaryContactEmail(emailids)) {
				logResults.createLogs("Y", "PASS", "Email Id Entered in Text Box: " + emailids);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Email ID: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.AddContactButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On AddContactButton: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddContactButton: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.OfficeSetup()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On OfficeSetup: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On OfficeSetup: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.AddOffice()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On AddOffice: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddOffice: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.OfficeName(officename)) {
				logResults.createLogs("Y", "PASS", "Office Name Entered in Text Box: " + officename);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Office Name: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.CompanyLocationOfficeType(officetype)) {
				logResults.createLogs("Y", "PASS", "Company OfficeType Selected In the Dropdown: " + officetype);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Company OfficeType: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.OfficeAddress(companyaddress)) {
				logResults.createLogs("Y", "PASS", "Office Address Entered in Text Box: " + companyaddress);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Entering Office Address: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.OfficeCountry(country)) {
				logResults.createLogs("Y", "PASS", "Company's Country Selected In the Dropdown: " + country);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Company's Country: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.OfficeState(state)) {
				logResults.createLogs("Y", "PASS", "Company's State Selected In the Dropdown: " + state);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Company's State: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.AddOfficeSave()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On AddOfficeSave: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddOfficeSave: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.AddDepartment()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On AddDept: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Clicking On AddDept: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.DepartmentName(deptname)) {
				logResults.createLogs("Y", "PASS", "Dept Name Entered in Text Box: " + deptname);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Entering Dept Name: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.AssignOffice(officename)) {
				logResults.createLogs("Y", "PASS", "Company's Assign Office Selected In the Dropdown: " + officename);
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting Company's Assign Office: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.AddDeptSave()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On AddDeptSave: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On AddDeptSave: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.PoliciesButton()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On Policy Button: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Successfully Clicked On Policy Button: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.UseAsDefaultButton()) {

				logResults.createLogs("Y", "PASS", "Successfully Clicked On UseAsDefaultButton Button: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking on  UseAsDefaultButton: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.GeneralSettings()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On GeneralSettings Button: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking on  GeneralSettings: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.TimeFormat()) {
				logResults.createLogs("Y", "PASS", "TimeFormat Selected In the Dropdown: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting  TimeFormat: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.TimeZone()) {
				logResults.createLogs("Y", "PASS", " TimeZone Selected In the Dropdown: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting TimeZone: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.TwoStepVerification()) {
				logResults.createLogs("Y", "PASS", "TwoStepVerification Selected In the Dropdown: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting TwoStepVerification: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.DateFormat()) {
				logResults.createLogs("Y", "PASS", "DateFormat Selected In the Dropdown: ");
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Selecting DateFormat: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.FinancialYear()) {
				logResults.createLogs("Y", "PASS", "FinancialYear Selected In the Dropdown: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting FinancialYear: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.GeneralSettingsSave()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On GeneralSettingsSave: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On GeneralSettingsSave: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.PersonalSetting()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On PersonalSetting: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On PersonalSetting: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.EmployeeIDDropdown()) {
				logResults.createLogs("Y", "PASS", "EmployeeIDDropdown Selected In the Dropdown: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Selecting EmployeeIDDropdown: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.EmployeeID(deptname)) {
				logResults.createLogs("Y", "PASS", "Employee id Entered In The Text Field: " + deptname);
			} else {
				logResults.createLogs("Y", "FAIL", "Error While Inputing Employee ID: " + MeghLoginPage.getExceptionDesc());
			}

			if (MeghLoginPage.PersonalSettingSave()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On PersonalSettingSave: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On  PersonalSettingSave: " + MeghLoginPage.getExceptionDesc());
			}
			
	

			if (MeghLoginPage.GoToDashBoard()) {
				logResults.createLogs("Y", "PASS", "Successfully Clicked On GoToDashBoard: ");
			} else {
				logResults.createLogs("Y", "FAIL",
						"Error While Clicking On  GoToDashBoard: " + MeghLoginPage.getExceptionDesc());
			}}

			@AfterMethod(alwaysRun = true)
			void AfterEachmethod() {
				logResults.onlyLog();
				if (driver != null) {
					driver.quit();
				}
			}

			@AfterTest(alwaysRun = true)
			void cleanUp() {
				if (driver != null) {
					driver.quit();
				}

				if (driver != null) {
					driver.quit();
				}
			}

		}
