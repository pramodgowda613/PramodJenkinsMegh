package com.MeghPI.Attendance.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.Utils;

public class MeghMasterLeaveTypePage {

	 WebDriver driver;
		private String exceptionDesc;
		Utils utils = new Utils(driver);
		public String firstrowleavename = "";
		public String firstrowleaveCode = "";

		
		public MeghMasterLeaveTypePage(WebDriver driver) {
			this.driver = driver;
			utils = new Utils(this.driver);
			PageFactory.initElements(driver, this);
		}
		
		
		
		
		
		@FindBy(xpath = "//button[@id='leavetype_tab']")
		private WebElement LeaveTypeButton;	 //1st TestCase
		
		@FindBy(xpath = "(//button[@id='btnAddLeaveType'])[2]")
		private WebElement AddLeaveTypeButton;	 //1st TestCase
		
		@FindBy(xpath = "//input[@id='txtLeaveTypeName']")
		private WebElement LeaveNameTextField;	 //1st TestCase
		
		@FindBy(xpath = "//input[@id='txtLeaveCode']")
		private WebElement LeaveCodeTextField;	 //1st TestCase
		
		@FindBy(xpath = "//textarea[@id='txtDescription']")
		private WebElement LeaveDescriptionTextField;	 //1st TestCase
		
		@FindBy(xpath = "//input[@id='rdPaid']")
		private WebElement PaidRadioButton;	 //1st TestCase
		
		@FindBy(xpath = "//input[@id='rdMale']")
		private WebElement MaleRadioButton;	 //1st TestCase
		
		@FindBy(xpath = "//button[@id='btnLeaveTypeSave']")
		private WebElement AddLeaveTypeSaveButton;	 //1st TestCase
		
		@FindBy(xpath = "//button[text()='Apply Leave']")
		private WebElement ApplyLeaveButton;	 //1st TestCase
		
		@FindBy(xpath = "//a[text()='Leave for Me']")
		private WebElement LeaveForMeButton;	 //1st TestCase
		
		@FindBy(xpath = "//span[contains(@id,'drpLeaveForMeLeaveType')]")
		private WebElement LeaveTypeDropDown;	 //1st TestCase
		
		@FindBy(xpath = "//input[@aria-controls='select2-drpLeaveForMeLeaveType-results']")
		private WebElement LeaveTypeDropDownSearchField;	 //1st TestCase
		
		@FindBy(xpath = "//ul[contains(@id, 'select2-drpLeaveForMeLeaveType-results')]")
		private WebElement LeaveTypeDropDownSearchResult;	 //1st TestCase
		
		
		@FindBy(xpath = "//table[@id='dtLeaveType']/tbody/tr[1]/td[6]/div/div/img")
		private WebElement LeaveType3Dots;	 //2nd TestCase
		
		@FindBy(xpath = "//table[@id='dtLeaveType']/tbody/tr[1]/td[6]/div/ul/li/a")
		private WebElement LeaveTypeEditButton;	 //2nd TestCase
		
		@FindBy(xpath = "//input[@aria-controls='dtLeaveType']")
		private WebElement LeaveTypeSearchField;	 //2nd TestCase
		
		@FindBy(xpath = "//table[@id='dtLeaveType']/tbody/tr[1]/td[1]")
		private WebElement LeaveTypeFirstRow;	 //2nd TestCase
		
		@FindBy(xpath = "//label[text()='Code']")
		private WebElement LeaveCodeCheckBox;	 //4th TestCase
		
		@FindBy(xpath = "//table[@id='dtLeaveType']/tbody/tr[1]/td[2]")
		private WebElement LeaveCodeRow;	 //4th TestCase
		
		@FindBy(xpath = "(//input[contains(@id, 'chxdtLeaveType')])[1]")
		private WebElement LeaveTypeToggleSwitch;	 //5th TestCase
		
		
		@FindBy(xpath = "//div[@id='policyMaster']/div/div/div[2]/h2/button")
		private WebElement LeaveButtonFromRightSideBar;	 //7th TestCase
		
