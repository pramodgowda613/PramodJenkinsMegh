package com.MeghPI.Attendance.pages;


import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utils;

public class MeghLeavePolicyPage {

	WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
	public String actualPolicyName = "";
	public String fullLeaveName = "";
	public String firstname = "";
	public String AssignedEmpCounts = "";
	public String assignedEmpCountStr = "";
	public String FullEmpID = "";
	public String Designationname = "";
	public String DepartmentName = "";
	
	
	public MeghLeavePolicyPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//table[@id='dtLeavePolicy']/tbody/tr[1]/td[4]/a/button/img")
	private WebElement EditButton; //1stTestCase
	
	@FindBy(xpath = "//table[@id='dtLeavePolicy']/tbody/tr/td[1]/p/span")
	private List<WebElement> ContainsDefaultText; //1stTestCase
	
	@FindBy(xpath = "//select[@name='dtLeavePolicy_length']")
	private WebElement SelectPagination; //1stTestCase
	
	@FindBy(xpath = "//input[@aria-controls='dtLeavePolicy']")
	private WebElement LeavePolicySearchTextField; //1stTestCase
	
	@FindBy(xpath = "//div[@id='divLeaveTypes']/div")
	private List<WebElement>  AllLeaveTypesInPolicy;	 //7th TestCase
	
	@FindBy(xpath = "//button[@id='btnAddSelectedLeave']")
	private WebElement AddLeaveButton; //1stTestCase
	
	@FindBy(xpath = "//button[@id='btnCreatePolicy']")
	private WebElement SavePolicyButton; //1stTestCase
	
	@FindBy(xpath = "//table[@id='tblLeaveType']/tbody/tr/td[5]/..")
	private List<WebElement>  LeaveTypeRows;	 //7th TestCase
	
	@FindBy(xpath = "//button[@id='btnLeaveTypeRemoveConfirmYes']")
	private WebElement DeleteConfirm;	 //7th TestCase
	
	@FindBy(xpath = "//div[@id='policyMaster']/div/div/div[2]/h2/button")
	private WebElement LeaveDropDownFromSideBar;	 //1st TestCase
	
	@FindBy(xpath = "//div[@id='policyMaster']/div/div/div[2]/div/div/ul/li[1]/a")
	private WebElement LeavePolicyButtonFromSideBar;	 //1st TestCase
	
	@FindBy(xpath = "//div[text()='Leave Policy']")
	private WebElement LeavePolicyPageLoaded;	 //1st TestCase
	
	@FindBy(xpath = "//input[@id='txtPolicyName']")
	private WebElement LeavePolicyNameTextField;	 //1st TestCase
	
	
	@FindBy(xpath = "//input[@id='holiday_(hT)']")
	private WebElement HolidayLeaveSelected;	 //1st TestCase
	
	@FindBy(xpath = "//input[@id='Maternity_Leave (ML)']")
	private WebElement MaternityLeaveSelected;	 //1st TestCase
	
	@FindBy(xpath = "//input[@id='Sick_Leave (SL)']")
	private WebElement SickLeaveSelected;	 //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnAddSelectedLeave']")
	private WebElement AddLeaveSaveButton;	 //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnCreatePolicy']")
	private WebElement CreateLeavePolicyButton;	 //1st TestCase
	
	@FindBy(xpath = "//div[@id='v-pills-grade']/div/div/div[2]/div[2]/div/div/div//p[text()='LeavePolicy']/../p[1]")
	private WebElement LeavePolicyInManageProfile;	 //1st TestCase
	
	@FindBy(xpath = "//div[@id='divLeaveTypes']/div/div/input")
	private List<WebElement> AllLeaves;	 //1st TestCase
	
	@FindBy(xpath = "//div[@id='dtLeavePolicy_filter']/label/input")
	private WebElement LeavePolicySearchTextFields;	 //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtLeavePolicy']/tbody/tr[1]/td[4]/a/button/img")
	private WebElement LeavePolicyEditIcon;	 //2nd TestCase
	
	
	@FindBy(xpath = "//input[@id='chkDefault']")
	private WebElement DefaultCheckBox;	 //2nd TestCase
	
	@FindBy(xpath = "//input[@id='chkActive']")
	private WebElement ActiveCheckBox;	 //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtLeavePolicy']/tbody/tr[1]/td[1]/p/span")
	private WebElement SearchInputtedPolicyvalidation;	 //2nd TestCase
	
