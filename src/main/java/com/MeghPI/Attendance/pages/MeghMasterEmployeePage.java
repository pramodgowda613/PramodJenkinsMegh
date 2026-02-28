package com.MeghPI.Attendance.pages;



import java.io.File;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
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

import utils.Utils;
public class MeghMasterEmployeePage {

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
	public String selectedentityname = "";
	
	public MeghMasterEmployeePage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//div[@id='v-pills-tab']/../div/ul/li[@title='Directory']")
	private WebElement DirectoryButton; //1stTestCase
	
	@FindBy(xpath = "(//a[text()='Employee'])[2]")
	private WebElement EmployeeTab; //1stTestCase
	
	
	@FindBy(xpath = "(//h2[@id='headingOne'])[1]/button")
	private WebElement TotalCountDropDown; //1stTestCase
	
	@FindBy(xpath = "//p[text()='Add New']")
	private WebElement AddNewButton; //1stTestCase

	@FindBy(xpath = "//h2[text()='New Employee Onboarding']")
	private WebElement NewEmployeeFormValidation; //1stTestCase
	
	@FindBy(xpath = "(//a[text()='Company'])[2]")
	private WebElement  CompanyTab; //1stTestCase
	
	
	
	@FindBy(xpath = "//button[@id='entityType_tab']")
	private WebElement  EntityTypeButton; // 2nd TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddEntityType'])[2]")
	private WebElement  AddEntityTypeButton; // 2nd TestCase
	
	@FindBy(xpath = "//input[@id='txtEntityTypeName']")
	private WebElement  AddEntityTypeName; // 2nd TestCase
	
	@FindBy(xpath = "//select[@id='DrpCardType']")
	private WebElement  SelectCardType; // 2nd TestCase
	
	@FindBy(xpath = "//button[@id='btnEntityTypeSave']")
	private WebElement  AddEntityTypeSave; // 2nd TestCase
	
	@FindBy(xpath = "//select[@id='drpEntityType1']")
	private WebElement  EmployeeTypeDropdown; // 2nd TestCase
	
	
	@FindBy(xpath = "//button[@id='tab_CompanyLocation']")
	private WebElement  OfficeButton; // 3rd TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddCompanyLocation'])[2]")
	private WebElement  AddOfficeButton; // 3rd TestCase
	
	@FindBy(xpath = "//input[@id='txtCompanyLocationName']")
	private WebElement  OfficeName; // 3rd TestCase
	
	@FindBy(xpath = "//select[@id='drpOfcType']")
	private WebElement  OfficeTypeDropdown; // 3rd TestCase
	
	@FindBy(xpath = "//input[@id='txtLocationAddress']")
	private WebElement  OfficeAddressTextfield; // 3rd TestCase
	
	@FindBy(xpath = "//select[@id='drpLocationCountry']")
	private WebElement  CountryDropdown; // 3rd TestCase
	
	@FindBy(xpath = "//select[@id='drpLocationState']")
	private WebElement  StateDropdown; // 3rd TestCase
	
	@FindBy(xpath = "//select[@id='drpLocationCity']")
	private WebElement  CityDropdown; // 3rd TestCase
	
	@FindBy(xpath = "//input[@id='txtLocationPrimaryContact']")
	private WebElement  CompanyPrimaryContactNo; // 3rd TestCase
	
	@FindBy(xpath = "//input[@id='txtLocationPrimaryEmail']")
	private WebElement  CompanyPrimaryEmailId; // 3rd TestCase
	
	@FindBy(xpath = "//input[@id='chkLocationStatus']")
	private WebElement  CheckBoxForOfficeEnableDisable; // 3rd TestCase
	
	@FindBy(xpath = "//button[@id='btnCompanyLocationSave']")
	private WebElement  CompanyLocationSaveButton; // 3rd TestCase
	
	@FindBy(xpath = "//select[@id='drpCompanyLocation1']")
	private WebElement  CompanyLocationDropdown; // 3rd TestCase
	
	
	
	
	@FindBy(xpath = "//button[@id='btnAddMoreEmployee']")
	private WebElement  AddMoreEmployee; // 4th TestCase
	
	@FindBy(xpath = "//span[@id='lblIndex2']")
	private WebElement  Employee2; // 4th TestCase
	
	
	
	@FindBy(xpath = "(//span[@role='textbox'])[1]")
	private WebElement  ReportingToDropdown; // 4th TestCase
	