		@FindBy(xpath = "//a[text()='Leave Policy']")
		private WebElement LeavePolicyButton;	 //7th TestCase
		
		@FindBy(xpath = "(//button[@id='btnAddLeavePolicy'])[2]")
		private WebElement AddLeavePolicyButton;	 //7th TestCase
		

		@FindBy(xpath = "//button[@id='btnSelectLeaveType']")
		private WebElement SelectLeavePolicyButton;	 //7th TestCase
		
		@FindBy(xpath = "//div[@id='divLeaveTypes']/div")
		private List<WebElement>  AllLeaveTypesInPolicy;	 //7th TestCase
		
		@FindBy(xpath = "//table[@id='dtLeaveType']/tbody/tr/td[1]")
		private List<WebElement>  AllLeaveTypesFromLeaveTypeModule;	 //7th TestCase
		
		@FindBy(xpath = "//select[@name='dtLeaveType_length']")
		private WebElement PaginationSelect;	 //7th TestCase
		
		@FindBy(xpath = "//a[@id='policyIcon']")
		private WebElement PolicyIcon; //7th TestCase
		
		@FindBy(xpath = "//div[@class= 'toast-message']")
		private WebElement ToastMessage; //7th TestCase
		
		@FindBy(xpath = "//div[text()='Code']")
		private WebElement LeaveTypePageLoaded; //7th TestCase
		
