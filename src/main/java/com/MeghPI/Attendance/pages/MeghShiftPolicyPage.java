package com.MeghPI.Attendance.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utils;

public class MeghShiftPolicyPage {

	WebDriver driver;
	private static String exceptionDesc;
	Utils utils = new Utils(driver);
	public String firstrowshiftname = "";
	public String createdshiftname = "";
	public String actualFirstName = "";
	public String actualPolicyName = "";
	public String actualcountofemp ="";
	public String actualFirstRowNames = "";
	public String actualLastRowNames = "";
	public String employeeID ="";
	public String actualPolicySuffix = "";
	 
	
	public MeghShiftPolicyPage(WebDriver driver) {
		this.driver = driver;
		utils = new Utils(this.driver);
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	@FindBy(xpath = "//a[contains(text(),'Shift Policy')]")
	private WebElement ShiftPolicyButton; //1st TestCase
	
	@FindBy(xpath = "(//button[@id='btnAddShiftPolicy'])[2]")
	private WebElement AddShiftPolicyButton; //1st TestCase
	
	@FindBy(xpath = "//input[@id='txtPolicyName']")
	private WebElement PolicyNameTextField; //1st TestCase
	
	@FindBy(xpath = "//form[@id='frmShiftPolicy']/div[1]/div[2]/div[1]/div/div/div[2]/div/button[1]")
	private WebElement AddShiftButton; //1st TestCase
	
	@FindBy(xpath = "//label[text()='General Shift']")
	private WebElement GeneralShiftCheckBox; //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnAddSelectedShift']")
	private WebElement AddShiftSaveButton; //1st TestCase
	
	@FindBy(xpath = "//input[@type='search']")
	private WebElement ShiftSearchTextField; //1st TestCase
	
	@FindBy(xpath = "//table[@id='dtShiftPolicy']/tbody/tr[1]/td[1]/p/span")
	private WebElement FirstRowShiftName; //1st TestCase
	
	
	@FindBy(xpath = "//button[@id='btnCreatePolicy']")
	private WebElement CreatePolicySave; //1st TestCase
	
	@FindBy(xpath = "//button[@id='btnCreateShift']")
	private WebElement CreateShiftButton; //2nd TestCase
	
	@FindBy(xpath = "//input[@id='txtShiftName']")
	private WebElement ShiftNameTextField; //2nd TestCase
	
	@FindBy(xpath = "//input[@id='txtShiftCode']")
	private WebElement ShiftCodeTextField; //2nd TestCase
	
	@FindBy(xpath = "//input[@id='ShiftStartTime']/../input[2]")
	private WebElement ShiftStartTime; //2nd TestCase
	
	@FindBy(xpath = "//input[@id='ShiftEndTime']/../input[2]")
	private WebElement ShiftEndTime; //2nd TestCase
	
	@FindBy(xpath = "//input[@id='BufferingAllTime']/../input[2]")
	private WebElement BufferAllTime; //2nd TestCase
	
	@FindBy(xpath = "//input[@id='BufferingInTime']/../input[2]")
	private WebElement BufferingInTime; //2nd TestCase
	
	@FindBy(xpath = "//input[@id='BufferingOutTime']/../input[2]")
	private WebElement BufferingOutTime; //2nd TestCase
	
	@FindBy(xpath = "//input[@id='FullDayMin']/../input[2]")
	private WebElement FullDayTime; //2nd TestCase
	
	@FindBy(xpath = "//input[@id='HalfDayMin']/../input[2]")
	private WebElement HalfDayTime; //2nd TestCase
	
	@FindBy(xpath = "//button[@id='btnShiftSave']")
	private WebElement CreateShiftSaveButton; //2nd TestCase
	
	@FindBy(xpath = "//input[@aria-controls='dtShift']")
	private WebElement ShiftModuleSearchTextField; //2nd TestCase
	
	@FindBy(xpath = "//table[@id='dtShift']/tbody/tr[1]/td[1]")
	private WebElement ShiftSearchResult; //2nd TestCase
	
	@FindBy(xpath = "(//button[text()='Apply'])[1]")
	private WebElement ShiftAddEmpCriteriaApplyButton; //3rd TestCase
	
	@FindBy(xpath = "//table[@id='dtShiftPolicy']/tbody/tr[1]/td[3]/a/span")
	private WebElement AssignedEmpLinkedCount; //3rd TestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeDetails']/tbody/tr/td[1]/div/div/p[1]")
	private WebElement FirstEmpName; //3rd TestCase
	
	@FindBy(xpath = "(//p[text()='ShiftPolicy']/../p)[1]")
	private WebElement ShiftPolicyNameInManageProfile; //4th TestCase
	
	@FindBy(xpath = "//img[@alt='table-edit-icon']/..")
	private WebElement EditIcon; //7th TestCase
	
	@FindBy(xpath = "//input[@id='chkActive']")
	private WebElement SelectActiveCheckBox; //7th TestCase
	

	@FindBy(xpath = "//input[@id='ischxDefault']")
	private WebElement SelectDefaultCheckBox; //8th TestCase
	
	
	@FindBy(xpath = "//select[@name='dtShiftPolicy_length']")
	private WebElement PaginationDropDown; //9th TestCase
	
	
	@FindBy(xpath = "//table[@id='dtShiftPolicy']/tbody/tr/td[3]/a/span")
	private List<WebElement> AssignedCounts; //9th TestCase
	
	@FindBy(xpath = "(//button[@id='btnHeaderFilter'])[2]")
	private WebElement SearchDropDown; //9th TestCase
	
	@FindBy(xpath = "//label[text()='Last Name']")
	private WebElement LastNameCheckBox; //9th TestCase
	
	@FindBy(xpath = "//label[text()='Employee ID']")
	private WebElement EmpIDCheckBox; //9th TestCase
	
	@FindBy(xpath = "//label[text()='Designation']")
	private WebElement DesignationCheckBox; //9th TestCase
	
	@FindBy(xpath = "//label[text()='Department']")
	private WebElement DepartmentCheckBox; //9th TestCase
	
	@FindBy(xpath = "//label[text()='Office']")
	private WebElement OfficeCheckBox; //9th TestCase
	
	@FindBy(xpath = "//table[@id='dtEmployeeDetails']/tbody/tr[1]/td[1]/div/div/p[2]")
	private WebElement EmpID; //9th TestCase
	
	
	@FindBy(xpath = "//input[@aria-controls='dtEmployeeDetails']")
	private WebElement ClearTextField; //9th TestCase
	
	@FindBy(xpath = "//div[@id='policyMaster']/div/h3")
	private WebElement ShiftMasterLoaded; //9th TestCase
	
	@FindBy(xpath = "//div[text()='Policy Name']")
	private WebElement ShiftPolicyPageLoaded; //9th TestCase
	
	@FindBy(xpath = "//div[@id='policyMaster']/div/div/div[3]/div/div/ul/li[1]/a")
	private WebElement ShiftButtonFromSidebar; //9th TestCase
	
	@FindBy(xpath = "(//input[@aria-label='Hour'])[1]")
	private WebElement ShiftTimingsMouseOver; //9th TestCase
	
	public boolean ShiftPolicyButton()
	{
		try {
			utils.waitForEle(ShiftPolicyButton, "visible", "", 10);
			ShiftPolicyButton.isDisplayed();
			ShiftPolicyButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddShiftPolicyButton()
	{
		try {
			utils.waitForEle(AddShiftPolicyButton, "visible", "", 10);
			AddShiftPolicyButton.isDisplayed();
			AddShiftPolicyButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean PolicyNameTextField(String policyname) {
		try {

			utils.waitForEle(PolicyNameTextField,  "visible", "", 10);
			PolicyNameTextField.isDisplayed();
			PolicyNameTextField.clear();
			PolicyNameTextField.sendKeys(policyname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddShiftButton()
	{
		try {
			utils.waitForEle(AddShiftButton, "visible", "", 10);
		
			AddShiftButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean GeneralShiftCheckBox()
	{
		try {
			utils.waitForEle(GeneralShiftCheckBox, "visible", "", 10);
			GeneralShiftCheckBox.isDisplayed();
			if(!GeneralShiftCheckBox.isSelected())
			{
				GeneralShiftCheckBox.click();
			}
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AddShiftSaveButton()
	{
		try {
			utils.waitForEle(AddShiftSaveButton, "visible", "", 10);
			AddShiftSaveButton.isDisplayed();
			AddShiftSaveButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftSearchTextField(String policyname) {
		try {
			Thread.sleep(8000);

			utils.waitForEle(ShiftSearchTextField, "visible", "", 10);
			ShiftSearchTextField.isDisplayed();
			ShiftSearchTextField.clear();
			ShiftSearchTextField.sendKeys(policyname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FirstRowShiftName()
	{
		try {
			utils.waitForEle(FirstRowShiftName, "visible", "", 10);
			FirstRowShiftName.isDisplayed();
		firstrowshiftname =	FirstRowShiftName.getText();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FirstRowShiftNameComparison(String shiftname) {
	    try {
	    	Thread.sleep(4000);
	        if (firstrowshiftname.equalsIgnoreCase(shiftname)) {
	            return true;
	        } else {
	            throw new Exception("Assigned employee comparison failed: values do not match.");
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	public boolean CreatePolicySave()
	{
		try {
			utils.waitForEle(CreatePolicySave, "visible", "", 10);
			CreatePolicySave.isDisplayed();
			CreatePolicySave.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//2nd TestCase
	
	public boolean CreateShiftButton()
	{
		try {
			utils.waitForEle(CreateShiftButton, "visible", "", 10);
			CreateShiftButton.isDisplayed();
			CreateShiftButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftNameTextField(String shiftname) {
		try {

			utils.waitForEle(ShiftNameTextField,  "visible", "", 10);
			ShiftNameTextField.isDisplayed();
			ShiftNameTextField.clear();
			ShiftNameTextField.sendKeys(shiftname);
			Thread.sleep(4000);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftCodeTextField(String shiftcode) {
		try {

			utils.waitForEle(ShiftCodeTextField,  "visible", "", 10);
			ShiftCodeTextField.isDisplayed();
			ShiftCodeTextField.clear();
			ShiftCodeTextField.sendKeys(shiftcode);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftStartTimeClick()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(ShiftStartTime, "visible", "", 10);
			
			ShiftStartTime.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean ShiftStartTime(String shiftstarttime) {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Remove readonly just in case, though Flatpickr API doesn't need it
	        js.executeScript("document.getElementById('ShiftStartTime').removeAttribute('readonly');");

	        // Use Flatpickr's setDate API
	        js.executeScript(
	            "if (document.getElementById('ShiftStartTime')._flatpickr) {" +
	            "  document.getElementById('ShiftStartTime')._flatpickr.setDate('" + shiftstarttime + "', true);" +
	            "} else { throw new Error('Flatpickr not initialized on ShiftStartTime'); }"
	        );

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}


	
	public boolean ShiftEndTimeClick()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(ShiftEndTime, "visible", "", 10);
	
			ShiftEndTime.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftEndTime(String shiftendtime) {
		 try {
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('ShiftEndTime').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('ShiftEndTime')._flatpickr) {" +
		            "  document.getElementById('ShiftEndTime')._flatpickr.setDate('" + shiftendtime + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on ShiftEndTime'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean BufferAllTimeClick()
	{
		try {
			utils.waitForEle(BufferAllTime, "visible", "", 10);
			BufferAllTime.isDisplayed();
			BufferAllTime.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}

	public boolean BufferAllTime(String shiftbuffertime) {
		 try {
		        JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Remove readonly just in case, though Flatpickr API doesn't need it
		        js.executeScript("document.getElementById('BufferingAllTime').removeAttribute('readonly');");

		        // Use Flatpickr's setDate API
		        js.executeScript(
		            "if (document.getElementById('BufferingAllTime')._flatpickr) {" +
		            "  document.getElementById('BufferingAllTime')._flatpickr.setDate('" + shiftbuffertime + "', true);" +
		            "} else { throw new Error('Flatpickr not initialized on BufferingAllTime'); }"
		        );

		    } catch (Exception e) {
		        exceptionDesc = e.getMessage();
		        return false;
		    }
		    return true;
		}
	
	public boolean BufferingInTimeClick()
	{
		try {
			utils.waitForEle(BufferingInTime, "visible", "", 10);
			BufferingInTime.isDisplayed();
			BufferingInTime.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean BufferingInTime(String BufferingInTime) {
		try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Remove readonly just in case, though Flatpickr API doesn't need it
	        js.executeScript("document.getElementById('BufferingInTime').removeAttribute('readonly');");

	        // Use Flatpickr's setDate API
	        js.executeScript(
	            "if (document.getElementById('BufferingInTime')._flatpickr) {" +
	            "  document.getElementById('BufferingInTime')._flatpickr.setDate('" + BufferingInTime + "', true);" +
	            "} else { throw new Error('Flatpickr not initialized on BufferingInTime'); }"
	        );

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean BufferingOutTimeClick()
	{
		try {
			utils.waitForEle(BufferingOutTime, "visible", "", 10);
			BufferingOutTime.isDisplayed();
			BufferingOutTime.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean BufferingOutTime(String BufferingOutTime) {
		try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Remove readonly just in case, though Flatpickr API doesn't need it
	        js.executeScript("document.getElementById('BufferingOutTime').removeAttribute('readonly');");

	        // Use Flatpickr's setDate API
	        js.executeScript(
	            "if (document.getElementById('BufferingOutTime')._flatpickr) {" +
	            "  document.getElementById('BufferingOutTime')._flatpickr.setDate('" + BufferingOutTime + "', true);" +
	            "} else { throw new Error('Flatpickr not initialized on BufferingOutTime'); }"
	        );

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean FullDayTimeClick()
	{
		try {
			utils.waitForEle(FullDayTime, "visible", "", 10);
			FullDayTime.isDisplayed();
			FullDayTime.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FullDayTime(String FullDayMin) {
		try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Remove readonly just in case, though Flatpickr API doesn't need it
	        js.executeScript("document.getElementById('FullDayMin').removeAttribute('readonly');");

	        // Use Flatpickr's setDate API
	        js.executeScript(
	            "if (document.getElementById('FullDayMin')._flatpickr) {" +
	            "  document.getElementById('FullDayMin')._flatpickr.setDate('" + FullDayMin + "', true);" +
	            "} else { throw new Error('Flatpickr not initialized on FullDayMin'); }"
	        );

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean HalfDayTimeClick()
	{
		try {
			utils.waitForEle(HalfDayTime, "visible", "", 10);
			HalfDayTime.isDisplayed();
			HalfDayTime.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean HalfDayTime(String HalfDayMin) {
		try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Remove readonly just in case, though Flatpickr API doesn't need it
	        js.executeScript("document.getElementById('HalfDayMin').removeAttribute('readonly');");

	        // Use Flatpickr's setDate API
	        js.executeScript(
	            "if (document.getElementById('HalfDayMin')._flatpickr) {" +
	            "  document.getElementById('HalfDayMin')._flatpickr.setDate('" + HalfDayMin + "', true);" +
	            "} else { throw new Error('Flatpickr not initialized on HalfDayMin'); }"
	        );

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean CreateShiftSaveButton()
	{
		try {
			utils.waitForEle(CreateShiftSaveButton, "visible", "", 10);

			CreateShiftSaveButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean BrowserRefresh()
	{
		try {
			driver.navigate().refresh();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftModuleSearchTextField(String shiftname) {
		try {

			Thread.sleep(4000);
			
			utils.waitForEle(ShiftModuleSearchTextField,  "visible", "", 10);
			ShiftModuleSearchTextField.isDisplayed();
			ShiftModuleSearchTextField.clear();
			ShiftModuleSearchTextField.sendKeys(shiftname);
			
		} catch (Exception e) {
			exceptionDesc = e.getMessage().toString();
			return false;
		}
		return true;
	}	
	
	public boolean ShiftSearchResult()
	{
		try {
			utils.waitForEle(ShiftSearchResult, "visible", "", 10);
			ShiftSearchResult.isDisplayed();
		createdshiftname =	ShiftSearchResult.getText();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean CreatedShiftNameComparison(String shiftname) {
	    try {
	    	Thread.sleep(4000);
	        if (createdshiftname.equalsIgnoreCase(shiftname)) {
	            return true;
	        } else {
	            throw new Exception("Created ShiftName comparison failed: values do not match.");
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}	
	
	//3rd TestCase
	
	public boolean ShiftAddEmpCriteriaApplyButton()
	{
		try {
			utils.waitForEle(ShiftAddEmpCriteriaApplyButton, "visible", "", 10);
			ShiftAddEmpCriteriaApplyButton.isDisplayed();
			ShiftAddEmpCriteriaApplyButton.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean AssignedEmpLinkedCount()
	{
		try {
			Thread.sleep(8000);
			utils.waitForEle(AssignedEmpLinkedCount, "visible", "", 10);
			AssignedEmpLinkedCount.isDisplayed();
			AssignedEmpLinkedCount.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean FirstEmpName(String expectedFirstName) {
	    try {
	        utils.waitForEle(FirstEmpName, "visible", "", 10);

	        if (FirstEmpName.isDisplayed()) {
	            String fullText = FirstEmpName.getText(); // e.g., "Autofn090525123900 Autoln090525123900"
	             actualFirstName = fullText.split(" ")[0]; // Extract first part before space
	            
	            if (actualFirstName.equalsIgnoreCase(expectedFirstName)) {
	                System.out.println("First name matched: " + actualFirstName);
	                return true;
	            } else {
	                throw new Exception("First name does not match. Expected: " + expectedFirstName + ", Actual: " + actualFirstName);
	            }

	        } else {
	            throw new Exception("EmployeeFirstName element is not visible");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}
	
	//4th TestCase
	public boolean FirstEmpNames() {
	    try {
	    	Thread.sleep(4000);
	        utils.waitForEle(FirstEmpName, "visible", "", 10);

	        FirstEmpName.isDisplayed(); 
	            String fullText = FirstEmpName.getText(); // e.g., "Autofn090525123900 Autoln090525123900"
	             actualFirstName = fullText.split(" ")[0]; // Extract first part before space
	       
	       
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	
	public boolean ShiftPolicyNameInManageProfile(String expectedPolicyName) {
	    try {
	        utils.waitForEle(ShiftPolicyNameInManageProfile, "visible", "", 10);

	        if (ShiftPolicyNameInManageProfile.isDisplayed()) {
	            actualPolicyName = ShiftPolicyNameInManageProfile.getText().trim();

	            if (actualPolicyName.toLowerCase().contains(expectedPolicyName.trim().toLowerCase())) {
	                return true;
	            } else {
	                throw new Exception("Policy name mismatch: Expected text to contain '" + expectedPolicyName + "', but found '" + actualPolicyName + "'");
	            }
	        } else {
	            throw new Exception("ShiftPolicyName element is not displayed.");
	        }
	    } catch (Exception e) {
	        exceptionDesc = e.getMessage(); // assuming exceptionDesc is defined at class level
	        return false;
	    }
	}


	//6th TestCase
	
	public boolean AssignedEmpLinkedCounts(String expectedCount) {
	    try {
	        utils.waitForEle(AssignedEmpLinkedCount, "visible", "", 10);

	        if (AssignedEmpLinkedCount.isDisplayed()) {
	        	actualcountofemp = AssignedEmpLinkedCount.getText().trim();
	            if (actualcountofemp.equalsIgnoreCase(expectedCount.trim())) {
	                return true;
	            } else {
	                throw new Exception("Employee count mismatch: Expected '" + expectedCount + "', but found '" + actualcountofemp + "'");
	            }
	        } else {
	            throw new Exception("AssignedEmpLinkedCount element is not visible.");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

	//7th TestCase
	
	public boolean EditIcon()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(EditIcon, "visible", "", 10);
			EditIcon.isDisplayed();
			EditIcon.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean SelectActiveCheckBox()
	{
		try {
			Thread.sleep(4000);
			utils.waitForEle(SelectActiveCheckBox, "visible", "", 10);
			SelectActiveCheckBox.isDisplayed();
	if(!SelectActiveCheckBox.isSelected())
	{
		SelectActiveCheckBox.click();
		Thread.sleep(3000);
	}
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//8th TestCase
	
	public boolean SelectDefaultCheckBox() {
	    try {
	        utils.waitForEle(SelectDefaultCheckBox, "visible", "", 10);

	        if (SelectDefaultCheckBox.isDisplayed()) {
	            if (!SelectDefaultCheckBox.isEnabled()) {
	                // Checkbox is disabled (already default or not editable)
	                return true;
	            }

	            // If checkbox is enabled
	            if (!SelectDefaultCheckBox.isSelected()) {
	                // If not already selected, click to select it
	                SelectDefaultCheckBox.click();
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

	
	//9th TestCase
	public boolean PaginationDropDown()
	{
		try {
			Thread.sleep(3000);
			utils.waitForEle(PaginationDropDown, "visible", "", 10);
			PaginationDropDown.isDisplayed();
Select CheckBox = new Select(PaginationDropDown);
CheckBox.selectByIndex(2);
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	//9th TestCase
	
	public boolean AssignedEmpLinkedCountIfGreaterThanZero() {
	    try {
	      

	        for (WebElement linkedCount : AssignedCounts) {
	            if (linkedCount.isDisplayed()) {
	                String countText = linkedCount.getText().trim();

	                // Handle non-integer cases safely
	                int count = Integer.parseInt(countText);

	                if (count > 0) {
	                    linkedCount.click(); // click the element with count > 0
	                    return true;
	                }
	            } else {
	                throw new Exception("Assigned count element is not visible.");
	            }
	        }

	        // No elements had count > 0, but functionally OK
	        return true;

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}

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
	
	public boolean EmpIDCheckBox()
	{
		try {
			utils.waitForEle(EmpIDCheckBox, "visible", "", 10);
			EmpIDCheckBox.isDisplayed();
			EmpIDCheckBox.click();
			
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
	
	public boolean FirstrowEmpName() {
	    try {
	        utils.waitForEle(FirstEmpName, "visible", "", 10);

	        if (FirstEmpName.isDisplayed()) {
	            String fullText = FirstEmpName.getText(); // e.g., "Autofn090525123900 Autoln090525123900"
	             actualFirstRowNames = fullText.split(" ")[0]; // Extract first part before space
	
	        } else {
	            throw new Exception("EmployeeFirstName element is not visible");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean FirstrowEmpLastName() {
	    try {
	        utils.waitForEle(FirstEmpName, "visible", "", 10);

	        if (FirstEmpName.isDisplayed()) {
	            String fullText = FirstEmpName.getText(); // e.g., "Autofn090525123900 Autoln090525123900"
	             actualLastRowNames = fullText.split(" ")[1]; // Extract first part before space
	
	        } else {
	            throw new Exception("EmployeeFirstName element is not visible");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	public boolean EmpID() {
	    try {
	        utils.waitForEle(EmpID, "visible", "", 10);

	        if (EmpID.isDisplayed()) {
	            String fullText = EmpID.getText(); 
	             employeeID = fullText.startsWith("#") ? fullText.substring(1) : fullText;// Remove the '#' if it exists

	
	        } else {
	            throw new Exception("EmployeeFirstName element is not visible");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	    return true;
	}
	
	
	public boolean ClearTextField()
	{
		try {
			utils.waitForEle(ClearTextField, "visible", "", 10);
			ClearTextField.isDisplayed();
			ClearTextField.clear();
			ClearTextField.click();
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	public boolean ShiftPolicyNameInManageProfiles(String expectedPolicyName) {
	    try {
	    	Thread.sleep(6000);
	        utils.waitForEle(ShiftPolicyNameInManageProfile, "visible", "", 10);

	        if (ShiftPolicyNameInManageProfile.isDisplayed()) {
	            String fullText = ShiftPolicyNameInManageProfile.getText().trim(); // e.g., "auto34333 (Default)"

	            System.out.println("Expected: " + expectedPolicyName + " | Actual: " + fullText);

	            if (fullText.toLowerCase().contains(expectedPolicyName.trim().toLowerCase())) {
	                return true;
	            } else {
	                throw new Exception("Policy name mismatch: Expected to contain '" + expectedPolicyName + "', but got '" + fullText + "'");
	            }

	        } else {
	            throw new Exception("ShiftPolicyName element is not displayed.");
	        }

	    } catch (Exception e) {
	        exceptionDesc = e.getMessage();
	        return false;
	    }
	}



	
	public boolean ShiftMasterLoaded()
	{
		try {
			utils.waitForEle(ShiftMasterLoaded, "visible", "", 10);
			ShiftMasterLoaded.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	
	public boolean ShiftPolicyPageLoaded()
	{
		try {
			utils.waitForEle(ShiftPolicyPageLoaded, "visible", "", 20);
			ShiftPolicyPageLoaded.isDisplayed();
			
			
		} catch (Exception e) {
			exceptionDesc=	e.getMessage().toString();
			return false;
		}
		return true;
	}
	
	
	public boolean ShiftButtonFromSidebar()
	{
		try {
			utils.waitForEle(ShiftButtonFromSidebar, "visible", "", 10);
			ShiftButtonFromSidebar.isDisplayed();
			ShiftButtonFromSidebar.click();
			
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
