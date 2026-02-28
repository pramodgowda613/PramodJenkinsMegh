package com.MeghPI.Attendance.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

public class MeghMasterAttendanceStatusPage {

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
	public String HDHrow = "";
	public String updatedcuststatus = "";
	public String desc = "";
	
	
	public MeghMasterAttendanceStatusPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[@id='attendanceStatus_tab']")
	private WebElement AttendanceStatusButton; //1st TestCase
	
	
	
	@FindBy(xpath = "//input[@aria-controls='dtAttnFinalStatus']")
	private WebElement AttendanceSearchTextField; //1st TestCase
	
	@FindBy(xpath = "(//div[contains(@id, 'divActionAttnFinalStatus')])[1]")
	private WebElement AttendanceStatus3Dots; //1st TestCase
	
	@FindBy(xpath = "(//a[@data-bs-target= '#phAttnFinalStatusEditor'])[1]")
	private WebElement AttendanceStatusEditButton; //1st TestCase
	
	@FindBy(xpath = "//input[@id='txtCustStatus']")
	private WebElement CustStatusTextField; //1st TestCase
	
	@FindBy(xpath = "//input[@id='txtStatusDesc']")
	private WebElement DescriptionTextField; //1st TestCase
	
	@FindBy(xpath = "//input[@id='colorPicker']")
	private WebElement ColorPickerField; //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnAttnFinalStatusSave']")
	private WebElement AttendanceStatusSaveButton; //1st TestCase
	
	
	@FindBy(xpath = "//table[@id='dtAttnFinalStatus']/tbody/tr[1]/td[2]")
	private WebElement UpdatedCustStatusRowName; //1st TestCase
	
	@FindBy(xpath = "//table[@id='dtAttnFinalStatus']/tbody/tr[5]/td[2]")
	private WebElement HDHStatusEditing; //1st TestCase
	
	@FindBy(xpath = "//label[text()='Cust Status']")
	private WebElement CustStatusCheckBox; //1st TestCase
	
	@FindBy(xpath = "//label[text()='Status Desc']")
	private WebElement DescriptionCheckBox; //1st TestCase
	
	@FindBy(xpath = "//table[@id='dtAttnFinalStatus']/tbody/tr[1]/td[3]/span")
	private WebElement DescriptionRow; //1st TestCase
	
	
	@FindBy(xpath = "//div[text()='Org Status']")
	private WebElement AttendanceStatusPageLoaded; //1st TestCase
	
	//1st TestCase
	
	
	public boolean AttendanceStatusButton()
	{
		try {
			utils.waitForEle(AttendanceStatusButton, "visible", "", 10);
			AttendanceStatusButton.isDisplayed();
			AttendanceStatusButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AttendanceSearchTextField(String custstatus) {
		try {
			Thread.sleep(2000);
			utils.waitForEle(AttendanceSearchTextField,  "visible", "", 10);
			AttendanceSearchTextField.isDisplayed();
			AttendanceSearchTextField.clear();
			AttendanceSearchTextField.sendKeys(custstatus);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AttendanceStatus3Dots()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(AttendanceStatus3Dots, "visible", "", 10);
			AttendanceStatus3Dots.isDisplayed();
			AttendanceStatus3Dots.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean AttendanceStatusEditButton()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(AttendanceStatusEditButton, "visible", "", 10);
			AttendanceStatusEditButton.isDisplayed();
			AttendanceStatusEditButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CustStatusTextField(String custstatus) {
		try {

			utils.waitForEle(CustStatusTextField,  "visible", "", 10);
			CustStatusTextField.isDisplayed();
			CustStatusTextField.clear();
			CustStatusTextField.sendKeys(custstatus);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	

	public boolean DescriptionTextField(String description) {
		try {

			utils.waitForEle(DescriptionTextField,  "visible", "", 10);
			DescriptionTextField.isDisplayed();
			DescriptionTextField.clear();
			DescriptionTextField.sendKeys(description);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AttendanceStatusSaveButton()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(AttendanceStatusSaveButton, "visible", "", 10);
			AttendanceStatusSaveButton.isDisplayed();
			AttendanceStatusSaveButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean UpdatedCustStatusRowName()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(UpdatedCustStatusRowName, "visible", "", 10);
			UpdatedCustStatusRowName.isDisplayed();
			 updatedcuststatus = UpdatedCustStatusRowName.getText();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean HDHStatusEditing()
	{
		try {
			utils.waitForEle(HDHStatusEditing, "visible", "", 10);
			HDHStatusEditing.isDisplayed();
			HDHrow =HDHStatusEditing.getText();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	
	
	public boolean CustStatusCheckBox()
	{
		try {
			utils.waitForEle(CustStatusCheckBox, "visible", "", 10);
			CustStatusCheckBox.isDisplayed();
			CustStatusCheckBox.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean DescriptionCheckBox()
	{
		try {
			utils.waitForEle(DescriptionCheckBox, "visible", "", 10);
			DescriptionCheckBox.isDisplayed();
			DescriptionCheckBox.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DescriptionRow()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(DescriptionRow, "visible", "", 10);
			DescriptionRow.isDisplayed();
		desc=	DescriptionRow.getText();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean AttendanceStatusPageLoaded() {
	    try {
	        // Wait for the element to become visible (up to 15 seconds for slower environments)
	        utils.waitForEle(AttendanceStatusPageLoaded, "visible", "", 15);

	        // Check if the element is actually displayed
	        if (AttendanceStatusPageLoaded.isDisplayed()) {
	            return true;
	        } else {
	            throw new Exception("Attendance Status Page element is not displayed.");
	        }

	    } catch (Exception e) {
	        exceptionDesc = "AttendanceStatusPageLoaded validation failed: " + e.getMessage();
	        return false;
	    }
	}

	
	
	
	
	public String getExceptionDesc() {
		return this.exceptionDesc;
	}

	public  void setExceptionDesc(String exceptionDesc) {  
		exceptionDesc = this.exceptionDesc;
	}
	
}