		@FindBy(xpath = "//input[@id='rdUnPaid']")
		private WebElement UnpaidRadioButton; //7th TestCase
		
		
		public boolean  LeaveTypeButton()
		{
			try {
				utils.waitForEle(LeaveTypeButton, "visible", "", 10);
				LeaveTypeButton.isDisplayed();
				LeaveTypeButton.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean  AddLeaveTypeButton()
		{
			try {
				utils.waitForEle(AddLeaveTypeButton, "visible", "", 10);
				AddLeaveTypeButton.isDisplayed();
				AddLeaveTypeButton.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		
		public boolean LeaveNameTextField(String leavename) {
			try {

				utils.waitForEle(LeaveNameTextField,  "visible", "", 10);
				LeaveNameTextField.isDisplayed();
				LeaveNameTextField.clear();
				LeaveNameTextField.sendKeys(leavename);
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean LeaveCodeTextField(String leavecode) {
			try {

				utils.waitForEle(LeaveCodeTextField,  "visible", "", 10);
				LeaveCodeTextField.isDisplayed();
				LeaveCodeTextField.clear();
				LeaveCodeTextField.sendKeys(leavecode);
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean LeaveDescriptionTextField(String leavedescription) {
			try {

				utils.waitForEle(LeaveDescriptionTextField,  "visible", "", 10);
				LeaveDescriptionTextField.isDisplayed();
				LeaveDescriptionTextField.clear();
				LeaveDescriptionTextField.sendKeys(leavedescription);
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean  PaidRadioButton()
		{
			try {
				utils.waitForEle(PaidRadioButton, "visible", "", 10);
				PaidRadioButton.isDisplayed();
				PaidRadioButton.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
	
		public boolean  MaleRadioButton()
		{
			try {
				utils.waitForEle(MaleRadioButton, "visible", "", 10);
				MaleRadioButton.isDisplayed();
				MaleRadioButton.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean  AddLeaveTypeSaveButton()
		{
			try {
				utils.waitForEle(AddLeaveTypeSaveButton, "visible", "", 10);
				AddLeaveTypeSaveButton.isDisplayed();
				AddLeaveTypeSaveButton.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean  ApplyLeaveButton()
		{
			try {
				utils.waitForEle(ApplyLeaveButton, "visible", "", 20);
				ApplyLeaveButton.isDisplayed();
				ApplyLeaveButton.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean  LeaveForMeButton()
		{
			try {
				utils.waitForEle(LeaveForMeButton, "visible", "", 10);
				LeaveForMeButton.isDisplayed();
				LeaveForMeButton.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}		
	
		public boolean  LeaveTypeDropDown()
		{
			try {
				utils.waitForEle(LeaveTypeDropDown, "visible", "", 20);
				LeaveTypeDropDown.isDisplayed();
				LeaveTypeDropDown.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean LeaveTypeDropDownSearchField(String leavename) {
			try {

				utils.waitForEle(LeaveTypeDropDownSearchField,  "visible", "", 20);
			
				LeaveTypeDropDownSearchField.clear();
				LeaveTypeDropDownSearchField.sendKeys(leavename);
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean  LeaveTypeDropDownSearchResult()
		{
			try {
				utils.waitForEle(LeaveTypeDropDownSearchResult, "visible", "", 10);
				LeaveTypeDropDownSearchResult.isDisplayed();
			
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean LeaveTypeDropDownSearchResults(String leavename) {
		    try {
		    	Thread.sleep(2000);
		        utils.waitForEle(LeaveTypeDropDownSearchResult, "visible", "", 10);

		        String fullText = LeaveTypeDropDownSearchResult.getText().trim();

		        if (fullText.startsWith(leavename.trim())) {
		            return true;
		        } else {
		            throw new Exception("Leave name '" + leavename + "' not found at the beginning of the text: " + fullText);
		        }

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		}

		
		
		
		
		
		
		
		
		
		//2nd TestCase
		
		public boolean  LeaveType3Dots()
		{
			try {
				Thread.sleep(3000);
				utils.waitForEle(LeaveType3Dots, "visible", "", 10);
				LeaveType3Dots.isDisplayed();
				LeaveType3Dots.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean  LeaveTypeEditButton()
		{
			try {
				utils.waitForEle(LeaveTypeEditButton, "visible", "", 10);
				LeaveTypeEditButton.isDisplayed();
				LeaveTypeEditButton.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean LeaveTypeSearchField(String leavename) {
			try {
				Thread.sleep(2000);
				utils.waitForEle(LeaveTypeSearchField,  "visible", "", 20);
				LeaveTypeSearchField.isDisplayed();
				LeaveTypeSearchField.clear();
				LeaveTypeSearchField.sendKeys(leavename);
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean  LeaveTypeFirstRow()
		{
			try {
				Thread.sleep(4000);
				
				utils.waitForEle(LeaveTypeFirstRow, "visible", "", 10);
				LeaveTypeFirstRow.isDisplayed();
			firstrowleavename =	LeaveTypeFirstRow.getText();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		//4th TestCase
		
		public boolean  LeaveCodeCheckBox()
		{
			try {
				utils.waitForEle(LeaveCodeCheckBox, "visible", "", 10);
				LeaveCodeCheckBox.isDisplayed();
				LeaveCodeCheckBox.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean  LeaveCodeRow()
		{
			try {
				utils.waitForEle(LeaveCodeRow, "visible", "", 20);
				LeaveCodeRow.isDisplayed();
			firstrowleaveCode =	LeaveCodeRow.getText();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		
		public boolean Comparison(String leavecode) {
		    try {
		        if (!LeaveCodeRow.getText().equalsIgnoreCase(leavecode)) {
		            exceptionDesc = "Leave code does not match. Expected: " + leavecode + ", Found: " + LeaveCodeRow.getText();
		            return false;
		        }
		    } catch (Exception e) {
		        exceptionDesc = e.getMessage().toString();
		        return false;
		    }
		    return true;
		}

		
		
		public boolean  LeaveTypeToggleSwitch()
		{
			try {
				Thread.sleep(2000);
				utils.waitForEle(LeaveTypeToggleSwitch, "visible", "", 10);
				LeaveTypeToggleSwitch.isDisplayed();
				LeaveTypeToggleSwitch.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		//7th TestCase
			
		
		public boolean  LeaveButtonFromRightSideBar()
		{
			try {
				
				utils.waitForEle(LeaveButtonFromRightSideBar, "visible", "", 20);
				LeaveButtonFromRightSideBar.isDisplayed();
				LeaveButtonFromRightSideBar.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		
		
		
		public boolean  AddLeavePolicyButton()
		{
			try {
				utils.waitForEle(AddLeavePolicyButton, "visible", "", 10);
				AddLeavePolicyButton.isDisplayed();
				AddLeavePolicyButton.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean  SelectLeavePolicyButton()
		{
			try {
				Thread.sleep(2000);
				utils.waitForEle(SelectLeavePolicyButton, "visible", "", 10);
				SelectLeavePolicyButton.isDisplayed();
				SelectLeavePolicyButton.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public ArrayList<String> AllLeaveTypesInPolicy() {
		    ArrayList<String> arr = new ArrayList<>();
		    try {
		        PolicyIcon.click();
		        Thread.sleep(2000);
		        LeaveButtonFromRightSideBar.click();
		        Thread.sleep(2000);
		        LeavePolicyButton.click();
		        Thread.sleep(2000);
		        AddLeavePolicyButton.click();
		        Thread.sleep(2000);
		        SelectLeavePolicyButton.click();
		        Thread.sleep(2000);

		        for (WebElement eachleave : AllLeaveTypesInPolicy) {
		            String leavename = eachleave.getText().trim();
		            arr.add(leavename);
		        }
		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		    }
		    return arr;
		}

		public ArrayList<String> AllLeaveTypesFromLeaveTypeModule() {
		    ArrayList<String> arr = new ArrayList<>();
		    try {
		        driver.get("http://demo.meghpi.com/Directory/Company");
		        Thread.sleep(2000);
		        LeaveTypeButton.click();
		        Thread.sleep(4000);

		        Select pages = new Select(PaginationSelect);
		        pages.selectByVisibleText("50");
		        Thread.sleep(4000);

		        for (WebElement createdeachleave : AllLeaveTypesFromLeaveTypeModule) {
		            String leavenames = createdeachleave.getText().trim();
		            arr.add(leavenames);
		        }
		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		    }
		    return arr;
		}

		public boolean Comparision() {
		    try {
		        List<String> createdLeaves = AllLeaveTypesFromLeaveTypeModule();
		        List<String> leaveTypesInPolicies = AllLeaveTypesInPolicy();

		        // Normalize: Remove suffix in brackets from policy names
		        Set<String> normalizedPolicyNames = leaveTypesInPolicies.stream()
		            .map(name -> name.replaceAll("\\s*\\(.*?\\)", "").trim().toLowerCase())
		            .collect(Collectors.toSet());

		        Set<String> normalizedCreatedLeaves = createdLeaves.stream()
		            .map(String::toLowerCase)
		            .collect(Collectors.toSet());

		        System.out.println("Normalized Policy Names: " + normalizedPolicyNames);
		        System.out.println("Normalized Created Leaves: " + normalizedCreatedLeaves);

		        if (!normalizedCreatedLeaves.equals(normalizedPolicyNames)) {
		            // Optional: Print what is missing/mismatched
		            Set<String> missingInPolicy = new HashSet<>(normalizedCreatedLeaves);
		            missingInPolicy.removeAll(normalizedPolicyNames);

		            Set<String> extraInPolicy = new HashSet<>(normalizedPolicyNames);
		            extraInPolicy.removeAll(normalizedCreatedLeaves);

		            System.out.println("Missing in policy: " + missingInPolicy);
		            System.out.println("Extra in policy: " + extraInPolicy);

		            return false;
		        }

		        System.out.println("All leave types matched successfully.");
		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}

		public boolean  ToastMessage()
		{
			try {
				utils.waitForEle(ToastMessage, "visible", "", 10);
				ToastMessage.isDisplayed();
			
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
			
		public boolean  LeaveTypePageLoaded()
		{
			try {
				utils.waitForEle(LeaveTypePageLoaded, "visible", "", 20);
				LeaveTypePageLoaded.isDisplayed();
			
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		
		public boolean  UnpaidRadioButton()
		{
			try {
				utils.waitForEle(UnpaidRadioButton, "visible", "", 20);
				UnpaidRadioButton.click();
			
				
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
