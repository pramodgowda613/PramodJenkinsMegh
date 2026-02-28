package com.MeghPI.Attendance.pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;
public class MeghMasterDesignationPage {

	
	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
	public String Designation1stRecordName = "";
	public String SecondRowDesignationNames = "";
	
	
	public MeghMasterDesignationPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	@FindBy(xpath = "//button[@id='tab_Designation']")
	private WebElement DesignationButon;  //1st TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddDesignation'])[2]")
	private WebElement AddDesignationButon;  //1st TestCase
	
	@FindBy(xpath = "//input[@id='txtDesignationName']")
	private WebElement DesignationTextField;  //1st TestCase
	
	@FindBy(xpath = "//textarea[@id='txtDesignationDescription']")
	private WebElement DesignationTextAreaField;  //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpDesignationAssignOfc-container']")
	private WebElement AssignOfficeDropdown;  //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpDesignationAssignOfc-results']")
	private WebElement OfficeDropdownSearchField;  //1st TestCase
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpDesignationAssignOfc-result')])[1]")
	private WebElement OfficeSearchResult;  //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpDesignationDepartment-container']")
	private WebElement DepartmentDropdown;  //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpDesignationDepartment-results']")
	private WebElement DepartmentDropdownSearchField;  //1st TestCase
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpDesignationDepartment-result')])[1]")
	private WebElement DepartmentDropdownSearchResult;  //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpDesignationTeam-container']")
	private WebElement TeamDropdown;  //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls= 'select2-drpDesignationTeam-results']")
	private WebElement TeamDropdownSearchField;  //1st TestCase
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpDesignationTeam-result')])[1]")
	private WebElement TeamDropdownSearchResult;  //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnDesignationSave']")
	private WebElement AddDesignationSaveButton;  //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpDesignation-container']")
	private WebElement ManageProfileDesignationDropdown;  //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpDesignation-results']")
	private WebElement ManageProfileDesignationDropdownSearchField;  //1st TestCase
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpDesignation-result')])[1]")
	private WebElement ManageProfileDesignationDropdownSearchResult;  //1st TestCase
	
	@FindBy(xpath = "(//div[contains(@id, 'divActionDesignation')])[1]/div/img")
	private WebElement Designation3dots2ndRow;  //2nd TestCase
	
	@FindBy(xpath = "(//div[contains(@id,'divActionDesignation')])[1]/ul/li")
	private WebElement DesignationEdit2ndRow;  //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtDesignation']/tbody/tr[1]/td[1]")
	private WebElement Designation1stRowName;  //2nd TestCase
	
	@FindBy(xpath = "(//input[@placeholder='Search'])[2]")
	private WebElement DesignationSearchTextField;  //2nd TestCase
	
	@FindBy(xpath = "(//input[contains(@id, 'chxdtDesignation')])[1]")
	private WebElement DesignationToggleSwitch;  //4th TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemTeam-Name']")
	private WebElement DesignationSearchTeamOptionSelected;  //6th TestCase
	
	@FindBy(xpath = "//table[@id='dtDesignation']/tbody/tr[2]/td[1]")
	private WebElement SecondRowDesignationName;  //6th TestCase
	
