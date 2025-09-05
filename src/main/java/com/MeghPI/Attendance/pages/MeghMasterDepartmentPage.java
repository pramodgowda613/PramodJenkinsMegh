package com.MeghPI.Attendance.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.Utils;
public class MeghMasterDepartmentPage {

	WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
	public String EmployeeName = "";
	public String GetOfficeName = "";
	public String Firstrecorddepartmentname = "";
	
	public MeghMasterDepartmentPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//h2[@id='department_menu']")
	private WebElement DepartmentDropIcon; //1st TestCase
	
	
	@FindBy(xpath = "//button[@id='tab_dept']")
	private WebElement DepartmentButton; //1st TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddDepartment'])[2]")
	private WebElement AddDepartmentButton; //1st TestCase
	
	@FindBy(xpath = "//input[@id='txtDepartmentName']")
	private WebElement DepartmentName; //1st TestCase
	
	@FindBy(xpath = "//textarea[@id='txtDepartmentDescription']")
	private WebElement DepartmentDescription; //1st TestCase
	
	@FindBy(xpath = "//select[@id='drpAssignOfc']")
	private WebElement AssignOfficeDropdown; //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpDeptHOD-container']")
	private WebElement HeadOfDepartmentDropdownicon; //1st TestCase
	
	@FindBy(xpath = "(//input[@type='search'])[2]")
	private WebElement HeadOfDepartmentDropdownSearchTextField; //1st TestCase
	
	@FindBy(xpath = "(//li[@role='option'])[1]")
	private WebElement HeadOfDepartmentDropdownSearchResult; //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnDepartmentSave']")
	private WebElement AddDepartmentSaveButton; //1st TestCase
	
	@FindBy(xpath = "(//div[@data-bs-toggle='dropdown'])[1]")
	private WebElement Employee3dotsFirstRecord; //1st TestCase
	
	@FindBy(xpath = "(//a[text()='Manage Profile'])[1]")
	private WebElement ManageProfileButton; //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnEdit']")
	private WebElement EditIcon; //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpDepartment-container']")
	private WebElement DepartmentDropdown; //1st TestCase
	
	@FindBy(xpath = "(//input[@type='search'])[1]")
	private WebElement DepartmentDropdownSearchTextField; //1st TestCase
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpDepartment-result')])[1]")
	private WebElement DepartmentDropdownSearchResult; //1st TestCase
	
	@FindBy(xpath = "//table[@id='dtDepartment']/tbody/tr[1]/td[4]/div/div/img")
	private WebElement Department3DotsIcon; //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtDepartment']/tbody/tr[1]/td[4]/div/ul/li/a")
	private WebElement DepartmentEditIcon; //2nd TestCase
	
	@FindBy(xpath = "//input[@aria-controls='dtDepartment']")
	private WebElement DepartmentSearchIcon; //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtDepartment']/tbody/tr[1]/td[1]")
	private WebElement DepartmentFirstRecord; //2nd TestCase
	
	@FindBy(xpath = "(//button[@id='btnHeaderFilter'])[2]")
	private WebElement SearchDropDown; //3rd TestCase
	
	@FindBy(xpath = "(//label[text()='Office Name'])[2]")
	private WebElement DepartmentSearchOfficeNameOption; //3rd TestCase
	
	@FindBy(xpath = "//label[@id='txtDepartmentName-error']")
	private WebElement DepartmentNameValidation; //4th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpAssignOfc-container']")
	private WebElement HODDropdownIcon; //10th TestCase
	
	@FindBy(xpath = "(//input[@type='search'])[2]")
	private WebElement HODDropdownSearchTextField; //10th TestCase
	
	@FindBy(xpath = "//li[text()='No results found']")
	private WebElement DropDownErrorMsg; //10th TestCase
	
	@FindBy(xpath = "(//li[contains(@id,'select2-drpAssignOfc-result')])[1]")
	private WebElement HODSearchResult; //9th TestCase
	