	@FindBy(xpath = "//select[@id='drpCompanyLocation']")
	private WebElement OfficeDropDown;	 //3rd TestCase
	
	@FindBy(xpath = "//select[@id='drpDepartment']")
	private WebElement DepartmentDropDown;	 //3rd TestCase
	
	@FindBy(xpath = "//select[@id='drpTeam']")
	private WebElement TeamDropDown;	 //3rd TestCase
	
	
	@FindBy(xpath = "//table[@id='dtLeavePolicy']/tbody/tr[1]/td[2]/a/span")
	private WebElement AssignedEmpCountLink;	 //3rd TestCase
	
	@FindBy(xpath = "//div[@id='dtEmployeeDetails_filter']/label/input")
	private WebElement AssignedEmpCountLinkSearchTextField;	 //3rd TestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeDetails']/tbody/tr[1]/td[1]/div/div/p[1]")
	private WebElement AssignedEmpCountLinkSearchResult;	 //3rd TestCase
	
	@FindBy(xpath = "//table[@id='dtLeavePolicy']/tbody/tr[1]/td[4]/button/img")
	private WebElement LeavePolicyDeleteIcon;	 //4th TestCase
	
	@FindBy(xpath = "//button[@id='btnCreateLeaveType']")
	private WebElement CreateLeaveTypeButton;	 //7th TestCase
	
	@FindBy(xpath = "//div[@class='toast-message']")
	private WebElement SuccessMsg;
	
	@FindBy(xpath = "//button[@id='btnAddLeaveType']")
	private WebElement AddLeaveTypeSaveButton;
	
	@FindBy(xpath = "//div[@id='add_criteria']/div/div/div[2]/div/div/div[2]/div[8]/div/button")
	private WebElement AddMoreCriteria;
	
	@FindBy(xpath = "//div[@id='dtEmployeeDetails_filter']/../div[2]/button")
	private WebElement SearchDropDown;   //9th TestCase
	
	@FindBy(xpath = "//label[text()='Last Name']")
	private WebElement LastNameCheckBox;   //9th TestCase
	
	@FindBy(xpath = "//label[text()='Employee ID']")
	private WebElement EmployeeIDCheckBox;   //9th TestCase
	
	@FindBy(xpath = "//label[text()='Designation']")
	private WebElement DesignationCheckBox;   //9th TestCase
	
	@FindBy(xpath = "//label[text()='Department']")
	private WebElement DepartmentCheckBox;   //9th TestCase
	
	@FindBy(xpath = "//label[text()='Office']")
	private WebElement OfficeCheckBox;   //9th TestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeDetails']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement EmpIdValidation;   //9th TestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeDetails']/tbody/tr[1]/td[2]")
	private WebElement DesignationValidation;   //9th TestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeDetails']/tbody/tr[1]/td[3]")
	private WebElement DepartmentValidation;   //9th TestCase
	