	@FindBy(xpath = "//div[text()='Designation Name']")
	private WebElement DesignationPageLoaded;  //6th TestCase
	
	
	
	
	
	
	//1st TestCase
	public boolean DesignationButon()
	{
		try {
			utils.waitForEle(DesignationButon, "visible", "", 10);
			DesignationButon.isDisplayed();
			DesignationButon.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddDesignationButon()
	{
		try {
			utils.waitForEle(AddDesignationButon, "visible", "", 10);
			AddDesignationButon.isDisplayed();
			AddDesignationButon.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean DesignationTextField(String designationname) {
		try {

			utils.waitForEle(DesignationTextField,  "visible", "", 10);
			DesignationTextField.isDisplayed();
			DesignationTextField.clear();
			DesignationTextField.sendKeys(designationname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DesignationTextAreaField(String designationdescription) {
		try {

			utils.waitForEle(DesignationTextAreaField,  "visible", "", 10);
			DesignationTextAreaField.isDisplayed();
			DesignationTextAreaField.clear();
			DesignationTextAreaField.sendKeys(designationdescription);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AssignOfficeDropdown()
	{
		try {
			utils.waitForEle(AssignOfficeDropdown, "visible", "", 10);
			AssignOfficeDropdown.isDisplayed();
			AssignOfficeDropdown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OfficeDropdownSearchField(String assignoffice) {
		try {

			utils.waitForEle(OfficeDropdownSearchField,  "visible", "", 10);
			OfficeDropdownSearchField.isDisplayed();
		
			OfficeDropdownSearchField.sendKeys(assignoffice);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OfficeSearchResult()
	{
		try {
			utils.waitForEle(OfficeSearchResult, "visible", "", 10);
			OfficeSearchResult.isDisplayed();
			OfficeSearchResult.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
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
	
	public boolean DepartmentDropdownSearchField(String mappeddept) {
		try {

			utils.waitForEle(DepartmentDropdownSearchField,  "visible", "", 10);
			DepartmentDropdownSearchField.isDisplayed();
			
			DepartmentDropdownSearchField.sendKeys(mappeddept);
			
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
			DepartmentDropdownSearchResult.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean TeamDropdown()
	{
		try {
			utils.waitForEle(TeamDropdown, "visible", "", 10);
			TeamDropdown.isDisplayed();
			TeamDropdown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamDropdownSearchField(String team) {
		try {

			utils.waitForEle(TeamDropdownSearchField,  "visible", "", 10);
			TeamDropdownSearchField.isDisplayed();
			
			TeamDropdownSearchField.sendKeys(team);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamDropdownSearchResult()
	{
		try {
			utils.waitForEle(TeamDropdownSearchResult, "visible", "", 10);
			TeamDropdownSearchResult.isDisplayed();
			TeamDropdownSearchResult.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddDesignationSaveButton()
	{
		try {
			utils.waitForEle(AddDesignationSaveButton, "visible", "", 10);
	
			AddDesignationSaveButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ManageProfileDesignationDropdown()
	{
		try {
			Thread.sleep(2000);
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			exe.executeScript("arguments[0].scrollIntoView(true);", ManageProfileDesignationDropdown);
			Thread.sleep(2000);
			
			
			utils.waitForEle(ManageProfileDesignationDropdown, "visible", "", 10);
			ManageProfileDesignationDropdown.isDisplayed();
			ManageProfileDesignationDropdown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ManageProfileDesignationDropdownSearchField(String designationname) {
		try {

			utils.waitForEle(ManageProfileDesignationDropdownSearchField,  "visible", "", 10);
			ManageProfileDesignationDropdownSearchField.isDisplayed();
			
			ManageProfileDesignationDropdownSearchField.sendKeys(designationname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ManageProfileDesignationDropdownSearchResult()
	{
		try {
			utils.waitForEle(ManageProfileDesignationDropdownSearchResult, "visible", "", 10);
			ManageProfileDesignationDropdownSearchResult.isDisplayed();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//2nd TestCase
	public boolean Designation3dots2ndRow()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(Designation3dots2ndRow, "visible", "", 10);
		
			Designation3dots2ndRow.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DesignationEdit2ndRow()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(DesignationEdit2ndRow, "visible", "", 10);
			
			DesignationEdit2ndRow.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SecondRowDesignationName()
	{
		try {
			utils.waitForEle(SecondRowDesignationName, "visible", "", 10);
			SecondRowDesignationName.isDisplayed();
			SecondRowDesignationNames =		SecondRowDesignationName.getText();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean Designation1stRowName()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(Designation1stRowName, "visible", "", 10);
			Designation1stRowName.isDisplayed();
	Designation1stRecordName =		Designation1stRowName.getText();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean DesignationSearchTextField(String designationname) {
		try {
Thread.sleep(2000);
			utils.waitForEle(DesignationSearchTextField,  "visible", "", 10);
			
			DesignationSearchTextField.clear();
			
			DesignationSearchTextField.sendKeys(designationname);
			Thread.sleep(2000);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//4th TestCase
	
	public boolean DesignationToggleSwitch()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(DesignationToggleSwitch, "visible", "", 10);
			DesignationToggleSwitch.isDisplayed();
			DesignationToggleSwitch.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	//6th TestCase

	public boolean DesignationSearchTeamOptionSelected()
	{
		try {
			utils.waitForEle(DesignationSearchTeamOptionSelected, "visible", "", 10);
			DesignationSearchTeamOptionSelected.isDisplayed();
			DesignationSearchTeamOptionSelected.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean DesignationPageLoaded()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(DesignationPageLoaded, "visible", "", 15);
			DesignationPageLoaded.isDisplayed();
		
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
