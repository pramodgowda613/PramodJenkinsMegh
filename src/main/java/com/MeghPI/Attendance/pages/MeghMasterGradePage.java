package com.MeghPI.Attendance.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.Utils;


public class MeghMasterGradePage {


	 WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
public String firstgradename = "";
	
	public MeghMasterGradePage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	@FindBy(xpath = "//button[@id='tab_Grade']")
	private WebElement GradeButton;	 //1st TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddGrade'])[2]")
	private WebElement AddGradeButton;	 //1st TestCase
	
	@FindBy(xpath = "//input[@id='txtGradeName']")
	private WebElement GradeNameTextField;	 //1st TestCase
	
	@FindBy(xpath = "//textarea[@id='txtGradeDescription']")
	private WebElement GradeDescriptionTextField;	 //1st TestCase
	
	@FindBy(xpath = "//select[@id='drpGradeAssignOfc']")
	private WebElement AssignOfficeDropDown;	 //1st TestCase
	
	@FindBy(xpath = "//select[@id='drpGradeDepartment']")
	private WebElement DepartmentDropDown;	 //1st TestCase
	
	@FindBy(xpath = "//select[@id='drpGradeTeam']")
	private WebElement TeamDropDown;	 //1st TestCase
	
	@FindBy(xpath = "//select[@id='drpGradeDesignation']")
	private WebElement DesignationDropDown;	 //1st TestCase
	
	@FindBy(xpath = "//select[@id='drpGradeRole']")
	private WebElement RoleDropDown;	 //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnGradeSave']")
	private WebElement AddGradeSaveButton;	 //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpGrade-container']")
	private WebElement ManageProfileGradeDropDown;	 //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpGrade-results']")
	private WebElement ManageProfileGradeSearchField;	 //1st TestCase
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpGrade-result')])[1]")
	private WebElement ManageProfileGradeSearchResult;	 //1st TestCase
	
	@FindBy(xpath = "(//div[contains(@id, 'divActionGrade')])[1]")
	private WebElement Grade3Dots;	 //2nd TestCase
	
	@FindBy(xpath = "(//a[@data-bs-target='#phGradeEditor'])[1]/..")
	private WebElement GradeEditIcon;	 //2nd TestCase
	
	@FindBy(xpath = "//input[@aria-controls='dtGrade']")
	private WebElement GradeSearchField;	 //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtGrade']/tbody/tr[1]/td[1]")
	private WebElement GradeSearchResultsOrFirstRecord;	 //2nd TestCase
	
	@FindBy(xpath = "(//input[contains(@id, 'chxdtGrade')])[1]")
	private WebElement GradeToggleSwitch;	 //4th TestCase
	
	@FindBy(xpath = "//div[text()='Grade Name']")
	private WebElement GradePageLoaded;	 //4th TestCase
	
	
	
	
	//1St TestCase
	
