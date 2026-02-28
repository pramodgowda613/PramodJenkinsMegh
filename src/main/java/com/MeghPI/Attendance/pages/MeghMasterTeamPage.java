package com.MeghPI.Attendance.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;
public class MeghMasterTeamPage {

	WebDriver driver;
	private  String exceptionDesc;
	Utils utils = new Utils(driver);
	public String FirstTeamRecord = "";
	 
	
	public MeghMasterTeamPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath = "//h2[@id='team_menu']")
	private WebElement TeamDropIcon; //1st TestCase
	
	@FindBy(xpath = "//button[@id='tab_teams']")
	private WebElement TeamButton; //1st TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddTeam'])[2]")
	private WebElement AddTeamButton; //1st TestCase
	
	@FindBy(xpath = "//input[@id='txtTeamName']")
	private WebElement TeamNameTextField; //1st TestCase
	
	@FindBy(xpath = "//textarea[@id='txtTeamDescription']")
	private WebElement TeamAreaTextField; //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpTeamAssignOfc-container']")
	private WebElement AssignOfficeDropdown; //1st TestCase
	
	@FindBy(xpath = "//input[@class='select2-search__field']")
	private WebElement AssignOfficeTextField; //1st TestCase
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpTeamAssignOfc-result')])[1]")
	private WebElement AssignOfficeResult;  //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpDepartment-container']")
	private WebElement DepartmentDropdown; //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpDepartment-results']")
	private WebElement DepartmentSearchField;  //1st TestCase
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpDepartment-result')])[1]")
	private WebElement DepartmentResult;  //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpTeamLead-container']")
	private WebElement TeamLeadDropdown;  //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpTeamLead-results']")
	private WebElement TeamLeadSearchField;  //1st TestCase
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpTeamLead-result')])[1]")
	private WebElement TeamLeadResult;  //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnTeamSave']")
	private WebElement TeamSaveButton;  //1st TestCase
	
	@FindBy(xpath = "//select[@id='drpEmployeeGroup']/../span/span//span[@id='select2-drpEmployeeGroup-container']")
	private WebElement ManageProfileTeamDropdown;  //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpEmployeeGroup-results']")
	private WebElement ManageProfileTeamDropdownSearchField;  //1st TestCase
	
	@FindBy(xpath = "//ul[contains(@id, 'select2-drpEmployeeGroup-result')]/li[1]")
	private WebElement ManageProfileTeamDropdownSearchResult;  //1st TestCase
	
	@FindBy(xpath = "(//div[contains(@id, 'divActionTeam')])[1]")
	private WebElement Team3dots;  //2nd TestCase
	
	@FindBy(xpath = "(//a[contains(@data-bs-target, '#phTeamEditor')])[1]/..")
	private WebElement FirstTeamRecordEditIcon;  //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtTeam']/tbody/tr[1]/td[1]")
	private WebElement FirstTeamRecordName;  //2nd TestCase
	
	@FindBy(xpath = "(//input[@type='search'])[2]")
	private WebElement TeamSearchTextField;  //2nd TestCase
	
	@FindBy(xpath = "(//input[contains(@id, 'chxdtTeam')])[1]")
	private WebElement TeamToggleSwitch;  //4th TestCase
	
	@FindBy(xpath = "//li[text()='No results found']")
	private WebElement NoResultFoundMsg;  //4th TestCase
	
	@FindBy(xpath = "//input[@id='chkdivDrpItemIsActivedtTeam']")
	private WebElement StatusCheckbox;  //6th TestCase
	
	@FindBy(xpath = "//button[@id='tab_teamMapping']")
	private WebElement TeamMappingButton;  //1st TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddTeamDepartmentMapping'])[2]")
	private WebElement AddTeamMappingButton;  //1st TestCase
	
	@FindBy(xpath = "//span[@id='select2-drpTeam-container']")
	private WebElement TeamDropDowns;  //1st TestCase
	
	@FindBy(xpath = "//input[@aria-controls='select2-drpTeam-results']")
	private WebElement TeamDropDwonTextField;  //1st TestCase	
	
	@FindBy(xpath = "(//li[contains(@id, 'select2-drpTeam-result')])[1]")
	private WebElement TeamSearchResult;  //1st TestCase
	
	@FindBy(xpath = "//div[text()='Team Name']")
	private WebElement TeamPageLoaded;  //1st TestCase
	
	
	
	
	//1st TestCase
	public boolean TeamButton() {
		try {

			utils.waitForEle(TeamButton, "visible", "", 10);
			TeamButton.isDisplayed();
			TeamButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddTeamButton() {
		try {

			utils.waitForEle(AddTeamButton, "visible", "", 10);
			AddTeamButton.isDisplayed();
			AddTeamButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamNameTextField(String teamname) {
		try {

			utils.waitForEle(TeamNameTextField,  "visible", "", 10);
			TeamNameTextField.isDisplayed();
			TeamNameTextField.clear();
			TeamNameTextField.sendKeys(teamname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamAreaTextField(String teamdescription) {
		try {

			utils.waitForEle(TeamAreaTextField,  "visible", "", 10);
			TeamAreaTextField.isDisplayed();
			TeamAreaTextField.sendKeys(teamdescription);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AssignOfficeDropdown() {
		try {

			utils.waitForEle(AssignOfficeDropdown, "visible", "", 10);
			AssignOfficeDropdown.isDisplayed();
			AssignOfficeDropdown.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AssignOfficeTextField(String assignoffice) {
		try {

			utils.waitForEle(AssignOfficeTextField,  "visible", "", 10);
			AssignOfficeTextField.isDisplayed();
			AssignOfficeTextField.sendKeys(assignoffice);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AssignOfficeResult() {
		try {

			utils.waitForEle(AssignOfficeResult, "visible", "", 10);
			AssignOfficeResult.isDisplayed();
			AssignOfficeResult.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentDropdown() {
		try {

			utils.waitForEle(DepartmentDropdown, "visible", "", 10);
			DepartmentDropdown.isDisplayed();
			DepartmentDropdown.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean DepartmentSearchField(String mappeddept) {
		try {

			utils.waitForEle(DepartmentSearchField,  "visible", "", 10);
			DepartmentSearchField.isDisplayed();
			DepartmentSearchField.sendKeys(mappeddept);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentResult() {
		try {

			utils.waitForEle(DepartmentResult, "visible", "", 10);
			DepartmentResult.isDisplayed();
			DepartmentResult.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamLeadDropdown() {
		try {

			utils.waitForEle(TeamLeadDropdown, "visible", "", 10);
			TeamLeadDropdown.isDisplayed();
			TeamLeadDropdown.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamLeadSearchField(String mappedhod) {
		try {

			utils.waitForEle(TeamLeadSearchField,  "visible", "", 10);
			TeamLeadSearchField.isDisplayed();
			TeamLeadSearchField.sendKeys(mappedhod);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamLeadResult() {
		try {

			utils.waitForEle(TeamLeadResult, "visible", "", 10);
			TeamLeadResult.isDisplayed();
			TeamLeadResult.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean TeamSaveButton() {
		try {

			utils.waitForEle(TeamSaveButton, "visible", "", 10);
			TeamSaveButton.isDisplayed();
			TeamSaveButton.click();
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ManageProfileTeamDropdown() {
	    try {
	        // Re-locate the element just before action
	        By dropdownLocator = By.xpath("//select[@id='drpEmployeeGroup']/../span/span//span[@id='select2-drpEmployeeGroup-container']");
	        
	        utils.waitForEle(driver.findElement(dropdownLocator), "visible", "", 10);
	        
	        WebElement dropdown = driver.findElement(dropdownLocator);
	        
	        if (dropdown.isDisplayed()) {
	            dropdown.click();
	            return true;
	        }
	    } catch (StaleElementReferenceException sere) {
	        try {
	            // Retry after stale reference
	            By dropdownLocator = By.xpath("//select[@id='drpEmployeeGroup']/../span/span//span[@id='select2-drpEmployeeGroup-container']");
	            utils.waitForEle(driver.findElement(dropdownLocator), "visible", "", 10);
	            
	            WebElement dropdown = driver.findElement(dropdownLocator);
	            
	            if (dropdown.isDisplayed()) {
	                dropdown.click();
	                return true;
	            }
	        } catch (Exception retryException) {
	            exceptionDesc = retryException.getMessage().toString();
	            return false;
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage().toString();
	        return false;
	    }
	    return false;
	}

	
	public boolean ManageProfileTeamDropdownSearchField(String teamname) {
		try {

			utils.waitForEle(ManageProfileTeamDropdownSearchField,  "visible", "", 10);
			ManageProfileTeamDropdownSearchField.isDisplayed();
			ManageProfileTeamDropdownSearchField.sendKeys(teamname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ManageProfileTeamDropdownSearchResult() {
		try {
			Thread.sleep(2000);
			utils.waitForEle(ManageProfileTeamDropdownSearchResult, "visible", "", 10);
			ManageProfileTeamDropdownSearchResult.isDisplayed();
		
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//2nd TestCase
		public boolean Team3dots() {
			try {
				Thread.sleep(2000);
				utils.waitForEle(Team3dots, "visible", "", 10);
				Team3dots.isDisplayed();
				Team3dots.click();
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
	
		public boolean FirstTeamRecordEditIcon() {
			try {

				utils.waitForEle(FirstTeamRecordEditIcon, "visible", "", 10);
				FirstTeamRecordEditIcon.isDisplayed();
				FirstTeamRecordEditIcon.click();
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
	
		public boolean FirstTeamRecordName() {
			try {
				Thread.sleep(2000);
				utils.waitForEle(FirstTeamRecordName, "visible", "", 10);
				FirstTeamRecordName.isDisplayed();
			FirstTeamRecord =	FirstTeamRecordName.getText();
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
	
	
		public boolean TeamSearchTextField(String teamname) {
			try {

				Thread.sleep(4000);
				utils.waitForEle(TeamSearchTextField,  "visible", "", 10);
				TeamSearchTextField.isDisplayed();
				TeamSearchTextField.clear();
				TeamSearchTextField.sendKeys(teamname);
				Thread.sleep(4000);
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
	
	//4th TestCase
		public boolean TeamToggleSwitch() {
			try {
				Thread.sleep(2000);
				utils.waitForEle(TeamToggleSwitch, "visible", "", 10);
				TeamToggleSwitch.isDisplayed();
				TeamToggleSwitch.click();
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}	
	
		public boolean NoResultFoundMsg() {
			try {
				Thread.sleep(2000);
				utils.waitForEle(NoResultFoundMsg, "visible", "", 10);
				NoResultFoundMsg.isDisplayed();	
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}	
	
	//6th TestCase

		public boolean StatusCheckbox() {
			try {

				utils.waitForEle(StatusCheckbox, "visible", "", 10);
				StatusCheckbox.isDisplayed();
				StatusCheckbox.click();
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}	
	
	//1st TestCase
		public boolean TeamMappingButton() {
			try {
				Thread.sleep(2000);
				utils.waitForEle(TeamMappingButton, "visible", "", 10);
				TeamMappingButton.isDisplayed();
				TeamMappingButton.click();
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}	
	
		public boolean AddTeamMappingButton() {
			try {
				Thread.sleep(2000);
				utils.waitForEle(AddTeamMappingButton, "visible", "", 10);
				AddTeamMappingButton.isDisplayed();
				AddTeamMappingButton.click();
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
	
		public boolean TeamDropDowns() {
			try {
				Thread.sleep(2000);
				utils.waitForEle(TeamDropDowns, "visible", "", 10);
				TeamDropDowns.isDisplayed();
				TeamDropDowns.click();
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
	
		public boolean TeamDropDwonTextField(String teamname) {
			try {
				Thread.sleep(2000);
				utils.waitForEle(TeamDropDwonTextField,  "visible", "", 10);
				TeamDropDwonTextField.isDisplayed();
				TeamDropDwonTextField.sendKeys(teamname);
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
	
		public boolean TeamSearchResult(String teamname) {
		    try {
		    	Thread.sleep(2000);
		        utils.waitForEle(TeamSearchResult, "visible", "", 10);

		        if (TeamSearchResult.isDisplayed()) {
		            String actualTeamName = TeamSearchResult.getText().trim();
		            if (actualTeamName.equalsIgnoreCase(teamname.trim())) {
		                return true;
		            } else {
		                throw new Exception("Team name mismatch: Expected '" + teamname + "', but found '" + actualTeamName + "'");
		            }
		        } else {
		            throw new Exception("TeamSearchResult element is not displayed.");
		        }

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		}

		
	
		public boolean TeamPageLoaded() {
			try {

				utils.waitForEle(TeamPageLoaded, "visible", "", 15);
				TeamPageLoaded.isDisplayed();
				
				
			} catch (Exception e) {
				exceptionDesc = e.getMessage().toString();
				return false;
			}
			return true;
		}
	
		
		public boolean TeamDropIcon() {
			try {
				Thread.sleep(2000);
				utils.waitForEle(TeamDropIcon, "visible", "", 15);
				TeamDropIcon.click();
				
				
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
			exceptionDesc = this.exceptionDesc;
		}
	
	
	
}