	@FindBy(xpath = "(//div[@class='user_details'])[1]/p[1]")
	private WebElement EmployeeFirstRecord; //11th TestCase
	
	@FindBy(xpath = "(//span[@role='textbox'])[3]")
	private WebElement EmployeeAssignedOfficeName; //11th TestCase
	
	@FindBy(xpath = "//table[@id='dtDepartment']/tbody/tr[1]/td[3]/div/div/input")
	private WebElement DepartmentToggleSwitch; //12th TestCase
	
	@FindBy(xpath = "//button[text()='OK']")
	private WebElement DepartmentToggleSwitchConfirmButton; //12th TestCase
	
	@FindBy(xpath = "//li[text()='No results found']")
	private WebElement DepartmentNoResultFoundMsg; //12th TestCase
	
	@FindBy(xpath = "//button[@id='tab_deptMapping']")
	private WebElement DepartmentMappingButton; //1st TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddDepartmentLocationMapping'])[2]")
	private WebElement AddDepartmentMappingButton; //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpDepartment-container']")
	private WebElement DepartmentDropDown; //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpDepartment-results']")
	private WebElement DepartmentDropDownTextField; //1st TestCase
	
	@FindBy(xpath = "(//li[contains(@id,'select2-drpDepartment-result')])[1]")
	private WebElement DepartmentDropDownSearchResult; //1st TestCase
	
	@FindBy(xpath = "(//div[text()='Department Name'])[1]")
	private WebElement DepartmentPageLoaded; //1st TestCase
	
	@FindBy(xpath = "(//div[text()='Department Name'])[2]")
	private WebElement DepartmentMappingPageLoaded; //1st TestCase
	
