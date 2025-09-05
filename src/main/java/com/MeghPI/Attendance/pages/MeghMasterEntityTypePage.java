package com.MeghPI.Attendance.pages;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.Utils;

public class MeghMasterEntityTypePage {

	 WebDriver driver;
		private static String exceptionDesc;
		Utils utils = new Utils(driver);
	public String entityname = "";
		
		public MeghMasterEntityTypePage(WebDriver driver) {
			this.driver = driver;
			utils = new Utils(this.driver);
			PageFactory.initElements(driver, this);
		}
		
		
		
		
		
		@FindBy(xpath = "//button[@id='entityType_tab']")
		private WebElement EntityTypeButton;	 //1st TestCase
	
		@FindBy(xpath = "//label[text()='CardType']")
		private WebElement CardTypeOptionToSearch;	 //1st TestCase
	
		
		@FindBy(xpath = "//input[@aria-controls='dtEntityType']")
		private WebElement EntitySearchField;	 //1st TestCase
		
		@FindBy(xpath = "//table[@id='dtEntityType']/tbody/tr[1]/td[1]")
		private WebElement EntityTypeFirstRow;	 //1st TestCase
		
		@FindBy(xpath = "//table[@id='dtEntityType']/tbody/tr/td[2]")
		private List<WebElement> entityTypeRows; //2nd TestCase
		
		@FindBy(xpath = "(//button[@id='btnHeaderFilter'])[2]")
		private WebElement EntitySearchDropDown; //2nd TestCase

		
		@FindBy(xpath = "(//div[contains(@id, 'divActionEntityType')])[1]")
		private WebElement Entity3Dots; //3rd TestCase
		
		@FindBy(xpath = "(//a[@data-bs-target='#phEntityTypeEditor'])[1]")
		private WebElement EntityEditButton; //3rd TestCase
		
		@FindBy(xpath = "(//input[contains(@id, 'chxdtEntityType')])[1]")
		private WebElement EntityToggleSwitch; //5th TestCase
		
		@FindBy(xpath = "//select[@id='drpEntityType1']")
		private WebElement EntityDropDownInAddEmployee; //5th TestCase
		
		@FindBy(xpath = "//div[text()='CardType']")
		private WebElement EntityTypePageLoaded; //5th TestCase
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public boolean  CardTypeOptionToSearch()
		{
			try {
				utils.waitForEle(CardTypeOptionToSearch, "visible", "", 10);
				CardTypeOptionToSearch.isDisplayed();
				CardTypeOptionToSearch.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean EntitySearchField(String cardtype) {
			try {

				utils.waitForEle(EntitySearchField,  "visible", "", 10);
				EntitySearchField.isDisplayed();
				EntitySearchField.clear();
				EntitySearchField.sendKeys(cardtype);
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
		
		public boolean  EntityTypeFirstRow()
		{
			try {
				utils.waitForEle(EntityTypeFirstRow, "visible", "", 10);
				EntityTypeFirstRow.isDisplayed();
			entityname =	EntityTypeFirstRow.getText();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean verifyAllEntityTypesAreStaff() {
		    try {
		        for (WebElement row : entityTypeRows) {
		            String text = row.getText().trim();
		      
		            if (!text.equalsIgnoreCase("WORKER")) {
		            	 System.out.println(text);
		                exceptionDesc = "Found unexpected entity type: " + text;
		                return false;
		               
		            }
		        }
		      
		    } catch (Exception e) {
		        exceptionDesc = e.getMessage().toString();
		        return false;
		    }
		    return true;
		}
	
		public boolean  EntitySearchDropDown()
		{
			try {
				utils.waitForEle(EntitySearchDropDown, "visible", "", 10);
				EntitySearchDropDown.isDisplayed();
				EntitySearchDropDown.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		//3rd TestCase
		
		public boolean  Entity3Dots()
		{
			try {
				utils.waitForEle(Entity3Dots, "visible", "", 10);
				Entity3Dots.isDisplayed();
				Entity3Dots.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
		
		public boolean  EntityEditButton()
		{
			try {
				utils.waitForEle(EntityEditButton, "visible", "", 10);
				EntityEditButton.isDisplayed();
				Thread.sleep(2000);
				EntityEditButton.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}		
		
			
		//5th TestCase
		
		public boolean  EntityToggleSwitch()
		{
			try {
				utils.waitForEle(EntityToggleSwitch, "visible", "", 10);
				EntityToggleSwitch.isDisplayed();
				
				EntityToggleSwitch.click();
				
			} catch (Exception e) {
				exceptionDesc=	e.getMessage().toString();
				return false;
			}
			return true;
		}	
			
		public boolean EntityDropDownInAddEmployee(String disabledEntityName) {
		    try {
		        utils.waitForEle(EntityDropDownInAddEmployee, "visible", "", 10);
		        if (EntityDropDownInAddEmployee.isDisplayed()) {
		            Select select = new Select(EntityDropDownInAddEmployee);
		            List<WebElement> options = select.getOptions();
		            
		            for (WebElement option : options) {
		                if (option.getText().trim().equalsIgnoreCase(disabledEntityName)) {
		                    exceptionDesc = "Disabled entity is still visible in the dropdown: " + disabledEntityName;
		                    return false; // Fail if disabled entity is found
		                }
		            }
		        }
		       
		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true; // Pass if disabled entity is NOT found
		}

		
		public boolean  EntityTypePageLoaded()
		{
			try {
				utils.waitForEle(EntityTypePageLoaded, "visible", "", 10);
				EntityTypePageLoaded.isDisplayed();
				
		
				
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
