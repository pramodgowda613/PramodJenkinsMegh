package com.MeghPI.Attendance.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;


	
public class MeghPIDevicePage {

	WebDriver driver;
	private String exceptionDesc;
	Utils utils = new Utils(driver);
  public String HolidayPolicyName = "";
  public String actualholiday = "";
  public String Defaultholiday = "";
	
	
	
	
	public MeghPIDevicePage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//button[contains(text(), 'Device Setup')]")
	private WebElement DeviceSetUpButton; //1stTestCase
	
	@FindBy(xpath = "//a[contains(text(), 'Device List')]")
	private WebElement DeviceListButton; //1stTestCase
	
	@FindBy(xpath = "(//button[@id='btnAddDevice'])[2]")
	private WebElement AddDeviceButton; //1stTestCase
	
	@FindBy(xpath = "//input[@id='txtDeviceName']")
	private WebElement DeviceNameTextField; //1stTestCase
	
	@FindBy(xpath = "//input[@id='txtDeviceNumber']")
	private WebElement DeviceIDTextField; //1stTestCase
	
	@FindBy(xpath = "//select[@id='drpDeviceMode']")
	private WebElement ModeSelection; //1stTestCase
	
	@FindBy(xpath = "//select[@id='drpDeviceType']")
	private WebElement TypeSelection; //1stTestCase
	
	@FindBy(xpath = "//select[@id='drpCompanyLocation']")
	private WebElement AssignedLocationSelection; //1stTestCase
	
	@FindBy(xpath = "//select[@id='drpDeviceGroup']")
	private WebElement AssignedGroupSelection; //1stTestCase
	
	@FindBy(xpath = "//button[@id='btnDeviceSave']")
	private WebElement AddDeviceSaveButton; //1stTestCase
	
	
	public boolean  DeviceSetUpButton()
	{
		try {
			utils.waitForEle(DeviceSetUpButton, "visible", "", 15);
		
			DeviceSetUpButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean  DeviceListButton()
	{
		try {
			utils.waitForEle(DeviceListButton, "visible", "", 15);
		
			DeviceListButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  AddDeviceButton()
	{
		try {
			utils.waitForEle(AddDeviceButton, "visible", "", 15);
		
			AddDeviceButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  DeviceNameTextField(String devicename)
	{
		try {
			utils.waitForEle(DeviceNameTextField, "visible", "", 15);
		
			DeviceNameTextField.clear();
			DeviceNameTextField.sendKeys(devicename);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  DeviceIDTextField(String deviceid)
	{
		try {
			utils.waitForEle(DeviceIDTextField, "visible", "", 15);
		
			DeviceIDTextField.clear();
			DeviceIDTextField.sendKeys(deviceid);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	

	
	
	public boolean  ModeSelection()
	{
		try {
			Thread.sleep(4000);
		
			utils.waitForEle(ModeSelection, "visible", "", 15);
	Select sel = new Select(ModeSelection);
	sel.selectByIndex(1);
		
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  TypeSelection()
	{
		try {
			Thread.sleep(4000);
		
			utils.waitForEle(TypeSelection, "visible", "", 15);
	Select sel = new Select(TypeSelection);
	sel.selectByIndex(1);
		
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  AssignedLocationSelection(String locationname)
	{
		try {
			Thread.sleep(4000);
		
			utils.waitForEle(AssignedLocationSelection, "visible", "", 15);
	Select sel = new Select(AssignedLocationSelection);
	sel.selectByVisibleText(locationname);
		
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  AssignedGroupSelection()
	{
		try {
			Thread.sleep(4000);
		
			utils.waitForEle(AssignedGroupSelection, "visible", "", 15);
	Select sel = new Select(AssignedGroupSelection);
	sel.selectByIndex(0);
		
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean  AddDeviceSaveButton()
	{
		try {
			utils.waitForEle(AddDeviceSaveButton, "visible", "", 15);
		
			AddDeviceSaveButton.click();
			
			
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