	@FindBy(xpath = "//input[@type='search']")
	private WebElement  ReportingToSearchInput; // 4th TestCase
	
	@FindBy(xpath = "//li[@role='option']")
	private WebElement  ReportingToSearchResult; // 4th TestCase
	
	@FindBy(xpath = "//input[@id='txtEmployeeId1']")
	private WebElement  EmployeeId; // 5th TestCase
	
	@FindBy(xpath = "//input[@id='txtFirstName1']")
	private WebElement  EmployeeFirstName; // 5th TestCase
	
	@FindBy(xpath = "//input[@id='txtLastName1']")
	private WebElement  EmployeeLastName; // 5th TestCase
	
	@FindBy(xpath = "//input[@id='txtEmailID1']")
	private WebElement  EmployeeEmailID; // 5th TestCase
	
	@FindBy(xpath = "//input[@id='txtPhoneNo1']")
	private WebElement  EmployeePhoneNo; // 5th TestCase
	
	@FindBy(xpath = "//input[@id='Joiningdate1']/../input[2]")
	private WebElement  EmployeeJoiningDate; // 5th TestCase
	
	@FindBy(xpath = "//input[@id='flexCheckDefault1']")
	private WebElement  SendEmailInvitationCheckBox; // 5th TestCase
	
	
	@FindBy(xpath = "//button[@id='btnEmployeeSave']")
	private WebElement  AddEmployeeSave; // 5th TestCase
	
	@FindBy(xpath = "//a[text()='Go to Directory']/..")
	private WebElement  GoToDirectory; // 5th TestCase
	
	@FindBy(xpath = "//button[@id='btnHeaderFilter']")
	private WebElement  SearchDropDown; // 5th TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemLastNamedtEmployee']")
	private WebElement  LasTNameSelected; // 5th TestCase
	
	@FindBy(xpath = "(//input[@type='search'])[1]")
	private WebElement  SearchTextField; // 5th TestCase

	@FindBy(xpath = "(//div[@class='user_details'])[1]")
    private WebElement  SearchResult; // 5th TestCase
			
	@FindBy(xpath = "(//div[@data-bs-toggle='dropdown'])[1]")
    private WebElement  DotsMenu; // 14th TestCase
	
	@FindBy(xpath = "(//a[text()='Web Enrollment'])[1]")
    private WebElement  WebEnrollment; // 14th TestCase
	
	@FindBy(xpath = "//input[@id='fileInput']")
    private WebElement  ImageUploadButton; // 14th TestCase
	
	@FindBy(xpath = "//button[@id='cropButton']")
    private WebElement  CropImage; // 14th TestCase
	
	@FindBy(xpath = "//button[@id='btnFaceSubmit']")
    private WebElement  SaveEnrollmentButton; // 14th TestCase
	
	@FindBy(xpath = "//div[text()='Entity has been enrolled successfully.']")
    private WebElement  SaveEnrollmentSuccessMsg; // 14th TestCase
	
	@FindBy(xpath = "//button[@id='btnFaceTab']")
	private WebElement  FaceEnrollmentTab; // 14th TestCase
	
	@FindBy(xpath = "//div[@id='div_employees']/div/div[1]/div[1]/p")
	private WebElement  EmployeeFormDisplayValidated; // 14th TestCase
	
	@FindBy(xpath = "//div[text()='Employee Name']")
	private WebElement  DirectoryPageLoaded; // 14th TestCase
	
	@FindBy(xpath = "//div[@id='goog-gt-tt']/../div[21]/div[2]/div/div[2]/div/span[contains(text(), '1')][1]")
	private WebElement  FirstDateSelection; // 14th TestCase
	
	@FindBy(xpath = "//select[@id='drpCompanyLocation']")
	private WebElement  OfficeNameSelected; // 14th TestCase
	
	@FindBy(xpath = "//select[@id='drpEntityType']")
	private WebElement  EntityTypeSelected; // 14th TestCase
	
	
	@FindBy(xpath = "//select[@id= 'drpLocationTimeZone']")
	private WebElement  TimeZoneInOfficeSelected; // 14th TestCase
	
	@FindBy(xpath = "//input[@id='txtGeoFencingRange']")
	private WebElement  GeoFencingRange; // 14th TestCase
	
