package com.MeghPI.Attendance.pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.Utils;
public class MeghMasterDepartmentPage {

	WebDriver driver;
	private String exceptionDesc;
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
	
	@FindBy(xpath = "//select[@id='drpCompanyLocation']")
	private WebElement CompanyLocationSelected; //1st TestCase
	
	@FindBy(xpath = "//input[@id='txtDateofJoining']/../input[2]")
	private WebElement ManageProfileDateField; //1st TestCase
	
	
	
	
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
			utils.waitForEle(AddDepartmentButton, "visible", "", 30);
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

			utils.waitForEle(DepartmentName,  "visible", "", 30);
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

			utils.waitForEle(DepartmentDescription,  "visible", "", 30);
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
	    	utils.waitForEle(AssignOfficeDropdown, "visible", "", 30);
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
			utils.waitForEle(HeadOfDepartmentDropdownicon, "visible", "", 30);
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
			utils.waitForEle(HeadOfDepartmentDropdownSearchResult, "visible", "", 30);
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
			utils.waitForEle(AddDepartmentSaveButton, "visible", "", 30);
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
			Thread.sleep(2000);
			utils.waitForEle(Employee3dotsFirstRecord, "visible", "", 30);
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
			utils.waitForEle(ManageProfileButton, "visible", "", 30);

			ManageProfileButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean EditIcon() {
	    try {
	        Thread.sleep(3000);
	        Actions act = new Actions(driver);
	        act.moveToElement(EditIcon).perform();
	        utils.waitForEle(EditIcon, "visible", "", 30);
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
	            utils.waitForEle(EditIcon, "visible", "", 30);
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
			utils.waitForEle(DepartmentDropdown, "visible", "", 30);
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
			Thread.sleep(2000);
			utils.waitForEle(DepartmentDropdownSearchTextField,  "visible", "", 30);
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
			Thread.sleep(2000);
			utils.waitForEle(DepartmentDropdownSearchResult, "visible", "", 30);
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
			utils.waitForEle(Department3DotsIcon, "visible", "", 30);
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
			utils.waitForEle(DepartmentEditIcon, "visible", "", 30);
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
			
			
			Thread.sleep(2000);
			utils.waitForEle(DepartmentSearchIcon,  "visible", "", 30);
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
			Thread.sleep(2000);
			utils.waitForEle(DepartmentFirstRecord, "visible", "", 30);
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
			Thread.sleep(2000);
			utils.waitForEle(SearchDropDown, "visible", "", 30);
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
			utils.waitForEle(DepartmentSearchOfficeNameOption, "visible", "", 30);
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
			utils.waitForEle(DepartmentNameValidation, "visible", "", 30);
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
			utils.waitForEle(HODDropdownIcon, "visible", "", 30);
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

			utils.waitForEle(HODDropdownSearchTextField,  "visible", "", 30);
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
			utils.waitForEle(DropDownErrorMsg, "visible", "", 30);
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
			utils.waitForEle(HODSearchResult, "visible", "", 30);
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
			utils.waitForEle(EmployeeFirstRecord, "visible", "", 30);
			
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
			utils.waitForEle(EmployeeAssignedOfficeName, "visible", "", 30);
			
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
			utils.waitForEle(DepartmentToggleSwitch, "visible", "", 30);
		
		
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
			Thread.sleep(3000);
			utils.waitForEle(DepartmentToggleSwitchConfirmButton, "visible", "", 30);
			
			DepartmentToggleSwitchConfirmButton.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentNoResultFoundMsg()
	{
		try {
			utils.waitForEle(DepartmentNoResultFoundMsg, "visible", "", 30);
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
			utils.waitForEle(DepartmentMappingButton, "visible", "", 30);
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
			utils.waitForEle(AddDepartmentMappingButton, "visible", "", 30);
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
			Thread.sleep(2000);
			utils.waitForEle(DepartmentDropDown, "visible", "", 30);
			DepartmentDropDown.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  DepartmentDropDownTextField(String deptname) {
		try {

			utils.waitForEle(DepartmentDropDownTextField,  "visible", "", 30);
			DepartmentDropDownTextField.sendKeys(deptname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentDropDownSearchResult(String deptname) {
	    try {
	        utils.waitForEle(DepartmentDropDownSearchResult, "visible", "", 30);
	        
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
			Thread.sleep(2000);
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
			Thread.sleep(3000);
			utils.waitForEle(DepartmentDropIcon, "visible", "", 15);
			DepartmentDropIcon.click();
			Thread.sleep(2000);			
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CompanyLocationSelected(String office) {
	    try {
	    	Thread.sleep(4000);
	    	utils.waitForEle(CompanyLocationSelected, "visible", "", 20);
	     Select select = new Select(CompanyLocationSelected);
	        select.selectByVisibleText(office);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ManageProfileDateField()
	{
		try {
	
			utils.waitForEle(ManageProfileDateField, "visible", "", 15);
			ManageProfileDateField.click();
			
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	public boolean FirstDateSelection(String Date) {
		
		 try {
			 Thread.sleep(2000);
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('txtDateofJoining').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('txtDateofJoining')._flatpickr) {" +
		            "  document.getElementById('txtDateofJoining')._flatpickr.setDate('" + Date + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on txtDateofJoining'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
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
	
	
	
	

