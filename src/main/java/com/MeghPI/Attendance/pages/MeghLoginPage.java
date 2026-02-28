package com.MeghPI.Attendance.pages;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.LogResults;
import utils.Pramod;
import utils.Utils;

public class MeghLoginPage {
	
	WebDriver driver;
	//LogResults logResults = new LogResults();
	private String exceptionDesc;
	Utils utils = new Utils(driver);
	public String extractedOTP = "";
	public String SignupOTP = "";
	public String companyCodes = "";
	public String extractedLoginOTP = "";
	public String bodyText = "";
	public String Empid = "";


	public ArrayList<String> windowList;
	
	

	public MeghLoginPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "btnLoginWithPwd")
	private WebElement loginWithPasswrdButton;

	@FindBy(xpath = "//h2[text()='Sign In !']")
	private WebElement signInText;// 1stTestCase

	@FindBy(xpath = "//label[@id='txtCompanyCode-error']")
	private WebElement companyCodeErrorMsg;// 2ndTestCase

	@FindBy(xpath = "//label[text()='Please Enter Valid Company code.']")
	private WebElement companyCodeValidationMsg;// 3rdTestCase

	@FindBy(xpath = "//button[@id='btnLoginWithOTP']")
	private WebElement loginWithOTP;// 4thTestCase

	@FindBy(xpath = "//input[@id='txtOTPUserName']")
	private WebElement loginWithOtpUserName;// 4thTestCase

	@FindBy(xpath = "//input[@id='txtCaptchaUserOTP']")
	private WebElement loginWithOtpCaptcha;// 4thTestCase

	@FindBy(xpath = "//button[@id='btnOTPReq']")
	private WebElement requestOTPButton;// 4thTestCase

	@FindBy(xpath = "//input[@id='OTPNumber']")
	private WebElement EnterOTPLogin;// 4thTestCase

	@FindBy(xpath = "//input[@id='txtCaptchaOTP']")
	private WebElement EnterCaptchaOTPLogin;// 4thTestCase

	@FindBy(xpath = "//button[@id='btnOTPVerfiy']")
	private WebElement VerifyAndLoginButtonOTP;// 4thTestCase

	@FindBy(xpath = "//div[text()='Invalid credentials for given company code.']")
	private WebElement InvalidMsg;// 8thTestCase

	@FindBy(xpath = "//h2[text()='Login to your account!']")
	private WebElement NavigateBackToCompanyCode;// 8thTestCase

	@FindBy(xpath = "//div[@class='toast-message']")
	private WebElement InvalidPasswordValidation;

	@FindBy(xpath = "//a[text()='Forgot password?']")
	private WebElement ForgotPassword;// 12thTestCase

	@FindBy(xpath = "//input[@id='txtEmailName']")
	private WebElement TxtEmailID;// 12thTestCase

	@FindBy(xpath = "//button[@id='btnOTPReqPW']")
	private WebElement RequestOTPFP;// 12thTestCase

	@FindBy(xpath = "//input[@id='txtOTPNumber']")
	private WebElement OTPTextField;// 12thTestCase

	@FindBy(id = "btnVerifyOTP")
	private WebElement VerifyAndContinueButton;// 12thTestCase

	@FindBy(xpath = "//a[@id='resendOTP']")
	private WebElement ResendOTP;// 12thTestCase

	@FindBy(xpath = "//div[text()='OTP has been sent successfully.']")
	private WebElement OTPSentSuccess;// 12thTestCase

	@FindBy(xpath = "//label[@id='txtPassword-error']")
	private WebElement PasswordCriteria;// 13thTestCase

	@FindBy(xpath = "//div[@id='divCompanyCode']/div[2]/a")
	private WebElement CreateAnAccount;// 14thTestCase

	@FindBy(xpath = "//input[@id='txtFirstName']")
	private WebElement FirstName;// 14thTestCase

	@FindBy(xpath = "//input[@id='txtLastName']")
	private WebElement LastName;// 14thTestCase

	@FindBy(xpath = "//input[@id='txtEmail']")
	private WebElement Email;// 14thTestCase

	@FindBy(xpath = "//input[@id='txtPhoneNo']")
	private WebElement PhoneNumber;// 14thTestCase

	@FindBy(xpath = "//input[@id='txtPassword']")
	private WebElement CreatePassword;// 14thTestCase

	@FindBy(xpath = "//input[@id='txtConfirmPassword']")
	private WebElement ConfirmPassword;// 14thTestCase

	@FindBy(xpath = "//button[@id='btnSignUpClick']")
	private WebElement SignUpButton;// 14thTestCase

	@FindBy(xpath = "//input[@id='txtCompanyName']")
	private WebElement CompanyName;// 14thTestCase

	@FindBy(xpath = "//input[@name='CompanyCode']")
	private WebElement CompanyCode;//

	@FindBy(xpath = "//select[@id='drpCompanyHeadcount']")
	private WebElement CompanyHeadCount;// 14thTestCase

	@FindBy(xpath = "//option[text()='51 To 100']")
	private WebElement CompanyHeadCountSelected;// 14thTestCase

	@FindBy(xpath = "//select[@id='drpIndustryType']")
	private WebElement IndustryType;// 14thTestCase

	@FindBy(xpath = "//option[text()='Software Industry']")
	private WebElement IndstryTypeSelected;// 14thTestCase

	@FindBy(xpath = "//select[@id='drpRoleName']")
	private WebElement YourRole;// 14thTestCase

	@FindBy(xpath = "//option[text()='Admin']")
	private WebElement YourRoleSelected;// 14thTestCase

	@FindBy(xpath = "//button[@id='btnComplete']")
	private WebElement CompleteButton;// 14thTestCase

	@FindBy(xpath = "//input[@id='SignupOTPNumber']")
	private WebElement EnterOTPSignUp;// 14thTestCase

	@FindBy(xpath = "//input[@id='txtSignupCaptchaOTP']")
	private WebElement EnterCaptchaSignUp;// 14thTestCase

	@FindBy(xpath = "//button[@id='signupBtnOTPVerfiy']")
	private WebElement VerifyAndLoginButton;// 14thTestCase

	@FindBy(xpath = "//a[text()='Resend OTP']")
	private WebElement ResendOTPSignup;// 14thTestCase

	@FindBy(xpath = "//input[@value='2' and @type='checkbox']")
	private WebElement VMSSelected;// 14thTestCase

	@FindBy(xpath = "//input[@value='3' and @type='checkbox']")
	private WebElement CMSSelected;// 14thTestCase

	@FindBy(xpath = "//button[@id='btnModuleSave']")
	private WebElement SaveButton;// 14thTestCase

	@FindBy(xpath = "//input[@type='radio' and @value='1']")
	private WebElement FreePlan;// 14thTestCase

	@FindBy(xpath = "//input[@data-id='6']")
	private WebElement PlanF;// 14thTestCase

	@FindBy(xpath = "//button[@id='btnPlanMakePayment']")
	private WebElement MakePaymentButton;// 14thTestCase

	@FindBy(xpath = "//p[text()='Cards (Credit/Debit)']")
	private WebElement CardsCredit;// 14thTestCase

	@FindBy(xpath = "//input[@id='cardNumber']")
	private WebElement CardNumber;// 14thTestCase

	@FindBy(xpath = "//input[@id='cardExpiry']")
	private WebElement CardExpiry;// 14thTestCase

	@FindBy(xpath = "//input[@id='cardCvv']")
	private WebElement CardCVV;// 14thTestCase

	@FindBy(xpath = "//input[@id='cardOwnerName']")
	private WebElement cardOwnerName;// 14thTestCase

	@FindBy(xpath = "//span[text()='PROCEED']")
	private WebElement ProceedButton;// 14thTestCase

	@FindBy(xpath = "//input[@id='password']")
	private WebElement EnterPaymentOTP;// 14thTestCase

	@FindBy(xpath = "//input[@id='submitBtn']")
	private WebElement OTPConfirmButton;// 14thTestCase

	@FindBy(xpath = "//button[text() = 'Send anyway']")
	private WebElement SendAnyWayButton;// 14thTestCase

	@FindBy(xpath = "//button[@id='btnBackToLogin']")
	private WebElement BackToLoginButton;// 14thTestCase

	@FindBy(xpath = "//p[text()='Company Details']")
	private WebElement CompanyDetails;// 14thTestCase

	@FindBy(xpath = "//input[@type='file']")
	private WebElement UploadButton;// 14thTestCase

	@FindBy(xpath = "//input[@id='txtCompanyWebsite']")
	private WebElement CompanyWebsite;// 14thTestCase

	@FindBy(xpath = "//button[text()='Add Details']")
	private WebElement AddDetails;// 14thTestCase

	@FindBy(xpath = "//div[text()='Record has been saved successfully']")
	private WebElement SuccessMsg;// 14thTestCase

	@FindBy(xpath = "//p[text()='Address']")
	private WebElement AddAddress; // 14TestCase

	@FindBy(xpath = "//span[@id='select2-drpCountryId-container']")
	private WebElement CountrySelectionDropdown; // 14TestCase //

	@FindBy(xpath = "//select[@id='drpCountryId']")
	private WebElement CountryIndiaSelected; // 14TestCase

	@FindBy(xpath = "//li[text()='India']")
	private WebElement IndiaSelected; // 14TestCase //

	@FindBy(xpath = "//span[@id='select2-drpStateId-container']")
	private WebElement StateDropdown; // 14TestCase //

	@FindBy(xpath = "//select[@id='drpStateId']")
	private WebElement StateDropdownSelected; // 14TestCase

	@FindBy(xpath = "//select[@id='drpCityId']")
	private WebElement SelectCityDropdown; // 14TestCase

	@FindBy(xpath = "//input[@id='txtCompanyAddress1']")
	private WebElement AddressTextfield; // 14TestCase

	@FindBy(xpath = "//input[@id='txtPinCode']")
	private WebElement PinCode; // 14TestCase

	@FindBy(xpath = "//button[@id='btnAddCompanyAddressDetails']")
	private WebElement AddAddressButton; // 14TestCase

	@FindBy(xpath = "//p[text()='Contact Details']")
	private WebElement ContactDetails; // 14TestCase

	@FindBy(xpath = "//input[@id='txtPrimaryContactName']")
	private WebElement PrimaryContactName; // 14TestCase

	@FindBy(xpath = "//input[@id='txtPrimaryContactNumber']")
	private WebElement PrimaryContactNumber; // 14TestCase

	@FindBy(xpath = "//input[@id='txtPrimaryContactEmail']")
	private WebElement PrimaryContactEmail; // 14TestCase

	@FindBy(xpath = "//button[@id='btnAddCompanyContactDetails']")
	private WebElement AddContactButton; // 14TestCase

	@FindBy(xpath = "(//p[text()='Office Setup'])[1]")
	private WebElement OfficeSetup; // 14TestCase

	@FindBy(xpath = "(//p[text()='Add Office'])[1]")
	private WebElement AddOffice; // 14TestCase

	@FindBy(xpath = "//input[@id='txtCompanyLocationName']")
	private WebElement OfficeName; // 14TestCase

	@FindBy(xpath = "//select[@id='drpCompanyLocationOfficeType']")
	private WebElement CompanyLocationOfficeType; // 14TestCase

	@FindBy(xpath = "//input[@id='txtCompanyLocationAddress']")
	private WebElement OfficeAddress; // 14TestCase

	@FindBy(xpath = "//select[@id='drpCompanyLocationCountry']")
	private WebElement OfficeCountry; // 14TestCase

	@FindBy(xpath = "//select[@id='drpCompanyLocationState']")
	private WebElement OfficeState; // 14TestCase

	@FindBy(xpath = "//select[@id='drpCompanyLocationCity']")
	private WebElement OfficeCity; // 14TestCase

	@FindBy(xpath = "//button[@id='btnCompanyLocationSave']")
	private WebElement AddOfficeSave; // 14TestCase

	@FindBy(xpath = "//p[text()='Add Department']")
	private WebElement AddDepartment; // 14TestCase

	@FindBy(xpath = "//input[@id='txtDepartmentName']")
	private WebElement DepartmentName; // 14TestCase

	@FindBy(xpath = "//select[@id='drpCompanyLocation']")
	private WebElement AssignOffice; // 14TestCase

	@FindBy(xpath = "//button[@id='btnDepartmentSave']")
	private WebElement AddDeptSave; // 14TestCase

	@FindBy(xpath = "//p[text()='Policies']")
	private WebElement PoliciesButton; // 14TestCase

	@FindBy(xpath = "//button[@id='btnDefaultPolicy']")
	private WebElement UseAsDefaultButton; // 14TestCase

	@FindBy(xpath = "//button[@id='general-settings-tab']")
	private WebElement GeneralSettings; // 14TestCase

	@FindBy(xpath = "//select[@id='drpTimeFormat']")
	private WebElement TimeFormat; // 14TestCase

	@FindBy(xpath = "//select[@id='drpTimeZone']")
	private WebElement TimeZone; // 14TestCase

	@FindBy(xpath = "//select[@id='drpTwoStepVerfication']")
	private WebElement TwoStepVerification; // 14TestCase

	@FindBy(xpath = "//select[@id='drpDateFormat']")
	private WebElement DateFormat; // 14TestCase

	@FindBy(xpath = "//select[@id='drpFinancialYear']")
	private WebElement FinancialYear; // 14TestCase

	@FindBy(xpath = "//button[@id='btnAddGeneralSetting']")
	private WebElement GeneralSettingsSave; // 14TestCase

	@FindBy(xpath = "(//p[text()='Personal Settings'])[1]")
	private WebElement PersonalSetting; // 14TestCase

	@FindBy(xpath = "//select[@id='drpIsEmployeeID']")
	private WebElement EmployeeIDDropdown; // 14TestCase

	@FindBy(xpath = "//input[@id='EmployeeID']")
	private WebElement EmployeeID; // 14TestCase

	@FindBy(xpath = "//button[@id='btnAddPersonalSetting']")
	private WebElement PersonalSettingSave; // 14TestCase

	@FindBy(xpath = "(//button[text()='Go to Dashboard'])[2]")
	private WebElement GoToDashBoard; // 14TestCase

	@FindBy(xpath = "//button[@id='v-pills-settings-tab']")
	private WebElement EmployeeEnroll; // 14TestCase

	@FindBy(xpath = "(//button[text()='Go to Dashboard'])[1]")
	private WebElement GoToDashboardModule; // 14TestCase

	@FindBy(xpath = "//a[text()='Get Email']")
	private WebElement MailCheck; // 14TestCase /click > switch again to mail

	@FindBy(xpath = "//a[text()='ACTIVATION_LINK']")
	private WebElement ActivationLink; // 14TestCase //inside iframe

	@FindBy(xpath = "//a[text()='Login Here']")
	private WebElement LoginHere; // 14TestCase

	@FindBy(xpath = "//div[@id='inbox_pane']/div[3]/div/div[4]/div/div/table/tbody/tr/td[contains(text(), 'Company Activation')]")
	private WebElement EmailLinkRecieved; // 14TestCase

	@FindBy(xpath = "//div[@id='inbox_pane']/div[3]/div/div[4]/div/div/table/tbody/tr/td[contains(text(), 'OTP for Sign')]")
	private WebElement OTPForSignUpInMailinator; // 14th TestCase

	@FindBy(xpath = "//div[@id='inbox_pane']/div[3]/div/div[4]/div/div/table/tbody/tr/td[contains(text(), 'OTP Request for Forgot')]")
	private WebElement OTPForForgotPassword; // 21st TestCase

	@FindBy(xpath = "//label[@id='txtUsername-error']")
	private WebElement UserNameEmpty; // 15TestCase

	@FindBy(xpath = "//a[text()='Forgot company code?']")
	private WebElement ForgotCompanyCode; // 19th TestCase

	@FindBy(xpath = "//input[@id='txtEmailId']")
	private WebElement ForgotCompanyEmailID; // 19th TestCase

	@FindBy(xpath = "//button[@id='btnReqCode']")
	private WebElement RequestCompanyCode; // 19th TestCase

	@FindBy(xpath = "//div[text()='Email Id not found.']")
	private WebElement InvalidUserName; // 19th TestCase

	@FindBy(xpath = "//li[text()='Invalid Credentials.']")
	private WebElement InvalidCredentials; // 20th TestCase

	@FindBy(xpath = "//input[@id='txtNewPassword']")
	private WebElement NewPassword; // 21st TestCase

	@FindBy(xpath = "//input[@id='txtConfirmPassword']")
	private WebElement NewConfirmPassword; // 21st TestCase

	@FindBy(xpath = "//button[@id='btnResetPW']")
	private WebElement PasswordSetButton; // 21st TestCase

	@FindBy(xpath = "//button[@id='LoginContinue']")
	private WebElement ContinueToLogin; // 21st TestCase

	@FindBy(xpath = "//input[@id='txtUsername']")
	private WebElement userName;

	@FindBy(xpath = "//input[@id='txtPassword']")
	private WebElement Passords;

	@FindBy(xpath = "//input[@id='txtCaptcha']")
	private WebElement captchaTextField;

	@FindBy(xpath = "//button[@id='btnSignin']")
	private WebElement signinButton;

	@FindBy(xpath = "//span[contains(text(), 'Good')]")
	private WebElement loginValidate;

	@FindBy(xpath = "//body[@id='addCustomClassForBody']/section/div/div/div[3]/ul[1]/li[5]")
	private WebElement loginValidateNotificationIcon;

	@FindBy(xpath = "//div[@id='divCompanyCode']/div[1]/h2")
	private WebElement ApplicationLoaded;

	@FindBy(xpath = "//h2[text()='LogIn with OTP']")
	private WebElement LoginWithOTPPageLoaded; // 4th TestCase

	@FindBy(xpath = "(//span[text()='GET FREE TRIAL'])[1]")
	private WebElement GetFreeTrailButton; // 4th TestCase

	@FindBy(xpath = "(//button[@aria-label='Close'])[2]")
	private WebElement PopupCloseButton; // 4th TestCase

	@FindBy(xpath = "//input[@id='inbox_field']")
	private WebElement InBoxTextField; // 4th TestCase

	@FindBy(xpath = "//button[@class='primary-btn']")
	private WebElement SearchButtonOfMailinator; // 4th TestCase

	@FindBy(xpath = "//div[@id='inbox_pane']/div[3]/div/div[4]/div/div/table/tbody/tr/td[contains(text(), 'One-Time Password')]")
	private WebElement FirstRecievedMailClick; // 4th TestCase

	@FindBy(xpath = "//iframe[@id='html_msg_body']")
	private WebElement HTMLMsgBodyInMailinator; // 4th TestCase

	@FindBy(xpath = "//body[1]/div[1]/p[2]")
	private WebElement MsgBodyInMailinator; // 4th TestCase

	@FindBy(xpath = "//body[@marginheight='0']")
	private WebElement SignUpMailBody; // 14th TestCase

	@FindBy(xpath = "(//div[@id='v-pills-tab'])[2]/../div[2]/div[1]/div/form[1]/div/div/div/div[1]/span")
	private WebElement GetEmpID; // 14th TestCase

	@FindBy(xpath = "//div[@id='inbox_pane']/div[3]/div/div[4]/div/div/table/tbody/tr/td[contains(text(), 'Your Company Code Retrieval')]")
	private WebElement CompanyCodeRetrievalLink; // 14th TestCase

	@FindBy(xpath = "(//input[@type='checkbox'])[1]/../label")
	private WebElement SelectCheckBox; // 14th TestCase

	@FindBy(xpath = "//div[@id='email_pane']/div/div[2]/div[2]/div")
	private WebElement DeleteButton; // 14th TestCase

	@FindBy(xpath = "//div[@id='email_pane']/div/div[1]/div[2]/a")
	private WebElement BackToInBox; // 14th TestCase

	@FindBy(xpath = "//label[@class='checkbox-label']")
	private List<WebElement> checkboxesOfMailinator;

	@FindBy(xpath = "//button[@aria-label='Delete Button']")
	private WebElement btndelete;
	
	@FindBy(xpath = "//button[text()='Continue without saving']")
	private WebElement ContinueWithoutSaving;
	
	@FindBy(xpath = "//img[@id='imgCaptcha']")
	private WebElement CaptchaLoaded;
	
	

	public boolean enterCompanyCode(String cmpcode) {
		try {
			
			utils.waitForEle(CompanyCode, "visible", "", 30);
			CompanyCode.sendKeys(cmpcode);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;

	}

	public boolean loginWithPassButton() {
		try {

			utils.waitForEle(loginWithPasswrdButton, "visible", "", 20);

			loginWithPasswrdButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean enterUserName(String user) {
		try {
			utils.waitForEle(userName, "visible", "", 20);
			userName.sendKeys(user);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;

	}

	public boolean enterPassword(String password) {
		try {
			utils.waitForEle(Passords, "visible", "", 20);
			Passords.sendKeys(password);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;

	}

	public boolean captchaText(String captcha) {
		try {
			utils.waitForEle(captchaTextField, "visible", "", 20);
			
			
			captchaTextField.sendKeys(captcha);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean clickLoginButton() {

		try {
			utils.waitForEle(signinButton, "visible", "", 30);
			

			signinButton.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}

		return true;

	}

	public boolean SignupValidation() {
		try {

			// Wait until login validation element is visible
			utils.waitForEle(loginValidate, "visible", "", 50);

			// If notification icon is not displayed, throw error

		} catch (Exception e) {
			exceptionDesc = e.getMessage();
			return false;
		}
		return true;
	}

	public boolean loginValidation() {

	    int maxRetries = 5;
	    int delay = 2000;
	    exceptionDesc = "";

	    try {

	        for (int attempt = 1; attempt <= maxRetries; attempt++) {

	            // ------------------------------------------------
	            // 1️⃣ Check Login Success Indicators
	            // ------------------------------------------------
	            if (utils.waitForEle(loginValidate, "visible", "", 30)
	                    || utils.waitForEle(loginValidateNotificationIcon, "visible", "", 30)) {

	                // 🔄 Refresh AFTER login success to load all modules
	                driver.navigate().refresh();
	                Thread.sleep(delay);

	                // Optional safety re-check after refresh
	                if (utils.waitForEle(loginValidate, "visible", "", 30)
	                        || utils.waitForEle(loginValidateNotificationIcon, "visible", "", 30)) {
	                    return true;
	                }
	            }

	            // ------------------------------------------------
	            // 2️⃣ Login not successful → Refresh & Retry
	            // ------------------------------------------------
	            driver.navigate().refresh();
	            Thread.sleep(delay);

	            // ------------------------------------------------
	            // 3️⃣ Still on Company sign-in page
	            // ------------------------------------------------
	            if (CompanyCode.isDisplayed()) {
	                exceptionDesc = "Attempt " + attempt +
	                        ": Login page still visible after refresh.";
	            }

	            if (attempt < maxRetries) {
	                Thread.sleep(delay);
	            }
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	    }

	    return false;
	}




	// firstcase
	public boolean signinTextValidation() {
	    int maxAttempts = 3;
	    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
	        try {
	            // Step 1: Wait for page load (generic)
	        	
	            Pramod.waitForPageToLoad(driver, 30);
	            
	            // Step 2: Wait for SignIn text (page-specific)
	            utils.waitForEle(signInText, "visible", "", 20);
	            utils.waitForEle(CaptchaLoaded, "visible", "", 30);
	            if (CaptchaLoaded.isDisplayed()) {
	                // Validate Forgot Password link as well
	                if (ForgotPassword.isDisplayed() && ForgotPassword.isEnabled() && CaptchaLoaded.isDisplayed()){
	                    return true;
	                } else {
	                    throw new Exception("Forgot Password link not visible or disabled.");
	                }
	            } else {
	                throw new Exception("Sign-in text not visible.");
	            }

	        } catch (Exception e) {
	            exceptionDesc = "Attempt " + attempt + " failed: " + e.getMessage();

	            if (attempt == maxAttempts) {
	                return false; // stop retrying after last attempt
	            }
	        }
	    }
	    return false;
	}



	// 2ndTestCase
	public boolean companyCodeErrorMsg() {
		try {

			utils.waitForEle(companyCodeErrorMsg, "visible", "", 30);
			companyCodeErrorMsg.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 3rdTestCase

	public boolean companyCodeValidationMsg() {
		try {

			utils.waitForEle(companyCodeValidationMsg, "visible", "", 30);
			companyCodeValidationMsg.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 4th TestCase

	public boolean loginWithOTP() {
		try {

			utils.waitForEle(loginWithOTP, "visible", "", 30);
			loginWithOTP.isDisplayed();
			loginWithOTP.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean loginWithOtpUserName(String user) {
		try {

			utils.waitForEle(loginWithOtpUserName, "visible", "", 30);
			loginWithOtpUserName.isDisplayed();
			loginWithOtpUserName.sendKeys(user);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean loginWithOtpCaptcha(String captcha) {
		try {

			utils.waitForEle(loginWithOtpCaptcha, "visible", "", 30);
			loginWithOtpCaptcha.isDisplayed();
			loginWithOtpCaptcha.sendKeys(captcha);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean requestOTPButton() {
		try {

			utils.waitForEle(requestOTPButton, "visible", "", 30);
			requestOTPButton.isDisplayed();
			requestOTPButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EnterOTPLogin(String loginWithOTP) {
		try {

			utils.waitForEle(EnterOTPLogin, "visible", "", 30);
			EnterOTPLogin.isDisplayed();
			EnterOTPLogin.sendKeys(loginWithOTP);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EnterCaptchaOTPLogin(String captcha) {
		try {

			utils.waitForEle(EnterCaptchaOTPLogin, "visible", "", 30);
			EnterCaptchaOTPLogin.isDisplayed();
			EnterCaptchaOTPLogin.sendKeys(captcha);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean VerifyAndLoginButtonOTP() {
		try {

			Thread.sleep(2000);
			return Utils.safeClick(driver, VerifyAndLoginButtonOTP);


		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
	}

	// 8thTestCase
	public boolean invalidMsg() {
		try {

			utils.waitForEle(InvalidMsg, "visible", "", 30);
			InvalidMsg.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 9th TestCase
	public boolean InvalidPasswordValidation() {
		try {

			utils.waitForEle(InvalidPasswordValidation, "visible", "", 30);
			InvalidPasswordValidation.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 11th TestCase

	public boolean NavigateBackToCompanyCode() {
		try {

			utils.waitForEle(NavigateBackToCompanyCode, "visible", "", 30);
			NavigateBackToCompanyCode.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 12TestCase
	public boolean ForgotPassword() {
		try {

			utils.waitForEle(ForgotPassword, "visible", "", 30);
			ForgotPassword.isDisplayed();
			ForgotPassword.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean TxtEmailID(String user) {
		try {

			utils.waitForEle(TxtEmailID, "visible", "", 30);
			TxtEmailID.isDisplayed();
			TxtEmailID.sendKeys(user);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RequestOTPFP() {
		try {

			utils.waitForEle(RequestOTPFP, "visible", "", 30);
			RequestOTPFP.isDisplayed();
			RequestOTPFP.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OTPTextField(String OTP) {
		try {

			utils.waitForEle(OTPTextField, "visible", "", 30);
			OTPTextField.isDisplayed();
			OTPTextField.sendKeys(OTP);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean VerifyAndContinueButton() {
		try {
			utils.waitForEle(VerifyAndContinueButton, "enable", "", 20);
			VerifyAndContinueButton.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ResendOTP() {
		try {

			utils.waitForEle(ResendOTP, "visible", "", 30);
			ResendOTP.isDisplayed();
			ResendOTP.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OTPSentSuccess() {
		try {

			utils.waitForEle(OTPSentSuccess, "visible", "", 30);
			OTPSentSuccess.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 13thTestCase
	public boolean PasswordCriteria() {
		try {

			utils.waitForEle(PasswordCriteria, "visible", "", 30);
			PasswordCriteria.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 14thTestCase
	public boolean CreateAnAccount() {
		try {
			Thread.sleep(2000);
			utils.waitForEle(CreateAnAccount, "visible", "", 30);

			CreateAnAccount.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean FirstName(String firstname) {
		try {

			utils.waitForEle(FirstName, "visible", "", 30);
			FirstName.isDisplayed();
			FirstName.sendKeys(firstname);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LastName(String lastname) {
		try {

			utils.waitForEle(LastName, "visible", "", 30);
			LastName.isDisplayed();
			LastName.sendKeys(lastname);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean Email(String emailid) {
		try {

			utils.waitForEle(Email, "visible", "", 30);
			Email.isDisplayed();
			Email.sendKeys(emailid);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PhoneNumber(String phonenumber) {
		try {

			utils.waitForEle(PhoneNumber, "visible", "", 30);
			PhoneNumber.isDisplayed();
			PhoneNumber.sendKeys(phonenumber);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CreatePassword(String createpassword) {
		try {

			utils.waitForEle(CreatePassword, "visible", "", 30);
			CreatePassword.isDisplayed();
			CreatePassword.sendKeys(createpassword);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ConfirmPassword(String confirmpassword) {
		try {

			utils.waitForEle(ConfirmPassword, "visible", "", 30);
			ConfirmPassword.isDisplayed();
			ConfirmPassword.sendKeys(confirmpassword);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean SignUpButton() {
		try {

			utils.waitForEle(SignUpButton, "visible", "", 30);
			SignUpButton.isDisplayed();
			SignUpButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CompanyName(String companyname) {
		try {

			utils.waitForEle(CompanyName, "visible", "", 30);
			CompanyName.isDisplayed();
			CompanyName.sendKeys(companyname);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CompanyCode(String companycodes) {
		try {

			utils.waitForEle(CompanyCode, "visible", "", 30);
			CompanyCode.isDisplayed();
			CompanyCode.sendKeys(companycodes);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean selectCompanyHeadCount(String headcount) {
		try {
			Thread.sleep(3000);
			utils.waitForEle(CompanyHeadCount, "visible", "", 30);
			Select select = new Select(CompanyHeadCount);
			select.selectByVisibleText(headcount);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean IndustryType(String industrytype) {
		try {
			utils.waitForEle(IndustryType, "visible", "", 30);
			Select select = new Select(IndustryType);
			select.selectByVisibleText(industrytype);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean YourRole(String yourrole) {
		try {
			utils.waitForEle(YourRole, "visible", "", 30);
			Select select = new Select(YourRole);
			select.selectByVisibleText(yourrole);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CompleteButton() {
		try {

			utils.waitForEle(CompleteButton, "visible", "", 30);
			CompleteButton.isDisplayed();
			CompleteButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EnterOTPSignUp1() {
		try {

			utils.waitForEle(EnterOTPSignUp, "visible", "", 30);
			EnterOTPSignUp.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EnterOTPSignUp(String OTP) {
		try {

			utils.waitForEle(EnterOTPSignUp, "visible", "", 30);
			EnterOTPSignUp.isDisplayed();
			EnterOTPSignUp.sendKeys(OTP);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EnterCaptchaSignUp(String captcha) {
		try {

			utils.waitForEle(EnterCaptchaSignUp, "visible", "", 30);
			EnterCaptchaSignUp.isDisplayed();
			EnterCaptchaSignUp.sendKeys(captcha);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean VerifyAndLoginButton() {
		try {

			utils.waitForEle(VerifyAndLoginButton, "visible", "", 30);
			VerifyAndLoginButton.isDisplayed();
			VerifyAndLoginButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean VMSSelected() {
		try {

			utils.waitForEle(VMSSelected, "visible", "", 30);
			VMSSelected.isDisplayed();
			VMSSelected.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CMSSelected() {
		try {

			utils.waitForEle(CMSSelected, "visible", "", 30);
			CMSSelected.isDisplayed();
			CMSSelected.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean SaveButton() {
		try {

			utils.waitForEle(SaveButton, "visible", "", 30);
			SaveButton.isDisplayed();
			SaveButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PlanF() {
		try {
			Thread.sleep(2000);
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			exe.executeScript("arguments[0].scrollIntoView(true);", PlanF);

			utils.waitForEle(PlanF, "visible", "", 20);
			PlanF.isDisplayed();
			PlanF.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean MakePaymentButton() {
		try {
			
			utils.waitForEle(MakePaymentButton, "visible", "", 20);
			MakePaymentButton.isDisplayed();
			MakePaymentButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CardsCredit() {
		try {
			
			utils.waitForEle(CardsCredit, "visible", "", 40);
			
			CardsCredit.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CardNumber(String cardnumber) {
		try {

			utils.waitForEle(CardNumber, "visible", "", 30);
			CardNumber.isDisplayed();
			CardNumber.sendKeys(cardnumber);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CardExpiry(String cardexpiry) {
		try {

			utils.waitForEle(CardExpiry, "visible", "", 30);
			CardExpiry.isDisplayed();
			CardExpiry.sendKeys(cardexpiry);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CardCVV(String cardcvv) {
		try {

			utils.waitForEle(CardCVV, "visible", "", 30);
			CardCVV.isDisplayed();
			CardCVV.sendKeys(cardcvv);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean cardOwnerName(String cardownername) {
		try {

			utils.waitForEle(cardOwnerName, "visible", "", 30);
			cardOwnerName.isDisplayed();
			cardOwnerName.sendKeys(cardownername);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ProceedButton() {
		try {

			utils.waitForEle(ProceedButton, "visible", "", 15);
			ProceedButton.isDisplayed();
			ProceedButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EnterPaymentOTP(String paymentotp) {
		try {

			utils.waitForEle(EnterPaymentOTP, "visible", "", 30);
		
			EnterPaymentOTP.sendKeys(paymentotp);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OTPConfirmButton() {
		try {

			utils.waitForEle(OTPConfirmButton, "visible", "", 30);
		
			OTPConfirmButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean BackToLoginButton() {
		try {
			Thread.sleep(2000);
			return Utils.safeClick(driver, BackToLoginButton);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
	}

	public boolean CompanyDetails() {
		try {
			Thread.sleep(2000);
			utils.waitForEle(CompanyDetails, "visible", "", 30);
		
			CompanyDetails.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean UploadButton(String imgpath) {
		try {
			Thread.sleep(3000);
			// Only set file detector if it's a real RemoteWebDriver instance
			if (driver instanceof RemoteWebDriver && !(driver.getClass().getName().contains("ChromeDriver")
					|| driver.getClass().getName().contains("ChromiumDriver"))) {
				((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
			}

			File file = new File(imgpath);
			String canonicalPath = file.getCanonicalPath();

			utils.waitForEle(UploadButton, "visible", "", 30);
			UploadButton.sendKeys(canonicalPath);

		} catch (Exception e) {
			exceptionDesc = e.getMessage();
			return false;
		}
		return true;
	}

	public boolean CompanyWebsite(String companywebsite) {
		try {

			utils.waitForEle(CompanyWebsite, "visible", "", 30);
			CompanyWebsite.isDisplayed();
			CompanyWebsite.sendKeys(companywebsite);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AddDetails() {
		try {

			utils.waitForEle(AddDetails, "visible", "", 30);
			AddDetails.isDisplayed();
			AddDetails.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean SuccessMsg() {
		try {

			utils.waitForEle(SuccessMsg, "visible", "", 30);
			SuccessMsg.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AddAddress() {
		try {

			utils.waitForEle(AddAddress, "visible", "", 30);
			AddAddress.isDisplayed();
			AddAddress.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CountryIndiaSelected(String country) {
		try {
			utils.waitForEle(CountryIndiaSelected, "visible", "", 30);
			Select select = new Select(CountryIndiaSelected);
			select.selectByVisibleText(country);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean StateDropdownSelected(String state) {
		try {
			Thread.sleep(4000);
			utils.waitForEle(StateDropdownSelected, "visible", "", 30);
			Select select = new Select(StateDropdownSelected);
			select.selectByVisibleText(state);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean SelectCityDropdown() {
		try {
			Thread.sleep(4000);
			utils.waitForEle(SelectCityDropdown, "visible", "", 30);
			Select select = new Select(SelectCityDropdown);
			select.selectByIndex(1);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AddressTextfield(String companyaddress) {
		try {

			utils.waitForEle(AddressTextfield, "visible", "", 30);
			AddressTextfield.isDisplayed();
			AddressTextfield.sendKeys(companyaddress);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PinCode(String zipcode) {
		try {

			utils.waitForEle(PinCode, "visible", "", 30);
			PinCode.isDisplayed();
			PinCode.sendKeys(zipcode);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AddAddressButton() {
		try {
          Thread.sleep(4000);
            utils.waitForEle(AddAddressButton, "visible", "", 30);
	
			AddAddressButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ContactDetails() {
		try {
			Thread.sleep(4000);
			utils.waitForEle(ContactDetails, "visible", "", 30);
			ContactDetails.isDisplayed();
			ContactDetails.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PrimaryContactName(String firstname) {
		try {

			utils.waitForEle(PrimaryContactName, "visible", "", 30);
			PrimaryContactName.isDisplayed();
			PrimaryContactName.sendKeys(firstname);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PrimaryContactNumber(String phonenumber) {
		try {

			utils.waitForEle(PrimaryContactNumber, "visible", "", 30);
			PrimaryContactNumber.isDisplayed();
			PrimaryContactNumber.sendKeys(phonenumber);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PrimaryContactEmail(String emailid) {
		try {

			utils.waitForEle(PrimaryContactEmail, "visible", "", 30);
			PrimaryContactEmail.isDisplayed();
			PrimaryContactEmail.sendKeys(emailid);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AddContactButton() {
		try {

			utils.waitForEle(AddContactButton, "visible", "", 30);
			AddContactButton.isDisplayed();
			AddContactButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OfficeSetup() {
		try {

			utils.waitForEle(OfficeSetup, "visible", "", 30);
			OfficeSetup.isDisplayed();
			OfficeSetup.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AddOffice() {
		try {

			utils.waitForEle(AddOffice, "visible", "", 30);
			AddOffice.isDisplayed();
			AddOffice.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OfficeName(String officename) {
		try {

			utils.waitForEle(OfficeName, "visible", "", 30);
			OfficeName.isDisplayed();
			OfficeName.sendKeys(officename);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CompanyLocationOfficeType(String officetype) {
		try {
			utils.waitForEle(CompanyLocationOfficeType, "visible", "", 30);
			Select select = new Select(CompanyLocationOfficeType);
			select.selectByVisibleText(officetype);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OfficeAddress(String companyaddress) {
		try {

			utils.waitForEle(OfficeAddress, "visible", "", 30);
			OfficeAddress.isDisplayed();
			OfficeAddress.sendKeys(companyaddress);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OfficeCountry(String country) {
		try {
			utils.waitForEle(OfficeCountry, "visible", "", 30);
			Select select = new Select(OfficeCountry);
			select.selectByVisibleText(country);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OfficeState(String state) {
		try {
			Thread.sleep(4000);
			utils.waitForEle(OfficeState, "visible", "", 30);
			Select select = new Select(OfficeState);
			select.selectByVisibleText(state);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OfficeCity() {
		try {
			Thread.sleep(3000);
			utils.waitForEle(OfficeCity, "visible", "", 30);
			Select select = new Select(OfficeCity);
			select.selectByIndex(1);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AddOfficeSave() {
		try {

			utils.waitForEle(AddOfficeSave, "visible", "", 30);
			AddOfficeSave.isDisplayed();
			AddOfficeSave.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AddDepartment() {
		try {

			utils.waitForEle(AddDepartment, "visible", "", 30);
			AddDepartment.isDisplayed();
			AddDepartment.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DepartmentName(String deptname) {
		try {

			utils.waitForEle(DepartmentName, "visible", "", 30);
			DepartmentName.isDisplayed();
			DepartmentName.sendKeys(deptname);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AssignOffice(String officename) {
		try {
			Thread.sleep(3000);
			utils.waitForEle(AssignOffice, "visible", "", 30);
			Select select = new Select(AssignOffice);
			select.selectByVisibleText(officename);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AddDeptSave() {
		try {

			utils.waitForEle(AddDeptSave, "visible", "", 30);
			AddDeptSave.isDisplayed();
			AddDeptSave.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PoliciesButton() {
		try {

			utils.waitForEle(PoliciesButton, "visible", "", 30);
			PoliciesButton.isDisplayed();
			PoliciesButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean UseAsDefaultButton() {

		try {
			Thread.sleep(4000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", UseAsDefaultButton);
			Thread.sleep(4000);
			utils.waitForEle(UseAsDefaultButton, "visible", "", 30);
			UseAsDefaultButton.isDisplayed();
			UseAsDefaultButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean GeneralSettings() {
		try {

			utils.waitForEle(GeneralSettings, "visible", "", 30);
			GeneralSettings.isDisplayed();
			GeneralSettings.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean TimeFormat() {
		try {
			Thread.sleep(3000);
			utils.waitForEle(TimeFormat, "visible", "", 30);
			Select select = new Select(TimeFormat);
			select.selectByVisibleText("24 hours");

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean TimeZone() {
		try {
			Thread.sleep(3000);
			utils.waitForEle(TimeZone, "visible", "", 30);
			Select select = new Select(TimeZone);
			select.selectByVisibleText("(UTC+05:30) Chennai, Kolkata, Mumbai, New Delhi (India Standard Time)");

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean TwoStepVerification() {
		try {
			Thread.sleep(3000);
			utils.waitForEle(TwoStepVerification, "visible", "", 30);
			Select select = new Select(TwoStepVerification);
			select.selectByVisibleText("No");

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DateFormat() {
		try {
			Thread.sleep(3000);
			utils.waitForEle(DateFormat, "visible", "", 30);
			Select select = new Select(DateFormat);
			select.selectByIndex(2);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean FinancialYear() {
		try {
			Thread.sleep(3000);
			utils.waitForEle(FinancialYear, "visible", "", 30);
			Select select = new Select(FinancialYear);
			select.selectByIndex(2);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean GeneralSettingsSave() {
		try {

			utils.waitForEle(GeneralSettingsSave, "visible", "", 30);
			GeneralSettingsSave.isDisplayed();
			GeneralSettingsSave.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PersonalSetting() {
		try {

			utils.waitForEle(PersonalSetting, "visible", "", 30);
			PersonalSetting.isDisplayed();
			PersonalSetting.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmployeeIDDropdown() {
		try {
			Thread.sleep(3000);
			utils.waitForEle(EmployeeIDDropdown, "visible", "", 30);
			Select select = new Select(EmployeeIDDropdown);
			select.selectByVisibleText("No");

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean EmployeeID(String deptname) {
		try {

			utils.waitForEle(EmployeeID, "visible", "", 30);
			EmployeeID.isDisplayed();
			EmployeeID.sendKeys(deptname);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PersonalSettingSave() {
		try {

			utils.waitForEle(PersonalSettingSave, "visible", "", 30);
			PersonalSettingSave.isDisplayed();
			PersonalSettingSave.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean GoToDashBoard() {
		try {
			Thread.sleep(2000);

			if (utils.waitForEle(GoToDashBoard, "visible", "", 30)) {
				GoToDashBoard.click();
				Thread.sleep(3000);
			} else {
				// GoToDashBoard not visible — perform alternate flow
				utils.waitForEle(EmployeeEnroll, "clickable", "", 30);
				EmployeeEnroll.click();

				utils.waitForEle(GoToDashboardModule, "clickable", "", 30);
				GoToDashboardModule.click();

				Thread.sleep(3000);
			}

		} catch (Exception e) {
			exceptionDesc = e.getMessage();
			return false;
		}
		return true;
	}

	public boolean MailCheck() {
		try {

			utils.waitForEle(MailCheck, "visible", "", 30);
			MailCheck.isDisplayed();
			MailCheck.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LoginHere() {
		try {

			utils.waitForEle(LoginHere, "visible", "", 30);
			LoginHere.isDisplayed();

			LoginHere.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean SendAnyWayButton() {
		try {

			utils.waitForEle(SendAnyWayButton, "visible", "", 30);
			SendAnyWayButton.isDisplayed();

			SendAnyWayButton.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 15th Test Case
	public boolean UserNameEmpty() {
		try {

			utils.waitForEle(UserNameEmpty, "visible", "", 30);
			UserNameEmpty.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 19th TestCase
	public boolean ForgotCompanyCode() {
		try {

			utils.waitForEle(ForgotCompanyCode, "visible", "", 30);
			ForgotCompanyCode.isDisplayed();
			ForgotCompanyCode.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ForgotCompanyEmailID(String user) {
		try {

			utils.waitForEle(ForgotCompanyEmailID, "visible", "", 30);
			ForgotCompanyEmailID.isDisplayed();
			ForgotCompanyEmailID.sendKeys(user);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean RequestCompanyCode() {
		try {

			utils.waitForEle(RequestCompanyCode, "visible", "", 30);
			RequestCompanyCode.isDisplayed();
			RequestCompanyCode.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean InvalidUserName() {
		try {

			utils.waitForEle(InvalidUserName, "visible", "", 30);
			InvalidUserName.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 20th TestCase
	public boolean InvalidCredentials() {
		try {

			utils.waitForEle(InvalidCredentials, "visible", "", 30);
			InvalidCredentials.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// Forgot Password Get OTP

//Get Signup OTP

//21st TestCase

	public boolean NewPassword(String password) {
		try {

			utils.waitForEle(NewPassword, "visible", "", 30);
			NewPassword.isDisplayed();
			NewPassword.sendKeys(password);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean NewConfirmPassword(String password) {
		try {

			utils.waitForEle(NewConfirmPassword, "visible", "", 30);
			NewConfirmPassword.isDisplayed();
			NewConfirmPassword.sendKeys(password);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PasswordSetButton() {
		try {

			utils.waitForEle(PasswordSetButton, "visible", "", 30);
			PasswordSetButton.isDisplayed();
			PasswordSetButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ContinueToLogin() {
		try {
			utils.waitForEle(ContinueToLogin, "visible", "", 30);
			ContinueToLogin.isDisplayed();
			ContinueToLogin.click();
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// Login With OTP
	public boolean GetOTPFromMailForLogin() {
		try {

			driver.switchTo().frame(HTMLMsgBodyInMailinator);

			System.out.println(HTMLMsgBodyInMailinator);

			Thread.sleep(4000);

			System.out.println(MsgBodyInMailinator);

			bodyText = MsgBodyInMailinator.getAttribute("innerHTML");
			System.out.println("HTML content: " + bodyText);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OTPExtractionFromBody() {
		try {

			try {
				String otpRaw = bodyText.split("Your OTP:")[1];
				extractedLoginOTP = otpRaw.replaceAll("[^0-9]", ""); // Extract only digits

				System.out.println(extractedOTP + " this is OTP");
			} catch (Exception e) {
				System.out.println("Failed to extract OTP: " + e.getMessage());
			}

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean TabSwitchBack() {
		try {

			driver.switchTo().window(windowList.get(0));

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ApplicationLoaded() {
	    int maxRetries = 2; // you can increase this if needed
	    for (int attempt = 1; attempt <= maxRetries; attempt++) {
	        try {
	        	driver.navigate().refresh();
	            utils.waitForEle(ApplicationLoaded, "visible", "", 30);
	            
	            return true; // element found, page loaded successfully
	        } catch (Exception e) {
	            exceptionDesc = e.getMessage();
	            if (attempt < maxRetries) {
	                System.out.println("Application not loaded, refreshing... (Attempt " + attempt + ")");
	                driver.navigate().refresh();
	                utils.waitForEle(ApplicationLoaded, "visible", "", 30);
	            } else {
	                System.out.println("Application failed to load after retries.");
	                return false;
	            }
	        }
	    }
	    return false;
	}


	public boolean LoginWithOTPPageLoaded() {
		try {
			Thread.sleep(3000);
			utils.waitForEle(LoginWithOTPPageLoaded, "visible", "", 30);
			LoginWithOTPPageLoaded.isDisplayed();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// 4th TestCase

	public boolean GetFreeTrailButton() {
		try {
			utils.waitForEle(GetFreeTrailButton, "visible", "", 15);
			GetFreeTrailButton.isDisplayed();
			GetFreeTrailButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean PopupCloseButton() {
		try {
			utils.waitForEle(PopupCloseButton, "visible", "", 15);
			PopupCloseButton.isDisplayed();
			PopupCloseButton.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean InBoxTextField(String emailforyop) {
		try {
			Thread.sleep(6000);

			utils.waitForEle(InBoxTextField, "visible", "", 30);

			InBoxTextField.clear();
			InBoxTextField.sendKeys(emailforyop);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean SearchButtonOfMailinator() {
		try {
			utils.waitForEle(SearchButtonOfMailinator, "visible", "", 15);
			SearchButtonOfMailinator.isDisplayed();
			SearchButtonOfMailinator.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean FirstRecievedMailClick() {
		try {
			Thread.sleep(20000);
			utils.waitForEle(FirstRecievedMailClick, "visible", "", 300);
			FirstRecievedMailClick.isDisplayed();
			FirstRecievedMailClick.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean NewTabOpenAndMailinatorOpen(String URL) {
		try {
			Thread.sleep(2000);

			((JavascriptExecutor) driver).executeScript("window.open()");

			Set<String> windows = driver.getWindowHandles();
			windowList = new ArrayList<String>(windows); // Convert to list

			// Example: switch to 2nd window (index 1)
			driver.switchTo().window(windowList.get(1));
			driver.get(URL);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean closeAllOtherTabsExceptCurrent() {
		try {
			String currentHandle = driver.getWindowHandle(); // Get current tab handle
			Set<String> allHandles = driver.getWindowHandles(); // Get all tab handles

			for (String handle : allHandles) {
				if (!handle.equals(currentHandle)) {
					driver.switchTo().window(handle);
					driver.close(); // Close the tab
				}
			}

			// Switch back to the original current tab
			driver.switchTo().window(currentHandle);
			return true;
		} catch (Exception e) {
			exceptionDesc = "Error while closing other tabs: " + e.getMessage();
			return false;
		}
	}

	public boolean OTPForSignUpInMailinator() {
		try {

			utils.waitForEle(OTPForSignUpInMailinator, "visible", "", 30);
			OTPForSignUpInMailinator.isDisplayed();
			OTPForSignUpInMailinator.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// Signup OTP
	public boolean GetOTPFromMailForSignUp() {
		try {

			driver.switchTo().frame(HTMLMsgBodyInMailinator);

			System.out.println(HTMLMsgBodyInMailinator);

			Thread.sleep(4000);

			System.out.println(MsgBodyInMailinator);

			bodyText = SignUpMailBody.getAttribute("innerHTML");
			System.out.println("HTML content: " + bodyText);

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean OTPForSignUpExtracted() {
		try {

			SignupOTP = bodyText.split("OTP")[1].replaceAll("[^0-9]", "");
			System.out.println(SignupOTP + "this is OTP");

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	// Activation Link
	public boolean ActivationLinkRecieved() {
		try {

			utils.waitForEle(EmailLinkRecieved, "visible", "", 200);
			EmailLinkRecieved.isDisplayed();
			EmailLinkRecieved.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ActivationLink() {
		try {

			utils.waitForEle(ActivationLink, "visible", "", 30);
			ActivationLink.isDisplayed();
			ActivationLink.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AccountActivationToLoginPage() {
		try {
			Thread.sleep(4000);
			Set<String> windowHandles = driver.getWindowHandles();
			boolean found = false;

			for (String handle : windowHandles) {
				driver.switchTo().window(handle);

				if (driver.getTitle().equalsIgnoreCase("MeghPI - Login")) {
					found = true;
					break;
				}
			}

			if (!found) {
				exceptionDesc = "Tab with title 'MeghPI - Login' not found. Current title: " + driver.getTitle();
				return false;
			}

			return true;

		} catch (Exception e) {
			exceptionDesc = "Error while switching to MeghPI Login tab: " + e.getMessage();
			return false;
		}
	}

	// 21st TestCase

	public boolean OTPForForgotPassword()  {
		int attempts = 0;

		while (attempts < 2) {
			try {

				utils.waitForEle(OTPForForgotPassword, "visible", "", 90);

				if (OTPForForgotPassword.isDisplayed()) {
					OTPForForgotPassword.click();
					return true;
				} else {
					throw new Exception("OTP element is not displayed.");
				}

			} catch (Exception e) {
				exceptionDesc = "Attempt " + (attempts + 1) + " failed: " + e.getMessage();
				attempts++;

				if (attempts < 2) {
					driver.navigate().refresh();
					 // small wait after refresh
				} else {
					return false;
				}
			}
		}

		return false;
	}

	public boolean ForgotPasswordOTPExtracted() {
		try {
			// Check if the bodyText contains the OTP section
			if (bodyText.contains("Your OTP:")) {
				String otpRaw = bodyText.split("Your OTP:")[1].trim();
				extractedOTP = otpRaw.replaceAll("[^0-9]", ""); // Extract digits only

				System.out.println(extractedOTP + " this is OTP");

				if (extractedOTP.isEmpty()) {
					throw new Exception("OTP extracted is empty.");
				}

				return true;
			} else {
				throw new Exception("'Your OTP:' text not found in body.");
			}

		} catch (Exception e) {
			exceptionDesc = "Failed to extract OTP: " + e.getMessage();
			return false;
		}
	}

	public boolean GetEmpID()  {
		
		int attempts = 0;

		while (attempts < 2) {
			try {
				Thread.sleep(3000);
				utils.waitForEle(GetEmpID, "visible", "", 30);

				if (GetEmpID.isDisplayed()) {
					Empid = GetEmpID.getText();
					return true;
				} else {
					throw new Exception("GetEmpID element is not displayed.");
				}

			} catch (Exception e) {
				exceptionDesc = "Attempt " + (attempts + 1) + " failed: " + e.getMessage();
				attempts++;

				if (attempts < 2) {
					driver.navigate().refresh();
					try {
						Thread.sleep(3000); // optional pause after refresh
					} catch (InterruptedException ie) {
						Thread.currentThread().interrupt();
					}
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public boolean CompanyCodeRetrievalLink() {
		try {

			utils.waitForEle(CompanyCodeRetrievalLink, "visible", "", 30);
			CompanyCodeRetrievalLink.isDisplayed();
			CompanyCodeRetrievalLink.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean CompanyCodeFetching() {
		try {
			Pattern pattern = Pattern.compile("Your Company Code\\(s\\):\\s*<b>(\\w+)", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(bodyText);

			if (matcher.find()) {
				companyCodes = matcher.group(1).trim(); // Extract and trim the company code
				System.out.println(companyCodes + " is the extracted Company Code");
			} else {
				System.out.println("Company Code not found in email body.");
			}
		} catch (Exception e) {
			System.out.println("Failed to extract Company Code: " + e.getMessage().toString());
		}
		return true;
	}

	public boolean SelectCheckBox() {
		try {

			utils.waitForEle(SelectCheckBox, "visible", "", 30);
			SelectCheckBox.isDisplayed();
			SelectCheckBox.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DeleteButton() {
		try {
			utils.waitForEle(DeleteButton, "visible", "", 30);

			try {
				// Attempt to click original DeleteButton
				DeleteButton.click();
			} catch (ElementNotInteractableException e) {
				// If not clickable, try clicking it directly using XPath
				WebElement fallbackDeleteBtn = driver
						.findElement(By.xpath("//div[@id='email_pane']/div/div[2]/div[2]/div"));
				utils.waitForEle(fallbackDeleteBtn, "clickable", "", 30);
				fallbackDeleteBtn.click();

				driver.navigate().back();
				Thread.sleep(4000);
			}

		} catch (Exception e) {
			exceptionDesc = e.getMessage();
			return false;
		}
		return true;
	}

	public boolean BackToInBox() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement inbox = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='email_pane']/div/div[1]/div[2]/a")));

			inbox.click();
			return true;
		} catch (Exception e) {
			exceptionDesc = e.getMessage();
			return false;
		}
	}

	public boolean ErrorToastIsClicked() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(InvalidPasswordValidation));
			if (InvalidPasswordValidation.isDisplayed()) {
				InvalidPasswordValidation.click();
			}
		} catch (org.openqa.selenium.TimeoutException e) {
		} catch (Exception e) {
			exceptionDesc = e.getMessage();
			return false;
		}
		return true;
	}

	// 21st TestCase

	public boolean delMailinatorMsg() {
		try {
			Thread.sleep(4000);
			driver.navigate().back();
			Thread.sleep(4000);

			for (WebElement checkbox : checkboxesOfMailinator) {
				if (checkbox.isDisplayed() && checkbox.isEnabled()) {
					checkbox.click();
				}
			}
			if (btndelete.isDisplayed() && btndelete.isEnabled()) {
				btndelete.click();
			} else {
				System.out.println("Delete button is not clickable");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean LoginVerify(String cmpcode, String user, String password, String captcha, LogResults loglogin) {
		try {
			try {
				utils.waitForEle(ApplicationLoaded, "visible", "", 30);
				loglogin.createLogs("Y", "PASS", " Application loaded successfully.");
			} catch (Exception e) {
				exceptionDesc = "Application loading failed: " + e.getMessage();
				return false;
			}

			try {
			
				utils.waitForEle(CompanyCode, "visible", "", 30);
				CompanyCode.sendKeys(cmpcode);
				loglogin.createLogs("Y", "PASS", " Company code entered successfully." + cmpcode);
			} catch (Exception e) {
				exceptionDesc = "Company code entry failed: " + e.getMessage();
				return false;
			}

			try {
				utils.waitForEle(loginWithPasswrdButton, "visible", "", 30);
				loginWithPasswrdButton.click();
				loglogin.createLogs("Y", "PASS", "Clicked On 'Login with Password' successfully.");
			} catch (Exception e) {
				exceptionDesc = "Login with password click failed: " + e.getMessage();
				return false;
			}

			// Sign-in section
			int signInAttempts = 0;
			while (signInAttempts < 2) {
				try {
					Thread.sleep(2000);
					utils.waitForEle(signInText, "visible", "", 20);
					if (signInText.isDisplayed()) {
						loglogin.createLogs("Y", "PASS", " Sign-in section appeared.");
						break;
					} else {
						throw new Exception("Sign-in section not displayed.");
					}
				} catch (Exception e) {
					signInAttempts++;
					if (signInAttempts >= 2) {
						exceptionDesc = "Sign-in section load failed: " + e.getMessage();
						return false;
					}
					Thread.sleep(2000);
				}
			}

			try {
				utils.waitForEle(userName, "visible", "", 20);
				userName.sendKeys(user);
				loglogin.createLogs("Y", "PASS", " Email Id entered." + user);
			} catch (Exception e) {
				exceptionDesc = "Email ID entry failed: " + e.getMessage();
				return false;
			}

			try {
				utils.waitForEle(Passords, "visible", "", 30);
				Passords.sendKeys(password);
				loglogin.createLogs("Y", "PASS", " Password entered." + password);
			} catch (Exception e) {
				exceptionDesc = "Password entry failed: " + e.getMessage();
				return false;
			}

			try {
				utils.waitForEle(captchaTextField, "visible", "", 30);
				captchaTextField.sendKeys(captcha);
				loglogin.createLogs("Y", "PASS", " Captcha entered." + captcha);
			} catch (Exception e) {
				exceptionDesc = "Captcha entry failed: " + e.getMessage();
				return false;
			}

			try {
				utils.waitForEle(signinButton, "visible", "", 30);
				signinButton.click();
				loglogin.createLogs("Y", "PASS", " Sign-in button clicked.");
			} catch (Exception e) {
				exceptionDesc = "Sign-in button click failed: " + e.getMessage();
				return false;
			}

			int loginAttempts = 0;
			while (loginAttempts < 2) {
				try {
					utils.waitForEle(loginValidate, "visible", "", 20);
					if (loginValidate.isDisplayed()) {
						loglogin.createLogs("Y", "PASS", " Login validated successfully And Good Text Is Displayed.");
						return true;
					} else {
						throw new Exception("Login validation failed And Good Text Isn't Displayed.");
					}
				} catch (Exception e) {
					loginAttempts++;
					if (loginAttempts < 2) {
						driver.navigate().refresh();
						Thread.sleep(2000);
					} else {
						exceptionDesc = "Login failed after retry: " + e.getMessage();
						return false;
					}
				}
			}

		} catch (Exception e) {
			exceptionDesc = "Unexpected error during login: " + e.getMessage();
			return false;
		}

		return true;
	}

	public boolean ContinueWithoutSavingButton() {
		try {

			utils.waitForEle(ContinueWithoutSaving, "visible", "", 15);
			ContinueWithoutSaving.click();

		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean MainLandingPage() {
	    int maxRetries = 3;

	    try {
	        for (int attempt = 1; attempt <= maxRetries; attempt++) {

	            driver.navigate().to("https://demo.meghpi.com/Dashboard");
	            Thread.sleep(2000); // wait a bit for page to load

	            try {
	                // check element on dashboard page
	                if (loginValidate.isDisplayed()) {
	                    // element found → success
	                    return true;
	                }
	            } catch (Exception e) {
	                // element not present yet, ignore and retry
	            }

	            // Not loaded yet, refresh and try again
	            driver.navigate().refresh();
	            Thread.sleep(2000);
	            System.out.println("Retry " + attempt + ": " + driver.getCurrentUrl());
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        System.out.println("Exception: " + driver.getCurrentUrl());
	        return false;
	    }

	    // after all retries still not landed
	    return false;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public  void setExceptionDesc(String exceptionDesc) {  
		exceptionDesc = this.exceptionDesc;
	}
}
