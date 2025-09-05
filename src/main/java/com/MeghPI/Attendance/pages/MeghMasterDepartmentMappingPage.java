package com.MeghPI.Attendance.pages;



import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.Utils;
public class MeghMasterDepartmentMappingPage {

	WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
	public String EmployeeName = "";
	public String GetOfficeName = "";
	public String Firstrecorddepartmentname = "";
	
	public MeghMasterDepartmentMappingPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@FindBy(xpath = "//span[@id='select2-drpAssignOfc-container']")
	private WebElement OfficeDropDown; //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpAssignOfc-results']")
	private WebElement OfficeDropDownSearchField; //1st TestCase
	
	@FindBy(xpath = "//li[contains(@id, 'select2-drpAssignOfc-result')][1]")
	private WebElement OfficeDropDownSearchResult; //1st TestCase
	
	@FindBy(xpath = "//select[@id='drpDepartment']")
	private WebElement DepartmentSelectDropDown; //3rd TestCase
	
	@FindBy(xpath = "//select[@id='drpAssignOfc']")
	private WebElement OfficeSelectDropDown; //3rd TestCase
	
	@FindBy(xpath = "//span[@aria-labelledby='select2-drpDeptHOD-container']")
	private WebElement HeadOfDeptDrpdown; //3rd TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpDeptHOD-results']")
	private WebElement HeadOfDeptDrpdownTextField; //3rd TestCase
	
	@FindBy(xpath = "//button[@id='btnDeptMappingSave']")
	private WebElement AddDeptMappingSaveButton; //3rd TestCase
	
	@FindBy(xpath = "//div[@class='toast toast-error']")
	private WebElement DepartmentMappingErrorMessage; //2nd TestCase
	
	@FindBy(xpath = "//div[@id='dtDepartmentLocationMapping_filter']/../div[2]/button")
	private WebElement SearchDropDown; //3rd TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemCompanyLocation-NamedtDepartmentLocationMapping']")
	private WebElement OfficeNameCheckBox; //3rd TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemEntity-FirstNamedtDepartmentLocationMapping']")
	private WebElement FirstNameCheckBox; //3rd TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemEntity-LastNamedtDepartmentLocationMapping']")
	private WebElement LastNameCheckBox; //3rd TestCase
	
	@FindBy(xpath = "//div[@id='dtDepartmentLocationMapping_filter']/label/input")
	private WebElement SearchTextField; //3rd TestCase
	
	@FindBy(xpath = "//table[@id='dtDepartmentLocationMapping']/tbody/tr/td[2]")
	private List<WebElement> OfficeNameSearchResult; //3rd TestCase
	
	
	@FindBy(xpath = "//table[@id='dtDepartmentLocationMapping']/tbody/tr/td[3]")
	private List<WebElement> HODNameSearchResult; //3rd TestCase
	
	@FindBy(xpath = "//table[@id='dtDepartmentLocationMapping']/tbody/tr/td[5]/div/div/input")
	private WebElement MappedDeptToggleSwitch; //4th TestCase
	
	
	
	
	
	//1st TestCase
	