	//1st TestCase
	public boolean DepartmentButton()
	{
		try {
			utils.waitForEle(DepartmentButton, "visible", "", 15);
		
			DepartmentButton.click();
			Thread.sleep(2000);	
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	public boolean AddDepartmentButton()
	{
		try {
			utils.waitForEle(AddDepartmentButton, "visible", "", 10);
			AddDepartmentButton.isDisplayed();
			AddDepartmentButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean DepartmentName(String deptname) {
		try {

			utils.waitForEle(DepartmentName,  "visible", "", 10);
			DepartmentName.isDisplayed();
			DepartmentName.clear();
			DepartmentName.sendKeys(deptname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean DepartmentDescription(String deptdescription) {
		try {

			utils.waitForEle(DepartmentDescription,  "visible", "", 10);
			DepartmentDescription.isDisplayed();
			DepartmentDescription.sendKeys(deptdescription);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean AssignOfficeDropdown() {
	    try {
	    	Thread.sleep(3000);
	    	utils.waitForEle(AssignOfficeDropdown, "visible", "", 10);
	     Select select = new Select(AssignOfficeDropdown);
	        select.selectByIndex(1);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean HeadOfDepartmentDropdownicon()
	{
		try {
			utils.waitForEle(HeadOfDepartmentDropdownicon, "visible", "", 10);
			HeadOfDepartmentDropdownicon.isDisplayed();
			HeadOfDepartmentDropdownicon.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean HeadOfDepartmentDropdownSearchTextField(String headofdepartment) {
		try {

			utils.waitForEle(HeadOfDepartmentDropdownSearchTextField, "visible", headofdepartment);
			HeadOfDepartmentDropdownSearchTextField.isDisplayed();
			HeadOfDepartmentDropdownSearchTextField.sendKeys(headofdepartment);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean HeadOfDepartmentDropdownSearchResult()
	{
		try {
			utils.waitForEle(HeadOfDepartmentDropdownSearchResult, "visible", "", 10);
			HeadOfDepartmentDropdownSearchResult.isDisplayed();
			HeadOfDepartmentDropdownSearchResult.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddDepartmentSaveButton()
	{
		try {
			utils.waitForEle(AddDepartmentSaveButton, "visible", "", 10);
			AddDepartmentSaveButton.isDisplayed();
			AddDepartmentSaveButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean Employee3dotsFirstRecord()
	{
		try {
			utils.waitForEle(Employee3dotsFirstRecord, "visible", "", 10);
			Employee3dotsFirstRecord.isDisplayed();
			Employee3dotsFirstRecord.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ManageProfileButton()
	{
		try {
			utils.waitForEle(ManageProfileButton, "visible", "", 10);
			ManageProfileButton.isDisplayed();
			ManageProfileButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EditIcon() {
	    try {
	        Thread.sleep(4000);
	        Actions act = new Actions(driver);
	        act.moveToElement(EditIcon).perform();
	        utils.waitForEle(EditIcon, "visible", "", 10);
	        Thread.sleep(2000);

	        if (EditIcon.isDisplayed()) {
	            EditIcon.click();
	            return true;
	        }
	    } catch (Exception e1) {
	        try {
	            driver.navigate().refresh();
	            Thread.sleep(4000);
	            Actions act = new Actions(driver);
	            act.moveToElement(EditIcon).perform();
	            utils.waitForEle(EditIcon, "visible", "", 10);
	            Thread.sleep(2000);

	            if (EditIcon.isDisplayed()) {
	                EditIcon.click();
	                return true;
	            }
	        } catch (Exception e2) {
	            exceptionDesc = e2.getMessage().toString();
	            return false;
	        }
	    }
	    return false;
	}

	
	public boolean DepartmentDropdown()
	{
		try {
			utils.waitForEle(DepartmentDropdown, "visible", "", 10);
			DepartmentDropdown.isDisplayed();
			DepartmentDropdown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentDropdownSearchTextField(String deptname) {
		try {

			utils.waitForEle(DepartmentDropdownSearchTextField,  "visible", "", 10);
			DepartmentDropdownSearchTextField.isDisplayed();
			DepartmentDropdownSearchTextField.sendKeys(deptname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean DepartmentDropdownSearchResult()
	{
		try {
			utils.waitForEle(DepartmentDropdownSearchResult, "visible", "", 10);
			DepartmentDropdownSearchResult.isDisplayed();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//2nd TestCase
	public boolean Department3DotsIcon()
	{
		try {
			utils.waitForEle(Department3DotsIcon, "visible", "", 10);
			Department3DotsIcon.isDisplayed();
			Department3DotsIcon.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentEditIcon()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(DepartmentEditIcon, "visible", "", 10);
			DepartmentEditIcon.isDisplayed();
			DepartmentEditIcon.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentSearchIcon(String deptname) {
		try {
			
			

			utils.waitForEle(DepartmentSearchIcon,  "visible", "", 10);
			DepartmentSearchIcon.isDisplayed();
			DepartmentSearchIcon.clear();
			DepartmentSearchIcon.sendKeys(deptname);
			Thread.sleep(4000);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	
	public boolean DepartmentFirstRecord()
	{
		try {
			utils.waitForEle(DepartmentFirstRecord, "visible", "", 10);
			DepartmentFirstRecord.isDisplayed();
			Firstrecorddepartmentname =	DepartmentFirstRecord.getText();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//3rd TestCase
	 
	public boolean SearchDropDown()
	{
		try {
			utils.waitForEle(SearchDropDown, "visible", "", 10);
			SearchDropDown.isDisplayed();
			SearchDropDown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentSearchOfficeNameOption()
	{
		try {
			utils.waitForEle(DepartmentSearchOfficeNameOption, "visible", "", 10);
			DepartmentSearchOfficeNameOption.isDisplayed();
			DepartmentSearchOfficeNameOption.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//4th TestCase
	
	public boolean DepartmentNameValidation()
	{
		try {
			utils.waitForEle(DepartmentNameValidation, "visible", "", 10);
			DepartmentNameValidation.isDisplayed();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//10th TestCase
	
	public boolean HODDropdownIcon()
	{
		try {
			utils.waitForEle(HODDropdownIcon, "visible", "", 10);
			HODDropdownIcon.isDisplayed();
			HODDropdownIcon.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean HODDropdownSearchTextField(String officename) {
		try {

			utils.waitForEle(HODDropdownSearchTextField,  "visible", "", 10);
			HODDropdownSearchTextField.isDisplayed();
			HODDropdownSearchTextField.sendKeys(officename);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DropDownErrorMsg()
	{
		try {
			utils.waitForEle(DropDownErrorMsg, "visible", "", 10);
			DropDownErrorMsg.isDisplayed();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//9th TestCase
	
	public boolean HODSearchResult()
	{
		try {
			utils.waitForEle(HODSearchResult, "visible", "", 10);
			HODSearchResult.isDisplayed();
			HODSearchResult.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//11th TestCase
	
	public boolean EmployeeFirstRecord()
	{
		try {
			utils.waitForEle(EmployeeFirstRecord, "visible", "", 10);
			
			EmployeeFirstRecord.isDisplayed();
		EmployeeName =	EmployeeFirstRecord.getText();
	
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EmployeeAssignedOfficeName()
	{
		try {
			utils.waitForEle(EmployeeAssignedOfficeName, "visible", "", 10);
			
			EmployeeAssignedOfficeName.isDisplayed();
		GetOfficeName =	EmployeeAssignedOfficeName.getText();
	
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//12Th TestCase
	public boolean DepartmentToggleSwitch()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(DepartmentToggleSwitch, "visible", "", 10);
		
		
			Actions act = new Actions(driver);
		
			act.doubleClick(DepartmentToggleSwitch).perform();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentToggleSwitchConfirmButton()
	{
		try {
			utils.waitForEle(DepartmentToggleSwitchConfirmButton, "visible", "", 10);
			
			DepartmentToggleSwitchConfirmButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentNoResultFoundMsg()
	{
		try {
			utils.waitForEle(DepartmentNoResultFoundMsg, "visible", "", 10);
			DepartmentNoResultFoundMsg.isDisplayed();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//1st TestCase
	
	public boolean DepartmentMappingButton()
	{
		try {
			utils.waitForEle(DepartmentMappingButton, "visible", "", 10);
			DepartmentMappingButton.isDisplayed();
			DepartmentMappingButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddDepartmentMappingButton()
	{
		try {
			utils.waitForEle(AddDepartmentMappingButton, "visible", "", 10);
			AddDepartmentMappingButton.isDisplayed();
			AddDepartmentMappingButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentDropDown()
	{
		try {
			utils.waitForEle(DepartmentDropDown, "visible", "", 10);
			DepartmentDropDown.isDisplayed();
			DepartmentDropDown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  DepartmentDropDownTextField(String deptname) {
		try {

			utils.waitForEle(DepartmentDropDownTextField,  "visible", "", 10);
			DepartmentDropDownTextField.isDisplayed();
			DepartmentDropDownTextField.sendKeys(deptname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentDropDownSearchResult(String deptname) {
	    try {
	        utils.waitForEle(DepartmentDropDownSearchResult, "visible", "", 10);
	        
	        if (DepartmentDropDownSearchResult.isDisplayed()) {
	            String actualDeptName = DepartmentDropDownSearchResult.getText().trim();
	            
	            if (actualDeptName.equalsIgnoreCase(deptname.trim())) {
	                return true;
	            } else {
	                throw new Exception("Department name mismatch. Expected: '" 
	                    + deptname + "', but found: '" + actualDeptName + "'");
	            }
	        } else {
	            throw new Exception("Department dropdown result is not displayed.");
	        }
	        
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean DepartmentPageLoaded()
	{
		try {
			utils.waitForEle(DepartmentPageLoaded, "visible", "", 15);
			DepartmentPageLoaded.isDisplayed();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean DepartmentDropIcon()
	{
		try {
			utils.waitForEle(DepartmentDropIcon, "visible", "", 15);
			DepartmentDropIcon.click();
			Thread.sleep(2000);			
		
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
		exceptionDesc = exceptionDesc;
	}
	
	
	}
	
	
	
	