	@FindBy(xpath = "//li[contains(@id, 'select2-drpDepartment-result')]")
	private WebElement DeptOptionSelected;   //9th TestCase
	
	
	public boolean EditButton()
	{
		try {
			utils.waitForEle(EditButton, "visible", "", 10);
			EditButton.isDisplayed();
			EditButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ContainsDefaultText(String expectedPolicySuffix) {
	    try {
	        for (WebElement contains : ContainsDefaultText) {
	            String fullText = contains.getText().trim(); // e.g., "AutoLPNTSFE (Default)"
	            
	            if (fullText.toLowerCase().contains(expectedPolicySuffix.toLowerCase())) {
	                // Extract the part before the first '('
	                actualPolicyName = fullText.split("\\(")[0].trim(); // Extracts "AutoLPNTSFE"
	                System.out.println("Policy name without suffix: " + actualPolicyName);
	                return true;
	            }
	        }

	        throw new Exception("Expected suffix '" + expectedPolicySuffix + "' not found in any element.");

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}


	public boolean LeavePolicySearchTextField(String LeavePolicyName) {
		try {

			utils.waitForEle(LeavePolicySearchTextField,  "visible", "", 10);
			LeavePolicySearchTextField.isDisplayed();
			LeavePolicySearchTextField.sendKeys(LeavePolicyName);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ClickMatchingValue(String expectedValue) {
	    try {
	    	Thread.sleep(4000);
	        for (WebElement element : AllLeaveTypesInPolicy) {
	            String fullText = element.getText().trim(); // e.g., "030625183114GNV (MKP)"
	            String mainValue = fullText.split(" \\(")[0].trim(); // "030625183114GNV"

	            if (mainValue.equalsIgnoreCase(expectedValue.trim())) {
	                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	                wait.until(ExpectedConditions.elementToBeClickable(element));
	                element.click();
	                return true;
	            }
	        }

	        throw new Exception("Expected value '" + expectedValue + "' not found in the list.");
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean AddLeaveButton()
	{
		try {
			utils.waitForEle(AddLeaveButton, "visible", "", 10);
			AddLeaveButton.isDisplayed();
			AddLeaveButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean SavePolicyButton()
	{
		try {
			utils.waitForEle(SavePolicyButton, "visible", "", 10);
			SavePolicyButton.isDisplayed();
			SavePolicyButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeaveTypeRows(String leaveTypeToDelete) {
	    try {
	    	Thread.sleep(4000);
	        // Get all delete buttons in the table
	        List<WebElement> deleteButtons = driver.findElements(By.xpath("//table[@id='tblLeaveType']/tbody/tr/td[5]/button"));

	        boolean found = false;
	        for (WebElement button : deleteButtons) {
	            String leaveTypeName = button.getAttribute("data-leavetypename").trim();

	            if (leaveTypeName.equalsIgnoreCase(leaveTypeToDelete)) {
	            	
	         
	                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
	                button.click();
	                return true;
	            } else {
	                System.out.println("Mismatched leave type: expected '" + leaveTypeToDelete + "', but found '" + leaveTypeName + "'");
	            }
	        }

	        throw new Exception("Leave type '" + leaveTypeToDelete + "' not found in table.");

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	
	public boolean DeleteConfirm()
	{
		try {
			utils.waitForEle(DeleteConfirm, "visible", "", 10);
			DeleteConfirm.isDisplayed();
			DeleteConfirm.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//1st TestCase
	public boolean LeaveDropDownFromSideBar()
	{
		try {
			utils.waitForEle(LeaveDropDownFromSideBar, "visible", "", 10);
			LeaveDropDownFromSideBar.isDisplayed();
			LeaveDropDownFromSideBar.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean LeavePolicyButtonFromSideBar()
	{
		try {
			utils.waitForEle(LeavePolicyButtonFromSideBar, "visible", "", 10);
			LeavePolicyButtonFromSideBar.isDisplayed();
			LeavePolicyButtonFromSideBar.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}    
	

	public boolean LeavePolicyPageLoaded()
	{
		try {
			Thread.sleep(6000);
			utils.waitForEle(LeavePolicyPageLoaded, "visible", "", 30);
			LeavePolicyPageLoaded.isDisplayed();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}    
	
	public boolean LeavePolicyNameTextField(String leavepolicyname) {
		try {
			
Thread.sleep(3000);
			utils.waitForEle(LeavePolicyNameTextField,  "visible", "", 10);
			LeavePolicyNameTextField.isDisplayed();
			LeavePolicyNameTextField.clear();
			LeavePolicyNameTextField.sendKeys(leavepolicyname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean AllLeaves(String sickleave) {
	    try {
	        Thread.sleep(3000);  // Consider replacing with proper wait if possible

	        for (WebElement eachLeave : AllLeaves) {
	            String leaveId = eachLeave.getAttribute("id").trim();

	            if (leaveId.equalsIgnoreCase(sickleave.trim())) {
	                if (!eachLeave.isSelected()) {
	                    eachLeave.click();
	                }
	                return true; // Successfully found and clicked
	            }
	        }

	        // If no matching leave was found
	        throw new Exception("Leave ID '" + sickleave + "' not found in AllLeaves list.");

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	public boolean AllLeaves2(String TravelLeave) {
	    try {
	        Thread.sleep(3000); // Consider replacing with explicit wait

	        for (WebElement eachLeave : AllLeaves) {
	            String leaveId = eachLeave.getAttribute("id").trim();

	            if (leaveId.toLowerCase().contains(TravelLeave.trim().toLowerCase())) {
	                if (!eachLeave.isSelected()) {
	                    eachLeave.click();
	                }
	                return true; // Successfully found and clicked
	            }
	        }

	        // If no matching leave was found
	        throw new Exception("Leave ID containing '" + TravelLeave + "' not found in AllLeaves list.");

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	
	public boolean AllLeaves3(String casualleave) {
	    try {
	        Thread.sleep(3000);  // Consider replacing with proper wait if possible

	        for (WebElement eachLeave : AllLeaves) {
	            String leaveId = eachLeave.getAttribute("id").trim();

	            if (leaveId.equalsIgnoreCase(casualleave.trim())) {
	                if (!eachLeave.isSelected()) {
	                    eachLeave.click();
	                }
	                return true; // Successfully found and clicked
	            }
	        }

	        // If no matching leave was found
	        throw new Exception("Leave ID '" + casualleave + "' not found in AllLeaves list.");

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean AddLeaveSaveButton()
	{
		try {
			utils.waitForEle(AddLeaveSaveButton, "visible", "", 10);
			AddLeaveSaveButton.isDisplayed();
			AddLeaveSaveButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}  
	
	public boolean CreateLeavePolicyButton()
	{
		try {
			
			utils.waitForEle(CreateLeavePolicyButton, "visible", "", 10);
			CreateLeavePolicyButton.isDisplayed();
			CreateLeavePolicyButton.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}  
	
	
	public boolean LeavePolicyInManageProfile(String expectedText) {
	    try {
	        Thread.sleep(3000); // Replace with explicit wait if needed
	        utils.waitForEle(LeavePolicyInManageProfile,  "visible", "", 10);

	        if (!LeavePolicyInManageProfile.isDisplayed()) {
	            throw new Exception("Leave policy element not displayed.");
	        }

	         fullLeaveName = LeavePolicyInManageProfile.getText().trim();

	        // Check if the full text contains the expected text (case-insensitive)
	        if (fullLeaveName.toLowerCase().contains(expectedText.trim().toLowerCase())) {
	            return true;
	        } else {
	            throw new Exception("Leave policy mismatch: expected text '" + expectedText + "' not found in '" + fullLeaveName + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	//2nd TestCase
	public boolean LeavePolicySearchTextFields(String leavepolicyname) {
		try {
			
Thread.sleep(3000);
			utils.waitForEle(LeavePolicySearchTextFields,  "visible", "", 10);
			LeavePolicySearchTextFields.isDisplayed();
			LeavePolicySearchTextFields.clear();
			LeavePolicySearchTextFields.sendKeys(leavepolicyname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean LeavePolicyEditIcon()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(LeavePolicyEditIcon, "visible", "", 10);
			LeavePolicyEditIcon.isDisplayed();
			LeavePolicyEditIcon.click();
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
	
	public boolean DefaultCheckBox() {
	    try {
	        utils.waitForEle(DefaultCheckBox, "visible", "", 10);

	        if (DefaultCheckBox.isDisplayed()) {
	            if (!DefaultCheckBox.isEnabled()) {
	                // Checkbox is disabled (already default or not editable)
	                return true;
	            }

	            // If checkbox is enabled
	            if (!DefaultCheckBox.isSelected()) {
	                // If not already selected, click to select it
	            	DefaultCheckBox.click();
	            }

	            // If already selected, do nothing
	            return true;
	        } else {
	            throw new Exception("Default checkbox is not displayed.");
	        }

	    } catch (Exception e) {
	        exceptionDesc = "Error in SelectDefaultCheckBox: " + e.getMessage();
	        return false;
	    }
	}
	
	public boolean ActiveCheckBox()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(ActiveCheckBox, "visible", "", 10);
			if(!ActiveCheckBox.isSelected())
{
	ActiveCheckBox.click();
	Thread.sleep(4000);
}
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean SearchInputtedPolicyvalidation(String leavepolicyname) {
	    int attempts = 0;

	    while (attempts < 2) {
	        try {
	            Thread.sleep(4000); // slight delay to allow UI rendering
	            utils.waitForEle(SearchInputtedPolicyvalidation, "visible", "", 10);

	            if (SearchInputtedPolicyvalidation.isDisplayed()) {
	                String displayedText = SearchInputtedPolicyvalidation.getText().trim();

	                // Convert both to lowercase for case-insensitive comparison
	                if (displayedText.toLowerCase().contains(leavepolicyname.toLowerCase())) {
	                    return true;
	                } else {
	                    throw new Exception("Policy name mismatch. Expected to find: " + leavepolicyname + " but found: " + displayedText);
	                }
	            } else {
	                throw new Exception("SearchInputtedPolicyvalidation element is not displayed.");
	            }
	        } catch (Exception e) {
	            exceptionDesc = "Attempt " + (attempts + 1) + " failed: " + e.getMessage();
	            attempts++;

	            if (attempts < 2) {
	                try { Thread.sleep(2000); } catch (InterruptedException ie) {
	                    Thread.currentThread().interrupt();
	                }
	            } else {
	                return false;
	            }
	        }
	    }
	    return false;
	}
	
	//3rd TestCase
	public boolean OfficeDropDown(String officename)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(OfficeDropDown, "visible", "", 10);
			OfficeDropDown.isDisplayed();
		
		Select office = new Select(OfficeDropDown);
		office.selectByVisibleText(officename);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
	
	public boolean DepartmentDropDown(String Deptname)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(DepartmentDropDown, "visible", "", 10);
			DepartmentDropDown.isDisplayed();
		
		Select Department = new Select(DepartmentDropDown);
		Department.selectByVisibleText(Deptname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
	
	public boolean TeamDropDown(String teamname)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(TeamDropDown, "visible", "", 10);
			TeamDropDown.isDisplayed();
		
		Select Team = new Select(TeamDropDown);
		Team.selectByVisibleText(teamname);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
	
	public boolean AssignedEmpCountLink() {
	    int attempts = 0;

	    while (attempts < 2) {
	        try {
	        	Thread.sleep(4000);
	            // Re-locate the element in each attempt to avoid stale reference
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            WebElement element = wait.until(ExpectedConditions.visibilityOf(AssignedEmpCountLink));

	            if (element.isDisplayed()) {
	                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	                element.click();
	                return true;
	            } else {
	                throw new Exception("AssignedEmpCountLink is not displayed.");
	            }

	        } catch (StaleElementReferenceException staleEx) {
	            exceptionDesc = "Stale element reference in attempt " + (attempts + 1) + ": " + staleEx.getMessage();
	        } catch (Exception e) {
	            exceptionDesc = "AssignedEmpCountLink click failed: " + e.getMessage();
	            return false;
	        }

	        attempts++;
	        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
	    }

	    return false;
	}
  
	
	public boolean AssignedEmpCountLinkSearchTextField(String leavepolicyname)
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(AssignedEmpCountLinkSearchTextField, "visible", "", 10);
			AssignedEmpCountLinkSearchTextField.isDisplayed();
			AssignedEmpCountLinkSearchTextField.clear();
			AssignedEmpCountLinkSearchTextField.sendKeys(leavepolicyname);
		
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
	
	public boolean AssignedEmpCountLinkSearchResult(String firstnames) {
	    try {
	        Thread.sleep(4000); // Replace with explicit wait if needed
	        utils.waitForEle(AssignedEmpCountLinkSearchResult,  "visible", "", 10);

	        if (!AssignedEmpCountLinkSearchResult.isDisplayed()) {
	            throw new Exception("Searched Employee element not displayed.");
	        }

	         firstname = AssignedEmpCountLinkSearchResult.getText().trim();

	        // Check if the full text contains the expected text (case-insensitive)
	        if (firstname.toLowerCase().contains(firstnames.trim().toLowerCase())) {
	            return true;
	        } else {
	            throw new Exception("Employee Name mismatch: expected text '" + firstnames + "' not found in '" + firstname + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean EmployeeListLoaded()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(AssignedEmpCountLinkSearchResult, "visible", "", 20);
			AssignedEmpCountLinkSearchResult.isDisplayed();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
	
	//4th TestCase
	
	public boolean LeavePolicyDeleteIcon()
	{
		try {
		
			utils.waitForEle(LeavePolicyDeleteIcon, "visible", "", 10);
			LeavePolicyDeleteIcon.isDisplayed();
			LeavePolicyDeleteIcon.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
	
	
	
	public boolean AssignedEmpCount(String AssignedEmpCount) {
	    try {
	        Thread.sleep(3000);  

	             AssignedEmpCounts = AssignedEmpCountLink.getText();

	            if (AssignedEmpCounts.equalsIgnoreCase(AssignedEmpCount.trim())) {
	                
	                return true; // Successfully found 
	            }
	        

	    
	        throw new Exception("AssignedEmp Count '" + AssignedEmpCounts + "Is Not Proper");

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean AssignedEmpCountGreaterThanZero() {
	    try {
	        Thread.sleep(4000);  // Consider replacing with explicit wait for stability

	        // Wait for element visibility before interaction
	        utils.waitForEle(AssignedEmpCountLink, "visible", "", 10);

	        if (!AssignedEmpCountLink.isDisplayed()) {
	            throw new Exception("AssignedEmpCountLink is not displayed on the page.");
	        }

	        // Get the text and parse to integer
	         assignedEmpCountStr = AssignedEmpCountLink.getText().trim();
	        int empCount = Integer.parseInt(assignedEmpCountStr);

	        if (empCount > 0) {
	            return true;
	        } else {
	            throw new Exception("Assigned employee count is zero.");
	        }

	    } catch (NumberFormatException nfe) {
	        exceptionDesc = "Failed to parse assigned employee count: " + nfe.getMessage();
	        return false;
	    } catch (Exception e) {
	        exceptionDesc = "Error validating assigned employee count: " + e.getMessage();
	        return false;
	    }
	}

	//7th TestCase
	
	public boolean CreateLeaveTypeButton()
	{
		try {
		
			utils.waitForEle(CreateLeaveTypeButton, "visible", "", 10);
			CreateLeaveTypeButton.isDisplayed();
			CreateLeaveTypeButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
	
	
	
	public boolean SuccessMsg()
	{
		try {
		
			utils.waitForEle(SuccessMsg, "visible", "", 10);
			SuccessMsg.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
	
	
	
	
	public boolean AddLeaveTypeSaveButton()
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
	
	//9th TestCase
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
	
	public boolean EmployeeIDCheckBox()
	{
		try {
		
			utils.waitForEle(EmployeeIDCheckBox, "visible", "", 10);
			EmployeeIDCheckBox.isDisplayed();
			EmployeeIDCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DesignationCheckBox()
	{
		try {
		
			utils.waitForEle(DesignationCheckBox, "visible", "", 10);
			DesignationCheckBox.isDisplayed();
			DesignationCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean DepartmentCheckBox()
	{
		try {
		
			utils.waitForEle(DepartmentCheckBox, "visible", "", 10);
			DepartmentCheckBox.isDisplayed();
			DepartmentCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean OfficeCheckBox()
	{
		try {
		
			utils.waitForEle(OfficeCheckBox, "visible", "", 10);
			OfficeCheckBox.isDisplayed();
			OfficeCheckBox.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean EmpIdValidation(String expectedText) {
	    try {
	        Thread.sleep(3000); // Replace with explicit wait if needed
	        utils.waitForEle(EmpIdValidation,  "visible", "", 10);

	        if (!EmpIdValidation.isDisplayed()) {
	            throw new Exception("Emp Id element not displayed.");
	        }

	         FullEmpID = EmpIdValidation.getText().trim();

	        // Check if the full text contains the expected text (case-insensitive)
	        if (FullEmpID.toLowerCase().contains(expectedText.trim().toLowerCase())) {
	            return true;
	        } else {
	            throw new Exception("Emp ID mismatch: expected text '" + expectedText + "' not found in '" + FullEmpID + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean DesignationValidation(String expectedText) {
	    try {
	        Thread.sleep(3000); // Replace with explicit wait if needed
	        utils.waitForEle(DesignationValidation,  "visible", "", 10);

	        if (!DesignationValidation.isDisplayed()) {
	            throw new Exception("Emp Designation element not displayed.");
	        }

	         Designationname = DesignationValidation.getText().trim();

	        // Check if the full text contains the expected text (case-insensitive)
	        if (Designationname.toLowerCase().contains(expectedText.trim().toLowerCase())) {
	            return true;
	        } else {
	            throw new Exception("Emp ID mismatch: expected text '" + expectedText + "' not found in '" + Designationname + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean DepartmentValidation(String expectedText) {
	    try {
	        Thread.sleep(3000); // Replace with explicit wait if needed
	        utils.waitForEle(DepartmentValidation,  "visible", "", 10);

	        if (!DepartmentValidation.isDisplayed()) {
	            throw new Exception("Emp Department element not displayed.");
	        }

	         DepartmentName = DepartmentValidation.getText().trim();

	        // Check if the full text contains the expected text (case-insensitive)
	        if (DepartmentName.toLowerCase().contains(expectedText.trim().toLowerCase())) {
	            return true;
	        } else {
	            throw new Exception("Emp ID mismatch: expected text '" + expectedText + "' not found in '" + DepartmentName + "'");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	
	public boolean SelectPagination()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(SelectPagination, "visible", "", 10);
			SelectPagination.isDisplayed();
		
		Select office = new Select(SelectPagination);
		office.selectByVisibleText("100");
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	} 
	
	
	
	public boolean DeptOptionSelected()
	{
		try {
		Thread.sleep(2000);
		utils.waitForEle(DeptOptionSelected, "visible", "", 10);
			
			DeptOptionSelected.click();
			
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
