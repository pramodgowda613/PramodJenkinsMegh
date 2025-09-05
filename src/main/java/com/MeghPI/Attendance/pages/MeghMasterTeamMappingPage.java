package com.MeghPI.Attendance.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.Utils;

public class MeghMasterTeamMappingPage {

	
	WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
	public String EmployeeName = "";
	public String GetOfficeName = "";
	public String Firstrecorddepartmentname = "";
	public String Firstrow = "";
	
	public MeghMasterTeamMappingPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@FindBy(xpath = "//button[@id='tab_teamMapping']")
	private WebElement TeamMappingButton; //1st TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddTeamDepartmentMapping'])[2]")
	private WebElement AddTeamMappingButton; //1st TestCase
	
	@FindBy(xpath = "//select[@id='drpTeam']")
	private WebElement TeamDropDownOfForm; //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpTeamDepartment-container']")
	private WebElement TeamDeptDropDown; //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpTeamDepartment-results']")
	private WebElement TeamDeptSearchField; //1st TestCase
	
	@FindBy(xpath = "//li[contains(@id, 'select2-drpTeamDepartment-result')][1]")
	private WebElement TeamDeptSearchResult; //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnTeamMappingSave']")
	private WebElement AddTeamMappingSaveButton; //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls= 'dtTeamDepartmentMapping']")
	private WebElement TeamMappingSearchTextField; //7th TestCase
	
	@FindBy(xpath = "//table[@id='dtTeamDepartmentMapping']/tbody/tr[1]/td[4]/div/div/input")
	private WebElement TeamMappingToggleSwitch; //7th TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpDepartment-results']")
	private WebElement DeptDropDownTextField; //1st TestCase
	
	@FindBy(xpath = "//ul[contains(@id, 'select2-drpDepartment-result')]")
	private WebElement DeptSearchResult; //7th TestCase
	
	@FindBy(xpath = "//div[@id='grdTeamDepartmentMapping']/../div/div[2]/div/div[1]/div[1]/div[2]")
	private WebElement SearchDropDown; //8th TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemEntity-FirstNamedtTeamDepartmentMapping']")
	private WebElement FirstNameCheckBox; //8th TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemEntity-LastNamedtTeamDepartmentMapping']")
	private WebElement LastNameCheckBox; //8th TestCase
	
	
	@FindBy(xpath = "//div[@id='grdTeamDepartmentMapping']/../div/div[2]/div/div[1]/div[1]/div/label/input")
	private WebElement SearchTextField; //8th TestCase
	
	@FindBy(xpath = "//table[@id='dtTeamDepartmentMapping']/tbody/tr[1]/td[1]")
	private WebElement TeamMappingFirstRecord; //8th TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpTeam-container']")
	private WebElement TeamDropDownClick; //8th TestCase
	
	
	
	

	//1st TestCase
	public boolean  TeamDropDownOfForm(String teamname) {
		try {
			Thread.sleep(4000);
			utils.waitForEle( TeamDropDownOfForm, "visible", "", 15);
			 TeamDropDownOfForm.isDisplayed();
	Select TeamOption = new Select(TeamDropDownOfForm);
	TeamOption.selectByVisibleText(teamname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamDeptDropDown() {
		try {

			utils.waitForEle(TeamDeptDropDown, "visible", "", 10);
			TeamDeptDropDown.isDisplayed();
			TeamDeptDropDown.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamDeptSearchField(String deptname) {
		try {

			utils.waitForEle(TeamDeptSearchField,  "visible", "", 10);
			TeamDeptSearchField.isDisplayed();
			TeamDeptSearchField.clear();
			TeamDeptSearchField.sendKeys(deptname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamDeptSearchResult() {
		try {

			utils.waitForEle(TeamDeptSearchResult, "visible", "", 10);
			TeamDeptSearchResult.isDisplayed();
			TeamDeptSearchResult.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddTeamMappingSaveButton() {
		try {

			utils.waitForEle(AddTeamMappingSaveButton, "visible", "", 10);
			AddTeamMappingSaveButton.isDisplayed();
			AddTeamMappingSaveButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	//7th TestCase
	public boolean TeamMappingSearchTextField(String teamname) {
		try {

			utils.waitForEle(TeamMappingSearchTextField,  "visible", "", 10);
			TeamMappingSearchTextField.isDisplayed();
			TeamMappingSearchTextField.clear();
			TeamMappingSearchTextField.sendKeys(teamname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamMappingToggleSwitch() {
		try {

			utils.waitForEle(TeamMappingToggleSwitch, "visible", "", 10);
			TeamMappingToggleSwitch.isDisplayed();
			TeamMappingToggleSwitch.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean DeptDropDownTextField(String deptname) {
		try {

			utils.waitForEle(DeptDropDownTextField,  "visible", "", 10);
			DeptDropDownTextField.isDisplayed();
			DeptDropDownTextField.clear();
			DeptDropDownTextField.sendKeys(deptname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DeptSearchResult() {
		try {

			utils.waitForEle(DeptSearchResult, "visible", "", 10);
			DeptSearchResult.isDisplayed();
			DeptSearchResult.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//8th TestCase
	
	public boolean SearchDropDown() {
		try {

			utils.waitForEle(SearchDropDown, "visible", "", 10);
			SearchDropDown.isDisplayed();
			SearchDropDown.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FirstNameCheckBox() {
		try {

			utils.waitForEle(FirstNameCheckBox, "visible", "", 10);
			FirstNameCheckBox.isDisplayed();
			FirstNameCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LastNameCheckBox() {
		try {

			utils.waitForEle(LastNameCheckBox, "visible", "", 10);
			LastNameCheckBox.isDisplayed();
			LastNameCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	public boolean SearchTextField(String teamname) {
		try {

			utils.waitForEle(SearchTextField,  "visible", "", 10);
			SearchTextField.isDisplayed();
			SearchTextField.clear();
			SearchTextField.sendKeys(teamname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamMappingFirstRecord(String teamname) {
	    try {
	        utils.waitForEle(TeamMappingFirstRecord,  "visible", "", 10);

	        String actualText = TeamMappingFirstRecord.getText().trim();
	        if (actualText.equalsIgnoreCase(teamname)) {
	            return true;
	        } else {
	            throw new Exception("Team name mismatch: expected '" + teamname + "', but found '" + actualText + "'");
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean FirstRecord() {
		try {

			utils.waitForEle(TeamMappingFirstRecord, "visible", "", 10);
			TeamMappingFirstRecord.isDisplayed();
		Firstrow =	TeamMappingFirstRecord.getText();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
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
