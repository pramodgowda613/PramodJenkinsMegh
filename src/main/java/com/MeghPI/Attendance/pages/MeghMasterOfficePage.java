package com.MeghPI.Attendance.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.Utils;

public class MeghMasterOfficePage {

	WebDriver driver;
	private  String exceptionDesc;
	Utils utils = new Utils(driver);
	
	 public String OfficeName = "";
	 public String Pagination = "";

	
	public MeghMasterOfficePage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath = "//div[text()= 'Record has been saved successfully']")
	private WebElement OfficeCreationSuccessMsg; //1st TestCase
	
	@FindBy(xpath = "(//div[@data-bs-toggle= 'dropdown'])[1]")
	private WebElement OfficeEdit3dots; //2nd TestCase
	
    @FindBy(xpath = "(//a[text()= 'Edit'])[1]")
	private WebElement OfficeEditButton; //2nd TestCase
	
   
    @FindBy(xpath = "//input[@id='txtCompanyLocationName']")
    private WebElement OfficeNameTextField; //3rd TestCase
	
    @FindBy(xpath = "(//span[@role='presentation'])[2]")
    private WebElement CompanyLocationDropDown; //3rd TestCase
	
    @FindBy(xpath = "//input[@type='search']")
    private WebElement CompanyLocationTextField; //3rd TestCase
	
    @FindBy(xpath = "//li[text()='No results found']")
    private WebElement SearchResultErrorMsg; //3rd TestCase
	
    @FindBy(xpath = "//table[@id='dtCompanyLocation']/tbody/tr[1]/td[1]")
    private WebElement FirstCompanyRecord; //3rd TestCase
	
    
    @FindBy(xpath = "(//input[@type= 'checkbox'])[7]")
    private WebElement FirstCompanyRecordToggleSwitch; //4th TestCase
    
    @FindBy(xpath = "//button[text()= 'OK']")
    private WebElement ConfirmButton; //4th TestCase
    
    
    @FindBy(xpath = "(//li[contains(@id, 'select2-drpCompanyLocation1-result')])[1]")
    private WebElement SearchResult; //4th TestCase
    
    @FindBy(xpath = "//button[@id='btnHeaderFilter']")
    private WebElement SearchDropDown; //5th TestCase
    
    @FindBy(xpath = "//label[text()='Office Type']")
    private WebElement SearchOptionSelected; //5th TestCase
   
    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement OfficeSearchTextField; //5th TestCase
    
    @FindBy(xpath = "//table[@id='dtCompanyLocation']/tbody/tr[1]/td[2]")
    private WebElement SearchResultValidation; //5th TestCase    
    
    @FindBy(xpath = "//select[@name='dtCompanyLocation_length']")
    private WebElement PaginationDropdown; //6th TestCase 
    
    @FindBy(xpath = "(//div[@data-bs-toggle= 'dropdown'])[2]")
    private WebElement Companyoffice3dotsOf2ndRecord; //7th TestCase 
    
    @FindBy(xpath = "(//a[text()= 'Edit'])[2]")
    private WebElement CompanyofficeEdit2nd; //7th TestCase 
    
    
    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement DuplicateErrorMessage; //7th TestCase 
    
    @FindBy(xpath = "//label[@id='txtCompanyLocationName-error']")
    private WebElement OfficeNameValidationMessage; //8th TestCase 
    
    @FindBy(xpath = "//label[@id='txtLocationPrimaryContact-error']")
    private WebElement OfficeNumberValidation; //14th TestCase 
    
    @FindBy(xpath = "//label[@id='txtLocationPrimaryEmail-error']")
    private WebElement OfficeEmailValidation; //14th TestCase 
    
    @FindBy(xpath = "//div[text()='Office Name']")
    private WebElement OfficePageValidation; //14th TestCase 
    
    
    
	//1st TestCase
	public boolean OfficeCreationSuccessMsg()
	{
		try {
			utils.waitForEle(OfficeCreationSuccessMsg, "visible", "", 10);
			OfficeCreationSuccessMsg.isDisplayed();
	
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//2nd TestCase
	public boolean OfficeEdit3dots()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(OfficeEdit3dots, "visible", "", 30);
			OfficeEdit3dots.isDisplayed();
			OfficeEdit3dots.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean OfficeEditButton()
	{
		try {
			utils.waitForEle(OfficeEditButton, "visible", "", 30);
			OfficeEditButton.isDisplayed();
			OfficeEditButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	//3rdTestCase
	public boolean FirstCompanyRecord()
	{
		try {
			utils.waitForEle(FirstCompanyRecord, "visible", "", 30);
			
			FirstCompanyRecord.isDisplayed();
		OfficeName =	FirstCompanyRecord.getText();
		System.out.println("scrollaction done" + OfficeName);
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean CompanyLocationDropDown()
	{
		try {
			utils.waitForEle(CompanyLocationDropDown, "visible", "", 10);
			CompanyLocationDropDown.isDisplayed();
			CompanyLocationDropDown.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CompanyLocationTextField(String officenames) {
		try {
			Thread.sleep(2000);
			utils.waitForEle(CompanyLocationTextField,  "visible", "", 10);
			CompanyLocationTextField.isDisplayed();
			CompanyLocationTextField.sendKeys(officenames);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean SearchResultErrorMsg()
	{
		try {
			Thread.sleep(2000);
			utils.waitForEle(SearchResultErrorMsg, "visible", "", 10);
			SearchResultErrorMsg.isDisplayed();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//4th TestCase
	 
	
	    public boolean FirstCompanyRecordToggleSwitch()
		{
			try {
				Thread.sleep(4000);
				utils.waitForEle(FirstCompanyRecordToggleSwitch, "visible", "", 10);
				FirstCompanyRecordToggleSwitch.isDisplayed();
				FirstCompanyRecordToggleSwitch.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
	
	    public boolean ConfirmButton()
		{
			try {
				Thread.sleep(2000);
				utils.waitForEle(ConfirmButton, "visible", "", 10);
				ConfirmButton.isDisplayed();
				ConfirmButton.click();
				Thread.sleep(2000);
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
	

	public boolean SearchResult()
	{
		try {
			utils.waitForEle(SearchResult, "visible", "", 20);
			SearchResult.isDisplayed();
		
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//5th TestCase
	
	
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
	
	    public boolean SearchOptionSelected()
		{
			try {
				utils.waitForEle(SearchOptionSelected, "visible", "", 10);
				SearchOptionSelected.isDisplayed();
				SearchOptionSelected.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
	
	    public boolean OfficeSearchTextField(String officetype) {
			try {
Thread.sleep(4000);
				utils.waitForEle(OfficeSearchTextField,  "visible", "", 10);
	
				OfficeSearchTextField.clear();
				OfficeSearchTextField.sendKeys(officetype);
				Thread.sleep(4000);
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
	
	  
	    public boolean SearchResultValidation()
		{
			try {
				utils.waitForEle(SearchResultValidation, "visible", "", 20);
				SearchResultValidation.isDisplayed();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
	
	    //6th
	    public boolean PaginationDropdown()
		{
			try {
				Thread.sleep(3000);
				utils.waitForEle(PaginationDropdown, "visible", "", 10);
				PaginationDropdown.isDisplayed();
			Select sel = new Select(PaginationDropdown);
			sel.selectByIndex(2);
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		} 
	    
	    public boolean Paginationvalidation()
		{
			try {
				utils.waitForEle(PaginationDropdown, "visible", "", 10);
				PaginationDropdown.isDisplayed();
			
		Pagination = PaginationDropdown.getText();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
	    
	    //7th TestCase
	   
	    
	    public boolean Companyoffice3dotsOf2ndRecord()
		{
			try {
				utils.waitForEle(Companyoffice3dotsOf2ndRecord, "visible", "", 10);
				Companyoffice3dotsOf2ndRecord.isDisplayed();
				Companyoffice3dotsOf2ndRecord.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
	    
	    public boolean CompanyofficeEdit2nd()
		{
			try {
				utils.waitForEle(CompanyofficeEdit2nd, "visible", "", 10);
				CompanyofficeEdit2nd.isDisplayed();
				CompanyofficeEdit2nd.click();
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
	  
	    
	    public boolean DuplicateErrorMessage()
		{
			try {
				utils.waitForEle(DuplicateErrorMessage, "visible", "", 15);
			
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		} 
	    
	    
	   //8th TestCase
	    public boolean OfficeNameValidationMessage()
		{
			try {
				utils.waitForEle(OfficeNameValidationMessage, "visible", "", 10);
				OfficeNameValidationMessage.isDisplayed();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
	   
	    //14Th TestCase
	    
	    public boolean OfficeNumberValidation()
		{
			try {
				utils.waitForEle(OfficeNumberValidation, "visible", "", 10);
				OfficeNumberValidation.isDisplayed();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
	      
	    //15th TestCase
	    public boolean OfficeEmailValidation()
		{
			try {
				utils.waitForEle(OfficeEmailValidation, "visible", "", 10);
				OfficeEmailValidation.isDisplayed();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}  
	    
	    
	    public boolean OfficePageValidation()
		{
			try {
				utils.waitForEle(OfficePageValidation, "visible", "", 20);
				OfficePageValidation.isDisplayed();
				
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