	public boolean OfficeDropDown()
	{
		try {
			utils.waitForEle(OfficeDropDown, "visible", "", 10);
			OfficeDropDown.isDisplayed();
			OfficeDropDown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  OfficeDropDownSearchField(String officename) {
		try {

			utils.waitForEle(OfficeDropDownSearchField,  "visible", "", 10);
			OfficeDropDownSearchField.isDisplayed();
			OfficeDropDownSearchField.sendKeys(officename);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean OfficeDropDownSearchResult(String officename) {
	    try {
	        utils.waitForEle(OfficeDropDownSearchResult, "visible", "", 10);
	        
	        if (OfficeDropDownSearchResult.isDisplayed()) {
	            String actualOfficeName = OfficeDropDownSearchResult.getText().trim();
	            
	            if (actualOfficeName.equalsIgnoreCase(officename.trim())) {
	                return true;
	            } else {
	                throw new Exception("Office name mismatch. Expected: '" 
	                    + officename + "', but found: '" + actualOfficeName + "'");
	            }
	        } else {
	            throw new Exception("Office dropdown result is not displayed.");
	        }
	        
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	//3rd TestCase
	
	public boolean DepartmentSelectDropDown(String deptname)
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(DepartmentSelectDropDown, "visible", "", 10);
			DepartmentSelectDropDown.isDisplayed();
		Select dept = new Select(DepartmentSelectDropDown);
		dept.selectByVisibleText(deptname);
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OfficeSelectDropDown(String officename)
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(OfficeSelectDropDown, "visible", "", 10);
			OfficeSelectDropDown.isDisplayed();
		Select office = new Select(OfficeSelectDropDown);
		office.selectByVisibleText(officename);
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddDeptMappingSaveButton()
	{
		try {
			utils.waitForEle(AddDeptMappingSaveButton, "visible", "", 10);
			AddDeptMappingSaveButton.isDisplayed();
			AddDeptMappingSaveButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	
	
	public boolean DepartmentMappingErrorMessage()
	{
		try {
			utils.waitForEle(DepartmentMappingErrorMessage, "visible", "", 10);
			DepartmentMappingErrorMessage.isDisplayed();

		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean BrowserRefresh()
	{
		try {
			driver.navigate().refresh();
			Thread.sleep(4000);
		}
		catch (Exception e) {
		exceptionDesc =	e.getMessage().toString();
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
	
	public boolean OfficeNameCheckBox()
	{
		try {
			utils.waitForEle(OfficeNameCheckBox, "visible", "", 10);
			OfficeNameCheckBox.isDisplayed();
			OfficeNameCheckBox.click();

		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FirstNameCheckBox()
	{
		try {
			utils.waitForEle(FirstNameCheckBox, "visible", "", 10);
			FirstNameCheckBox.isDisplayed();
			FirstNameCheckBox.click();

		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LastNameCheckBox()
	{
		try {
			utils.waitForEle(LastNameCheckBox, "visible", "", 10);
			LastNameCheckBox.isDisplayed();
			LastNameCheckBox.click();

		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  SearchTextField(String input) {
		try {

			utils.waitForEle(SearchTextField,  "visible", "", 10);
			SearchTextField.isDisplayed();
			SearchTextField.clear();
			SearchTextField.sendKeys(input);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean OfficeNameSearchResult(String office) {
	    try {
	    	Thread.sleep(4000);
	        for (WebElement officename : OfficeNameSearchResult) {
	            if (!officename.getText().toLowerCase().contains(office.toLowerCase())) {
	                throw new Exception("Office name '" + officename.getText() + "' does not contain '" + office + "'");
	            }
	        }
	        return true; // All matched
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	
	
	
	public boolean HODNameSearchResult(String empfirstname) {
	    try {
	    	Thread.sleep(4000);
	        for (WebElement element : HODNameSearchResult) {
	            String fullName = element.getText().toLowerCase().trim();
	            String firstName = fullName.split(" ")[0];

	            if (!firstName.equals(empfirstname.toLowerCase())) {
	                throw new Exception("HOD First name '" + firstName +  "' does not contain '" + empfirstname + "'");
	
	            }
	        }
	        return true; // All matched
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean HODLastNameSearchResult(String emplastnames) {
	    try {
	    	Thread.sleep(4000);
	        for (WebElement element : HODNameSearchResult) {
	            String emplastname = element.getText().toLowerCase().trim();
	            String lastname = emplastname.split(" ")[1];

	            if (!lastname.equals(emplastnames.toLowerCase())) {
	                throw new Exception("HOD Last name '" + lastname +  "' does not contain '" + emplastnames + "'");
	
	            }
	        }
	        return true; // All matched
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}	
	
	//4th TestCase
	
	public boolean MappedDeptToggleSwitch()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(MappedDeptToggleSwitch, "visible", "", 30);
		
			MappedDeptToggleSwitch.click();

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