	public boolean GradeButton()
	{
		try {
			utils.waitForEle(GradeButton, "visible", "", 10);
			GradeButton.isDisplayed();
			GradeButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean AddGradeButton()
	{
		try {
			utils.waitForEle(AddGradeButton, "visible", "", 10);
			AddGradeButton.isDisplayed();
			AddGradeButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	public boolean GradeNameTextField(String gradename) {
		try {

			utils.waitForEle(GradeNameTextField,  "visible", "", 10);
			GradeNameTextField.isDisplayed();
			GradeNameTextField.clear();
			GradeNameTextField.sendKeys(gradename);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean GradeDescriptionTextField(String gradedescription) {
		try {

			utils.waitForEle(GradeDescriptionTextField,  "visible", "", 10);
			GradeDescriptionTextField.isDisplayed();
			GradeDescriptionTextField.clear();
			GradeDescriptionTextField.sendKeys(gradedescription);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	public boolean AssignOfficeDropDown(String assignoffice) {
	    try {
	    	Thread.sleep(3000);
	    	utils.waitForEle(AssignOfficeDropDown,  "visible", "", 10);
	     Select select = new Select(AssignOfficeDropDown);
	        select.selectByVisibleText(assignoffice);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentDropDown(String mappeddept) {
	    try {
	    	Thread.sleep(3000);
	    	utils.waitForEle(DepartmentDropDown,  "visible", "", 10);
	     Select select = new Select(DepartmentDropDown);
	        select.selectByVisibleText(mappeddept);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamDropDown(String mappedteam) {
	    try {
	    	Thread.sleep(3000);
	    	utils.waitForEle(TeamDropDown,  "visible", "", 10);
	     Select select = new Select(TeamDropDown);
	        select.selectByVisibleText(mappedteam);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DesignationDropDown(String mappeddesignation) {
	    try {
	    	Thread.sleep(3000);
	    	utils.waitForEle(DesignationDropDown,  "visible", "", 10);
	     Select select = new Select(DesignationDropDown);
	        select.selectByVisibleText(mappeddesignation);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean RoleDropDown(String role) {
	    try {
	    	Thread.sleep(3000);
	    	utils.waitForEle(RoleDropDown,  "visible", "", 10);
	     Select select = new Select(RoleDropDown);
	        select.selectByVisibleText(role);
	       
	    } catch (Exception e) {
	    	exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  AddGradeSaveButton()
	{
		try {
			utils.waitForEle(AddGradeSaveButton, "visible", "", 10);
			 AddGradeSaveButton.isDisplayed();
			 AddGradeSaveButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean  ManageProfileGradeDropDown()
	{
		try {
			Thread.sleep(8000);
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			exe.executeScript("arguments[0].scrollIntoView(true);", ManageProfileGradeDropDown);
			Thread.sleep(2000);
			
			utils.waitForEle(ManageProfileGradeDropDown, "visible", "", 10);
			ManageProfileGradeDropDown.isDisplayed();
			ManageProfileGradeDropDown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ManageProfileGradeSearchField(String gradename) {
		try {

			utils.waitForEle(ManageProfileGradeSearchField,  "visible", "", 10);
			ManageProfileGradeSearchField.isDisplayed();
			ManageProfileGradeSearchField.clear();
			ManageProfileGradeSearchField.sendKeys(gradename);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	
	public boolean  ManageProfileGradeSearchResult()
	{
		try {
			utils.waitForEle(ManageProfileGradeSearchResult, "visible", "", 10);
			ManageProfileGradeSearchResult.isDisplayed();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//2nd TestCase
	public boolean  Grade3Dots()
	{
		try {
			utils.waitForEle(Grade3Dots, "visible", "", 10);
			Grade3Dots.isDisplayed();
			Grade3Dots.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  GradeEditIcon()
	{
		try {
			utils.waitForEle(GradeEditIcon, "visible", "", 10);
			GradeEditIcon.isDisplayed();
			GradeEditIcon.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean GradeSearchField(String gradename) {
		try {

			utils.waitForEle(GradeSearchField,  "visible", "", 10);
			GradeSearchField.isDisplayed();
			GradeSearchField.clear();
			GradeSearchField.sendKeys(gradename);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean  GradeSearchResultsOrFirstRecord()
	{
		try {
			utils.waitForEle(GradeSearchResultsOrFirstRecord, "visible", "", 10);
			GradeSearchResultsOrFirstRecord.isDisplayed();
		firstgradename	= GradeSearchResultsOrFirstRecord.getText();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//4th TestCase
	
	public boolean  GradeToggleSwitch()
	{
		try {
			utils.waitForEle(GradeToggleSwitch, "visible", "", 10);
			GradeToggleSwitch.isDisplayed();
			GradeToggleSwitch.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	//6th TestCase
	
	
	public boolean  GradePageLoaded()
	{
		try {
			utils.waitForEle(GradePageLoaded, "visible", "", 20);
			GradePageLoaded.isDisplayed();
			
			
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