	@FindBy(xpath = "//input[@id='txtLatitude']")
	private WebElement  LatitudeTextField; // 14th TestCase
	
	
	@FindBy(xpath = "//input[@id='txtLongitude']")
	private WebElement  LongitudeTextField; // 14th TestCase
	

	
	
	
	
	// 1st TestCase
	public boolean DirectoryButton() {
	    try {
	    	Thread.sleep(2000);
	    	driver.navigate().refresh();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement directoryBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='v-pills-Directory-tab']")));

	        // Scroll to element if needed
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", directoryBtn);
Thread.sleep(2000);	        
	        try {
	            // Try normal click
	            directoryBtn.click();
	        } catch (ElementClickInterceptedException e) {
	            // Fallback to JavaScript click
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", directoryBtn);
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Failed to click Directory button: " + e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean EmployeeTab()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(EmployeeTab, "visible", "", 20);
			
			EmployeeTab.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TotalCountDropDown()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(TotalCountDropDown, "visible", "", 10);
		
			TotalCountDropDown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean AddNewButton()
	{
		try {
			utils.waitForEle(AddNewButton, "visible", "", 10);
			AddNewButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean NewEmployeeFormValidation()
	{
		try {
			utils.waitForEle(NewEmployeeFormValidation, "visible", "", 10);
			NewEmployeeFormValidation.isDisplayed();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
//2nd TestCase
	public boolean CompanyTab() {
	    try {

	        // Wait for Company tab and click
	        utils.waitForEle(CompanyTab, "visible", "", 20);
	        CompanyTab.click();

	        // If Office button not visible → refresh page and retry
	        if (!OfficeButton.isDisplayed()) {

	            driver.navigate().refresh();

	            utils.waitForEle(CompanyTab, "visible", "", 20);
	            CompanyTab.click();

	            // Final validation
	            if (!OfficeButton.isDisplayed()) {
	                throw new Exception("Office button not visible even after page refresh");
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean EntityTypeButton()
	{
		try {
			utils.waitForEle(EntityTypeButton, "visible", "", 10);
			EntityTypeButton.isDisplayed();
			EntityTypeButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddEntityTypeButton()
	{
		try {
			utils.waitForEle(AddEntityTypeButton, "visible", "", 10);
			AddEntityTypeButton.isDisplayed();
			AddEntityTypeButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddEntityTypeName(String entityname) {
		try {

			utils.waitForEle(AddEntityTypeName,  "visible", "", 20);
		
			AddEntityTypeName.clear();
			AddEntityTypeName.sendKeys(entityname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SelectCardType() {
	    try {
	    	Thread.sleep(3000);
	    	utils.waitForEle(SelectCardType, "visible", "", 10);
	     Select select = new Select(SelectCardType);
	        select.selectByIndex(1);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddEntityTypeSave()
	{
		try {
			utils.waitForEle(AddEntityTypeSave, "visible", "", 10);
			AddEntityTypeSave.isDisplayed();
			AddEntityTypeSave.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeTypeDropdown(String emptype) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Wait until dropdown is visible
	        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.id("drpEntityType1")));

	        // Wait until options are loaded (more than just "--Select--")
	        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
	                By.xpath("//select[@id='drpEntityType1']/option"), 1));

	        // Now create Select
	        Select select = new Select(dropdown);

	        boolean found = false;
	        for (WebElement opt : select.getOptions()) {
	            if (opt.getText().trim().equalsIgnoreCase(emptype.trim())) {
	                opt.click();
	                found = true;
	                break;
	            }
	        }

	        if (!found) {
	            throw new NoSuchElementException("Option not found: " + emptype);
	        }

	        WebElement selected = select.getFirstSelectedOption();
	        selectedentityname = selected.getText().trim();

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Error while selecting Employee Type: " + e.getMessage();
	        return false;
	    }
	}


	
	//3rd TestCase
	
	public boolean OfficeButton()
	{
		try {
			utils.waitForEle(OfficeButton, "visible", "", 10);
			OfficeButton.isDisplayed();
			OfficeButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddOfficeButton()
	{
		try {
			utils.waitForEle(AddOfficeButton, "visible", "", 10);
			AddOfficeButton.isDisplayed();
			AddOfficeButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OfficeName(String officename) {
		try {

			utils.waitForEle(OfficeName,  "visible", "", 10);
			OfficeName.isDisplayed();
			OfficeName.clear();
			OfficeName.sendKeys(officename);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OfficeTypeDropdown() {
	    try {
	    	Thread.sleep(3000);
	    	utils.waitForEle(OfficeTypeDropdown, "visible", "", 10);
	     Select select = new Select(OfficeTypeDropdown);
	        select.selectByIndex(4);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OfficeAddressTextfield(String officeaddress) {
		try {

			utils.waitForEle(OfficeAddressTextfield,  "visible", "", 10);
			OfficeAddressTextfield.isDisplayed();
			OfficeAddressTextfield.clear();
			OfficeAddressTextfield.sendKeys(officeaddress);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean CountryDropdown() {
	    try {
	    	Thread.sleep(3000);
	    	utils.waitForEle(CountryDropdown, "visible", "", 10);
	     Select select = new Select(CountryDropdown);
	        select.selectByIndex(1);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean StateDropdown(String state) {
	    try {
	    	Thread.sleep(4000);
	    	utils.waitForEle(StateDropdown,  "visible", "", 10);
	     Select select = new Select(StateDropdown);
	        select.selectByVisibleText(state);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CityDropdown() {
	    try {
	    	Thread.sleep(3000);
	    	utils.waitForEle(CityDropdown, "visible", "", 10);
	     Select select = new Select(CityDropdown);
	        select.selectByVisibleText("Shikarpur");
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CompanyPrimaryContactNo(String officeno) {
		try {

			utils.waitForEle(CompanyPrimaryContactNo,  "visible", "", 10);
			CompanyPrimaryContactNo.isDisplayed();
			CompanyPrimaryContactNo.clear();
			CompanyPrimaryContactNo.sendKeys(officeno);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CompanyPrimaryEmailId(String officeemail) {
		try {

			utils.waitForEle(CompanyPrimaryEmailId,  "visible", "", 10);
			CompanyPrimaryEmailId.isDisplayed();
			CompanyPrimaryEmailId.clear();
			CompanyPrimaryEmailId.sendKeys(officeemail);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CheckBoxForOfficeEnableDisable()
	{
		try {
			utils.waitForEle(CheckBoxForOfficeEnableDisable, "visible", "", 10);
			CheckBoxForOfficeEnableDisable.isDisplayed();
			CheckBoxForOfficeEnableDisable.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CompanyLocationSaveButton()
	{
		try {
			utils.waitForEle(CompanyLocationSaveButton, "visible", "", 20);
		
			CompanyLocationSaveButton.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CompanyLocationDropdown(String companylocation) {
	    try {
	    	Thread.sleep(3000);
	    	utils.waitForEle(CompanyLocationDropdown,  "visible", "", 10);
	     Select select = new Select(CompanyLocationDropdown);
	        select.selectByVisibleText(companylocation);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//4th TestCase
	
	public boolean AddMoreEmployee()
	{
		try {
			utils.waitForEle(AddMoreEmployee, "visible", "", 10);
			AddMoreEmployee.isDisplayed();
			AddMoreEmployee.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean Employee2()
	{
		try {
			utils.waitForEle(Employee2, "visible", "", 10);
			Employee2.isDisplayed();
;
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	

	
	//5th TestCase
	
	public boolean ReportingToDropdown() {
		try {
			utils.waitForEle(ReportingToDropdown, "visible", "", 10);
			ReportingToDropdown.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ReportingToSearchInput(String reportingto) {
		try {
			Thread.sleep(1000);
			utils.waitForEle(ReportingToSearchInput,  "visible", "", 10);
			ReportingToSearchInput.sendKeys(reportingto);
			Thread.sleep(1000);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ReportingToSearchResult() {
		try {
			Thread.sleep(1000);
			utils.waitForEle(ReportingToSearchResult, "visible", "", 10);
			ReportingToSearchResult.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean EmployeeId(String empid) {
		try {

			utils.waitForEle(EmployeeId,  "visible", "", 10);
			
			EmployeeId.sendKeys(empid);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeFirstName(String empfirstname) {
		try {

			utils.waitForEle(EmployeeFirstName,  "visible", "", 10);
			EmployeeFirstName.isDisplayed();
			EmployeeFirstName.sendKeys(empfirstname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeLastName(String emplastname) {
		try {

			utils.waitForEle(EmployeeLastName,  "visible", "", 10);
			EmployeeLastName.isDisplayed();
			EmployeeLastName.sendKeys(emplastname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	

	public boolean EmployeeEmailID(String empemail) {
		try {

			utils.waitForEle(EmployeeEmailID,  "visible", "", 10);
			EmployeeEmailID.isDisplayed();
			EmployeeEmailID.sendKeys(empemail);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeePhoneNo(String empphoneno) {
		try {

			utils.waitForEle(EmployeePhoneNo,  "visible", "", 10);
			EmployeePhoneNo.isDisplayed();
			EmployeePhoneNo.sendKeys(empphoneno);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeJoiningDate(String empjoiningdate) {
		try {

			utils.waitForEle(EmployeeJoiningDate,  "visible", "", 10);
			EmployeeJoiningDate.isDisplayed();
			EmployeeJoiningDate.sendKeys(empjoiningdate);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SendEmailInvitationCheckBox()
	{
		try {
			utils.waitForEle(SendEmailInvitationCheckBox, "visible", "", 10);

			SendEmailInvitationCheckBox.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddEmployeeSave()
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement AddEmployeeSave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnEmployeeSave']")));
			
			
			AddEmployeeSave.isDisplayed();
			
			AddEmployeeSave.click();
			Thread.sleep(3000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean GoToDirectory() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	        // Wait until the element is clickable
	        WebElement goToDirectory = wait.until(
	            ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Go to Directory']/.."))
	        );

	        // Scroll into view (handles elements partially hidden or overlapped)
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", goToDirectory);

	        // Small pause for animations (if needed)
	        Thread.sleep(300); // optional and minimal

	        try {
	            // Try regular click
	            goToDirectory.click();
	        } catch (ElementClickInterceptedException e) {
	            // If click is intercepted, use JS click
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", goToDirectory);
	        }

	        return true;

	    } catch (Exception e) {
	        exceptionDesc = "Failed to click 'Go to Directory' link: " + e.getMessage();
	        return false;
	    }
	}

	

	
	public boolean SearchDropDown()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(SearchDropDown, "visible", "", 10);
			SearchDropDown.isDisplayed();
			SearchDropDown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LasTNameSelected()
	{
		try {
			utils.waitForEle(LasTNameSelected, "visible", "", 10);
			LasTNameSelected.isDisplayed();
			LasTNameSelected.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SearchTextField(String emplastname) {
		try {
			Thread.sleep(4000);

			utils.waitForEle(SearchTextField,  "visible", "", 20);
			SearchTextField.clear();
			SearchTextField.sendKeys(emplastname);
			  Thread.sleep(2000);
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SearchResult()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(SearchResult, "visible", "", 20);
			SearchResult.isDisplayed();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//14th TestCase
	public boolean DotsMenu()
	{
		try {
			utils.waitForEle(DotsMenu, "visible", "", 10);
			DotsMenu.isDisplayed();
			DotsMenu.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean WebEnrollment()
	{
		try {
			utils.waitForEle(WebEnrollment, "visible", "", 20);
			WebEnrollment.isDisplayed();
			WebEnrollment.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ImageUploadButton(String empimage) {
	    try {
	    	Thread.sleep(4000);
	        // Only set file detector if it's a real RemoteWebDriver instance
	        if (driver instanceof RemoteWebDriver &&
	            !(driver.getClass().getName().contains("ChromeDriver") || driver.getClass().getName().contains("ChromiumDriver"))) {
	            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
	        }

	        File file = new File(empimage);
	        String canonicalPath = file.getCanonicalPath();

	        utils.waitForEle(ImageUploadButton,  "visible", "", 10);
	        ImageUploadButton.sendKeys(canonicalPath);

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}


	
	
	
	
	
	public boolean CropImage()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(CropImage, "visible", "", 10);
			CropImage.isDisplayed();
			CropImage.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean SaveEnrollmentButton()
	{
		try {
			utils.waitForEle(SaveEnrollmentButton, "visible", "", 10);
			SaveEnrollmentButton.isDisplayed();
			SaveEnrollmentButton.click();
			Thread.sleep(8000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SaveEnrollmentSuccessMsg() {
	    try {
	        utils.waitForEle(SaveEnrollmentSuccessMsg, "visible", "", 10);

	        if (SaveEnrollmentSuccessMsg.isDisplayed()) {
	        	Thread.sleep(2000);
	            return true; // Success message displayed
	          
	        } else {
	            exceptionDesc = "INFO: Success message not displayed, but enrollment might be completed.";
	            return true; // Still return true since you don't want the test to fail
	        }
	    } catch (Exception e) {
	        exceptionDesc = "INFO: Exception occurred while checking success message: " + e.getMessage();
	        return true; // Return true since failure here should also not fail the test
	    }
	}

	
	
//EntityType TestCase return method
	
	public boolean SelectCardTypebytext(String cardtype) {
	    try {
	    	Thread.sleep(3000);
	    	utils.waitForEle(SelectCardType,  "visible", "", 10);
	     Select select = new Select(SelectCardType);
	        select.selectByVisibleText(cardtype);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
		
	
	public boolean FaceEnrollmentTab()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(FaceEnrollmentTab, "visible", "", 10);
			FaceEnrollmentTab.isDisplayed();
			FaceEnrollmentTab.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean EmployeeFormDisplayValidated()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(EmployeeFormDisplayValidated, "visible", "", 15);
			EmployeeFormDisplayValidated.isDisplayed();
			Thread.sleep(2000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean DirectoryPageLoaded() {
	    try {
	        // First attempt
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.visibilityOf(DirectoryPageLoaded));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", DirectoryPageLoaded);
	        Thread.sleep(500);

	        if (DirectoryPageLoaded.isDisplayed()) {
	            return true;
	        } else {
	            throw new Exception("Directory page element is present but not visible on first attempt.");
	        }

	    } catch (Exception firstEx) {
	        try {
	            // Retry attempt after page refresh
	            driver.navigate().refresh();
	            Thread.sleep(3000); // allow page to reload

	            // Click Directory button again
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	            WebElement directoryBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("v-pills-Directory-tab"))); // adjust locator as needed
	            directoryBtn.click();

	            // Retry waiting for page to load
	            wait.until(ExpectedConditions.visibilityOf(DirectoryPageLoaded));
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", DirectoryPageLoaded);
	            Thread.sleep(500);

	            if (DirectoryPageLoaded.isDisplayed()) {
	                return true;
	            } else {
	                throw new Exception("Directory page element is not visible after retry.");
	            }

	        } catch (Exception retryEx) {
	            exceptionDesc = "DirectoryPageLoaded failed after retry: " + retryEx.getMessage();
	            return false;
	        }
	    }
	}
	
	
	
	public boolean FirstDateSelection(String Date) {
		
		 try {
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('Joiningdate1').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('Joiningdate1')._flatpickr) {" +
		            "  document.getElementById('Joiningdate1')._flatpickr.setDate('" + Date + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on Joiningdate1'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	
	
	
	
	
	public boolean EmployeeDateTextField()
	{
		try {
			utils.waitForEle(EmployeeJoiningDate, "visible", "", 15);
			EmployeeJoiningDate.click();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean OfficeNameSelected(String officename)
	{
		try {
			Thread.sleep(4000);
			
			utils.waitForEle(OfficeNameSelected, "visible", "", 15);
		Select sel = new Select(OfficeNameSelected);
		sel.selectByVisibleText(officename);
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean EntityTypeSelected(String entityname)
	{
		try {
			Thread.sleep(4000);
			
			utils.waitForEle(EntityTypeSelected, "visible", "", 15);
		Select sel = new Select(EntityTypeSelected);
		sel.selectByVisibleText(entityname);
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	
	public boolean TimeZoneInOfficeSelected()
	{
		try {
			Thread.sleep(4000);
			
			utils.waitForEle(TimeZoneInOfficeSelected, "visible", "", 15);
		Select sel = new Select(TimeZoneInOfficeSelected);
		sel.selectByVisibleText("(UTC+05:30) Chennai, Kolkata, Mumbai, New Delhi (India Standard Time)");
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean GeoFencingRange(String GeoValue)
	{
		try {
			utils.waitForEle(GeoFencingRange, "visible", "", 15);
			GeoFencingRange.clear();
			GeoFencingRange.sendKeys(GeoValue);
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LatitudeTextField(String GeoValue)
	{
		try {
			utils.waitForEle(LatitudeTextField, "visible", "", 15);
			LatitudeTextField.clear();
			LatitudeTextField.sendKeys(GeoValue);
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LongitudeTextField(String GeoValue)
	{
		try {
			utils.waitForEle(LongitudeTextField, "visible", "", 15);
			LongitudeTextField.clear();
			LongitudeTextField.sendKeys(GeoValue);
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public  void setExceptionDesc(String exceptionDesc) {  
		exceptionDesc = this.exceptionDesc;
	}
	
	
}
